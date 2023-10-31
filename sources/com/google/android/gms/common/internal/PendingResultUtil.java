package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class PendingResultUtil {
    private static final zaa zaou;

    @KeepForSdk
    public interface ResultConverter<R extends Result, T> {
        @KeepForSdk
        T convert(R r);
    }

    public interface zaa {
        ApiException zaf(Status status);
    }

    public PendingResultUtil() {
    }

    @KeepForSdk
    public static <R extends Result, T> Task<T> toTask(PendingResult<R> pendingResult, ResultConverter<R, T> resultConverter) {
        TaskCompletionSource taskCompletionSource;
        PendingResult.StatusListener statusListener;
        PendingResult<R> pendingResult2 = pendingResult;
        new TaskCompletionSource();
        TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        new zaj(pendingResult2, taskCompletionSource2, resultConverter, zaou);
        pendingResult2.addStatusListener(statusListener);
        return taskCompletionSource2.getTask();
    }

    @KeepForSdk
    public static <R extends Result, T extends Response<R>> Task<T> toResponseTask(PendingResult<R> pendingResult, T t) {
        ResultConverter resultConverter;
        new zak(t);
        return toTask(pendingResult, resultConverter);
    }

    @KeepForSdk
    public static <R extends Result> Task<Void> toVoidTask(PendingResult<R> pendingResult) {
        ResultConverter resultConverter;
        new zal();
        return toTask(pendingResult, resultConverter);
    }

    static {
        zaa zaa2;
        new zai();
        zaou = zaa2;
    }
}
