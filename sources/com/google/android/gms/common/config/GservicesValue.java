package com.google.android.gms.common.config;

import android.content.Context;
import android.os.Binder;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class GservicesValue<T> {
    private static final Object sLock;
    private static zza zzbm = null;
    private static int zzbn = 0;
    private static Context zzbo;
    @GuardedBy("sLock")
    private static HashSet<String> zzbp;
    protected final String mKey;
    protected final T zzbq;
    private T zzbr = null;

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zza(String str, Float f);

        Integer zza(String str, Integer num);
    }

    @KeepForSdk
    public static boolean isInitialized() {
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract T zzd(String str);

    private static boolean zzi() {
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    protected GservicesValue(String str, T t) {
        this.mKey = str;
        this.zzbq = t;
    }

    @KeepForSdk
    @VisibleForTesting
    public void override(T t) {
        int w = Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        T t2 = t;
        this.zzbr = t2;
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean zzi = zzi();
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    @VisibleForTesting
    public void resetOverride() {
        this.zzbr = null;
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    public final T get() {
        long clearCallingIdentity;
        if (this.zzbr != null) {
            return this.zzbr;
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Object obj3 = sLock;
                Object obj4 = obj3;
                synchronized (obj3) {
                    try {
                        zzbp = null;
                        zzbo = null;
                        try {
                            T zzd = zzd(this.mKey);
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            return zzd;
                        } catch (SecurityException e) {
                            clearCallingIdentity = Binder.clearCallingIdentity();
                            T zzd2 = zzd(this.mKey);
                            Binder.restoreCallingIdentity(clearCallingIdentity);
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            return zzd2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            Binder.restoreCallingIdentity(clearCallingIdentity);
                            throw th2;
                        }
                    } catch (Throwable th3) {
                        while (true) {
                            Throwable th4 = th3;
                            Object obj5 = obj4;
                            throw th4;
                        }
                    }
                }
            } catch (Throwable th5) {
                while (true) {
                    Throwable th6 = th5;
                    Object obj6 = obj2;
                    throw th6;
                }
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    @KeepForSdk
    public static GservicesValue<Boolean> value(String str, boolean z) {
        GservicesValue<Boolean> gservicesValue;
        new zza(str, Boolean.valueOf(z));
        return gservicesValue;
    }

    @KeepForSdk
    public static GservicesValue<Long> value(String str, Long l) {
        GservicesValue<Long> gservicesValue;
        new zzb(str, l);
        return gservicesValue;
    }

    @KeepForSdk
    public static GservicesValue<Integer> value(String str, Integer num) {
        GservicesValue<Integer> gservicesValue;
        new zzc(str, num);
        return gservicesValue;
    }

    @KeepForSdk
    public static GservicesValue<Float> value(String str, Float f) {
        GservicesValue<Float> gservicesValue;
        new zzd(str, f);
        return gservicesValue;
    }

    @KeepForSdk
    public static GservicesValue<String> value(String str, String str2) {
        GservicesValue<String> gservicesValue;
        new zze(str, str2);
        return gservicesValue;
    }

    static {
        Object obj;
        new Object();
        sLock = obj;
    }
}
