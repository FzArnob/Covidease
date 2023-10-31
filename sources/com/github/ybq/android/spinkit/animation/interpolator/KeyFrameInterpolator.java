package com.github.ybq.android.spinkit.animation.interpolator;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;

public class KeyFrameInterpolator implements Interpolator {
    private float[] fractions;
    private TimeInterpolator interpolator;

    public static KeyFrameInterpolator easeInOut(float... fractions2) {
        KeyFrameInterpolator keyFrameInterpolator;
        new KeyFrameInterpolator(Ease.inOut(), new float[0]);
        KeyFrameInterpolator interpolator2 = keyFrameInterpolator;
        interpolator2.setFractions(fractions2);
        return interpolator2;
    }

    public static KeyFrameInterpolator pathInterpolator(float controlX1, float controlY1, float controlX2, float controlY2, float... fractions2) {
        KeyFrameInterpolator keyFrameInterpolator;
        new KeyFrameInterpolator(PathInterpolatorCompat.create(controlX1, controlY1, controlX2, controlY2), new float[0]);
        KeyFrameInterpolator interpolator2 = keyFrameInterpolator;
        interpolator2.setFractions(fractions2);
        return interpolator2;
    }

    public KeyFrameInterpolator(TimeInterpolator interpolator2, float... fractions2) {
        this.interpolator = interpolator2;
        this.fractions = fractions2;
    }

    public void setFractions(float... fractions2) {
        float[] fArr = fractions2;
        this.fractions = fArr;
    }

    public float getInterpolation(float f) {
        float input = f;
        if (this.fractions.length > 1) {
            int i = 0;
            while (i < this.fractions.length - 1) {
                float start = this.fractions[i];
                float end = this.fractions[i + 1];
                float duration = end - start;
                if (input < start || input > end) {
                    i++;
                } else {
                    return start + (this.interpolator.getInterpolation((input - start) / duration) * duration);
                }
            }
        }
        return this.interpolator.getInterpolation(input);
    }
}
