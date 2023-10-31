package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

final class BitmapPreFillRunner implements Runnable {
    static final int BACKOFF_RATIO = 4;
    private static final Clock DEFAULT_CLOCK;
    static final long INITIAL_BACKOFF_MS = 40;
    static final long MAX_BACKOFF_MS = TimeUnit.SECONDS.toMillis(1);
    static final long MAX_DURATION_MS = 32;
    private static final String TAG = "PreFillRunner";
    private final BitmapPool bitmapPool;
    private final Clock clock;
    private long currentDelay;
    private final Handler handler;
    private boolean isCancelled;
    private final MemoryCache memoryCache;
    private final Set<PreFillType> seenTypes;
    private final PreFillQueue toPrefill;

    static {
        Clock clock2;
        new Clock();
        DEFAULT_CLOCK = clock2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BitmapPreFillRunner(com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r14, com.bumptech.glide.load.engine.cache.MemoryCache r15, com.bumptech.glide.load.engine.prefill.PreFillQueue r16) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            com.bumptech.glide.load.engine.prefill.BitmapPreFillRunner$Clock r8 = DEFAULT_CLOCK
            android.os.Handler r9 = new android.os.Handler
            r12 = r9
            r9 = r12
            r10 = r12
            android.os.Looper r11 = android.os.Looper.getMainLooper()
            r10.<init>(r11)
            r4.<init>(r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.prefill.BitmapPreFillRunner.<init>(com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.engine.cache.MemoryCache, com.bumptech.glide.load.engine.prefill.PreFillQueue):void");
    }

    BitmapPreFillRunner(BitmapPool bitmapPool2, MemoryCache memoryCache2, PreFillQueue allocationOrder, Clock clock2, Handler handler2) {
        Set<PreFillType> set;
        new HashSet();
        this.seenTypes = set;
        this.currentDelay = INITIAL_BACKOFF_MS;
        this.bitmapPool = bitmapPool2;
        this.memoryCache = memoryCache2;
        this.toPrefill = allocationOrder;
        this.clock = clock2;
        this.handler = handler2;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    private boolean allocate() {
        StringBuilder sb;
        Key key;
        long start = this.clock.now();
        while (!this.toPrefill.isEmpty() && !isGcDetected(start)) {
            PreFillType toAllocate = this.toPrefill.remove();
            Bitmap bitmap = Bitmap.createBitmap(toAllocate.getWidth(), toAllocate.getHeight(), toAllocate.getConfig());
            if (getFreeMemoryCacheBytes() >= Util.getBitmapByteSize(bitmap)) {
                new UniqueKey((C15241) null);
                Resource<?> put = this.memoryCache.put(key, BitmapResource.obtain(bitmap, this.bitmapPool));
            } else {
                addToBitmapPool(toAllocate, bitmap);
            }
            if (Log.isLoggable(TAG, 3)) {
                new StringBuilder();
                int d = Log.d(TAG, sb.append("allocated [").append(toAllocate.getWidth()).append("x").append(toAllocate.getHeight()).append("] ").append(toAllocate.getConfig()).append(" size: ").append(Util.getBitmapByteSize(bitmap)).toString());
            }
        }
        return !this.isCancelled && !this.toPrefill.isEmpty();
    }

    private boolean isGcDetected(long startTimeMs) {
        return this.clock.now() - startTimeMs >= 32;
    }

    private int getFreeMemoryCacheBytes() {
        return this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize();
    }

    private void addToBitmapPool(PreFillType preFillType, Bitmap bitmap) {
        Bitmap fromPool;
        PreFillType toAllocate = preFillType;
        Bitmap bitmap2 = bitmap;
        if (this.seenTypes.add(toAllocate) && (fromPool = this.bitmapPool.get(toAllocate.getWidth(), toAllocate.getHeight(), toAllocate.getConfig())) != null) {
            boolean put = this.bitmapPool.put(fromPool);
        }
        boolean put2 = this.bitmapPool.put(bitmap2);
    }

    public void run() {
        if (allocate()) {
            boolean postDelayed = this.handler.postDelayed(this, getNextDelay());
        }
    }

    private long getNextDelay() {
        long result = this.currentDelay;
        this.currentDelay = Math.min(this.currentDelay * 4, MAX_BACKOFF_MS);
        return result;
    }

    private static class UniqueKey implements Key {
        private UniqueKey() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ UniqueKey(C15241 r4) {
            this();
            C15241 r1 = r4;
        }

        public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        }
    }

    static class Clock {
        Clock() {
        }

        public long now() {
            return SystemClock.currentThreadTimeMillis();
        }
    }
}
