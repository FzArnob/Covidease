package com.shaded.fasterxml.jackson.core.util;

import java.util.LinkedHashMap;
import java.util.Map;

public final class InternCache extends LinkedHashMap<String, String> {
    private static final int MAX_ENTRIES = 100;
    public static final InternCache instance;

    static {
        InternCache internCache;
        new InternCache();
        instance = internCache;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private InternCache() {
        super(100, 0.8f, true);
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry<String, String> entry) {
        Map.Entry<String, String> entry2 = entry;
        return size() > 100;
    }

    public synchronized String intern(String str) {
        String str2;
        String str3 = str;
        synchronized (this) {
            String str4 = (String) get(str3);
            if (str4 == null) {
                str4 = str3.intern();
                Object put = put(str4, str4);
            }
            str2 = str4;
        }
        return str2;
    }
}
