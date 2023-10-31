package com.bumptech.glide.load.model.stream;

import android.content.Context;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.InputStream;
import org.shaded.apache.http.HttpStatus;

public class HttpUrlGlideUrlLoader implements StreamModelLoader<GlideUrl> {
    private final ModelCache<GlideUrl, GlideUrl> modelCache;

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final ModelCache<GlideUrl, GlideUrl> modelCache;

        public Factory() {
            ModelCache<GlideUrl, GlideUrl> modelCache2;
            new ModelCache<>(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            this.modelCache = modelCache2;
        }

        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory genericLoaderFactory) {
            ModelLoader<GlideUrl, InputStream> modelLoader;
            Context context2 = context;
            GenericLoaderFactory genericLoaderFactory2 = genericLoaderFactory;
            new HttpUrlGlideUrlLoader(this.modelCache);
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpUrlGlideUrlLoader() {
        this((ModelCache<GlideUrl, GlideUrl>) null);
    }

    public HttpUrlGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> modelCache2) {
        this.modelCache = modelCache2;
    }

    public DataFetcher<InputStream> getResourceFetcher(GlideUrl glideUrl, int i, int i2) {
        DataFetcher<InputStream> dataFetcher;
        GlideUrl model = glideUrl;
        int i3 = i;
        int i4 = i2;
        GlideUrl url = model;
        if (this.modelCache != null) {
            url = this.modelCache.get(model, 0, 0);
            if (url == null) {
                this.modelCache.put(model, 0, 0, model);
                url = model;
            }
        }
        new HttpUrlFetcher(url);
        return dataFetcher;
    }
}
