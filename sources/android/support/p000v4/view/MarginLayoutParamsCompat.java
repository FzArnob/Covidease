package android.support.p000v4.view;

import android.os.Build;
import android.view.ViewGroup;

/* renamed from: android.support.v4.view.MarginLayoutParamsCompat */
public final class MarginLayoutParamsCompat {
    public static int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        if (Build.VERSION.SDK_INT >= 17) {
            return lp.getMarginStart();
        }
        return lp.leftMargin;
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        if (Build.VERSION.SDK_INT >= 17) {
            return lp.getMarginEnd();
        }
        return lp.rightMargin;
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        int marginStart = i;
        if (Build.VERSION.SDK_INT >= 17) {
            lp.setMarginStart(marginStart);
            return;
        }
        lp.leftMargin = marginStart;
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        int marginEnd = i;
        if (Build.VERSION.SDK_INT >= 17) {
            lp.setMarginEnd(marginEnd);
            return;
        }
        lp.rightMargin = marginEnd;
    }

    public static boolean isMarginRelative(ViewGroup.MarginLayoutParams marginLayoutParams) {
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        if (Build.VERSION.SDK_INT >= 17) {
            return lp.isMarginRelative();
        }
        return false;
    }

    public static int getLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams) {
        int result;
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        if (Build.VERSION.SDK_INT >= 17) {
            result = lp.getLayoutDirection();
        } else {
            result = 0;
        }
        if (!(result == 0 || result == 1)) {
            result = 0;
        }
        return result;
    }

    public static void setLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        int layoutDirection = i;
        if (Build.VERSION.SDK_INT >= 17) {
            lp.setLayoutDirection(layoutDirection);
        }
    }

    public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        ViewGroup.MarginLayoutParams lp = marginLayoutParams;
        int layoutDirection = i;
        if (Build.VERSION.SDK_INT >= 17) {
            lp.resolveLayoutDirection(layoutDirection);
        }
    }

    private MarginLayoutParamsCompat() {
    }
}
