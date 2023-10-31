package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public class RingSprite extends ShapeSprite {
    public RingSprite() {
    }

    public void drawShape(Canvas canvas, Paint paint) {
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        if (getDrawBounds() != null) {
            paint2.setStyle(Paint.Style.STROKE);
            int radius = Math.min(getDrawBounds().width(), getDrawBounds().height()) / 2;
            paint2.setStrokeWidth((float) (radius / 12));
            canvas2.drawCircle((float) getDrawBounds().centerX(), (float) getDrawBounds().centerY(), (float) radius, paint2);
        }
    }

    public ValueAnimator onCreateAnimation() {
        return null;
    }
}
