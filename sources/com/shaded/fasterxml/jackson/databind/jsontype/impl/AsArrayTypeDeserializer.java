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

public class AsArrayTypeDeserializer extends TypeDeserializerBase implements Serializable {
    private static final long serialVersionUID = 5345570420394408290L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        super(javaType, typeIdResolver, str, z, cls);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsArrayTypeDeserializer(AsArrayTypeDeserializer asArrayTypeDeserializer, BeanProperty beanProperty) {
        super(asArrayTypeDeserializer, beanProperty);
    }

    public TypeDeserializer forProperty(BeanProperty beanProperty) {
        TypeDeserializer typeDeserializer;
        BeanProperty beanProperty2 = beanProperty;
        if (beanProperty2 == this._property) {
            return this;
        }
        new AsArrayTypeDeserializer(this, beanProperty2);
        return typeDeserializer;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.WRAPPER_ARRAY;
    }

    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
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
        JsonParserSequence jsonParserSequence = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        boolean isExpectedStartArrayToken = jsonParserSequence.isExpectedStartArrayToken();
        String _locateTypeId = _locateTypeId(jsonParserSequence, deserializationContext2);
        JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext2, _locateTypeId);
        if (this._typeIdVisible && jsonParserSequence.getCurrentToken() == JsonToken.START_OBJECT) {
            new TokenBuffer((ObjectCodec) null);
            TokenBuffer tokenBuffer2 = tokenBuffer;
            tokenBuffer2.writeStartObject();
            tokenBuffer2.writeFieldName(this._typePropertyName);
            tokenBuffer2.writeString(_locateTypeId);
            jsonParserSequence = JsonParserSequence.createFlattened(tokenBuffer2.asParser(jsonParserSequence), jsonParserSequence);
            JsonToken nextToken = jsonParserSequence.nextToken();
        }
        Object deserialize = _findDeserializer.deserialize(jsonParserSequence, deserializationContext2);
        if (!isExpectedStartArrayToken || jsonParserSequence.nextToken() == JsonToken.END_ARRAY) {
            return deserialize;
        }
        throw deserializationContext2.wrongTokenException(jsonParserSequence, JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value");
    }

    /* access modifiers changed from: protected */
    public final String _locateTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        StringBuilder sb2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (!jsonParser2.isExpectedStartArrayToken()) {
            if (this._defaultImpl != null) {
                return this._idResolver.idFromBaseType();
            }
            JsonToken jsonToken = JsonToken.START_ARRAY;
            new StringBuilder();
            throw deserializationContext2.wrongTokenException(jsonParser2, jsonToken, sb2.append("need JSON Array to contain As.WRAPPER_ARRAY type information for class ").append(baseTypeName()).toString());
        } else if (jsonParser2.nextToken() == JsonToken.VALUE_STRING) {
            String text = jsonParser2.getText();
            JsonToken nextToken = jsonParser2.nextToken();
            return text;
        } else if (this._defaultImpl != null) {
            return this._idResolver.idFromBaseType();
        } else {
            JsonToken jsonToken2 = JsonToken.VALUE_STRING;
            new StringBuilder();
            throw deserializationContext2.wrongTokenException(jsonParser2, jsonToken2, sb.append("need JSON String that contains type id (for subtype of ").append(baseTypeName()).append(")").toString());
        }
    }
}
