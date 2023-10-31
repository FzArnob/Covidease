package org.shaded.apache.http.impl.auth;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.auth.AuthScheme;
import org.shaded.apache.http.auth.AuthSchemeFactory;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public class DigestSchemeFactory implements AuthSchemeFactory {
    public DigestSchemeFactory() {
    }

    public AuthScheme newInstance(HttpParams httpParams) {
        AuthScheme authScheme;
        HttpParams httpParams2 = httpParams;
        new DigestScheme();
        return authScheme;
    }
}
