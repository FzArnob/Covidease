package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorAssetPathFetcher;
import com.bumptech.glide.load.data.FileDescriptorLocalUriFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.UriLoader;

public class FileDescriptorUriLoader extends UriLoader<ParcelFileDescriptor> implements FileDescriptorModelLoader<Uri> {

    public static class Factory implements ModelLoaderFactory<Uri, ParcelFileDescriptor> {
        public Factory() {
        }

        public ModelLoader<Uri, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<Uri, ParcelFileDescriptor> modelLoader;
            new FileDescriptorUriLoader(context, factories.buildModelLoader(GlideUrl.class, ParcelFileDescriptor.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FileDescriptorUriLoader(android.content.Context r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            java.lang.Class<com.bumptech.glide.load.model.GlideUrl> r4 = com.bumptech.glide.load.model.GlideUrl.class
            r5 = r1
            com.bumptech.glide.load.model.ModelLoader r4 = com.bumptech.glide.Glide.buildFileDescriptorModelLoader(r4, (android.content.Context) r5)
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.file_descriptor.FileDescriptorUriLoader.<init>(android.content.Context):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileDescriptorUriLoader(Context context, ModelLoader<GlideUrl, ParcelFileDescriptor> urlLoader) {
        super(context, urlLoader);
    }

    /* access modifiers changed from: protected */
    public DataFetcher<ParcelFileDescriptor> getLocalUriFetcher(Context context, Uri uri) {
        DataFetcher<ParcelFileDescriptor> dataFetcher;
        new FileDescriptorLocalUriFetcher(context, uri);
        return dataFetcher;
    }

    /* access modifiers changed from: protected */
    public DataFetcher<ParcelFileDescriptor> getAssetPathFetcher(Context context, String assetPath) {
        DataFetcher<ParcelFileDescriptor> dataFetcher;
        new FileDescriptorAssetPathFetcher(context.getApplicationContext().getAssets(), assetPath);
        return dataFetcher;
    }
}
