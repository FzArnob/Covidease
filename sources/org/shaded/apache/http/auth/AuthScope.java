package org.shaded.apache.http.auth;

import java.util.Locale;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.util.LangUtils;

@Immutable
public class AuthScope {
    public static final AuthScope ANY;
    public static final String ANY_HOST = null;
    public static final int ANY_PORT = -1;
    public static final String ANY_REALM = null;
    public static final String ANY_SCHEME = null;
    private final String host;
    private final int port;
    private final String realm;
    private final String scheme;

    static {
        AuthScope authScope;
        new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
        ANY = authScope;
    }

    public AuthScope(String str, int i, String str2, String str3) {
        String host2 = str;
        int port2 = i;
        String realm2 = str2;
        String scheme2 = str3;
        this.host = host2 == null ? ANY_HOST : host2.toLowerCase(Locale.ENGLISH);
        this.port = port2 < 0 ? -1 : port2;
        this.realm = realm2 == null ? ANY_REALM : realm2;
        this.scheme = scheme2 == null ? ANY_SCHEME : scheme2.toUpperCase(Locale.ENGLISH);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AuthScope(String host2, int port2, String realm2) {
        this(host2, port2, realm2, ANY_SCHEME);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AuthScope(String host2, int port2) {
        this(host2, port2, ANY_REALM, ANY_SCHEME);
    }

    public AuthScope(AuthScope authScope) {
        Throwable th;
        AuthScope authscope = authScope;
        if (authscope == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Scope may not be null");
            throw th2;
        }
        this.host = authscope.getHost();
        this.port = authscope.getPort();
        this.realm = authscope.getRealm();
        this.scheme = authscope.getScheme();
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getRealm() {
        return this.realm;
    }

    public String getScheme() {
        return this.scheme;
    }

    public int match(AuthScope authScope) {
        AuthScope that = authScope;
        int factor = 0;
        if (LangUtils.equals((Object) this.scheme, (Object) that.scheme)) {
            factor = 0 + 1;
        } else if (!(this.scheme == ANY_SCHEME || that.scheme == ANY_SCHEME)) {
            return -1;
        }
        if (LangUtils.equals((Object) this.realm, (Object) that.realm)) {
            factor += 2;
        } else if (!(this.realm == ANY_REALM || that.realm == ANY_REALM)) {
            return -1;
        }
        if (this.port == that.port) {
            factor += 4;
        } else if (!(this.port == -1 || that.port == -1)) {
            return -1;
        }
        if (LangUtils.equals((Object) this.host, (Object) that.host)) {
            factor += 8;
        } else if (!(this.host == ANY_HOST || that.host == ANY_HOST)) {
            return -1;
        }
        return factor;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuthScope)) {
            return super.equals(o);
        }
        AuthScope that = (AuthScope) o;
        return LangUtils.equals((Object) this.host, (Object) that.host) && this.port == that.port && LangUtils.equals((Object) this.realm, (Object) that.realm) && LangUtils.equals((Object) this.scheme, (Object) that.scheme);
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buffer = stringBuffer;
        if (this.scheme != null) {
            StringBuffer append = buffer.append(this.scheme.toUpperCase(Locale.ENGLISH));
            StringBuffer append2 = buffer.append(' ');
        }
        if (this.realm != null) {
            StringBuffer append3 = buffer.append('\'');
            StringBuffer append4 = buffer.append(this.realm);
            StringBuffer append5 = buffer.append('\'');
        } else {
            StringBuffer append6 = buffer.append("<any realm>");
        }
        if (this.host != null) {
            StringBuffer append7 = buffer.append('@');
            StringBuffer append8 = buffer.append(this.host);
            if (this.port >= 0) {
                StringBuffer append9 = buffer.append(':');
                StringBuffer append10 = buffer.append(this.port);
            }
        }
        return buffer.toString();
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.host), this.port), (Object) this.realm), (Object) this.scheme);
    }
}
