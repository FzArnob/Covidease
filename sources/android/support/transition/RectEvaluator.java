package android.support.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

class RectEvaluator implements TypeEvaluator<Rect> {
    private Rect mRect;

    RectEvaluator() {
    }

    RectEvaluator(Rect reuseRect) {
        this.mRect = reuseRect;
    }

    public Rect evaluate(float f, Rect rect, Rect rect2) {
        Rect rect3;
        float fraction = f;
        Rect startValue = rect;
        Rect endValue = rect2;
        int left = startValue.left + ((int) (((float) (endValue.left - startValue.left)) * fraction));
        int top = startValue.top + ((int) (((float) (endValue.top - startValue.top)) * fraction));
        int right = startValue.right + ((int) (((float) (endValue.right - startValue.right)) * fraction));
        int bottom = startValue.bottom + ((int) (((float) (endValue.bottom - startValue.bottom)) * fraction));
        if (this.mRect == null) {
            new Rect(left, top, right, bottom);
            return rect3;
        }
        this.mRect.set(left, top, right, bottom);
        return this.mRect;
    }
}
