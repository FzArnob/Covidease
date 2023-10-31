package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public interface IResolveAccountCallbacks extends IInterface {

    public static abstract class Stub extends zab implements IResolveAccountCallbacks {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Stub() {
            super("com.google.android.gms.common.internal.IResolveAccountCallbacks");
        }

        public static class Proxy extends zaa implements IResolveAccountCallbacks {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IResolveAccountCallbacks");
            }

            public void onAccountResolutionComplete(ResolveAccountResponse resolveAccountResponse) throws RemoteException {
                Parcel zaa = zaa();
                zac.zaa(zaa, (Parcelable) resolveAccountResponse);
                zab(2, zaa);
            }
        }

        public static IResolveAccountCallbacks asInterface(IBinder iBinder) {
            IResolveAccountCallbacks iResolveAccountCallbacks;
            IBinder iBinder2 = iBinder;
            if (iBinder2 == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IResolveAccountCallbacks) {
                return (IResolveAccountCallbacks) iInterface;
            }
            new Proxy(iBinder2);
            return iResolveAccountCallbacks;
        }

        /* access modifiers changed from: protected */
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            int i3 = i2;
            if (i != 2) {
                return false;
            }
            onAccountResolutionComplete((ResolveAccountResponse) zac.zaa(parcel3, ResolveAccountResponse.CREATOR));
            parcel4.writeNoException();
            return true;
        }
    }

    void onAccountResolutionComplete(ResolveAccountResponse resolveAccountResponse) throws RemoteException;
}
