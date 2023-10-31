package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class DeviceProperties {
    private static Boolean zzgn;
    private static Boolean zzgo;
    private static Boolean zzgp;
    private static Boolean zzgq;
    private static Boolean zzgr;
    private static Boolean zzgs;
    private static Boolean zzgt;
    private static Boolean zzgu;

    private DeviceProperties() {
    }

    @KeepForSdk
    public static boolean isTablet(Resources resources) {
        boolean z;
        Resources resources2 = resources;
        if (resources2 == null) {
            return false;
        }
        if (zzgn == null) {
            if (!((resources2.getConfiguration().screenLayout & 15) > 3)) {
                Resources resources3 = resources2;
                if (zzgo == null) {
                    Configuration configuration = resources3.getConfiguration();
                    zzgo = Boolean.valueOf((configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600);
                }
                if (!zzgo.booleanValue()) {
                    z = false;
                    zzgn = Boolean.valueOf(z);
                }
            }
            z = true;
            zzgn = Boolean.valueOf(z);
        }
        return zzgn.booleanValue();
    }

    @TargetApi(20)
    @KeepForSdk
    public static boolean isWearable(Context context) {
        Context context2 = context;
        if (zzgp == null) {
            zzgp = Boolean.valueOf(PlatformVersion.isAtLeastKitKatWatch() && context2.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return zzgp.booleanValue();
    }

    @TargetApi(26)
    @KeepForSdk
    public static boolean isWearableWithoutPlayStore(Context context) {
        Context context2 = context;
        if (!isWearable(context2) || (PlatformVersion.isAtLeastN() && (!isSidewinder(context2) || PlatformVersion.isAtLeastO()))) {
            return false;
        }
        return true;
    }

    @TargetApi(21)
    @KeepForSdk
    public static boolean isSidewinder(Context context) {
        Context context2 = context;
        if (zzgq == null) {
            zzgq = Boolean.valueOf(PlatformVersion.isAtLeastLollipop() && context2.getPackageManager().hasSystemFeature("cn.google"));
        }
        return zzgq.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(Context context) {
        Context context2 = context;
        if (zzgr == null) {
            PackageManager packageManager = context2.getPackageManager();
            zzgr = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services"));
        }
        return zzgr.booleanValue();
    }

    public static boolean zzf(Context context) {
        Context context2 = context;
        if (zzgs == null) {
            zzgs = Boolean.valueOf(context2.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context2.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return zzgs.booleanValue();
    }

    @KeepForSdk
    public static boolean isAuto(Context context) {
        Context context2 = context;
        if (zzgt == null) {
            zzgt = Boolean.valueOf(PlatformVersion.isAtLeastO() && context2.getPackageManager().hasSystemFeature("android.hardware.type.automotive"));
        }
        return zzgt.booleanValue();
    }

    @KeepForSdk
    public static boolean isTv(Context context) {
        Context context2 = context;
        if (zzgu == null) {
            PackageManager packageManager = context2.getPackageManager();
            PackageManager packageManager2 = packageManager;
            zzgu = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.tv") || packageManager2.hasSystemFeature("android.hardware.type.television") || packageManager2.hasSystemFeature("android.software.leanback"));
        }
        return zzgu.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }
}
