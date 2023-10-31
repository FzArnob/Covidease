package org.shaded.apache.http.client;

import org.shaded.apache.http.auth.AuthScope;
import org.shaded.apache.http.auth.Credentials;

public interface CredentialsProvider {
    void clear();

    Credentials getCredentials(AuthScope authScope);

    void setCredentials(AuthScope authScope, Credentials credentials);
}
