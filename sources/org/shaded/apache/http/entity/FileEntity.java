package org.shaded.apache.http.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileEntity extends AbstractHttpEntity implements Cloneable {
    protected final File file;

    public FileEntity(File file2, String str) {
        Throwable th;
        File file3 = file2;
        String contentType = str;
        if (file3 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("File may not be null");
            throw th2;
        }
        this.file = file3;
        setContentType(contentType);
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return this.file.length();
    }

    public InputStream getContent() throws IOException {
        InputStream inputStream;
        new FileInputStream(this.file);
        return inputStream;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        InputStream inputStream;
        Throwable th;
        OutputStream outstream = outputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output stream may not be null");
            throw th2;
        }
        new FileInputStream(this.file);
        InputStream instream = inputStream;
        try {
            byte[] tmp = new byte[4096];
            while (true) {
                int read = instream.read(tmp);
                int l = read;
                if (read != -1) {
                    outstream.write(tmp, 0, l);
                } else {
                    outstream.flush();
                    instream.close();
                    return;
                }
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            instream.close();
            throw th4;
        }
    }

    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
