package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import com.google.appinventor.components.common.PropertyTypeConstants;

public class KodularResourcesUtil {
    public KodularResourcesUtil() {
    }

    public static String getString(Context context, String str) {
        Context context2 = context;
        return context2.getResources().getString(getIdentifier(context2, str, PropertyTypeConstants.PROPERTY_TYPE_STRING));
    }

    public static int getColor(Context context, String str) {
        Context context2 = context;
        return ContextCompat.getColor(context2, getIdentifier(context2, str, PropertyTypeConstants.PROPERTY_TYPE_COLOR));
    }

    public static int getDimension(Context context, String str) {
        Context context2 = context;
        return (int) context2.getResources().getDimension(getIdentifier(context2, str, "dimen"));
    }

    public static int getStyle(Context context, String str) {
        return getIdentifier(context, str, "style");
    }

    public static Drawable getDrawable(Context context, String str) {
        Context context2 = context;
        return ContextCompat.getDrawable(context2, getIdentifier(context2, str, "drawable"));
    }

    public static int getIdentifier(Context context, String str, String str2) {
        Context context2 = context;
        return context2.getResources().getIdentifier(str, str2, context2.getPackageName());
    }
}
