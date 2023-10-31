package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.os.Build;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleLayoutContainer;
import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;

public class Circle extends CircleLayoutContainer {
    public Circle() {
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
        final /* synthetic */ Circle this$0;

        Dot(Circle circle) {
            this.this$0 = circle;
            setScale(0.0f);
        }

        public ValueAnimator onCreateAnimation() {
            SpriteAnimatorBuilder spriteAnimatorBuilder;
            float[] fractions = {0.0f, 0.5f, 1.0f};
            new SpriteAnimatorBuilder(this);
            Float[] fArr = new Float[3];
            fArr[0] = Float.valueOf(0.0f);
            Float[] fArr2 = fArr;
            fArr2[1] = Float.valueOf(1.0f);
            Float[] fArr3 = fArr2;
            fArr3[2] = Float.valueOf(0.0f);
            return spriteAnimatorBuilder.scale(fractions, fArr3).duration(1200).easeInOut(fractions).build();
        }
    }
}
