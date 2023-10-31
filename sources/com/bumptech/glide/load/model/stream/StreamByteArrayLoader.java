package com.bumptech.glide.load.model.stream;

import android.content.Context;
import com.bumptech.glide.load.data.ByteArrayFetcher;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.InputStream;

public class StreamByteArrayLoader implements StreamModelLoader<byte[]> {

    /* renamed from: id */
    private final String f312id;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public StreamByteArrayLoader() {
        this("");
    }

    @Deprecated
    public StreamByteArrayLoader(String id) {
        this.f312id = id;
    }

    public DataFetcher<InputStream> getResourceFetcher(byte[] model, int i, int i2) {
        DataFetcher<InputStream> dataFetcher;
        int i3 = i;
        int i4 = i2;
        new ByteArrayFetcher(model, this.f312id);
        return dataFetcher;
    }

    public static class Factory implements ModelLoaderFactory<byte[], InputStream> {
        public Factory() {
        }

        public ModelLoader<byte[], InputStream> build(Context context, GenericLoaderFactory genericLoaderFactory) {
            ModelLoader<byte[], InputStream> modelLoader;
            Context context2 = context;
            GenericLoaderFactory genericLoaderFactory2 = genericLoaderFactory;
            new StreamByteArrayLoader();
            return modelLoader;
        }

        public void teardown() {
        }
    }
}
