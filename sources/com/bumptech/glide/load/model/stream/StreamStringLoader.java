package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.StringLoader;
import java.io.InputStream;

public class StreamStringLoader extends StringLoader<InputStream> implements StreamModelLoader<String> {

    public static class Factory implements ModelLoaderFactory<String, InputStream> {
        public Factory() {
        }

        public ModelLoader<String, InputStream> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<String, InputStream> modelLoader;
            Context context2 = context;
            new StreamStringLoader((ModelLoader<Uri, InputStream>) factories.buildModelLoader(Uri.class, InputStream.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public StreamStringLoader(Context context) {
        this(Glide.buildStreamModelLoader(Uri.class, context));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StreamStringLoader(ModelLoader<Uri, InputStream> uriLoader) {
        super(uriLoader);
    }
}
