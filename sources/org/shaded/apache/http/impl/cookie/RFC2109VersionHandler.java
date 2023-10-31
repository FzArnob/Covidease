package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class RFC2109VersionHandler extends AbstractCookieAttributeHandler {
    public RFC2109VersionHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        SetCookie cookie = setCookie;
        String value = str;
        if (cookie == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Cookie may not be null");
            throw th5;
        } else if (value == null) {
            Throwable th6 = th3;
            new MalformedCookieException("Missing value for version attribute");
            throw th6;
        } else if (value.trim().length() == 0) {
            Throwable th7 = th2;
            new MalformedCookieException("Blank value for version attribute");
            throw th7;
        } else {
            try {
                cookie.setVersion(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                NumberFormatException e2 = e;
                Throwable th8 = th;
                new StringBuilder();
                new MalformedCookieException(sb.append("Invalid version: ").append(e2.getMessage()).toString());
                throw th8;
            }
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        Throwable th2;
        Cookie cookie2 = cookie;
        CookieOrigin cookieOrigin2 = cookieOrigin;
        if (cookie2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Cookie may not be null");
            throw th3;
        } else if (cookie2.getVersion() < 0) {
            Throwable th4 = th;
            new MalformedCookieException("Cookie version may not be negative");
            throw th4;
        }
    }
}
