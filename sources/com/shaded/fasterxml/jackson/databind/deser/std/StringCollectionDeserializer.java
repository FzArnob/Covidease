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
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final JavaType _collectionType;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<String> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public StringCollectionDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, ValueInstantiator valueInstantiator) {
        this(javaType, valueInstantiator, (JsonDeserializer<?>) null, jsonDeserializer);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected StringCollectionDeserializer(com.shaded.fasterxml.jackson.databind.JavaType r8, com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r9, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r10, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r11) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r0
            r6 = r1
            java.lang.Class r6 = r6.getRawClass()
            r5.<init>(r6)
            r5 = r0
            r6 = r1
            r5._collectionType = r6
            r5 = r0
            r6 = r4
            r5._valueDeserializer = r6
            r5 = r0
            r6 = r2
            r5._valueInstantiator = r6
            r5 = r0
            r6 = r3
            r5._delegateDeserializer = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer.<init>(com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator, com.shaded.fasterxml.jackson.databind.JsonDeserializer, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* access modifiers changed from: protected */
    public StringCollectionDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        StringCollectionDeserializer stringCollectionDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        JsonDeserializer<?> jsonDeserializer4 = jsonDeserializer2;
        if (this._valueDeserializer == jsonDeserializer4 && this._delegateDeserializer == jsonDeserializer3) {
            return this;
        }
        new StringCollectionDeserializer(this._collectionType, this._valueInstantiator, jsonDeserializer3, jsonDeserializer4);
        return stringCollectionDeserializer;
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<Object> jsonDeserializer = null;
        if (!(this._valueInstantiator == null || this._valueInstantiator.getDelegateCreator() == null)) {
            jsonDeserializer = findDeserializer(deserializationContext2, this._valueInstantiator.getDelegateType(deserializationContext2.getConfig()), beanProperty2);
        }
        JsonDeserializer<Object> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = findConvertingContentDeserializer(deserializationContext2, beanProperty2, jsonDeserializer2);
            if (jsonDeserializer2 == null) {
                jsonDeserializer2 = deserializationContext2.findContextualValueDeserializer(this._collectionType.getContentType(), beanProperty2);
            }
        } else if (jsonDeserializer2 instanceof ContextualDeserializer) {
            jsonDeserializer2 = ((ContextualDeserializer) jsonDeserializer2).createContextual(deserializationContext2, beanProperty2);
        }
        if (isDefaultDeserializer(jsonDeserializer2)) {
            jsonDeserializer2 = null;
        }
        return withResolved(jsonDeserializer, jsonDeserializer2);
    }

    public JavaType getContentType() {
        return this._collectionType.getContentType();
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        }
        return deserialize(jsonParser2, deserializationContext2, (Collection<String>) (Collection) this._valueInstantiator.createUsingDefault(deserializationContext2));
    }

    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Collection<String> collection2 = collection;
        if (!jsonParser2.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser2, deserializationContext2, collection2);
        }
        if (this._valueDeserializer != null) {
            return deserializeUsingCustom(jsonParser2, deserializationContext2, collection2, this._valueDeserializer);
        }
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            JsonToken jsonToken = nextToken;
            if (nextToken == JsonToken.END_ARRAY) {
                return collection2;
            }
            boolean add = collection2.add(jsonToken == JsonToken.VALUE_NULL ? null : _parseString(jsonParser2, deserializationContext2));
        }
    }

    private Collection<String> deserializeUsingCustom(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection, JsonDeserializer<String> jsonDeserializer) throws IOException, JsonProcessingException {
        String deserialize;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Collection<String> collection2 = collection;
        JsonDeserializer<String> jsonDeserializer2 = jsonDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            JsonToken jsonToken = nextToken;
            if (nextToken == JsonToken.END_ARRAY) {
                return collection2;
            }
            if (jsonToken == JsonToken.VALUE_NULL) {
                deserialize = null;
            } else {
                deserialize = jsonDeserializer2.deserialize(jsonParser2, deserializationContext2);
            }
            boolean add = collection2.add(deserialize);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private final Collection<String> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        String _parseString;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Collection<String> collection2 = collection;
        if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            throw deserializationContext2.mappingException(this._collectionType.getRawClass());
        }
        JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
        if (jsonParser2.getCurrentToken() == JsonToken.VALUE_NULL) {
            _parseString = null;
        } else {
            _parseString = jsonDeserializer == null ? _parseString(jsonParser2, deserializationContext2) : jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
        }
        boolean add = collection2.add(_parseString);
        return collection2;
    }
}
