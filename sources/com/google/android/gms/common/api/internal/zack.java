package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zack extends TaskApiCall<A, ResultT> {
    private final /* synthetic */ TaskApiCall.Builder zakn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zack(TaskApiCall.Builder builder, Feature[] featureArr, boolean z) {
        super(featureArr, z, (zaci) null);
        this.zakn = builder;
    }

    /* access modifiers changed from: protected */
    public final void doExecute(A a, TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException {
        this.zakn.zakm.accept(a, taskCompletionSource);
    }
}
