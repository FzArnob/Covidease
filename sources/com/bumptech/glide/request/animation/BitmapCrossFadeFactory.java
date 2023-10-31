package com.bumptech.glide.request.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;

public class BitmapCrossFadeFactory extends BitmapContainerCrossFadeFactory<Bitmap> {
    public BitmapCrossFadeFactory() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitmapCrossFadeFactory(int duration) {
        super(duration);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitmapCrossFadeFactory(Context context, int defaultAnimationId, int duration) {
        super(context, defaultAnimationId, duration);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitmapCrossFadeFactory(Animation defaultAnimation, int duration) {
        super(defaultAnimation, duration);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitmapCrossFadeFactory(GlideAnimationFactory<Drawable> realFactory) {
        super(realFactory);
    }

    /* access modifiers changed from: protected */
    public Bitmap getBitmap(Bitmap current) {
        return current;
    }
}
