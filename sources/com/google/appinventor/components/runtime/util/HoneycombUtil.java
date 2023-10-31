package com.google.appinventor.components.runtime.util;

import android.view.View;
import android.view.ViewGroup;

public class HoneycombUtil {
    public static final int VIEWGROUP_MEASURED_HEIGHT_STATE_SHIFT = 16;

    private HoneycombUtil() {
    }

    public static int combineMeasuredStates(ViewGroup viewGroup, int i, int i2) {
        ViewGroup viewGroup2 = viewGroup;
        return ViewGroup.combineMeasuredStates(i, i2);
    }

    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    public static int resolveSizeAndState(ViewGroup viewGroup, int i, int i2, int i3) {
        ViewGroup viewGroup2 = viewGroup;
        return ViewGroup.resolveSizeAndState(i, i2, i3);
    }

    public static void viewSetRotate(View view, double d) {
        view.setRotation((float) d);
    }
}
