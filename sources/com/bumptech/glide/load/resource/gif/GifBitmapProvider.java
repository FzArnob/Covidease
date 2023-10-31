package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

class GifBitmapProvider implements GifDecoder.BitmapProvider {
    private final BitmapPool bitmapPool;

    public GifBitmapProvider(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
    }

    public Bitmap obtain(int width, int height, Bitmap.Config config) {
        return this.bitmapPool.getDirty(width, height, config);
    }

    public void release(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (!this.bitmapPool.put(bitmap2)) {
            bitmap2.recycle();
        }
    }
}
