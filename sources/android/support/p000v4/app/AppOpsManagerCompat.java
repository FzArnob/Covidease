package android.support.p000v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: android.support.v4.app.AppOpsManagerCompat */
public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    private AppOpsManagerCompat() {
    }

    @Nullable
    public static String permissionToOp(@NonNull String str) {
        String permission = str;
        if (Build.VERSION.SDK_INT >= 23) {
            return AppOpsManager.permissionToOp(permission);
        }
        return null;
    }

    public static int noteOp(@NonNull Context context, @NonNull String str, int i, @NonNull String str2) {
        Context context2 = context;
        String op = str;
        int uid = i;
        String packageName = str2;
        if (Build.VERSION.SDK_INT >= 19) {
            return ((AppOpsManager) context2.getSystemService("appops")).noteOp(op, uid, packageName);
        }
        return 1;
    }

    public static int noteOpNoThrow(@NonNull Context context, @NonNull String str, int i, @NonNull String str2) {
        Context context2 = context;
        String op = str;
        int uid = i;
        String packageName = str2;
        if (Build.VERSION.SDK_INT >= 19) {
            return ((AppOpsManager) context2.getSystemService("appops")).noteOpNoThrow(op, uid, packageName);
        }
        return 1;
    }

    public static int noteProxyOp(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        Context context2 = context;
        String op = str;
        String proxiedPackageName = str2;
        if (Build.VERSION.SDK_INT >= 23) {
            return ((AppOpsManager) context2.getSystemService(AppOpsManager.class)).noteProxyOp(op, proxiedPackageName);
        }
        return 1;
    }

    public static int noteProxyOpNoThrow(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        Context context2 = context;
        String op = str;
        String proxiedPackageName = str2;
        if (Build.VERSION.SDK_INT >= 23) {
            return ((AppOpsManager) context2.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(op, proxiedPackageName);
        }
        return 1;
    }
}
