package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bytes.BytesResource;
import java.io.ByteArrayOutputStream;

public class BitmapBytesTranscoder implements ResourceTranscoder<Bitmap, byte[]> {
    private final Bitmap.CompressFormat compressFormat;
    private final int quality;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BitmapBytesTranscoder() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    public BitmapBytesTranscoder(Bitmap.CompressFormat compressFormat2, int quality2) {
        this.compressFormat = compressFormat2;
        this.quality = quality2;
    }

    public Resource<byte[]> transcode(Resource<Bitmap> resource) {
        ByteArrayOutputStream byteArrayOutputStream;
        Resource<byte[]> resource2;
        Resource<Bitmap> toTranscode = resource;
        new ByteArrayOutputStream();
        ByteArrayOutputStream os = byteArrayOutputStream;
        boolean compress = toTranscode.get().compress(this.compressFormat, this.quality, os);
        toTranscode.recycle();
        new BytesResource(os.toByteArray());
        return resource2;
    }

    public String getId() {
        return "BitmapBytesTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
