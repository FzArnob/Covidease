package android.support.p000v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;

/* renamed from: android.support.v4.view.GravityCompat */
public final class GravityCompat {
    public static final int END = 8388613;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    public static void apply(int i, int i2, int i3, Rect rect, Rect rect2, int i4) {
        int gravity = i;
        int w = i2;
        int h = i3;
        Rect container = rect;
        Rect outRect = rect2;
        int layoutDirection = i4;
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(gravity, w, h, container, outRect, layoutDirection);
        } else {
            Gravity.apply(gravity, w, h, container, outRect);
        }
    }

    public static void apply(int i, int i2, int i3, Rect rect, int i4, int i5, Rect rect2, int i6) {
        int gravity = i;
        int w = i2;
        int h = i3;
        Rect container = rect;
        int xAdj = i4;
        int yAdj = i5;
        Rect outRect = rect2;
        int layoutDirection = i6;
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(gravity, w, h, container, xAdj, yAdj, outRect, layoutDirection);
        } else {
            Gravity.apply(gravity, w, h, container, xAdj, yAdj, outRect);
        }
    }

    public static void applyDisplay(int i, Rect rect, Rect rect2, int i2) {
        int gravity = i;
        Rect display = rect;
        Rect inoutObj = rect2;
        int layoutDirection = i2;
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.applyDisplay(gravity, display, inoutObj, layoutDirection);
        } else {
            Gravity.applyDisplay(gravity, display, inoutObj);
        }
    }

    public static int getAbsoluteGravity(int i, int i2) {
        int gravity = i;
        int layoutDirection = i2;
        if (Build.VERSION.SDK_INT >= 17) {
            return Gravity.getAbsoluteGravity(gravity, layoutDirection);
        }
        return gravity & -8388609;
    }

    private GravityCompat() {
    }
}
