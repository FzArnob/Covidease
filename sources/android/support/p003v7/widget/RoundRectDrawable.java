package android.support.p003v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
/* renamed from: android.support.v7.widget.RoundRectDrawable */
class RoundRectDrawable extends Drawable {
    private ColorStateList mBackground;
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private boolean mInsetForPadding = false;
    private boolean mInsetForRadius = true;
    private float mPadding;
    private final Paint mPaint;
    private float mRadius;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff.Mode mTintMode = PorterDuff.Mode.SRC_IN;

    RoundRectDrawable(ColorStateList backgroundColor, float radius) {
        Paint paint;
        RectF rectF;
        Rect rect;
        this.mRadius = radius;
        new Paint(5);
        this.mPaint = paint;
        setBackground(backgroundColor);
        new RectF();
        this.mBoundsF = rectF;
        new Rect();
        this.mBoundsI = rect;
    }

    private void setBackground(ColorStateList colorStateList) {
        ColorStateList color = colorStateList;
        this.mBackground = color == null ? ColorStateList.valueOf(0) : color;
        this.mPaint.setColor(this.mBackground.getColorForState(getState(), this.mBackground.getDefaultColor()));
    }

    /* access modifiers changed from: package-private */
    public void setPadding(float f, boolean z, boolean z2) {
        float padding = f;
        boolean insetForPadding = z;
        boolean insetForRadius = z2;
        if (padding != this.mPadding || this.mInsetForPadding != insetForPadding || this.mInsetForRadius != insetForRadius) {
            this.mPadding = padding;
            this.mInsetForPadding = insetForPadding;
            this.mInsetForRadius = insetForRadius;
            updateBounds((Rect) null);
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public float getPadding() {
        return this.mPadding;
    }

    public void draw(Canvas canvas) {
        boolean clearColorFilter;
        Canvas canvas2 = canvas;
        Paint paint = this.mPaint;
        if (this.mTintFilter == null || paint.getColorFilter() != null) {
            clearColorFilter = false;
        } else {
            ColorFilter colorFilter = paint.setColorFilter(this.mTintFilter);
            clearColorFilter = true;
        }
        canvas2.drawRoundRect(this.mBoundsF, this.mRadius, this.mRadius, paint);
        if (clearColorFilter) {
            ColorFilter colorFilter2 = paint.setColorFilter((ColorFilter) null);
        }
    }

    private void updateBounds(Rect rect) {
        Rect bounds = rect;
        if (bounds == null) {
            bounds = getBounds();
        }
        this.mBoundsF.set((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom);
        this.mBoundsI.set(bounds);
        if (this.mInsetForPadding) {
            float vInset = RoundRectDrawableWithShadow.calculateVerticalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
            this.mBoundsI.inset((int) Math.ceil((double) RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mPadding, this.mRadius, this.mInsetForRadius)), (int) Math.ceil((double) vInset));
            this.mBoundsF.set(this.mBoundsI);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        super.onBoundsChange(bounds);
        updateBounds(bounds);
    }

    public void getOutline(Outline outline) {
        outline.setRoundRect(this.mBoundsI, this.mRadius);
    }

    /* access modifiers changed from: package-private */
    public void setRadius(float f) {
        float radius = f;
        if (radius != this.mRadius) {
            this.mRadius = radius;
            updateBounds((Rect) null);
            invalidateSelf();
        }
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        ColorFilter colorFilter = this.mPaint.setColorFilter(cf);
    }

    public int getOpacity() {
        return -3;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setColor(@Nullable ColorStateList color) {
        setBackground(color);
        invalidateSelf();
    }

    public ColorStateList getColor() {
        return this.mBackground;
    }

    public void setTintList(ColorStateList tint) {
        this.mTint = tint;
        this.mTintFilter = createTintFilter(this.mTint, this.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(PorterDuff.Mode tintMode) {
        this.mTintMode = tintMode;
        this.mTintFilter = createTintFilter(this.mTint, this.mTintMode);
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] stateSet) {
        int newColor = this.mBackground.getColorForState(stateSet, this.mBackground.getDefaultColor());
        boolean colorChanged = newColor != this.mPaint.getColor();
        if (colorChanged) {
            this.mPaint.setColor(newColor);
        }
        if (this.mTint == null || this.mTintMode == null) {
            return colorChanged;
        }
        this.mTintFilter = createTintFilter(this.mTint, this.mTintMode);
        return true;
    }

    public boolean isStateful() {
        return (this.mTint != null && this.mTint.isStateful()) || (this.mBackground != null && this.mBackground.isStateful()) || super.isStateful();
    }

    private PorterDuffColorFilter createTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        ColorStateList tint = colorStateList;
        PorterDuff.Mode tintMode = mode;
        if (tint == null || tintMode == null) {
            return null;
        }
        new PorterDuffColorFilter(tint.getColorForState(getState(), 0), tintMode);
        return porterDuffColorFilter;
    }
}
