package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.util.Util;

class ResourceRecycler {
    private final Handler handler;
    private boolean isRecycling;

    ResourceRecycler() {
        Handler handler2;
        Handler.Callback callback;
        new ResourceRecyclerCallback((C15121) null);
        new Handler(Looper.getMainLooper(), callback);
        this.handler = handler2;
    }

    public void recycle(Resource<?> resource) {
        Resource<?> resource2 = resource;
        Util.assertMainThread();
        if (this.isRecycling) {
            this.handler.obtainMessage(1, resource2).sendToTarget();
            return;
        }
        this.isRecycling = true;
        resource2.recycle();
        this.isRecycling = false;
    }

    private static class ResourceRecyclerCallback implements Handler.Callback {
        public static final int RECYCLE_RESOURCE = 1;

        private ResourceRecyclerCallback() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ ResourceRecyclerCallback(C15121 r4) {
            this();
            C15121 r1 = r4;
        }

        public boolean handleMessage(Message message) {
            Message message2 = message;
            if (message2.what != 1) {
                return false;
            }
            ((Resource) message2.obj).recycle();
            return true;
        }
    }
}
