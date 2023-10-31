package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

@KeepForSdk
public class InstantApps {
    private static Context zzhv;
    private static Boolean zzhw;

    public InstantApps() {
    }

    @KeepForSdk
    public static synchronized boolean isInstantApp(Context context) {
        boolean booleanValue;
        Context context2 = context;
        synchronized (InstantApps.class) {
            Context applicationContext = context2.getApplicationContext();
            if (zzhv == null || zzhw == null || zzhv != applicationContext) {
                zzhw = null;
                if (PlatformVersion.isAtLeastO()) {
                    zzhw = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        Class<?> loadClass = context2.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzhw = true;
                    } catch (ClassNotFoundException e) {
                        zzhw = false;
                    }
                }
                zzhv = applicationContext;
                booleanValue = zzhw.booleanValue();
            } else {
                booleanValue = zzhw.booleanValue();
            }
        }
        return booleanValue;
    }
}
