package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

public abstract class zzy extends zzb implements zzx {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzy() {
        super("com.google.android.gms.location.ILocationListener");
    }

    public static zzx zzc(IBinder iBinder) {
        zzx zzx;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzx) {
            return (zzx) iInterface;
        }
        new zzz(iBinder2);
        return zzx;
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        onLocationChanged((Location) zzc.zza(parcel3, Location.CREATOR));
        return true;
    }
}
