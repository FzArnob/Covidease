package com.google.android.gms.common.api.internal;

final class zat implements Runnable {
    private final /* synthetic */ zas zaeq;

    zat(zas zas) {
        this.zaeq = zas;
    }

    public final void run() {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zax();
            this.zaeq.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeq.zaeo.unlock();
            throw th2;
        }
    }
}
