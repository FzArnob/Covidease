package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;
import org.shaded.apache.http.HttpStatus;

public class CubeGrid extends SpriteContainer {
    public CubeGrid() {
    }

    public Sprite[] onCreateChild() {
        GridItem gridItem;
        int[] delays = {200, HttpStatus.SC_MULTIPLE_CHOICES, HttpStatus.SC_BAD_REQUEST, 100, 200, HttpStatus.SC_MULTIPLE_CHOICES, 0, 100, 200};
        GridItem[] gridItems = new GridItem[9];
        for (int i = 0; i < gridItems.length; i++) {
            new GridItem(this, (C15511) null);
            gridItems[i] = gridItem;
            Sprite animationDelay = gridItems[i].setAnimationDelay(delays[i]);
        }
        return gridItems;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        Rect bounds2 = clipSquare(bounds);
        int width = (int) (((float) bounds2.width()) * 0.33f);
        int height = (int) (((float) bounds2.height()) * 0.33f);
        for (int i = 0; i < getChildCount(); i++) {
            int l = bounds2.left + ((i % 3) * width);
            int t = bounds2.top + ((i / 3) * height);
            getChildAt(i).setDrawBounds(l, t, l + width, t + height);
        }
    }

    private class GridItem extends RectSprite {
        final /* synthetic */ CubeGrid this$0;

        private GridItem(CubeGrid cubeGrid) {
            this.this$0 = cubeGrid;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ GridItem(CubeGrid x0, C15511 r7) {
            this(x0);
            C15511 r2 = r7;
        }

        public ValueAnimator onCreateAnimation() {
            SpriteAnimatorBuilder spriteAnimatorBuilder;
            float[] fractions = {0.0f, 0.35f, 0.7f, 1.0f};
            new SpriteAnimatorBuilder(this);
            Float[] fArr = new Float[4];
            fArr[0] = Float.valueOf(1.0f);
            Float[] fArr2 = fArr;
            fArr2[1] = Float.valueOf(0.0f);
            Float[] fArr3 = fArr2;
            fArr3[2] = Float.valueOf(1.0f);
            Float[] fArr4 = fArr3;
            fArr4[3] = Float.valueOf(1.0f);
            return spriteAnimatorBuilder.scale(fractions, fArr4).duration(1300).easeInOut(fractions).build();
        }
    }
}
