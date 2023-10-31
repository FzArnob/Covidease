package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import com.bumptech.glide.load.engine.cache.DiskCache;

public final class InternalCacheDiskCacheFactory extends DiskLruCacheFactory {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InternalCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InternalCacheDiskCacheFactory(Context context, int diskCacheSize) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, diskCacheSize);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InternalCacheDiskCacheFactory(android.content.Context r11, java.lang.String r12, int r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory$1 r5 = new com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory$1
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r1
            r8 = r2
            r6.<init>(r7, r8)
            r6 = r3
            r4.<init>((com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter) r5, (int) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory.<init>(android.content.Context, java.lang.String, int):void");
    }
}
