package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.annotation.RestrictTo;
import android.util.StateSet;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class StateListAnimator {
    private final Animator.AnimatorListener animationListener;
    private Tuple lastMatch = null;
    ValueAnimator runningAnimator = null;
    private final ArrayList<Tuple> tuples;

    public StateListAnimator() {
        ArrayList<Tuple> arrayList;
        Animator.AnimatorListener animatorListener;
        new ArrayList<>();
        this.tuples = arrayList;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ StateListAnimator this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(Animator animator) {
                if (this.this$0.runningAnimator == animator) {
                    this.this$0.runningAnimator = null;
                }
            }
        };
        this.animationListener = animatorListener;
    }

    public void addState(int[] specs, ValueAnimator valueAnimator) {
        Object obj;
        ValueAnimator animator = valueAnimator;
        new Tuple(specs, animator);
        animator.addListener(this.animationListener);
        boolean add = this.tuples.add(obj);
    }

    public void setState(int[] iArr) {
        int[] state = iArr;
        Tuple match = null;
        int count = this.tuples.size();
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            }
            Tuple tuple = this.tuples.get(i);
            if (StateSet.stateSetMatches(tuple.specs, state)) {
                match = tuple;
                break;
            }
            i++;
        }
        if (match != this.lastMatch) {
            if (this.lastMatch != null) {
                cancel();
            }
            this.lastMatch = match;
            if (match != null) {
                start(match);
            }
        }
    }

    private void start(Tuple match) {
        this.runningAnimator = match.animator;
        this.runningAnimator.start();
    }

    private void cancel() {
        if (this.runningAnimator != null) {
            this.runningAnimator.cancel();
            this.runningAnimator = null;
        }
    }

    public void jumpToCurrentState() {
        if (this.runningAnimator != null) {
            this.runningAnimator.end();
            this.runningAnimator = null;
        }
    }

    static class Tuple {
        final ValueAnimator animator;
        final int[] specs;

        Tuple(int[] specs2, ValueAnimator animator2) {
            this.specs = specs2;
            this.animator = animator2;
        }
    }
}
