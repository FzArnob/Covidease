package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ScreenDensityUtil {
    public static final int DEFAULT_NORMAL_SHORT_DIMENSION = 320;
    public static final float MAXIMUM_ASPECT_RATIO = 1.7791667f;

    private ScreenDensityUtil() {
    }

    public static float computeCompatibleScaling(Context context) {
        Point point;
        DisplayMetrics displayMetrics;
        int i;
        int i2;
        int i3;
        Context context2 = context;
        DisplayMetrics displayMetrics2 = context2.getResources().getDisplayMetrics();
        new Point();
        Point point2 = point;
        Point point3 = point2;
        new DisplayMetrics();
        DisplayMetrics displayMetrics3 = displayMetrics;
        Display defaultDisplay = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
        int i4 = Build.VERSION.SDK_INT;
        int i5 = i4;
        if (i4 >= 24) {
            defaultDisplay.getMetrics(displayMetrics3);
            point3.x = displayMetrics3.widthPixels;
            point3.y = displayMetrics3.heightPixels;
        } else if (i5 >= 17) {
            JellybeanUtil.getRealSize(defaultDisplay, point3);
        } else if (i5 > 10) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                try {
                    point3.x = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                    point3.y = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                } catch (IllegalArgumentException e) {
                    int e2 = Log.e("ScreenDensityUtil", "Error reading raw screen size", e);
                } catch (IllegalAccessException e3) {
                    int e4 = Log.e("ScreenDensityUtil", "Error reading raw screen size", e3);
                } catch (InvocationTargetException e5) {
                    int e6 = Log.e("ScreenDensityUtil", "Error reading raw screen size", e5);
                }
            } catch (NoSuchMethodException e7) {
                int e8 = Log.e("ScreenDensityUtil", "Error reading raw screen size", e7);
            }
        } else {
            point3.x = defaultDisplay.getWidth();
            point3.y = defaultDisplay.getHeight();
        }
        int i6 = point2.x;
        int i7 = point2.y;
        if (i6 < i7) {
            i = i6;
            i2 = i7;
        } else {
            i = i7;
            i2 = i6;
        }
        int i8 = (int) ((320.0f * displayMetrics2.density) + 0.5f);
        float f = ((float) i2) / ((float) i);
        float f2 = f;
        if (f > 1.7791667f) {
            f2 = 1.7791667f;
        }
        int i9 = (int) ((((float) i8) * f2) + 0.5f);
        if (i6 < i7) {
            i3 = i8;
            i8 = i9;
        } else {
            i3 = i9;
        }
        return Math.max(1.0f, Math.min(Math.min(((float) i6) / ((float) i3), ((float) i7) / ((float) i8)), 1.7791667f));
    }
}
