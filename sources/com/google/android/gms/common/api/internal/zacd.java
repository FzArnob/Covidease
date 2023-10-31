package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.concurrent.TimeUnit;

public final class zacd<R extends Result> extends PendingResult<R> {
    private final Status mStatus;

    public zacd(Status status) {
        Status status2 = status;
        Object checkNotNull = Preconditions.checkNotNull(status2, "Status must not be null");
        Preconditions.checkArgument(!status2.isSuccess(), "Status must not be success");
        this.mStatus = status2;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public final Status getStatus() {
        return this.mStatus;
    }

    @NonNull
    public final R await() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    @NonNull
    public final R await(long j, @NonNull TimeUnit timeUnit) {
        Throwable th;
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    public final void cancel() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    public final boolean isCanceled() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback) {
        Throwable th;
        ResultCallback<? super R> resultCallback2 = resultCallback;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback, long j, @NonNull TimeUnit timeUnit) {
        Throwable th;
        ResultCallback<? super R> resultCallback2 = resultCallback;
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    public final void addStatusListener(@NonNull PendingResult.StatusListener statusListener) {
        Throwable th;
        PendingResult.StatusListener statusListener2 = statusListener;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    @ShowFirstParty
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        Throwable th;
        ResultTransform<? super R, ? extends S> resultTransform2 = resultTransform;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }

    @Nullable
    public final Integer zam() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
        throw th2;
    }
}
