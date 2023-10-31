package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

class EngineKey implements Key {
    private static final String EMPTY_LOG_STRING = "";
    private final ResourceDecoder cacheDecoder;
    private final ResourceDecoder decoder;
    private final ResourceEncoder encoder;
    private int hashCode;
    private final int height;

    /* renamed from: id */
    private final String f309id;
    private Key originalKey;
    private final Key signature;
    private final Encoder sourceEncoder;
    private String stringKey;
    private final ResourceTranscoder transcoder;
    private final Transformation transformation;
    private final int width;

    public EngineKey(String id, Key signature2, int width2, int height2, ResourceDecoder cacheDecoder2, ResourceDecoder decoder2, Transformation transformation2, ResourceEncoder encoder2, ResourceTranscoder transcoder2, Encoder sourceEncoder2) {
        this.f309id = id;
        this.signature = signature2;
        this.width = width2;
        this.height = height2;
        this.cacheDecoder = cacheDecoder2;
        this.decoder = decoder2;
        this.transformation = transformation2;
        this.encoder = encoder2;
        this.transcoder = transcoder2;
        this.sourceEncoder = sourceEncoder2;
    }

    public Key getOriginalKey() {
        Key key;
        if (this.originalKey == null) {
            new OriginalKey(this.f309id, this.signature);
            this.originalKey = key;
        }
        return this.originalKey;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EngineKey engineKey = (EngineKey) o;
        if (!this.f309id.equals(engineKey.f309id)) {
            return false;
        }
        if (!this.signature.equals(engineKey.signature)) {
            return false;
        }
        if (this.height != engineKey.height) {
            return false;
        }
        if (this.width != engineKey.width) {
            return false;
        }
        if ((this.transformation == null) ^ (engineKey.transformation == null)) {
            return false;
        }
        if (this.transformation != null && !this.transformation.getId().equals(engineKey.transformation.getId())) {
            return false;
        }
        if ((this.decoder == null) ^ (engineKey.decoder == null)) {
            return false;
        }
        if (this.decoder != null && !this.decoder.getId().equals(engineKey.decoder.getId())) {
            return false;
        }
        if ((this.cacheDecoder == null) ^ (engineKey.cacheDecoder == null)) {
            return false;
        }
        if (this.cacheDecoder != null && !this.cacheDecoder.getId().equals(engineKey.cacheDecoder.getId())) {
            return false;
        }
        if ((this.encoder == null) ^ (engineKey.encoder == null)) {
            return false;
        }
        if (this.encoder != null && !this.encoder.getId().equals(engineKey.encoder.getId())) {
            return false;
        }
        if ((this.transcoder == null) ^ (engineKey.transcoder == null)) {
            return false;
        }
        if (this.transcoder != null && !this.transcoder.getId().equals(engineKey.transcoder.getId())) {
            return false;
        }
        if ((this.sourceEncoder == null) ^ (engineKey.sourceEncoder == null)) {
            return false;
        }
        if (this.sourceEncoder == null || this.sourceEncoder.getId().equals(engineKey.sourceEncoder.getId())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.f309id.hashCode();
            this.hashCode = (31 * this.hashCode) + this.signature.hashCode();
            this.hashCode = (31 * this.hashCode) + this.width;
            this.hashCode = (31 * this.hashCode) + this.height;
            this.hashCode = (31 * this.hashCode) + (this.cacheDecoder != null ? this.cacheDecoder.getId().hashCode() : 0);
            this.hashCode = (31 * this.hashCode) + (this.decoder != null ? this.decoder.getId().hashCode() : 0);
            this.hashCode = (31 * this.hashCode) + (this.transformation != null ? this.transformation.getId().hashCode() : 0);
            this.hashCode = (31 * this.hashCode) + (this.encoder != null ? this.encoder.getId().hashCode() : 0);
            this.hashCode = (31 * this.hashCode) + (this.transcoder != null ? this.transcoder.getId().hashCode() : 0);
            this.hashCode = (31 * this.hashCode) + (this.sourceEncoder != null ? this.sourceEncoder.getId().hashCode() : 0);
        }
        return this.hashCode;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (this.stringKey == null) {
            new StringBuilder();
            StringBuilder append = sb.append("EngineKey{").append(this.f309id).append('+').append(this.signature).append("+[").append(this.width).append('x').append(this.height).append("]+").append('\'');
            if (this.cacheDecoder != null) {
                str = this.cacheDecoder.getId();
            } else {
                str = "";
            }
            StringBuilder append2 = append.append(str).append('\'').append('+').append('\'');
            if (this.decoder != null) {
                str2 = this.decoder.getId();
            } else {
                str2 = "";
            }
            StringBuilder append3 = append2.append(str2).append('\'').append('+').append('\'');
            if (this.transformation != null) {
                str3 = this.transformation.getId();
            } else {
                str3 = "";
            }
            StringBuilder append4 = append3.append(str3).append('\'').append('+').append('\'');
            if (this.encoder != null) {
                str4 = this.encoder.getId();
            } else {
                str4 = "";
            }
            StringBuilder append5 = append4.append(str4).append('\'').append('+').append('\'');
            if (this.transcoder != null) {
                str5 = this.transcoder.getId();
            } else {
                str5 = "";
            }
            StringBuilder append6 = append5.append(str5).append('\'').append('+').append('\'');
            if (this.sourceEncoder != null) {
                str6 = this.sourceEncoder.getId();
            } else {
                str6 = "";
            }
            this.stringKey = append6.append(str6).append('\'').append('}').toString();
        }
        return this.stringKey;
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        MessageDigest messageDigest2 = messageDigest;
        byte[] dimensions = ByteBuffer.allocate(8).putInt(this.width).putInt(this.height).array();
        this.signature.updateDiskCacheKey(messageDigest2);
        messageDigest2.update(this.f309id.getBytes("UTF-8"));
        messageDigest2.update(dimensions);
        messageDigest2.update((this.cacheDecoder != null ? this.cacheDecoder.getId() : "").getBytes("UTF-8"));
        messageDigest2.update((this.decoder != null ? this.decoder.getId() : "").getBytes("UTF-8"));
        messageDigest2.update((this.transformation != null ? this.transformation.getId() : "").getBytes("UTF-8"));
        messageDigest2.update((this.encoder != null ? this.encoder.getId() : "").getBytes("UTF-8"));
        messageDigest2.update((this.sourceEncoder != null ? this.sourceEncoder.getId() : "").getBytes("UTF-8"));
    }
}
