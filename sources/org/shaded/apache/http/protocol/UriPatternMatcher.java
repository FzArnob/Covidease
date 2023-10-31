package org.shaded.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;

public class UriPatternMatcher {
    private final Map map;

    public UriPatternMatcher() {
        Map map2;
        new HashMap();
        this.map = map2;
    }

    public void register(String str, Object obj) {
        Throwable th;
        String pattern = str;
        Object obj2 = obj;
        if (pattern == null) {
            Throwable th2 = th;
            new IllegalArgumentException("URI request pattern may not be null");
            throw th2;
        }
        Object put = this.map.put(pattern, obj2);
    }

    public void unregister(String str) {
        String pattern = str;
        if (pattern != null) {
            Object remove = this.map.remove(pattern);
        }
    }

    public void setHandlers(Map map2) {
        Throwable th;
        Map map3 = map2;
        if (map3 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Map of handlers may not be null");
            throw th2;
        }
        this.map.clear();
        this.map.putAll(map3);
    }

    public Object lookup(String str) {
        Throwable th;
        String requestURI = str;
        if (requestURI == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Request URI may not be null");
            throw th2;
        }
        int index = requestURI.indexOf("?");
        if (index != -1) {
            requestURI = requestURI.substring(0, index);
        }
        Object handler = this.map.get(requestURI);
        if (handler == null) {
            String bestMatch = null;
            for (String pattern : this.map.keySet()) {
                if (matchUriRequestPattern(pattern, requestURI) && (bestMatch == null || bestMatch.length() < pattern.length() || (bestMatch.length() == pattern.length() && pattern.endsWith("*")))) {
                    handler = this.map.get(pattern);
                    bestMatch = pattern;
                }
            }
        }
        return handler;
    }

    /* access modifiers changed from: protected */
    public boolean matchUriRequestPattern(String str, String str2) {
        String pattern = str;
        String requestUri = str2;
        if (pattern.equals("*")) {
            return true;
        }
        return (pattern.endsWith("*") && requestUri.startsWith(pattern.substring(0, pattern.length() + -1))) || (pattern.startsWith("*") && requestUri.endsWith(pattern.substring(1, pattern.length())));
    }
}
