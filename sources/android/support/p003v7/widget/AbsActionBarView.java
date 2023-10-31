package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p003v7.appcompat.C0425R;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v7.widget.AbsActionBarView */
abstract class AbsActionBarView extends ViewGroup {
    private static final int FADE_DURATION = 200;
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimatorCompat mVisibilityAnim;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    AbsActionBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    AbsActionBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    AbsActionBarView(android.content.Context r12, android.util.AttributeSet r13, int r14) {
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
            android.support.v7.widget.AbsActionBarView$VisibilityAnimListener r6 = new android.support.v7.widget.AbsActionBarView$VisibilityAnimListener
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r7.<init>(r8)
            r5.mVisAnimListener = r6
            android.util.TypedValue r5 = new android.util.TypedValue
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r4 = r5
            r5 = r1
            android.content.res.Resources$Theme r5 = r5.getTheme()
            int r6 = android.support.p003v7.appcompat.C0425R.attr.actionBarPopupTheme
            r7 = r4
            r8 = 1
            boolean r5 = r5.resolveAttribute(r6, r7, r8)
            if (r5 == 0) goto L_0x0044
            r5 = r4
            int r5 = r5.resourceId
            if (r5 == 0) goto L_0x0044
            r5 = r0
            android.view.ContextThemeWrapper r6 = new android.view.ContextThemeWrapper
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r1
            r9 = r4
            int r9 = r9.resourceId
            r7.<init>(r8, r9)
            r5.mPopupContext = r6
        L_0x0043:
            return
        L_0x0044:
            r5 = r0
            r6 = r1
            r5.mPopupContext = r6
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.AbsActionBarView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        Configuration newConfig = configuration;
        super.onConfigurationChanged(newConfig);
        TypedArray a = getContext().obtainStyledAttributes((AttributeSet) null, C0425R.styleable.ActionBar, C0425R.attr.actionBarStyle, 0);
        setContentHeight(a.getLayoutDimension(C0425R.styleable.ActionBar_height, 0));
        a.recycle();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.onConfigurationChanged(newConfig);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        if (action == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean handled = super.onTouchEvent(ev);
            if (action == 0 && !handled) {
                this.mEatingTouch = true;
            }
        }
        if (action == 1 || action == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        if (action == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean handled = super.onHoverEvent(ev);
            if (action == 9 && !handled) {
                this.mEatingHover = true;
            }
        }
        if (action == 10 || action == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    public void setContentHeight(int height) {
        this.mContentHeight = height;
        requestLayout();
    }

    public int getContentHeight() {
        return this.mContentHeight;
    }

    public int getAnimatedVisibility() {
        if (this.mVisibilityAnim != null) {
            return this.mVisAnimListener.mFinalVisibility;
        }
        return getVisibility();
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        int visibility = i;
        long duration = j;
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
        }
        if (visibility == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            ViewPropertyAnimatorCompat anim = ViewCompat.animate(this).alpha(1.0f);
            ViewPropertyAnimatorCompat duration2 = anim.setDuration(duration);
            ViewPropertyAnimatorCompat listener = anim.setListener(this.mVisAnimListener.withFinalVisibility(anim, visibility));
            return anim;
        }
        ViewPropertyAnimatorCompat anim2 = ViewCompat.animate(this).alpha(0.0f);
        ViewPropertyAnimatorCompat duration3 = anim2.setDuration(duration);
        ViewPropertyAnimatorCompat listener2 = anim2.setListener(this.mVisAnimListener.withFinalVisibility(anim2, visibility));
        return anim2;
    }

    public void animateToVisibility(int visibility) {
        setupAnimatorToVisibility(visibility, 200).start();
    }

    public void setVisibility(int i) {
        int visibility = i;
        if (visibility != getVisibility()) {
            if (this.mVisibilityAnim != null) {
                this.mVisibilityAnim.cancel();
            }
            super.setVisibility(visibility);
        }
    }

    public boolean showOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public void postShowOverflowMenu() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ AbsActionBarView this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                boolean showOverflowMenu = this.this$0.showOverflowMenu();
            }
        };
        boolean post = post(runnable);
    }

    public boolean hideOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowPending();
        }
        return false;
    }

    public boolean isOverflowReserved() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowReserved();
    }

    public boolean canShowOverflowMenu() {
        return isOverflowReserved() && getVisibility() == 0;
    }

    public void dismissPopupMenus() {
        if (this.mActionMenuPresenter != null) {
            boolean dismissPopupMenus = this.mActionMenuPresenter.dismissPopupMenus();
        }
    }

    /* access modifiers changed from: protected */
    public int measureChildView(View view, int i, int childSpecHeight, int spacing) {
        View child = view;
        int availableWidth = i;
        child.measure(View.MeasureSpec.makeMeasureSpec(availableWidth, Integer.MIN_VALUE), childSpecHeight);
        return Math.max(0, (availableWidth - child.getMeasuredWidth()) - spacing);
    }

    protected static int next(int i, int i2, boolean isRtl) {
        int x = i;
        int val = i2;
        return isRtl ? x - val : x + val;
    }

    /* access modifiers changed from: protected */
    public int positionChild(View view, int i, int y, int contentHeight, boolean z) {
        View child = view;
        int x = i;
        boolean reverse = z;
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        int childTop = y + ((contentHeight - childHeight) / 2);
        if (reverse) {
            child.layout(x - childWidth, childTop, x, childTop + childHeight);
        } else {
            child.layout(x, childTop, x + childWidth, childTop + childHeight);
        }
        return reverse ? -childWidth : childWidth;
    }

    /* renamed from: android.support.v7.widget.AbsActionBarView$VisibilityAnimListener */
    protected class VisibilityAnimListener implements ViewPropertyAnimatorListener {
        private boolean mCanceled = false;
        int mFinalVisibility;
        final /* synthetic */ AbsActionBarView this$0;

        protected VisibilityAnimListener(AbsActionBarView this$02) {
            this.this$0 = this$02;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat animation, int visibility) {
            this.this$0.mVisibilityAnim = animation;
            this.mFinalVisibility = visibility;
            return this;
        }

        public void onAnimationStart(View view) {
            View view2 = view;
            AbsActionBarView.super.setVisibility(0);
            this.mCanceled = false;
        }

        public void onAnimationEnd(View view) {
            View view2 = view;
            if (!this.mCanceled) {
                this.this$0.mVisibilityAnim = null;
                AbsActionBarView.super.setVisibility(this.mFinalVisibility);
            }
        }

        public void onAnimationCancel(View view) {
            View view2 = view;
            this.mCanceled = true;
        }
    }
}
