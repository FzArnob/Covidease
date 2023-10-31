package org.shaded.apache.http.message;

import org.shaded.apache.http.HeaderElement;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.util.CharArrayBuffer;
import org.shaded.apache.http.util.LangUtils;

public class BasicHeaderElement implements HeaderElement, Cloneable {
    private final String name;
    private final NameValuePair[] parameters;
    private final String value;

    public BasicHeaderElement(String str, String str2, NameValuePair[] nameValuePairArr) {
        Throwable th;
        String name2 = str;
        String value2 = str2;
        NameValuePair[] parameters2 = nameValuePairArr;
        if (name2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Name may not be null");
            throw th2;
        }
        this.name = name2;
        this.value = value2;
        if (parameters2 != null) {
            this.parameters = parameters2;
            return;
        }
        this.parameters = new NameValuePair[0];
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BasicHeaderElement(String name2, String value2) {
        this(name2, value2, (NameValuePair[]) null);
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public NameValuePair[] getParameters() {
        return (NameValuePair[]) this.parameters.clone();
    }

    public int getParameterCount() {
        return this.parameters.length;
    }

    public NameValuePair getParameter(int index) {
        return this.parameters[index];
    }

    public NameValuePair getParameterByName(String str) {
        Throwable th;
        String name2 = str;
        if (name2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Name may not be null");
            throw th2;
        }
        NameValuePair found = null;
        int i = 0;
        while (true) {
            if (i >= this.parameters.length) {
                break;
            }
            NameValuePair current = this.parameters[i];
            if (current.getName().equalsIgnoreCase(name2)) {
                found = current;
                break;
            }
            i++;
        }
        return found;
    }

    public boolean equals(Object obj) {
        Object object = obj;
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof HeaderElement)) {
            return false;
        }
        BasicHeaderElement that = (BasicHeaderElement) object;
        return this.name.equals(that.name) && LangUtils.equals((Object) this.value, (Object) that.value) && LangUtils.equals((Object[]) this.parameters, (Object[]) that.parameters);
    }

    public int hashCode() {
        int hash = LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.name), (Object) this.value);
        for (int i = 0; i < this.parameters.length; i++) {
            hash = LangUtils.hashCode(hash, (Object) this.parameters[i]);
        }
        return hash;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer;
        new CharArrayBuffer(64);
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(this.name);
        if (this.value != null) {
            buffer.append("=");
            buffer.append(this.value);
        }
        for (int i = 0; i < this.parameters.length; i++) {
            buffer.append("; ");
            buffer.append((Object) this.parameters[i]);
        }
        return buffer.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
