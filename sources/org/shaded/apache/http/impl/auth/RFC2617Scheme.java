package org.shaded.apache.http.impl.auth;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.message.BasicHeaderValueParser;
import org.shaded.apache.http.message.HeaderValueParser;
import org.shaded.apache.http.message.ParserCursor;
import org.shaded.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class RFC2617Scheme extends AuthSchemeBase {
    private Map<String, String> params;

    public RFC2617Scheme() {
    }

    /* access modifiers changed from: protected */
    public void parseChallenge(CharArrayBuffer charArrayBuffer, int pos, int i) throws MalformedChallengeException {
        ParserCursor cursor;
        Map<String, String> map;
        Throwable th;
        CharArrayBuffer buffer = charArrayBuffer;
        int i2 = i;
        HeaderValueParser parser = BasicHeaderValueParser.DEFAULT;
        new ParserCursor(pos, buffer.length());
        HeaderElement[] elements = parser.parseElements(buffer, cursor);
        if (elements.length == 0) {
            Throwable th2 = th;
            new MalformedChallengeException("Authentication challenge is empty");
            throw th2;
        }
        new HashMap(elements.length);
        this.params = map;
        HeaderElement[] arr$ = elements;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            HeaderElement element = arr$[i$];
            String put = this.params.put(element.getName(), element.getValue());
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParameters() {
        Map<String, String> map;
        if (this.params == null) {
            new HashMap();
            this.params = map;
        }
        return this.params;
    }

    public String getParameter(String str) {
        Throwable th;
        String name = str;
        if (name == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameter name may not be null");
            throw th2;
        } else if (this.params == null) {
            return null;
        } else {
            return this.params.get(name.toLowerCase(Locale.ENGLISH));
        }
    }

    public String getRealm() {
        return getParameter("realm");
    }
}
