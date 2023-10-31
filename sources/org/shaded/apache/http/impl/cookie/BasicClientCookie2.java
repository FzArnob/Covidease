package org.shaded.apache.http.impl.cookie;

import java.util.Date;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.cookie.SetCookie2;

@NotThreadSafe
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private String commentURL;
    private boolean discard;
    private int[] ports;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicClientCookie2(String name, String value) {
        super(name, value);
    }

    public int[] getPorts() {
        return this.ports;
    }

    public void setPorts(int[] ports2) {
        int[] iArr = ports2;
        this.ports = iArr;
    }

    public String getCommentURL() {
        return this.commentURL;
    }

    public void setCommentURL(String commentURL2) {
        String str = commentURL2;
        this.commentURL = str;
    }

    public void setDiscard(boolean discard2) {
        boolean z = discard2;
        this.discard = z;
    }

    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 clone = (BasicClientCookie2) super.clone();
        clone.ports = (int[]) this.ports.clone();
        return clone;
    }
}
