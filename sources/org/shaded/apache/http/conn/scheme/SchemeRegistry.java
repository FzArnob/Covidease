package org.shaded.apache.http.conn.scheme;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.GuardedBy;
import org.shaded.apache.http.annotation.ThreadSafe;

@ThreadSafe
public final class SchemeRegistry {
    @GuardedBy("this")
    private final Map<String, Scheme> registeredSchemes;

    public SchemeRegistry() {
        Map<String, Scheme> map;
        new LinkedHashMap();
        this.registeredSchemes = map;
    }

    public final synchronized Scheme getScheme(String str) {
        Scheme scheme;
        Throwable th;
        StringBuilder sb;
        String name = str;
        synchronized (this) {
            Scheme found = get(name);
            if (found == null) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Scheme '").append(name).append("' not registered.").toString());
                throw th2;
            }
            scheme = found;
        }
        return scheme;
    }

    public final synchronized Scheme getScheme(HttpHost httpHost) {
        Scheme scheme;
        Throwable th;
        HttpHost host = httpHost;
        synchronized (this) {
            if (host == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Host must not be null.");
                throw th2;
            }
            scheme = getScheme(host.getSchemeName());
        }
        return scheme;
    }

    public final synchronized Scheme get(String str) {
        Scheme scheme;
        Throwable th;
        String name = str;
        synchronized (this) {
            if (name == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Name must not be null.");
                throw th2;
            }
            scheme = this.registeredSchemes.get(name);
        }
        return scheme;
    }

    public final synchronized Scheme register(Scheme scheme) {
        Scheme put;
        Throwable th;
        Scheme sch = scheme;
        synchronized (this) {
            if (sch == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Scheme must not be null.");
                throw th2;
            }
            put = this.registeredSchemes.put(sch.getName(), sch);
        }
        return put;
    }

    public final synchronized Scheme unregister(String str) {
        Scheme remove;
        Throwable th;
        String name = str;
        synchronized (this) {
            if (name == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Name must not be null.");
                throw th2;
            }
            remove = this.registeredSchemes.remove(name);
        }
        return remove;
    }

    public final synchronized List<String> getSchemeNames() {
        List<String> list;
        List<String> list2;
        synchronized (this) {
            List<String> list3 = list;
            new ArrayList(this.registeredSchemes.keySet());
            list2 = list3;
        }
        return list2;
    }

    public synchronized void setItems(Map<String, Scheme> map) {
        Map<String, Scheme> map2 = map;
        synchronized (this) {
            if (map2 != null) {
                this.registeredSchemes.clear();
                this.registeredSchemes.putAll(map2);
            }
        }
    }
}
