package org.shaded.apache.http.impl.entity;

import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.entity.BasicHttpEntity;
import org.shaded.apache.http.entity.ContentLengthStrategy;
import org.shaded.apache.http.impl.p006io.ChunkedInputStream;
import org.shaded.apache.http.impl.p006io.ContentLengthInputStream;
import org.shaded.apache.http.impl.p006io.IdentityInputStream;
import org.shaded.apache.http.p007io.SessionInputBuffer;
import org.shaded.apache.http.protocol.HTTP;

public class EntityDeserializer {
    private final ContentLengthStrategy lenStrategy;

    public EntityDeserializer(ContentLengthStrategy contentLengthStrategy) {
        Throwable th;
        ContentLengthStrategy lenStrategy2 = contentLengthStrategy;
        if (lenStrategy2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Content length strategy may not be null");
            throw th2;
        }
        this.lenStrategy = lenStrategy2;
    }

    /* access modifiers changed from: protected */
    public BasicHttpEntity doDeserialize(SessionInputBuffer sessionInputBuffer, HttpMessage httpMessage) throws HttpException, IOException {
        BasicHttpEntity basicHttpEntity;
        InputStream inputStream;
        InputStream inputStream2;
        InputStream inputStream3;
        SessionInputBuffer inbuffer = sessionInputBuffer;
        HttpMessage message = httpMessage;
        new BasicHttpEntity();
        BasicHttpEntity entity = basicHttpEntity;
        long len = this.lenStrategy.determineLength(message);
        if (len == -2) {
            entity.setChunked(true);
            entity.setContentLength(-1);
            new ChunkedInputStream(inbuffer);
            entity.setContent(inputStream3);
        } else if (len == -1) {
            entity.setChunked(false);
            entity.setContentLength(-1);
            new IdentityInputStream(inbuffer);
            entity.setContent(inputStream2);
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            new ContentLengthInputStream(inbuffer, len);
            entity.setContent(inputStream);
        }
        Header contentTypeHeader = message.getFirstHeader(HTTP.CONTENT_TYPE);
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        Header contentEncodingHeader = message.getFirstHeader(HTTP.CONTENT_ENCODING);
        if (contentEncodingHeader != null) {
            entity.setContentEncoding(contentEncodingHeader);
        }
        return entity;
    }

    public HttpEntity deserialize(SessionInputBuffer sessionInputBuffer, HttpMessage httpMessage) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        SessionInputBuffer inbuffer = sessionInputBuffer;
        HttpMessage message = httpMessage;
        if (inbuffer == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Session input buffer may not be null");
            throw th3;
        } else if (message != null) {
            return doDeserialize(inbuffer, message);
        } else {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP message may not be null");
            throw th4;
        }
    }
}
