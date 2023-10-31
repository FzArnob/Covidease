package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
    @Nullable
    private AnimatorSet currentAnimation;

    /* access modifiers changed from: protected */
    @NonNull
    public abstract AnimatorSet onCreateExpandedStateChangeAnimation(View view, View view2, boolean z, boolean z2);

    static /* synthetic */ AnimatorSet access$002(ExpandableTransformationBehavior x0, AnimatorSet x1) {
        AnimatorSet animatorSet = x1;
        AnimatorSet animatorSet2 = animatorSet;
        x0.currentAnimation = animatorSet2;
        return animatorSet;
    }

    public ExpandableTransformationBehavior() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpandableTransformationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public boolean onExpandedStateChange(View view, View view2, boolean z, boolean z2) {
        Animator.AnimatorListener animatorListener;
        View dependency = view;
        View child = view2;
        boolean expanded = z;
        boolean animated = z2;
        boolean currentlyAnimating = this.currentAnimation != null;
        if (currentlyAnimating) {
            this.currentAnimation.cancel();
        }
        this.currentAnimation = onCreateExpandedStateChangeAnimation(dependency, child, expanded, currentlyAnimating);
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ ExpandableTransformationBehavior this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                AnimatorSet access$002 = ExpandableTransformationBehavior.access$002(this.this$0, (AnimatorSet) null);
            }
        };
        this.currentAnimation.addListener(animatorListener);
        this.currentAnimation.start();
        if (!animated) {
            this.currentAnimation.end();
        }
        return true;
    }
}
