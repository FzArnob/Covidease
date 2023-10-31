package org.shaded.apache.http.auth;

import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class AuthState {
    private AuthScheme authScheme;
    private AuthScope authScope;
    private Credentials credentials;

    public AuthState() {
    }

    public void invalidate() {
        this.authScheme = null;
        this.authScope = null;
        this.credentials = null;
    }

    public boolean isValid() {
        return this.authScheme != null;
    }

    public void setAuthScheme(AuthScheme authScheme2) {
        AuthScheme authScheme3 = authScheme2;
        if (authScheme3 == null) {
            invalidate();
            return;
        }
        this.authScheme = authScheme3;
    }

    public AuthScheme getAuthScheme() {
        return this.authScheme;
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Credentials credentials2) {
        Credentials credentials3 = credentials2;
        this.credentials = credentials3;
    }

    public AuthScope getAuthScope() {
        return this.authScope;
    }

    public void setAuthScope(AuthScope authScope2) {
        AuthScope authScope3 = authScope2;
        this.authScope = authScope3;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append("auth scope [");
        StringBuilder append2 = buffer.append(this.authScope);
        StringBuilder append3 = buffer.append("]; credentials set [");
        StringBuilder append4 = buffer.append(this.credentials != null ? "true" : "false");
        StringBuilder append5 = buffer.append("]");
        return buffer.toString();
    }
}
