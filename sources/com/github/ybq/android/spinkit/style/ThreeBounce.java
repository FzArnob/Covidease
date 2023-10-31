package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;

public class ThreeBounce extends SpriteContainer {
    public ThreeBounce() {
    }

    public Sprite[] onCreateChild() {
        Sprite sprite;
        Sprite sprite2;
        Sprite sprite3;
        Sprite[] spriteArr = new Sprite[3];
        new Bounce(this);
        spriteArr[0] = sprite;
        Sprite[] spriteArr2 = spriteArr;
        new Bounce(this);
        spriteArr2[1] = sprite2;
        Sprite[] spriteArr3 = spriteArr2;
        new Bounce(this);
        spriteArr3[2] = sprite3;
        return spriteArr3;
    }

    public void onChildCreated(Sprite... spriteArr) {
        Sprite[] sprites = spriteArr;
        super.onChildCreated(sprites);
        Sprite animationDelay = sprites[1].setAnimationDelay(ComponentConstants.TEXTBOX_PREFERRED_WIDTH);
        Sprite animationDelay2 = sprites[2].setAnimationDelay(ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        Rect bounds2 = clipSquare(bounds);
        int radius = bounds2.width() / 8;
        int top = bounds2.centerY() - radius;
        int bottom = bounds2.centerY() + radius;
        for (int i = 0; i < getChildCount(); i++) {
            int left = ((bounds2.width() * i) / 3) + bounds2.left;
            getChildAt(i).setDrawBounds(left, top, left + (radius * 2), bottom);
        }
    }

    private class Bounce extends CircleSprite {
        final /* synthetic */ ThreeBounce this$0;

        Bounce(ThreeBounce threeBounce) {
            this.this$0 = threeBounce;
            setScale(0.0f);
        }

        public ValueAnimator onCreateAnimation() {
            SpriteAnimatorBuilder spriteAnimatorBuilder;
            float[] fractions = {0.0f, 0.4f, 0.8f, 1.0f};
            new SpriteAnimatorBuilder(this);
            Float[] fArr = new Float[4];
            fArr[0] = Float.valueOf(0.0f);
            Float[] fArr2 = fArr;
            fArr2[1] = Float.valueOf(1.0f);
            Float[] fArr3 = fArr2;
            fArr3[2] = Float.valueOf(0.0f);
            Float[] fArr4 = fArr3;
            fArr4[3] = Float.valueOf(0.0f);
            return spriteAnimatorBuilder.scale(fractions, fArr4).duration(1400).easeInOut(fractions).build();
        }
    }
}
