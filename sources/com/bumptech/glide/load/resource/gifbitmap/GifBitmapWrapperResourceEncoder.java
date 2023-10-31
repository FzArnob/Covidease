package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.OutputStream;

public class GifBitmapWrapperResourceEncoder implements ResourceEncoder<GifBitmapWrapper> {
    private final ResourceEncoder<Bitmap> bitmapEncoder;
    private final ResourceEncoder<GifDrawable> gifEncoder;

    /* renamed from: id */
    private String f317id;

    public GifBitmapWrapperResourceEncoder(ResourceEncoder<Bitmap> bitmapEncoder2, ResourceEncoder<GifDrawable> gifEncoder2) {
        this.bitmapEncoder = bitmapEncoder2;
        this.gifEncoder = gifEncoder2;
    }

    public boolean encode(Resource<GifBitmapWrapper> resource, OutputStream outputStream) {
        OutputStream os = outputStream;
        GifBitmapWrapper gifBitmap = resource.get();
        Resource<Bitmap> bitmapResource = gifBitmap.getBitmapResource();
        if (bitmapResource != null) {
            return this.bitmapEncoder.encode(bitmapResource, os);
        }
        return this.gifEncoder.encode(gifBitmap.getGifResource(), os);
    }

    public String getId() {
        StringBuilder sb;
        if (this.f317id == null) {
            new StringBuilder();
            this.f317id = sb.append(this.bitmapEncoder.getId()).append(this.gifEncoder.getId()).toString();
        }
        return this.f317id;
    }
}
