package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

@JacksonStdImpl
public class JsonValueSerializer extends StdSerializer<Object> implements ContextualSerializer, JsonFormatVisitable, SchemaAware {
    protected final Method _accessorMethod;
    protected final boolean _forceTypeInformation;
    protected final BeanProperty _property;
    protected final JsonSerializer<Object> _valueSerializer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonValueSerializer(Method method, JsonSerializer<Object> jsonSerializer) {
        super(Object.class);
        this._accessorMethod = method;
        this._valueSerializer = jsonSerializer;
        this._property = null;
        this._forceTypeInformation = true;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JsonValueSerializer(com.shaded.fasterxml.jackson.databind.ser.std.JsonValueSerializer r8, com.shaded.fasterxml.jackson.databind.BeanProperty r9, com.shaded.fasterxml.jackson.databind.JsonSerializer<?> r10, boolean r11) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r0
            r6 = r1
            java.lang.Class r6 = r6.handledType()
            java.lang.Class r6 = _notNullClass(r6)
            r5.<init>(r6)
            r5 = r0
            r6 = r1
            java.lang.reflect.Method r6 = r6._accessorMethod
            r5._accessorMethod = r6
            r5 = r0
            r6 = r3
            r5._valueSerializer = r6
            r5 = r0
            r6 = r2
            r5._property = r6
            r5 = r0
            r6 = r4
            r5._forceTypeInformation = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.JsonValueSerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.std.JsonValueSerializer, com.shaded.fasterxml.jackson.databind.BeanProperty, com.shaded.fasterxml.jackson.databind.JsonSerializer, boolean):void");
    }

    private static final Class<Object> _notNullClass(Class<?> cls) {
        Class cls2 = cls;
        return cls2 == null ? Object.class : cls2;
    }

    public JsonValueSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, boolean z) {
        JsonValueSerializer jsonValueSerializer;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        boolean z2 = z;
        if (this._property == beanProperty2 && this._valueSerializer == jsonSerializer2 && z2 == this._forceTypeInformation) {
            return this;
        }
        new JsonValueSerializer(this, beanProperty2, jsonSerializer2, z2);
        return jsonValueSerializer;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer == null) {
            if (serializerProvider2.isEnabled(MapperFeature.USE_STATIC_TYPING) || Modifier.isFinal(this._accessorMethod.getReturnType().getModifiers())) {
                JavaType constructType = serializerProvider2.constructType(this._accessorMethod.getGenericReturnType());
                JsonSerializer<Object> findTypedValueSerializer = serializerProvider2.findTypedValueSerializer(constructType, false, this._property);
                return withResolved(beanProperty2, findTypedValueSerializer, isNaturalTypeWithStdHandling(constructType.getRawClass(), findTypedValueSerializer));
            }
        } else if (jsonSerializer instanceof ContextualSerializer) {
            return withResolved(beanProperty2, ((ContextualSerializer) jsonSerializer).createContextual(serializerProvider2, beanProperty2), this._forceTypeInformation);
        }
        return this;
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        try {
            Object invoke = this._accessorMethod.invoke(obj2, new Object[0]);
            if (invoke == null) {
                serializerProvider2.defaultSerializeNull(jsonGenerator2);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider2.findTypedValueSerializer(invoke.getClass(), true, this._property);
            }
            jsonSerializer.serialize(invoke, jsonGenerator2, serializerProvider2);
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
            new StringBuilder();
            throw JsonMappingException.wrapWithPath(th, obj2, sb.append(this._accessorMethod.getName()).append("()").toString());
        }
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        try {
            Object invoke = this._accessorMethod.invoke(obj2, new Object[0]);
            if (invoke == null) {
                serializerProvider2.defaultSerializeNull(jsonGenerator2);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider2.findValueSerializer(invoke.getClass(), this._property);
            } else if (this._forceTypeInformation) {
                typeSerializer2.writeTypePrefixForScalar(obj2, jsonGenerator2);
                jsonSerializer.serialize(invoke, jsonGenerator2, serializerProvider2);
                typeSerializer2.writeTypeSuffixForScalar(obj2, jsonGenerator2);
                return;
            }
            jsonSerializer.serializeWithType(invoke, jsonGenerator2, serializerProvider2, typeSerializer2);
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
            new StringBuilder();
            throw JsonMappingException.wrapWithPath(th, obj2, sb.append(this._accessorMethod.getName()).append("()").toString());
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        Type type2 = type;
        return this._valueSerializer instanceof SchemaAware ? ((SchemaAware) this._valueSerializer).getSchema(serializerProvider, (Type) null) : JsonSchema.getDefaultSchemaNode();
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        if (this._valueSerializer != null) {
            this._valueSerializer.acceptJsonFormatVisitor(jsonFormatVisitorWrapper2, (JavaType) null);
        } else {
            JsonAnyFormatVisitor expectAnyFormat = jsonFormatVisitorWrapper2.expectAnyFormat(javaType2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isNaturalTypeWithStdHandling(Class<?> cls, JsonSerializer<?> jsonSerializer) {
        Class<?> cls2 = cls;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        if (cls2.isPrimitive()) {
            if (!(cls2 == Integer.TYPE || cls2 == Boolean.TYPE || cls2 == Double.TYPE)) {
                return false;
            }
        } else if (!(cls2 == String.class || cls2 == Integer.class || cls2 == Boolean.class || cls2 == Double.class)) {
            return false;
        }
        return isDefaultSerializer(jsonSerializer2);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("(@JsonValue serializer for method ").append(this._accessorMethod.getDeclaringClass()).append("#").append(this._accessorMethod.getName()).append(")").toString();
    }
}
