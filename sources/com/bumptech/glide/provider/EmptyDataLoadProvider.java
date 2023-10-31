package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import java.io.File;

public class EmptyDataLoadProvider<T, Z> implements DataLoadProvider<T, Z> {
    private static final DataLoadProvider<?, ?> EMPTY_DATA_LOAD_PROVIDER;

    public EmptyDataLoadProvider() {
    }

    static {
        DataLoadProvider<?, ?> dataLoadProvider;
        new EmptyDataLoadProvider();
        EMPTY_DATA_LOAD_PROVIDER = dataLoadProvider;
    }

    public static <T, Z> DataLoadProvider<T, Z> get() {
        return EMPTY_DATA_LOAD_PROVIDER;
    }

    public ResourceDecoder<File, Z> getCacheDecoder() {
        return null;
    }

    public ResourceDecoder<T, Z> getSourceDecoder() {
        return null;
    }

    public Encoder<T> getSourceEncoder() {
        return null;
    }

    public ResourceEncoder<Z> getEncoder() {
        return null;
    }
}
