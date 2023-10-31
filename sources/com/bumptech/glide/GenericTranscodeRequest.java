package com.bumptech.glide;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.provider.FixedLoadProvider;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;

public class GenericTranscodeRequest<ModelType, DataType, ResourceType> extends GenericRequestBuilder<ModelType, DataType, ResourceType, ResourceType> implements DownloadOptions {
    private final Class<DataType> dataClass;
    private final ModelLoader<ModelType, DataType> modelLoader;
    private final RequestManager.OptionsApplier optionsApplier;
    private final Class<ResourceType> resourceClass;

    private static <A, T, Z, R> LoadProvider<A, T, Z, R> build(Glide glide, ModelLoader<A, T> modelLoader2, Class<T> dataClass2, Class<Z> resourceClass2, ResourceTranscoder<Z, R> transcoder) {
        LoadProvider<A, T, Z, R> loadProvider;
        new FixedLoadProvider(modelLoader2, transcoder, glide.buildDataProvider(dataClass2, resourceClass2));
        return loadProvider;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    GenericTranscodeRequest(java.lang.Class<ResourceType> r14, com.bumptech.glide.GenericRequestBuilder<ModelType, ?, ?, ?> r15, com.bumptech.glide.load.model.ModelLoader<ModelType, DataType> r16, java.lang.Class<DataType> r17, java.lang.Class<ResourceType> r18, com.bumptech.glide.RequestManager.OptionsApplier r19) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r0
            r8 = r2
            com.bumptech.glide.Glide r8 = r8.glide
            r9 = r3
            r10 = r4
            r11 = r5
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r12 = com.bumptech.glide.load.resource.transcode.UnitTranscoder.get()
            com.bumptech.glide.provider.LoadProvider r8 = build(r8, r9, r10, r11, r12)
            r9 = r1
            r10 = r2
            r7.<init>(r8, r9, r10)
            r7 = r0
            r8 = r3
            r7.modelLoader = r8
            r7 = r0
            r8 = r4
            r7.dataClass = r8
            r7 = r0
            r8 = r5
            r7.resourceClass = r8
            r7 = r0
            r8 = r6
            r7.optionsApplier = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GenericTranscodeRequest.<init>(java.lang.Class, com.bumptech.glide.GenericRequestBuilder, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, java.lang.Class, com.bumptech.glide.RequestManager$OptionsApplier):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    GenericTranscodeRequest(android.content.Context r19, com.bumptech.glide.Glide r20, java.lang.Class<ModelType> r21, com.bumptech.glide.load.model.ModelLoader<ModelType, DataType> r22, java.lang.Class<DataType> r23, java.lang.Class<ResourceType> r24, com.bumptech.glide.manager.RequestTracker r25, com.bumptech.glide.manager.Lifecycle r26, com.bumptech.glide.RequestManager.OptionsApplier r27) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            r7 = r25
            r8 = r26
            r9 = r27
            r10 = r0
            r11 = r1
            r12 = r3
            r13 = r2
            r14 = r4
            r15 = r5
            r16 = r6
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r17 = com.bumptech.glide.load.resource.transcode.UnitTranscoder.get()
            com.bumptech.glide.provider.LoadProvider r13 = build(r13, r14, r15, r16, r17)
            r14 = r6
            r15 = r2
            r16 = r7
            r17 = r8
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)
            r10 = r0
            r11 = r4
            r10.modelLoader = r11
            r10 = r0
            r11 = r5
            r10.dataClass = r11
            r10 = r0
            r11 = r6
            r10.resourceClass = r11
            r10 = r0
            r11 = r9
            r10.optionsApplier = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GenericTranscodeRequest.<init>(android.content.Context, com.bumptech.glide.Glide, java.lang.Class, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, java.lang.Class, com.bumptech.glide.manager.RequestTracker, com.bumptech.glide.manager.Lifecycle, com.bumptech.glide.RequestManager$OptionsApplier):void");
    }

    public <TranscodeType> GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcode(ResourceTranscoder<ResourceType, TranscodeType> transcoder, Class<TranscodeType> transcodeClass) {
        GenericRequestBuilder genericRequestBuilder;
        new GenericRequestBuilder(build(this.glide, this.modelLoader, this.dataClass, this.resourceClass, transcoder), transcodeClass, this);
        return this.optionsApplier.apply(genericRequestBuilder);
    }

    public <Y extends Target<File>> Y downloadOnly(Y target) {
        return getDownloadOnlyRequest().into(target);
    }

    public FutureTarget<File> downloadOnly(int width, int height) {
        return getDownloadOnlyRequest().into(width, height);
    }

    private GenericRequestBuilder<ModelType, DataType, File, File> getDownloadOnlyRequest() {
        LoadProvider loadProvider;
        GenericRequestBuilder genericRequestBuilder;
        new FixedLoadProvider(this.modelLoader, UnitTranscoder.get(), this.glide.buildDataProvider(this.dataClass, File.class));
        new GenericRequestBuilder(loadProvider, File.class, this);
        return this.optionsApplier.apply(genericRequestBuilder).priority(Priority.LOW).diskCacheStrategy(DiskCacheStrategy.SOURCE).skipMemoryCache(true);
    }
}
