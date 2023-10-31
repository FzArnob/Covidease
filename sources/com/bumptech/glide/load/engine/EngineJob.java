package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.EngineRunnable;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class EngineJob implements EngineRunnable.EngineRunnableManager {
    private static final EngineResourceFactory DEFAULT_FACTORY;
    private static final Handler MAIN_THREAD_HANDLER;
    private static final int MSG_COMPLETE = 1;
    private static final int MSG_EXCEPTION = 2;
    private final List<ResourceCallback> cbs;
    private final ExecutorService diskCacheService;
    private EngineResource<?> engineResource;
    private final EngineResourceFactory engineResourceFactory;
    private EngineRunnable engineRunnable;
    private Exception exception;
    private volatile Future<?> future;
    private boolean hasException;
    private boolean hasResource;
    private Set<ResourceCallback> ignoredCallbacks;
    private final boolean isCacheable;
    private boolean isCancelled;
    private final Key key;
    private final EngineJobListener listener;
    private Resource<?> resource;
    private final ExecutorService sourceService;

    static {
        EngineResourceFactory engineResourceFactory2;
        Handler handler;
        Handler.Callback callback;
        new EngineResourceFactory();
        DEFAULT_FACTORY = engineResourceFactory2;
        new MainThreadCallback((C15111) null);
        new Handler(Looper.getMainLooper(), callback);
        MAIN_THREAD_HANDLER = handler;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EngineJob(Key key2, ExecutorService diskCacheService2, ExecutorService sourceService2, boolean isCacheable2, EngineJobListener listener2) {
        this(key2, diskCacheService2, sourceService2, isCacheable2, listener2, DEFAULT_FACTORY);
    }

    public EngineJob(Key key2, ExecutorService diskCacheService2, ExecutorService sourceService2, boolean isCacheable2, EngineJobListener listener2, EngineResourceFactory engineResourceFactory2) {
        List<ResourceCallback> list;
        new ArrayList();
        this.cbs = list;
        this.key = key2;
        this.diskCacheService = diskCacheService2;
        this.sourceService = sourceService2;
        this.isCacheable = isCacheable2;
        this.listener = listener2;
        this.engineResourceFactory = engineResourceFactory2;
    }

    public void start(EngineRunnable engineRunnable2) {
        EngineRunnable engineRunnable3 = engineRunnable2;
        this.engineRunnable = engineRunnable3;
        this.future = this.diskCacheService.submit(engineRunnable3);
    }

    public void submitForSource(EngineRunnable runnable) {
        this.future = this.sourceService.submit(runnable);
    }

    public void addCallback(ResourceCallback resourceCallback) {
        ResourceCallback cb = resourceCallback;
        Util.assertMainThread();
        if (this.hasResource) {
            cb.onResourceReady(this.engineResource);
        } else if (this.hasException) {
            cb.onException(this.exception);
        } else {
            boolean add = this.cbs.add(cb);
        }
    }

    public void removeCallback(ResourceCallback resourceCallback) {
        ResourceCallback cb = resourceCallback;
        Util.assertMainThread();
        if (this.hasResource || this.hasException) {
            addIgnoredCallback(cb);
            return;
        }
        boolean remove = this.cbs.remove(cb);
        if (this.cbs.isEmpty()) {
            cancel();
        }
    }

    private void addIgnoredCallback(ResourceCallback resourceCallback) {
        Set<ResourceCallback> set;
        ResourceCallback cb = resourceCallback;
        if (this.ignoredCallbacks == null) {
            new HashSet();
            this.ignoredCallbacks = set;
        }
        boolean add = this.ignoredCallbacks.add(cb);
    }

    private boolean isInIgnoredCallbacks(ResourceCallback cb) {
        return this.ignoredCallbacks != null && this.ignoredCallbacks.contains(cb);
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        if (!this.hasException && !this.hasResource && !this.isCancelled) {
            this.engineRunnable.cancel();
            Future currentFuture = this.future;
            if (currentFuture != null) {
                boolean cancel = currentFuture.cancel(true);
            }
            this.isCancelled = true;
            this.listener.onEngineJobCancelled(this, this.key);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void onResourceReady(Resource<?> resource2) {
        this.resource = resource2;
        MAIN_THREAD_HANDLER.obtainMessage(1, this).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void handleResultOnMainThread() {
        Throwable th;
        if (this.isCancelled) {
            this.resource.recycle();
        } else if (this.cbs.isEmpty()) {
            Throwable th2 = th;
            new IllegalStateException("Received a resource without any callbacks to notify");
            throw th2;
        } else {
            this.engineResource = this.engineResourceFactory.build(this.resource, this.isCacheable);
            this.hasResource = true;
            this.engineResource.acquire();
            this.listener.onEngineJobComplete(this.key, this.engineResource);
            for (ResourceCallback cb : this.cbs) {
                if (!isInIgnoredCallbacks(cb)) {
                    this.engineResource.acquire();
                    cb.onResourceReady(this.engineResource);
                }
            }
            this.engineResource.release();
        }
    }

    public void onException(Exception e) {
        this.exception = e;
        MAIN_THREAD_HANDLER.obtainMessage(2, this).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void handleExceptionOnMainThread() {
        Throwable th;
        if (!this.isCancelled) {
            if (this.cbs.isEmpty()) {
                Throwable th2 = th;
                new IllegalStateException("Received an exception without any callbacks to notify");
                throw th2;
            }
            this.hasException = true;
            this.listener.onEngineJobComplete(this.key, (EngineResource<?>) null);
            for (ResourceCallback cb : this.cbs) {
                if (!isInIgnoredCallbacks(cb)) {
                    cb.onException(this.exception);
                }
            }
        }
    }

    static class EngineResourceFactory {
        EngineResourceFactory() {
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <R> com.bumptech.glide.load.engine.EngineResource<R> build(com.bumptech.glide.load.engine.Resource<R> r9, boolean r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                com.bumptech.glide.load.engine.EngineResource r3 = new com.bumptech.glide.load.engine.EngineResource
                r7 = r3
                r3 = r7
                r4 = r7
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r0 = r3
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.EngineJob.EngineResourceFactory.build(com.bumptech.glide.load.engine.Resource, boolean):com.bumptech.glide.load.engine.EngineResource");
        }
    }

    private static class MainThreadCallback implements Handler.Callback {
        private MainThreadCallback() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ MainThreadCallback(C15111 r4) {
            this();
            C15111 r1 = r4;
        }

        public boolean handleMessage(Message message) {
            Message message2 = message;
            if (1 != message2.what && 2 != message2.what) {
                return false;
            }
            EngineJob job = (EngineJob) message2.obj;
            if (1 == message2.what) {
                job.handleResultOnMainThread();
            } else {
                job.handleExceptionOnMainThread();
            }
            return true;
        }
    }
}
