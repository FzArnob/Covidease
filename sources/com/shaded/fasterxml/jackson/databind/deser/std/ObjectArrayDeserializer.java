package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final ArrayType _arrayType;
    protected final Class<?> _elementClass;
    protected JsonDeserializer<Object> _elementDeserializer;
    protected final TypeDeserializer _elementTypeDeserializer;
    protected final boolean _untyped;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectArrayDeserializer(ArrayType arrayType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(Object[].class);
        ArrayType arrayType2 = arrayType;
        JsonDeserializer<Object> jsonDeserializer2 = jsonDeserializer;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        this._arrayType = arrayType2;
        this._elementClass = arrayType2.getContentType().getRawClass();
        this._untyped = this._elementClass == Object.class;
        this._elementDeserializer = jsonDeserializer2;
        this._elementTypeDeserializer = typeDeserializer2;
    }

    public ObjectArrayDeserializer withDeserializer(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        ObjectArrayDeserializer objectArrayDeserializer;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (jsonDeserializer2 == this._elementDeserializer && typeDeserializer2 == this._elementTypeDeserializer) {
            return this;
        }
        new ObjectArrayDeserializer(this._arrayType, jsonDeserializer2, typeDeserializer2);
        return objectArrayDeserializer;
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<Object> findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext2, beanProperty2, this._elementDeserializer);
        if (findConvertingContentDeserializer == null) {
            findConvertingContentDeserializer = deserializationContext2.findContextualValueDeserializer(this._arrayType.getContentType(), beanProperty2);
        } else if (findConvertingContentDeserializer instanceof ContextualDeserializer) {
            findConvertingContentDeserializer = ((ContextualDeserializer) findConvertingContentDeserializer).createContextual(deserializationContext2, beanProperty2);
        }
        TypeDeserializer typeDeserializer = this._elementTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty2);
        }
        return withDeserializer(typeDeserializer, findConvertingContentDeserializer);
    }

    public JavaType getContentType() {
        return this._arrayType.getContentType();
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._elementDeserializer;
    }

    public Object[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object[] completeAndClearBuffer;
        Object deserializeWithType;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (!jsonParser2.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser2, deserializationContext2);
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext2.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        TypeDeserializer typeDeserializer = this._elementTypeDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            JsonToken jsonToken = nextToken;
            if (nextToken == JsonToken.END_ARRAY) {
                break;
            }
            if (jsonToken == JsonToken.VALUE_NULL) {
                deserializeWithType = null;
            } else if (typeDeserializer == null) {
                deserializeWithType = this._elementDeserializer.deserialize(jsonParser2, deserializationContext2);
            } else {
                deserializeWithType = this._elementDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
            }
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            }
            int i2 = i;
            i++;
            resetAndStart[i2] = deserializeWithType;
        }
        if (this._untyped) {
            completeAndClearBuffer = leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i);
        } else {
            completeAndClearBuffer = leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, this._elementClass);
        }
        deserializationContext2.returnObjectBuffer(leaseObjectBuffer);
        return completeAndClearBuffer;
    }

    public Object[] deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return (Object[]) typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public Byte[] deserializeFromBase64(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        byte[] binaryValue = jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
        Byte[] bArr = new Byte[binaryValue.length];
        int length = binaryValue.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = Byte.valueOf(binaryValue[i]);
        }
        return bArr;
    }

    private final Object[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        Object[] objArr;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
            return null;
        }
        if (deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_NULL) {
                deserializeWithType = null;
            } else if (this._elementTypeDeserializer == null) {
                deserializeWithType = this._elementDeserializer.deserialize(jsonParser2, deserializationContext2);
            } else {
                deserializeWithType = this._elementDeserializer.deserializeWithType(jsonParser2, deserializationContext2, this._elementTypeDeserializer);
            }
            if (this._untyped) {
                objArr = new Object[1];
            } else {
                objArr = (Object[]) Array.newInstance(this._elementClass, 1);
            }
            objArr[0] = deserializeWithType;
            return objArr;
        } else if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && this._elementClass == Byte.class) {
            return deserializeFromBase64(jsonParser2, deserializationContext2);
        } else {
            throw deserializationContext2.mappingException(this._arrayType.getRawClass());
        }
    }
}
