package org.shaded.apache.http.conn.ssl;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class AllowAllHostnameVerifier extends AbstractVerifier {
    public AllowAllHostnameVerifier() {
    }

    public final void verify(String host, String[] cns, String[] subjectAlts) {
    }

    public final String toString() {
        return "ALLOW_ALL";
    }
}
