package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
import java.io.IOException;

class CacheLoader {
    private static final String TAG = "CacheLoader";
    private final DiskCache diskCache;

    public CacheLoader(DiskCache diskCache2) {
        this.diskCache = diskCache2;
    }

    public <Z> Resource<Z> load(Key key, ResourceDecoder<File, Z> resourceDecoder, int i, int i2) {
        Key key2 = key;
        ResourceDecoder<File, Z> decoder = resourceDecoder;
        int width = i;
        int height = i2;
        File fromCache = this.diskCache.get(key2);
        if (fromCache == null) {
            return null;
        }
        Resource<Z> result = null;
        try {
            result = decoder.decode(fromCache, width, height);
        } catch (IOException e) {
            IOException e2 = e;
            if (Log.isLoggable(TAG, 3)) {
                int d = Log.d(TAG, "Exception decoding image from cache", e2);
            }
        }
        if (result == null) {
            if (Log.isLoggable(TAG, 3)) {
                int d2 = Log.d(TAG, "Failed to decode image from cache or not present in cache");
            }
            this.diskCache.delete(key2);
        }
        return result;
    }
}
