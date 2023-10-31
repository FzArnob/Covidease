package com.bumptech.glide.request.animation;

import com.bumptech.glide.request.animation.GlideAnimation;

public class NoAnimation<R> implements GlideAnimation<R> {
    /* access modifiers changed from: private */
    public static final NoAnimation<?> NO_ANIMATION;
    private static final GlideAnimationFactory<?> NO_ANIMATION_FACTORY;

    public NoAnimation() {
    }

    static {
        NoAnimation<?> noAnimation;
        GlideAnimationFactory<?> glideAnimationFactory;
        new NoAnimation<>();
        NO_ANIMATION = noAnimation;
        new NoAnimationFactory();
        NO_ANIMATION_FACTORY = glideAnimationFactory;
    }

    public static class NoAnimationFactory<R> implements GlideAnimationFactory<R> {
        public NoAnimationFactory() {
        }

        public GlideAnimation<R> build(boolean z, boolean z2) {
            boolean z3 = z;
            boolean z4 = z2;
            return NoAnimation.NO_ANIMATION;
        }
    }

    public static <R> GlideAnimationFactory<R> getFactory() {
        return NO_ANIMATION_FACTORY;
    }

    public static <R> GlideAnimation<R> get() {
        return NO_ANIMATION;
    }

    public boolean animate(Object obj, GlideAnimation.ViewAdapter viewAdapter) {
        Object obj2 = obj;
        GlideAnimation.ViewAdapter viewAdapter2 = viewAdapter;
        return false;
    }
}
