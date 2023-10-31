package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

public class BeanPropertyWriter implements BeanProperty {
    public static final Object MARKER_FOR_EMPTY;
    protected final Method _accessorMethod;
    protected final JavaType _cfgSerializationType;
    protected final Annotations _contextAnnotations;
    protected final JavaType _declaredType;
    protected PropertySerializerMap _dynamicSerializers;
    protected final Field _field;
    protected final Class<?>[] _includeInViews;
    protected HashMap<Object, Object> _internalSettings;
    protected final boolean _isRequired;
    protected final AnnotatedMember _member;
    protected final SerializedString _name;
    protected JavaType _nonTrivialBaseType;
    protected JsonSerializer<Object> _nullSerializer;
    protected JsonSerializer<Object> _serializer;
    protected final boolean _suppressNulls;
    protected final Object _suppressableValue;
    protected TypeSerializer _typeSerializer;
    protected final PropertyName _wrapperName;

    static {
        Object obj;
        new Object();
        MARKER_FOR_EMPTY = obj;
    }

    public BeanPropertyWriter(BeanPropertyDefinition beanPropertyDefinition, AnnotatedMember annotatedMember, Annotations annotations, JavaType javaType, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, boolean z, Object obj) {
        SerializedString serializedString;
        Throwable th;
        StringBuilder sb;
        BeanPropertyDefinition beanPropertyDefinition2 = beanPropertyDefinition;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        TypeSerializer typeSerializer2 = typeSerializer;
        JavaType javaType3 = javaType2;
        boolean z2 = z;
        Object obj2 = obj;
        this._member = annotatedMember2;
        this._contextAnnotations = annotations;
        new SerializedString(beanPropertyDefinition2.getName());
        this._name = serializedString;
        this._wrapperName = beanPropertyDefinition2.getWrapperName();
        this._declaredType = javaType;
        this._serializer = jsonSerializer2;
        this._dynamicSerializers = jsonSerializer2 == null ? PropertySerializerMap.emptyMap() : null;
        this._typeSerializer = typeSerializer2;
        this._cfgSerializationType = javaType3;
        this._isRequired = beanPropertyDefinition2.isRequired();
        if (annotatedMember2 instanceof AnnotatedField) {
            this._accessorMethod = null;
            this._field = (Field) annotatedMember2.getMember();
        } else if (annotatedMember2 instanceof AnnotatedMethod) {
            this._accessorMethod = (Method) annotatedMember2.getMember();
            this._field = null;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can not pass member of type ").append(annotatedMember2.getClass().getName()).toString());
            throw th2;
        }
        this._suppressNulls = z2;
        this._suppressableValue = obj2;
        this._includeInViews = beanPropertyDefinition2.findViews();
        this._nullSerializer = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanPropertyWriter(com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            com.shaded.fasterxml.jackson.core.io.SerializedString r4 = r4._name
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter.<init>(com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter):void");
    }

    protected BeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, SerializedString serializedString) {
        HashMap<Object, Object> hashMap;
        BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
        this._name = serializedString;
        this._wrapperName = beanPropertyWriter2._wrapperName;
        this._member = beanPropertyWriter2._member;
        this._contextAnnotations = beanPropertyWriter2._contextAnnotations;
        this._declaredType = beanPropertyWriter2._declaredType;
        this._accessorMethod = beanPropertyWriter2._accessorMethod;
        this._field = beanPropertyWriter2._field;
        this._serializer = beanPropertyWriter2._serializer;
        this._nullSerializer = beanPropertyWriter2._nullSerializer;
        if (beanPropertyWriter2._internalSettings != null) {
            new HashMap<>(beanPropertyWriter2._internalSettings);
            this._internalSettings = hashMap;
        }
        this._cfgSerializationType = beanPropertyWriter2._cfgSerializationType;
        this._dynamicSerializers = beanPropertyWriter2._dynamicSerializers;
        this._suppressNulls = beanPropertyWriter2._suppressNulls;
        this._suppressableValue = beanPropertyWriter2._suppressableValue;
        this._includeInViews = beanPropertyWriter2._includeInViews;
        this._typeSerializer = beanPropertyWriter2._typeSerializer;
        this._nonTrivialBaseType = beanPropertyWriter2._nonTrivialBaseType;
        this._isRequired = beanPropertyWriter2._isRequired;
    }

    public BeanPropertyWriter rename(NameTransformer nameTransformer) {
        BeanPropertyWriter beanPropertyWriter;
        SerializedString serializedString;
        String transform = nameTransformer.transform(this._name.getValue());
        if (transform.equals(this._name.toString())) {
            return this;
        }
        new SerializedString(transform);
        new BeanPropertyWriter(this, serializedString);
        return beanPropertyWriter;
    }

    public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
        Throwable th;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        if (this._serializer == null || this._serializer == jsonSerializer2) {
            this._serializer = jsonSerializer2;
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("Can not override serializer");
        throw th2;
    }

    public void assignNullSerializer(JsonSerializer<Object> jsonSerializer) {
        Throwable th;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        if (this._nullSerializer == null || this._nullSerializer == jsonSerializer2) {
            this._nullSerializer = jsonSerializer2;
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("Can not override null serializer");
        throw th2;
    }

    public BeanPropertyWriter unwrappingWriter(NameTransformer nameTransformer) {
        BeanPropertyWriter beanPropertyWriter;
        new UnwrappingBeanPropertyWriter(this, nameTransformer);
        return beanPropertyWriter;
    }

    public void setNonTrivialBaseType(JavaType javaType) {
        JavaType javaType2 = javaType;
        this._nonTrivialBaseType = javaType2;
    }

    public String getName() {
        return this._name.getValue();
    }

    public JavaType getType() {
        return this._declaredType;
    }

    public PropertyName getWrapperName() {
        return this._wrapperName;
    }

    public boolean isRequired() {
        return this._isRequired;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._member.getAnnotation(cls);
    }

    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return this._contextAnnotations.get(cls);
    }

    public AnnotatedMember getMember() {
        return this._member;
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor) throws JsonMappingException {
        JsonObjectFormatVisitor jsonObjectFormatVisitor2 = jsonObjectFormatVisitor;
        if (jsonObjectFormatVisitor2 == null) {
            return;
        }
        if (isRequired()) {
            jsonObjectFormatVisitor2.property((BeanProperty) this);
        } else {
            jsonObjectFormatVisitor2.optionalProperty((BeanProperty) this);
        }
    }

    public Object getInternalSetting(Object obj) {
        Object obj2 = obj;
        if (this._internalSettings == null) {
            return null;
        }
        return this._internalSettings.get(obj2);
    }

    public Object setInternalSetting(Object obj, Object obj2) {
        HashMap<Object, Object> hashMap;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (this._internalSettings == null) {
            new HashMap<>();
            this._internalSettings = hashMap;
        }
        return this._internalSettings.put(obj3, obj4);
    }

    public Object removeInternalSetting(Object obj) {
        Object obj2 = obj;
        Object obj3 = null;
        if (this._internalSettings != null) {
            obj3 = this._internalSettings.remove(obj2);
            if (this._internalSettings.size() == 0) {
                this._internalSettings = null;
            }
        }
        return obj3;
    }

    public SerializedString getSerializedName() {
        return this._name;
    }

    public boolean hasSerializer() {
        return this._serializer != null;
    }

    public boolean hasNullSerializer() {
        return this._nullSerializer != null;
    }

    public boolean willSuppressNulls() {
        return this._suppressNulls;
    }

    public JsonSerializer<Object> getSerializer() {
        return this._serializer;
    }

    public JavaType getSerializationType() {
        return this._cfgSerializationType;
    }

    public Class<?> getRawSerializationType() {
        return this._cfgSerializationType == null ? null : this._cfgSerializationType.getRawClass();
    }

    public Class<?> getPropertyType() {
        if (this._accessorMethod != null) {
            return this._accessorMethod.getReturnType();
        }
        return this._field.getType();
    }

    public Type getGenericPropertyType() {
        if (this._accessorMethod != null) {
            return this._accessorMethod.getGenericReturnType();
        }
        return this._field.getGenericType();
    }

    public Class<?>[] getViews() {
        return this._includeInViews;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public boolean isRequired(AnnotationIntrospector annotationIntrospector) {
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        return this._isRequired;
    }

    public void depositSchemaProperty(ObjectNode objectNode, SerializerProvider serializerProvider) throws JsonMappingException {
        Type rawClass;
        JsonNode defaultSchemaNode;
        ObjectNode objectNode2 = objectNode;
        SerializerProvider serializerProvider2 = serializerProvider;
        JavaType serializationType = getSerializationType();
        if (serializationType == null) {
            rawClass = getGenericPropertyType();
        } else {
            rawClass = serializationType.getRawClass();
        }
        Type type = rawClass;
        JsonSerializer<Object> serializer = getSerializer();
        if (serializer == null) {
            Class<?> rawSerializationType = getRawSerializationType();
            if (rawSerializationType == null) {
                rawSerializationType = getPropertyType();
            }
            serializer = serializerProvider2.findValueSerializer(rawSerializationType, (BeanProperty) this);
        }
        boolean z = !isRequired();
        if (serializer instanceof SchemaAware) {
            defaultSchemaNode = ((SchemaAware) serializer).getSchema(serializerProvider2, type, z);
        } else {
            defaultSchemaNode = JsonSchema.getDefaultSchemaNode();
        }
        JsonNode put = objectNode2.put(getName(), defaultSchemaNode);
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        Object obj3 = get(obj2);
        if (obj3 != null) {
            JsonSerializer<Object> jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class<?> cls = obj3.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(obj3)) {
                        return;
                    }
                } else if (this._suppressableValue.equals(obj3)) {
                    return;
                }
            }
            if (obj3 == obj2) {
                _handleSelfReference(obj2, jsonSerializer);
            }
            jsonGenerator2.writeFieldName((SerializableString) this._name);
            if (this._typeSerializer == null) {
                jsonSerializer.serialize(obj3, jsonGenerator2, serializerProvider2);
            } else {
                jsonSerializer.serializeWithType(obj3, jsonGenerator2, serializerProvider2, this._typeSerializer);
            }
        } else if (this._nullSerializer != null) {
            jsonGenerator2.writeFieldName((SerializableString) this._name);
            this._nullSerializer.serialize(null, jsonGenerator2, serializerProvider2);
        }
    }

    public void serializeAsColumn(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        Object obj3 = get(obj2);
        if (obj3 != null) {
            JsonSerializer<Object> jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class<?> cls = obj3.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(obj3)) {
                        serializeAsPlaceholder(obj2, jsonGenerator2, serializerProvider2);
                        return;
                    }
                } else if (this._suppressableValue.equals(obj3)) {
                    serializeAsPlaceholder(obj2, jsonGenerator2, serializerProvider2);
                    return;
                }
            }
            if (obj3 == obj2) {
                _handleSelfReference(obj2, jsonSerializer);
            }
            if (this._typeSerializer == null) {
                jsonSerializer.serialize(obj3, jsonGenerator2, serializerProvider2);
            } else {
                jsonSerializer.serializeWithType(obj3, jsonGenerator2, serializerProvider2, this._typeSerializer);
            }
        } else if (this._nullSerializer != null) {
            this._nullSerializer.serialize(null, jsonGenerator2, serializerProvider2);
        } else {
            jsonGenerator2.writeNull();
        }
    }

    public void serializeAsPlaceholder(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._nullSerializer != null) {
            this._nullSerializer.serialize(null, jsonGenerator2, serializerProvider2);
        } else {
            jsonGenerator2.writeNull();
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer;
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        Class<?> cls2 = cls;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._nonTrivialBaseType != null) {
            findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(serializerProvider2.constructSpecializedType(this._nonTrivialBaseType, cls2), serializerProvider2, (BeanProperty) this);
        } else {
            findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(cls2, serializerProvider2, (BeanProperty) this);
        }
        if (propertySerializerMap2 != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    public final Object get(Object obj) throws Exception {
        Object obj2 = obj;
        if (this._accessorMethod != null) {
            return this._accessorMethod.invoke(obj2, new Object[0]);
        }
        return this._field.get(obj2);
    }

    /* access modifiers changed from: protected */
    public void _handleSelfReference(Object obj, JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        Throwable th;
        Object obj2 = obj;
        if (!jsonSerializer.usesObjectId()) {
            Throwable th2 = th;
            new JsonMappingException("Direct self-reference leading to cycle");
            throw th2;
        }
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        new StringBuilder(40);
        StringBuilder sb3 = sb;
        StringBuilder append = sb3.append("property '").append(getName()).append("' (");
        if (this._accessorMethod != null) {
            StringBuilder append2 = sb3.append("via method ").append(this._accessorMethod.getDeclaringClass().getName()).append("#").append(this._accessorMethod.getName());
        } else {
            StringBuilder append3 = sb3.append("field \"").append(this._field.getDeclaringClass().getName()).append("#").append(this._field.getName());
        }
        if (this._serializer == null) {
            StringBuilder append4 = sb3.append(", no static serializer");
        } else {
            new StringBuilder();
            StringBuilder append5 = sb3.append(sb2.append(", static serializer of type ").append(this._serializer.getClass().getName()).toString());
        }
        StringBuilder append6 = sb3.append(')');
        return sb3.toString();
    }
}
