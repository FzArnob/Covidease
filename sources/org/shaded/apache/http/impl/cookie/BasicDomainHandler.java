package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class BasicDomainHandler implements CookieAttributeHandler {
    public BasicDomainHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        SetCookie cookie = setCookie;
        String value = str;
        if (cookie == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Cookie may not be null");
            throw th4;
        } else if (value == null) {
            Throwable th5 = th2;
            new MalformedCookieException("Missing value for domain attribute");
            throw th5;
        } else if (value.trim().length() == 0) {
            Throwable th6 = th;
            new MalformedCookieException("Blank value for domain attribute");
            throw th6;
        } else {
            cookie.setDomain(value);
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (cookie2 == null) {
            Throwable th6 = th5;
            new IllegalArgumentException("Cookie may not be null");
            throw th6;
        } else if (origin == null) {
            Throwable th7 = th4;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th7;
        } else {
            String host = origin.getHost();
            String domain = cookie2.getDomain();
            if (domain == null) {
                Throwable th8 = th3;
                new MalformedCookieException("Cookie domain may not be null");
                throw th8;
            } else if (host.contains(".")) {
                if (!host.endsWith(domain)) {
                    if (domain.startsWith(".")) {
                        domain = domain.substring(1, domain.length());
                    }
                    if (!host.equals(domain)) {
                        Throwable th9 = th2;
                        new StringBuilder();
                        new MalformedCookieException(sb2.append("Illegal domain attribute \"").append(domain).append("\". Domain of origin: \"").append(host).append("\"").toString());
                        throw th9;
                    }
                }
            } else if (!host.equals(domain)) {
                Throwable th10 = th;
                new StringBuilder();
                new MalformedCookieException(sb.append("Illegal domain attribute \"").append(domain).append("\". Domain of origin: \"").append(host).append("\"").toString());
                throw th10;
            }
        }
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        StringBuilder sb;
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
            String host = origin.getHost();
            String domain = cookie2.getDomain();
            if (domain == null) {
                return false;
            }
            if (host.equals(domain)) {
                return true;
            }
            if (!domain.startsWith(".")) {
                new StringBuilder();
                domain = sb.append('.').append(domain).toString();
            }
            return host.endsWith(domain) || host.equals(domain.substring(1));
        }
    }
}
