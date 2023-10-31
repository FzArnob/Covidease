package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzaq extends zzb implements zzap {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzaq() {
        super("com.google.android.gms.maps.internal.IOnMapReadyCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IGoogleMapDelegate iGoogleMapDelegate;
        IGoogleMapDelegate iGoogleMapDelegate2;
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        IBinder readStrongBinder = parcel3.readStrongBinder();
        IBinder iBinder = readStrongBinder;
        if (readStrongBinder == null) {
            iGoogleMapDelegate2 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IGoogleMapDelegate) {
                iGoogleMapDelegate2 = (IGoogleMapDelegate) iInterface;
            } else {
                iGoogleMapDelegate2 = iGoogleMapDelegate;
                new zzg(iBinder);
            }
        }
        zza(iGoogleMapDelegate2);
        parcel4.writeNoException();
        return true;
    }
}
