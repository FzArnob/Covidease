package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzl extends zzb implements zzk {
    public static zzk zzd(IBinder iBinder) {
        zzk zzk;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzk) {
            return (zzk) iInterface;
        }
        new zzm(iBinder2);
        return zzk;
    }
}
