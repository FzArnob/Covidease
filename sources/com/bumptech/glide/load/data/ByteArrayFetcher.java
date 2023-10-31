package com.bumptech.glide.load.data;

import com.bumptech.glide.Priority;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayFetcher implements DataFetcher<InputStream> {
    private final byte[] bytes;

    /* renamed from: id */
    private final String f307id;

    public ByteArrayFetcher(byte[] bytes2, String id) {
        this.bytes = bytes2;
        this.f307id = id;
    }

    public InputStream loadData(Priority priority) {
        InputStream inputStream;
        Priority priority2 = priority;
        new ByteArrayInputStream(this.bytes);
        return inputStream;
    }

    public void cleanup() {
    }

    public String getId() {
        return this.f307id;
    }

    public void cancel() {
    }
}
