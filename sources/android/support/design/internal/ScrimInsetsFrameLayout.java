package android.support.design.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ScrimInsetsFrameLayout extends FrameLayout {
    Drawable insetForeground;
    Rect insets;
    private Rect tempRect;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ScrimInsetsFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ScrimInsetsFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ScrimInsetsFrameLayout(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            android.graphics.Rect r6 = new android.graphics.Rect
            r11 = r6
            r6 = r11
            r7 = r11
            r7.<init>()
            r5.tempRect = r6
            r5 = r1
            r6 = r2
            int[] r7 = android.support.design.C0064R.styleable.ScrimInsetsFrameLayout
            r8 = r3
            int r9 = android.support.design.C0064R.C0068style.Widget_Design_ScrimInsetsFrameLayout
            r10 = 0
            int[] r10 = new int[r10]
            android.content.res.TypedArray r5 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r5, r6, r7, r8, r9, r10)
            r4 = r5
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.ScrimInsetsFrameLayout_insetForeground
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.insetForeground = r6
            r5 = r4
            r5.recycle()
            r5 = r0
            r6 = 1
            r5.setWillNotDraw(r6)
            r5 = r0
            android.support.design.internal.ScrimInsetsFrameLayout$1 r6 = new android.support.design.internal.ScrimInsetsFrameLayout$1
            r11 = r6
            r6 = r11
            r7 = r11
            r8 = r0
            r7.<init>(r8)
            android.support.p000v4.view.ViewCompat.setOnApplyWindowInsetsListener(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.internal.ScrimInsetsFrameLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void draw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        super.draw(canvas2);
        int width = getWidth();
        int height = getHeight();
        if (this.insets != null && this.insetForeground != null) {
            int sc = canvas2.save();
            canvas2.translate((float) getScrollX(), (float) getScrollY());
            this.tempRect.set(0, 0, width, this.insets.top);
            this.insetForeground.setBounds(this.tempRect);
            this.insetForeground.draw(canvas2);
            this.tempRect.set(0, height - this.insets.bottom, width, height);
            this.insetForeground.setBounds(this.tempRect);
            this.insetForeground.draw(canvas2);
            this.tempRect.set(0, this.insets.top, this.insets.left, height - this.insets.bottom);
            this.insetForeground.setBounds(this.tempRect);
            this.insetForeground.draw(canvas2);
            this.tempRect.set(width - this.insets.right, this.insets.top, width, height - this.insets.bottom);
            this.insetForeground.setBounds(this.tempRect);
            this.insetForeground.draw(canvas2);
            canvas2.restoreToCount(sc);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.insetForeground != null) {
            this.insetForeground.setCallback(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.insetForeground != null) {
            this.insetForeground.setCallback((Drawable.Callback) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onInsetsChanged(WindowInsetsCompat insets2) {
    }
}
