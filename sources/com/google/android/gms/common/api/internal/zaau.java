package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zagj;

    private zaau(zaak zaak) {
        this.zagj = zaak;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public abstract void zaan();

    @WorkerThread
    public void run() {
        this.zagj.zaeo.lock();
        try {
            if (Thread.interrupted()) {
                this.zagj.zaeo.unlock();
                return;
            }
            zaan();
            this.zagj.zaeo.unlock();
        } catch (RuntimeException e) {
            this.zagj.zaft.zab(e);
            this.zagj.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zagj.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ zaau(zaak zaak, zaal zaal) {
        this(zaak);
        zaal zaal2 = zaal;
    }
}
