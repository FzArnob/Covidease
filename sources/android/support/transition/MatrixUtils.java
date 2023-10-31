package android.support.transition;

import android.graphics.Matrix;
import android.graphics.RectF;

class MatrixUtils {
    static final Matrix IDENTITY_MATRIX;

    static {
        Matrix matrix;
        new Matrix() {
            /* access modifiers changed from: package-private */
            public void oops() {
                Throwable th;
                Throwable th2 = th;
                new IllegalStateException("Matrix can not be modified");
                throw th2;
            }

            public void set(Matrix matrix) {
                Matrix matrix2 = matrix;
                oops();
            }

            public void reset() {
                oops();
            }

            public void setTranslate(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
            }

            public void setScale(float f, float f2, float f3, float f4) {
                float f5 = f;
                float f6 = f2;
                float f7 = f3;
                float f8 = f4;
                oops();
            }

            public void setScale(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
            }

            public void setRotate(float f, float f2, float f3) {
                float f4 = f;
                float f5 = f2;
                float f6 = f3;
                oops();
            }

            public void setRotate(float f) {
                float f2 = f;
                oops();
            }

            public void setSinCos(float f, float f2, float f3, float f4) {
                float f5 = f;
                float f6 = f2;
                float f7 = f3;
                float f8 = f4;
                oops();
            }

            public void setSinCos(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
            }

            public void setSkew(float f, float f2, float f3, float f4) {
                float f5 = f;
                float f6 = f2;
                float f7 = f3;
                float f8 = f4;
                oops();
            }

            public void setSkew(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
            }

            public boolean setConcat(Matrix matrix, Matrix matrix2) {
                Matrix matrix3 = matrix;
                Matrix matrix4 = matrix2;
                oops();
                return false;
            }

            public boolean preTranslate(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
                return false;
            }

            public boolean preScale(float f, float f2, float f3, float f4) {
                float f5 = f;
                float f6 = f2;
                float f7 = f3;
                float f8 = f4;
                oops();
                return false;
            }

            public boolean preScale(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
                return false;
            }

            public boolean preRotate(float f, float f2, float f3) {
                float f4 = f;
                float f5 = f2;
                float f6 = f3;
                oops();
                return false;
            }

            public boolean preRotate(float f) {
                float f2 = f;
                oops();
                return false;
            }

            public boolean preSkew(float f, float f2, float f3, float f4) {
                float f5 = f;
                float f6 = f2;
                float f7 = f3;
                float f8 = f4;
                oops();
                return false;
            }

            public boolean preSkew(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
                return false;
            }

            public boolean preConcat(Matrix matrix) {
                Matrix matrix2 = matrix;
                oops();
                return false;
            }

            public boolean postTranslate(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
                return false;
            }

            public boolean postScale(float f, float f2, float f3, float f4) {
                float f5 = f;
                float f6 = f2;
                float f7 = f3;
                float f8 = f4;
                oops();
                return false;
            }

            public boolean postScale(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
                return false;
            }

            public boolean postRotate(float f, float f2, float f3) {
                float f4 = f;
                float f5 = f2;
                float f6 = f3;
                oops();
                return false;
            }

            public boolean postRotate(float f) {
                float f2 = f;
                oops();
                return false;
            }

            public boolean postSkew(float f, float f2, float f3, float f4) {
                float f5 = f;
                float f6 = f2;
                float f7 = f3;
                float f8 = f4;
                oops();
                return false;
            }

            public boolean postSkew(float f, float f2) {
                float f3 = f;
                float f4 = f2;
                oops();
                return false;
            }

            public boolean postConcat(Matrix matrix) {
                Matrix matrix2 = matrix;
                oops();
                return false;
            }

            public boolean setRectToRect(RectF rectF, RectF rectF2, Matrix.ScaleToFit scaleToFit) {
                RectF rectF3 = rectF;
                RectF rectF4 = rectF2;
                Matrix.ScaleToFit scaleToFit2 = scaleToFit;
                oops();
                return false;
            }

            public boolean setPolyToPoly(float[] fArr, int i, float[] fArr2, int i2, int i3) {
                float[] fArr3 = fArr;
                int i4 = i;
                float[] fArr4 = fArr2;
                int i5 = i2;
                int i6 = i3;
                oops();
                return false;
            }

            public void setValues(float[] fArr) {
                float[] fArr2 = fArr;
                oops();
            }
        };
        IDENTITY_MATRIX = matrix;
    }

    private MatrixUtils() {
    }
}
