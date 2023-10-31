package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class ChasingDots extends SpriteContainer {
    public ChasingDots() {
    }

    public Sprite[] onCreateChild() {
        Sprite sprite;
        Sprite sprite2;
        Sprite[] spriteArr = new Sprite[2];
        new Dot(this);
        spriteArr[0] = sprite;
        Sprite[] spriteArr2 = spriteArr;
        new Dot(this);
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

    public ValueAnimator onCreateAnimation() {
        SpriteAnimatorBuilder spriteAnimatorBuilder;
        Interpolator interpolator;
        float[] fractions = {0.0f, 1.0f};
        new SpriteAnimatorBuilder(this);
        Integer[] numArr = new Integer[2];
        numArr[0] = 0;
        Integer[] numArr2 = numArr;
        numArr2[1] = 360;
        new LinearInterpolator();
        return spriteAnimatorBuilder.rotate(fractions, numArr2).duration(2000).interpolator(interpolator).build();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        Rect bounds2 = clipSquare(bounds);
        int drawW = (int) (((float) bounds2.width()) * 0.6f);
        getChildAt(0).setDrawBounds(bounds2.right - drawW, bounds2.top, bounds2.right, bounds2.top + drawW);
        getChildAt(1).setDrawBounds(bounds2.right - drawW, bounds2.bottom - drawW, bounds2.right, bounds2.bottom);
    }

    private class Dot extends CircleSprite {
        final /* synthetic */ ChasingDots this$0;

        Dot(ChasingDots chasingDots) {
            this.this$0 = chasingDots;
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
