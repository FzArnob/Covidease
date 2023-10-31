package org.shaded.apache.http.impl.cookie;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.cookie.SetCookie;

@NotThreadSafe
public class BasicClientCookie implements SetCookie, ClientCookie, Cloneable {
    private Map<String, String> attribs;
    private String cookieComment;
    private String cookieDomain;
    private Date cookieExpiryDate;
    private String cookiePath;
    private int cookieVersion;
    private boolean isSecure;
    private final String name;
    private String value;

    public BasicClientCookie(String str, String str2) {
        Map<String, String> map;
        Throwable th;
        String name2 = str;
        String value2 = str2;
        if (name2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Name may not be null");
            throw th2;
        }
        this.name = name2;
        new HashMap();
        this.attribs = map;
        this.value = value2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value2) {
        String str = value2;
        this.value = str;
    }

    public String getComment() {
        return this.cookieComment;
    }

    public void setComment(String comment) {
        String str = comment;
        this.cookieComment = str;
    }

    public String getCommentURL() {
        return null;
    }

    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        Date date = expiryDate;
        this.cookieExpiryDate = date;
    }

    public boolean isPersistent() {
        return null != this.cookieExpiryDate;
    }

    public String getDomain() {
        return this.cookieDomain;
    }

    public void setDomain(String str) {
        String domain = str;
        if (domain != null) {
            this.cookieDomain = domain.toLowerCase(Locale.ENGLISH);
            return;
        }
        this.cookieDomain = null;
    }

    public String getPath() {
        return this.cookiePath;
    }

    public void setPath(String path) {
        String str = path;
        this.cookiePath = str;
    }

    public boolean isSecure() {
        return this.isSecure;
    }

    public void setSecure(boolean secure) {
        boolean z = secure;
        this.isSecure = z;
    }

    public int[] getPorts() {
        return null;
    }

    public int getVersion() {
        return this.cookieVersion;
    }

    public void setVersion(int version) {
        int i = version;
        this.cookieVersion = i;
    }

    public boolean isExpired(Date date) {
        Throwable th;
        Date date2 = date;
        if (date2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Date may not be null");
            throw th2;
        }
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= date2.getTime();
    }

    public void setAttribute(String name2, String value2) {
        String put = this.attribs.put(name2, value2);
    }

    public String getAttribute(String name2) {
        return this.attribs.get(name2);
    }

    public boolean containsAttribute(String name2) {
        return this.attribs.get(name2) != null;
    }

    public Object clone() throws CloneNotSupportedException {
        Map<String, String> map;
        BasicClientCookie clone = (BasicClientCookie) super.clone();
        new HashMap(this.attribs);
        clone.attribs = map;
        return clone;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder buffer = sb;
        StringBuilder append = buffer.append("[version: ");
        StringBuilder append2 = buffer.append(Integer.toString(this.cookieVersion));
        StringBuilder append3 = buffer.append("]");
        StringBuilder append4 = buffer.append("[name: ");
        StringBuilder append5 = buffer.append(this.name);
        StringBuilder append6 = buffer.append("]");
        StringBuilder append7 = buffer.append("[value: ");
        StringBuilder append8 = buffer.append(this.value);
        StringBuilder append9 = buffer.append("]");
        StringBuilder append10 = buffer.append("[domain: ");
        StringBuilder append11 = buffer.append(this.cookieDomain);
        StringBuilder append12 = buffer.append("]");
        StringBuilder append13 = buffer.append("[path: ");
        StringBuilder append14 = buffer.append(this.cookiePath);
        StringBuilder append15 = buffer.append("]");
        StringBuilder append16 = buffer.append("[expiry: ");
        StringBuilder append17 = buffer.append(this.cookieExpiryDate);
        StringBuilder append18 = buffer.append("]");
        return buffer.toString();
    }
}
