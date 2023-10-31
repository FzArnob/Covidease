package android.support.design.circularreveal;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.circularreveal.CircularRevealWidget;
import android.support.design.widget.MathUtils;
import android.support.p000v4.internal.view.SupportMenu;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularRevealHelper {
    public static final int BITMAP_SHADER = 0;
    public static final int CLIP_PATH = 1;
    private static final boolean DEBUG = false;
    public static final int REVEAL_ANIMATOR = 2;
    public static final int STRATEGY;
    private boolean buildingCircularRevealCache;
    private Paint debugPaint;
    private final Delegate delegate;
    private boolean hasCircularRevealCache;
    @Nullable
    private Drawable overlayDrawable;
    @Nullable
    private CircularRevealWidget.RevealInfo revealInfo;
    private final Paint revealPaint;
    private final Path revealPath;
    private final Paint scrimPaint;
    private final View view;

    interface Delegate {
        void actualDraw(Canvas canvas);

        boolean actualIsOpaque();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Strategy {
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            STRATEGY = 2;
        } else if (Build.VERSION.SDK_INT >= 18) {
            STRATEGY = 1;
        } else {
            STRATEGY = 0;
        }
    }

    public CircularRevealHelper(Delegate delegate2) {
        Path path;
        Paint paint;
        Paint paint2;
        Delegate delegate3 = delegate2;
        this.delegate = delegate3;
        this.view = (View) delegate3;
        this.view.setWillNotDraw(false);
        new Path();
        this.revealPath = path;
        new Paint(7);
        this.revealPaint = paint;
        new Paint(1);
        this.scrimPaint = paint2;
        this.scrimPaint.setColor(0);
    }

    public void buildCircularRevealCache() {
        Shader shader;
        Canvas canvas;
        if (STRATEGY == 0) {
            this.buildingCircularRevealCache = true;
            this.hasCircularRevealCache = false;
            this.view.buildDrawingCache();
            Bitmap bitmap = this.view.getDrawingCache();
            if (!(bitmap != null || this.view.getWidth() == 0 || this.view.getHeight() == 0)) {
                bitmap = Bitmap.createBitmap(this.view.getWidth(), this.view.getHeight(), Bitmap.Config.ARGB_8888);
                new Canvas(bitmap);
                this.view.draw(canvas);
            }
            if (bitmap != null) {
                new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                Shader shader2 = this.revealPaint.setShader(shader);
            }
            this.buildingCircularRevealCache = false;
            this.hasCircularRevealCache = true;
        }
    }

    public void destroyCircularRevealCache() {
        if (STRATEGY == 0) {
            this.hasCircularRevealCache = false;
            this.view.destroyDrawingCache();
            Shader shader = this.revealPaint.setShader((Shader) null);
            this.view.invalidate();
        }
    }

    public void setRevealInfo(@Nullable CircularRevealWidget.RevealInfo revealInfo2) {
        CircularRevealWidget.RevealInfo revealInfo3;
        CircularRevealWidget.RevealInfo revealInfo4 = revealInfo2;
        if (revealInfo4 == null) {
            this.revealInfo = null;
        } else {
            if (this.revealInfo == null) {
                new CircularRevealWidget.RevealInfo(revealInfo4);
                this.revealInfo = revealInfo3;
            } else {
                this.revealInfo.set(revealInfo4);
            }
            if (MathUtils.geq(revealInfo4.radius, getDistanceToFurthestCorner(revealInfo4), 1.0E-4f)) {
                this.revealInfo.radius = Float.MAX_VALUE;
            }
        }
        invalidateRevealInfo();
    }

    @Nullable
    public CircularRevealWidget.RevealInfo getRevealInfo() {
        CircularRevealWidget.RevealInfo revealInfo2;
        if (this.revealInfo == null) {
            return null;
        }
        new CircularRevealWidget.RevealInfo(this.revealInfo);
        CircularRevealWidget.RevealInfo revealInfo3 = revealInfo2;
        if (revealInfo3.isInvalid()) {
            revealInfo3.radius = getDistanceToFurthestCorner(revealInfo3);
        }
        return revealInfo3;
    }

    public void setCircularRevealScrimColor(@ColorInt int color) {
        this.scrimPaint.setColor(color);
        this.view.invalidate();
    }

    @ColorInt
    public int getCircularRevealScrimColor() {
        return this.scrimPaint.getColor();
    }

    @Nullable
    public Drawable getCircularRevealOverlayDrawable() {
        return this.overlayDrawable;
    }

    public void setCircularRevealOverlayDrawable(@Nullable Drawable drawable) {
        this.overlayDrawable = drawable;
        this.view.invalidate();
    }

    private void invalidateRevealInfo() {
        if (STRATEGY == 1) {
            this.revealPath.rewind();
            if (this.revealInfo != null) {
                this.revealPath.addCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius, Path.Direction.CW);
            }
        }
        this.view.invalidate();
    }

    private float getDistanceToFurthestCorner(CircularRevealWidget.RevealInfo revealInfo2) {
        CircularRevealWidget.RevealInfo revealInfo3 = revealInfo2;
        return MathUtils.distanceToFurthestCorner(revealInfo3.centerX, revealInfo3.centerY, 0.0f, 0.0f, (float) this.view.getWidth(), (float) this.view.getHeight());
    }

    public void draw(Canvas canvas) {
        Throwable th;
        StringBuilder sb;
        Canvas canvas2 = canvas;
        if (shouldDrawCircularReveal()) {
            switch (STRATEGY) {
                case 0:
                    canvas2.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius, this.revealPaint);
                    if (shouldDrawScrim()) {
                        canvas2.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius, this.scrimPaint);
                        break;
                    }
                    break;
                case 1:
                    int count = canvas2.save();
                    boolean clipPath = canvas2.clipPath(this.revealPath);
                    this.delegate.actualDraw(canvas2);
                    if (shouldDrawScrim()) {
                        canvas2.drawRect(0.0f, 0.0f, (float) this.view.getWidth(), (float) this.view.getHeight(), this.scrimPaint);
                    }
                    canvas2.restoreToCount(count);
                    break;
                case 2:
                    this.delegate.actualDraw(canvas2);
                    if (shouldDrawScrim()) {
                        canvas2.drawRect(0.0f, 0.0f, (float) this.view.getWidth(), (float) this.view.getHeight(), this.scrimPaint);
                        break;
                    }
                    break;
                default:
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Unsupported strategy ").append(STRATEGY).toString());
                    throw th2;
            }
        } else {
            this.delegate.actualDraw(canvas2);
            if (shouldDrawScrim()) {
                canvas2.drawRect(0.0f, 0.0f, (float) this.view.getWidth(), (float) this.view.getHeight(), this.scrimPaint);
            }
        }
        drawOverlayDrawable(canvas2);
    }

    private void drawOverlayDrawable(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (shouldDrawOverlayDrawable()) {
            Rect bounds = this.overlayDrawable.getBounds();
            float translationX = this.revealInfo.centerX - (((float) bounds.width()) / 2.0f);
            float translationY = this.revealInfo.centerY - (((float) bounds.height()) / 2.0f);
            canvas2.translate(translationX, translationY);
            this.overlayDrawable.draw(canvas2);
            canvas2.translate(-translationX, -translationY);
        }
    }

    public boolean isOpaque() {
        return this.delegate.actualIsOpaque() && !shouldDrawCircularReveal();
    }

    private boolean shouldDrawCircularReveal() {
        boolean invalidRevealInfo = this.revealInfo == null || this.revealInfo.isInvalid();
        if (STRATEGY == 0) {
            return !invalidRevealInfo && this.hasCircularRevealCache;
        }
        return !invalidRevealInfo;
    }

    private boolean shouldDrawScrim() {
        return !this.buildingCircularRevealCache && Color.alpha(this.scrimPaint.getColor()) != 0;
    }

    private boolean shouldDrawOverlayDrawable() {
        return (this.buildingCircularRevealCache || this.overlayDrawable == null || this.revealInfo == null) ? false : true;
    }

    private void drawDebugMode(Canvas canvas) {
        Canvas canvas2 = canvas;
        this.delegate.actualDraw(canvas2);
        if (shouldDrawScrim()) {
            canvas2.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius, this.scrimPaint);
        }
        if (shouldDrawCircularReveal()) {
            drawDebugCircle(canvas2, -16777216, 10.0f);
            drawDebugCircle(canvas2, SupportMenu.CATEGORY_MASK, 5.0f);
        }
        drawOverlayDrawable(canvas2);
    }

    private void drawDebugCircle(Canvas canvas, int color, float f) {
        float width = f;
        this.debugPaint.setColor(color);
        this.debugPaint.setStrokeWidth(width);
        canvas.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius - (width / 2.0f), this.debugPaint);
    }
}
