package org.shaded.apache.http.conn.ssl;

import javax.net.ssl.SSLException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class StrictHostnameVerifier extends AbstractVerifier {
    public StrictHostnameVerifier() {
    }

    public final void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
        verify(host, cns, subjectAlts, true);
    }

    public final String toString() {
        return "STRICT";
    }
}
