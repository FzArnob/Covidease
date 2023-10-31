package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

public abstract class zzv extends zzb implements zzu {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzv() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzu zzb(IBinder iBinder) {
        zzu zzu;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzu) {
            return (zzu) iInterface;
        }
        new zzw(iBinder2);
        return zzu;
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 1:
                onLocationResult((LocationResult) zzc.zza(parcel3, LocationResult.CREATOR));
                break;
            case 2:
                onLocationAvailability((LocationAvailability) zzc.zza(parcel3, LocationAvailability.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
