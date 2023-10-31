package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;

@Immutable
public abstract class AbstractCookieAttributeHandler implements CookieAttributeHandler {
    public AbstractCookieAttributeHandler() {
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Cookie cookie2 = cookie;
        CookieOrigin cookieOrigin2 = cookieOrigin;
        return true;
    }
}
