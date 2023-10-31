package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzd extends zzb implements zzc {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzd() {
        super("com.google.android.gms.maps.internal.ICancelableCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 1:
                onFinish();
                break;
            case 2:
                onCancel();
                break;
            default:
                return false;
        }
        parcel4.writeNoException();
        return true;
    }
}
