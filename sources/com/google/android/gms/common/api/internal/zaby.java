package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zaby implements RemoteCall {
    private final BiConsumer zakf;

    zaby(BiConsumer biConsumer) {
        this.zakf = biConsumer;
    }

    public final void accept(Object obj, Object obj2) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        Api.AnyClient anyClient = (Api.AnyClient) obj;
        Api.AnyClient anyClient2 = anyClient;
        this.zakf.accept(anyClient, taskCompletionSource);
    }
}
