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

public final class FloatNode extends NumericNode {
    protected final float _value;

    public FloatNode(float f) {
        this._value = f;
    }

    public static FloatNode valueOf(float f) {
        FloatNode floatNode;
        new FloatNode(f);
        return floatNode;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.FLOAT;
    }

    public boolean isFloatingPointNumber() {
        return true;
    }

    public boolean isFloat() {
        return true;
    }

    public boolean canConvertToInt() {
        return this._value >= -2.14748365E9f && this._value <= 2.14748365E9f;
    }

    public boolean canConvertToLong() {
        return this._value >= -9.223372E18f && this._value <= 9.223372E18f;
    }

    public Number numberValue() {
        return Float.valueOf(this._value);
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
        return this._value;
    }

    public double doubleValue() {
        return (double) this._value;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.valueOf((double) this._value);
    }

    public BigInteger bigIntegerValue() {
        return decimalValue().toBigInteger();
    }

    public String asText() {
        return NumberOutput.toString((double) this._value);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator.writeNumber(this._value);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 != 0) goto L_0x000f
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x000f:
            r3 = r1
            java.lang.Class r3 = r3.getClass()
            r4 = r0
            java.lang.Class r4 = r4.getClass()
            if (r3 == r4) goto L_0x001e
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x001e:
            r3 = r1
            com.shaded.fasterxml.jackson.databind.node.FloatNode r3 = (com.shaded.fasterxml.jackson.databind.node.FloatNode) r3
            float r3 = r3._value
            r2 = r3
            r3 = r0
            float r3 = r3._value
            r4 = r2
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 != 0) goto L_0x0031
            r3 = 1
        L_0x002f:
            r0 = r3
            goto L_0x0008
        L_0x0031:
            r3 = 0
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.FloatNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return Float.floatToIntBits(this._value);
    }
}
