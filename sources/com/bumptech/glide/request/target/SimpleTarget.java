package com.bumptech.glide.request.target;

import com.bumptech.glide.util.Util;

public abstract class SimpleTarget<Z> extends BaseTarget<Z> {
    private final int height;
    private final int width;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleTarget() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public SimpleTarget(int width2, int height2) {
        this.width = width2;
        this.height = height2;
    }

    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        Throwable th;
        StringBuilder sb;
        SizeReadyCallback cb = sizeReadyCallback;
        if (!Util.isValidDimensions(this.width, this.height)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ").append(this.width).append(" and height: ").append(this.height).append(", either provide dimensions in the constructor").append(" or call override()").toString());
            throw th2;
        }
        cb.onSizeReady(this.width, this.height);
    }
}
