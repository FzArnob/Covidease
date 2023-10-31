package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.animation.interpolator.KeyFrameInterpolator;
import com.github.ybq.android.spinkit.sprite.RingSprite;

public class PulseRing extends RingSprite {
    public PulseRing() {
        setScale(0.0f);
    }

    public ValueAnimator onCreateAnimation() {
        SpriteAnimatorBuilder spriteAnimatorBuilder;
        float[] fractions = {0.0f, 0.7f, 1.0f};
        new SpriteAnimatorBuilder(this);
        Float[] fArr = new Float[3];
        fArr[0] = Float.valueOf(0.0f);
        Float[] fArr2 = fArr;
        fArr2[1] = Float.valueOf(1.0f);
        Float[] fArr3 = fArr2;
        fArr3[2] = Float.valueOf(1.0f);
        Integer[] numArr = new Integer[3];
        numArr[0] = 255;
        Integer[] numArr2 = numArr;
        numArr2[1] = 178;
        Integer[] numArr3 = numArr2;
        numArr3[2] = 0;
        return spriteAnimatorBuilder.scale(fractions, fArr3).alpha(fractions, numArr3).duration(1000).interpolator(KeyFrameInterpolator.pathInterpolator(0.21f, 0.53f, 0.56f, 0.8f, fractions)).build();
    }
}
