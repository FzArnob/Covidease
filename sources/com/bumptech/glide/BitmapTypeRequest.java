package com.bumptech.glide;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.InputStream;

public class BitmapTypeRequest<ModelType> extends BitmapRequestBuilder<ModelType, Bitmap> {
    private final ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader;
    private final Glide glide;
    private final RequestManager.OptionsApplier optionsApplier;
    private final ModelLoader<ModelType, InputStream> streamModelLoader;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: com.bumptech.glide.load.resource.transcode.ResourceTranscoder<Z, R>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <A, R> com.bumptech.glide.provider.FixedLoadProvider<A, com.bumptech.glide.load.model.ImageVideoWrapper, android.graphics.Bitmap, R> buildProvider(com.bumptech.glide.Glide r13, com.bumptech.glide.load.model.ModelLoader<A, java.io.InputStream> r14, com.bumptech.glide.load.model.ModelLoader<A, android.os.ParcelFileDescriptor> r15, java.lang.Class<R> r16, com.bumptech.glide.load.resource.transcode.ResourceTranscoder<android.graphics.Bitmap, R> r17) {
        /*
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r7 = r1
            if (r7 != 0) goto L_0x0010
            r7 = r2
            if (r7 != 0) goto L_0x0010
            r7 = 0
            r0 = r7
        L_0x000f:
            return r0
        L_0x0010:
            r7 = r4
            if (r7 != 0) goto L_0x001c
            r7 = r0
            java.lang.Class<android.graphics.Bitmap> r8 = android.graphics.Bitmap.class
            r9 = r3
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r7 = r7.buildTranscoder(r8, r9)
            r4 = r7
        L_0x001c:
            r7 = r0
            java.lang.Class<com.bumptech.glide.load.model.ImageVideoWrapper> r8 = com.bumptech.glide.load.model.ImageVideoWrapper.class
            java.lang.Class<android.graphics.Bitmap> r9 = android.graphics.Bitmap.class
            com.bumptech.glide.provider.DataLoadProvider r7 = r7.buildDataProvider(r8, r9)
            r5 = r7
            com.bumptech.glide.load.model.ImageVideoModelLoader r7 = new com.bumptech.glide.load.model.ImageVideoModelLoader
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r1
            r10 = r2
            r8.<init>(r9, r10)
            r6 = r7
            com.bumptech.glide.provider.FixedLoadProvider r7 = new com.bumptech.glide.provider.FixedLoadProvider
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r6
            r10 = r4
            r11 = r5
            r8.<init>(r9, r10, r11)
            r0 = r7
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.BitmapTypeRequest.buildProvider(com.bumptech.glide.Glide, com.bumptech.glide.load.model.ModelLoader, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, com.bumptech.glide.load.resource.transcode.ResourceTranscoder):com.bumptech.glide.provider.FixedLoadProvider");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    BitmapTypeRequest(com.bumptech.glide.GenericRequestBuilder<ModelType, ?, ?, ?> r12, com.bumptech.glide.load.model.ModelLoader<ModelType, java.io.InputStream> r13, com.bumptech.glide.load.model.ModelLoader<ModelType, android.os.ParcelFileDescriptor> r14, com.bumptech.glide.RequestManager.OptionsApplier r15) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r0
            r6 = r1
            com.bumptech.glide.Glide r6 = r6.glide
            r7 = r2
            r8 = r3
            java.lang.Class<android.graphics.Bitmap> r9 = android.graphics.Bitmap.class
            r10 = 0
            com.bumptech.glide.provider.FixedLoadProvider r6 = buildProvider(r6, r7, r8, r9, r10)
            java.lang.Class<android.graphics.Bitmap> r7 = android.graphics.Bitmap.class
            r8 = r1
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = r2
            r5.streamModelLoader = r6
            r5 = r0
            r6 = r3
            r5.fileDescriptorModelLoader = r6
            r5 = r0
            r6 = r1
            com.bumptech.glide.Glide r6 = r6.glide
            r5.glide = r6
            r5 = r0
            r6 = r4
            r5.optionsApplier = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.BitmapTypeRequest.<init>(com.bumptech.glide.GenericRequestBuilder, com.bumptech.glide.load.model.ModelLoader, com.bumptech.glide.load.model.ModelLoader, com.bumptech.glide.RequestManager$OptionsApplier):void");
    }

    public <R> BitmapRequestBuilder<ModelType, R> transcode(ResourceTranscoder<Bitmap, R> transcoder, Class<R> cls) {
        GenericRequestBuilder genericRequestBuilder;
        Class<R> transcodeClass = cls;
        new BitmapRequestBuilder(buildProvider(this.glide, this.streamModelLoader, this.fileDescriptorModelLoader, transcodeClass, transcoder), transcodeClass, this);
        return (BitmapRequestBuilder) this.optionsApplier.apply(genericRequestBuilder);
    }

    public BitmapRequestBuilder<ModelType, byte[]> toBytes() {
        ResourceTranscoder resourceTranscoder;
        new BitmapBytesTranscoder();
        return transcode(resourceTranscoder, byte[].class);
    }

    public BitmapRequestBuilder<ModelType, byte[]> toBytes(Bitmap.CompressFormat compressFormat, int quality) {
        ResourceTranscoder resourceTranscoder;
        new BitmapBytesTranscoder(compressFormat, quality);
        return transcode(resourceTranscoder, byte[].class);
    }
}
