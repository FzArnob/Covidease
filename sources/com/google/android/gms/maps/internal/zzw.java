package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzi;

public abstract class zzw extends zzb implements zzv {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzw() {
        super("com.google.android.gms.maps.internal.IOnCircleClickListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        zza(zzi.zzc(parcel3.readStrongBinder()));
        parcel4.writeNoException();
        return true;
    }
}
