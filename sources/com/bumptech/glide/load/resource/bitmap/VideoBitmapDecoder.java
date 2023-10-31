package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;

public class VideoBitmapDecoder implements BitmapDecoder<ParcelFileDescriptor> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY;
    private static final int NO_FRAME = -1;
    private MediaMetadataRetrieverFactory factory;
    private int frame;

    static {
        MediaMetadataRetrieverFactory mediaMetadataRetrieverFactory;
        new MediaMetadataRetrieverFactory();
        DEFAULT_FACTORY = mediaMetadataRetrieverFactory;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoBitmapDecoder() {
        this(DEFAULT_FACTORY, -1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoBitmapDecoder(int frame2) {
        this(DEFAULT_FACTORY, checkValidFrame(frame2));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    VideoBitmapDecoder(MediaMetadataRetrieverFactory factory2) {
        this(factory2, -1);
    }

    VideoBitmapDecoder(MediaMetadataRetrieverFactory factory2, int frame2) {
        this.factory = factory2;
        this.frame = frame2;
    }

    public Bitmap decode(ParcelFileDescriptor parcelFileDescriptor, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) throws IOException {
        Bitmap result;
        ParcelFileDescriptor resource = parcelFileDescriptor;
        BitmapPool bitmapPool2 = bitmapPool;
        int i3 = i;
        int i4 = i2;
        DecodeFormat decodeFormat2 = decodeFormat;
        MediaMetadataRetriever mediaMetadataRetriever = this.factory.build();
        mediaMetadataRetriever.setDataSource(resource.getFileDescriptor());
        if (this.frame >= 0) {
            result = mediaMetadataRetriever.getFrameAtTime((long) this.frame);
        } else {
            result = mediaMetadataRetriever.getFrameAtTime();
        }
        mediaMetadataRetriever.release();
        resource.close();
        return result;
    }

    public String getId() {
        return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }

    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            MediaMetadataRetriever mediaMetadataRetriever;
            new MediaMetadataRetriever();
            return mediaMetadataRetriever;
        }
    }

    private static int checkValidFrame(int i) {
        Throwable th;
        int frame2 = i;
        if (frame2 >= 0) {
            return frame2;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Requested frame must be non-negative");
        throw th2;
    }
}
