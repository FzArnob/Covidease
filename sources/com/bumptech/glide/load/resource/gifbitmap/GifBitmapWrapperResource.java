package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public class GifBitmapWrapperResource implements Resource<GifBitmapWrapper> {
    private final GifBitmapWrapper data;

    public GifBitmapWrapperResource(GifBitmapWrapper gifBitmapWrapper) {
        Throwable th;
        GifBitmapWrapper data2 = gifBitmapWrapper;
        if (data2 == null) {
            Throwable th2 = th;
            new NullPointerException("Data must not be null");
            throw th2;
        }
        this.data = data2;
    }

    public GifBitmapWrapper get() {
        return this.data;
    }

    public int getSize() {
        return this.data.getSize();
    }

    public void recycle() {
        Resource<Bitmap> bitmapResource = this.data.getBitmapResource();
        if (bitmapResource != null) {
            bitmapResource.recycle();
        }
        Resource<GifDrawable> gifDataResource = this.data.getGifResource();
        if (gifDataResource != null) {
            gifDataResource.recycle();
        }
    }
}
