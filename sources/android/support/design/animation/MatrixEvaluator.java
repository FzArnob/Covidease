package android.support.design.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public class MatrixEvaluator implements TypeEvaluator<Matrix> {
    private final float[] tempEndValues = new float[9];
    private final Matrix tempMatrix;
    private final float[] tempStartValues = new float[9];

    public MatrixEvaluator() {
        Matrix matrix;
        new Matrix();
        this.tempMatrix = matrix;
    }

    public Matrix evaluate(float f, Matrix startValue, Matrix endValue) {
        float fraction = f;
        startValue.getValues(this.tempStartValues);
        endValue.getValues(this.tempEndValues);
        for (int i = 0; i < 9; i++) {
            this.tempEndValues[i] = this.tempStartValues[i] + (fraction * (this.tempEndValues[i] - this.tempStartValues[i]));
        }
        this.tempMatrix.setValues(this.tempEndValues);
        return this.tempMatrix;
    }
}
