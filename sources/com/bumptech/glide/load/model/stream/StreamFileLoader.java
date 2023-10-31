package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.File;
import java.io.InputStream;

public class StreamFileLoader extends FileLoader<InputStream> implements StreamModelLoader<File> {

    public static class Factory implements ModelLoaderFactory<File, InputStream> {
        public Factory() {
        }

        public ModelLoader<File, InputStream> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<File, InputStream> modelLoader;
            Context context2 = context;
            new StreamFileLoader((ModelLoader<Uri, InputStream>) factories.buildModelLoader(Uri.class, InputStream.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public StreamFileLoader(Context context) {
        this(Glide.buildStreamModelLoader(Uri.class, context));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StreamFileLoader(ModelLoader<Uri, InputStream> uriLoader) {
        super(uriLoader);
    }
}
