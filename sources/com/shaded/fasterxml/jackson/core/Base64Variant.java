package com.shaded.fasterxml.jackson.core;

import java.io.Serializable;
import java.util.Arrays;

public final class Base64Variant implements Serializable {
    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    static final char PADDING_CHAR_NONE = '\u0000';
    private static final long serialVersionUID = 1;
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    protected final transient int _maxLineLength;
    protected final String _name;
    protected final transient char _paddingChar;
    protected final transient boolean _usesPadding;

    public Base64Variant(String str, String str2, boolean z, char c, int i) {
        Throwable th;
        StringBuilder sb;
        String str3 = str2;
        boolean z2 = z;
        char c2 = c;
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        this._usesPadding = z2;
        this._paddingChar = c2;
        this._maxLineLength = i;
        int length = str3.length();
        if (length != 64) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Base64Alphabet length must be exactly 64 (was ").append(length).append(")").toString());
            throw th2;
        }
        str3.getChars(0, length, this._base64ToAsciiC, 0);
        Arrays.fill(this._asciiToBase64, -1);
        for (int i2 = 0; i2 < length; i2++) {
            char c3 = this._base64ToAsciiC[i2];
            this._base64ToAsciiB[i2] = (byte) c3;
            this._asciiToBase64[c3] = i2;
        }
        if (z2) {
            this._asciiToBase64[c2] = -2;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Base64Variant(com.shaded.fasterxml.jackson.core.Base64Variant r11, java.lang.String r12, int r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r1
            boolean r7 = r7._usesPadding
            r8 = r1
            char r8 = r8._paddingChar
            r9 = r3
            r4.<init>((com.shaded.fasterxml.jackson.core.Base64Variant) r5, (java.lang.String) r6, (boolean) r7, (char) r8, (int) r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.Base64Variant.<init>(com.shaded.fasterxml.jackson.core.Base64Variant, java.lang.String, int):void");
    }

    public Base64Variant(Base64Variant base64Variant, String str, boolean z, char c, int i) {
        Base64Variant base64Variant2 = base64Variant;
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        byte[] bArr = base64Variant2._base64ToAsciiB;
        System.arraycopy(bArr, 0, this._base64ToAsciiB, 0, bArr.length);
        char[] cArr = base64Variant2._base64ToAsciiC;
        System.arraycopy(cArr, 0, this._base64ToAsciiC, 0, cArr.length);
        int[] iArr = base64Variant2._asciiToBase64;
        System.arraycopy(iArr, 0, this._asciiToBase64, 0, iArr.length);
        this._usesPadding = z;
        this._paddingChar = c;
        this._maxLineLength = i;
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        return Base64Variants.valueOf(this._name);
    }

    public String getName() {
        return this._name;
    }

    public boolean usesPadding() {
        return this._usesPadding;
    }

    public boolean usesPaddingChar(char c) {
        return c == this._paddingChar;
    }

    public boolean usesPaddingChar(int i) {
        return i == this._paddingChar;
    }

    public char getPaddingChar() {
        return this._paddingChar;
    }

    public byte getPaddingByte() {
        return (byte) this._paddingChar;
    }

    public int getMaxLineLength() {
        return this._maxLineLength;
    }

    public int decodeBase64Char(char c) {
        char c2 = c;
        return c2 <= 127 ? this._asciiToBase64[c2] : -1;
    }

    public int decodeBase64Char(int i) {
        int i2 = i;
        return i2 <= 127 ? this._asciiToBase64[i2] : -1;
    }

    public int decodeBase64Byte(byte b) {
        byte b2 = b;
        return b2 <= Byte.MAX_VALUE ? this._asciiToBase64[b2] : -1;
    }

    public char encodeBase64BitsAsChar(int i) {
        return this._base64ToAsciiC[i];
    }

    public int encodeBase64Chunk(int i, char[] cArr, int i2) {
        int i3 = i;
        char[] cArr2 = cArr;
        int i4 = i2;
        int i5 = i4;
        int i6 = i4 + 1;
        cArr2[i5] = this._base64ToAsciiC[(i3 >> 18) & 63];
        int i7 = i6;
        int i8 = i6 + 1;
        cArr2[i7] = this._base64ToAsciiC[(i3 >> 12) & 63];
        int i9 = i8;
        int i10 = i8 + 1;
        cArr2[i9] = this._base64ToAsciiC[(i3 >> 6) & 63];
        cArr2[i10] = this._base64ToAsciiC[i3 & 63];
        return i10 + 1;
    }

    public void encodeBase64Chunk(StringBuilder sb, int i) {
        StringBuilder sb2 = sb;
        int i2 = i;
        StringBuilder append = sb2.append(this._base64ToAsciiC[(i2 >> 18) & 63]);
        StringBuilder append2 = sb2.append(this._base64ToAsciiC[(i2 >> 12) & 63]);
        StringBuilder append3 = sb2.append(this._base64ToAsciiC[(i2 >> 6) & 63]);
        StringBuilder append4 = sb2.append(this._base64ToAsciiC[i2 & 63]);
    }

    public int encodeBase64Partial(int i, int i2, char[] cArr, int i3) {
        int i4 = i;
        int i5 = i2;
        char[] cArr2 = cArr;
        int i6 = i3;
        int i7 = i6;
        int i8 = i6 + 1;
        cArr2[i7] = this._base64ToAsciiC[(i4 >> 18) & 63];
        int i9 = i8;
        int i10 = i8 + 1;
        cArr2[i9] = this._base64ToAsciiC[(i4 >> 12) & 63];
        if (this._usesPadding) {
            int i11 = i10;
            int i12 = i10 + 1;
            cArr2[i11] = i5 == 2 ? this._base64ToAsciiC[(i4 >> 6) & 63] : this._paddingChar;
            int i13 = i12;
            i10 = i12 + 1;
            cArr2[i13] = this._paddingChar;
        } else if (i5 == 2) {
            int i14 = i10;
            i10++;
            cArr2[i14] = this._base64ToAsciiC[(i4 >> 6) & 63];
        }
        return i10;
    }

    public void encodeBase64Partial(StringBuilder sb, int i, int i2) {
        StringBuilder sb2 = sb;
        int i3 = i;
        int i4 = i2;
        StringBuilder append = sb2.append(this._base64ToAsciiC[(i3 >> 18) & 63]);
        StringBuilder append2 = sb2.append(this._base64ToAsciiC[(i3 >> 12) & 63]);
        if (this._usesPadding) {
            StringBuilder append3 = sb2.append(i4 == 2 ? this._base64ToAsciiC[(i3 >> 6) & 63] : this._paddingChar);
            StringBuilder append4 = sb2.append(this._paddingChar);
        } else if (i4 == 2) {
            StringBuilder append5 = sb2.append(this._base64ToAsciiC[(i3 >> 6) & 63]);
        }
    }

    public byte encodeBase64BitsAsByte(int i) {
        return this._base64ToAsciiB[i];
    }

    public int encodeBase64Chunk(int i, byte[] bArr, int i2) {
        int i3 = i;
        byte[] bArr2 = bArr;
        int i4 = i2;
        int i5 = i4;
        int i6 = i4 + 1;
        bArr2[i5] = this._base64ToAsciiB[(i3 >> 18) & 63];
        int i7 = i6;
        int i8 = i6 + 1;
        bArr2[i7] = this._base64ToAsciiB[(i3 >> 12) & 63];
        int i9 = i8;
        int i10 = i8 + 1;
        bArr2[i9] = this._base64ToAsciiB[(i3 >> 6) & 63];
        bArr2[i10] = this._base64ToAsciiB[i3 & 63];
        return i10 + 1;
    }

    public int encodeBase64Partial(int i, int i2, byte[] bArr, int i3) {
        int i4 = i;
        int i5 = i2;
        byte[] bArr2 = bArr;
        int i6 = i3;
        int i7 = i6;
        int i8 = i6 + 1;
        bArr2[i7] = this._base64ToAsciiB[(i4 >> 18) & 63];
        int i9 = i8;
        int i10 = i8 + 1;
        bArr2[i9] = this._base64ToAsciiB[(i4 >> 12) & 63];
        if (this._usesPadding) {
            byte b = (byte) this._paddingChar;
            int i11 = i10;
            int i12 = i10 + 1;
            bArr2[i11] = i5 == 2 ? this._base64ToAsciiB[(i4 >> 6) & 63] : b;
            int i13 = i12;
            i10 = i12 + 1;
            bArr2[i13] = b;
        } else if (i5 == 2) {
            int i14 = i10;
            i10++;
            bArr2[i14] = this._base64ToAsciiB[(i4 >> 6) & 63];
        }
        return i10;
    }

    public String encode(byte[] bArr) {
        return encode(bArr, false);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String encode(byte[] r16, boolean r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r10 = r1
            int r10 = r10.length
            r3 = r10
            r10 = r3
            r11 = r3
            r12 = 2
            int r11 = r11 >> 2
            int r10 = r10 + r11
            r11 = r3
            r12 = 3
            int r11 = r11 >> 3
            int r10 = r10 + r11
            r4 = r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r4
            r11.<init>(r12)
            r5 = r10
            r10 = r2
            if (r10 == 0) goto L_0x0028
            r10 = r5
            r11 = 34
            java.lang.StringBuilder r10 = r10.append(r11)
        L_0x0028:
            r10 = r0
            int r10 = r10.getMaxLineLength()
            r11 = 2
            int r10 = r10 >> 2
            r4 = r10
            r10 = 0
            r6 = r10
            r10 = r3
            r11 = 3
            int r10 = r10 + -3
            r7 = r10
        L_0x0038:
            r10 = r6
            r11 = r7
            if (r10 > r11) goto L_0x0088
            r10 = r1
            r11 = r6
            int r6 = r6 + 1
            byte r10 = r10[r11]
            r11 = 8
            int r10 = r10 << 8
            r8 = r10
            r10 = r8
            r11 = r1
            r12 = r6
            int r6 = r6 + 1
            byte r11 = r11[r12]
            r12 = 255(0xff, float:3.57E-43)
            r11 = r11 & 255(0xff, float:3.57E-43)
            r10 = r10 | r11
            r8 = r10
            r10 = r8
            r11 = 8
            int r10 = r10 << 8
            r11 = r1
            r12 = r6
            int r6 = r6 + 1
            byte r11 = r11[r12]
            r12 = 255(0xff, float:3.57E-43)
            r11 = r11 & 255(0xff, float:3.57E-43)
            r10 = r10 | r11
            r8 = r10
            r10 = r0
            r11 = r5
            r12 = r8
            r10.encodeBase64Chunk(r11, r12)
            int r4 = r4 + -1
            r10 = r4
            if (r10 > 0) goto L_0x0087
            r10 = r5
            r11 = 92
            java.lang.StringBuilder r10 = r10.append(r11)
            r10 = r5
            r11 = 110(0x6e, float:1.54E-43)
            java.lang.StringBuilder r10 = r10.append(r11)
            r10 = r0
            int r10 = r10.getMaxLineLength()
            r11 = 2
            int r10 = r10 >> 2
            r4 = r10
        L_0x0087:
            goto L_0x0038
        L_0x0088:
            r10 = r3
            r11 = r6
            int r10 = r10 - r11
            r8 = r10
            r10 = r8
            if (r10 <= 0) goto L_0x00b6
            r10 = r1
            r11 = r6
            int r6 = r6 + 1
            byte r10 = r10[r11]
            r11 = 16
            int r10 = r10 << 16
            r9 = r10
            r10 = r8
            r11 = 2
            if (r10 != r11) goto L_0x00af
            r10 = r9
            r11 = r1
            r12 = r6
            int r6 = r6 + 1
            byte r11 = r11[r12]
            r12 = 255(0xff, float:3.57E-43)
            r11 = r11 & 255(0xff, float:3.57E-43)
            r12 = 8
            int r11 = r11 << 8
            r10 = r10 | r11
            r9 = r10
        L_0x00af:
            r10 = r0
            r11 = r5
            r12 = r9
            r13 = r8
            r10.encodeBase64Partial(r11, r12, r13)
        L_0x00b6:
            r10 = r2
            if (r10 == 0) goto L_0x00c0
            r10 = r5
            r11 = 34
            java.lang.StringBuilder r10 = r10.append(r11)
        L_0x00c0:
            r10 = r5
            java.lang.String r10 = r10.toString()
            r0 = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.Base64Variant.encode(byte[], boolean):java.lang.String");
    }

    public String toString() {
        return this._name;
    }
}
