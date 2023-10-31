package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.common.zzb;

public abstract class zzn extends zzb implements zzm {
    public static zzm zzc(IBinder iBinder) {
        zzm zzm;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzm) {
            return (zzm) iInterface;
        }
        new zzo(iBinder2);
        return zzm;
    }
}
