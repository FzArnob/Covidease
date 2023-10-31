package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;

public class DiskLruCacheFactory implements DiskCache.Factory {
    private final CacheDirectoryGetter cacheDirectoryGetter;
    private final int diskCacheSize;

    public interface CacheDirectoryGetter {
        File getCacheDirectory();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DiskLruCacheFactory(java.lang.String r9, int r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            com.bumptech.glide.load.engine.cache.DiskLruCacheFactory$1 r4 = new com.bumptech.glide.load.engine.cache.DiskLruCacheFactory$1
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>(r6)
            r5 = r2
            r3.<init>((com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter) r4, (int) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.<init>(java.lang.String, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DiskLruCacheFactory(java.lang.String r11, java.lang.String r12, int r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            com.bumptech.glide.load.engine.cache.DiskLruCacheFactory$2 r5 = new com.bumptech.glide.load.engine.cache.DiskLruCacheFactory$2
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
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.<init>(java.lang.String, java.lang.String, int):void");
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter2, int diskCacheSize2) {
        this.diskCacheSize = diskCacheSize2;
        this.cacheDirectoryGetter = cacheDirectoryGetter2;
    }

    public DiskCache build() {
        File cacheDir = this.cacheDirectoryGetter.getCacheDirectory();
        if (cacheDir == null) {
            return null;
        }
        if (cacheDir.mkdirs() || (cacheDir.exists() && cacheDir.isDirectory())) {
            return DiskLruCacheWrapper.get(cacheDir, this.diskCacheSize);
        }
        return null;
    }
}
