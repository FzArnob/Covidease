package com.bumptech.glide.request.animation;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

public class DrawableCrossFadeViewAnimation<T extends Drawable> implements GlideAnimation<T> {
    private final GlideAnimation<T> defaultAnimation;
    private final int duration;

    public DrawableCrossFadeViewAnimation(GlideAnimation<T> defaultAnimation2, int duration2) {
        this.defaultAnimation = defaultAnimation2;
        this.duration = duration2;
    }

    public boolean animate(T t, GlideAnimation.ViewAdapter viewAdapter) {
        TransitionDrawable transitionDrawable;
        T current = t;
        GlideAnimation.ViewAdapter adapter = viewAdapter;
        Drawable previous = adapter.getCurrentDrawable();
        if (previous != null) {
            TransitionDrawable transitionDrawable2 = transitionDrawable;
            Drawable[] drawableArr = new Drawable[2];
            drawableArr[0] = previous;
            Drawable[] drawableArr2 = drawableArr;
            drawableArr2[1] = current;
            new TransitionDrawable(drawableArr2);
            TransitionDrawable transitionDrawable3 = transitionDrawable2;
            transitionDrawable3.setCrossFadeEnabled(true);
            transitionDrawable3.startTransition(this.duration);
            adapter.setDrawable(transitionDrawable3);
            return true;
        }
        boolean animate = this.defaultAnimation.animate(current, adapter);
        return false;
    }
}
