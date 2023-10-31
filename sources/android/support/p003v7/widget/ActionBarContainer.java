package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p003v7.appcompat.C0425R;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ActionBarContainer */
public class ActionBarContainer extends FrameLayout {
    private View mActionBarView;
    Drawable mBackground;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActionBarContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActionBarContainer(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            android.support.v7.widget.ActionBarBackgroundDrawable r5 = new android.support.v7.widget.ActionBarBackgroundDrawable
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r0
            r6.<init>(r7)
            r3 = r5
            r5 = r0
            r6 = r3
            android.support.p000v4.view.ViewCompat.setBackground(r5, r6)
            r5 = r1
            r6 = r2
            int[] r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionBar
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
            r4 = r5
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionBar_background
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.mBackground = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionBar_backgroundStacked
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.mStackedBackground = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionBar_height
            r8 = -1
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.mHeight = r6
            r5 = r0
            int r5 = r5.getId()
            int r6 = android.support.p003v7.appcompat.C0425R.C0427id.split_action_bar
            if (r5 != r6) goto L_0x0057
            r5 = r0
            r6 = 1
            r5.mIsSplit = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionBar_backgroundSplit
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.mSplitBackground = r6
        L_0x0057:
            r5 = r4
            r5.recycle()
            r5 = r0
            r6 = r0
            boolean r6 = r6.mIsSplit
            if (r6 == 0) goto L_0x006d
            r6 = r0
            android.graphics.drawable.Drawable r6 = r6.mSplitBackground
            if (r6 != 0) goto L_0x006b
            r6 = 1
        L_0x0067:
            r5.setWillNotDraw(r6)
            return
        L_0x006b:
            r6 = 0
            goto L_0x0067
        L_0x006d:
            r6 = r0
            android.graphics.drawable.Drawable r6 = r6.mBackground
            if (r6 != 0) goto L_0x0079
            r6 = r0
            android.graphics.drawable.Drawable r6 = r6.mStackedBackground
            if (r6 != 0) goto L_0x0079
            r6 = 1
            goto L_0x0067
        L_0x0079:
            r6 = 0
            goto L_0x0067
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ActionBarContainer.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = findViewById(C0425R.C0427id.action_bar);
        this.mContextView = findViewById(C0425R.C0427id.action_context_bar);
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable bg = drawable;
        if (this.mBackground != null) {
            this.mBackground.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mBackground);
        }
        this.mBackground = bg;
        if (bg != null) {
            bg.setCallback(this);
            if (this.mActionBarView != null) {
                this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
            }
        }
        setWillNotDraw(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable bg = drawable;
        if (this.mStackedBackground != null) {
            this.mStackedBackground.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mStackedBackground);
        }
        this.mStackedBackground = bg;
        if (bg != null) {
            bg.setCallback(this);
            if (this.mIsStacked && this.mStackedBackground != null) {
                this.mStackedBackground.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
            }
        }
        setWillNotDraw(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable bg = drawable;
        if (this.mSplitBackground != null) {
            this.mSplitBackground.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mSplitBackground);
        }
        this.mSplitBackground = bg;
        if (bg != null) {
            bg.setCallback(this);
            if (this.mIsSplit && this.mSplitBackground != null) {
                this.mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        setWillNotDraw(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null);
        invalidate();
    }

    public void setVisibility(int i) {
        int visibility = i;
        super.setVisibility(visibility);
        boolean isVisible = visibility == 0;
        if (this.mBackground != null) {
            boolean visible = this.mBackground.setVisible(isVisible, false);
        }
        if (this.mStackedBackground != null) {
            boolean visible2 = this.mStackedBackground.setVisible(isVisible, false);
        }
        if (this.mSplitBackground != null) {
            boolean visible3 = this.mSplitBackground.setVisible(isVisible, false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        Drawable who = drawable;
        return (who == this.mBackground && !this.mIsSplit) || (who == this.mStackedBackground && this.mIsStacked) || ((who == this.mSplitBackground && this.mIsSplit) || super.verifyDrawable(who));
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackground != null && this.mBackground.isStateful()) {
            boolean state = this.mBackground.setState(getDrawableState());
        }
        if (this.mStackedBackground != null && this.mStackedBackground.isStateful()) {
            boolean state2 = this.mStackedBackground.setState(getDrawableState());
        }
        if (this.mSplitBackground != null && this.mSplitBackground.isStateful()) {
            boolean state3 = this.mSplitBackground.setState(getDrawableState());
        }
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mBackground != null) {
            this.mBackground.jumpToCurrentState();
        }
        if (this.mStackedBackground != null) {
            this.mStackedBackground.jumpToCurrentState();
        }
        if (this.mSplitBackground != null) {
            this.mSplitBackground.jumpToCurrentState();
        }
    }

    public void setTransitioning(boolean z) {
        boolean isTransitioning = z;
        this.mIsTransitioning = isTransitioning;
        setDescendantFocusability(isTransitioning ? 393216 : 262144);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mIsTransitioning || super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        boolean onTouchEvent = super.onTouchEvent(ev);
        return true;
    }

    public boolean onHoverEvent(MotionEvent ev) {
        boolean onHoverEvent = super.onHoverEvent(ev);
        return true;
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        ScrollingTabContainerView tabView = scrollingTabContainerView;
        if (this.mTabContainer != null) {
            removeView(this.mTabContainer);
        }
        this.mTabContainer = tabView;
        if (tabView != null) {
            addView(tabView);
            ViewGroup.LayoutParams lp = tabView.getLayoutParams();
            lp.width = -1;
            lp.height = -2;
            tabView.setAllowCollapse(false);
        }
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        View view2 = view;
        ActionMode.Callback callback2 = callback;
        return null;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        View child = view;
        ActionMode.Callback callback2 = callback;
        int type = i;
        if (type != 0) {
            return super.startActionModeForChild(child, callback2, type);
        }
        return null;
    }

    private boolean isCollapsed(View view) {
        View view2 = view;
        return view2 == null || view2.getVisibility() == 8 || view2.getMeasuredHeight() == 0;
    }

    private int getMeasuredHeightWithMargins(View view) {
        View view2 = view;
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view2.getLayoutParams();
        return view2.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
    }

    public void onMeasure(int i, int i2) {
        int topMarginForTabs;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (this.mActionBarView == null && View.MeasureSpec.getMode(heightMeasureSpec) == Integer.MIN_VALUE && this.mHeight >= 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(this.mHeight, View.MeasureSpec.getSize(heightMeasureSpec)), Integer.MIN_VALUE);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mActionBarView != null) {
            int mode = View.MeasureSpec.getMode(heightMeasureSpec);
            if (this.mTabContainer != null && this.mTabContainer.getVisibility() != 8 && mode != 1073741824) {
                if (!isCollapsed(this.mActionBarView)) {
                    topMarginForTabs = getMeasuredHeightWithMargins(this.mActionBarView);
                } else if (!isCollapsed(this.mContextView)) {
                    topMarginForTabs = getMeasuredHeightWithMargins(this.mContextView);
                } else {
                    topMarginForTabs = 0;
                }
                setMeasuredDimension(getMeasuredWidth(), Math.min(topMarginForTabs + getMeasuredHeightWithMargins(this.mTabContainer), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(heightMeasureSpec) : Integer.MAX_VALUE));
            }
        }
    }

    public void onLayout(boolean changed, int i, int t, int i2, int b) {
        int l = i;
        int r = i2;
        super.onLayout(changed, l, t, r, b);
        View tabContainer = this.mTabContainer;
        boolean hasTabs = (tabContainer == null || tabContainer.getVisibility() == 8) ? false : true;
        if (!(tabContainer == null || tabContainer.getVisibility() == 8)) {
            int containerHeight = getMeasuredHeight();
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) tabContainer.getLayoutParams();
            tabContainer.layout(l, (containerHeight - tabContainer.getMeasuredHeight()) - lp.bottomMargin, r, containerHeight - lp.bottomMargin);
        }
        boolean needsInvalidate = false;
        if (!this.mIsSplit) {
            if (this.mBackground != null) {
                if (this.mActionBarView.getVisibility() == 0) {
                    this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
                } else if (this.mContextView == null || this.mContextView.getVisibility() != 0) {
                    this.mBackground.setBounds(0, 0, 0, 0);
                } else {
                    this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
                }
                needsInvalidate = true;
            }
            this.mIsStacked = hasTabs;
            if (hasTabs && this.mStackedBackground != null) {
                this.mStackedBackground.setBounds(tabContainer.getLeft(), tabContainer.getTop(), tabContainer.getRight(), tabContainer.getBottom());
                needsInvalidate = true;
            }
        } else if (this.mSplitBackground != null) {
            this.mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            needsInvalidate = true;
        }
        if (needsInvalidate) {
            invalidate();
        }
    }
}
