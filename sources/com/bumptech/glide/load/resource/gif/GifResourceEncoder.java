package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifencoder.AnimatedGifEncoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.OutputStream;

public class GifResourceEncoder implements ResourceEncoder<GifDrawable> {
    private static final Factory FACTORY;
    private static final String TAG = "GifEncoder";
    private final BitmapPool bitmapPool;
    private final Factory factory;
    private final GifDecoder.BitmapProvider provider;

    static {
        Factory factory2;
        new Factory();
        FACTORY = factory2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GifResourceEncoder(BitmapPool bitmapPool2) {
        this(bitmapPool2, FACTORY);
    }

    GifResourceEncoder(BitmapPool bitmapPool2, Factory factory2) {
        GifDecoder.BitmapProvider bitmapProvider;
        BitmapPool bitmapPool3 = bitmapPool2;
        this.bitmapPool = bitmapPool3;
        new GifBitmapProvider(bitmapPool3);
        this.provider = bitmapProvider;
        this.factory = factory2;
    }

    /* JADX INFO: finally extract failed */
    public boolean encode(Resource<GifDrawable> resource, OutputStream outputStream) {
        StringBuilder sb;
        OutputStream os = outputStream;
        long startTime = LogTime.getLogTime();
        GifDrawable drawable = resource.get();
        Transformation<Bitmap> transformation = drawable.getFrameTransformation();
        if (transformation instanceof UnitTransformation) {
            return writeDataDirect(drawable.getData(), os);
        }
        GifDecoder decoder = decodeHeaders(drawable.getData());
        AnimatedGifEncoder encoder = this.factory.buildEncoder();
        if (!encoder.start(os)) {
            return false;
        }
        int i = 0;
        while (i < decoder.getFrameCount()) {
            Resource<Bitmap> transformedResource = getTransformedFrame(decoder.getNextFrame(), transformation, drawable);
            try {
                if (!encoder.addFrame(transformedResource.get())) {
                    transformedResource.recycle();
                    return false;
                }
                encoder.setDelay(decoder.getDelay(decoder.getCurrentFrameIndex()));
                decoder.advance();
                transformedResource.recycle();
                i++;
            } catch (Throwable th) {
                Throwable th2 = th;
                transformedResource.recycle();
                throw th2;
            }
        }
        boolean result = encoder.finish();
        if (Log.isLoggable(TAG, 2)) {
            new StringBuilder();
            int v = Log.v(TAG, sb.append("Encoded gif with ").append(decoder.getFrameCount()).append(" frames and ").append(drawable.getData().length).append(" bytes in ").append(LogTime.getElapsedMillis(startTime)).append(" ms").toString());
        }
        return result;
    }

    private boolean writeDataDirect(byte[] data, OutputStream os) {
        boolean success = true;
        try {
            os.write(data);
        } catch (IOException e) {
            IOException e2 = e;
            if (Log.isLoggable(TAG, 3)) {
                int d = Log.d(TAG, "Failed to write data to output stream in GifResourceEncoder", e2);
            }
            success = false;
        }
        return success;
    }

    private GifDecoder decodeHeaders(byte[] bArr) {
        byte[] data = bArr;
        GifHeaderParser parser = this.factory.buildParser();
        GifHeaderParser data2 = parser.setData(data);
        GifHeader header = parser.parseHeader();
        GifDecoder decoder = this.factory.buildDecoder(this.provider);
        decoder.setData(header, data);
        decoder.advance();
        return decoder;
    }

    private Resource<Bitmap> getTransformedFrame(Bitmap currentFrame, Transformation<Bitmap> transformation, GifDrawable gifDrawable) {
        GifDrawable drawable = gifDrawable;
        Resource<Bitmap> bitmapResource = this.factory.buildFrameResource(currentFrame, this.bitmapPool);
        Resource<Bitmap> transformedResource = transformation.transform(bitmapResource, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        if (!bitmapResource.equals(transformedResource)) {
            bitmapResource.recycle();
        }
        return transformedResource;
    }

    public String getId() {
        return "";
    }

    static class Factory {
        Factory() {
        }

        public GifDecoder buildDecoder(GifDecoder.BitmapProvider bitmapProvider) {
            GifDecoder gifDecoder;
            new GifDecoder(bitmapProvider);
            return gifDecoder;
        }

        public GifHeaderParser buildParser() {
            GifHeaderParser gifHeaderParser;
            new GifHeaderParser();
            return gifHeaderParser;
        }

        public AnimatedGifEncoder buildEncoder() {
            AnimatedGifEncoder animatedGifEncoder;
            new AnimatedGifEncoder();
            return animatedGifEncoder;
        }

        public Resource<Bitmap> buildFrameResource(Bitmap bitmap, BitmapPool bitmapPool) {
            Resource<Bitmap> resource;
            new BitmapResource(bitmap, bitmapPool);
            return resource;
        }
    }
}
