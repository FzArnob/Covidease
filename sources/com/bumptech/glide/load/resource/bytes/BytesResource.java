package com.bumptech.glide.load.resource.bytes;

import com.bumptech.glide.load.engine.Resource;

public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    public BytesResource(byte[] bArr) {
        Throwable th;
        byte[] bytes2 = bArr;
        if (bytes2 == null) {
            Throwable th2 = th;
            new NullPointerException("Bytes must not be null");
            throw th2;
        }
        this.bytes = bytes2;
    }

    public byte[] get() {
        return this.bytes;
    }

    public int getSize() {
        return this.bytes.length;
    }

    public void recycle() {
    }
}
