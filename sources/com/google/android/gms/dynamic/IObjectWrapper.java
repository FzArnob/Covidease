package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.common.zzb;

public interface IObjectWrapper extends IInterface {

    public static abstract class Stub extends zzb implements IObjectWrapper {

        public static class zza extends com.google.android.gms.internal.common.zza implements IObjectWrapper {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamic.IObjectWrapper");
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Stub() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static IObjectWrapper asInterface(IBinder iBinder) {
            IObjectWrapper iObjectWrapper;
            IBinder iBinder2 = iBinder;
            if (iBinder2 == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            IInterface iInterface = queryLocalInterface;
            if (queryLocalInterface instanceof IObjectWrapper) {
                return (IObjectWrapper) iInterface;
            }
            new zza(iBinder2);
            return iObjectWrapper;
        }
    }
}
