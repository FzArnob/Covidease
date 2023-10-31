package org.shaded.apache.http.impl.entity;

import java.io.IOException;
import java.io.OutputStream;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.entity.ContentLengthStrategy;
import org.shaded.apache.http.impl.p006io.ChunkedOutputStream;
import org.shaded.apache.http.impl.p006io.ContentLengthOutputStream;
import org.shaded.apache.http.impl.p006io.IdentityOutputStream;
import org.shaded.apache.http.p007io.SessionOutputBuffer;

public class EntitySerializer {
    private final ContentLengthStrategy lenStrategy;

    public EntitySerializer(ContentLengthStrategy contentLengthStrategy) {
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
    public OutputStream doSerialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage message) throws HttpException, IOException {
        OutputStream outputStream;
        OutputStream outputStream2;
        OutputStream outputStream3;
        SessionOutputBuffer outbuffer = sessionOutputBuffer;
        long len = this.lenStrategy.determineLength(message);
        if (len == -2) {
            new ChunkedOutputStream(outbuffer);
            return outputStream3;
        } else if (len == -1) {
            new IdentityOutputStream(outbuffer);
            return outputStream2;
        } else {
            new ContentLengthOutputStream(outbuffer, len);
            return outputStream;
        }
    }

    public void serialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage, HttpEntity httpEntity) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        SessionOutputBuffer outbuffer = sessionOutputBuffer;
        HttpMessage message = httpMessage;
        HttpEntity entity = httpEntity;
        if (outbuffer == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Session output buffer may not be null");
            throw th4;
        } else if (message == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("HTTP message may not be null");
            throw th5;
        } else if (entity == null) {
            Throwable th6 = th;
            new IllegalArgumentException("HTTP entity may not be null");
            throw th6;
        } else {
            OutputStream outstream = doSerialize(outbuffer, message);
            entity.writeTo(outstream);
            outstream.close();
        }
    }
}
