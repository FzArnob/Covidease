package org.shaded.apache.http.conn.ssl;

import javax.net.ssl.SSLException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class BrowserCompatHostnameVerifier extends AbstractVerifier {
    public BrowserCompatHostnameVerifier() {
    }

    public final void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
        verify(host, cns, subjectAlts, false);
    }

    public final String toString() {
        return "BROWSER_COMPATIBLE";
    }
}
