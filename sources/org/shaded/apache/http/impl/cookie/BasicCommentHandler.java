package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class BasicCommentHandler extends AbstractCookieAttributeHandler {
    public BasicCommentHandler() {
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
        cookie.setComment(value);
    }
}
