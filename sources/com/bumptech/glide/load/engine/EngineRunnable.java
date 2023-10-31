package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.executor.Prioritized;
import com.bumptech.glide.request.ResourceCallback;

class EngineRunnable implements Runnable, Prioritized {
    private static final String TAG = "EngineRunnable";
    private final DecodeJob<?, ?, ?> decodeJob;
    private volatile boolean isCancelled;
    private final EngineRunnableManager manager;
    private final Priority priority;
    private Stage stage = Stage.CACHE;

    interface EngineRunnableManager extends ResourceCallback {
        void submitForSource(EngineRunnable engineRunnable);
    }

    private enum Stage {
    }

    public EngineRunnable(EngineRunnableManager manager2, DecodeJob<?, ?, ?> decodeJob2, Priority priority2) {
        this.manager = manager2;
        this.decodeJob = decodeJob2;
        this.priority = priority2;
    }

    public void cancel() {
        this.isCancelled = true;
        this.decodeJob.cancel();
    }

    public void run() {
        Exception exc;
        if (!this.isCancelled) {
            Exception exception = null;
            Resource<?> resource = null;
            try {
                resource = decode();
            } catch (OutOfMemoryError e) {
                OutOfMemoryError e2 = e;
                if (Log.isLoggable(TAG, 2)) {
                    int v = Log.v(TAG, "Out Of Memory Error decoding", e2);
                }
                new ErrorWrappingGlideException(e2);
                exception = exc;
            } catch (Exception e3) {
                Exception e4 = e3;
                if (Log.isLoggable(TAG, 2)) {
                    int v2 = Log.v(TAG, "Exception decoding", e4);
                }
                exception = e4;
            }
            if (this.isCancelled) {
                if (resource != null) {
                    resource.recycle();
                }
            } else if (resource == null) {
                onLoadFailed(exception);
            } else {
                onLoadComplete(resource);
            }
        }
    }

    private boolean isDecodingFromCache() {
        return this.stage == Stage.CACHE;
    }

    private void onLoadComplete(Resource resource) {
        this.manager.onResourceReady(resource);
    }

    private void onLoadFailed(Exception exc) {
        Exception e = exc;
        if (isDecodingFromCache()) {
            this.stage = Stage.SOURCE;
            this.manager.submitForSource(this);
            return;
        }
        this.manager.onException(e);
    }

    private Resource<?> decode() throws Exception {
        if (isDecodingFromCache()) {
            return decodeFromCache();
        }
        return decodeFromSource();
    }

    private Resource<?> decodeFromCache() throws Exception {
        StringBuilder sb;
        Resource<?> result = null;
        try {
            result = this.decodeJob.decodeResultFromCache();
        } catch (Exception e) {
            Exception e2 = e;
            if (Log.isLoggable(TAG, 3)) {
                new StringBuilder();
                int d = Log.d(TAG, sb.append("Exception decoding result from cache: ").append(e2).toString());
            }
        }
        if (result == null) {
            result = this.decodeJob.decodeSourceFromCache();
        }
        return result;
    }

    private Resource<?> decodeFromSource() throws Exception {
        return this.decodeJob.decodeFromSource();
    }

    public int getPriority() {
        return this.priority.ordinal();
    }
}
