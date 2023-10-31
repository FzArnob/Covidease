package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class GmsClientSupervisor {
    private static final Object zzdp;
    private static GmsClientSupervisor zzdq;

    public GmsClientSupervisor() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zza zza2, ServiceConnection serviceConnection, String str);

    /* access modifiers changed from: protected */
    public abstract void zzb(zza zza2, ServiceConnection serviceConnection, String str);

    @KeepForSdk
    public static GmsClientSupervisor getInstance(Context context) {
        GmsClientSupervisor gmsClientSupervisor;
        Context context2 = context;
        Object obj = zzdp;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (zzdq == null) {
                    new zze(context2.getApplicationContext());
                    zzdq = gmsClientSupervisor;
                }
                return zzdq;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    protected static final class zza {
        private final ComponentName mComponentName;
        private final String zzdr;
        private final String zzds;
        private final int zzdt;

        public zza(String str, int i) {
            int i2 = i;
            this.zzdr = Preconditions.checkNotEmpty(str);
            this.zzds = "com.google.android.gms";
            this.mComponentName = null;
            this.zzdt = 129;
        }

        public zza(String str, String str2, int i) {
            this.zzdr = Preconditions.checkNotEmpty(str);
            this.zzds = Preconditions.checkNotEmpty(str2);
            this.mComponentName = null;
            this.zzdt = i;
        }

        public zza(ComponentName componentName, int i) {
            int i2 = i;
            this.zzdr = null;
            this.zzds = null;
            this.mComponentName = (ComponentName) Preconditions.checkNotNull(componentName);
            this.zzdt = 129;
        }

        public final String toString() {
            return this.zzdr == null ? this.mComponentName.flattenToString() : this.zzdr;
        }

        public final String getPackage() {
            return this.zzds;
        }

        public final ComponentName getComponentName() {
            return this.mComponentName;
        }

        public final int zzq() {
            return this.zzdt;
        }

        public final Intent zzb(Context context) {
            Intent intent;
            Intent component;
            Intent intent2;
            Context context2 = context;
            if (this.zzdr != null) {
                new Intent(this.zzdr);
                component = intent2.setPackage(this.zzds);
            } else {
                new Intent();
                component = intent.setComponent(this.mComponentName);
            }
            return component;
        }

        public final int hashCode() {
            Object[] objArr = new Object[4];
            objArr[0] = this.zzdr;
            Object[] objArr2 = objArr;
            objArr2[1] = this.zzds;
            Object[] objArr3 = objArr2;
            objArr3[2] = this.mComponentName;
            Object[] objArr4 = objArr3;
            objArr4[3] = Integer.valueOf(this.zzdt);
            return Objects.hashCode(objArr4);
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r3 = r0
                r4 = r1
                if (r3 != r4) goto L_0x0009
                r3 = 1
                r0 = r3
            L_0x0008:
                return r0
            L_0x0009:
                r3 = r1
                boolean r3 = r3 instanceof com.google.android.gms.common.internal.GmsClientSupervisor.zza
                if (r3 != 0) goto L_0x0011
                r3 = 0
                r0 = r3
                goto L_0x0008
            L_0x0011:
                r3 = r1
                com.google.android.gms.common.internal.GmsClientSupervisor$zza r3 = (com.google.android.gms.common.internal.GmsClientSupervisor.zza) r3
                r2 = r3
                r3 = r0
                java.lang.String r3 = r3.zzdr
                r4 = r2
                java.lang.String r4 = r4.zzdr
                boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
                if (r3 == 0) goto L_0x0044
                r3 = r0
                java.lang.String r3 = r3.zzds
                r4 = r2
                java.lang.String r4 = r4.zzds
                boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
                if (r3 == 0) goto L_0x0044
                r3 = r0
                android.content.ComponentName r3 = r3.mComponentName
                r4 = r2
                android.content.ComponentName r4 = r4.mComponentName
                boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
                if (r3 == 0) goto L_0x0044
                r3 = r0
                int r3 = r3.zzdt
                r4 = r2
                int r4 = r4.zzdt
                if (r3 != r4) goto L_0x0044
                r3 = 1
                r0 = r3
                goto L_0x0008
            L_0x0044:
                r3 = 0
                r0 = r3
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClientSupervisor.zza.equals(java.lang.Object):boolean");
        }
    }

    @KeepForSdk
    public boolean bindService(String str, ServiceConnection serviceConnection, String str2) {
        zza zza2;
        new zza(str, 129);
        return zza(zza2, serviceConnection, str2);
    }

    @KeepForSdk
    public boolean bindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zza zza2;
        new zza(componentName, 129);
        return zza(zza2, serviceConnection, str);
    }

    @KeepForSdk
    public void unbindService(String str, ServiceConnection serviceConnection, String str2) {
        zza zza2;
        new zza(str, 129);
        zzb(zza2, serviceConnection, str2);
    }

    public final void zza(String str, String str2, int i, ServiceConnection serviceConnection, String str3) {
        zza zza2;
        new zza(str, str2, i);
        zzb(zza2, serviceConnection, str3);
    }

    @KeepForSdk
    public void unbindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zza zza2;
        new zza(componentName, 129);
        zzb(zza2, serviceConnection, str);
    }

    static {
        Object obj;
        new Object();
        zzdp = obj;
    }
}
