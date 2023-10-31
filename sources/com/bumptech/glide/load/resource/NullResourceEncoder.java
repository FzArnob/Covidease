package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.OutputStream;

public class NullResourceEncoder<T> implements ResourceEncoder<T> {
    private static final NullResourceEncoder<?> NULL_ENCODER;

    public NullResourceEncoder() {
    }

    static {
        NullResourceEncoder<?> nullResourceEncoder;
        new NullResourceEncoder<>();
        NULL_ENCODER = nullResourceEncoder;
    }

    public static <T> NullResourceEncoder<T> get() {
        return NULL_ENCODER;
    }

    public boolean encode(Resource<T> resource, OutputStream outputStream) {
        Resource<T> resource2 = resource;
        OutputStream outputStream2 = outputStream;
        return false;
    }

    public String getId() {
        return "";
    }
}
