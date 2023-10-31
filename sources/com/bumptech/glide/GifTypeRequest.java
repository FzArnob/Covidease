package com.bumptech.glide;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.InputStream;

public class GifTypeRequest<ModelType> extends GifRequestBuilder<ModelType> {
    private final RequestManager.OptionsApplier optionsApplier;
    private final ModelLoader<ModelType, InputStream> streamModelLoader;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: com.bumptech.glide.load.resource.transcode.ResourceTranscoder<Z, R>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <A, R> com.bumptech.glide.provider.FixedLoadProvider<A, java.io.InputStream, com.bumptech.glide.load.resource.gif.GifDrawable, R> buildProvider(com.bumptech.glide.Glide r11, com.bumptech.glide.load.model.ModelLoader<A, java.io.InputStream> r12, java.lang.Class<R> r13, com.bumptech.glide.load.resource.transcode.ResourceTranscoder<com.bumptech.glide.load.resource.gif.GifDrawable, R> r14) {
        /*
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r5 = r1
            if (r5 != 0) goto L_0x000a
            r5 = 0
            r0 = r5
        L_0x0009:
            return r0
        L_0x000a:
            r5 = r3
            if (r5 != 0) goto L_0x0016
            r5 = r0
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r6 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            r7 = r2
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r5 = r5.buildTranscoder(r6, r7)
            r3 = r5
        L_0x0016:
            r5 = r0
            java.lang.Class<java.io.InputStream> r6 = java.io.InputStream.class
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r7 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.provider.DataLoadProvider r5 = r5.buildDataProvider(r6, r7)
            r4 = r5
            com.bumptech.glide.provider.FixedLoadProvider r5 = new com.bumptech.glide.provider.FixedLoadProvider
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r1
            r8 = r3
            r9 = r4
            r6.<init>(r7, r8, r9)
            r0 = r5
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GifTypeRequest.buildProvider(com.bumptech.glide.Glide, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, com.bumptech.glide.load.resource.transcode.ResourceTranscoder):com.bumptech.glide.provider.FixedLoadProvider");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    GifTypeRequest(com.bumptech.glide.GenericRequestBuilder<ModelType, ?, ?, ?> r10, com.bumptech.glide.load.model.ModelLoader<ModelType, java.io.InputStream> r11, com.bumptech.glide.RequestManager.OptionsApplier r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            com.bumptech.glide.Glide r5 = r5.glide
            r6 = r2
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r7 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            r8 = 0
            com.bumptech.glide.provider.FixedLoadProvider r5 = buildProvider(r5, r6, r7, r8)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r6 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            r7 = r1
            r4.<init>(r5, r6, r7)
            r4 = r0
            r5 = r2
            r4.streamModelLoader = r5
            r4 = r0
            r5 = r3
            r4.optionsApplier = r5
            r4 = r0
            com.bumptech.glide.GifRequestBuilder r4 = r4.crossFade()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GifTypeRequest.<init>(com.bumptech.glide.GenericRequestBuilder, com.bumptech.glide.load.model.ModelLoader, com.bumptech.glide.RequestManager$OptionsApplier):void");
    }

    public <R> GenericRequestBuilder<ModelType, InputStream, GifDrawable, R> transcode(ResourceTranscoder<GifDrawable, R> transcoder, Class<R> cls) {
        GenericRequestBuilder genericRequestBuilder;
        Class<R> transcodeClass = cls;
        new GenericRequestBuilder(buildProvider(this.glide, this.streamModelLoader, transcodeClass, transcoder), transcodeClass, this);
        return this.optionsApplier.apply(genericRequestBuilder);
    }

    public GenericRequestBuilder<ModelType, InputStream, GifDrawable, byte[]> toBytes() {
        ResourceTranscoder resourceTranscoder;
        new GifDrawableBytesTranscoder();
        return transcode(resourceTranscoder, byte[].class);
    }
}
