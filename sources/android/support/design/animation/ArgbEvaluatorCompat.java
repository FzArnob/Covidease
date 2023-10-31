package android.support.design.animation;

import android.animation.TypeEvaluator;

public class ArgbEvaluatorCompat implements TypeEvaluator<Integer> {
    private static final ArgbEvaluatorCompat instance;

    public ArgbEvaluatorCompat() {
    }

    static {
        ArgbEvaluatorCompat argbEvaluatorCompat;
        new ArgbEvaluatorCompat();
        instance = argbEvaluatorCompat;
    }

    public static ArgbEvaluatorCompat getInstance() {
        return instance;
    }

    public Integer evaluate(float f, Integer startValue, Integer endValue) {
        float fraction = f;
        int startInt = startValue.intValue();
        float startA = ((float) ((startInt >> 24) & 255)) / 255.0f;
        int endInt = endValue.intValue();
        float startR = (float) Math.pow((double) (((float) ((startInt >> 16) & 255)) / 255.0f), 2.2d);
        float startG = (float) Math.pow((double) (((float) ((startInt >> 8) & 255)) / 255.0f), 2.2d);
        float startB = (float) Math.pow((double) (((float) (startInt & 255)) / 255.0f), 2.2d);
        float endR = (float) Math.pow((double) (((float) ((endInt >> 16) & 255)) / 255.0f), 2.2d);
        float endG = (float) Math.pow((double) (((float) ((endInt >> 8) & 255)) / 255.0f), 2.2d);
        return Integer.valueOf((Math.round((startA + (fraction * ((((float) ((endInt >> 24) & 255)) / 255.0f) - startA))) * 255.0f) << 24) | (Math.round(((float) Math.pow((double) (startR + (fraction * (endR - startR))), 0.45454545454545453d)) * 255.0f) << 16) | (Math.round(((float) Math.pow((double) (startG + (fraction * (endG - startG))), 0.45454545454545453d)) * 255.0f) << 8) | Math.round(((float) Math.pow((double) (startB + (fraction * (((float) Math.pow((double) (((float) (endInt & 255)) / 255.0f), 2.2d)) - startB))), 0.45454545454545453d)) * 255.0f));
    }
}
