package android.support.design.widget;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.C0064R;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.math.MathUtils;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int activePointerId;
    private BottomSheetCallback callback;
    int collapsedOffset;
    private final ViewDragHelper.Callback dragCallback;
    /* access modifiers changed from: private */
    public boolean fitToContents = true;
    int fitToContentsOffset;
    int halfExpandedOffset;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int lastNestedScrollDy;
    private int lastPeekHeight;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightMin;
    private boolean skipCollapsed;
    int state = 4;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);

        public BottomSheetCallback() {
        }
    }

    public BottomSheetBehavior() {
        ViewDragHelper.Callback callback2;
        new ViewDragHelper.Callback(this) {
            final /* synthetic */ BottomSheetBehavior this$0;

            {
                this.this$0 = this$0;
            }

            public boolean tryCaptureView(@NonNull View view, int i) {
                View scroll;
                View child = view;
                int pointerId = i;
                if (this.this$0.state == 1) {
                    return false;
                }
                if (this.this$0.touchingScrollingChild) {
                    return false;
                }
                if (this.this$0.state == 3 && this.this$0.activePointerId == pointerId && (scroll = (View) this.this$0.nestedScrollingChildRef.get()) != null && scroll.canScrollVertically(-1)) {
                    return false;
                }
                return this.this$0.viewRef != null && this.this$0.viewRef.get() == child;
            }

            public void onViewPositionChanged(@NonNull View view, int i, int top, int i2, int i3) {
                View view2 = view;
                int i4 = i;
                int i5 = i2;
                int i6 = i3;
                this.this$0.dispatchOnSlide(top);
            }

            public void onViewDragStateChanged(int state) {
                if (state == 1) {
                    this.this$0.setStateInternal(1);
                }
            }

            public void onViewReleased(@NonNull View view, float f, float f2) {
                int top;
                int targetState;
                Runnable runnable;
                View releasedChild = view;
                float xvel = f;
                float yvel = f2;
                if (yvel < 0.0f) {
                    if (this.this$0.fitToContents) {
                        top = this.this$0.fitToContentsOffset;
                        targetState = 3;
                    } else if (releasedChild.getTop() > this.this$0.halfExpandedOffset) {
                        top = this.this$0.halfExpandedOffset;
                        targetState = 6;
                    } else {
                        top = 0;
                        targetState = 3;
                    }
                } else if (this.this$0.hideable && this.this$0.shouldHide(releasedChild, yvel) && (releasedChild.getTop() > this.this$0.collapsedOffset || Math.abs(xvel) < Math.abs(yvel))) {
                    top = this.this$0.parentHeight;
                    targetState = 5;
                } else if (yvel == 0.0f || Math.abs(xvel) > Math.abs(yvel)) {
                    int currentTop = releasedChild.getTop();
                    if (this.this$0.fitToContents) {
                        if (Math.abs(currentTop - this.this$0.fitToContentsOffset) < Math.abs(currentTop - this.this$0.collapsedOffset)) {
                            top = this.this$0.fitToContentsOffset;
                            targetState = 3;
                        } else {
                            top = this.this$0.collapsedOffset;
                            targetState = 4;
                        }
                    } else if (currentTop < this.this$0.halfExpandedOffset) {
                        if (currentTop < Math.abs(currentTop - this.this$0.collapsedOffset)) {
                            top = 0;
                            targetState = 3;
                        } else {
                            top = this.this$0.halfExpandedOffset;
                            targetState = 6;
                        }
                    } else if (Math.abs(currentTop - this.this$0.halfExpandedOffset) < Math.abs(currentTop - this.this$0.collapsedOffset)) {
                        top = this.this$0.halfExpandedOffset;
                        targetState = 6;
                    } else {
                        top = this.this$0.collapsedOffset;
                        targetState = 4;
                    }
                } else {
                    top = this.this$0.collapsedOffset;
                    targetState = 4;
                }
                if (this.this$0.viewDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top)) {
                    this.this$0.setStateInternal(2);
                    new SettleRunnable(this.this$0, releasedChild, targetState);
                    ViewCompat.postOnAnimation(releasedChild, runnable);
                    return;
                }
                this.this$0.setStateInternal(targetState);
            }

            public int clampViewPositionVertical(@NonNull View view, int top, int i) {
                View view2 = view;
                int i2 = i;
                return MathUtils.clamp(top, this.this$0.getExpandedOffset(), this.this$0.hideable ? this.this$0.parentHeight : this.this$0.collapsedOffset);
            }

            public int clampViewPositionHorizontal(@NonNull View child, int i, int i2) {
                int i3 = i;
                int i4 = i2;
                return child.getLeft();
            }

            public int getViewVerticalDragRange(@NonNull View view) {
                View view2 = view;
                if (this.this$0.hideable) {
                    return this.this$0.parentHeight;
                }
                return this.this$0.collapsedOffset;
            }
        };
        this.dragCallback = callback2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BottomSheetBehavior(android.content.Context r12, android.util.AttributeSet r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r6 = r0
            r7 = r1
            r8 = r2
            r6.<init>(r7, r8)
            r6 = r0
            r7 = 1
            r6.fitToContents = r7
            r6 = r0
            r7 = 4
            r6.state = r7
            r6 = r0
            android.support.design.widget.BottomSheetBehavior$2 r7 = new android.support.design.widget.BottomSheetBehavior$2
            r10 = r7
            r7 = r10
            r8 = r10
            r9 = r0
            r8.<init>(r9)
            r6.dragCallback = r7
            r6 = r1
            r7 = r2
            int[] r8 = android.support.design.C0064R.styleable.BottomSheetBehavior_Layout
            android.content.res.TypedArray r6 = r6.obtainStyledAttributes(r7, r8)
            r3 = r6
            r6 = r3
            int r7 = android.support.design.C0064R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight
            android.util.TypedValue r6 = r6.peekValue(r7)
            r4 = r6
            r6 = r4
            if (r6 == 0) goto L_0x0076
            r6 = r4
            int r6 = r6.data
            r7 = -1
            if (r6 != r7) goto L_0x0076
            r6 = r0
            r7 = r4
            int r7 = r7.data
            r6.setPeekHeight(r7)
        L_0x003e:
            r6 = r0
            r7 = r3
            int r8 = android.support.design.C0064R.styleable.BottomSheetBehavior_Layout_behavior_hideable
            r9 = 0
            boolean r7 = r7.getBoolean(r8, r9)
            r6.setHideable(r7)
            r6 = r0
            r7 = r3
            int r8 = android.support.design.C0064R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents
            r9 = 1
            boolean r7 = r7.getBoolean(r8, r9)
            r6.setFitToContents(r7)
            r6 = r0
            r7 = r3
            int r8 = android.support.design.C0064R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed
            r9 = 0
            boolean r7 = r7.getBoolean(r8, r9)
            r6.setSkipCollapsed(r7)
            r6 = r3
            r6.recycle()
            r6 = r1
            android.view.ViewConfiguration r6 = android.view.ViewConfiguration.get(r6)
            r5 = r6
            r6 = r0
            r7 = r5
            int r7 = r7.getScaledMaximumFlingVelocity()
            float r7 = (float) r7
            r6.maximumVelocity = r7
            return
        L_0x0076:
            r6 = r0
            r7 = r3
            int r8 = android.support.design.C0064R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight
            r9 = -1
            int r7 = r7.getDimensionPixelSize(r8, r9)
            r6.setPeekHeight(r7)
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout parent, V child) {
        Parcelable parcelable;
        new SavedState(super.onSaveInstanceState(parent, child), this.state);
        return parcelable;
    }

    public void onRestoreInstanceState(CoordinatorLayout parent, V child, Parcelable state2) {
        SavedState ss = (SavedState) state2;
        super.onRestoreInstanceState(parent, child, ss.getSuperState());
        if (ss.state == 1 || ss.state == 2) {
            this.state = 4;
            return;
        }
        this.state = ss.state;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        WeakReference<V> weakReference;
        WeakReference<View> weakReference2;
        CoordinatorLayout parent = coordinatorLayout;
        V child = v;
        int layoutDirection = i;
        if (ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
            child.setFitsSystemWindows(true);
        }
        int savedTop = child.getTop();
        parent.onLayoutChild(child, layoutDirection);
        this.parentHeight = parent.getHeight();
        if (this.peekHeightAuto) {
            if (this.peekHeightMin == 0) {
                this.peekHeightMin = parent.getResources().getDimensionPixelSize(C0064R.dimen.design_bottom_sheet_peek_height_min);
            }
            this.lastPeekHeight = Math.max(this.peekHeightMin, this.parentHeight - ((parent.getWidth() * 9) / 16));
        } else {
            this.lastPeekHeight = this.peekHeight;
        }
        this.fitToContentsOffset = Math.max(0, this.parentHeight - child.getHeight());
        this.halfExpandedOffset = this.parentHeight / 2;
        calculateCollapsedOffset();
        if (this.state == 3) {
            ViewCompat.offsetTopAndBottom(child, getExpandedOffset());
        } else if (this.state == 6) {
            ViewCompat.offsetTopAndBottom(child, this.halfExpandedOffset);
        } else if (this.hideable && this.state == 5) {
            ViewCompat.offsetTopAndBottom(child, this.parentHeight);
        } else if (this.state == 4) {
            ViewCompat.offsetTopAndBottom(child, this.collapsedOffset);
        } else if (this.state == 1 || this.state == 2) {
            ViewCompat.offsetTopAndBottom(child, savedTop - child.getTop());
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(parent, this.dragCallback);
        }
        new WeakReference<>(child);
        this.viewRef = weakReference;
        new WeakReference<>(findScrollingChild(child));
        this.nestedScrollingChildRef = weakReference2;
        return true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z;
        CoordinatorLayout parent = coordinatorLayout;
        V child = v;
        MotionEvent event = motionEvent;
        if (!child.isShown()) {
            this.ignoreEvents = true;
            return false;
        }
        int action = event.getActionMasked();
        if (action == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(event);
        switch (action) {
            case 0:
                int initialX = (int) event.getX();
                this.initialY = (int) event.getY();
                View scroll = this.nestedScrollingChildRef != null ? (View) this.nestedScrollingChildRef.get() : null;
                if (scroll != null && parent.isPointInChildBounds(scroll, initialX, this.initialY)) {
                    this.activePointerId = event.getPointerId(event.getActionIndex());
                    this.touchingScrollingChild = true;
                }
                this.ignoreEvents = this.activePointerId == -1 && !parent.isPointInChildBounds(child, initialX, this.initialY);
                break;
            case 1:
            case 3:
                this.touchingScrollingChild = false;
                this.activePointerId = -1;
                if (this.ignoreEvents) {
                    this.ignoreEvents = false;
                    return false;
                }
                break;
        }
        if (!this.ignoreEvents && this.viewDragHelper != null && this.viewDragHelper.shouldInterceptTouchEvent(event)) {
            return true;
        }
        View scroll2 = this.nestedScrollingChildRef != null ? (View) this.nestedScrollingChildRef.get() : null;
        if (action != 2 || scroll2 == null || this.ignoreEvents || this.state == 1 || parent.isPointInChildBounds(scroll2, (int) event.getX(), (int) event.getY()) || this.viewDragHelper == null || Math.abs(((float) this.initialY) - event.getY()) <= ((float) this.viewDragHelper.getTouchSlop())) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V child = v;
        MotionEvent event = motionEvent;
        if (!child.isShown()) {
            return false;
        }
        int action = event.getActionMasked();
        if (this.state == 1 && action == 0) {
            return true;
        }
        if (this.viewDragHelper != null) {
            this.viewDragHelper.processTouchEvent(event);
        }
        if (action == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(event);
        if (action == 2 && !this.ignoreEvents && Math.abs(((float) this.initialY) - event.getY()) > ((float) this.viewDragHelper.getTouchSlop())) {
            this.viewDragHelper.captureChildView(child, event.getPointerId(event.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int axes, int i) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V v2 = v;
        View view3 = view;
        View view4 = view2;
        int i2 = i;
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (axes & 2) != 0;
    }

    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i, int i2, @NonNull int[] iArr, int type) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V child = v;
        View target = view;
        int i3 = i;
        int dy = i2;
        int[] consumed = iArr;
        if (type != 1 && target == ((View) this.nestedScrollingChildRef.get())) {
            int currentTop = child.getTop();
            int newTop = currentTop - dy;
            if (dy > 0) {
                if (newTop < getExpandedOffset()) {
                    consumed[1] = currentTop - getExpandedOffset();
                    ViewCompat.offsetTopAndBottom(child, -consumed[1]);
                    setStateInternal(3);
                } else {
                    consumed[1] = dy;
                    ViewCompat.offsetTopAndBottom(child, -dy);
                    setStateInternal(1);
                }
            } else if (dy < 0 && !target.canScrollVertically(-1)) {
                if (newTop <= this.collapsedOffset || this.hideable) {
                    consumed[1] = dy;
                    ViewCompat.offsetTopAndBottom(child, -dy);
                    setStateInternal(1);
                } else {
                    consumed[1] = currentTop - this.collapsedOffset;
                    ViewCompat.offsetTopAndBottom(child, -consumed[1]);
                    setStateInternal(4);
                }
            }
            dispatchOnSlide(child.getTop());
            this.lastNestedScrollDy = dy;
            this.nestedScrolled = true;
        }
    }

    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i) {
        int top;
        int targetState;
        Runnable runnable;
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V child = v;
        View target = view;
        int i2 = i;
        if (child.getTop() == getExpandedOffset()) {
            setStateInternal(3);
        } else if (target == this.nestedScrollingChildRef.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
                top = getExpandedOffset();
                targetState = 3;
            } else if (this.hideable && shouldHide(child, getYVelocity())) {
                top = this.parentHeight;
                targetState = 5;
            } else if (this.lastNestedScrollDy == 0) {
                int currentTop = child.getTop();
                if (this.fitToContents) {
                    if (Math.abs(currentTop - this.fitToContentsOffset) < Math.abs(currentTop - this.collapsedOffset)) {
                        top = this.fitToContentsOffset;
                        targetState = 3;
                    } else {
                        top = this.collapsedOffset;
                        targetState = 4;
                    }
                } else if (currentTop < this.halfExpandedOffset) {
                    if (currentTop < Math.abs(currentTop - this.collapsedOffset)) {
                        top = 0;
                        targetState = 3;
                    } else {
                        top = this.halfExpandedOffset;
                        targetState = 6;
                    }
                } else if (Math.abs(currentTop - this.halfExpandedOffset) < Math.abs(currentTop - this.collapsedOffset)) {
                    top = this.halfExpandedOffset;
                    targetState = 6;
                } else {
                    top = this.collapsedOffset;
                    targetState = 4;
                }
            } else {
                top = this.collapsedOffset;
                targetState = 4;
            }
            if (this.viewDragHelper.smoothSlideViewTo(child, child.getLeft(), top)) {
                setStateInternal(2);
                new SettleRunnable(this, child, targetState);
                ViewCompat.postOnAnimation(child, runnable);
            } else {
                setStateInternal(targetState);
            }
            this.nestedScrolled = false;
        }
    }

    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View view, float velocityX, float velocityY) {
        View target = view;
        return target == this.nestedScrollingChildRef.get() && (this.state != 3 || super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY));
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public void setFitToContents(boolean z) {
        boolean fitToContents2 = z;
        if (this.fitToContents != fitToContents2) {
            this.fitToContents = fitToContents2;
            if (this.viewRef != null) {
                calculateCollapsedOffset();
            }
            setStateInternal((!this.fitToContents || this.state != 6) ? this.state : 3);
        }
    }

    public final void setPeekHeight(int i) {
        V view;
        int peekHeight2 = i;
        boolean layout = false;
        if (peekHeight2 == -1) {
            if (!this.peekHeightAuto) {
                this.peekHeightAuto = true;
                layout = true;
            }
        } else if (this.peekHeightAuto || this.peekHeight != peekHeight2) {
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, peekHeight2);
            this.collapsedOffset = this.parentHeight - peekHeight2;
            layout = true;
        }
        if (layout && this.state == 4 && this.viewRef != null && (view = (View) this.viewRef.get()) != null) {
            view.requestLayout();
        }
    }

    public final int getPeekHeight() {
        return this.peekHeightAuto ? -1 : this.peekHeight;
    }

    public void setHideable(boolean hideable2) {
        boolean z = hideable2;
        this.hideable = z;
    }

    public boolean isHideable() {
        return this.hideable;
    }

    public void setSkipCollapsed(boolean skipCollapsed2) {
        boolean z = skipCollapsed2;
        this.skipCollapsed = z;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public void setBottomSheetCallback(BottomSheetCallback callback2) {
        BottomSheetCallback bottomSheetCallback = callback2;
        this.callback = bottomSheetCallback;
    }

    public final void setState(int i) {
        Runnable runnable;
        int state2 = i;
        if (state2 != this.state) {
            if (this.viewRef != null) {
                V child = (View) this.viewRef.get();
                if (child != null) {
                    ViewParent parent = child.getParent();
                    if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(child)) {
                        startSettlingAnimation(child, state2);
                        return;
                    }
                    final V v = child;
                    final int i2 = state2;
                    new Runnable(this) {
                        final /* synthetic */ BottomSheetBehavior this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            this.this$0.startSettlingAnimation(v, i2);
                        }
                    };
                    boolean post = child.post(runnable);
                }
            } else if (state2 == 4 || state2 == 3 || state2 == 6 || (this.hideable && state2 == 5)) {
                this.state = state2;
            }
        }
    }

    public final int getState() {
        return this.state;
    }

    /* access modifiers changed from: package-private */
    public void setStateInternal(int i) {
        int state2 = i;
        if (this.state != state2) {
            this.state = state2;
            if (state2 == 6 || state2 == 3) {
                updateImportantForAccessibility(true);
            } else if (state2 == 5 || state2 == 4) {
                updateImportantForAccessibility(false);
            }
            View bottomSheet = (View) this.viewRef.get();
            if (bottomSheet != null && this.callback != null) {
                this.callback.onStateChanged(bottomSheet, state2);
            }
        }
    }

    private void calculateCollapsedOffset() {
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - this.lastPeekHeight, this.fitToContentsOffset);
            return;
        }
        this.collapsedOffset = this.parentHeight - this.lastPeekHeight;
    }

    private void reset() {
        this.activePointerId = -1;
        if (this.velocityTracker != null) {
            this.velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldHide(View view, float f) {
        View child = view;
        float yvel = f;
        if (this.skipCollapsed) {
            return true;
        }
        if (child.getTop() < this.collapsedOffset) {
            return false;
        }
        return Math.abs((((float) child.getTop()) + (yvel * HIDE_FRICTION)) - ((float) this.collapsedOffset)) / ((float) this.peekHeight) > HIDE_THRESHOLD;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public View findScrollingChild(View view) {
        View view2 = view;
        if (ViewCompat.isNestedScrollingEnabled(view2)) {
            return view2;
        }
        if (view2 instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view2;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View scrollingChild = findScrollingChild(group.getChildAt(i));
                if (scrollingChild != null) {
                    return scrollingChild;
                }
            }
        }
        return null;
    }

    private float getYVelocity() {
        if (this.velocityTracker == null) {
            return 0.0f;
        }
        this.velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    /* access modifiers changed from: private */
    public int getExpandedOffset() {
        return this.fitToContents ? this.fitToContentsOffset : 0;
    }

    /* access modifiers changed from: package-private */
    public void startSettlingAnimation(View view, int i) {
        Throwable th;
        StringBuilder sb;
        int top;
        Runnable runnable;
        View child = view;
        int state2 = i;
        if (state2 == 4) {
            top = this.collapsedOffset;
        } else if (state2 == 6) {
            top = this.halfExpandedOffset;
            if (this.fitToContents && top <= this.fitToContentsOffset) {
                state2 = 3;
                top = this.fitToContentsOffset;
            }
        } else if (state2 == 3) {
            top = getExpandedOffset();
        } else if (!this.hideable || state2 != 5) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Illegal state argument: ").append(state2).toString());
            throw th2;
        } else {
            top = this.parentHeight;
        }
        if (this.viewDragHelper.smoothSlideViewTo(child, child.getLeft(), top)) {
            setStateInternal(2);
            new SettleRunnable(this, child, state2);
            ViewCompat.postOnAnimation(child, runnable);
            return;
        }
        setStateInternal(state2);
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnSlide(int i) {
        int top = i;
        View bottomSheet = (View) this.viewRef.get();
        if (bottomSheet != null && this.callback != null) {
            if (top > this.collapsedOffset) {
                this.callback.onSlide(bottomSheet, ((float) (this.collapsedOffset - top)) / ((float) (this.parentHeight - this.collapsedOffset)));
            } else {
                this.callback.onSlide(bottomSheet, ((float) (this.collapsedOffset - top)) / ((float) (this.collapsedOffset - getExpandedOffset())));
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    private class SettleRunnable implements Runnable {
        private final int targetState;
        final /* synthetic */ BottomSheetBehavior this$0;
        private final View view;

        SettleRunnable(BottomSheetBehavior bottomSheetBehavior, View view2, int targetState2) {
            this.this$0 = bottomSheetBehavior;
            this.view = view2;
            this.targetState = targetState2;
        }

        public void run() {
            if (this.this$0.viewDragHelper == null || !this.this$0.viewDragHelper.continueSettling(true)) {
                this.this$0.setStateInternal(this.targetState);
            } else {
                ViewCompat.postOnAnimation(this.view, this);
            }
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        final int state;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public SavedState(Parcel source) {
            this(source, (ClassLoader) null);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SavedState(android.os.Parcel r7, java.lang.ClassLoader r8) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r8
                r3 = r0
                r4 = r1
                r5 = r2
                r3.<init>(r4, r5)
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.state = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(Parcelable superState, int state2) {
            super(superState);
            this.state = state2;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel out = parcel;
            super.writeToParcel(out, flags);
            out.writeInt(this.state);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                    SavedState savedState;
                    new SavedState(in, loader);
                    return savedState;
                }

                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in, (ClassLoader) null);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V view) {
        Throwable th;
        Throwable th2;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            Throwable th3 = th2;
            new IllegalArgumentException("The view is not a child of CoordinatorLayout");
            throw th3;
        }
        V behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) behavior;
        }
        Throwable th4 = th;
        new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        throw th4;
    }

    private void updateImportantForAccessibility(boolean z) {
        Map<View, Integer> map;
        boolean expanded = z;
        if (this.viewRef != null) {
            ViewParent viewParent = ((View) this.viewRef.get()).getParent();
            if (viewParent instanceof CoordinatorLayout) {
                CoordinatorLayout parent = (CoordinatorLayout) viewParent;
                int childCount = parent.getChildCount();
                if (Build.VERSION.SDK_INT >= 16 && expanded) {
                    if (this.importantForAccessibilityMap == null) {
                        new HashMap(childCount);
                        this.importantForAccessibilityMap = map;
                    } else {
                        return;
                    }
                }
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);
                    if (child != this.viewRef.get()) {
                        if (expanded) {
                            if (Build.VERSION.SDK_INT >= 16) {
                                Integer put = this.importantForAccessibilityMap.put(child, Integer.valueOf(child.getImportantForAccessibility()));
                            }
                            ViewCompat.setImportantForAccessibility(child, 4);
                        } else if (this.importantForAccessibilityMap != null && this.importantForAccessibilityMap.containsKey(child)) {
                            ViewCompat.setImportantForAccessibility(child, this.importantForAccessibilityMap.get(child).intValue());
                        }
                    }
                }
                if (!expanded) {
                    this.importantForAccessibilityMap = null;
                }
            }
        }
    }
}
