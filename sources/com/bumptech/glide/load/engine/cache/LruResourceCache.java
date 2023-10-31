package com.bumptech.glide.load.engine.cache;

import android.annotation.SuppressLint;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.util.LruCache;

public class LruResourceCache extends LruCache<Key, Resource<?>> implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    public /* bridge */ /* synthetic */ Resource put(Key x0, Resource x1) {
        return (Resource) super.put(x0, x1);
    }

    public /* bridge */ /* synthetic */ Resource remove(Key x0) {
        return (Resource) super.remove(x0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LruResourceCache(int size) {
        super(size);
    }

    public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener listener2) {
        MemoryCache.ResourceRemovedListener resourceRemovedListener = listener2;
        this.listener = resourceRemovedListener;
    }

    /* access modifiers changed from: protected */
    public void onItemEvicted(Key key, Resource<?> resource) {
        Key key2 = key;
        Resource<?> item = resource;
        if (this.listener != null) {
            this.listener.onResourceRemoved(item);
        }
    }

    /* access modifiers changed from: protected */
    public int getSize(Resource<?> item) {
        return item.getSize();
    }

    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        int level = i;
        if (level >= 60) {
            clearMemory();
        } else if (level >= 40) {
            trimToSize(getCurrentSize() / 2);
        }
    }
}
