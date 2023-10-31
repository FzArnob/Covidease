package com.bumptech.glide.load.model;

import com.bumptech.glide.load.model.LazyHeaders;
import java.util.Collections;
import java.util.Map;

public interface Headers {
    public static final Headers DEFAULT;
    @Deprecated
    public static final Headers NONE;

    Map<String, String> getHeaders();

    static {
        Headers headers;
        LazyHeaders.Builder builder;
        new Headers() {
            public Map<String, String> getHeaders() {
                return Collections.emptyMap();
            }
        };
        NONE = headers;
        new LazyHeaders.Builder();
        DEFAULT = builder.build();
    }
}
