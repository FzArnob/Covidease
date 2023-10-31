package android.support.p003v7.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.p003v7.cardview.C0429R;

/* renamed from: android.support.v7.widget.RoundRectDrawableWithShadow */
class RoundRectDrawableWithShadow extends Drawable {
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    private static final float SHADOW_MULTIPLIER = 1.5f;
    static RoundRectHelper sRoundRectHelper;
    private boolean mAddPaddingForCorners = true;
    private ColorStateList mBackground;
    private final RectF mCardBounds;
    private float mCornerRadius;
    private Paint mCornerShadowPaint;
    private Path mCornerShadowPath;
    private boolean mDirty = true;
    private Paint mEdgeShadowPaint;
    private final int mInsetShadow;
    private Paint mPaint;
    private boolean mPrintedShadowClipWarning = false;
    private float mRawMaxShadowSize;
    private float mRawShadowSize;
    private final int mShadowEndColor;
    private float mShadowSize;
    private final int mShadowStartColor;

    /* renamed from: android.support.v7.widget.RoundRectDrawableWithShadow$RoundRectHelper */
    interface RoundRectHelper {
        void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint);
    }

    RoundRectDrawableWithShadow(Resources resources, ColorStateList backgroundColor, float radius, float shadowSize, float maxShadowSize) {
        Paint paint;
        Paint paint2;
        RectF rectF;
        Paint paint3;
        Resources resources2 = resources;
        this.mShadowStartColor = resources2.getColor(C0429R.color.cardview_shadow_start_color);
        this.mShadowEndColor = resources2.getColor(C0429R.color.cardview_shadow_end_color);
        this.mInsetShadow = resources2.getDimensionPixelSize(C0429R.dimen.cardview_compat_inset_shadow);
        new Paint(5);
        this.mPaint = paint;
        setBackground(backgroundColor);
        new Paint(5);
        this.mCornerShadowPaint = paint2;
        this.mCornerShadowPaint.setStyle(Paint.Style.FILL);
        this.mCornerRadius = (float) ((int) (radius + 0.5f));
        new RectF();
        this.mCardBounds = rectF;
        new Paint(this.mCornerShadowPaint);
        this.mEdgeShadowPaint = paint3;
        this.mEdgeShadowPaint.setAntiAlias(false);
        setShadowSize(shadowSize, maxShadowSize);
    }

    private void setBackground(ColorStateList colorStateList) {
        ColorStateList color = colorStateList;
        this.mBackground = color == null ? ColorStateList.valueOf(0) : color;
        this.mPaint.setColor(this.mBackground.getColorForState(getState(), this.mBackground.getDefaultColor()));
    }

    private int toEven(float value) {
        int i = (int) (value + 0.5f);
        if (i % 2 == 1) {
            return i - 1;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void setAddPaddingForCorners(boolean addPaddingForCorners) {
        this.mAddPaddingForCorners = addPaddingForCorners;
        invalidateSelf();
    }

    public void setAlpha(int i) {
        int alpha = i;
        this.mPaint.setAlpha(alpha);
        this.mCornerShadowPaint.setAlpha(alpha);
        this.mEdgeShadowPaint.setAlpha(alpha);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mDirty = true;
    }

    private void setShadowSize(float f, float f2) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        float shadowSize = f;
        float maxShadowSize = f2;
        if (shadowSize < 0.0f) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Invalid shadow size ").append(shadowSize).append(". Must be >= 0").toString());
            throw th3;
        } else if (maxShadowSize < 0.0f) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid max shadow size ").append(maxShadowSize).append(". Must be >= 0").toString());
            throw th4;
        } else {
            float shadowSize2 = (float) toEven(shadowSize);
            float maxShadowSize2 = (float) toEven(maxShadowSize);
            if (shadowSize2 > maxShadowSize2) {
                shadowSize2 = maxShadowSize2;
                if (!this.mPrintedShadowClipWarning) {
                    this.mPrintedShadowClipWarning = true;
                }
            }
            if (this.mRawShadowSize != shadowSize2 || this.mRawMaxShadowSize != maxShadowSize2) {
                this.mRawShadowSize = shadowSize2;
                this.mRawMaxShadowSize = maxShadowSize2;
                this.mShadowSize = (float) ((int) ((shadowSize2 * SHADOW_MULTIPLIER) + ((float) this.mInsetShadow) + 0.5f));
                this.mDirty = true;
                invalidateSelf();
            }
        }
    }

    public boolean getPadding(Rect padding) {
        int vOffset = (int) Math.ceil((double) calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        int hOffset = (int) Math.ceil((double) calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        padding.set(hOffset, vOffset, hOffset, vOffset);
        return true;
    }

    static float calculateVerticalPadding(float f, float f2, boolean addPaddingForCorners) {
        float maxShadowSize = f;
        float cornerRadius = f2;
        if (addPaddingForCorners) {
            return (float) (((double) (maxShadowSize * SHADOW_MULTIPLIER)) + ((1.0d - COS_45) * ((double) cornerRadius)));
        }
        return maxShadowSize * SHADOW_MULTIPLIER;
    }

    static float calculateHorizontalPadding(float f, float f2, boolean addPaddingForCorners) {
        float maxShadowSize = f;
        float cornerRadius = f2;
        if (addPaddingForCorners) {
            return (float) (((double) maxShadowSize) + ((1.0d - COS_45) * ((double) cornerRadius)));
        }
        return maxShadowSize;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] stateSet) {
        int newColor = this.mBackground.getColorForState(stateSet, this.mBackground.getDefaultColor());
        if (this.mPaint.getColor() == newColor) {
            return false;
        }
        this.mPaint.setColor(newColor);
        this.mDirty = true;
        invalidateSelf();
        return true;
    }

    public boolean isStateful() {
        return (this.mBackground != null && this.mBackground.isStateful()) || super.isStateful();
    }

    public void setColorFilter(ColorFilter cf) {
        ColorFilter colorFilter = this.mPaint.setColorFilter(cf);
    }

    public int getOpacity() {
        return -3;
    }

    /* access modifiers changed from: package-private */
    public void setCornerRadius(float f) {
        Throwable th;
        StringBuilder sb;
        float radius = f;
        if (radius < 0.0f) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid radius ").append(radius).append(". Must be >= 0").toString());
            throw th2;
        }
        float radius2 = (float) ((int) (radius + 0.5f));
        if (this.mCornerRadius != radius2) {
            this.mCornerRadius = radius2;
            this.mDirty = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.mDirty) {
            buildComponents(getBounds());
            this.mDirty = false;
        }
        canvas2.translate(0.0f, this.mRawShadowSize / 2.0f);
        drawShadow(canvas2);
        canvas2.translate(0.0f, (-this.mRawShadowSize) / 2.0f);
        sRoundRectHelper.drawRoundRect(canvas2, this.mCardBounds, this.mCornerRadius, this.mPaint);
    }

    private void drawShadow(Canvas canvas) {
        Canvas canvas2 = canvas;
        float edgeShadowTop = (-this.mCornerRadius) - this.mShadowSize;
        float inset = this.mCornerRadius + ((float) this.mInsetShadow) + (this.mRawShadowSize / 2.0f);
        boolean drawHorizontalEdges = this.mCardBounds.width() - (2.0f * inset) > 0.0f;
        boolean drawVerticalEdges = this.mCardBounds.height() - (2.0f * inset) > 0.0f;
        int saved = canvas2.save();
        canvas2.translate(this.mCardBounds.left + inset, this.mCardBounds.top + inset);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas2.drawRect(0.0f, edgeShadowTop, this.mCardBounds.width() - (2.0f * inset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas2.restoreToCount(saved);
        int saved2 = canvas2.save();
        canvas2.translate(this.mCardBounds.right - inset, this.mCardBounds.bottom - inset);
        canvas2.rotate(180.0f);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas2.drawRect(0.0f, edgeShadowTop, this.mCardBounds.width() - (2.0f * inset), (-this.mCornerRadius) + this.mShadowSize, this.mEdgeShadowPaint);
        }
        canvas2.restoreToCount(saved2);
        int saved3 = canvas2.save();
        canvas2.translate(this.mCardBounds.left + inset, this.mCardBounds.bottom - inset);
        canvas2.rotate(270.0f);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas2.drawRect(0.0f, edgeShadowTop, this.mCardBounds.height() - (2.0f * inset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas2.restoreToCount(saved3);
        int saved4 = canvas2.save();
        canvas2.translate(this.mCardBounds.right - inset, this.mCardBounds.top + inset);
        canvas2.rotate(90.0f);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas2.drawRect(0.0f, edgeShadowTop, this.mCardBounds.height() - (2.0f * inset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas2.restoreToCount(saved4);
    }

    private void buildShadowCorners() {
        RectF rectF;
        RectF rectF2;
        Shader shader;
        Shader shader2;
        Path path;
        new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        RectF innerBounds = rectF;
        new RectF(innerBounds);
        RectF outerBounds = rectF2;
        outerBounds.inset(-this.mShadowSize, -this.mShadowSize);
        if (this.mCornerShadowPath == null) {
            new Path();
            this.mCornerShadowPath = path;
        } else {
            this.mCornerShadowPath.reset();
        }
        this.mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(outerBounds, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(innerBounds, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        float startRatio = this.mCornerRadius / (this.mCornerRadius + this.mShadowSize);
        Paint paint = this.mCornerShadowPaint;
        Shader shader3 = shader;
        float f = this.mCornerRadius + this.mShadowSize;
        int[] iArr = new int[3];
        iArr[0] = this.mShadowStartColor;
        int[] iArr2 = iArr;
        iArr2[1] = this.mShadowStartColor;
        int[] iArr3 = iArr2;
        int[] iArr4 = iArr3;
        iArr3[2] = this.mShadowEndColor;
        float[] fArr = new float[3];
        fArr[0] = 0.0f;
        float[] fArr2 = fArr;
        fArr2[1] = startRatio;
        float[] fArr3 = fArr2;
        fArr3[2] = 1.0f;
        new RadialGradient(0.0f, 0.0f, f, iArr4, fArr3, Shader.TileMode.CLAMP);
        Shader shader4 = paint.setShader(shader3);
        Paint paint2 = this.mEdgeShadowPaint;
        Shader shader5 = shader2;
        float f2 = (-this.mCornerRadius) - this.mShadowSize;
        int[] iArr5 = new int[3];
        iArr5[0] = this.mShadowStartColor;
        int[] iArr6 = iArr5;
        iArr6[1] = this.mShadowStartColor;
        int[] iArr7 = iArr6;
        iArr7[2] = this.mShadowEndColor;
        new LinearGradient(0.0f, (-this.mCornerRadius) + this.mShadowSize, 0.0f, f2, iArr7, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
        Shader shader6 = paint2.setShader(shader5);
        this.mEdgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect rect) {
        Rect bounds = rect;
        float verticalOffset = this.mRawMaxShadowSize * SHADOW_MULTIPLIER;
        this.mCardBounds.set(((float) bounds.left) + this.mRawMaxShadowSize, ((float) bounds.top) + verticalOffset, ((float) bounds.right) - this.mRawMaxShadowSize, ((float) bounds.bottom) - verticalOffset);
        buildShadowCorners();
    }

    /* access modifiers changed from: package-private */
    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    /* access modifiers changed from: package-private */
    public void getMaxShadowAndCornerPadding(Rect into) {
        boolean padding = getPadding(into);
    }

    /* access modifiers changed from: package-private */
    public void setShadowSize(float size) {
        setShadowSize(size, this.mRawMaxShadowSize);
    }

    /* access modifiers changed from: package-private */
    public void setMaxShadowSize(float size) {
        setShadowSize(this.mRawShadowSize, size);
    }

    /* access modifiers changed from: package-private */
    public float getShadowSize() {
        return this.mRawShadowSize;
    }

    /* access modifiers changed from: package-private */
    public float getMaxShadowSize() {
        return this.mRawMaxShadowSize;
    }

    /* access modifiers changed from: package-private */
    public float getMinWidth() {
        return (2.0f * Math.max(this.mRawMaxShadowSize, this.mCornerRadius + ((float) this.mInsetShadow) + (this.mRawMaxShadowSize / 2.0f))) + ((this.mRawMaxShadowSize + ((float) this.mInsetShadow)) * 2.0f);
    }

    /* access modifiers changed from: package-private */
    public float getMinHeight() {
        return (2.0f * Math.max(this.mRawMaxShadowSize, this.mCornerRadius + ((float) this.mInsetShadow) + ((this.mRawMaxShadowSize * SHADOW_MULTIPLIER) / 2.0f))) + (((this.mRawMaxShadowSize * SHADOW_MULTIPLIER) + ((float) this.mInsetShadow)) * 2.0f);
    }

    /* access modifiers changed from: package-private */
    public void setColor(@Nullable ColorStateList color) {
        setBackground(color);
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getColor() {
        return this.mBackground;
    }
}
