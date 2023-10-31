package com.bumptech.glide.load.model;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.data.DataFetcher;

public class ResourceLoader<T> implements ModelLoader<Integer, T> {
    private static final String TAG = "ResourceLoader";
    private final Resources resources;
    private final ModelLoader<Uri, T> uriLoader;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ResourceLoader(Context context, ModelLoader<Uri, T> uriLoader2) {
        this(context.getResources(), uriLoader2);
    }

    public ResourceLoader(Resources resources2, ModelLoader<Uri, T> uriLoader2) {
        this.resources = resources2;
        this.uriLoader = uriLoader2;
    }

    public DataFetcher<T> getResourceFetcher(Integer num, int i, int i2) {
        StringBuilder sb;
        StringBuilder sb2;
        Integer model = num;
        int width = i;
        int height = i2;
        Uri uri = null;
        try {
            new StringBuilder();
            uri = Uri.parse(sb2.append("android.resource://").append(this.resources.getResourcePackageName(model.intValue())).append('/').append(this.resources.getResourceTypeName(model.intValue())).append('/').append(this.resources.getResourceEntryName(model.intValue())).toString());
        } catch (Resources.NotFoundException e) {
            Resources.NotFoundException e2 = e;
            if (Log.isLoggable(TAG, 5)) {
                new StringBuilder();
                int w = Log.w(TAG, sb.append("Received invalid resource id: ").append(model).toString(), e2);
            }
        }
        if (uri != null) {
            return this.uriLoader.getResourceFetcher(uri, width, height);
        }
        return null;
    }
}
