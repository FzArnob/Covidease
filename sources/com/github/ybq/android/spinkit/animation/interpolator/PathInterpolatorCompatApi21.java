package com.github.ybq.android.spinkit.animation.interpolator;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

class PathInterpolatorCompatApi21 {
    private PathInterpolatorCompatApi21() {
    }

    @TargetApi(21)
    public static Interpolator create(Path path) {
        Interpolator interpolator;
        new PathInterpolator(path);
        return interpolator;
    }

    @TargetApi(21)
    public static Interpolator create(float controlX, float controlY) {
        Interpolator interpolator;
        new PathInterpolator(controlX, controlY);
        return interpolator;
    }

    @TargetApi(21)
    public static Interpolator create(float controlX1, float controlY1, float controlX2, float controlY2) {
        Interpolator interpolator;
        new PathInterpolator(controlX1, controlY1, controlX2, controlY2);
        return interpolator;
    }
}
