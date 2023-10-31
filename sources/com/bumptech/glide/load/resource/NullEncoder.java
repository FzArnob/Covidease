package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.Encoder;
import java.io.OutputStream;

public class NullEncoder<T> implements Encoder<T> {
    private static final NullEncoder<?> NULL_ENCODER;

    public NullEncoder() {
    }

    static {
        NullEncoder<?> nullEncoder;
        new NullEncoder<>();
        NULL_ENCODER = nullEncoder;
    }

    public static <T> Encoder<T> get() {
        return NULL_ENCODER;
    }

    public boolean encode(T t, OutputStream outputStream) {
        T t2 = t;
        OutputStream outputStream2 = outputStream;
        return false;
    }

    public String getId() {
        return "";
    }
}
