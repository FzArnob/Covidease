package com.google.android.gms.common.internal;

import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

public final class zzp {
    private static Object sLock;
    @GuardedBy("sLock")
    private static boolean zzeo;
    private static String zzep;
    private static int zzeq;

    public static String zzc(Context context) {
        zze(context);
        return zzep;
    }

    public static int zzd(Context context) {
        zze(context);
        return zzeq;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zze(android.content.Context r11) {
        /*
            r0 = r11
            java.lang.Object r7 = sLock
            r10 = r7
            r7 = r10
            r8 = r10
            r1 = r8
            monitor-enter(r7)
            boolean r7 = zzeo     // Catch:{ all -> 0x0056 }
            if (r7 == 0) goto L_0x000f
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
        L_0x000e:
            return
        L_0x000f:
            r7 = 1
            zzeo = r7     // Catch:{ all -> 0x0056 }
            r7 = r0
            java.lang.String r7 = r7.getPackageName()     // Catch:{ all -> 0x0056 }
            r2 = r7
            r7 = r0
            com.google.android.gms.common.wrappers.PackageManagerWrapper r7 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r7)     // Catch:{ all -> 0x0056 }
            r3 = r7
            r7 = r3
            r8 = r2
            r9 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r7 = r7.getApplicationInfo(r8, r9)     // Catch:{ NameNotFoundException -> 0x0048 }
            android.os.Bundle r7 = r7.metaData     // Catch:{ NameNotFoundException -> 0x0048 }
            r10 = r7
            r7 = r10
            r8 = r10
            r5 = r8
            if (r7 != 0) goto L_0x0031
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
            goto L_0x000e
        L_0x0031:
            r7 = r5
            java.lang.String r8 = "com.google.app.id"
            java.lang.String r7 = r7.getString(r8)     // Catch:{ NameNotFoundException -> 0x0048 }
            zzep = r7     // Catch:{ NameNotFoundException -> 0x0048 }
            r7 = r5
            java.lang.String r8 = "com.google.android.gms.version"
            int r7 = r7.getInt(r8)     // Catch:{ NameNotFoundException -> 0x0048 }
            zzeq = r7     // Catch:{ NameNotFoundException -> 0x0048 }
        L_0x0045:
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
            goto L_0x000e
        L_0x0048:
            r7 = move-exception
            r4 = r7
            java.lang.String r7 = "MetadataValueReader"
            java.lang.String r8 = "This should never happen."
            r9 = r4
            int r7 = android.util.Log.wtf(r7, r8, r9)     // Catch:{ all -> 0x0056 }
            goto L_0x0045
        L_0x0056:
            r7 = move-exception
            r6 = r7
            r7 = r1
            monitor-exit(r7)     // Catch:{ all -> 0x0056 }
            r7 = r6
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzp.zze(android.content.Context):void");
    }

    static {
        Object obj;
        new Object();
        sLock = obj;
    }
}
