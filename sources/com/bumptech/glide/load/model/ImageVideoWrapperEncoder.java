package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.Encoder;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageVideoWrapperEncoder implements Encoder<ImageVideoWrapper> {
    private final Encoder<ParcelFileDescriptor> fileDescriptorEncoder;

    /* renamed from: id */
    private String f311id;
    private final Encoder<InputStream> streamEncoder;

    public ImageVideoWrapperEncoder(Encoder<InputStream> streamEncoder2, Encoder<ParcelFileDescriptor> fileDescriptorEncoder2) {
        this.streamEncoder = streamEncoder2;
        this.fileDescriptorEncoder = fileDescriptorEncoder2;
    }

    public boolean encode(ImageVideoWrapper imageVideoWrapper, OutputStream outputStream) {
        ImageVideoWrapper data = imageVideoWrapper;
        OutputStream os = outputStream;
        if (data.getStream() != null) {
            return this.streamEncoder.encode(data.getStream(), os);
        }
        return this.fileDescriptorEncoder.encode(data.getFileDescriptor(), os);
    }

    public String getId() {
        StringBuilder sb;
        if (this.f311id == null) {
            new StringBuilder();
            this.f311id = sb.append(this.streamEncoder.getId()).append(this.fileDescriptorEncoder.getId()).toString();
        }
        return this.f311id;
    }
}
