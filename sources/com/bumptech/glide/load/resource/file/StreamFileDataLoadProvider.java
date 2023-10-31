package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.NullResourceEncoder;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;
import java.io.InputStream;

public class StreamFileDataLoadProvider implements DataLoadProvider<InputStream, File> {
    private static final ErrorSourceDecoder ERROR_DECODER;
    private final ResourceDecoder<File, File> cacheDecoder;
    private final Encoder<InputStream> encoder;

    static {
        ErrorSourceDecoder errorSourceDecoder;
        new ErrorSourceDecoder((C15311) null);
        ERROR_DECODER = errorSourceDecoder;
    }

    public StreamFileDataLoadProvider() {
        ResourceDecoder<File, File> resourceDecoder;
        Encoder<InputStream> encoder2;
        new FileDecoder();
        this.cacheDecoder = resourceDecoder;
        new StreamEncoder();
        this.encoder = encoder2;
    }

    public ResourceDecoder<File, File> getCacheDecoder() {
        return this.cacheDecoder;
    }

    public ResourceDecoder<InputStream, File> getSourceDecoder() {
        return ERROR_DECODER;
    }

    public Encoder<InputStream> getSourceEncoder() {
        return this.encoder;
    }

    public ResourceEncoder<File> getEncoder() {
        return NullResourceEncoder.get();
    }

    private static class ErrorSourceDecoder implements ResourceDecoder<InputStream, File> {
        private ErrorSourceDecoder() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ ErrorSourceDecoder(C15311 r4) {
            this();
            C15311 r1 = r4;
        }

        public Resource<File> decode(InputStream inputStream, int i, int i2) {
            Throwable th;
            InputStream inputStream2 = inputStream;
            int i3 = i;
            int i4 = i2;
            Throwable th2 = th;
            new Error("You cannot decode a File from an InputStream by default, try either #diskCacheStratey(DiskCacheStrategy.SOURCE) to avoid this call or #decoder(ResourceDecoder) to replace this Decoder");
            throw th2;
        }

        public String getId() {
            return "";
        }
    }
}
