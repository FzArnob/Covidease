package org.shaded.apache.http.impl.client;

import java.util.HashMap;
import org.shaded.apache.http.annotation.GuardedBy;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.auth.AuthScope;
import org.shaded.apache.http.auth.Credentials;
import org.shaded.apache.http.client.CredentialsProvider;

@ThreadSafe
public class BasicCredentialsProvider implements CredentialsProvider {
    @GuardedBy("this")
    private final HashMap<AuthScope, Credentials> credMap;

    public BasicCredentialsProvider() {
        HashMap<AuthScope, Credentials> hashMap;
        new HashMap<>();
        this.credMap = hashMap;
    }

    public synchronized void setCredentials(AuthScope authScope, Credentials credentials) {
        Throwable th;
        AuthScope authscope = authScope;
        Credentials credentials2 = credentials;
        synchronized (this) {
            if (authscope == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Authentication scope may not be null");
                throw th2;
            }
            Credentials put = this.credMap.put(authscope, credentials2);
        }
    }

    private static Credentials matchCredentials(HashMap<AuthScope, Credentials> hashMap, AuthScope authScope) {
        HashMap<AuthScope, Credentials> map = hashMap;
        AuthScope authscope = authScope;
        Credentials creds = map.get(authscope);
        if (creds == null) {
            int bestMatchFactor = -1;
            AuthScope bestMatch = null;
            for (AuthScope current : map.keySet()) {
                int factor = authscope.match(current);
                if (factor > bestMatchFactor) {
                    bestMatchFactor = factor;
                    bestMatch = current;
                }
            }
            if (bestMatch != null) {
                creds = map.get(bestMatch);
            }
        }
        return creds;
    }

    public synchronized Credentials getCredentials(AuthScope authScope) {
        Credentials matchCredentials;
        Throwable th;
        AuthScope authscope = authScope;
        synchronized (this) {
            if (authscope == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Authentication scope may not be null");
                throw th2;
            }
            matchCredentials = matchCredentials(this.credMap, authscope);
        }
        return matchCredentials;
    }

    public String toString() {
        return this.credMap.toString();
    }

    public synchronized void clear() {
        synchronized (this) {
            this.credMap.clear();
        }
    }
}
