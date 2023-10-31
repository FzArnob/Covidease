package com.bumptech.glide.load.model;

import com.bumptech.glide.load.data.DataFetcher;
import java.net.URL;

public class UrlLoader<T> implements ModelLoader<URL, T> {
    private final ModelLoader<GlideUrl, T> glideUrlLoader;

    public UrlLoader(ModelLoader<GlideUrl, T> glideUrlLoader2) {
        this.glideUrlLoader = glideUrlLoader2;
    }

    public DataFetcher<T> getResourceFetcher(URL model, int width, int height) {
        Object obj;
        new GlideUrl(model);
        return this.glideUrlLoader.getResourceFetcher(obj, width, height);
    }
}
