package org.shaded.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;

public class BasicHttpContext implements HttpContext {
    private Map map;
    private final HttpContext parentContext;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BasicHttpContext() {
        this((HttpContext) null);
    }

    public BasicHttpContext(HttpContext parentContext2) {
        this.map = null;
        this.parentContext = parentContext2;
    }

    public Object getAttribute(String str) {
        Throwable th;
        String id = str;
        if (id == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Id may not be null");
            throw th2;
        }
        Object obj = null;
        if (this.map != null) {
            obj = this.map.get(id);
        }
        if (obj == null && this.parentContext != null) {
            obj = this.parentContext.getAttribute(id);
        }
        return obj;
    }

    public void setAttribute(String str, Object obj) {
        Map map2;
        Throwable th;
        String id = str;
        Object obj2 = obj;
        if (id == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Id may not be null");
            throw th2;
        }
        if (this.map == null) {
            new HashMap();
            this.map = map2;
        }
        Object put = this.map.put(id, obj2);
    }

    public Object removeAttribute(String str) {
        Throwable th;
        String id = str;
        if (id == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Id may not be null");
            throw th2;
        } else if (this.map != null) {
            return this.map.remove(id);
        } else {
            return null;
        }
    }
}
