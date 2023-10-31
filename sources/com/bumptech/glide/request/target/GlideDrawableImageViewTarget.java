package com.bumptech.glide.request.target;

import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

public class GlideDrawableImageViewTarget extends ImageViewTarget<GlideDrawable> {
    private static final float SQUARE_RATIO_MARGIN = 0.05f;
    private int maxLoopCount;
    private GlideDrawable resource;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GlideDrawableImageViewTarget(ImageView view) {
        this(view, -1);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GlideDrawableImageViewTarget(ImageView view, int maxLoopCount2) {
        super(view);
        this.maxLoopCount = maxLoopCount2;
    }

    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
        GlideDrawable glideDrawable2;
        GlideDrawable resource2 = glideDrawable;
        GlideAnimation<? super GlideDrawable> animation = glideAnimation;
        if (!resource2.isAnimated()) {
            float viewRatio = ((float) ((ImageView) this.view).getWidth()) / ((float) ((ImageView) this.view).getHeight());
            float drawableRatio = ((float) resource2.getIntrinsicWidth()) / ((float) resource2.getIntrinsicHeight());
            if (Math.abs(viewRatio - 1.0f) <= SQUARE_RATIO_MARGIN && Math.abs(drawableRatio - 1.0f) <= SQUARE_RATIO_MARGIN) {
                new SquaringDrawable(resource2, ((ImageView) this.view).getWidth());
                resource2 = glideDrawable2;
            }
        }
        super.onResourceReady(resource2, animation);
        this.resource = resource2;
        resource2.setLoopCount(this.maxLoopCount);
        resource2.start();
    }

    /* access modifiers changed from: protected */
    public void setResource(GlideDrawable resource2) {
        ((ImageView) this.view).setImageDrawable(resource2);
    }

    public void onStart() {
        if (this.resource != null) {
            this.resource.start();
        }
    }

    public void onStop() {
        if (this.resource != null) {
            this.resource.stop();
        }
    }
}
