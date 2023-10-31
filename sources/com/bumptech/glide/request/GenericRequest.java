package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.util.Queue;

public final class GenericRequest<A, T, Z, R> implements Request, SizeReadyCallback, ResourceCallback {
    private static final Queue<GenericRequest<?, ?, ?, ?>> REQUEST_POOL = Util.createQueue(0);
    private static final String TAG = "GenericRequest";
    private static final double TO_MEGABYTE = 9.5367431640625E-7d;
    private GlideAnimationFactory<R> animationFactory;
    private Context context;
    private DiskCacheStrategy diskCacheStrategy;
    private Engine engine;
    private Drawable errorDrawable;
    private int errorResourceId;
    private Drawable fallbackDrawable;
    private int fallbackResourceId;
    private boolean isMemoryCacheable;
    private LoadProvider<A, T, Z, R> loadProvider;
    private Engine.LoadStatus loadStatus;
    private boolean loadedFromMemoryCache;
    private A model;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private int placeholderResourceId;
    private Priority priority;
    private RequestCoordinator requestCoordinator;
    private RequestListener<? super A, R> requestListener;
    private Resource<?> resource;
    private Key signature;
    private float sizeMultiplier;
    private long startTime;
    private Status status;
    private final String tag = String.valueOf(hashCode());
    private Target<R> target;
    private Class<R> transcodeClass;
    private Transformation<Z> transformation;

    private enum Status {
    }

    public static <A, T, Z, R> GenericRequest<A, T, Z, R> obtain(LoadProvider<A, T, Z, R> loadProvider2, A a, Key key, Context context2, Priority priority2, Target<R> target2, float f, Drawable drawable, int i, Drawable drawable2, int i2, Drawable drawable3, int i3, RequestListener<? super A, R> requestListener2, RequestCoordinator requestCoordinator2, Engine engine2, Transformation<Z> transformation2, Class<R> cls, boolean z, GlideAnimationFactory<R> glideAnimationFactory, int i4, int i5, DiskCacheStrategy diskCacheStrategy2) {
        GenericRequest genericRequest;
        LoadProvider<A, T, Z, R> loadProvider3 = loadProvider2;
        A model2 = a;
        Key signature2 = key;
        Context context3 = context2;
        Priority priority3 = priority2;
        Target<R> target3 = target2;
        float sizeMultiplier2 = f;
        Drawable placeholderDrawable2 = drawable;
        int placeholderResourceId2 = i;
        Drawable errorDrawable2 = drawable2;
        int errorResourceId2 = i2;
        Drawable fallbackDrawable2 = drawable3;
        int fallbackResourceId2 = i3;
        RequestListener<? super A, R> requestListener3 = requestListener2;
        RequestCoordinator requestCoordinator3 = requestCoordinator2;
        Engine engine3 = engine2;
        Transformation<Z> transformation3 = transformation2;
        Class<R> transcodeClass2 = cls;
        boolean isMemoryCacheable2 = z;
        GlideAnimationFactory<R> animationFactory2 = glideAnimationFactory;
        int overrideWidth2 = i4;
        int overrideHeight2 = i5;
        DiskCacheStrategy diskCacheStrategy3 = diskCacheStrategy2;
        GenericRequest poll = REQUEST_POOL.poll();
        if (poll == null) {
            new GenericRequest();
            poll = genericRequest;
        }
        poll.init(loadProvider3, model2, signature2, context3, priority3, target3, sizeMultiplier2, placeholderDrawable2, placeholderResourceId2, errorDrawable2, errorResourceId2, fallbackDrawable2, fallbackResourceId2, requestListener3, requestCoordinator3, engine3, transformation3, transcodeClass2, isMemoryCacheable2, animationFactory2, overrideWidth2, overrideHeight2, diskCacheStrategy3);
        return poll;
    }

    private GenericRequest() {
    }

    public void recycle() {
        this.loadProvider = null;
        this.model = null;
        this.context = null;
        this.target = null;
        this.placeholderDrawable = null;
        this.errorDrawable = null;
        this.fallbackDrawable = null;
        this.requestListener = null;
        this.requestCoordinator = null;
        this.transformation = null;
        this.animationFactory = null;
        this.loadedFromMemoryCache = false;
        this.loadStatus = null;
        boolean offer = REQUEST_POOL.offer(this);
    }

    private void init(LoadProvider<A, T, Z, R> loadProvider2, A a, Key signature2, Context context2, Priority priority2, Target<R> target2, float sizeMultiplier2, Drawable placeholderDrawable2, int placeholderResourceId2, Drawable errorDrawable2, int errorResourceId2, Drawable fallbackDrawable2, int fallbackResourceId2, RequestListener<? super A, R> requestListener2, RequestCoordinator requestCoordinator2, Engine engine2, Transformation<Z> transformation2, Class<R> transcodeClass2, boolean isMemoryCacheable2, GlideAnimationFactory<R> animationFactory2, int overrideWidth2, int overrideHeight2, DiskCacheStrategy diskCacheStrategy2) {
        LoadProvider<A, T, Z, R> loadProvider3 = loadProvider2;
        A model2 = a;
        Transformation<Z> transformation3 = transformation2;
        DiskCacheStrategy diskCacheStrategy3 = diskCacheStrategy2;
        this.loadProvider = loadProvider3;
        this.model = model2;
        this.signature = signature2;
        this.fallbackDrawable = fallbackDrawable2;
        this.fallbackResourceId = fallbackResourceId2;
        this.context = context2.getApplicationContext();
        this.priority = priority2;
        this.target = target2;
        this.sizeMultiplier = sizeMultiplier2;
        this.placeholderDrawable = placeholderDrawable2;
        this.placeholderResourceId = placeholderResourceId2;
        this.errorDrawable = errorDrawable2;
        this.errorResourceId = errorResourceId2;
        this.requestListener = requestListener2;
        this.requestCoordinator = requestCoordinator2;
        this.engine = engine2;
        this.transformation = transformation3;
        this.transcodeClass = transcodeClass2;
        this.isMemoryCacheable = isMemoryCacheable2;
        this.animationFactory = animationFactory2;
        this.overrideWidth = overrideWidth2;
        this.overrideHeight = overrideHeight2;
        this.diskCacheStrategy = diskCacheStrategy3;
        this.status = Status.PENDING;
        if (model2 != null) {
            check("ModelLoader", loadProvider3.getModelLoader(), "try .using(ModelLoader)");
            check("Transcoder", loadProvider3.getTranscoder(), "try .as*(Class).transcode(ResourceTranscoder)");
            check("Transformation", transformation3, "try .transform(UnitTransformation.get())");
            if (diskCacheStrategy3.cacheSource()) {
                check("SourceEncoder", loadProvider3.getSourceEncoder(), "try .sourceEncoder(Encoder) or .diskCacheStrategy(NONE/RESULT)");
            } else {
                check("SourceDecoder", loadProvider3.getSourceDecoder(), "try .decoder/.imageDecoder/.videoDecoder(ResourceDecoder) or .diskCacheStrategy(ALL/SOURCE)");
            }
            if (diskCacheStrategy3.cacheSource() || diskCacheStrategy3.cacheResult()) {
                check("CacheDecoder", loadProvider3.getCacheDecoder(), "try .cacheDecoder(ResouceDecoder) or .diskCacheStrategy(NONE)");
            }
            if (diskCacheStrategy3.cacheResult()) {
                check("Encoder", loadProvider3.getEncoder(), "try .encode(ResourceEncoder) or .diskCacheStrategy(NONE/SOURCE)");
            }
        }
    }

    private static void check(String str, Object object, String str2) {
        StringBuilder sb;
        Throwable th;
        String name = str;
        String suggestion = str2;
        if (object == null) {
            new StringBuilder(name);
            StringBuilder message = sb;
            StringBuilder append = message.append(" must not be null");
            if (suggestion != null) {
                StringBuilder append2 = message.append(", ");
                StringBuilder append3 = message.append(suggestion);
            }
            Throwable th2 = th;
            new NullPointerException(message.toString());
            throw th2;
        }
    }

    public void begin() {
        StringBuilder sb;
        this.startTime = LogTime.getLogTime();
        if (this.model == null) {
            onException((Exception) null);
            return;
        }
        this.status = Status.WAITING_FOR_SIZE;
        if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
            onSizeReady(this.overrideWidth, this.overrideHeight);
        } else {
            this.target.getSize(this);
        }
        if (!isComplete() && !isFailed() && canNotifyStatusChanged()) {
            this.target.onLoadStarted(getPlaceholderDrawable());
        }
        if (Log.isLoggable(TAG, 2)) {
            new StringBuilder();
            logV(sb.append("finished run method in ").append(LogTime.getElapsedMillis(this.startTime)).toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        this.status = Status.CANCELLED;
        if (this.loadStatus != null) {
            this.loadStatus.cancel();
            this.loadStatus = null;
        }
    }

    public void clear() {
        Util.assertMainThread();
        if (this.status != Status.CLEARED) {
            cancel();
            if (this.resource != null) {
                releaseResource(this.resource);
            }
            if (canNotifyStatusChanged()) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            this.status = Status.CLEARED;
        }
    }

    public boolean isPaused() {
        return this.status == Status.PAUSED;
    }

    public void pause() {
        clear();
        this.status = Status.PAUSED;
    }

    private void releaseResource(Resource resource2) {
        this.engine.release(resource2);
        this.resource = null;
    }

    public boolean isRunning() {
        return this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public boolean isResourceSet() {
        return isComplete();
    }

    public boolean isCancelled() {
        return this.status == Status.CANCELLED || this.status == Status.CLEARED;
    }

    public boolean isFailed() {
        return this.status == Status.FAILED;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null && this.fallbackResourceId > 0) {
            this.fallbackDrawable = this.context.getResources().getDrawable(this.fallbackResourceId);
        }
        return this.fallbackDrawable;
    }

    private void setErrorPlaceholder(Exception exc) {
        Exception e = exc;
        if (canNotifyStatusChanged()) {
            Drawable error = this.model == null ? getFallbackDrawable() : null;
            if (error == null) {
                error = getErrorDrawable();
            }
            if (error == null) {
                error = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(e, error);
        }
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null && this.errorResourceId > 0) {
            this.errorDrawable = this.context.getResources().getDrawable(this.errorResourceId);
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null && this.placeholderResourceId > 0) {
            this.placeholderDrawable = this.context.getResources().getDrawable(this.placeholderResourceId);
        }
        return this.placeholderDrawable;
    }

    public void onSizeReady(int i, int i2) {
        StringBuilder sb;
        StringBuilder sb2;
        Exception exc;
        StringBuilder sb3;
        StringBuilder sb4;
        int width = i;
        int height = i2;
        if (Log.isLoggable(TAG, 2)) {
            new StringBuilder();
            logV(sb4.append("Got onSizeReady in ").append(LogTime.getElapsedMillis(this.startTime)).toString());
        }
        if (this.status == Status.WAITING_FOR_SIZE) {
            this.status = Status.RUNNING;
            int width2 = Math.round(this.sizeMultiplier * ((float) width));
            int height2 = Math.round(this.sizeMultiplier * ((float) height));
            DataFetcher<T> dataFetcher = this.loadProvider.getModelLoader().getResourceFetcher(this.model, width2, height2);
            if (dataFetcher == null) {
                new StringBuilder();
                new Exception(sb3.append("Failed to load model: '").append(this.model).append("'").toString());
                onException(exc);
                return;
            }
            ResourceTranscoder<Z, R> transcoder = this.loadProvider.getTranscoder();
            if (Log.isLoggable(TAG, 2)) {
                new StringBuilder();
                logV(sb2.append("finished setup for calling load in ").append(LogTime.getElapsedMillis(this.startTime)).toString());
            }
            this.loadedFromMemoryCache = true;
            this.loadStatus = this.engine.load(this.signature, width2, height2, dataFetcher, this.loadProvider, this.transformation, transcoder, this.priority, this.isMemoryCacheable, this.diskCacheStrategy, this);
            this.loadedFromMemoryCache = this.resource != null;
            if (Log.isLoggable(TAG, 2)) {
                new StringBuilder();
                logV(sb.append("finished onSizeReady in ").append(LogTime.getElapsedMillis(this.startTime)).toString());
            }
        }
    }

    private boolean canSetResource() {
        return this.requestCoordinator == null || this.requestCoordinator.canSetImage(this);
    }

    private boolean canNotifyStatusChanged() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        return this.requestCoordinator == null || !this.requestCoordinator.isAnyResourceSet();
    }

    private void notifyLoadSuccess() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestSuccess(this);
        }
    }

    public void onResourceReady(Resource<?> resource2) {
        StringBuilder sb;
        Exception exc;
        StringBuilder sb2;
        Resource<?> resource3 = resource2;
        if (resource3 == null) {
            new StringBuilder();
            new Exception(sb2.append("Expected to receive a Resource<R> with an object of ").append(this.transcodeClass).append(" inside, but instead got null.").toString());
            onException(exc);
            return;
        }
        Object received = resource3.get();
        if (received == null || !this.transcodeClass.isAssignableFrom(received.getClass())) {
            releaseResource(resource3);
            Exception exc2 = r8;
            new StringBuilder();
            Exception exc3 = new Exception(sb.append("Expected to receive an object of ").append(this.transcodeClass).append(" but instead got ").append(received != null ? received.getClass() : "").append("{").append(received).append("}").append(" inside Resource{").append(resource3).append("}.").append(received != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.").toString());
            onException(exc2);
        } else if (!canSetResource()) {
            releaseResource(resource3);
            this.status = Status.COMPLETE;
        } else {
            onResourceReady(resource3, received);
        }
    }

    private void onResourceReady(Resource<?> resource2, R r) {
        StringBuilder sb;
        Resource<?> resource3 = resource2;
        R result = r;
        boolean isFirstResource = isFirstReadyResource();
        this.status = Status.COMPLETE;
        this.resource = resource3;
        if (this.requestListener == null || !this.requestListener.onResourceReady(result, this.model, this.target, this.loadedFromMemoryCache, isFirstResource)) {
            this.target.onResourceReady(result, this.animationFactory.build(this.loadedFromMemoryCache, isFirstResource));
        }
        notifyLoadSuccess();
        if (Log.isLoggable(TAG, 2)) {
            new StringBuilder();
            logV(sb.append("Resource ready in ").append(LogTime.getElapsedMillis(this.startTime)).append(" size: ").append(((double) resource3.getSize()) * TO_MEGABYTE).append(" fromCache: ").append(this.loadedFromMemoryCache).toString());
        }
    }

    public void onException(Exception exc) {
        Exception e = exc;
        if (Log.isLoggable(TAG, 3)) {
            int d = Log.d(TAG, "load failed", e);
        }
        this.status = Status.FAILED;
        if (this.requestListener == null || !this.requestListener.onException(e, this.model, this.target, isFirstReadyResource())) {
            setErrorPlaceholder(e);
        }
    }

    private void logV(String message) {
        StringBuilder sb;
        new StringBuilder();
        int v = Log.v(TAG, sb.append(message).append(" this: ").append(this.tag).toString());
    }
}
