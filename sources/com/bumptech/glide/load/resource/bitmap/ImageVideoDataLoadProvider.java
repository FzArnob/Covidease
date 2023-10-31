package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.model.ImageVideoWrapperEncoder;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;
import java.io.InputStream;

public class ImageVideoDataLoadProvider implements DataLoadProvider<ImageVideoWrapper, Bitmap> {
    private final ResourceDecoder<File, Bitmap> cacheDecoder;
    private final ResourceEncoder<Bitmap> encoder;
    private final ImageVideoBitmapDecoder sourceDecoder;
    private final ImageVideoWrapperEncoder sourceEncoder;

    public ImageVideoDataLoadProvider(DataLoadProvider<InputStream, Bitmap> dataLoadProvider, DataLoadProvider<ParcelFileDescriptor, Bitmap> dataLoadProvider2) {
        ImageVideoWrapperEncoder imageVideoWrapperEncoder;
        ImageVideoBitmapDecoder imageVideoBitmapDecoder;
        DataLoadProvider<InputStream, Bitmap> streamBitmapProvider = dataLoadProvider;
        DataLoadProvider<ParcelFileDescriptor, Bitmap> fileDescriptorBitmapProvider = dataLoadProvider2;
        this.encoder = streamBitmapProvider.getEncoder();
        new ImageVideoWrapperEncoder(streamBitmapProvider.getSourceEncoder(), fileDescriptorBitmapProvider.getSourceEncoder());
        this.sourceEncoder = imageVideoWrapperEncoder;
        this.cacheDecoder = streamBitmapProvider.getCacheDecoder();
        new ImageVideoBitmapDecoder(streamBitmapProvider.getSourceDecoder(), fileDescriptorBitmapProvider.getSourceDecoder());
        this.sourceDecoder = imageVideoBitmapDecoder;
    }

    public ResourceDecoder<File, Bitmap> getCacheDecoder() {
        return this.cacheDecoder;
    }

    public ResourceDecoder<ImageVideoWrapper, Bitmap> getSourceDecoder() {
        return this.sourceDecoder;
    }

    public Encoder<ImageVideoWrapper> getSourceEncoder() {
        return this.sourceEncoder;
    }

    public ResourceEncoder<Bitmap> getEncoder() {
        return this.encoder;
    }
}
