package org.shaded.apache.http.client.params;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public final class AuthPolicy {
    public static final String BASIC = "Basic";
    public static final String DIGEST = "Digest";
    public static final String NTLM = "NTLM";

    private AuthPolicy() {
    }
}
