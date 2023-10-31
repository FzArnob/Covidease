package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzr extends zzb implements zzq {
    public static zzq zzf(IBinder iBinder) {
        zzq zzq;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzq) {
            return (zzq) iInterface;
        }
        new zzs(iBinder2);
        return zzq;
    }
}
