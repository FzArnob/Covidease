package android.support.transition;

import android.animation.TypeEvaluator;

class FloatArrayEvaluator implements TypeEvaluator<float[]> {
    private float[] mArray;

    FloatArrayEvaluator(float[] reuseArray) {
        this.mArray = reuseArray;
    }

    public float[] evaluate(float f, float[] fArr, float[] fArr2) {
        float fraction = f;
        float[] startValue = fArr;
        float[] endValue = fArr2;
        float[] array = this.mArray;
        if (array == null) {
            array = new float[startValue.length];
        }
        for (int i = 0; i < array.length; i++) {
            float start = startValue[i];
            array[i] = start + (fraction * (endValue[i] - start));
        }
        return array;
    }
}
