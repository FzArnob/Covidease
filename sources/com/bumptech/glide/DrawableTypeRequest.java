package com.bumptech.glide;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;
import java.io.InputStream;

public class DrawableTypeRequest<ModelType> extends DrawableRequestBuilder<ModelType> implements DownloadOptions {
    private final ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader;
    private final RequestManager.OptionsApplier optionsApplier;
    private final ModelLoader<ModelType, InputStream> streamModelLoader;

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <A, Z, R> com.bumptech.glide.provider.FixedLoadProvider<A, com.bumptech.glide.load.model.ImageVideoWrapper, Z, R> buildProvider(com.bumptech.glide.Glide r14, com.bumptech.glide.load.model.ModelLoader<A, java.io.InputStream> r15, com.bumptech.glide.load.model.ModelLoader<A, android.os.ParcelFileDescriptor> r16, java.lang.Class<Z> r17, java.lang.Class<R> r18, com.bumptech.glide.load.resource.transcode.ResourceTranscoder<Z, R> r19) {
        /*
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r8 = r1
            if (r8 != 0) goto L_0x0013
            r8 = r2
            if (r8 != 0) goto L_0x0013
            r8 = 0
            r0 = r8
        L_0x0012:
            return r0
        L_0x0013:
            r8 = r5
            if (r8 != 0) goto L_0x001e
            r8 = r0
            r9 = r3
            r10 = r4
            com.bumptech.glide.load.resource.transcode.ResourceTranscoder r8 = r8.buildTranscoder(r9, r10)
            r5 = r8
        L_0x001e:
            r8 = r0
            java.lang.Class<com.bumptech.glide.load.model.ImageVideoWrapper> r9 = com.bumptech.glide.load.model.ImageVideoWrapper.class
            r10 = r3
            com.bumptech.glide.provider.DataLoadProvider r8 = r8.buildDataProvider(r9, r10)
            r6 = r8
            com.bumptech.glide.load.model.ImageVideoModelLoader r8 = new com.bumptech.glide.load.model.ImageVideoModelLoader
            r13 = r8
            r8 = r13
            r9 = r13
            r10 = r1
            r11 = r2
            r9.<init>(r10, r11)
            r7 = r8
            com.bumptech.glide.provider.FixedLoadProvider r8 = new com.bumptech.glide.provider.FixedLoadProvider
            r13 = r8
            r8 = r13
            r9 = r13
            r10 = r7
            r11 = r5
            r12 = r6
            r9.<init>(r10, r11, r12)
            r0 = r8
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.DrawableTypeRequest.buildProvider(com.bumptech.glide.Glide, com.bumptech.glide.load.model.ModelLoader, com.bumptech.glide.load.model.ModelLoader, java.lang.Class, java.lang.Class, com.bumptech.glide.load.resource.transcode.ResourceTranscoder):com.bumptech.glide.provider.FixedLoadProvider");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    DrawableTypeRequest(java.lang.Class<ModelType> r19, com.bumptech.glide.load.model.ModelLoader<ModelType, java.io.InputStream> r20, com.bumptech.glide.load.model.ModelLoader<ModelType, android.os.ParcelFileDescriptor> r21, android.content.Context r22, com.bumptech.glide.Glide r23, com.bumptech.glide.manager.RequestTracker r24, com.bumptech.glide.manager.Lifecycle r25, com.bumptech.glide.RequestManager.OptionsApplier r26) {
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
            r9 = r0
            r10 = r4
            r11 = r1
            r12 = r5
            r13 = r2
            r14 = r3
            java.lang.Class<com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper> r15 = com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper.class
            java.lang.Class<com.bumptech.glide.load.resource.drawable.GlideDrawable> r16 = com.bumptech.glide.load.resource.drawable.GlideDrawable.class
            r17 = 0
            com.bumptech.glide.provider.FixedLoadProvider r12 = buildProvider(r12, r13, r14, r15, r16, r17)
            r13 = r5
            r14 = r6
            r15 = r7
            r9.<init>(r10, r11, r12, r13, r14, r15)
            r9 = r0
            r10 = r2
            r9.streamModelLoader = r10
            r9 = r0
            r10 = r3
            r9.fileDescriptorModelLoader = r10
            r9 = r0
            r10 = r8
            r9.optionsApplier = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.DrawableTypeRequest.<init>(java.lang.Class, com.bumptech.glide.load.model.ModelLoader, com.bumptech.glide.load.model.ModelLoader, android.content.Context, com.bumptech.glide.Glide, com.bumptech.glide.manager.RequestTracker, com.bumptech.glide.manager.Lifecycle, com.bumptech.glide.RequestManager$OptionsApplier):void");
    }

    public BitmapTypeRequest<ModelType> asBitmap() {
        GenericRequestBuilder genericRequestBuilder;
        new BitmapTypeRequest(this, this.streamModelLoader, this.fileDescriptorModelLoader, this.optionsApplier);
        return (BitmapTypeRequest) this.optionsApplier.apply(genericRequestBuilder);
    }

    public GifTypeRequest<ModelType> asGif() {
        GenericRequestBuilder genericRequestBuilder;
        new GifTypeRequest(this, this.streamModelLoader, this.optionsApplier);
        return (GifTypeRequest) this.optionsApplier.apply(genericRequestBuilder);
    }

    public <Y extends Target<File>> Y downloadOnly(Y target) {
        return getDownloadOnlyRequest().downloadOnly(target);
    }

    public FutureTarget<File> downloadOnly(int width, int height) {
        return getDownloadOnlyRequest().downloadOnly(width, height);
    }

    private GenericTranscodeRequest<ModelType, InputStream, File> getDownloadOnlyRequest() {
        GenericRequestBuilder genericRequestBuilder;
        new GenericTranscodeRequest(File.class, this, this.streamModelLoader, InputStream.class, File.class, this.optionsApplier);
        return (GenericTranscodeRequest) this.optionsApplier.apply(genericRequestBuilder);
    }
}
