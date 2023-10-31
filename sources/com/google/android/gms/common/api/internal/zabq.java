package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zabq extends BroadcastReceiver {
    private Context mContext;
    private final zabr zaji;

    public zabq(zabr zabr) {
        this.zaji = zabr;
    }

    public final void zac(Context context) {
        Context context2 = context;
        this.mContext = context2;
    }

    public final synchronized void unregister() {
        synchronized (this) {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver(this);
            }
            this.mContext = null;
        }
    }

    public final void onReceive(Context context, Intent intent) {
        Context context2 = context;
        Uri data = intent.getData();
        String str = null;
        if (data != null) {
            str = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(str)) {
            this.zaji.zas();
            unregister();
        }
    }
}
