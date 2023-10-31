package org.shaded.apache.http.impl.entity;

import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.entity.ContentLengthStrategy;
import org.shaded.apache.http.protocol.HTTP;

public class StrictContentLengthStrategy implements ContentLengthStrategy {
    public StrictContentLengthStrategy() {
    }

    public long determineLength(HttpMessage httpMessage) throws HttpException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        Throwable th3;
        StringBuffer stringBuffer3;
        Throwable th4;
        HttpMessage message = httpMessage;
        if (message == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("HTTP message may not be null");
            throw th5;
        }
        Header transferEncodingHeader = message.getFirstHeader(HTTP.TRANSFER_ENCODING);
        Header contentLengthHeader = message.getFirstHeader(HTTP.CONTENT_LEN);
        if (transferEncodingHeader != null) {
            String s = transferEncodingHeader.getValue();
            if (HTTP.CHUNK_CODING.equalsIgnoreCase(s)) {
                if (!message.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
                    return -2;
                }
                Throwable th6 = th3;
                new StringBuffer();
                new ProtocolException(stringBuffer3.append("Chunked transfer encoding not allowed for ").append(message.getProtocolVersion()).toString());
                throw th6;
            } else if (HTTP.IDENTITY_CODING.equalsIgnoreCase(s)) {
                return -1;
            } else {
                Throwable th7 = th2;
                new StringBuffer();
                new ProtocolException(stringBuffer2.append("Unsupported transfer encoding: ").append(s).toString());
                throw th7;
            }
        } else if (contentLengthHeader == null) {
            return -1;
        } else {
            String s2 = contentLengthHeader.getValue();
            try {
                return Long.parseLong(s2);
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
                Throwable th8 = th;
                new StringBuffer();
                new ProtocolException(stringBuffer.append("Invalid content length: ").append(s2).toString());
                throw th8;
            }
        }
    }
}
