package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class TaskUtil {
    public TaskUtil() {
    }

    @KeepForSdk
    public static void setResultOrApiException(Status status, TaskCompletionSource<Void> taskCompletionSource) {
        setResultOrApiException(status, (Object) null, taskCompletionSource);
    }

    @KeepForSdk
    public static <TResult> void setResultOrApiException(Status status, TResult tresult, TaskCompletionSource<TResult> taskCompletionSource) {
        Exception exc;
        Status status2 = status;
        TResult tresult2 = tresult;
        TaskCompletionSource<TResult> taskCompletionSource2 = taskCompletionSource;
        if (status2.isSuccess()) {
            taskCompletionSource2.setResult(tresult2);
            return;
        }
        new ApiException(status2);
        taskCompletionSource2.setException(exc);
    }

    @KeepForSdk
    @Deprecated
    public static Task<Void> toVoidTaskThatFailsOnFalse(Task<Boolean> task) {
        Continuation continuation;
        new zacl();
        return task.continueWith(continuation);
    }
}
