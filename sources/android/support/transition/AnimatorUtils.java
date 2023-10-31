package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.NonNull;
import java.util.ArrayList;

class AnimatorUtils {

    interface AnimatorPauseListenerCompat {
        void onAnimationPause(Animator animator);

        void onAnimationResume(Animator animator);
    }

    static void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter animatorListenerAdapter) {
        Animator animator2 = animator;
        AnimatorListenerAdapter listener = animatorListenerAdapter;
        if (Build.VERSION.SDK_INT >= 19) {
            animator2.addPauseListener(listener);
        }
    }

    static void pause(@NonNull Animator animator) {
        Animator animator2 = animator;
        if (Build.VERSION.SDK_INT >= 19) {
            animator2.pause();
            return;
        }
        ArrayList<Animator.AnimatorListener> listeners = animator2.getListeners();
        if (listeners != null) {
            int size = listeners.size();
            for (int i = 0; i < size; i++) {
                Animator.AnimatorListener listener = listeners.get(i);
                if (listener instanceof AnimatorPauseListenerCompat) {
                    ((AnimatorPauseListenerCompat) listener).onAnimationPause(animator2);
                }
            }
        }
    }

    static void resume(@NonNull Animator animator) {
        Animator animator2 = animator;
        if (Build.VERSION.SDK_INT >= 19) {
            animator2.resume();
            return;
        }
        ArrayList<Animator.AnimatorListener> listeners = animator2.getListeners();
        if (listeners != null) {
            int size = listeners.size();
            for (int i = 0; i < size; i++) {
                Animator.AnimatorListener listener = listeners.get(i);
                if (listener instanceof AnimatorPauseListenerCompat) {
                    ((AnimatorPauseListenerCompat) listener).onAnimationResume(animator2);
                }
            }
        }
    }

    private AnimatorUtils() {
    }
}
