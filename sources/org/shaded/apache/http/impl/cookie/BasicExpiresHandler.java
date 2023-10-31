package org.shaded.apache.http.impl.cookie;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class BasicExpiresHandler extends AbstractCookieAttributeHandler {
    private final String[] datepatterns;

    public BasicExpiresHandler(String[] strArr) {
        Throwable th;
        String[] datepatterns2 = strArr;
        if (datepatterns2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Array of date patterns may not be null");
            throw th2;
        }
        this.datepatterns = datepatterns2;
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        SetCookie cookie = setCookie;
        String value = str;
        if (cookie == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Cookie may not be null");
            throw th4;
        } else if (value == null) {
            Throwable th5 = th2;
            new MalformedCookieException("Missing value for expires attribute");
            throw th5;
        } else {
            try {
                cookie.setExpiryDate(DateUtils.parseDate(value, this.datepatterns));
            } catch (DateParseException e) {
                DateParseException dateParseException = e;
                Throwable th6 = th;
                new StringBuilder();
                new MalformedCookieException(sb.append("Unable to parse expires attribute: ").append(value).toString());
                throw th6;
            }
        }
    }
}
