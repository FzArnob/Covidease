package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import java.util.HashMap;

public final class SerializerCache {
    private ReadOnlyClassToSerializerMap _readOnlyMap = null;
    private HashMap<TypeKey, JsonSerializer<Object>> _sharedMap;

    public SerializerCache() {
        HashMap<TypeKey, JsonSerializer<Object>> hashMap;
        new HashMap<>(64);
        this._sharedMap = hashMap;
    }

    /* JADX INFO: finally extract failed */
    public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
        synchronized (this) {
            try {
                ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap = this._readOnlyMap;
                if (readOnlyClassToSerializerMap == null) {
                    ReadOnlyClassToSerializerMap from = ReadOnlyClassToSerializerMap.from(this._sharedMap);
                    readOnlyClassToSerializerMap = from;
                    this._readOnlyMap = from;
                }
                return readOnlyClassToSerializerMap.instance();
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    public synchronized int size() {
        int size;
        synchronized (this) {
            size = this._sharedMap.size();
        }
        return size;
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        Object obj;
        Class<?> cls2 = cls;
        synchronized (this) {
            try {
                new TypeKey(cls2, false);
                JsonSerializer<Object> jsonSerializer = this._sharedMap.get(obj);
                return jsonSerializer;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        Object obj;
        JavaType javaType2 = javaType;
        synchronized (this) {
            try {
                new TypeKey(javaType2, false);
                JsonSerializer<Object> jsonSerializer = this._sharedMap.get(obj);
                return jsonSerializer;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        Object obj;
        JavaType javaType2 = javaType;
        synchronized (this) {
            try {
                new TypeKey(javaType2, true);
                JsonSerializer<Object> jsonSerializer = this._sharedMap.get(obj);
                return jsonSerializer;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        Object obj;
        Class<?> cls2 = cls;
        synchronized (this) {
            try {
                new TypeKey(cls2, true);
                JsonSerializer<Object> jsonSerializer = this._sharedMap.get(obj);
                return jsonSerializer;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void addTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer) {
        Object obj;
        JavaType javaType2 = javaType;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        synchronized (this) {
            try {
                new TypeKey(javaType2, true);
                if (this._sharedMap.put(obj, jsonSerializer2) == null) {
                    this._readOnlyMap = null;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void addTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        Object obj;
        Class<?> cls2 = cls;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        synchronized (this) {
            try {
                new TypeKey(cls2, true);
                if (this._sharedMap.put(obj, jsonSerializer2) == null) {
                    this._readOnlyMap = null;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void addAndResolveNonTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) throws JsonMappingException {
        Object obj;
        Class<?> cls2 = cls;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        synchronized (this) {
            try {
                new TypeKey(cls2, false);
                if (this._sharedMap.put(obj, jsonSerializer2) == null) {
                    this._readOnlyMap = null;
                }
                if (jsonSerializer2 instanceof ResolvableSerializer) {
                    ((ResolvableSerializer) jsonSerializer2).resolve(serializerProvider2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void addAndResolveNonTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) throws JsonMappingException {
        Object obj;
        JavaType javaType2 = javaType;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        synchronized (this) {
            try {
                new TypeKey(javaType2, false);
                if (this._sharedMap.put(obj, jsonSerializer2) == null) {
                    this._readOnlyMap = null;
                }
                if (jsonSerializer2 instanceof ResolvableSerializer) {
                    ((ResolvableSerializer) jsonSerializer2).resolve(serializerProvider2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public synchronized void flush() {
        synchronized (this) {
            this._sharedMap.clear();
        }
    }

    public static final class TypeKey {
        protected Class<?> _class;
        protected int _hashCode;
        protected boolean _isTyped;
        protected JavaType _type;

        public TypeKey(Class<?> cls, boolean z) {
            Class<?> cls2 = cls;
            boolean z2 = z;
            this._class = cls2;
            this._type = null;
            this._isTyped = z2;
            this._hashCode = hash(cls2, z2);
        }

        public TypeKey(JavaType javaType, boolean z) {
            JavaType javaType2 = javaType;
            boolean z2 = z;
            this._type = javaType2;
            this._class = null;
            this._isTyped = z2;
            this._hashCode = hash(javaType2, z2);
        }

        private static final int hash(Class<?> cls, boolean z) {
            int hashCode = cls.getName().hashCode();
            if (z) {
                hashCode++;
            }
            return hashCode;
        }

        private static final int hash(JavaType javaType, boolean z) {
            int hashCode = javaType.hashCode() - 1;
            if (z) {
                hashCode--;
            }
            return hashCode;
        }

        public void resetTyped(Class<?> cls) {
            Class<?> cls2 = cls;
            this._type = null;
            this._class = cls2;
            this._isTyped = true;
            this._hashCode = hash(cls2, true);
        }

        public void resetUntyped(Class<?> cls) {
            Class<?> cls2 = cls;
            this._type = null;
            this._class = cls2;
            this._isTyped = false;
            this._hashCode = hash(cls2, false);
        }

        public void resetTyped(JavaType javaType) {
            JavaType javaType2 = javaType;
            this._type = javaType2;
            this._class = null;
            this._isTyped = true;
            this._hashCode = hash(javaType2, true);
        }

        public void resetUntyped(JavaType javaType) {
            JavaType javaType2 = javaType;
            this._type = javaType2;
            this._class = null;
            this._isTyped = false;
            this._hashCode = hash(javaType2, false);
        }

        public final int hashCode() {
            return this._hashCode;
        }

        public final String toString() {
            StringBuilder sb;
            StringBuilder sb2;
            if (this._class != null) {
                new StringBuilder();
                return sb2.append("{class: ").append(this._class.getName()).append(", typed? ").append(this._isTyped).append("}").toString();
            }
            new StringBuilder();
            return sb.append("{type: ").append(this._type).append(", typed? ").append(this._isTyped).append("}").toString();
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r3 = r1
                if (r3 != 0) goto L_0x0008
                r3 = 0
                r0 = r3
            L_0x0007:
                return r0
            L_0x0008:
                r3 = r1
                r4 = r0
                if (r3 != r4) goto L_0x000f
                r3 = 1
                r0 = r3
                goto L_0x0007
            L_0x000f:
                r3 = r1
                java.lang.Class r3 = r3.getClass()
                r4 = r0
                java.lang.Class r4 = r4.getClass()
                if (r3 == r4) goto L_0x001e
                r3 = 0
                r0 = r3
                goto L_0x0007
            L_0x001e:
                r3 = r1
                com.shaded.fasterxml.jackson.databind.ser.SerializerCache$TypeKey r3 = (com.shaded.fasterxml.jackson.databind.ser.SerializerCache.TypeKey) r3
                r2 = r3
                r3 = r2
                boolean r3 = r3._isTyped
                r4 = r0
                boolean r4 = r4._isTyped
                if (r3 != r4) goto L_0x0048
                r3 = r0
                java.lang.Class<?> r3 = r3._class
                if (r3 == 0) goto L_0x003c
                r3 = r2
                java.lang.Class<?> r3 = r3._class
                r4 = r0
                java.lang.Class<?> r4 = r4._class
                if (r3 != r4) goto L_0x003a
                r3 = 1
            L_0x0038:
                r0 = r3
                goto L_0x0007
            L_0x003a:
                r3 = 0
                goto L_0x0038
            L_0x003c:
                r3 = r0
                com.shaded.fasterxml.jackson.databind.JavaType r3 = r3._type
                r4 = r2
                com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._type
                boolean r3 = r3.equals(r4)
                r0 = r3
                goto L_0x0007
            L_0x0048:
                r3 = 0
                r0 = r3
                goto L_0x0007
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.SerializerCache.TypeKey.equals(java.lang.Object):boolean");
        }
    }
}
