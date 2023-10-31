package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzo extends zzb implements zzn {
    public static zzn zze(IBinder iBinder) {
        zzn zzn;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzn) {
            return (zzn) iInterface;
        }
        new zzp(iBinder2);
        return zzn;
    }
}
