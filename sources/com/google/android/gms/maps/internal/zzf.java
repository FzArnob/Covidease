package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zze;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public final class zzf extends zza implements zze {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICreator");
    }

    public final IMapFragmentDelegate zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        IMapFragmentDelegate iMapFragmentDelegate;
        IMapFragmentDelegate iMapFragmentDelegate2;
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        Parcel zza2 = zza(2, zza);
        Parcel parcel = zza2;
        IBinder readStrongBinder = zza2.readStrongBinder();
        IBinder iBinder = readStrongBinder;
        if (readStrongBinder == null) {
            iMapFragmentDelegate2 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IMapFragmentDelegate) {
                iMapFragmentDelegate2 = (IMapFragmentDelegate) iInterface;
            } else {
                iMapFragmentDelegate2 = iMapFragmentDelegate;
                new zzj(iBinder);
            }
        }
        IMapFragmentDelegate iMapFragmentDelegate3 = iMapFragmentDelegate2;
        parcel.recycle();
        return iMapFragmentDelegate3;
    }

    public final IMapViewDelegate zza(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions) throws RemoteException {
        IMapViewDelegate iMapViewDelegate;
        IMapViewDelegate iMapViewDelegate2;
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzc.zza(parcel, (Parcelable) googleMapOptions);
        Parcel zza2 = zza(3, parcel);
        Parcel parcel2 = zza2;
        IBinder readStrongBinder = zza2.readStrongBinder();
        IBinder iBinder = readStrongBinder;
        if (readStrongBinder == null) {
            iMapViewDelegate2 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IMapViewDelegate) {
                iMapViewDelegate2 = (IMapViewDelegate) iInterface;
            } else {
                iMapViewDelegate2 = iMapViewDelegate;
                new zzk(iBinder);
            }
        }
        IMapViewDelegate iMapViewDelegate3 = iMapViewDelegate2;
        parcel2.recycle();
        return iMapViewDelegate3;
    }

    public final ICameraUpdateFactoryDelegate zze() throws RemoteException {
        ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate;
        ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate2;
        Parcel zza = zza(4, zza());
        Parcel parcel = zza;
        IBinder readStrongBinder = zza.readStrongBinder();
        IBinder iBinder = readStrongBinder;
        if (readStrongBinder == null) {
            iCameraUpdateFactoryDelegate2 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof ICameraUpdateFactoryDelegate) {
                iCameraUpdateFactoryDelegate2 = (ICameraUpdateFactoryDelegate) iInterface;
            } else {
                iCameraUpdateFactoryDelegate2 = iCameraUpdateFactoryDelegate;
                new zzb(iBinder);
            }
        }
        ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate3 = iCameraUpdateFactoryDelegate2;
        parcel.recycle();
        return iCameraUpdateFactoryDelegate3;
    }

    public final zze zzf() throws RemoteException {
        Parcel zza = zza(5, zza());
        zze zzb = com.google.android.gms.internal.maps.zzf.zzb(zza.readStrongBinder());
        zza.recycle();
        return zzb;
    }

    public final void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        parcel.writeInt(i);
        zzb(6, parcel);
    }

    public final IStreetViewPanoramaViewDelegate zza(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException {
        IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate;
        IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate2;
        Parcel zza = zza();
        Parcel parcel = zza;
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzc.zza(parcel, (Parcelable) streetViewPanoramaOptions);
        Parcel zza2 = zza(7, parcel);
        Parcel parcel2 = zza2;
        IBinder readStrongBinder = zza2.readStrongBinder();
        IBinder iBinder = readStrongBinder;
        if (readStrongBinder == null) {
            iStreetViewPanoramaViewDelegate2 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IStreetViewPanoramaViewDelegate) {
                iStreetViewPanoramaViewDelegate2 = (IStreetViewPanoramaViewDelegate) iInterface;
            } else {
                iStreetViewPanoramaViewDelegate2 = iStreetViewPanoramaViewDelegate;
                new zzbw(iBinder);
            }
        }
        IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate3 = iStreetViewPanoramaViewDelegate2;
        parcel2.recycle();
        return iStreetViewPanoramaViewDelegate3;
    }

    public final IStreetViewPanoramaFragmentDelegate zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate;
        IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate2;
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        Parcel zza2 = zza(8, zza);
        Parcel parcel = zza2;
        IBinder readStrongBinder = zza2.readStrongBinder();
        IBinder iBinder = readStrongBinder;
        if (readStrongBinder == null) {
            iStreetViewPanoramaFragmentDelegate2 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IStreetViewPanoramaFragmentDelegate) {
                iStreetViewPanoramaFragmentDelegate2 = (IStreetViewPanoramaFragmentDelegate) iInterface;
            } else {
                iStreetViewPanoramaFragmentDelegate2 = iStreetViewPanoramaFragmentDelegate;
                new zzbv(iBinder);
            }
        }
        IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate3 = iStreetViewPanoramaFragmentDelegate2;
        parcel.recycle();
        return iStreetViewPanoramaFragmentDelegate3;
    }
}
