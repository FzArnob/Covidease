package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.ChildLoadProvider;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.animation.NoAnimation;
import com.bumptech.glide.request.animation.ViewAnimationFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Util;
import java.io.File;

public class GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private GlideAnimationFactory<TranscodeType> animationFactory;
    protected final Context context;
    private DiskCacheStrategy diskCacheStrategy;
    private int errorId;
    private Drawable errorPlaceholder;
    private Drawable fallbackDrawable;
    private int fallbackResource;
    protected final Glide glide;
    private boolean isCacheable;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    private boolean isTransformationSet;
    protected final Lifecycle lifecycle;
    private ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> loadProvider;
    private ModelType model;
    protected final Class<ModelType> modelClass;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private int placeholderId;
    private Priority priority;
    private RequestListener<? super ModelType, TranscodeType> requestListener;
    protected final RequestTracker requestTracker;
    private Key signature;
    private Float sizeMultiplier;
    private Float thumbSizeMultiplier;
    private GenericRequestBuilder<?, ?, ?, TranscodeType> thumbnailRequestBuilder;
    protected final Class<TranscodeType> transcodeClass;
    private Transformation<ResourceType> transformation;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    GenericRequestBuilder(com.bumptech.glide.provider.LoadProvider<ModelType, DataType, ResourceType, TranscodeType> r13, java.lang.Class<TranscodeType> r14, com.bumptech.glide.GenericRequestBuilder<ModelType, ?, ?, ?> r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r0
            r5 = r3
            android.content.Context r5 = r5.context
            r6 = r3
            java.lang.Class<ModelType> r6 = r6.modelClass
            r7 = r1
            r8 = r2
            r9 = r3
            com.bumptech.glide.Glide r9 = r9.glide
            r10 = r3
            com.bumptech.glide.manager.RequestTracker r10 = r10.requestTracker
            r11 = r3
            com.bumptech.glide.manager.Lifecycle r11 = r11.lifecycle
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            r4 = r0
            r5 = r3
            ModelType r5 = r5.model
            r4.model = r5
            r4 = r0
            r5 = r3
            boolean r5 = r5.isModelSet
            r4.isModelSet = r5
            r4 = r0
            r5 = r3
            com.bumptech.glide.load.Key r5 = r5.signature
            r4.signature = r5
            r4 = r0
            r5 = r3
            com.bumptech.glide.load.engine.DiskCacheStrategy r5 = r5.diskCacheStrategy
            r4.diskCacheStrategy = r5
            r4 = r0
            r5 = r3
            boolean r5 = r5.isCacheable
            r4.isCacheable = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GenericRequestBuilder.<init>(com.bumptech.glide.provider.LoadProvider, java.lang.Class, com.bumptech.glide.GenericRequestBuilder):void");
    }

    GenericRequestBuilder(Context context2, Class<ModelType> cls, LoadProvider<ModelType, DataType, ResourceType, TranscodeType> loadProvider2, Class<TranscodeType> transcodeClass2, Glide glide2, RequestTracker requestTracker2, Lifecycle lifecycle2) {
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider;
        Throwable th;
        Throwable th2;
        ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> childLoadProvider2;
        Context context3 = context2;
        Class<ModelType> modelClass2 = cls;
        LoadProvider<ModelType, DataType, ResourceType, TranscodeType> loadProvider3 = loadProvider2;
        this.signature = EmptySignature.obtain();
        this.sizeMultiplier = Float.valueOf(1.0f);
        this.priority = null;
        this.isCacheable = true;
        this.animationFactory = NoAnimation.getFactory();
        this.overrideHeight = -1;
        this.overrideWidth = -1;
        this.diskCacheStrategy = DiskCacheStrategy.RESULT;
        this.transformation = UnitTransformation.get();
        this.context = context3;
        this.modelClass = modelClass2;
        this.transcodeClass = transcodeClass2;
        this.glide = glide2;
        this.requestTracker = requestTracker2;
        this.lifecycle = lifecycle2;
        if (loadProvider3 != null) {
            childLoadProvider = childLoadProvider2;
            new ChildLoadProvider<>(loadProvider3);
        } else {
            childLoadProvider = null;
        }
        this.loadProvider = childLoadProvider;
        if (context3 == null) {
            Throwable th3 = th2;
            new NullPointerException("Context can't be null");
            throw th3;
        } else if (modelClass2 != null && loadProvider3 == null) {
            Throwable th4 = th;
            new NullPointerException("LoadProvider must not be null");
            throw th4;
        }
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> thumbnail(GenericRequestBuilder<?, ?, ?, TranscodeType> genericRequestBuilder) {
        Throwable th;
        GenericRequestBuilder<?, ?, ?, TranscodeType> thumbnailRequest = genericRequestBuilder;
        if (equals(thumbnailRequest)) {
            Throwable th2 = th;
            new IllegalArgumentException("You cannot set a request as a thumbnail for itself. Consider using clone() on the request you are passing to thumbnail()");
            throw th2;
        }
        this.thumbnailRequestBuilder = thumbnailRequest;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> thumbnail(float f) {
        Throwable th;
        float sizeMultiplier2 = f;
        if (sizeMultiplier2 < 0.0f || sizeMultiplier2 > 1.0f) {
            Throwable th2 = th;
            new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
            throw th2;
        }
        this.thumbSizeMultiplier = Float.valueOf(sizeMultiplier2);
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sizeMultiplier(float f) {
        Throwable th;
        float sizeMultiplier2 = f;
        if (sizeMultiplier2 < 0.0f || sizeMultiplier2 > 1.0f) {
            Throwable th2 = th;
            new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
            throw th2;
        }
        this.sizeMultiplier = Float.valueOf(sizeMultiplier2);
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> decoder(ResourceDecoder<DataType, ResourceType> resourceDecoder) {
        ResourceDecoder<DataType, ResourceType> decoder = resourceDecoder;
        if (this.loadProvider != null) {
            this.loadProvider.setSourceDecoder(decoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> cacheDecoder(ResourceDecoder<File, ResourceType> resourceDecoder) {
        ResourceDecoder<File, ResourceType> cacheDecoder = resourceDecoder;
        if (this.loadProvider != null) {
            this.loadProvider.setCacheDecoder(cacheDecoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sourceEncoder(Encoder<DataType> encoder) {
        Encoder<DataType> sourceEncoder = encoder;
        if (this.loadProvider != null) {
            this.loadProvider.setSourceEncoder(sourceEncoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> diskCacheStrategy(DiskCacheStrategy strategy) {
        this.diskCacheStrategy = strategy;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> encoder(ResourceEncoder<ResourceType> resourceEncoder) {
        ResourceEncoder<ResourceType> encoder = resourceEncoder;
        if (this.loadProvider != null) {
            this.loadProvider.setEncoder(encoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> priority(Priority priority2) {
        this.priority = priority2;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transform(Transformation<ResourceType>... transformationArr) {
        Transformation<ResourceType> transformation2;
        Transformation<ResourceType>[] transformations = transformationArr;
        this.isTransformationSet = true;
        if (transformations.length == 1) {
            this.transformation = transformations[0];
        } else {
            new MultiTransformation((Transformation<T>[]) transformations);
            this.transformation = transformation2;
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> dontTransform() {
        return transform(UnitTransformation.get());
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcoder(ResourceTranscoder<ResourceType, TranscodeType> resourceTranscoder) {
        ResourceTranscoder<ResourceType, TranscodeType> transcoder = resourceTranscoder;
        if (this.loadProvider != null) {
            this.loadProvider.setTranscoder(transcoder);
        }
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> dontAnimate() {
        return animate(NoAnimation.getFactory());
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(int animationId) {
        GlideAnimationFactory glideAnimationFactory;
        new ViewAnimationFactory(this.context, animationId);
        return animate(glideAnimationFactory);
    }

    @Deprecated
    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(Animation animation) {
        GlideAnimationFactory glideAnimationFactory;
        new ViewAnimationFactory(animation);
        return animate(glideAnimationFactory);
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(ViewPropertyAnimation.Animator animator) {
        GlideAnimationFactory glideAnimationFactory;
        new ViewPropertyAnimationFactory(animator);
        return animate(glideAnimationFactory);
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(GlideAnimationFactory<TranscodeType> glideAnimationFactory) {
        Throwable th;
        GlideAnimationFactory<TranscodeType> animationFactory2 = glideAnimationFactory;
        if (animationFactory2 == null) {
            Throwable th2 = th;
            new NullPointerException("Animation factory must not be null!");
            throw th2;
        }
        this.animationFactory = animationFactory2;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> placeholder(int resourceId) {
        this.placeholderId = resourceId;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> placeholder(Drawable drawable) {
        this.placeholderDrawable = drawable;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> fallback(Drawable drawable) {
        this.fallbackDrawable = drawable;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> fallback(int resourceId) {
        this.fallbackResource = resourceId;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> error(int resourceId) {
        this.errorId = resourceId;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> error(Drawable drawable) {
        this.errorPlaceholder = drawable;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> listener(RequestListener<? super ModelType, TranscodeType> requestListener2) {
        this.requestListener = requestListener2;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> skipMemoryCache(boolean skip) {
        this.isCacheable = !skip;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> override(int i, int i2) {
        Throwable th;
        int width = i;
        int height = i2;
        if (!Util.isValidDimensions(width, height)) {
            Throwable th2 = th;
            new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
            throw th2;
        }
        this.overrideWidth = width;
        this.overrideHeight = height;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> signature(Key key) {
        Throwable th;
        Key signature2 = key;
        if (signature2 == null) {
            Throwable th2 = th;
            new NullPointerException("Signature must not be null");
            throw th2;
        }
        this.signature = signature2;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> load(ModelType model2) {
        this.model = model2;
        this.isModelSet = true;
        return this;
    }

    public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> clone() {
        Throwable th;
        try {
            GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> clone = (GenericRequestBuilder) super.clone();
            clone.loadProvider = this.loadProvider != null ? this.loadProvider.clone() : null;
            return clone;
        } catch (CloneNotSupportedException e) {
            CloneNotSupportedException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    public <Y extends Target<TranscodeType>> Y into(Y y) {
        Throwable th;
        Throwable th2;
        GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> target = y;
        Util.assertMainThread();
        if (target == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("You must pass in a non null Target");
            throw th3;
        } else if (!this.isModelSet) {
            Throwable th4 = th;
            new IllegalArgumentException("You must first set a model (try #load())");
            throw th4;
        } else {
            Request previous = target.getRequest();
            if (previous != null) {
                previous.clear();
                this.requestTracker.removeRequest(previous);
                previous.recycle();
            }
            Request request = buildRequest(target);
            target.setRequest(request);
            this.lifecycle.addListener(target);
            this.requestTracker.runRequest(request);
            return target;
        }
    }

    public Target<TranscodeType> into(ImageView imageView) {
        Throwable th;
        ImageView view = imageView;
        Util.assertMainThread();
        if (view == null) {
            Throwable th2 = th;
            new IllegalArgumentException("You must pass in a non null View");
            throw th2;
        }
        if (!this.isTransformationSet && view.getScaleType() != null) {
            switch (C15032.$SwitchMap$android$widget$ImageView$ScaleType[view.getScaleType().ordinal()]) {
                case 1:
                    applyCenterCrop();
                    break;
                case 2:
                case 3:
                case 4:
                    applyFitCenter();
                    break;
            }
        }
        return into(this.glide.buildImageViewTarget(view, this.transcodeClass));
    }

    /* renamed from: com.bumptech.glide.GenericRequestBuilder$2 */
    static /* synthetic */ class C15032 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
                NoSuchFieldError noSuchFieldError = e;
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
                NoSuchFieldError noSuchFieldError2 = e2;
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
                NoSuchFieldError noSuchFieldError3 = e3;
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
                NoSuchFieldError noSuchFieldError4 = e4;
            }
        }
    }

    public FutureTarget<TranscodeType> into(int width, int height) {
        RequestFutureTarget requestFutureTarget;
        Runnable runnable;
        new RequestFutureTarget(this.glide.getMainHandler(), width, height);
        RequestFutureTarget requestFutureTarget2 = requestFutureTarget;
        final RequestFutureTarget requestFutureTarget3 = requestFutureTarget2;
        new Runnable(this) {
            final /* synthetic */ GenericRequestBuilder this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                if (!requestFutureTarget3.isCancelled()) {
                    Target into = this.this$0.into(requestFutureTarget3);
                }
            }
        };
        boolean post = this.glide.getMainHandler().post(runnable);
        return requestFutureTarget2;
    }

    public Target<TranscodeType> preload(int width, int height) {
        return into(PreloadTarget.obtain(width, height));
    }

    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    public void applyCenterCrop() {
    }

    /* access modifiers changed from: package-private */
    public void applyFitCenter() {
    }

    private Priority getThumbnailPriority() {
        Priority result;
        if (this.priority == Priority.LOW) {
            result = Priority.NORMAL;
        } else if (this.priority == Priority.NORMAL) {
            result = Priority.HIGH;
        } else {
            result = Priority.IMMEDIATE;
        }
        return result;
    }

    private Request buildRequest(Target<TranscodeType> target) {
        Target<TranscodeType> target2 = target;
        if (this.priority == null) {
            this.priority = Priority.NORMAL;
        }
        return buildRequestRecursive(target2, (ThumbnailRequestCoordinator) null);
    }

    private Request buildRequestRecursive(Target<TranscodeType> target, ThumbnailRequestCoordinator thumbnailRequestCoordinator) {
        ThumbnailRequestCoordinator thumbnailRequestCoordinator2;
        ThumbnailRequestCoordinator thumbnailRequestCoordinator3;
        Throwable th;
        Target<TranscodeType> target2 = target;
        ThumbnailRequestCoordinator parentCoordinator = thumbnailRequestCoordinator;
        if (this.thumbnailRequestBuilder != null) {
            if (this.isThumbnailBuilt) {
                Throwable th2 = th;
                new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
                throw th2;
            }
            if (this.thumbnailRequestBuilder.animationFactory.equals(NoAnimation.getFactory())) {
                this.thumbnailRequestBuilder.animationFactory = this.animationFactory;
            }
            if (this.thumbnailRequestBuilder.priority == null) {
                this.thumbnailRequestBuilder.priority = getThumbnailPriority();
            }
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight) && !Util.isValidDimensions(this.thumbnailRequestBuilder.overrideWidth, this.thumbnailRequestBuilder.overrideHeight)) {
                GenericRequestBuilder<?, ?, ?, TranscodeType> override = this.thumbnailRequestBuilder.override(this.overrideWidth, this.overrideHeight);
            }
            new ThumbnailRequestCoordinator(parentCoordinator);
            ThumbnailRequestCoordinator coordinator = thumbnailRequestCoordinator3;
            Request fullRequest = obtainRequest(target2, this.sizeMultiplier.floatValue(), this.priority, coordinator);
            this.isThumbnailBuilt = true;
            Request thumbRequest = this.thumbnailRequestBuilder.buildRequestRecursive(target2, coordinator);
            this.isThumbnailBuilt = false;
            coordinator.setRequests(fullRequest, thumbRequest);
            return coordinator;
        } else if (this.thumbSizeMultiplier == null) {
            return obtainRequest(target2, this.sizeMultiplier.floatValue(), this.priority, parentCoordinator);
        } else {
            new ThumbnailRequestCoordinator(parentCoordinator);
            ThumbnailRequestCoordinator coordinator2 = thumbnailRequestCoordinator2;
            coordinator2.setRequests(obtainRequest(target2, this.sizeMultiplier.floatValue(), this.priority, coordinator2), obtainRequest(target2, this.thumbSizeMultiplier.floatValue(), getThumbnailPriority(), coordinator2));
            return coordinator2;
        }
    }

    private Request obtainRequest(Target<TranscodeType> target, float sizeMultiplier2, Priority priority2, RequestCoordinator requestCoordinator) {
        return GenericRequest.obtain(this.loadProvider, this.model, this.signature, this.context, priority2, target, sizeMultiplier2, this.placeholderDrawable, this.placeholderId, this.errorPlaceholder, this.errorId, this.fallbackDrawable, this.fallbackResource, this.requestListener, requestCoordinator, this.glide.getEngine(), this.transformation, this.transcodeClass, this.isCacheable, this.animationFactory, this.overrideWidth, this.overrideHeight, this.diskCacheStrategy);
    }
}
