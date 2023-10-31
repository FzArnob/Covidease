package com.github.ybq.android.spinkit.animation;

import android.util.Property;

public abstract class FloatProperty<T> extends Property<T, Float> {
    public abstract void setValue(T t, float f);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FloatProperty(String name) {
        super(Float.class, name);
    }

    public final void set(T object, Float value) {
        setValue(object, value.floatValue());
    }
}
