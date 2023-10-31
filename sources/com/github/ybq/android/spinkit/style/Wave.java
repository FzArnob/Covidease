package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class Wave extends SpriteContainer {
    public Wave() {
    }

    public Sprite[] onCreateChild() {
        WaveItem waveItem;
        WaveItem[] waveItems = new WaveItem[5];
        for (int i = 0; i < waveItems.length; i++) {
            new WaveItem(this);
            waveItems[i] = waveItem;
            if (Build.VERSION.SDK_INT >= 24) {
                Sprite animationDelay = waveItems[i].setAnimationDelay(600 + (i * 100));
            } else {
                Sprite animationDelay2 = waveItems[i].setAnimationDelay(-1200 + (i * 100));
            }
        }
        return waveItems;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        Rect bounds2 = clipSquare(bounds);
        int rw = bounds2.width() / getChildCount();
        int width = ((bounds2.width() / 5) * 3) / 5;
        for (int i = 0; i < getChildCount(); i++) {
            Sprite sprite = getChildAt(i);
            int l = bounds2.left + (i * rw) + (rw / 5);
            sprite.setDrawBounds(l, bounds2.top, l + width, bounds2.bottom);
        }
    }

    private class WaveItem extends RectSprite {
        final /* synthetic */ Wave this$0;

        WaveItem(Wave wave) {
            this.this$0 = wave;
            setScaleY(0.4f);
        }

        public ValueAnimator onCreateAnimation() {
            SpriteAnimatorBuilder spriteAnimatorBuilder;
            float[] fractions = {0.0f, 0.2f, 0.4f, 1.0f};
            new SpriteAnimatorBuilder(this);
            Float[] fArr = new Float[4];
            fArr[0] = Float.valueOf(0.4f);
            Float[] fArr2 = fArr;
            fArr2[1] = Float.valueOf(1.0f);
            Float[] fArr3 = fArr2;
            fArr3[2] = Float.valueOf(0.4f);
            Float[] fArr4 = fArr3;
            fArr4[3] = Float.valueOf(0.4f);
            return spriteAnimatorBuilder.scaleY(fractions, fArr4).duration(1200).easeInOut(fractions).build();
        }
    }
}
