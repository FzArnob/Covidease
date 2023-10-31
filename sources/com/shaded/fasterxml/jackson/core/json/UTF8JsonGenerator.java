package com.shaded.fasterxml.jackson.core.json;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;
import com.shaded.fasterxml.jackson.core.p005io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UTF8JsonGenerator extends JsonGeneratorImpl {
    private static final byte BYTE_0 = 48;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COLON = 58;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_QUOTE = 34;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_RCURLY = 125;
    private static final byte BYTE_u = 117;
    private static final byte[] FALSE_BYTES = {Ev3Constants.Opcode.JR_LT32, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_NEQF, Ev3Constants.Opcode.JR_LT16};
    static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final int MAX_BYTES_TO_BUFFER = 512;
    private static final byte[] NULL_BYTES = {Ev3Constants.Opcode.JR_EQ32, 117, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_EQ8};
    protected static final int SURR1_FIRST = 55296;
    protected static final int SURR1_LAST = 56319;
    protected static final int SURR2_FIRST = 56320;
    protected static final int SURR2_LAST = 57343;
    private static final byte[] TRUE_BYTES = {Ev3Constants.Opcode.JR_LTEQ8, Ev3Constants.Opcode.JR_NEQ32, 117, Ev3Constants.Opcode.JR_LT16};
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _entityBuffer;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail = 0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UTF8JsonGenerator(com.shaded.fasterxml.jackson.core.p005io.IOContext r10, int r11, com.shaded.fasterxml.jackson.core.ObjectCodec r12, java.io.OutputStream r13) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = 0
            r5._outputTail = r6
            r5 = r0
            r6 = r4
            r5._outputStream = r6
            r5 = r0
            r6 = 1
            r5._bufferRecyclable = r6
            r5 = r0
            r6 = r1
            byte[] r6 = r6.allocWriteEncodingBuffer()
            r5._outputBuffer = r6
            r5 = r0
            r6 = r0
            byte[] r6 = r6._outputBuffer
            int r6 = r6.length
            r5._outputEnd = r6
            r5 = r0
            r6 = r0
            int r6 = r6._outputEnd
            r7 = 3
            int r6 = r6 >> 3
            r5._outputMaxContiguous = r6
            r5 = r0
            r6 = r1
            char[] r6 = r6.allocConcatBuffer()
            r5._charBuffer = r6
            r5 = r0
            r6 = r0
            char[] r6 = r6._charBuffer
            int r6 = r6.length
            r5._charBufferLength = r6
            r5 = r0
            com.shaded.fasterxml.jackson.core.JsonGenerator$Feature r6 = com.shaded.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII
            boolean r5 = r5.isEnabled(r6)
            if (r5 == 0) goto L_0x004f
            r5 = r0
            r6 = 127(0x7f, float:1.78E-43)
            com.shaded.fasterxml.jackson.core.JsonGenerator r5 = r5.setHighestNonEscapedChar(r6)
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator.<init>(com.shaded.fasterxml.jackson.core.io.IOContext, int, com.shaded.fasterxml.jackson.core.ObjectCodec, java.io.OutputStream):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UTF8JsonGenerator(com.shaded.fasterxml.jackson.core.p005io.IOContext r13, int r14, com.shaded.fasterxml.jackson.core.ObjectCodec r15, java.io.OutputStream r16, byte[] r17, int r18, boolean r19) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            r7 = r19
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r8.<init>(r9, r10, r11)
            r8 = r0
            r9 = 0
            r8._outputTail = r9
            r8 = r0
            r9 = r4
            r8._outputStream = r9
            r8 = r0
            r9 = r7
            r8._bufferRecyclable = r9
            r8 = r0
            r9 = r6
            r8._outputTail = r9
            r8 = r0
            r9 = r5
            r8._outputBuffer = r9
            r8 = r0
            r9 = r0
            byte[] r9 = r9._outputBuffer
            int r9 = r9.length
            r8._outputEnd = r9
            r8 = r0
            r9 = r0
            int r9 = r9._outputEnd
            r10 = 3
            int r9 = r9 >> 3
            r8._outputMaxContiguous = r9
            r8 = r0
            r9 = r1
            char[] r9 = r9.allocConcatBuffer()
            r8._charBuffer = r9
            r8 = r0
            r9 = r0
            char[] r9 = r9._charBuffer
            int r9 = r9.length
            r8._charBufferLength = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator.<init>(com.shaded.fasterxml.jackson.core.io.IOContext, int, com.shaded.fasterxml.jackson.core.ObjectCodec, java.io.OutputStream, byte[], int, boolean):void");
    }

    public Object getOutputTarget() {
        return this._outputStream;
    }

    public final void writeFieldName(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        int writeFieldName = this._writeContext.writeFieldName(str2);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str2, writeFieldName == 1);
            return;
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            bArr[i] = 44;
        }
        _writeFieldName(str2);
    }

    public final void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        int writeFieldName = this._writeContext.writeFieldName(serializableString2.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString2, writeFieldName == 1);
            return;
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            bArr[i] = 44;
        }
        _writeFieldName(serializableString2);
    }

    public final void writeStartArray() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 91;
    }

    public final void writeEndArray() throws IOException, JsonGenerationException {
        StringBuilder sb;
        if (!this._writeContext.inArray()) {
            new StringBuilder();
            _reportError(sb.append("Current context not an ARRAY but ").append(this._writeContext.getTypeDesc()).toString());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            bArr[i] = 93;
        }
        this._writeContext = this._writeContext.getParent();
    }

    public final void writeStartObject() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 123;
    }

    public final void writeEndObject() throws IOException, JsonGenerationException {
        StringBuilder sb;
        if (!this._writeContext.inObject()) {
            new StringBuilder();
            _reportError(sb.append("Current context not an object but ").append(this._writeContext.getTypeDesc()).toString());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            bArr[i] = 125;
        }
        this._writeContext = this._writeContext.getParent();
    }

    /* access modifiers changed from: protected */
    public final void _writeFieldName(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            _writeStringSegments(str2);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        int length = str2.length();
        if (length <= this._charBufferLength) {
            str2.getChars(0, length, this._charBuffer, 0);
            if (length <= this._outputMaxContiguous) {
                if (this._outputTail + length > this._outputEnd) {
                    _flushBuffer();
                }
                _writeStringSegment(this._charBuffer, 0, length);
            } else {
                _writeStringSegments(this._charBuffer, 0, length);
            }
        } else {
            _writeStringSegments(str2);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    /* access modifiers changed from: protected */
    public final void _writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            int appendQuotedUTF8 = serializableString2.appendQuotedUTF8(this._outputBuffer, this._outputTail);
            if (appendQuotedUTF8 < 0) {
                _writeBytes(serializableString2.asQuotedUTF8());
                return;
            }
            this._outputTail += appendQuotedUTF8;
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        int appendQuotedUTF82 = serializableString2.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF82 < 0) {
            _writeBytes(serializableString2.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF82;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    /* access modifiers changed from: protected */
    public final void _writePPFieldName(String str, boolean z) throws IOException, JsonGenerationException {
        String str2 = str;
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            bArr[i] = 34;
            int length = str2.length();
            if (length <= this._charBufferLength) {
                str2.getChars(0, length, this._charBuffer, 0);
                if (length <= this._outputMaxContiguous) {
                    if (this._outputTail + length > this._outputEnd) {
                        _flushBuffer();
                    }
                    _writeStringSegment(this._charBuffer, 0, length);
                } else {
                    _writeStringSegments(this._charBuffer, 0, length);
                }
            } else {
                _writeStringSegments(str2);
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            int i4 = i3 + 1;
            this._outputTail = i4;
            bArr2[i3] = 34;
            return;
        }
        _writeStringSegments(str2);
    }

    /* access modifiers changed from: protected */
    public final void _writePPFieldName(SerializableString serializableString, boolean z) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        boolean isEnabled = isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES);
        if (isEnabled) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            bArr[i] = 34;
        }
        _writeBytes(serializableString2.asQuotedUTF8());
        if (isEnabled) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            int i4 = i3 + 1;
            this._outputTail = i4;
            bArr2[i3] = 34;
        }
    }

    public void writeString(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        _verifyValueWrite("write text value");
        if (str2 == null) {
            _writeNull();
            return;
        }
        int length = str2.length();
        if (length > this._charBufferLength) {
            _writeLongString(str2);
            return;
        }
        str2.getChars(0, length, this._charBuffer, 0);
        if (length > this._outputMaxContiguous) {
            _writeLongString(this._charBuffer, 0, length);
            return;
        }
        if (this._outputTail + length >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        _writeStringSegment(this._charBuffer, 0, length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    private void _writeLongString(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        _writeStringSegments(str2);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    private void _writeLongString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = 34;
        _writeStringSegments(this._charBuffer, 0, i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        bArr2[i7] = 34;
    }

    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = 34;
        if (i4 <= this._outputMaxContiguous) {
            if (this._outputTail + i4 > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr2, i3, i4);
        } else {
            _writeStringSegments(cArr2, i3, i4);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        bArr2[i7] = 34;
    }

    public final void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        int appendQuotedUTF8 = serializableString2.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString2.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr3[i5] = 34;
        _writeBytes(bArr2, i3, i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr4 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        bArr4[i7] = 34;
    }

    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr3[i5] = 34;
        if (i4 <= this._outputMaxContiguous) {
            _writeUTF8Segment(bArr2, i3, i4);
        } else {
            _writeUTF8Segments(bArr2, i3, i4);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr4 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        bArr4[i7] = 34;
    }

    public void writeRaw(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        int i = 0;
        int length = str2.length();
        while (true) {
            int i2 = length;
            if (i2 > 0) {
                char[] cArr = this._charBuffer;
                int length2 = cArr.length;
                int i3 = i2 < length2 ? i2 : length2;
                str2.getChars(i, i + i3, cArr, 0);
                writeRaw(cArr, 0, i3);
                i += i3;
                length = i2 - i3;
            } else {
                return;
            }
        }
    }

    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        while (i4 > 0) {
            char[] cArr = this._charBuffer;
            int length = cArr.length;
            int i5 = i4 < length ? i4 : length;
            str2.getChars(i3, i3 + i5, cArr, 0);
            writeRaw(cArr, 0, i5);
            i3 += i5;
            i4 -= i5;
        }
    }

    public void writeRaw(SerializableString serializableString) throws IOException, JsonGenerationException {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        if (asUnquotedUTF8.length > 0) {
            _writeBytes(asUnquotedUTF8);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        r6 = r2;
        r2 = r2 + 1;
        r4 = r1[r6];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        if (r4 >= 2048) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        r5 = r0._outputBuffer;
        r6 = r0;
        r10 = r6;
        r6 = r10;
        r11 = r10._outputTail;
        r8 = r11 + 1;
        r6._outputTail = r8;
        r5[r11] = (byte) (192 | (r4 >> 6));
        r5 = r0._outputBuffer;
        r6 = r0;
        r10 = r6;
        r6 = r10;
        r11 = r10._outputTail;
        r8 = r11 + 1;
        r6._outputTail = r8;
        r5[r11] = (byte) (128 | (r4 & '?'));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x009f, code lost:
        r5 = _outputRawMultiByteChar(r4, r1, r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeRaw(char[] r13, int r14, int r15) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r5 = r3
            r6 = r3
            int r5 = r5 + r6
            r6 = r3
            int r5 = r5 + r6
            r4 = r5
            r5 = r0
            int r5 = r5._outputTail
            r6 = r4
            int r5 = r5 + r6
            r6 = r0
            int r6 = r6._outputEnd
            if (r5 <= r6) goto L_0x0026
            r5 = r0
            int r5 = r5._outputEnd
            r6 = r4
            if (r5 >= r6) goto L_0x0022
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5._writeSegmentedRaw(r6, r7, r8)
        L_0x0021:
            return
        L_0x0022:
            r5 = r0
            r5._flushBuffer()
        L_0x0026:
            r5 = r3
            r6 = r2
            int r5 = r5 + r6
            r3 = r5
        L_0x002a:
            r5 = r2
            r6 = r3
            if (r5 >= r6) goto L_0x009d
        L_0x002e:
            r5 = r1
            r6 = r2
            char r5 = r5[r6]
            r4 = r5
            r5 = r4
            r6 = 127(0x7f, float:1.78E-43)
            if (r5 <= r6) goto L_0x0080
            r5 = r1
            r6 = r2
            int r2 = r2 + 1
            char r5 = r5[r6]
            r4 = r5
            r5 = r4
            r6 = 2048(0x800, float:2.87E-42)
            if (r5 >= r6) goto L_0x009f
            r5 = r0
            byte[] r5 = r5._outputBuffer
            r6 = r0
            r10 = r6
            r6 = r10
            r7 = r10
            int r7 = r7._outputTail
            r10 = r6
            r11 = r7
            r6 = r11
            r7 = r10
            r8 = r11
            r9 = 1
            int r8 = r8 + 1
            r7._outputTail = r8
            r7 = 192(0xc0, float:2.69E-43)
            r8 = r4
            r9 = 6
            int r8 = r8 >> 6
            r7 = r7 | r8
            byte r7 = (byte) r7
            r5[r6] = r7
            r5 = r0
            byte[] r5 = r5._outputBuffer
            r6 = r0
            r10 = r6
            r6 = r10
            r7 = r10
            int r7 = r7._outputTail
            r10 = r6
            r11 = r7
            r6 = r11
            r7 = r10
            r8 = r11
            r9 = 1
            int r8 = r8 + 1
            r7._outputTail = r8
            r7 = 128(0x80, float:1.794E-43)
            r8 = r4
            r9 = 63
            r8 = r8 & 63
            r7 = r7 | r8
            byte r7 = (byte) r7
            r5[r6] = r7
        L_0x007f:
            goto L_0x002a
        L_0x0080:
            r5 = r0
            byte[] r5 = r5._outputBuffer
            r6 = r0
            r10 = r6
            r6 = r10
            r7 = r10
            int r7 = r7._outputTail
            r10 = r6
            r11 = r7
            r6 = r11
            r7 = r10
            r8 = r11
            r9 = 1
            int r8 = r8 + 1
            r7._outputTail = r8
            r7 = r4
            byte r7 = (byte) r7
            r5[r6] = r7
            int r2 = r2 + 1
            r5 = r2
            r6 = r3
            if (r5 < r6) goto L_0x009e
        L_0x009d:
            goto L_0x0021
        L_0x009e:
            goto L_0x002e
        L_0x009f:
            r5 = r0
            r6 = r4
            r7 = r1
            r8 = r2
            r9 = r3
            int r5 = r5._outputRawMultiByteChar(r6, r7, r8, r9)
            goto L_0x007f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator.writeRaw(char[], int, int):void");
    }

    public void writeRaw(char c) throws IOException, JsonGenerationException {
        char c2 = c;
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        if (c2 <= 127) {
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            bArr[i] = (byte) c2;
        } else if (c2 < 2048) {
            int i3 = this._outputTail;
            int i4 = i3 + 1;
            this._outputTail = i4;
            bArr[i3] = (byte) (192 | (c2 >> 6));
            int i5 = this._outputTail;
            int i6 = i5 + 1;
            this._outputTail = i6;
            bArr[i5] = (byte) (128 | (c2 & '?'));
        } else {
            int _outputRawMultiByteChar = _outputRawMultiByteChar(c2, (char[]) null, 0, 0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        r8 = r0;
        r12 = r8;
        r8 = r12;
        r13 = r12._outputTail;
        r10 = r13 + 1;
        r8._outputTail = r10;
        r5[r13] = (byte) (192 | (r6 >> 6));
        r8 = r0;
        r12 = r8;
        r8 = r12;
        r13 = r12._outputTail;
        r10 = r13 + 1;
        r8._outputTail = r10;
        r5[r13] = (byte) (128 | (r6 & '?'));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0097, code lost:
        r7 = _outputRawMultiByteChar(r6, r1, r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0025, code lost:
        if ((r0._outputTail + 3) < r0._outputEnd) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0027, code lost:
        _flushBuffer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002b, code lost:
        r8 = r2;
        r2 = r2 + 1;
        r6 = r1[r8];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        if (r6 >= 2048) goto L_0x0097;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeSegmentedRaw(char[] r15, int r16, int r17) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r7 = r0
            int r7 = r7._outputEnd
            r4 = r7
            r7 = r0
            byte[] r7 = r7._outputBuffer
            r5 = r7
        L_0x000e:
            r7 = r2
            r8 = r3
            if (r7 >= r8) goto L_0x0094
        L_0x0012:
            r7 = r1
            r8 = r2
            char r7 = r7[r8]
            r6 = r7
            r7 = r6
            r8 = 128(0x80, float:1.794E-43)
            if (r7 < r8) goto L_0x006f
            r7 = r0
            int r7 = r7._outputTail
            r8 = 3
            int r7 = r7 + 3
            r8 = r0
            int r8 = r8._outputEnd
            if (r7 < r8) goto L_0x002b
            r7 = r0
            r7._flushBuffer()
        L_0x002b:
            r7 = r1
            r8 = r2
            int r2 = r2 + 1
            char r7 = r7[r8]
            r6 = r7
            r7 = r6
            r8 = 2048(0x800, float:2.87E-42)
            if (r7 >= r8) goto L_0x0097
            r7 = r5
            r8 = r0
            r12 = r8
            r8 = r12
            r9 = r12
            int r9 = r9._outputTail
            r12 = r8
            r13 = r9
            r8 = r13
            r9 = r12
            r10 = r13
            r11 = 1
            int r10 = r10 + 1
            r9._outputTail = r10
            r9 = 192(0xc0, float:2.69E-43)
            r10 = r6
            r11 = 6
            int r10 = r10 >> 6
            r9 = r9 | r10
            byte r9 = (byte) r9
            r7[r8] = r9
            r7 = r5
            r8 = r0
            r12 = r8
            r8 = r12
            r9 = r12
            int r9 = r9._outputTail
            r12 = r8
            r13 = r9
            r8 = r13
            r9 = r12
            r10 = r13
            r11 = 1
            int r10 = r10 + 1
            r9._outputTail = r10
            r9 = 128(0x80, float:1.794E-43)
            r10 = r6
            r11 = 63
            r10 = r10 & 63
            r9 = r9 | r10
            byte r9 = (byte) r9
            r7[r8] = r9
        L_0x006e:
            goto L_0x000e
        L_0x006f:
            r7 = r0
            int r7 = r7._outputTail
            r8 = r4
            if (r7 < r8) goto L_0x0079
            r7 = r0
            r7._flushBuffer()
        L_0x0079:
            r7 = r5
            r8 = r0
            r12 = r8
            r8 = r12
            r9 = r12
            int r9 = r9._outputTail
            r12 = r8
            r13 = r9
            r8 = r13
            r9 = r12
            r10 = r13
            r11 = 1
            int r10 = r10 + 1
            r9._outputTail = r10
            r9 = r6
            byte r9 = (byte) r9
            r7[r8] = r9
            int r2 = r2 + 1
            r7 = r2
            r8 = r3
            if (r7 < r8) goto L_0x0095
        L_0x0094:
            return
        L_0x0095:
            goto L_0x0012
        L_0x0097:
            r7 = r0
            r8 = r6
            r9 = r1
            r10 = r2
            r11 = r3
            int r7 = r7._outputRawMultiByteChar(r8, r9, r10, r11)
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator._writeSegmentedRaw(char[], int, int):void");
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        Base64Variant base64Variant2 = base64Variant;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        _verifyValueWrite("write binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr3[i5] = 34;
        _writeBinary(base64Variant2, bArr2, i3, i3 + i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr4 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        bArr4[i7] = 34;
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException, JsonGenerationException {
        int i2;
        StringBuilder sb;
        Base64Variant base64Variant2 = base64Variant;
        InputStream inputStream2 = inputStream;
        int i3 = i;
        _verifyValueWrite("write binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        bArr[i4] = 34;
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        if (i3 < 0) {
            try {
                i2 = _writeBinary(base64Variant2, inputStream2, allocBase64Buffer);
            } catch (Throwable th) {
                Throwable th2 = th;
                this._ioContext.releaseBase64Buffer(allocBase64Buffer);
                throw th2;
            }
        } else {
            int _writeBinary = _writeBinary(base64Variant2, inputStream2, allocBase64Buffer, i3);
            if (_writeBinary > 0) {
                new StringBuilder();
                _reportError(sb.append("Too few bytes available: missing ").append(_writeBinary).append(" bytes (out of ").append(i3).append(")").toString());
            }
            i2 = i3;
        }
        this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i6 = this._outputTail;
        int i7 = i6 + 1;
        this._outputTail = i7;
        bArr2[i6] = 34;
        return i2;
    }

    public void writeNumber(short s) throws IOException, JsonGenerationException {
        short s2 = s;
        _verifyValueWrite("write number");
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s2);
            return;
        }
        this._outputTail = NumberOutput.outputInt((int) s2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedShort(short s) throws IOException {
        short s2 = s;
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        this._outputTail = NumberOutput.outputInt((int) s2, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    public void writeNumber(int i) throws IOException, JsonGenerationException {
        int i2 = i;
        _verifyValueWrite("write number");
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i2);
            return;
        }
        this._outputTail = NumberOutput.outputInt(i2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedInt(int i) throws IOException {
        int i2 = i;
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = 34;
        this._outputTail = NumberOutput.outputInt(i2, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr2[i5] = 34;
    }

    public void writeNumber(long j) throws IOException, JsonGenerationException {
        long j2 = j;
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(j2);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(j2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedLong(long j) throws IOException {
        long j2 = j;
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        this._outputTail = NumberOutput.outputLong(j2, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        BigInteger bigInteger2 = bigInteger;
        _verifyValueWrite("write number");
        if (bigInteger2 == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigInteger2);
        } else {
            writeRaw(bigInteger2.toString());
        }
    }

    public void writeNumber(double d) throws IOException, JsonGenerationException {
        double d2 = d;
        if (this._cfgNumbersAsStrings || ((Double.isNaN(d2) || Double.isInfinite(d2)) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(d2));
            return;
        }
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(d2));
    }

    public void writeNumber(float f) throws IOException, JsonGenerationException {
        float f2 = f;
        if (this._cfgNumbersAsStrings || ((Float.isNaN(f2) || Float.isInfinite(f2)) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(f2));
            return;
        }
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(f2));
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        BigDecimal bigDecimal2 = bigDecimal;
        _verifyValueWrite("write number");
        if (bigDecimal2 == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigDecimal2);
        } else {
            writeRaw(bigDecimal2.toString());
        }
    }

    public void writeNumber(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(str2);
        } else {
            writeRaw(str2);
        }
    }

    private void _writeQuotedRaw(Object obj) throws IOException {
        Object obj2 = obj;
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        bArr[i] = 34;
        writeRaw(obj2.toString());
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr2[i3] = 34;
    }

    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        boolean z2 = z;
        _verifyValueWrite("write boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = z2 ? TRUE_BYTES : FALSE_BYTES;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    public void writeNull() throws IOException, JsonGenerationException {
        _verifyValueWrite("write null value");
        _writeNull();
    }

    /* access modifiers changed from: protected */
    public final void _verifyValueWrite(String str) throws IOException, JsonGenerationException {
        byte b;
        StringBuilder sb;
        String str2 = str;
        int writeValue = this._writeContext.writeValue();
        if (writeValue == 5) {
            new StringBuilder();
            _reportError(sb.append("Can not ").append(str2).append(", expecting field name").toString());
        }
        if (this._cfgPrettyPrinter == null) {
            switch (writeValue) {
                case 1:
                    b = 44;
                    break;
                case 2:
                    b = 58;
                    break;
                case 3:
                    if (this._rootValueSeparator != null) {
                        byte[] asUnquotedUTF8 = this._rootValueSeparator.asUnquotedUTF8();
                        if (asUnquotedUTF8.length > 0) {
                            _writeBytes(asUnquotedUTF8);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            this._outputBuffer[this._outputTail] = b;
            this._outputTail++;
            return;
        }
        _verifyPrettyValueWrite(str2, writeValue);
    }

    /* access modifiers changed from: protected */
    public final void _verifyPrettyValueWrite(String str, int i) throws IOException, JsonGenerationException {
        String str2 = str;
        switch (i) {
            case 0:
                if (this._writeContext.inArray()) {
                    this._cfgPrettyPrinter.beforeArrayValues(this);
                    return;
                } else if (this._writeContext.inObject()) {
                    this._cfgPrettyPrinter.beforeObjectEntries(this);
                    return;
                } else {
                    return;
                }
            case 1:
                this._cfgPrettyPrinter.writeArrayValueSeparator(this);
                return;
            case 2:
                this._cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
                return;
            case 3:
                this._cfgPrettyPrinter.writeRootValueSeparator(this);
                return;
            default:
                _throwInternal();
                return;
        }
    }

    public final void flush() throws IOException {
        _flushBuffer();
        if (this._outputStream != null && isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._outputStream.flush();
        }
    }

    public void close() throws IOException {
        super.close();
        if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonWriteContext outputContext = getOutputContext();
                if (!outputContext.inArray()) {
                    if (!outputContext.inObject()) {
                        break;
                    }
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        _flushBuffer();
        if (this._outputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this._outputStream.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._outputStream.flush();
            }
        }
        _releaseBuffers();
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    private final void _writeBytes(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
            if (length > 512) {
                this._outputStream.write(bArr2, 0, length);
                return;
            }
        }
        System.arraycopy(bArr2, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    private final void _writeBytes(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this._outputTail + i4 > this._outputEnd) {
            _flushBuffer();
            if (i4 > 512) {
                this._outputStream.write(bArr2, i3, i4);
                return;
            }
        }
        System.arraycopy(bArr2, i3, this._outputBuffer, this._outputTail, i4);
        this._outputTail += i4;
    }

    private final void _writeStringSegments(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        int length = str2.length();
        int i = 0;
        char[] cArr = this._charBuffer;
        while (length > 0) {
            int min = Math.min(this._outputMaxContiguous, length);
            str2.getChars(i, i + min, cArr, 0);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, 0, min);
            i += min;
            length -= min;
        }
    }

    private final void _writeStringSegments(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        do {
            int min = Math.min(this._outputMaxContiguous, i4);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr2, i3, min);
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
    }

    private final void _writeStringSegment(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char c;
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2 + i3;
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i3 < i4 && (c = cArr2[i3]) <= 127 && iArr[c] == 0) {
            int i6 = i5;
            i5++;
            bArr[i6] = (byte) c;
            i3++;
        }
        this._outputTail = i5;
        if (i3 >= i4) {
            return;
        }
        if (this._characterEscapes != null) {
            _writeCustomStringSegment2(cArr2, i3, i4);
        } else if (this._maximumNonEscapedChar == 0) {
            _writeStringSegment2(cArr2, i3, i4);
        } else {
            _writeStringSegmentASCII2(cArr2, i3, i4);
        }
    }

    private final void _writeStringSegment2(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        if (this._outputTail + (6 * (i4 - i3)) > this._outputEnd) {
            _flushBuffer();
        }
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i3 < i4) {
            int i6 = i3;
            i3++;
            char c = cArr2[i6];
            if (c <= 127) {
                if (iArr[c] == 0) {
                    int i7 = i5;
                    i5++;
                    bArr[i7] = (byte) c;
                } else {
                    int i8 = iArr[c];
                    if (i8 > 0) {
                        int i9 = i5;
                        int i10 = i5 + 1;
                        bArr[i9] = 92;
                        int i11 = i10;
                        i5 = i10 + 1;
                        bArr[i11] = (byte) i8;
                    } else {
                        i5 = _writeGenericEscape(c, i5);
                    }
                }
            } else if (c <= 2047) {
                int i12 = i5;
                int i13 = i5 + 1;
                bArr[i12] = (byte) (192 | (c >> 6));
                int i14 = i13;
                i5 = i13 + 1;
                bArr[i14] = (byte) (128 | (c & '?'));
            } else {
                i5 = _outputMultiByteChar(c, i5);
            }
        }
        this._outputTail = i5;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeStringSegmentASCII2(char[] r16, int r17, int r18) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r10 = r0
            int r10 = r10._outputTail
            r11 = 6
            r12 = r3
            r13 = r2
            int r12 = r12 - r13
            int r11 = r11 * r12
            int r10 = r10 + r11
            r11 = r0
            int r11 = r11._outputEnd
            if (r10 <= r11) goto L_0x0019
            r10 = r0
            r10._flushBuffer()
        L_0x0019:
            r10 = r0
            int r10 = r10._outputTail
            r4 = r10
            r10 = r0
            byte[] r10 = r10._outputBuffer
            r5 = r10
            r10 = r0
            int[] r10 = r10._outputEscapes
            r6 = r10
            r10 = r0
            int r10 = r10._maximumNonEscapedChar
            r7 = r10
        L_0x0029:
            r10 = r2
            r11 = r3
            if (r10 >= r11) goto L_0x00a3
            r10 = r1
            r11 = r2
            int r2 = r2 + 1
            char r10 = r10[r11]
            r8 = r10
            r10 = r8
            r11 = 127(0x7f, float:1.78E-43)
            if (r10 > r11) goto L_0x006a
            r10 = r6
            r11 = r8
            r10 = r10[r11]
            if (r10 != 0) goto L_0x0048
            r10 = r5
            r11 = r4
            int r4 = r4 + 1
            r12 = r8
            byte r12 = (byte) r12
            r10[r11] = r12
            goto L_0x0029
        L_0x0048:
            r10 = r6
            r11 = r8
            r10 = r10[r11]
            r9 = r10
            r10 = r9
            if (r10 <= 0) goto L_0x0061
            r10 = r5
            r11 = r4
            int r4 = r4 + 1
            r12 = 92
            r10[r11] = r12
            r10 = r5
            r11 = r4
            int r4 = r4 + 1
            r12 = r9
            byte r12 = (byte) r12
            r10[r11] = r12
            goto L_0x0029
        L_0x0061:
            r10 = r0
            r11 = r8
            r12 = r4
            int r10 = r10._writeGenericEscape(r11, r12)
            r4 = r10
            goto L_0x0029
        L_0x006a:
            r10 = r8
            r11 = r7
            if (r10 <= r11) goto L_0x0077
            r10 = r0
            r11 = r8
            r12 = r4
            int r10 = r10._writeGenericEscape(r11, r12)
            r4 = r10
            goto L_0x0029
        L_0x0077:
            r10 = r8
            r11 = 2047(0x7ff, float:2.868E-42)
            if (r10 > r11) goto L_0x009a
            r10 = r5
            r11 = r4
            int r4 = r4 + 1
            r12 = 192(0xc0, float:2.69E-43)
            r13 = r8
            r14 = 6
            int r13 = r13 >> 6
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
            r10 = r5
            r11 = r4
            int r4 = r4 + 1
            r12 = 128(0x80, float:1.794E-43)
            r13 = r8
            r14 = 63
            r13 = r13 & 63
            r12 = r12 | r13
            byte r12 = (byte) r12
            r10[r11] = r12
        L_0x0099:
            goto L_0x0029
        L_0x009a:
            r10 = r0
            r11 = r8
            r12 = r4
            int r10 = r10._outputMultiByteChar(r11, r12)
            r4 = r10
            goto L_0x0099
        L_0x00a3:
            r10 = r0
            r11 = r4
            r10._outputTail = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator._writeStringSegmentASCII2(char[], int, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v51, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v53, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeCustomStringSegment2(char[] r20, int r21, int r22) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r12 = r0
            int r12 = r12._outputTail
            r13 = 6
            r14 = r3
            r15 = r2
            int r14 = r14 - r15
            int r13 = r13 * r14
            int r12 = r12 + r13
            r13 = r0
            int r13 = r13._outputEnd
            if (r12 <= r13) goto L_0x001a
            r12 = r0
            r12._flushBuffer()
        L_0x001a:
            r12 = r0
            int r12 = r12._outputTail
            r4 = r12
            r12 = r0
            byte[] r12 = r12._outputBuffer
            r5 = r12
            r12 = r0
            int[] r12 = r12._outputEscapes
            r6 = r12
            r12 = r0
            int r12 = r12._maximumNonEscapedChar
            if (r12 > 0) goto L_0x0052
            r12 = 65535(0xffff, float:9.1834E-41)
        L_0x002e:
            r7 = r12
            r12 = r0
            com.shaded.fasterxml.jackson.core.io.CharacterEscapes r12 = r12._characterEscapes
            r8 = r12
        L_0x0033:
            r12 = r2
            r13 = r3
            if (r12 >= r13) goto L_0x0119
            r12 = r1
            r13 = r2
            int r2 = r2 + 1
            char r12 = r12[r13]
            r9 = r12
            r12 = r9
            r13 = 127(0x7f, float:1.78E-43)
            if (r12 > r13) goto L_0x00c2
            r12 = r6
            r13 = r9
            r12 = r12[r13]
            if (r12 != 0) goto L_0x0056
            r12 = r5
            r13 = r4
            int r4 = r4 + 1
            r14 = r9
            byte r14 = (byte) r14
            r12[r13] = r14
            goto L_0x0033
        L_0x0052:
            r12 = r0
            int r12 = r12._maximumNonEscapedChar
            goto L_0x002e
        L_0x0056:
            r12 = r6
            r13 = r9
            r12 = r12[r13]
            r10 = r12
            r12 = r10
            if (r12 <= 0) goto L_0x006f
            r12 = r5
            r13 = r4
            int r4 = r4 + 1
            r14 = 92
            r12[r13] = r14
            r12 = r5
            r13 = r4
            int r4 = r4 + 1
            r14 = r10
            byte r14 = (byte) r14
            r12[r13] = r14
            goto L_0x0033
        L_0x006f:
            r12 = r10
            r13 = -2
            if (r12 != r13) goto L_0x00b8
            r12 = r8
            r13 = r9
            com.shaded.fasterxml.jackson.core.SerializableString r12 = r12.getEscapeSequence(r13)
            r11 = r12
            r12 = r11
            if (r12 != 0) goto L_0x00a7
            r12 = r0
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r18 = r13
            r13 = r18
            r14 = r18
            r14.<init>()
            java.lang.String r14 = "Invalid custom escape definitions; custom escape not found for character code 0x"
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r9
            java.lang.String r14 = java.lang.Integer.toHexString(r14)
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = ", although was supposed to have one"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12._reportError(r13)
        L_0x00a7:
            r12 = r0
            r13 = r5
            r14 = r4
            r15 = r11
            r16 = r3
            r17 = r2
            int r16 = r16 - r17
            int r12 = r12._writeCustomEscape(r13, r14, r15, r16)
            r4 = r12
            goto L_0x0033
        L_0x00b8:
            r12 = r0
            r13 = r9
            r14 = r4
            int r12 = r12._writeGenericEscape(r13, r14)
            r4 = r12
            goto L_0x0033
        L_0x00c2:
            r12 = r9
            r13 = r7
            if (r12 <= r13) goto L_0x00d0
            r12 = r0
            r13 = r9
            r14 = r4
            int r12 = r12._writeGenericEscape(r13, r14)
            r4 = r12
            goto L_0x0033
        L_0x00d0:
            r12 = r8
            r13 = r9
            com.shaded.fasterxml.jackson.core.SerializableString r12 = r12.getEscapeSequence(r13)
            r10 = r12
            r12 = r10
            if (r12 == 0) goto L_0x00eb
            r12 = r0
            r13 = r5
            r14 = r4
            r15 = r10
            r16 = r3
            r17 = r2
            int r16 = r16 - r17
            int r12 = r12._writeCustomEscape(r13, r14, r15, r16)
            r4 = r12
            goto L_0x0033
        L_0x00eb:
            r12 = r9
            r13 = 2047(0x7ff, float:2.868E-42)
            if (r12 > r13) goto L_0x0110
            r12 = r5
            r13 = r4
            int r4 = r4 + 1
            r14 = 192(0xc0, float:2.69E-43)
            r15 = r9
            r16 = 6
            int r15 = r15 >> 6
            r14 = r14 | r15
            byte r14 = (byte) r14
            r12[r13] = r14
            r12 = r5
            r13 = r4
            int r4 = r4 + 1
            r14 = 128(0x80, float:1.794E-43)
            r15 = r9
            r16 = 63
            r15 = r15 & 63
            r14 = r14 | r15
            byte r14 = (byte) r14
            r12[r13] = r14
        L_0x010e:
            goto L_0x0033
        L_0x0110:
            r12 = r0
            r13 = r9
            r14 = r4
            int r12 = r12._outputMultiByteChar(r13, r14)
            r4 = r12
            goto L_0x010e
        L_0x0119:
            r12 = r0
            r13 = r4
            r12._outputTail = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator._writeCustomStringSegment2(char[], int, int):void");
    }

    private int _writeCustomEscape(byte[] bArr, int i, SerializableString serializableString, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length > 6) {
            return _handleLongCustomEscape(bArr2, i3, this._outputEnd, asUnquotedUTF8, i4);
        }
        System.arraycopy(asUnquotedUTF8, 0, bArr2, i3, length);
        return i3 + length;
    }

    private int _handleLongCustomEscape(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException, JsonGenerationException {
        byte[] bArr3 = bArr;
        int i4 = i;
        int i5 = i2;
        byte[] bArr4 = bArr2;
        int i6 = i3;
        int length = bArr4.length;
        if (i4 + length > i5) {
            this._outputTail = i4;
            _flushBuffer();
            int i7 = this._outputTail;
            if (length > bArr3.length) {
                this._outputStream.write(bArr4, 0, length);
                return i7;
            }
            System.arraycopy(bArr4, 0, bArr3, i7, length);
            i4 = i7 + length;
        }
        if (i4 + (6 * i6) <= i5) {
            return i4;
        }
        _flushBuffer();
        return this._outputTail;
    }

    private void _writeUTF8Segments(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        do {
            int min = Math.min(this._outputMaxContiguous, i4);
            _writeUTF8Segment(bArr2, i3, min);
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
    }

    private void _writeUTF8Segment(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        int[] iArr = this._outputEscapes;
        int i5 = i3;
        int i6 = i3 + i4;
        while (i5 < i6) {
            int i7 = i5;
            i5++;
            byte b = bArr2[i7];
            if (b >= 0 && iArr[b] != 0) {
                _writeUTF8Segment2(bArr2, i3, i4);
                return;
            }
        }
        if (this._outputTail + i4 > this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(bArr2, i3, this._outputBuffer, this._outputTail, i4);
        this._outputTail += i4;
    }

    private void _writeUTF8Segment2(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        int i3;
        byte[] bArr2 = bArr;
        int i4 = i;
        int i5 = i2;
        int i6 = this._outputTail;
        if (i6 + (i5 * 6) > this._outputEnd) {
            _flushBuffer();
            i6 = this._outputTail;
        }
        byte[] bArr3 = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i7 = i5 + i4;
        while (i4 < i7) {
            int i8 = i4;
            i4++;
            byte b = bArr2[i8];
            byte b2 = b;
            if (b2 < 0 || iArr[b2] == 0) {
                int i9 = i3;
                i3++;
                bArr3[i9] = b;
            } else {
                int i10 = iArr[b2];
                if (i10 > 0) {
                    int i11 = i3;
                    int i12 = i3 + 1;
                    bArr3[i11] = 92;
                    int i13 = i12;
                    i3 = i12 + 1;
                    bArr3[i13] = (byte) i10;
                } else {
                    i3 = _writeGenericEscape(b2, i3);
                }
            }
        }
        this._outputTail = i3;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant r19, byte[] r20, int r21, int r22) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r10 = r4
            r11 = 3
            int r10 = r10 + -3
            r5 = r10
            r10 = r0
            int r10 = r10._outputEnd
            r11 = 6
            int r10 = r10 + -6
            r6 = r10
            r10 = r1
            int r10 = r10.getMaxLineLength()
            r11 = 2
            int r10 = r10 >> 2
            r7 = r10
        L_0x001f:
            r10 = r3
            r11 = r5
            if (r10 > r11) goto L_0x00b3
            r10 = r0
            int r10 = r10._outputTail
            r11 = r6
            if (r10 <= r11) goto L_0x002d
            r10 = r0
            r10._flushBuffer()
        L_0x002d:
            r10 = r2
            r11 = r3
            int r3 = r3 + 1
            byte r10 = r10[r11]
            r11 = 8
            int r10 = r10 << 8
            r8 = r10
            r10 = r8
            r11 = r2
            r12 = r3
            int r3 = r3 + 1
            byte r11 = r11[r12]
            r12 = 255(0xff, float:3.57E-43)
            r11 = r11 & 255(0xff, float:3.57E-43)
            r10 = r10 | r11
            r8 = r10
            r10 = r8
            r11 = 8
            int r10 = r10 << 8
            r11 = r2
            r12 = r3
            int r3 = r3 + 1
            byte r11 = r11[r12]
            r12 = 255(0xff, float:3.57E-43)
            r11 = r11 & 255(0xff, float:3.57E-43)
            r10 = r10 | r11
            r8 = r10
            r10 = r0
            r11 = r1
            r12 = r8
            r13 = r0
            byte[] r13 = r13._outputBuffer
            r14 = r0
            int r14 = r14._outputTail
            int r11 = r11.encodeBase64Chunk((int) r12, (byte[]) r13, (int) r14)
            r10._outputTail = r11
            int r7 = r7 + -1
            r10 = r7
            if (r10 > 0) goto L_0x00b1
            r10 = r0
            byte[] r10 = r10._outputBuffer
            r11 = r0
            r16 = r11
            r11 = r16
            r12 = r16
            int r12 = r12._outputTail
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            r13 = r17
            r14 = 1
            int r13 = r13 + 1
            r12._outputTail = r13
            r12 = 92
            r10[r11] = r12
            r10 = r0
            byte[] r10 = r10._outputBuffer
            r11 = r0
            r16 = r11
            r11 = r16
            r12 = r16
            int r12 = r12._outputTail
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            r13 = r17
            r14 = 1
            int r13 = r13 + 1
            r12._outputTail = r13
            r12 = 110(0x6e, float:1.54E-43)
            r10[r11] = r12
            r10 = r1
            int r10 = r10.getMaxLineLength()
            r11 = 2
            int r10 = r10 >> 2
            r7 = r10
        L_0x00b1:
            goto L_0x001f
        L_0x00b3:
            r10 = r4
            r11 = r3
            int r10 = r10 - r11
            r8 = r10
            r10 = r8
            if (r10 <= 0) goto L_0x00f4
            r10 = r0
            int r10 = r10._outputTail
            r11 = r6
            if (r10 <= r11) goto L_0x00c4
            r10 = r0
            r10._flushBuffer()
        L_0x00c4:
            r10 = r2
            r11 = r3
            int r3 = r3 + 1
            byte r10 = r10[r11]
            r11 = 16
            int r10 = r10 << 16
            r9 = r10
            r10 = r8
            r11 = 2
            if (r10 != r11) goto L_0x00e4
            r10 = r9
            r11 = r2
            r12 = r3
            int r3 = r3 + 1
            byte r11 = r11[r12]
            r12 = 255(0xff, float:3.57E-43)
            r11 = r11 & 255(0xff, float:3.57E-43)
            r12 = 8
            int r11 = r11 << 8
            r10 = r10 | r11
            r9 = r10
        L_0x00e4:
            r10 = r0
            r11 = r1
            r12 = r9
            r13 = r8
            r14 = r0
            byte[] r14 = r14._outputBuffer
            r15 = r0
            int r15 = r15._outputTail
            int r11 = r11.encodeBase64Partial((int) r12, (int) r13, (byte[]) r14, (int) r15)
            r10._outputTail = r11
        L_0x00f4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator._writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant, byte[], int, int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int _writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant r22, java.io.InputStream r23, byte[] r24, int r25) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            r5 = r25
            r13 = 0
            r6 = r13
            r13 = 0
            r7 = r13
            r13 = -3
            r8 = r13
            r13 = r1
            int r13 = r13._outputEnd
            r14 = 6
            int r13 = r13 + -6
            r9 = r13
            r13 = r2
            int r13 = r13.getMaxLineLength()
            r14 = 2
            int r13 = r13 >> 2
            r10 = r13
        L_0x0020:
            r13 = r5
            r14 = 2
            if (r13 <= r14) goto L_0x003c
            r13 = r6
            r14 = r8
            if (r13 <= r14) goto L_0x00a3
            r13 = r1
            r14 = r3
            r15 = r4
            r16 = r6
            r17 = r7
            r18 = r5
            int r13 = r13._readMore(r14, r15, r16, r17, r18)
            r7 = r13
            r13 = 0
            r6 = r13
            r13 = r7
            r14 = 3
            if (r13 >= r14) goto L_0x009e
        L_0x003c:
            r13 = r5
            if (r13 <= 0) goto L_0x009b
            r13 = r1
            r14 = r3
            r15 = r4
            r16 = r6
            r17 = r7
            r18 = r5
            int r13 = r13._readMore(r14, r15, r16, r17, r18)
            r7 = r13
            r13 = 0
            r6 = r13
            r13 = r7
            if (r13 <= 0) goto L_0x009b
            r13 = r1
            int r13 = r13._outputTail
            r14 = r9
            if (r13 <= r14) goto L_0x005c
            r13 = r1
            r13._flushBuffer()
        L_0x005c:
            r13 = r4
            r14 = r6
            int r6 = r6 + 1
            byte r13 = r13[r14]
            r14 = 16
            int r13 = r13 << 16
            r11 = r13
            r13 = r6
            r14 = r7
            if (r13 >= r14) goto L_0x0145
            r13 = r11
            r14 = r4
            r15 = r6
            byte r14 = r14[r15]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & 255(0xff, float:3.57E-43)
            r15 = 8
            int r14 = r14 << 8
            r13 = r13 | r14
            r11 = r13
            r13 = 2
            r12 = r13
        L_0x007c:
            r13 = r1
            r14 = r2
            r15 = r11
            r16 = r12
            r17 = r1
            r0 = r17
            byte[] r0 = r0._outputBuffer
            r17 = r0
            r18 = r1
            r0 = r18
            int r0 = r0._outputTail
            r18 = r0
            int r14 = r14.encodeBase64Partial((int) r15, (int) r16, (byte[]) r17, (int) r18)
            r13._outputTail = r14
            r13 = r5
            r14 = r12
            int r13 = r13 - r14
            r5 = r13
        L_0x009b:
            r13 = r5
            r1 = r13
            return r1
        L_0x009e:
            r13 = r7
            r14 = 3
            int r13 = r13 + -3
            r8 = r13
        L_0x00a3:
            r13 = r1
            int r13 = r13._outputTail
            r14 = r9
            if (r13 <= r14) goto L_0x00ad
            r13 = r1
            r13._flushBuffer()
        L_0x00ad:
            r13 = r4
            r14 = r6
            int r6 = r6 + 1
            byte r13 = r13[r14]
            r14 = 8
            int r13 = r13 << 8
            r11 = r13
            r13 = r11
            r14 = r4
            r15 = r6
            int r6 = r6 + 1
            byte r14 = r14[r15]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & 255(0xff, float:3.57E-43)
            r13 = r13 | r14
            r11 = r13
            r13 = r11
            r14 = 8
            int r13 = r13 << 8
            r14 = r4
            r15 = r6
            int r6 = r6 + 1
            byte r14 = r14[r15]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & 255(0xff, float:3.57E-43)
            r13 = r13 | r14
            r11 = r13
            int r5 = r5 + -3
            r13 = r1
            r14 = r2
            r15 = r11
            r16 = r1
            r0 = r16
            byte[] r0 = r0._outputBuffer
            r16 = r0
            r17 = r1
            r0 = r17
            int r0 = r0._outputTail
            r17 = r0
            int r14 = r14.encodeBase64Chunk((int) r15, (byte[]) r16, (int) r17)
            r13._outputTail = r14
            int r10 = r10 + -1
            r13 = r10
            if (r13 > 0) goto L_0x0143
            r13 = r1
            byte[] r13 = r13._outputBuffer
            r14 = r1
            r19 = r14
            r14 = r19
            r15 = r19
            int r15 = r15._outputTail
            r19 = r14
            r20 = r15
            r14 = r20
            r15 = r19
            r16 = r20
            r17 = 1
            int r16 = r16 + 1
            r0 = r16
            r15._outputTail = r0
            r15 = 92
            r13[r14] = r15
            r13 = r1
            byte[] r13 = r13._outputBuffer
            r14 = r1
            r19 = r14
            r14 = r19
            r15 = r19
            int r15 = r15._outputTail
            r19 = r14
            r20 = r15
            r14 = r20
            r15 = r19
            r16 = r20
            r17 = 1
            int r16 = r16 + 1
            r0 = r16
            r15._outputTail = r0
            r15 = 110(0x6e, float:1.54E-43)
            r13[r14] = r15
            r13 = r2
            int r13 = r13.getMaxLineLength()
            r14 = 2
            int r13 = r13 >> 2
            r10 = r13
        L_0x0143:
            goto L_0x0020
        L_0x0145:
            r13 = 1
            r12 = r13
            goto L_0x007c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator._writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant, java.io.InputStream, byte[], int):int");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int _writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant r22, java.io.InputStream r23, byte[] r24) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            r13 = 0
            r5 = r13
            r13 = 0
            r6 = r13
            r13 = -3
            r7 = r13
            r13 = 0
            r8 = r13
            r13 = r1
            int r13 = r13._outputEnd
            r14 = 6
            int r13 = r13 + -6
            r9 = r13
            r13 = r2
            int r13 = r13.getMaxLineLength()
            r14 = 2
            int r13 = r13 >> 2
            r10 = r13
        L_0x0020:
            r13 = r5
            r14 = r7
            if (r13 <= r14) goto L_0x0094
            r13 = r1
            r14 = r3
            r15 = r4
            r16 = r5
            r17 = r6
            r18 = r4
            r0 = r18
            int r0 = r0.length
            r18 = r0
            int r13 = r13._readMore(r14, r15, r16, r17, r18)
            r6 = r13
            r13 = 0
            r5 = r13
            r13 = r6
            r14 = 3
            if (r13 >= r14) goto L_0x008f
            r13 = r5
            r14 = r6
            if (r13 >= r14) goto L_0x008c
            r13 = r1
            int r13 = r13._outputTail
            r14 = r9
            if (r13 <= r14) goto L_0x004b
            r13 = r1
            r13._flushBuffer()
        L_0x004b:
            r13 = r4
            r14 = r5
            int r5 = r5 + 1
            byte r13 = r13[r14]
            r14 = 16
            int r13 = r13 << 16
            r11 = r13
            r13 = 1
            r12 = r13
            r13 = r5
            r14 = r6
            if (r13 >= r14) goto L_0x006d
            r13 = r11
            r14 = r4
            r15 = r5
            byte r14 = r14[r15]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & 255(0xff, float:3.57E-43)
            r15 = 8
            int r14 = r14 << 8
            r13 = r13 | r14
            r11 = r13
            r13 = 2
            r12 = r13
        L_0x006d:
            r13 = r8
            r14 = r12
            int r13 = r13 + r14
            r8 = r13
            r13 = r1
            r14 = r2
            r15 = r11
            r16 = r12
            r17 = r1
            r0 = r17
            byte[] r0 = r0._outputBuffer
            r17 = r0
            r18 = r1
            r0 = r18
            int r0 = r0._outputTail
            r18 = r0
            int r14 = r14.encodeBase64Partial((int) r15, (int) r16, (byte[]) r17, (int) r18)
            r13._outputTail = r14
        L_0x008c:
            r13 = r8
            r1 = r13
            return r1
        L_0x008f:
            r13 = r6
            r14 = 3
            int r13 = r13 + -3
            r7 = r13
        L_0x0094:
            r13 = r1
            int r13 = r13._outputTail
            r14 = r9
            if (r13 <= r14) goto L_0x009e
            r13 = r1
            r13._flushBuffer()
        L_0x009e:
            r13 = r4
            r14 = r5
            int r5 = r5 + 1
            byte r13 = r13[r14]
            r14 = 8
            int r13 = r13 << 8
            r11 = r13
            r13 = r11
            r14 = r4
            r15 = r5
            int r5 = r5 + 1
            byte r14 = r14[r15]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & 255(0xff, float:3.57E-43)
            r13 = r13 | r14
            r11 = r13
            r13 = r11
            r14 = 8
            int r13 = r13 << 8
            r14 = r4
            r15 = r5
            int r5 = r5 + 1
            byte r14 = r14[r15]
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & 255(0xff, float:3.57E-43)
            r13 = r13 | r14
            r11 = r13
            int r8 = r8 + 3
            r13 = r1
            r14 = r2
            r15 = r11
            r16 = r1
            r0 = r16
            byte[] r0 = r0._outputBuffer
            r16 = r0
            r17 = r1
            r0 = r17
            int r0 = r0._outputTail
            r17 = r0
            int r14 = r14.encodeBase64Chunk((int) r15, (byte[]) r16, (int) r17)
            r13._outputTail = r14
            int r10 = r10 + -1
            r13 = r10
            if (r13 > 0) goto L_0x0134
            r13 = r1
            byte[] r13 = r13._outputBuffer
            r14 = r1
            r19 = r14
            r14 = r19
            r15 = r19
            int r15 = r15._outputTail
            r19 = r14
            r20 = r15
            r14 = r20
            r15 = r19
            r16 = r20
            r17 = 1
            int r16 = r16 + 1
            r0 = r16
            r15._outputTail = r0
            r15 = 92
            r13[r14] = r15
            r13 = r1
            byte[] r13 = r13._outputBuffer
            r14 = r1
            r19 = r14
            r14 = r19
            r15 = r19
            int r15 = r15._outputTail
            r19 = r14
            r20 = r15
            r14 = r20
            r15 = r19
            r16 = r20
            r17 = 1
            int r16 = r16 + 1
            r0 = r16
            r15._outputTail = r0
            r15 = 110(0x6e, float:1.54E-43)
            r13[r14] = r15
            r13 = r2
            int r13 = r13.getMaxLineLength()
            r14 = 2
            int r13 = r13 >> 2
            r10 = r13
        L_0x0134:
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator._writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant, java.io.InputStream, byte[]):int");
    }

    private int _readMore(InputStream inputStream, byte[] bArr, int i, int i2, int i3) throws IOException {
        InputStream inputStream2 = inputStream;
        byte[] bArr2 = bArr;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        int i7 = 0;
        while (i4 < i5) {
            int i8 = i7;
            i7++;
            int i9 = i4;
            i4++;
            bArr2[i8] = bArr2[i9];
        }
        int i10 = i7;
        int min = Math.min(i6, bArr2.length);
        do {
            int i11 = min - i10;
            if (i11 == 0) {
                break;
            }
            int read = inputStream2.read(bArr2, i10, i11);
            if (read < 0) {
                return i10;
            }
            i10 += read;
        } while (i10 < 3);
        return i10;
    }

    private int _outputRawMultiByteChar(int i, char[] cArr, int i2, int i3) throws IOException {
        int i4 = i;
        char[] cArr2 = cArr;
        int i5 = i2;
        int i6 = i3;
        if (i4 < SURR1_FIRST || i4 > SURR2_LAST) {
            byte[] bArr = this._outputBuffer;
            int i7 = this._outputTail;
            int i8 = i7 + 1;
            this._outputTail = i8;
            bArr[i7] = (byte) (224 | (i4 >> 12));
            int i9 = this._outputTail;
            int i10 = i9 + 1;
            this._outputTail = i10;
            bArr[i9] = (byte) (128 | ((i4 >> 6) & 63));
            int i11 = this._outputTail;
            int i12 = i11 + 1;
            this._outputTail = i12;
            bArr[i11] = (byte) (128 | (i4 & 63));
            return i5;
        }
        if (i5 >= i6) {
            _reportError("Split surrogate on writeRaw() input (last character)");
        }
        _outputSurrogates(i4, cArr2[i5]);
        return i5 + 1;
    }

    /* access modifiers changed from: protected */
    public final void _outputSurrogates(int i, int i2) throws IOException {
        int _decodeSurrogate = _decodeSurrogate(i, i2);
        if (this._outputTail + 4 > this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = (byte) (240 | (_decodeSurrogate >> 18));
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = (byte) (128 | ((_decodeSurrogate >> 12) & 63));
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        bArr[i7] = (byte) (128 | ((_decodeSurrogate >> 6) & 63));
        int i9 = this._outputTail;
        int i10 = i9 + 1;
        this._outputTail = i10;
        bArr[i9] = (byte) (128 | (_decodeSurrogate & 63));
    }

    private int _outputMultiByteChar(int i, int i2) throws IOException {
        int i3;
        int i4 = i;
        int i5 = i2;
        byte[] bArr = this._outputBuffer;
        if (i4 < SURR1_FIRST || i4 > SURR2_LAST) {
            int i6 = i5;
            int i7 = i5 + 1;
            bArr[i6] = (byte) (224 | (i4 >> 12));
            int i8 = i7;
            int i9 = i7 + 1;
            bArr[i8] = (byte) (128 | ((i4 >> 6) & 63));
            int i10 = i9;
            i3 = i9 + 1;
            bArr[i10] = (byte) (128 | (i4 & 63));
        } else {
            int i11 = i5;
            int i12 = i5 + 1;
            bArr[i11] = 92;
            int i13 = i12;
            int i14 = i12 + 1;
            bArr[i13] = 117;
            int i15 = i14;
            int i16 = i14 + 1;
            bArr[i15] = HEX_CHARS[(i4 >> 12) & 15];
            int i17 = i16;
            int i18 = i16 + 1;
            bArr[i17] = HEX_CHARS[(i4 >> 8) & 15];
            int i19 = i18;
            int i20 = i18 + 1;
            bArr[i19] = HEX_CHARS[(i4 >> 4) & 15];
            int i21 = i20;
            i3 = i20 + 1;
            bArr[i21] = HEX_CHARS[i4 & 15];
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public final int _decodeSurrogate(int i, int i2) throws IOException {
        StringBuilder sb;
        int i3 = i;
        int i4 = i2;
        if (i4 < SURR2_FIRST || i4 > SURR2_LAST) {
            new StringBuilder();
            _reportError(sb.append("Incomplete surrogate pair: first char 0x").append(Integer.toHexString(i3)).append(", second 0x").append(Integer.toHexString(i4)).toString());
        }
        return 65536 + ((i3 - SURR1_FIRST) << 10) + (i4 - SURR2_FIRST);
    }

    private void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }

    private int _writeGenericEscape(int i, int i2) throws IOException {
        int i3;
        int i4 = i;
        int i5 = i2;
        byte[] bArr = this._outputBuffer;
        int i6 = i5;
        int i7 = i5 + 1;
        bArr[i6] = 92;
        int i8 = i7;
        int i9 = i7 + 1;
        bArr[i8] = 117;
        if (i4 > 255) {
            int i10 = (i4 >> 8) & 255;
            int i11 = i9;
            int i12 = i9 + 1;
            bArr[i11] = HEX_CHARS[i10 >> 4];
            int i13 = i12;
            i3 = i12 + 1;
            bArr[i13] = HEX_CHARS[i10 & 15];
            i4 &= 255;
        } else {
            int i14 = i9;
            int i15 = i9 + 1;
            bArr[i14] = 48;
            int i16 = i15;
            i3 = i15 + 1;
            bArr[i16] = 48;
        }
        int i17 = i3;
        int i18 = i3 + 1;
        bArr[i17] = HEX_CHARS[i4 >> 4];
        bArr[i18] = HEX_CHARS[i4 & 15];
        return i18 + 1;
    }

    /* access modifiers changed from: protected */
    public final void _flushBuffer() throws IOException {
        int i = this._outputTail;
        if (i > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, i);
        }
    }
}
