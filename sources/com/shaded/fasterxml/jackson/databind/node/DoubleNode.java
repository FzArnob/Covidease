package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.p005io.NumberOutput;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class DoubleNode extends NumericNode {
    protected final double _value;

    public DoubleNode(double d) {
        this._value = d;
    }

    public static DoubleNode valueOf(double d) {
        DoubleNode doubleNode;
        new DoubleNode(d);
        return doubleNode;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.DOUBLE;
    }

    public boolean isFloatingPointNumber() {
        return true;
    }

    public boolean isDouble() {
        return true;
    }

    public boolean canConvertToInt() {
        return this._value >= -2.147483648E9d && this._value <= 2.147483647E9d;
    }

    public boolean canConvertToLong() {
        return this._value >= -9.223372036854776E18d && this._value <= 9.223372036854776E18d;
    }

    public Number numberValue() {
        return Double.valueOf(this._value);
    }

    public short shortValue() {
        return (short) ((int) this._value);
    }

    public int intValue() {
        return (int) this._value;
    }

    public long longValue() {
        return (long) this._value;
    }

    public float floatValue() {
        return (float) this._value;
    }

    public double doubleValue() {
        return this._value;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    public BigInteger bigIntegerValue() {
        return decimalValue().toBigInteger();
    }

    public String asText() {
        return NumberOutput.toString(this._value);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator.writeNumber(this._value);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r4 = r1
            r5 = r0
            if (r4 != r5) goto L_0x0009
            r4 = 1
            r0 = r4
        L_0x0008:
            return r0
        L_0x0009:
            r4 = r1
            if (r4 != 0) goto L_0x000f
            r4 = 0
            r0 = r4
            goto L_0x0008
        L_0x000f:
            r4 = r1
            java.lang.Class r4 = r4.getClass()
            r5 = r0
            java.lang.Class r5 = r5.getClass()
            if (r4 == r5) goto L_0x001e
            r4 = 0
            r0 = r4
            goto L_0x0008
        L_0x001e:
            r4 = r1
            com.shaded.fasterxml.jackson.databind.node.DoubleNode r4 = (com.shaded.fasterxml.jackson.databind.node.DoubleNode) r4
            double r4 = r4._value
            r2 = r4
            r4 = r0
            double r4 = r4._value
            r6 = r2
            int r4 = java.lang.Double.compare(r4, r6)
            if (r4 != 0) goto L_0x0031
            r4 = 1
        L_0x002f:
            r0 = r4
            goto L_0x0008
        L_0x0031:
            r4 = 0
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.DoubleNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this._value);
        return ((int) doubleToLongBits) ^ ((int) (doubleToLongBits >> 32));
    }
}
