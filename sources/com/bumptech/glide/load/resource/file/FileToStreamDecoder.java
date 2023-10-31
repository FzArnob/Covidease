package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileToStreamDecoder<T> implements ResourceDecoder<File, T> {
    private static final FileOpener DEFAULT_FILE_OPENER;
    private final FileOpener fileOpener;
    private ResourceDecoder<InputStream, T> streamDecoder;

    static {
        FileOpener fileOpener2;
        new FileOpener();
        DEFAULT_FILE_OPENER = fileOpener2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileToStreamDecoder(ResourceDecoder<InputStream, T> streamDecoder2) {
        this(streamDecoder2, DEFAULT_FILE_OPENER);
    }

    FileToStreamDecoder(ResourceDecoder<InputStream, T> streamDecoder2, FileOpener fileOpener2) {
        this.streamDecoder = streamDecoder2;
        this.fileOpener = fileOpener2;
    }

    /* JADX INFO: finally extract failed */
    public Resource<T> decode(File source, int i, int i2) throws IOException {
        int width = i;
        int height = i2;
        InputStream is = null;
        try {
            is = this.fileOpener.open(source);
            Resource<T> result = this.streamDecoder.decode(is, width, height);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    IOException iOException = e;
                }
            }
            return result;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                }
            }
            throw th2;
        }
    }

    public String getId() {
        return "";
    }

    static class FileOpener {
        FileOpener() {
        }

        public InputStream open(File file) throws FileNotFoundException {
            InputStream inputStream;
            new FileInputStream(file);
            return inputStream;
        }
    }
}
