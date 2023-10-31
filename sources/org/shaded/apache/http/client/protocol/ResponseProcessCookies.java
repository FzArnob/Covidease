package org.shaded.apache.http.client.protocol;

import java.io.IOException;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.client.CookieStore;
import org.shaded.apache.http.cookie.C1496SM;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.CookieSpec;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class ResponseProcessCookies implements HttpResponseInterceptor {
    private final Log log = LogFactory.getLog((Class) getClass());

    public ResponseProcessCookies() {
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        if (response == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP request may not be null");
            throw th3;
        } else if (context == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th4;
        } else {
            CookieSpec cookieSpec = (CookieSpec) context.getAttribute(ClientContext.COOKIE_SPEC);
            if (cookieSpec != null) {
                CookieStore cookieStore = (CookieStore) context.getAttribute(ClientContext.COOKIE_STORE);
                if (cookieStore == null) {
                    this.log.info("CookieStore not available in HTTP context");
                    return;
                }
                CookieOrigin cookieOrigin = (CookieOrigin) context.getAttribute(ClientContext.COOKIE_ORIGIN);
                if (cookieOrigin == null) {
                    this.log.info("CookieOrigin not available in HTTP context");
                    return;
                }
                processCookies(response.headerIterator(C1496SM.SET_COOKIE), cookieSpec, cookieOrigin, cookieStore);
                if (cookieSpec.getVersion() > 0) {
                    processCookies(response.headerIterator(C1496SM.SET_COOKIE2), cookieSpec, cookieOrigin, cookieStore);
                }
            }
        }
    }

    private void processCookies(HeaderIterator headerIterator, CookieSpec cookieSpec, CookieOrigin cookieOrigin, CookieStore cookieStore) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        HeaderIterator iterator = headerIterator;
        CookieSpec cookieSpec2 = cookieSpec;
        CookieOrigin cookieOrigin2 = cookieOrigin;
        CookieStore cookieStore2 = cookieStore;
        while (iterator.hasNext()) {
            Header header = iterator.nextHeader();
            try {
                for (Cookie cookie : cookieSpec2.parse(header, cookieOrigin2)) {
                    try {
                        cookieSpec2.validate(cookie, cookieOrigin2);
                        cookieStore2.addCookie(cookie);
                        if (this.log.isDebugEnabled()) {
                            Log log2 = this.log;
                            new StringBuilder();
                            log2.debug(sb3.append("Cookie accepted: \"").append(cookie).append("\". ").toString());
                        }
                    } catch (MalformedCookieException e) {
                        MalformedCookieException ex = e;
                        if (this.log.isWarnEnabled()) {
                            Log log3 = this.log;
                            new StringBuilder();
                            log3.warn(sb2.append("Cookie rejected: \"").append(cookie).append("\". ").append(ex.getMessage()).toString());
                        }
                    }
                }
            } catch (MalformedCookieException e2) {
                MalformedCookieException ex2 = e2;
                if (this.log.isWarnEnabled()) {
                    Log log4 = this.log;
                    new StringBuilder();
                    log4.warn(sb.append("Invalid cookie header: \"").append(header).append("\". ").append(ex2.getMessage()).toString());
                }
            }
        }
    }
}
