package org.shaded.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.params.CookiePolicy;
import org.shaded.apache.http.cookie.C1496SM;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.CookiePathComparator;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.message.BufferedHeader;
import org.shaded.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class RFC2109Spec extends CookieSpecBase {
    private static final String[] DATE_PATTERNS;
    private static final CookiePathComparator PATH_COMPARATOR;
    private final String[] datepatterns;
    private final boolean oneHeader;

    static {
        CookiePathComparator cookiePathComparator;
        new CookiePathComparator();
        PATH_COMPARATOR = cookiePathComparator;
        String[] strArr = new String[3];
        strArr[0] = "EEE, dd MMM yyyy HH:mm:ss zzz";
        String[] strArr2 = strArr;
        strArr2[1] = DateUtils.PATTERN_RFC1036;
        String[] strArr3 = strArr2;
        strArr3[2] = DateUtils.PATTERN_ASCTIME;
        DATE_PATTERNS = strArr3;
    }

    public RFC2109Spec(String[] strArr, boolean z) {
        CookieAttributeHandler cookieAttributeHandler;
        CookieAttributeHandler cookieAttributeHandler2;
        CookieAttributeHandler cookieAttributeHandler3;
        CookieAttributeHandler cookieAttributeHandler4;
        CookieAttributeHandler cookieAttributeHandler5;
        CookieAttributeHandler cookieAttributeHandler6;
        CookieAttributeHandler cookieAttributeHandler7;
        String[] datepatterns2 = strArr;
        boolean oneHeader2 = z;
        if (datepatterns2 != null) {
            this.datepatterns = (String[]) datepatterns2.clone();
        } else {
            this.datepatterns = DATE_PATTERNS;
        }
        this.oneHeader = oneHeader2;
        new RFC2109VersionHandler();
        registerAttribHandler(ClientCookie.VERSION_ATTR, cookieAttributeHandler);
        new BasicPathHandler();
        registerAttribHandler(ClientCookie.PATH_ATTR, cookieAttributeHandler2);
        new RFC2109DomainHandler();
        registerAttribHandler(ClientCookie.DOMAIN_ATTR, cookieAttributeHandler3);
        new BasicMaxAgeHandler();
        registerAttribHandler(ClientCookie.MAX_AGE_ATTR, cookieAttributeHandler4);
        new BasicSecureHandler();
        registerAttribHandler(ClientCookie.SECURE_ATTR, cookieAttributeHandler5);
        new BasicCommentHandler();
        registerAttribHandler(ClientCookie.COMMENT_ATTR, cookieAttributeHandler6);
        new BasicExpiresHandler(this.datepatterns);
        registerAttribHandler(ClientCookie.EXPIRES_ATTR, cookieAttributeHandler7);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RFC2109Spec() {
        this((String[]) null, false);
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        Header header2 = header;
        CookieOrigin origin = cookieOrigin;
        if (header2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Header may not be null");
            throw th4;
        } else if (origin == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th5;
        } else if (!header2.getName().equalsIgnoreCase(C1496SM.SET_COOKIE)) {
            Throwable th6 = th;
            new StringBuilder();
            new MalformedCookieException(sb.append("Unrecognized cookie header '").append(header2.toString()).append("'").toString());
            throw th6;
        } else {
            return parse(header2.getElements(), origin);
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (cookie2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Cookie may not be null");
            throw th4;
        }
        String name = cookie2.getName();
        if (name.indexOf(32) != -1) {
            Throwable th5 = th2;
            new MalformedCookieException("Cookie name may not contain blanks");
            throw th5;
        } else if (name.startsWith("$")) {
            Throwable th6 = th;
            new MalformedCookieException("Cookie name may not start with $");
            throw th6;
        } else {
            super.validate(cookie2, origin);
        }
    }

    public List<Header> formatCookies(List<Cookie> list) {
        List<Cookie> list2;
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
            if (cookies.size() > 1) {
                new ArrayList(cookies);
                cookies = list2;
                Collections.sort(cookies, PATH_COMPARATOR);
            }
            if (this.oneHeader) {
                return doFormatOneHeader(cookies);
            }
            return doFormatManyHeaders(cookies);
        }
    }

    private List<Header> doFormatOneHeader(List<Cookie> list) {
        CharArrayBuffer charArrayBuffer;
        List<Header> list2;
        Object obj;
        List<Cookie> cookies = list;
        int version = Integer.MAX_VALUE;
        for (Cookie cookie : cookies) {
            if (cookie.getVersion() < version) {
                version = cookie.getVersion();
            }
        }
        new CharArrayBuffer(40 * cookies.size());
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(C1496SM.COOKIE);
        buffer.append(": ");
        buffer.append("$Version=");
        buffer.append(Integer.toString(version));
        for (Cookie cooky : cookies) {
            buffer.append("; ");
            formatCookieAsVer(buffer, cooky, version);
        }
        new ArrayList(1);
        List<Header> headers = list2;
        new BufferedHeader(buffer);
        boolean add = headers.add(obj);
        return headers;
    }

    private List<Header> doFormatManyHeaders(List<Cookie> list) {
        List<Header> list2;
        CharArrayBuffer charArrayBuffer;
        Object obj;
        List<Cookie> cookies = list;
        new ArrayList(cookies.size());
        List<Header> headers = list2;
        for (Cookie cookie : cookies) {
            int version = cookie.getVersion();
            new CharArrayBuffer(40);
            CharArrayBuffer buffer = charArrayBuffer;
            buffer.append("Cookie: ");
            buffer.append("$Version=");
            buffer.append(Integer.toString(version));
            buffer.append("; ");
            formatCookieAsVer(buffer, cookie, version);
            new BufferedHeader(buffer);
            boolean add = headers.add(obj);
        }
        return headers;
    }

    /* access modifiers changed from: protected */
    public void formatParamAsVer(CharArrayBuffer charArrayBuffer, String name, String str, int i) {
        CharArrayBuffer buffer = charArrayBuffer;
        String value = str;
        int version = i;
        buffer.append(name);
        buffer.append("=");
        if (value == null) {
            return;
        }
        if (version > 0) {
            buffer.append('\"');
            buffer.append(value);
            buffer.append('\"');
            return;
        }
        buffer.append(value);
    }

    /* access modifiers changed from: protected */
    public void formatCookieAsVer(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        CharArrayBuffer buffer = charArrayBuffer;
        Cookie cookie2 = cookie;
        int version = i;
        formatParamAsVer(buffer, cookie2.getName(), cookie2.getValue(), version);
        if (cookie2.getPath() != null && (cookie2 instanceof ClientCookie) && ((ClientCookie) cookie2).containsAttribute(ClientCookie.PATH_ATTR)) {
            buffer.append("; ");
            formatParamAsVer(buffer, "$Path", cookie2.getPath(), version);
        }
        if (cookie2.getDomain() != null && (cookie2 instanceof ClientCookie) && ((ClientCookie) cookie2).containsAttribute(ClientCookie.DOMAIN_ATTR)) {
            buffer.append("; ");
            formatParamAsVer(buffer, "$Domain", cookie2.getDomain(), version);
        }
    }

    public int getVersion() {
        return 1;
    }

    public Header getVersionHeader() {
        return null;
    }

    public String toString() {
        return CookiePolicy.RFC_2109;
    }
}
