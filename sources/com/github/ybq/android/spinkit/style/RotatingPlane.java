package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;

public class RotatingPlane extends RectSprite {
    public RotatingPlane() {
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        setDrawBounds(clipSquare(bounds));
    }

    public ValueAnimator onCreateAnimation() {
        SpriteAnimatorBuilder spriteAnimatorBuilder;
        float[] fractions = {0.0f, 0.5f, 1.0f};
        new SpriteAnimatorBuilder(this);
        Integer[] numArr = new Integer[3];
        numArr[0] = 0;
        Integer[] numArr2 = numArr;
        numArr2[1] = -180;
        Integer[] numArr3 = numArr2;
        numArr3[2] = -180;
        Integer[] numArr4 = new Integer[3];
        numArr4[0] = 0;
        Integer[] numArr5 = numArr4;
        numArr5[1] = 0;
        Integer[] numArr6 = numArr5;
        numArr6[2] = -180;
        return spriteAnimatorBuilder.rotateX(fractions, numArr3).rotateY(fractions, numArr6).duration(1200).easeInOut(fractions).build();
    }
}
