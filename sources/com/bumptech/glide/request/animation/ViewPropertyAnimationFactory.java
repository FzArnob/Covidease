package com.bumptech.glide.request.animation;

import com.bumptech.glide.request.animation.ViewPropertyAnimation;

public class ViewPropertyAnimationFactory<R> implements GlideAnimationFactory<R> {
    private ViewPropertyAnimation<R> animation;
    private final ViewPropertyAnimation.Animator animator;

    public ViewPropertyAnimationFactory(ViewPropertyAnimation.Animator animator2) {
        this.animator = animator2;
    }

    public GlideAnimation<R> build(boolean isFromMemoryCache, boolean z) {
        ViewPropertyAnimation<R> viewPropertyAnimation;
        boolean isFirstResource = z;
        if (isFromMemoryCache || !isFirstResource) {
            return NoAnimation.get();
        }
        if (this.animation == null) {
            new ViewPropertyAnimation<>(this.animator);
            this.animation = viewPropertyAnimation;
        }
        return this.animation;
    }
}
