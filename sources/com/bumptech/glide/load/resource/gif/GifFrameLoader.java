package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.NullEncoder;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

class GifFrameLoader {
    private final FrameCallback callback;
    private DelayTarget current;
    private final GifDecoder gifDecoder;
    private final Handler handler;
    private boolean isCleared;
    private boolean isLoadPending;
    private boolean isRunning;
    private GenericRequestBuilder<GifDecoder, GifDecoder, Bitmap, Bitmap> requestBuilder;

    public interface FrameCallback {
        void onFrameReady(int i);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifFrameLoader(android.content.Context r16, com.bumptech.glide.load.resource.gif.GifFrameLoader.FrameCallback r17, com.bumptech.glide.gifdecoder.GifDecoder r18, int r19, int r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r0
            r7 = r2
            r8 = r3
            r9 = 0
            r10 = r1
            r11 = r3
            r12 = r4
            r13 = r5
            r14 = r1
            com.bumptech.glide.Glide r14 = com.bumptech.glide.Glide.get(r14)
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r14 = r14.getBitmapPool()
            com.bumptech.glide.GenericRequestBuilder r10 = getRequestBuilder(r10, r11, r12, r13, r14)
            r6.<init>(r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.GifFrameLoader.<init>(android.content.Context, com.bumptech.glide.load.resource.gif.GifFrameLoader$FrameCallback, com.bumptech.glide.gifdecoder.GifDecoder, int, int):void");
    }

    GifFrameLoader(FrameCallback frameCallback, GifDecoder gifDecoder2, Handler handler2, GenericRequestBuilder<GifDecoder, GifDecoder, Bitmap, Bitmap> genericRequestBuilder) {
        Handler handler3;
        Handler.Callback callback2;
        FrameCallback callback3 = frameCallback;
        GifDecoder gifDecoder3 = gifDecoder2;
        Handler handler4 = handler2;
        GenericRequestBuilder<GifDecoder, GifDecoder, Bitmap, Bitmap> requestBuilder2 = genericRequestBuilder;
        this.isRunning = false;
        this.isLoadPending = false;
        if (handler4 == null) {
            new FrameLoaderCallback(this, (C15321) null);
            new Handler(Looper.getMainLooper(), callback2);
            handler4 = handler3;
        }
        this.callback = callback3;
        this.gifDecoder = gifDecoder3;
        this.handler = handler4;
        this.requestBuilder = requestBuilder2;
    }

    public void setFrameTransformation(Transformation<Bitmap> transformation) {
        Throwable th;
        Transformation<Bitmap> transformation2 = transformation;
        if (transformation2 == null) {
            Throwable th2 = th;
            new NullPointerException("Transformation must not be null");
            throw th2;
        }
        this.requestBuilder = this.requestBuilder.transform(transformation2);
    }

    public void start() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.isCleared = false;
            loadNextFrame();
        }
    }

    public void stop() {
        this.isRunning = false;
    }

    public void clear() {
        stop();
        if (this.current != null) {
            Glide.clear((Target<?>) this.current);
            this.current = null;
        }
        this.isCleared = true;
    }

    public Bitmap getCurrentFrame() {
        return this.current != null ? this.current.getResource() : null;
    }

    private void loadNextFrame() {
        Target target;
        Key key;
        if (this.isRunning && !this.isLoadPending) {
            this.isLoadPending = true;
            long targetTime = SystemClock.uptimeMillis() + ((long) this.gifDecoder.getNextDelay());
            this.gifDecoder.advance();
            new DelayTarget(this.handler, this.gifDecoder.getCurrentFrameIndex(), targetTime);
            new FrameSignature();
            Target into = this.requestBuilder.signature(key).into(target);
        }
    }

    /* access modifiers changed from: package-private */
    public void onFrameReady(DelayTarget delayTarget) {
        DelayTarget delayTarget2 = delayTarget;
        if (this.isCleared) {
            this.handler.obtainMessage(2, delayTarget2).sendToTarget();
            return;
        }
        DelayTarget previous = this.current;
        this.current = delayTarget2;
        this.callback.onFrameReady(delayTarget2.index);
        if (previous != null) {
            this.handler.obtainMessage(2, previous).sendToTarget();
        }
        this.isLoadPending = false;
        loadNextFrame();
    }

    private class FrameLoaderCallback implements Handler.Callback {
        public static final int MSG_CLEAR = 2;
        public static final int MSG_DELAY = 1;
        final /* synthetic */ GifFrameLoader this$0;

        private FrameLoaderCallback(GifFrameLoader gifFrameLoader) {
            this.this$0 = gifFrameLoader;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ FrameLoaderCallback(GifFrameLoader x0, C15321 r7) {
            this(x0);
            C15321 r2 = r7;
        }

        public boolean handleMessage(Message message) {
            Message msg = message;
            if (msg.what == 1) {
                this.this$0.onFrameReady((DelayTarget) msg.obj);
                return true;
            }
            if (msg.what == 2) {
                Glide.clear((Target<?>) (DelayTarget) msg.obj);
            }
            return false;
        }
    }

    static class DelayTarget extends SimpleTarget<Bitmap> {
        private final Handler handler;
        /* access modifiers changed from: private */
        public final int index;
        private Bitmap resource;
        private final long targetTime;

        public DelayTarget(Handler handler2, int index2, long targetTime2) {
            this.handler = handler2;
            this.index = index2;
            this.targetTime = targetTime2;
        }

        public Bitmap getResource() {
            return this.resource;
        }

        public void onResourceReady(Bitmap resource2, GlideAnimation<? super Bitmap> glideAnimation) {
            GlideAnimation<? super Bitmap> glideAnimation2 = glideAnimation;
            this.resource = resource2;
            boolean sendMessageAtTime = this.handler.sendMessageAtTime(this.handler.obtainMessage(1, this), this.targetTime);
        }
    }

    private static GenericRequestBuilder<GifDecoder, GifDecoder, Bitmap, Bitmap> getRequestBuilder(Context context, GifDecoder gifDecoder2, int width, int height, BitmapPool bitmapPool) {
        ResourceDecoder resourceDecoder;
        ModelLoader modelLoader;
        new GifFrameResourceDecoder(bitmapPool);
        ResourceDecoder resourceDecoder2 = resourceDecoder;
        new GifFrameModelLoader();
        ModelLoader modelLoader2 = modelLoader;
        return Glide.with(context).using(modelLoader2, GifDecoder.class).load(gifDecoder2).mo23786as(Bitmap.class).sourceEncoder(NullEncoder.get()).decoder(resourceDecoder2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).override(width, height);
    }

    static class FrameSignature implements Key {
        private final UUID uuid;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public FrameSignature() {
            this(UUID.randomUUID());
        }

        FrameSignature(UUID uuid2) {
            this.uuid = uuid2;
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (o instanceof FrameSignature) {
                return ((FrameSignature) o).uuid.equals(this.uuid);
            }
            return false;
        }

        public int hashCode() {
            return this.uuid.hashCode();
        }

        public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
            Throwable th;
            MessageDigest messageDigest2 = messageDigest;
            Throwable th2 = th;
            new UnsupportedOperationException("Not implemented");
            throw th2;
        }
    }
}
