package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class BitmapImageViewTarget extends ImageViewTarget<Bitmap> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitmapImageViewTarget(ImageView view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public void setResource(Bitmap resource) {
        ((ImageView) this.view).setImageBitmap(resource);
    }
}
