package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.zzk;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzo extends zza implements zzm {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    public final boolean zza(zzk zzk, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (Parcelable) zzk);
        zzc.zza(parcel, (IInterface) iObjectWrapper);
        Parcel zza2 = zza(5, parcel);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }
}
