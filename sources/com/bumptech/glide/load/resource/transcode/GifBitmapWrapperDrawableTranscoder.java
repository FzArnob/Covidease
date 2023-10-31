package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;

public class GifBitmapWrapperDrawableTranscoder implements ResourceTranscoder<GifBitmapWrapper, GlideDrawable> {
    private final ResourceTranscoder<Bitmap, GlideBitmapDrawable> bitmapDrawableResourceTranscoder;

    public GifBitmapWrapperDrawableTranscoder(ResourceTranscoder<Bitmap, GlideBitmapDrawable> bitmapDrawableResourceTranscoder2) {
        this.bitmapDrawableResourceTranscoder = bitmapDrawableResourceTranscoder2;
    }

    public Resource<GlideDrawable> transcode(Resource<GifBitmapWrapper> toTranscode) {
        Resource<GlideBitmapDrawable> gifResource;
        GifBitmapWrapper gifBitmap = toTranscode.get();
        Resource<Bitmap> bitmapResource = gifBitmap.getBitmapResource();
        if (bitmapResource != null) {
            gifResource = this.bitmapDrawableResourceTranscoder.transcode(bitmapResource);
        } else {
            gifResource = gifBitmap.getGifResource();
        }
        return gifResource;
    }

    public String getId() {
        return "GifBitmapWrapperDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
