package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.os.Build;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class DoubleBounce extends SpriteContainer {
    public DoubleBounce() {
    }

    public Sprite[] onCreateChild() {
        Sprite sprite;
        Sprite sprite2;
        Sprite[] spriteArr = new Sprite[2];
        new Bounce(this);
        spriteArr[0] = sprite;
        Sprite[] spriteArr2 = spriteArr;
        new Bounce(this);
        spriteArr2[1] = sprite2;
        return spriteArr2;
    }

    public void onChildCreated(Sprite... spriteArr) {
        Sprite[] sprites = spriteArr;
        super.onChildCreated(sprites);
        if (Build.VERSION.SDK_INT >= 24) {
            Sprite animationDelay = sprites[1].setAnimationDelay(1000);
        } else {
            Sprite animationDelay2 = sprites[1].setAnimationDelay(-1000);
        }
    }

    private class Bounce extends CircleSprite {
        final /* synthetic */ DoubleBounce this$0;

        Bounce(DoubleBounce doubleBounce) {
            this.this$0 = doubleBounce;
            setAlpha(153);
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
            return spriteAnimatorBuilder.scale(fractions, fArr3).duration(2000).easeInOut(fractions).build();
        }
    }
}
