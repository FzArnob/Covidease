package org.shaded.apache.http.impl.cookie;

import java.util.Locale;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class RFC2109DomainHandler implements CookieAttributeHandler {
    public RFC2109DomainHandler() {
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
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (cookie2 == null) {
            Throwable th9 = th8;
            new IllegalArgumentException("Cookie may not be null");
            throw th9;
        } else if (origin == null) {
            Throwable th10 = th7;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th10;
        } else {
            String host = origin.getHost();
            String domain = cookie2.getDomain();
            if (domain == null) {
                Throwable th11 = th6;
                new MalformedCookieException("Cookie domain may not be null");
                throw th11;
            } else if (domain.equals(host)) {
            } else {
                if (domain.indexOf(46) == -1) {
                    Throwable th12 = th5;
                    new StringBuilder();
                    new MalformedCookieException(sb5.append("Domain attribute \"").append(domain).append("\" does not match the host \"").append(host).append("\"").toString());
                    throw th12;
                } else if (!domain.startsWith(".")) {
                    Throwable th13 = th4;
                    new StringBuilder();
                    new MalformedCookieException(sb4.append("Domain attribute \"").append(domain).append("\" violates RFC 2109: domain must start with a dot").toString());
                    throw th13;
                } else {
                    int dotIndex = domain.indexOf(46, 1);
                    if (dotIndex < 0 || dotIndex == domain.length() - 1) {
                        Throwable th14 = th;
                        new StringBuilder();
                        new MalformedCookieException(sb.append("Domain attribute \"").append(domain).append("\" violates RFC 2109: domain must contain an embedded dot").toString());
                        throw th14;
                    }
                    String host2 = host.toLowerCase(Locale.ENGLISH);
                    if (!host2.endsWith(domain)) {
                        Throwable th15 = th3;
                        new StringBuilder();
                        new MalformedCookieException(sb3.append("Illegal domain attribute \"").append(domain).append("\". Domain of origin: \"").append(host2).append("\"").toString());
                        throw th15;
                    } else if (host2.substring(0, host2.length() - domain.length()).indexOf(46) != -1) {
                        Throwable th16 = th2;
                        new StringBuilder();
                        new MalformedCookieException(sb2.append("Domain attribute \"").append(domain).append("\" violates RFC 2109: host minus domain may not contain any dots").toString());
                        throw th16;
                    }
                }
            }
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
            String host = origin.getHost();
            String domain = cookie2.getDomain();
            if (domain == null) {
                return false;
            }
            return host.equals(domain) || (domain.startsWith(".") && host.endsWith(domain));
        }
    }
}
