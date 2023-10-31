package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final Status mStatus;
    private final PendingResult<?>[] zabc;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.mStatus = status;
        this.zabc = pendingResultArr;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        BatchResultToken<R> batchResultToken2 = batchResultToken;
        Preconditions.checkArgument(batchResultToken2.mId < this.zabc.length, "The result token does not belong to this batch");
        return this.zabc[batchResultToken2.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
