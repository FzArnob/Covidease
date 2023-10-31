package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

@KeepForSdk
public class PackageManagerWrapper {
    private final Context zzhx;

    public PackageManagerWrapper(Context context) {
        this.zzhx = context;
    }

    @KeepForSdk
    public ApplicationInfo getApplicationInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.zzhx.getPackageManager().getApplicationInfo(str, i);
    }

    @KeepForSdk
    public PackageInfo getPackageInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.zzhx.getPackageManager().getPackageInfo(str, i);
    }

    public final PackageInfo zza(String str, int i, int i2) throws PackageManager.NameNotFoundException {
        int i3 = i;
        int i4 = i2;
        return this.zzhx.getPackageManager().getPackageInfo(str, 64);
    }

    public final String[] getPackagesForUid(int i) {
        return this.zzhx.getPackageManager().getPackagesForUid(i);
    }

    @TargetApi(19)
    public final boolean zzb(int i, String str) {
        int i2 = i;
        String str2 = str;
        if (PlatformVersion.isAtLeastKitKat()) {
            try {
                ((AppOpsManager) this.zzhx.getSystemService("appops")).checkPackage(i2, str2);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        } else {
            String[] packagesForUid = this.zzhx.getPackageManager().getPackagesForUid(i2);
            if (!(str2 == null || packagesForUid == null)) {
                for (int i3 = 0; i3 < packagesForUid.length; i3++) {
                    if (str2.equals(packagesForUid[i3])) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @KeepForSdk
    public int checkCallingOrSelfPermission(String str) {
        return this.zzhx.checkCallingOrSelfPermission(str);
    }

    @KeepForSdk
    public int checkPermission(String str, String str2) {
        return this.zzhx.getPackageManager().checkPermission(str, str2);
    }

    @KeepForSdk
    public CharSequence getApplicationLabel(String str) throws PackageManager.NameNotFoundException {
        return this.zzhx.getPackageManager().getApplicationLabel(this.zzhx.getPackageManager().getApplicationInfo(str, 0));
    }

    @KeepForSdk
    public boolean isCallerInstantApp() {
        if (Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zzhx);
        }
        if (PlatformVersion.isAtLeastO()) {
            String nameForUid = this.zzhx.getPackageManager().getNameForUid(Binder.getCallingUid());
            String str = nameForUid;
            if (nameForUid != null) {
                return this.zzhx.getPackageManager().isInstantApp(str);
            }
        }
        return false;
    }
}
