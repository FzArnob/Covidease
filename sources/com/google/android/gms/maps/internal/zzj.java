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
import com.google.android.gms.maps.GoogleMapOptions;

public final class zzj extends zza implements IMapFragmentDelegate {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
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

    public final void onInflate(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzc.zza(parcel, (Parcelable) googleMapOptions);
        zzc.zza(parcel, (Parcelable) bundle);
        zzb(2, parcel);
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle);
        zzb(3, zza);
    }

    public final IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzc.zza(parcel, (IInterface) iObjectWrapper2);
        zzc.zza(parcel, (Parcelable) bundle);
        Parcel zza2 = zza(4, parcel);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    public final void onResume() throws RemoteException {
        zzb(5, zza());
    }

    public final void onPause() throws RemoteException {
        zzb(6, zza());
    }

    public final void onDestroyView() throws RemoteException {
        zzb(7, zza());
    }

    public final void onDestroy() throws RemoteException {
        zzb(8, zza());
    }

    public final void onLowMemory() throws RemoteException {
        zzb(9, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Bundle bundle2 = bundle;
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle2);
        Parcel zza2 = zza(10, zza);
        Parcel parcel = zza2;
        if (zza2.readInt() != 0) {
            bundle2.readFromParcel(parcel);
        }
        parcel.recycle();
    }

    public final boolean isReady() throws RemoteException {
        Parcel zza = zza(11, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void getMapAsync(zzap zzap) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzap);
        zzb(12, zza);
    }

    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle);
        zzb(13, zza);
    }

    public final void onExitAmbient() throws RemoteException {
        zzb(14, zza());
    }

    public final void onStart() throws RemoteException {
        zzb(15, zza());
    }

    public final void onStop() throws RemoteException {
        zzb(16, zza());
    }
}
