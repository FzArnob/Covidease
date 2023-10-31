package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.load.engine.Resource;

public class UnitTranscoder<Z> implements ResourceTranscoder<Z, Z> {
    private static final UnitTranscoder<?> UNIT_TRANSCODER;

    public UnitTranscoder() {
    }

    static {
        UnitTranscoder<?> unitTranscoder;
        new UnitTranscoder<>();
        UNIT_TRANSCODER = unitTranscoder;
    }

    public static <Z> ResourceTranscoder<Z, Z> get() {
        return UNIT_TRANSCODER;
    }

    public Resource<Z> transcode(Resource<Z> toTranscode) {
        return toTranscode;
    }

    public String getId() {
        return "";
    }
}
