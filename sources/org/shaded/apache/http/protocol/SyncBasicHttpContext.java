package org.shaded.apache.http.protocol;

public class SyncBasicHttpContext extends BasicHttpContext {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SyncBasicHttpContext(HttpContext parentContext) {
        super(parentContext);
    }

    public synchronized Object getAttribute(String str) {
        Object attribute;
        String id = str;
        synchronized (this) {
            attribute = super.getAttribute(id);
        }
        return attribute;
    }

    public synchronized void setAttribute(String str, Object obj) {
        String id = str;
        Object obj2 = obj;
        synchronized (this) {
            super.setAttribute(id, obj2);
        }
    }

    public synchronized Object removeAttribute(String str) {
        Object removeAttribute;
        String id = str;
        synchronized (this) {
            removeAttribute = super.removeAttribute(id);
        }
        return removeAttribute;
    }
}
