package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class BitmapToGlideDrawableTranscoder implements ResourceTranscoder<Bitmap, GlideDrawable> {
    private final GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BitmapToGlideDrawableTranscoder(android.content.Context r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            com.bumptech.glide.load.resource.transcode.GlideBitmapDrawableTranscoder r3 = new com.bumptech.glide.load.resource.transcode.GlideBitmapDrawableTranscoder
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            r2.<init>((com.bumptech.glide.load.resource.transcode.GlideBitmapDrawableTranscoder) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.transcode.BitmapToGlideDrawableTranscoder.<init>(android.content.Context):void");
    }

    public BitmapToGlideDrawableTranscoder(GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder2) {
        this.glideBitmapDrawableTranscoder = glideBitmapDrawableTranscoder2;
    }

    public Resource<GlideDrawable> transcode(Resource<Bitmap> toTranscode) {
        return this.glideBitmapDrawableTranscoder.transcode(toTranscode);
    }

    public String getId() {
        return this.glideBitmapDrawableTranscoder.getId();
    }
}
