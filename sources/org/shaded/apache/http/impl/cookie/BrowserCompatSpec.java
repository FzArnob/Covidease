package org.shaded.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.shaded.apache.http.FormattedHeader;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.client.params.CookiePolicy;
import org.shaded.apache.http.cookie.C1496SM;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieOrigin;
import org.shaded.apache.http.cookie.MalformedCookieException;
import org.shaded.apache.http.message.BufferedHeader;
import org.shaded.apache.http.message.ParserCursor;
import org.shaded.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BrowserCompatSpec extends CookieSpecBase {
    @Deprecated
    protected static final String[] DATE_PATTERNS;
    private static final String[] DEFAULT_DATE_PATTERNS;
    private final String[] datepatterns;

    static {
        String[] strArr = new String[14];
        strArr[0] = "EEE, dd MMM yyyy HH:mm:ss zzz";
        String[] strArr2 = strArr;
        strArr2[1] = DateUtils.PATTERN_RFC1036;
        String[] strArr3 = strArr2;
        strArr3[2] = DateUtils.PATTERN_ASCTIME;
        String[] strArr4 = strArr3;
        strArr4[3] = "EEE, dd-MMM-yyyy HH:mm:ss z";
        String[] strArr5 = strArr4;
        strArr5[4] = "EEE, dd-MMM-yyyy HH-mm-ss z";
        String[] strArr6 = strArr5;
        strArr6[5] = "EEE, dd MMM yy HH:mm:ss z";
        String[] strArr7 = strArr6;
        strArr7[6] = "EEE dd-MMM-yyyy HH:mm:ss z";
        String[] strArr8 = strArr7;
        strArr8[7] = "EEE dd MMM yyyy HH:mm:ss z";
        String[] strArr9 = strArr8;
        strArr9[8] = "EEE dd-MMM-yyyy HH-mm-ss z";
        String[] strArr10 = strArr9;
        strArr10[9] = "EEE dd-MMM-yy HH:mm:ss z";
        String[] strArr11 = strArr10;
        strArr11[10] = "EEE dd MMM yy HH:mm:ss z";
        String[] strArr12 = strArr11;
        strArr12[11] = "EEE,dd-MMM-yy HH:mm:ss z";
        String[] strArr13 = strArr12;
        strArr13[12] = "EEE,dd-MMM-yyyy HH:mm:ss z";
        String[] strArr14 = strArr13;
        strArr14[13] = "EEE, dd-MM-yyyy HH:mm:ss z";
        DATE_PATTERNS = strArr14;
        String[] strArr15 = new String[14];
        strArr15[0] = "EEE, dd MMM yyyy HH:mm:ss zzz";
        String[] strArr16 = strArr15;
        strArr16[1] = DateUtils.PATTERN_RFC1036;
        String[] strArr17 = strArr16;
        strArr17[2] = DateUtils.PATTERN_ASCTIME;
        String[] strArr18 = strArr17;
        strArr18[3] = "EEE, dd-MMM-yyyy HH:mm:ss z";
        String[] strArr19 = strArr18;
        strArr19[4] = "EEE, dd-MMM-yyyy HH-mm-ss z";
        String[] strArr20 = strArr19;
        strArr20[5] = "EEE, dd MMM yy HH:mm:ss z";
        String[] strArr21 = strArr20;
        strArr21[6] = "EEE dd-MMM-yyyy HH:mm:ss z";
        String[] strArr22 = strArr21;
        strArr22[7] = "EEE dd MMM yyyy HH:mm:ss z";
        String[] strArr23 = strArr22;
        strArr23[8] = "EEE dd-MMM-yyyy HH-mm-ss z";
        String[] strArr24 = strArr23;
        strArr24[9] = "EEE dd-MMM-yy HH:mm:ss z";
        String[] strArr25 = strArr24;
        strArr25[10] = "EEE dd MMM yy HH:mm:ss z";
        String[] strArr26 = strArr25;
        strArr26[11] = "EEE,dd-MMM-yy HH:mm:ss z";
        String[] strArr27 = strArr26;
        strArr27[12] = "EEE,dd-MMM-yyyy HH:mm:ss z";
        String[] strArr28 = strArr27;
        strArr28[13] = "EEE, dd-MM-yyyy HH:mm:ss z";
        DEFAULT_DATE_PATTERNS = strArr28;
    }

    public BrowserCompatSpec(String[] strArr) {
        CookieAttributeHandler cookieAttributeHandler;
        CookieAttributeHandler cookieAttributeHandler2;
        CookieAttributeHandler cookieAttributeHandler3;
        CookieAttributeHandler cookieAttributeHandler4;
        CookieAttributeHandler cookieAttributeHandler5;
        CookieAttributeHandler cookieAttributeHandler6;
        String[] datepatterns2 = strArr;
        if (datepatterns2 != null) {
            this.datepatterns = (String[]) datepatterns2.clone();
        } else {
            this.datepatterns = DEFAULT_DATE_PATTERNS;
        }
        new BasicPathHandler();
        registerAttribHandler(ClientCookie.PATH_ATTR, cookieAttributeHandler);
        new BasicDomainHandler();
        registerAttribHandler(ClientCookie.DOMAIN_ATTR, cookieAttributeHandler2);
        new BasicMaxAgeHandler();
        registerAttribHandler(ClientCookie.MAX_AGE_ATTR, cookieAttributeHandler3);
        new BasicSecureHandler();
        registerAttribHandler(ClientCookie.SECURE_ATTR, cookieAttributeHandler4);
        new BasicCommentHandler();
        registerAttribHandler(ClientCookie.COMMENT_ATTR, cookieAttributeHandler5);
        new BasicExpiresHandler(this.datepatterns);
        registerAttribHandler(ClientCookie.EXPIRES_ATTR, cookieAttributeHandler6);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BrowserCompatSpec() {
        this((String[]) null);
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException {
        HeaderElement[] elems;
        CharArrayBuffer charArrayBuffer;
        CharArrayBuffer buffer;
        ParserCursor parserCursor;
        ParserCursor cursor;
        Throwable th;
        ParserCursor parserCursor2;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        Throwable th4;
        Header header2 = header;
        CookieOrigin origin = cookieOrigin;
        if (header2 == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Header may not be null");
            throw th5;
        } else if (origin == null) {
            Throwable th6 = th3;
            new IllegalArgumentException("Cookie origin may not be null");
            throw th6;
        } else {
            String headername = header2.getName();
            String headervalue = header2.getValue();
            if (!headername.equalsIgnoreCase(C1496SM.SET_COOKIE)) {
                Throwable th7 = th2;
                new StringBuilder();
                new MalformedCookieException(sb.append("Unrecognized cookie header '").append(header2.toString()).append("'").toString());
                throw th7;
            }
            boolean isNetscapeCookie = false;
            int i1 = headervalue.toLowerCase(Locale.ENGLISH).indexOf("expires=");
            if (i1 != -1) {
                int i12 = i1 + "expires=".length();
                int i2 = headervalue.indexOf(59, i12);
                if (i2 == -1) {
                    i2 = headervalue.length();
                }
                try {
                    Date parseDate = DateUtils.parseDate(headervalue.substring(i12, i2), this.datepatterns);
                    isNetscapeCookie = true;
                } catch (DateParseException e) {
                    DateParseException dateParseException = e;
                }
            }
            if (isNetscapeCookie) {
                NetscapeDraftHeaderParser parser = NetscapeDraftHeaderParser.DEFAULT;
                if (header2 instanceof FormattedHeader) {
                    buffer = ((FormattedHeader) header2).getBuffer();
                    new ParserCursor(((FormattedHeader) header2).getValuePos(), buffer.length());
                    cursor = parserCursor2;
                } else {
                    String s = header2.getValue();
                    if (s == null) {
                        Throwable th8 = th;
                        new MalformedCookieException("Header value is null");
                        throw th8;
                    }
                    new CharArrayBuffer(s.length());
                    buffer = charArrayBuffer;
                    buffer.append(s);
                    new ParserCursor(0, buffer.length());
                    cursor = parserCursor;
                }
                elems = new HeaderElement[]{parser.parseHeader(buffer, cursor)};
            } else {
                elems = header2.getElements();
            }
            return parse(elems, origin);
        }
    }

    public List<Header> formatCookies(List<Cookie> list) {
        CharArrayBuffer charArrayBuffer;
        List<Header> list2;
        Object obj;
        Throwable th;
        Throwable th2;
        List<Cookie> cookies = list;
        if (cookies == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("List of cookies may not be null");
            throw th3;
        } else if (cookies.isEmpty()) {
            Throwable th4 = th;
            new IllegalArgumentException("List of cookies may not be empty");
            throw th4;
        } else {
            new CharArrayBuffer(20 * cookies.size());
            CharArrayBuffer buffer = charArrayBuffer;
            buffer.append(C1496SM.COOKIE);
            buffer.append(": ");
            for (int i = 0; i < cookies.size(); i++) {
                Cookie cookie = cookies.get(i);
                if (i > 0) {
                    buffer.append("; ");
                }
                buffer.append(cookie.getName());
                buffer.append("=");
                String s = cookie.getValue();
                if (s != null) {
                    buffer.append(s);
                }
            }
            new ArrayList(1);
            List<Header> headers = list2;
            new BufferedHeader(buffer);
            boolean add = headers.add(obj);
            return headers;
        }
    }

    public int getVersion() {
        return 0;
    }

    public Header getVersionHeader() {
        return null;
    }

    public String toString() {
        return CookiePolicy.BROWSER_COMPATIBILITY;
    }
}
