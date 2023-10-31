package org.shaded.apache.http.impl.cookie;

import java.util.Date;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.cookie.SetCookie;

@Immutable
public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler {
    public BasicMaxAgeHandler() {
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Throwable th;
        StringBuilder sb;
        Date date;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        Throwable th4;
        SetCookie cookie = setCookie;
        String value = str;
        if (cookie == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Cookie may not be null");
            throw th5;
        } else if (value == null) {
            Throwable th6 = th3;
            new MalformedCookieException("Missing value for max-age attribute");
            throw th6;
        } else {
            try {
                int age = Integer.parseInt(value);
                if (age < 0) {
                    Throwable th7 = th2;
                    new StringBuilder();
                    new MalformedCookieException(sb2.append("Negative max-age attribute: ").append(value).toString());
                    throw th7;
                }
                new Date(System.currentTimeMillis() + (((long) age) * 1000));
                cookie.setExpiryDate(date);
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
                Throwable th8 = th;
                new StringBuilder();
                new MalformedCookieException(sb.append("Invalid max-age attribute: ").append(value).toString());
                throw th8;
            }
        }
    }
}
