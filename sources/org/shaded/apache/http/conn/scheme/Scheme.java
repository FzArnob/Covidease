package org.shaded.apache.http.conn.scheme;

import java.util.Locale;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.util.LangUtils;

@Immutable
public final class Scheme {
    private final int defaultPort;
    private final boolean layered;
    private final String name;
    private final SocketFactory socketFactory;
    private String stringRep;

    public Scheme(String str, SocketFactory socketFactory2, int i) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        String name2 = str;
        SocketFactory factory = socketFactory2;
        int port = i;
        if (name2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Scheme name may not be null");
            throw th4;
        } else if (factory == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Socket factory may not be null");
            throw th5;
        } else if (port <= 0 || port > 65535) {
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Port is invalid: ").append(port).toString());
            throw th6;
        } else {
            this.name = name2.toLowerCase(Locale.ENGLISH);
            this.socketFactory = factory;
            this.defaultPort = port;
            this.layered = factory instanceof LayeredSocketFactory;
        }
    }

    public final int getDefaultPort() {
        return this.defaultPort;
    }

    public final SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isLayered() {
        return this.layered;
    }

    public final int resolvePort(int i) {
        int port = i;
        return port <= 0 ? this.defaultPort : port;
    }

    public final String toString() {
        StringBuilder sb;
        if (this.stringRep == null) {
            new StringBuilder();
            StringBuilder buffer = sb;
            StringBuilder append = buffer.append(this.name);
            StringBuilder append2 = buffer.append(':');
            StringBuilder append3 = buffer.append(Integer.toString(this.defaultPort));
            this.stringRep = buffer.toString();
        }
        return this.stringRep;
    }

    public final boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null) {
            return false;
        }
        if (this == obj2) {
            return true;
        }
        if (!(obj2 instanceof Scheme)) {
            return false;
        }
        Scheme s = (Scheme) obj2;
        return this.name.equals(s.name) && this.defaultPort == s.defaultPort && this.layered == s.layered && this.socketFactory.equals(s.socketFactory);
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.defaultPort), (Object) this.name), this.layered), (Object) this.socketFactory);
    }
}
