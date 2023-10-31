package android.support.p000v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.NestedScrollingChild2;
import android.support.p000v4.view.NestedScrollingChildHelper;
import android.support.p000v4.view.NestedScrollingParent2;
import android.support.p000v4.view.NestedScrollingParentHelper;
import android.support.p000v4.view.ScrollingView;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import gnu.expr.Declaration;
import java.util.List;

/* renamed from: android.support.v4.widget.NestedScrollView */
public class NestedScrollView extends FrameLayout implements NestedScrollingParent2, NestedScrollingChild2, ScrollingView {
    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE;
    static final int ANIMATED_SCROLL_GAP = 250;
    private static final int INVALID_POINTER = -1;
    static final float MAX_SCROLL_FACTOR = 0.5f;
    private static final int[] SCROLLVIEW_STYLEABLE = {16843130};
    private static final String TAG = "NestedScrollView";
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private boolean mIsBeingDragged;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private OnScrollChangeListener mOnScrollChangeListener;
    private final NestedScrollingParentHelper mParentHelper;
    private SavedState mSavedState;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;

    /* renamed from: android.support.v4.widget.NestedScrollView$OnScrollChangeListener */
    public interface OnScrollChangeListener {
        void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4);
    }

    static {
        AccessibilityDelegate accessibilityDelegate;
        new AccessibilityDelegate();
        ACCESSIBILITY_DELEGATE = accessibilityDelegate;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NestedScrollView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NestedScrollView(@android.support.annotation.NonNull android.content.Context r12, @android.support.annotation.Nullable android.util.AttributeSet r13, int r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            android.graphics.Rect r6 = new android.graphics.Rect
            r10 = r6
            r6 = r10
            r7 = r10
            r7.<init>()
            r5.mTempRect = r6
            r5 = r0
            r6 = 1
            r5.mIsLayoutDirty = r6
            r5 = r0
            r6 = 0
            r5.mIsLaidOut = r6
            r5 = r0
            r6 = 0
            r5.mChildToScrollTo = r6
            r5 = r0
            r6 = 0
            r5.mIsBeingDragged = r6
            r5 = r0
            r6 = 1
            r5.mSmoothScrollingEnabled = r6
            r5 = r0
            r6 = -1
            r5.mActivePointerId = r6
            r5 = r0
            r6 = 2
            int[] r6 = new int[r6]
            r5.mScrollOffset = r6
            r5 = r0
            r6 = 2
            int[] r6 = new int[r6]
            r5.mScrollConsumed = r6
            r5 = r0
            r5.initScrollView()
            r5 = r1
            r6 = r2
            int[] r7 = SCROLLVIEW_STYLEABLE
            r8 = r3
            r9 = 0
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7, r8, r9)
            r4 = r5
            r5 = r0
            r6 = r4
            r7 = 0
            r8 = 0
            boolean r6 = r6.getBoolean(r7, r8)
            r5.setFillViewport(r6)
            r5 = r4
            r5.recycle()
            r5 = r0
            android.support.v4.view.NestedScrollingParentHelper r6 = new android.support.v4.view.NestedScrollingParentHelper
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r7.<init>(r8)
            r5.mParentHelper = r6
            r5 = r0
            android.support.v4.view.NestedScrollingChildHelper r6 = new android.support.v4.view.NestedScrollingChildHelper
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r7.<init>(r8)
            r5.mChildHelper = r6
            r5 = r0
            r6 = 1
            r5.setNestedScrollingEnabled(r6)
            r5 = r0
            android.support.v4.widget.NestedScrollView$AccessibilityDelegate r6 = ACCESSIBILITY_DELEGATE
            android.support.p000v4.view.ViewCompat.setAccessibilityDelegate(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.NestedScrollView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public boolean startNestedScroll(int axes, int type) {
        return this.mChildHelper.startNestedScroll(axes, type);
    }

    public void stopNestedScroll(int type) {
        this.mChildHelper.stopNestedScroll(type);
    }

    public boolean hasNestedScrollingParent(int type) {
        return this.mChildHelper.hasNestedScrollingParent(type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.mChildHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return startNestedScroll(axes, 0);
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, 0);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, 0);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int axes, int i) {
        View view3 = view;
        View view4 = view2;
        int i2 = i;
        return (axes & 2) != 0;
    }

    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int i) {
        int type = i;
        this.mParentHelper.onNestedScrollAccepted(child, target, axes, type);
        boolean startNestedScroll = startNestedScroll(2, type);
    }

    public void onStopNestedScroll(@NonNull View target, int i) {
        int type = i;
        this.mParentHelper.onStopNestedScroll(target, type);
        stopNestedScroll(type);
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int type) {
        View view2 = view;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int dyUnconsumed = i4;
        int oldScrollY = getScrollY();
        scrollBy(0, dyUnconsumed);
        int myConsumed = getScrollY() - oldScrollY;
        boolean dispatchNestedScroll = dispatchNestedScroll(0, myConsumed, 0, dyUnconsumed - myConsumed, (int[]) null, type);
    }

    public void onNestedPreScroll(@NonNull View view, int dx, int dy, @NonNull int[] consumed, int type) {
        View view2 = view;
        boolean dispatchNestedPreScroll = dispatchNestedPreScroll(dx, dy, consumed, (int[]) null, type);
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return onStartNestedScroll(child, target, nestedScrollAxes, 0);
    }

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        onNestedScrollAccepted(child, target, nestedScrollAxes, 0);
    }

    public void onStopNestedScroll(View target) {
        onStopNestedScroll(target, 0);
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0);
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        onNestedPreScroll(target, dx, dy, consumed, 0);
    }

    public boolean onNestedFling(View view, float f, float f2, boolean consumed) {
        View view2 = view;
        float f3 = f;
        float velocityY = f2;
        if (consumed) {
            return false;
        }
        flingWithNestedDispatch((int) velocityY);
        return true;
    }

    public boolean onNestedPreFling(View view, float velocityX, float velocityY) {
        View view2 = view;
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int length = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < length) {
            return ((float) scrollY) / ((float) length);
        }
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View child = getChildAt(0);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
        int length = getVerticalFadingEdgeLength();
        int span = ((child.getBottom() + lp.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (span < length) {
            return ((float) span) / ((float) length);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (MAX_SCROLL_FACTOR * ((float) getHeight()));
    }

    private void initScrollView() {
        OverScroller overScroller;
        new OverScroller(getContext());
        this.mScroller = overScroller;
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        this.mTouchSlop = configuration.getScaledTouchSlop();
        this.mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    public void addView(View view) {
        Throwable th;
        View child = view;
        if (getChildCount() > 0) {
            Throwable th2 = th;
            new IllegalStateException("ScrollView can host only one direct child");
            throw th2;
        }
        super.addView(child);
    }

    public void addView(View view, int i) {
        Throwable th;
        View child = view;
        int index = i;
        if (getChildCount() > 0) {
            Throwable th2 = th;
            new IllegalStateException("ScrollView can host only one direct child");
            throw th2;
        }
        super.addView(child, index);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        Throwable th;
        View child = view;
        ViewGroup.LayoutParams params = layoutParams;
        if (getChildCount() > 0) {
            Throwable th2 = th;
            new IllegalStateException("ScrollView can host only one direct child");
            throw th2;
        }
        super.addView(child, params);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        Throwable th;
        View child = view;
        int index = i;
        ViewGroup.LayoutParams params = layoutParams;
        if (getChildCount() > 0) {
            Throwable th2 = th;
            new IllegalStateException("ScrollView can host only one direct child");
            throw th2;
        }
        super.addView(child, index, params);
    }

    public void setOnScrollChangeListener(@Nullable OnScrollChangeListener l) {
        OnScrollChangeListener onScrollChangeListener = l;
        this.mOnScrollChangeListener = onScrollChangeListener;
    }

    private boolean canScroll() {
        if (getChildCount() <= 0) {
            return false;
        }
        View child = getChildAt(0);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
        return (child.getHeight() + lp.topMargin) + lp.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public boolean isFillViewport() {
        return this.mFillViewport;
    }

    public void setFillViewport(boolean z) {
        boolean fillViewport = z;
        if (fillViewport != this.mFillViewport) {
            this.mFillViewport = fillViewport;
            requestLayout();
        }
    }

    public boolean isSmoothScrollingEnabled() {
        return this.mSmoothScrollingEnabled;
    }

    public void setSmoothScrollingEnabled(boolean smoothScrollingEnabled) {
        boolean z = smoothScrollingEnabled;
        this.mSmoothScrollingEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        int l = i;
        int t = i2;
        int oldl = i3;
        int oldt = i4;
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.mOnScrollChangeListener != null) {
            this.mOnScrollChangeListener.onScrollChange(this, l, t, oldl, oldt);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mFillViewport && View.MeasureSpec.getMode(heightMeasureSpec) != 0 && getChildCount() > 0) {
            View child = getChildAt(0);
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
            int childSize = child.getMeasuredHeight();
            int parentSpace = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - lp.topMargin) - lp.bottomMargin;
            if (childSize < parentSpace) {
                child.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width), View.MeasureSpec.makeMeasureSpec(parentSpace, Declaration.MODULE_REFERENCE));
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        return super.dispatchKeyEvent(event) || executeKeyEvent(event);
    }

    public boolean executeKeyEvent(@NonNull KeyEvent keyEvent) {
        boolean z;
        KeyEvent event = keyEvent;
        this.mTempRect.setEmpty();
        if (canScroll()) {
            boolean handled = false;
            if (event.getAction() == 0) {
                switch (event.getKeyCode()) {
                    case 19:
                        if (event.isAltPressed()) {
                            handled = fullScroll(33);
                            break;
                        } else {
                            handled = arrowScroll(33);
                            break;
                        }
                    case 20:
                        if (event.isAltPressed()) {
                            handled = fullScroll(130);
                            break;
                        } else {
                            handled = arrowScroll(130);
                            break;
                        }
                    case 62:
                        boolean pageScroll = pageScroll(event.isShiftPressed() ? 33 : 130);
                        break;
                }
            }
            return handled;
        } else if (!isFocused() || event.getKeyCode() == 4) {
            return false;
        } else {
            View currentFocused = findFocus();
            if (currentFocused == this) {
                currentFocused = null;
            }
            View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, 130);
            if (nextFocused == null || nextFocused == this || !nextFocused.requestFocus(130)) {
                z = false;
            } else {
                z = true;
            }
            return z;
        }
    }

    private boolean inChild(int i, int i2) {
        int x = i;
        int y = i2;
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View child = getChildAt(0);
        return y >= child.getTop() - scrollY && y < child.getBottom() - scrollY && x >= child.getLeft() && x < child.getRight();
    }

    private void initOrResetVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
            return;
        }
        this.mVelocityTracker.clear();
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        boolean disallowIntercept = z;
        if (disallowIntercept) {
            recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        StringBuilder sb;
        MotionEvent ev = motionEvent;
        int action = ev.getAction();
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        switch (action & 255) {
            case 0:
                int y = (int) ev.getY();
                if (inChild((int) ev.getX(), y)) {
                    this.mLastMotionY = y;
                    this.mActivePointerId = ev.getPointerId(0);
                    initOrResetVelocityTracker();
                    this.mVelocityTracker.addMovement(ev);
                    boolean computeScrollOffset = this.mScroller.computeScrollOffset();
                    this.mIsBeingDragged = !this.mScroller.isFinished();
                    boolean startNestedScroll = startNestedScroll(2, 0);
                    break;
                } else {
                    this.mIsBeingDragged = false;
                    recycleVelocityTracker();
                    break;
                }
            case 1:
            case 3:
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                recycleVelocityTracker();
                if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                stopNestedScroll(0);
                break;
            case 2:
                int activePointerId = this.mActivePointerId;
                if (activePointerId != -1) {
                    int pointerIndex = ev.findPointerIndex(activePointerId);
                    if (pointerIndex != -1) {
                        int y2 = (int) ev.getY(pointerIndex);
                        if (Math.abs(y2 - this.mLastMotionY) > this.mTouchSlop && (getNestedScrollAxes() & 2) == 0) {
                            this.mIsBeingDragged = true;
                            this.mLastMotionY = y2;
                            initVelocityTrackerIfNotExists();
                            this.mVelocityTracker.addMovement(ev);
                            this.mNestedYOffset = 0;
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                                break;
                            }
                        }
                    } else {
                        new StringBuilder();
                        int e = Log.e(TAG, sb.append("Invalid pointerId=").append(activePointerId).append(" in onInterceptTouchEvent").toString());
                        break;
                    }
                }
                break;
            case 6:
                onSecondaryPointerUp(ev);
                break;
        }
        return this.mIsBeingDragged;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:72:0x03b9, code lost:
        if (r2.mEdgeGlowBottom.isFinished() == false) goto L_0x03bb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r29) {
        /*
            r28 = this;
            r2 = r28
            r3 = r29
            r16 = r2
            r16.initVelocityTrackerIfNotExists()
            r16 = r3
            android.view.MotionEvent r16 = android.view.MotionEvent.obtain(r16)
            r4 = r16
            r16 = r3
            int r16 = r16.getActionMasked()
            r5 = r16
            r16 = r5
            if (r16 != 0) goto L_0x0027
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mNestedYOffset = r0
        L_0x0027:
            r16 = r4
            r17 = 0
            r18 = r2
            r0 = r18
            int r0 = r0.mNestedYOffset
            r18 = r0
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r16.offsetLocation(r17, r18)
            r16 = r5
            switch(r16) {
                case 0: goto L_0x0061;
                case 1: goto L_0x041f;
                case 2: goto L_0x00f0;
                case 3: goto L_0x04af;
                case 4: goto L_0x0040;
                case 5: goto L_0x04fd;
                case 6: goto L_0x052c;
                default: goto L_0x0040;
            }
        L_0x0040:
            r16 = r2
            r0 = r16
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r16 = r0
            if (r16 == 0) goto L_0x0057
            r16 = r2
            r0 = r16
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r16 = r0
            r17 = r4
            r16.addMovement(r17)
        L_0x0057:
            r16 = r4
            r16.recycle()
            r16 = 1
            r2 = r16
        L_0x0060:
            return r2
        L_0x0061:
            r16 = r2
            int r16 = r16.getChildCount()
            if (r16 != 0) goto L_0x006e
            r16 = 0
            r2 = r16
            goto L_0x0060
        L_0x006e:
            r16 = r2
            r17 = r2
            r0 = r17
            android.widget.OverScroller r0 = r0.mScroller
            r17 = r0
            boolean r17 = r17.isFinished()
            if (r17 != 0) goto L_0x00ed
            r17 = 1
        L_0x0080:
            r26 = r16
            r27 = r17
            r16 = r27
            r17 = r26
            r18 = r27
            r0 = r18
            r1 = r17
            r1.mIsBeingDragged = r0
            if (r16 == 0) goto L_0x00a5
            r16 = r2
            android.view.ViewParent r16 = r16.getParent()
            r6 = r16
            r16 = r6
            if (r16 == 0) goto L_0x00a5
            r16 = r6
            r17 = 1
            r16.requestDisallowInterceptTouchEvent(r17)
        L_0x00a5:
            r16 = r2
            r0 = r16
            android.widget.OverScroller r0 = r0.mScroller
            r16 = r0
            boolean r16 = r16.isFinished()
            if (r16 != 0) goto L_0x00be
            r16 = r2
            r0 = r16
            android.widget.OverScroller r0 = r0.mScroller
            r16 = r0
            r16.abortAnimation()
        L_0x00be:
            r16 = r2
            r17 = r3
            float r17 = r17.getY()
            r0 = r17
            int r0 = (int) r0
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mLastMotionY = r0
            r16 = r2
            r17 = r3
            r18 = 0
            int r17 = r17.getPointerId(r18)
            r0 = r17
            r1 = r16
            r1.mActivePointerId = r0
            r16 = r2
            r17 = 2
            r18 = 0
            boolean r16 = r16.startNestedScroll(r17, r18)
            goto L_0x0040
        L_0x00ed:
            r17 = 0
            goto L_0x0080
        L_0x00f0:
            r16 = r3
            r17 = r2
            r0 = r17
            int r0 = r0.mActivePointerId
            r17 = r0
            int r16 = r16.findPointerIndex(r17)
            r6 = r16
            r16 = r6
            r17 = -1
            r0 = r16
            r1 = r17
            if (r0 != r1) goto L_0x013c
            java.lang.String r16 = "NestedScrollView"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r26 = r17
            r17 = r26
            r18 = r26
            r18.<init>()
            java.lang.String r18 = "Invalid pointerId="
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r2
            r0 = r18
            int r0 = r0.mActivePointerId
            r18 = r0
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r18 = " in onTouchEvent"
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            int r16 = android.util.Log.e(r16, r17)
            goto L_0x0040
        L_0x013c:
            r16 = r3
            r17 = r6
            float r16 = r16.getY(r17)
            r0 = r16
            int r0 = (int) r0
            r16 = r0
            r7 = r16
            r16 = r2
            r0 = r16
            int r0 = r0.mLastMotionY
            r16 = r0
            r17 = r7
            int r16 = r16 - r17
            r8 = r16
            r16 = r2
            r17 = 0
            r18 = r8
            r19 = r2
            r0 = r19
            int[] r0 = r0.mScrollConsumed
            r19 = r0
            r20 = r2
            r0 = r20
            int[] r0 = r0.mScrollOffset
            r20 = r0
            r21 = 0
            boolean r16 = r16.dispatchNestedPreScroll(r17, r18, r19, r20, r21)
            if (r16 == 0) goto L_0x01c3
            r16 = r8
            r17 = r2
            r0 = r17
            int[] r0 = r0.mScrollConsumed
            r17 = r0
            r18 = 1
            r17 = r17[r18]
            int r16 = r16 - r17
            r8 = r16
            r16 = r4
            r17 = 0
            r18 = r2
            r0 = r18
            int[] r0 = r0.mScrollOffset
            r18 = r0
            r19 = 1
            r18 = r18[r19]
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r16.offsetLocation(r17, r18)
            r16 = r2
            r26 = r16
            r16 = r26
            r17 = r26
            r0 = r17
            int r0 = r0.mNestedYOffset
            r17 = r0
            r18 = r2
            r0 = r18
            int[] r0 = r0.mScrollOffset
            r18 = r0
            r19 = 1
            r18 = r18[r19]
            int r17 = r17 + r18
            r0 = r17
            r1 = r16
            r1.mNestedYOffset = r0
        L_0x01c3:
            r16 = r2
            r0 = r16
            boolean r0 = r0.mIsBeingDragged
            r16 = r0
            if (r16 != 0) goto L_0x0210
            r16 = r8
            int r16 = java.lang.Math.abs(r16)
            r17 = r2
            r0 = r17
            int r0 = r0.mTouchSlop
            r17 = r0
            r0 = r16
            r1 = r17
            if (r0 <= r1) goto L_0x0210
            r16 = r2
            android.view.ViewParent r16 = r16.getParent()
            r9 = r16
            r16 = r9
            if (r16 == 0) goto L_0x01f4
            r16 = r9
            r17 = 1
            r16.requestDisallowInterceptTouchEvent(r17)
        L_0x01f4:
            r16 = r2
            r17 = 1
            r0 = r17
            r1 = r16
            r1.mIsBeingDragged = r0
            r16 = r8
            if (r16 <= 0) goto L_0x031f
            r16 = r8
            r17 = r2
            r0 = r17
            int r0 = r0.mTouchSlop
            r17 = r0
            int r16 = r16 - r17
            r8 = r16
        L_0x0210:
            r16 = r2
            r0 = r16
            boolean r0 = r0.mIsBeingDragged
            r16 = r0
            if (r16 == 0) goto L_0x0040
            r16 = r2
            r17 = r7
            r18 = r2
            r0 = r18
            int[] r0 = r0.mScrollOffset
            r18 = r0
            r19 = 1
            r18 = r18[r19]
            int r17 = r17 - r18
            r0 = r17
            r1 = r16
            r1.mLastMotionY = r0
            r16 = r2
            int r16 = r16.getScrollY()
            r9 = r16
            r16 = r2
            int r16 = r16.getScrollRange()
            r10 = r16
            r16 = r2
            int r16 = r16.getOverScrollMode()
            r11 = r16
            r16 = r11
            if (r16 == 0) goto L_0x025c
            r16 = r11
            r17 = 1
            r0 = r16
            r1 = r17
            if (r0 != r1) goto L_0x032f
            r16 = r10
            if (r16 <= 0) goto L_0x032f
        L_0x025c:
            r16 = 1
        L_0x025e:
            r12 = r16
            r16 = r2
            r17 = 0
            r18 = r8
            r19 = 0
            r20 = r2
            int r20 = r20.getScrollY()
            r21 = 0
            r22 = r10
            r23 = 0
            r24 = 0
            r25 = 1
            boolean r16 = r16.overScrollByCompat(r17, r18, r19, r20, r21, r22, r23, r24, r25)
            if (r16 == 0) goto L_0x0293
            r16 = r2
            r17 = 0
            boolean r16 = r16.hasNestedScrollingParent(r17)
            if (r16 != 0) goto L_0x0293
            r16 = r2
            r0 = r16
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r16 = r0
            r16.clear()
        L_0x0293:
            r16 = r2
            int r16 = r16.getScrollY()
            r17 = r9
            int r16 = r16 - r17
            r13 = r16
            r16 = r8
            r17 = r13
            int r16 = r16 - r17
            r14 = r16
            r16 = r2
            r17 = 0
            r18 = r13
            r19 = 0
            r20 = r14
            r21 = r2
            r0 = r21
            int[] r0 = r0.mScrollOffset
            r21 = r0
            r22 = 0
            boolean r16 = r16.dispatchNestedScroll(r17, r18, r19, r20, r21, r22)
            if (r16 == 0) goto L_0x0333
            r16 = r2
            r26 = r16
            r16 = r26
            r17 = r26
            r0 = r17
            int r0 = r0.mLastMotionY
            r17 = r0
            r18 = r2
            r0 = r18
            int[] r0 = r0.mScrollOffset
            r18 = r0
            r19 = 1
            r18 = r18[r19]
            int r17 = r17 - r18
            r0 = r17
            r1 = r16
            r1.mLastMotionY = r0
            r16 = r4
            r17 = 0
            r18 = r2
            r0 = r18
            int[] r0 = r0.mScrollOffset
            r18 = r0
            r19 = 1
            r18 = r18[r19]
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r16.offsetLocation(r17, r18)
            r16 = r2
            r26 = r16
            r16 = r26
            r17 = r26
            r0 = r17
            int r0 = r0.mNestedYOffset
            r17 = r0
            r18 = r2
            r0 = r18
            int[] r0 = r0.mScrollOffset
            r18 = r0
            r19 = 1
            r18 = r18[r19]
            int r17 = r17 + r18
            r0 = r17
            r1 = r16
            r1.mNestedYOffset = r0
        L_0x031d:
            goto L_0x0040
        L_0x031f:
            r16 = r8
            r17 = r2
            r0 = r17
            int r0 = r0.mTouchSlop
            r17 = r0
            int r16 = r16 + r17
            r8 = r16
            goto L_0x0210
        L_0x032f:
            r16 = 0
            goto L_0x025e
        L_0x0333:
            r16 = r12
            if (r16 == 0) goto L_0x031d
            r16 = r2
            r16.ensureGlows()
            r16 = r9
            r17 = r8
            int r16 = r16 + r17
            r15 = r16
            r16 = r15
            if (r16 >= 0) goto L_0x03c2
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r16 = r0
            r17 = r8
            r0 = r17
            float r0 = (float) r0
            r17 = r0
            r18 = r2
            int r18 = r18.getHeight()
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            float r17 = r17 / r18
            r18 = r3
            r19 = r6
            float r18 = r18.getX(r19)
            r19 = r2
            int r19 = r19.getWidth()
            r0 = r19
            float r0 = (float) r0
            r19 = r0
            float r18 = r18 / r19
            android.support.p000v4.widget.EdgeEffectCompat.onPull(r16, r17, r18)
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r16 = r0
            boolean r16 = r16.isFinished()
            if (r16 != 0) goto L_0x0395
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r16 = r0
            r16.onRelease()
        L_0x0395:
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r16 = r0
            if (r16 == 0) goto L_0x031d
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r16 = r0
            boolean r16 = r16.isFinished()
            if (r16 == 0) goto L_0x03bb
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r16 = r0
            boolean r16 = r16.isFinished()
            if (r16 != 0) goto L_0x031d
        L_0x03bb:
            r16 = r2
            android.support.p000v4.view.ViewCompat.postInvalidateOnAnimation(r16)
            goto L_0x031d
        L_0x03c2:
            r16 = r15
            r17 = r10
            r0 = r16
            r1 = r17
            if (r0 <= r1) goto L_0x0395
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r16 = r0
            r17 = r8
            r0 = r17
            float r0 = (float) r0
            r17 = r0
            r18 = r2
            int r18 = r18.getHeight()
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            float r17 = r17 / r18
            r18 = 1065353216(0x3f800000, float:1.0)
            r19 = r3
            r20 = r6
            float r19 = r19.getX(r20)
            r20 = r2
            int r20 = r20.getWidth()
            r0 = r20
            float r0 = (float) r0
            r20 = r0
            float r19 = r19 / r20
            float r18 = r18 - r19
            android.support.p000v4.widget.EdgeEffectCompat.onPull(r16, r17, r18)
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r16 = r0
            boolean r16 = r16.isFinished()
            if (r16 != 0) goto L_0x0395
            r16 = r2
            r0 = r16
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r16 = r0
            r16.onRelease()
            goto L_0x0395
        L_0x041f:
            r16 = r2
            r0 = r16
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r16 = r0
            r9 = r16
            r16 = r9
            r17 = 1000(0x3e8, float:1.401E-42)
            r18 = r2
            r0 = r18
            int r0 = r0.mMaximumVelocity
            r18 = r0
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r16.computeCurrentVelocity(r17, r18)
            r16 = r9
            r17 = r2
            r0 = r17
            int r0 = r0.mActivePointerId
            r17 = r0
            float r16 = r16.getYVelocity(r17)
            r0 = r16
            int r0 = (int) r0
            r16 = r0
            r10 = r16
            r16 = r10
            int r16 = java.lang.Math.abs(r16)
            r17 = r2
            r0 = r17
            int r0 = r0.mMinimumVelocity
            r17 = r0
            r0 = r16
            r1 = r17
            if (r0 <= r1) goto L_0x0483
            r16 = r2
            r17 = r10
            r0 = r17
            int r0 = -r0
            r17 = r0
            r16.flingWithNestedDispatch(r17)
        L_0x0472:
            r16 = r2
            r17 = -1
            r0 = r17
            r1 = r16
            r1.mActivePointerId = r0
            r16 = r2
            r16.endDrag()
            goto L_0x0040
        L_0x0483:
            r16 = r2
            r0 = r16
            android.widget.OverScroller r0 = r0.mScroller
            r16 = r0
            r17 = r2
            int r17 = r17.getScrollX()
            r18 = r2
            int r18 = r18.getScrollY()
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = r2
            int r22 = r22.getScrollRange()
            boolean r16 = r16.springBack(r17, r18, r19, r20, r21, r22)
            if (r16 == 0) goto L_0x0472
            r16 = r2
            android.support.p000v4.view.ViewCompat.postInvalidateOnAnimation(r16)
            goto L_0x0472
        L_0x04af:
            r16 = r2
            r0 = r16
            boolean r0 = r0.mIsBeingDragged
            r16 = r0
            if (r16 == 0) goto L_0x04ec
            r16 = r2
            int r16 = r16.getChildCount()
            if (r16 <= 0) goto L_0x04ec
            r16 = r2
            r0 = r16
            android.widget.OverScroller r0 = r0.mScroller
            r16 = r0
            r17 = r2
            int r17 = r17.getScrollX()
            r18 = r2
            int r18 = r18.getScrollY()
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = r2
            int r22 = r22.getScrollRange()
            boolean r16 = r16.springBack(r17, r18, r19, r20, r21, r22)
            if (r16 == 0) goto L_0x04ec
            r16 = r2
            android.support.p000v4.view.ViewCompat.postInvalidateOnAnimation(r16)
        L_0x04ec:
            r16 = r2
            r17 = -1
            r0 = r17
            r1 = r16
            r1.mActivePointerId = r0
            r16 = r2
            r16.endDrag()
            goto L_0x0040
        L_0x04fd:
            r16 = r3
            int r16 = r16.getActionIndex()
            r11 = r16
            r16 = r2
            r17 = r3
            r18 = r11
            float r17 = r17.getY(r18)
            r0 = r17
            int r0 = (int) r0
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mLastMotionY = r0
            r16 = r2
            r17 = r3
            r18 = r11
            int r17 = r17.getPointerId(r18)
            r0 = r17
            r1 = r16
            r1.mActivePointerId = r0
            goto L_0x0040
        L_0x052c:
            r16 = r2
            r17 = r3
            r16.onSecondaryPointerUp(r17)
            r16 = r2
            r17 = r3
            r18 = r3
            r19 = r2
            r0 = r19
            int r0 = r0.mActivePointerId
            r19 = r0
            int r18 = r18.findPointerIndex(r19)
            float r17 = r17.getY(r18)
            r0 = r17
            int r0 = (int) r0
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mLastMotionY = r0
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.mLastMotionY = (int) ev.getY(newPointerIndex);
            this.mActivePointerId = ev.getPointerId(newPointerIndex);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        if ((event.getSource() & 2) != 0) {
            switch (event.getAction()) {
                case 8:
                    if (!this.mIsBeingDragged) {
                        float vscroll = event.getAxisValue(9);
                        if (vscroll != 0.0f) {
                            int delta = (int) (vscroll * getVerticalScrollFactorCompat());
                            int range = getScrollRange();
                            int oldScrollY = getScrollY();
                            int newScrollY = oldScrollY - delta;
                            if (newScrollY < 0) {
                                newScrollY = 0;
                            } else if (newScrollY > range) {
                                newScrollY = range;
                            }
                            if (newScrollY != oldScrollY) {
                                super.scrollTo(getScrollX(), newScrollY);
                                return true;
                            }
                        }
                    }
                    break;
            }
        }
        return false;
    }

    private float getVerticalScrollFactorCompat() {
        TypedValue typedValue;
        Throwable th;
        if (this.mVerticalScrollFactor == 0.0f) {
            new TypedValue();
            TypedValue outValue = typedValue;
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(16842829, outValue, true)) {
                Throwable th2 = th;
                new IllegalStateException("Expected theme to define listPreferredItemHeight.");
                throw th2;
            }
            this.mVerticalScrollFactor = outValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.mVerticalScrollFactor;
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int scrollX, int scrollY, boolean z, boolean z2) {
        boolean z3 = z;
        boolean z4 = z2;
        super.scrollTo(scrollX, scrollY);
    }

    /* access modifiers changed from: package-private */
    public boolean overScrollByCompat(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        int deltaX = i;
        int deltaY = i2;
        int scrollX = i3;
        int scrollY = i4;
        int scrollRangeX = i5;
        int scrollRangeY = i6;
        int maxOverScrollX = i7;
        int maxOverScrollY = i8;
        boolean z2 = z;
        int overScrollMode = getOverScrollMode();
        boolean canScrollHorizontal = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean canScrollVertical = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean overScrollHorizontal = overScrollMode == 0 || (overScrollMode == 1 && canScrollHorizontal);
        boolean overScrollVertical = overScrollMode == 0 || (overScrollMode == 1 && canScrollVertical);
        int newScrollX = scrollX + deltaX;
        if (!overScrollHorizontal) {
            maxOverScrollX = 0;
        }
        int newScrollY = scrollY + deltaY;
        if (!overScrollVertical) {
            maxOverScrollY = 0;
        }
        int left = -maxOverScrollX;
        int right = maxOverScrollX + scrollRangeX;
        int top = -maxOverScrollY;
        int bottom = maxOverScrollY + scrollRangeY;
        boolean clampedX = false;
        if (newScrollX > right) {
            newScrollX = right;
            clampedX = true;
        } else if (newScrollX < left) {
            newScrollX = left;
            clampedX = true;
        }
        boolean clampedY = false;
        if (newScrollY > bottom) {
            newScrollY = bottom;
            clampedY = true;
        } else if (newScrollY < top) {
            newScrollY = top;
            clampedY = true;
        }
        if (clampedY && !hasNestedScrollingParent(1)) {
            boolean springBack = this.mScroller.springBack(newScrollX, newScrollY, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(newScrollX, newScrollY, clampedX, clampedY);
        return clampedX || clampedY;
    }

    /* access modifiers changed from: package-private */
    public int getScrollRange() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
            scrollRange = Math.max(0, ((child.getHeight() + lp.topMargin) + lp.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
        }
        return scrollRange;
    }

    private View findFocusableViewInBounds(boolean z, int i, int i2) {
        boolean topFocus = z;
        int top = i;
        int bottom = i2;
        List<View> focusables = getFocusables(2);
        View focusCandidate = null;
        boolean foundFullyContainedFocusable = false;
        int count = focusables.size();
        for (int i3 = 0; i3 < count; i3++) {
            View view = focusables.get(i3);
            int viewTop = view.getTop();
            int viewBottom = view.getBottom();
            if (top < viewBottom && viewTop < bottom) {
                boolean viewIsFullyContained = top < viewTop && viewBottom < bottom;
                if (focusCandidate == null) {
                    focusCandidate = view;
                    foundFullyContainedFocusable = viewIsFullyContained;
                } else {
                    boolean viewIsCloserToBoundary = (topFocus && viewTop < focusCandidate.getTop()) || (!topFocus && viewBottom > focusCandidate.getBottom());
                    if (foundFullyContainedFocusable) {
                        if (viewIsFullyContained && viewIsCloserToBoundary) {
                            focusCandidate = view;
                        }
                    } else if (viewIsFullyContained) {
                        focusCandidate = view;
                        foundFullyContainedFocusable = true;
                    } else if (viewIsCloserToBoundary) {
                        focusCandidate = view;
                    }
                }
            }
        }
        return focusCandidate;
    }

    public boolean pageScroll(int i) {
        int direction = i;
        boolean down = direction == 130;
        int height = getHeight();
        if (down) {
            this.mTempRect.top = getScrollY() + height;
            int count = getChildCount();
            if (count > 0) {
                View view = getChildAt(count - 1);
                int bottom = view.getBottom() + ((FrameLayout.LayoutParams) view.getLayoutParams()).bottomMargin + getPaddingBottom();
                if (this.mTempRect.top + height > bottom) {
                    this.mTempRect.top = bottom - height;
                }
            }
        } else {
            this.mTempRect.top = getScrollY() - height;
            if (this.mTempRect.top < 0) {
                this.mTempRect.top = 0;
            }
        }
        this.mTempRect.bottom = this.mTempRect.top + height;
        return scrollAndFocus(direction, this.mTempRect.top, this.mTempRect.bottom);
    }

    public boolean fullScroll(int i) {
        int count;
        int direction = i;
        boolean down = direction == 130;
        int height = getHeight();
        this.mTempRect.top = 0;
        this.mTempRect.bottom = height;
        if (down && (count = getChildCount()) > 0) {
            View view = getChildAt(count - 1);
            this.mTempRect.bottom = view.getBottom() + ((FrameLayout.LayoutParams) view.getLayoutParams()).bottomMargin + getPaddingBottom();
            this.mTempRect.top = this.mTempRect.bottom - height;
        }
        return scrollAndFocus(direction, this.mTempRect.top, this.mTempRect.bottom);
    }

    private boolean scrollAndFocus(int i, int i2, int i3) {
        int direction = i;
        int top = i2;
        int bottom = i3;
        boolean handled = true;
        int height = getHeight();
        int containerTop = getScrollY();
        int containerBottom = containerTop + height;
        boolean up = direction == 33;
        View newFocused = findFocusableViewInBounds(up, top, bottom);
        if (newFocused == null) {
            newFocused = this;
        }
        if (top < containerTop || bottom > containerBottom) {
            doScrollY(up ? top - containerTop : bottom - containerBottom);
        } else {
            handled = false;
        }
        if (newFocused != findFocus()) {
            boolean requestFocus = newFocused.requestFocus(direction);
        }
        return handled;
    }

    public boolean arrowScroll(int i) {
        int direction = i;
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        }
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        int maxJump = getMaxScrollAmount();
        if (nextFocused == null || !isWithinDeltaOfScreen(nextFocused, maxJump, getHeight())) {
            int scrollDelta = maxJump;
            if (direction == 33 && getScrollY() < scrollDelta) {
                scrollDelta = getScrollY();
            } else if (direction == 130 && getChildCount() > 0) {
                View child = getChildAt(0);
                scrollDelta = Math.min((child.getBottom() + ((FrameLayout.LayoutParams) child.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxJump);
            }
            if (scrollDelta == 0) {
                return false;
            }
            doScrollY(direction == 130 ? scrollDelta : -scrollDelta);
        } else {
            nextFocused.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords(nextFocused, this.mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
            boolean requestFocus = nextFocused.requestFocus(direction);
        }
        if (currentFocused != null && currentFocused.isFocused() && isOffScreen(currentFocused)) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            boolean requestFocus2 = requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    private boolean isOffScreen(View descendant) {
        return !isWithinDeltaOfScreen(descendant, 0, getHeight());
    }

    private boolean isWithinDeltaOfScreen(View view, int i, int i2) {
        View descendant = view;
        int delta = i;
        int height = i2;
        descendant.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(descendant, this.mTempRect);
        return this.mTempRect.bottom + delta >= getScrollY() && this.mTempRect.top - delta <= getScrollY() + height;
    }

    private void doScrollY(int i) {
        int delta = i;
        if (delta == 0) {
            return;
        }
        if (this.mSmoothScrollingEnabled) {
            smoothScrollBy(0, delta);
        } else {
            scrollBy(0, delta);
        }
    }

    public final void smoothScrollBy(int i, int i2) {
        int dx = i;
        int dy = i2;
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250) {
                View child = getChildAt(0);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
                int childSize = child.getHeight() + lp.topMargin + lp.bottomMargin;
                int parentSpace = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int scrollY = getScrollY();
                int dy2 = Math.max(0, Math.min(scrollY + dy, Math.max(0, childSize - parentSpace))) - scrollY;
                this.mLastScrollerY = getScrollY();
                this.mScroller.startScroll(getScrollX(), scrollY, 0, dy2);
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                if (!this.mScroller.isFinished()) {
                    this.mScroller.abortAnimation();
                }
                scrollBy(dx, dy);
            }
            this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void smoothScrollTo(int x, int y) {
        smoothScrollBy(x - getScrollX(), y - getScrollY());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int computeVerticalScrollRange() {
        int count = getChildCount();
        int parentSpace = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (count == 0) {
            return parentSpace;
        }
        View child = getChildAt(0);
        int scrollRange = child.getBottom() + ((FrameLayout.LayoutParams) child.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int overscrollBottom = Math.max(0, scrollRange - parentSpace);
        if (scrollY < 0) {
            scrollRange -= scrollY;
        } else if (scrollY > overscrollBottom) {
            scrollRange += scrollY - overscrollBottom;
        }
        return scrollRange;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    /* access modifiers changed from: protected */
    public void measureChild(View view, int parentWidthMeasureSpec, int i) {
        View child = view;
        int i2 = i;
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight(), child.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int parentWidthMeasureSpec, int widthUsed, int i, int i2) {
        View child = view;
        int i3 = i;
        int i4 = i2;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed, lp.width), View.MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, 0));
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            int currX = this.mScroller.getCurrX();
            int y = this.mScroller.getCurrY();
            int dy = y - this.mLastScrollerY;
            if (dispatchNestedPreScroll(0, dy, this.mScrollConsumed, (int[]) null, 1)) {
                dy -= this.mScrollConsumed[1];
            }
            if (dy != 0) {
                int range = getScrollRange();
                int oldScrollY = getScrollY();
                boolean overScrollByCompat = overScrollByCompat(0, dy, getScrollX(), oldScrollY, 0, range, 0, 0, false);
                int scrolledDeltaY = getScrollY() - oldScrollY;
                if (!dispatchNestedScroll(0, scrolledDeltaY, 0, dy - scrolledDeltaY, (int[]) null, 1)) {
                    int mode = getOverScrollMode();
                    if (mode == 0 || (mode == 1 && range > 0)) {
                        ensureGlows();
                        if (y <= 0 && oldScrollY > 0) {
                            this.mEdgeGlowTop.onAbsorb((int) this.mScroller.getCurrVelocity());
                        } else if (y >= range && oldScrollY < range) {
                            this.mEdgeGlowBottom.onAbsorb((int) this.mScroller.getCurrVelocity());
                        }
                    }
                }
            }
            this.mLastScrollerY = y;
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        if (hasNestedScrollingParent(1)) {
            stopNestedScroll(1);
        }
        this.mLastScrollerY = 0;
    }

    private void scrollToChild(View view) {
        View child = view;
        child.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(child, this.mTempRect);
        int scrollDelta = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
        if (scrollDelta != 0) {
            scrollBy(0, scrollDelta);
        }
    }

    private boolean scrollToChildRect(Rect rect, boolean z) {
        boolean immediate = z;
        int delta = computeScrollDeltaToGetChildRectOnScreen(rect);
        boolean scroll = delta != 0;
        if (scroll) {
            if (immediate) {
                scrollBy(0, delta);
            } else {
                smoothScrollBy(0, delta);
            }
        }
        return scroll;
    }

    /* access modifiers changed from: protected */
    public int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        int scrollYDelta;
        int scrollYDelta2;
        Rect rect2 = rect;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int screenTop = getScrollY();
        int screenBottom = screenTop + height;
        int actualScreenBottom = screenBottom;
        int fadingEdge = getVerticalFadingEdgeLength();
        if (rect2.top > 0) {
            screenTop += fadingEdge;
        }
        View child = getChildAt(0);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
        if (rect2.bottom < child.getHeight() + lp.topMargin + lp.bottomMargin) {
            screenBottom -= fadingEdge;
        }
        int scrollYDelta3 = 0;
        if (rect2.bottom > screenBottom && rect2.top > screenTop) {
            if (rect2.height() > height) {
                scrollYDelta2 = 0 + (rect2.top - screenTop);
            } else {
                scrollYDelta2 = 0 + (rect2.bottom - screenBottom);
            }
            scrollYDelta3 = Math.min(scrollYDelta2, (child.getBottom() + lp.bottomMargin) - actualScreenBottom);
        } else if (rect2.top < screenTop && rect2.bottom < screenBottom) {
            if (rect2.height() > height) {
                scrollYDelta = 0 - (screenBottom - rect2.bottom);
            } else {
                scrollYDelta = 0 - (screenTop - rect2.top);
            }
            scrollYDelta3 = Math.max(scrollYDelta, -getScrollY());
        }
        return scrollYDelta3;
    }

    public void requestChildFocus(View view, View view2) {
        View child = view;
        View focused = view2;
        if (!this.mIsLayoutDirty) {
            scrollToChild(focused);
        } else {
            this.mChildToScrollTo = focused;
        }
        super.requestChildFocus(child, focused);
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        View findNextFocusFromRect;
        int direction = i;
        Rect previouslyFocusedRect = rect;
        if (direction == 2) {
            direction = 130;
        } else if (direction == 1) {
            direction = 33;
        }
        if (previouslyFocusedRect == null) {
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocus(this, (View) null, direction);
        } else {
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(this, previouslyFocusedRect, direction);
        }
        View nextFocus = findNextFocusFromRect;
        if (nextFocus == null) {
            return false;
        }
        if (isOffScreen(nextFocus)) {
            return false;
        }
        return nextFocus.requestFocus(direction, previouslyFocusedRect);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean immediate) {
        View child = view;
        Rect rectangle = rect;
        rectangle.offset(child.getLeft() - child.getScrollX(), child.getTop() - child.getScrollY());
        return scrollToChildRect(rectangle, immediate);
    }

    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int i, int r, int i2) {
        int t = i;
        int b = i2;
        super.onLayout(changed, l, t, r, b);
        this.mIsLayoutDirty = false;
        if (this.mChildToScrollTo != null && isViewDescendantOf(this.mChildToScrollTo, this)) {
            scrollToChild(this.mChildToScrollTo);
        }
        this.mChildToScrollTo = null;
        if (!this.mIsLaidOut) {
            if (this.mSavedState != null) {
                scrollTo(getScrollX(), this.mSavedState.scrollPosition);
                this.mSavedState = null;
            }
            int childSize = 0;
            if (getChildCount() > 0) {
                View child = getChildAt(0);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
                childSize = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            }
            int parentSpace = ((b - t) - getPaddingTop()) - getPaddingBottom();
            int currentScrollY = getScrollY();
            int newScrollY = clamp(currentScrollY, parentSpace, childSize);
            if (newScrollY != currentScrollY) {
                scrollTo(getScrollX(), newScrollY);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.mIsLaidOut = true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsLaidOut = false;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int i) {
        int oldh = i;
        super.onSizeChanged(w, h, oldw, oldh);
        View currentFocused = findFocus();
        if (null != currentFocused && this != currentFocused && isWithinDeltaOfScreen(currentFocused, 0, oldh)) {
            currentFocused.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords(currentFocused, this.mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
        }
    }

    private static boolean isViewDescendantOf(View view, View view2) {
        View child = view;
        View parent = view2;
        if (child == parent) {
            return true;
        }
        ViewParent theParent = child.getParent();
        return (theParent instanceof ViewGroup) && isViewDescendantOf((View) theParent, parent);
    }

    public void fling(int i) {
        int velocityY = i;
        if (getChildCount() > 0) {
            boolean startNestedScroll = startNestedScroll(2, 1);
            this.mScroller.fling(getScrollX(), getScrollY(), 0, velocityY, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.mLastScrollerY = getScrollY();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void flingWithNestedDispatch(int i) {
        int velocityY = i;
        int scrollY = getScrollY();
        boolean canFling = (scrollY > 0 || velocityY > 0) && (scrollY < getScrollRange() || velocityY < 0);
        if (!dispatchNestedPreFling(0.0f, (float) velocityY)) {
            boolean dispatchNestedFling = dispatchNestedFling(0.0f, (float) velocityY, canFling);
            fling(velocityY);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        recycleVelocityTracker();
        stopNestedScroll(0);
        if (this.mEdgeGlowTop != null) {
            this.mEdgeGlowTop.onRelease();
            this.mEdgeGlowBottom.onRelease();
        }
    }

    public void scrollTo(int i, int i2) {
        int x = i;
        int y = i2;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
            int parentSpaceHorizontal = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int childSizeHorizontal = child.getWidth() + lp.leftMargin + lp.rightMargin;
            int parentSpaceVertical = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int childSizeVertical = child.getHeight() + lp.topMargin + lp.bottomMargin;
            int x2 = clamp(x, parentSpaceHorizontal, childSizeHorizontal);
            int y2 = clamp(y, parentSpaceVertical, childSizeVertical);
            if (x2 != getScrollX() || y2 != getScrollY()) {
                super.scrollTo(x2, y2);
            }
        }
    }

    private void ensureGlows() {
        EdgeEffect edgeEffect;
        EdgeEffect edgeEffect2;
        if (getOverScrollMode() == 2) {
            this.mEdgeGlowTop = null;
            this.mEdgeGlowBottom = null;
        } else if (this.mEdgeGlowTop == null) {
            Context context = getContext();
            new EdgeEffect(context);
            this.mEdgeGlowTop = edgeEffect;
            new EdgeEffect(context);
            this.mEdgeGlowBottom = edgeEffect2;
        }
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.draw(canvas2);
        if (this.mEdgeGlowTop != null) {
            int scrollY = getScrollY();
            if (!this.mEdgeGlowTop.isFinished()) {
                int restoreCount = canvas2.save();
                int width = getWidth();
                int height = getHeight();
                int xTranslation = 0;
                int yTranslation = Math.min(0, scrollY);
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    xTranslation = 0 + getPaddingLeft();
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    yTranslation += getPaddingTop();
                }
                canvas2.translate((float) xTranslation, (float) yTranslation);
                this.mEdgeGlowTop.setSize(width, height);
                if (this.mEdgeGlowTop.draw(canvas2)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas2.restoreToCount(restoreCount);
            }
            if (!this.mEdgeGlowBottom.isFinished()) {
                int restoreCount2 = canvas2.save();
                int width2 = getWidth();
                int height2 = getHeight();
                int xTranslation2 = 0;
                int yTranslation2 = Math.max(getScrollRange(), scrollY) + height2;
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width2 -= getPaddingLeft() + getPaddingRight();
                    xTranslation2 = 0 + getPaddingLeft();
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    height2 -= getPaddingTop() + getPaddingBottom();
                    yTranslation2 -= getPaddingBottom();
                }
                canvas2.translate((float) (xTranslation2 - width2), (float) yTranslation2);
                canvas2.rotate(180.0f, (float) width2, 0.0f);
                this.mEdgeGlowBottom.setSize(width2, height2);
                if (this.mEdgeGlowBottom.draw(canvas2)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas2.restoreToCount(restoreCount2);
            }
        }
    }

    private static int clamp(int i, int i2, int i3) {
        int n = i;
        int my = i2;
        int child = i3;
        if (my >= child || n < 0) {
            return 0;
        }
        if (my + n > child) {
            return child - my;
        }
        return n;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.mSavedState = ss;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState ss = savedState;
        ss.scrollPosition = getScrollY();
        return ss;
    }

    /* renamed from: android.support.v4.widget.NestedScrollView$SavedState */
    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        public int scrollPosition;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SavedState(Parcelable superState) {
            super(superState);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        SavedState(android.os.Parcel r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = r1
                int r3 = r3.readInt()
                r2.scrollPosition = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.NestedScrollView.SavedState.<init>(android.os.Parcel):void");
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel dest = parcel;
            super.writeToParcel(dest, flags);
            dest.writeInt(this.scrollPosition);
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("HorizontalScrollView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" scrollPosition=").append(this.scrollPosition).append("}").toString();
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* renamed from: android.support.v4.widget.NestedScrollView$AccessibilityDelegate */
    static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        AccessibilityDelegate() {
        }

        public boolean performAccessibilityAction(View view, int i, Bundle arguments) {
            View host = view;
            int action = i;
            if (super.performAccessibilityAction(host, action, arguments)) {
                return true;
            }
            NestedScrollView nsvHost = (NestedScrollView) host;
            if (!nsvHost.isEnabled()) {
                return false;
            }
            switch (action) {
                case 4096:
                    int targetScrollY = Math.min(nsvHost.getScrollY() + ((nsvHost.getHeight() - nsvHost.getPaddingBottom()) - nsvHost.getPaddingTop()), nsvHost.getScrollRange());
                    if (targetScrollY == nsvHost.getScrollY()) {
                        return false;
                    }
                    nsvHost.smoothScrollTo(0, targetScrollY);
                    return true;
                case 8192:
                    int targetScrollY2 = Math.max(nsvHost.getScrollY() - ((nsvHost.getHeight() - nsvHost.getPaddingBottom()) - nsvHost.getPaddingTop()), 0);
                    if (targetScrollY2 == nsvHost.getScrollY()) {
                        return false;
                    }
                    nsvHost.smoothScrollTo(0, targetScrollY2);
                    return true;
                default:
                    return false;
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int scrollRange;
            View host = view;
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            super.onInitializeAccessibilityNodeInfo(host, info);
            NestedScrollView nsvHost = (NestedScrollView) host;
            info.setClassName(ScrollView.class.getName());
            if (nsvHost.isEnabled() && (scrollRange = nsvHost.getScrollRange()) > 0) {
                info.setScrollable(true);
                if (nsvHost.getScrollY() > 0) {
                    info.addAction(8192);
                }
                if (nsvHost.getScrollY() < scrollRange) {
                    info.addAction(4096);
                }
            }
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            View host = view;
            AccessibilityEvent event = accessibilityEvent;
            super.onInitializeAccessibilityEvent(host, event);
            NestedScrollView nsvHost = (NestedScrollView) host;
            event.setClassName(ScrollView.class.getName());
            event.setScrollable(nsvHost.getScrollRange() > 0);
            event.setScrollX(nsvHost.getScrollX());
            event.setScrollY(nsvHost.getScrollY());
            AccessibilityRecordCompat.setMaxScrollX(event, nsvHost.getScrollX());
            AccessibilityRecordCompat.setMaxScrollY(event, nsvHost.getScrollRange());
        }
    }
}
