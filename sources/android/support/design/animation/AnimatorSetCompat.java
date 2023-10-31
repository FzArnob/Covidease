package android.support.design.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.annotation.RestrictTo;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimatorSetCompat {
    public AnimatorSetCompat() {
    }

    public static void playTogether(AnimatorSet animatorSet, List<Animator> list) {
        AnimatorSet animatorSet2 = animatorSet;
        List<Animator> items = list;
        long totalDuration = 0;
        int count = items.size();
        for (int i = 0; i < count; i++) {
            Animator animator = items.get(i);
            totalDuration = Math.max(totalDuration, animator.getStartDelay() + animator.getDuration());
        }
        Animator fix = ValueAnimator.ofInt(new int[]{0, 0});
        Animator duration = fix.setDuration(totalDuration);
        items.add(0, fix);
        animatorSet2.playTogether(items);
    }
}
