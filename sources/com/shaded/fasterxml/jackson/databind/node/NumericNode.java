package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class NumericNode extends ValueNode {
    public abstract String asText();

    public abstract BigInteger bigIntegerValue();

    public abstract boolean canConvertToInt();

    public abstract boolean canConvertToLong();

    public abstract BigDecimal decimalValue();

    public abstract double doubleValue();

    public abstract int intValue();

    public abstract long longValue();

    public abstract JsonParser.NumberType numberType();

    public abstract Number numberValue();

    protected NumericNode() {
    }

    public final JsonNodeType getNodeType() {
        return JsonNodeType.NUMBER;
    }

    public final int asInt() {
        return intValue();
    }

    public final int asInt(int i) {
        int i2 = i;
        return intValue();
    }

    public final long asLong() {
        return longValue();
    }

    public final long asLong(long j) {
        long j2 = j;
        return longValue();
    }

    public final double asDouble() {
        return doubleValue();
    }

    public final double asDouble(double d) {
        double d2 = d;
        return doubleValue();
    }
}
