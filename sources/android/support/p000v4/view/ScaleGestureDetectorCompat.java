package android.support.p000v4.view;

import android.os.Build;
import android.view.ScaleGestureDetector;

/* renamed from: android.support.v4.view.ScaleGestureDetectorCompat */
public final class ScaleGestureDetectorCompat {
    private ScaleGestureDetectorCompat() {
    }

    @Deprecated
    public static void setQuickScaleEnabled(Object scaleGestureDetector, boolean enabled) {
        setQuickScaleEnabled((ScaleGestureDetector) scaleGestureDetector, enabled);
    }

    public static void setQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector, boolean z) {
        ScaleGestureDetector scaleGestureDetector2 = scaleGestureDetector;
        boolean enabled = z;
        if (Build.VERSION.SDK_INT >= 19) {
            scaleGestureDetector2.setQuickScaleEnabled(enabled);
        }
    }

    @Deprecated
    public static boolean isQuickScaleEnabled(Object scaleGestureDetector) {
        return isQuickScaleEnabled((ScaleGestureDetector) scaleGestureDetector);
    }

    public static boolean isQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector) {
        ScaleGestureDetector scaleGestureDetector2 = scaleGestureDetector;
        if (Build.VERSION.SDK_INT >= 19) {
            return scaleGestureDetector2.isQuickScaleEnabled();
        }
        return false;
    }
}
