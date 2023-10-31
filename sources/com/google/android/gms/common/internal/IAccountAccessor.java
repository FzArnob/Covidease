package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IAccountAccessor extends IInterface {

    public static abstract class Stub extends zzb implements IAccountAccessor {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }

        public static class zza extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
            }

            public final Account getAccount() throws RemoteException {
                Parcel zza = zza(2, zza());
                Account account = (Account) zzc.zza(zza, Account.CREATOR);
                zza.recycle();
                return account;
            }
        }

        public static IAccountAccessor asInterface(IBinder iBinder) {
            IAccountAccessor iAccountAccessor;
            IBinder iBinder2 = iBinder;
            if (iBinder2 == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IAccountAccessor) {
                return (IAccountAccessor) iInterface;
            }
            new zza(iBinder2);
            return iAccountAccessor;
        }

        /* access modifiers changed from: protected */
        public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            int i3 = i2;
            if (i != 2) {
                return false;
            }
            Account account = getAccount();
            parcel4.writeNoException();
            zzc.zzb(parcel4, account);
            return true;
        }
    }

    Account getAccount() throws RemoteException;
}
