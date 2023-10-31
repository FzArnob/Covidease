package com.google.android.gms.internal.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class zap extends Handler {
    private static volatile zaq zarr = null;

    public zap() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zap(Looper looper) {
        super(looper);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zap(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }

    public final void dispatchMessage(Message message) {
        Message message2 = message;
        Message message3 = message2;
        super.dispatchMessage(message2);
    }
}
