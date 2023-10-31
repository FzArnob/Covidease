package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.request.animation.GlideAnimation;

public abstract class ImageViewTarget<Z> extends ViewTarget<ImageView, Z> implements GlideAnimation.ViewAdapter {
    /* access modifiers changed from: protected */
    public abstract void setResource(Z z);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageViewTarget(ImageView view) {
        super(view);
    }

    public Drawable getCurrentDrawable() {
        return ((ImageView) this.view).getDrawable();
    }

    public void setDrawable(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    public void onLoadStarted(Drawable placeholder) {
        ((ImageView) this.view).setImageDrawable(placeholder);
    }

    public void onLoadFailed(Exception exc, Drawable errorDrawable) {
        Exception exc2 = exc;
        ((ImageView) this.view).setImageDrawable(errorDrawable);
    }

    public void onLoadCleared(Drawable placeholder) {
        ((ImageView) this.view).setImageDrawable(placeholder);
    }

    public void onResourceReady(Z z, GlideAnimation<? super Z> glideAnimation) {
        Z resource = z;
        GlideAnimation<? super Z> glideAnimation2 = glideAnimation;
        if (glideAnimation2 == null || !glideAnimation2.animate(resource, this)) {
            setResource(resource);
        }
    }
}
