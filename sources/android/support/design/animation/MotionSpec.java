package android.support.design.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimatorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleableRes;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MotionSpec {
    private static final String TAG = "MotionSpec";
    private final C1650SimpleArrayMap<String, MotionTiming> timings;

    public MotionSpec() {
        C1650SimpleArrayMap<String, MotionTiming> simpleArrayMap;
        new C1650SimpleArrayMap<>();
        this.timings = simpleArrayMap;
    }

    public boolean hasTiming(String name) {
        return this.timings.get(name) != null;
    }

    public MotionTiming getTiming(String str) {
        Throwable th;
        String name = str;
        if (hasTiming(name)) {
            return this.timings.get(name);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public void setTiming(String name, @Nullable MotionTiming timing) {
        MotionTiming put = this.timings.put(name, timing);
    }

    public long getTotalDuration() {
        long duration = 0;
        int count = this.timings.size();
        for (int i = 0; i < count; i++) {
            MotionTiming timing = this.timings.valueAt(i);
            duration = Math.max(duration, timing.getDelay() + timing.getDuration());
        }
        return duration;
    }

    @Nullable
    public static MotionSpec createFromAttribute(Context context, TypedArray typedArray, @StyleableRes int i) {
        int resourceId;
        Context context2 = context;
        TypedArray attributes = typedArray;
        int index = i;
        if (!attributes.hasValue(index) || (resourceId = attributes.getResourceId(index, 0)) == 0) {
            return null;
        }
        return createFromResource(context2, resourceId);
    }

    @Nullable
    public static MotionSpec createFromResource(Context context, @AnimatorRes int i) {
        StringBuilder sb;
        List list;
        int id = i;
        try {
            Animator animator = AnimatorInflater.loadAnimator(context, id);
            if (animator instanceof AnimatorSet) {
                return createSpecFromAnimators(((AnimatorSet) animator).getChildAnimations());
            }
            if (animator == null) {
                return null;
            }
            new ArrayList();
            List list2 = list;
            boolean add = list2.add(animator);
            return createSpecFromAnimators(list2);
        } catch (Exception e) {
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Can't load animation resource ID #0x").append(Integer.toHexString(id)).toString(), e);
            return null;
        }
    }

    private static MotionSpec createSpecFromAnimators(List<Animator> list) {
        MotionSpec motionSpec;
        List<Animator> animators = list;
        new MotionSpec();
        MotionSpec spec = motionSpec;
        int count = animators.size();
        for (int i = 0; i < count; i++) {
            addTimingFromAnimator(spec, animators.get(i));
        }
        return spec;
    }

    private static void addTimingFromAnimator(MotionSpec motionSpec, Animator animator) {
        Throwable th;
        StringBuilder sb;
        MotionSpec spec = motionSpec;
        Animator animator2 = animator;
        if (animator2 instanceof ObjectAnimator) {
            ObjectAnimator anim = (ObjectAnimator) animator2;
            spec.setTiming(anim.getPropertyName(), MotionTiming.createFromAnimator(anim));
            return;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Animator must be an ObjectAnimator: ").append(animator2).toString());
        throw th2;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.timings.equals(((MotionSpec) o).timings);
    }

    public int hashCode() {
        return this.timings.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder out = sb;
        StringBuilder append = out.append(10);
        StringBuilder append2 = out.append(getClass().getName());
        StringBuilder append3 = out.append('{');
        StringBuilder append4 = out.append(Integer.toHexString(System.identityHashCode(this)));
        StringBuilder append5 = out.append(" timings: ");
        StringBuilder append6 = out.append(this.timings);
        StringBuilder append7 = out.append("}\n");
        return out.toString();
    }
}
