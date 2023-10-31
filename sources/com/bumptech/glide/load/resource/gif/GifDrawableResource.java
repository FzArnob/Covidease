package com.bumptech.glide.load.resource.gif;

import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.util.Util;

public class GifDrawableResource extends DrawableResource<GifDrawable> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GifDrawableResource(GifDrawable drawable) {
        super(drawable);
    }

    public int getSize() {
        return ((GifDrawable) this.drawable).getData().length + Util.getBitmapByteSize(((GifDrawable) this.drawable).getFirstFrame());
    }

    public void recycle() {
        ((GifDrawable) this.drawable).stop();
        ((GifDrawable) this.drawable).recycle();
    }
}
