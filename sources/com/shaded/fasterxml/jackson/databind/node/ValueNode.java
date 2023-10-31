package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.List;

public abstract class ValueNode extends BaseJsonNode {
    public abstract JsonToken asToken();

    protected ValueNode() {
    }

    public <T extends JsonNode> T deepCopy() {
        return this;
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForScalar(this, jsonGenerator2);
        serialize(jsonGenerator2, serializerProvider);
        typeSerializer2.writeTypeSuffixForScalar(this, jsonGenerator2);
    }

    public String toString() {
        return asText();
    }

    public final JsonNode get(int i) {
        int i2 = i;
        return null;
    }

    public final JsonNode path(int i) {
        int i2 = i;
        return MissingNode.getInstance();
    }

    public final boolean has(int i) {
        int i2 = i;
        return false;
    }

    public final boolean hasNonNull(int i) {
        int i2 = i;
        return false;
    }

    public final JsonNode get(String str) {
        String str2 = str;
        return null;
    }

    public final JsonNode path(String str) {
        String str2 = str;
        return MissingNode.getInstance();
    }

    public final boolean has(String str) {
        String str2 = str;
        return false;
    }

    public final boolean hasNonNull(String str) {
        String str2 = str;
        return false;
    }

    public final JsonNode findValue(String str) {
        String str2 = str;
        return null;
    }

    public final ObjectNode findParent(String str) {
        String str2 = str;
        return null;
    }

    public final List<JsonNode> findValues(String str, List<JsonNode> list) {
        String str2 = str;
        return list;
    }

    public final List<String> findValuesAsText(String str, List<String> list) {
        String str2 = str;
        return list;
    }

    public final List<JsonNode> findParents(String str, List<JsonNode> list) {
        String str2 = str;
        return list;
    }
}
