package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.internal.zzah;
import com.google.android.gms.maps.model.RuntimeRemoteException;

final class zzm implements LocationSource.OnLocationChangedListener {
    private final /* synthetic */ zzah zzu;

    zzm(zzl zzl, zzah zzah) {
        zzl zzl2 = zzl;
        this.zzu = zzah;
    }

    public final void onLocationChanged(Location location) {
        Throwable th;
        try {
            this.zzu.zza(location);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
