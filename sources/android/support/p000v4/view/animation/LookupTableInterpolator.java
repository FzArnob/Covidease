package android.support.p000v4.view.animation;

import android.view.animation.Interpolator;

/* renamed from: android.support.v4.view.animation.LookupTableInterpolator */
abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize = (1.0f / ((float) (this.mValues.length - 1)));
    private final float[] mValues;

    protected LookupTableInterpolator(float[] values) {
        this.mValues = values;
    }

    public float getInterpolation(float f) {
        float input = f;
        if (input >= 1.0f) {
            return 1.0f;
        }
        if (input <= 0.0f) {
            return 0.0f;
        }
        int position = Math.min((int) (input * ((float) (this.mValues.length - 1))), this.mValues.length - 2);
        return this.mValues[position] + (((input - (((float) position) * this.mStepSize)) / this.mStepSize) * (this.mValues[position + 1] - this.mValues[position]));
    }
}
