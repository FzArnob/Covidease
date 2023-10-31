package com.github.ybq.android.spinkit.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.sprite.Sprite;

public class AnimationUtils {
    public AnimationUtils() {
    }

    public static void start(Animator animator) {
        Animator animator2 = animator;
        if (animator2 != null && !animator2.isStarted()) {
            animator2.start();
        }
    }

    public static void stop(Animator animator) {
        Animator animator2 = animator;
        if (animator2 != null && !animator2.isRunning()) {
            animator2.end();
        }
    }

    public static void start(Sprite... sprites) {
        Sprite[] spriteArr = sprites;
        int length = spriteArr.length;
        for (int i = 0; i < length; i++) {
            spriteArr[i].start();
        }
    }

    public static void stop(Sprite... sprites) {
        Sprite[] spriteArr = sprites;
        int length = spriteArr.length;
        for (int i = 0; i < length; i++) {
            spriteArr[i].stop();
        }
    }

    public static boolean isRunning(Sprite... sprites) {
        Sprite[] spriteArr = sprites;
        int length = spriteArr.length;
        for (int i = 0; i < length; i++) {
            if (spriteArr[i].isRunning()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRunning(ValueAnimator valueAnimator) {
        ValueAnimator animator = valueAnimator;
        return animator != null && animator.isRunning();
    }

    public static boolean isStarted(ValueAnimator valueAnimator) {
        ValueAnimator animator = valueAnimator;
        return animator != null && animator.isStarted();
    }
}
