package org.shaded.apache.http.impl.cookie;

import java.util.Locale;
import java.util.StringTokenizer;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;

@Immutable
public class NetscapeDomainHandler extends BasicDomainHandler {
    public NetscapeDomainHandler() {
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        StringTokenizer stringTokenizer;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        super.validate(cookie2, origin);
        String host = origin.getHost();
        String domain = cookie2.getDomain();
        if (host.contains(".")) {
            new StringTokenizer(domain, ".");
            int domainParts = stringTokenizer.countTokens();
            if (isSpecialDomain(domain)) {
                if (domainParts < 2) {
                    Throwable th3 = th2;
                    new StringBuilder();
                    new MalformedCookieException(sb2.append("Domain attribute \"").append(domain).append("\" violates the Netscape cookie specification for ").append("special domains").toString());
                    throw th3;
                }
            } else if (domainParts < 3) {
                Throwable th4 = th;
                new StringBuilder();
                new MalformedCookieException(sb.append("Domain attribute \"").append(domain).append("\" violates the Netscape cookie specification").toString());
                throw th4;
            }
        }
    }

    private static boolean isSpecialDomain(String domain) {
        String ucDomain = domain.toUpperCase(Locale.ENGLISH);
        return ucDomain.endsWith(".COM") || ucDomain.endsWith(".EDU") || ucDomain.endsWith(".NET") || ucDomain.endsWith(".GOV") || ucDomain.endsWith(".MIL") || ucDomain.endsWith(".ORG") || ucDomain.endsWith(".INT");
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
            String host = origin.getHost();
            String domain = cookie2.getDomain();
            if (domain == null) {
                return false;
            }
            return host.endsWith(domain);
        }
    }
}
