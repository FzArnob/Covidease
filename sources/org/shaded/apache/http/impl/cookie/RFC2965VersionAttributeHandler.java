package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;
import org.shaded.apache.http.cookie.SetCookie2;

@Immutable
public class RFC2965VersionAttributeHandler implements CookieAttributeHandler {
    public RFC2965VersionAttributeHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        int version;
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
            new MalformedCookieException("Missing value for version attribute");
            throw th5;
        } else {
            try {
                version = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
                version = -1;
            }
            if (version < 0) {
                Throwable th6 = th;
                new MalformedCookieException("Invalid cookie version.");
                throw th6;
            }
            cookie.setVersion(version);
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
        } else if ((cookie2 instanceof SetCookie2) && (cookie2 instanceof ClientCookie) && !((ClientCookie) cookie2).containsAttribute(ClientCookie.VERSION_ATTR)) {
            Throwable th4 = th;
            new MalformedCookieException("Violates RFC 2965. Version attribute is required.");
            throw th4;
        }
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Cookie cookie2 = cookie;
        CookieOrigin cookieOrigin2 = cookieOrigin;
        return true;
    }
}
