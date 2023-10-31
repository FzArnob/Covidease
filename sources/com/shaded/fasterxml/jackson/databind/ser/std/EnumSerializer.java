package com.shaded.fasterxml.jackson.databind.ser.std;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.shaded.fasterxml.jackson.databind.node.ArrayNode;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer<Enum<?>> implements ContextualSerializer {
    protected final Boolean _serializeAsIndex;
    protected final EnumValues _values;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public EnumSerializer(EnumValues enumValues) {
        this(enumValues, (Boolean) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumSerializer(EnumValues enumValues, Boolean bool) {
        super(Enum.class, false);
        this._values = enumValues;
        this._serializeAsIndex = bool;
    }

    public static EnumSerializer construct(Class<Enum<?>> cls, SerializationConfig serializationConfig, BeanDescription beanDescription, JsonFormat.Value value) {
        EnumSerializer enumSerializer;
        Class<Enum<?>> cls2 = cls;
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        JsonFormat.Value value2 = value;
        AnnotationIntrospector annotationIntrospector = serializationConfig2.getAnnotationIntrospector();
        new EnumSerializer(serializationConfig2.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING) ? EnumValues.constructFromToString(cls2, annotationIntrospector) : EnumValues.constructFromName(cls2, annotationIntrospector), _isShapeWrittenUsingIndex(cls2, value2, true));
        return enumSerializer;
    }

    @Deprecated
    public static EnumSerializer construct(Class<Enum<?>> cls, SerializationConfig serializationConfig, BeanDescription beanDescription) {
        BeanDescription beanDescription2 = beanDescription;
        return construct(cls, serializationConfig, beanDescription2, beanDescription2.findExpectedFormat((JsonFormat.Value) null));
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value findFormat;
        Boolean _isShapeWrittenUsingIndex;
        JsonSerializer<?> jsonSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        if (beanProperty2 == null || (findFormat = serializerProvider2.getAnnotationIntrospector().findFormat((Annotated) beanProperty2.getMember())) == null || (_isShapeWrittenUsingIndex = _isShapeWrittenUsingIndex(beanProperty2.getType().getRawClass(), findFormat, false)) == this._serializeAsIndex) {
            return this;
        }
        new EnumSerializer(this._values, _isShapeWrittenUsingIndex);
        return jsonSerializer;
    }

    public EnumValues getEnumValues() {
        return this._values;
    }

    public final void serialize(Enum<?> enumR, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Enum<?> enumR2 = enumR;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (_serializeAsIndex(serializerProvider)) {
            jsonGenerator2.writeNumber(enumR2.ordinal());
        } else {
            jsonGenerator2.writeString((SerializableString) this._values.serializedValueFor(enumR2));
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        if (_serializeAsIndex(serializerProvider2)) {
            return createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, true);
        }
        ObjectNode createSchemaNode = createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_STRING, true);
        if (type2 != null && serializerProvider2.constructType(type2).isEnumType()) {
            ArrayNode putArray = createSchemaNode.putArray("enum");
            for (SerializedString value : this._values.values()) {
                ArrayNode add = putArray.add(value.getValue());
            }
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        Set set;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        if (jsonFormatVisitorWrapper2.getProvider().isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX)) {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper2.expectIntegerFormat(javaType2);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.INT);
                return;
            }
            return;
        }
        JsonStringFormatVisitor expectStringFormat = jsonFormatVisitorWrapper2.expectStringFormat(javaType2);
        if (javaType2 != null && expectStringFormat != null && javaType2.isEnumType()) {
            new LinkedHashSet();
            Set set2 = set;
            for (SerializedString value : this._values.values()) {
                boolean add = set2.add(value.getValue());
            }
            expectStringFormat.enumTypes(set2);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean _serializeAsIndex(SerializerProvider serializerProvider) {
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._serializeAsIndex != null) {
            return this._serializeAsIndex.booleanValue();
        }
        return serializerProvider2.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class<?> cls, JsonFormat.Value value, boolean z) {
        StringBuilder sb;
        Class<?> cls2 = cls;
        JsonFormat.Value value2 = value;
        boolean z2 = z;
        JsonFormat.Shape shape = value2 == null ? null : value2.getShape();
        if (shape == null) {
            return null;
        }
        if (shape == JsonFormat.Shape.ANY || shape == JsonFormat.Shape.SCALAR) {
            return null;
        }
        if (shape == JsonFormat.Shape.STRING) {
            return Boolean.FALSE;
        }
        if (shape.isNumeric()) {
            return Boolean.TRUE;
        }
        IllegalArgumentException illegalArgumentException = r8;
        new StringBuilder();
        IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException(sb.append("Unsupported serialization shape (").append(shape).append(") for Enum ").append(cls2.getName()).append(", not supported as ").append(z2 ? "class" : "property").append(" annotation").toString());
        throw illegalArgumentException;
    }
}
