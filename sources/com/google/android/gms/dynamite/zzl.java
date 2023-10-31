package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzl extends zza implements zzk {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        parcel.writeString(str);
        parcel.writeInt(i);
        zzc.zza(parcel, (IInterface) iObjectWrapper2);
        Parcel zza2 = zza(2, parcel);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        parcel.writeString(str);
        parcel.writeInt(i);
        zzc.zza(parcel, (IInterface) iObjectWrapper2);
        Parcel zza2 = zza(3, parcel);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }
}
