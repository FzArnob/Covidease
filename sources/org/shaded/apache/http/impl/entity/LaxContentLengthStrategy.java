package org.shaded.apache.http.impl.entity;

import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.entity.ContentLengthStrategy;
import org.shaded.apache.http.params.CoreProtocolPNames;
import org.shaded.apache.http.protocol.HTTP;

public class LaxContentLengthStrategy implements ContentLengthStrategy {
    public LaxContentLengthStrategy() {
    }

    public long determineLength(HttpMessage httpMessage) throws HttpException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        Throwable th3;
        StringBuffer stringBuffer2;
        Throwable th4;
        Throwable th5;
        StringBuffer stringBuffer3;
        Throwable th6;
        HttpMessage message = httpMessage;
        if (message == null) {
            Throwable th7 = th6;
            new IllegalArgumentException("HTTP message may not be null");
            throw th7;
        }
        boolean strict = message.getParams().isParameterTrue(CoreProtocolPNames.STRICT_TRANSFER_ENCODING);
        Header transferEncodingHeader = message.getFirstHeader(HTTP.TRANSFER_ENCODING);
        Header contentLengthHeader = message.getFirstHeader(HTTP.CONTENT_LEN);
        if (transferEncodingHeader != null) {
            try {
                HeaderElement[] encodings = transferEncodingHeader.getElements();
                if (strict) {
                    int i = 0;
                    while (i < encodings.length) {
                        String encoding = encodings[i].getName();
                        if (encoding == null || encoding.length() <= 0 || encoding.equalsIgnoreCase(HTTP.CHUNK_CODING) || encoding.equalsIgnoreCase(HTTP.IDENTITY_CODING)) {
                            i++;
                        } else {
                            Throwable th8 = th5;
                            new StringBuffer();
                            new ProtocolException(stringBuffer3.append("Unsupported transfer encoding: ").append(encoding).toString());
                            throw th8;
                        }
                    }
                }
                int len = encodings.length;
                if (HTTP.IDENTITY_CODING.equalsIgnoreCase(transferEncodingHeader.getValue())) {
                    return -1;
                }
                if (len > 0 && HTTP.CHUNK_CODING.equalsIgnoreCase(encodings[len - 1].getName())) {
                    return -2;
                }
                if (!strict) {
                    return -1;
                }
                Throwable th9 = th4;
                new ProtocolException("Chunk-encoding must be the last one applied");
                throw th9;
            } catch (ParseException e) {
                ParseException px = e;
                Throwable th10 = th3;
                new StringBuffer();
                new ProtocolException(stringBuffer2.append("Invalid Transfer-Encoding header value: ").append(transferEncodingHeader).toString(), px);
                throw th10;
            }
        } else if (contentLengthHeader == null) {
            return -1;
        } else {
            long contentlen = -1;
            Header[] headers = message.getHeaders(HTTP.CONTENT_LEN);
            if (!strict || headers.length <= 1) {
                int i2 = headers.length - 1;
                while (true) {
                    if (i2 < 0) {
                        break;
                    }
                    Header header = headers[i2];
                    try {
                        contentlen = Long.parseLong(header.getValue());
                        break;
                    } catch (NumberFormatException e2) {
                        NumberFormatException numberFormatException = e2;
                        if (strict) {
                            Throwable th11 = th;
                            new StringBuffer();
                            new ProtocolException(stringBuffer.append("Invalid content length: ").append(header.getValue()).toString());
                            throw th11;
                        }
                        i2--;
                    }
                }
                if (contentlen >= 0) {
                    return contentlen;
                }
                return -1;
            }
            Throwable th12 = th2;
            new ProtocolException("Multiple content length headers");
            throw th12;
        }
    }
}
