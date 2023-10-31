package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import gnu.expr.Declaration;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class CrashUtils {
    private static final String[] zzgg;
    private static DropBoxManager zzgh = null;
    private static boolean zzgi = false;
    private static int zzgj = -1;
    @GuardedBy("CrashUtils.class")
    private static int zzgk = 0;
    @GuardedBy("CrashUtils.class")
    private static int zzgl = 0;

    public CrashUtils() {
    }

    @KeepForSdk
    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        return zza(context, th, Declaration.EARLY_INIT);
    }

    private static boolean zza(Context context, Throwable th, int i) {
        Throwable th2 = th;
        int i2 = i;
        try {
            Object checkNotNull = Preconditions.checkNotNull(context);
            Object checkNotNull2 = Preconditions.checkNotNull(th2);
            return false;
        } catch (Exception e) {
            int e2 = Log.e("CrashUtils", "Error adding exception to DropBox!", e);
            return false;
        }
    }

    static {
        String[] strArr = new String[5];
        strArr[0] = "android.";
        String[] strArr2 = strArr;
        strArr2[1] = "com.android.";
        String[] strArr3 = strArr2;
        strArr3[2] = "dalvik.";
        String[] strArr4 = strArr3;
        strArr4[3] = "java.";
        String[] strArr5 = strArr4;
        strArr5[4] = "javax.";
        zzgg = strArr5;
    }
}
