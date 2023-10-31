package com.bumptech.glide.load.model.stream;

import android.content.Context;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.UrlLoader;
import java.io.InputStream;
import java.net.URL;

public class StreamUrlLoader extends UrlLoader<InputStream> {

    public static class Factory implements ModelLoaderFactory<URL, InputStream> {
        public Factory() {
        }

        public ModelLoader<URL, InputStream> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<URL, InputStream> modelLoader;
            Context context2 = context;
            new StreamUrlLoader(factories.buildModelLoader(GlideUrl.class, InputStream.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StreamUrlLoader(ModelLoader<GlideUrl, InputStream> glideUrlLoader) {
        super(glideUrlLoader);
    }
}
