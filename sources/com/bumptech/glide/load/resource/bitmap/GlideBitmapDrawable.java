package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.google.appinventor.components.common.ComponentConstants;

public class GlideBitmapDrawable extends GlideDrawable {
    private boolean applyGravity;
    private final Rect destRect;
    private int height;
    private boolean mutated;
    private BitmapState state;
    private int width;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GlideBitmapDrawable(android.content.res.Resources r10, android.graphics.Bitmap r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            r4 = r1
            com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable$BitmapState r5 = new com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable$BitmapState
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r2
            r6.<init>((android.graphics.Bitmap) r7)
            r3.<init>((android.content.res.Resources) r4, (com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable.BitmapState) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable.<init>(android.content.res.Resources, android.graphics.Bitmap):void");
    }

    GlideBitmapDrawable(Resources resources, BitmapState bitmapState) {
        Rect rect;
        int targetDensity;
        Throwable th;
        Resources res = resources;
        BitmapState state2 = bitmapState;
        new Rect();
        this.destRect = rect;
        if (state2 == null) {
            Throwable th2 = th;
            new NullPointerException("BitmapState must not be null");
            throw th2;
        }
        this.state = state2;
        if (res != null) {
            int density = res.getDisplayMetrics().densityDpi;
            targetDensity = density == 0 ? ComponentConstants.TEXTBOX_PREFERRED_WIDTH : density;
            state2.targetDensity = targetDensity;
        } else {
            targetDensity = state2.targetDensity;
        }
        this.width = state2.bitmap.getScaledWidth(targetDensity);
        this.height = state2.bitmap.getScaledHeight(targetDensity);
    }

    public int getIntrinsicWidth() {
        return this.width;
    }

    public int getIntrinsicHeight() {
        return this.height;
    }

    public boolean isAnimated() {
        return false;
    }

    public void setLoopCount(int loopCount) {
    }

    public void start() {
    }

    public void stop() {
    }

    public boolean isRunning() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.applyGravity = true;
    }

    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.applyGravity) {
            Gravity.apply(119, this.width, this.height, getBounds(), this.destRect);
            this.applyGravity = false;
        }
        canvas2.drawBitmap(this.state.bitmap, (Rect) null, this.destRect, this.state.paint);
    }

    public void setAlpha(int i) {
        int alpha = i;
        if (this.state.paint.getAlpha() != alpha) {
            this.state.setAlpha(alpha);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.state.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        Bitmap bm = this.state.bitmap;
        return (bm == null || bm.hasAlpha() || this.state.paint.getAlpha() < 255) ? -3 : -1;
    }

    public Drawable mutate() {
        BitmapState bitmapState;
        if (!this.mutated && super.mutate() == this) {
            new BitmapState(this.state);
            this.state = bitmapState;
            this.mutated = true;
        }
        return this;
    }

    public Bitmap getBitmap() {
        return this.state.bitmap;
    }

    static class BitmapState extends Drawable.ConstantState {
        private static final Paint DEFAULT_PAINT;
        private static final int DEFAULT_PAINT_FLAGS = 6;
        private static final int GRAVITY = 119;
        final Bitmap bitmap;
        Paint paint;
        int targetDensity;

        static {
            Paint paint2;
            new Paint(6);
            DEFAULT_PAINT = paint2;
        }

        public BitmapState(Bitmap bitmap2) {
            this.paint = DEFAULT_PAINT;
            this.bitmap = bitmap2;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        BitmapState(com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable.BitmapState r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                android.graphics.Bitmap r3 = r3.bitmap
                r2.<init>((android.graphics.Bitmap) r3)
                r2 = r0
                r3 = r1
                int r3 = r3.targetDensity
                r2.targetDensity = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable.BitmapState.<init>(com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable$BitmapState):void");
        }

        /* access modifiers changed from: package-private */
        public void setColorFilter(ColorFilter colorFilter) {
            mutatePaint();
            ColorFilter colorFilter2 = this.paint.setColorFilter(colorFilter);
        }

        /* access modifiers changed from: package-private */
        public void setAlpha(int alpha) {
            mutatePaint();
            this.paint.setAlpha(alpha);
        }

        /* access modifiers changed from: package-private */
        public void mutatePaint() {
            Paint paint2;
            if (DEFAULT_PAINT == this.paint) {
                new Paint(6);
                this.paint = paint2;
            }
        }

        public Drawable newDrawable() {
            Drawable drawable;
            new GlideBitmapDrawable((Resources) null, this);
            return drawable;
        }

        public Drawable newDrawable(Resources res) {
            Drawable drawable;
            new GlideBitmapDrawable(res, this);
            return drawable;
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }
}
