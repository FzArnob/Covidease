package com.shaded.fasterxml.jackson.core.p005io;

import java.io.CharConversionException;
import java.io.IOException;

/* renamed from: com.shaded.fasterxml.jackson.core.io.UTF32Reader */
public class UTF32Reader extends BaseReader {
    protected final boolean _bigEndian;
    protected int _byteCount = 0;
    protected int _charCount = 0;
    protected final boolean _managedBuffers;
    protected char _surrogate = 0;

    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    public /* bridge */ /* synthetic */ int read() throws IOException {
        return super.read();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UTF32Reader(com.shaded.fasterxml.jackson.core.p005io.IOContext r14, java.io.InputStream r15, byte[] r16, int r17, int r18, boolean r19) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r4
            r12 = r5
            r7.<init>(r8, r9, r10, r11, r12)
            r7 = r0
            r8 = 0
            r7._surrogate = r8
            r7 = r0
            r8 = 0
            r7._charCount = r8
            r7 = r0
            r8 = 0
            r7._byteCount = r8
            r7 = r0
            r8 = r6
            r7._bigEndian = r8
            r7 = r0
            r8 = r2
            if (r8 == 0) goto L_0x002c
            r8 = 1
        L_0x0029:
            r7._managedBuffers = r8
            return
        L_0x002c:
            r8 = 0
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.p005io.UTF32Reader.<init>(com.shaded.fasterxml.jackson.core.io.IOContext, java.io.InputStream, byte[], int, int, boolean):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v33, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(char[] r14, int r15, int r16) throws java.io.IOException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r7 = r0
            byte[] r7 = r7._buffer
            if (r7 != 0) goto L_0x000d
            r7 = -1
            r0 = r7
        L_0x000c:
            return r0
        L_0x000d:
            r7 = r3
            r8 = 1
            if (r7 >= r8) goto L_0x0014
            r7 = r3
            r0 = r7
            goto L_0x000c
        L_0x0014:
            r7 = r2
            if (r7 < 0) goto L_0x001e
            r7 = r2
            r8 = r3
            int r7 = r7 + r8
            r8 = r1
            int r8 = r8.length
            if (r7 <= r8) goto L_0x0025
        L_0x001e:
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r7.reportBounds(r8, r9, r10)
        L_0x0025:
            r7 = r3
            r8 = r2
            int r7 = r7 + r8
            r3 = r7
            r7 = r2
            r4 = r7
            r7 = r0
            char r7 = r7._surrogate
            if (r7 == 0) goto L_0x0105
            r7 = r1
            r8 = r4
            int r4 = r4 + 1
            r9 = r0
            char r9 = r9._surrogate
            r7[r8] = r9
            r7 = r0
            r8 = 0
            r7._surrogate = r8
        L_0x003d:
            r7 = r4
            r8 = r3
            if (r7 >= r8) goto L_0x00f3
            r7 = r0
            int r7 = r7._ptr
            r5 = r7
            r7 = r0
            boolean r7 = r7._bigEndian
            if (r7 == 0) goto L_0x011d
            r7 = r0
            byte[] r7 = r7._buffer
            r8 = r5
            byte r7 = r7[r8]
            r8 = 24
            int r7 = r7 << 24
            r8 = r0
            byte[] r8 = r8._buffer
            r9 = r5
            r10 = 1
            int r9 = r9 + 1
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 16
            int r8 = r8 << 16
            r7 = r7 | r8
            r8 = r0
            byte[] r8 = r8._buffer
            r9 = r5
            r10 = 2
            int r9 = r9 + 2
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 8
            int r8 = r8 << 8
            r7 = r7 | r8
            r8 = r0
            byte[] r8 = r8._buffer
            r9 = r5
            r10 = 3
            int r9 = r9 + 3
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r7 = r7 | r8
            r6 = r7
        L_0x0087:
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            int r8 = r8._ptr
            r9 = 4
            int r8 = r8 + 4
            r7._ptr = r8
            r7 = r6
            r8 = 65535(0xffff, float:9.1834E-41)
            if (r7 <= r8) goto L_0x015c
            r7 = r6
            r8 = 1114111(0x10ffff, float:1.561202E-39)
            if (r7 <= r8) goto L_0x00cb
            r7 = r0
            r8 = r6
            r9 = r4
            r10 = r2
            int r9 = r9 - r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r12 = r10
            r10 = r12
            r11 = r12
            r11.<init>()
            java.lang.String r11 = "(above "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = 1114111(0x10ffff, float:1.561202E-39)
            java.lang.String r11 = java.lang.Integer.toHexString(r11)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = ") "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r7.reportInvalid(r8, r9, r10)
        L_0x00cb:
            r7 = r6
            r8 = 65536(0x10000, float:9.18355E-41)
            int r7 = r7 - r8
            r6 = r7
            r7 = r1
            r8 = r4
            int r4 = r4 + 1
            r9 = 55296(0xd800, float:7.7486E-41)
            r10 = r6
            r11 = 10
            int r10 = r10 >> 10
            int r9 = r9 + r10
            char r9 = (char) r9
            r7[r8] = r9
            r7 = 56320(0xdc00, float:7.8921E-41)
            r8 = r6
            r9 = 1023(0x3ff, float:1.434E-42)
            r8 = r8 & 1023(0x3ff, float:1.434E-42)
            r7 = r7 | r8
            r6 = r7
            r7 = r4
            r8 = r3
            if (r7 < r8) goto L_0x015c
            r7 = r0
            r8 = r6
            char r8 = (char) r8
            r7._surrogate = r8
        L_0x00f3:
            r7 = r4
            r8 = r2
            int r7 = r7 - r8
            r3 = r7
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            int r8 = r8._charCount
            r9 = r3
            int r8 = r8 + r9
            r7._charCount = r8
            r7 = r3
            r0 = r7
            goto L_0x000c
        L_0x0105:
            r7 = r0
            int r7 = r7._length
            r8 = r0
            int r8 = r8._ptr
            int r7 = r7 - r8
            r5 = r7
            r7 = r5
            r8 = 4
            if (r7 >= r8) goto L_0x003d
            r7 = r0
            r8 = r5
            boolean r7 = r7.loadMore(r8)
            if (r7 != 0) goto L_0x003d
            r7 = -1
            r0 = r7
            goto L_0x000c
        L_0x011d:
            r7 = r0
            byte[] r7 = r7._buffer
            r8 = r5
            byte r7 = r7[r8]
            r8 = 255(0xff, float:3.57E-43)
            r7 = r7 & 255(0xff, float:3.57E-43)
            r8 = r0
            byte[] r8 = r8._buffer
            r9 = r5
            r10 = 1
            int r9 = r9 + 1
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 8
            int r8 = r8 << 8
            r7 = r7 | r8
            r8 = r0
            byte[] r8 = r8._buffer
            r9 = r5
            r10 = 2
            int r9 = r9 + 2
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = 16
            int r8 = r8 << 16
            r7 = r7 | r8
            r8 = r0
            byte[] r8 = r8._buffer
            r9 = r5
            r10 = 3
            int r9 = r9 + 3
            byte r8 = r8[r9]
            r9 = 24
            int r8 = r8 << 24
            r7 = r7 | r8
            r6 = r7
            goto L_0x0087
        L_0x015c:
            r7 = r1
            r8 = r4
            int r4 = r4 + 1
            r9 = r6
            char r9 = (char) r9
            r7[r8] = r9
            r7 = r0
            int r7 = r7._ptr
            r8 = r0
            int r8 = r8._length
            if (r7 < r8) goto L_0x016d
            goto L_0x00f3
        L_0x016d:
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.p005io.UTF32Reader.read(char[], int, int):int");
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        Throwable th;
        StringBuilder sb;
        int i3 = i;
        int i4 = this._byteCount + i3;
        int i5 = this._charCount;
        Throwable th2 = th;
        new StringBuilder();
        new CharConversionException(sb.append("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ").append(i3).append(", needed ").append(i2).append(", at char #").append(i5).append(", byte #").append(i4).append(")").toString());
        throw th2;
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        Throwable th;
        StringBuilder sb;
        int i3 = (this._byteCount + this._ptr) - 1;
        int i4 = this._charCount + i2;
        Throwable th2 = th;
        new StringBuilder();
        new CharConversionException(sb.append("Invalid UTF-32 character 0x").append(Integer.toHexString(i)).append(str).append(" at char #").append(i4).append(", byte #").append(i3).append(")").toString());
        throw th2;
    }

    private boolean loadMore(int i) throws IOException {
        int read;
        int i2 = i;
        this._byteCount += this._length - i2;
        if (i2 > 0) {
            if (this._ptr > 0) {
                for (int i3 = 0; i3 < i2; i3++) {
                    this._buffer[i3] = this._buffer[this._ptr + i3];
                }
                this._ptr = 0;
            }
            this._length = i2;
        } else {
            this._ptr = 0;
            int read2 = this._in == null ? -1 : this._in.read(this._buffer);
            if (read2 < 1) {
                this._length = 0;
                if (read2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read2;
        }
        while (this._length < 4) {
            if (this._in == null) {
                read = -1;
            } else {
                read = this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            }
            int i4 = read;
            if (i4 < 1) {
                if (i4 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += i4;
        }
        return true;
    }
}
