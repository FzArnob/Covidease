package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzj extends zza implements zzi {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        parcel.writeString(str);
        parcel.writeInt(i);
        Parcel zza2 = zza(2, parcel);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    public final int zza(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        parcel.writeString(str);
        zzc.writeBoolean(parcel, z);
        Parcel zza2 = zza(3, parcel);
        int readInt = zza2.readInt();
        zza2.recycle();
        return readInt;
    }

    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        parcel.writeString(str);
        parcel.writeInt(i);
        Parcel zza2 = zza(4, parcel);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    public final int zzb(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        parcel.writeString(str);
        zzc.writeBoolean(parcel, z);
        Parcel zza2 = zza(5, parcel);
        int readInt = zza2.readInt();
        zza2.recycle();
        return readInt;
    }

    public final int zzak() throws RemoteException {
        Parcel zza = zza(6, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
