package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LruBitmapPool implements BitmapPool {
    private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    private static final String TAG = "LruBitmapPool";
    private final Set<Bitmap.Config> allowedConfigs;
    private int currentSize;
    private int evictions;
    private int hits;
    private final int initialMaxSize;
    private int maxSize;
    private int misses;
    private int puts;
    private final LruPoolStrategy strategy;
    private final BitmapTracker tracker;

    private interface BitmapTracker {
        void add(Bitmap bitmap);

        void remove(Bitmap bitmap);
    }

    LruBitmapPool(int i, LruPoolStrategy strategy2, Set<Bitmap.Config> allowedConfigs2) {
        BitmapTracker bitmapTracker;
        int maxSize2 = i;
        this.initialMaxSize = maxSize2;
        this.maxSize = maxSize2;
        this.strategy = strategy2;
        this.allowedConfigs = allowedConfigs2;
        new NullBitmapTracker((C15131) null);
        this.tracker = bitmapTracker;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LruBitmapPool(int maxSize2) {
        this(maxSize2, getDefaultStrategy(), getDefaultAllowedConfigs());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LruBitmapPool(int maxSize2, Set<Bitmap.Config> allowedConfigs2) {
        this(maxSize2, getDefaultStrategy(), allowedConfigs2);
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setSizeMultiplier(float f) {
        float sizeMultiplier = f;
        synchronized (this) {
            this.maxSize = Math.round(((float) this.initialMaxSize) * sizeMultiplier);
            evict();
        }
    }

    public synchronized boolean put(Bitmap bitmap) {
        boolean z;
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th;
        Bitmap bitmap2 = bitmap;
        synchronized (this) {
            if (bitmap2 == null) {
                Throwable th2 = th;
                new NullPointerException("Bitmap must not be null");
                throw th2;
            } else if (!bitmap2.isMutable() || this.strategy.getSize(bitmap2) > this.maxSize || !this.allowedConfigs.contains(bitmap2.getConfig())) {
                if (Log.isLoggable(TAG, 2)) {
                    new StringBuilder();
                    int v = Log.v(TAG, sb.append("Reject bitmap from pool, bitmap: ").append(this.strategy.logBitmap(bitmap2)).append(", is mutable: ").append(bitmap2.isMutable()).append(", is allowed config: ").append(this.allowedConfigs.contains(bitmap2.getConfig())).toString());
                }
                z = false;
            } else {
                int size = this.strategy.getSize(bitmap2);
                this.strategy.put(bitmap2);
                this.tracker.add(bitmap2);
                this.puts++;
                this.currentSize += size;
                if (Log.isLoggable(TAG, 2)) {
                    new StringBuilder();
                    int v2 = Log.v(TAG, sb2.append("Put bitmap in pool=").append(this.strategy.logBitmap(bitmap2)).toString());
                }
                dump();
                evict();
                z = true;
            }
        }
        return z;
    }

    private void evict() {
        trimToSize(this.maxSize);
    }

    public synchronized Bitmap get(int i, int i2, Bitmap.Config config) {
        Bitmap bitmap;
        int width = i;
        int height = i2;
        Bitmap.Config config2 = config;
        synchronized (this) {
            Bitmap result = getDirty(width, height, config2);
            if (result != null) {
                result.eraseColor(0);
            }
            bitmap = result;
        }
        return bitmap;
    }

    @TargetApi(12)
    public synchronized Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        Bitmap bitmap;
        StringBuilder sb;
        StringBuilder sb2;
        int width = i;
        int height = i2;
        Bitmap.Config config2 = config;
        synchronized (this) {
            Bitmap result = this.strategy.get(width, height, config2 != null ? config2 : DEFAULT_CONFIG);
            if (result == null) {
                if (Log.isLoggable(TAG, 3)) {
                    new StringBuilder();
                    int d = Log.d(TAG, sb2.append("Missing bitmap=").append(this.strategy.logBitmap(width, height, config2)).toString());
                }
                this.misses++;
            } else {
                this.hits++;
                this.currentSize -= this.strategy.getSize(result);
                this.tracker.remove(result);
                if (Build.VERSION.SDK_INT >= 12) {
                    result.setHasAlpha(true);
                }
            }
            if (Log.isLoggable(TAG, 2)) {
                new StringBuilder();
                int v = Log.v(TAG, sb.append("Get bitmap=").append(this.strategy.logBitmap(width, height, config2)).toString());
            }
            dump();
            bitmap = result;
        }
        return bitmap;
    }

    public void clearMemory() {
        if (Log.isLoggable(TAG, 3)) {
            int d = Log.d(TAG, "clearMemory");
        }
        trimToSize(0);
    }

    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        StringBuilder sb;
        int level = i;
        if (Log.isLoggable(TAG, 3)) {
            new StringBuilder();
            int d = Log.d(TAG, sb.append("trimMemory, level=").append(level).toString());
        }
        if (level >= 60) {
            clearMemory();
        } else if (level >= 40) {
            trimToSize(this.maxSize / 2);
        }
    }

    private synchronized void trimToSize(int i) {
        StringBuilder sb;
        int size = i;
        synchronized (this) {
            while (true) {
                if (this.currentSize <= size) {
                    break;
                }
                Bitmap removed = this.strategy.removeLast();
                if (removed == null) {
                    if (Log.isLoggable(TAG, 5)) {
                        int w = Log.w(TAG, "Size mismatch, resetting");
                        dumpUnchecked();
                    }
                    this.currentSize = 0;
                } else {
                    this.tracker.remove(removed);
                    this.currentSize -= this.strategy.getSize(removed);
                    removed.recycle();
                    this.evictions++;
                    if (Log.isLoggable(TAG, 3)) {
                        new StringBuilder();
                        int d = Log.d(TAG, sb.append("Evicting bitmap=").append(this.strategy.logBitmap(removed)).toString());
                    }
                    dump();
                }
            }
        }
    }

    private void dump() {
        if (Log.isLoggable(TAG, 2)) {
            dumpUnchecked();
        }
    }

    private void dumpUnchecked() {
        StringBuilder sb;
        new StringBuilder();
        int v = Log.v(TAG, sb.append("Hits=").append(this.hits).append(", misses=").append(this.misses).append(", puts=").append(this.puts).append(", evictions=").append(this.evictions).append(", currentSize=").append(this.currentSize).append(", maxSize=").append(this.maxSize).append("\nStrategy=").append(this.strategy).toString());
    }

    private static LruPoolStrategy getDefaultStrategy() {
        LruPoolStrategy lruPoolStrategy;
        LruPoolStrategy strategy2;
        LruPoolStrategy lruPoolStrategy2;
        if (Build.VERSION.SDK_INT >= 19) {
            new SizeConfigStrategy();
            strategy2 = lruPoolStrategy2;
        } else {
            new AttributeStrategy();
            strategy2 = lruPoolStrategy;
        }
        return strategy2;
    }

    private static Set<Bitmap.Config> getDefaultAllowedConfigs() {
        Set<Bitmap.Config> set;
        new HashSet<>();
        Set<Bitmap.Config> configs = set;
        boolean addAll = configs.addAll(Arrays.asList(Bitmap.Config.values()));
        if (Build.VERSION.SDK_INT >= 19) {
            boolean add = configs.add((Object) null);
        }
        return Collections.unmodifiableSet(configs);
    }

    private static class ThrowingBitmapTracker implements BitmapTracker {
        private final Set<Bitmap> bitmaps;

        private ThrowingBitmapTracker() {
            Set set;
            new HashSet();
            this.bitmaps = Collections.synchronizedSet(set);
        }

        public void add(Bitmap bitmap) {
            Throwable th;
            StringBuilder sb;
            Bitmap bitmap2 = bitmap;
            if (this.bitmaps.contains(bitmap2)) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Can't add already added bitmap: ").append(bitmap2).append(" [").append(bitmap2.getWidth()).append("x").append(bitmap2.getHeight()).append("]").toString());
                throw th2;
            }
            boolean add = this.bitmaps.add(bitmap2);
        }

        public void remove(Bitmap bitmap) {
            Throwable th;
            Bitmap bitmap2 = bitmap;
            if (!this.bitmaps.contains(bitmap2)) {
                Throwable th2 = th;
                new IllegalStateException("Cannot remove bitmap not in tracker");
                throw th2;
            }
            boolean remove = this.bitmaps.remove(bitmap2);
        }
    }

    private static class NullBitmapTracker implements BitmapTracker {
        private NullBitmapTracker() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ NullBitmapTracker(C15131 r4) {
            this();
            C15131 r1 = r4;
        }

        public void add(Bitmap bitmap) {
        }

        public void remove(Bitmap bitmap) {
        }
    }
}
