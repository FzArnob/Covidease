package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

public class NullDecoder<T, Z> implements ResourceDecoder<T, Z> {
    private static final NullDecoder<?, ?> NULL_DECODER;

    public NullDecoder() {
    }

    static {
        NullDecoder<?, ?> nullDecoder;
        new NullDecoder<>();
        NULL_DECODER = nullDecoder;
    }

    public static <T, Z> NullDecoder<T, Z> get() {
        return NULL_DECODER;
    }

    public Resource<Z> decode(T t, int i, int i2) {
        T t2 = t;
        int i3 = i;
        int i4 = i2;
        return null;
    }

    public String getId() {
        return "";
    }
}
