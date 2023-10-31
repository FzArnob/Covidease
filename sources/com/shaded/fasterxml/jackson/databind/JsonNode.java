package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.TreeNode;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeType;
import com.shaded.fasterxml.jackson.databind.util.EmptyIterator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class JsonNode implements TreeNode, Iterable<JsonNode> {
    public abstract String asText();

    public abstract <T extends JsonNode> T deepCopy();

    public abstract boolean equals(Object obj);

    public abstract JsonNode findParent(String str);

    public abstract List<JsonNode> findParents(String str, List<JsonNode> list);

    public abstract JsonNode findPath(String str);

    public abstract JsonNode findValue(String str);

    public abstract List<JsonNode> findValues(String str, List<JsonNode> list);

    public abstract List<String> findValuesAsText(String str, List<String> list);

    public abstract JsonNode get(int i);

    public abstract JsonNodeType getNodeType();

    public abstract JsonNode path(int i);

    public abstract JsonNode path(String str);

    public abstract String toString();

    protected JsonNode() {
    }

    public int size() {
        return 0;
    }

    public final boolean isValueNode() {
        switch (getNodeType()) {
            case ARRAY:
            case OBJECT:
            case MISSING:
                return false;
            default:
                return true;
        }
    }

    public final boolean isContainerNode() {
        JsonNodeType nodeType = getNodeType();
        return nodeType == JsonNodeType.OBJECT || nodeType == JsonNodeType.ARRAY;
    }

    public final boolean isMissingNode() {
        return getNodeType() == JsonNodeType.MISSING;
    }

    public final boolean isArray() {
        return getNodeType() == JsonNodeType.ARRAY;
    }

    public final boolean isObject() {
        return getNodeType() == JsonNodeType.OBJECT;
    }

    public JsonNode get(String str) {
        String str2 = str;
        return null;
    }

    public Iterator<String> fieldNames() {
        return EmptyIterator.instance();
    }

    public final boolean isPojo() {
        return getNodeType() == JsonNodeType.POJO;
    }

    public final boolean isNumber() {
        return getNodeType() == JsonNodeType.NUMBER;
    }

    public boolean isIntegralNumber() {
        return false;
    }

    public boolean isFloatingPointNumber() {
        return false;
    }

    public boolean isShort() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isLong() {
        return false;
    }

    public boolean isFloat() {
        return false;
    }

    public boolean isDouble() {
        return false;
    }

    public boolean isBigDecimal() {
        return false;
    }

    public boolean isBigInteger() {
        return false;
    }

    public final boolean isTextual() {
        return getNodeType() == JsonNodeType.STRING;
    }

    public final boolean isBoolean() {
        return getNodeType() == JsonNodeType.BOOLEAN;
    }

    public final boolean isNull() {
        return getNodeType() == JsonNodeType.NULL;
    }

    public final boolean isBinary() {
        return getNodeType() == JsonNodeType.BINARY;
    }

    public boolean canConvertToInt() {
        return false;
    }

    public boolean canConvertToLong() {
        return false;
    }

    public String textValue() {
        return null;
    }

    public byte[] binaryValue() throws IOException {
        return null;
    }

    public boolean booleanValue() {
        return false;
    }

    public Number numberValue() {
        return null;
    }

    public short shortValue() {
        return 0;
    }

    public int intValue() {
        return 0;
    }

    public long longValue() {
        return 0;
    }

    public float floatValue() {
        return 0.0f;
    }

    public double doubleValue() {
        return 0.0d;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.ZERO;
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.ZERO;
    }

    public int asInt() {
        return asInt(0);
    }

    public int asInt(int i) {
        return i;
    }

    public long asLong() {
        return asLong(0);
    }

    public long asLong(long j) {
        return j;
    }

    public double asDouble() {
        return asDouble(0.0d);
    }

    public double asDouble(double d) {
        return d;
    }

    public boolean asBoolean() {
        return asBoolean(false);
    }

    public boolean asBoolean(boolean z) {
        return z;
    }

    public boolean has(String str) {
        return get(str) != null;
    }

    public boolean has(int i) {
        return get(i) != null;
    }

    public boolean hasNonNull(String str) {
        JsonNode jsonNode = get(str);
        return jsonNode != null && !jsonNode.isNull();
    }

    public boolean hasNonNull(int i) {
        JsonNode jsonNode = get(i);
        return jsonNode != null && !jsonNode.isNull();
    }

    public final Iterator<JsonNode> iterator() {
        return elements();
    }

    public Iterator<JsonNode> elements() {
        return EmptyIterator.instance();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return EmptyIterator.instance();
    }

    public final List<JsonNode> findValues(String str) {
        List<JsonNode> findValues = findValues(str, (List<JsonNode>) null);
        if (findValues == null) {
            return Collections.emptyList();
        }
        return findValues;
    }

    public final List<String> findValuesAsText(String str) {
        List<String> findValuesAsText = findValuesAsText(str, (List<String>) null);
        if (findValuesAsText == null) {
            return Collections.emptyList();
        }
        return findValuesAsText;
    }

    public final List<JsonNode> findParents(String str) {
        List<JsonNode> findParents = findParents(str, (List<JsonNode>) null);
        if (findParents == null) {
            return Collections.emptyList();
        }
        return findParents;
    }

    public JsonNode with(String str) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("JsonNode not of type ObjectNode (but ").append(getClass().getName()).append("), can not call with() on it").toString());
        throw th2;
    }

    public JsonNode withArray(String str) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("JsonNode not of type ObjectNode (but ").append(getClass().getName()).append("), can not call withArray() on it").toString());
        throw th2;
    }
}
