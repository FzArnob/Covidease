package com.firebase.client.core;

import java.net.URI;
import org.shaded.apache.http.HttpHost;

public class RepoInfo {
    private static final String LAST_SESSION_ID_PARAM = "ls";
    private static final String VERSION_PARAM = "v";
    public String host;
    public String internalHost;
    public String namespace;
    public boolean secure;

    public RepoInfo() {
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(HttpHost.DEFAULT_SCHEME_NAME).append(this.secure ? "s" : "").append("://").append(this.host).toString();
    }

    public String toDebugString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("(host=").append(this.host).append(", secure=").append(this.secure).append(", ns=").append(this.namespace).append(" internal=").append(this.internalHost).append(")").toString();
    }

    public URI getConnectionURL(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String optLastSessionId = str;
        String scheme = this.secure ? "wss" : "ws";
        new StringBuilder();
        String url = sb.append(scheme).append("://").append(this.internalHost).append("/.ws?ns=").append(this.namespace).append("&").append(VERSION_PARAM).append("=").append(Constants.WIRE_PROTOCOL_VERSION).toString();
        if (optLastSessionId != null) {
            new StringBuilder();
            url = sb2.append(url).append("&ls=").append(optLastSessionId).toString();
        }
        return URI.create(url);
    }

    public boolean isCacheableHost() {
        return this.internalHost.startsWith("s-");
    }

    public boolean isSecure() {
        return this.secure;
    }

    public boolean isDemoHost() {
        return this.host.contains(".firebaseio-demo.com");
    }

    public boolean isCustomHost() {
        return !this.host.contains(".firebaseio.com") && !this.host.contains(".firebaseio-demo.com");
    }
}
