package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.util.MultiClassKey;
import java.util.HashMap;
import java.util.Map;

public class TranscoderRegistry {
    private static final MultiClassKey GET_KEY;
    private final Map<MultiClassKey, ResourceTranscoder<?, ?>> factories;

    public TranscoderRegistry() {
        Map<MultiClassKey, ResourceTranscoder<?, ?>> map;
        new HashMap();
        this.factories = map;
    }

    static {
        MultiClassKey multiClassKey;
        new MultiClassKey();
        GET_KEY = multiClassKey;
    }

    public <Z, R> void register(Class<Z> decodedClass, Class<R> transcodedClass, ResourceTranscoder<Z, R> transcoder) {
        Object obj;
        new MultiClassKey(decodedClass, transcodedClass);
        ResourceTranscoder<?, ?> put = this.factories.put(obj, transcoder);
    }

    /* JADX INFO: finally extract failed */
    public <Z, R> ResourceTranscoder<Z, R> get(Class<Z> cls, Class<R> cls2) {
        Throwable th;
        StringBuilder sb;
        Class<Z> decodedClass = cls;
        Class<R> transcodedClass = cls2;
        if (decodedClass.equals(transcodedClass)) {
            return UnitTranscoder.get();
        }
        MultiClassKey multiClassKey = GET_KEY;
        MultiClassKey multiClassKey2 = multiClassKey;
        synchronized (multiClassKey) {
            try {
                GET_KEY.set(decodedClass, transcodedClass);
                ResourceTranscoder<?, ?> result = this.factories.get(GET_KEY);
                if (result != null) {
                    return result;
                }
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("No transcoder registered for ").append(decodedClass).append(" and ").append(transcodedClass).toString());
                throw th2;
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    MultiClassKey multiClassKey3 = multiClassKey2;
                    throw th4;
                }
            }
        }
    }
}
