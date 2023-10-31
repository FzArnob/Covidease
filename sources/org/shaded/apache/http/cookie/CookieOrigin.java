package org.shaded.apache.http.cookie;

import java.util.Locale;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public final class CookieOrigin {
    private final String host;
    private final String path;
    private final int port;
    private final boolean secure;

    public CookieOrigin(String str, int i, String str2, boolean z) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        Throwable th4;
        String host2 = str;
        int port2 = i;
        String path2 = str2;
        boolean secure2 = z;
        if (host2 == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Host of origin may not be null");
            throw th5;
        } else if (host2.trim().length() == 0) {
            Throwable th6 = th3;
            new IllegalArgumentException("Host of origin may not be blank");
            throw th6;
        } else if (port2 < 0) {
            Throwable th7 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid port: ").append(port2).toString());
            throw th7;
        } else if (path2 == null) {
            Throwable th8 = th;
            new IllegalArgumentException("Path of origin may not be null.");
            throw th8;
        } else {
            this.host = host2.toLowerCase(Locale.ENGLISH);
            this.port = port2;
            if (path2.trim().length() != 0) {
                this.path = path2;
            } else {
                this.path = "/";
            }
            this.secure = secure2;
        }
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        return this.path;
    }

    public int getPort() {
        return this.port;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append('[');
        if (this.secure) {
            StringBuilder append2 = buffer.append("(secure)");
        }
        StringBuilder append3 = buffer.append(this.host);
        StringBuilder append4 = buffer.append(':');
        StringBuilder append5 = buffer.append(Integer.toString(this.port));
        StringBuilder append6 = buffer.append(this.path);
        StringBuilder append7 = buffer.append(']');
        return buffer.toString();
    }
}
