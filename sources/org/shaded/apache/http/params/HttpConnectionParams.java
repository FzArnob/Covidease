package org.shaded.apache.http.params;

public final class HttpConnectionParams implements CoreConnectionPNames {
    private HttpConnectionParams() {
    }

    public static int getSoTimeout(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getIntParameter(CoreConnectionPNames.SO_TIMEOUT, 0);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setSoTimeout(HttpParams httpParams, int i) {
        Throwable th;
        HttpParams params = httpParams;
        int timeout = i;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams intParameter = params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
    }

    public static boolean getTcpNoDelay(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setTcpNoDelay(HttpParams httpParams, boolean z) {
        Throwable th;
        HttpParams params = httpParams;
        boolean value = z;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams booleanParameter = params.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, value);
    }

    public static int getSocketBufferSize(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, -1);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setSocketBufferSize(HttpParams httpParams, int i) {
        Throwable th;
        HttpParams params = httpParams;
        int size = i;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams intParameter = params.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, size);
    }

    public static int getLinger(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getIntParameter(CoreConnectionPNames.SO_LINGER, -1);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setLinger(HttpParams httpParams, int i) {
        Throwable th;
        HttpParams params = httpParams;
        int value = i;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams intParameter = params.setIntParameter(CoreConnectionPNames.SO_LINGER, value);
    }

    public static int getConnectionTimeout(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 0);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setConnectionTimeout(HttpParams httpParams, int i) {
        Throwable th;
        HttpParams params = httpParams;
        int timeout = i;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams intParameter = params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
    }

    public static boolean isStaleCheckingEnabled(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setStaleCheckingEnabled(HttpParams httpParams, boolean z) {
        Throwable th;
        HttpParams params = httpParams;
        boolean value = z;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams booleanParameter = params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, value);
    }
}
