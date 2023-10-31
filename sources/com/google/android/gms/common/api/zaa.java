package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

final class zaa implements PendingResult.StatusListener {
    private final /* synthetic */ Batch zabd;

    zaa(Batch batch) {
        this.zabd = batch;
    }

    /* JADX INFO: finally extract failed */
    public final void onComplete(Status status) {
        Status status2;
        Result result;
        Status status3;
        Status status4 = status;
        Object zaa = this.zabd.mLock;
        Object obj = zaa;
        synchronized (zaa) {
            try {
                if (this.zabd.isCanceled()) {
                    return;
                }
                if (status4.isCanceled()) {
                    boolean zaa2 = Batch.zaa(this.zabd, true);
                } else if (!status4.isSuccess()) {
                    boolean zab = Batch.zab(this.zabd, true);
                }
                int zab2 = Batch.zab(this.zabd);
                if (this.zabd.zaaz == 0) {
                    if (this.zabd.zabb) {
                        zaa.super.cancel();
                    } else {
                        if (this.zabd.zaba) {
                            new Status(13);
                            status2 = status3;
                        } else {
                            status2 = Status.RESULT_SUCCESS;
                        }
                        new BatchResult(status2, this.zabd.zabc);
                        this.zabd.setResult(result);
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj2 = obj;
                throw th2;
            }
        }
    }
}
