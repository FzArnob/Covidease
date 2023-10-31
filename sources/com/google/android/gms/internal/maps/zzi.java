package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzi extends zzb implements zzh {
    public static zzh zzc(IBinder iBinder) {
        zzh zzh;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzh) {
            return (zzh) iInterface;
        }
        new zzj(iBinder2);
        return zzh;
    }
}
