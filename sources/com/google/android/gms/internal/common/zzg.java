package com.google.android.gms.internal.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

public final class zzg {
    private static volatile boolean zziy = (!zzam());

    public static boolean zzam() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @TargetApi(24)
    @RequiresApi(24)
    public static Context getDeviceProtectedStorageContext(Context context) {
        Context context2 = context;
        if (context2.isDeviceProtectedStorage()) {
            return context2;
        }
        return context2.createDeviceProtectedStorageContext();
    }
}
