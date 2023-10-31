package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;
import java.io.InputStream;

public class ImageVideoGifDrawableLoadProvider implements DataLoadProvider<ImageVideoWrapper, GifBitmapWrapper> {
    private final ResourceDecoder<File, GifBitmapWrapper> cacheDecoder;
    private final ResourceEncoder<GifBitmapWrapper> encoder;
    private final ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> sourceDecoder;
    private final Encoder<ImageVideoWrapper> sourceEncoder;

    public ImageVideoGifDrawableLoadProvider(DataLoadProvider<ImageVideoWrapper, Bitmap> dataLoadProvider, DataLoadProvider<InputStream, GifDrawable> dataLoadProvider2, BitmapPool bitmapPool) {
        GifBitmapWrapperResourceDecoder gifBitmapWrapperResourceDecoder;
        ResourceDecoder<File, GifBitmapWrapper> resourceDecoder;
        ResourceDecoder resourceDecoder2;
        ResourceEncoder<GifBitmapWrapper> resourceEncoder;
        DataLoadProvider<ImageVideoWrapper, Bitmap> bitmapProvider = dataLoadProvider;
        DataLoadProvider<InputStream, GifDrawable> gifProvider = dataLoadProvider2;
        new GifBitmapWrapperResourceDecoder(bitmapProvider.getSourceDecoder(), gifProvider.getSourceDecoder(), bitmapPool);
        GifBitmapWrapperResourceDecoder decoder = gifBitmapWrapperResourceDecoder;
        new GifBitmapWrapperStreamResourceDecoder(decoder);
        new FileToStreamDecoder(resourceDecoder2);
        this.cacheDecoder = resourceDecoder;
        this.sourceDecoder = decoder;
        new GifBitmapWrapperResourceEncoder(bitmapProvider.getEncoder(), gifProvider.getEncoder());
        this.encoder = resourceEncoder;
        this.sourceEncoder = bitmapProvider.getSourceEncoder();
    }

    public ResourceDecoder<File, GifBitmapWrapper> getCacheDecoder() {
        return this.cacheDecoder;
    }

    public ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> getSourceDecoder() {
        return this.sourceDecoder;
    }

    public Encoder<ImageVideoWrapper> getSourceEncoder() {
        return this.sourceEncoder;
    }

    public ResourceEncoder<GifBitmapWrapper> getEncoder() {
        return this.encoder;
    }
}
