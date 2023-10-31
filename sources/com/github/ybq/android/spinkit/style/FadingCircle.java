package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.os.Build;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleLayoutContainer;
import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;

public class FadingCircle extends CircleLayoutContainer {
    public FadingCircle() {
    }

    public Sprite[] onCreateChild() {
        Dot dot;
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            new Dot(this);
            dots[i] = dot;
            if (Build.VERSION.SDK_INT >= 24) {
                Sprite animationDelay = dots[i].setAnimationDelay(100 * i);
            } else {
                Sprite animationDelay2 = dots[i].setAnimationDelay((100 * i) - 1200);
            }
        }
        return dots;
    }

    private class Dot extends CircleSprite {
        final /* synthetic */ FadingCircle this$0;

        Dot(FadingCircle fadingCircle) {
            this.this$0 = fadingCircle;
            setAlpha(0);
        }

        public ValueAnimator onCreateAnimation() {
            SpriteAnimatorBuilder spriteAnimatorBuilder;
            float[] fractions = {0.0f, 0.39f, 0.4f, 1.0f};
            new SpriteAnimatorBuilder(this);
            Integer[] numArr = new Integer[4];
            numArr[0] = 0;
            Integer[] numArr2 = numArr;
            numArr2[1] = 0;
            Integer[] numArr3 = numArr2;
            numArr3[2] = 255;
            Integer[] numArr4 = numArr3;
            numArr4[3] = 0;
            return spriteAnimatorBuilder.alpha(fractions, numArr4).duration(1200).easeInOut(fractions).build();
        }
    }
}
