package org.shaded.apache.http.impl.cookie;

import java.util.StringTokenizer;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;
import org.shaded.apache.http.cookie.SetCookie2;

@Immutable
public class RFC2965PortAttributeHandler implements CookieAttributeHandler {
    public RFC2965PortAttributeHandler() {
    }

    private static int[] parsePortAttribute(String portValue) throws MalformedCookieException {
        StringTokenizer stringTokenizer;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        new StringTokenizer(portValue, ",");
        StringTokenizer st = stringTokenizer;
        int[] ports = new int[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            try {
                ports[i] = Integer.parseInt(st.nextToken().trim());
                if (ports[i] < 0) {
                    Throwable th3 = th2;
                    new MalformedCookieException("Invalid Port attribute.");
                    throw th3;
                }
                i++;
            } catch (NumberFormatException e) {
                NumberFormatException e2 = e;
                Throwable th4 = th;
                new StringBuilder();
                new MalformedCookieException(sb.append("Invalid Port attribute: ").append(e2.getMessage()).toString());
                throw th4;
            }
        }
        return ports;
    }

    private static boolean portMatch(int i, int[] iArr) {
        int port = i;
        int[] ports = iArr;
        boolean portInList = false;
        int i2 = 0;
        int len = ports.length;
        while (true) {
            if (i2 >= len) {
                break;
            } else if (port == ports[i2]) {
                portInList = true;
                break;
            } else {
                i2++;
            }
        }
        return portInList;
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Throwable th;
        SetCookie cookie = setCookie;
        String portValue = str;
        if (cookie == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Cookie may not be null");
            throw th2;
        } else if (cookie instanceof SetCookie2) {
            SetCookie2 cookie2 = (SetCookie2) cookie;
            if (portValue != null && portValue.trim().length() > 0) {
                cookie2.setPorts(parsePortAttribute(portValue));
            }
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Cookie cookie2 = cookie;
        CookieOrigin origin = cookieOrigin;
        if (cookie2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Cookie may not be null");
            throw th4;
        } else if (origin == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th5;
        } else {
            int port = origin.getPort();
            if ((cookie2 instanceof ClientCookie) && ((ClientCookie) cookie2).containsAttribute(ClientCookie.PORT_ATTR) && !portMatch(port, cookie2.getPorts())) {
                Throwable th6 = th;
                new MalformedCookieException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
                throw th6;
            }
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
        } else {
            int port = origin.getPort();
            if ((cookie2 instanceof ClientCookie) && ((ClientCookie) cookie2).containsAttribute(ClientCookie.PORT_ATTR)) {
                if (cookie2.getPorts() == null) {
                    return false;
                }
                if (!portMatch(port, cookie2.getPorts())) {
                    return false;
                }
            }
            return true;
        }
    }
}
