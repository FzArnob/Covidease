package com.bumptech.glide;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.stream.MediaStoreStreamLoader;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.bumptech.glide.signature.StringSignature;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class RequestManager implements LifecycleListener {
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Glide glide;
    /* access modifiers changed from: private */
    public final Lifecycle lifecycle;
    /* access modifiers changed from: private */
    public DefaultOptions options;
    /* access modifiers changed from: private */
    public final OptionsApplier optionsApplier;
    /* access modifiers changed from: private */
    public final RequestTracker requestTracker;
    private final RequestManagerTreeNode treeNode;

    public interface DefaultOptions {
        <T> void apply(GenericRequestBuilder<T, ?, ?, ?> genericRequestBuilder);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RequestManager(android.content.Context r13, com.bumptech.glide.manager.Lifecycle r14, com.bumptech.glide.manager.RequestManagerTreeNode r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            com.bumptech.glide.manager.RequestTracker r8 = new com.bumptech.glide.manager.RequestTracker
            r11 = r8
            r8 = r11
            r9 = r11
            r9.<init>()
            com.bumptech.glide.manager.ConnectivityMonitorFactory r9 = new com.bumptech.glide.manager.ConnectivityMonitorFactory
            r11 = r9
            r9 = r11
            r10 = r11
            r10.<init>()
            r4.<init>(r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestManager.<init>(android.content.Context, com.bumptech.glide.manager.Lifecycle, com.bumptech.glide.manager.RequestManagerTreeNode):void");
    }

    RequestManager(Context context2, Lifecycle lifecycle2, RequestManagerTreeNode treeNode2, RequestTracker requestTracker2, ConnectivityMonitorFactory factory) {
        OptionsApplier optionsApplier2;
        ConnectivityMonitor.ConnectivityListener connectivityListener;
        Handler handler;
        Runnable runnable;
        Context context3 = context2;
        Lifecycle lifecycle3 = lifecycle2;
        RequestTracker requestTracker3 = requestTracker2;
        this.context = context3.getApplicationContext();
        this.lifecycle = lifecycle3;
        this.treeNode = treeNode2;
        this.requestTracker = requestTracker3;
        this.glide = Glide.get(context3);
        new OptionsApplier(this);
        this.optionsApplier = optionsApplier2;
        new RequestManagerConnectivityListener(requestTracker3);
        ConnectivityMonitor connectivityMonitor = factory.build(context3, connectivityListener);
        if (Util.isOnBackgroundThread()) {
            new Handler(Looper.getMainLooper());
            final Lifecycle lifecycle4 = lifecycle3;
            new Runnable(this) {
                final /* synthetic */ RequestManager this$0;

                {
                    this.this$0 = r6;
                }

                public void run() {
                    lifecycle4.addListener(this.this$0);
                }
            };
            boolean post = handler.post(runnable);
        } else {
            lifecycle3.addListener(this);
        }
        lifecycle3.addListener(connectivityMonitor);
    }

    public void onTrimMemory(int level) {
        this.glide.trimMemory(level);
    }

    public void onLowMemory() {
        this.glide.clearMemory();
    }

    public void setDefaultOptions(DefaultOptions options2) {
        DefaultOptions defaultOptions = options2;
        this.options = defaultOptions;
    }

    public boolean isPaused() {
        Util.assertMainThread();
        return this.requestTracker.isPaused();
    }

    public void pauseRequests() {
        Util.assertMainThread();
        this.requestTracker.pauseRequests();
    }

    public void pauseRequestsRecursive() {
        Util.assertMainThread();
        pauseRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.pauseRequests();
        }
    }

    public void resumeRequests() {
        Util.assertMainThread();
        this.requestTracker.resumeRequests();
    }

    public void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.resumeRequests();
        }
    }

    public void onStart() {
        resumeRequests();
    }

    public void onStop() {
        pauseRequests();
    }

    public void onDestroy() {
        this.requestTracker.clearRequests();
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <A, T> com.bumptech.glide.RequestManager.GenericModelRequest<A, T> using(com.bumptech.glide.load.model.ModelLoader<A, T> r10, java.lang.Class<T> r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            com.bumptech.glide.RequestManager$GenericModelRequest r3 = new com.bumptech.glide.RequestManager$GenericModelRequest
            r8 = r3
            r3 = r8
            r4 = r8
            r5 = r0
            r6 = r1
            r7 = r2
            r4.<init>(r5, r6, r7)
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestManager.using(com.bumptech.glide.load.model.ModelLoader, java.lang.Class):com.bumptech.glide.RequestManager$GenericModelRequest");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.bumptech.glide.RequestManager.ImageModelRequest<T> using(com.bumptech.glide.load.model.stream.StreamModelLoader<T> r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            com.bumptech.glide.RequestManager$ImageModelRequest r2 = new com.bumptech.glide.RequestManager$ImageModelRequest
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r5 = r1
            r3.<init>(r4, r5)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestManager.using(com.bumptech.glide.load.model.stream.StreamModelLoader):com.bumptech.glide.RequestManager$ImageModelRequest");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bumptech.glide.RequestManager.ImageModelRequest<byte[]> using(com.bumptech.glide.load.model.stream.StreamByteArrayLoader r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            com.bumptech.glide.RequestManager$ImageModelRequest r2 = new com.bumptech.glide.RequestManager$ImageModelRequest
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r5 = r1
            r3.<init>(r4, r5)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestManager.using(com.bumptech.glide.load.model.stream.StreamByteArrayLoader):com.bumptech.glide.RequestManager$ImageModelRequest");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.bumptech.glide.RequestManager.VideoModelRequest<T> using(com.bumptech.glide.load.model.file_descriptor.FileDescriptorModelLoader<T> r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            com.bumptech.glide.RequestManager$VideoModelRequest r2 = new com.bumptech.glide.RequestManager$VideoModelRequest
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r5 = r1
            r3.<init>(r4, r5)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestManager.using(com.bumptech.glide.load.model.file_descriptor.FileDescriptorModelLoader):com.bumptech.glide.RequestManager$VideoModelRequest");
    }

    public DrawableTypeRequest<String> load(String string) {
        return (DrawableTypeRequest) fromString().load(string);
    }

    public DrawableTypeRequest<String> fromString() {
        return loadGeneric(String.class);
    }

    public DrawableTypeRequest<Uri> load(Uri uri) {
        return (DrawableTypeRequest) fromUri().load(uri);
    }

    public DrawableTypeRequest<Uri> fromUri() {
        return loadGeneric(Uri.class);
    }

    @Deprecated
    public DrawableTypeRequest<Uri> loadFromMediaStore(Uri uri, String mimeType, long dateModified, int orientation) {
        Key signature;
        new MediaStoreSignature(mimeType, dateModified, orientation);
        return (DrawableTypeRequest) loadFromMediaStore(uri).signature(signature);
    }

    public DrawableTypeRequest<Uri> loadFromMediaStore(Uri uri) {
        return (DrawableTypeRequest) fromMediaStore().load(uri);
    }

    public DrawableTypeRequest<Uri> fromMediaStore() {
        ModelLoader<Uri, InputStream> mediaStoreLoader;
        GenericRequestBuilder genericRequestBuilder;
        new MediaStoreStreamLoader(this.context, Glide.buildStreamModelLoader(Uri.class, this.context));
        new DrawableTypeRequest(Uri.class, mediaStoreLoader, Glide.buildFileDescriptorModelLoader(Uri.class, this.context), this.context, this.glide, this.requestTracker, this.lifecycle, this.optionsApplier);
        return (DrawableTypeRequest) this.optionsApplier.apply(genericRequestBuilder);
    }

    public DrawableTypeRequest<File> load(File file) {
        return (DrawableTypeRequest) fromFile().load(file);
    }

    public DrawableTypeRequest<File> fromFile() {
        return loadGeneric(File.class);
    }

    public DrawableTypeRequest<Integer> load(Integer resourceId) {
        return (DrawableTypeRequest) fromResource().load(resourceId);
    }

    public DrawableTypeRequest<Integer> fromResource() {
        return (DrawableTypeRequest) loadGeneric(Integer.class).signature(ApplicationVersionSignature.obtain(this.context));
    }

    @Deprecated
    public DrawableTypeRequest<URL> load(URL url) {
        return (DrawableTypeRequest) fromUrl().load(url);
    }

    @Deprecated
    public DrawableTypeRequest<URL> fromUrl() {
        return loadGeneric(URL.class);
    }

    @Deprecated
    public DrawableTypeRequest<byte[]> load(byte[] model, String id) {
        Key key;
        new StringSignature(id);
        return (DrawableTypeRequest) load(model).signature(key);
    }

    public DrawableTypeRequest<byte[]> load(byte[] model) {
        return (DrawableTypeRequest) fromBytes().load(model);
    }

    public DrawableTypeRequest<byte[]> fromBytes() {
        Key key;
        new StringSignature(UUID.randomUUID().toString());
        return (DrawableTypeRequest) loadGeneric(byte[].class).signature(key).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
    }

    public <T> DrawableTypeRequest<T> load(T t) {
        T model = t;
        return (DrawableTypeRequest) loadGeneric(getSafeClass(model)).load(model);
    }

    public <T> DrawableTypeRequest<T> from(Class<T> modelClass) {
        return loadGeneric(modelClass);
    }

    private <T> DrawableTypeRequest<T> loadGeneric(Class<T> cls) {
        GenericRequestBuilder genericRequestBuilder;
        Throwable th;
        StringBuilder sb;
        Class<T> modelClass = cls;
        ModelLoader<T, InputStream> streamModelLoader = Glide.buildStreamModelLoader(modelClass, this.context);
        ModelLoader<T, ParcelFileDescriptor> fileDescriptorModelLoader = Glide.buildFileDescriptorModelLoader(modelClass, this.context);
        if (modelClass != null && streamModelLoader == null && fileDescriptorModelLoader == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Unknown type ").append(modelClass).append(". You must provide a Model of a type for").append(" which there is a registered ModelLoader, if you are using a custom model, you must first call").append(" Glide#register with a ModelLoaderFactory for your custom model class").toString());
            throw th2;
        }
        new DrawableTypeRequest(modelClass, streamModelLoader, fileDescriptorModelLoader, this.context, this.glide, this.requestTracker, this.lifecycle, this.optionsApplier);
        return (DrawableTypeRequest) this.optionsApplier.apply(genericRequestBuilder);
    }

    /* access modifiers changed from: private */
    public static <T> Class<T> getSafeClass(T t) {
        T model = t;
        return model != null ? model.getClass() : null;
    }

    public final class VideoModelRequest<T> {
        private final ModelLoader<T, ParcelFileDescriptor> loader;
        final /* synthetic */ RequestManager this$0;

        VideoModelRequest(RequestManager requestManager, ModelLoader<T, ParcelFileDescriptor> loader2) {
            this.this$0 = requestManager;
            this.loader = loader2;
        }

        public DrawableTypeRequest<T> load(T t) {
            GenericRequestBuilder genericRequestBuilder;
            T model = t;
            new DrawableTypeRequest(RequestManager.getSafeClass(model), (ModelLoader) null, this.loader, this.this$0.context, this.this$0.glide, this.this$0.requestTracker, this.this$0.lifecycle, this.this$0.optionsApplier);
            return (DrawableTypeRequest) ((DrawableTypeRequest) this.this$0.optionsApplier.apply(genericRequestBuilder)).load(model);
        }
    }

    public final class ImageModelRequest<T> {
        private final ModelLoader<T, InputStream> loader;
        final /* synthetic */ RequestManager this$0;

        ImageModelRequest(RequestManager requestManager, ModelLoader<T, InputStream> loader2) {
            this.this$0 = requestManager;
            this.loader = loader2;
        }

        public DrawableTypeRequest<T> from(Class<T> modelClass) {
            GenericRequestBuilder genericRequestBuilder;
            new DrawableTypeRequest(modelClass, this.loader, (ModelLoader) null, this.this$0.context, this.this$0.glide, this.this$0.requestTracker, this.this$0.lifecycle, this.this$0.optionsApplier);
            return (DrawableTypeRequest) this.this$0.optionsApplier.apply(genericRequestBuilder);
        }

        public DrawableTypeRequest<T> load(T t) {
            T model = t;
            return (DrawableTypeRequest) from(RequestManager.getSafeClass(model)).load(model);
        }
    }

    public final class GenericModelRequest<A, T> {
        /* access modifiers changed from: private */
        public final Class<T> dataClass;
        /* access modifiers changed from: private */
        public final ModelLoader<A, T> modelLoader;
        final /* synthetic */ RequestManager this$0;

        GenericModelRequest(RequestManager requestManager, ModelLoader<A, T> modelLoader2, Class<T> dataClass2) {
            this.this$0 = requestManager;
            this.modelLoader = modelLoader2;
            this.dataClass = dataClass2;
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.bumptech.glide.RequestManager.GenericModelRequest<A, T>.GenericTypeRequest from(java.lang.Class<A> r8) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                com.bumptech.glide.RequestManager$GenericModelRequest$GenericTypeRequest r2 = new com.bumptech.glide.RequestManager$GenericModelRequest$GenericTypeRequest
                r6 = r2
                r2 = r6
                r3 = r6
                r4 = r0
                r5 = r1
                r3.<init>((com.bumptech.glide.RequestManager.GenericModelRequest) r4, r5)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestManager.GenericModelRequest.from(java.lang.Class):com.bumptech.glide.RequestManager$GenericModelRequest$GenericTypeRequest");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.bumptech.glide.RequestManager.GenericModelRequest<A, T>.GenericTypeRequest load(A r8) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                com.bumptech.glide.RequestManager$GenericModelRequest$GenericTypeRequest r2 = new com.bumptech.glide.RequestManager$GenericModelRequest$GenericTypeRequest
                r6 = r2
                r2 = r6
                r3 = r6
                r4 = r0
                r5 = r1
                r3.<init>((com.bumptech.glide.RequestManager.GenericModelRequest) r4, r5)
                r0 = r2
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestManager.GenericModelRequest.load(java.lang.Object):com.bumptech.glide.RequestManager$GenericModelRequest$GenericTypeRequest");
        }

        public final class GenericTypeRequest {
            private final A model;
            private final Class<A> modelClass;
            private final boolean providedModel;
            final /* synthetic */ GenericModelRequest this$1;

            GenericTypeRequest(GenericModelRequest genericModelRequest, A a) {
                A model2 = a;
                this.this$1 = genericModelRequest;
                this.providedModel = true;
                this.model = model2;
                this.modelClass = RequestManager.getSafeClass(model2);
            }

            GenericTypeRequest(GenericModelRequest genericModelRequest, Class<A> modelClass2) {
                this.this$1 = genericModelRequest;
                this.providedModel = false;
                this.model = null;
                this.modelClass = modelClass2;
            }

            /* renamed from: as */
            public <Z> GenericTranscodeRequest<A, T, Z> mo23786as(Class<Z> resourceClass) {
                GenericRequestBuilder genericRequestBuilder;
                new GenericTranscodeRequest(this.this$1.this$0.context, this.this$1.this$0.glide, this.modelClass, this.this$1.modelLoader, this.this$1.dataClass, resourceClass, this.this$1.this$0.requestTracker, this.this$1.this$0.lifecycle, this.this$1.this$0.optionsApplier);
                GenericTranscodeRequest<A, T, Z> result = (GenericTranscodeRequest) this.this$1.this$0.optionsApplier.apply(genericRequestBuilder);
                if (this.providedModel) {
                    GenericRequestBuilder<A, T, Z, TranscodeType> load = result.load(this.model);
                }
                return result;
            }
        }
    }

    class OptionsApplier {
        final /* synthetic */ RequestManager this$0;

        OptionsApplier(RequestManager requestManager) {
            this.this$0 = requestManager;
        }

        public <A, X extends GenericRequestBuilder<A, ?, ?, ?>> X apply(X x) {
            OptionsApplier builder = x;
            if (this.this$0.options != null) {
                this.this$0.options.apply(builder);
            }
            return builder;
        }
    }

    private static class RequestManagerConnectivityListener implements ConnectivityMonitor.ConnectivityListener {
        private final RequestTracker requestTracker;

        public RequestManagerConnectivityListener(RequestTracker requestTracker2) {
            this.requestTracker = requestTracker2;
        }

        public void onConnectivityChanged(boolean isConnected) {
            if (isConnected) {
                this.requestTracker.restartRequests();
            }
        }
    }
}
