package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.FilterProvider;
import com.shaded.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.shaded.fasterxml.jackson.databind.ser.SerializerCache;
import com.shaded.fasterxml.jackson.databind.ser.SerializerFactory;
import com.shaded.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.shaded.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.shaded.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SerializerProvider extends DatabindContext {
    protected static final boolean CACHE_UNKNOWN_MAPPINGS = false;
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER;
    public static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER;
    protected static final JavaType TYPE_OBJECT = TypeFactory.defaultInstance().uncheckedSimpleType(Object.class);
    protected final SerializationConfig _config;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final Class<?> _serializationView;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected JsonSerializer<Object> _unknownTypeSerializer;

    public abstract WritableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator);

    public abstract JsonSerializer<Object> serializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    static {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> jsonSerializer2;
        new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
        DEFAULT_NULL_KEY_SERIALIZER = jsonSerializer;
        new UnknownSerializer();
        DEFAULT_UNKNOWN_SERIALIZER = jsonSerializer2;
    }

    public SerializerProvider() {
        SerializerCache serializerCache;
        RootNameLookup rootNameLookup;
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._config = null;
        this._serializerFactory = null;
        new SerializerCache();
        this._serializerCache = serializerCache;
        this._knownSerializers = null;
        new RootNameLookup();
        this._rootNames = rootNameLookup;
        this._serializationView = null;
    }

    protected SerializerProvider(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        Throwable th;
        SerializerProvider serializerProvider2 = serializerProvider;
        SerializationConfig serializationConfig2 = serializationConfig;
        SerializerFactory serializerFactory2 = serializerFactory;
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig2 == null) {
            Throwable th2 = th;
            new NullPointerException();
            throw th2;
        }
        this._serializerFactory = serializerFactory2;
        this._config = serializationConfig2;
        this._serializerCache = serializerProvider2._serializerCache;
        this._unknownTypeSerializer = serializerProvider2._unknownTypeSerializer;
        this._keySerializer = serializerProvider2._keySerializer;
        this._nullValueSerializer = serializerProvider2._nullValueSerializer;
        this._nullKeySerializer = serializerProvider2._nullKeySerializer;
        this._rootNames = serializerProvider2._rootNames;
        this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
        this._serializationView = serializationConfig2.getActiveView();
    }

    public void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer) {
        Throwable th;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        if (jsonSerializer2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null JsonSerializer");
            throw th2;
        }
        this._keySerializer = jsonSerializer2;
    }

    public void setNullValueSerializer(JsonSerializer<Object> jsonSerializer) {
        Throwable th;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        if (jsonSerializer2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null JsonSerializer");
            throw th2;
        }
        this._nullValueSerializer = jsonSerializer2;
    }

    public void setNullKeySerializer(JsonSerializer<Object> jsonSerializer) {
        Throwable th;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        if (jsonSerializer2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null JsonSerializer");
            throw th2;
        }
        this._nullKeySerializer = jsonSerializer2;
    }

    public final SerializationConfig getConfig() {
        return this._config;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public final Class<?> getActiveView() {
        return this._serializationView;
    }

    @Deprecated
    public final Class<?> getSerializationView() {
        return this._serializationView;
    }

    public final boolean isEnabled(SerializationFeature serializationFeature) {
        return this._config.isEnabled(serializationFeature);
    }

    public final FilterProvider getFilterProvider() {
        return this._config.getFilterProvider();
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        Class<?> cls2 = cls;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls2);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer(cls2);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls2));
                if (untypedValueSerializer == null) {
                    untypedValueSerializer = _createAndCacheUntypedSerializer(cls2);
                    if (untypedValueSerializer == null) {
                        return getUnknownTypeSerializer(cls2);
                    }
                }
            }
        }
        return _handleContextual(untypedValueSerializer, beanProperty2);
    }

    public JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JavaType javaType2 = javaType;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType2);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType2);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = _createAndCacheUntypedSerializer(javaType2);
                if (untypedValueSerializer == null) {
                    return getUnknownTypeSerializer(javaType2.getRawClass());
                }
            }
        }
        return _handleContextual(untypedValueSerializer, beanProperty2);
    }

    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer;
        Class<?> cls2 = cls;
        boolean z2 = z;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(cls2);
        if (typedValueSerializer != null) {
            return typedValueSerializer;
        }
        JsonSerializer<Object> typedValueSerializer2 = this._serializerCache.typedValueSerializer(cls2);
        if (typedValueSerializer2 != null) {
            return typedValueSerializer2;
        }
        JsonSerializer<Object> findValueSerializer = findValueSerializer(cls2, beanProperty2);
        TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(cls2));
        if (createTypeSerializer != null) {
            new TypeWrappedSerializer(createTypeSerializer.forProperty(beanProperty2), findValueSerializer);
            findValueSerializer = jsonSerializer;
        }
        if (z2) {
            this._serializerCache.addTypedSerializer(cls2, findValueSerializer);
        }
        return findValueSerializer;
    }

    public JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer;
        JavaType javaType2 = javaType;
        boolean z2 = z;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(javaType2);
        if (typedValueSerializer != null) {
            return typedValueSerializer;
        }
        JsonSerializer<Object> typedValueSerializer2 = this._serializerCache.typedValueSerializer(javaType2);
        if (typedValueSerializer2 != null) {
            return typedValueSerializer2;
        }
        JsonSerializer<Object> findValueSerializer = findValueSerializer(javaType2, beanProperty2);
        TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, javaType2);
        if (createTypeSerializer != null) {
            new TypeWrappedSerializer(createTypeSerializer.forProperty(beanProperty2), findValueSerializer);
            findValueSerializer = jsonSerializer;
        }
        if (z2) {
            this._serializerCache.addTypedSerializer(javaType2, findValueSerializer);
        }
        return findValueSerializer;
    }

    public JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return _handleContextualResolvable(this._serializerFactory.createKeySerializer(this._config, javaType, this._keySerializer), beanProperty);
    }

    public JsonSerializer<Object> getDefaultNullKeySerializer() {
        return this._nullKeySerializer;
    }

    public JsonSerializer<Object> getDefaultNullValueSerializer() {
        return this._nullValueSerializer;
    }

    public JsonSerializer<Object> findNullKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JavaType javaType2 = javaType;
        BeanProperty beanProperty2 = beanProperty;
        return getDefaultNullKeySerializer();
    }

    public JsonSerializer<Object> findNullValueSerializer(BeanProperty beanProperty) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        return getDefaultNullValueSerializer();
    }

    public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls) {
        Class<?> cls2 = cls;
        return this._unknownTypeSerializer;
    }

    public final void defaultSerializeValue(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (obj2 == null) {
            getDefaultNullValueSerializer().serialize(null, jsonGenerator2, this);
            return;
        }
        findTypedValueSerializer(obj2.getClass(), true, (BeanProperty) null).serialize(obj2, jsonGenerator2, this);
    }

    public final void defaultSerializeField(String str, Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeFieldName(str);
        if (obj2 == null) {
            getDefaultNullValueSerializer().serialize(null, jsonGenerator2, this);
            return;
        }
        findTypedValueSerializer(obj2.getClass(), true, (BeanProperty) null).serialize(obj2, jsonGenerator2, this);
    }

    public final void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Date date;
        long j2 = j;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator2.writeNumber(j2);
            return;
        }
        new Date(j2);
        jsonGenerator2.writeString(_dateFormat().format(date));
    }

    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Date date2 = date;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator2.writeNumber(date2.getTime());
        } else {
            jsonGenerator2.writeString(_dateFormat().format(date2));
        }
    }

    public void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Date date;
        long j2 = j;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator2.writeFieldName(String.valueOf(j2));
            return;
        }
        new Date(j2);
        jsonGenerator2.writeFieldName(_dateFormat().format(date));
    }

    public void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Date date2 = date;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator2.writeFieldName(String.valueOf(date2.getTime()));
        } else {
            jsonGenerator2.writeFieldName(_dateFormat().format(date2));
        }
    }

    public final void defaultSerializeNull(JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        getDefaultNullValueSerializer().serialize(null, jsonGenerator, this);
    }

    /* access modifiers changed from: protected */
    public void _reportIncompatibleRootType(Object obj, JavaType javaType) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        JavaType javaType2 = javaType;
        if (!javaType2.isPrimitive() || !ClassUtil.wrapperType(javaType2.getRawClass()).isAssignableFrom(obj2.getClass())) {
            Throwable th2 = th;
            new StringBuilder();
            new JsonMappingException(sb.append("Incompatible types: declared root type (").append(javaType2).append(") vs ").append(obj2.getClass().getName()).toString());
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> cls) throws JsonMappingException {
        Class<?> cls2 = cls;
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls2);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        JsonSerializer<Object> untypedValueSerializer2 = this._serializerCache.untypedValueSerializer(cls2);
        if (untypedValueSerializer2 != null) {
            return untypedValueSerializer2;
        }
        return _createAndCacheUntypedSerializer(cls2);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> cls) throws JsonMappingException {
        Throwable th;
        Class<?> cls2 = cls;
        try {
            JsonSerializer<Object> _createUntypedSerializer = _createUntypedSerializer(this._config.constructType(cls2));
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(cls2, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            IllegalArgumentException illegalArgumentException = e;
            Throwable th2 = th;
            new JsonMappingException(illegalArgumentException.getMessage(), (JsonLocation) null, illegalArgumentException);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType javaType) throws JsonMappingException {
        Throwable th;
        JavaType javaType2 = javaType;
        try {
            JsonSerializer<Object> _createUntypedSerializer = _createUntypedSerializer(javaType2);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType2, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            IllegalArgumentException illegalArgumentException = e;
            Throwable th2 = th;
            new JsonMappingException(illegalArgumentException.getMessage(), (JsonLocation) null, illegalArgumentException);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _createUntypedSerializer(JavaType javaType) throws JsonMappingException {
        return this._serializerFactory.createSerializer(this, javaType);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        BeanProperty beanProperty2 = beanProperty;
        if (jsonSerializer2 instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer2).resolve(this);
        }
        return _handleContextual(jsonSerializer2, beanProperty2);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _handleResolvable(JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        if (jsonSerializer2 instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer2).resolve(this);
        }
        return jsonSerializer2;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _handleContextual(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        BeanProperty beanProperty2 = beanProperty;
        if (jsonSerializer2 instanceof ContextualSerializer) {
            jsonSerializer2 = ((ContextualSerializer) jsonSerializer2).createContextual(this, beanProperty2);
        }
        return jsonSerializer2;
    }

    /* access modifiers changed from: protected */
    public final DateFormat _dateFormat() {
        if (this._dateFormat != null) {
            return this._dateFormat;
        }
        DateFormat dateFormat = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat;
        return dateFormat;
    }
}
