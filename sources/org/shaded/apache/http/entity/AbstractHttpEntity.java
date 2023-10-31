package org.shaded.apache.http.entity;

import java.io.IOException;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.message.BasicHeader;
import org.shaded.apache.http.protocol.HTTP;

public abstract class AbstractHttpEntity implements HttpEntity {
    protected boolean chunked;
    protected Header contentEncoding;
    protected Header contentType;

    protected AbstractHttpEntity() {
    }

    public Header getContentType() {
        return this.contentType;
    }

    public Header getContentEncoding() {
        return this.contentEncoding;
    }

    public boolean isChunked() {
        return this.chunked;
    }

    public void setContentType(Header contentType2) {
        Header header = contentType2;
        this.contentType = header;
    }

    public void setContentType(String str) {
        Header header;
        String ctString = str;
        Header h = null;
        if (ctString != null) {
            new BasicHeader(HTTP.CONTENT_TYPE, ctString);
            h = header;
        }
        setContentType(h);
    }

    public void setContentEncoding(Header contentEncoding2) {
        Header header = contentEncoding2;
        this.contentEncoding = header;
    }

    public void setContentEncoding(String str) {
        Header header;
        String ceString = str;
        Header h = null;
        if (ceString != null) {
            new BasicHeader(HTTP.CONTENT_ENCODING, ceString);
            h = header;
        }
        setContentEncoding(h);
    }

    public void setChunked(boolean b) {
        boolean z = b;
        this.chunked = z;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        Throwable th;
        if (isStreaming()) {
            Throwable th2 = th;
            new UnsupportedOperationException("streaming entity does not implement consumeContent()");
            throw th2;
        }
    }
}
