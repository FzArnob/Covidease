package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.CameraPosition;

public abstract class zzm extends zzb implements zzl {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzm() {
        super("com.google.android.gms.maps.internal.IOnCameraChangeListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        onCameraChange((CameraPosition) zzc.zza(parcel3, CameraPosition.CREATOR));
        parcel4.writeNoException();
        return true;
    }
}
