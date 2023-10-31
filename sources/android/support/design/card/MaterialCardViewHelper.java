package android.support.design.card;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.RestrictTo;
import android.support.design.C0064R;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class MaterialCardViewHelper {
    private static final int DEFAULT_STROKE_VALUE = -1;
    private final MaterialCardView materialCardView;
    private int strokeColor;
    private int strokeWidth;

    public MaterialCardViewHelper(MaterialCardView card) {
        this.materialCardView = card;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        TypedArray attributes = typedArray;
        this.strokeColor = attributes.getColor(C0064R.styleable.MaterialCardView_strokeColor, -1);
        this.strokeWidth = attributes.getDimensionPixelSize(C0064R.styleable.MaterialCardView_strokeWidth, 0);
        updateForeground();
        adjustContentPadding();
    }

    /* access modifiers changed from: package-private */
    public void setStrokeColor(@ColorInt int strokeColor2) {
        this.strokeColor = strokeColor2;
        updateForeground();
    }

    /* access modifiers changed from: package-private */
    @ColorInt
    public int getStrokeColor() {
        return this.strokeColor;
    }

    /* access modifiers changed from: package-private */
    public void setStrokeWidth(@Dimension int strokeWidth2) {
        this.strokeWidth = strokeWidth2;
        updateForeground();
        adjustContentPadding();
    }

    /* access modifiers changed from: package-private */
    @Dimension
    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    /* access modifiers changed from: package-private */
    public void updateForeground() {
        this.materialCardView.setForeground(createForegroundDrawable());
    }

    private Drawable createForegroundDrawable() {
        GradientDrawable gradientDrawable;
        new GradientDrawable();
        GradientDrawable fgDrawable = gradientDrawable;
        fgDrawable.setCornerRadius(this.materialCardView.getRadius());
        if (this.strokeColor != -1) {
            fgDrawable.setStroke(this.strokeWidth, this.strokeColor);
        }
        return fgDrawable;
    }

    private void adjustContentPadding() {
        this.materialCardView.setContentPadding(this.materialCardView.getContentPaddingLeft() + this.strokeWidth, this.materialCardView.getContentPaddingTop() + this.strokeWidth, this.materialCardView.getContentPaddingRight() + this.strokeWidth, this.materialCardView.getContentPaddingBottom() + this.strokeWidth);
    }
}
