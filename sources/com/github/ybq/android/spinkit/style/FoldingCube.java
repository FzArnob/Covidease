package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;
import org.shaded.apache.http.HttpStatus;

public class FoldingCube extends SpriteContainer {
    private boolean wrapContent = false;

    public FoldingCube() {
    }

    public Sprite[] onCreateChild() {
        Cube cube;
        Cube[] cubes = new Cube[4];
        for (int i = 0; i < cubes.length; i++) {
            new Cube(this);
            cubes[i] = cube;
            if (Build.VERSION.SDK_INT >= 24) {
                Sprite animationDelay = cubes[i].setAnimationDelay(HttpStatus.SC_MULTIPLE_CHOICES * i);
            } else {
                Sprite animationDelay2 = cubes[i].setAnimationDelay((HttpStatus.SC_MULTIPLE_CHOICES * i) - 1200);
            }
        }
        return cubes;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect rect2;
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        Rect bounds2 = clipSquare(bounds);
        int size = Math.min(bounds2.width(), bounds2.height());
        if (this.wrapContent) {
            size = (int) Math.sqrt((double) ((size * size) / 2));
            int oW = (bounds2.width() - size) / 2;
            int oH = (bounds2.height() - size) / 2;
            new Rect(bounds2.left + oW, bounds2.top + oH, bounds2.right - oW, bounds2.bottom - oH);
            bounds2 = rect2;
        }
        int px = bounds2.left + (size / 2) + 1;
        int py = bounds2.top + (size / 2) + 1;
        for (int i = 0; i < getChildCount(); i++) {
            Sprite sprite = getChildAt(i);
            sprite.setDrawBounds(bounds2.left, bounds2.top, px, py);
            sprite.setPivotX((float) sprite.getDrawBounds().right);
            sprite.setPivotY((float) sprite.getDrawBounds().bottom);
        }
    }

    public void drawChild(Canvas canvas) {
        Canvas canvas2 = canvas;
        Rect bounds = clipSquare(getBounds());
        for (int i = 0; i < getChildCount(); i++) {
            int count = canvas2.save();
            canvas2.rotate((float) (45 + (i * 90)), (float) bounds.centerX(), (float) bounds.centerY());
            getChildAt(i).draw(canvas2);
            canvas2.restoreToCount(count);
        }
    }

    private class Cube extends RectSprite {
        final /* synthetic */ FoldingCube this$0;

        Cube(FoldingCube foldingCube) {
            this.this$0 = foldingCube;
            setAlpha(0);
            setRotateX(-180);
        }

        public ValueAnimator onCreateAnimation() {
            SpriteAnimatorBuilder spriteAnimatorBuilder;
            Interpolator interpolator;
            float[] fractions = {0.0f, 0.1f, 0.25f, 0.75f, 0.9f, 1.0f};
            new SpriteAnimatorBuilder(this);
            Integer[] numArr = new Integer[6];
            numArr[0] = 0;
            Integer[] numArr2 = numArr;
            numArr2[1] = 0;
            Integer[] numArr3 = numArr2;
            numArr3[2] = 255;
            Integer[] numArr4 = numArr3;
            numArr4[3] = 255;
            Integer[] numArr5 = numArr4;
            numArr5[4] = 0;
            Integer[] numArr6 = numArr5;
            numArr6[5] = 0;
            Integer[] numArr7 = new Integer[6];
            numArr7[0] = -180;
            Integer[] numArr8 = numArr7;
            numArr8[1] = -180;
            Integer[] numArr9 = numArr8;
            numArr9[2] = 0;
            Integer[] numArr10 = numArr9;
            numArr10[3] = 0;
            Integer[] numArr11 = numArr10;
            numArr11[4] = 0;
            Integer[] numArr12 = numArr11;
            numArr12[5] = 0;
            Integer[] numArr13 = new Integer[6];
            numArr13[0] = 0;
            Integer[] numArr14 = numArr13;
            numArr14[1] = 0;
            Integer[] numArr15 = numArr14;
            numArr15[2] = 0;
            Integer[] numArr16 = numArr15;
            numArr16[3] = 0;
            Integer[] numArr17 = numArr16;
            numArr17[4] = 180;
            Integer[] numArr18 = numArr17;
            numArr18[5] = 180;
            new LinearInterpolator();
            return spriteAnimatorBuilder.alpha(fractions, numArr6).rotateX(fractions, numArr12).rotateY(fractions, numArr18).duration(2400).interpolator(interpolator).build();
        }
    }
}
