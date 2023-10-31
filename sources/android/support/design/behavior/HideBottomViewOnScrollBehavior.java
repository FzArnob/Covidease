package android.support.design.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.design.animation.AnimationUtils;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    protected static final int ENTER_ANIMATION_DURATION = 225;
    protected static final int EXIT_ANIMATION_DURATION = 175;
    private static final int STATE_SCROLLED_DOWN = 1;
    private static final int STATE_SCROLLED_UP = 2;
    private ViewPropertyAnimator currentAnimator;
    private int currentState = 2;
    private int height = 0;

    static /* synthetic */ ViewPropertyAnimator access$002(HideBottomViewOnScrollBehavior x0, ViewPropertyAnimator x1) {
        ViewPropertyAnimator viewPropertyAnimator = x1;
        ViewPropertyAnimator viewPropertyAnimator2 = viewPropertyAnimator;
        x0.currentAnimator = viewPropertyAnimator2;
        return viewPropertyAnimator;
    }

    public HideBottomViewOnScrollBehavior() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onLayoutChild(CoordinatorLayout parent, V v, int layoutDirection) {
        V child = v;
        this.height = child.getMeasuredHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int nestedScrollAxes) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V v2 = v;
        View view3 = view;
        View view4 = view2;
        return nestedScrollAxes == 2;
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        V child = v;
        View view2 = view;
        int i5 = i;
        int dyConsumed = i2;
        int i6 = i3;
        int i7 = i4;
        if (this.currentState != 1 && dyConsumed > 0) {
            slideDown(child);
        } else if (this.currentState != 2 && dyConsumed < 0) {
            slideUp(child);
        }
    }

    /* access modifiers changed from: protected */
    public void slideUp(V v) {
        V child = v;
        if (this.currentAnimator != null) {
            this.currentAnimator.cancel();
            child.clearAnimation();
        }
        this.currentState = 2;
        animateChildTo(child, 0, 225, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    }

    /* access modifiers changed from: protected */
    public void slideDown(V v) {
        V child = v;
        if (this.currentAnimator != null) {
            this.currentAnimator.cancel();
            child.clearAnimation();
        }
        this.currentState = 1;
        animateChildTo(child, this.height, 175, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
    }

    private void animateChildTo(V child, int targetY, long duration, TimeInterpolator interpolator) {
        Animator.AnimatorListener animatorListener;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ HideBottomViewOnScrollBehavior this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                ViewPropertyAnimator access$002 = HideBottomViewOnScrollBehavior.access$002(this.this$0, (ViewPropertyAnimator) null);
            }
        };
        this.currentAnimator = child.animate().translationY((float) targetY).setInterpolator(interpolator).setDuration(duration).setListener(animatorListener);
    }
}
