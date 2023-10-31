package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends BasePendingResult<BatchResult> {
    /* access modifiers changed from: private */
    public final Object mLock;
    /* access modifiers changed from: private */
    public int zaaz;
    /* access modifiers changed from: private */
    public boolean zaba;
    /* access modifiers changed from: private */
    public boolean zabb;
    /* access modifiers changed from: private */
    public final PendingResult<?>[] zabc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        Object obj;
        PendingResult.StatusListener statusListener;
        Result result;
        List<PendingResult<?>> list2 = list;
        new Object();
        this.mLock = obj;
        this.zaaz = list2.size();
        this.zabc = new PendingResult[this.zaaz];
        if (list2.isEmpty()) {
            new BatchResult(Status.RESULT_SUCCESS, this.zabc);
            setResult(result);
            return;
        }
        for (int i = 0; i < list2.size(); i++) {
            PendingResult pendingResult = list2.get(i);
            this.zabc[i] = pendingResult;
            new zaa(this);
            pendingResult.addStatusListener(statusListener);
        }
    }

    public static final class Builder {
        private List<PendingResult<?>> zabe;
        private GoogleApiClient zabf;

        public Builder(GoogleApiClient googleApiClient) {
            List<PendingResult<?>> list;
            new ArrayList();
            this.zabe = list;
            this.zabf = googleApiClient;
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final <R extends com.google.android.gms.common.api.Result> com.google.android.gms.common.api.BatchResultToken<R> add(com.google.android.gms.common.api.PendingResult<R> r8) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                com.google.android.gms.common.api.BatchResultToken r3 = new com.google.android.gms.common.api.BatchResultToken
                r6 = r3
                r3 = r6
                r4 = r6
                r5 = r0
                java.util.List<com.google.android.gms.common.api.PendingResult<?>> r5 = r5.zabe
                int r5 = r5.size()
                r4.<init>(r5)
                r2 = r3
                r3 = r0
                java.util.List<com.google.android.gms.common.api.PendingResult<?>> r3 = r3.zabe
                r4 = r1
                boolean r3 = r3.add(r4)
                r3 = r2
                r0 = r3
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Batch.Builder.add(com.google.android.gms.common.api.PendingResult):com.google.android.gms.common.api.BatchResultToken");
        }

        public final Batch build() {
            Batch batch;
            new Batch(this.zabe, this.zabf, (zaa) null);
            return batch;
        }
    }

    public final void cancel() {
        super.cancel();
        PendingResult<?>[] pendingResultArr = this.zabc;
        PendingResult<?>[] pendingResultArr2 = pendingResultArr;
        int length = pendingResultArr.length;
        for (int i = 0; i < length; i++) {
            pendingResultArr2[i].cancel();
        }
    }

    public final BatchResult createFailedResult(Status status) {
        BatchResult batchResult;
        new BatchResult(status, this.zabc);
        return batchResult;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zaa zaa) {
        this(list, googleApiClient);
        zaa zaa2 = zaa;
    }

    static /* synthetic */ boolean zaa(Batch batch, boolean z) {
        boolean z2 = z;
        batch.zabb = true;
        return true;
    }

    static /* synthetic */ boolean zab(Batch batch, boolean z) {
        boolean z2 = z;
        batch.zaba = true;
        return true;
    }

    static /* synthetic */ int zab(Batch batch) {
        Batch batch2 = batch;
        int i = batch2.zaaz;
        batch2.zaaz = i - 1;
        return i;
    }
}
