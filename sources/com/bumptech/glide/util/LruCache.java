package com.bumptech.glide.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<T, Y> {
    private final LinkedHashMap<T, Y> cache;
    private int currentSize = 0;
    private final int initialMaxSize;
    private int maxSize;

    public LruCache(int i) {
        LinkedHashMap<T, Y> linkedHashMap;
        int size = i;
        new LinkedHashMap<>(100, 0.75f, true);
        this.cache = linkedHashMap;
        this.initialMaxSize = size;
        this.maxSize = size;
    }

    public void setSizeMultiplier(float f) {
        Throwable th;
        float multiplier = f;
        if (multiplier < 0.0f) {
            Throwable th2 = th;
            new IllegalArgumentException("Multiplier must be >= 0");
            throw th2;
        }
        this.maxSize = Math.round(((float) this.initialMaxSize) * multiplier);
        evict();
    }

    /* access modifiers changed from: protected */
    public int getSize(Y y) {
        Y y2 = y;
        return 1;
    }

    /* access modifiers changed from: protected */
    public void onItemEvicted(T t, Y y) {
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public boolean contains(T key) {
        return this.cache.containsKey(key);
    }

    public Y get(T key) {
        return this.cache.get(key);
    }

    public Y put(T t, Y y) {
        T key = t;
        Y item = y;
        if (getSize(item) >= this.maxSize) {
            onItemEvicted(key, item);
            return null;
        }
        LruCache<T, Y> result = this.cache.put(key, item);
        if (item != null) {
            this.currentSize += getSize(item);
        }
        if (result != null) {
            this.currentSize -= getSize(result);
        }
        evict();
        return result;
    }

    public Y remove(T key) {
        LruCache<T, Y> value = this.cache.remove(key);
        if (value != null) {
            this.currentSize -= getSize(value);
        }
        return value;
    }

    public void clearMemory() {
        trimToSize(0);
    }

    /* access modifiers changed from: protected */
    public void trimToSize(int i) {
        int size = i;
        while (this.currentSize > size) {
            Map.Entry<T, Y> last = this.cache.entrySet().iterator().next();
            Y toRemove = last.getValue();
            this.currentSize -= getSize(toRemove);
            T key = last.getKey();
            Object remove = this.cache.remove(key);
            onItemEvicted(key, toRemove);
        }
    }

    private void evict() {
        trimToSize(this.maxSize);
    }
}
