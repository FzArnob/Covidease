package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;
import org.shaded.apache.http.cookie.SetCookie2;

@Immutable
public class RFC2965CommentUrlAttributeHandler implements CookieAttributeHandler {
    public RFC2965CommentUrlAttributeHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        SetCookie cookie = setCookie;
        String commenturl = str;
        if (cookie instanceof SetCookie2) {
            ((SetCookie2) cookie).setCommentURL(commenturl);
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
