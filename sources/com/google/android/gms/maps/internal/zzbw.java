package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzbw extends zza implements IStreetViewPanoramaViewDelegate {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
    }

    public final IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException {
        IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate;
        IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate2;
        Parcel zza = zza(1, zza());
        Parcel parcel = zza;
        IBinder readStrongBinder = zza.readStrongBinder();
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
        IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate3 = iStreetViewPanoramaDelegate2;
        parcel.recycle();
        return iStreetViewPanoramaDelegate3;
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle);
        zzb(2, zza);
    }

    public final void onResume() throws RemoteException {
        zzb(3, zza());
    }

    public final void onPause() throws RemoteException {
        zzb(4, zza());
    }

    public final void onDestroy() throws RemoteException {
        zzb(5, zza());
    }

    public final void onLowMemory() throws RemoteException {
        zzb(6, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Bundle bundle2 = bundle;
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle2);
        Parcel zza2 = zza(7, zza);
        Parcel parcel = zza2;
        if (zza2.readInt() != 0) {
            bundle2.readFromParcel(parcel);
        }
        parcel.recycle();
    }

    public final IObjectWrapper getView() throws RemoteException {
        Parcel zza = zza(8, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }

    public final void getStreetViewPanoramaAsync(zzbp zzbp) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzbp);
        zzb(9, zza);
    }

    public final void onStart() throws RemoteException {
        zzb(10, zza());
    }

    public final void onStop() throws RemoteException {
        zzb(11, zza());
    }
}
