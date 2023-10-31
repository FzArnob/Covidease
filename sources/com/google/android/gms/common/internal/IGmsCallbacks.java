package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IGmsCallbacks extends IInterface {
    void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    void zza(int i, Bundle bundle) throws RemoteException;

    void zza(int i, IBinder iBinder, zzb zzb) throws RemoteException;

    public static abstract class zza extends zzb implements IGmsCallbacks {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zza() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }

        /* access modifiers changed from: protected */
        public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            int i3 = i2;
            switch (i) {
                case 1:
                    onPostInitComplete(parcel3.readInt(), parcel3.readStrongBinder(), (Bundle) zzc.zza(parcel3, Bundle.CREATOR));
                    break;
                case 2:
                    zza(parcel3.readInt(), (Bundle) zzc.zza(parcel3, Bundle.CREATOR));
                    break;
                case 3:
                    zza(parcel3.readInt(), parcel3.readStrongBinder(), (zzb) zzc.zza(parcel3, zzb.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel4.writeNoException();
            return true;
        }
    }
}
