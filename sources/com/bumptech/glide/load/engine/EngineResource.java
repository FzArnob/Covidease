package com.bumptech.glide.load.engine;

import android.os.Looper;
import com.bumptech.glide.load.Key;

class EngineResource<Z> implements Resource<Z> {
    private int acquired;
    private final boolean isCacheable;
    private boolean isRecycled;
    private Key key;
    private ResourceListener listener;
    private final Resource<Z> resource;

    interface ResourceListener {
        void onResourceReleased(Key key, EngineResource<?> engineResource);
    }

    EngineResource(Resource<Z> resource2, boolean z) {
        Throwable th;
        Resource<Z> toWrap = resource2;
        boolean isCacheable2 = z;
        if (toWrap == null) {
            Throwable th2 = th;
            new NullPointerException("Wrapped resource must not be null");
            throw th2;
        }
        this.resource = toWrap;
        this.isCacheable = isCacheable2;
    }

    /* access modifiers changed from: package-private */
    public void setResourceListener(Key key2, ResourceListener listener2) {
        this.key = key2;
        this.listener = listener2;
    }

    /* access modifiers changed from: package-private */
    public boolean isCacheable() {
        return this.isCacheable;
    }

    public Z get() {
        return this.resource.get();
    }

    public int getSize() {
        return this.resource.getSize();
    }

    public void recycle() {
        Throwable th;
        Throwable th2;
        if (this.acquired > 0) {
            Throwable th3 = th2;
            new IllegalStateException("Cannot recycle a resource while it is still acquired");
            throw th3;
        } else if (this.isRecycled) {
            Throwable th4 = th;
            new IllegalStateException("Cannot recycle a resource that has already been recycled");
            throw th4;
        } else {
            this.isRecycled = true;
            this.resource.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public void acquire() {
        Throwable th;
        Throwable th2;
        if (this.isRecycled) {
            Throwable th3 = th2;
            new IllegalStateException("Cannot acquire a recycled resource");
            throw th3;
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            Throwable th4 = th;
            new IllegalThreadStateException("Must call acquire on the main thread");
            throw th4;
        } else {
            this.acquired++;
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        Throwable th;
        Throwable th2;
        if (this.acquired <= 0) {
            Throwable th3 = th2;
            new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            throw th3;
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            Throwable th4 = th;
            new IllegalThreadStateException("Must call release on the main thread");
            throw th4;
        } else {
            int i = this.acquired - 1;
            int i2 = i;
            this.acquired = i;
            if (i2 == 0) {
                this.listener.onResourceReleased(this.key, this);
            }
        }
    }
}
