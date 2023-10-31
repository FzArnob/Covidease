package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.common.zzg;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@KeepForSdk
public class AndroidUtilsLight {
    private static volatile int zzgf = -1;

    public AndroidUtilsLight() {
    }

    @KeepForSdk
    public static byte[] getPackageCertificateHashBytes(Context context, String str) throws PackageManager.NameNotFoundException {
        String str2 = "SHA1";
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
        PackageInfo packageInfo2 = packageInfo;
        if (packageInfo.signatures != null && packageInfo2.signatures.length == 1) {
            MessageDigest zzj = zzj(str2);
            MessageDigest messageDigest = zzj;
            if (zzj != null) {
                return messageDigest.digest(packageInfo2.signatures[0].toByteArray());
            }
        }
        return null;
    }

    public static MessageDigest zzj(String str) {
        String str2 = str;
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str2);
                MessageDigest messageDigest = instance;
                if (instance != null) {
                    return messageDigest;
                }
                i++;
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return null;
    }

    @TargetApi(24)
    @KeepForSdk
    @Deprecated
    public static Context getDeviceProtectedStorageContext(Context context) {
        Context context2 = context;
        if (zzg.zzam()) {
            return zzg.getDeviceProtectedStorageContext(context2);
        }
        return context2;
    }
}
