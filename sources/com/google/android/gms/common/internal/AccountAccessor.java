package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.IAccountAccessor;

public class AccountAccessor extends IAccountAccessor.Stub {
    @KeepForSdk
    public static Account getAccountBinderSafe(IAccountAccessor iAccountAccessor) {
        IAccountAccessor iAccountAccessor2 = iAccountAccessor;
        Account account = null;
        if (iAccountAccessor2 != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = iAccountAccessor2.getAccount();
                Binder.restoreCallingIdentity(clearCallingIdentity);
            } catch (RemoteException e) {
                int w = Log.w("AccountAccessor", "Remote account accessor probably died");
                Binder.restoreCallingIdentity(clearCallingIdentity);
            } catch (Throwable th) {
                Throwable th2 = th;
                Binder.restoreCallingIdentity(clearCallingIdentity);
                throw th2;
            }
        }
        return account;
    }

    public final Account getAccount() {
        Throwable th;
        Throwable th2 = th;
        new NoSuchMethodError();
        throw th2;
    }

    public boolean equals(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new NoSuchMethodError();
        throw th2;
    }
}
