package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ObjectArraySerializer extends ArraySerializerBase<Object[]> implements ContextualSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectArraySerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(Object[].class, (BeanProperty) null);
        this._elementType = javaType;
        this._staticTyping = z;
        this._valueTypeSerializer = typeSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyMap();
        this._elementSerializer = jsonSerializer;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ObjectArraySerializer(com.shaded.fasterxml.jackson.databind.ser.std.ObjectArraySerializer r6, com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>((com.shaded.fasterxml.jackson.databind.ser.std.ArraySerializerBase<?>) r4)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._elementType
            r3._elementType = r4
            r3 = r0
            r4 = r2
            r3._valueTypeSerializer = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._staticTyping
            r3._staticTyping = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap r4 = r4._dynamicSerializers
            r3._dynamicSerializers = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r4._elementSerializer
            r3._elementSerializer = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.ObjectArraySerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.std.ObjectArraySerializer, com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ObjectArraySerializer(com.shaded.fasterxml.jackson.databind.ser.std.ObjectArraySerializer r9, com.shaded.fasterxml.jackson.databind.BeanProperty r10, com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer r11, com.shaded.fasterxml.jackson.databind.JsonSerializer<?> r12) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>((com.shaded.fasterxml.jackson.databind.ser.std.ArraySerializerBase<?>) r6, (com.shaded.fasterxml.jackson.databind.BeanProperty) r7)
            r5 = r0
            r6 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r6 = r6._elementType
            r5._elementType = r6
            r5 = r0
            r6 = r3
            r5._valueTypeSerializer = r6
            r5 = r0
            r6 = r1
            boolean r6 = r6._staticTyping
            r5._staticTyping = r6
            r5 = r0
            r6 = r1
            com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap r6 = r6._dynamicSerializers
            r5._dynamicSerializers = r6
            r5 = r0
            r6 = r4
            r5._elementSerializer = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.ObjectArraySerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.std.ObjectArraySerializer, com.shaded.fasterxml.jackson.databind.BeanProperty, com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer, com.shaded.fasterxml.jackson.databind.JsonSerializer):void");
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        ContainerSerializer<?> containerSerializer;
        new ObjectArraySerializer(this._elementType, this._staticTyping, typeSerializer, this._elementSerializer);
        return containerSerializer;
    }

    public ObjectArraySerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        ObjectArraySerializer objectArraySerializer;
        BeanProperty beanProperty2 = beanProperty;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        if (this._property == beanProperty2 && jsonSerializer2 == this._elementSerializer && this._valueTypeSerializer == typeSerializer2) {
            return this;
        }
        new ObjectArraySerializer(this, beanProperty2, typeSerializer2, jsonSerializer2);
        return objectArraySerializer;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedMember member;
        Object findContentSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        if (typeSerializer != null) {
            typeSerializer = typeSerializer.forProperty(beanProperty2);
        }
        JsonSerializer<Object> jsonSerializer = null;
        if (!(beanProperty2 == null || (member = beanProperty2.getMember()) == null || (findContentSerializer = serializerProvider2.getAnnotationIntrospector().findContentSerializer(member)) == null)) {
            jsonSerializer = serializerProvider2.serializerInstance(member, findContentSerializer);
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._elementSerializer;
        }
        JsonSerializer<Object> findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider2, beanProperty2, jsonSerializer);
        if (findConvertingContentSerializer == null) {
            if (this._elementType != null && (this._staticTyping || hasContentTypeAnnotation(serializerProvider2, beanProperty2))) {
                findConvertingContentSerializer = serializerProvider2.findValueSerializer(this._elementType, beanProperty2);
            }
        } else if (findConvertingContentSerializer instanceof ContextualSerializer) {
            findConvertingContentSerializer = ((ContextualSerializer) findConvertingContentSerializer).createContextual(serializerProvider2, beanProperty2);
        }
        return withResolved(beanProperty2, typeSerializer, findConvertingContentSerializer);
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    public boolean isEmpty(Object[] objArr) {
        Object[] objArr2 = objArr;
        return objArr2 == null || objArr2.length == 0;
    }

    public boolean hasSingleElement(Object[] objArr) {
        return objArr.length == 1;
    }

    public void serializeContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Throwable th;
        Object[] objArr2 = objArr;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        int length = objArr2.length;
        if (length != 0) {
            if (this._elementSerializer != null) {
                serializeContentsUsing(objArr2, jsonGenerator2, serializerProvider2, this._elementSerializer);
            } else if (this._valueTypeSerializer != null) {
                serializeTypedContents(objArr2, jsonGenerator2, serializerProvider2);
            } else {
                int i = 0;
                Object obj = null;
                try {
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < length) {
                        obj = objArr2[i];
                        if (obj == null) {
                            serializerProvider2.defaultSerializeNull(jsonGenerator2);
                        } else {
                            Class<?> cls = obj.getClass();
                            JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                            if (serializerFor == null) {
                                if (this._elementType.hasGenericTypes()) {
                                    serializerFor = _findAndAddDynamic(propertySerializerMap, serializerProvider2.constructSpecializedType(this._elementType, cls), serializerProvider2);
                                } else {
                                    serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                                }
                            }
                            serializerFor.serialize(obj, jsonGenerator2, serializerProvider2);
                        }
                        i++;
                    }
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    Throwable th2 = e2;
                    while (true) {
                        th = th2;
                        if ((th instanceof InvocationTargetException) && th.getCause() != null) {
                            th2 = th.getCause();
                        }
                    }
                    if (th instanceof Error) {
                        throw ((Error) th);
                    }
                    throw JsonMappingException.wrapWithPath(th, obj, i);
                }
            }
        }
    }

    public void serializeContentsUsing(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        Throwable th;
        Object[] objArr2 = objArr;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        int length = objArr2.length;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        int i = 0;
        Object obj = null;
        while (i < length) {
            try {
                obj = objArr2[i];
                if (obj == null) {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } else if (typeSerializer == null) {
                    jsonSerializer2.serialize(obj, jsonGenerator2, serializerProvider2);
                } else {
                    jsonSerializer2.serializeWithType(obj, jsonGenerator2, serializerProvider2, typeSerializer);
                }
                i++;
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                Throwable th2 = e2;
                while (true) {
                    th = th2;
                    if ((th instanceof InvocationTargetException) && th.getCause() != null) {
                        th2 = th.getCause();
                    }
                }
                if (th instanceof Error) {
                    throw ((Error) th);
                }
                throw JsonMappingException.wrapWithPath(th, obj, i);
            }
        }
    }

    public void serializeTypedContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Throwable th;
        Object[] objArr2 = objArr;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        int length = objArr2.length;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        try {
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            for (int i = 0; i < length; i++) {
                Object obj = objArr2[i];
                if (obj == null) {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } else {
                    Class<?> cls = obj.getClass();
                    JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                    }
                    serializerFor.serializeWithType(obj, jsonGenerator2, serializerProvider2, typeSerializer);
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            Throwable th2 = e2;
            while (true) {
                th = th2;
                if ((th instanceof InvocationTargetException) && th.getCause() != null) {
                    th2 = th.getCause();
                }
            }
            if (th instanceof Error) {
                throw ((Error) th);
            }
            throw JsonMappingException.wrapWithPath(th, (Object) null, 0);
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        if (type2 != null) {
            JavaType constructType = serializerProvider2.constructType(type2);
            if (constructType.isArrayType()) {
                Class<?> rawClass = ((ArrayType) constructType).getContentType().getRawClass();
                if (rawClass == Object.class) {
                    JsonNode put = createSchemaNode.put("items", JsonSchema.getDefaultSchemaNode());
                } else {
                    JsonSerializer<Object> findValueSerializer = serializerProvider2.findValueSerializer(rawClass, this._property);
                    JsonNode put2 = createSchemaNode.put("items", findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider2, (Type) null) : JsonSchema.getDefaultSchemaNode());
                }
            }
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        Throwable th;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2);
        if (expectArrayFormat != null) {
            JavaType moreSpecificType = jsonFormatVisitorWrapper2.getProvider().getTypeFactory().moreSpecificType(this._elementType, javaType2.getContentType());
            if (moreSpecificType == null) {
                Throwable th2 = th;
                new JsonMappingException("Could not resolve type");
                throw th2;
            }
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = jsonFormatVisitorWrapper2.getProvider().findValueSerializer(moreSpecificType, this._property);
            }
            expectArrayFormat.itemsFormat(jsonSerializer, moreSpecificType);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(cls, serializerProvider, this._property);
        if (propertySerializerMap2 != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap2 != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }
}
