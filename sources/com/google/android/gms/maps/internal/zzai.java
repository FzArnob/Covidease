package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzai extends zza implements zzah {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
    }

    public final void zza(Location location) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) location);
        zzb(2, zza);
    }
}
