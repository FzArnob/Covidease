package org.shaded.apache.http.auth;

import java.security.Principal;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.util.LangUtils;

@Immutable
public class UsernamePasswordCredentials implements Credentials {
    private final String password;
    private final BasicUserPrincipal principal;

    public UsernamePasswordCredentials(String str) {
        BasicUserPrincipal basicUserPrincipal;
        BasicUserPrincipal basicUserPrincipal2;
        Throwable th;
        String usernamePassword = str;
        if (usernamePassword == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Username:password string may not be null");
            throw th2;
        }
        int atColon = usernamePassword.indexOf(58);
        if (atColon >= 0) {
            new BasicUserPrincipal(usernamePassword.substring(0, atColon));
            this.principal = basicUserPrincipal2;
            this.password = usernamePassword.substring(atColon + 1);
            return;
        }
        new BasicUserPrincipal(usernamePassword);
        this.principal = basicUserPrincipal;
        this.password = null;
    }

    public UsernamePasswordCredentials(String str, String str2) {
        BasicUserPrincipal basicUserPrincipal;
        Throwable th;
        String userName = str;
        String password2 = str2;
        if (userName == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Username may not be null");
            throw th2;
        }
        new BasicUserPrincipal(userName);
        this.principal = basicUserPrincipal;
        this.password = password2;
    }

    public Principal getUserPrincipal() {
        return this.principal;
    }

    public String getUserName() {
        return this.principal.getName();
    }

    public String getPassword() {
        return this.password;
    }

    public int hashCode() {
        return this.principal.hashCode();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof UsernamePasswordCredentials) {
            if (LangUtils.equals((Object) this.principal, (Object) ((UsernamePasswordCredentials) o).principal)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.principal.toString();
    }
}
