package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public final class PendingResults {
    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(Status status) {
        BasePendingResult basePendingResult;
        Status status2 = status;
        Object checkNotNull = Preconditions.checkNotNull(status2, "Result must not be null");
        new StatusPendingResult(Looper.getMainLooper());
        BasePendingResult basePendingResult2 = basePendingResult;
        BasePendingResult basePendingResult3 = basePendingResult2;
        basePendingResult2.setResult(status2);
        return basePendingResult3;
    }

    private static final class zac<R extends Result> extends BasePendingResult<R> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zac(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final R createFailedResult(Status status) {
            Throwable th;
            Status status2 = status;
            Throwable th2 = th;
            new UnsupportedOperationException("Creating failed results is not supported");
            throw th2;
        }
    }

    private static final class zaa<R extends Result> extends BasePendingResult<R> {
        private final R zaci;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zaa(R r) {
            super(Looper.getMainLooper());
            this.zaci = r;
        }

        /* access modifiers changed from: protected */
        public final R createFailedResult(Status status) {
            Throwable th;
            if (status.getStatusCode() == this.zaci.getStatus().getStatusCode()) {
                return this.zaci;
            }
            Throwable th2 = th;
            new UnsupportedOperationException("Creating failed results is not supported");
            throw th2;
        }
    }

    private static final class zab<R extends Result> extends BasePendingResult<R> {
        private final R zacj;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zab(GoogleApiClient googleApiClient, R r) {
            super(googleApiClient);
            this.zacj = r;
        }

        /* access modifiers changed from: protected */
        public final R createFailedResult(Status status) {
            Status status2 = status;
            return this.zacj;
        }
    }

    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(Status status, GoogleApiClient googleApiClient) {
        BasePendingResult basePendingResult;
        Status status2 = status;
        Object checkNotNull = Preconditions.checkNotNull(status2, "Result must not be null");
        new StatusPendingResult(googleApiClient);
        BasePendingResult basePendingResult2 = basePendingResult;
        BasePendingResult basePendingResult3 = basePendingResult2;
        basePendingResult2.setResult(status2);
        return basePendingResult3;
    }

    @KeepForSdk
    public static <R extends Result> PendingResult<R> immediateFailedResult(R r, GoogleApiClient googleApiClient) {
        BasePendingResult basePendingResult;
        R r2 = r;
        GoogleApiClient googleApiClient2 = googleApiClient;
        Object checkNotNull = Preconditions.checkNotNull(r2, "Result must not be null");
        Preconditions.checkArgument(!r2.getStatus().isSuccess(), "Status code must not be SUCCESS");
        new zab(googleApiClient2, r2);
        BasePendingResult basePendingResult2 = basePendingResult;
        BasePendingResult basePendingResult3 = basePendingResult2;
        basePendingResult2.setResult(r2);
        return basePendingResult3;
    }

    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r) {
        BasePendingResult basePendingResult;
        OptionalPendingResult<R> optionalPendingResult;
        R r2 = r;
        Object checkNotNull = Preconditions.checkNotNull(r2, "Result must not be null");
        new zac((GoogleApiClient) null);
        BasePendingResult basePendingResult2 = basePendingResult;
        basePendingResult2.setResult(r2);
        new OptionalPendingResultImpl(basePendingResult2);
        return optionalPendingResult;
    }

    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r, GoogleApiClient googleApiClient) {
        BasePendingResult basePendingResult;
        OptionalPendingResult<R> optionalPendingResult;
        R r2 = r;
        Object checkNotNull = Preconditions.checkNotNull(r2, "Result must not be null");
        new zac(googleApiClient);
        BasePendingResult basePendingResult2 = basePendingResult;
        basePendingResult2.setResult(r2);
        new OptionalPendingResultImpl(basePendingResult2);
        return optionalPendingResult;
    }

    public static PendingResult<Status> canceledPendingResult() {
        PendingResult<Status> pendingResult;
        new StatusPendingResult(Looper.getMainLooper());
        PendingResult<Status> pendingResult2 = pendingResult;
        pendingResult2.cancel();
        return pendingResult2;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R r) {
        PendingResult<R> pendingResult;
        R r2 = r;
        Object checkNotNull = Preconditions.checkNotNull(r2, "Result must not be null");
        Preconditions.checkArgument(r2.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        new zaa(r2);
        PendingResult<R> pendingResult2 = pendingResult;
        pendingResult2.cancel();
        return pendingResult2;
    }

    @KeepForSdk
    private PendingResults() {
    }
}
