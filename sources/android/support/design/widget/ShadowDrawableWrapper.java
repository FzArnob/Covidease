package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.design.C0064R;
import android.support.p000v4.content.ContextCompat;
import android.support.p003v7.graphics.drawable.DrawableWrapper;

public class ShadowDrawableWrapper extends DrawableWrapper {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean addPaddingForCorners = true;
    final RectF contentBounds;
    float cornerRadius;
    final Paint cornerShadowPaint;
    Path cornerShadowPath;
    private boolean dirty = true;
    final Paint edgeShadowPaint;
    float maxShadowSize;
    private boolean printedShadowClipWarning = false;
    float rawMaxShadowSize;
    float rawShadowSize;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    float shadowSize;
    private final int shadowStartColor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ShadowDrawableWrapper(Context context, Drawable content, float radius, float shadowSize2, float maxShadowSize2) {
        super(content);
        Paint paint;
        RectF rectF;
        Paint paint2;
        Context context2 = context;
        this.shadowStartColor = ContextCompat.getColor(context2, C0064R.color.design_fab_shadow_start_color);
        this.shadowMiddleColor = ContextCompat.getColor(context2, C0064R.color.design_fab_shadow_mid_color);
        this.shadowEndColor = ContextCompat.getColor(context2, C0064R.color.design_fab_shadow_end_color);
        new Paint(5);
        this.cornerShadowPaint = paint;
        this.cornerShadowPaint.setStyle(Paint.Style.FILL);
        this.cornerRadius = (float) Math.round(radius);
        new RectF();
        this.contentBounds = rectF;
        new Paint(this.cornerShadowPaint);
        this.edgeShadowPaint = paint2;
        this.edgeShadowPaint.setAntiAlias(false);
        setShadowSize(shadowSize2, maxShadowSize2);
    }

    private static int toEven(float value) {
        int i = Math.round(value);
        return i % 2 == 1 ? i - 1 : i;
    }

    public void setAddPaddingForCorners(boolean addPaddingForCorners2) {
        this.addPaddingForCorners = addPaddingForCorners2;
        invalidateSelf();
    }

    public void setAlpha(int i) {
        int alpha = i;
        super.setAlpha(alpha);
        this.cornerShadowPaint.setAlpha(alpha);
        this.edgeShadowPaint.setAlpha(alpha);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect rect2 = rect;
        this.dirty = true;
    }

    public void setShadowSize(float f, float f2) {
        Throwable th;
        float shadowSize2 = f;
        float maxShadowSize2 = f2;
        if (shadowSize2 < 0.0f || maxShadowSize2 < 0.0f) {
            Throwable th2 = th;
            new IllegalArgumentException("invalid shadow size");
            throw th2;
        }
        float shadowSize3 = (float) toEven(shadowSize2);
        float maxShadowSize3 = (float) toEven(maxShadowSize2);
        if (shadowSize3 > maxShadowSize3) {
            shadowSize3 = maxShadowSize3;
            if (!this.printedShadowClipWarning) {
                this.printedShadowClipWarning = true;
            }
        }
        if (this.rawShadowSize != shadowSize3 || this.rawMaxShadowSize != maxShadowSize3) {
            this.rawShadowSize = shadowSize3;
            this.rawMaxShadowSize = maxShadowSize3;
            this.shadowSize = (float) Math.round(shadowSize3 * SHADOW_MULTIPLIER);
            this.maxShadowSize = maxShadowSize3;
            this.dirty = true;
            invalidateSelf();
        }
    }

    public void setShadowSize(float size) {
        setShadowSize(size, this.rawMaxShadowSize);
    }

    public float getShadowSize() {
        return this.rawShadowSize;
    }

    public boolean getPadding(Rect padding) {
        int vOffset = (int) Math.ceil((double) calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        int hOffset = (int) Math.ceil((double) calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        padding.set(hOffset, vOffset, hOffset, vOffset);
        return true;
    }

    public static float calculateVerticalPadding(float f, float f2, boolean addPaddingForCorners2) {
        float maxShadowSize2 = f;
        float cornerRadius2 = f2;
        if (addPaddingForCorners2) {
            return (float) (((double) (maxShadowSize2 * SHADOW_MULTIPLIER)) + ((1.0d - COS_45) * ((double) cornerRadius2)));
        }
        return maxShadowSize2 * SHADOW_MULTIPLIER;
    }

    public static float calculateHorizontalPadding(float f, float f2, boolean addPaddingForCorners2) {
        float maxShadowSize2 = f;
        float cornerRadius2 = f2;
        if (addPaddingForCorners2) {
            return (float) (((double) maxShadowSize2) + ((1.0d - COS_45) * ((double) cornerRadius2)));
        }
        return maxShadowSize2;
    }

    public int getOpacity() {
        return -3;
    }

    public void setCornerRadius(float radius) {
        float radius2 = (float) Math.round(radius);
        if (this.cornerRadius != radius2) {
            this.cornerRadius = radius2;
            this.dirty = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.dirty) {
            buildComponents(getBounds());
            this.dirty = false;
        }
        drawShadow(canvas2);
        super.draw(canvas2);
    }

    public final void setRotation(float f) {
        float rotation2 = f;
        if (this.rotation != rotation2) {
            this.rotation = rotation2;
            invalidateSelf();
        }
    }

    private void drawShadow(Canvas canvas) {
        Canvas canvas2 = canvas;
        int rotateSaved = canvas2.save();
        canvas2.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
        float edgeShadowTop = (-this.cornerRadius) - this.shadowSize;
        float shadowOffset = this.cornerRadius;
        boolean drawHorizontalEdges = this.contentBounds.width() - (2.0f * shadowOffset) > 0.0f;
        boolean drawVerticalEdges = this.contentBounds.height() - (2.0f * shadowOffset) > 0.0f;
        float shadowOffsetTop = this.rawShadowSize - (this.rawShadowSize * SHADOW_TOP_SCALE);
        float shadowScaleHorizontal = shadowOffset / (shadowOffset + (this.rawShadowSize - (this.rawShadowSize * SHADOW_HORIZ_SCALE)));
        float shadowScaleTop = shadowOffset / (shadowOffset + shadowOffsetTop);
        float shadowScaleBottom = shadowOffset / (shadowOffset + (this.rawShadowSize - (this.rawShadowSize * SHADOW_BOTTOM_SCALE)));
        int saved = canvas2.save();
        canvas2.translate(this.contentBounds.left + shadowOffset, this.contentBounds.top + shadowOffset);
        canvas2.scale(shadowScaleHorizontal, shadowScaleTop);
        canvas2.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / shadowScaleHorizontal, SHADOW_BOTTOM_SCALE);
            canvas2.drawRect(0.0f, edgeShadowTop, this.contentBounds.width() - (2.0f * shadowOffset), -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas2.restoreToCount(saved);
        int saved2 = canvas2.save();
        canvas2.translate(this.contentBounds.right - shadowOffset, this.contentBounds.bottom - shadowOffset);
        canvas2.scale(shadowScaleHorizontal, shadowScaleBottom);
        canvas2.rotate(180.0f);
        canvas2.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / shadowScaleHorizontal, SHADOW_BOTTOM_SCALE);
            canvas2.drawRect(0.0f, edgeShadowTop, this.contentBounds.width() - (2.0f * shadowOffset), (-this.cornerRadius) + this.shadowSize, this.edgeShadowPaint);
        }
        canvas2.restoreToCount(saved2);
        int saved3 = canvas2.save();
        canvas2.translate(this.contentBounds.left + shadowOffset, this.contentBounds.bottom - shadowOffset);
        canvas2.scale(shadowScaleHorizontal, shadowScaleBottom);
        canvas2.rotate(270.0f);
        canvas2.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (drawVerticalEdges) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / shadowScaleBottom, SHADOW_BOTTOM_SCALE);
            canvas2.drawRect(0.0f, edgeShadowTop, this.contentBounds.height() - (2.0f * shadowOffset), -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas2.restoreToCount(saved3);
        int saved4 = canvas2.save();
        canvas2.translate(this.contentBounds.right - shadowOffset, this.contentBounds.top + shadowOffset);
        canvas2.scale(shadowScaleHorizontal, shadowScaleTop);
        canvas2.rotate(90.0f);
        canvas2.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (drawVerticalEdges) {
            canvas2.scale(SHADOW_BOTTOM_SCALE / shadowScaleTop, SHADOW_BOTTOM_SCALE);
            canvas2.drawRect(0.0f, edgeShadowTop, this.contentBounds.height() - (2.0f * shadowOffset), -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas2.restoreToCount(saved4);
        canvas2.restoreToCount(rotateSaved);
    }

    private void buildShadowCorners() {
        RectF rectF;
        RectF rectF2;
        Shader shader;
        Shader shader2;
        Path path;
        new RectF(-this.cornerRadius, -this.cornerRadius, this.cornerRadius, this.cornerRadius);
        RectF innerBounds = rectF;
        new RectF(innerBounds);
        RectF outerBounds = rectF2;
        outerBounds.inset(-this.shadowSize, -this.shadowSize);
        if (this.cornerShadowPath == null) {
            new Path();
            this.cornerShadowPath = path;
        } else {
            this.cornerShadowPath.reset();
        }
        this.cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.cornerShadowPath.moveTo(-this.cornerRadius, 0.0f);
        this.cornerShadowPath.rLineTo(-this.shadowSize, 0.0f);
        this.cornerShadowPath.arcTo(outerBounds, 180.0f, 90.0f, false);
        this.cornerShadowPath.arcTo(innerBounds, 270.0f, -90.0f, false);
        this.cornerShadowPath.close();
        float shadowRadius = -outerBounds.top;
        if (shadowRadius > 0.0f) {
            float startRatio = this.cornerRadius / shadowRadius;
            float midRatio = startRatio + ((SHADOW_BOTTOM_SCALE - startRatio) / 2.0f);
            Paint paint = this.cornerShadowPaint;
            Shader shader3 = shader2;
            int[] iArr = new int[4];
            iArr[0] = 0;
            int[] iArr2 = iArr;
            iArr2[1] = this.shadowStartColor;
            int[] iArr3 = iArr2;
            iArr3[2] = this.shadowMiddleColor;
            int[] iArr4 = iArr3;
            int[] iArr5 = iArr4;
            iArr4[3] = this.shadowEndColor;
            float[] fArr = new float[4];
            fArr[0] = 0.0f;
            float[] fArr2 = fArr;
            fArr2[1] = startRatio;
            float[] fArr3 = fArr2;
            fArr3[2] = midRatio;
            float[] fArr4 = fArr3;
            fArr4[3] = 1.0f;
            new RadialGradient(0.0f, 0.0f, shadowRadius, iArr5, fArr4, Shader.TileMode.CLAMP);
            Shader shader4 = paint.setShader(shader3);
        }
        Paint paint2 = this.edgeShadowPaint;
        Shader shader5 = shader;
        float f = innerBounds.top;
        float f2 = outerBounds.top;
        int[] iArr6 = new int[3];
        iArr6[0] = this.shadowStartColor;
        int[] iArr7 = iArr6;
        iArr7[1] = this.shadowMiddleColor;
        int[] iArr8 = iArr7;
        iArr8[2] = this.shadowEndColor;
        new LinearGradient(0.0f, f, 0.0f, f2, iArr8, new float[]{0.0f, SHADOW_HORIZ_SCALE, SHADOW_BOTTOM_SCALE}, Shader.TileMode.CLAMP);
        Shader shader6 = paint2.setShader(shader5);
        this.edgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect rect) {
        Rect bounds = rect;
        float verticalOffset = this.rawMaxShadowSize * SHADOW_MULTIPLIER;
        this.contentBounds.set(((float) bounds.left) + this.rawMaxShadowSize, ((float) bounds.top) + verticalOffset, ((float) bounds.right) - this.rawMaxShadowSize, ((float) bounds.bottom) - verticalOffset);
        getWrappedDrawable().setBounds((int) this.contentBounds.left, (int) this.contentBounds.top, (int) this.contentBounds.right, (int) this.contentBounds.bottom);
        buildShadowCorners();
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public void setMaxShadowSize(float size) {
        setShadowSize(this.rawShadowSize, size);
    }

    public float getMaxShadowSize() {
        return this.rawMaxShadowSize;
    }

    public float getMinWidth() {
        return (2.0f * Math.max(this.rawMaxShadowSize, this.cornerRadius + (this.rawMaxShadowSize / 2.0f))) + (this.rawMaxShadowSize * 2.0f);
    }

    public float getMinHeight() {
        return (2.0f * Math.max(this.rawMaxShadowSize, this.cornerRadius + ((this.rawMaxShadowSize * SHADOW_MULTIPLIER) / 2.0f))) + (this.rawMaxShadowSize * SHADOW_MULTIPLIER * 2.0f);
    }
}
