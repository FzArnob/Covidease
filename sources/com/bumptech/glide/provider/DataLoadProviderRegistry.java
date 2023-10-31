package com.bumptech.glide.provider;

import com.bumptech.glide.util.MultiClassKey;
import java.util.HashMap;
import java.util.Map;

public class DataLoadProviderRegistry {
    private static final MultiClassKey GET_KEY;
    private final Map<MultiClassKey, DataLoadProvider<?, ?>> providers;

    public DataLoadProviderRegistry() {
        Map<MultiClassKey, DataLoadProvider<?, ?>> map;
        new HashMap();
        this.providers = map;
    }

    static {
        MultiClassKey multiClassKey;
        new MultiClassKey();
        GET_KEY = multiClassKey;
    }

    public <T, Z> void register(Class<T> dataClass, Class<Z> resourceClass, DataLoadProvider<T, Z> provider) {
        Object obj;
        new MultiClassKey(dataClass, resourceClass);
        DataLoadProvider<?, ?> put = this.providers.put(obj, provider);
    }

    public <T, Z> DataLoadProvider<T, Z> get(Class<T> cls, Class<Z> cls2) {
        Class<T> dataClass = cls;
        Class<Z> resourceClass = cls2;
        MultiClassKey multiClassKey = GET_KEY;
        MultiClassKey multiClassKey2 = multiClassKey;
        synchronized (multiClassKey) {
            try {
                GET_KEY.set(dataClass, resourceClass);
                DataLoadProvider<?, ?> result = this.providers.get(GET_KEY);
                if (result == null) {
                    result = EmptyDataLoadProvider.get();
                }
                return result;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    MultiClassKey multiClassKey3 = multiClassKey2;
                    throw th2;
                }
            }
        }
    }
}
