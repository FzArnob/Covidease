package com.bumptech.glide;

import android.content.Context;
import android.os.Build;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;
import java.util.concurrent.ExecutorService;

public class GlideBuilder {
    private BitmapPool bitmapPool;
    private final Context context;
    private DecodeFormat decodeFormat;
    private DiskCache.Factory diskCacheFactory;
    private ExecutorService diskCacheService;
    private Engine engine;
    private MemoryCache memoryCache;
    private ExecutorService sourceService;

    public GlideBuilder(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public GlideBuilder setBitmapPool(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
        return this;
    }

    public GlideBuilder setMemoryCache(MemoryCache memoryCache2) {
        this.memoryCache = memoryCache2;
        return this;
    }

    @Deprecated
    public GlideBuilder setDiskCache(DiskCache diskCache) {
        DiskCache.Factory factory;
        final DiskCache diskCache2 = diskCache;
        new DiskCache.Factory(this) {
            final /* synthetic */ GlideBuilder this$0;

            {
                this.this$0 = r6;
            }

            public DiskCache build() {
                return diskCache2;
            }
        };
        return setDiskCache(factory);
    }

    public GlideBuilder setDiskCache(DiskCache.Factory diskCacheFactory2) {
        this.diskCacheFactory = diskCacheFactory2;
        return this;
    }

    public GlideBuilder setResizeService(ExecutorService service) {
        this.sourceService = service;
        return this;
    }

    public GlideBuilder setDiskCacheService(ExecutorService service) {
        this.diskCacheService = service;
        return this;
    }

    public GlideBuilder setDecodeFormat(DecodeFormat decodeFormat2) {
        this.decodeFormat = decodeFormat2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public GlideBuilder setEngine(Engine engine2) {
        this.engine = engine2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Glide createGlide() {
        MemorySizeCalculator memorySizeCalculator;
        Glide glide;
        Engine engine2;
        DiskCache.Factory factory;
        MemoryCache memoryCache2;
        BitmapPool bitmapPool2;
        BitmapPool bitmapPool3;
        ExecutorService executorService;
        ExecutorService executorService2;
        if (this.sourceService == null) {
            new FifoPriorityThreadPoolExecutor(Math.max(1, Runtime.getRuntime().availableProcessors()));
            this.sourceService = executorService2;
        }
        if (this.diskCacheService == null) {
            new FifoPriorityThreadPoolExecutor(1);
            this.diskCacheService = executorService;
        }
        new MemorySizeCalculator(this.context);
        MemorySizeCalculator calculator = memorySizeCalculator;
        if (this.bitmapPool == null) {
            if (Build.VERSION.SDK_INT >= 11) {
                new LruBitmapPool(calculator.getBitmapPoolSize());
                this.bitmapPool = bitmapPool3;
            } else {
                new BitmapPoolAdapter();
                this.bitmapPool = bitmapPool2;
            }
        }
        if (this.memoryCache == null) {
            new LruResourceCache(calculator.getMemoryCacheSize());
            this.memoryCache = memoryCache2;
        }
        if (this.diskCacheFactory == null) {
            new InternalCacheDiskCacheFactory(this.context);
            this.diskCacheFactory = factory;
        }
        if (this.engine == null) {
            new Engine(this.memoryCache, this.diskCacheFactory, this.diskCacheService, this.sourceService);
            this.engine = engine2;
        }
        if (this.decodeFormat == null) {
            this.decodeFormat = DecodeFormat.DEFAULT;
        }
        new Glide(this.engine, this.memoryCache, this.bitmapPool, this.context, this.decodeFormat);
        return glide;
    }
}
