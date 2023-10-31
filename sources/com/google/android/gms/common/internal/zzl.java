package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzl extends zza implements IGmsCallbacks {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zza.writeInt(i);
        parcel.writeStrongBinder(iBinder);
        zzc.zza(parcel, (Parcelable) bundle);
        zzb(1, parcel);
    }

    public final void zza(int i, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zza.writeInt(i);
        zzc.zza(parcel, (Parcelable) bundle);
        zzb(2, parcel);
    }

    public final void zza(int i, IBinder iBinder, zzb zzb) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zza.writeInt(i);
        parcel.writeStrongBinder(iBinder);
        zzc.zza(parcel, (Parcelable) zzb);
        zzb(3, parcel);
    }
}
