package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import java.util.Collection;
import java.util.HashMap;

public class TypeNameIdResolver extends TypeIdResolverBase {
    protected final MapperConfig<?> _config;
    protected final HashMap<String, JavaType> _idToType;
    protected final HashMap<String, String> _typeToId;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected TypeNameIdResolver(com.shaded.fasterxml.jackson.databind.cfg.MapperConfig<?> r9, com.shaded.fasterxml.jackson.databind.JavaType r10, java.util.HashMap<java.lang.String, java.lang.String> r11, java.util.HashMap<java.lang.String, com.shaded.fasterxml.jackson.databind.JavaType> r12) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r0
            r6 = r2
            r7 = r1
            com.shaded.fasterxml.jackson.databind.type.TypeFactory r7 = r7.getTypeFactory()
            r5.<init>(r6, r7)
            r5 = r0
            r6 = r1
            r5._config = r6
            r5 = r0
            r6 = r3
            r5._typeToId = r6
            r5 = r0
            r6 = r4
            r5._idToType = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.jsontype.impl.TypeNameIdResolver.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfig, com.shaded.fasterxml.jackson.databind.JavaType, java.util.HashMap, java.util.HashMap):void");
    }

    public static TypeNameIdResolver construct(MapperConfig<?> mapperConfig, JavaType javaType, Collection<NamedType> collection, boolean z, boolean z2) {
        TypeNameIdResolver typeNameIdResolver;
        String _defaultTypeId;
        JavaType javaType2;
        HashMap hashMap;
        HashMap hashMap2;
        Throwable th;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        JavaType javaType3 = javaType;
        Collection<NamedType> collection2 = collection;
        boolean z3 = z;
        boolean z4 = z2;
        if (z3 == z4) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        HashMap hashMap3 = null;
        HashMap hashMap4 = null;
        if (z3) {
            new HashMap();
            hashMap3 = hashMap2;
        }
        if (z4) {
            new HashMap();
            hashMap4 = hashMap;
        }
        if (collection2 != null) {
            for (NamedType next : collection2) {
                Class<?> type = next.getType();
                if (next.hasName()) {
                    _defaultTypeId = next.getName();
                } else {
                    _defaultTypeId = _defaultTypeId(type);
                }
                String str = _defaultTypeId;
                if (z3) {
                    Object put = hashMap3.put(type.getName(), str);
                }
                if (z4 && ((javaType2 = (JavaType) hashMap4.get(str)) == null || !type.isAssignableFrom(javaType2.getRawClass()))) {
                    Object put2 = hashMap4.put(str, mapperConfig2.constructType(type));
                }
            }
        }
        new TypeNameIdResolver(mapperConfig2, javaType3, hashMap3, hashMap4);
        return typeNameIdResolver;
    }

    public JsonTypeInfo.C1434Id getMechanism() {
        return JsonTypeInfo.C1434Id.NAME;
    }

    /* JADX INFO: finally extract failed */
    public String idFromValue(Object obj) {
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        HashMap<String, String> hashMap = this._typeToId;
        HashMap<String, String> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                String str = this._typeToId.get(name);
                if (str == null) {
                    if (this._config.isAnnotationProcessingEnabled()) {
                        str = this._config.getAnnotationIntrospector().findTypeName(this._config.introspectClassAnnotations(cls).getClassInfo());
                    }
                    if (str == null) {
                        str = _defaultTypeId(cls);
                    }
                    String put = this._typeToId.put(name, str);
                }
                return str;
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<String, String> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    public String idFromValueAndType(Object obj, Class<?> cls) {
        Object obj2 = obj;
        Class<?> cls2 = cls;
        if (obj2 == null) {
            return null;
        }
        return idFromValue(obj2);
    }

    public JavaType typeFromId(String str) throws IllegalArgumentException {
        return this._idToType.get(str);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('[').append(getClass().getName());
        StringBuilder append2 = sb2.append("; id-to-type=").append(this._idToType);
        StringBuilder append3 = sb2.append(']');
        return sb2.toString();
    }

    protected static String _defaultTypeId(Class<?> cls) {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? name : name.substring(lastIndexOf + 1);
    }
}
