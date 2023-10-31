package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class DrawableImageViewTarget extends ImageViewTarget<Drawable> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DrawableImageViewTarget(ImageView view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public void setResource(Drawable resource) {
        ((ImageView) this.view).setImageDrawable(resource);
    }
}
