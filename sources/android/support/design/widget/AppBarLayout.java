package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.math.MathUtils;
import android.support.p000v4.util.ObjectsCompat;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.NestedScrollingChild;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class AppBarLayout extends LinearLayout {
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE = 0;
    private int downPreScrollRange;
    private int downScrollRange;
    private boolean haveChildWithInterpolator;
    private WindowInsetsCompat lastInsets;
    private boolean liftOnScroll;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List<BaseOnOffsetChangedListener> listeners;
    private int pendingAction;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    public interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
        void onOffsetChanged(T t, int i);
    }

    public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener<AppBarLayout> {
        void onOffsetChanged(AppBarLayout appBarLayout, int i);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppBarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppBarLayout(android.content.Context r12, android.util.AttributeSet r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            r5 = -1
            r4.totalScrollRange = r5
            r4 = r0
            r5 = -1
            r4.downPreScrollRange = r5
            r4 = r0
            r5 = -1
            r4.downScrollRange = r5
            r4 = r0
            r5 = 0
            r4.pendingAction = r5
            r4 = r0
            r5 = 1
            r4.setOrientation(r5)
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 21
            if (r4 < r5) goto L_0x0030
            r4 = r0
            android.support.design.widget.ViewUtilsLollipop.setBoundsViewOutlineProvider(r4)
            r4 = r0
            r5 = r2
            r6 = 0
            int r7 = android.support.design.C0064R.C0068style.Widget_Design_AppBarLayout
            android.support.design.widget.ViewUtilsLollipop.setStateListAnimatorFromAttrs(r4, r5, r6, r7)
        L_0x0030:
            r4 = r1
            r5 = r2
            int[] r6 = android.support.design.C0064R.styleable.AppBarLayout
            r7 = 0
            int r8 = android.support.design.C0064R.C0068style.Widget_Design_AppBarLayout
            r9 = 0
            int[] r9 = new int[r9]
            android.content.res.TypedArray r4 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r4, r5, r6, r7, r8, r9)
            r3 = r4
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.AppBarLayout_android_background
            android.graphics.drawable.Drawable r5 = r5.getDrawable(r6)
            android.support.p000v4.view.ViewCompat.setBackground(r4, r5)
            r4 = r3
            int r5 = android.support.design.C0064R.styleable.AppBarLayout_expanded
            boolean r4 = r4.hasValue(r5)
            if (r4 == 0) goto L_0x0061
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.AppBarLayout_expanded
            r7 = 0
            boolean r5 = r5.getBoolean(r6, r7)
            r6 = 0
            r7 = 0
            r4.setExpanded(r5, r6, r7)
        L_0x0061:
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 21
            if (r4 < r5) goto L_0x007d
            r4 = r3
            int r5 = android.support.design.C0064R.styleable.AppBarLayout_elevation
            boolean r4 = r4.hasValue(r5)
            if (r4 == 0) goto L_0x007d
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.AppBarLayout_elevation
            r7 = 0
            int r5 = r5.getDimensionPixelSize(r6, r7)
            float r5 = (float) r5
            android.support.design.widget.ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(r4, r5)
        L_0x007d:
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 26
            if (r4 < r5) goto L_0x00ad
            r4 = r3
            int r5 = android.support.design.C0064R.styleable.AppBarLayout_android_keyboardNavigationCluster
            boolean r4 = r4.hasValue(r5)
            if (r4 == 0) goto L_0x0098
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.AppBarLayout_android_keyboardNavigationCluster
            r7 = 0
            boolean r5 = r5.getBoolean(r6, r7)
            r4.setKeyboardNavigationCluster(r5)
        L_0x0098:
            r4 = r3
            int r5 = android.support.design.C0064R.styleable.AppBarLayout_android_touchscreenBlocksFocus
            boolean r4 = r4.hasValue(r5)
            if (r4 == 0) goto L_0x00ad
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.AppBarLayout_android_touchscreenBlocksFocus
            r7 = 0
            boolean r5 = r5.getBoolean(r6, r7)
            r4.setTouchscreenBlocksFocus(r5)
        L_0x00ad:
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.AppBarLayout_liftOnScroll
            r7 = 0
            boolean r5 = r5.getBoolean(r6, r7)
            r4.liftOnScroll = r5
            r4 = r3
            r4.recycle()
            r4 = r0
            android.support.design.widget.AppBarLayout$1 r5 = new android.support.design.widget.AppBarLayout$1
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r0
            r6.<init>(r7)
            android.support.p000v4.view.ViewCompat.setOnApplyWindowInsetsListener(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void addOnOffsetChangedListener(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        List<BaseOnOffsetChangedListener> list;
        BaseOnOffsetChangedListener listener = baseOnOffsetChangedListener;
        if (this.listeners == null) {
            new ArrayList();
            this.listeners = list;
        }
        if (listener != null && !this.listeners.contains(listener)) {
            boolean add = this.listeners.add(listener);
        }
    }

    public void addOnOffsetChangedListener(OnOffsetChangedListener listener) {
        addOnOffsetChangedListener((BaseOnOffsetChangedListener) listener);
    }

    public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        BaseOnOffsetChangedListener listener = baseOnOffsetChangedListener;
        if (this.listeners != null && listener != null) {
            boolean remove = this.listeners.remove(listener);
        }
    }

    public void removeOnOffsetChangedListener(OnOffsetChangedListener listener) {
        removeOnOffsetChangedListener((BaseOnOffsetChangedListener) listener);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        invalidateScrollRanges();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        invalidateScrollRanges();
        this.haveChildWithInterpolator = false;
        int i = 0;
        int z = getChildCount();
        while (true) {
            if (i >= z) {
                break;
            } else if (((LayoutParams) getChildAt(i).getLayoutParams()).getScrollInterpolator() != null) {
                this.haveChildWithInterpolator = true;
                break;
            } else {
                i++;
            }
        }
        if (!this.liftableOverride) {
            boolean liftableState = setLiftableState(this.liftOnScroll || hasCollapsibleChild());
        }
    }

    private boolean hasCollapsibleChild() {
        int z = getChildCount();
        for (int i = 0; i < z; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).isCollapsible()) {
                return true;
            }
        }
        return false;
    }

    private void invalidateScrollRanges() {
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
    }

    public void setOrientation(int i) {
        Throwable th;
        int orientation = i;
        if (orientation != 1) {
            Throwable th2 = th;
            new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
            throw th2;
        }
        super.setOrientation(orientation);
    }

    public void setExpanded(boolean expanded) {
        setExpanded(expanded, ViewCompat.isLaidOut(this));
    }

    public void setExpanded(boolean expanded, boolean animate) {
        setExpanded(expanded, animate, true);
    }

    private void setExpanded(boolean expanded, boolean animate, boolean force) {
        this.pendingAction = (expanded ? 1 : 2) | (animate ? 4 : 0) | (force ? 8 : 0);
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        new LayoutParams(-1, -2);
        return layoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        LayoutParams layoutParams3;
        LayoutParams layoutParams4;
        ViewGroup.LayoutParams p = layoutParams;
        if (Build.VERSION.SDK_INT >= 19 && (p instanceof LinearLayout.LayoutParams)) {
            new LayoutParams((LinearLayout.LayoutParams) p);
            return layoutParams4;
        } else if (p instanceof ViewGroup.MarginLayoutParams) {
            new LayoutParams((ViewGroup.MarginLayoutParams) p);
            return layoutParams3;
        } else {
            new LayoutParams(p);
            return layoutParams2;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    public final int getTotalScrollRange() {
        if (this.totalScrollRange != -1) {
            return this.totalScrollRange;
        }
        int range = 0;
        int i = 0;
        int z = getChildCount();
        while (true) {
            if (i >= z) {
                break;
            }
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int flags = lp.scrollFlags;
            if ((flags & 1) == 0) {
                break;
            }
            range += childHeight + lp.topMargin + lp.bottomMargin;
            if ((flags & 2) != 0) {
                range -= ViewCompat.getMinimumHeight(child);
                break;
            }
            i++;
        }
        int max = Math.max(0, range - getTopInset());
        int i2 = max;
        this.totalScrollRange = i2;
        return max;
    }

    /* access modifiers changed from: package-private */
    public boolean hasScrollableChildren() {
        return getTotalScrollRange() != 0;
    }

    /* access modifiers changed from: package-private */
    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    /* access modifiers changed from: package-private */
    public int getDownNestedPreScrollRange() {
        if (this.downPreScrollRange != -1) {
            return this.downPreScrollRange;
        }
        int range = 0;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int flags = lp.scrollFlags;
            if ((flags & 5) == 5) {
                int range2 = range + lp.topMargin + lp.bottomMargin;
                if ((flags & 8) != 0) {
                    range = range2 + ViewCompat.getMinimumHeight(child);
                } else if ((flags & 2) != 0) {
                    range = range2 + (childHeight - ViewCompat.getMinimumHeight(child));
                } else {
                    range = range2 + (childHeight - getTopInset());
                }
            } else if (range > 0) {
                break;
            }
        }
        int max = Math.max(0, range);
        int i2 = max;
        this.downPreScrollRange = i2;
        return max;
    }

    /* access modifiers changed from: package-private */
    public int getDownNestedScrollRange() {
        if (this.downScrollRange != -1) {
            return this.downScrollRange;
        }
        int range = 0;
        int i = 0;
        int z = getChildCount();
        while (true) {
            if (i >= z) {
                break;
            }
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            int flags = lp.scrollFlags;
            if ((flags & 1) == 0) {
                break;
            }
            range += childHeight;
            if ((flags & 2) != 0) {
                range -= ViewCompat.getMinimumHeight(child) + getTopInset();
                break;
            }
            i++;
        }
        int max = Math.max(0, range);
        int i2 = max;
        this.downScrollRange = i2;
        return max;
    }

    /* access modifiers changed from: package-private */
    public void dispatchOffsetUpdates(int i) {
        int offset = i;
        if (this.listeners != null) {
            int z = this.listeners.size();
            for (int i2 = 0; i2 < z; i2++) {
                BaseOnOffsetChangedListener listener = this.listeners.get(i2);
                if (listener != null) {
                    listener.onOffsetChanged(this, offset);
                }
            }
        }
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minHeight = ViewCompat.getMinimumHeight(this);
        if (minHeight != 0) {
            return (minHeight * 2) + topInset;
        }
        int childCount = getChildCount();
        int lastChildMinHeight = childCount >= 1 ? ViewCompat.getMinimumHeight(getChildAt(childCount - 1)) : 0;
        if (lastChildMinHeight != 0) {
            return (lastChildMinHeight * 2) + topInset;
        }
        return getHeight() / 3;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int extraSpace = i;
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] extraStates = this.tmpStatesArray;
        int[] states = super.onCreateDrawableState(extraSpace + extraStates.length);
        extraStates[0] = this.liftable ? C0064R.attr.state_liftable : -C0064R.attr.state_liftable;
        extraStates[1] = (!this.liftable || !this.lifted) ? -C0064R.attr.state_lifted : C0064R.attr.state_lifted;
        extraStates[2] = this.liftable ? C0064R.attr.state_collapsible : -C0064R.attr.state_collapsible;
        extraStates[3] = (!this.liftable || !this.lifted) ? -C0064R.attr.state_collapsed : C0064R.attr.state_collapsed;
        return mergeDrawableStates(states, extraStates);
    }

    public boolean setLiftable(boolean liftable2) {
        this.liftableOverride = true;
        return setLiftableState(liftable2);
    }

    private boolean setLiftableState(boolean z) {
        boolean liftable2 = z;
        if (this.liftable == liftable2) {
            return false;
        }
        this.liftable = liftable2;
        refreshDrawableState();
        return true;
    }

    public boolean setLifted(boolean lifted2) {
        return setLiftedState(lifted2);
    }

    /* access modifiers changed from: package-private */
    public boolean setLiftedState(boolean z) {
        boolean lifted2 = z;
        if (this.lifted == lifted2) {
            return false;
        }
        this.lifted = lifted2;
        refreshDrawableState();
        return true;
    }

    public void setLiftOnScroll(boolean liftOnScroll2) {
        boolean z = liftOnScroll2;
        this.liftOnScroll = z;
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    @Deprecated
    public void setTargetElevation(float f) {
        float elevation = f;
        if (Build.VERSION.SDK_INT >= 21) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, elevation);
        }
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public int getPendingAction() {
        return this.pendingAction;
    }

    /* access modifiers changed from: package-private */
    public void resetPendingAction() {
        this.pendingAction = 0;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final int getTopInset() {
        return this.lastInsets != null ? this.lastInsets.getSystemWindowInsetTop() : 0;
    }

    /* access modifiers changed from: package-private */
    public WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat insets = windowInsetsCompat;
        WindowInsetsCompat newInsets = null;
        if (ViewCompat.getFitsSystemWindows(this)) {
            newInsets = insets;
        }
        if (!ObjectsCompat.equals(this.lastInsets, newInsets)) {
            this.lastInsets = newInsets;
            invalidateScrollRanges();
        }
        return insets;
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        static final int COLLAPSIBLE_FLAGS = 10;
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        public static final int SCROLL_FLAG_SNAP_MARGINS = 32;
        int scrollFlags = 1;
        Interpolator scrollInterpolator;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.content.Context r10, android.util.AttributeSet r11) {
            /*
                r9 = this;
                r0 = r9
                r1 = r10
                r2 = r11
                r5 = r0
                r6 = r1
                r7 = r2
                r5.<init>(r6, r7)
                r5 = r0
                r6 = 1
                r5.scrollFlags = r6
                r5 = r1
                r6 = r2
                int[] r7 = android.support.design.C0064R.styleable.AppBarLayout_Layout
                android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
                r3 = r5
                r5 = r0
                r6 = r3
                int r7 = android.support.design.C0064R.styleable.AppBarLayout_Layout_layout_scrollFlags
                r8 = 0
                int r6 = r6.getInt(r7, r8)
                r5.scrollFlags = r6
                r5 = r3
                int r6 = android.support.design.C0064R.styleable.AppBarLayout_Layout_layout_scrollInterpolator
                boolean r5 = r5.hasValue(r6)
                if (r5 == 0) goto L_0x003c
                r5 = r3
                int r6 = android.support.design.C0064R.styleable.AppBarLayout_Layout_layout_scrollInterpolator
                r7 = 0
                int r5 = r5.getResourceId(r6, r7)
                r4 = r5
                r5 = r0
                r6 = r1
                r7 = r4
                android.view.animation.Interpolator r6 = android.view.animation.AnimationUtils.loadInterpolator(r6, r7)
                r5.scrollInterpolator = r6
            L_0x003c:
                r5 = r3
                r5.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height, float weight) {
            super(width, height, weight);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        @RequiresApi(19)
        public LayoutParams(LinearLayout.LayoutParams source) {
            super(source);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        @android.support.annotation.RequiresApi(19)
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.support.design.widget.AppBarLayout.LayoutParams r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = 1
                r2.scrollFlags = r3
                r2 = r0
                r3 = r1
                int r3 = r3.scrollFlags
                r2.scrollFlags = r3
                r2 = r0
                r3 = r1
                android.view.animation.Interpolator r3 = r3.scrollInterpolator
                r2.scrollInterpolator = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.LayoutParams.<init>(android.support.design.widget.AppBarLayout$LayoutParams):void");
        }

        public void setScrollFlags(int flags) {
            int i = flags;
            this.scrollFlags = i;
        }

        public int getScrollFlags() {
            return this.scrollFlags;
        }

        public void setScrollInterpolator(Interpolator interpolator) {
            Interpolator interpolator2 = interpolator;
            this.scrollInterpolator = interpolator2;
        }

        public Interpolator getScrollInterpolator() {
            return this.scrollInterpolator;
        }

        /* access modifiers changed from: package-private */
        public boolean isCollapsible() {
            return (this.scrollFlags & 1) == 1 && (this.scrollFlags & 10) != 0;
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            return super.onLayoutChild(coordinatorLayout, appBarLayout, i);
        }

        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3, int i4) {
            return super.onMeasureChild(coordinatorLayout, appBarLayout, i, i2, i3, i4);
        }

        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
            super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
        }

        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int i3, int i4, int i5) {
            super.onNestedScroll(coordinatorLayout, appBarLayout, view, i, i2, i3, i4, i5);
        }

        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
        }

        public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.onSaveInstanceState(coordinatorLayout, appBarLayout);
        }

        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
            return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i, i2);
        }

        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
            super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i);
        }

        public /* bridge */ /* synthetic */ void setDragCallback(@Nullable BaseBehavior.BaseDragCallback baseDragCallback) {
            super.setDragCallback(baseDragCallback);
        }

        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i) {
            return super.setLeftAndRightOffset(i);
        }

        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i) {
            return super.setTopAndBottomOffset(i);
        }

        public static abstract class DragCallback extends BaseBehavior.BaseDragCallback<AppBarLayout> {
            public DragCallback() {
            }
        }

        public Behavior() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
    }

    protected static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        private static final int INVALID_POSITION = -1;
        private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
        private WeakReference<View> lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        /* access modifiers changed from: private */
        public int offsetDelta;
        private int offsetToChildIndexOnLayout = -1;
        private boolean offsetToChildIndexOnLayoutIsMinHeight;
        private float offsetToChildIndexOnLayoutPerc;
        private BaseDragCallback onDragCallback;

        public static abstract class BaseDragCallback<T extends AppBarLayout> {
            public abstract boolean canDrag(@NonNull T t);

            public BaseDragCallback() {
            }
        }

        public BaseBehavior() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BaseBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public boolean onStartNestedScroll(CoordinatorLayout parent, T t, View directTargetChild, View view, int nestedScrollAxes, int i) {
            T child = t;
            View view2 = view;
            int type = i;
            boolean started = (nestedScrollAxes & 2) != 0 && (child.isLiftOnScroll() || canScrollChildren(parent, child, directTargetChild));
            if (started && this.offsetAnimator != null) {
                this.offsetAnimator.cancel();
            }
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = type;
            return started;
        }

        private boolean canScrollChildren(CoordinatorLayout parent, T t, View directTargetChild) {
            T child = t;
            return child.hasScrollableChildren() && parent.getHeight() - directTargetChild.getHeight() <= child.getHeight();
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, T t, View view, int i, int i2, int[] iArr, int i3) {
            int min;
            int max;
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            T child = t;
            View target = view;
            int i4 = i;
            int dy = i2;
            int[] consumed = iArr;
            int type = i3;
            if (dy != 0) {
                if (dy < 0) {
                    min = -child.getTotalScrollRange();
                    max = min + child.getDownNestedPreScrollRange();
                } else {
                    min = -child.getUpNestedPreScrollRange();
                    max = 0;
                }
                if (min != max) {
                    consumed[1] = scroll(coordinatorLayout2, child, dy, min, max);
                    stopNestedScrollIfNeeded(dy, child, target, type);
                }
            }
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout, T t, View view, int i, int i2, int i3, int i4, int i5) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            T child = t;
            View target = view;
            int i6 = i;
            int i7 = i2;
            int i8 = i3;
            int dyUnconsumed = i4;
            int type = i5;
            if (dyUnconsumed < 0) {
                int scroll = scroll(coordinatorLayout2, child, dyUnconsumed, -child.getDownNestedScrollRange(), 0);
                stopNestedScrollIfNeeded(dyUnconsumed, child, target, type);
            }
            if (child.isLiftOnScroll()) {
                boolean liftedState = child.setLiftedState(target.getScrollY() > 0);
            }
        }

        private void stopNestedScrollIfNeeded(int i, T t, View view, int type) {
            int dy = i;
            T child = t;
            View target = view;
            if (type == 1) {
                int curOffset = getTopBottomOffsetForScrollingSibling();
                if ((dy < 0 && curOffset == 0) || (dy > 0 && curOffset == (-child.getDownNestedScrollRange()))) {
                    ViewCompat.stopNestedScroll(target, 1);
                }
            }
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, T t, View view, int i) {
            WeakReference<View> weakReference;
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            T abl = t;
            View target = view;
            int type = i;
            if (this.lastStartedType == 0 || type == 1) {
                snapToChildIfNeeded(coordinatorLayout2, abl);
            }
            new WeakReference<>(target);
            this.lastNestedScrollingChildRef = weakReference;
        }

        public void setDragCallback(@Nullable BaseDragCallback callback) {
            BaseDragCallback baseDragCallback = callback;
            this.onDragCallback = baseDragCallback;
        }

        private void animateOffsetTo(CoordinatorLayout coordinatorLayout, T t, int i, float velocity) {
            int duration;
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            T child = t;
            int offset = i;
            int distance = Math.abs(getTopBottomOffsetForScrollingSibling() - offset);
            float velocity2 = Math.abs(velocity);
            if (velocity2 > 0.0f) {
                duration = 3 * Math.round(1000.0f * (((float) distance) / velocity2));
            } else {
                duration = (int) (((((float) distance) / ((float) child.getHeight())) + 1.0f) * 150.0f);
            }
            animateOffsetWithDuration(coordinatorLayout2, child, offset, duration);
        }

        private void animateOffsetWithDuration(CoordinatorLayout coordinatorLayout, T t, int i, int i2) {
            ValueAnimator valueAnimator;
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            T child = t;
            int offset = i;
            int duration = i2;
            int currentOffset = getTopBottomOffsetForScrollingSibling();
            if (currentOffset != offset) {
                if (this.offsetAnimator == null) {
                    new ValueAnimator();
                    this.offsetAnimator = valueAnimator;
                    this.offsetAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                    final CoordinatorLayout coordinatorLayout3 = coordinatorLayout2;
                    final T t2 = child;
                    new ValueAnimator.AnimatorUpdateListener(this) {
                        final /* synthetic */ BaseBehavior this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void onAnimationUpdate(ValueAnimator animator) {
                            int headerTopBottomOffset = this.this$0.setHeaderTopBottomOffset(coordinatorLayout3, t2, ((Integer) animator.getAnimatedValue()).intValue());
                        }
                    };
                    this.offsetAnimator.addUpdateListener(animatorUpdateListener);
                } else {
                    this.offsetAnimator.cancel();
                }
                ValueAnimator duration2 = this.offsetAnimator.setDuration((long) Math.min(duration, MAX_OFFSET_ANIMATION_DURATION));
                ValueAnimator valueAnimator2 = this.offsetAnimator;
                int[] iArr = new int[2];
                iArr[0] = currentOffset;
                int[] iArr2 = iArr;
                iArr2[1] = offset;
                valueAnimator2.setIntValues(iArr2);
                this.offsetAnimator.start();
            } else if (this.offsetAnimator != null && this.offsetAnimator.isRunning()) {
                this.offsetAnimator.cancel();
            }
        }

        private int getChildIndexOnOffset(T t, int i) {
            T abl = t;
            int offset = i;
            int count = abl.getChildCount();
            for (int i2 = 0; i2 < count; i2++) {
                View child = abl.getChildAt(i2);
                int top = child.getTop();
                int bottom = child.getBottom();
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (checkFlag(lp.getScrollFlags(), 32)) {
                    top -= lp.topMargin;
                    bottom += lp.bottomMargin;
                }
                if (top <= (-offset) && bottom >= (-offset)) {
                    return i2;
                }
            }
            return -1;
        }

        private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, T t) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            T abl = t;
            int offset = getTopBottomOffsetForScrollingSibling();
            int offsetChildIndex = getChildIndexOnOffset(abl, offset);
            if (offsetChildIndex >= 0) {
                View offsetChild = abl.getChildAt(offsetChildIndex);
                LayoutParams lp = (LayoutParams) offsetChild.getLayoutParams();
                int flags = lp.getScrollFlags();
                if ((flags & 17) == 17) {
                    int snapTop = -offsetChild.getTop();
                    int snapBottom = -offsetChild.getBottom();
                    if (offsetChildIndex == abl.getChildCount() - 1) {
                        snapBottom += abl.getTopInset();
                    }
                    if (checkFlag(flags, 2)) {
                        snapBottom += ViewCompat.getMinimumHeight(offsetChild);
                    } else if (checkFlag(flags, 5)) {
                        int seam = snapBottom + ViewCompat.getMinimumHeight(offsetChild);
                        if (offset < seam) {
                            snapTop = seam;
                        } else {
                            snapBottom = seam;
                        }
                    }
                    if (checkFlag(flags, 32)) {
                        snapTop += lp.topMargin;
                        snapBottom -= lp.bottomMargin;
                    }
                    animateOffsetTo(coordinatorLayout2, abl, MathUtils.clamp(offset < (snapBottom + snapTop) / 2 ? snapBottom : snapTop, -abl.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private static boolean checkFlag(int flags, int i) {
            int check = i;
            return (flags & check) == check;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, T t, int i, int i2, int i3, int i4) {
            CoordinatorLayout parent = coordinatorLayout;
            T child = t;
            int parentWidthMeasureSpec = i;
            int widthUsed = i2;
            int parentHeightMeasureSpec = i3;
            int heightUsed = i4;
            if (((CoordinatorLayout.LayoutParams) child.getLayoutParams()).height != -2) {
                return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
            }
            parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, View.MeasureSpec.makeMeasureSpec(0, 0), heightUsed);
            return true;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, T t, int layoutDirection) {
            int offset;
            CoordinatorLayout parent = coordinatorLayout;
            T abl = t;
            boolean handled = super.onLayoutChild(parent, abl, layoutDirection);
            int pendingAction = abl.getPendingAction();
            if (this.offsetToChildIndexOnLayout >= 0 && (pendingAction & 8) == 0) {
                View child = abl.getChildAt(this.offsetToChildIndexOnLayout);
                int offset2 = -child.getBottom();
                if (this.offsetToChildIndexOnLayoutIsMinHeight) {
                    offset = offset2 + ViewCompat.getMinimumHeight(child) + abl.getTopInset();
                } else {
                    offset = offset2 + Math.round(((float) child.getHeight()) * this.offsetToChildIndexOnLayoutPerc);
                }
                int headerTopBottomOffset = setHeaderTopBottomOffset(parent, abl, offset);
            } else if (pendingAction != 0) {
                boolean animate = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int offset3 = -abl.getUpNestedPreScrollRange();
                    if (animate) {
                        animateOffsetTo(parent, abl, offset3, 0.0f);
                    } else {
                        int headerTopBottomOffset2 = setHeaderTopBottomOffset(parent, abl, offset3);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (animate) {
                        animateOffsetTo(parent, abl, 0, 0.0f);
                    } else {
                        int headerTopBottomOffset3 = setHeaderTopBottomOffset(parent, abl, 0);
                    }
                }
            }
            abl.resetPendingAction();
            this.offsetToChildIndexOnLayout = -1;
            boolean topAndBottomOffset = setTopAndBottomOffset(MathUtils.clamp(getTopAndBottomOffset(), -abl.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(parent, abl, getTopAndBottomOffset(), 0, true);
            abl.dispatchOffsetUpdates(getTopAndBottomOffset());
            return handled;
        }

        /* access modifiers changed from: package-private */
        public boolean canDragView(T t) {
            T view = t;
            if (this.onDragCallback != null) {
                return this.onDragCallback.canDrag(view);
            }
            if (this.lastNestedScrollingChildRef == null) {
                return true;
            }
            View scrollingView = (View) this.lastNestedScrollingChildRef.get();
            return scrollingView != null && scrollingView.isShown() && !scrollingView.canScrollVertically(-1);
        }

        /* access modifiers changed from: package-private */
        public void onFlingFinished(CoordinatorLayout parent, T layout) {
            snapToChildIfNeeded(parent, layout);
        }

        /* access modifiers changed from: package-private */
        public int getMaxDragOffset(T view) {
            return -view.getDownNestedScrollRange();
        }

        /* access modifiers changed from: package-private */
        public int getScrollRangeForDragFling(T view) {
            return view.getTotalScrollRange();
        }

        /* access modifiers changed from: package-private */
        public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, T t, int i, int i2, int i3) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            T appBarLayout = t;
            int newOffset = i;
            int minOffset = i2;
            int maxOffset = i3;
            int curOffset = getTopBottomOffsetForScrollingSibling();
            int consumed = 0;
            if (minOffset == 0 || curOffset < minOffset || curOffset > maxOffset) {
                this.offsetDelta = 0;
            } else {
                int newOffset2 = MathUtils.clamp(newOffset, minOffset, maxOffset);
                if (curOffset != newOffset2) {
                    int interpolatedOffset = appBarLayout.hasChildWithInterpolator() ? interpolateOffset(appBarLayout, newOffset2) : newOffset2;
                    boolean offsetChanged = setTopAndBottomOffset(interpolatedOffset);
                    consumed = curOffset - newOffset2;
                    this.offsetDelta = newOffset2 - interpolatedOffset;
                    if (!offsetChanged && appBarLayout.hasChildWithInterpolator()) {
                        coordinatorLayout2.dispatchDependentViewsChanged(appBarLayout);
                    }
                    appBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
                    updateAppBarLayoutDrawableState(coordinatorLayout2, appBarLayout, newOffset2, newOffset2 < curOffset ? -1 : 1, false);
                }
            }
            return consumed;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public boolean isOffsetAnimatorRunning() {
            return this.offsetAnimator != null && this.offsetAnimator.isRunning();
        }

        private int interpolateOffset(T t, int i) {
            T layout = t;
            int offset = i;
            int absOffset = Math.abs(offset);
            int i2 = 0;
            int z = layout.getChildCount();
            while (true) {
                if (i2 >= z) {
                    break;
                }
                View child = layout.getChildAt(i2);
                LayoutParams childLp = (LayoutParams) child.getLayoutParams();
                Interpolator interpolator = childLp.getScrollInterpolator();
                if (absOffset < child.getTop() || absOffset > child.getBottom()) {
                    i2++;
                } else if (interpolator != null) {
                    int childScrollableHeight = 0;
                    int flags = childLp.getScrollFlags();
                    if ((flags & 1) != 0) {
                        childScrollableHeight = 0 + child.getHeight() + childLp.topMargin + childLp.bottomMargin;
                        if ((flags & 2) != 0) {
                            childScrollableHeight -= ViewCompat.getMinimumHeight(child);
                        }
                    }
                    if (ViewCompat.getFitsSystemWindows(child)) {
                        childScrollableHeight -= layout.getTopInset();
                    }
                    if (childScrollableHeight > 0) {
                        return Integer.signum(offset) * (child.getTop() + Math.round(((float) childScrollableHeight) * interpolator.getInterpolation(((float) (absOffset - child.getTop())) / ((float) childScrollableHeight))));
                    }
                }
            }
            return offset;
        }

        private void updateAppBarLayoutDrawableState(CoordinatorLayout coordinatorLayout, T t, int i, int i2, boolean z) {
            View scrollingChild;
            CoordinatorLayout parent = coordinatorLayout;
            T layout = t;
            int offset = i;
            int direction = i2;
            boolean forceJump = z;
            View child = getAppBarChildOnOffset(layout, offset);
            if (child != null) {
                int flags = ((LayoutParams) child.getLayoutParams()).getScrollFlags();
                boolean lifted = false;
                if ((flags & 1) != 0) {
                    int minHeight = ViewCompat.getMinimumHeight(child);
                    if (direction > 0 && (flags & 12) != 0) {
                        lifted = (-offset) >= (child.getBottom() - minHeight) - layout.getTopInset();
                    } else if ((flags & 2) != 0) {
                        lifted = (-offset) >= (child.getBottom() - minHeight) - layout.getTopInset();
                    }
                }
                if (layout.isLiftOnScroll() && (scrollingChild = findFirstScrollingChild(parent)) != null) {
                    lifted = scrollingChild.getScrollY() > 0;
                }
                boolean changed = layout.setLiftedState(lifted);
                if (Build.VERSION.SDK_INT < 11) {
                    return;
                }
                if (forceJump || (changed && shouldJumpElevationState(parent, layout))) {
                    layout.jumpDrawablesToCurrentState();
                }
            }
        }

        private boolean shouldJumpElevationState(CoordinatorLayout parent, T layout) {
            List<View> dependencies = parent.getDependents(layout);
            int size = dependencies.size();
            for (int i = 0; i < size; i++) {
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) dependencies.get(i).getLayoutParams()).getBehavior();
                if (behavior instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) behavior).getOverlayTop() != 0;
                }
            }
            return false;
        }

        private static View getAppBarChildOnOffset(AppBarLayout appBarLayout, int offset) {
            AppBarLayout layout = appBarLayout;
            int absOffset = Math.abs(offset);
            int z = layout.getChildCount();
            for (int i = 0; i < z; i++) {
                View child = layout.getChildAt(i);
                if (absOffset >= child.getTop() && absOffset <= child.getBottom()) {
                    return child;
                }
            }
            return null;
        }

        @Nullable
        private View findFirstScrollingChild(CoordinatorLayout coordinatorLayout) {
            CoordinatorLayout parent = coordinatorLayout;
            int z = parent.getChildCount();
            for (int i = 0; i < z; i++) {
                View child = parent.getChildAt(i);
                if (child instanceof NestedScrollingChild) {
                    return child;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout parent, T t) {
            SavedState savedState;
            T abl = t;
            Parcelable superState = super.onSaveInstanceState(parent, abl);
            int offset = getTopAndBottomOffset();
            int i = 0;
            int count = abl.getChildCount();
            while (i < count) {
                View child = abl.getChildAt(i);
                int visBottom = child.getBottom() + offset;
                if (child.getTop() + offset > 0 || visBottom < 0) {
                    i++;
                } else {
                    new SavedState(superState);
                    SavedState ss = savedState;
                    ss.firstVisibleChildIndex = i;
                    ss.firstVisibleChildAtMinimumHeight = visBottom == ViewCompat.getMinimumHeight(child) + abl.getTopInset();
                    ss.firstVisibleChildPercentageShown = ((float) visBottom) / ((float) child.getHeight());
                    return ss;
                }
            }
            return superState;
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, T t, Parcelable parcelable) {
            CoordinatorLayout parent = coordinatorLayout;
            T appBarLayout = t;
            Parcelable state = parcelable;
            if (state instanceof SavedState) {
                SavedState ss = (SavedState) state;
                super.onRestoreInstanceState(parent, appBarLayout, ss.getSuperState());
                this.offsetToChildIndexOnLayout = ss.firstVisibleChildIndex;
                this.offsetToChildIndexOnLayoutPerc = ss.firstVisibleChildPercentageShown;
                this.offsetToChildIndexOnLayoutIsMinHeight = ss.firstVisibleChildAtMinimumHeight;
                return;
            }
            super.onRestoreInstanceState(parent, appBarLayout, state);
            this.offsetToChildIndexOnLayout = -1;
        }

        protected static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR;
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;

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
                    r3.firstVisibleChildIndex = r4
                    r3 = r0
                    r4 = r1
                    float r4 = r4.readFloat()
                    r3.firstVisibleChildPercentageShown = r4
                    r3 = r0
                    r4 = r1
                    byte r4 = r4.readByte()
                    if (r4 == 0) goto L_0x0025
                    r4 = 1
                L_0x0022:
                    r3.firstVisibleChildAtMinimumHeight = r4
                    return
                L_0x0025:
                    r4 = 0
                    goto L_0x0022
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.BaseBehavior.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SavedState(Parcelable superState) {
                super(superState);
            }

            public void writeToParcel(Parcel parcel, int flags) {
                Parcel dest = parcel;
                super.writeToParcel(dest, flags);
                dest.writeInt(this.firstVisibleChildIndex);
                dest.writeFloat(this.firstVisibleChildPercentageShown);
                dest.writeByte((byte) (this.firstVisibleChildAtMinimumHeight ? 1 : 0));
            }

            static {
                Parcelable.Creator<SavedState> creator;
                new Parcelable.ClassLoaderCreator<SavedState>() {
                    public SavedState createFromParcel(Parcel source, ClassLoader loader) {
                        SavedState savedState;
                        new SavedState(source, loader);
                        return savedState;
                    }

                    public SavedState createFromParcel(Parcel source) {
                        SavedState savedState;
                        new SavedState(source, (ClassLoader) null);
                        return savedState;
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
                CREATOR = creator;
            }
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            return super.onLayoutChild(coordinatorLayout, view, i);
        }

        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            return super.onMeasureChild(coordinatorLayout, view, i, i2, i3, i4);
        }

        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i) {
            return super.setLeftAndRightOffset(i);
        }

        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i) {
            return super.setTopAndBottomOffset(i);
        }

        public ScrollingViewBehavior() {
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ScrollingViewBehavior(android.content.Context r9, android.util.AttributeSet r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r1
                r5 = r2
                int[] r6 = android.support.design.C0064R.styleable.ScrollingViewBehavior_Layout
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                int r6 = android.support.design.C0064R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop
                r7 = 0
                int r5 = r5.getDimensionPixelSize(r6, r7)
                r4.setOverlayTop(r5)
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.ScrollingViewBehavior.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View dependency) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            View view2 = view;
            return dependency instanceof AppBarLayout;
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            View child = view;
            View dependency = view2;
            offsetChildAsNeeded(child, dependency);
            updateLiftedStateIfNeeded(child, dependency);
            return false;
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            CoordinatorLayout parent = coordinatorLayout;
            View child = view;
            Rect rectangle = rect;
            boolean immediate = z;
            AppBarLayout header = findFirstDependency(parent.getDependencies(child));
            if (header != null) {
                rectangle.offset(child.getLeft(), child.getTop());
                Rect parentRect = this.tempRect1;
                parentRect.set(0, 0, parent.getWidth(), parent.getHeight());
                if (!parentRect.contains(rectangle)) {
                    header.setExpanded(false, !immediate);
                    return true;
                }
            }
            return false;
        }

        private void offsetChildAsNeeded(View view, View view2) {
            View child = view;
            View dependency = view2;
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
            if (behavior instanceof BaseBehavior) {
                ViewCompat.offsetTopAndBottom(child, (((dependency.getBottom() - child.getTop()) + ((BaseBehavior) behavior).offsetDelta) + getVerticalLayoutGap()) - getOverlapPixelsForOffset(dependency));
            }
        }

        /* access modifiers changed from: package-private */
        public float getOverlapRatioForOffset(View view) {
            View header = view;
            if (header instanceof AppBarLayout) {
                AppBarLayout abl = (AppBarLayout) header;
                int totalScrollRange = abl.getTotalScrollRange();
                int preScrollDown = abl.getDownNestedPreScrollRange();
                int offset = getAppBarLayoutOffset(abl);
                if (preScrollDown != 0 && totalScrollRange + offset <= preScrollDown) {
                    return 0.0f;
                }
                int availScrollRange = totalScrollRange - preScrollDown;
                if (availScrollRange != 0) {
                    return 1.0f + (((float) offset) / ((float) availScrollRange));
                }
            }
            return 0.0f;
        }

        private static int getAppBarLayoutOffset(AppBarLayout abl) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) abl.getLayoutParams()).getBehavior();
            if (behavior instanceof BaseBehavior) {
                return ((BaseBehavior) behavior).getTopBottomOffsetForScrollingSibling();
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public AppBarLayout findFirstDependency(List<View> list) {
            List<View> views = list;
            int z = views.size();
            for (int i = 0; i < z; i++) {
                View view = views.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public int getScrollRange(View view) {
            View v = view;
            if (v instanceof AppBarLayout) {
                return ((AppBarLayout) v).getTotalScrollRange();
            }
            return super.getScrollRange(v);
        }

        private void updateLiftedStateIfNeeded(View view, View view2) {
            View child = view;
            View dependency = view2;
            if (dependency instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) dependency;
                if (appBarLayout.isLiftOnScroll()) {
                    boolean liftedState = appBarLayout.setLiftedState(child.getScrollY() > 0);
                }
            }
        }
    }
}
