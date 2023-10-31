package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzf extends zzb implements zze {
    public static zze zzb(IBinder iBinder) {
        zze zze;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zze) {
            return (zze) iInterface;
        }
        new zzg(iBinder2);
        return zze;
    }
}
