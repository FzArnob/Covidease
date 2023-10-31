package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import java.io.IOException;
import java.io.InputStream;

public class ImageVideoBitmapDecoder implements ResourceDecoder<ImageVideoWrapper, Bitmap> {
    private static final String TAG = "ImageVideoDecoder";
    private final ResourceDecoder<ParcelFileDescriptor, Bitmap> fileDescriptorDecoder;
    private final ResourceDecoder<InputStream, Bitmap> streamDecoder;

    public ImageVideoBitmapDecoder(ResourceDecoder<InputStream, Bitmap> streamDecoder2, ResourceDecoder<ParcelFileDescriptor, Bitmap> fileDescriptorDecoder2) {
        this.streamDecoder = streamDecoder2;
        this.fileDescriptorDecoder = fileDescriptorDecoder2;
    }

    public Resource<Bitmap> decode(ImageVideoWrapper imageVideoWrapper, int i, int i2) throws IOException {
        ParcelFileDescriptor fileDescriptor;
        ImageVideoWrapper source = imageVideoWrapper;
        int width = i;
        int height = i2;
        Resource<Bitmap> result = null;
        InputStream is = source.getStream();
        if (is != null) {
            try {
                result = this.streamDecoder.decode(is, width, height);
            } catch (IOException e) {
                IOException e2 = e;
                if (Log.isLoggable(TAG, 2)) {
                    int v = Log.v(TAG, "Failed to load image from stream, trying FileDescriptor", e2);
                }
            }
        }
        if (result == null && (fileDescriptor = source.getFileDescriptor()) != null) {
            result = this.fileDescriptorDecoder.decode(fileDescriptor, width, height);
        }
        return result;
    }

    public String getId() {
        return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
