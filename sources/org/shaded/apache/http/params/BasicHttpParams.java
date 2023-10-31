package org.shaded.apache.http.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class BasicHttpParams extends AbstractHttpParams implements Serializable, Cloneable {
    private static final long serialVersionUID = -7086398485908701455L;
    private final HashMap parameters;

    public BasicHttpParams() {
        HashMap hashMap;
        new HashMap();
        this.parameters = hashMap;
    }

    public Object getParameter(String name) {
        return this.parameters.get(name);
    }

    public HttpParams setParameter(String name, Object value) {
        Object put = this.parameters.put(name, value);
        return this;
    }

    public boolean removeParameter(String str) {
        String name = str;
        if (!this.parameters.containsKey(name)) {
            return false;
        }
        Object remove = this.parameters.remove(name);
        return true;
    }

    public void setParameters(String[] strArr, Object obj) {
        String[] names = strArr;
        Object value = obj;
        for (int i = 0; i < names.length; i++) {
            HttpParams parameter = setParameter(names[i], value);
        }
    }

    public boolean isParameterSet(String name) {
        return getParameter(name) != null;
    }

    public boolean isParameterSetLocally(String name) {
        return this.parameters.get(name) != null;
    }

    public void clear() {
        this.parameters.clear();
    }

    public HttpParams copy() {
        BasicHttpParams basicHttpParams;
        new BasicHttpParams();
        BasicHttpParams clone = basicHttpParams;
        copyParams(clone);
        return clone;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpParams clone = (BasicHttpParams) super.clone();
        copyParams(clone);
        return clone;
    }

    /* access modifiers changed from: protected */
    public void copyParams(HttpParams httpParams) {
        HttpParams target = httpParams;
        for (Map.Entry me : this.parameters.entrySet()) {
            if (me.getKey() instanceof String) {
                HttpParams parameter = target.setParameter((String) me.getKey(), me.getValue());
            }
        }
    }
}
