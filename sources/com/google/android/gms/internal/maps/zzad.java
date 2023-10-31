package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzad extends zzb implements zzac {
    public static zzac zzj(IBinder iBinder) {
        zzac zzac;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzac) {
            return (zzac) iInterface;
        }
        new zzae(iBinder2);
        return zzac;
    }
}
