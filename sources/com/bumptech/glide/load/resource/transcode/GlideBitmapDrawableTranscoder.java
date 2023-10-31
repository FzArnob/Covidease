package com.bumptech.glide.load.resource.transcode;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawableResource;

public class GlideBitmapDrawableTranscoder implements ResourceTranscoder<Bitmap, GlideBitmapDrawable> {
    private final BitmapPool bitmapPool;
    private final Resources resources;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GlideBitmapDrawableTranscoder(android.content.Context r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            android.content.res.Resources r3 = r3.getResources()
            r4 = r1
            com.bumptech.glide.Glide r4 = com.bumptech.glide.Glide.get(r4)
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r4 = r4.getBitmapPool()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.transcode.GlideBitmapDrawableTranscoder.<init>(android.content.Context):void");
    }

    public GlideBitmapDrawableTranscoder(Resources resources2, BitmapPool bitmapPool2) {
        this.resources = resources2;
        this.bitmapPool = bitmapPool2;
    }

    public Resource<GlideBitmapDrawable> transcode(Resource<Bitmap> toTranscode) {
        GlideBitmapDrawable drawable;
        Resource<GlideBitmapDrawable> resource;
        new GlideBitmapDrawable(this.resources, toTranscode.get());
        new GlideBitmapDrawableResource(drawable, this.bitmapPool);
        return resource;
    }

    public String getId() {
        return "GlideBitmapDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
