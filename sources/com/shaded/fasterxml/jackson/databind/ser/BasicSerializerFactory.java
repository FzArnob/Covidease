package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonSerializable;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.shaded.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.EnumMapSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.InetAddressSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.shaded.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.SqlTimeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.shaded.fasterxml.jackson.databind.ser.std.StdContainerSerializers;
import com.shaded.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.StdJdkSerializers;
import com.shaded.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.shaded.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import com.shaded.fasterxml.jackson.databind.util.EnumValues;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TimeZone;

public abstract class BasicSerializerFactory extends SerializerFactory implements Serializable {
    protected static final HashMap<String, JsonSerializer<?>> _concrete;
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy;
    protected final SerializerFactoryConfig _factoryConfig;

    public abstract JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType) throws JsonMappingException;

    /* access modifiers changed from: protected */
    public abstract Iterable<Serializers> customSerializers();

    public abstract SerializerFactory withConfig(SerializerFactoryConfig serializerFactoryConfig);

    static {
        HashMap<String, JsonSerializer<?>> hashMap;
        HashMap<String, Class<? extends JsonSerializer<?>>> hashMap2;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Throwable th;
        StringBuilder sb;
        new HashMap<>();
        _concrete = hashMap;
        new HashMap<>();
        _concreteLazy = hashMap2;
        new StringSerializer();
        JsonSerializer<?> put = _concrete.put(String.class.getName(), obj);
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        JsonSerializer<?> put2 = _concrete.put(StringBuffer.class.getName(), toStringSerializer);
        JsonSerializer<?> put3 = _concrete.put(StringBuilder.class.getName(), toStringSerializer);
        JsonSerializer<?> put4 = _concrete.put(Character.class.getName(), toStringSerializer);
        JsonSerializer<?> put5 = _concrete.put(Character.TYPE.getName(), toStringSerializer);
        NumberSerializers.addAll(_concrete);
        new BooleanSerializer(true);
        JsonSerializer<?> put6 = _concrete.put(Boolean.TYPE.getName(), obj2);
        new BooleanSerializer(false);
        JsonSerializer<?> put7 = _concrete.put(Boolean.class.getName(), obj3);
        new NumberSerializers.NumberSerializer();
        Object obj5 = obj4;
        JsonSerializer<?> put8 = _concrete.put(BigInteger.class.getName(), obj5);
        JsonSerializer<?> put9 = _concrete.put(BigDecimal.class.getName(), obj5);
        JsonSerializer<?> put10 = _concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
        DateSerializer dateSerializer = DateSerializer.instance;
        JsonSerializer<?> put11 = _concrete.put(Date.class.getName(), dateSerializer);
        JsonSerializer<?> put12 = _concrete.put(Timestamp.class.getName(), dateSerializer);
        Class<? extends JsonSerializer<?>> put13 = _concreteLazy.put(java.sql.Date.class.getName(), SqlDateSerializer.class);
        Class<? extends JsonSerializer<?>> put14 = _concreteLazy.put(Time.class.getName(), SqlTimeSerializer.class);
        for (Map.Entry next : StdJdkSerializers.all()) {
            Object value = next.getValue();
            if (value instanceof JsonSerializer) {
                JsonSerializer<?> put15 = _concrete.put(((Class) next.getKey()).getName(), (JsonSerializer) value);
            } else if (value instanceof Class) {
                Class<? extends JsonSerializer<?>> put16 = _concreteLazy.put(((Class) next.getKey()).getName(), (Class) value);
            } else {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Internal error: unrecognized value of type ").append(next.getClass().getName()).toString());
                throw th2;
            }
        }
        Class<? extends JsonSerializer<?>> put17 = _concreteLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
    }

    protected BasicSerializerFactory(SerializerFactoryConfig serializerFactoryConfig) {
        SerializerFactoryConfig serializerFactoryConfig2;
        SerializerFactoryConfig serializerFactoryConfig3;
        SerializerFactoryConfig serializerFactoryConfig4 = serializerFactoryConfig;
        if (serializerFactoryConfig4 == null) {
            serializerFactoryConfig2 = serializerFactoryConfig3;
            new SerializerFactoryConfig();
        } else {
            serializerFactoryConfig2 = serializerFactoryConfig4;
        }
        this._factoryConfig = serializerFactoryConfig2;
    }

    public SerializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public final SerializerFactory withAdditionalSerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalSerializers(serializers));
    }

    public final SerializerFactory withAdditionalKeySerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalKeySerializers(serializers));
    }

    public final SerializerFactory withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        return withConfig(this._factoryConfig.withSerializerModifier(beanSerializerModifier));
    }

    public JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, JsonSerializer<Object> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        BeanDescription introspectClassAnnotations = serializationConfig2.introspectClassAnnotations(javaType2.getRawClass());
        JsonSerializer<?> jsonSerializer3 = null;
        if (this._factoryConfig.hasKeySerializers()) {
            for (Serializers findSerializer : this._factoryConfig.keySerializers()) {
                jsonSerializer3 = findSerializer.findSerializer(serializationConfig2, javaType2, introspectClassAnnotations);
                if (jsonSerializer3 != null) {
                    break;
                }
            }
        }
        if (jsonSerializer3 == null) {
            jsonSerializer3 = jsonSerializer2;
            if (jsonSerializer3 == null) {
                jsonSerializer3 = StdKeySerializers.getStdKeySerializer(javaType2);
            }
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyKeySerializer : this._factoryConfig.serializerModifiers()) {
                jsonSerializer3 = modifyKeySerializer.modifyKeySerializer(serializationConfig2, javaType2, introspectClassAnnotations, jsonSerializer3);
            }
        }
        return jsonSerializer3;
    }

    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        AnnotatedClass classInfo = serializationConfig2.introspectClassAnnotations(javaType2.getRawClass()).getClassInfo();
        AnnotationIntrospector annotationIntrospector = serializationConfig2.getAnnotationIntrospector();
        TypeResolverBuilder<?> findTypeResolver = annotationIntrospector.findTypeResolver(serializationConfig2, classInfo, javaType2);
        Collection<NamedType> collection = null;
        if (findTypeResolver == null) {
            findTypeResolver = serializationConfig2.getDefaultTyper(javaType2);
        } else {
            collection = serializationConfig2.getSubtypeResolver().collectAndResolveSubtypes(classInfo, (MapperConfig<?>) serializationConfig2, annotationIntrospector);
        }
        if (findTypeResolver == null) {
            return null;
        }
        return findTypeResolver.buildTypeSerializer(serializationConfig2, javaType2, collection);
    }

    public final JsonSerializer<?> getNullSerializer() {
        return NullSerializer.instance;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByLookup(JavaType javaType, SerializationConfig serializationConfig, BeanDescription beanDescription, boolean z) {
        Class cls;
        Throwable th;
        StringBuilder sb;
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        String name = javaType.getRawClass().getName();
        JsonSerializer<?> jsonSerializer = _concrete.get(name);
        if (jsonSerializer != null || (cls = _concreteLazy.get(name)) == null) {
            return jsonSerializer;
        }
        try {
            return (JsonSerializer) cls.newInstance();
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Failed to instantiate standard serializer (of type ").append(cls.getName()).append("): ").append(exc.getMessage()).toString(), exc);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByAnnotations(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanDescription beanDescription2 = beanDescription;
        if (JsonSerializable.class.isAssignableFrom(javaType.getRawClass())) {
            return SerializableSerializer.instance;
        }
        AnnotatedMethod findJsonValueMethod = beanDescription2.findJsonValueMethod();
        if (findJsonValueMethod == null) {
            return null;
        }
        Method annotated = findJsonValueMethod.getAnnotated();
        if (serializerProvider2.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotated);
        }
        new JsonValueSerializer(annotated, findSerializerFromAnnotation(serializerProvider2, findJsonValueMethod));
        return jsonSerializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByPrimaryType(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        Class<?> rawClass = javaType2.getRawClass();
        if (InetAddress.class.isAssignableFrom(rawClass)) {
            return InetAddressSerializer.instance;
        }
        if (TimeZone.class.isAssignableFrom(rawClass)) {
            return TimeZoneSerializer.instance;
        }
        if (Charset.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        }
        JsonSerializer<?> findOptionalStdSerializer = findOptionalStdSerializer(serializerProvider2, javaType2, beanDescription2, z2);
        if (findOptionalStdSerializer != null) {
            return findOptionalStdSerializer;
        }
        if (Number.class.isAssignableFrom(rawClass)) {
            return NumberSerializers.NumberSerializer.instance;
        }
        if (Enum.class.isAssignableFrom(rawClass)) {
            return buildEnumSerializer(serializerProvider2.getConfig(), javaType2, beanDescription2);
        }
        if (Calendar.class.isAssignableFrom(rawClass)) {
            return CalendarSerializer.instance;
        }
        if (Date.class.isAssignableFrom(rawClass)) {
            return DateSerializer.instance;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findOptionalStdSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        boolean z2 = z;
        return OptionalHandlerFactory.instance.findSerializer(serializerProvider.getConfig(), javaType, beanDescription);
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        Class<?> rawClass = javaType2.getRawClass();
        if (Iterator.class.isAssignableFrom(rawClass)) {
            return buildIteratorSerializer(serializationConfig2, javaType2, beanDescription2, z2);
        }
        if (Iterable.class.isAssignableFrom(rawClass)) {
            return buildIterableSerializer(serializationConfig2, javaType2, beanDescription2, z2);
        }
        if (CharSequence.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> findSerializerFromAnnotation(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Annotated annotated2 = annotated;
        Object findSerializer = serializerProvider2.getAnnotationIntrospector().findSerializer(annotated2);
        if (findSerializer == null) {
            return null;
        }
        return findConvertingSerializer(serializerProvider2, annotated2, serializerProvider2.serializerInstance(annotated2, findSerializer));
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findConvertingSerializer(SerializerProvider serializerProvider, Annotated annotated, JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer2;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<?> jsonSerializer3 = jsonSerializer;
        Converter<Object, Object> findConverter = findConverter(serializerProvider2, annotated);
        if (findConverter == null) {
            return jsonSerializer3;
        }
        new StdDelegatingSerializer(findConverter, findConverter.getOutputType(serializerProvider2.getTypeFactory()), jsonSerializer3);
        return jsonSerializer2;
    }

    /* access modifiers changed from: protected */
    public Converter<Object, Object> findConverter(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Annotated annotated2 = annotated;
        Object findSerializationConverter = serializerProvider2.getAnnotationIntrospector().findSerializationConverter(annotated2);
        if (findSerializationConverter == null) {
            return null;
        }
        return serializerProvider2.converterInstance(annotated2, findSerializationConverter);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final JsonSerializer<?> buildContainerSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty, boolean z) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        return buildContainerSerializer(serializerProvider, javaType, beanDescription, z);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildContainerSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        SerializationConfig config = serializerProvider2.getConfig();
        if (!z2 && javaType2.useStaticType() && (!javaType2.isContainerType() || javaType2.getContentType().getRawClass() != Object.class)) {
            z2 = true;
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(config, javaType2.getContentType());
        if (createTypeSerializer != null) {
            z2 = false;
        }
        JsonSerializer<Object> _findContentSerializer = _findContentSerializer(serializerProvider2, beanDescription2.getClassInfo());
        if (javaType2.isMapLikeType()) {
            MapLikeType mapLikeType = (MapLikeType) javaType2;
            JsonSerializer<Object> _findKeySerializer = _findKeySerializer(serializerProvider2, beanDescription2.getClassInfo());
            if (mapLikeType.isTrueMapType()) {
                return buildMapSerializer(config, (MapType) mapLikeType, beanDescription2, z2, _findKeySerializer, createTypeSerializer, _findContentSerializer);
            }
            for (Serializers findMapLikeSerializer : customSerializers()) {
                MapLikeType mapLikeType2 = (MapLikeType) javaType2;
                JsonSerializer<?> findMapLikeSerializer2 = findMapLikeSerializer.findMapLikeSerializer(config, mapLikeType2, beanDescription2, _findKeySerializer, createTypeSerializer, _findContentSerializer);
                if (findMapLikeSerializer2 != null) {
                    if (this._factoryConfig.hasSerializerModifiers()) {
                        for (BeanSerializerModifier modifyMapLikeSerializer : this._factoryConfig.serializerModifiers()) {
                            findMapLikeSerializer2 = modifyMapLikeSerializer.modifyMapLikeSerializer(config, mapLikeType2, beanDescription2, findMapLikeSerializer2);
                        }
                    }
                    return findMapLikeSerializer2;
                }
            }
            return null;
        } else if (javaType2.isCollectionLikeType()) {
            CollectionLikeType collectionLikeType = (CollectionLikeType) javaType2;
            if (collectionLikeType.isTrueCollectionType()) {
                return buildCollectionSerializer(config, (CollectionType) collectionLikeType, beanDescription2, z2, createTypeSerializer, _findContentSerializer);
            }
            CollectionLikeType collectionLikeType2 = (CollectionLikeType) javaType2;
            for (Serializers findCollectionLikeSerializer : customSerializers()) {
                JsonSerializer<?> findCollectionLikeSerializer2 = findCollectionLikeSerializer.findCollectionLikeSerializer(config, collectionLikeType2, beanDescription2, createTypeSerializer, _findContentSerializer);
                if (findCollectionLikeSerializer2 != null) {
                    if (this._factoryConfig.hasSerializerModifiers()) {
                        for (BeanSerializerModifier modifyCollectionLikeSerializer : this._factoryConfig.serializerModifiers()) {
                            findCollectionLikeSerializer2 = modifyCollectionLikeSerializer.modifyCollectionLikeSerializer(config, collectionLikeType2, beanDescription2, findCollectionLikeSerializer2);
                        }
                    }
                    return findCollectionLikeSerializer2;
                }
            }
            return null;
        } else if (javaType2.isArrayType()) {
            return buildArraySerializer(config, (ArrayType) javaType2, beanDescription2, z2, createTypeSerializer, _findContentSerializer);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        return buildCollectionSerializer(serializationConfig, collectionType, beanDescription, z, typeSerializer, jsonSerializer);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) throws JsonMappingException {
        SerializationConfig serializationConfig2 = serializationConfig;
        CollectionType collectionType2 = collectionType;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        IndexedStringListSerializer indexedStringListSerializer = null;
        for (Serializers findCollectionSerializer : customSerializers()) {
            indexedStringListSerializer = findCollectionSerializer.findCollectionSerializer(serializationConfig2, collectionType2, beanDescription2, typeSerializer2, jsonSerializer2);
            if (indexedStringListSerializer != null) {
                break;
            }
        }
        if (indexedStringListSerializer == null) {
            JsonFormat.Value findExpectedFormat = beanDescription2.findExpectedFormat((JsonFormat.Value) null);
            if (findExpectedFormat != null && findExpectedFormat.getShape() == JsonFormat.Shape.OBJECT) {
                return null;
            }
            Class<?> rawClass = collectionType2.getRawClass();
            if (EnumSet.class.isAssignableFrom(rawClass)) {
                JavaType contentType = collectionType2.getContentType();
                if (!contentType.isEnumType()) {
                    contentType = null;
                }
                indexedStringListSerializer = StdContainerSerializers.enumSetSerializer(contentType);
            } else {
                Class<?> rawClass2 = collectionType2.getContentType().getRawClass();
                if (isIndexedList(rawClass)) {
                    if (rawClass2 != String.class) {
                        indexedStringListSerializer = StdContainerSerializers.indexedListSerializer(collectionType2.getContentType(), z2, typeSerializer2, jsonSerializer2);
                    } else if (jsonSerializer2 == null || ClassUtil.isJacksonStdImpl((Object) jsonSerializer2)) {
                        indexedStringListSerializer = IndexedStringListSerializer.instance;
                    }
                } else if (rawClass2 == String.class && (jsonSerializer2 == null || ClassUtil.isJacksonStdImpl((Object) jsonSerializer2))) {
                    indexedStringListSerializer = StringCollectionSerializer.instance;
                }
                if (indexedStringListSerializer == null) {
                    indexedStringListSerializer = StdContainerSerializers.collectionSerializer(collectionType2.getContentType(), z2, typeSerializer2, jsonSerializer2);
                }
            }
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyCollectionSerializer : this._factoryConfig.serializerModifiers()) {
                indexedStringListSerializer = modifyCollectionSerializer.modifyCollectionSerializer(serializationConfig2, collectionType2, beanDescription2, indexedStringListSerializer);
            }
        }
        return indexedStringListSerializer;
    }

    /* access modifiers changed from: protected */
    public boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer3;
        SerializationConfig serializationConfig2 = serializationConfig;
        MapType mapType2 = mapType;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        JsonSerializer<Object> jsonSerializer4 = jsonSerializer;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer5 = jsonSerializer2;
        MapSerializer mapSerializer = null;
        for (Serializers findMapSerializer : customSerializers()) {
            mapSerializer = findMapSerializer.findMapSerializer(serializationConfig2, mapType2, beanDescription2, jsonSerializer4, typeSerializer2, jsonSerializer5);
            if (mapSerializer != null) {
                break;
            }
        }
        if (mapSerializer == null) {
            if (EnumMap.class.isAssignableFrom(mapType2.getRawClass())) {
                JavaType keyType = mapType2.getKeyType();
                EnumValues enumValues = null;
                if (keyType.isEnumType()) {
                    enumValues = EnumValues.construct(keyType.getRawClass(), serializationConfig2.getAnnotationIntrospector());
                }
                new EnumMapSerializer(mapType2.getContentType(), z2, enumValues, typeSerializer2, jsonSerializer5);
                mapSerializer = jsonSerializer3;
            } else {
                mapSerializer = MapSerializer.construct(serializationConfig2.getAnnotationIntrospector().findPropertiesToIgnore(beanDescription2.getClassInfo()), mapType2, z2, typeSerializer2, jsonSerializer4, jsonSerializer5);
            }
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyMapSerializer : this._factoryConfig.serializerModifiers()) {
                mapSerializer = modifyMapSerializer.modifyMapSerializer(serializationConfig2, mapType2, beanDescription2, mapSerializer);
            }
        }
        return mapSerializer;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer2;
        SerializationConfig serializationConfig2 = serializationConfig;
        ArrayType arrayType2 = arrayType;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer3 = jsonSerializer;
        StringArraySerializer stringArraySerializer = null;
        for (Serializers findArraySerializer : customSerializers()) {
            stringArraySerializer = findArraySerializer.findArraySerializer(serializationConfig2, arrayType2, beanDescription2, typeSerializer2, jsonSerializer3);
            if (stringArraySerializer != null) {
                break;
            }
        }
        if (stringArraySerializer == null) {
            Class<?> rawClass = arrayType2.getRawClass();
            if (jsonSerializer3 == null || ClassUtil.isJacksonStdImpl((Object) jsonSerializer3)) {
                if (String[].class == rawClass) {
                    stringArraySerializer = StringArraySerializer.instance;
                } else {
                    stringArraySerializer = StdArraySerializers.findStandardImpl(rawClass);
                }
            }
            if (stringArraySerializer == null) {
                new ObjectArraySerializer(arrayType2.getContentType(), z2, typeSerializer2, jsonSerializer3);
                stringArraySerializer = jsonSerializer2;
            }
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyArraySerializer : this._factoryConfig.serializerModifiers()) {
                stringArraySerializer = modifyArraySerializer.modifyArraySerializer(serializationConfig2, arrayType2, beanDescription2, stringArraySerializer);
            }
        }
        return stringArraySerializer;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig2, containedType);
        return StdContainerSerializers.iteratorSerializer(containedType, usesStaticTyping(serializationConfig2, beanDescription2, createTypeSerializer), createTypeSerializer);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig2, containedType);
        return StdContainerSerializers.iterableSerializer(containedType, usesStaticTyping(serializationConfig2, beanDescription2, createTypeSerializer), createTypeSerializer);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildEnumSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        JsonFormat.Value findExpectedFormat = beanDescription2.findExpectedFormat((JsonFormat.Value) null);
        if (findExpectedFormat == null || findExpectedFormat.getShape() != JsonFormat.Shape.OBJECT) {
            JsonSerializer construct = EnumSerializer.construct(javaType2.getRawClass(), serializationConfig2, beanDescription2, findExpectedFormat);
            if (this._factoryConfig.hasSerializerModifiers()) {
                for (BeanSerializerModifier modifyEnumSerializer : this._factoryConfig.serializerModifiers()) {
                    construct = modifyEnumSerializer.modifyEnumSerializer(serializationConfig2, javaType2, beanDescription2, construct);
                }
            }
            return construct;
        }
        boolean removeProperty = ((BasicBeanDescription) beanDescription2).removeProperty("declaringClass");
        return null;
    }

    /* access modifiers changed from: protected */
    public <T extends JavaType> T modifyTypeByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        Throwable th;
        StringBuilder sb;
        SerializationConfig serializationConfig2 = serializationConfig;
        Annotated annotated2 = annotated;
        T t2 = t;
        Class<?> findSerializationType = serializationConfig2.getAnnotationIntrospector().findSerializationType(annotated2);
        if (findSerializationType != null) {
            try {
                t2 = t2.widenBy(findSerializationType);
            } catch (IllegalArgumentException e) {
                IllegalArgumentException illegalArgumentException = e;
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Failed to widen type ").append(t2).append(" with concrete-type annotation (value ").append(findSerializationType.getName()).append("), method '").append(annotated2.getName()).append("': ").append(illegalArgumentException.getMessage()).toString());
                throw th2;
            }
        }
        return modifySecondaryTypesByAnnotation(serializationConfig2, annotated2, t2);
    }

    protected static <T extends JavaType> T modifySecondaryTypesByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Annotated annotated2 = annotated;
        T t2 = t;
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        if (t2.isContainerType()) {
            Class<?> findSerializationKeyType = annotationIntrospector.findSerializationKeyType(annotated2, t2.getKeyType());
            if (findSerializationKeyType != null) {
                if (!(t2 instanceof MapType)) {
                    Throwable th4 = th3;
                    new StringBuilder();
                    new IllegalArgumentException(sb3.append("Illegal key-type annotation: type ").append(t2).append(" is not a Map type").toString());
                    throw th4;
                }
                try {
                    t2 = ((MapType) t2).widenKey(findSerializationKeyType);
                } catch (IllegalArgumentException e) {
                    IllegalArgumentException illegalArgumentException = e;
                    Throwable th5 = th2;
                    new StringBuilder();
                    new IllegalArgumentException(sb2.append("Failed to narrow key type ").append(t2).append(" with key-type annotation (").append(findSerializationKeyType.getName()).append("): ").append(illegalArgumentException.getMessage()).toString());
                    throw th5;
                }
            }
            Class<?> findSerializationContentType = annotationIntrospector.findSerializationContentType(annotated2, t2.getContentType());
            if (findSerializationContentType != null) {
                try {
                    t2 = t2.widenContentsBy(findSerializationContentType);
                } catch (IllegalArgumentException e2) {
                    IllegalArgumentException illegalArgumentException2 = e2;
                    Throwable th6 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Failed to narrow content type ").append(t2).append(" with content-type annotation (").append(findSerializationContentType.getName()).append("): ").append(illegalArgumentException2.getMessage()).toString());
                    throw th6;
                }
            }
        }
        return t2;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findKeySerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Annotated annotated2 = annotated;
        Object findKeySerializer = serializerProvider2.getAnnotationIntrospector().findKeySerializer(annotated2);
        if (findKeySerializer != null) {
            return serializerProvider2.serializerInstance(annotated2, findKeySerializer);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findContentSerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Annotated annotated2 = annotated;
        Object findContentSerializer = serializerProvider2.getAnnotationIntrospector().findContentSerializer(annotated2);
        if (findContentSerializer != null) {
            return serializerProvider2.serializerInstance(annotated2, findContentSerializer);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final boolean usesStaticTyping(SerializationConfig serializationConfig, BeanDescription beanDescription, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        BeanProperty beanProperty2 = beanProperty;
        return usesStaticTyping(serializationConfig, beanDescription, typeSerializer);
    }

    /* access modifiers changed from: protected */
    public boolean usesStaticTyping(SerializationConfig serializationConfig, BeanDescription beanDescription, TypeSerializer typeSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        if (typeSerializer != null) {
            return false;
        }
        JsonSerialize.Typing findSerializationTyping = serializationConfig2.getAnnotationIntrospector().findSerializationTyping(beanDescription2.getClassInfo());
        if (findSerializationTyping == null) {
            return serializationConfig2.isEnabled(MapperFeature.USE_STATIC_TYPING);
        }
        return findSerializationTyping == JsonSerialize.Typing.STATIC;
    }

    /* access modifiers changed from: protected */
    public Class<?> _verifyAsClass(Object obj, String str, Class<?> cls) {
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
}
