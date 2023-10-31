package org.shaded.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.shaded.apache.http.FormattedHeader;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.params.CookiePolicy;
import org.shaded.apache.http.cookie.C1496SM;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.message.BufferedHeader;
import org.shaded.apache.http.message.ParserCursor;
import org.shaded.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class NetscapeDraftSpec extends CookieSpecBase {
    protected static final String EXPIRES_PATTERN = "EEE, dd-MMM-yyyy HH:mm:ss z";
    private final String[] datepatterns;

    public NetscapeDraftSpec(String[] strArr) {
        CookieAttributeHandler cookieAttributeHandler;
        CookieAttributeHandler cookieAttributeHandler2;
        CookieAttributeHandler cookieAttributeHandler3;
        CookieAttributeHandler cookieAttributeHandler4;
        CookieAttributeHandler cookieAttributeHandler5;
        CookieAttributeHandler cookieAttributeHandler6;
        String[] datepatterns2 = strArr;
        if (datepatterns2 != null) {
            this.datepatterns = (String[]) datepatterns2.clone();
        } else {
            String[] strArr2 = new String[1];
            strArr2[0] = EXPIRES_PATTERN;
            this.datepatterns = strArr2;
        }
        new BasicPathHandler();
        registerAttribHandler(ClientCookie.PATH_ATTR, cookieAttributeHandler);
        new NetscapeDomainHandler();
        registerAttribHandler(ClientCookie.DOMAIN_ATTR, cookieAttributeHandler2);
        new BasicMaxAgeHandler();
        registerAttribHandler(ClientCookie.MAX_AGE_ATTR, cookieAttributeHandler3);
        new BasicSecureHandler();
        registerAttribHandler(ClientCookie.SECURE_ATTR, cookieAttributeHandler4);
        new BasicCommentHandler();
        registerAttribHandler(ClientCookie.COMMENT_ATTR, cookieAttributeHandler5);
        new BasicExpiresHandler(this.datepatterns);
        registerAttribHandler(ClientCookie.EXPIRES_ATTR, cookieAttributeHandler6);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NetscapeDraftSpec() {
        this((String[]) null);
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException {
        CharArrayBuffer charArrayBuffer;
        CharArrayBuffer buffer;
        ParserCursor parserCursor;
        ParserCursor cursor;
        Throwable th;
        ParserCursor parserCursor2;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        Throwable th4;
        Header header2 = header;
        CookieOrigin origin = cookieOrigin;
        if (header2 == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Header may not be null");
            throw th5;
        } else if (origin == null) {
            Throwable th6 = th3;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th6;
        } else if (!header2.getName().equalsIgnoreCase(C1496SM.SET_COOKIE)) {
            Throwable th7 = th2;
            new StringBuilder();
            new MalformedCookieException(sb.append("Unrecognized cookie header '").append(header2.toString()).append("'").toString());
            throw th7;
        } else {
            NetscapeDraftHeaderParser parser = NetscapeDraftHeaderParser.DEFAULT;
            if (header2 instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header2).getBuffer();
                new ParserCursor(((FormattedHeader) header2).getValuePos(), buffer.length());
                cursor = parserCursor2;
            } else {
                String s = header2.getValue();
                if (s == null) {
                    Throwable th8 = th;
                    new MalformedCookieException("Header value is null");
                    throw th8;
                }
                new CharArrayBuffer(s.length());
                buffer = charArrayBuffer;
                buffer.append(s);
                new ParserCursor(0, buffer.length());
                cursor = parserCursor;
            }
            return parse(new HeaderElement[]{parser.parseHeader(buffer, cursor)}, origin);
        }
    }

    public List<Header> formatCookies(List<Cookie> list) {
        CharArrayBuffer charArrayBuffer;
        List<Header> list2;
        Object obj;
        Throwable th;
        Throwable th2;
        List<Cookie> cookies = list;
        if (cookies == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("List of cookies may not be null");
            throw th3;
        } else if (cookies.isEmpty()) {
            Throwable th4 = th;
            new IllegalArgumentException("List of cookies may not be empty");
            throw th4;
        } else {
            new CharArrayBuffer(20 * cookies.size());
            CharArrayBuffer buffer = charArrayBuffer;
            buffer.append(C1496SM.COOKIE);
            buffer.append(": ");
            for (int i = 0; i < cookies.size(); i++) {
                Cookie cookie = cookies.get(i);
                if (i > 0) {
                    buffer.append("; ");
                }
                buffer.append(cookie.getName());
                String s = cookie.getValue();
                if (s != null) {
                    buffer.append("=");
                    buffer.append(s);
                }
            }
            new ArrayList(1);
            List<Header> headers = list2;
            new BufferedHeader(buffer);
            boolean add = headers.add(obj);
            return headers;
        }
    }

    public int getVersion() {
        return 0;
    }

    public Header getVersionHeader() {
        return null;
    }

    public String toString() {
        return CookiePolicy.NETSCAPE;
    }
}
