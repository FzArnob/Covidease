package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzo extends zzb implements zzn {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzo() {
        super("com.google.android.gms.maps.internal.IOnCameraIdleListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        onCameraIdle();
        parcel4.writeNoException();
        return true;
    }
}
