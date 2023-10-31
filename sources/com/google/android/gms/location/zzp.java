package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.internal.location.zzad;
import com.google.android.gms.internal.location.zzak;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzp extends zzak {
    private final /* synthetic */ TaskCompletionSource zzab;

    zzp(FusedLocationProviderClient fusedLocationProviderClient, TaskCompletionSource taskCompletionSource) {
        FusedLocationProviderClient fusedLocationProviderClient2 = fusedLocationProviderClient;
        this.zzab = taskCompletionSource;
    }

    public final void zza(zzad zzad) throws RemoteException {
        Exception exc;
        Status status;
        Status status2 = zzad.getStatus();
        Status status3 = status2;
        if (status2 == null) {
            new Status(8, "Got null status from location service");
            new ApiException(status);
            boolean trySetException = this.zzab.trySetException(exc);
        } else if (status3.getStatusCode() == 0) {
            this.zzab.setResult(true);
        } else {
            boolean trySetException2 = this.zzab.trySetException(ApiExceptionUtil.fromStatus(status3));
        }
    }
}
