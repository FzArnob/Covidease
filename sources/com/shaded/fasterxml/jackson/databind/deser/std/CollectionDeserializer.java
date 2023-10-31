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
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements ContextualDeserializer {
    private static final long serialVersionUID = -2003828398549708958L;
    protected final JavaType _collectionType;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
        this(javaType, jsonDeserializer, typeDeserializer, valueInstantiator, (JsonDeserializer<Object>) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected CollectionDeserializer(com.shaded.fasterxml.jackson.databind.JavaType r9, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r10, com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer r11, com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r13) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r0
            r7 = r1
            java.lang.Class r7 = r7.getRawClass()
            r6.<init>(r7)
            r6 = r0
            r7 = r1
            r6._collectionType = r7
            r6 = r0
            r7 = r2
            r6._valueDeserializer = r7
            r6 = r0
            r7 = r3
            r6._valueTypeDeserializer = r7
            r6 = r0
            r7 = r4
            r6._valueInstantiator = r7
            r6 = r0
            r7 = r5
            r6._delegateDeserializer = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.CollectionDeserializer.<init>(com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer, com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer, com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected CollectionDeserializer(com.shaded.fasterxml.jackson.databind.deser.std.CollectionDeserializer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            java.lang.Class r3 = r3._valueClass
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r3 = r3._collectionType
            r2._collectionType = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r3 = r3._valueDeserializer
            r2._valueDeserializer = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer r3 = r3._valueTypeDeserializer
            r2._valueTypeDeserializer = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r3 = r3._valueInstantiator
            r2._valueInstantiator = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r3 = r3._delegateDeserializer
            r2._delegateDeserializer = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.CollectionDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.std.CollectionDeserializer):void");
    }

    /* access modifiers changed from: protected */
    public CollectionDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        CollectionDeserializer collectionDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        JsonDeserializer<?> jsonDeserializer4 = jsonDeserializer2;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        if (jsonDeserializer3 == this._delegateDeserializer && jsonDeserializer4 == this._valueDeserializer && typeDeserializer2 == this._valueTypeDeserializer) {
            return this;
        }
        new CollectionDeserializer(this._collectionType, jsonDeserializer4, typeDeserializer2, this._valueInstantiator, jsonDeserializer3);
        return collectionDeserializer;
    }

    public CollectionDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<Object> jsonDeserializer = null;
        if (this._valueInstantiator != null && this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext2.getConfig());
            if (delegateType == null) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Invalid delegate-creator definition for ").append(this._collectionType).append(": value instantiator (").append(this._valueInstantiator.getClass().getName()).append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'").toString());
                throw th2;
            }
            jsonDeserializer = findDeserializer(deserializationContext2, delegateType, beanProperty2);
        }
        JsonDeserializer<Object> findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext2, beanProperty2, this._valueDeserializer);
        if (findConvertingContentDeserializer == null) {
            findConvertingContentDeserializer = deserializationContext2.findContextualValueDeserializer(this._collectionType.getContentType(), beanProperty2);
        } else if (findConvertingContentDeserializer instanceof ContextualDeserializer) {
            findConvertingContentDeserializer = ((ContextualDeserializer) findConvertingContentDeserializer).createContextual(deserializationContext2, beanProperty2);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty2);
        }
        return withResolved(jsonDeserializer, findConvertingContentDeserializer, typeDeserializer);
    }

    public JavaType getContentType() {
        return this._collectionType.getContentType();
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
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
        return deserialize(jsonParser2, deserializationContext2, (Collection<Object>) (Collection) this._valueInstantiator.createUsingDefault(deserializationContext2));
    }

    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Collection<Object> collection2 = collection;
        if (!jsonParser2.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser2, deserializationContext2, collection2);
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            JsonToken jsonToken = nextToken;
            if (nextToken == JsonToken.END_ARRAY) {
                return collection2;
            }
            if (jsonToken == JsonToken.VALUE_NULL) {
                deserializeWithType = null;
            } else if (typeDeserializer == null) {
                deserializeWithType = jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
            } else {
                deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
            }
            boolean add = collection2.add(deserializeWithType);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public final Collection<Object> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Collection<Object> collection2 = collection;
        if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            throw deserializationContext2.mappingException(this._collectionType.getRawClass());
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (jsonParser2.getCurrentToken() == JsonToken.VALUE_NULL) {
            deserializeWithType = null;
        } else if (typeDeserializer == null) {
            deserializeWithType = jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
        } else {
            deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
        }
        boolean add = collection2.add(deserializeWithType);
        return collection2;
    }
}
