package com.shaded.fasterxml.jackson.core.p005io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* renamed from: com.shaded.fasterxml.jackson.core.io.UTF8Writer */
public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    private final IOContext _context;
    private OutputStream _out;
    private byte[] _outBuffer;
    private final int _outBufferEnd;
    private int _outPtr;
    private int _surrogate = 0;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        IOContext iOContext2 = iOContext;
        this._context = iOContext2;
        this._out = outputStream;
        this._outBuffer = iOContext2.allocWriteEncodingBuffer();
        this._outBufferEnd = this._outBuffer.length - 4;
        this._outPtr = 0;
    }

    public Writer append(char c) throws IOException {
        write((int) c);
        return this;
    }

    public void close() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            OutputStream outputStream = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream.close();
            int i = this._surrogate;
            this._surrogate = 0;
            if (i > 0) {
                illegalSurrogate(i);
            }
        }
    }

    public void flush() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    public void write(char[] cArr) throws IOException {
        char[] cArr2 = cArr;
        write(cArr2, 0, cArr2.length);
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        int i3;
        char[] cArr2 = cArr;
        int i4 = i;
        int i5 = i2;
        if (i5 >= 2) {
            if (this._surrogate > 0) {
                int i6 = i4;
                i4++;
                i5--;
                write(convertSurrogate(cArr2[i6]));
            }
            int i7 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i8 = this._outBufferEnd;
            int i9 = i5 + i3;
            while (i3 < i9) {
                if (i7 >= i8) {
                    this._out.write(bArr, 0, i7);
                    i7 = 0;
                }
                int i10 = i3;
                i3++;
                char c = cArr2[i10];
                if (c < 128) {
                    int i11 = i7;
                    i7++;
                    bArr[i11] = (byte) c;
                    int i12 = i9 - i3;
                    int i13 = i8 - i7;
                    if (i12 > i13) {
                        i12 = i13;
                    }
                    int i14 = i12 + i3;
                    while (true) {
                        if (i3 >= i14) {
                            continue;
                            break;
                        }
                        int i15 = i3;
                        i3++;
                        c = cArr2[i15];
                        if (c >= 128) {
                            break;
                        }
                        int i16 = i7;
                        i7++;
                        bArr[i16] = (byte) c;
                    }
                }
                if (c < 2048) {
                    int i17 = i7;
                    int i18 = i7 + 1;
                    bArr[i17] = (byte) (192 | (c >> 6));
                    int i19 = i18;
                    i7 = i18 + 1;
                    bArr[i19] = (byte) (128 | (c & '?'));
                } else if (c < SURR1_FIRST || c > SURR2_LAST) {
                    int i20 = i7;
                    int i21 = i7 + 1;
                    bArr[i20] = (byte) (224 | (c >> 12));
                    int i22 = i21;
                    int i23 = i21 + 1;
                    bArr[i22] = (byte) (128 | ((c >> 6) & 63));
                    int i24 = i23;
                    i7 = i23 + 1;
                    bArr[i24] = (byte) (128 | (c & '?'));
                } else {
                    if (c > SURR1_LAST) {
                        this._outPtr = i7;
                        illegalSurrogate(c);
                    }
                    this._surrogate = c;
                    if (i3 >= i9) {
                        break;
                    }
                    int i25 = i3;
                    i3++;
                    int convertSurrogate = convertSurrogate(cArr2[i25]);
                    if (convertSurrogate > 1114111) {
                        this._outPtr = i7;
                        illegalSurrogate(convertSurrogate);
                    }
                    int i26 = i7;
                    int i27 = i7 + 1;
                    bArr[i26] = (byte) (240 | (convertSurrogate >> 18));
                    int i28 = i27;
                    int i29 = i27 + 1;
                    bArr[i28] = (byte) (128 | ((convertSurrogate >> 12) & 63));
                    int i30 = i29;
                    int i31 = i29 + 1;
                    bArr[i30] = (byte) (128 | ((convertSurrogate >> 6) & 63));
                    int i32 = i31;
                    i7 = i31 + 1;
                    bArr[i32] = (byte) (128 | (convertSurrogate & 63));
                }
            }
            this._outPtr = i7;
        } else if (i5 == 1) {
            write((int) cArr2[i4]);
        }
    }

    public void write(int i) throws IOException {
        int i2;
        int i3 = i;
        if (this._surrogate > 0) {
            i3 = convertSurrogate(i3);
        } else if (i3 >= SURR1_FIRST && i3 <= SURR2_LAST) {
            if (i3 > SURR1_LAST) {
                illegalSurrogate(i3);
            }
            this._surrogate = i3;
            return;
        }
        if (this._outPtr >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, this._outPtr);
            this._outPtr = 0;
        }
        if (i3 < 128) {
            byte[] bArr = this._outBuffer;
            int i4 = this._outPtr;
            int i5 = i4 + 1;
            this._outPtr = i5;
            bArr[i4] = (byte) i3;
            return;
        }
        int i6 = this._outPtr;
        if (i3 < 2048) {
            int i7 = i6;
            int i8 = i6 + 1;
            this._outBuffer[i7] = (byte) (192 | (i3 >> 6));
            int i9 = i8;
            i2 = i8 + 1;
            this._outBuffer[i9] = (byte) (128 | (i3 & 63));
        } else if (i3 <= 65535) {
            int i10 = i6;
            int i11 = i6 + 1;
            this._outBuffer[i10] = (byte) (224 | (i3 >> 12));
            int i12 = i11;
            int i13 = i11 + 1;
            this._outBuffer[i12] = (byte) (128 | ((i3 >> 6) & 63));
            int i14 = i13;
            i2 = i13 + 1;
            this._outBuffer[i14] = (byte) (128 | (i3 & 63));
        } else {
            if (i3 > 1114111) {
                illegalSurrogate(i3);
            }
            int i15 = i6;
            int i16 = i6 + 1;
            this._outBuffer[i15] = (byte) (240 | (i3 >> 18));
            int i17 = i16;
            int i18 = i16 + 1;
            this._outBuffer[i17] = (byte) (128 | ((i3 >> 12) & 63));
            int i19 = i18;
            int i20 = i18 + 1;
            this._outBuffer[i19] = (byte) (128 | ((i3 >> 6) & 63));
            int i21 = i20;
            i2 = i20 + 1;
            this._outBuffer[i21] = (byte) (128 | (i3 & 63));
        }
        this._outPtr = i2;
    }

    public void write(String str) throws IOException {
        String str2 = str;
        write(str2, 0, str2.length());
    }

    public void write(String str, int i, int i2) throws IOException {
        int i3;
        String str2 = str;
        int i4 = i;
        int i5 = i2;
        if (i5 >= 2) {
            if (this._surrogate > 0) {
                int i6 = i4;
                i4++;
                i5--;
                write(convertSurrogate(str2.charAt(i6)));
            }
            int i7 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i8 = this._outBufferEnd;
            int i9 = i5 + i3;
            while (i3 < i9) {
                if (i7 >= i8) {
                    this._out.write(bArr, 0, i7);
                    i7 = 0;
                }
                int i10 = i3;
                i3++;
                char charAt = str2.charAt(i10);
                if (charAt < 128) {
                    int i11 = i7;
                    i7++;
                    bArr[i11] = (byte) charAt;
                    int i12 = i9 - i3;
                    int i13 = i8 - i7;
                    if (i12 > i13) {
                        i12 = i13;
                    }
                    int i14 = i12 + i3;
                    while (true) {
                        if (i3 >= i14) {
                            continue;
                            break;
                        }
                        int i15 = i3;
                        i3++;
                        charAt = str2.charAt(i15);
                        if (charAt >= 128) {
                            break;
                        }
                        int i16 = i7;
                        i7++;
                        bArr[i16] = (byte) charAt;
                    }
                }
                if (charAt < 2048) {
                    int i17 = i7;
                    int i18 = i7 + 1;
                    bArr[i17] = (byte) (192 | (charAt >> 6));
                    int i19 = i18;
                    i7 = i18 + 1;
                    bArr[i19] = (byte) (128 | (charAt & '?'));
                } else if (charAt < SURR1_FIRST || charAt > SURR2_LAST) {
                    int i20 = i7;
                    int i21 = i7 + 1;
                    bArr[i20] = (byte) (224 | (charAt >> 12));
                    int i22 = i21;
                    int i23 = i21 + 1;
                    bArr[i22] = (byte) (128 | ((charAt >> 6) & 63));
                    int i24 = i23;
                    i7 = i23 + 1;
                    bArr[i24] = (byte) (128 | (charAt & '?'));
                } else {
                    if (charAt > SURR1_LAST) {
                        this._outPtr = i7;
                        illegalSurrogate(charAt);
                    }
                    this._surrogate = charAt;
                    if (i3 >= i9) {
                        break;
                    }
                    int i25 = i3;
                    i3++;
                    int convertSurrogate = convertSurrogate(str2.charAt(i25));
                    if (convertSurrogate > 1114111) {
                        this._outPtr = i7;
                        illegalSurrogate(convertSurrogate);
                    }
                    int i26 = i7;
                    int i27 = i7 + 1;
                    bArr[i26] = (byte) (240 | (convertSurrogate >> 18));
                    int i28 = i27;
                    int i29 = i27 + 1;
                    bArr[i28] = (byte) (128 | ((convertSurrogate >> 12) & 63));
                    int i30 = i29;
                    int i31 = i29 + 1;
                    bArr[i30] = (byte) (128 | ((convertSurrogate >> 6) & 63));
                    int i32 = i31;
                    i7 = i31 + 1;
                    bArr[i32] = (byte) (128 | (convertSurrogate & 63));
                }
            }
            this._outPtr = i7;
        } else if (i5 == 1) {
            write((int) str2.charAt(i4));
        }
    }

    /* access modifiers changed from: protected */
    public int convertSurrogate(int i) throws IOException {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        int i3 = this._surrogate;
        this._surrogate = 0;
        if (i2 >= SURR2_FIRST && i2 <= SURR2_LAST) {
            return 65536 + ((i3 - SURR1_FIRST) << 10) + (i2 - SURR2_FIRST);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IOException(sb.append("Broken surrogate pair: first char 0x").append(Integer.toHexString(i3)).append(", second 0x").append(Integer.toHexString(i2)).append("; illegal combination").toString());
        throw th2;
    }

    protected static void illegalSurrogate(int i) throws IOException {
        Throwable th;
        Throwable th2 = th;
        new IOException(illegalSurrogateDesc(i));
        throw th2;
    }

    protected static String illegalSurrogateDesc(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        int i2 = i;
        if (i2 > 1114111) {
            new StringBuilder();
            return sb4.append("Illegal character point (0x").append(Integer.toHexString(i2)).append(") to output; max is 0x10FFFF as per RFC 4627").toString();
        } else if (i2 < SURR1_FIRST) {
            new StringBuilder();
            return sb.append("Illegal character point (0x").append(Integer.toHexString(i2)).append(") to output").toString();
        } else if (i2 <= SURR1_LAST) {
            new StringBuilder();
            return sb3.append("Unmatched first part of surrogate pair (0x").append(Integer.toHexString(i2)).append(")").toString();
        } else {
            new StringBuilder();
            return sb2.append("Unmatched second part of surrogate pair (0x").append(Integer.toHexString(i2)).append(")").toString();
        }
    }
}
