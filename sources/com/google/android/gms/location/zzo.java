package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends UnregisterListenerMethod<zzaz, LocationCallback> {
    private final /* synthetic */ FusedLocationProviderClient zzaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzo(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zzaa = fusedLocationProviderClient;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        try {
            ((zzaz) anyClient).zzb(getListenerKey(), this.zzaa.zza(taskCompletionSource2));
        } catch (RuntimeException e) {
            boolean trySetException = taskCompletionSource2.trySetException(e);
        }
    }
}
