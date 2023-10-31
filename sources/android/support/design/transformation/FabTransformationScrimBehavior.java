package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.MotionTiming;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    public static final long COLLAPSE_DELAY = 0;
    public static final long COLLAPSE_DURATION = 150;
    public static final long EXPAND_DELAY = 75;
    public static final long EXPAND_DURATION = 150;
    private final MotionTiming collapseTiming;
    private final MotionTiming expandTiming;

    public FabTransformationScrimBehavior() {
        MotionTiming motionTiming;
        MotionTiming motionTiming2;
        new MotionTiming(75, 150);
        this.expandTiming = motionTiming;
        new MotionTiming(0, 150);
        this.collapseTiming = motionTiming2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FabTransformationScrimBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        MotionTiming motionTiming;
        MotionTiming motionTiming2;
        new MotionTiming(75, 150);
        this.expandTiming = motionTiming;
        new MotionTiming(0, 150);
        this.collapseTiming = motionTiming2;
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View dependency) {
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        View view2 = view;
        return dependency instanceof FloatingActionButton;
    }

    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return super.onTouchEvent(parent, child, ev);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AnimatorSet onCreateExpandedStateChangeAnimation(View view, View view2, boolean z, boolean isAnimating) {
        List<Animator> list;
        List list2;
        AnimatorSet animatorSet;
        Animator.AnimatorListener animatorListener;
        View view3 = view;
        View child = view2;
        boolean expanded = z;
        new ArrayList<>();
        List<Animator> animations = list;
        new ArrayList();
        createScrimAnimation(child, expanded, isAnimating, animations, list2);
        new AnimatorSet();
        AnimatorSet set = animatorSet;
        AnimatorSetCompat.playTogether(set, animations);
        final boolean z2 = expanded;
        final View view4 = child;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ FabTransformationScrimBehavior this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                if (z2) {
                    view4.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                if (!z2) {
                    view4.setVisibility(4);
                }
            }
        };
        set.addListener(animatorListener);
        return set;
    }

    private void createScrimAnimation(View view, boolean z, boolean z2, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animator;
        View child = view;
        boolean expanded = z;
        boolean currentlyAnimating = z2;
        List<Animator> animations = list;
        List<Animator.AnimatorListener> list3 = list2;
        MotionTiming timing = expanded ? this.expandTiming : this.collapseTiming;
        if (expanded) {
            if (!currentlyAnimating) {
                child.setAlpha(0.0f);
            }
            animator = ObjectAnimator.ofFloat(child, View.ALPHA, new float[]{1.0f});
        } else {
            animator = ObjectAnimator.ofFloat(child, View.ALPHA, new float[]{0.0f});
        }
        timing.apply(animator);
        boolean add = animations.add(animator);
    }
}
