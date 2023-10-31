package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public abstract class zzj extends zzb implements zzi {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzj() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    public static zzi zzb(IBinder iBinder) {
        zzi zzi;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzi) {
            return (zzi) iInterface;
        }
        new zzk(iBinder2);
        return zzi;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 1:
                IObjectWrapper zzb = zzb();
                parcel4.writeNoException();
                zzc.zza(parcel4, (IInterface) zzb);
                break;
            case 2:
                int zzc = zzc();
                parcel4.writeNoException();
                parcel4.writeInt(zzc);
                break;
            default:
                return false;
        }
        return true;
    }
}
