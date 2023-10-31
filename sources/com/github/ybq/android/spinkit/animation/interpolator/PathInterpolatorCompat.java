package com.github.ybq.android.spinkit.animation.interpolator;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;

public class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        Path path2 = path;
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(path2);
        }
        return PathInterpolatorCompatBase.create(path2);
    }

    public static Interpolator create(float f, float f2) {
        float controlX = f;
        float controlY = f2;
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(controlX, controlY);
        }
        return PathInterpolatorCompatBase.create(controlX, controlY);
    }

    public static Interpolator create(float f, float f2, float f3, float f4) {
        float controlX1 = f;
        float controlY1 = f2;
        float controlX2 = f3;
        float controlY2 = f4;
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(controlX1, controlY1, controlX2, controlY2);
        }
        return PathInterpolatorCompatBase.create(controlX1, controlY1, controlX2, controlY2);
    }
}
