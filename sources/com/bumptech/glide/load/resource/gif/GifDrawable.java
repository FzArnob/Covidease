package com.bumptech.glide.load.resource.gif;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifFrameLoader;

public class GifDrawable extends GlideDrawable implements GifFrameLoader.FrameCallback {
    private boolean applyGravity;
    private final GifDecoder decoder;
    private final Rect destRect;
    private final GifFrameLoader frameLoader;
    private boolean isRecycled;
    private boolean isRunning;
    private boolean isStarted;
    private boolean isVisible;
    private int loopCount;
    private int maxLoopCount;
    private final Paint paint;
    private final GifState state;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifDrawable(android.content.Context r24, com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider r25, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r26, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r27, int r28, int r29, com.bumptech.glide.gifdecoder.GifHeader r30, byte[] r31, android.graphics.Bitmap r32) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            r4 = r27
            r5 = r28
            r6 = r29
            r7 = r30
            r8 = r31
            r9 = r32
            r10 = r0
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r11 = new com.bumptech.glide.load.resource.gif.GifDrawable$GifState
            r22 = r11
            r11 = r22
            r12 = r22
            r13 = r7
            r14 = r8
            r15 = r1
            r16 = r4
            r17 = r5
            r18 = r6
            r19 = r2
            r20 = r3
            r21 = r9
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21)
            r10.<init>(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.GifDrawable.<init>(android.content.Context, com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.Transformation, int, int, com.bumptech.glide.gifdecoder.GifHeader, byte[], android.graphics.Bitmap):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifDrawable(com.bumptech.glide.load.resource.gif.GifDrawable r18, android.graphics.Bitmap r19, com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r0
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r5 = new com.bumptech.glide.load.resource.gif.GifDrawable$GifState
            r16 = r5
            r5 = r16
            r6 = r16
            r7 = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r7 = r7.state
            com.bumptech.glide.gifdecoder.GifHeader r7 = r7.gifHeader
            r8 = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r8 = r8.state
            byte[] r8 = r8.data
            r9 = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r9 = r9.state
            android.content.Context r9 = r9.context
            r10 = r3
            r11 = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r11 = r11.state
            int r11 = r11.targetWidth
            r12 = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r12 = r12.state
            int r12 = r12.targetHeight
            r13 = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r13 = r13.state
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r13 = r13.bitmapProvider
            r14 = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$GifState r14 = r14.state
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r14 = r14.bitmapPool
            r15 = r2
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r4.<init>(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.GifDrawable.<init>(com.bumptech.glide.load.resource.gif.GifDrawable, android.graphics.Bitmap, com.bumptech.glide.load.Transformation):void");
    }

    GifDrawable(GifState gifState) {
        Rect rect;
        GifDecoder gifDecoder;
        Paint paint2;
        GifFrameLoader gifFrameLoader;
        Throwable th;
        GifState state2 = gifState;
        new Rect();
        this.destRect = rect;
        this.isVisible = true;
        this.maxLoopCount = -1;
        if (state2 == null) {
            Throwable th2 = th;
            new NullPointerException("GifState must not be null");
            throw th2;
        }
        this.state = state2;
        new GifDecoder(state2.bitmapProvider);
        this.decoder = gifDecoder;
        new Paint();
        this.paint = paint2;
        this.decoder.setData(state2.gifHeader, state2.data);
        new GifFrameLoader(state2.context, this, this.decoder, state2.targetWidth, state2.targetHeight);
        this.frameLoader = gifFrameLoader;
        this.frameLoader.setFrameTransformation(state2.frameTransformation);
    }

    GifDrawable(GifDecoder decoder2, GifFrameLoader frameLoader2, Bitmap firstFrame, BitmapPool bitmapPool, Paint paint2) {
        Rect rect;
        GifState gifState;
        new Rect();
        this.destRect = rect;
        this.isVisible = true;
        this.maxLoopCount = -1;
        this.decoder = decoder2;
        this.frameLoader = frameLoader2;
        new GifState((GifState) null);
        this.state = gifState;
        this.paint = paint2;
        this.state.bitmapPool = bitmapPool;
        this.state.firstFrame = firstFrame;
    }

    public Bitmap getFirstFrame() {
        return this.state.firstFrame;
    }

    public void setFrameTransformation(Transformation<Bitmap> transformation, Bitmap bitmap) {
        Throwable th;
        Throwable th2;
        Transformation<Bitmap> frameTransformation = transformation;
        Bitmap firstFrame = bitmap;
        if (firstFrame == null) {
            Throwable th3 = th2;
            new NullPointerException("The first frame of the GIF must not be null");
            throw th3;
        } else if (frameTransformation == null) {
            Throwable th4 = th;
            new NullPointerException("The frame transformation must not be null");
            throw th4;
        } else {
            this.state.frameTransformation = frameTransformation;
            this.state.firstFrame = firstFrame;
            this.frameLoader.setFrameTransformation(frameTransformation);
        }
    }

    public GifDecoder getDecoder() {
        return this.decoder;
    }

    public Transformation<Bitmap> getFrameTransformation() {
        return this.state.frameTransformation;
    }

    public byte[] getData() {
        return this.state.data;
    }

    public int getFrameCount() {
        return this.decoder.getFrameCount();
    }

    private void resetLoopCount() {
        this.loopCount = 0;
    }

    public void start() {
        this.isStarted = true;
        resetLoopCount();
        if (this.isVisible) {
            startRunning();
        }
    }

    public void stop() {
        this.isStarted = false;
        stopRunning();
        if (Build.VERSION.SDK_INT < 11) {
            reset();
        }
    }

    private void reset() {
        this.frameLoader.clear();
        invalidateSelf();
    }

    private void startRunning() {
        if (this.decoder.getFrameCount() == 1) {
            invalidateSelf();
        } else if (!this.isRunning) {
            this.isRunning = true;
            this.frameLoader.start();
            invalidateSelf();
        }
    }

    private void stopRunning() {
        this.isRunning = false;
        this.frameLoader.stop();
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = z;
        boolean restart = z2;
        this.isVisible = visible;
        if (!visible) {
            stopRunning();
        } else if (this.isStarted) {
            startRunning();
        }
        return super.setVisible(visible, restart);
    }

    public int getIntrinsicWidth() {
        return this.state.firstFrame.getWidth();
    }

    public int getIntrinsicHeight() {
        return this.state.firstFrame.getHeight();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    /* access modifiers changed from: package-private */
    public void setIsRunning(boolean isRunning2) {
        boolean z = isRunning2;
        this.isRunning = z;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.applyGravity = true;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (!this.isRecycled) {
            if (this.applyGravity) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), this.destRect);
                this.applyGravity = false;
            }
            Bitmap currentFrame = this.frameLoader.getCurrentFrame();
            canvas2.drawBitmap(currentFrame != null ? currentFrame : this.state.firstFrame, (Rect) null, this.destRect, this.paint);
        }
    }

    public void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ColorFilter colorFilter2 = this.paint.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -2;
    }

    @TargetApi(11)
    public void onFrameReady(int i) {
        int frameIndex = i;
        if (Build.VERSION.SDK_INT < 11 || getCallback() != null) {
            invalidateSelf();
            if (frameIndex == this.decoder.getFrameCount() - 1) {
                this.loopCount++;
            }
            if (this.maxLoopCount != -1 && this.loopCount >= this.maxLoopCount) {
                stop();
                return;
            }
            return;
        }
        stop();
        reset();
    }

    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    public void recycle() {
        this.isRecycled = true;
        boolean put = this.state.bitmapPool.put(this.state.firstFrame);
        this.frameLoader.clear();
        this.frameLoader.stop();
    }

    /* access modifiers changed from: package-private */
    public boolean isRecycled() {
        return this.isRecycled;
    }

    public boolean isAnimated() {
        return true;
    }

    public void setLoopCount(int i) {
        Throwable th;
        int loopCount2 = i;
        if (loopCount2 <= 0 && loopCount2 != -1 && loopCount2 != 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
            throw th2;
        } else if (loopCount2 == 0) {
            int intrinsicCount = this.decoder.getTotalIterationCount();
            this.maxLoopCount = intrinsicCount == 0 ? -1 : intrinsicCount;
        } else {
            this.maxLoopCount = loopCount2;
        }
    }

    static class GifState extends Drawable.ConstantState {
        private static final int GRAVITY = 119;
        BitmapPool bitmapPool;
        GifDecoder.BitmapProvider bitmapProvider;
        Context context;
        byte[] data;
        Bitmap firstFrame;
        Transformation<Bitmap> frameTransformation;
        GifHeader gifHeader;
        int targetHeight;
        int targetWidth;

        public GifState(GifHeader gifHeader2, byte[] bArr, Context context2, Transformation<Bitmap> transformation, int i, int i2, GifDecoder.BitmapProvider bitmapProvider2, BitmapPool bitmapPool2, Bitmap bitmap) {
            Throwable th;
            GifHeader header = gifHeader2;
            byte[] data2 = bArr;
            Context context3 = context2;
            Transformation<Bitmap> frameTransformation2 = transformation;
            int targetWidth2 = i;
            int targetHeight2 = i2;
            GifDecoder.BitmapProvider provider = bitmapProvider2;
            BitmapPool bitmapPool3 = bitmapPool2;
            Bitmap firstFrame2 = bitmap;
            if (firstFrame2 == null) {
                Throwable th2 = th;
                new NullPointerException("The first frame of the GIF must not be null");
                throw th2;
            }
            this.gifHeader = header;
            this.data = data2;
            this.bitmapPool = bitmapPool3;
            this.firstFrame = firstFrame2;
            this.context = context3.getApplicationContext();
            this.frameTransformation = frameTransformation2;
            this.targetWidth = targetWidth2;
            this.targetHeight = targetHeight2;
            this.bitmapProvider = provider;
        }

        public GifState(GifState gifState) {
            GifState original = gifState;
            if (original != null) {
                this.gifHeader = original.gifHeader;
                this.data = original.data;
                this.context = original.context;
                this.frameTransformation = original.frameTransformation;
                this.targetWidth = original.targetWidth;
                this.targetHeight = original.targetHeight;
                this.bitmapProvider = original.bitmapProvider;
                this.bitmapPool = original.bitmapPool;
                this.firstFrame = original.firstFrame;
            }
        }

        public Drawable newDrawable(Resources resources) {
            Resources resources2 = resources;
            return newDrawable();
        }

        public Drawable newDrawable() {
            Drawable drawable;
            new GifDrawable(this);
            return drawable;
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }
}
