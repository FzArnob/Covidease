package org.shaded.apache.http.impl.cookie;

import java.util.Locale;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class RFC2965DomainAttributeHandler implements CookieAttributeHandler {
    public RFC2965DomainAttributeHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        Throwable th3;
        SetCookie cookie = setCookie;
        String domain = str;
        if (cookie == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Cookie may not be null");
            throw th4;
        } else if (domain == null) {
            Throwable th5 = th2;
            new MalformedCookieException("Missing value for domain attribute");
            throw th5;
        } else if (domain.trim().length() == 0) {
            Throwable th6 = th;
            new MalformedCookieException("Blank value for domain attribute");
            throw th6;
        } else {
            String domain2 = domain.toLowerCase(Locale.ENGLISH);
            if (!domain2.startsWith(".")) {
                new StringBuilder();
                domain2 = sb.append('.').append(domain2).toString();
            }
            cookie.setDomain(domain2);
        }
    }

    public boolean domainMatch(String str, String str2) {
        String host = str;
        String domain = str2;
        return host.equals(domain) || (domain.startsWith(".") && host.endsWith(domain));
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
            String host = origin.getHost().toLowerCase(Locale.ENGLISH);
            if (cookie2.getDomain() == null) {
                Throwable th11 = th6;
                new MalformedCookieException("Invalid cookie state: domain not specified");
                throw th11;
            }
            String cookieDomain = cookie2.getDomain().toLowerCase(Locale.ENGLISH);
            if (!(cookie2 instanceof ClientCookie) || !((ClientCookie) cookie2).containsAttribute(ClientCookie.DOMAIN_ATTR)) {
                if (!cookie2.getDomain().equals(host)) {
                    Throwable th12 = th;
                    new StringBuilder();
                    new MalformedCookieException(sb.append("Illegal domain attribute: \"").append(cookie2.getDomain()).append("\".").append("Domain of origin: \"").append(host).append("\"").toString());
                    throw th12;
                }
            } else if (!cookieDomain.startsWith(".")) {
                Throwable th13 = th5;
                new StringBuilder();
                new MalformedCookieException(sb5.append("Domain attribute \"").append(cookie2.getDomain()).append("\" violates RFC 2109: domain must start with a dot").toString());
                throw th13;
            } else {
                int dotIndex = cookieDomain.indexOf(46, 1);
                if ((dotIndex < 0 || dotIndex == cookieDomain.length() - 1) && !cookieDomain.equals(".local")) {
                    Throwable th14 = th4;
                    new StringBuilder();
                    new MalformedCookieException(sb4.append("Domain attribute \"").append(cookie2.getDomain()).append("\" violates RFC 2965: the value contains no embedded dots ").append("and the value is not .local").toString());
                    throw th14;
                } else if (!domainMatch(host, cookieDomain)) {
                    Throwable th15 = th3;
                    new StringBuilder();
                    new MalformedCookieException(sb3.append("Domain attribute \"").append(cookie2.getDomain()).append("\" violates RFC 2965: effective host name does not ").append("domain-match domain attribute.").toString());
                    throw th15;
                } else if (host.substring(0, host.length() - cookieDomain.length()).indexOf(46) != -1) {
                    Throwable th16 = th2;
                    new StringBuilder();
                    new MalformedCookieException(sb2.append("Domain attribute \"").append(cookie2.getDomain()).append("\" violates RFC 2965: ").append("effective host minus domain may not contain any dots").toString());
                    throw th16;
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
            String host = origin.getHost().toLowerCase(Locale.ENGLISH);
            String cookieDomain = cookie2.getDomain();
            if (!domainMatch(host, cookieDomain)) {
                return false;
            }
            return host.substring(0, host.length() - cookieDomain.length()).indexOf(46) == -1;
        }
    }
}
