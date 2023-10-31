package android.support.graphics.drawable;

import android.animation.TypeEvaluator;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance;

    public ArgbEvaluator() {
    }

    static {
        ArgbEvaluator argbEvaluator;
        new ArgbEvaluator();
        sInstance = argbEvaluator;
    }

    public static ArgbEvaluator getInstance() {
        return sInstance;
    }

    public Object evaluate(float f, Object startValue, Object endValue) {
        float fraction = f;
        int startInt = ((Integer) startValue).intValue();
        float startA = ((float) ((startInt >> 24) & 255)) / 255.0f;
        int endInt = ((Integer) endValue).intValue();
        float startR = (float) Math.pow((double) (((float) ((startInt >> 16) & 255)) / 255.0f), 2.2d);
        float startG = (float) Math.pow((double) (((float) ((startInt >> 8) & 255)) / 255.0f), 2.2d);
        float startB = (float) Math.pow((double) (((float) (startInt & 255)) / 255.0f), 2.2d);
        float endR = (float) Math.pow((double) (((float) ((endInt >> 16) & 255)) / 255.0f), 2.2d);
        float endG = (float) Math.pow((double) (((float) ((endInt >> 8) & 255)) / 255.0f), 2.2d);
        return Integer.valueOf((Math.round((startA + (fraction * ((((float) ((endInt >> 24) & 255)) / 255.0f) - startA))) * 255.0f) << 24) | (Math.round(((float) Math.pow((double) (startR + (fraction * (endR - startR))), 0.45454545454545453d)) * 255.0f) << 16) | (Math.round(((float) Math.pow((double) (startG + (fraction * (endG - startG))), 0.45454545454545453d)) * 255.0f) << 8) | Math.round(((float) Math.pow((double) (startB + (fraction * (((float) Math.pow((double) (((float) (endInt & 255)) / 255.0f), 2.2d)) - startB))), 0.45454545454545453d)) * 255.0f));
    }
}
