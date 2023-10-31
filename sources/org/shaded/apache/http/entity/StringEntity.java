package org.shaded.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class StringEntity extends AbstractHttpEntity implements Cloneable {
    protected final byte[] content;

    public StringEntity(String str, String str2) throws UnsupportedEncodingException {
        StringBuffer stringBuffer;
        Throwable th;
        String s = str;
        String charset = str2;
        if (s == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Source string may not be null");
            throw th2;
        }
        charset = charset == null ? "ISO-8859-1" : charset;
        this.content = s.getBytes(charset);
        new StringBuffer();
        setContentType(stringBuffer.append("text/plain; charset=").append(charset).toString());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public StringEntity(String s) throws UnsupportedEncodingException {
        this(s, (String) null);
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public InputStream getContent() throws IOException {
        InputStream inputStream;
        new ByteArrayInputStream(this.content);
        return inputStream;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Throwable th;
        OutputStream outstream = outputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output stream may not be null");
            throw th2;
        }
        outstream.write(this.content);
        outstream.flush();
    }

    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
