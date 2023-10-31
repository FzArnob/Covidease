package com.bumptech.glide.load.resource.gifbitmap;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import java.io.IOException;
import java.io.InputStream;

public class GifBitmapWrapperStreamResourceDecoder implements ResourceDecoder<InputStream, GifBitmapWrapper> {
    private final ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> gifBitmapDecoder;

    public GifBitmapWrapperStreamResourceDecoder(ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> gifBitmapDecoder2) {
        this.gifBitmapDecoder = gifBitmapDecoder2;
    }

    public Resource<GifBitmapWrapper> decode(InputStream source, int width, int height) throws IOException {
        Object obj;
        new ImageVideoWrapper(source, (ParcelFileDescriptor) null);
        return this.gifBitmapDecoder.decode(obj, width, height);
    }

    public String getId() {
        return this.gifBitmapDecoder.getId();
    }
}
