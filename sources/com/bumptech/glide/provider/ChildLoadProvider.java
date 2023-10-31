package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;

public class ChildLoadProvider<A, T, Z, R> implements LoadProvider<A, T, Z, R>, Cloneable {
    private ResourceDecoder<File, Z> cacheDecoder;
    private ResourceEncoder<Z> encoder;
    private final LoadProvider<A, T, Z, R> parent;
    private ResourceDecoder<T, Z> sourceDecoder;
    private Encoder<T> sourceEncoder;
    private ResourceTranscoder<Z, R> transcoder;

    public ChildLoadProvider(LoadProvider<A, T, Z, R> parent2) {
        this.parent = parent2;
    }

    public ModelLoader<A, T> getModelLoader() {
        return this.parent.getModelLoader();
    }

    public void setCacheDecoder(ResourceDecoder<File, Z> cacheDecoder2) {
        ResourceDecoder<File, Z> resourceDecoder = cacheDecoder2;
        this.cacheDecoder = resourceDecoder;
    }

    public void setSourceDecoder(ResourceDecoder<T, Z> sourceDecoder2) {
        ResourceDecoder<T, Z> resourceDecoder = sourceDecoder2;
        this.sourceDecoder = resourceDecoder;
    }

    public void setEncoder(ResourceEncoder<Z> encoder2) {
        ResourceEncoder<Z> resourceEncoder = encoder2;
        this.encoder = resourceEncoder;
    }

    public void setTranscoder(ResourceTranscoder<Z, R> transcoder2) {
        ResourceTranscoder<Z, R> resourceTranscoder = transcoder2;
        this.transcoder = resourceTranscoder;
    }

    public void setSourceEncoder(Encoder<T> sourceEncoder2) {
        Encoder<T> encoder2 = sourceEncoder2;
        this.sourceEncoder = encoder2;
    }

    public ResourceDecoder<File, Z> getCacheDecoder() {
        if (this.cacheDecoder != null) {
            return this.cacheDecoder;
        }
        return this.parent.getCacheDecoder();
    }

    public ResourceDecoder<T, Z> getSourceDecoder() {
        if (this.sourceDecoder != null) {
            return this.sourceDecoder;
        }
        return this.parent.getSourceDecoder();
    }

    public Encoder<T> getSourceEncoder() {
        if (this.sourceEncoder != null) {
            return this.sourceEncoder;
        }
        return this.parent.getSourceEncoder();
    }

    public ResourceEncoder<Z> getEncoder() {
        if (this.encoder != null) {
            return this.encoder;
        }
        return this.parent.getEncoder();
    }

    public ResourceTranscoder<Z, R> getTranscoder() {
        if (this.transcoder != null) {
            return this.transcoder;
        }
        return this.parent.getTranscoder();
    }

    public ChildLoadProvider<A, T, Z, R> clone() {
        Throwable th;
        try {
            return (ChildLoadProvider) super.clone();
        } catch (CloneNotSupportedException e) {
            CloneNotSupportedException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }
}
