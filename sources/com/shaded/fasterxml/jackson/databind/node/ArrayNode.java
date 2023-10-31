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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class ArrayNode extends ContainerNode<ArrayNode> {
    private final List<JsonNode> _children;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        List<JsonNode> list;
        new ArrayList();
        this._children = list;
    }

    public ArrayNode deepCopy() {
        ArrayNode arrayNode;
        new ArrayNode(this._nodeFactory);
        ArrayNode arrayNode2 = arrayNode;
        for (JsonNode deepCopy : this._children) {
            boolean add = arrayNode2._children.add(deepCopy.deepCopy());
        }
        return arrayNode2;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.ARRAY;
    }

    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    public JsonNode get(int i) {
        int i2 = i;
        if (i2 < 0 || i2 >= this._children.size()) {
            return null;
        }
        return this._children.get(i2);
    }

    public JsonNode get(String str) {
        String str2 = str;
        return null;
    }

    public JsonNode path(String str) {
        String str2 = str;
        return MissingNode.getInstance();
    }

    public JsonNode path(int i) {
        int i2 = i;
        if (i2 < 0 || i2 >= this._children.size()) {
            return MissingNode.getInstance();
        }
        return this._children.get(i2);
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator2.writeStartArray();
        Iterator<JsonNode> it = this._children.iterator();
        while (it.hasNext()) {
            ((BaseJsonNode) it.next()).serialize(jsonGenerator2, serializerProvider2);
        }
        jsonGenerator2.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForArray(this, jsonGenerator2);
        Iterator<JsonNode> it = this._children.iterator();
        while (it.hasNext()) {
            ((BaseJsonNode) it.next()).serialize(jsonGenerator2, serializerProvider2);
        }
        typeSerializer2.writeTypeSuffixForArray(this, jsonGenerator2);
    }

    public JsonNode findValue(String str) {
        String str2 = str;
        for (JsonNode findValue : this._children) {
            JsonNode findValue2 = findValue.findValue(str2);
            if (findValue2 != null) {
                return findValue2;
            }
        }
        return null;
    }

    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        String str2 = str;
        List<JsonNode> list2 = list;
        for (JsonNode findValues : this._children) {
            list2 = findValues.findValues(str2, list2);
        }
        return list2;
    }

    public List<String> findValuesAsText(String str, List<String> list) {
        String str2 = str;
        List<String> list2 = list;
        for (JsonNode findValuesAsText : this._children) {
            list2 = findValuesAsText.findValuesAsText(str2, list2);
        }
        return list2;
    }

    public ObjectNode findParent(String str) {
        String str2 = str;
        for (JsonNode findParent : this._children) {
            JsonNode findParent2 = findParent.findParent(str2);
            if (findParent2 != null) {
                return (ObjectNode) findParent2;
            }
        }
        return null;
    }

    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        String str2 = str;
        List<JsonNode> list2 = list;
        for (JsonNode findParents : this._children) {
            list2 = findParents.findParents(str2, list2);
        }
        return list2;
    }

    public JsonNode set(int i, JsonNode jsonNode) {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        NullNode nullNode = jsonNode;
        if (nullNode == null) {
            nullNode = nullNode();
        }
        if (i2 >= 0 && i2 < this._children.size()) {
            return this._children.set(i2, nullNode);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IndexOutOfBoundsException(sb.append("Illegal index ").append(i2).append(", array size ").append(size()).toString());
        throw th2;
    }

    public ArrayNode add(JsonNode jsonNode) {
        NullNode nullNode = jsonNode;
        if (nullNode == null) {
            nullNode = nullNode();
        }
        ArrayNode _add = _add(nullNode);
        return this;
    }

    public ArrayNode addAll(ArrayNode arrayNode) {
        boolean addAll = this._children.addAll(arrayNode._children);
        return this;
    }

    public ArrayNode addAll(Collection<JsonNode> collection) {
        boolean addAll = this._children.addAll(collection);
        return this;
    }

    public ArrayNode insert(int i, JsonNode jsonNode) {
        int i2 = i;
        NullNode nullNode = jsonNode;
        if (nullNode == null) {
            nullNode = nullNode();
        }
        ArrayNode _insert = _insert(i2, nullNode);
        return this;
    }

    public JsonNode remove(int i) {
        int i2 = i;
        if (i2 < 0 || i2 >= this._children.size()) {
            return null;
        }
        return this._children.remove(i2);
    }

    public ArrayNode removeAll() {
        this._children.clear();
        return this;
    }

    public ArrayNode addArray() {
        ArrayNode arrayNode = arrayNode();
        ArrayNode _add = _add(arrayNode);
        return arrayNode;
    }

    public ObjectNode addObject() {
        ObjectNode objectNode = objectNode();
        ArrayNode _add = _add(objectNode);
        return objectNode;
    }

    public ArrayNode addPOJO(Object obj) {
        Object obj2 = obj;
        if (obj2 == null) {
            ArrayNode addNull = addNull();
        } else {
            ArrayNode _add = _add(pojoNode(obj2));
        }
        return this;
    }

    public ArrayNode addNull() {
        ArrayNode _add = _add(nullNode());
        return this;
    }

    public ArrayNode add(int i) {
        ArrayNode _add = _add(numberNode(i));
        return this;
    }

    public ArrayNode add(Integer num) {
        Integer num2 = num;
        if (num2 == null) {
            return addNull();
        }
        return _add(numberNode(num2.intValue()));
    }

    public ArrayNode add(long j) {
        return _add(numberNode(j));
    }

    public ArrayNode add(Long l) {
        Long l2 = l;
        if (l2 == null) {
            return addNull();
        }
        return _add(numberNode(l2.longValue()));
    }

    public ArrayNode add(float f) {
        return _add(numberNode(f));
    }

    public ArrayNode add(Float f) {
        Float f2 = f;
        if (f2 == null) {
            return addNull();
        }
        return _add(numberNode(f2.floatValue()));
    }

    public ArrayNode add(double d) {
        return _add(numberNode(d));
    }

    public ArrayNode add(Double d) {
        Double d2 = d;
        if (d2 == null) {
            return addNull();
        }
        return _add(numberNode(d2.doubleValue()));
    }

    public ArrayNode add(BigDecimal bigDecimal) {
        BigDecimal bigDecimal2 = bigDecimal;
        if (bigDecimal2 == null) {
            return addNull();
        }
        return _add(numberNode(bigDecimal2));
    }

    public ArrayNode add(String str) {
        String str2 = str;
        if (str2 == null) {
            return addNull();
        }
        return _add(textNode(str2));
    }

    public ArrayNode add(boolean z) {
        return _add(booleanNode(z));
    }

    public ArrayNode add(Boolean bool) {
        Boolean bool2 = bool;
        if (bool2 == null) {
            return addNull();
        }
        return _add(booleanNode(bool2.booleanValue()));
    }

    public ArrayNode add(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return addNull();
        }
        return _add(binaryNode(bArr2));
    }

    public ArrayNode insertArray(int i) {
        ArrayNode arrayNode = arrayNode();
        ArrayNode _insert = _insert(i, arrayNode);
        return arrayNode;
    }

    public ObjectNode insertObject(int i) {
        ObjectNode objectNode = objectNode();
        ArrayNode _insert = _insert(i, objectNode);
        return objectNode;
    }

    public ArrayNode insertPOJO(int i, Object obj) {
        int i2 = i;
        Object obj2 = obj;
        if (obj2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, pojoNode(obj2));
    }

    public ArrayNode insertNull(int i) {
        ArrayNode _insert = _insert(i, nullNode());
        return this;
    }

    public ArrayNode insert(int i, int i2) {
        ArrayNode _insert = _insert(i, numberNode(i2));
        return this;
    }

    public ArrayNode insert(int i, Integer num) {
        int i2 = i;
        Integer num2 = num;
        if (num2 == null) {
            ArrayNode insertNull = insertNull(i2);
        } else {
            ArrayNode _insert = _insert(i2, numberNode(num2.intValue()));
        }
        return this;
    }

    public ArrayNode insert(int i, long j) {
        return _insert(i, numberNode(j));
    }

    public ArrayNode insert(int i, Long l) {
        int i2 = i;
        Long l2 = l;
        if (l2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, numberNode(l2.longValue()));
    }

    public ArrayNode insert(int i, float f) {
        return _insert(i, numberNode(f));
    }

    public ArrayNode insert(int i, Float f) {
        int i2 = i;
        Float f2 = f;
        if (f2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, numberNode(f2.floatValue()));
    }

    public ArrayNode insert(int i, double d) {
        return _insert(i, numberNode(d));
    }

    public ArrayNode insert(int i, Double d) {
        int i2 = i;
        Double d2 = d;
        if (d2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, numberNode(d2.doubleValue()));
    }

    public ArrayNode insert(int i, BigDecimal bigDecimal) {
        int i2 = i;
        BigDecimal bigDecimal2 = bigDecimal;
        if (bigDecimal2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, numberNode(bigDecimal2));
    }

    public ArrayNode insert(int i, String str) {
        int i2 = i;
        String str2 = str;
        if (str2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, textNode(str2));
    }

    public ArrayNode insert(int i, boolean z) {
        return _insert(i, booleanNode(z));
    }

    public ArrayNode insert(int i, Boolean bool) {
        int i2 = i;
        Boolean bool2 = bool;
        if (bool2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, booleanNode(bool2.booleanValue()));
    }

    public ArrayNode insert(int i, byte[] bArr) {
        int i2 = i;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return insertNull(i2);
        }
        return _insert(i2, binaryNode(bArr2));
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
            java.util.List<com.shaded.fasterxml.jackson.databind.JsonNode> r2 = r2._children
            r3 = r1
            com.shaded.fasterxml.jackson.databind.node.ArrayNode r3 = (com.shaded.fasterxml.jackson.databind.node.ArrayNode) r3
            java.util.List<com.shaded.fasterxml.jackson.databind.JsonNode> r3 = r3._children
            boolean r2 = r2.equals(r3)
            r0 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.ArrayNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(16 + (size() << 4));
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('[');
        int size = this._children.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                StringBuilder append2 = sb2.append(',');
            }
            StringBuilder append3 = sb2.append(this._children.get(i).toString());
        }
        StringBuilder append4 = sb2.append(']');
        return sb2.toString();
    }

    private ArrayNode _add(JsonNode jsonNode) {
        boolean add = this._children.add(jsonNode);
        return this;
    }

    private ArrayNode _insert(int i, JsonNode jsonNode) {
        int i2 = i;
        JsonNode jsonNode2 = jsonNode;
        if (i2 < 0) {
            this._children.add(0, jsonNode2);
        } else if (i2 >= this._children.size()) {
            boolean add = this._children.add(jsonNode2);
        } else {
            this._children.add(i2, jsonNode2);
        }
        return this;
    }
}
