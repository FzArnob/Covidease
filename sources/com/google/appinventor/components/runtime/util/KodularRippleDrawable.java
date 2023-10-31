package com.google.appinventor.components.runtime.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;

public class KodularRippleDrawable {
    private KodularRippleDrawable() {
    }

    public static void setRippleDrawable(View view, int i, int i2) {
        StateListDrawable stateListDrawable;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        View view2 = view;
        int i3 = i;
        int i4 = i2;
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                new RippleDrawable(ColorStateList.valueOf(i4), view2.getBackground(), (Drawable) null);
                view2.setBackground(drawable4);
                return;
            }
            new StateListDrawable();
            StateListDrawable stateListDrawable2 = stateListDrawable;
            StateListDrawable stateListDrawable3 = stateListDrawable2;
            new ColorDrawable(i4);
            stateListDrawable2.addState(new int[]{16842919}, drawable);
            new ColorDrawable(i4);
            stateListDrawable3.addState(new int[]{16842908}, drawable2);
            new ColorDrawable(i3);
            stateListDrawable3.addState(new int[0], drawable3);
            view2.setBackground(stateListDrawable3);
        } catch (Exception e) {
            int e2 = Log.e("KodularRippleDrawable", String.valueOf(e));
        }
    }
}
