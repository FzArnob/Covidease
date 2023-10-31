package org.shaded.apache.http.auth;

import java.security.Principal;
import java.util.Locale;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.util.LangUtils;

@Immutable
public class NTCredentials implements Credentials {
    private final String password;
    private final NTUserPrincipal principal;
    private final String workstation;

    public NTCredentials(String str) {
        String username;
        NTUserPrincipal nTUserPrincipal;
        NTUserPrincipal nTUserPrincipal2;
        Throwable th;
        String usernamePassword = str;
        if (usernamePassword == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Username:password string may not be null");
            throw th2;
        }
        int atColon = usernamePassword.indexOf(58);
        if (atColon >= 0) {
            username = usernamePassword.substring(0, atColon);
            this.password = usernamePassword.substring(atColon + 1);
        } else {
            username = usernamePassword;
            this.password = null;
        }
        int atSlash = username.indexOf(47);
        if (atSlash >= 0) {
            new NTUserPrincipal(username.substring(0, atSlash).toUpperCase(Locale.ENGLISH), username.substring(atSlash + 1));
            this.principal = nTUserPrincipal2;
        } else {
            new NTUserPrincipal((String) null, username.substring(atSlash + 1));
            this.principal = nTUserPrincipal;
        }
        this.workstation = null;
    }

    public NTCredentials(String str, String str2, String str3, String str4) {
        NTUserPrincipal nTUserPrincipal;
        Throwable th;
        String userName = str;
        String password2 = str2;
        String workstation2 = str3;
        String domain = str4;
        if (userName == null) {
            Throwable th2 = th;
            new IllegalArgumentException("User name may not be null");
            throw th2;
        }
        new NTUserPrincipal(domain, userName);
        this.principal = nTUserPrincipal;
        this.password = password2;
        if (workstation2 != null) {
            this.workstation = workstation2.toUpperCase(Locale.ENGLISH);
            return;
        }
        this.workstation = null;
    }

    public Principal getUserPrincipal() {
        return this.principal;
    }

    public String getUserName() {
        return this.principal.getUsername();
    }

    public String getPassword() {
        return this.password;
    }

    public String getDomain() {
        return this.principal.getDomain();
    }

    public String getWorkstation() {
        return this.workstation;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.principal), (Object) this.workstation);
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof NTCredentials) {
            NTCredentials that = (NTCredentials) o;
            if (LangUtils.equals((Object) this.principal, (Object) that.principal) && LangUtils.equals((Object) this.workstation, (Object) that.workstation)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append("[principal: ");
        StringBuilder append2 = buffer.append(this.principal);
        StringBuilder append3 = buffer.append("][workstation: ");
        StringBuilder append4 = buffer.append(this.workstation);
        StringBuilder append5 = buffer.append("]");
        return buffer.toString();
    }
}
