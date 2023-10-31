package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class GifResourceDecoder implements ResourceDecoder<InputStream, GifDrawable> {
    private static final GifDecoderPool DECODER_POOL;
    private static final GifHeaderParserPool PARSER_POOL;
    private static final String TAG = "GifResourceDecoder";
    private final BitmapPool bitmapPool;
    private final Context context;
    private final GifDecoderPool decoderPool;
    private final GifHeaderParserPool parserPool;
    private final GifBitmapProvider provider;

    static {
        GifHeaderParserPool gifHeaderParserPool;
        GifDecoderPool gifDecoderPool;
        new GifHeaderParserPool();
        PARSER_POOL = gifHeaderParserPool;
        new GifDecoderPool();
        DECODER_POOL = gifDecoderPool;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifResourceDecoder(android.content.Context r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            com.bumptech.glide.Glide r4 = com.bumptech.glide.Glide.get(r4)
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r4 = r4.getBitmapPool()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.GifResourceDecoder.<init>(android.content.Context):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GifResourceDecoder(Context context2, BitmapPool bitmapPool2) {
        this(context2, bitmapPool2, PARSER_POOL, DECODER_POOL);
    }

    GifResourceDecoder(Context context2, BitmapPool bitmapPool2, GifHeaderParserPool parserPool2, GifDecoderPool decoderPool2) {
        GifBitmapProvider gifBitmapProvider;
        BitmapPool bitmapPool3 = bitmapPool2;
        this.context = context2.getApplicationContext();
        this.bitmapPool = bitmapPool3;
        this.decoderPool = decoderPool2;
        new GifBitmapProvider(bitmapPool3);
        this.provider = gifBitmapProvider;
        this.parserPool = parserPool2;
    }

    /* JADX INFO: finally extract failed */
    public GifDrawableResource decode(InputStream source, int width, int height) {
        byte[] data = inputStreamToBytes(source);
        GifHeaderParser parser = this.parserPool.obtain(data);
        GifDecoder decoder = this.decoderPool.obtain(this.provider);
        try {
            GifDrawableResource decode = decode(data, width, height, parser, decoder);
            this.parserPool.release(parser);
            this.decoderPool.release(decoder);
            return decode;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.parserPool.release(parser);
            this.decoderPool.release(decoder);
            throw th2;
        }
    }

    private GifDrawableResource decode(byte[] bArr, int i, int i2, GifHeaderParser parser, GifDecoder gifDecoder) {
        GifDrawable gifDrawable;
        GifDrawableResource gifDrawableResource;
        byte[] data = bArr;
        int width = i;
        int height = i2;
        GifDecoder decoder = gifDecoder;
        GifHeader header = parser.parseHeader();
        if (header.getNumFrames() <= 0 || header.getStatus() != 0) {
            return null;
        }
        Bitmap firstFrame = decodeFirstFrame(decoder, header, data);
        if (firstFrame == null) {
            return null;
        }
        new GifDrawable(this.context, this.provider, this.bitmapPool, UnitTransformation.get(), width, height, header, data, firstFrame);
        new GifDrawableResource(gifDrawable);
        return gifDrawableResource;
    }

    private Bitmap decodeFirstFrame(GifDecoder gifDecoder, GifHeader header, byte[] data) {
        GifDecoder decoder = gifDecoder;
        decoder.setData(header, data);
        decoder.advance();
        return decoder.getNextFrame();
    }

    public String getId() {
        return "";
    }

    private static byte[] inputStreamToBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream is = inputStream;
        new ByteArrayOutputStream(16384);
        ByteArrayOutputStream buffer = byteArrayOutputStream;
        try {
            byte[] data = new byte[16384];
            while (true) {
                int read = is.read(data);
                int nRead = read;
                if (read == -1) {
                    break;
                }
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
        } catch (IOException e) {
            int w = Log.w(TAG, "Error reading data from stream", e);
        }
        return buffer.toByteArray();
    }

    static class GifDecoderPool {
        private final Queue<GifDecoder> pool = Util.createQueue(0);

        GifDecoderPool() {
        }

        public synchronized GifDecoder obtain(GifDecoder.BitmapProvider bitmapProvider) {
            GifDecoder gifDecoder;
            GifDecoder gifDecoder2;
            GifDecoder.BitmapProvider bitmapProvider2 = bitmapProvider;
            synchronized (this) {
                GifDecoder result = this.pool.poll();
                if (result == null) {
                    GifDecoder gifDecoder3 = gifDecoder2;
                    new GifDecoder(bitmapProvider2);
                    result = gifDecoder3;
                }
                gifDecoder = result;
            }
            return gifDecoder;
        }

        public synchronized void release(GifDecoder gifDecoder) {
            GifDecoder decoder = gifDecoder;
            synchronized (this) {
                decoder.clear();
                boolean offer = this.pool.offer(decoder);
            }
        }
    }

    static class GifHeaderParserPool {
        private final Queue<GifHeaderParser> pool = Util.createQueue(0);

        GifHeaderParserPool() {
        }

        public synchronized GifHeaderParser obtain(byte[] bArr) {
            GifHeaderParser data;
            GifHeaderParser gifHeaderParser;
            byte[] data2 = bArr;
            synchronized (this) {
                GifHeaderParser result = this.pool.poll();
                if (result == null) {
                    new GifHeaderParser();
                    result = gifHeaderParser;
                }
                data = result.setData(data2);
            }
            return data;
        }

        public synchronized void release(GifHeaderParser gifHeaderParser) {
            GifHeaderParser parser = gifHeaderParser;
            synchronized (this) {
                parser.clear();
                boolean offer = this.pool.offer(parser);
            }
        }
    }
}
