package org.shaded.apache.http.auth;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.http.annotation.GuardedBy;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.params.HttpParams;

@ThreadSafe
public final class AuthSchemeRegistry {
    @GuardedBy("this")
    private final Map<String, AuthSchemeFactory> registeredSchemes;

    public AuthSchemeRegistry() {
        Map<String, AuthSchemeFactory> map;
        new LinkedHashMap();
        this.registeredSchemes = map;
    }

    public synchronized void register(String str, AuthSchemeFactory authSchemeFactory) {
        Throwable th;
        Throwable th2;
        String name = str;
        AuthSchemeFactory factory = authSchemeFactory;
        synchronized (this) {
            if (name == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Name may not be null");
                throw th3;
            } else if (factory == null) {
                Throwable th4 = th;
                new IllegalArgumentException("Authentication scheme factory may not be null");
                throw th4;
            } else {
                AuthSchemeFactory put = this.registeredSchemes.put(name.toLowerCase(Locale.ENGLISH), factory);
            }
        }
    }

    public synchronized void unregister(String str) {
        Throwable th;
        String name = str;
        synchronized (this) {
            if (name == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Name may not be null");
                throw th2;
            }
            AuthSchemeFactory remove = this.registeredSchemes.remove(name.toLowerCase(Locale.ENGLISH));
        }
    }

    public synchronized AuthScheme getAuthScheme(String str, HttpParams httpParams) throws IllegalStateException {
        Throwable th;
        StringBuilder sb;
        AuthScheme newInstance;
        Throwable th2;
        String name = str;
        HttpParams params = httpParams;
        synchronized (this) {
            if (name == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Name may not be null");
                throw th3;
            }
            AuthSchemeFactory factory = this.registeredSchemes.get(name.toLowerCase(Locale.ENGLISH));
            if (factory != null) {
                newInstance = factory.newInstance(params);
            } else {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Unsupported authentication scheme: ").append(name).toString());
                throw th4;
            }
        }
        return newInstance;
    }

    public synchronized List<String> getSchemeNames() {
        List<String> list;
        List<String> list2;
        synchronized (this) {
            List<String> list3 = list;
            new ArrayList(this.registeredSchemes.keySet());
            list2 = list3;
        }
        return list2;
    }

    public synchronized void setItems(Map<String, AuthSchemeFactory> map) {
        Map<String, AuthSchemeFactory> map2 = map;
        synchronized (this) {
            if (map2 != null) {
                this.registeredSchemes.clear();
                this.registeredSchemes.putAll(map2);
            }
        }
    }
}
