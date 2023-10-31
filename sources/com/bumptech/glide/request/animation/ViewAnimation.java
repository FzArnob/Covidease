package com.bumptech.glide.request.animation;

import android.view.View;
import android.view.animation.Animation;
import com.bumptech.glide.request.animation.GlideAnimation;

public class ViewAnimation<R> implements GlideAnimation<R> {
    private final AnimationFactory animationFactory;

    interface AnimationFactory {
        Animation build();
    }

    ViewAnimation(AnimationFactory animationFactory2) {
        this.animationFactory = animationFactory2;
    }

    public boolean animate(R r, GlideAnimation.ViewAdapter adapter) {
        R r2 = r;
        View view = adapter.getView();
        if (view != null) {
            view.clearAnimation();
            view.startAnimation(this.animationFactory.build());
        }
        return false;
    }
}
