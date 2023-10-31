package com.github.ybq.android.spinkit.animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Log;
import android.util.Property;
import android.view.animation.Interpolator;
import com.github.ybq.android.spinkit.animation.interpolator.KeyFrameInterpolator;
import com.github.ybq.android.spinkit.sprite.Sprite;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SpriteAnimatorBuilder {
    private static final String TAG = "SpriteAnimatorBuilder";
    private long duration = 2000;
    private Map<String, FrameData> fds;
    private Interpolator interpolator;
    private int repeatCount = -1;
    private Sprite sprite;
    private int startFrame = 0;

    class FrameData<T> {
        float[] fractions;
        Property property;
        final /* synthetic */ SpriteAnimatorBuilder this$0;
        T[] values;

        public FrameData(SpriteAnimatorBuilder this$02, float[] fractions2, Property property2, T[] values2) {
            this.this$0 = this$02;
            this.fractions = fractions2;
            this.property = property2;
            this.values = values2;
        }
    }

    class IntFrameData extends FrameData<Integer> {
        final /* synthetic */ SpriteAnimatorBuilder this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public IntFrameData(com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder r11, float[] r12, android.util.Property r13, java.lang.Integer[] r14) {
            /*
                r10 = this;
                r0 = r10
                r1 = r11
                r2 = r12
                r3 = r13
                r4 = r14
                r5 = r0
                r6 = r1
                r5.this$0 = r6
                r5 = r0
                r6 = r1
                r7 = r2
                r8 = r3
                r9 = r4
                r5.<init>(r6, r7, r8, r9)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder.IntFrameData.<init>(com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder, float[], android.util.Property, java.lang.Integer[]):void");
        }
    }

    class FloatFrameData extends FrameData<Float> {
        final /* synthetic */ SpriteAnimatorBuilder this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public FloatFrameData(com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder r11, float[] r12, android.util.Property r13, java.lang.Float[] r14) {
            /*
                r10 = this;
                r0 = r10
                r1 = r11
                r2 = r12
                r3 = r13
                r4 = r14
                r5 = r0
                r6 = r1
                r5.this$0 = r6
                r5 = r0
                r6 = r1
                r7 = r2
                r8 = r3
                r9 = r4
                r5.<init>(r6, r7, r8, r9)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder.FloatFrameData.<init>(com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder, float[], android.util.Property, java.lang.Float[]):void");
        }
    }

    public SpriteAnimatorBuilder(Sprite sprite2) {
        Map<String, FrameData> map;
        new HashMap();
        this.fds = map;
        this.sprite = sprite2;
    }

    public SpriteAnimatorBuilder scale(float[] fractions, Float... scale) {
        holder(fractions, (Property) Sprite.SCALE, scale);
        return this;
    }

    public SpriteAnimatorBuilder alpha(float[] fractions, Integer... alpha) {
        holder(fractions, (Property) Sprite.ALPHA, alpha);
        return this;
    }

    public SpriteAnimatorBuilder scaleX(float[] fractions, Float... scaleX) {
        holder(fractions, (Property) Sprite.SCALE, scaleX);
        return this;
    }

    public SpriteAnimatorBuilder scaleY(float[] fractions, Float... scaleY) {
        holder(fractions, (Property) Sprite.SCALE_Y, scaleY);
        return this;
    }

    public SpriteAnimatorBuilder rotateX(float[] fractions, Integer... rotateX) {
        holder(fractions, (Property) Sprite.ROTATE_X, rotateX);
        return this;
    }

    public SpriteAnimatorBuilder rotateY(float[] fractions, Integer... rotateY) {
        holder(fractions, (Property) Sprite.ROTATE_Y, rotateY);
        return this;
    }

    public SpriteAnimatorBuilder translateX(float[] fractions, Integer... translateX) {
        holder(fractions, (Property) Sprite.TRANSLATE_X, translateX);
        return this;
    }

    public SpriteAnimatorBuilder translateY(float[] fractions, Integer... translateY) {
        holder(fractions, (Property) Sprite.TRANSLATE_Y, translateY);
        return this;
    }

    public SpriteAnimatorBuilder rotate(float[] fractions, Integer... rotate) {
        holder(fractions, (Property) Sprite.ROTATE, rotate);
        return this;
    }

    public SpriteAnimatorBuilder translateXPercentage(float[] fractions, Float... translateXPercentage) {
        holder(fractions, (Property) Sprite.TRANSLATE_X_PERCENTAGE, translateXPercentage);
        return this;
    }

    public SpriteAnimatorBuilder translateYPercentage(float[] fractions, Float... translateYPercentage) {
        holder(fractions, (Property) Sprite.TRANSLATE_Y_PERCENTAGE, translateYPercentage);
        return this;
    }

    private void holder(float[] fArr, Property property, Float[] fArr2) {
        Object obj;
        float[] fractions = fArr;
        Property property2 = property;
        Float[] values = fArr2;
        ensurePair(fractions.length, values.length);
        new FloatFrameData(this, fractions, property2, values);
        FrameData put = this.fds.put(property2.getName(), obj);
    }

    private void holder(float[] fArr, Property property, Integer[] numArr) {
        Object obj;
        float[] fractions = fArr;
        Property property2 = property;
        Integer[] values = numArr;
        ensurePair(fractions.length, values.length);
        new IntFrameData(this, fractions, property2, values);
        FrameData put = this.fds.put(property2.getName(), obj);
    }

    private void ensurePair(int i, int i2) {
        Throwable th;
        int fractionsLength = i;
        int valuesLength = i2;
        if (fractionsLength != valuesLength) {
            Throwable th2 = th;
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(fractionsLength);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(valuesLength);
            new IllegalStateException(String.format(locale, "The fractions.length must equal values.length, fraction.length[%d], values.length[%d]", objArr2));
            throw th2;
        }
    }

    public SpriteAnimatorBuilder interpolator(Interpolator interpolator2) {
        this.interpolator = interpolator2;
        return this;
    }

    public SpriteAnimatorBuilder easeInOut(float... fractions) {
        SpriteAnimatorBuilder interpolator2 = interpolator(KeyFrameInterpolator.easeInOut(fractions));
        return this;
    }

    public SpriteAnimatorBuilder duration(long duration2) {
        this.duration = duration2;
        return this;
    }

    public SpriteAnimatorBuilder repeatCount(int repeatCount2) {
        this.repeatCount = repeatCount2;
        return this;
    }

    public SpriteAnimatorBuilder startFrame(int i) {
        int startFrame2 = i;
        if (startFrame2 < 0) {
            int w = Log.w(TAG, "startFrame should always be non-negative");
            startFrame2 = 0;
        }
        this.startFrame = startFrame2;
        return this;
    }

    public ObjectAnimator build() {
        PropertyValuesHolder[] holders = new PropertyValuesHolder[this.fds.size()];
        int i = 0;
        for (Map.Entry<String, FrameData> value : this.fds.entrySet()) {
            FrameData data = (FrameData) value.getValue();
            Keyframe[] keyframes = new Keyframe[data.fractions.length];
            float[] fractions = data.fractions;
            float startF = fractions[this.startFrame];
            int j = this.startFrame;
            while (true) {
                if (j >= this.startFrame + data.values.length) {
                    break;
                }
                int key = j - this.startFrame;
                int vk = j % data.values.length;
                float fraction = fractions[vk] - startF;
                if (fraction < 0.0f) {
                    fraction = fractions[fractions.length - 1] + fraction;
                }
                if (data instanceof IntFrameData) {
                    keyframes[key] = Keyframe.ofInt(fraction, ((Integer) data.values[vk]).intValue());
                } else if (data instanceof FloatFrameData) {
                    keyframes[key] = Keyframe.ofFloat(fraction, ((Float) data.values[vk]).floatValue());
                } else {
                    keyframes[key] = Keyframe.ofObject(fraction, data.values[vk]);
                }
                j++;
            }
            holders[i] = PropertyValuesHolder.ofKeyframe(data.property, keyframes);
            i++;
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this.sprite, holders);
        ObjectAnimator duration2 = animator.setDuration(this.duration);
        animator.setRepeatCount(this.repeatCount);
        animator.setInterpolator(this.interpolator);
        return animator;
    }
}
