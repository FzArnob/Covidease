package org.shaded.apache.http.auth;

import java.security.Principal;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.util.LangUtils;

@Immutable
public final class BasicUserPrincipal implements Principal {
    private final String username;

    public BasicUserPrincipal(String str) {
        Throwable th;
        String username2 = str;
        if (username2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("User name may not be null");
            throw th2;
        }
        this.username = username2;
    }

    public String getName() {
        return this.username;
    }

    public int hashCode() {
        return LangUtils.hashCode(17, (Object) this.username);
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof BasicUserPrincipal) {
            if (LangUtils.equals((Object) this.username, (Object) ((BasicUserPrincipal) o).username)) {
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
        StringBuilder append2 = buffer.append(this.username);
        StringBuilder append3 = buffer.append("]");
        return buffer.toString();
    }
}
