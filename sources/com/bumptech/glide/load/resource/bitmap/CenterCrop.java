package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public class CenterCrop extends BitmapTransformation {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CenterCrop(Context context) {
        super(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CenterCrop(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        BitmapPool pool = bitmapPool;
        Bitmap toTransform = bitmap;
        int outWidth = i;
        int outHeight = i2;
        Bitmap toReuse = pool.get(outWidth, outHeight, toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888);
        Bitmap transformed = TransformationUtils.centerCrop(toReuse, toTransform, outWidth, outHeight);
        if (!(toReuse == null || toReuse == transformed || pool.put(toReuse))) {
            toReuse.recycle();
        }
        return transformed;
    }

    public String getId() {
        return "CenterCrop.com.bumptech.glide.load.resource.bitmap";
    }
}
