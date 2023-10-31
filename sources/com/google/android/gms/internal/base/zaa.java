package com.google.android.gms.internal.base;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zaa implements IInterface {
    private final IBinder zaa;
    private final String zab;

    protected zaa(IBinder iBinder, String str) {
        this.zaa = iBinder;
        this.zab = str;
    }

    public IBinder asBinder() {
        return this.zaa;
    }

    /* access modifiers changed from: protected */
    public final Parcel zaa() {
        Parcel obtain = Parcel.obtain();
        Parcel parcel = obtain;
        obtain.writeInterfaceToken(this.zab);
        return parcel;
    }

    /* access modifiers changed from: protected */
    public final Parcel zaa(int i, Parcel parcel) throws RemoteException {
        int i2 = i;
        Parcel parcel2 = parcel;
        Parcel obtain = Parcel.obtain();
        try {
            boolean transact = this.zaa.transact(i2, parcel2, obtain, 0);
            obtain.readException();
            parcel2.recycle();
            return obtain;
        } catch (RuntimeException e) {
            RuntimeException runtimeException = e;
            obtain.recycle();
            throw runtimeException;
        } catch (Throwable th) {
            Throwable th2 = th;
            parcel2.recycle();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public final void zab(int i, Parcel parcel) throws RemoteException {
        int i2 = i;
        Parcel parcel2 = parcel;
        Parcel obtain = Parcel.obtain();
        try {
            boolean transact = this.zaa.transact(i2, parcel2, obtain, 0);
            obtain.readException();
            parcel2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            Throwable th2 = th;
            parcel2.recycle();
            obtain.recycle();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public final void zac(int i, Parcel parcel) throws RemoteException {
        int i2 = i;
        Parcel parcel2 = parcel;
        try {
            boolean transact = this.zaa.transact(1, parcel2, (Parcel) null, 1);
            parcel2.recycle();
        } catch (Throwable th) {
            Throwable th2 = th;
            parcel2.recycle();
            throw th2;
        }
    }
}
