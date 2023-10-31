package org.shaded.apache.http.cookie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.http.annotation.GuardedBy;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.params.HttpParams;

@ThreadSafe
public final class CookieSpecRegistry {
    @GuardedBy("this")
    private final Map<String, CookieSpecFactory> registeredSpecs;

    public CookieSpecRegistry() {
        Map<String, CookieSpecFactory> map;
        new LinkedHashMap();
        this.registeredSpecs = map;
    }

    public synchronized void register(String str, CookieSpecFactory cookieSpecFactory) {
        Throwable th;
        Throwable th2;
        String name = str;
        CookieSpecFactory factory = cookieSpecFactory;
        synchronized (this) {
            if (name == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Name may not be null");
                throw th3;
            } else if (factory == null) {
                Throwable th4 = th;
                new IllegalArgumentException("Cookie spec factory may not be null");
                throw th4;
            } else {
                CookieSpecFactory put = this.registeredSpecs.put(name.toLowerCase(Locale.ENGLISH), factory);
            }
        }
    }

    public synchronized void unregister(String str) {
        Throwable th;
        String id = str;
        synchronized (this) {
            if (id == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Id may not be null");
                throw th2;
            }
            CookieSpecFactory remove = this.registeredSpecs.remove(id.toLowerCase(Locale.ENGLISH));
        }
    }

    public synchronized CookieSpec getCookieSpec(String str, HttpParams httpParams) throws IllegalStateException {
        Throwable th;
        StringBuilder sb;
        CookieSpec newInstance;
        Throwable th2;
        String name = str;
        HttpParams params = httpParams;
        synchronized (this) {
            if (name == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Name may not be null");
                throw th3;
            }
            CookieSpecFactory factory = this.registeredSpecs.get(name.toLowerCase(Locale.ENGLISH));
            if (factory != null) {
                newInstance = factory.newInstance(params);
            } else {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Unsupported cookie spec: ").append(name).toString());
                throw th4;
            }
        }
        return newInstance;
    }

    public synchronized CookieSpec getCookieSpec(String str) throws IllegalStateException {
        CookieSpec cookieSpec;
        String name = str;
        synchronized (this) {
            cookieSpec = getCookieSpec(name, (HttpParams) null);
        }
        return cookieSpec;
    }

    public synchronized List<String> getSpecNames() {
        List<String> list;
        List<String> list2;
        synchronized (this) {
            List<String> list3 = list;
            new ArrayList(this.registeredSpecs.keySet());
            list2 = list3;
        }
        return list2;
    }

    public synchronized void setItems(Map<String, CookieSpecFactory> map) {
        Map<String, CookieSpecFactory> map2 = map;
        synchronized (this) {
            if (map2 != null) {
                this.registeredSpecs.clear();
                this.registeredSpecs.putAll(map2);
            }
        }
    }
}
