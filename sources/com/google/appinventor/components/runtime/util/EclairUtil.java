package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.widget.EditText;

public class EclairUtil {
    private EclairUtil() {
    }

    public static void overridePendingTransitions(Activity activity, int i, int i2) {
        activity.overridePendingTransition(i, i2);
    }

    public static String getInstallerPackageName(String str, Activity activity) {
        return activity.getPackageManager().getInstallerPackageName(str);
    }

    public static void disableSuggestions(EditText editText) {
        EditText editText2 = editText;
        editText2.setInputType(editText2.getInputType() | 524288);
    }
}
