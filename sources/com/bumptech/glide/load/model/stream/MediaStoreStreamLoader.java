package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.MediaStoreThumbFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

public class MediaStoreStreamLoader implements StreamModelLoader<Uri> {
    private final Context context;
    private final ModelLoader<Uri, InputStream> uriLoader;

    public MediaStoreStreamLoader(Context context2, ModelLoader<Uri, InputStream> uriLoader2) {
        this.context = context2;
        this.uriLoader = uriLoader2;
    }

    public DataFetcher<InputStream> getResourceFetcher(Uri uri, int i, int i2) {
        DataFetcher<InputStream> dataFetcher;
        Uri model = uri;
        int width = i;
        int height = i2;
        new MediaStoreThumbFetcher(this.context, model, this.uriLoader.getResourceFetcher(model, width, height), width, height);
        return dataFetcher;
    }
}
