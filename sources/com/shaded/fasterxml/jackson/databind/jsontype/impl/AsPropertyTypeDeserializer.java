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

public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsPropertyTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        super(javaType, typeIdResolver, str, z, cls);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsPropertyTypeDeserializer(AsPropertyTypeDeserializer asPropertyTypeDeserializer, BeanProperty beanProperty) {
        super(asPropertyTypeDeserializer, beanProperty);
    }

    public TypeDeserializer forProperty(BeanProperty beanProperty) {
        TypeDeserializer typeDeserializer;
        BeanProperty beanProperty2 = beanProperty;
        if (beanProperty2 == this._property) {
            return this;
        }
        new AsPropertyTypeDeserializer(this, beanProperty2);
        return typeDeserializer;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.PROPERTY;
    }

    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        } else if (currentToken == JsonToken.START_ARRAY) {
            return _deserializeTypedUsingDefaultImpl(jsonParser2, deserializationContext2, (TokenBuffer) null);
        } else {
            if (currentToken != JsonToken.FIELD_NAME) {
                return _deserializeTypedUsingDefaultImpl(jsonParser2, deserializationContext2, (TokenBuffer) null);
            }
        }
        TokenBuffer tokenBuffer2 = null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            if (this._typePropertyName.equals(currentName)) {
                return _deserializeTypedForId(jsonParser2, deserializationContext2, tokenBuffer2);
            }
            if (tokenBuffer2 == null) {
                new TokenBuffer((ObjectCodec) null);
                tokenBuffer2 = tokenBuffer;
            }
            tokenBuffer2.writeFieldName(currentName);
            tokenBuffer2.copyCurrentStructure(jsonParser2);
            currentToken = jsonParser2.nextToken();
        }
        return _deserializeTypedUsingDefaultImpl(jsonParser2, deserializationContext2, tokenBuffer2);
    }

    /* access modifiers changed from: protected */
    public final Object _deserializeTypedForId(JsonParser jsonParser, DeserializationContext deserializationContext, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer2;
        JsonParserSequence jsonParserSequence = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        TokenBuffer tokenBuffer3 = tokenBuffer;
        String text = jsonParserSequence.getText();
        JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext2, text);
        if (this._typeIdVisible) {
            if (tokenBuffer3 == null) {
                new TokenBuffer((ObjectCodec) null);
                tokenBuffer3 = tokenBuffer2;
            }
            tokenBuffer3.writeFieldName(jsonParserSequence.getCurrentName());
            tokenBuffer3.writeString(text);
        }
        if (tokenBuffer3 != null) {
            jsonParserSequence = JsonParserSequence.createFlattened(tokenBuffer3.asParser(jsonParserSequence), jsonParserSequence);
        }
        JsonToken nextToken = jsonParserSequence.nextToken();
        return _findDeserializer.deserialize(jsonParserSequence, deserializationContext2);
    }

    /* access modifiers changed from: protected */
    public Object _deserializeTypedUsingDefaultImpl(JsonParser jsonParser, DeserializationContext deserializationContext, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        TokenBuffer tokenBuffer2 = tokenBuffer;
        JsonDeserializer<Object> _findDefaultImplDeserializer = _findDefaultImplDeserializer(deserializationContext2);
        if (_findDefaultImplDeserializer != null) {
            if (tokenBuffer2 != null) {
                tokenBuffer2.writeEndObject();
                jsonParser2 = tokenBuffer2.asParser(jsonParser2);
                JsonToken nextToken = jsonParser2.nextToken();
            }
            return _findDefaultImplDeserializer.deserialize(jsonParser2, deserializationContext2);
        }
        Object deserializeIfNatural = TypeDeserializer.deserializeIfNatural(jsonParser2, deserializationContext2, this._baseType);
        if (deserializeIfNatural != null) {
            return deserializeIfNatural;
        }
        if (jsonParser2.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromAny(jsonParser2, deserializationContext2);
        }
        JsonToken jsonToken = JsonToken.FIELD_NAME;
        new StringBuilder();
        throw deserializationContext2.wrongTokenException(jsonParser2, jsonToken, sb.append("missing property '").append(this._typePropertyName).append("' that is to contain type id  (for class ").append(baseTypeName()).append(")").toString());
    }

    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParser2.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromArray(jsonParser2, deserializationContext2);
        }
        return deserializeTypedFromObject(jsonParser2, deserializationContext2);
    }
}
