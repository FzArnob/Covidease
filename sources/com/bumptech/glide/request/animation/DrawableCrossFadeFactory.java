package com.bumptech.glide.request.animation;

import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.bumptech.glide.request.animation.ViewAnimation;

public class DrawableCrossFadeFactory<T extends Drawable> implements GlideAnimationFactory<T> {
    private static final int DEFAULT_DURATION_MS = 300;
    private final ViewAnimationFactory<T> animationFactory;
    private final int duration;
    private DrawableCrossFadeViewAnimation<T> firstResourceAnimation;
    private DrawableCrossFadeViewAnimation<T> secondResourceAnimation;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DrawableCrossFadeFactory() {
        this(300);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DrawableCrossFadeFactory(int r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            com.bumptech.glide.request.animation.ViewAnimationFactory r3 = new com.bumptech.glide.request.animation.ViewAnimationFactory
            r8 = r3
            r3 = r8
            r4 = r8
            com.bumptech.glide.request.animation.DrawableCrossFadeFactory$DefaultAnimationFactory r5 = new com.bumptech.glide.request.animation.DrawableCrossFadeFactory$DefaultAnimationFactory
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r1
            r6.<init>(r7)
            r4.<init>((com.bumptech.glide.request.animation.ViewAnimation.AnimationFactory) r5)
            r4 = r1
            r2.<init>(r3, (int) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.DrawableCrossFadeFactory.<init>(int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DrawableCrossFadeFactory(android.content.Context r11, int r12, int r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            com.bumptech.glide.request.animation.ViewAnimationFactory r5 = new com.bumptech.glide.request.animation.ViewAnimationFactory
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r1
            r8 = r2
            r6.<init>(r7, r8)
            r6 = r3
            r4.<init>(r5, (int) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.DrawableCrossFadeFactory.<init>(android.content.Context, int, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DrawableCrossFadeFactory(android.view.animation.Animation r9, int r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            com.bumptech.glide.request.animation.ViewAnimationFactory r4 = new com.bumptech.glide.request.animation.ViewAnimationFactory
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>((android.view.animation.Animation) r6)
            r5 = r2
            r3.<init>(r4, (int) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.DrawableCrossFadeFactory.<init>(android.view.animation.Animation, int):void");
    }

    DrawableCrossFadeFactory(ViewAnimationFactory<T> animationFactory2, int duration2) {
        this.animationFactory = animationFactory2;
        this.duration = duration2;
    }

    public GlideAnimation<T> build(boolean isFromMemoryCache, boolean z) {
        boolean isFirstResource = z;
        if (isFromMemoryCache) {
            return NoAnimation.get();
        }
        if (isFirstResource) {
            return getFirstResourceAnimation();
        }
        return getSecondResourceAnimation();
    }

    private GlideAnimation<T> getFirstResourceAnimation() {
        DrawableCrossFadeViewAnimation<T> drawableCrossFadeViewAnimation;
        if (this.firstResourceAnimation == null) {
            new DrawableCrossFadeViewAnimation<>(this.animationFactory.build(false, true), this.duration);
            this.firstResourceAnimation = drawableCrossFadeViewAnimation;
        }
        return this.firstResourceAnimation;
    }

    private GlideAnimation<T> getSecondResourceAnimation() {
        DrawableCrossFadeViewAnimation<T> drawableCrossFadeViewAnimation;
        if (this.secondResourceAnimation == null) {
            new DrawableCrossFadeViewAnimation<>(this.animationFactory.build(false, false), this.duration);
            this.secondResourceAnimation = drawableCrossFadeViewAnimation;
        }
        return this.secondResourceAnimation;
    }

    private static class DefaultAnimationFactory implements ViewAnimation.AnimationFactory {
        private final int duration;

        DefaultAnimationFactory(int duration2) {
            this.duration = duration2;
        }

        public Animation build() {
            AlphaAnimation alphaAnimation;
            new AlphaAnimation(0.0f, 1.0f);
            AlphaAnimation animation = alphaAnimation;
            animation.setDuration((long) this.duration);
            return animation;
        }
    }
}
