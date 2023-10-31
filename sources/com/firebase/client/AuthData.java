package com.firebase.client;

import java.util.Collections;
import java.util.Map;

public class AuthData {
    private final Map<String, Object> auth;
    private final long expires;
    private final String provider;
    private final Map<String, Object> providerData;
    private final String token;
    private final String uid;

    public AuthData(String token2, long expires2, String uid2, String provider2, Map<String, Object> map, Map<String, Object> map2) {
        Map<String, Object> auth2 = map;
        Map<String, Object> providerData2 = map2;
        this.token = token2;
        this.expires = expires2;
        this.uid = uid2;
        this.provider = provider2;
        this.providerData = providerData2 != null ? Collections.unmodifiableMap(providerData2) : null;
        this.auth = auth2 != null ? Collections.unmodifiableMap(auth2) : null;
    }

    public String getToken() {
        return this.token;
    }

    public long getExpires() {
        return this.expires;
    }

    public String getUid() {
        return this.uid;
    }

    public String getProvider() {
        return this.provider;
    }

    public Map<String, Object> getProviderData() {
        return this.providerData;
    }

    public Map<String, Object> getAuth() {
        return this.auth;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthData authData = (AuthData) o;
        if (this.provider == null ? authData.provider != null : !this.provider.equals(authData.provider)) {
            return false;
        }
        if (this.providerData == null ? authData.providerData != null : !this.providerData.equals(authData.providerData)) {
            return false;
        }
        if (this.auth == null ? authData.auth != null : !this.auth.equals(authData.auth)) {
            return false;
        }
        if (this.token == null ? authData.token != null : !this.token.equals(authData.token)) {
            return false;
        }
        if (this.expires != authData.expires) {
            return false;
        }
        if (this.uid == null ? authData.uid != null : !this.uid.equals(authData.uid)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (31 * ((31 * ((31 * ((31 * (this.token != null ? this.token.hashCode() : 0)) + (this.uid != null ? this.uid.hashCode() : 0))) + (this.provider != null ? this.provider.hashCode() : 0))) + (this.providerData != null ? this.providerData.hashCode() : 0))) + (this.auth != null ? this.auth.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("AuthData{uid='").append(this.uid).append('\'').append(", provider='").append(this.provider).append('\'').append(", token='").append(this.token == null ? null : "***").append('\'').append(", expires='").append(this.expires).append('\'').append(", auth='").append(this.auth).append('\'').append(", providerData='").append(this.providerData).append('\'').append('}').toString();
    }
}
