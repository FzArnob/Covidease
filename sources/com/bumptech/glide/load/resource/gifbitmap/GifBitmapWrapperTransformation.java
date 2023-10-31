package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public class GifBitmapWrapperTransformation implements Transformation<GifBitmapWrapper> {
    private final Transformation<Bitmap> bitmapTransformation;
    private final Transformation<GifDrawable> gifDataTransformation;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifBitmapWrapperTransformation(com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r11, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = r2
            com.bumptech.glide.load.resource.gif.GifDrawableTransformation r5 = new com.bumptech.glide.load.resource.gif.GifDrawableTransformation
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r2
            r8 = r1
            r6.<init>(r7, r8)
            r3.<init>((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r4, (com.bumptech.glide.load.Transformation<com.bumptech.glide.load.resource.gif.GifDrawable>) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapperTransformation.<init>(com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.Transformation):void");
    }

    GifBitmapWrapperTransformation(Transformation<Bitmap> bitmapTransformation2, Transformation<GifDrawable> gifDataTransformation2) {
        this.bitmapTransformation = bitmapTransformation2;
        this.gifDataTransformation = gifDataTransformation2;
    }

    public Resource<GifBitmapWrapper> transform(Resource<GifBitmapWrapper> resource, int i, int i2) {
        GifBitmapWrapper gifBitmap;
        Resource<GifBitmapWrapper> resource2;
        GifBitmapWrapper gifBitmap2;
        Resource<GifBitmapWrapper> resource3;
        Resource<GifBitmapWrapper> resource4 = resource;
        int outWidth = i;
        int outHeight = i2;
        Resource<Bitmap> bitmapResource = resource4.get().getBitmapResource();
        Resource<GifDrawable> gifResource = resource4.get().getGifResource();
        if (bitmapResource != null && this.bitmapTransformation != null) {
            Resource<Bitmap> transformed = this.bitmapTransformation.transform(bitmapResource, outWidth, outHeight);
            if (!bitmapResource.equals(transformed)) {
                new GifBitmapWrapper(transformed, resource4.get().getGifResource());
                new GifBitmapWrapperResource(gifBitmap2);
                return resource3;
            }
        } else if (!(gifResource == null || this.gifDataTransformation == null)) {
            Resource<GifDrawable> transformed2 = this.gifDataTransformation.transform(gifResource, outWidth, outHeight);
            if (!gifResource.equals(transformed2)) {
                new GifBitmapWrapper(resource4.get().getBitmapResource(), transformed2);
                new GifBitmapWrapperResource(gifBitmap);
                return resource2;
            }
        }
        return resource4;
    }

    public String getId() {
        return this.bitmapTransformation.getId();
    }
}
