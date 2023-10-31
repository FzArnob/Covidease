package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public abstract class RemoteCreator<T> {
    private final String zzic;
    private T zzid;

    @KeepForSdk
    protected RemoteCreator(String str) {
        this.zzic = str;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract T getRemoteCreator(IBinder iBinder);

    @KeepForSdk
    public static class RemoteCreatorException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RemoteCreatorException(String str) {
            super(str);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RemoteCreatorException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public final T getRemoteCreatorInstance(Context context) throws RemoteCreatorException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Context context2 = context;
        if (this.zzid == null) {
            Object checkNotNull = Preconditions.checkNotNull(context2);
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context2);
            Context context3 = remoteContext;
            if (remoteContext == null) {
                Throwable th5 = th4;
                new RemoteCreatorException("Could not get remote context.");
                throw th5;
            }
            try {
                this.zzid = getRemoteCreator((IBinder) context3.getClassLoader().loadClass(this.zzic).newInstance());
            } catch (ClassNotFoundException e) {
                ClassNotFoundException classNotFoundException = e;
                Throwable th6 = th3;
                new RemoteCreatorException("Could not load creator class.", classNotFoundException);
                throw th6;
            } catch (InstantiationException e2) {
                InstantiationException instantiationException = e2;
                Throwable th7 = th2;
                new RemoteCreatorException("Could not instantiate creator.", instantiationException);
                throw th7;
            } catch (IllegalAccessException e3) {
                IllegalAccessException illegalAccessException = e3;
                Throwable th8 = th;
                new RemoteCreatorException("Could not access creator.", illegalAccessException);
                throw th8;
            }
        }
        return this.zzid;
    }
}
