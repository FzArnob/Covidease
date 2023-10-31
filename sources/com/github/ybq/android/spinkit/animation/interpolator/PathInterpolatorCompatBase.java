package com.github.ybq.android.spinkit.animation.interpolator;

import android.graphics.Path;
import android.view.animation.Interpolator;

class PathInterpolatorCompatBase {
    private PathInterpolatorCompatBase() {
    }

    public static Interpolator create(Path path) {
        Interpolator interpolator;
        new PathInterpolatorDonut(path);
        return interpolator;
    }

    public static Interpolator create(float controlX, float controlY) {
        Interpolator interpolator;
        new PathInterpolatorDonut(controlX, controlY);
        return interpolator;
    }

    public static Interpolator create(float controlX1, float controlY1, float controlX2, float controlY2) {
        Interpolator interpolator;
        new PathInterpolatorDonut(controlX1, controlY1, controlX2, controlY2);
        return interpolator;
    }
}
