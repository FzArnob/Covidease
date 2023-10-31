package android.support.p000v4.view.animation;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* renamed from: android.support.v4.view.animation.PathInterpolatorCompat */
public final class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        Interpolator interpolator;
        Interpolator interpolator2;
        Path path2 = path;
        if (Build.VERSION.SDK_INT >= 21) {
            new PathInterpolator(path2);
            return interpolator2;
        }
        new PathInterpolatorApi14(path2);
        return interpolator;
    }

    public static Interpolator create(float f, float f2) {
        Interpolator interpolator;
        Interpolator interpolator2;
        float controlX = f;
        float controlY = f2;
        if (Build.VERSION.SDK_INT >= 21) {
            new PathInterpolator(controlX, controlY);
            return interpolator2;
        }
        new PathInterpolatorApi14(controlX, controlY);
        return interpolator;
    }

    public static Interpolator create(float f, float f2, float f3, float f4) {
        Interpolator interpolator;
        Interpolator interpolator2;
        float controlX1 = f;
        float controlY1 = f2;
        float controlX2 = f3;
        float controlY2 = f4;
        if (Build.VERSION.SDK_INT >= 21) {
            new PathInterpolator(controlX1, controlY1, controlX2, controlY2);
            return interpolator2;
        }
        new PathInterpolatorApi14(controlX1, controlY1, controlX2, controlY2);
        return interpolator;
    }
}
