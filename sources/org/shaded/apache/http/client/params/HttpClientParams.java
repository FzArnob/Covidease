package org.shaded.apache.http.client.params;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public class HttpClientParams {
    private HttpClientParams() {
    }

    public static boolean isRedirecting(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setRedirecting(HttpParams httpParams, boolean z) {
        Throwable th;
        HttpParams params = httpParams;
        boolean value = z;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams booleanParameter = params.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, value);
    }

    public static boolean isAuthenticating(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getBooleanParameter(ClientPNames.HANDLE_AUTHENTICATION, true);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setAuthenticating(HttpParams httpParams, boolean z) {
        Throwable th;
        HttpParams params = httpParams;
        boolean value = z;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams booleanParameter = params.setBooleanParameter(ClientPNames.HANDLE_AUTHENTICATION, value);
    }

    public static String getCookiePolicy(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        String cookiePolicy = (String) params.getParameter(ClientPNames.COOKIE_POLICY);
        return cookiePolicy == null ? CookiePolicy.BEST_MATCH : cookiePolicy;
    }

    public static void setCookiePolicy(HttpParams httpParams, String str) {
        Throwable th;
        HttpParams params = httpParams;
        String cookiePolicy = str;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams parameter = params.setParameter(ClientPNames.COOKIE_POLICY, cookiePolicy);
    }
}
