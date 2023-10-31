package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    private static final long serialVersionUID = 5471961369237518580L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ArrayBlockingQueueDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator, JsonDeserializer<Object> jsonDeserializer2) {
        super(javaType, jsonDeserializer, typeDeserializer, valueInstantiator, jsonDeserializer2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ArrayBlockingQueueDeserializer(ArrayBlockingQueueDeserializer arrayBlockingQueueDeserializer) {
        super(arrayBlockingQueueDeserializer);
    }

    /* access modifiers changed from: protected */
    public ArrayBlockingQueueDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        ArrayBlockingQueueDeserializer arrayBlockingQueueDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        JsonDeserializer<?> jsonDeserializer4 = jsonDeserializer2;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        if (jsonDeserializer3 == this._delegateDeserializer && jsonDeserializer4 == this._valueDeserializer && typeDeserializer2 == this._valueTypeDeserializer) {
            return this;
        }
        new ArrayBlockingQueueDeserializer(this._collectionType, jsonDeserializer4, typeDeserializer2, this._valueInstantiator, jsonDeserializer3);
        return arrayBlockingQueueDeserializer;
    }

    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        }
        if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING) {
            String text = jsonParser2.getText();
            if (text.length() == 0) {
                return (Collection) this._valueInstantiator.createFromString(deserializationContext2, text);
            }
        }
        return deserialize(jsonParser2, deserializationContext2, (Collection<Object>) null);
    }

    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException, JsonProcessingException {
        ArrayList arrayList;
        Collection<Object> collection2;
        Object deserializeWithType;
        Collection collection3;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Collection<Object> collection4 = collection;
        if (!jsonParser2.isExpectedStartArrayToken()) {
            new ArrayBlockingQueue(1);
            return handleNonArray(jsonParser2, deserializationContext2, collection3);
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            JsonToken jsonToken = nextToken;
            if (nextToken == JsonToken.END_ARRAY) {
                break;
            }
            if (jsonToken == JsonToken.VALUE_NULL) {
                deserializeWithType = null;
            } else if (typeDeserializer == null) {
                deserializeWithType = jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
            } else {
                deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
            }
            boolean add = arrayList2.add(deserializeWithType);
        }
        if (collection4 != null) {
            boolean addAll = collection4.addAll(arrayList2);
            return collection4;
        }
        new ArrayBlockingQueue(arrayList2.size(), false, arrayList2);
        return collection2;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
