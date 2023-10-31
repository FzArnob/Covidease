package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public class FitCenter extends BitmapTransformation {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FitCenter(Context context) {
        super(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FitCenter(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.fitCenter(toTransform, pool, outWidth, outHeight);
    }

    public String getId() {
        return "FitCenter.com.bumptech.glide.load.resource.bitmap";
    }
}
