package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;
import org.shaded.apache.http.cookie.SetCookie2;

@Immutable
public class RFC2965DiscardAttributeHandler implements CookieAttributeHandler {
    public RFC2965DiscardAttributeHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        SetCookie cookie = setCookie;
        String str2 = str;
        if (cookie instanceof SetCookie2) {
            ((SetCookie2) cookie).setDiscard(true);
        }
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Cookie cookie2 = cookie;
        CookieOrigin cookieOrigin2 = cookieOrigin;
        return true;
    }
}
