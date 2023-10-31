package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class WanderingCubes extends SpriteContainer {
    public WanderingCubes() {
    }

    public Sprite[] onCreateChild() {
        Sprite sprite;
        Sprite sprite2;
        Sprite[] spriteArr = new Sprite[2];
        new Cube(this, 0);
        spriteArr[0] = sprite;
        Sprite[] spriteArr2 = spriteArr;
        new Cube(this, 3);
        spriteArr2[1] = sprite2;
        return spriteArr2;
    }

    public void onChildCreated(Sprite... spriteArr) {
        Sprite[] sprites = spriteArr;
        super.onChildCreated(sprites);
        if (Build.VERSION.SDK_INT < 24) {
            Sprite animationDelay = sprites[1].setAnimationDelay(-900);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        Rect bounds2 = clipSquare(bounds);
        super.onBoundsChange(bounds2);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setDrawBounds(bounds2.left, bounds2.top, bounds2.left + (bounds2.width() / 4), bounds2.top + (bounds2.height() / 4));
        }
    }

    private class Cube extends RectSprite {
        int startFrame;
        final /* synthetic */ WanderingCubes this$0;

        public Cube(WanderingCubes wanderingCubes, int startFrame2) {
            this.this$0 = wanderingCubes;
            this.startFrame = startFrame2;
        }

        public ValueAnimator onCreateAnimation() {
            SpriteAnimatorBuilder spriteAnimatorBuilder;
            float[] fractions = {0.0f, 0.25f, 0.5f, 0.51f, 0.75f, 1.0f};
            new SpriteAnimatorBuilder(this);
            Integer[] numArr = new Integer[6];
            numArr[0] = 0;
            Integer[] numArr2 = numArr;
            numArr2[1] = -90;
            Integer[] numArr3 = numArr2;
            numArr3[2] = -179;
            Integer[] numArr4 = numArr3;
            numArr4[3] = -180;
            Integer[] numArr5 = numArr4;
            numArr5[4] = -270;
            Integer[] numArr6 = numArr5;
            numArr6[5] = -360;
            Float[] fArr = new Float[6];
            fArr[0] = Float.valueOf(0.0f);
            Float[] fArr2 = fArr;
            fArr2[1] = Float.valueOf(0.75f);
            Float[] fArr3 = fArr2;
            fArr3[2] = Float.valueOf(0.75f);
            Float[] fArr4 = fArr3;
            fArr4[3] = Float.valueOf(0.75f);
            Float[] fArr5 = fArr4;
            fArr5[4] = Float.valueOf(0.0f);
            Float[] fArr6 = fArr5;
            fArr6[5] = Float.valueOf(0.0f);
            Float[] fArr7 = new Float[6];
            fArr7[0] = Float.valueOf(0.0f);
            Float[] fArr8 = fArr7;
            fArr8[1] = Float.valueOf(0.0f);
            Float[] fArr9 = fArr8;
            fArr9[2] = Float.valueOf(0.75f);
            Float[] fArr10 = fArr9;
            fArr10[3] = Float.valueOf(0.75f);
            Float[] fArr11 = fArr10;
            fArr11[4] = Float.valueOf(0.75f);
            Float[] fArr12 = fArr11;
            fArr12[5] = Float.valueOf(0.0f);
            Float[] fArr13 = new Float[6];
            fArr13[0] = Float.valueOf(1.0f);
            Float[] fArr14 = fArr13;
            fArr14[1] = Float.valueOf(0.5f);
            Float[] fArr15 = fArr14;
            fArr15[2] = Float.valueOf(1.0f);
            Float[] fArr16 = fArr15;
            fArr16[3] = Float.valueOf(1.0f);
            Float[] fArr17 = fArr16;
            fArr17[4] = Float.valueOf(0.5f);
            Float[] fArr18 = fArr17;
            fArr18[5] = Float.valueOf(1.0f);
            SpriteAnimatorBuilder builder = spriteAnimatorBuilder.rotate(fractions, numArr6).translateXPercentage(fractions, fArr6).translateYPercentage(fractions, fArr12).scale(fractions, fArr18).duration(1800).easeInOut(fractions);
            if (Build.VERSION.SDK_INT >= 24) {
                SpriteAnimatorBuilder startFrame2 = builder.startFrame(this.startFrame);
            }
            return builder.build();
        }
    }
}
