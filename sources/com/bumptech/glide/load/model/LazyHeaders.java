package com.bumptech.glide.load.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LazyHeaders implements Headers {
    private volatile Map<String, String> combinedHeaders;
    private final Map<String, List<LazyHeaderFactory>> headers;

    LazyHeaders(Map<String, List<LazyHeaderFactory>> headers2) {
        this.headers = Collections.unmodifiableMap(headers2);
    }

    /* JADX INFO: finally extract failed */
    public Map<String, String> getHeaders() {
        if (this.combinedHeaders == null) {
            synchronized (this) {
                try {
                    if (this.combinedHeaders == null) {
                        this.combinedHeaders = Collections.unmodifiableMap(generateHeaders());
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        throw th2;
                    }
                }
            }
        }
        return this.combinedHeaders;
    }

    private Map<String, String> generateHeaders() {
        Map<String, String> map;
        StringBuilder sb;
        new HashMap();
        Map<String, String> combinedHeaders2 = map;
        for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.headers.entrySet()) {
            new StringBuilder();
            StringBuilder sb2 = sb;
            List<LazyHeaderFactory> factories = entry.getValue();
            for (int i = 0; i < factories.size(); i++) {
                StringBuilder append = sb2.append(factories.get(i).buildHeader());
                if (i != factories.size() - 1) {
                    StringBuilder append2 = sb2.append(',');
                }
            }
            String put = combinedHeaders2.put(entry.getKey(), sb2.toString());
        }
        return combinedHeaders2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("LazyHeaders{headers=").append(this.headers).append('}').toString();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof LazyHeaders)) {
            return false;
        }
        return this.headers.equals(((LazyHeaders) o).headers);
    }

    public int hashCode() {
        return this.headers.hashCode();
    }

    public static final class Builder {
        private static final String DEFAULT_ENCODING = "identity";
        private static final Map<String, List<LazyHeaderFactory>> DEFAULT_HEADERS;
        private static final String DEFAULT_USER_AGENT = System.getProperty("http.agent");
        private static final String ENCODING_HEADER = "Accept-Encoding";
        private static final String USER_AGENT_HEADER = "User-Agent";
        private boolean copyOnModify = true;
        private Map<String, List<LazyHeaderFactory>> headers = DEFAULT_HEADERS;
        private boolean isEncodingDefault = true;
        private boolean isUserAgentDefault = true;

        public Builder() {
        }

        static {
            Map<String, List<LazyHeaderFactory>> map;
            Object obj;
            Object obj2;
            new HashMap<>(2);
            Map<String, List<LazyHeaderFactory>> temp = map;
            if (!TextUtils.isEmpty(DEFAULT_USER_AGENT)) {
                new StringHeaderFactory(DEFAULT_USER_AGENT);
                List<LazyHeaderFactory> put = temp.put("User-Agent", Collections.singletonList(obj2));
            }
            new StringHeaderFactory("identity");
            List<LazyHeaderFactory> put2 = temp.put(ENCODING_HEADER, Collections.singletonList(obj));
            DEFAULT_HEADERS = Collections.unmodifiableMap(temp);
        }

        public Builder addHeader(String key, String value) {
            LazyHeaderFactory lazyHeaderFactory;
            new StringHeaderFactory(value);
            return addHeader(key, lazyHeaderFactory);
        }

        public Builder addHeader(String str, LazyHeaderFactory lazyHeaderFactory) {
            String key = str;
            LazyHeaderFactory factory = lazyHeaderFactory;
            if ((this.isEncodingDefault && ENCODING_HEADER.equalsIgnoreCase(key)) || (this.isUserAgentDefault && "User-Agent".equalsIgnoreCase(key))) {
                return setHeader(key, factory);
            }
            copyIfNecessary();
            boolean add = getFactories(key).add(factory);
            return this;
        }

        public Builder setHeader(String key, String str) {
            LazyHeaderFactory lazyHeaderFactory;
            LazyHeaderFactory lazyHeaderFactory2;
            String value = str;
            String str2 = key;
            if (value == null) {
                lazyHeaderFactory2 = null;
            } else {
                lazyHeaderFactory2 = lazyHeaderFactory;
                new StringHeaderFactory(value);
            }
            return setHeader(str2, lazyHeaderFactory2);
        }

        public Builder setHeader(String str, LazyHeaderFactory lazyHeaderFactory) {
            String key = str;
            LazyHeaderFactory factory = lazyHeaderFactory;
            copyIfNecessary();
            if (factory == null) {
                List<LazyHeaderFactory> remove = this.headers.remove(key);
            } else {
                List<LazyHeaderFactory> factories = getFactories(key);
                factories.clear();
                boolean add = factories.add(factory);
            }
            if (this.isEncodingDefault && ENCODING_HEADER.equalsIgnoreCase(key)) {
                this.isEncodingDefault = false;
            }
            if (this.isUserAgentDefault && "User-Agent".equalsIgnoreCase(key)) {
                this.isUserAgentDefault = false;
            }
            return this;
        }

        private List<LazyHeaderFactory> getFactories(String str) {
            List<LazyHeaderFactory> list;
            String key = str;
            List<LazyHeaderFactory> factories = this.headers.get(key);
            if (factories == null) {
                new ArrayList<>();
                factories = list;
                List<LazyHeaderFactory> put = this.headers.put(key, factories);
            }
            return factories;
        }

        private void copyIfNecessary() {
            if (this.copyOnModify) {
                this.copyOnModify = false;
                this.headers = copyHeaders();
            }
        }

        public LazyHeaders build() {
            LazyHeaders lazyHeaders;
            this.copyOnModify = true;
            new LazyHeaders(this.headers);
            return lazyHeaders;
        }

        private Map<String, List<LazyHeaderFactory>> copyHeaders() {
            Map<String, List<LazyHeaderFactory>> map;
            Object obj;
            new HashMap(this.headers.size());
            Map<String, List<LazyHeaderFactory>> result = map;
            for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.headers.entrySet()) {
                new ArrayList(entry.getValue());
                List<LazyHeaderFactory> put = result.put(entry.getKey(), obj);
            }
            return result;
        }
    }

    static final class StringHeaderFactory implements LazyHeaderFactory {
        private final String value;

        StringHeaderFactory(String value2) {
            this.value = value2;
        }

        public String buildHeader() {
            return this.value;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("StringHeaderFactory{value='").append(this.value).append('\'').append('}').toString();
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (!(o instanceof StringHeaderFactory)) {
                return false;
            }
            return this.value.equals(((StringHeaderFactory) o).value);
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }
}
