package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ResourceLoader;
import java.io.InputStream;

public class StreamResourceLoader extends ResourceLoader<InputStream> implements StreamModelLoader<Integer> {

    public static class Factory implements ModelLoaderFactory<Integer, InputStream> {
        public Factory() {
        }

        public ModelLoader<Integer, InputStream> build(Context context, GenericLoaderFactory factories) {
            ModelLoader<Integer, InputStream> modelLoader;
            new StreamResourceLoader(context, factories.buildModelLoader(Uri.class, InputStream.class));
            return modelLoader;
        }

        public void teardown() {
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StreamResourceLoader(android.content.Context r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            r5 = r1
            com.bumptech.glide.load.model.ModelLoader r4 = com.bumptech.glide.Glide.buildStreamModelLoader(r4, (android.content.Context) r5)
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.stream.StreamResourceLoader.<init>(android.content.Context):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StreamResourceLoader(Context context, ModelLoader<Uri, InputStream> uriLoader) {
        super(context, uriLoader);
    }
}
