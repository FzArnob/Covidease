package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zacb extends UnregisterListenerMethod<A, L> {
    private final /* synthetic */ RegistrationMethods.Builder zakh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zacb(RegistrationMethods.Builder builder, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zakh = builder;
    }

    /* access modifiers changed from: protected */
    public final void unregisterListener(A a, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        this.zakh.zakc.accept(a, taskCompletionSource);
    }
}
