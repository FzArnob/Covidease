package org.shaded.apache.http.impl.cookie;

import java.util.List;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.params.CookiePolicy;
import org.shaded.apache.http.cookie.C1496SM;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.CookieSpec;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie2;

@NotThreadSafe
public class BestMatchSpec implements CookieSpec {
    private BrowserCompatSpec compat;
    private final String[] datepatterns;
    private NetscapeDraftSpec netscape;
    private RFC2109Spec obsoleteStrict;
    private final boolean oneHeader;
    private RFC2965Spec strict;

    public BestMatchSpec(String[] strArr, boolean z) {
        String[] datepatterns2 = strArr;
        boolean oneHeader2 = z;
        this.datepatterns = datepatterns2 == null ? null : (String[]) datepatterns2.clone();
        this.oneHeader = oneHeader2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BestMatchSpec() {
        this((String[]) null, false);
    }

    private RFC2965Spec getStrict() {
        RFC2965Spec rFC2965Spec;
        if (this.strict == null) {
            new RFC2965Spec(this.datepatterns, this.oneHeader);
            this.strict = rFC2965Spec;
        }
        return this.strict;
    }

    private RFC2109Spec getObsoleteStrict() {
        RFC2109Spec rFC2109Spec;
        if (this.obsoleteStrict == null) {
            new RFC2109Spec(this.datepatterns, this.oneHeader);
            this.obsoleteStrict = rFC2109Spec;
        }
        return this.obsoleteStrict;
    }

    private BrowserCompatSpec getCompat() {
        BrowserCompatSpec browserCompatSpec;
        if (this.compat == null) {
            new BrowserCompatSpec(this.datepatterns);
            this.compat = browserCompatSpec;
        }
        return this.compat;
    }

    private NetscapeDraftSpec getNetscape() {
        NetscapeDraftSpec netscapeDraftSpec;
        if (this.netscape == null) {
            new NetscapeDraftSpec(this.datepatterns);
            this.netscape = netscapeDraftSpec;
        }
        return this.netscape;
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        Throwable th2;
        Header header2 = header;
        CookieOrigin origin = cookieOrigin;
        if (header2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Header may not be null");
            throw th3;
        } else if (origin == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th4;
        } else {
            HeaderElement[] helems = header2.getElements();
            boolean versioned = false;
            boolean netscape2 = false;
            HeaderElement[] arr$ = helems;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                HeaderElement helem = arr$[i$];
                if (helem.getParameterByName(ClientCookie.VERSION_ATTR) != null) {
                    versioned = true;
                }
                if (helem.getParameterByName(ClientCookie.EXPIRES_ATTR) != null) {
                    netscape2 = true;
                }
            }
            if (versioned) {
                if (C1496SM.SET_COOKIE2.equals(header2.getName())) {
                    return getStrict().parse(helems, origin);
                }
                return getObsoleteStrict().parse(helems, origin);
            } else if (netscape2) {
                return getNetscape().parse(header2, origin);
            } else {
                return getCompat().parse(helems, origin);
            }
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
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
        } else if (cookie2.getVersion() <= 0) {
            getCompat().validate(cookie2, origin);
        } else if (cookie2 instanceof SetCookie2) {
            getStrict().validate(cookie2, origin);
        } else {
            getObsoleteStrict().validate(cookie2, origin);
        }
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
        } else if (cookie2.getVersion() <= 0) {
            return getCompat().match(cookie2, origin);
        } else {
            if (cookie2 instanceof SetCookie2) {
                return getStrict().match(cookie2, origin);
            }
            return getObsoleteStrict().match(cookie2, origin);
        }
    }

    public List<Header> formatCookies(List<Cookie> list) {
        Throwable th;
        List<Cookie> cookies = list;
        if (cookies == null) {
            Throwable th2 = th;
            new IllegalArgumentException("List of cookie may not be null");
            throw th2;
        }
        int version = Integer.MAX_VALUE;
        boolean isSetCookie2 = true;
        for (Cookie cookie : cookies) {
            if (!(cookie instanceof SetCookie2)) {
                isSetCookie2 = false;
            }
            if (cookie.getVersion() < version) {
                version = cookie.getVersion();
            }
        }
        if (version <= 0) {
            return getCompat().formatCookies(cookies);
        }
        if (isSetCookie2) {
            return getStrict().formatCookies(cookies);
        }
        return getObsoleteStrict().formatCookies(cookies);
    }

    public int getVersion() {
        return getStrict().getVersion();
    }

    public Header getVersionHeader() {
        return getStrict().getVersionHeader();
    }

    public String toString() {
        return CookiePolicy.BEST_MATCH;
    }
}
