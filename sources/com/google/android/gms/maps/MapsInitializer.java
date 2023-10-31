package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import javax.annotation.concurrent.GuardedBy;

public final class MapsInitializer {
    @GuardedBy("MapsInitializer.class")
    private static boolean zzbm = false;

    public static synchronized int initialize(Context context) {
        int i;
        Throwable th;
        Context context2 = context;
        synchronized (MapsInitializer.class) {
            Object checkNotNull = Preconditions.checkNotNull(context2, "Context is null");
            if (zzbm) {
                i = 0;
            } else {
                try {
                    zze zza = zzbz.zza(context2);
                    CameraUpdateFactory.zza(zza.zze());
                    BitmapDescriptorFactory.zza(zza.zzf());
                    zzbm = true;
                    i = 0;
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    Throwable th2 = th;
                    new RuntimeRemoteException(remoteException);
                    throw th2;
                } catch (GooglePlayServicesNotAvailableException e2) {
                    i = e2.errorCode;
                }
            }
        }
        return i;
    }

    private MapsInitializer() {
    }
}
