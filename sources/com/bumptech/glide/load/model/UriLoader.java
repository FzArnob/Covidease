package com.bumptech.glide.load.model;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;
import org.shaded.apache.http.HttpHost;

public abstract class UriLoader<T> implements ModelLoader<Uri, T> {
    private final Context context;
    private final ModelLoader<GlideUrl, T> urlLoader;

    /* access modifiers changed from: protected */
    public abstract DataFetcher<T> getAssetPathFetcher(Context context2, String str);

    /* access modifiers changed from: protected */
    public abstract DataFetcher<T> getLocalUriFetcher(Context context2, Uri uri);

    public UriLoader(Context context2, ModelLoader<GlideUrl, T> urlLoader2) {
        this.context = context2;
        this.urlLoader = urlLoader2;
    }

    public final DataFetcher<T> getResourceFetcher(Uri uri, int i, int i2) {
        Object obj;
        Uri model = uri;
        int width = i;
        int height = i2;
        String scheme = model.getScheme();
        DataFetcher<T> result = null;
        if (isLocalUri(scheme)) {
            if (AssetUriParser.isAssetUri(model)) {
                result = getAssetPathFetcher(this.context, AssetUriParser.toAssetPath(model));
            } else {
                result = getLocalUriFetcher(this.context, model);
            }
        } else if (this.urlLoader != null && (HttpHost.DEFAULT_SCHEME_NAME.equals(scheme) || "https".equals(scheme))) {
            new GlideUrl(model.toString());
            result = this.urlLoader.getResourceFetcher(obj, width, height);
        }
        return result;
    }

    private static boolean isLocalUri(String str) {
        String scheme = str;
        return "file".equals(scheme) || "content".equals(scheme) || "android.resource".equals(scheme);
    }
}
