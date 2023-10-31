package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.engine.Resource;

public class SimpleResource<T> implements Resource<T> {
    protected final T data;

    public SimpleResource(T t) {
        Throwable th;
        T data2 = t;
        if (data2 == null) {
            Throwable th2 = th;
            new NullPointerException("Data must not be null");
            throw th2;
        }
        this.data = data2;
    }

    public final T get() {
        return this.data;
    }

    public final int getSize() {
        return 1;
    }

    public void recycle() {
    }
}
