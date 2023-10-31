package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public class CircleSprite extends ShapeSprite {
    public CircleSprite() {
    }

    public ValueAnimator onCreateAnimation() {
        return null;
    }

    public void drawShape(Canvas canvas, Paint paint) {
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        if (getDrawBounds() != null) {
            canvas2.drawCircle((float) getDrawBounds().centerX(), (float) getDrawBounds().centerY(), (float) (Math.min(getDrawBounds().width(), getDrawBounds().height()) / 2), paint2);
        }
    }
}
