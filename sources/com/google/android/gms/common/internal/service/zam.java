package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zam extends zaa implements zal {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zam(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    public final void zaa(zaj zaj) throws RemoteException {
        Parcel zaa = zaa();
        zac.zaa(zaa, (IInterface) zaj);
        zac(1, zaa);
    }
}
