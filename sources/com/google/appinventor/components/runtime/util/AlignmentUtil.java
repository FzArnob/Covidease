package com.google.appinventor.components.runtime.util;

import android.support.p000v4.view.GravityCompat;
import com.google.appinventor.components.runtime.LinearLayout;

public class AlignmentUtil {
    private LinearLayout viewLayout;

    public AlignmentUtil(LinearLayout linearLayout) {
        this.viewLayout = linearLayout;
    }

    public void setHorizontalAlignment(int i) throws IllegalArgumentException {
        Throwable th;
        int i2 = i;
        switch (i2) {
            case 1:
                this.viewLayout.setHorizontalGravity(GravityCompat.START);
                return;
            case 2:
                this.viewLayout.setHorizontalGravity(GravityCompat.END);
                return;
            case 3:
                this.viewLayout.setHorizontalGravity(1);
                return;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("Bad value to setHorizontalAlignment: ".concat(String.valueOf(i2)));
                throw th2;
        }
    }

    public void setVerticalAlignment(int i) throws IllegalArgumentException {
        Throwable th;
        int i2 = i;
        switch (i2) {
            case 1:
                this.viewLayout.setVerticalGravity(48);
                return;
            case 2:
                this.viewLayout.setVerticalGravity(16);
                return;
            case 3:
                this.viewLayout.setVerticalGravity(80);
                return;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("Bad value to setVerticalAlignment: ".concat(String.valueOf(i2)));
                throw th2;
        }
    }
}
