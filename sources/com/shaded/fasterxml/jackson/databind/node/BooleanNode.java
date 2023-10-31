package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class BooleanNode extends ValueNode {
    public static final BooleanNode FALSE;
    public static final BooleanNode TRUE;
    private final boolean _value;

    static {
        BooleanNode booleanNode;
        BooleanNode booleanNode2;
        new BooleanNode(true);
        TRUE = booleanNode;
        new BooleanNode(false);
        FALSE = booleanNode2;
    }

    private BooleanNode(boolean z) {
        this._value = z;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.BOOLEAN;
    }

    public JsonToken asToken() {
        return this._value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    public boolean booleanValue() {
        return this._value;
    }

    public String asText() {
        return this._value ? "true" : "false";
    }

    public boolean asBoolean() {
        return this._value;
    }

    public boolean asBoolean(boolean z) {
        boolean z2 = z;
        return this._value;
    }

    public int asInt(int i) {
        int i2 = i;
        return this._value ? 1 : 0;
    }

    public long asLong(long j) {
        long j2 = j;
        return this._value ? 1 : 0;
    }

    public double asDouble(double d) {
        double d2 = d;
        return this._value ? 1.0d : 0.0d;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator.writeBoolean(this._value);
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
            r2 = r1
            java.lang.Class r2 = r2.getClass()
            r3 = r0
            java.lang.Class r3 = r3.getClass()
            if (r2 == r3) goto L_0x001e
            r2 = 0
            r0 = r2
            goto L_0x0008
        L_0x001e:
            r2 = r0
            boolean r2 = r2._value
            r3 = r1
            com.shaded.fasterxml.jackson.databind.node.BooleanNode r3 = (com.shaded.fasterxml.jackson.databind.node.BooleanNode) r3
            boolean r3 = r3._value
            if (r2 != r3) goto L_0x002b
            r2 = 1
        L_0x0029:
            r0 = r2
            goto L_0x0008
        L_0x002b:
            r2 = 0
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.BooleanNode.equals(java.lang.Object):boolean");
    }
}
