package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements ContextualDeserializer {
    public static final StringArrayDeserializer instance;
    private static final long serialVersionUID = -7589512013334920693L;
    protected JsonDeserializer<String> _elementDeserializer;

    static {
        StringArrayDeserializer stringArrayDeserializer;
        new StringArrayDeserializer();
        instance = stringArrayDeserializer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StringArrayDeserializer() {
        super((Class<?>) String[].class);
        this._elementDeserializer = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected StringArrayDeserializer(JsonDeserializer<?> jsonDeserializer) {
        super((Class<?>) String[].class);
        this._elementDeserializer = jsonDeserializer;
    }

    public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String _parseString;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (!jsonParser2.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser2, deserializationContext2);
        }
        if (this._elementDeserializer != null) {
            return _deserializeCustom(jsonParser2, deserializationContext2);
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext2.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            JsonToken jsonToken = nextToken;
            if (nextToken != JsonToken.END_ARRAY) {
                if (jsonToken == JsonToken.VALUE_STRING) {
                    _parseString = jsonParser2.getText();
                } else if (jsonToken == JsonToken.VALUE_NULL) {
                    _parseString = null;
                } else {
                    _parseString = _parseString(jsonParser2, deserializationContext2);
                }
                if (i >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i = 0;
                }
                int i2 = i;
                i++;
                resetAndStart[i2] = _parseString;
            } else {
                String[] strArr = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, String.class);
                deserializationContext2.returnObjectBuffer(leaseObjectBuffer);
                return strArr;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final String[] _deserializeCustom(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        ObjectBuffer leaseObjectBuffer = deserializationContext2.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        JsonDeserializer<String> jsonDeserializer = this._elementDeserializer;
        int i = 0;
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            JsonToken jsonToken = nextToken;
            if (nextToken != JsonToken.END_ARRAY) {
                String deserialize = jsonToken == JsonToken.VALUE_NULL ? null : jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
                if (i >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i = 0;
                }
                int i2 = i;
                i++;
                resetAndStart[i2] = deserialize;
            } else {
                String[] strArr = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, String.class);
                deserializationContext2.returnObjectBuffer(leaseObjectBuffer);
                return strArr;
            }
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private final String[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            String[] strArr = new String[1];
            String[] strArr2 = strArr;
            strArr[0] = jsonParser2.getCurrentToken() == JsonToken.VALUE_NULL ? null : _parseString(jsonParser2, deserializationContext2);
            return strArr2;
        } else if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
            return null;
        } else {
            throw deserializationContext2.mappingException((Class<?>) this._valueClass);
        }
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<Object> findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext2, beanProperty2, this._elementDeserializer);
        if (findConvertingContentDeserializer == null) {
            findConvertingContentDeserializer = deserializationContext2.findContextualValueDeserializer(deserializationContext2.constructType(String.class), beanProperty2);
        } else if (findConvertingContentDeserializer instanceof ContextualDeserializer) {
            findConvertingContentDeserializer = ((ContextualDeserializer) findConvertingContentDeserializer).createContextual(deserializationContext2, beanProperty2);
        }
        if (findConvertingContentDeserializer != null && isDefaultDeserializer(findConvertingContentDeserializer)) {
            findConvertingContentDeserializer = null;
        }
        if (this._elementDeserializer != findConvertingContentDeserializer) {
            new StringArrayDeserializer(findConvertingContentDeserializer);
            return jsonDeserializer;
        }
        return this;
    }
}
