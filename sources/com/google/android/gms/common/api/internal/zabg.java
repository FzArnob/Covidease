package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zap;

final class zabg extends zap {
    private final /* synthetic */ zabe zahv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zabg(zabe zabe, Looper looper) {
        super(looper);
        this.zahv = zabe;
    }

    public final void handleMessage(Message message) {
        StringBuilder sb;
        Message message2 = message;
        switch (message2.what) {
            case 1:
                ((zabf) message2.obj).zac(this.zahv);
                return;
            case 2:
                throw ((RuntimeException) message2.obj);
            default:
                int i = message2.what;
                new StringBuilder(31);
                int w = Log.w("GACStateManager", sb.append("Unknown message id: ").append(i).toString());
                return;
        }
    }
}
