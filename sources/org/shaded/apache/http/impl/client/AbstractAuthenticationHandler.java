package org.shaded.apache.http.impl.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.FormattedHeader;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.auth.AuthScheme;
import org.shaded.apache.http.auth.AuthSchemeRegistry;
import org.shaded.apache.http.auth.AuthenticationException;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.client.AuthenticationHandler;
import org.shaded.apache.http.client.protocol.ClientContext;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.protocol.HttpContext;
import org.shaded.apache.http.util.CharArrayBuffer;

@Immutable
public abstract class AbstractAuthenticationHandler implements AuthenticationHandler {
    private static final List<String> DEFAULT_SCHEME_PRIORITY;
    private final Log log = LogFactory.getLog((Class) getClass());

    static {
        String[] strArr = new String[3];
        strArr[0] = "ntlm";
        String[] strArr2 = strArr;
        strArr2[1] = "digest";
        String[] strArr3 = strArr2;
        strArr3[2] = "basic";
        DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(strArr3));
    }

    public AbstractAuthenticationHandler() {
    }

    /* access modifiers changed from: protected */
    public Map<String, Header> parseChallenges(Header[] headerArr) throws MalformedChallengeException {
        Map<String, Header> map;
        int pos;
        CharArrayBuffer buffer;
        Throwable th;
        CharArrayBuffer charArrayBuffer;
        Header[] headers = headerArr;
        new HashMap(headers.length);
        Map<String, Header> map2 = map;
        Header[] arr$ = headers;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            Header header = arr$[i$];
            if (header instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header).getBuffer();
                pos = ((FormattedHeader) header).getValuePos();
            } else {
                String s = header.getValue();
                if (s == null) {
                    Throwable th2 = th;
                    new MalformedChallengeException("Header value is null");
                    throw th2;
                }
                new CharArrayBuffer(s.length());
                buffer = charArrayBuffer;
                buffer.append(s);
                pos = 0;
            }
            while (pos < buffer.length() && HTTP.isWhitespace(buffer.charAt(pos))) {
                pos++;
            }
            int beginIndex = pos;
            while (pos < buffer.length() && !HTTP.isWhitespace(buffer.charAt(pos))) {
                pos++;
            }
            Header put = map2.put(buffer.substring(beginIndex, pos).toLowerCase(Locale.ENGLISH), header);
        }
        return map2;
    }

    /* access modifiers changed from: protected */
    public List<String> getAuthPreferences() {
        return DEFAULT_SCHEME_PRIORITY;
    }

    public AuthScheme selectScheme(Map<String, Header> map, HttpResponse httpResponse, HttpContext httpContext) throws AuthenticationException {
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        Throwable th2;
        Map<String, Header> challenges = map;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        AuthSchemeRegistry registry = (AuthSchemeRegistry) context.getAttribute(ClientContext.AUTHSCHEME_REGISTRY);
        if (registry == null) {
            Throwable th3 = th2;
            new IllegalStateException("AuthScheme registry not set in HTTP context");
            throw th3;
        }
        Collection<String> authPrefs = (Collection) context.getAttribute(ClientContext.AUTH_SCHEME_PREF);
        if (authPrefs == null) {
            authPrefs = getAuthPreferences();
        }
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb5.append("Authentication schemes in the order of preference: ").append(authPrefs).toString());
        }
        AuthScheme authScheme = null;
        Iterator i$ = authPrefs.iterator();
        while (true) {
            if (!i$.hasNext()) {
                break;
            }
            String id = i$.next();
            if (challenges.get(id.toLowerCase(Locale.ENGLISH)) != null) {
                if (this.log.isDebugEnabled()) {
                    Log log3 = this.log;
                    new StringBuilder();
                    log3.debug(sb4.append(id).append(" authentication scheme selected").toString());
                }
                try {
                    authScheme = registry.getAuthScheme(id, response.getParams());
                    break;
                } catch (IllegalStateException e) {
                    IllegalStateException illegalStateException = e;
                    if (this.log.isWarnEnabled()) {
                        Log log4 = this.log;
                        new StringBuilder();
                        log4.warn(sb3.append("Authentication scheme ").append(id).append(" not supported").toString());
                    }
                }
            } else if (this.log.isDebugEnabled()) {
                Log log5 = this.log;
                new StringBuilder();
                log5.debug(sb2.append("Challenge for ").append(id).append(" authentication scheme not available").toString());
            }
        }
        if (authScheme != null) {
            return authScheme;
        }
        Throwable th4 = th;
        new StringBuilder();
        new AuthenticationException(sb.append("Unable to respond to any of these challenges: ").append(challenges).toString());
        throw th4;
    }
}
