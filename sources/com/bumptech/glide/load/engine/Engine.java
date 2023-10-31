package com.bumptech.glide.load.engine;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.DataLoadProvider;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class Engine implements EngineJobListener, MemoryCache.ResourceRemovedListener, EngineResource.ResourceListener {
    private static final String TAG = "Engine";
    private final Map<Key, WeakReference<EngineResource<?>>> activeResources;
    private final MemoryCache cache;
    private final LazyDiskCacheProvider diskCacheProvider;
    private final EngineJobFactory engineJobFactory;
    private final Map<Key, EngineJob> jobs;
    private final EngineKeyFactory keyFactory;
    private final ResourceRecycler resourceRecycler;
    private ReferenceQueue<EngineResource<?>> resourceReferenceQueue;

    public static class LoadStatus {

        /* renamed from: cb */
        private final ResourceCallback f308cb;
        private final EngineJob engineJob;

        public LoadStatus(ResourceCallback cb, EngineJob engineJob2) {
            this.f308cb = cb;
            this.engineJob = engineJob2;
        }

        public void cancel() {
            this.engineJob.removeCallback(this.f308cb);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Engine(MemoryCache memoryCache, DiskCache.Factory diskCacheFactory, ExecutorService diskCacheService, ExecutorService sourceService) {
        this(memoryCache, diskCacheFactory, diskCacheService, sourceService, (Map<Key, EngineJob>) null, (EngineKeyFactory) null, (Map<Key, WeakReference<EngineResource<?>>>) null, (EngineJobFactory) null, (ResourceRecycler) null);
    }

    Engine(MemoryCache memoryCache, DiskCache.Factory diskCacheFactory, ExecutorService executorService, ExecutorService executorService2, Map<Key, EngineJob> map, EngineKeyFactory engineKeyFactory, Map<Key, WeakReference<EngineResource<?>>> map2, EngineJobFactory engineJobFactory2, ResourceRecycler resourceRecycler2) {
        LazyDiskCacheProvider lazyDiskCacheProvider;
        ResourceRecycler resourceRecycler3;
        EngineJobFactory engineJobFactory3;
        Map<Key, EngineJob> map3;
        EngineKeyFactory engineKeyFactory2;
        Map<Key, WeakReference<EngineResource<?>>> map4;
        MemoryCache cache2 = memoryCache;
        ExecutorService diskCacheService = executorService;
        ExecutorService sourceService = executorService2;
        Map<Key, EngineJob> jobs2 = map;
        EngineKeyFactory keyFactory2 = engineKeyFactory;
        Map<Key, WeakReference<EngineResource<?>>> activeResources2 = map2;
        EngineJobFactory engineJobFactory4 = engineJobFactory2;
        ResourceRecycler resourceRecycler4 = resourceRecycler2;
        this.cache = cache2;
        new LazyDiskCacheProvider(diskCacheFactory);
        this.diskCacheProvider = lazyDiskCacheProvider;
        if (activeResources2 == null) {
            new HashMap();
            activeResources2 = map4;
        }
        this.activeResources = activeResources2;
        if (keyFactory2 == null) {
            new EngineKeyFactory();
            keyFactory2 = engineKeyFactory2;
        }
        this.keyFactory = keyFactory2;
        if (jobs2 == null) {
            new HashMap();
            jobs2 = map3;
        }
        this.jobs = jobs2;
        if (engineJobFactory4 == null) {
            new EngineJobFactory(diskCacheService, sourceService, this);
            engineJobFactory4 = engineJobFactory3;
        }
        this.engineJobFactory = engineJobFactory4;
        if (resourceRecycler4 == null) {
            new ResourceRecycler();
            resourceRecycler4 = resourceRecycler3;
        }
        this.resourceRecycler = resourceRecycler4;
        cache2.setResourceRemovedListener(this);
    }

    public <T, Z, R> LoadStatus load(Key signature, int i, int i2, DataFetcher<T> dataFetcher, DataLoadProvider<T, Z> dataLoadProvider, Transformation<Z> transformation, ResourceTranscoder<Z, R> resourceTranscoder, Priority priority, boolean z, DiskCacheStrategy diskCacheStrategy, ResourceCallback resourceCallback) {
        DecodeJob decodeJob;
        EngineRunnable runnable;
        LoadStatus loadStatus;
        LoadStatus loadStatus2;
        int width = i;
        int height = i2;
        DataFetcher<T> fetcher = dataFetcher;
        DataLoadProvider<T, Z> loadProvider = dataLoadProvider;
        Transformation<Z> transformation2 = transformation;
        ResourceTranscoder<Z, R> transcoder = resourceTranscoder;
        Priority priority2 = priority;
        boolean isMemoryCacheable = z;
        DiskCacheStrategy diskCacheStrategy2 = diskCacheStrategy;
        ResourceCallback cb = resourceCallback;
        Util.assertMainThread();
        long startTime = LogTime.getLogTime();
        EngineKey key = this.keyFactory.buildKey(fetcher.getId(), signature, width, height, loadProvider.getCacheDecoder(), loadProvider.getSourceDecoder(), transformation2, loadProvider.getEncoder(), transcoder, loadProvider.getSourceEncoder());
        EngineResource<?> cached = loadFromCache(key, isMemoryCacheable);
        if (cached != null) {
            cb.onResourceReady(cached);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Loaded resource from cache", startTime, key);
            }
            return null;
        }
        EngineResource<?> active = loadFromActiveResources(key, isMemoryCacheable);
        if (active != null) {
            cb.onResourceReady(active);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Loaded resource from active resources", startTime, key);
            }
            return null;
        }
        EngineJob current = this.jobs.get(key);
        if (current != null) {
            current.addCallback(cb);
            if (Log.isLoggable(TAG, 2)) {
                logWithTimeAndKey("Added to existing load", startTime, key);
            }
            new LoadStatus(cb, current);
            return loadStatus2;
        }
        EngineJob engineJob = this.engineJobFactory.build(key, isMemoryCacheable);
        new DecodeJob(key, width, height, fetcher, loadProvider, transformation2, transcoder, this.diskCacheProvider, diskCacheStrategy2, priority2);
        new EngineRunnable(engineJob, decodeJob, priority2);
        EngineJob put = this.jobs.put(key, engineJob);
        engineJob.addCallback(cb);
        engineJob.start(runnable);
        if (Log.isLoggable(TAG, 2)) {
            logWithTimeAndKey("Started new load", startTime, key);
        }
        new LoadStatus(cb, engineJob);
        return loadStatus;
    }

    private static void logWithTimeAndKey(String log, long startTime, Key key) {
        StringBuilder sb;
        new StringBuilder();
        int v = Log.v(TAG, sb.append(log).append(" in ").append(LogTime.getElapsedMillis(startTime)).append("ms, key: ").append(key).toString());
    }

    private EngineResource<?> loadFromActiveResources(Key key, boolean isMemoryCacheable) {
        Key key2 = key;
        if (!isMemoryCacheable) {
            return null;
        }
        EngineResource<?> active = null;
        WeakReference<EngineResource<?>> activeRef = this.activeResources.get(key2);
        if (activeRef != null) {
            active = (EngineResource) activeRef.get();
            if (active != null) {
                active.acquire();
            } else {
                WeakReference<EngineResource<?>> remove = this.activeResources.remove(key2);
            }
        }
        return active;
    }

    private EngineResource<?> loadFromCache(Key key, boolean isMemoryCacheable) {
        Object obj;
        Key key2 = key;
        if (!isMemoryCacheable) {
            return null;
        }
        EngineResource<?> cached = getEngineResourceFromCache(key2);
        if (cached != null) {
            cached.acquire();
            new ResourceWeakReference(key2, cached, getReferenceQueue());
            WeakReference<EngineResource<?>> put = this.activeResources.put(key2, obj);
        }
        return cached;
    }

    private EngineResource<?> getEngineResourceFromCache(Key key) {
        EngineResource engineResource;
        EngineResource result;
        Resource<?> cached = this.cache.remove(key);
        if (cached == null) {
            result = null;
        } else if (cached instanceof EngineResource) {
            result = (EngineResource) cached;
        } else {
            new EngineResource(cached, true);
            result = engineResource;
        }
        return result;
    }

    public void release(Resource resource) {
        Throwable th;
        Resource resource2 = resource;
        Util.assertMainThread();
        if (resource2 instanceof EngineResource) {
            ((EngineResource) resource2).release();
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Cannot release anything but an EngineResource");
        throw th2;
    }

    public void onEngineJobComplete(Key key, EngineResource<?> engineResource) {
        Object obj;
        Key key2 = key;
        EngineResource<?> resource = engineResource;
        Util.assertMainThread();
        if (resource != null) {
            resource.setResourceListener(key2, this);
            if (resource.isCacheable()) {
                new ResourceWeakReference(key2, resource, getReferenceQueue());
                WeakReference<EngineResource<?>> put = this.activeResources.put(key2, obj);
            }
        }
        EngineJob remove = this.jobs.remove(key2);
    }

    public void onEngineJobCancelled(EngineJob engineJob, Key key) {
        Key key2 = key;
        Util.assertMainThread();
        if (engineJob.equals(this.jobs.get(key2))) {
            EngineJob remove = this.jobs.remove(key2);
        }
    }

    public void onResourceRemoved(Resource<?> resource) {
        Util.assertMainThread();
        this.resourceRecycler.recycle(resource);
    }

    public void onResourceReleased(Key key, EngineResource engineResource) {
        Key cacheKey = key;
        EngineResource resource = engineResource;
        Util.assertMainThread();
        WeakReference<EngineResource<?>> remove = this.activeResources.remove(cacheKey);
        if (resource.isCacheable()) {
            Resource<?> put = this.cache.put(cacheKey, resource);
        } else {
            this.resourceRecycler.recycle(resource);
        }
    }

    public void clearDiskCache() {
        this.diskCacheProvider.getDiskCache().clear();
    }

    private ReferenceQueue<EngineResource<?>> getReferenceQueue() {
        ReferenceQueue<EngineResource<?>> referenceQueue;
        MessageQueue.IdleHandler idleHandler;
        if (this.resourceReferenceQueue == null) {
            new ReferenceQueue<>();
            this.resourceReferenceQueue = referenceQueue;
            new RefQueueIdleHandler(this.activeResources, this.resourceReferenceQueue);
            Looper.myQueue().addIdleHandler(idleHandler);
        }
        return this.resourceReferenceQueue;
    }

    private static class LazyDiskCacheProvider implements DecodeJob.DiskCacheProvider {
        private volatile DiskCache diskCache;
        private final DiskCache.Factory factory;

        public LazyDiskCacheProvider(DiskCache.Factory factory2) {
            this.factory = factory2;
        }

        /* JADX INFO: finally extract failed */
        public DiskCache getDiskCache() {
            DiskCache diskCache2;
            if (this.diskCache == null) {
                synchronized (this) {
                    try {
                        if (this.diskCache == null) {
                            this.diskCache = this.factory.build();
                        }
                        if (this.diskCache == null) {
                            new DiskCacheAdapter();
                            this.diskCache = diskCache2;
                        }
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            throw th2;
                        }
                    }
                }
            }
            return this.diskCache;
        }
    }

    private static class ResourceWeakReference extends WeakReference<EngineResource<?>> {
        /* access modifiers changed from: private */
        public final Key key;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResourceWeakReference(Key key2, EngineResource<?> r, ReferenceQueue<? super EngineResource<?>> q) {
            super(r, q);
            this.key = key2;
        }
    }

    private static class RefQueueIdleHandler implements MessageQueue.IdleHandler {
        private final Map<Key, WeakReference<EngineResource<?>>> activeResources;
        private final ReferenceQueue<EngineResource<?>> queue;

        public RefQueueIdleHandler(Map<Key, WeakReference<EngineResource<?>>> activeResources2, ReferenceQueue<EngineResource<?>> queue2) {
            this.activeResources = activeResources2;
            this.queue = queue2;
        }

        public boolean queueIdle() {
            ResourceWeakReference ref = (ResourceWeakReference) this.queue.poll();
            if (ref != null) {
                WeakReference<EngineResource<?>> remove = this.activeResources.remove(ref.key);
            }
            return true;
        }
    }

    static class EngineJobFactory {
        private final ExecutorService diskCacheService;
        private final EngineJobListener listener;
        private final ExecutorService sourceService;

        public EngineJobFactory(ExecutorService diskCacheService2, ExecutorService sourceService2, EngineJobListener listener2) {
            this.diskCacheService = diskCacheService2;
            this.sourceService = sourceService2;
            this.listener = listener2;
        }

        public EngineJob build(Key key, boolean isMemoryCacheable) {
            EngineJob engineJob;
            new EngineJob(key, this.diskCacheService, this.sourceService, isMemoryCacheable, this.listener);
            return engineJob;
        }
    }
}
