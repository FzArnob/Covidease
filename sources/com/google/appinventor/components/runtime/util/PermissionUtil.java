package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import gnu.expr.Declaration;

public class PermissionUtil {
    private static String LOG_TAG = "Permission Util";

    private PermissionUtil() {
    }

    public static String[] getNeededPermissions(Context context) {
        Context context2 = context;
        try {
            PackageManager packageManager = context2.getPackageManager();
            return packageManager.getPackageInfo(packageManager.getApplicationInfo(context2.getPackageName(), 0).packageName, 4096).requestedPermissions;
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
            return new String[0];
        }
    }

    public static boolean arePermissionsGranted(Context context) {
        Context context2 = context;
        String[] neededPermissions = getNeededPermissions(context2);
        String[] strArr = neededPermissions;
        if (neededPermissions == null || strArr.length == 0) {
            return true;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (ContextCompat.checkSelfPermission((Activity) context2, strArr[i]) == -1) {
                return false;
            }
        }
        return true;
    }

    public static void appSettings(Context context) {
        Intent intent;
        Context context2 = context;
        new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", context2.getPackageName(), (String) null));
        Intent intent2 = intent;
        Intent addFlags = intent2.addFlags(Declaration.IS_DYNAMIC);
        context2.startActivity(intent2);
    }

    public static void appSystemSettings(Context context) {
        Intent intent;
        StringBuilder sb;
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 23) {
            new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
            Intent intent2 = intent;
            Intent intent3 = intent2;
            new StringBuilder("package:");
            Intent data = intent2.setData(Uri.parse(sb.append(context2.getPackageName()).toString()));
            Intent addFlags = intent3.addFlags(Declaration.IS_DYNAMIC);
            context2.startActivity(intent3);
        }
    }

    public static boolean writeSystemSettings(Context context) {
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.System.canWrite(context2);
        }
        return true;
    }
}
