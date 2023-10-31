package org.shaded.apache.http.impl.cookie;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.shaded.apache.http.client.utils.Punycode;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

public class PublicSuffixFilter implements CookieAttributeHandler {
    private Set<String> exceptions;
    private Set<String> suffixes;
    private final CookieAttributeHandler wrapped;

    public PublicSuffixFilter(CookieAttributeHandler wrapped2) {
        this.wrapped = wrapped2;
    }

    public void setPublicSuffixes(Collection<String> suffixes2) {
        Set<String> set;
        Set<String> set2 = set;
        new HashSet(suffixes2);
        this.suffixes = set2;
    }

    public void setExceptions(Collection<String> exceptions2) {
        Set<String> set;
        Set<String> set2 = set;
        new HashSet(exceptions2);
        this.exceptions = set2;
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (isForPublicSuffix(cookie2)) {
            return false;
        }
        return this.wrapped.match(cookie2, origin);
    }

    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        this.wrapped.parse(cookie, value);
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        this.wrapped.validate(cookie, origin);
    }

    private boolean isForPublicSuffix(Cookie cookie) {
        StringBuilder sb;
        String domain = cookie.getDomain();
        if (domain.startsWith(".")) {
            domain = domain.substring(1);
        }
        String domain2 = Punycode.toUnicode(domain);
        if (this.exceptions != null && this.exceptions.contains(domain2)) {
            return false;
        }
        if (this.suffixes == null) {
            return false;
        }
        while (!this.suffixes.contains(domain2)) {
            if (domain2.startsWith("*.")) {
                domain2 = domain2.substring(2);
            }
            int nextdot = domain2.indexOf(46);
            if (nextdot != -1) {
                new StringBuilder();
                domain2 = sb.append("*").append(domain2.substring(nextdot)).toString();
                if (domain2.length() <= 0) {
                }
            }
            return false;
        }
        return true;
    }
}
