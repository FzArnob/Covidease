package org.shaded.apache.http.message;

import java.util.Locale;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.ReasonPhraseCatalog;
import org.shaded.apache.http.StatusLine;

public class BasicHttpResponse extends AbstractHttpMessage implements HttpResponse {
    private HttpEntity entity;
    private Locale locale;
    private ReasonPhraseCatalog reasonCatalog;
    private StatusLine statusline;

    public BasicHttpResponse(StatusLine statusLine, ReasonPhraseCatalog reasonPhraseCatalog, Locale locale2) {
        Throwable th;
        StatusLine statusline2 = statusLine;
        ReasonPhraseCatalog catalog = reasonPhraseCatalog;
        Locale locale3 = locale2;
        if (statusline2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Status line may not be null.");
            throw th2;
        }
        this.statusline = statusline2;
        this.reasonCatalog = catalog;
        this.locale = locale3 != null ? locale3 : Locale.getDefault();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BasicHttpResponse(StatusLine statusline2) {
        this(statusline2, (ReasonPhraseCatalog) null, (Locale) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BasicHttpResponse(org.shaded.apache.http.ProtocolVersion r12, int r13, java.lang.String r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            org.shaded.apache.http.message.BasicStatusLine r5 = new org.shaded.apache.http.message.BasicStatusLine
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r1
            r8 = r2
            r9 = r3
            r6.<init>(r7, r8, r9)
            r6 = 0
            r7 = 0
            r4.<init>((org.shaded.apache.http.StatusLine) r5, (org.shaded.apache.http.ReasonPhraseCatalog) r6, (java.util.Locale) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.message.BasicHttpResponse.<init>(org.shaded.apache.http.ProtocolVersion, int, java.lang.String):void");
    }

    public ProtocolVersion getProtocolVersion() {
        return this.statusline.getProtocolVersion();
    }

    public StatusLine getStatusLine() {
        return this.statusline;
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setStatusLine(StatusLine statusLine) {
        Throwable th;
        StatusLine statusline2 = statusLine;
        if (statusline2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Status line may not be null");
            throw th2;
        }
        this.statusline = statusline2;
    }

    public void setStatusLine(ProtocolVersion ver, int i) {
        StatusLine statusLine;
        int code = i;
        new BasicStatusLine(ver, code, getReason(code));
        this.statusline = statusLine;
    }

    public void setStatusLine(ProtocolVersion ver, int code, String reason) {
        StatusLine statusLine;
        StatusLine statusLine2 = statusLine;
        new BasicStatusLine(ver, code, reason);
        this.statusline = statusLine2;
    }

    public void setStatusCode(int i) {
        StatusLine statusLine;
        int code = i;
        new BasicStatusLine(this.statusline.getProtocolVersion(), code, getReason(code));
        this.statusline = statusLine;
    }

    public void setReasonPhrase(String str) {
        StatusLine statusLine;
        Throwable th;
        String reason = str;
        if (reason == null || (reason.indexOf(10) < 0 && reason.indexOf(13) < 0)) {
            new BasicStatusLine(this.statusline.getProtocolVersion(), this.statusline.getStatusCode(), reason);
            this.statusline = statusLine;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Line break in reason phrase.");
        throw th2;
    }

    public void setEntity(HttpEntity entity2) {
        HttpEntity httpEntity = entity2;
        this.entity = httpEntity;
    }

    public void setLocale(Locale locale2) {
        StatusLine statusLine;
        Throwable th;
        Locale loc = locale2;
        if (loc == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Locale may not be null.");
            throw th2;
        }
        this.locale = loc;
        int code = this.statusline.getStatusCode();
        new BasicStatusLine(this.statusline.getProtocolVersion(), code, getReason(code));
        this.statusline = statusLine;
    }

    /* access modifiers changed from: protected */
    public String getReason(int code) {
        return this.reasonCatalog == null ? null : this.reasonCatalog.getReason(code, this.locale);
    }
}
