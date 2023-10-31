package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;

public class zzs extends zzb implements zzr {
    public static zzr zza(IBinder iBinder) {
        zzr zzr;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.IDeviceOrientationListener");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzr) {
            return (zzr) iInterface;
        }
        new zzt(iBinder2);
        return zzr;
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Throwable th;
        int i3 = i;
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i4 = i2;
        Throwable th2 = th;
        new NoSuchMethodError();
        throw th2;
    }
}
