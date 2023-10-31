package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzu extends zzb implements zzt {
    public static zzt zzg(IBinder iBinder) {
        zzt zzt;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzt) {
            return (zzt) iInterface;
        }
        new zzv(iBinder2);
        return zzt;
    }
}
