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

public final class zzk extends zza implements IMapViewDelegate {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    public final IGoogleMapDelegate getMap() throws RemoteException {
        IGoogleMapDelegate iGoogleMapDelegate;
        IGoogleMapDelegate iGoogleMapDelegate2;
        Parcel zza = zza(1, zza());
        Parcel parcel = zza;
        IBinder readStrongBinder = zza.readStrongBinder();
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
        IGoogleMapDelegate iGoogleMapDelegate3 = iGoogleMapDelegate2;
        parcel.recycle();
        return iGoogleMapDelegate3;
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

    public final void getMapAsync(zzap zzap) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzap);
        zzb(9, zza);
    }

    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle);
        zzb(10, zza);
    }

    public final void onExitAmbient() throws RemoteException {
        zzb(11, zza());
    }

    public final void onStart() throws RemoteException {
        zzb(12, zza());
    }

    public final void onStop() throws RemoteException {
        zzb(13, zza());
    }
}
