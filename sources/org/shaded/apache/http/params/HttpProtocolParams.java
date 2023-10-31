package org.shaded.apache.http.params;

import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolVersion;

public final class HttpProtocolParams implements CoreProtocolPNames {
    private HttpProtocolParams() {
    }

    public static String getHttpElementCharset(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        String charset = (String) params.getParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET);
        if (charset == null) {
            charset = "US-ASCII";
        }
        return charset;
    }

    public static void setHttpElementCharset(HttpParams httpParams, String str) {
        Throwable th;
        HttpParams params = httpParams;
        String charset = str;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams parameter = params.setParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET, charset);
    }

    public static String getContentCharset(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        String charset = (String) params.getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET);
        if (charset == null) {
            charset = "ISO-8859-1";
        }
        return charset;
    }

    public static void setContentCharset(HttpParams httpParams, String str) {
        Throwable th;
        HttpParams params = httpParams;
        String charset = str;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams parameter = params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset);
    }

    public static ProtocolVersion getVersion(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        Object param = params.getParameter(CoreProtocolPNames.PROTOCOL_VERSION);
        if (param == null) {
            return HttpVersion.HTTP_1_1;
        }
        return (ProtocolVersion) param;
    }

    public static void setVersion(HttpParams httpParams, ProtocolVersion protocolVersion) {
        Throwable th;
        HttpParams params = httpParams;
        ProtocolVersion version = protocolVersion;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams parameter = params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, version);
    }

    public static String getUserAgent(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return (String) params.getParameter(CoreProtocolPNames.USER_AGENT);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setUserAgent(HttpParams httpParams, String str) {
        Throwable th;
        HttpParams params = httpParams;
        String useragent = str;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams parameter = params.setParameter(CoreProtocolPNames.USER_AGENT, useragent);
    }

    public static boolean useExpectContinue(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setUseExpectContinue(HttpParams httpParams, boolean z) {
        Throwable th;
        HttpParams params = httpParams;
        boolean b = z;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams booleanParameter = params.setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, b);
    }
}
