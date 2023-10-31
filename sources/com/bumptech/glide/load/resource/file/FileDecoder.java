package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;

public class FileDecoder implements ResourceDecoder<File, File> {
    public FileDecoder() {
    }

    public Resource<File> decode(File source, int i, int i2) {
        Resource<File> resource;
        int i3 = i;
        int i4 = i2;
        new FileResource(source);
        return resource;
    }

    public String getId() {
        return "";
    }
}
