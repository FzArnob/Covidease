package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzau extends zzb implements zzat {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 1:
                zzb(zzu.zzg(parcel3.readStrongBinder()));
                break;
            case 2:
                zzd(zzu.zzg(parcel3.readStrongBinder()));
                break;
            case 3:
                zzc(zzu.zzg(parcel3.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel4.writeNoException();
        return true;
    }
}
