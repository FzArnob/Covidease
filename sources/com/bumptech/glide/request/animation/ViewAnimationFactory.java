package com.bumptech.glide.request.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bumptech.glide.request.animation.ViewAnimation;

public class ViewAnimationFactory<R> implements GlideAnimationFactory<R> {
    private final ViewAnimation.AnimationFactory animationFactory;
    private GlideAnimation<R> glideAnimation;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ViewAnimationFactory(android.view.animation.Animation r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            com.bumptech.glide.request.animation.ViewAnimationFactory$ConcreteAnimationFactory r3 = new com.bumptech.glide.request.animation.ViewAnimationFactory$ConcreteAnimationFactory
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            r2.<init>((com.bumptech.glide.request.animation.ViewAnimation.AnimationFactory) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.ViewAnimationFactory.<init>(android.view.animation.Animation):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ViewAnimationFactory(android.content.Context r10, int r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            com.bumptech.glide.request.animation.ViewAnimationFactory$ResourceAnimationFactory r4 = new com.bumptech.glide.request.animation.ViewAnimationFactory$ResourceAnimationFactory
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r3.<init>((com.bumptech.glide.request.animation.ViewAnimation.AnimationFactory) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.animation.ViewAnimationFactory.<init>(android.content.Context, int):void");
    }

    ViewAnimationFactory(ViewAnimation.AnimationFactory animationFactory2) {
        this.animationFactory = animationFactory2;
    }

    public GlideAnimation<R> build(boolean isFromMemoryCache, boolean z) {
        GlideAnimation<R> glideAnimation2;
        boolean isFirstResource = z;
        if (isFromMemoryCache || !isFirstResource) {
            return NoAnimation.get();
        }
        if (this.glideAnimation == null) {
            new ViewAnimation(this.animationFactory);
            this.glideAnimation = glideAnimation2;
        }
        return this.glideAnimation;
    }

    private static class ConcreteAnimationFactory implements ViewAnimation.AnimationFactory {
        private final Animation animation;

        public ConcreteAnimationFactory(Animation animation2) {
            this.animation = animation2;
        }

        public Animation build() {
            return this.animation;
        }
    }

    private static class ResourceAnimationFactory implements ViewAnimation.AnimationFactory {
        private final int animationId;
        private final Context context;

        public ResourceAnimationFactory(Context context2, int animationId2) {
            this.context = context2.getApplicationContext();
            this.animationId = animationId2;
        }

        public Animation build() {
            return AnimationUtils.loadAnimation(this.context, this.animationId);
        }
    }
}
