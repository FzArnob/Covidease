package org.shaded.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.params.CookiePolicy;
import org.shaded.apache.http.cookie.C1496SM;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.message.BufferedHeader;
import org.shaded.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class RFC2965Spec extends RFC2109Spec {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RFC2965Spec() {
        this((String[]) null, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RFC2965Spec(String[] datepatterns, boolean oneHeader) {
        super(datepatterns, oneHeader);
        CookieAttributeHandler cookieAttributeHandler;
        CookieAttributeHandler cookieAttributeHandler2;
        CookieAttributeHandler cookieAttributeHandler3;
        CookieAttributeHandler cookieAttributeHandler4;
        CookieAttributeHandler cookieAttributeHandler5;
        new RFC2965DomainAttributeHandler();
        registerAttribHandler(ClientCookie.DOMAIN_ATTR, cookieAttributeHandler);
        new RFC2965PortAttributeHandler();
        registerAttribHandler(ClientCookie.PORT_ATTR, cookieAttributeHandler2);
        new RFC2965CommentUrlAttributeHandler();
        registerAttribHandler(ClientCookie.COMMENTURL_ATTR, cookieAttributeHandler3);
        new RFC2965DiscardAttributeHandler();
        registerAttribHandler(ClientCookie.DISCARD_ATTR, cookieAttributeHandler4);
        new RFC2965VersionAttributeHandler();
        registerAttribHandler(ClientCookie.VERSION_ATTR, cookieAttributeHandler5);
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
        } else if (!header2.getName().equalsIgnoreCase(C1496SM.SET_COOKIE2)) {
            Throwable th6 = th;
            new StringBuilder();
            new MalformedCookieException(sb.append("Unrecognized cookie header '").append(header2.toString()).append("'").toString());
            throw th6;
        } else {
            CookieOrigin origin2 = adjustEffectiveHost(origin);
            return createCookies(header2.getElements(), origin2);
        }
    }

    /* access modifiers changed from: protected */
    public List<Cookie> parse(HeaderElement[] elems, CookieOrigin origin) throws MalformedCookieException {
        return createCookies(elems, adjustEffectiveHost(origin));
    }

    private List<Cookie> createCookies(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) throws MalformedCookieException {
        List<Cookie> list;
        Throwable th;
        BasicClientCookie2 basicClientCookie2;
        Map<String, NameValuePair> map;
        HeaderElement[] elems = headerElementArr;
        CookieOrigin origin = cookieOrigin;
        new ArrayList(elems.length);
        List<Cookie> cookies = list;
        HeaderElement[] arr$ = elems;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            HeaderElement headerelement = arr$[i$];
            String name = headerelement.getName();
            String value = headerelement.getValue();
            if (name == null || name.length() == 0) {
                Throwable th2 = th;
                new MalformedCookieException("Cookie name may not be empty");
                throw th2;
            }
            new BasicClientCookie2(name, value);
            BasicClientCookie2 cookie = basicClientCookie2;
            cookie.setPath(getDefaultPath(origin));
            cookie.setDomain(getDefaultDomain(origin));
            cookie.setPorts(new int[]{origin.getPort()});
            NameValuePair[] attribs = headerelement.getParameters();
            new HashMap<>(attribs.length);
            Map<String, NameValuePair> attribmap = map;
            for (int j = attribs.length - 1; j >= 0; j--) {
                NameValuePair param = attribs[j];
                NameValuePair put = attribmap.put(param.getName().toLowerCase(Locale.ENGLISH), param);
            }
            for (Map.Entry<String, NameValuePair> value2 : attribmap.entrySet()) {
                NameValuePair attrib = (NameValuePair) value2.getValue();
                String s = attrib.getName().toLowerCase(Locale.ENGLISH);
                cookie.setAttribute(s, attrib.getValue());
                CookieAttributeHandler handler = findAttribHandler(s);
                if (handler != null) {
                    handler.parse(cookie, attrib.getValue());
                }
            }
            boolean add = cookies.add(cookie);
        }
        return cookies;
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        Throwable th2;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (cookie2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Cookie may not be null");
            throw th3;
        } else if (origin == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th4;
        } else {
            super.validate(cookie2, adjustEffectiveHost(origin));
        }
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Throwable th;
        Throwable th2;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (cookie2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Cookie may not be null");
            throw th3;
        } else if (origin == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th4;
        } else {
            return super.match(cookie2, adjustEffectiveHost(origin));
        }
    }

    /* access modifiers changed from: protected */
    public void formatCookieAsVer(CharArrayBuffer charArrayBuffer, Cookie cookie, int version) {
        String s;
        int[] ports;
        CharArrayBuffer buffer = charArrayBuffer;
        Cookie cookie2 = cookie;
        super.formatCookieAsVer(buffer, cookie2, version);
        if ((cookie2 instanceof ClientCookie) && (s = ((ClientCookie) cookie2).getAttribute(ClientCookie.PORT_ATTR)) != null) {
            buffer.append("; $Port");
            buffer.append("=\"");
            if (s.trim().length() > 0 && (ports = cookie2.getPorts()) != null) {
                int len = ports.length;
                for (int i = 0; i < len; i++) {
                    if (i > 0) {
                        buffer.append(",");
                    }
                    buffer.append(Integer.toString(ports[i]));
                }
            }
            buffer.append("\"");
        }
    }

    private static CookieOrigin adjustEffectiveHost(CookieOrigin cookieOrigin) {
        StringBuilder sb;
        CookieOrigin origin;
        CookieOrigin origin2 = cookieOrigin;
        String host = origin2.getHost();
        boolean isLocalHost = true;
        int i = 0;
        while (true) {
            if (i >= host.length()) {
                break;
            }
            char ch = host.charAt(i);
            if (ch == '.' || ch == ':') {
                isLocalHost = false;
            } else {
                i++;
            }
        }
        isLocalHost = false;
        if (!isLocalHost) {
            return origin2;
        }
        new StringBuilder();
        new CookieOrigin(sb.append(host).append(".local").toString(), origin2.getPort(), origin2.getPath(), origin2.isSecure());
        return origin;
    }

    public int getVersion() {
        return 1;
    }

    public Header getVersionHeader() {
        CharArrayBuffer charArrayBuffer;
        Header header;
        new CharArrayBuffer(40);
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(C1496SM.COOKIE2);
        buffer.append(": ");
        buffer.append("$Version=");
        buffer.append(Integer.toString(getVersion()));
        new BufferedHeader(buffer);
        return header;
    }

    public String toString() {
        return CookiePolicy.RFC_2965;
    }
}
