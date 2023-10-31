package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdc;
    private static ClassLoader zzdd = null;
    private static Integer zzde = null;
    private boolean zzdf = false;

    public DowngradeableSafeParcel() {
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract boolean prepareForClientVersion(int i);

    private static ClassLoader zzp() {
        Object obj = zzdc;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    protected static Integer getUnparcelClientVersion() {
        Object obj = zzdc;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean shouldDowngrade() {
        return this.zzdf;
    }

    @KeepForSdk
    public void setShouldDowngrade(boolean z) {
        boolean z2 = z;
        this.zzdf = z2;
    }

    @KeepForSdk
    protected static boolean canUnparcelSafely(String str) {
        String str2 = str;
        ClassLoader zzp = zzp();
        return true;
    }

    static {
        Object obj;
        new Object();
        zzdc = obj;
    }
}
