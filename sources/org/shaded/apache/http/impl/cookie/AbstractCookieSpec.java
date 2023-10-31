package org.shaded.apache.http.impl.cookie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.cookie.CookieAttributeHandler;
import org.shaded.apache.http.cookie.CookieSpec;

@NotThreadSafe
public abstract class AbstractCookieSpec implements CookieSpec {
    private final Map<String, CookieAttributeHandler> attribHandlerMap;

    public AbstractCookieSpec() {
        Map<String, CookieAttributeHandler> map;
        new HashMap(10);
        this.attribHandlerMap = map;
    }

    public void registerAttribHandler(String str, CookieAttributeHandler cookieAttributeHandler) {
        Throwable th;
        Throwable th2;
        String name = str;
        CookieAttributeHandler handler = cookieAttributeHandler;
        if (name == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Attribute name may not be null");
            throw th3;
        } else if (handler == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Attribute handler may not be null");
            throw th4;
        } else {
            CookieAttributeHandler put = this.attribHandlerMap.put(name, handler);
        }
    }

    /* access modifiers changed from: protected */
    public CookieAttributeHandler findAttribHandler(String name) {
        return this.attribHandlerMap.get(name);
    }

    /* access modifiers changed from: protected */
    public CookieAttributeHandler getAttribHandler(String str) {
        Throwable th;
        StringBuilder sb;
        String name = str;
        CookieAttributeHandler handler = findAttribHandler(name);
        if (handler != null) {
            return handler;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Handler not registered for ").append(name).append(" attribute.").toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public Collection<CookieAttributeHandler> getAttribHandlers() {
        return this.attribHandlerMap.values();
    }
}
