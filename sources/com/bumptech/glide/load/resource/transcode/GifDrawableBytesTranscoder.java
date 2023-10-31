package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bytes.BytesResource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
    public GifDrawableBytesTranscoder() {
    }

    public Resource<byte[]> transcode(Resource<GifDrawable> toTranscode) {
        Resource<byte[]> resource;
        new BytesResource(toTranscode.get().getData());
        return resource;
    }

    public String getId() {
        return "GifDrawableBytesTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
