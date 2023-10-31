package com.google.appinventor.components.runtime.util;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;

public class PaintUtil {
    private PaintUtil() {
    }

    public static void changePaint(Paint paint, int i) {
        Paint paint2 = paint;
        int i2 = i;
        paint2.setColor(i2 & 16777215);
        paint2.setAlpha(i2 >>> 24);
        Xfermode xfermode = paint2.setXfermode((Xfermode) null);
    }

    public static void changePaintTransparent(Paint paint) {
        Xfermode xfermode;
        Paint paint2 = paint;
        paint2.setAlpha(0);
        new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Xfermode xfermode2 = paint2.setXfermode(xfermode);
    }

    public static int hexStringToInt(String str) {
        String str2 = str;
        String str3 = str2;
        if (str2.startsWith("#x") || str2.startsWith("&H")) {
            str3 = str2.substring(2);
        }
        long parseLong = Long.parseLong(str3, 16);
        long j = parseLong;
        return (int) parseLong;
    }
}
