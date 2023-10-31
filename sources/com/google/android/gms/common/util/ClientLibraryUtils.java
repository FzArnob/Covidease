package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;

@KeepForSdk
public class ClientLibraryUtils {
    private ClientLibraryUtils() {
    }

    @KeepForSdk
    public static int getClientVersion(Context context, String str) {
        PackageInfo zzb = zzb(context, str);
        PackageInfo packageInfo = zzb;
        if (zzb == null || packageInfo.applicationInfo == null) {
            return -1;
        }
        Bundle bundle = packageInfo.applicationInfo.metaData;
        Bundle bundle2 = bundle;
        if (bundle == null) {
            return -1;
        }
        return bundle2.getInt("com.google.android.gms.version", -1);
    }

    @Nullable
    private static PackageInfo zzb(Context context, String str) {
        try {
            return Wrappers.packageManager(context).getPackageInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean zzc(Context context, String str) {
        String str2 = str;
        boolean equals = "com.google.android.gms".equals(str2);
        try {
            return (Wrappers.packageManager(context).getApplicationInfo(str2, 0).flags & 2097152) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @KeepForSdk
    public static boolean isPackageSide() {
        return false;
    }
}
