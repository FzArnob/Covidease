package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

public class GifDrawableTransformation implements Transformation<GifDrawable> {
    private final BitmapPool bitmapPool;
    private final Transformation<Bitmap> wrapped;

    public GifDrawableTransformation(Transformation<Bitmap> wrapped2, BitmapPool bitmapPool2) {
        this.wrapped = wrapped2;
        this.bitmapPool = bitmapPool2;
    }

    public Resource<GifDrawable> transform(Resource<GifDrawable> resource, int outWidth, int outHeight) {
        Resource<Bitmap> bitmapResource;
        Resource<GifDrawable> resource2;
        GifDrawable gifDrawable;
        Resource<GifDrawable> resource3 = resource;
        GifDrawable drawable = resource3.get();
        Bitmap firstFrame = resource3.get().getFirstFrame();
        new BitmapResource(firstFrame, this.bitmapPool);
        Bitmap transformedFrame = this.wrapped.transform(bitmapResource, outWidth, outHeight).get();
        if (transformedFrame.equals(firstFrame)) {
            return resource3;
        }
        new GifDrawable(drawable, transformedFrame, this.wrapped);
        new GifDrawableResource(gifDrawable);
        return resource2;
    }

    public String getId() {
        return this.wrapped.getId();
    }
}
