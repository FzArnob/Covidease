package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;
import com.bumptech.glide.load.data.StreamLocalUriFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.UriLoader;
import java.io.InputStream;

public class StreamUriLoader extends UriLoader<InputStream> implements StreamModelLoader<Uri> {

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {
        public Factory() {
        }

        public ModelLoader<Uri, InputStream> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<Uri, InputStream> modelLoader;
            new StreamUriLoader(context, factories.buildModelLoader(GlideUrl.class, InputStream.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StreamUriLoader(android.content.Context r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            java.lang.Class<com.bumptech.glide.load.model.GlideUrl> r4 = com.bumptech.glide.load.model.GlideUrl.class
            r5 = r1
            com.bumptech.glide.load.model.ModelLoader r4 = com.bumptech.glide.Glide.buildStreamModelLoader(r4, (android.content.Context) r5)
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.stream.StreamUriLoader.<init>(android.content.Context):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StreamUriLoader(Context context, ModelLoader<GlideUrl, InputStream> urlLoader) {
        super(context, urlLoader);
    }

    /* access modifiers changed from: protected */
    public DataFetcher<InputStream> getLocalUriFetcher(Context context, Uri uri) {
        DataFetcher<InputStream> dataFetcher;
        new StreamLocalUriFetcher(context, uri);
        return dataFetcher;
    }

    /* access modifiers changed from: protected */
    public DataFetcher<InputStream> getAssetPathFetcher(Context context, String assetPath) {
        DataFetcher<InputStream> dataFetcher;
        new StreamAssetPathFetcher(context.getApplicationContext().getAssets(), assetPath);
        return dataFetcher;
    }
}
