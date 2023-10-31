package android.support.design.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.GravityCompat;
import android.support.p003v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ForegroundLinearLayout extends LinearLayoutCompat {
    private Drawable foreground;
    boolean foregroundBoundsChanged;
    private int foregroundGravity;
    protected boolean mForegroundInPadding;
    private final Rect overlayBounds;
    private final Rect selfBounds;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ForegroundLinearLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ForegroundLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ForegroundLinearLayout(android.content.Context r14, android.util.AttributeSet r15, int r16) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r3
            r6.<init>(r7, r8, r9)
            r6 = r0
            android.graphics.Rect r7 = new android.graphics.Rect
            r12 = r7
            r7 = r12
            r8 = r12
            r8.<init>()
            r6.selfBounds = r7
            r6 = r0
            android.graphics.Rect r7 = new android.graphics.Rect
            r12 = r7
            r7 = r12
            r8 = r12
            r8.<init>()
            r6.overlayBounds = r7
            r6 = r0
            r7 = 119(0x77, float:1.67E-43)
            r6.foregroundGravity = r7
            r6 = r0
            r7 = 1
            r6.mForegroundInPadding = r7
            r6 = r0
            r7 = 0
            r6.foregroundBoundsChanged = r7
            r6 = r1
            r7 = r2
            int[] r8 = android.support.design.C0064R.styleable.ForegroundLinearLayout
            r9 = r3
            r10 = 0
            r11 = 0
            int[] r11 = new int[r11]
            android.content.res.TypedArray r6 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r6, r7, r8, r9, r10, r11)
            r4 = r6
            r6 = r0
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.ForegroundLinearLayout_android_foregroundGravity
            r9 = r0
            int r9 = r9.foregroundGravity
            int r7 = r7.getInt(r8, r9)
            r6.foregroundGravity = r7
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.ForegroundLinearLayout_android_foreground
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5 = r6
            r6 = r5
            if (r6 == 0) goto L_0x005a
            r6 = r0
            r7 = r5
            r6.setForeground(r7)
        L_0x005a:
            r6 = r0
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.ForegroundLinearLayout_foregroundInsidePadding
            r9 = 1
            boolean r7 = r7.getBoolean(r8, r9)
            r6.mForegroundInPadding = r7
            r6 = r4
            r6.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.internal.ForegroundLinearLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public int getForegroundGravity() {
        return this.foregroundGravity;
    }

    public void setForegroundGravity(int i) {
        Rect padding;
        int foregroundGravity2 = i;
        if (this.foregroundGravity != foregroundGravity2) {
            if ((foregroundGravity2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
                foregroundGravity2 |= GravityCompat.START;
            }
            if ((foregroundGravity2 & 112) == 0) {
                foregroundGravity2 |= 48;
            }
            this.foregroundGravity = foregroundGravity2;
            if (this.foregroundGravity == 119 && this.foreground != null) {
                new Rect();
                boolean padding2 = this.foreground.getPadding(padding);
            }
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        Drawable who = drawable;
        return super.verifyDrawable(who) || who == this.foreground;
    }

    @RequiresApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.foreground != null) {
            this.foreground.jumpToCurrentState();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.foreground != null && this.foreground.isStateful()) {
            boolean state = this.foreground.setState(getDrawableState());
        }
    }

    public void setForeground(Drawable drawable) {
        Rect padding;
        Drawable drawable2 = drawable;
        if (this.foreground != drawable2) {
            if (this.foreground != null) {
                this.foreground.setCallback((Drawable.Callback) null);
                unscheduleDrawable(this.foreground);
            }
            this.foreground = drawable2;
            if (drawable2 != null) {
                setWillNotDraw(false);
                drawable2.setCallback(this);
                if (drawable2.isStateful()) {
                    boolean state = drawable2.setState(getDrawableState());
                }
                if (this.foregroundGravity == 119) {
                    new Rect();
                    boolean padding2 = drawable2.getPadding(padding);
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    public Drawable getForeground() {
        return this.foreground;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int left, int top, int right, int bottom) {
        boolean changed = z;
        super.onLayout(changed, left, top, right, bottom);
        this.foregroundBoundsChanged |= changed;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.foregroundBoundsChanged = true;
    }

    public void draw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        super.draw(canvas2);
        if (this.foreground != null) {
            Drawable foreground2 = this.foreground;
            if (this.foregroundBoundsChanged) {
                this.foregroundBoundsChanged = false;
                Rect selfBounds2 = this.selfBounds;
                Rect overlayBounds2 = this.overlayBounds;
                int w = getRight() - getLeft();
                int h = getBottom() - getTop();
                if (this.mForegroundInPadding) {
                    selfBounds2.set(0, 0, w, h);
                } else {
                    selfBounds2.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
                }
                Gravity.apply(this.foregroundGravity, foreground2.getIntrinsicWidth(), foreground2.getIntrinsicHeight(), selfBounds2, overlayBounds2);
                foreground2.setBounds(overlayBounds2);
            }
            foreground2.draw(canvas2);
        }
    }

    @TargetApi(21)
    @RequiresApi(21)
    public void drawableHotspotChanged(float f, float f2) {
        float x = f;
        float y = f2;
        super.drawableHotspotChanged(x, y);
        if (this.foreground != null) {
            this.foreground.setHotspot(x, y);
        }
    }
}
