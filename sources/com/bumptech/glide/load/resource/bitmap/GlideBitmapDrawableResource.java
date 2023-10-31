package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.util.Util;

public class GlideBitmapDrawableResource extends DrawableResource<GlideBitmapDrawable> {
    private final BitmapPool bitmapPool;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GlideBitmapDrawableResource(GlideBitmapDrawable drawable, BitmapPool bitmapPool2) {
        super(drawable);
        this.bitmapPool = bitmapPool2;
    }

    public int getSize() {
        return Util.getBitmapByteSize(((GlideBitmapDrawable) this.drawable).getBitmap());
    }

    public void recycle() {
        boolean put = this.bitmapPool.put(((GlideBitmapDrawable) this.drawable).getBitmap());
    }
}
