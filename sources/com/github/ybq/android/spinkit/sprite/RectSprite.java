package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public class RectSprite extends ShapeSprite {
    public RectSprite() {
    }

    public ValueAnimator onCreateAnimation() {
        return null;
    }

    public void drawShape(Canvas canvas, Paint paint) {
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        if (getDrawBounds() != null) {
            canvas2.drawRect(getDrawBounds(), paint2);
        }
    }
}
