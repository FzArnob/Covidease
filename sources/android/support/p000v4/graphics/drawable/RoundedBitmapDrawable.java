package android.support.p000v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import com.google.appinventor.components.common.ComponentConstants;

/* renamed from: android.support.v4.graphics.drawable.RoundedBitmapDrawable */
public abstract class RoundedBitmapDrawable extends Drawable {
    private static final int DEFAULT_PAINT_FLAGS = 3;
    private boolean mApplyGravity;
    final Bitmap mBitmap;
    private int mBitmapHeight;
    private final BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect;
    private final RectF mDstRectF;
    private int mGravity = 119;
    private boolean mIsCircular;
    private final Paint mPaint;
    private final Matrix mShaderMatrix;
    private int mTargetDensity = ComponentConstants.TEXTBOX_PREFERRED_WIDTH;

    @NonNull
    public final Paint getPaint() {
        return this.mPaint;
    }

    @Nullable
    public final Bitmap getBitmap() {
        return this.mBitmap;
    }

    private void computeBitmapSize() {
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }

    public void setTargetDensity(@NonNull Canvas canvas) {
        setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(@NonNull DisplayMetrics metrics) {
        setTargetDensity(metrics.densityDpi);
    }

    public void setTargetDensity(int i) {
        int density = i;
        if (this.mTargetDensity != density) {
            this.mTargetDensity = density == 0 ? ComponentConstants.TEXTBOX_PREFERRED_WIDTH : density;
            if (this.mBitmap != null) {
                computeBitmapSize();
            }
            invalidateSelf();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setGravity(int i) {
        int gravity = i;
        if (this.mGravity != gravity) {
            this.mGravity = gravity;
            this.mApplyGravity = true;
            invalidateSelf();
        }
    }

    public void setMipMap(boolean z) {
        Throwable th;
        boolean z2 = z;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public boolean hasMipMap() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void setAntiAlias(boolean aa) {
        this.mPaint.setAntiAlias(aa);
        invalidateSelf();
    }

    public boolean hasAntiAlias() {
        return this.mPaint.isAntiAlias();
    }

    public void setFilterBitmap(boolean filter) {
        this.mPaint.setFilterBitmap(filter);
        invalidateSelf();
    }

    public void setDither(boolean dither) {
        this.mPaint.setDither(dither);
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public void gravityCompatApply(int i, int i2, int i3, Rect rect, Rect rect2) {
        Throwable th;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        Rect rect3 = rect;
        Rect rect4 = rect2;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public void updateDstRect() {
        if (this.mApplyGravity) {
            if (this.mIsCircular) {
                int minDimen = Math.min(this.mBitmapWidth, this.mBitmapHeight);
                gravityCompatApply(this.mGravity, minDimen, minDimen, getBounds(), this.mDstRect);
                int minDrawDimen = Math.min(this.mDstRect.width(), this.mDstRect.height());
                this.mDstRect.inset(Math.max(0, (this.mDstRect.width() - minDrawDimen) / 2), Math.max(0, (this.mDstRect.height() - minDrawDimen) / 2));
                this.mCornerRadius = 0.5f * ((float) minDrawDimen);
            } else {
                gravityCompatApply(this.mGravity, this.mBitmapWidth, this.mBitmapHeight, getBounds(), this.mDstRect);
            }
            this.mDstRectF.set(this.mDstRect);
            if (this.mBitmapShader != null) {
                this.mShaderMatrix.setTranslate(this.mDstRectF.left, this.mDstRectF.top);
                boolean preScale = this.mShaderMatrix.preScale(this.mDstRectF.width() / ((float) this.mBitmap.getWidth()), this.mDstRectF.height() / ((float) this.mBitmap.getHeight()));
                this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
                Shader shader = this.mPaint.setShader(this.mBitmapShader);
            }
            this.mApplyGravity = false;
        }
    }

    public void draw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            updateDstRect();
            if (this.mPaint.getShader() == null) {
                canvas2.drawBitmap(bitmap, (Rect) null, this.mDstRect, this.mPaint);
            } else {
                canvas2.drawRoundRect(this.mDstRectF, this.mCornerRadius, this.mCornerRadius, this.mPaint);
            }
        }
    }

    public void setAlpha(int i) {
        int alpha = i;
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    public void setColorFilter(ColorFilter cf) {
        ColorFilter colorFilter = this.mPaint.setColorFilter(cf);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public void setCircular(boolean z) {
        boolean circular = z;
        this.mIsCircular = circular;
        this.mApplyGravity = true;
        if (circular) {
            updateCircularCornerRadius();
            Shader shader = this.mPaint.setShader(this.mBitmapShader);
            invalidateSelf();
            return;
        }
        setCornerRadius(0.0f);
    }

    private void updateCircularCornerRadius() {
        this.mCornerRadius = (float) (Math.min(this.mBitmapHeight, this.mBitmapWidth) / 2);
    }

    public boolean isCircular() {
        return this.mIsCircular;
    }

    public void setCornerRadius(float f) {
        float cornerRadius = f;
        if (this.mCornerRadius != cornerRadius) {
            this.mIsCircular = false;
            if (isGreaterThanZero(cornerRadius)) {
                Shader shader = this.mPaint.setShader(this.mBitmapShader);
            } else {
                Shader shader2 = this.mPaint.setShader((Shader) null);
            }
            this.mCornerRadius = cornerRadius;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if (this.mIsCircular) {
            updateCircularCornerRadius();
        }
        this.mApplyGravity = true;
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public int getOpacity() {
        if (this.mGravity != 119 || this.mIsCircular) {
            return -3;
        }
        Bitmap bm = this.mBitmap;
        return (bm == null || bm.hasAlpha() || this.mPaint.getAlpha() < 255 || isGreaterThanZero(this.mCornerRadius)) ? -3 : -1;
    }

    RoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        Paint paint;
        Matrix matrix;
        Rect rect;
        RectF rectF;
        BitmapShader bitmapShader;
        Resources res = resources;
        Bitmap bitmap2 = bitmap;
        new Paint(3);
        this.mPaint = paint;
        new Matrix();
        this.mShaderMatrix = matrix;
        new Rect();
        this.mDstRect = rect;
        new RectF();
        this.mDstRectF = rectF;
        this.mApplyGravity = true;
        if (res != null) {
            this.mTargetDensity = res.getDisplayMetrics().densityDpi;
        }
        this.mBitmap = bitmap2;
        if (this.mBitmap != null) {
            computeBitmapSize();
            new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.mBitmapShader = bitmapShader;
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
        this.mBitmapShader = null;
    }

    private static boolean isGreaterThanZero(float toCompare) {
        return toCompare > 0.05f;
    }
}
