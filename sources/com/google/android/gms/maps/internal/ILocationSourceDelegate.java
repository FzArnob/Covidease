package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public interface ILocationSourceDelegate extends IInterface {
    void activate(zzah zzah) throws RemoteException;

    void deactivate() throws RemoteException;

    public static abstract class zza extends zzb implements ILocationSourceDelegate {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zza() {
            super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
        }

        /* access modifiers changed from: protected */
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            zzah zzah;
            zzah zzah2;
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            int i3 = i2;
            switch (i) {
                case 1:
                    IBinder readStrongBinder = parcel3.readStrongBinder();
                    IBinder iBinder = readStrongBinder;
                    if (readStrongBinder == null) {
                        zzah2 = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                        IInterface iInterface = queryLocalInterface;
                        if (queryLocalInterface instanceof zzah) {
                            zzah2 = (zzah) iInterface;
                        } else {
                            zzah2 = zzah;
                            new zzai(iBinder);
                        }
                    }
                    activate(zzah2);
                    break;
                case 2:
                    deactivate();
                    break;
                default:
                    return false;
            }
            parcel4.writeNoException();
            return true;
        }
    }
}
