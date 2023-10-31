package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzq;
import com.google.android.gms.internal.maps.zzr;
import java.util.ArrayList;
import java.util.List;

public final class IndoorBuilding {
    @NonNull
    private final zzn zzdd;
    @NonNull
    private final zza zzde;

    @VisibleForTesting
    static class zza {
        public static final zza zzdf;

        private zza() {
        }

        @NonNull
        public static IndoorLevel zza(@NonNull zzq zzq) {
            IndoorLevel indoorLevel;
            new IndoorLevel(zzq);
            return indoorLevel;
        }

        @NonNull
        public static zzq zza(IBinder iBinder) {
            return zzr.zzf(iBinder);
        }

        static {
            zza zza;
            new zza();
            zzdf = zza;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public IndoorBuilding(@NonNull zzn zzn) {
        this(zzn, zza.zzdf);
    }

    @VisibleForTesting
    private IndoorBuilding(@NonNull zzn zzn, @NonNull zza zza2) {
        this.zzdd = (zzn) Preconditions.checkNotNull(zzn, "delegate");
        this.zzde = (zza) Preconditions.checkNotNull(zza2, "shim");
    }

    public final int getDefaultLevelIndex() {
        Throwable th;
        try {
            return this.zzdd.getDefaultLevelIndex();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final int getActiveLevelIndex() {
        Throwable th;
        try {
            return this.zzdd.getActiveLevelIndex();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final List<IndoorLevel> getLevels() {
        Throwable th;
        List<IndoorLevel> list;
        try {
            List<IBinder> levels = this.zzdd.getLevels();
            new ArrayList(levels.size());
            List<IndoorLevel> list2 = list;
            for (IBinder zza2 : levels) {
                boolean add = list2.add(zza.zza(zza.zza(zza2)));
            }
            return list2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isUnderground() {
        Throwable th;
        try {
            return this.zzdd.isUnderground();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean equals(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (!(obj2 instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.zzdd.zzb(((IndoorBuilding) obj2).zzdd);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final int hashCode() {
        Throwable th;
        try {
            return this.zzdd.zzj();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
