package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zacl implements Continuation<Boolean, Void> {
    zacl() {
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        Throwable th;
        Status status;
        if (((Boolean) task.getResult()).booleanValue()) {
            return null;
        }
        Throwable th2 = th;
        new Status(13, "listener already unregistered");
        new ApiException(status);
        throw th2;
    }
}
