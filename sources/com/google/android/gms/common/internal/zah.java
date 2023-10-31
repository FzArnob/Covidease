package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zah extends zaa implements ISignInButtonCreator {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    public final IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i, int i2) throws RemoteException {
        Parcel zaa = zaa();
        Parcel parcel = zaa;
        zac.zaa(zaa, (IInterface) iObjectWrapper);
        parcel.writeInt(i);
        parcel.writeInt(i2);
        Parcel zaa2 = zaa(1, parcel);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zaa2.readStrongBinder());
        zaa2.recycle();
        return asInterface;
    }

    public final IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig) throws RemoteException {
        Parcel zaa = zaa();
        Parcel parcel = zaa;
        zac.zaa(zaa, (IInterface) iObjectWrapper);
        zac.zaa(parcel, (Parcelable) signInButtonConfig);
        Parcel zaa2 = zaa(2, parcel);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zaa2.readStrongBinder());
        zaa2.recycle();
        return asInterface;
    }
}
