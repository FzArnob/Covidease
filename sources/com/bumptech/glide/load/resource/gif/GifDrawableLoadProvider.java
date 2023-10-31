package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;
import java.io.InputStream;

public class GifDrawableLoadProvider implements DataLoadProvider<InputStream, GifDrawable> {
    private final FileToStreamDecoder<GifDrawable> cacheDecoder;
    private final GifResourceDecoder decoder;
    private final GifResourceEncoder encoder;
    private final StreamEncoder sourceEncoder;

    public GifDrawableLoadProvider(Context context, BitmapPool bitmapPool) {
        GifResourceDecoder gifResourceDecoder;
        FileToStreamDecoder<GifDrawable> fileToStreamDecoder;
        GifResourceEncoder gifResourceEncoder;
        StreamEncoder streamEncoder;
        BitmapPool bitmapPool2 = bitmapPool;
        new GifResourceDecoder(context, bitmapPool2);
        this.decoder = gifResourceDecoder;
        new FileToStreamDecoder<>(this.decoder);
        this.cacheDecoder = fileToStreamDecoder;
        new GifResourceEncoder(bitmapPool2);
        this.encoder = gifResourceEncoder;
        new StreamEncoder();
        this.sourceEncoder = streamEncoder;
    }

    public ResourceDecoder<File, GifDrawable> getCacheDecoder() {
        return this.cacheDecoder;
    }

    public ResourceDecoder<InputStream, GifDrawable> getSourceDecoder() {
        return this.decoder;
    }

    public Encoder<InputStream> getSourceEncoder() {
        return this.sourceEncoder;
    }

    public ResourceEncoder<GifDrawable> getEncoder() {
        return this.encoder;
    }
}
