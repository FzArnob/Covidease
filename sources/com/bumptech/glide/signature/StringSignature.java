package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class StringSignature implements Key {
    private final String signature;

    public StringSignature(String str) {
        Throwable th;
        String signature2 = str;
        if (signature2 == null) {
            Throwable th2 = th;
            new NullPointerException("Signature cannot be null!");
            throw th2;
        }
        this.signature = signature2;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.signature.equals(((StringSignature) o).signature);
    }

    public int hashCode() {
        return this.signature.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.signature.getBytes("UTF-8"));
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("StringSignature{signature='").append(this.signature).append('\'').append('}').toString();
    }
}
