package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public interface IStatusCallback extends IInterface {

    public static abstract class Stub extends zab implements IStatusCallback {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        public static class zaa extends com.google.android.gms.internal.base.zaa implements IStatusCallback {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            zaa(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.api.internal.IStatusCallback");
            }

            public final void onResult(Status status) throws RemoteException {
                Parcel zaa = zaa();
                zac.zaa(zaa, (Parcelable) status);
                zac(1, zaa);
            }
        }

        public static IStatusCallback asInterface(IBinder iBinder) {
            IStatusCallback iStatusCallback;
            IBinder iBinder2 = iBinder;
            if (iBinder2 == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IStatusCallback) {
                return (IStatusCallback) iInterface;
            }
            new zaa(iBinder2);
            return iStatusCallback;
        }

        /* access modifiers changed from: protected */
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            int i3 = i2;
            if (i != 1) {
                return false;
            }
            onResult((Status) zac.zaa(parcel3, Status.CREATOR));
            return true;
        }
    }

    void onResult(Status status) throws RemoteException;
}
