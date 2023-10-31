package com.github.ybq.android.spinkit.animation;

import android.util.Property;

public abstract class IntProperty<T> extends Property<T, Integer> {
    public abstract void setValue(T t, int i);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IntProperty(String name) {
        super(Integer.class, name);
    }

    public final void set(T object, Integer value) {
        setValue(object, value.intValue());
    }
}
