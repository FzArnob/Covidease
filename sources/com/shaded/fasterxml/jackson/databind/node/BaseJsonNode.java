package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializable;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class BaseJsonNode extends JsonNode implements JsonSerializable {
    public abstract JsonToken asToken();

    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException;

    public abstract void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException;

    protected BaseJsonNode() {
    }

    public final JsonNode findPath(String str) {
        JsonNode findValue = findValue(str);
        if (findValue == null) {
            return MissingNode.getInstance();
        }
        return findValue;
    }

    public JsonParser traverse() {
        JsonParser jsonParser;
        new TreeTraversingParser(this);
        return jsonParser;
    }

    public JsonParser traverse(ObjectCodec objectCodec) {
        JsonParser jsonParser;
        new TreeTraversingParser(this, objectCodec);
        return jsonParser;
    }

    public JsonParser.NumberType numberType() {
        return null;
    }
}
