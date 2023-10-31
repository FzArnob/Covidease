package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
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
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;

@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializer<EnumMap<? extends Enum<?>, ?>> implements ContextualSerializer {
    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(EnumMap.class, false);
        JavaType javaType2 = javaType;
        EnumValues enumValues2 = enumValues;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        this._property = null;
        this._staticTyping = z || (javaType2 != null && javaType2.isFinal());
        this._valueType = javaType2;
        this._keyEnums = enumValues2;
        this._valueTypeSerializer = typeSerializer2;
        this._valueSerializer = jsonSerializer2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EnumMapSerializer(com.shaded.fasterxml.jackson.databind.ser.std.EnumMapSerializer r7, com.shaded.fasterxml.jackson.databind.BeanProperty r8, com.shaded.fasterxml.jackson.databind.JsonSerializer<?> r9) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r0
            r5 = r1
            r4.<init>((com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer<?>) r5)
            r4 = r0
            r5 = r2
            r4._property = r5
            r4 = r0
            r5 = r1
            boolean r5 = r5._staticTyping
            r4._staticTyping = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r5 = r5._valueType
            r4._valueType = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.util.EnumValues r5 = r5._keyEnums
            r4._keyEnums = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer r5 = r5._valueTypeSerializer
            r4._valueTypeSerializer = r5
            r4 = r0
            r5 = r3
            r4._valueSerializer = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.EnumMapSerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.std.EnumMapSerializer, com.shaded.fasterxml.jackson.databind.BeanProperty, com.shaded.fasterxml.jackson.databind.JsonSerializer):void");
    }

    public EnumMapSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        EnumMapSerializer enumMapSerializer;
        new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, typeSerializer, this._valueSerializer);
        return enumMapSerializer;
    }

    public EnumMapSerializer withValueSerializer(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        EnumMapSerializer enumMapSerializer;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        if (this._property == beanProperty2 && jsonSerializer2 == this._valueSerializer) {
            return this;
        }
        new EnumMapSerializer(this, beanProperty2, jsonSerializer2);
        return enumMapSerializer;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedMember member;
        Object findContentSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> jsonSerializer = null;
        if (!(beanProperty2 == null || (member = beanProperty2.getMember()) == null || (findContentSerializer = serializerProvider2.getAnnotationIntrospector().findContentSerializer(member)) == null)) {
            jsonSerializer = serializerProvider2.serializerInstance(member, findContentSerializer);
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._valueSerializer;
        }
        JsonSerializer<?> findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider2, beanProperty2, jsonSerializer);
        if (findConvertingContentSerializer == null) {
            if (this._staticTyping) {
                return withValueSerializer(beanProperty2, serializerProvider2.findValueSerializer(this._valueType, beanProperty2));
            }
        } else if (this._valueSerializer instanceof ContextualSerializer) {
            findConvertingContentSerializer = ((ContextualSerializer) findConvertingContentSerializer).createContextual(serializerProvider2, beanProperty2);
        }
        if (findConvertingContentSerializer != this._valueSerializer) {
            return withValueSerializer(beanProperty2, findConvertingContentSerializer);
        }
        return this;
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    public boolean isEmpty(EnumMap<? extends Enum<?>, ?> enumMap) {
        EnumMap<? extends Enum<?>, ?> enumMap2 = enumMap;
        return enumMap2 == null || enumMap2.isEmpty();
    }

    public boolean hasSingleElement(EnumMap<? extends Enum<?>, ?> enumMap) {
        return enumMap.size() == 1;
    }

    public void serialize(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        EnumMap<? extends Enum<?>, ?> enumMap2 = enumMap;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator2.writeStartObject();
        if (!enumMap2.isEmpty()) {
            serializeContents(enumMap2, jsonGenerator2, serializerProvider2);
        }
        jsonGenerator2.writeEndObject();
    }

    public void serializeWithType(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        EnumMap<? extends Enum<?>, ?> enumMap2 = enumMap;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForObject(enumMap2, jsonGenerator2);
        if (!enumMap2.isEmpty()) {
            serializeContents(enumMap2, jsonGenerator2, serializerProvider2);
        }
        typeSerializer2.writeTypeSuffixForObject(enumMap2, jsonGenerator2);
    }

    /* access modifiers changed from: protected */
    public void serializeContents(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> findValueSerializer;
        EnumMap<? extends Enum<?>, ?> enumMap2 = enumMap;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._valueSerializer != null) {
            serializeContentsUsing(enumMap2, jsonGenerator2, serializerProvider2, this._valueSerializer);
            return;
        }
        JsonSerializer<Object> jsonSerializer = null;
        Class<?> cls = null;
        EnumValues enumValues = this._keyEnums;
        boolean z = !serializerProvider2.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        for (Map.Entry next : enumMap2.entrySet()) {
            Object value = next.getValue();
            if (!z || value != null) {
                Enum enumR = (Enum) next.getKey();
                if (enumValues == null) {
                    enumValues = ((EnumSerializer) ((StdSerializer) serializerProvider2.findValueSerializer((Class<?>) enumR.getDeclaringClass(), this._property))).getEnumValues();
                }
                jsonGenerator2.writeFieldName((SerializableString) enumValues.serializedValueFor(enumR));
                if (value == null) {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } else {
                    Class<?> cls2 = value.getClass();
                    if (cls2 == cls) {
                        findValueSerializer = jsonSerializer;
                    } else {
                        findValueSerializer = serializerProvider2.findValueSerializer(cls2, this._property);
                        jsonSerializer = findValueSerializer;
                        cls = cls2;
                    }
                    if (typeSerializer == null) {
                        try {
                            findValueSerializer.serialize(value, jsonGenerator2, serializerProvider2);
                        } catch (Exception e) {
                            wrapAndThrow(serializerProvider2, (Throwable) e, (Object) enumMap2, ((Enum) next.getKey()).name());
                        }
                    } else {
                        findValueSerializer.serializeWithType(value, jsonGenerator2, serializerProvider2, typeSerializer);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        EnumMap<? extends Enum<?>, ?> enumMap2 = enumMap;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        EnumValues enumValues = this._keyEnums;
        boolean z = !serializerProvider2.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        for (Map.Entry next : enumMap2.entrySet()) {
            Object value = next.getValue();
            if (!z || value != null) {
                Enum enumR = (Enum) next.getKey();
                if (enumValues == null) {
                    enumValues = ((EnumSerializer) ((StdSerializer) serializerProvider2.findValueSerializer((Class<?>) enumR.getDeclaringClass(), this._property))).getEnumValues();
                }
                jsonGenerator2.writeFieldName((SerializableString) enumValues.serializedValueFor(enumR));
                if (value == null) {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } else if (typeSerializer == null) {
                    try {
                        jsonSerializer2.serialize(value, jsonGenerator2, serializerProvider2);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider2, (Throwable) e, (Object) enumMap2, ((Enum) next.getKey()).name());
                    }
                } else {
                    jsonSerializer2.serializeWithType(value, jsonGenerator2, serializerProvider2, typeSerializer);
                }
            }
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        if (type2 instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type2).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                JavaType constructType = serializerProvider2.constructType(actualTypeArguments[0]);
                JavaType constructType2 = serializerProvider2.constructType(actualTypeArguments[1]);
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                Enum[] enumArr = (Enum[]) constructType.getRawClass().getEnumConstants();
                int length = enumArr.length;
                for (int i = 0; i < length; i++) {
                    Enum enumR = enumArr[i];
                    JsonSerializer<Object> findValueSerializer = serializerProvider2.findValueSerializer(constructType2.getRawClass(), this._property);
                    JsonNode put = objectNode.put(serializerProvider2.getConfig().getAnnotationIntrospector().findEnumValue(enumR), findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider2, (Type) null) : JsonSchema.getDefaultSchemaNode());
                }
                JsonNode put2 = createSchemaNode.put("properties", (JsonNode) objectNode);
            }
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonObjectFormatVisitor expectObjectFormat;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        if (jsonFormatVisitorWrapper2 != null && (expectObjectFormat = jsonFormatVisitorWrapper2.expectObjectFormat(javaType2)) != null) {
            JavaType containedType = javaType2.containedType(1);
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null && containedType != null) {
                jsonSerializer = jsonFormatVisitorWrapper2.getProvider().findValueSerializer(containedType, this._property);
            }
            if (containedType == null) {
                containedType = jsonFormatVisitorWrapper2.getProvider().constructType(Object.class);
            }
            EnumValues enumValues = this._keyEnums;
            if (enumValues == null) {
                JavaType containedType2 = javaType2.containedType(0);
                if (containedType2 == null) {
                    Throwable th3 = th2;
                    new StringBuilder();
                    new IllegalStateException(sb2.append("Can not resolve Enum type of EnumMap: ").append(javaType2).toString());
                    throw th3;
                }
                JsonSerializer<Object> findValueSerializer = containedType2 == null ? null : jsonFormatVisitorWrapper2.getProvider().findValueSerializer(containedType2, this._property);
                if (!(findValueSerializer instanceof EnumSerializer)) {
                    Throwable th4 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Can not resolve Enum type of EnumMap: ").append(javaType2).toString());
                    throw th4;
                }
                enumValues = ((EnumSerializer) findValueSerializer).getEnumValues();
            }
            for (Map.Entry next : enumValues.internalMap().entrySet()) {
                String value = ((SerializedString) next.getValue()).getValue();
                if (jsonSerializer == null) {
                    jsonSerializer = jsonFormatVisitorWrapper2.getProvider().findValueSerializer(next.getKey().getClass(), this._property);
                }
                expectObjectFormat.property(value, jsonSerializer, containedType);
            }
        }
    }
}
