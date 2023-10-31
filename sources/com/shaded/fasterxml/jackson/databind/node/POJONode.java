package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.POJO;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public byte[] binaryValue() throws IOException {
        if (this._value instanceof byte[]) {
            return (byte[]) this._value;
        }
        return super.binaryValue();
    }

    public String asText() {
        return this._value == null ? "null" : this._value.toString();
    }

    public boolean asBoolean(boolean z) {
        boolean z2 = z;
        if (this._value == null || !(this._value instanceof Boolean)) {
            return z2;
        }
        return ((Boolean) this._value).booleanValue();
    }

    public int asInt(int i) {
        int i2 = i;
        if (this._value instanceof Number) {
            return ((Number) this._value).intValue();
        }
        return i2;
    }

    public long asLong(long j) {
        long j2 = j;
        if (this._value instanceof Number) {
            return ((Number) this._value).longValue();
        }
        return j2;
    }

    public double asDouble(double d) {
        double d2 = d;
        if (this._value instanceof Number) {
            return ((Number) this._value).doubleValue();
        }
        return d2;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._value == null) {
            jsonGenerator2.writeNull();
        } else {
            jsonGenerator2.writeObject(this._value);
        }
    }

    public Object getPojo() {
        return this._value;
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
            com.shaded.fasterxml.jackson.databind.node.POJONode r3 = (com.shaded.fasterxml.jackson.databind.node.POJONode) r3
            r2 = r3
            r3 = r0
            java.lang.Object r3 = r3._value
            if (r3 != 0) goto L_0x0031
            r3 = r2
            java.lang.Object r3 = r3._value
            if (r3 != 0) goto L_0x002f
            r3 = 1
        L_0x002d:
            r0 = r3
            goto L_0x0008
        L_0x002f:
            r3 = 0
            goto L_0x002d
        L_0x0031:
            r3 = r0
            java.lang.Object r3 = r3._value
            r4 = r2
            java.lang.Object r4 = r4._value
            boolean r3 = r3.equals(r4)
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.POJONode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        return String.valueOf(this._value);
    }
}
