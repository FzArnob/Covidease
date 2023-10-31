package org.shaded.apache.http.impl;

import java.util.Locale;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseFactory;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.ReasonPhraseCatalog;
import org.shaded.apache.http.StatusLine;
import org.shaded.apache.http.message.BasicHttpResponse;
import org.shaded.apache.http.message.BasicStatusLine;
import org.shaded.apache.http.protocol.HttpContext;

public class DefaultHttpResponseFactory implements HttpResponseFactory {
    protected final ReasonPhraseCatalog reasonCatalog;

    public DefaultHttpResponseFactory(ReasonPhraseCatalog reasonPhraseCatalog) {
        Throwable th;
        ReasonPhraseCatalog catalog = reasonPhraseCatalog;
        if (catalog == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Reason phrase catalog must not be null.");
            throw th2;
        }
        this.reasonCatalog = catalog;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultHttpResponseFactory() {
        this(EnglishReasonPhraseCatalog.INSTANCE);
    }

    public HttpResponse newHttpResponse(ProtocolVersion protocolVersion, int i, HttpContext httpContext) {
        StatusLine statusline;
        HttpResponse httpResponse;
        Throwable th;
        ProtocolVersion ver = protocolVersion;
        int status = i;
        HttpContext context = httpContext;
        if (ver == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP version may not be null");
            throw th2;
        }
        Locale loc = determineLocale(context);
        new BasicStatusLine(ver, status, this.reasonCatalog.getReason(status, loc));
        new BasicHttpResponse(statusline, this.reasonCatalog, loc);
        return httpResponse;
    }

    public HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext) {
        HttpResponse httpResponse;
        Throwable th;
        StatusLine statusline = statusLine;
        HttpContext context = httpContext;
        if (statusline == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Status line may not be null");
            throw th2;
        }
        new BasicHttpResponse(statusline, this.reasonCatalog, determineLocale(context));
        return httpResponse;
    }

    /* access modifiers changed from: protected */
    public Locale determineLocale(HttpContext httpContext) {
        HttpContext httpContext2 = httpContext;
        return Locale.getDefault();
    }
}
