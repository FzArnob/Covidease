package com.firebase.client.android;

import android.os.Handler;
import android.os.Looper;
import com.firebase.client.EventTarget;

public class AndroidEventTarget implements EventTarget {
    private final Handler handler;

    public AndroidEventTarget() {
        Handler handler2;
        new Handler(Looper.getMainLooper());
        this.handler = handler2;
    }

    public void postEvent(Runnable r) {
        boolean post = this.handler.post(r);
    }

    public void shutdown() {
    }

    public void restart() {
    }
}
