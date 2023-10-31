package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

public class BitmapPoolAdapter implements BitmapPool {
    public BitmapPoolAdapter() {
    }

    public int getMaxSize() {
        return 0;
    }

    public void setSizeMultiplier(float sizeMultiplier) {
    }

    public boolean put(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        return false;
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        int i3 = i;
        int i4 = i2;
        Bitmap.Config config2 = config;
        return null;
    }

    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        int i3 = i;
        int i4 = i2;
        Bitmap.Config config2 = config;
        return null;
    }

    public void clearMemory() {
    }

    public void trimMemory(int level) {
    }
}
