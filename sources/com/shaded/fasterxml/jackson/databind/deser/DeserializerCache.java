package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class DeserializerCache implements Serializable {
    private static final long serialVersionUID = 1;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers;
    protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers;

    public DeserializerCache() {
        ConcurrentHashMap<JavaType, JsonDeserializer<Object>> concurrentHashMap;
        HashMap<JavaType, JsonDeserializer<Object>> hashMap;
        new ConcurrentHashMap<>(64, 0.75f, 2);
        this._cachedDeserializers = concurrentHashMap;
        new HashMap<>(8);
        this._incompleteDeserializers = hashMap;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }

    public int cachedDeserializersCount() {
        return this._cachedDeserializers.size();
    }

    public void flushCachedDeserializers() {
        this._cachedDeserializers.clear();
    }

    public JsonDeserializer<Object> findValueDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        DeserializerFactory deserializerFactory2 = deserializerFactory;
        JavaType javaType2 = javaType;
        JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType2);
        if (_findCachedDeserializer != null) {
            return _findCachedDeserializer;
        }
        JsonDeserializer<Object> _createAndCacheValueDeserializer = _createAndCacheValueDeserializer(deserializationContext2, deserializerFactory2, javaType2);
        if (_createAndCacheValueDeserializer == null) {
            _createAndCacheValueDeserializer = _handleUnknownValueDeserializer(javaType2);
        }
        return _createAndCacheValueDeserializer;
    }

    public KeyDeserializer findKeyDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        KeyDeserializer createKeyDeserializer = deserializerFactory.createKeyDeserializer(deserializationContext2, javaType2);
        if (createKeyDeserializer == null) {
            return _handleUnknownKeyDeserializer(javaType2);
        }
        if (createKeyDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) createKeyDeserializer).resolve(deserializationContext2);
        }
        return createKeyDeserializer;
    }

    public boolean hasValueDeserializerFor(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) {
        DeserializationContext deserializationContext2 = deserializationContext;
        DeserializerFactory deserializerFactory2 = deserializerFactory;
        JavaType javaType2 = javaType;
        JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType2);
        if (_findCachedDeserializer == null) {
            try {
                _findCachedDeserializer = _createAndCacheValueDeserializer(deserializationContext2, deserializerFactory2, javaType2);
            } catch (Exception e) {
                Exception exc = e;
                return false;
            }
        }
        return _findCachedDeserializer != null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findCachedDeserializer(JavaType javaType) {
        Throwable th;
        JavaType javaType2 = javaType;
        if (javaType2 != null) {
            return this._cachedDeserializers.get(javaType2);
        }
        Throwable th2 = th;
        new IllegalArgumentException("Null JavaType passed");
        throw th2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> _createAndCacheValueDeserializer(com.shaded.fasterxml.jackson.databind.DeserializationContext r16, com.shaded.fasterxml.jackson.databind.deser.DeserializerFactory r17, com.shaded.fasterxml.jackson.databind.JavaType r18) throws com.shaded.fasterxml.jackson.databind.JsonMappingException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r10 = r0
            java.util.HashMap<com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r10 = r10._incompleteDeserializers
            r14 = r10
            r10 = r14
            r11 = r14
            r4 = r11
            monitor-enter(r10)
            r10 = r0
            r11 = r3
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r10 = r10._findCachedDeserializer(r11)     // Catch:{ all -> 0x0072 }
            r5 = r10
            r10 = r5
            if (r10 == 0) goto L_0x001e
            r10 = r5
            r11 = r4
            monitor-exit(r11)     // Catch:{ all -> 0x0072 }
            r0 = r10
        L_0x001d:
            return r0
        L_0x001e:
            r10 = r0
            java.util.HashMap<com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r10 = r10._incompleteDeserializers     // Catch:{ all -> 0x0072 }
            int r10 = r10.size()     // Catch:{ all -> 0x0072 }
            r6 = r10
            r10 = r6
            if (r10 <= 0) goto L_0x003c
            r10 = r0
            java.util.HashMap<com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r10 = r10._incompleteDeserializers     // Catch:{ all -> 0x0072 }
            r11 = r3
            java.lang.Object r10 = r10.get(r11)     // Catch:{ all -> 0x0072 }
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r10 = (com.shaded.fasterxml.jackson.databind.JsonDeserializer) r10     // Catch:{ all -> 0x0072 }
            r5 = r10
            r10 = r5
            if (r10 == 0) goto L_0x003c
            r10 = r5
            r11 = r4
            monitor-exit(r11)     // Catch:{ all -> 0x0072 }
            r0 = r10
            goto L_0x001d
        L_0x003c:
            r10 = r0
            r11 = r1
            r12 = r2
            r13 = r3
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r10 = r10._createAndCache2(r11, r12, r13)     // Catch:{ all -> 0x005c }
            r7 = r10
            r10 = r6
            if (r10 != 0) goto L_0x0057
            r10 = r0
            java.util.HashMap<com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r10 = r10._incompleteDeserializers     // Catch:{ all -> 0x0072 }
            int r10 = r10.size()     // Catch:{ all -> 0x0072 }
            if (r10 <= 0) goto L_0x0057
            r10 = r0
            java.util.HashMap<com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r10 = r10._incompleteDeserializers     // Catch:{ all -> 0x0072 }
            r10.clear()     // Catch:{ all -> 0x0072 }
        L_0x0057:
            r10 = r4
            monitor-exit(r10)     // Catch:{ all -> 0x0072 }
            r10 = r7
            r0 = r10
            goto L_0x001d
        L_0x005c:
            r10 = move-exception
            r8 = r10
            r10 = r6
            if (r10 != 0) goto L_0x0070
            r10 = r0
            java.util.HashMap<com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r10 = r10._incompleteDeserializers     // Catch:{ all -> 0x0072 }
            int r10 = r10.size()     // Catch:{ all -> 0x0072 }
            if (r10 <= 0) goto L_0x0070
            r10 = r0
            java.util.HashMap<com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r10 = r10._incompleteDeserializers     // Catch:{ all -> 0x0072 }
            r10.clear()     // Catch:{ all -> 0x0072 }
        L_0x0070:
            r10 = r8
            throw r10     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r10 = move-exception
            r9 = r10
            r10 = r4
            monitor-exit(r10)     // Catch:{ all -> 0x0072 }
            r10 = r9
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.DeserializerCache._createAndCacheValueDeserializer(com.shaded.fasterxml.jackson.databind.DeserializationContext, com.shaded.fasterxml.jackson.databind.deser.DeserializerFactory, com.shaded.fasterxml.jackson.databind.JavaType):com.shaded.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _createAndCache2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        Throwable th;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        try {
            JsonDeserializer<Object> _createDeserializer = _createDeserializer(deserializationContext2, deserializerFactory, javaType2);
            if (_createDeserializer == null) {
                return null;
            }
            boolean z = _createDeserializer instanceof ResolvableDeserializer;
            boolean isCachable = _createDeserializer.isCachable();
            if (z) {
                JsonDeserializer<Object> put = this._incompleteDeserializers.put(javaType2, _createDeserializer);
                ((ResolvableDeserializer) _createDeserializer).resolve(deserializationContext2);
                JsonDeserializer<Object> remove = this._incompleteDeserializers.remove(javaType2);
            }
            if (isCachable) {
                JsonDeserializer<Object> put2 = this._cachedDeserializers.put(javaType2, _createDeserializer);
            }
            return _createDeserializer;
        } catch (IllegalArgumentException e) {
            IllegalArgumentException illegalArgumentException = e;
            Throwable th2 = th;
            new JsonMappingException(illegalArgumentException.getMessage(), (JsonLocation) null, illegalArgumentException);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _createDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer;
        DeserializationContext deserializationContext2 = deserializationContext;
        DeserializerFactory deserializerFactory2 = deserializerFactory;
        JavaType javaType2 = javaType;
        DeserializationConfig config = deserializationContext2.getConfig();
        if (javaType2.isAbstract() || javaType2.isMapLikeType() || javaType2.isCollectionLikeType()) {
            javaType2 = deserializerFactory2.mapAbstractType(config, javaType2);
        }
        BeanDescription introspect = config.introspect(javaType2);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext2, introspect.getClassInfo(), javaType2);
        if (modifyTypeByAnnotation != javaType2) {
            javaType2 = modifyTypeByAnnotation;
            introspect = config.introspect(modifyTypeByAnnotation);
        }
        Class<?> findPOJOBuilder = introspect.findPOJOBuilder();
        if (findPOJOBuilder != null) {
            return deserializerFactory2.createBuilderBasedDeserializer(deserializationContext2, javaType2, introspect, findPOJOBuilder);
        }
        Converter<Object, Object> findDeserializationConverter = introspect.findDeserializationConverter();
        if (findDeserializationConverter == null) {
            return _createDeserializer2(deserializationContext2, deserializerFactory2, javaType2, introspect);
        }
        JavaType inputType = findDeserializationConverter.getInputType(deserializationContext2.getTypeFactory());
        new StdDelegatingDeserializer(findDeserializationConverter, inputType, _createDeserializer2(deserializationContext2, deserializerFactory2, inputType, introspect));
        return jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _createDeserializer2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonFormat.Value findExpectedFormat;
        DeserializationContext deserializationContext2 = deserializationContext;
        DeserializerFactory deserializerFactory2 = deserializerFactory;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        DeserializationConfig config = deserializationContext2.getConfig();
        if (javaType2.isEnumType()) {
            return deserializerFactory2.createEnumDeserializer(deserializationContext2, javaType2, beanDescription2);
        }
        if (javaType2.isContainerType()) {
            if (javaType2.isArrayType()) {
                return deserializerFactory2.createArrayDeserializer(deserializationContext2, (ArrayType) javaType2, beanDescription2);
            }
            if (javaType2.isMapLikeType()) {
                MapLikeType mapLikeType = (MapLikeType) javaType2;
                if (mapLikeType.isTrueMapType()) {
                    return deserializerFactory2.createMapDeserializer(deserializationContext2, (MapType) mapLikeType, beanDescription2);
                }
                return deserializerFactory2.createMapLikeDeserializer(deserializationContext2, mapLikeType, beanDescription2);
            } else if (javaType2.isCollectionLikeType() && ((findExpectedFormat = beanDescription2.findExpectedFormat((JsonFormat.Value) null)) == null || findExpectedFormat.getShape() != JsonFormat.Shape.OBJECT)) {
                CollectionLikeType collectionLikeType = (CollectionLikeType) javaType2;
                if (collectionLikeType.isTrueCollectionType()) {
                    return deserializerFactory2.createCollectionDeserializer(deserializationContext2, (CollectionType) collectionLikeType, beanDescription2);
                }
                return deserializerFactory2.createCollectionLikeDeserializer(deserializationContext2, collectionLikeType, beanDescription2);
            }
        }
        if (JsonNode.class.isAssignableFrom(javaType2.getRawClass())) {
            return deserializerFactory2.createTreeDeserializer(config, javaType2, beanDescription2);
        }
        return deserializerFactory2.createBeanDeserializer(deserializationContext2, javaType2, beanDescription2);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        Annotated annotated2 = annotated;
        Object findDeserializer = deserializationContext2.getAnnotationIntrospector().findDeserializer(annotated2);
        if (findDeserializer == null) {
            return null;
        }
        return findConvertingDeserializer(deserializationContext2, annotated2, deserializationContext2.deserializerInstance(annotated2, findDeserializer));
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext deserializationContext, Annotated annotated, JsonDeserializer<Object> jsonDeserializer) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer2;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonDeserializer<Object> jsonDeserializer3 = jsonDeserializer;
        Converter<Object, Object> findConverter = findConverter(deserializationContext2, annotated);
        if (findConverter == null) {
            return jsonDeserializer3;
        }
        new StdDelegatingDeserializer(findConverter, findConverter.getInputType(deserializationContext2.getTypeFactory()), jsonDeserializer3);
        return jsonDeserializer2;
    }

    /* access modifiers changed from: protected */
    public Converter<Object, Object> findConverter(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        Annotated annotated2 = annotated;
        Object findDeserializationConverter = deserializationContext2.getAnnotationIntrospector().findDeserializationConverter(annotated2);
        if (findDeserializationConverter == null) {
            return null;
        }
        return deserializationContext2.converterInstance(annotated2, findDeserializationConverter);
    }

    private JavaType modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, JavaType javaType) throws JsonMappingException {
        Object findContentDeserializer;
        Throwable th;
        StringBuilder sb;
        Object findKeyDeserializer;
        KeyDeserializer keyDeserializerInstance;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        DeserializationContext deserializationContext2 = deserializationContext;
        Annotated annotated2 = annotated;
        MapLikeType mapLikeType = javaType;
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        Class<?> findDeserializationType = annotationIntrospector.findDeserializationType(annotated2, mapLikeType);
        if (findDeserializationType != null) {
            try {
                mapLikeType = mapLikeType.narrowBy(findDeserializationType);
            } catch (IllegalArgumentException e) {
                IllegalArgumentException illegalArgumentException = e;
                Throwable th5 = th4;
                new StringBuilder();
                new JsonMappingException(sb4.append("Failed to narrow type ").append(mapLikeType).append(" with concrete-type annotation (value ").append(findDeserializationType.getName()).append("), method '").append(annotated2.getName()).append("': ").append(illegalArgumentException.getMessage()).toString(), (JsonLocation) null, illegalArgumentException);
                throw th5;
            }
        }
        if (mapLikeType.isContainerType()) {
            Class<?> findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated2, mapLikeType.getKeyType());
            if (findDeserializationKeyType != null) {
                if (!(mapLikeType instanceof MapLikeType)) {
                    Throwable th6 = th3;
                    new StringBuilder();
                    new JsonMappingException(sb3.append("Illegal key-type annotation: type ").append(mapLikeType).append(" is not a Map(-like) type").toString());
                    throw th6;
                }
                try {
                    mapLikeType = ((MapLikeType) mapLikeType).narrowKey(findDeserializationKeyType);
                } catch (IllegalArgumentException e2) {
                    IllegalArgumentException illegalArgumentException2 = e2;
                    Throwable th7 = th2;
                    new StringBuilder();
                    new JsonMappingException(sb2.append("Failed to narrow key type ").append(mapLikeType).append(" with key-type annotation (").append(findDeserializationKeyType.getName()).append("): ").append(illegalArgumentException2.getMessage()).toString(), (JsonLocation) null, illegalArgumentException2);
                    throw th7;
                }
            }
            JavaType keyType = mapLikeType.getKeyType();
            if (!(keyType == null || keyType.getValueHandler() != null || (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated2)) == null || (keyDeserializerInstance = deserializationContext2.keyDeserializerInstance(annotated2, findKeyDeserializer)) == null)) {
                mapLikeType = ((MapLikeType) mapLikeType).withKeyValueHandler(keyDeserializerInstance);
                JavaType keyType2 = mapLikeType.getKeyType();
            }
            Class<?> findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated2, mapLikeType.getContentType());
            if (findDeserializationContentType != null) {
                try {
                    mapLikeType = mapLikeType.narrowContentsBy(findDeserializationContentType);
                } catch (IllegalArgumentException e3) {
                    IllegalArgumentException illegalArgumentException3 = e3;
                    Throwable th8 = th;
                    new StringBuilder();
                    new JsonMappingException(sb.append("Failed to narrow content type ").append(mapLikeType).append(" with content-type annotation (").append(findDeserializationContentType.getName()).append("): ").append(illegalArgumentException3.getMessage()).toString(), (JsonLocation) null, illegalArgumentException3);
                    throw th8;
                }
            }
            if (mapLikeType.getContentType().getValueHandler() == null && (findContentDeserializer = annotationIntrospector.findContentDeserializer(annotated2)) != null) {
                JsonDeserializer<Object> jsonDeserializer = null;
                if (findContentDeserializer instanceof JsonDeserializer) {
                    JsonDeserializer jsonDeserializer2 = (JsonDeserializer) findContentDeserializer;
                } else {
                    Class<?> _verifyAsClass = _verifyAsClass(findContentDeserializer, "findContentDeserializer", JsonDeserializer.None.class);
                    if (_verifyAsClass != null) {
                        jsonDeserializer = deserializationContext2.deserializerInstance(annotated2, _verifyAsClass);
                    }
                }
                if (jsonDeserializer != null) {
                    mapLikeType = mapLikeType.withContentValueHandler(jsonDeserializer);
                }
            }
        }
        return mapLikeType;
    }

    private Class<?> _verifyAsClass(Object obj, String str, Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        String str2 = str;
        Class<?> cls2 = cls;
        if (obj2 == null) {
            return null;
        }
        if (!(obj2 instanceof Class)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("AnnotationIntrospector.").append(str2).append("() returned value of type ").append(obj2.getClass().getName()).append(": expected type JsonSerializer or Class<JsonSerializer> instead").toString());
            throw th2;
        }
        Class<?> cls3 = (Class) obj2;
        if (cls3 == cls2 || cls3 == NoClass.class) {
            return null;
        }
        return cls3;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _handleUnknownValueDeserializer(JavaType javaType) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        JavaType javaType2 = javaType;
        if (!ClassUtil.isConcrete(javaType2.getRawClass())) {
            Throwable th3 = th2;
            new StringBuilder();
            new JsonMappingException(sb2.append("Can not find a Value deserializer for abstract type ").append(javaType2).toString());
            throw th3;
        }
        Throwable th4 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not find a Value deserializer for type ").append(javaType2).toString());
        throw th4;
    }

    /* access modifiers changed from: protected */
    public KeyDeserializer _handleUnknownKeyDeserializer(JavaType javaType) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not find a (Map) Key deserializer for type ").append(javaType).toString());
        throw th2;
    }
}
