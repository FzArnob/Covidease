package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;

public abstract class zzak extends zzb implements zzaj {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzak() {
        super("com.google.android.gms.maps.internal.IOnMapClickListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        onMapClick((LatLng) zzc.zza(parcel3, LatLng.CREATOR));
        parcel4.writeNoException();
        return true;
    }
}
