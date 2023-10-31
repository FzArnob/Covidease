package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public class BitmapResource implements Resource<Bitmap> {
    private final Bitmap bitmap;
    private final BitmapPool bitmapPool;

    public static BitmapResource obtain(Bitmap bitmap2, BitmapPool bitmapPool2) {
        BitmapResource bitmapResource;
        Bitmap bitmap3 = bitmap2;
        BitmapPool bitmapPool3 = bitmapPool2;
        if (bitmap3 == null) {
            return null;
        }
        new BitmapResource(bitmap3, bitmapPool3);
        return bitmapResource;
    }

    public BitmapResource(Bitmap bitmap2, BitmapPool bitmapPool2) {
        Throwable th;
        Throwable th2;
        Bitmap bitmap3 = bitmap2;
        BitmapPool bitmapPool3 = bitmapPool2;
        if (bitmap3 == null) {
            Throwable th3 = th2;
            new NullPointerException("Bitmap must not be null");
            throw th3;
        } else if (bitmapPool3 == null) {
            Throwable th4 = th;
            new NullPointerException("BitmapPool must not be null");
            throw th4;
        } else {
            this.bitmap = bitmap3;
            this.bitmapPool = bitmapPool3;
        }
    }

    public Bitmap get() {
        return this.bitmap;
    }

    public int getSize() {
        return Util.getBitmapByteSize(this.bitmap);
    }

    public void recycle() {
        if (!this.bitmapPool.put(this.bitmap)) {
            this.bitmap.recycle();
        }
    }
}
