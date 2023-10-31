package android.support.p000v4.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.AppOpsManagerCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.content.PermissionChecker */
public final class PermissionChecker {
    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_DENIED_APP_OP = -2;
    public static final int PERMISSION_GRANTED = 0;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.content.PermissionChecker$PermissionResult */
    public @interface PermissionResult {
    }

    private PermissionChecker() {
    }

    public static int checkPermission(@NonNull Context context, @NonNull String str, int pid, int i, @Nullable String str2) {
        Context context2 = context;
        String permission = str;
        int uid = i;
        String packageName = str2;
        if (context2.checkPermission(permission, pid, uid) == -1) {
            return -1;
        }
        String op = AppOpsManagerCompat.permissionToOp(permission);
        if (op == null) {
            return 0;
        }
        if (packageName == null) {
            String[] packageNames = context2.getPackageManager().getPackagesForUid(uid);
            if (packageNames == null || packageNames.length <= 0) {
                return -1;
            }
            packageName = packageNames[0];
        }
        if (AppOpsManagerCompat.noteProxyOpNoThrow(context2, op, packageName) != 0) {
            return -2;
        }
        return 0;
    }

    public static int checkSelfPermission(@NonNull Context context, @NonNull String permission) {
        Context context2 = context;
        return checkPermission(context2, permission, Process.myPid(), Process.myUid(), context2.getPackageName());
    }

    public static int checkCallingPermission(@NonNull Context context, @NonNull String str, @Nullable String str2) {
        Context context2 = context;
        String permission = str;
        String packageName = str2;
        if (Binder.getCallingPid() == Process.myPid()) {
            return -1;
        }
        return checkPermission(context2, permission, Binder.getCallingPid(), Binder.getCallingUid(), packageName);
    }

    public static int checkCallingOrSelfPermission(@NonNull Context context, @NonNull String permission) {
        Context context2 = context;
        return checkPermission(context2, permission, Binder.getCallingPid(), Binder.getCallingUid(), Binder.getCallingPid() == Process.myPid() ? context2.getPackageName() : null);
    }
}
