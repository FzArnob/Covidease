package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzaa extends zzb implements zzz {
    public static zzz zzi(IBinder iBinder) {
        zzz zzz;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzz) {
            return (zzz) iInterface;
        }
        new zzab(iBinder2);
        return zzz;
    }
}
