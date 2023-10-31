package com.firebase.client.core;

public class AndroidSupport {
    private static final boolean IS_ANDROID = checkAndroid();

    public AndroidSupport() {
    }

    private static boolean checkAndroid() {
        try {
            Class<?> cls = Class.forName("android.app.Activity");
            return true;
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            return false;
        }
    }

    public static boolean isAndroid() {
        return IS_ANDROID;
    }
}
