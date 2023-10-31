package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.IOException;

public abstract class AssetPathFetcher<T> implements DataFetcher<T> {
    private static final String TAG = "AssetUriFetcher";
    private final AssetManager assetManager;
    private final String assetPath;
    private T data;

    /* access modifiers changed from: protected */
    public abstract void close(T t) throws IOException;

    /* access modifiers changed from: protected */
    public abstract T loadResource(AssetManager assetManager2, String str) throws IOException;

    public AssetPathFetcher(AssetManager assetManager2, String assetPath2) {
        this.assetManager = assetManager2;
        this.assetPath = assetPath2;
    }

    public T loadData(Priority priority) throws Exception {
        Priority priority2 = priority;
        this.data = loadResource(this.assetManager, this.assetPath);
        return this.data;
    }

    public void cleanup() {
        if (this.data != null) {
            try {
                close(this.data);
            } catch (IOException e) {
                IOException e2 = e;
                if (Log.isLoggable(TAG, 2)) {
                    int v = Log.v(TAG, "Failed to close data", e2);
                }
            }
        }
    }

    public String getId() {
        return this.assetPath;
    }

    public void cancel() {
    }
}
