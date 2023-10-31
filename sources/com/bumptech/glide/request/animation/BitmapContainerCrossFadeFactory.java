package com.bumptech.glide.request.animation;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.request.animation.GlideAnimation;

public abstract class BitmapContainerCrossFadeFactory<T> implements GlideAnimationFactory<T> {
    private final GlideAnimationFactory<Drawable> realFactory;

    /* access modifiers changed from: protected */
    public abstract Bitmap getBitmap(T t);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BitmapContainerCrossFadeFactory() {
        /*
            r5 = this;
            r0 = r5
            r1 = r0
            com.bumptech.glide.request.animation.DrawableCrossFadeFactory r2 = new com.bumptech.glide.request.animation.DrawableCrossFadeFactory
            r4 = r2
            r2 = r4
            r3 = r4
            r3.<init>()
            r1.<init>((com.bumptech.glide.request.animation.GlideAnimationFactory<android.graphics.drawable.Drawable>) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.BitmapContainerCrossFadeFactory.<init>():void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BitmapContainerCrossFadeFactory(int r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            com.bumptech.glide.request.animation.DrawableCrossFadeFactory r3 = new com.bumptech.glide.request.animation.DrawableCrossFadeFactory
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            r2.<init>((com.bumptech.glide.request.animation.GlideAnimationFactory<android.graphics.drawable.Drawable>) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.BitmapContainerCrossFadeFactory.<init>(int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BitmapContainerCrossFadeFactory(android.content.Context r12, int r13, int r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            com.bumptech.glide.request.animation.DrawableCrossFadeFactory r5 = new com.bumptech.glide.request.animation.DrawableCrossFadeFactory
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r1
            r8 = r2
            r9 = r3
            r6.<init>(r7, r8, r9)
            r4.<init>((com.bumptech.glide.request.animation.GlideAnimationFactory<android.graphics.drawable.Drawable>) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.BitmapContainerCrossFadeFactory.<init>(android.content.Context, int, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BitmapContainerCrossFadeFactory(android.view.animation.Animation r10, int r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            com.bumptech.glide.request.animation.DrawableCrossFadeFactory r4 = new com.bumptech.glide.request.animation.DrawableCrossFadeFactory
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r7 = r2
            r5.<init>((android.view.animation.Animation) r6, (int) r7)
            r3.<init>((com.bumptech.glide.request.animation.GlideAnimationFactory<android.graphics.drawable.Drawable>) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.BitmapContainerCrossFadeFactory.<init>(android.view.animation.Animation, int):void");
    }

    public BitmapContainerCrossFadeFactory(GlideAnimationFactory<Drawable> realFactory2) {
        this.realFactory = realFactory2;
    }

    public GlideAnimation<T> build(boolean isFromMemoryCache, boolean isFirstResource) {
        GlideAnimation<T> glideAnimation;
        new BitmapGlideAnimation(this, this.realFactory.build(isFromMemoryCache, isFirstResource));
        return glideAnimation;
    }

    private class BitmapGlideAnimation implements GlideAnimation<T> {
        final /* synthetic */ BitmapContainerCrossFadeFactory this$0;
        private final GlideAnimation<Drawable> transition;

        public BitmapGlideAnimation(BitmapContainerCrossFadeFactory bitmapContainerCrossFadeFactory, GlideAnimation<Drawable> transition2) {
            this.this$0 = bitmapContainerCrossFadeFactory;
            this.transition = transition2;
        }

        public boolean animate(T current, GlideAnimation.ViewAdapter viewAdapter) {
            Object obj;
            GlideAnimation.ViewAdapter adapter = viewAdapter;
            new BitmapDrawable(adapter.getView().getResources(), this.this$0.getBitmap(current));
            return this.transition.animate(obj, adapter);
        }
    }
}
