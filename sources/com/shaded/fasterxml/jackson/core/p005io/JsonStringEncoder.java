package com.shaded.fasterxml.jackson.core.p005io;

import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.shaded.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

/* renamed from: com.shaded.fasterxml.jackson.core.io.JsonStringEncoder */
public final class JsonStringEncoder {
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder;
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = new char[6];
    protected TextBuffer _textBuffer;

    static {
        ThreadLocal<SoftReference<JsonStringEncoder>> threadLocal;
        new ThreadLocal<>();
        _threadEncoder = threadLocal;
    }

    public JsonStringEncoder() {
        this._quoteBuffer[0] = '\\';
        this._quoteBuffer[2] = '0';
        this._quoteBuffer[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        JsonStringEncoder jsonStringEncoder;
        Object obj;
        SoftReference softReference = _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder2 = softReference == null ? null : (JsonStringEncoder) softReference.get();
        if (jsonStringEncoder2 == null) {
            new JsonStringEncoder();
            jsonStringEncoder2 = jsonStringEncoder;
            new SoftReference(jsonStringEncoder2);
            _threadEncoder.set(obj);
        }
        return jsonStringEncoder2;
    }

    /* JADX WARNING: type inference failed for: r15v7, types: [int] */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005a, code lost:
        r16 = r7;
        r7 = r7 + 1;
        r10 = r2.charAt(r16);
        r11 = r5[r10];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006b, code lost:
        if (r11 >= 0) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006d, code lost:
        r15 = _appendNumericEscape(r10, r1._quoteBuffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x007c, code lost:
        r12 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008b, code lost:
        if ((r9 + r12) <= r4.length) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x008d, code lost:
        r13 = r4.length - r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0095, code lost:
        if (r13 <= 0) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0097, code lost:
        java.lang.System.arraycopy(r1._quoteBuffer, 0, r4, r9, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00a5, code lost:
        r4 = r3.finishCurrentSegment();
        r14 = r12 - r13;
        java.lang.System.arraycopy(r1._quoteBuffer, r13, r4, 0, r14);
        r15 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00f8, code lost:
        r15 = _appendNamedEscape(r11, r1._quoteBuffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0109, code lost:
        java.lang.System.arraycopy(r1._quoteBuffer, 0, r4, r9, r12);
        r15 = r9 + r12;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(java.lang.String r22) {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            r15 = r1
            com.shaded.fasterxml.jackson.core.util.TextBuffer r15 = r15._textBuffer
            r3 = r15
            r15 = r3
            if (r15 != 0) goto L_0x0025
            r15 = r1
            com.shaded.fasterxml.jackson.core.util.TextBuffer r16 = new com.shaded.fasterxml.jackson.core.util.TextBuffer
            r20 = r16
            r16 = r20
            r17 = r20
            r18 = 0
            r17.<init>(r18)
            r20 = r16
            r16 = r20
            r17 = r20
            r3 = r17
            r0 = r16
            r15._textBuffer = r0
        L_0x0025:
            r15 = r3
            char[] r15 = r15.emptyAndGetCurrentSegment()
            r4 = r15
            int[] r15 = com.shaded.fasterxml.jackson.core.p005io.CharTypes.get7BitOutputEscapes()
            r5 = r15
            r15 = r5
            int r15 = r15.length
            r6 = r15
            r15 = 0
            r7 = r15
            r15 = r2
            int r15 = r15.length()
            r8 = r15
            r15 = 0
            r9 = r15
        L_0x003d:
            r15 = r7
            r16 = r8
            r0 = r16
            if (r15 >= r0) goto L_0x00e9
        L_0x0044:
            r15 = r2
            r16 = r7
            char r15 = r15.charAt(r16)
            r10 = r15
            r15 = r10
            r16 = r6
            r0 = r16
            if (r15 >= r0) goto L_0x00c3
            r15 = r5
            r16 = r10
            r15 = r15[r16]
            if (r15 == 0) goto L_0x00c3
            r15 = r2
            r16 = r7
            int r7 = r7 + 1
            char r15 = r15.charAt(r16)
            r10 = r15
            r15 = r5
            r16 = r10
            r15 = r15[r16]
            r11 = r15
            r15 = r11
            if (r15 >= 0) goto L_0x00f8
            r15 = r1
            r16 = r10
            r17 = r1
            r0 = r17
            char[] r0 = r0._quoteBuffer
            r17 = r0
            int r15 = r15._appendNumericEscape(r16, r17)
        L_0x007c:
            r12 = r15
            r15 = r9
            r16 = r12
            int r15 = r15 + r16
            r16 = r4
            r0 = r16
            int r0 = r0.length
            r16 = r0
            r0 = r16
            if (r15 <= r0) goto L_0x0109
            r15 = r4
            int r15 = r15.length
            r16 = r9
            int r15 = r15 - r16
            r13 = r15
            r15 = r13
            if (r15 <= 0) goto L_0x00a5
            r15 = r1
            char[] r15 = r15._quoteBuffer
            r16 = 0
            r17 = r4
            r18 = r9
            r19 = r13
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
        L_0x00a5:
            r15 = r3
            char[] r15 = r15.finishCurrentSegment()
            r4 = r15
            r15 = r12
            r16 = r13
            int r15 = r15 - r16
            r14 = r15
            r15 = r1
            char[] r15 = r15._quoteBuffer
            r16 = r13
            r17 = r4
            r18 = 0
            r19 = r14
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
            r15 = r14
            r9 = r15
        L_0x00c1:
            goto L_0x003d
        L_0x00c3:
            r15 = r9
            r16 = r4
            r0 = r16
            int r0 = r0.length
            r16 = r0
            r0 = r16
            if (r15 < r0) goto L_0x00d7
            r15 = r3
            char[] r15 = r15.finishCurrentSegment()
            r4 = r15
            r15 = 0
            r9 = r15
        L_0x00d7:
            r15 = r4
            r16 = r9
            int r9 = r9 + 1
            r17 = r10
            r15[r16] = r17
            int r7 = r7 + 1
            r15 = r7
            r16 = r8
            r0 = r16
            if (r15 < r0) goto L_0x00f6
        L_0x00e9:
            r15 = r3
            r16 = r9
            r15.setCurrentLength(r16)
            r15 = r3
            char[] r15 = r15.contentsAsArray()
            r1 = r15
            return r1
        L_0x00f6:
            goto L_0x0044
        L_0x00f8:
            r15 = r1
            r16 = r11
            r17 = r1
            r0 = r17
            char[] r0 = r0._quoteBuffer
            r17 = r0
            int r15 = r15._appendNamedEscape(r16, r17)
            goto L_0x007c
        L_0x0109:
            r15 = r1
            char[] r15 = r15._quoteBuffer
            r16 = 0
            r17 = r4
            r18 = r9
            r19 = r12
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
            r15 = r9
            r16 = r12
            int r15 = r15 + r16
            r9 = r15
            goto L_0x00c1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.p005io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v50, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] quoteAsUTF8(java.lang.String r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r10 = r0
            com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder r10 = r10._byteBuilder
            r2 = r10
            r10 = r2
            if (r10 != 0) goto L_0x001b
            r10 = r0
            com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder r11 = new com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder
            r15 = r11
            r11 = r15
            r12 = r15
            r13 = 0
            r12.<init>((com.shaded.fasterxml.jackson.core.util.BufferRecycler) r13)
            r15 = r11
            r11 = r15
            r12 = r15
            r2 = r12
            r10._byteBuilder = r11
        L_0x001b:
            r10 = 0
            r3 = r10
            r10 = r1
            int r10 = r10.length()
            r4 = r10
            r10 = 0
            r5 = r10
            r10 = r2
            byte[] r10 = r10.resetAndGetFirstSegment()
            r6 = r10
        L_0x002b:
            r10 = r3
            r11 = r4
            if (r10 >= r11) goto L_0x0092
            int[] r10 = com.shaded.fasterxml.jackson.core.p005io.CharTypes.get7BitOutputEscapes()
            r7 = r10
        L_0x0034:
            r10 = r1
            r11 = r3
            char r10 = r10.charAt(r11)
            r8 = r10
            r10 = r8
            r11 = 127(0x7f, float:1.78E-43)
            if (r10 > r11) goto L_0x0046
            r10 = r7
            r11 = r8
            r10 = r10[r11]
            if (r10 == 0) goto L_0x0077
        L_0x0046:
            r10 = r5
            r11 = r6
            int r11 = r11.length
            if (r10 < r11) goto L_0x0053
            r10 = r2
            byte[] r10 = r10.finishCurrentSegment()
            r6 = r10
            r10 = 0
            r5 = r10
        L_0x0053:
            r10 = r1
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10.charAt(r11)
            r8 = r10
            r10 = r8
            r11 = 127(0x7f, float:1.78E-43)
            if (r10 > r11) goto L_0x009d
            r10 = r7
            r11 = r8
            r10 = r10[r11]
            r9 = r10
            r10 = r0
            r11 = r8
            r12 = r9
            r13 = r2
            r14 = r5
            int r10 = r10._appendByteEscape(r11, r12, r13, r14)
            r5 = r10
            r10 = r2
            byte[] r10 = r10.getCurrentSegment()
            r6 = r10
            goto L_0x002b
        L_0x0077:
            r10 = r5
            r11 = r6
            int r11 = r11.length
            if (r10 < r11) goto L_0x0084
            r10 = r2
            byte[] r10 = r10.finishCurrentSegment()
            r6 = r10
            r10 = 0
            r5 = r10
        L_0x0084:
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = r8
            byte r12 = (byte) r12
            r10[r11] = r12
            int r3 = r3 + 1
            r10 = r3
            r11 = r4
            if (r10 < r11) goto L_0x009c
        L_0x0092:
            r10 = r0
            com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder r10 = r10._byteBuilder
            r11 = r5
            byte[] r10 = r10.completeAndCoalesce(r11)
            r0 = r10
            return r0
        L_0x009c:
            goto L_0x0034
        L_0x009d:
            r10 = r8
            r11 = 2047(0x7ff, float:2.868E-42)
            if (r10 > r11) goto L_0x00d0
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = 192(0xc0, float:2.69E-43)
            r13 = r8
            r14 = 6
            int r13 = r13 >> 6
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
            r10 = 128(0x80, float:1.794E-43)
            r11 = r8
            r12 = 63
            r11 = r11 & 63
            r10 = r10 | r11
            r8 = r10
        L_0x00b9:
            r10 = r5
            r11 = r6
            int r11 = r11.length
            if (r10 < r11) goto L_0x00c6
            r10 = r2
            byte[] r10 = r10.finishCurrentSegment()
            r6 = r10
            r10 = 0
            r5 = r10
        L_0x00c6:
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = r8
            byte r12 = (byte) r12
            r10[r11] = r12
            goto L_0x002b
        L_0x00d0:
            r10 = r8
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r10 < r11) goto L_0x00dc
            r10 = r8
            r11 = 57343(0xdfff, float:8.0355E-41)
            if (r10 <= r11) goto L_0x0114
        L_0x00dc:
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = 224(0xe0, float:3.14E-43)
            r13 = r8
            r14 = 12
            int r13 = r13 >> 12
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
            r10 = r5
            r11 = r6
            int r11 = r11.length
            if (r10 < r11) goto L_0x00f8
            r10 = r2
            byte[] r10 = r10.finishCurrentSegment()
            r6 = r10
            r10 = 0
            r5 = r10
        L_0x00f8:
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = 128(0x80, float:1.794E-43)
            r13 = r8
            r14 = 6
            int r13 = r13 >> 6
            r14 = 63
            r13 = r13 & 63
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
            r10 = 128(0x80, float:1.794E-43)
            r11 = r8
            r12 = 63
            r11 = r11 & 63
            r10 = r10 | r11
            r8 = r10
            goto L_0x00b9
        L_0x0114:
            r10 = r8
            r11 = 56319(0xdbff, float:7.892E-41)
            if (r10 <= r11) goto L_0x011e
            r10 = r8
            _illegalSurrogate(r10)
        L_0x011e:
            r10 = r3
            r11 = r4
            if (r10 < r11) goto L_0x0126
            r10 = r8
            _illegalSurrogate(r10)
        L_0x0126:
            r10 = r8
            r11 = r1
            r12 = r3
            int r3 = r3 + 1
            char r11 = r11.charAt(r12)
            int r10 = _convertSurrogate(r10, r11)
            r8 = r10
            r10 = r8
            r11 = 1114111(0x10ffff, float:1.561202E-39)
            if (r10 <= r11) goto L_0x013e
            r10 = r8
            _illegalSurrogate(r10)
        L_0x013e:
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = 240(0xf0, float:3.36E-43)
            r13 = r8
            r14 = 18
            int r13 = r13 >> 18
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
            r10 = r5
            r11 = r6
            int r11 = r11.length
            if (r10 < r11) goto L_0x015a
            r10 = r2
            byte[] r10 = r10.finishCurrentSegment()
            r6 = r10
            r10 = 0
            r5 = r10
        L_0x015a:
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = 128(0x80, float:1.794E-43)
            r13 = r8
            r14 = 12
            int r13 = r13 >> 12
            r14 = 63
            r13 = r13 & 63
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
            r10 = r5
            r11 = r6
            int r11 = r11.length
            if (r10 < r11) goto L_0x017a
            r10 = r2
            byte[] r10 = r10.finishCurrentSegment()
            r6 = r10
            r10 = 0
            r5 = r10
        L_0x017a:
            r10 = r6
            r11 = r5
            int r5 = r5 + 1
            r12 = 128(0x80, float:1.794E-43)
            r13 = r8
            r14 = 6
            int r13 = r13 >> 6
            r14 = 63
            r13 = r13 & 63
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
            r10 = 128(0x80, float:1.794E-43)
            r11 = r8
            r12 = 63
            r11 = r11 & 63
            r10 = r10 | r11
            r8 = r10
            goto L_0x00b9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.p005io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v30, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v31, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v33, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v51, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v52, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v53, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: char} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0070, code lost:
        if (r5 < r7) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0072, code lost:
        r6 = r2.finishCurrentSegment();
        r7 = r6.length;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0080, code lost:
        if (r8 >= 2048) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0082, code lost:
        r10 = r5;
        r5 = r5 + 1;
        r6[r10] = (byte) (192 | (r8 >> 6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0092, code lost:
        if (r5 < r7) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0094, code lost:
        r6 = r2.finishCurrentSegment();
        r7 = r6.length;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009f, code lost:
        r10 = r5;
        r5 = r5 + 1;
        r6[r10] = (byte) (128 | (r8 & '?'));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b4, code lost:
        if (r8 < SURR1_FIRST) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ba, code lost:
        if (r8 <= SURR2_LAST) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00bc, code lost:
        r10 = r5;
        r5 = r5 + 1;
        r6[r10] = (byte) (224 | (r8 >> 12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00cd, code lost:
        if (r5 < r7) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00cf, code lost:
        r6 = r2.finishCurrentSegment();
        r7 = r6.length;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00da, code lost:
        r10 = r5;
        r5 = r5 + 1;
        r6[r10] = (byte) (128 | ((r8 >> 6) & 63));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f1, code lost:
        if (r8 <= SURR1_LAST) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00f3, code lost:
        _illegalSurrogate(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f9, code lost:
        if (r3 < r4) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fb, code lost:
        _illegalSurrogate(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ff, code lost:
        r11 = r3;
        r3 = r3 + 1;
        r8 = _convertSurrogate(r8, r1.charAt(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0111, code lost:
        if (r8 <= 1114111) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0113, code lost:
        _illegalSurrogate(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0117, code lost:
        r10 = r5;
        r5 = r5 + 1;
        r6[r10] = (byte) (240 | (r8 >> 18));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0128, code lost:
        if (r5 < r7) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x012a, code lost:
        r6 = r2.finishCurrentSegment();
        r7 = r6.length;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0135, code lost:
        r10 = r5;
        r5 = r5 + 1;
        r6[r10] = (byte) (128 | ((r8 >> 12) & 63));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x014a, code lost:
        if (r5 < r7) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x014c, code lost:
        r6 = r2.finishCurrentSegment();
        r7 = r6.length;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0157, code lost:
        r10 = r5;
        r5 = r5 + 1;
        r6[r10] = (byte) (128 | ((r8 >> 6) & 63));
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] encodeAsUTF8(java.lang.String r16) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r9 = r0
            com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder r9 = r9._byteBuilder
            r2 = r9
            r9 = r2
            if (r9 != 0) goto L_0x001a
            r9 = r0
            com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder r10 = new com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = 0
            r11.<init>((com.shaded.fasterxml.jackson.core.util.BufferRecycler) r12)
            r14 = r10
            r10 = r14
            r11 = r14
            r2 = r11
            r9._byteBuilder = r10
        L_0x001a:
            r9 = 0
            r3 = r9
            r9 = r1
            int r9 = r9.length()
            r4 = r9
            r9 = 0
            r5 = r9
            r9 = r2
            byte[] r9 = r9.resetAndGetFirstSegment()
            r6 = r9
            r9 = r6
            int r9 = r9.length
            r7 = r9
        L_0x002d:
            r9 = r3
            r10 = r4
            if (r9 >= r10) goto L_0x005a
            r9 = r1
            r10 = r3
            int r3 = r3 + 1
            char r9 = r9.charAt(r10)
            r8 = r9
        L_0x003a:
            r9 = r8
            r10 = 127(0x7f, float:1.78E-43)
            if (r9 > r10) goto L_0x006e
            r9 = r5
            r10 = r7
            if (r9 < r10) goto L_0x004e
            r9 = r2
            byte[] r9 = r9.finishCurrentSegment()
            r6 = r9
            r9 = r6
            int r9 = r9.length
            r7 = r9
            r9 = 0
            r5 = r9
        L_0x004e:
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = r8
            byte r11 = (byte) r11
            r9[r10] = r11
            r9 = r3
            r10 = r4
            if (r9 < r10) goto L_0x0064
        L_0x005a:
            r9 = r0
            com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder r9 = r9._byteBuilder
            r10 = r5
            byte[] r9 = r9.completeAndCoalesce(r10)
            r0 = r9
            return r0
        L_0x0064:
            r9 = r1
            r10 = r3
            int r3 = r3 + 1
            char r9 = r9.charAt(r10)
            r8 = r9
            goto L_0x003a
        L_0x006e:
            r9 = r5
            r10 = r7
            if (r9 < r10) goto L_0x007d
            r9 = r2
            byte[] r9 = r9.finishCurrentSegment()
            r6 = r9
            r9 = r6
            int r9 = r9.length
            r7 = r9
            r9 = 0
            r5 = r9
        L_0x007d:
            r9 = r8
            r10 = 2048(0x800, float:2.87E-42)
            if (r9 >= r10) goto L_0x00b0
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = 192(0xc0, float:2.69E-43)
            r12 = r8
            r13 = 6
            int r12 = r12 >> 6
            r11 = r11 | r12
            byte r11 = (byte) r11
            r9[r10] = r11
        L_0x0090:
            r9 = r5
            r10 = r7
            if (r9 < r10) goto L_0x009f
            r9 = r2
            byte[] r9 = r9.finishCurrentSegment()
            r6 = r9
            r9 = r6
            int r9 = r9.length
            r7 = r9
            r9 = 0
            r5 = r9
        L_0x009f:
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = 128(0x80, float:1.794E-43)
            r12 = r8
            r13 = 63
            r12 = r12 & 63
            r11 = r11 | r12
            byte r11 = (byte) r11
            r9[r10] = r11
            goto L_0x002d
        L_0x00b0:
            r9 = r8
            r10 = 55296(0xd800, float:7.7486E-41)
            if (r9 < r10) goto L_0x00bc
            r9 = r8
            r10 = 57343(0xdfff, float:8.0355E-41)
            if (r9 <= r10) goto L_0x00ed
        L_0x00bc:
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = 224(0xe0, float:3.14E-43)
            r12 = r8
            r13 = 12
            int r12 = r12 >> 12
            r11 = r11 | r12
            byte r11 = (byte) r11
            r9[r10] = r11
            r9 = r5
            r10 = r7
            if (r9 < r10) goto L_0x00da
            r9 = r2
            byte[] r9 = r9.finishCurrentSegment()
            r6 = r9
            r9 = r6
            int r9 = r9.length
            r7 = r9
            r9 = 0
            r5 = r9
        L_0x00da:
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = 128(0x80, float:1.794E-43)
            r12 = r8
            r13 = 6
            int r12 = r12 >> 6
            r13 = 63
            r12 = r12 & 63
            r11 = r11 | r12
            byte r11 = (byte) r11
            r9[r10] = r11
            goto L_0x0090
        L_0x00ed:
            r9 = r8
            r10 = 56319(0xdbff, float:7.892E-41)
            if (r9 <= r10) goto L_0x00f7
            r9 = r8
            _illegalSurrogate(r9)
        L_0x00f7:
            r9 = r3
            r10 = r4
            if (r9 < r10) goto L_0x00ff
            r9 = r8
            _illegalSurrogate(r9)
        L_0x00ff:
            r9 = r8
            r10 = r1
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10.charAt(r11)
            int r9 = _convertSurrogate(r9, r10)
            r8 = r9
            r9 = r8
            r10 = 1114111(0x10ffff, float:1.561202E-39)
            if (r9 <= r10) goto L_0x0117
            r9 = r8
            _illegalSurrogate(r9)
        L_0x0117:
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = 240(0xf0, float:3.36E-43)
            r12 = r8
            r13 = 18
            int r12 = r12 >> 18
            r11 = r11 | r12
            byte r11 = (byte) r11
            r9[r10] = r11
            r9 = r5
            r10 = r7
            if (r9 < r10) goto L_0x0135
            r9 = r2
            byte[] r9 = r9.finishCurrentSegment()
            r6 = r9
            r9 = r6
            int r9 = r9.length
            r7 = r9
            r9 = 0
            r5 = r9
        L_0x0135:
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = 128(0x80, float:1.794E-43)
            r12 = r8
            r13 = 12
            int r12 = r12 >> 12
            r13 = 63
            r12 = r12 & 63
            r11 = r11 | r12
            byte r11 = (byte) r11
            r9[r10] = r11
            r9 = r5
            r10 = r7
            if (r9 < r10) goto L_0x0157
            r9 = r2
            byte[] r9 = r9.finishCurrentSegment()
            r6 = r9
            r9 = r6
            int r9 = r9.length
            r7 = r9
            r9 = 0
            r5 = r9
        L_0x0157:
            r9 = r6
            r10 = r5
            int r5 = r5 + 1
            r11 = 128(0x80, float:1.794E-43)
            r12 = r8
            r13 = 6
            int r12 = r12 >> 6
            r13 = 63
            r12 = r12 & 63
            r11 = r11 | r12
            byte r11 = (byte) r11
            r9[r10] = r11
            goto L_0x0090
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.p005io.JsonStringEncoder.encodeAsUTF8(java.lang.String):byte[]");
    }

    private int _appendNumericEscape(int i, char[] cArr) {
        int i2 = i;
        char[] cArr2 = cArr;
        cArr2[1] = 'u';
        cArr2[4] = HEX_CHARS[i2 >> 4];
        cArr2[5] = HEX_CHARS[i2 & 15];
        return 6;
    }

    private int _appendNamedEscape(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        int i4 = i;
        int i5 = i2;
        ByteArrayBuilder byteArrayBuilder2 = byteArrayBuilder;
        byteArrayBuilder2.setCurrentSegmentLength(i3);
        byteArrayBuilder2.append(92);
        if (i5 < 0) {
            byteArrayBuilder2.append(117);
            if (i4 > 255) {
                int i6 = i4 >> 8;
                byteArrayBuilder2.append(HEX_BYTES[i6 >> 4]);
                byteArrayBuilder2.append(HEX_BYTES[i6 & 15]);
                i4 &= 255;
            } else {
                byteArrayBuilder2.append(48);
                byteArrayBuilder2.append(48);
            }
            byteArrayBuilder2.append(HEX_BYTES[i4 >> 4]);
            byteArrayBuilder2.append(HEX_BYTES[i4 & 15]);
        } else {
            byteArrayBuilder2.append((byte) i5);
        }
        return byteArrayBuilder2.getCurrentSegmentLength();
    }

    protected static int _convertSurrogate(int i, int i2) {
        Throwable th;
        StringBuilder sb;
        int i3 = i;
        int i4 = i2;
        if (i4 >= SURR2_FIRST && i4 <= SURR2_LAST) {
            return 65536 + ((i3 - SURR1_FIRST) << 10) + (i4 - SURR2_FIRST);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Broken surrogate pair: first char 0x").append(Integer.toHexString(i3)).append(", second 0x").append(Integer.toHexString(i4)).append("; illegal combination").toString());
        throw th2;
    }

    protected static void _illegalSurrogate(int i) {
        Throwable th;
        Throwable th2 = th;
        new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
        throw th2;
    }
}
