package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.AnimationUtils;

public abstract class SpriteContainer extends Sprite {
    private int color;
    private Sprite[] sprites = onCreateChild();

    public abstract Sprite[] onCreateChild();

    public SpriteContainer() {
        initCallBack();
        onChildCreated(this.sprites);
    }

    private void initCallBack() {
        if (this.sprites != null) {
            Sprite[] spriteArr = this.sprites;
            int length = spriteArr.length;
            for (int i = 0; i < length; i++) {
                spriteArr[i].setCallback(this);
            }
        }
    }

    public void onChildCreated(Sprite... sprites2) {
    }

    public int getChildCount() {
        return this.sprites == null ? 0 : this.sprites.length;
    }

    public Sprite getChildAt(int index) {
        return this.sprites == null ? null : this.sprites[index];
    }

    public void setColor(int i) {
        int color2 = i;
        this.color = color2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            getChildAt(i2).setColor(color2);
        }
    }

    public int getColor() {
        return this.color;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.draw(canvas2);
        drawChild(canvas2);
    }

    public void drawChild(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.sprites != null) {
            Sprite[] spriteArr = this.sprites;
            int length = spriteArr.length;
            for (int i = 0; i < length; i++) {
                Sprite sprite = spriteArr[i];
                int count = canvas2.save();
                sprite.draw(canvas2);
                canvas2.restoreToCount(count);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawSelf(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        Sprite[] spriteArr = this.sprites;
        int length = spriteArr.length;
        for (int i = 0; i < length; i++) {
            spriteArr[i].setBounds(bounds);
        }
    }

    public void start() {
        super.start();
        AnimationUtils.start(this.sprites);
    }

    public void stop() {
        super.stop();
        AnimationUtils.stop(this.sprites);
    }

    public boolean isRunning() {
        return AnimationUtils.isRunning(this.sprites) || super.isRunning();
    }

    public ValueAnimator onCreateAnimation() {
        return null;
    }
}
