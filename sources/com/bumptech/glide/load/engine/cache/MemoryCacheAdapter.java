package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;

public class MemoryCacheAdapter implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    public MemoryCacheAdapter() {
    }

    public int getCurrentSize() {
        return 0;
    }

    public int getMaxSize() {
        return 0;
    }

    public void setSizeMultiplier(float multiplier) {
    }

    public Resource<?> remove(Key key) {
        Key key2 = key;
        return null;
    }

    public Resource<?> put(Key key, Resource<?> resource) {
        Key key2 = key;
        this.listener.onResourceRemoved(resource);
        return null;
    }

    public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener listener2) {
        MemoryCache.ResourceRemovedListener resourceRemovedListener = listener2;
        this.listener = resourceRemovedListener;
    }

    public void clearMemory() {
    }

    public void trimMemory(int level) {
    }
}
