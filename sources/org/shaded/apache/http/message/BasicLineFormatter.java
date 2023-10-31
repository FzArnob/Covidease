package org.shaded.apache.http.message;

import org.shaded.apache.http.FormattedHeader;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.RequestLine;
import org.shaded.apache.http.StatusLine;
import org.shaded.apache.http.util.CharArrayBuffer;

public class BasicLineFormatter implements LineFormatter {
    public static final BasicLineFormatter DEFAULT;

    public BasicLineFormatter() {
    }

    static {
        BasicLineFormatter basicLineFormatter;
        new BasicLineFormatter();
        DEFAULT = basicLineFormatter;
    }

    /* access modifiers changed from: protected */
    public CharArrayBuffer initBuffer(CharArrayBuffer charArrayBuffer) {
        CharArrayBuffer charArrayBuffer2;
        CharArrayBuffer buffer = charArrayBuffer;
        if (buffer != null) {
            buffer.clear();
        } else {
            new CharArrayBuffer(64);
            buffer = charArrayBuffer2;
        }
        return buffer;
    }

    public static final String formatProtocolVersion(ProtocolVersion protocolVersion, LineFormatter lineFormatter) {
        ProtocolVersion version = protocolVersion;
        LineFormatter formatter = lineFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.appendProtocolVersion((CharArrayBuffer) null, version).toString();
    }

    public CharArrayBuffer appendProtocolVersion(CharArrayBuffer charArrayBuffer, ProtocolVersion protocolVersion) {
        CharArrayBuffer charArrayBuffer2;
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        ProtocolVersion version = protocolVersion;
        if (version == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Protocol version may not be null");
            throw th2;
        }
        CharArrayBuffer result = buffer;
        int len = estimateProtocolVersionLen(version);
        if (result == null) {
            new CharArrayBuffer(len);
            result = charArrayBuffer2;
        } else {
            result.ensureCapacity(len);
        }
        result.append(version.getProtocol());
        result.append('/');
        result.append(Integer.toString(version.getMajor()));
        result.append('.');
        result.append(Integer.toString(version.getMinor()));
        return result;
    }

    /* access modifiers changed from: protected */
    public int estimateProtocolVersionLen(ProtocolVersion version) {
        return version.getProtocol().length() + 4;
    }

    public static final String formatRequestLine(RequestLine requestLine, LineFormatter lineFormatter) {
        RequestLine reqline = requestLine;
        LineFormatter formatter = lineFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatRequestLine((CharArrayBuffer) null, reqline).toString();
    }

    public CharArrayBuffer formatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        RequestLine reqline = requestLine;
        if (reqline == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Request line may not be null");
            throw th2;
        }
        CharArrayBuffer result = initBuffer(buffer);
        doFormatRequestLine(result, reqline);
        return result;
    }

    /* access modifiers changed from: protected */
    public void doFormatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        CharArrayBuffer buffer = charArrayBuffer;
        RequestLine reqline = requestLine;
        String method = reqline.getMethod();
        String uri = reqline.getUri();
        buffer.ensureCapacity(method.length() + 1 + uri.length() + 1 + estimateProtocolVersionLen(reqline.getProtocolVersion()));
        buffer.append(method);
        buffer.append(' ');
        buffer.append(uri);
        buffer.append(' ');
        CharArrayBuffer appendProtocolVersion = appendProtocolVersion(buffer, reqline.getProtocolVersion());
    }

    public static final String formatStatusLine(StatusLine statusLine, LineFormatter lineFormatter) {
        StatusLine statline = statusLine;
        LineFormatter formatter = lineFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatStatusLine((CharArrayBuffer) null, statline).toString();
    }

    public CharArrayBuffer formatStatusLine(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        StatusLine statline = statusLine;
        if (statline == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Status line may not be null");
            throw th2;
        }
        CharArrayBuffer result = initBuffer(buffer);
        doFormatStatusLine(result, statline);
        return result;
    }

    /* access modifiers changed from: protected */
    public void doFormatStatusLine(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        CharArrayBuffer buffer = charArrayBuffer;
        StatusLine statline = statusLine;
        int len = estimateProtocolVersionLen(statline.getProtocolVersion()) + 1 + 3 + 1;
        String reason = statline.getReasonPhrase();
        if (reason != null) {
            len += reason.length();
        }
        buffer.ensureCapacity(len);
        CharArrayBuffer appendProtocolVersion = appendProtocolVersion(buffer, statline.getProtocolVersion());
        buffer.append(' ');
        buffer.append(Integer.toString(statline.getStatusCode()));
        buffer.append(' ');
        if (reason != null) {
            buffer.append(reason);
        }
    }

    public static final String formatHeader(Header header, LineFormatter lineFormatter) {
        Header header2 = header;
        LineFormatter formatter = lineFormatter;
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatHeader((CharArrayBuffer) null, header2).toString();
    }

    public CharArrayBuffer formatHeader(CharArrayBuffer charArrayBuffer, Header header) {
        CharArrayBuffer result;
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        Header header2 = header;
        if (header2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header may not be null");
            throw th2;
        }
        if (header2 instanceof FormattedHeader) {
            result = ((FormattedHeader) header2).getBuffer();
        } else {
            result = initBuffer(buffer);
            doFormatHeader(result, header2);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public void doFormatHeader(CharArrayBuffer charArrayBuffer, Header header) {
        CharArrayBuffer buffer = charArrayBuffer;
        Header header2 = header;
        String name = header2.getName();
        String value = header2.getValue();
        int len = name.length() + 2;
        if (value != null) {
            len += value.length();
        }
        buffer.ensureCapacity(len);
        buffer.append(name);
        buffer.append(": ");
        if (value != null) {
            buffer.append(value);
        }
    }
}
