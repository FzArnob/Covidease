package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;

public class FileDescriptorBitmapDecoder implements ResourceDecoder<ParcelFileDescriptor, Bitmap> {
    private final VideoBitmapDecoder bitmapDecoder;
    private final BitmapPool bitmapPool;
    private DecodeFormat decodeFormat;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileDescriptorBitmapDecoder(Context context) {
        this(Glide.get(context).getBitmapPool(), DecodeFormat.DEFAULT);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileDescriptorBitmapDecoder(Context context, DecodeFormat decodeFormat2) {
        this(Glide.get(context).getBitmapPool(), decodeFormat2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FileDescriptorBitmapDecoder(com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r9, com.bumptech.glide.load.DecodeFormat r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder r4 = new com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()
            r5 = r1
            r6 = r2
            r3.<init>(r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder.<init>(com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.DecodeFormat):void");
    }

    public FileDescriptorBitmapDecoder(VideoBitmapDecoder bitmapDecoder2, BitmapPool bitmapPool2, DecodeFormat decodeFormat2) {
        this.bitmapDecoder = bitmapDecoder2;
        this.bitmapPool = bitmapPool2;
        this.decodeFormat = decodeFormat2;
    }

    public Resource<Bitmap> decode(ParcelFileDescriptor source, int width, int height) throws IOException {
        return BitmapResource.obtain(this.bitmapDecoder.decode(source, this.bitmapPool, width, height, this.decodeFormat), this.bitmapPool);
    }

    public String getId() {
        return "FileDescriptorBitmapDecoder.com.bumptech.glide.load.data.bitmap";
    }
}
