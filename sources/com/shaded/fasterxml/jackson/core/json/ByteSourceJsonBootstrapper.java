package com.shaded.fasterxml.jackson.core.json;

import android.support.p000v4.view.MotionEventCompat;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.shaded.fasterxml.jackson.core.JsonEncoding;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.format.InputAccessor;
import com.shaded.fasterxml.jackson.core.format.MatchStrength;
import com.shaded.fasterxml.jackson.core.p005io.IOContext;
import com.shaded.fasterxml.jackson.core.p005io.MergedStream;
import com.shaded.fasterxml.jackson.core.p005io.UTF32Reader;
import com.shaded.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.shaded.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ByteSourceJsonBootstrapper {
    static final byte UTF8_BOM_1 = -17;
    static final byte UTF8_BOM_2 = -69;
    static final byte UTF8_BOM_3 = -65;
    protected boolean _bigEndian = true;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar = 0;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    public ByteSourceJsonBootstrapper(IOContext iOContext, InputStream inputStream) {
        IOContext iOContext2 = iOContext;
        this._context = iOContext2;
        this._in = inputStream;
        this._inputBuffer = iOContext2.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public ByteSourceJsonBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        int i3 = i;
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i3;
        this._inputEnd = i3 + i2;
        this._inputProcessed = -i3;
        this._bufferRecyclable = false;
    }

    public JsonEncoding detectEncoding() throws IOException, JsonParseException {
        JsonEncoding jsonEncoding;
        Throwable th;
        boolean z = false;
        if (ensureLoaded(4)) {
            byte b = (this._inputBuffer[this._inputPtr] << 24) | ((this._inputBuffer[this._inputPtr + 1] & Ev3Constants.Opcode.TST) << 16) | ((this._inputBuffer[this._inputPtr + 2] & Ev3Constants.Opcode.TST) << 8) | (this._inputBuffer[this._inputPtr + 3] & Ev3Constants.Opcode.TST);
            if (handleBOM(b)) {
                z = true;
            } else if (checkUTF32(b)) {
                z = true;
            } else if (checkUTF16(b >>> 16)) {
                z = true;
            }
        } else if (ensureLoaded(2)) {
            if (checkUTF16(((this._inputBuffer[this._inputPtr] & Ev3Constants.Opcode.TST) << 8) | (this._inputBuffer[this._inputPtr + 1] & Ev3Constants.Opcode.TST))) {
                z = true;
            }
        }
        if (!z) {
            jsonEncoding = JsonEncoding.UTF8;
        } else {
            switch (this._bytesPerChar) {
                case 1:
                    jsonEncoding = JsonEncoding.UTF8;
                    break;
                case 2:
                    jsonEncoding = this._bigEndian ? JsonEncoding.UTF16_BE : JsonEncoding.UTF16_LE;
                    break;
                case 4:
                    jsonEncoding = this._bigEndian ? JsonEncoding.UTF32_BE : JsonEncoding.UTF32_LE;
                    break;
                default:
                    Throwable th2 = th;
                    new RuntimeException("Internal error");
                    throw th2;
            }
        }
        this._context.setEncoding(jsonEncoding);
        return jsonEncoding;
    }

    public Reader constructReader() throws IOException {
        InputStream inputStream;
        Reader reader;
        InputStream inputStream2;
        Reader reader2;
        Throwable th;
        JsonEncoding encoding = this._context.getEncoding();
        switch (encoding) {
            case UTF32_BE:
            case UTF32_LE:
                new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian());
                return reader2;
            case UTF16_BE:
            case UTF16_LE:
            case UTF8:
                InputStream inputStream3 = this._in;
                if (inputStream3 == null) {
                    new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
                    inputStream3 = inputStream2;
                } else if (this._inputPtr < this._inputEnd) {
                    new MergedStream(this._context, inputStream3, this._inputBuffer, this._inputPtr, this._inputEnd);
                    inputStream3 = inputStream;
                }
                new InputStreamReader(inputStream3, encoding.getJavaName());
                return reader;
            default:
                Throwable th2 = th;
                new RuntimeException("Internal error");
                throw th2;
        }
    }

    public JsonParser constructParser(int i, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer, boolean z, boolean z2) throws IOException, JsonParseException {
        JsonParser jsonParser;
        JsonParser jsonParser2;
        int i2 = i;
        ObjectCodec objectCodec2 = objectCodec;
        BytesToNameCanonicalizer bytesToNameCanonicalizer2 = bytesToNameCanonicalizer;
        CharsToNameCanonicalizer charsToNameCanonicalizer2 = charsToNameCanonicalizer;
        boolean z3 = z;
        boolean z4 = z2;
        if (detectEncoding() != JsonEncoding.UTF8 || !z3) {
            new ReaderBasedJsonParser(this._context, i2, constructReader(), objectCodec2, charsToNameCanonicalizer2.makeChild(z3, z4));
            return jsonParser;
        }
        new UTF8StreamJsonParser(this._context, i2, this._in, objectCodec2, bytesToNameCanonicalizer2.makeChild(z3, z4), this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        return jsonParser2;
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        InputAccessor inputAccessor2 = inputAccessor;
        if (!inputAccessor2.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor2.nextByte();
        if (nextByte == -17) {
            if (!inputAccessor2.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor2.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor2.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor2.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor2.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            nextByte = inputAccessor2.nextByte();
        }
        int skipSpace = skipSpace(inputAccessor2, nextByte);
        if (skipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (skipSpace == 123) {
            int skipSpace2 = skipSpace(inputAccessor2);
            if (skipSpace2 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace2 == 34 || skipSpace2 == 125) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.NO_MATCH;
        } else if (skipSpace == 91) {
            int skipSpace3 = skipSpace(inputAccessor2);
            if (skipSpace3 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace3 == 93 || skipSpace3 == 91) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        } else {
            MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
            if (skipSpace == 34) {
                return matchStrength;
            }
            if (skipSpace <= 57 && skipSpace >= 48) {
                return matchStrength;
            }
            if (skipSpace == 45) {
                int skipSpace4 = skipSpace(inputAccessor2);
                if (skipSpace4 < 0) {
                    return MatchStrength.INCONCLUSIVE;
                }
                return (skipSpace4 > 57 || skipSpace4 < 48) ? MatchStrength.NO_MATCH : matchStrength;
            } else if (skipSpace == 110) {
                return tryMatch(inputAccessor2, "ull", matchStrength);
            } else {
                if (skipSpace == 116) {
                    return tryMatch(inputAccessor2, "rue", matchStrength);
                }
                if (skipSpace == 102) {
                    return tryMatch(inputAccessor2, "alse", matchStrength);
                }
                return MatchStrength.NO_MATCH;
            }
        }
    }

    private static MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) throws IOException {
        InputAccessor inputAccessor2 = inputAccessor;
        String str2 = str;
        MatchStrength matchStrength2 = matchStrength;
        int length = str2.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor2.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor2.nextByte() != str2.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength2;
    }

    private static int skipSpace(InputAccessor inputAccessor) throws IOException {
        InputAccessor inputAccessor2 = inputAccessor;
        if (!inputAccessor2.hasMoreBytes()) {
            return -1;
        }
        return skipSpace(inputAccessor2, inputAccessor2.nextByte());
    }

    private static int skipSpace(InputAccessor inputAccessor, byte b) throws IOException {
        InputAccessor inputAccessor2 = inputAccessor;
        byte b2 = b;
        while (true) {
            byte b3 = b2 & Ev3Constants.Opcode.TST;
            if (b3 != 32 && b3 != 13 && b3 != 10 && b3 != 9) {
                return b3;
            }
            if (!inputAccessor2.hasMoreBytes()) {
                return -1;
            }
            b2 = inputAccessor2.nextByte();
            byte b4 = b2 & Ev3Constants.Opcode.TST;
        }
    }

    private boolean handleBOM(int i) throws IOException {
        int i2 = i;
        switch (i2) {
            case -16842752:
                break;
            case -131072:
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                this._bigEndian = false;
                return true;
            case 65279:
                this._bigEndian = true;
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                return true;
            case 65534:
                reportWeirdUCS4("2143");
                break;
        }
        reportWeirdUCS4("3412");
        int i3 = i2 >>> 16;
        if (i3 == 65279) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = true;
            return true;
        } else if (i3 == 65534) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = false;
            return true;
        } else if ((i2 >>> 8) != 15711167) {
            return false;
        } else {
            this._inputPtr += 3;
            this._bytesPerChar = 1;
            this._bigEndian = true;
            return true;
        }
    }

    private boolean checkUTF32(int i) throws IOException {
        int i2 = i;
        if ((i2 >> 8) == 0) {
            this._bigEndian = true;
        } else if ((i2 & 16777215) == 0) {
            this._bigEndian = false;
        } else if ((i2 & -16711681) == 0) {
            reportWeirdUCS4("3412");
        } else if ((i2 & -65281) != 0) {
            return false;
        } else {
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    private boolean checkUTF16(int i) {
        int i2 = i;
        if ((i2 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) == 0) {
            this._bigEndian = true;
        } else if ((i2 & 255) != 0) {
            return false;
        } else {
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private void reportWeirdUCS4(String str) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new CharConversionException(sb.append("Unsupported UCS-4 endianness (").append(str).append(") detected").toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public boolean ensureLoaded(int i) throws IOException {
        int read;
        int i2 = i;
        int i3 = this._inputEnd - this._inputPtr;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return true;
            }
            if (this._in == null) {
                read = -1;
            } else {
                read = this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            }
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i3 = i4 + read;
        }
    }
}
