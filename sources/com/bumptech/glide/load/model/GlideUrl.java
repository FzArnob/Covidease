package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class GlideUrl {
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    private final Headers headers;
    private String safeStringUrl;
    private URL safeUrl;
    private final String stringUrl;
    private final URL url;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GlideUrl(URL url2) {
        this(url2, Headers.DEFAULT);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GlideUrl(String url2) {
        this(url2, Headers.DEFAULT);
    }

    public GlideUrl(URL url2, Headers headers2) {
        Throwable th;
        Throwable th2;
        URL url3 = url2;
        Headers headers3 = headers2;
        if (url3 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("URL must not be null!");
            throw th3;
        } else if (headers3 == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Headers must not be null");
            throw th4;
        } else {
            this.url = url3;
            this.stringUrl = null;
            this.headers = headers3;
        }
    }

    public GlideUrl(String str, Headers headers2) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        String url2 = str;
        Headers headers3 = headers2;
        if (TextUtils.isEmpty(url2)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb.append("String url must not be empty or null: ").append(url2).toString());
            throw th3;
        } else if (headers3 == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Headers must not be null");
            throw th4;
        } else {
            this.stringUrl = url2;
            this.url = null;
            this.headers = headers3;
        }
    }

    public URL toURL() throws MalformedURLException {
        return getSafeUrl();
    }

    private URL getSafeUrl() throws MalformedURLException {
        URL url2;
        if (this.safeUrl == null) {
            new URL(getSafeStringUrl());
            this.safeUrl = url2;
        }
        return this.safeUrl;
    }

    public String toStringUrl() {
        return getSafeStringUrl();
    }

    private String getSafeStringUrl() {
        if (TextUtils.isEmpty(this.safeStringUrl)) {
            String unsafeStringUrl = this.stringUrl;
            if (TextUtils.isEmpty(unsafeStringUrl)) {
                unsafeStringUrl = this.url.toString();
            }
            this.safeStringUrl = Uri.encode(unsafeStringUrl, ALLOWED_URI_CHARS);
        }
        return this.safeStringUrl;
    }

    public Map<String, String> getHeaders() {
        return this.headers.getHeaders();
    }

    public String getCacheKey() {
        return this.stringUrl != null ? this.stringUrl : this.url.toString();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getCacheKey()).append(10).append(this.headers.toString()).toString();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof GlideUrl)) {
            return false;
        }
        GlideUrl other = (GlideUrl) o;
        return getCacheKey().equals(other.getCacheKey()) && this.headers.equals(other.headers);
    }

    public int hashCode() {
        return (31 * getCacheKey().hashCode()) + this.headers.hashCode();
    }
}
