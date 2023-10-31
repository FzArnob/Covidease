package com.github.ybq.android.spinkit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.github.ybq.android.spinkit.sprite.Sprite;

public class SpinKitView extends ProgressBar {
    private int mColor;
    private Sprite mSprite;
    private Style mStyle;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SpinKitView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SpinKitView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.SpinKitViewStyle);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SpinKitView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.SpinKitView);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @android.annotation.TargetApi(21)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SpinKitView(android.content.Context r12, android.util.AttributeSet r13, int r14, int r15) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r3
            r10 = r4
            r6.<init>(r7, r8, r9, r10)
            r6 = r1
            r7 = r2
            int[] r8 = com.github.ybq.android.spinkit.R.styleable.SpinKitView
            r9 = r3
            r10 = r4
            android.content.res.TypedArray r6 = r6.obtainStyledAttributes(r7, r8, r9, r10)
            r5 = r6
            r6 = r0
            com.github.ybq.android.spinkit.Style[] r7 = com.github.ybq.android.spinkit.Style.values()
            r8 = r5
            int r9 = com.github.ybq.android.spinkit.R.styleable.SpinKitView_SpinKit_Style
            r10 = 0
            int r8 = r8.getInt(r9, r10)
            r7 = r7[r8]
            r6.mStyle = r7
            r6 = r0
            r7 = r5
            int r8 = com.github.ybq.android.spinkit.R.styleable.SpinKitView_SpinKit_Color
            r9 = -1
            int r7 = r7.getColor(r8, r9)
            r6.mColor = r7
            r6 = r5
            r6.recycle()
            r6 = r0
            r6.init()
            r6 = r0
            r7 = 1
            r6.setIndeterminate(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.ybq.android.spinkit.SpinKitView.<init>(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    private void init() {
        Sprite sprite = SpriteFactory.create(this.mStyle);
        sprite.setColor(this.mColor);
        setIndeterminateDrawable(sprite);
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        Throwable th;
        Drawable d = drawable;
        if (!(d instanceof Sprite)) {
            Throwable th2 = th;
            new IllegalArgumentException("this d must be instanceof Sprite");
            throw th2;
        }
        setIndeterminateDrawable((Sprite) d);
    }

    public void setIndeterminateDrawable(Sprite sprite) {
        Sprite d = sprite;
        super.setIndeterminateDrawable(d);
        this.mSprite = d;
        if (this.mSprite.getColor() == 0) {
            this.mSprite.setColor(this.mColor);
        }
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        if (getVisibility() == 0) {
            this.mSprite.start();
        }
    }

    public Sprite getIndeterminateDrawable() {
        return this.mSprite;
    }

    public void setColor(int i) {
        int color = i;
        this.mColor = color;
        if (this.mSprite != null) {
            this.mSprite.setColor(color);
        }
        invalidate();
    }

    public void unscheduleDrawable(Drawable drawable) {
        Drawable who = drawable;
        super.unscheduleDrawable(who);
        if (who instanceof Sprite) {
            ((Sprite) who).stop();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        boolean hasWindowFocus = z;
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && this.mSprite != null && getVisibility() == 0) {
            this.mSprite.start();
        }
    }

    public void onScreenStateChanged(int i) {
        int screenState = i;
        super.onScreenStateChanged(screenState);
        if (screenState == 0 && this.mSprite != null) {
            this.mSprite.stop();
        }
    }
}
