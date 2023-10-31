package android.support.design.card;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.design.C0064R;
import android.support.p003v7.widget.CardView;
import android.util.AttributeSet;

public class MaterialCardView extends CardView {
    private final MaterialCardViewHelper cardViewHelper;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaterialCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaterialCardView(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.materialCardViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaterialCardView(android.content.Context r13, android.util.AttributeSet r14, int r15) {
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
            r5 = r1
            r6 = r2
            int[] r7 = android.support.design.C0064R.styleable.MaterialCardView
            r8 = r3
            int r9 = android.support.design.C0064R.C0068style.Widget_MaterialComponents_CardView
            r10 = 0
            int[] r10 = new int[r10]
            android.content.res.TypedArray r5 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r5, r6, r7, r8, r9, r10)
            r4 = r5
            r5 = r0
            android.support.design.card.MaterialCardViewHelper r6 = new android.support.design.card.MaterialCardViewHelper
            r11 = r6
            r6 = r11
            r7 = r11
            r8 = r0
            r7.<init>(r8)
            r5.cardViewHelper = r6
            r5 = r0
            android.support.design.card.MaterialCardViewHelper r5 = r5.cardViewHelper
            r6 = r4
            r5.loadFromAttributes(r6)
            r5 = r4
            r5.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.card.MaterialCardView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setStrokeColor(@ColorInt int strokeColor) {
        this.cardViewHelper.setStrokeColor(strokeColor);
    }

    @ColorInt
    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    public void setStrokeWidth(@Dimension int strokeWidth) {
        this.cardViewHelper.setStrokeWidth(strokeWidth);
    }

    @Dimension
    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    public void setRadius(float radius) {
        super.setRadius(radius);
        this.cardViewHelper.updateForeground();
    }
}
