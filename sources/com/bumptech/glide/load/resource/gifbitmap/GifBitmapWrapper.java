package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public class GifBitmapWrapper {
    private final Resource<Bitmap> bitmapResource;
    private final Resource<GifDrawable> gifResource;

    public GifBitmapWrapper(Resource<Bitmap> resource, Resource<GifDrawable> resource2) {
        Throwable th;
        Throwable th2;
        Resource<Bitmap> bitmapResource2 = resource;
        Resource<GifDrawable> gifResource2 = resource2;
        if (bitmapResource2 != null && gifResource2 != null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Can only contain either a bitmap resource or a gif resource, not both");
            throw th3;
        } else if (bitmapResource2 == null && gifResource2 == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Must contain either a bitmap resource or a gif resource");
            throw th4;
        } else {
            this.bitmapResource = bitmapResource2;
            this.gifResource = gifResource2;
        }
    }

    public int getSize() {
        if (this.bitmapResource != null) {
            return this.bitmapResource.getSize();
        }
        return this.gifResource.getSize();
    }

    public Resource<Bitmap> getBitmapResource() {
        return this.bitmapResource;
    }

    public Resource<GifDrawable> getGifResource() {
        return this.gifResource;
    }
}
