package android.support.design.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;

class CutoutDrawable extends GradientDrawable {
    private final RectF cutoutBounds;
    private final Paint cutoutPaint;
    private int savedLayer;

    CutoutDrawable() {
        Paint paint;
        RectF rectF;
        new Paint(1);
        this.cutoutPaint = paint;
        setPaintStyles();
        new RectF();
        this.cutoutBounds = rectF;
    }

    private void setPaintStyles() {
        Xfermode xfermode;
        this.cutoutPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.cutoutPaint.setColor(-1);
        new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        Xfermode xfermode2 = this.cutoutPaint.setXfermode(xfermode);
    }

    /* access modifiers changed from: package-private */
    public boolean hasCutout() {
        return !this.cutoutBounds.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void setCutout(float f, float f2, float f3, float f4) {
        float left = f;
        float top = f2;
        float right = f3;
        float bottom = f4;
        if (left != this.cutoutBounds.left || top != this.cutoutBounds.top || right != this.cutoutBounds.right || bottom != this.cutoutBounds.bottom) {
            this.cutoutBounds.set(left, top, right, bottom);
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public void setCutout(RectF rectF) {
        RectF bounds = rectF;
        setCutout(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    /* access modifiers changed from: package-private */
    public void removeCutout() {
        setCutout(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void draw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        preDraw(canvas2);
        super.draw(canvas2);
        canvas2.drawRect(this.cutoutBounds, this.cutoutPaint);
        postDraw(canvas2);
    }

    private void preDraw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        Drawable.Callback callback = getCallback();
        if (useHardwareLayer(callback)) {
            ((View) callback).setLayerType(2, (Paint) null);
        } else {
            saveCanvasLayer(canvas2);
        }
    }

    private void saveCanvasLayer(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        if (Build.VERSION.SDK_INT >= 21) {
            this.savedLayer = canvas2.saveLayer(0.0f, 0.0f, (float) canvas2.getWidth(), (float) canvas2.getHeight(), (Paint) null);
            return;
        }
        this.savedLayer = canvas2.saveLayer(0.0f, 0.0f, (float) canvas2.getWidth(), (float) canvas2.getHeight(), (Paint) null, 31);
    }

    private void postDraw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        if (!useHardwareLayer(getCallback())) {
            canvas2.restoreToCount(this.savedLayer);
        }
    }

    private boolean useHardwareLayer(Drawable.Callback callback) {
        return callback instanceof View;
    }
}
