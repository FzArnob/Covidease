package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.ByteArrayPool;
import java.io.IOException;
import java.io.InputStream;

public class GifBitmapWrapperResourceDecoder implements ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> {
    private static final ImageTypeParser DEFAULT_PARSER;
    private static final BufferedStreamFactory DEFAULT_STREAM_FACTORY;
    static final int MARK_LIMIT_BYTES = 2048;
    private final ResourceDecoder<ImageVideoWrapper, Bitmap> bitmapDecoder;
    private final BitmapPool bitmapPool;
    private final ResourceDecoder<InputStream, GifDrawable> gifDecoder;

    /* renamed from: id */
    private String f316id;
    private final ImageTypeParser parser;
    private final BufferedStreamFactory streamFactory;

    static {
        ImageTypeParser imageTypeParser;
        BufferedStreamFactory bufferedStreamFactory;
        new ImageTypeParser();
        DEFAULT_PARSER = imageTypeParser;
        new BufferedStreamFactory();
        DEFAULT_STREAM_FACTORY = bufferedStreamFactory;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GifBitmapWrapperResourceDecoder(ResourceDecoder<ImageVideoWrapper, Bitmap> bitmapDecoder2, ResourceDecoder<InputStream, GifDrawable> gifDecoder2, BitmapPool bitmapPool2) {
        this(bitmapDecoder2, gifDecoder2, bitmapPool2, DEFAULT_PARSER, DEFAULT_STREAM_FACTORY);
    }

    GifBitmapWrapperResourceDecoder(ResourceDecoder<ImageVideoWrapper, Bitmap> bitmapDecoder2, ResourceDecoder<InputStream, GifDrawable> gifDecoder2, BitmapPool bitmapPool2, ImageTypeParser parser2, BufferedStreamFactory streamFactory2) {
        this.bitmapDecoder = bitmapDecoder2;
        this.gifDecoder = gifDecoder2;
        this.bitmapPool = bitmapPool2;
        this.parser = parser2;
        this.streamFactory = streamFactory2;
    }

    public Resource<GifBitmapWrapper> decode(ImageVideoWrapper source, int width, int height) throws IOException {
        GifBitmapWrapper wrapper;
        Resource<GifBitmapWrapper> resource;
        Resource<GifBitmapWrapper> resource2;
        ByteArrayPool pool = ByteArrayPool.get();
        byte[] tempBytes = pool.getBytes();
        try {
            th = decode(source, width, height, tempBytes);
            if (wrapper != null) {
                resource = resource2;
                new GifBitmapWrapperResource(wrapper);
            } else {
                resource = null;
            }
            return resource;
        } finally {
            wrapper = th;
            boolean releaseBytes = pool.releaseBytes(tempBytes);
            GifBitmapWrapper gifBitmapWrapper = wrapper;
        }
    }

    private GifBitmapWrapper decode(ImageVideoWrapper imageVideoWrapper, int i, int i2, byte[] bArr) throws IOException {
        GifBitmapWrapper result;
        ImageVideoWrapper source = imageVideoWrapper;
        int width = i;
        int height = i2;
        byte[] bytes = bArr;
        if (source.getStream() != null) {
            result = decodeStream(source, width, height, bytes);
        } else {
            result = decodeBitmapWrapper(source, width, height);
        }
        return result;
    }

    private GifBitmapWrapper decodeStream(ImageVideoWrapper imageVideoWrapper, int i, int i2, byte[] bytes) throws IOException {
        ImageVideoWrapper forBitmapDecoder;
        ImageVideoWrapper source = imageVideoWrapper;
        int width = i;
        int height = i2;
        InputStream bis = this.streamFactory.build(source.getStream(), bytes);
        bis.mark(2048);
        ImageHeaderParser.ImageType type = this.parser.parse(bis);
        bis.reset();
        GifBitmapWrapper result = null;
        if (type == ImageHeaderParser.ImageType.GIF) {
            result = decodeGifWrapper(bis, width, height);
        }
        if (result == null) {
            new ImageVideoWrapper(bis, source.getFileDescriptor());
            result = decodeBitmapWrapper(forBitmapDecoder, width, height);
        }
        return result;
    }

    private GifBitmapWrapper decodeGifWrapper(InputStream bis, int width, int height) throws IOException {
        Resource<Bitmap> bitmapResource;
        GifBitmapWrapper gifBitmapWrapper;
        GifBitmapWrapper gifBitmapWrapper2;
        GifBitmapWrapper result = null;
        Resource<GifDrawable> gifResource = this.gifDecoder.decode(bis, width, height);
        if (gifResource != null) {
            GifDrawable drawable = gifResource.get();
            if (drawable.getFrameCount() > 1) {
                new GifBitmapWrapper((Resource<Bitmap>) null, gifResource);
                result = gifBitmapWrapper2;
            } else {
                new BitmapResource(drawable.getFirstFrame(), this.bitmapPool);
                new GifBitmapWrapper(bitmapResource, (Resource<GifDrawable>) null);
                result = gifBitmapWrapper;
            }
        }
        return result;
    }

    private GifBitmapWrapper decodeBitmapWrapper(ImageVideoWrapper toDecode, int width, int height) throws IOException {
        GifBitmapWrapper gifBitmapWrapper;
        GifBitmapWrapper result = null;
        Resource<Bitmap> bitmapResource = this.bitmapDecoder.decode(toDecode, width, height);
        if (bitmapResource != null) {
            new GifBitmapWrapper(bitmapResource, (Resource<GifDrawable>) null);
            result = gifBitmapWrapper;
        }
        return result;
    }

    public String getId() {
        StringBuilder sb;
        if (this.f316id == null) {
            new StringBuilder();
            this.f316id = sb.append(this.gifDecoder.getId()).append(this.bitmapDecoder.getId()).toString();
        }
        return this.f316id;
    }

    static class BufferedStreamFactory {
        BufferedStreamFactory() {
        }

        public InputStream build(InputStream is, byte[] buffer) {
            InputStream inputStream;
            new RecyclableBufferedInputStream(is, buffer);
            return inputStream;
        }
    }

    static class ImageTypeParser {
        ImageTypeParser() {
        }

        public ImageHeaderParser.ImageType parse(InputStream is) throws IOException {
            ImageHeaderParser imageHeaderParser;
            new ImageHeaderParser(is);
            return imageHeaderParser.getType();
        }
    }
}
