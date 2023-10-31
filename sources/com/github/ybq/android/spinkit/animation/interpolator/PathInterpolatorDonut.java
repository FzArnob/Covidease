package com.github.ybq.android.spinkit.animation.interpolator;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

class PathInterpolatorDonut implements Interpolator {
    private static final float PRECISION = 0.002f;

    /* renamed from: mX */
    private final float[] f318mX;

    /* renamed from: mY */
    private final float[] f319mY;

    public PathInterpolatorDonut(Path path) {
        PathMeasure pathMeasure;
        new PathMeasure(path, false);
        PathMeasure pathMeasure2 = pathMeasure;
        float pathLength = pathMeasure2.getLength();
        int numPoints = ((int) (pathLength / PRECISION)) + 1;
        this.f318mX = new float[numPoints];
        this.f319mY = new float[numPoints];
        float[] position = new float[2];
        for (int i = 0; i < numPoints; i++) {
            boolean posTan = pathMeasure2.getPosTan((((float) i) * pathLength) / ((float) (numPoints - 1)), position, (float[]) null);
            this.f318mX[i] = position[0];
            this.f319mY[i] = position[1];
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PathInterpolatorDonut(float controlX, float controlY) {
        this(createQuad(controlX, controlY));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PathInterpolatorDonut(float controlX1, float controlY1, float controlX2, float controlY2) {
        this(createCubic(controlX1, controlY1, controlX2, controlY2));
    }

    public float getInterpolation(float f) {
        float t = f;
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t >= 1.0f) {
            return 1.0f;
        }
        int startIndex = 0;
        int endIndex = this.f318mX.length - 1;
        while (endIndex - startIndex > 1) {
            int midIndex = (startIndex + endIndex) / 2;
            if (t < this.f318mX[midIndex]) {
                endIndex = midIndex;
            } else {
                startIndex = midIndex;
            }
        }
        float xRange = this.f318mX[endIndex] - this.f318mX[startIndex];
        if (xRange == 0.0f) {
            return this.f319mY[startIndex];
        }
        float fraction = (t - this.f318mX[startIndex]) / xRange;
        float startY = this.f319mY[startIndex];
        return startY + (fraction * (this.f319mY[endIndex] - startY));
    }

    private static Path createQuad(float controlX, float controlY) {
        Path path;
        new Path();
        Path path2 = path;
        path2.moveTo(0.0f, 0.0f);
        path2.quadTo(controlX, controlY, 1.0f, 1.0f);
        return path2;
    }

    private static Path createCubic(float controlX1, float controlY1, float controlX2, float controlY2) {
        Path path;
        new Path();
        Path path2 = path;
        path2.moveTo(0.0f, 0.0f);
        path2.cubicTo(controlX1, controlY1, controlX2, controlY2, 1.0f, 1.0f);
        return path2;
    }
}
