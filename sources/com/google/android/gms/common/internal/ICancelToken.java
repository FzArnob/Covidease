package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;

public interface ICancelToken extends IInterface {

    public static abstract class Stub extends zzb implements ICancelToken {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }

        public static class zza extends com.google.android.gms.internal.common.zza implements ICancelToken {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.ICancelToken");
            }

            public final void cancel() throws RemoteException {
                zzc(2, zza());
            }
        }

        public static ICancelToken asInterface(IBinder iBinder) {
            ICancelToken iCancelToken;
            IBinder iBinder2 = iBinder;
            if (iBinder2 == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof ICancelToken) {
                return (ICancelToken) iInterface;
            }
            new zza(iBinder2);
            return iCancelToken;
        }
    }

    void cancel() throws RemoteException;
}
