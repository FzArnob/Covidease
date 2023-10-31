package com.github.ybq.android.spinkit.sprite;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;

public abstract class ShapeSprite extends Sprite {
    private int mBaseColor;
    private Paint mPaint;
    private int mUseColor;

    public abstract void drawShape(Canvas canvas, Paint paint);

    public ShapeSprite() {
        Paint paint;
        setColor(-1);
        new Paint();
        this.mPaint = paint;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mUseColor);
    }

    public void setColor(int color) {
        this.mBaseColor = color;
        updateUseColor();
    }

    public int getColor() {
        return this.mBaseColor;
    }

    public int getUseColor() {
        return this.mUseColor;
    }

    public void setAlpha(int alpha) {
        super.setAlpha(alpha);
        updateUseColor();
    }

    private void updateUseColor() {
        int alpha = getAlpha();
        this.mUseColor = ((this.mBaseColor << 8) >>> 8) | ((((this.mBaseColor >>> 24) * (alpha + (alpha >> 7))) >> 8) << 24);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ColorFilter colorFilter2 = this.mPaint.setColorFilter(colorFilter);
    }

    /* access modifiers changed from: protected */
    public final void drawSelf(Canvas canvas) {
        this.mPaint.setColor(this.mUseColor);
        drawShape(canvas, this.mPaint);
    }
}
