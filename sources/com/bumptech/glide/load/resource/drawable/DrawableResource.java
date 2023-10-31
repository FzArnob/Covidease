package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.Resource;

public abstract class DrawableResource<T extends Drawable> implements Resource<T> {
    protected final T drawable;

    public DrawableResource(T t) {
        Throwable th;
        T drawable2 = t;
        if (drawable2 == null) {
            Throwable th2 = th;
            new NullPointerException("Drawable must not be null!");
            throw th2;
        }
        this.drawable = drawable2;
    }

    public final T get() {
        return this.drawable.getConstantState().newDrawable();
    }
}
