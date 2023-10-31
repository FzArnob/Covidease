package com.google.android.gms.common.api.internal;

abstract class zabf {
    private final zabd zahu;

    protected zabf(zabd zabd) {
        this.zahu = zabd;
    }

    /* access modifiers changed from: protected */
    public abstract void zaan();

    public final void zac(zabe zabe) {
        zabe zabe2 = zabe;
        zabe2.zaeo.lock();
        try {
            if (zabe2.zahq != this.zahu) {
                zabe2.zaeo.unlock();
                return;
            }
            zaan();
            zabe2.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            zabe2.zaeo.unlock();
            throw th2;
        }
    }
}
