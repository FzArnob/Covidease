package com.shaded.fasterxml.jackson.databind.node;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory implements Serializable, JsonNodeCreator {
    private static final JsonNodeFactory decimalsAsIs;
    private static final JsonNodeFactory decimalsNormalized;
    public static final JsonNodeFactory instance = decimalsNormalized;
    private static final long serialVersionUID = -3271940633258788634L;
    private final boolean _cfgBigDecimalExact;

    static {
        JsonNodeFactory jsonNodeFactory;
        JsonNodeFactory jsonNodeFactory2;
        new JsonNodeFactory(false);
        decimalsNormalized = jsonNodeFactory;
        new JsonNodeFactory(true);
        decimalsAsIs = jsonNodeFactory2;
    }

    public JsonNodeFactory(boolean z) {
        this._cfgBigDecimalExact = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected JsonNodeFactory() {
        this(false);
    }

    public static JsonNodeFactory withExactBigDecimals(boolean z) {
        return z ? decimalsAsIs : decimalsNormalized;
    }

    public BooleanNode booleanNode(boolean z) {
        return z ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    public NumericNode numberNode(byte b) {
        return IntNode.valueOf(b);
    }

    public ValueNode numberNode(Byte b) {
        Byte b2 = b;
        return b2 == null ? nullNode() : IntNode.valueOf(b2.intValue());
    }

    public NumericNode numberNode(short s) {
        return ShortNode.valueOf(s);
    }

    public ValueNode numberNode(Short sh) {
        Short sh2 = sh;
        return sh2 == null ? nullNode() : ShortNode.valueOf(sh2.shortValue());
    }

    public NumericNode numberNode(int i) {
        return IntNode.valueOf(i);
    }

    public ValueNode numberNode(Integer num) {
        Integer num2 = num;
        return num2 == null ? nullNode() : IntNode.valueOf(num2.intValue());
    }

    public NumericNode numberNode(long j) {
        return LongNode.valueOf(j);
    }

    public ValueNode numberNode(Long l) {
        Long l2 = l;
        return l2 == null ? nullNode() : LongNode.valueOf(l2.longValue());
    }

    public NumericNode numberNode(BigInteger bigInteger) {
        return BigIntegerNode.valueOf(bigInteger);
    }

    public NumericNode numberNode(float f) {
        return FloatNode.valueOf(f);
    }

    public ValueNode numberNode(Float f) {
        Float f2 = f;
        return f2 == null ? nullNode() : FloatNode.valueOf(f2.floatValue());
    }

    public NumericNode numberNode(double d) {
        return DoubleNode.valueOf(d);
    }

    public ValueNode numberNode(Double d) {
        Double d2 = d;
        return d2 == null ? nullNode() : DoubleNode.valueOf(d2.doubleValue());
    }

    public NumericNode numberNode(BigDecimal bigDecimal) {
        BigDecimal bigDecimal2 = bigDecimal;
        if (this._cfgBigDecimalExact) {
            return DecimalNode.valueOf(bigDecimal2);
        }
        return bigDecimal2.compareTo(BigDecimal.ZERO) == 0 ? DecimalNode.ZERO : DecimalNode.valueOf(bigDecimal2.stripTrailingZeros());
    }

    public TextNode textNode(String str) {
        return TextNode.valueOf(str);
    }

    public BinaryNode binaryNode(byte[] bArr) {
        return BinaryNode.valueOf(bArr);
    }

    public BinaryNode binaryNode(byte[] bArr, int i, int i2) {
        return BinaryNode.valueOf(bArr, i, i2);
    }

    public ArrayNode arrayNode() {
        ArrayNode arrayNode;
        new ArrayNode(this);
        return arrayNode;
    }

    public ObjectNode objectNode() {
        ObjectNode objectNode;
        new ObjectNode(this);
        return objectNode;
    }

    public ValueNode pojoNode(Object obj) {
        ValueNode valueNode;
        new POJONode(obj);
        return valueNode;
    }

    @Deprecated
    public POJONode POJONode(Object obj) {
        POJONode pOJONode;
        new POJONode(obj);
        return pOJONode;
    }
}
