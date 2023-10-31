package org.shaded.apache.http.message;

import java.io.Serializable;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.util.CharArrayBuffer;
import org.shaded.apache.http.util.LangUtils;

public class BasicNameValuePair implements NameValuePair, Cloneable, Serializable {
    private static final long serialVersionUID = -6437800749411518984L;
    private final String name;
    private final String value;

    public BasicNameValuePair(String str, String str2) {
        Throwable th;
        String name2 = str;
        String value2 = str2;
        if (name2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Name may not be null");
            throw th2;
        }
        this.name = name2;
        this.value = value2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer;
        if (this.value == null) {
            return this.name;
        }
        new CharArrayBuffer(this.name.length() + 1 + this.value.length());
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(this.name);
        buffer.append("=");
        buffer.append(this.value);
        return buffer.toString();
    }

    public boolean equals(Object obj) {
        Object object = obj;
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof NameValuePair)) {
            return false;
        }
        BasicNameValuePair that = (BasicNameValuePair) object;
        return this.name.equals(that.name) && LangUtils.equals((Object) this.value, (Object) that.value);
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.name), (Object) this.value);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
