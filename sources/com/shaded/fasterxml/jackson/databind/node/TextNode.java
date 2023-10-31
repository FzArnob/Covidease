package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.Base64Variants;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;
import com.shaded.fasterxml.jackson.core.p005io.NumberInput;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE;
    static final int INT_SPACE = 32;
    final String _value;

    static {
        TextNode textNode;
        new TextNode("");
        EMPTY_STRING_NODE = textNode;
    }

    public TextNode(String str) {
        this._value = str;
    }

    public static TextNode valueOf(String str) {
        TextNode textNode;
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        if (str2.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        new TextNode(str2);
        return textNode;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.STRING;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    public String textValue() {
        return this._value;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0040, code lost:
        if (r7 >= 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0042, code lost:
        _reportInvalidBase64(r1, r6, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004d, code lost:
        if (r4 < r5) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0053, code lost:
        r10 = r4;
        r4 = r4 + 1;
        r6 = r3.charAt(r10);
        r7 = r1.decodeBase64Char(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0064, code lost:
        if (r7 >= 0) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        _reportInvalidBase64(r1, r6, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006d, code lost:
        r8 = (r8 << 6) | r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0076, code lost:
        if (r4 < r5) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007d, code lost:
        if (r1.usesPadding() != false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007f, code lost:
        r2.append(r8 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008a, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008e, code lost:
        r10 = r4;
        r4 = r4 + 1;
        r6 = r3.charAt(r10);
        r7 = r1.decodeBase64Char(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009f, code lost:
        if (r7 >= 0) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a3, code lost:
        if (r7 == -2) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a5, code lost:
        _reportInvalidBase64(r1, r6, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ae, code lost:
        if (r4 < r5) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b0, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b4, code lost:
        r10 = r4;
        r4 = r4 + 1;
        r6 = r3.charAt(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c3, code lost:
        if (r1.usesPaddingChar(r6) != false) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c5, code lost:
        new java.lang.StringBuilder();
        _reportInvalidBase64(r1, r6, 3, r15.append("expected padding character '").append(r1.getPaddingChar()).append("'").toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ef, code lost:
        r2.append(r8 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fb, code lost:
        r8 = (r8 << 6) | r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0104, code lost:
        if (r4 < r5) goto L_0x011d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010b, code lost:
        if (r1.usesPadding() != false) goto L_0x0119;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010d, code lost:
        r2.appendTwoBytes(r8 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0119, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x011d, code lost:
        r10 = r4;
        r4 = r4 + 1;
        r6 = r3.charAt(r10);
        r7 = r1.decodeBase64Char(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x012e, code lost:
        if (r7 >= 0) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0132, code lost:
        if (r7 == -2) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0134, code lost:
        _reportInvalidBase64(r1, r6, 3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x013b, code lost:
        r2.appendTwoBytes(r8 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0147, code lost:
        r2.appendThreeBytes((r8 << 6) | r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        r7 = r1.decodeBase64Char(r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getBinaryValue(com.shaded.fasterxml.jackson.core.Base64Variant r17) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder r9 = new com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = 100
            r10.<init>((int) r11)
            r2 = r9
            r9 = r0
            java.lang.String r9 = r9._value
            r3 = r9
            r9 = 0
            r4 = r9
            r9 = r3
            int r9 = r9.length()
            r5 = r9
        L_0x001b:
            r9 = r4
            r10 = r5
            if (r9 >= r10) goto L_0x002c
        L_0x001f:
            r9 = r3
            r10 = r4
            int r4 = r4 + 1
            char r9 = r9.charAt(r10)
            r6 = r9
            r9 = r4
            r10 = r5
            if (r9 < r10) goto L_0x0033
        L_0x002c:
            r9 = r2
            byte[] r9 = r9.toByteArray()
            r0 = r9
            return r0
        L_0x0033:
            r9 = r6
            r10 = 32
            if (r9 <= r10) goto L_0x001f
            r9 = r1
            r10 = r6
            int r9 = r9.decodeBase64Char((char) r10)
            r7 = r9
            r9 = r7
            if (r9 >= 0) goto L_0x0049
            r9 = r0
            r10 = r1
            r11 = r6
            r12 = 0
            r9._reportInvalidBase64(r10, r11, r12)
        L_0x0049:
            r9 = r7
            r8 = r9
            r9 = r4
            r10 = r5
            if (r9 < r10) goto L_0x0053
            r9 = r0
            r9._reportBase64EOF()
        L_0x0053:
            r9 = r3
            r10 = r4
            int r4 = r4 + 1
            char r9 = r9.charAt(r10)
            r6 = r9
            r9 = r1
            r10 = r6
            int r9 = r9.decodeBase64Char((char) r10)
            r7 = r9
            r9 = r7
            if (r9 >= 0) goto L_0x006d
            r9 = r0
            r10 = r1
            r11 = r6
            r12 = 1
            r9._reportInvalidBase64(r10, r11, r12)
        L_0x006d:
            r9 = r8
            r10 = 6
            int r9 = r9 << 6
            r10 = r7
            r9 = r9 | r10
            r8 = r9
            r9 = r4
            r10 = r5
            if (r9 < r10) goto L_0x008e
            r9 = r1
            boolean r9 = r9.usesPadding()
            if (r9 != 0) goto L_0x008a
            r9 = r8
            r10 = 4
            int r9 = r9 >> 4
            r8 = r9
            r9 = r2
            r10 = r8
            r9.append(r10)
            goto L_0x002c
        L_0x008a:
            r9 = r0
            r9._reportBase64EOF()
        L_0x008e:
            r9 = r3
            r10 = r4
            int r4 = r4 + 1
            char r9 = r9.charAt(r10)
            r6 = r9
            r9 = r1
            r10 = r6
            int r9 = r9.decodeBase64Char((char) r10)
            r7 = r9
            r9 = r7
            if (r9 >= 0) goto L_0x00fb
            r9 = r7
            r10 = -2
            if (r9 == r10) goto L_0x00ac
            r9 = r0
            r10 = r1
            r11 = r6
            r12 = 2
            r9._reportInvalidBase64(r10, r11, r12)
        L_0x00ac:
            r9 = r4
            r10 = r5
            if (r9 < r10) goto L_0x00b4
            r9 = r0
            r9._reportBase64EOF()
        L_0x00b4:
            r9 = r3
            r10 = r4
            int r4 = r4 + 1
            char r9 = r9.charAt(r10)
            r6 = r9
            r9 = r1
            r10 = r6
            boolean r9 = r9.usesPaddingChar((char) r10)
            if (r9 != 0) goto L_0x00ef
            r9 = r0
            r10 = r1
            r11 = r6
            r12 = 3
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r15 = r13
            r13 = r15
            r14 = r15
            r14.<init>()
            java.lang.String r14 = "expected padding character '"
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r1
            char r14 = r14.getPaddingChar()
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = "'"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r9._reportInvalidBase64(r10, r11, r12, r13)
        L_0x00ef:
            r9 = r8
            r10 = 4
            int r9 = r9 >> 4
            r8 = r9
            r9 = r2
            r10 = r8
            r9.append(r10)
            goto L_0x001b
        L_0x00fb:
            r9 = r8
            r10 = 6
            int r9 = r9 << 6
            r10 = r7
            r9 = r9 | r10
            r8 = r9
            r9 = r4
            r10 = r5
            if (r9 < r10) goto L_0x011d
            r9 = r1
            boolean r9 = r9.usesPadding()
            if (r9 != 0) goto L_0x0119
            r9 = r8
            r10 = 2
            int r9 = r9 >> 2
            r8 = r9
            r9 = r2
            r10 = r8
            r9.appendTwoBytes(r10)
            goto L_0x002c
        L_0x0119:
            r9 = r0
            r9._reportBase64EOF()
        L_0x011d:
            r9 = r3
            r10 = r4
            int r4 = r4 + 1
            char r9 = r9.charAt(r10)
            r6 = r9
            r9 = r1
            r10 = r6
            int r9 = r9.decodeBase64Char((char) r10)
            r7 = r9
            r9 = r7
            if (r9 >= 0) goto L_0x0147
            r9 = r7
            r10 = -2
            if (r9 == r10) goto L_0x013b
            r9 = r0
            r10 = r1
            r11 = r6
            r12 = 3
            r9._reportInvalidBase64(r10, r11, r12)
        L_0x013b:
            r9 = r8
            r10 = 2
            int r9 = r9 >> 2
            r8 = r9
            r9 = r2
            r10 = r8
            r9.appendTwoBytes(r10)
        L_0x0145:
            goto L_0x001b
        L_0x0147:
            r9 = r8
            r10 = 6
            int r9 = r9 << 6
            r10 = r7
            r9 = r9 | r10
            r8 = r9
            r9 = r2
            r10 = r8
            r9.appendThreeBytes(r10)
            goto L_0x0145
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.TextNode.getBinaryValue(com.shaded.fasterxml.jackson.core.Base64Variant):byte[]");
    }

    public byte[] binaryValue() throws IOException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public String asText() {
        return this._value;
    }

    public boolean asBoolean(boolean z) {
        boolean z2 = z;
        if (this._value == null || !"true".equals(this._value.trim())) {
            return z2;
        }
        return true;
    }

    public int asInt(int i) {
        return NumberInput.parseAsInt(this._value, i);
    }

    public long asLong(long j) {
        return NumberInput.parseAsLong(this._value, j);
    }

    public double asDouble(double d) {
        return NumberInput.parseAsDouble(this._value, d);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._value == null) {
            jsonGenerator2.writeNull();
        } else {
            jsonGenerator2.writeString(this._value);
        }
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
            com.shaded.fasterxml.jackson.databind.node.TextNode r2 = (com.shaded.fasterxml.jackson.databind.node.TextNode) r2
            java.lang.String r2 = r2._value
            r3 = r0
            java.lang.String r3 = r3._value
            boolean r2 = r2.equals(r3)
            r0 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.node.TextNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        int length = this._value.length();
        new StringBuilder(length + 2 + (length >> 4));
        StringBuilder sb2 = sb;
        appendQuoted(sb2, this._value);
        return sb2.toString();
    }

    protected static void appendQuoted(StringBuilder sb, String str) {
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('\"');
        CharTypes.appendQuoted(sb2, str);
        StringBuilder append2 = sb2.append('\"');
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidBase64(Base64Variant base64Variant, char c, int i) throws JsonParseException {
        _reportInvalidBase64(base64Variant, c, i, (String) null);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) throws JsonParseException {
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Throwable th;
        StringBuilder sb5;
        StringBuilder sb6;
        Base64Variant base64Variant2 = base64Variant;
        char c2 = c;
        int i2 = i;
        String str2 = str;
        if (c2 <= ' ') {
            new StringBuilder();
            sb2 = sb6.append("Illegal white space character (code 0x").append(Integer.toHexString(c2)).append(") as character #").append(i2 + 1).append(" of 4-char base64 unit: can only used between units").toString();
        } else if (base64Variant2.usesPaddingChar(c2)) {
            new StringBuilder();
            sb2 = sb4.append("Unexpected padding character ('").append(base64Variant2.getPaddingChar()).append("') as character #").append(i2 + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        } else if (!Character.isDefined(c2) || Character.isISOControl(c2)) {
            new StringBuilder();
            sb2 = sb.append("Illegal character (code 0x").append(Integer.toHexString(c2)).append(") in base64 content").toString();
        } else {
            new StringBuilder();
            sb2 = sb3.append("Illegal character '").append(c2).append("' (code 0x").append(Integer.toHexString(c2)).append(") in base64 content").toString();
        }
        if (str2 != null) {
            new StringBuilder();
            sb2 = sb5.append(sb2).append(": ").append(str2).toString();
        }
        Throwable th2 = th;
        new JsonParseException(sb2, JsonLocation.f285NA);
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void _reportBase64EOF() throws JsonParseException {
        Throwable th;
        Throwable th2 = th;
        new JsonParseException("Unexpected end-of-String when base64 content", JsonLocation.f285NA);
        throw th2;
    }
}
