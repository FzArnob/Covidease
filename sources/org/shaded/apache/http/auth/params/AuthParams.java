package org.shaded.apache.http.auth.params;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public final class AuthParams {
    private AuthParams() {
    }

    public static String getCredentialCharset(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        String charset = (String) params.getParameter(AuthPNames.CREDENTIAL_CHARSET);
        if (charset == null) {
            charset = "US-ASCII";
        }
        return charset;
    }

    public static void setCredentialCharset(HttpParams httpParams, String str) {
        Throwable th;
        HttpParams params = httpParams;
        String charset = str;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams parameter = params.setParameter(AuthPNames.CREDENTIAL_CHARSET, charset);
    }
}
