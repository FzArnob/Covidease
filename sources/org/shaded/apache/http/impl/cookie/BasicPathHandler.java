package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class BasicPathHandler implements CookieAttributeHandler {
    public BasicPathHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Throwable th;
        SetCookie cookie = setCookie;
        String value = str;
        if (cookie == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Cookie may not be null");
            throw th2;
        }
        if (value == null || value.trim().length() == 0) {
            value = "/";
        }
        cookie.setPath(value);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        StringBuilder sb;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (!match(cookie2, origin)) {
            Throwable th2 = th;
            new StringBuilder();
            new MalformedCookieException(sb.append("Illegal path attribute \"").append(cookie2.getPath()).append("\". Path of origin: \"").append(origin.getPath()).append("\"").toString());
            throw th2;
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
            String targetpath = origin.getPath();
            String topmostPath = cookie2.getPath();
            if (topmostPath == null) {
                topmostPath = "/";
            }
            if (topmostPath.length() > 1 && topmostPath.endsWith("/")) {
                topmostPath = topmostPath.substring(0, topmostPath.length() - 1);
            }
            boolean match = targetpath.startsWith(topmostPath);
            if (match && targetpath.length() != topmostPath.length() && !topmostPath.endsWith("/")) {
                match = targetpath.charAt(topmostPath.length()) == '/';
            }
            return match;
        }
    }
}
