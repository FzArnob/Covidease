package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializer<Map<?, ?>> implements ContextualSerializer {
    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final HashSet<String> _ignoredEntries;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2) {
        super(Map.class, false);
        this._ignoredEntries = hashSet;
        this._keyType = javaType;
        this._valueType = javaType2;
        this._valueTypeIsStatic = z;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyMap();
        this._property = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MapSerializer(MapSerializer mapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet) {
        super(Map.class, false);
        MapSerializer mapSerializer2 = mapSerializer;
        this._ignoredEntries = hashSet;
        this._keyType = mapSerializer2._keyType;
        this._valueType = mapSerializer2._valueType;
        this._valueTypeIsStatic = mapSerializer2._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer2._valueTypeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = mapSerializer2._dynamicValueSerializers;
        this._property = beanProperty;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MapSerializer(MapSerializer mapSerializer, TypeSerializer typeSerializer) {
        super(Map.class, false);
        MapSerializer mapSerializer2 = mapSerializer;
        this._ignoredEntries = mapSerializer2._ignoredEntries;
        this._keyType = mapSerializer2._keyType;
        this._valueType = mapSerializer2._valueType;
        this._valueTypeIsStatic = mapSerializer2._valueTypeIsStatic;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = mapSerializer2._keySerializer;
        this._valueSerializer = mapSerializer2._valueSerializer;
        this._dynamicValueSerializers = mapSerializer2._dynamicValueSerializers;
        this._property = mapSerializer2._property;
    }

    public MapSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        MapSerializer mapSerializer;
        new MapSerializer(this, typeSerializer);
        return mapSerializer;
    }

    public MapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet) {
        MapSerializer mapSerializer;
        new MapSerializer(this, beanProperty, jsonSerializer, jsonSerializer2, hashSet);
        return mapSerializer;
    }

    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        JavaType keyType;
        JavaType contentType;
        MapSerializer mapSerializer;
        JavaType javaType2 = javaType;
        boolean z2 = z;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer3 = jsonSerializer;
        JsonSerializer<Object> jsonSerializer4 = jsonSerializer2;
        HashSet<String> set = toSet(strArr);
        if (javaType2 == null) {
            JavaType javaType3 = UNSPECIFIED_TYPE;
            contentType = javaType3;
            keyType = javaType3;
        } else {
            keyType = javaType2.getKeyType();
            contentType = javaType2.getContentType();
        }
        if (!z2) {
            z2 = contentType != null && contentType.isFinal();
        }
        new MapSerializer(set, keyType, contentType, z2, typeSerializer2, jsonSerializer3, jsonSerializer4);
        return mapSerializer;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.HashSet<java.lang.String> toSet(java.lang.String[] r10) {
        /*
            r0 = r10
            r6 = r0
            if (r6 == 0) goto L_0x0008
            r6 = r0
            int r6 = r6.length
            if (r6 != 0) goto L_0x000b
        L_0x0008:
            r6 = 0
            r0 = r6
        L_0x000a:
            return r0
        L_0x000b:
            java.util.HashSet r6 = new java.util.HashSet
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            int r8 = r8.length
            r7.<init>(r8)
            r1 = r6
            r6 = r0
            r2 = r6
            r6 = r2
            int r6 = r6.length
            r3 = r6
            r6 = 0
            r4 = r6
        L_0x001d:
            r6 = r4
            r7 = r3
            if (r6 >= r7) goto L_0x002f
            r6 = r2
            r7 = r4
            r6 = r6[r7]
            r5 = r6
            r6 = r1
            r7 = r5
            boolean r6 = r6.add(r7)
            int r4 = r4 + 1
            goto L_0x001d
        L_0x002f:
            r6 = r1
            r0 = r6
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.MapSerializer.toSet(java.lang.String[]):java.util.HashSet");
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        String[] findPropertiesToIgnore;
        HashSet<String> hashSet;
        HashSet<String> hashSet2;
        HashSet<String> hashSet3;
        AnnotatedMember member;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> jsonSerializer = null;
        JsonSerializer<?> jsonSerializer2 = null;
        if (!(beanProperty2 == null || (member = beanProperty2.getMember()) == null)) {
            AnnotationIntrospector annotationIntrospector = serializerProvider2.getAnnotationIntrospector();
            Object findKeySerializer = annotationIntrospector.findKeySerializer(member);
            if (findKeySerializer != null) {
                jsonSerializer2 = serializerProvider2.serializerInstance(member, findKeySerializer);
            }
            Object findContentSerializer = annotationIntrospector.findContentSerializer(member);
            if (findContentSerializer != null) {
                jsonSerializer = serializerProvider2.serializerInstance(member, findContentSerializer);
            }
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._valueSerializer;
        }
        JsonSerializer<Object> findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider2, beanProperty2, jsonSerializer);
        if (findConvertingContentSerializer == null) {
            if (this._valueTypeIsStatic || hasContentTypeAnnotation(serializerProvider2, beanProperty2)) {
                findConvertingContentSerializer = serializerProvider2.findValueSerializer(this._valueType, beanProperty2);
            }
        } else if (findConvertingContentSerializer instanceof ContextualSerializer) {
            findConvertingContentSerializer = ((ContextualSerializer) findConvertingContentSerializer).createContextual(serializerProvider2, beanProperty2);
        }
        if (jsonSerializer2 == null) {
            jsonSerializer2 = this._keySerializer;
        }
        if (jsonSerializer2 == null) {
            jsonSerializer2 = serializerProvider2.findKeySerializer(this._keyType, beanProperty2);
        } else if (jsonSerializer2 instanceof ContextualSerializer) {
            jsonSerializer2 = ((ContextualSerializer) jsonSerializer2).createContextual(serializerProvider2, beanProperty2);
        }
        HashSet<String> hashSet4 = this._ignoredEntries;
        AnnotationIntrospector annotationIntrospector2 = serializerProvider2.getAnnotationIntrospector();
        if (!(annotationIntrospector2 == null || beanProperty2 == null || (findPropertiesToIgnore = annotationIntrospector2.findPropertiesToIgnore(beanProperty2.getMember())) == null)) {
            if (hashSet4 == null) {
                hashSet2 = hashSet3;
                new HashSet<>();
            } else {
                hashSet2 = hashSet;
                new HashSet<>(hashSet4);
            }
            hashSet4 = hashSet2;
            String[] strArr = findPropertiesToIgnore;
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                boolean add = hashSet4.add(strArr[i]);
            }
        }
        return withResolved(beanProperty2, jsonSerializer2, findConvertingContentSerializer, hashSet4);
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    public boolean isEmpty(Map<?, ?> map) {
        Map<?, ?> map2 = map;
        return map2 == null || map2.isEmpty();
    }

    public boolean hasSingleElement(Map<?, ?> map) {
        return map.size() == 1;
    }

    public JsonSerializer<?> getKeySerializer() {
        return this._keySerializer;
    }

    public void serialize(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Map<?, ?> map2 = map;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator2.writeStartObject();
        if (!map2.isEmpty()) {
            if (serializerProvider2.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map2 = _orderEntries(map2);
            }
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map2, jsonGenerator2, serializerProvider2, this._valueSerializer);
            } else {
                serializeFields(map2, jsonGenerator2, serializerProvider2);
            }
        }
        jsonGenerator2.writeEndObject();
    }

    public void serializeWithType(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        Map<?, ?> map2 = map;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForObject(map2, jsonGenerator2);
        if (!map2.isEmpty()) {
            if (serializerProvider2.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map2 = _orderEntries(map2);
            }
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map2, jsonGenerator2, serializerProvider2, this._valueSerializer);
            } else {
                serializeFields(map2, jsonGenerator2, serializerProvider2);
            }
        }
        typeSerializer2.writeTypeSuffixForObject(map2, jsonGenerator2);
    }

    public void serializeFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        StringBuilder sb;
        Map<?, ?> map2 = map;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map2, jsonGenerator2, serializerProvider2);
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        boolean z = !serializerProvider2.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Map.Entry next : map2.entrySet()) {
            Object value = next.getValue();
            Object key = next.getKey();
            if (key == null) {
                serializerProvider2.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator2, serializerProvider2);
            } else if ((!z || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator2, serializerProvider2);
            }
            if (value == null) {
                serializerProvider2.defaultSerializeNull(jsonGenerator2);
            } else {
                Class<?> cls = value.getClass();
                JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                if (serializerFor == null) {
                    if (this._valueType.hasGenericTypes()) {
                        serializerFor = _findAndAddDynamic(propertySerializerMap, serializerProvider2.constructSpecializedType(this._valueType, cls), serializerProvider2);
                    } else {
                        serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                    }
                    propertySerializerMap = this._dynamicValueSerializers;
                }
                try {
                    serializerFor.serialize(value, jsonGenerator2, serializerProvider2);
                } catch (Exception e) {
                    new StringBuilder();
                    wrapAndThrow(serializerProvider2, (Throwable) e, (Object) map2, sb.append("").append(key).toString());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeFieldsUsing(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        StringBuilder sb;
        Map<?, ?> map2 = map;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        JsonSerializer<Object> jsonSerializer3 = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        boolean z = !serializerProvider2.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        for (Map.Entry next : map2.entrySet()) {
            Object value = next.getValue();
            Object key = next.getKey();
            if (key == null) {
                serializerProvider2.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator2, serializerProvider2);
            } else if ((!z || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer3.serialize(key, jsonGenerator2, serializerProvider2);
            }
            if (value == null) {
                serializerProvider2.defaultSerializeNull(jsonGenerator2);
            } else if (typeSerializer == null) {
                try {
                    jsonSerializer2.serialize(value, jsonGenerator2, serializerProvider2);
                } catch (Exception e) {
                    new StringBuilder();
                    wrapAndThrow(serializerProvider2, (Throwable) e, (Object) map2, sb.append("").append(key).toString());
                }
            } else {
                jsonSerializer2.serializeWithType(value, jsonGenerator2, serializerProvider2, typeSerializer);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeTypedFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> findValueSerializer;
        StringBuilder sb;
        Map<?, ?> map2 = map;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer = this._keySerializer;
        JsonSerializer<Object> jsonSerializer2 = null;
        Class<?> cls = null;
        HashSet<String> hashSet = this._ignoredEntries;
        boolean z = !serializerProvider2.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        for (Map.Entry next : map2.entrySet()) {
            Object value = next.getValue();
            Object key = next.getKey();
            if (key == null) {
                serializerProvider2.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator2, serializerProvider2);
            } else if ((!z || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator2, serializerProvider2);
            }
            if (value == null) {
                serializerProvider2.defaultSerializeNull(jsonGenerator2);
            } else {
                Class<?> cls2 = value.getClass();
                if (cls2 == cls) {
                    findValueSerializer = jsonSerializer2;
                } else {
                    findValueSerializer = serializerProvider2.findValueSerializer(cls2, this._property);
                    jsonSerializer2 = findValueSerializer;
                    cls = cls2;
                }
                try {
                    findValueSerializer.serializeWithType(value, jsonGenerator2, serializerProvider2, this._valueTypeSerializer);
                } catch (Exception e) {
                    new StringBuilder();
                    wrapAndThrow(serializerProvider2, (Throwable) e, (Object) map2, sb.append("").append(key).toString());
                }
            }
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        return createSchemaNode("object", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JsonMapFormatVisitor expectMapFormat = jsonFormatVisitorWrapper2 == null ? null : jsonFormatVisitorWrapper2.expectMapFormat(javaType);
        if (expectMapFormat != null) {
            expectMapFormat.keyFormat(this._keySerializer, this._keyType);
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findAndAddDynamic(this._dynamicValueSerializers, this._valueType, jsonFormatVisitorWrapper2.getProvider());
            }
            expectMapFormat.valueFormat(jsonSerializer, this._valueType);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(cls, serializerProvider, this._property);
        if (propertySerializerMap2 != findAndAddSerializer.map) {
            this._dynamicValueSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap2 != findAndAddSerializer.map) {
            this._dynamicValueSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public Map<?, ?> _orderEntries(Map<?, ?> map) {
        Map<?, ?> map2;
        Map<?, ?> map3 = map;
        if (map3 instanceof SortedMap) {
            return map3;
        }
        new TreeMap(map3);
        return map2;
    }
}
