package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;

public class FixedLoadProvider<A, T, Z, R> implements LoadProvider<A, T, Z, R> {
    private final DataLoadProvider<T, Z> dataLoadProvider;
    private final ModelLoader<A, T> modelLoader;
    private final ResourceTranscoder<Z, R> transcoder;

    public FixedLoadProvider(ModelLoader<A, T> modelLoader2, ResourceTranscoder<Z, R> resourceTranscoder, DataLoadProvider<T, Z> dataLoadProvider2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModelLoader<A, T> modelLoader3 = modelLoader2;
        ResourceTranscoder<Z, R> transcoder2 = resourceTranscoder;
        DataLoadProvider<T, Z> dataLoadProvider3 = dataLoadProvider2;
        if (modelLoader3 == null) {
            Throwable th4 = th3;
            new NullPointerException("ModelLoader must not be null");
            throw th4;
        }
        this.modelLoader = modelLoader3;
        if (transcoder2 == null) {
            Throwable th5 = th2;
            new NullPointerException("Transcoder must not be null");
            throw th5;
        }
        this.transcoder = transcoder2;
        if (dataLoadProvider3 == null) {
            Throwable th6 = th;
            new NullPointerException("DataLoadProvider must not be null");
            throw th6;
        }
        this.dataLoadProvider = dataLoadProvider3;
    }

    public ModelLoader<A, T> getModelLoader() {
        return this.modelLoader;
    }

    public ResourceTranscoder<Z, R> getTranscoder() {
        return this.transcoder;
    }

    public ResourceDecoder<File, Z> getCacheDecoder() {
        return this.dataLoadProvider.getCacheDecoder();
    }

    public ResourceDecoder<T, Z> getSourceDecoder() {
        return this.dataLoadProvider.getSourceDecoder();
    }

    public Encoder<T> getSourceEncoder() {
        return this.dataLoadProvider.getSourceEncoder();
    }

    public ResourceEncoder<Z> getEncoder() {
        return this.dataLoadProvider.getEncoder();
    }
}
