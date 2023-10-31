package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleSprite;

public class Pulse extends CircleSprite {
    public Pulse() {
        setScale(0.0f);
    }

    public ValueAnimator onCreateAnimation() {
        SpriteAnimatorBuilder spriteAnimatorBuilder;
        float[] fractions = {0.0f, 1.0f};
        new SpriteAnimatorBuilder(this);
        Float[] fArr = new Float[2];
        fArr[0] = Float.valueOf(0.0f);
        Float[] fArr2 = fArr;
        fArr2[1] = Float.valueOf(1.0f);
        Integer[] numArr = new Integer[2];
        numArr[0] = 255;
        Integer[] numArr2 = numArr;
        numArr2[1] = 0;
        return spriteAnimatorBuilder.scale(fractions, fArr2).alpha(fractions, numArr2).duration(1000).easeInOut(fractions).build();
    }
}
