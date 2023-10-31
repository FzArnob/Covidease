package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer<Object> {
    private static final Object[] NO_OBJECTS = new Object[0];
    public static final UntypedObjectDeserializer instance;
    private static final long serialVersionUID = 1;

    static {
        UntypedObjectDeserializer untypedObjectDeserializer;
        new UntypedObjectDeserializer();
        instance = untypedObjectDeserializer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UntypedObjectDeserializer() {
        super((Class<?>) Object.class);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        switch (jsonParser2.getCurrentToken()) {
            case START_OBJECT:
                return mapObject(jsonParser2, deserializationContext2);
            case START_ARRAY:
                return mapArray(jsonParser2, deserializationContext2);
            case FIELD_NAME:
                return mapObject(jsonParser2, deserializationContext2);
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser2.getEmbeddedObject();
            case VALUE_STRING:
                return jsonParser2.getText();
            case VALUE_NUMBER_INT:
                if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser2.getBigIntegerValue();
                }
                return jsonParser2.getNumberValue();
            case VALUE_NUMBER_FLOAT:
                if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser2.getDecimalValue();
                }
                return Double.valueOf(jsonParser2.getDoubleValue());
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_NULL:
                return null;
            default:
                throw deserializationContext2.mappingException((Class<?>) Object.class);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        switch (jsonParser2.getCurrentToken()) {
            case START_OBJECT:
            case START_ARRAY:
            case FIELD_NAME:
                return typeDeserializer2.deserializeTypedFromAny(jsonParser2, deserializationContext2);
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser2.getEmbeddedObject();
            case VALUE_STRING:
                return jsonParser2.getText();
            case VALUE_NUMBER_INT:
                if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser2.getBigIntegerValue();
                }
                return jsonParser2.getNumberValue();
            case VALUE_NUMBER_FLOAT:
                if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser2.getDecimalValue();
                }
                return Double.valueOf(jsonParser2.getDoubleValue());
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_NULL:
                return null;
            default:
                throw deserializationContext2.mappingException((Class<?>) Object.class);
        }
    }

    /* access modifiers changed from: protected */
    public Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List list;
        Object obj;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (deserializationContext2.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
            return mapArrayToArray(jsonParser2, deserializationContext2);
        }
        if (jsonParser2.nextToken() == JsonToken.END_ARRAY) {
            new ArrayList(4);
            return obj;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext2.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        int i2 = 0;
        do {
            Object deserialize = deserialize(jsonParser2, deserializationContext2);
            i2++;
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            }
            int i3 = i;
            i++;
            resetAndStart[i3] = deserialize;
        } while (jsonParser2.nextToken() != JsonToken.END_ARRAY);
        new ArrayList(i2 + (i2 >> 3) + 1);
        List list2 = list;
        leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, (List<Object>) list2);
        return list2;
    }

    /* access modifiers changed from: protected */
    public Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        LinkedHashMap linkedHashMap;
        LinkedHashMap linkedHashMap2;
        LinkedHashMap linkedHashMap3;
        Object obj;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        if (currentToken != JsonToken.FIELD_NAME) {
            new LinkedHashMap(4);
            return obj;
        }
        String text = jsonParser2.getText();
        JsonToken nextToken = jsonParser2.nextToken();
        Object deserialize = deserialize(jsonParser2, deserializationContext2);
        if (jsonParser2.nextToken() != JsonToken.FIELD_NAME) {
            new LinkedHashMap(4);
            LinkedHashMap linkedHashMap4 = linkedHashMap3;
            Object put = linkedHashMap4.put(text, deserialize);
            return linkedHashMap4;
        }
        String text2 = jsonParser2.getText();
        JsonToken nextToken2 = jsonParser2.nextToken();
        Object deserialize2 = deserialize(jsonParser2, deserializationContext2);
        if (jsonParser2.nextToken() != JsonToken.FIELD_NAME) {
            new LinkedHashMap(4);
            LinkedHashMap linkedHashMap5 = linkedHashMap2;
            Object put2 = linkedHashMap5.put(text, deserialize);
            Object put3 = linkedHashMap5.put(text2, deserialize2);
            return linkedHashMap5;
        }
        new LinkedHashMap();
        LinkedHashMap linkedHashMap6 = linkedHashMap;
        Object put4 = linkedHashMap6.put(text, deserialize);
        Object put5 = linkedHashMap6.put(text2, deserialize2);
        do {
            String text3 = jsonParser2.getText();
            JsonToken nextToken3 = jsonParser2.nextToken();
            Object put6 = linkedHashMap6.put(text3, deserialize(jsonParser2, deserializationContext2));
        } while (jsonParser2.nextToken() != JsonToken.END_OBJECT);
        return linkedHashMap6;
    }

    /* access modifiers changed from: protected */
    public Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParser2.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext2.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        do {
            Object deserialize = deserialize(jsonParser2, deserializationContext2);
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            }
            int i2 = i;
            i++;
            resetAndStart[i2] = deserialize;
        } while (jsonParser2.nextToken() != JsonToken.END_ARRAY);
        return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i);
    }
}
