package android.support.design.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MotionTiming {
    private long delay = 0;
    private long duration = 300;
    @Nullable
    private TimeInterpolator interpolator = null;
    private int repeatCount = 0;
    private int repeatMode = 1;

    public MotionTiming(long delay2, long duration2) {
        this.delay = delay2;
        this.duration = duration2;
    }

    public MotionTiming(long delay2, long duration2, @NonNull TimeInterpolator interpolator2) {
        this.delay = delay2;
        this.duration = duration2;
        this.interpolator = interpolator2;
    }

    public void apply(Animator animator) {
        Animator animator2 = animator;
        animator2.setStartDelay(getDelay());
        Animator duration2 = animator2.setDuration(getDuration());
        animator2.setInterpolator(getInterpolator());
        if (animator2 instanceof ValueAnimator) {
            ((ValueAnimator) animator2).setRepeatCount(getRepeatCount());
            ((ValueAnimator) animator2).setRepeatMode(getRepeatMode());
        }
    }

    public long getDelay() {
        return this.delay;
    }

    public long getDuration() {
        return this.duration;
    }

    public TimeInterpolator getInterpolator() {
        return this.interpolator != null ? this.interpolator : AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public int getRepeatCount() {
        return this.repeatCount;
    }

    public int getRepeatMode() {
        return this.repeatMode;
    }

    static MotionTiming createFromAnimator(ValueAnimator valueAnimator) {
        MotionTiming motionTiming;
        ValueAnimator animator = valueAnimator;
        new MotionTiming(animator.getStartDelay(), animator.getDuration(), getInterpolatorCompat(animator));
        MotionTiming timing = motionTiming;
        timing.repeatCount = animator.getRepeatCount();
        timing.repeatMode = animator.getRepeatMode();
        return timing;
    }

    private static TimeInterpolator getInterpolatorCompat(ValueAnimator animator) {
        TimeInterpolator interpolator2 = animator.getInterpolator();
        if ((interpolator2 instanceof AccelerateDecelerateInterpolator) || interpolator2 == null) {
            return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        }
        if (interpolator2 instanceof AccelerateInterpolator) {
            return AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        }
        if (interpolator2 instanceof DecelerateInterpolator) {
            return AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
        }
        return interpolator2;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MotionTiming that = (MotionTiming) o;
        if (getDelay() != that.getDelay()) {
            return false;
        }
        if (getDuration() != that.getDuration()) {
            return false;
        }
        if (getRepeatCount() != that.getRepeatCount()) {
            return false;
        }
        if (getRepeatMode() != that.getRepeatMode()) {
            return false;
        }
        return getInterpolator().getClass().equals(that.getInterpolator().getClass());
    }

    public int hashCode() {
        return (31 * ((31 * ((31 * ((31 * ((int) (getDelay() ^ (getDelay() >>> 32)))) + ((int) (getDuration() ^ (getDuration() >>> 32))))) + getInterpolator().getClass().hashCode())) + getRepeatCount())) + getRepeatMode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder out = sb;
        StringBuilder append = out.append(10);
        StringBuilder append2 = out.append(getClass().getName());
        StringBuilder append3 = out.append('{');
        StringBuilder append4 = out.append(Integer.toHexString(System.identityHashCode(this)));
        StringBuilder append5 = out.append(" delay: ");
        StringBuilder append6 = out.append(getDelay());
        StringBuilder append7 = out.append(" duration: ");
        StringBuilder append8 = out.append(getDuration());
        StringBuilder append9 = out.append(" interpolator: ");
        StringBuilder append10 = out.append(getInterpolator().getClass());
        StringBuilder append11 = out.append(" repeatCount: ");
        StringBuilder append12 = out.append(getRepeatCount());
        StringBuilder append13 = out.append(" repeatMode: ");
        StringBuilder append14 = out.append(getRepeatMode());
        StringBuilder append15 = out.append("}\n");
        return out.toString();
    }
}
