package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.File;

public class StringLoader<T> implements ModelLoader<String, T> {
    private final ModelLoader<Uri, T> uriLoader;

    public StringLoader(ModelLoader<Uri, T> uriLoader2) {
        this.uriLoader = uriLoader2;
    }

    public DataFetcher<T> getResourceFetcher(String str, int i, int i2) {
        Uri uri;
        String model = str;
        int width = i;
        int height = i2;
        if (TextUtils.isEmpty(model)) {
            return null;
        }
        if (model.startsWith("/")) {
            uri = toFileUri(model);
        } else {
            uri = Uri.parse(model);
            if (uri.getScheme() == null) {
                uri = toFileUri(model);
            }
        }
        return this.uriLoader.getResourceFetcher(uri, width, height);
    }

    private static Uri toFileUri(String path) {
        File file;
        new File(path);
        return Uri.fromFile(file);
    }
}
