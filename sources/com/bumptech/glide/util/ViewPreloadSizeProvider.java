package com.bumptech.glide.util;

import android.view.View;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.Arrays;

public class ViewPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T>, SizeReadyCallback {
    private int[] size;
    private SizeViewTarget viewTarget;

    public ViewPreloadSizeProvider() {
    }

    public ViewPreloadSizeProvider(View view) {
        setView(view);
    }

    public int[] getPreloadSize(T t, int i, int i2) {
        T t2 = t;
        int i3 = i;
        int i4 = i2;
        if (this.size == null) {
            return null;
        }
        return Arrays.copyOf(this.size, this.size.length);
    }

    public void onSizeReady(int width, int height) {
        int[] iArr = new int[2];
        iArr[0] = width;
        int[] iArr2 = iArr;
        iArr2[1] = height;
        this.size = iArr2;
        this.viewTarget = null;
    }

    public void setView(View view) {
        SizeViewTarget sizeViewTarget;
        View view2 = view;
        if (this.size == null && this.viewTarget == null) {
            new SizeViewTarget(view2, this);
            this.viewTarget = sizeViewTarget;
        }
    }

    private static final class SizeViewTarget extends ViewTarget<View, Object> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SizeViewTarget(View view, SizeReadyCallback callback) {
            super(view);
            getSize(callback);
        }

        public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
        }
    }
}
