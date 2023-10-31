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
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1518773374647478964L;
    protected final Class<?> _enumClass;
    protected JsonDeserializer<Enum<?>> _keyDeserializer;
    protected final JavaType _mapType;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public EnumMapDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        this(javaType, jsonDeserializer, jsonDeserializer2, (TypeDeserializer) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumMapDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        super((Class<?>) EnumMap.class);
        JavaType javaType2 = javaType;
        this._mapType = javaType2;
        this._enumClass = javaType2.getKeyType().getRawClass();
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = typeDeserializer;
    }

    @Deprecated
    public EnumMapDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        return withResolved(jsonDeserializer, jsonDeserializer2, (TypeDeserializer) null);
    }

    public EnumMapDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        EnumMapDeserializer enumMapDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        JsonDeserializer<?> jsonDeserializer4 = jsonDeserializer2;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        if (jsonDeserializer3 == this._keyDeserializer && jsonDeserializer4 == this._valueDeserializer && typeDeserializer2 == this._valueTypeDeserializer) {
            return this;
        }
        new EnumMapDeserializer(this._mapType, jsonDeserializer3, jsonDeserializer4, this._valueTypeDeserializer);
        return enumMapDeserializer;
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<Object> jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext2.findContextualValueDeserializer(this._mapType.getKeyType(), beanProperty2);
        }
        JsonDeserializer<?> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = deserializationContext2.findContextualValueDeserializer(this._mapType.getContentType(), beanProperty2);
        } else if (jsonDeserializer2 instanceof ContextualDeserializer) {
            jsonDeserializer2 = ((ContextualDeserializer) jsonDeserializer2).createContextual(deserializationContext2, beanProperty2);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty2);
        }
        return withResolved(jsonDeserializer, jsonDeserializer2, typeDeserializer);
    }

    public boolean isCachable() {
        return true;
    }

    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParser2.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext2.mappingException((Class<?>) EnumMap.class);
        }
        EnumMap<?, ?> constructMap = constructMap();
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (jsonParser2.nextToken() != JsonToken.END_OBJECT) {
            Enum deserialize = this._keyDeserializer.deserialize(jsonParser2, deserializationContext2);
            if (deserialize != null) {
                if (jsonParser2.nextToken() == JsonToken.VALUE_NULL) {
                    deserializeWithType = null;
                } else if (typeDeserializer == null) {
                    deserializeWithType = jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
                } else {
                    deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
                }
                Object put = constructMap.put(deserialize, deserializeWithType);
            } else if (!deserializationContext2.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                String str = null;
                try {
                    if (jsonParser2.hasCurrentToken()) {
                        str = jsonParser2.getText();
                    }
                } catch (Exception e) {
                    Exception exc = e;
                }
                throw deserializationContext2.weirdStringException(str, this._enumClass, "value not one of declared Enum instance names");
            } else {
                JsonToken nextToken = jsonParser2.nextToken();
                JsonParser skipChildren = jsonParser2.skipChildren();
            }
        }
        return constructMap;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.EnumMap<?, ?> constructMap() {
        /*
            r5 = this;
            r0 = r5
            java.util.EnumMap r1 = new java.util.EnumMap
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            java.lang.Class<?> r3 = r3._enumClass
            r2.<init>(r3)
            r0 = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.EnumMapDeserializer.constructMap():java.util.EnumMap");
    }
}
