package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.io.OutputStream;

public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final int DEFAULT_COMPRESSION_QUALITY = 90;
    private static final String TAG = "BitmapEncoder";
    private Bitmap.CompressFormat compressFormat;
    private int quality;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BitmapEncoder() {
        this((Bitmap.CompressFormat) null, 90);
    }

    public BitmapEncoder(Bitmap.CompressFormat compressFormat2, int quality2) {
        this.compressFormat = compressFormat2;
        this.quality = quality2;
    }

    public boolean encode(Resource<Bitmap> resource, OutputStream os) {
        StringBuilder sb;
        Bitmap bitmap = resource.get();
        long start = LogTime.getLogTime();
        Bitmap.CompressFormat format = getFormat(bitmap);
        boolean compress = bitmap.compress(format, this.quality, os);
        if (Log.isLoggable(TAG, 2)) {
            new StringBuilder();
            int v = Log.v(TAG, sb.append("Compressed with type: ").append(format).append(" of size ").append(Util.getBitmapByteSize(bitmap)).append(" in ").append(LogTime.getElapsedMillis(start)).toString());
        }
        return true;
    }

    public String getId() {
        return "BitmapEncoder.com.bumptech.glide.load.resource.bitmap";
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (this.compressFormat != null) {
            return this.compressFormat;
        }
        if (bitmap2.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }
}
