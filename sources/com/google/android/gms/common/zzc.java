package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzn;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
final class zzc {
    private static volatile zzm zzn;
    private static final Object zzo;
    private static Context zzp;

    static synchronized void zza(Context context) {
        Context context2 = context;
        synchronized (zzc.class) {
            if (zzp != null) {
                int w = Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context2 != null) {
                zzp = context2.getApplicationContext();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    static zzm zza(String str, zze zze, boolean z, boolean z2) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zzm zzb = zzb(str, zze, z, z2);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return zzb;
        } catch (Throwable th) {
            Throwable th2 = th;
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th2;
        }
    }

    private static zzm zzb(String str, zze zze, boolean z, boolean z2) {
        String str2;
        String str3;
        zzk zzk;
        Callable callable;
        Throwable th;
        String str4 = str;
        zze zze2 = zze;
        boolean z3 = z;
        boolean z4 = z2;
        try {
            if (zzn == null) {
                Object checkNotNull = Preconditions.checkNotNull(zzp);
                Object obj = zzo;
                Object obj2 = obj;
                synchronized (obj) {
                    if (zzn == null) {
                        zzn = zzn.zzc(DynamiteModule.load(zzp, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                }
            }
            Object checkNotNull2 = Preconditions.checkNotNull(zzp);
            new zzk(str4, zze2, z3, z4);
            try {
                if (zzn.zza(zzk, ObjectWrapper.wrap(zzp.getPackageManager()))) {
                    return zzm.zze();
                }
                new zzd(z3, str4, zze2);
                return zzm.zza(callable);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                int e2 = Log.e("GoogleCertificates", "Failed to get Google certificates from remote", remoteException);
                return zzm.zza("module call", remoteException);
            }
        } catch (DynamiteModule.LoadingException e3) {
            DynamiteModule.LoadingException loadingException = e3;
            int e4 = Log.e("GoogleCertificates", "Failed to get Google certificates from remote", loadingException);
            th = String.valueOf(loadingException.getMessage());
            if (r15.length() != 0) {
                str3 = "module init: ".concat(r10);
            } else {
                str3 = str2;
                new String("module init: ");
            }
            return zzm.zza(str3, loadingException);
        } finally {
            while (true) {
            }
        }
    }

    static final /* synthetic */ String zza(boolean z, String str, zze zze) throws Exception {
        boolean z2 = z;
        String str2 = str;
        zze zze2 = zze;
        return zzm.zzc(str2, zze2, z2, !z2 && zzb(str2, zze2, true, false).zzad);
    }

    static {
        Object obj;
        new Object();
        zzo = obj;
    }
}
