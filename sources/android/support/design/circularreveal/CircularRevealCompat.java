package android.support.design.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Build;
import android.support.design.circularreveal.CircularRevealWidget;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;

public final class CircularRevealCompat {
    private CircularRevealCompat() {
    }

    public static Animator createCircularReveal(CircularRevealWidget circularRevealWidget, float f, float f2, float f3) {
        CircularRevealWidget.RevealInfo revealInfo;
        AnimatorSet animatorSet;
        Throwable th;
        CircularRevealWidget view = circularRevealWidget;
        float centerX = f;
        float centerY = f2;
        float endRadius = f3;
        new CircularRevealWidget.RevealInfo(centerX, centerY, endRadius);
        Animator revealInfoAnimator = ObjectAnimator.ofObject(view, CircularRevealWidget.CircularRevealProperty.CIRCULAR_REVEAL, CircularRevealWidget.CircularRevealEvaluator.CIRCULAR_REVEAL, new CircularRevealWidget.RevealInfo[]{revealInfo});
        if (Build.VERSION.SDK_INT < 21) {
            return revealInfoAnimator;
        }
        CircularRevealWidget.RevealInfo revealInfo2 = view.getRevealInfo();
        if (revealInfo2 == null) {
            Throwable th2 = th;
            new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
            throw th2;
        }
        Animator circularRevealAnimator = ViewAnimationUtils.createCircularReveal((View) view, (int) centerX, (int) centerY, revealInfo2.radius, endRadius);
        new AnimatorSet();
        AnimatorSet set = animatorSet;
        Animator[] animatorArr = new Animator[2];
        animatorArr[0] = revealInfoAnimator;
        Animator[] animatorArr2 = animatorArr;
        animatorArr2[1] = circularRevealAnimator;
        set.playTogether(animatorArr2);
        return set;
    }

    public static Animator createCircularReveal(CircularRevealWidget circularRevealWidget, float f, float f2, float f3, float f4) {
        CircularRevealWidget.RevealInfo revealInfo;
        CircularRevealWidget.RevealInfo revealInfo2;
        AnimatorSet animatorSet;
        CircularRevealWidget view = circularRevealWidget;
        float centerX = f;
        float centerY = f2;
        float startRadius = f3;
        float endRadius = f4;
        Property<CircularRevealWidget, CircularRevealWidget.RevealInfo> property = CircularRevealWidget.CircularRevealProperty.CIRCULAR_REVEAL;
        TypeEvaluator<CircularRevealWidget.RevealInfo> typeEvaluator = CircularRevealWidget.CircularRevealEvaluator.CIRCULAR_REVEAL;
        CircularRevealWidget.RevealInfo[] revealInfoArr = new CircularRevealWidget.RevealInfo[2];
        new CircularRevealWidget.RevealInfo(centerX, centerY, startRadius);
        revealInfoArr[0] = revealInfo;
        CircularRevealWidget.RevealInfo[] revealInfoArr2 = revealInfoArr;
        new CircularRevealWidget.RevealInfo(centerX, centerY, endRadius);
        revealInfoArr2[1] = revealInfo2;
        Animator revealInfoAnimator = ObjectAnimator.ofObject(view, property, typeEvaluator, revealInfoArr2);
        if (Build.VERSION.SDK_INT < 21) {
            return revealInfoAnimator;
        }
        Animator circularRevealAnimator = ViewAnimationUtils.createCircularReveal((View) view, (int) centerX, (int) centerY, startRadius, endRadius);
        new AnimatorSet();
        AnimatorSet set = animatorSet;
        Animator[] animatorArr = new Animator[2];
        animatorArr[0] = revealInfoAnimator;
        Animator[] animatorArr2 = animatorArr;
        animatorArr2[1] = circularRevealAnimator;
        set.playTogether(animatorArr2);
        return set;
    }

    public static Animator.AnimatorListener createCircularRevealListener(CircularRevealWidget view) {
        Animator.AnimatorListener animatorListener;
        final CircularRevealWidget circularRevealWidget = view;
        new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                circularRevealWidget.buildCircularRevealCache();
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                circularRevealWidget.destroyCircularRevealCache();
            }
        };
        return animatorListener;
    }
}
