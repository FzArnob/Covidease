package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzbt extends zzb implements zzbs {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbt() {
        super("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 1:
                onSnapshotReady((Bitmap) zzc.zza(parcel3, Bitmap.CREATOR));
                break;
            case 2:
                zzb(IObjectWrapper.Stub.asInterface(parcel3.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel4.writeNoException();
        return true;
    }
}
