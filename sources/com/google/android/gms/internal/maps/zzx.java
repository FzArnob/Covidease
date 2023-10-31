package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzx extends zzb implements zzw {
    public static zzw zzh(IBinder iBinder) {
        zzw zzw;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzw) {
            return (zzw) iInterface;
        }
        new zzy(iBinder2);
        return zzw;
    }
}
