package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public abstract class BitmapTransformation implements Transformation<Bitmap> {
    private BitmapPool bitmapPool;

    /* access modifiers changed from: protected */
    public abstract Bitmap transform(BitmapPool bitmapPool2, Bitmap bitmap, int i, int i2);

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BitmapTransformation(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public BitmapTransformation(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
    }

    public final Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) {
        Resource<Bitmap> result;
        Throwable th;
        StringBuilder sb;
        Resource<Bitmap> resource2 = resource;
        int outWidth = i;
        int outHeight = i2;
        if (!Util.isValidDimensions(outWidth, outHeight)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Cannot apply transformation on width: ").append(outWidth).append(" or height: ").append(outHeight).append(" less than or equal to zero and not Target.SIZE_ORIGINAL").toString());
            throw th2;
        }
        Bitmap toTransform = resource2.get();
        Bitmap transformed = transform(this.bitmapPool, toTransform, outWidth == Integer.MIN_VALUE ? toTransform.getWidth() : outWidth, outHeight == Integer.MIN_VALUE ? toTransform.getHeight() : outHeight);
        if (toTransform.equals(transformed)) {
            result = resource2;
        } else {
            result = BitmapResource.obtain(transformed, this.bitmapPool);
        }
        return result;
    }
}
