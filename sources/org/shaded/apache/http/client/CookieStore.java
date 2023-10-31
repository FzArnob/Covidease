package org.shaded.apache.http.client;

import java.util.Date;
import java.util.List;
import org.shaded.apache.http.cookie.Cookie;

public interface CookieStore {
    void addCookie(Cookie cookie);

    void clear();

    boolean clearExpired(Date date);

    List<Cookie> getCookies();
}
