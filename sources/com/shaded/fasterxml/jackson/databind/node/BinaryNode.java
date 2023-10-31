package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.Base64Variants;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class BinaryNode extends ValueNode {
    static final BinaryNode EMPTY_BINARY_NODE;
    final byte[] _data;

    static {
        BinaryNode binaryNode;
        new BinaryNode(new byte[0]);
        EMPTY_BINARY_NODE = binaryNode;
    }

    public BinaryNode(byte[] bArr) {
        this._data = bArr;
    }

    public BinaryNode(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (i3 == 0 && i4 == bArr2.length) {
            this._data = bArr2;
            return;
        }
        this._data = new byte[i4];
        System.arraycopy(bArr2, i3, this._data, 0, i4);
    }

    public static BinaryNode valueOf(byte[] bArr) {
        BinaryNode binaryNode;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return null;
        }
        if (bArr2.length == 0) {
            return EMPTY_BINARY_NODE;
        }
        new BinaryNode(bArr2);
        return binaryNode;
    }

    public static BinaryNode valueOf(byte[] bArr, int i, int i2) {
        BinaryNode binaryNode;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (bArr2 == null) {
            return null;
        }
        if (i4 == 0) {
            return EMPTY_BINARY_NODE;
        }
        new BinaryNode(bArr2, i3, i4);
        return binaryNode;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.BINARY;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public byte[] binaryValue() {
        return this._data;
    }

    public String asText() {
        return Base64Variants.getDefaultVariant().encode(this._data, false);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeBinary(serializerProvider.getConfig().getBase64Variant(), this._data, 0, this._data.length);
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
            r2 = r1
            com.shaded.fasterxml.jackson.databind.node.BinaryNode r2 = (com.shaded.fasterxml.jackson.databind.node.BinaryNode) r2
            byte[] r2 = r2._data
            r3 = r0
            byte[] r3 = r3._data
            boolean r2 = java.util.Arrays.equals(r2, r3)
            r0 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.BinaryNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this._data == null ? -1 : this._data.length;
    }

    public String toString() {
        return Base64Variants.getDefaultVariant().encode(this._data, true);
    }
}
