package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzi extends zzb implements zzh {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzi() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 1:
                IObjectWrapper zzh = zzh(zzu.zzg(parcel3.readStrongBinder()));
                parcel4.writeNoException();
                zzc.zza(parcel4, (IInterface) zzh);
                break;
            case 2:
                IObjectWrapper zzi = zzi(zzu.zzg(parcel3.readStrongBinder()));
                parcel4.writeNoException();
                zzc.zza(parcel4, (IInterface) zzi);
                break;
            default:
                return false;
        }
        return true;
    }
}
