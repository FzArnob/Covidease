package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzbq extends zzb implements zzbp {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbq() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate;
        IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate2;
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        IBinder readStrongBinder = parcel3.readStrongBinder();
        IBinder iBinder = readStrongBinder;
        if (readStrongBinder == null) {
            iStreetViewPanoramaDelegate2 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IStreetViewPanoramaDelegate) {
                iStreetViewPanoramaDelegate2 = (IStreetViewPanoramaDelegate) iInterface;
            } else {
                iStreetViewPanoramaDelegate2 = iStreetViewPanoramaDelegate;
                new zzbu(iBinder);
            }
        }
        zza(iStreetViewPanoramaDelegate2);
        parcel4.writeNoException();
        return true;
    }
}
