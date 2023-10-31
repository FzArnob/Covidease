package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
public class Wrappers {
    private static Wrappers zzhz;
    private PackageManagerWrapper zzhy = null;

    public Wrappers() {
    }

    @VisibleForTesting
    private final synchronized PackageManagerWrapper zzi(Context context) {
        PackageManagerWrapper packageManagerWrapper;
        PackageManagerWrapper packageManagerWrapper2;
        Context context2 = context;
        synchronized (this) {
            if (this.zzhy == null) {
                new PackageManagerWrapper(context2.getApplicationContext() == null ? context2 : context2.getApplicationContext());
                this.zzhy = packageManagerWrapper2;
            }
            packageManagerWrapper = this.zzhy;
        }
        return packageManagerWrapper;
    }

    @KeepForSdk
    public static PackageManagerWrapper packageManager(Context context) {
        return zzhz.zzi(context);
    }

    static {
        Wrappers wrappers;
        new Wrappers();
        zzhz = wrappers;
    }
}
