package org.shaded.apache.http.message;

import java.util.Iterator;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.HttpMessage;
import org.shaded.apache.http.params.BasicHttpParams;
import org.shaded.apache.http.params.HttpParams;

public abstract class AbstractHttpMessage implements HttpMessage {
    protected HeaderGroup headergroup;
    protected HttpParams params;

    protected AbstractHttpMessage(HttpParams params2) {
        HeaderGroup headerGroup;
        new HeaderGroup();
        this.headergroup = headerGroup;
        this.params = params2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected AbstractHttpMessage() {
        this((HttpParams) null);
    }

    public boolean containsHeader(String name) {
        return this.headergroup.containsHeader(name);
    }

    public Header[] getHeaders(String name) {
        return this.headergroup.getHeaders(name);
    }

    public Header getFirstHeader(String name) {
        return this.headergroup.getFirstHeader(name);
    }

    public Header getLastHeader(String name) {
        return this.headergroup.getLastHeader(name);
    }

    public Header[] getAllHeaders() {
        return this.headergroup.getAllHeaders();
    }

    public void addHeader(Header header) {
        this.headergroup.addHeader(header);
    }

    public void addHeader(String str, String str2) {
        Header header;
        Throwable th;
        String name = str;
        String value = str2;
        if (name == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header name may not be null");
            throw th2;
        }
        new BasicHeader(name, value);
        this.headergroup.addHeader(header);
    }

    public void setHeader(Header header) {
        this.headergroup.updateHeader(header);
    }

    public void setHeader(String str, String str2) {
        Header header;
        Throwable th;
        String name = str;
        String value = str2;
        if (name == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header name may not be null");
            throw th2;
        }
        new BasicHeader(name, value);
        this.headergroup.updateHeader(header);
    }

    public void setHeaders(Header[] headers) {
        this.headergroup.setHeaders(headers);
    }

    public void removeHeader(Header header) {
        this.headergroup.removeHeader(header);
    }

    public void removeHeaders(String str) {
        String name = str;
        if (name != null) {
            Iterator i = this.headergroup.iterator();
            while (i.hasNext()) {
                if (name.equalsIgnoreCase(((Header) i.next()).getName())) {
                    i.remove();
                }
            }
        }
    }

    public HeaderIterator headerIterator() {
        return this.headergroup.iterator();
    }

    public HeaderIterator headerIterator(String name) {
        return this.headergroup.iterator(name);
    }

    public HttpParams getParams() {
        HttpParams httpParams;
        if (this.params == null) {
            new BasicHttpParams();
            this.params = httpParams;
        }
        return this.params;
    }

    public void setParams(HttpParams httpParams) {
        Throwable th;
        HttpParams params2 = httpParams;
        if (params2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        this.params = params2;
    }
}
