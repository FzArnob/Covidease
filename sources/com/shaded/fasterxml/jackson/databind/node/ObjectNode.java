package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ObjectNode extends ContainerNode<ObjectNode> {
    private final Map<String, JsonNode> _children;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        Map<String, JsonNode> map;
        new LinkedHashMap();
        this._children = map;
    }

    public ObjectNode deepCopy() {
        ObjectNode objectNode;
        new ObjectNode(this._nodeFactory);
        ObjectNode objectNode2 = objectNode;
        for (Map.Entry next : this._children.entrySet()) {
            JsonNode put = objectNode2._children.put(next.getKey(), ((JsonNode) next.getValue()).deepCopy());
        }
        return objectNode2;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.OBJECT;
    }

    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    public JsonNode get(int i) {
        int i2 = i;
        return null;
    }

    public JsonNode get(String str) {
        return this._children.get(str);
    }

    public Iterator<String> fieldNames() {
        return this._children.keySet().iterator();
    }

    public JsonNode path(int i) {
        int i2 = i;
        return MissingNode.getInstance();
    }

    public JsonNode path(String str) {
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode != null) {
            return jsonNode;
        }
        return MissingNode.getInstance();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return this._children.entrySet().iterator();
    }

    public ObjectNode with(String str) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        JsonNode jsonNode = this._children.get(str2);
        if (jsonNode == null) {
            ObjectNode objectNode = objectNode();
            JsonNode put = this._children.put(str2, objectNode);
            return objectNode;
        } else if (jsonNode instanceof ObjectNode) {
            return (ObjectNode) jsonNode;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new UnsupportedOperationException(sb.append("Property '").append(str2).append("' has value that is not of type ObjectNode (but ").append(jsonNode.getClass().getName()).append(")").toString());
            throw th2;
        }
    }

    public ArrayNode withArray(String str) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        JsonNode jsonNode = this._children.get(str2);
        if (jsonNode == null) {
            ArrayNode arrayNode = arrayNode();
            JsonNode put = this._children.put(str2, arrayNode);
            return arrayNode;
        } else if (jsonNode instanceof ArrayNode) {
            return (ArrayNode) jsonNode;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new UnsupportedOperationException(sb.append("Property '").append(str2).append("' has value that is not of type ArrayNode (but ").append(jsonNode.getClass().getName()).append(")").toString());
            throw th2;
        }
    }

    public JsonNode findValue(String str) {
        String str2 = str;
        for (Map.Entry next : this._children.entrySet()) {
            if (str2.equals(next.getKey())) {
                return (JsonNode) next.getValue();
            }
            JsonNode findValue = ((JsonNode) next.getValue()).findValue(str2);
            if (findValue != null) {
                return findValue;
            }
        }
        return null;
    }

    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        List<JsonNode> list2;
        String str2 = str;
        List<JsonNode> list3 = list;
        for (Map.Entry next : this._children.entrySet()) {
            if (str2.equals(next.getKey())) {
                if (list3 == null) {
                    new ArrayList();
                    list3 = list2;
                }
                boolean add = list3.add(next.getValue());
            } else {
                list3 = ((JsonNode) next.getValue()).findValues(str2, list3);
            }
        }
        return list3;
    }

    public List<String> findValuesAsText(String str, List<String> list) {
        List<String> list2;
        String str2 = str;
        List<String> list3 = list;
        for (Map.Entry next : this._children.entrySet()) {
            if (str2.equals(next.getKey())) {
                if (list3 == null) {
                    new ArrayList();
                    list3 = list2;
                }
                boolean add = list3.add(((JsonNode) next.getValue()).asText());
            } else {
                list3 = ((JsonNode) next.getValue()).findValuesAsText(str2, list3);
            }
        }
        return list3;
    }

    public ObjectNode findParent(String str) {
        String str2 = str;
        for (Map.Entry next : this._children.entrySet()) {
            if (str2.equals(next.getKey())) {
                return this;
            }
            JsonNode findParent = ((JsonNode) next.getValue()).findParent(str2);
            if (findParent != null) {
                return (ObjectNode) findParent;
            }
        }
        return null;
    }

    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        List<JsonNode> list2;
        String str2 = str;
        List<JsonNode> list3 = list;
        for (Map.Entry next : this._children.entrySet()) {
            if (str2.equals(next.getKey())) {
                if (list3 == null) {
                    new ArrayList();
                    list3 = list2;
                }
                boolean add = list3.add(this);
            } else {
                list3 = ((JsonNode) next.getValue()).findParents(str2, list3);
            }
        }
        return list3;
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator2.writeStartObject();
        for (Map.Entry next : this._children.entrySet()) {
            jsonGenerator2.writeFieldName((String) next.getKey());
            ((BaseJsonNode) next.getValue()).serialize(jsonGenerator2, serializerProvider2);
        }
        jsonGenerator2.writeEndObject();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForObject(this, jsonGenerator2);
        for (Map.Entry next : this._children.entrySet()) {
            jsonGenerator2.writeFieldName((String) next.getKey());
            ((BaseJsonNode) next.getValue()).serialize(jsonGenerator2, serializerProvider2);
        }
        typeSerializer2.writeTypeSuffixForObject(this, jsonGenerator2);
    }

    public JsonNode set(String str, JsonNode jsonNode) {
        String str2 = str;
        NullNode nullNode = jsonNode;
        if (nullNode == null) {
            nullNode = nullNode();
        }
        JsonNode put = this._children.put(str2, nullNode);
        return this;
    }

    public JsonNode setAll(Map<String, JsonNode> map) {
        for (Map.Entry next : map.entrySet()) {
            NullNode nullNode = (JsonNode) next.getValue();
            if (nullNode == null) {
                nullNode = nullNode();
            }
            JsonNode put = this._children.put(next.getKey(), nullNode);
        }
        return this;
    }

    public JsonNode setAll(ObjectNode objectNode) {
        this._children.putAll(objectNode._children);
        return this;
    }

    public JsonNode replace(String str, JsonNode jsonNode) {
        String str2 = str;
        NullNode nullNode = jsonNode;
        if (nullNode == null) {
            nullNode = nullNode();
        }
        return this._children.put(str2, nullNode);
    }

    public JsonNode without(String str) {
        JsonNode remove = this._children.remove(str);
        return this;
    }

    public ObjectNode without(Collection<String> collection) {
        boolean removeAll = this._children.keySet().removeAll(collection);
        return this;
    }

    public JsonNode put(String str, JsonNode jsonNode) {
        String str2 = str;
        NullNode nullNode = jsonNode;
        if (nullNode == null) {
            nullNode = nullNode();
        }
        return this._children.put(str2, nullNode);
    }

    public JsonNode remove(String str) {
        return this._children.remove(str);
    }

    public ObjectNode remove(Collection<String> collection) {
        boolean removeAll = this._children.keySet().removeAll(collection);
        return this;
    }

    public ObjectNode removeAll() {
        this._children.clear();
        return this;
    }

    public JsonNode putAll(Map<String, JsonNode> map) {
        return setAll(map);
    }

    public JsonNode putAll(ObjectNode objectNode) {
        return setAll(objectNode);
    }

    public ObjectNode retain(Collection<String> collection) {
        boolean retainAll = this._children.keySet().retainAll(collection);
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain((Collection<String>) Arrays.asList(strArr));
    }

    public ArrayNode putArray(String str) {
        ArrayNode arrayNode = arrayNode();
        JsonNode put = this._children.put(str, arrayNode);
        return arrayNode;
    }

    public ObjectNode putObject(String str) {
        ObjectNode objectNode = objectNode();
        JsonNode put = this._children.put(str, objectNode);
        return objectNode;
    }

    public ObjectNode putPOJO(String str, Object obj) {
        JsonNode put = this._children.put(str, pojoNode(obj));
        return this;
    }

    public ObjectNode putNull(String str) {
        JsonNode put = this._children.put(str, nullNode());
        return this;
    }

    public ObjectNode put(String str, short s) {
        JsonNode put = this._children.put(str, numberNode(s));
        return this;
    }

    public ObjectNode put(String str, Short sh) {
        String str2 = str;
        Short sh2 = sh;
        if (sh2 == null) {
            JsonNode put = this._children.put(str2, nullNode());
        } else {
            JsonNode put2 = this._children.put(str2, numberNode(sh2.shortValue()));
        }
        return this;
    }

    public ObjectNode put(String str, int i) {
        JsonNode put = this._children.put(str, numberNode(i));
        return this;
    }

    public ObjectNode put(String str, Integer num) {
        String str2 = str;
        Integer num2 = num;
        if (num2 == null) {
            JsonNode put = this._children.put(str2, nullNode());
        } else {
            JsonNode put2 = this._children.put(str2, numberNode(num2.intValue()));
        }
        return this;
    }

    public ObjectNode put(String str, long j) {
        JsonNode put = this._children.put(str, numberNode(j));
        return this;
    }

    public ObjectNode put(String str, Long l) {
        String str2 = str;
        Long l2 = l;
        if (l2 == null) {
            JsonNode put = this._children.put(str2, nullNode());
        } else {
            JsonNode put2 = this._children.put(str2, numberNode(l2.longValue()));
        }
        return this;
    }

    public ObjectNode put(String str, float f) {
        JsonNode put = this._children.put(str, numberNode(f));
        return this;
    }

    public ObjectNode put(String str, Float f) {
        String str2 = str;
        Float f2 = f;
        if (f2 == null) {
            JsonNode put = this._children.put(str2, nullNode());
        } else {
            JsonNode put2 = this._children.put(str2, numberNode(f2.floatValue()));
        }
        return this;
    }

    public ObjectNode put(String str, double d) {
        JsonNode put = this._children.put(str, numberNode(d));
        return this;
    }

    public ObjectNode put(String str, Double d) {
        String str2 = str;
        Double d2 = d;
        if (d2 == null) {
            JsonNode put = this._children.put(str2, nullNode());
        } else {
            JsonNode put2 = this._children.put(str2, numberNode(d2.doubleValue()));
        }
        return this;
    }

    public ObjectNode put(String str, BigDecimal bigDecimal) {
        String str2 = str;
        BigDecimal bigDecimal2 = bigDecimal;
        if (bigDecimal2 == null) {
            ObjectNode putNull = putNull(str2);
        } else {
            JsonNode put = this._children.put(str2, numberNode(bigDecimal2));
        }
        return this;
    }

    public ObjectNode put(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (str4 == null) {
            ObjectNode putNull = putNull(str3);
        } else {
            JsonNode put = this._children.put(str3, textNode(str4));
        }
        return this;
    }

    public ObjectNode put(String str, boolean z) {
        JsonNode put = this._children.put(str, booleanNode(z));
        return this;
    }

    public ObjectNode put(String str, Boolean bool) {
        String str2 = str;
        Boolean bool2 = bool;
        if (bool2 == null) {
            JsonNode put = this._children.put(str2, nullNode());
        } else {
            JsonNode put2 = this._children.put(str2, booleanNode(bool2.booleanValue()));
        }
        return this;
    }

    public ObjectNode put(String str, byte[] bArr) {
        String str2 = str;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            JsonNode put = this._children.put(str2, nullNode());
        } else {
            JsonNode put2 = this._children.put(str2, binaryNode(bArr2));
        }
        return this;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r1
            r3 = r0
            if (r2 != r3) goto L_0x0009
            r2 = 1
            r0 = r2
        L_0x0008:
            return r0
        L_0x0009:
            r2 = r1
            if (r2 != 0) goto L_0x000f
            r2 = 0
            r0 = r2
            goto L_0x0008
        L_0x000f:
            r2 = r0
            java.lang.Class r2 = r2.getClass()
            r3 = r1
            java.lang.Class r3 = r3.getClass()
            if (r2 == r3) goto L_0x001e
            r2 = 0
            r0 = r2
            goto L_0x0008
        L_0x001e:
            r2 = r0
            java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.JsonNode> r2 = r2._children
            r3 = r1
            com.shaded.fasterxml.jackson.databind.node.ObjectNode r3 = (com.shaded.fasterxml.jackson.databind.node.ObjectNode) r3
            java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.JsonNode> r3 = r3._children
            boolean r2 = r2.equals(r3)
            r0 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.ObjectNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(32 + (size() << 4));
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("{");
        int i = 0;
        for (Map.Entry next : this._children.entrySet()) {
            if (i > 0) {
                StringBuilder append2 = sb2.append(",");
            }
            i++;
            TextNode.appendQuoted(sb2, (String) next.getKey());
            StringBuilder append3 = sb2.append(':');
            StringBuilder append4 = sb2.append(((JsonNode) next.getValue()).toString());
        }
        StringBuilder append5 = sb2.append("}");
        return sb2.toString();
    }
}
