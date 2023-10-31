package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.node.ArrayNode;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.node.NullNode;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

/* compiled from: JsonNodeDeserializer */
abstract class BaseNodeDeserializer extends StdDeserializer<JsonNode> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseNodeDeserializer() {
        super((Class<?>) JsonNode.class);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public JsonNode getNullValue() {
        return NullNode.getInstance();
    }

    /* access modifiers changed from: protected */
    public void _reportProblem(JsonParser jsonParser, String str) throws JsonMappingException {
        Throwable th;
        Throwable th2 = th;
        new JsonMappingException(str, jsonParser.getTokenLocation());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws JsonProcessingException {
    }

    /* access modifiers changed from: protected */
    public final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        JsonNode textNode;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonNodeFactory jsonNodeFactory2 = jsonNodeFactory;
        ObjectNode objectNode = jsonNodeFactory2.objectNode();
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            switch (jsonParser2.nextToken()) {
                case START_OBJECT:
                    textNode = deserializeObject(jsonParser2, deserializationContext2, jsonNodeFactory2);
                    break;
                case START_ARRAY:
                    textNode = deserializeArray(jsonParser2, deserializationContext2, jsonNodeFactory2);
                    break;
                case VALUE_STRING:
                    textNode = jsonNodeFactory2.textNode(jsonParser2.getText());
                    break;
                default:
                    textNode = deserializeAny(jsonParser2, deserializationContext2, jsonNodeFactory2);
                    break;
            }
            JsonNode replace = objectNode.replace(currentName, textNode);
            if (replace != null) {
                _handleDuplicateField(currentName, objectNode, replace, textNode);
            }
            currentToken = jsonParser2.nextToken();
        }
        return objectNode;
    }

    /* access modifiers changed from: protected */
    public final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonNodeFactory jsonNodeFactory2 = jsonNodeFactory;
        ArrayNode arrayNode = jsonNodeFactory2.arrayNode();
        while (true) {
            JsonToken nextToken = jsonParser2.nextToken();
            if (nextToken != null) {
                switch (nextToken) {
                    case START_OBJECT:
                        ArrayNode add = arrayNode.add((JsonNode) deserializeObject(jsonParser2, deserializationContext2, jsonNodeFactory2));
                        break;
                    case START_ARRAY:
                        ArrayNode add2 = arrayNode.add((JsonNode) deserializeArray(jsonParser2, deserializationContext2, jsonNodeFactory2));
                        break;
                    case VALUE_STRING:
                        ArrayNode add3 = arrayNode.add((JsonNode) jsonNodeFactory2.textNode(jsonParser2.getText()));
                        break;
                    case END_ARRAY:
                        return arrayNode;
                    default:
                        ArrayNode add4 = arrayNode.add(deserializeAny(jsonParser2, deserializationContext2, jsonNodeFactory2));
                        break;
                }
            } else {
                throw deserializationContext2.mappingException("Unexpected end-of-input when binding data into ArrayNode");
            }
        }
    }

    /* access modifiers changed from: protected */
    public final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonNodeFactory jsonNodeFactory2 = jsonNodeFactory;
        switch (jsonParser2.getCurrentToken()) {
            case START_OBJECT:
                return deserializeObject(jsonParser2, deserializationContext2, jsonNodeFactory2);
            case START_ARRAY:
                return deserializeArray(jsonParser2, deserializationContext2, jsonNodeFactory2);
            case VALUE_STRING:
                return jsonNodeFactory2.textNode(jsonParser2.getText());
            case FIELD_NAME:
                return deserializeObject(jsonParser2, deserializationContext2, jsonNodeFactory2);
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = jsonParser2.getEmbeddedObject();
                if (embeddedObject == null) {
                    return jsonNodeFactory2.nullNode();
                }
                if (embeddedObject.getClass() == byte[].class) {
                    return jsonNodeFactory2.binaryNode((byte[]) embeddedObject);
                }
                return jsonNodeFactory2.pojoNode(embeddedObject);
            case VALUE_NUMBER_INT:
                JsonParser.NumberType numberType = jsonParser2.getNumberType();
                if (numberType == JsonParser.NumberType.BIG_INTEGER || deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonNodeFactory2.numberNode(jsonParser2.getBigIntegerValue());
                }
                if (numberType == JsonParser.NumberType.INT) {
                    return jsonNodeFactory2.numberNode(jsonParser2.getIntValue());
                }
                return jsonNodeFactory2.numberNode(jsonParser2.getLongValue());
            case VALUE_NUMBER_FLOAT:
                if (jsonParser2.getNumberType() == JsonParser.NumberType.BIG_DECIMAL || deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonNodeFactory2.numberNode(jsonParser2.getDecimalValue());
                }
                return jsonNodeFactory2.numberNode(jsonParser2.getDoubleValue());
            case VALUE_TRUE:
                return jsonNodeFactory2.booleanNode(true);
            case VALUE_FALSE:
                return jsonNodeFactory2.booleanNode(false);
            case VALUE_NULL:
                return jsonNodeFactory2.nullNode();
            default:
                throw deserializationContext2.mappingException(getValueClass());
        }
    }
}
