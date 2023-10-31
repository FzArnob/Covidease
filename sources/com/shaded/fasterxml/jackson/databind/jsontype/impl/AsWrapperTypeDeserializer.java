package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.util.JsonParserSequence;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;

public class AsWrapperTypeDeserializer extends TypeDeserializerBase implements Serializable {
    private static final long serialVersionUID = 5345570420394408290L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        super(javaType, typeIdResolver, str, z, (Class<?>) null);
        Class<?> cls2 = cls;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AsWrapperTypeDeserializer(AsWrapperTypeDeserializer asWrapperTypeDeserializer, BeanProperty beanProperty) {
        super(asWrapperTypeDeserializer, beanProperty);
    }

    public TypeDeserializer forProperty(BeanProperty beanProperty) {
        TypeDeserializer typeDeserializer;
        BeanProperty beanProperty2 = beanProperty;
        if (beanProperty2 == this._property) {
            return this;
        }
        new AsWrapperTypeDeserializer(this, beanProperty2);
        return typeDeserializer;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.WRAPPER_OBJECT;
    }

    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    private final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        StringBuilder sb;
        StringBuilder sb2;
        JsonParserSequence jsonParserSequence = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParserSequence.getCurrentToken() != JsonToken.START_OBJECT) {
            JsonToken jsonToken = JsonToken.START_OBJECT;
            new StringBuilder();
            throw deserializationContext2.wrongTokenException(jsonParserSequence, jsonToken, sb2.append("need JSON Object to contain As.WRAPPER_OBJECT type information for class ").append(baseTypeName()).toString());
        } else if (jsonParserSequence.nextToken() != JsonToken.FIELD_NAME) {
            JsonToken jsonToken2 = JsonToken.FIELD_NAME;
            new StringBuilder();
            throw deserializationContext2.wrongTokenException(jsonParserSequence, jsonToken2, sb.append("need JSON String that contains type id (for subtype of ").append(baseTypeName()).append(")").toString());
        } else {
            String text = jsonParserSequence.getText();
            JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext2, text);
            JsonToken nextToken = jsonParserSequence.nextToken();
            if (this._typeIdVisible && jsonParserSequence.getCurrentToken() == JsonToken.START_OBJECT) {
                new TokenBuffer((ObjectCodec) null);
                TokenBuffer tokenBuffer2 = tokenBuffer;
                tokenBuffer2.writeStartObject();
                tokenBuffer2.writeFieldName(this._typePropertyName);
                tokenBuffer2.writeString(text);
                jsonParserSequence = JsonParserSequence.createFlattened(tokenBuffer2.asParser(jsonParserSequence), jsonParserSequence);
                JsonToken nextToken2 = jsonParserSequence.nextToken();
            }
            Object deserialize = _findDeserializer.deserialize(jsonParserSequence, deserializationContext2);
            if (jsonParserSequence.nextToken() == JsonToken.END_OBJECT) {
                return deserialize;
            }
            throw deserializationContext2.wrongTokenException(jsonParserSequence, JsonToken.END_OBJECT, "expected closing END_OBJECT after type information and deserialized value");
        }
    }
}
