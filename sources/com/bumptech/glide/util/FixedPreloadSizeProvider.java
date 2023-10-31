package com.bumptech.glide.util;

import com.bumptech.glide.ListPreloader;
import java.util.Arrays;

public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int width, int height) {
        int[] iArr = new int[2];
        iArr[0] = width;
        int[] iArr2 = iArr;
        iArr2[1] = height;
        this.size = iArr2;
    }

    public int[] getPreloadSize(T t, int i, int i2) {
        T t2 = t;
        int i3 = i;
        int i4 = i2;
        return Arrays.copyOf(this.size, this.size.length);
    }
}
