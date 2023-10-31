package com.shaded.fasterxml.jackson.core.json;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;
import com.shaded.fasterxml.jackson.core.p005io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class WriterBasedJsonGenerator extends JsonGeneratorImpl {
    protected static final char[] HEX_CHARS = CharTypes.copyHexChars();
    protected static final int SHORT_WRITE = 32;
    protected SerializableString _currentEscape;
    protected char[] _entityBuffer;
    protected char[] _outputBuffer;
    protected int _outputEnd;
    protected int _outputHead = 0;
    protected int _outputTail = 0;
    protected final Writer _writer;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WriterBasedJsonGenerator(com.shaded.fasterxml.jackson.core.p005io.IOContext r10, int r11, com.shaded.fasterxml.jackson.core.ObjectCodec r12, java.io.Writer r13) {
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
            r5._outputHead = r6
            r5 = r0
            r6 = 0
            r5._outputTail = r6
            r5 = r0
            r6 = r4
            r5._writer = r6
            r5 = r0
            r6 = r1
            char[] r6 = r6.allocConcatBuffer()
            r5._outputBuffer = r6
            r5 = r0
            r6 = r0
            char[] r6 = r6._outputBuffer
            int r6 = r6.length
            r5._outputEnd = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator.<init>(com.shaded.fasterxml.jackson.core.io.IOContext, int, com.shaded.fasterxml.jackson.core.ObjectCodec, java.io.Writer):void");
    }

    public Object getOutputTarget() {
        return this._writer;
    }

    public void writeFieldName(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        int writeFieldName = this._writeContext.writeFieldName(str2);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(str2, writeFieldName == 1);
    }

    public void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        int writeFieldName = this._writeContext.writeFieldName(serializableString2.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializableString2, writeFieldName == 1);
    }

    public void writeStartArray() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = '[';
    }

    public void writeEndArray() throws IOException, JsonGenerationException {
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
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            cArr[i] = ']';
        }
        this._writeContext = this._writeContext.getParent();
    }

    public void writeStartObject() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = '{';
    }

    public void writeEndObject() throws IOException, JsonGenerationException {
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
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            cArr[i] = '}';
        }
        this._writeContext = this._writeContext.getParent();
    }

    /* access modifiers changed from: protected */
    public void _writeFieldName(String str, boolean z) throws IOException, JsonGenerationException {
        String str2 = str;
        boolean z2 = z;
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str2, z2);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z2) {
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            cArr[i] = ',';
        }
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            _writeString(str2);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr2[i3] = '\"';
        _writeString(str2);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        cArr3[i5] = '\"';
    }

    public void _writeFieldName(SerializableString serializableString, boolean z) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        boolean z2 = z;
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString2, z2);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z2) {
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            cArr[i] = ',';
        }
        char[] asQuotedChars = serializableString2.asQuotedChars();
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr2[i3] = '\"';
        int length = asQuotedChars.length;
        if (this._outputTail + length + 1 >= this._outputEnd) {
            writeRaw(asQuotedChars, 0, length);
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr3 = this._outputBuffer;
            int i5 = this._outputTail;
            int i6 = i5 + 1;
            this._outputTail = i6;
            cArr3[i5] = '\"';
            return;
        }
        System.arraycopy(asQuotedChars, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
        char[] cArr4 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        cArr4[i7] = '\"';
    }

    /* access modifiers changed from: protected */
    public void _writePPFieldName(String str, boolean z) throws IOException, JsonGenerationException {
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
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            cArr[i] = '\"';
            _writeString(str2);
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            int i4 = i3 + 1;
            this._outputTail = i4;
            cArr2[i3] = '\"';
            return;
        }
        _writeString(str2);
    }

    /* access modifiers changed from: protected */
    public void _writePPFieldName(SerializableString serializableString, boolean z) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        char[] asQuotedChars = serializableString2.asQuotedChars();
        if (isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            int i2 = i + 1;
            this._outputTail = i2;
            cArr[i] = '\"';
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            int i4 = i3 + 1;
            this._outputTail = i4;
            cArr2[i3] = '\"';
            return;
        }
        writeRaw(asQuotedChars, 0, asQuotedChars.length);
    }

    public void writeString(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        _verifyValueWrite("write text value");
        if (str2 == null) {
            _writeNull();
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = '\"';
        _writeString(str2);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr2[i3] = '\"';
    }

    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        cArr3[i5] = '\"';
        _writeString(cArr2, i3, i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr4 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        cArr4[i7] = '\"';
    }

    public void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = '\"';
        char[] asQuotedChars = serializableString2.asQuotedChars();
        int length = asQuotedChars.length;
        if (length < 32) {
            if (length > this._outputEnd - this._outputTail) {
                _flushBuffer();
            }
            System.arraycopy(asQuotedChars, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
        } else {
            _flushBuffer();
            this._writer.write(asQuotedChars, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr2[i3] = '\"';
    }

    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        _reportUnsupportedOperation();
    }

    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        _reportUnsupportedOperation();
    }

    public void writeRaw(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        int length = str2.length();
        int i = this._outputEnd - this._outputTail;
        if (i == 0) {
            _flushBuffer();
            i = this._outputEnd - this._outputTail;
        }
        if (i >= length) {
            str2.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
            return;
        }
        writeRawLong(str2);
    }

    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        int i5 = this._outputEnd - this._outputTail;
        if (i5 < i4) {
            _flushBuffer();
            i5 = this._outputEnd - this._outputTail;
        }
        if (i5 >= i4) {
            str2.getChars(i3, i3 + i4, this._outputBuffer, this._outputTail);
            this._outputTail += i4;
            return;
        }
        writeRawLong(str2.substring(i3, i3 + i4));
    }

    public void writeRaw(SerializableString serializableString) throws IOException, JsonGenerationException {
        writeRaw(serializableString.getValue());
    }

    public void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        if (i4 < 32) {
            if (i4 > this._outputEnd - this._outputTail) {
                _flushBuffer();
            }
            System.arraycopy(cArr2, i3, this._outputBuffer, this._outputTail, i4);
            this._outputTail += i4;
            return;
        }
        _flushBuffer();
        this._writer.write(cArr2, i3, i4);
    }

    public void writeRaw(char c) throws IOException, JsonGenerationException {
        char c2 = c;
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = c2;
    }

    private void writeRawLong(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        int i = this._outputEnd - this._outputTail;
        str2.getChars(0, i, this._outputBuffer, this._outputTail);
        this._outputTail += i;
        _flushBuffer();
        int i2 = i;
        int length = str2.length();
        int i3 = i;
        while (true) {
            int i4 = length - i3;
            if (i4 > this._outputEnd) {
                int i5 = this._outputEnd;
                str2.getChars(i2, i2 + i5, this._outputBuffer, 0);
                this._outputHead = 0;
                this._outputTail = i5;
                _flushBuffer();
                i2 += i5;
                length = i4;
                i3 = i5;
            } else {
                str2.getChars(i2, i2 + i4, this._outputBuffer, 0);
                this._outputHead = 0;
                this._outputTail = i4;
                return;
            }
        }
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
        char[] cArr = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        cArr[i5] = '\"';
        _writeBinary(base64Variant2, bArr2, i3, i3 + i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i7 = this._outputTail;
        int i8 = i7 + 1;
        this._outputTail = i8;
        cArr2[i7] = '\"';
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
        char[] cArr = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        cArr[i4] = '\"';
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
        char[] cArr2 = this._outputBuffer;
        int i6 = this._outputTail;
        int i7 = i6 + 1;
        this._outputTail = i7;
        cArr2[i6] = '\"';
        return i2;
    }

    public void writeNumber(short s) throws IOException, JsonGenerationException {
        short s2 = s;
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s2);
            return;
        }
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt((int) s2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedShort(short s) throws IOException {
        short s2 = s;
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = '\"';
        this._outputTail = NumberOutput.outputInt((int) s2, this._outputBuffer, this._outputTail);
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr2[i3] = '\"';
    }

    public void writeNumber(int i) throws IOException, JsonGenerationException {
        int i2 = i;
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i2);
            return;
        }
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(i2, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedInt(int i) throws IOException {
        int i2 = i;
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr[i3] = '\"';
        this._outputTail = NumberOutput.outputInt(i2, this._outputBuffer, this._outputTail);
        char[] cArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        cArr2[i5] = '\"';
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
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = '\"';
        this._outputTail = NumberOutput.outputLong(j2, this._outputBuffer, this._outputTail);
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr2[i3] = '\"';
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
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        int i2 = i + 1;
        this._outputTail = i2;
        cArr[i] = '\"';
        writeRaw(obj2.toString());
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr2[i3] = '\"';
    }

    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        int i;
        boolean z2 = z;
        _verifyValueWrite("write boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        int i2 = this._outputTail;
        char[] cArr = this._outputBuffer;
        if (z2) {
            cArr[i2] = 't';
            int i3 = i2 + 1;
            cArr[i3] = 'r';
            int i4 = i3 + 1;
            cArr[i4] = 'u';
            i = i4 + 1;
            cArr[i] = 'e';
        } else {
            cArr[i2] = 'f';
            int i5 = i2 + 1;
            cArr[i5] = 'a';
            int i6 = i5 + 1;
            cArr[i6] = 'l';
            int i7 = i6 + 1;
            cArr[i7] = 's';
            i = i7 + 1;
            cArr[i] = 'e';
        }
        this._outputTail = i + 1;
    }

    public void writeNull() throws IOException, JsonGenerationException {
        _verifyValueWrite("write null value");
        _writeNull();
    }

    /* access modifiers changed from: protected */
    public void _verifyValueWrite(String str) throws IOException, JsonGenerationException {
        char c;
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
                    c = ',';
                    break;
                case 2:
                    c = ':';
                    break;
                case 3:
                    if (this._rootValueSeparator != null) {
                        writeRaw(this._rootValueSeparator.getValue());
                        return;
                    }
                    return;
                default:
                    return;
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            this._outputBuffer[this._outputTail] = c;
            this._outputTail++;
            return;
        }
        _verifyPrettyValueWrite(str2, writeValue);
    }

    /* access modifiers changed from: protected */
    public void _verifyPrettyValueWrite(String str, int i) throws IOException, JsonGenerationException {
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

    public void flush() throws IOException {
        _flushBuffer();
        if (this._writer != null && isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._writer.flush();
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
        if (this._writer != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this._writer.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._writer.flush();
            }
        }
        _releaseBuffers();
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() {
        char[] cArr = this._outputBuffer;
        if (cArr != null) {
            this._outputBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    private void _writeString(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        int length = str2.length();
        if (length > this._outputEnd) {
            _writeLongString(str2);
            return;
        }
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
        }
        str2.getChars(0, length, this._outputBuffer, this._outputTail);
        if (this._characterEscapes != null) {
            _writeStringCustom(length);
        } else if (this._maximumNonEscapedChar != 0) {
            _writeStringASCII(length, this._maximumNonEscapedChar);
        } else {
            _writeString2(length);
        }
    }

    /* JADX WARNING: type inference failed for: r7v6, types: [int] */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0040, code lost:
        r7 = r0._outputBuffer;
        r8 = r0;
        r12 = r8;
        r8 = r12;
        r13 = r12._outputTail;
        r10 = r13 + 1;
        r8._outputTail = r10;
        r6 = r7[r13];
        _prependOrWriteCharacterEscape(r6, r3[r6]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        r5 = r0._outputTail - r0._outputHead;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0031, code lost:
        if (r5 <= 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        r0._writer.write(r0._outputBuffer, r0._outputHead, r5);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeString2(int r15) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r7 = r0
            int r7 = r7._outputTail
            r8 = r1
            int r7 = r7 + r8
            r2 = r7
            r7 = r0
            int[] r7 = r7._outputEscapes
            r3 = r7
            r7 = r3
            int r7 = r7.length
            r4 = r7
        L_0x000f:
            r7 = r0
            int r7 = r7._outputTail
            r8 = r2
            if (r7 >= r8) goto L_0x0073
        L_0x0015:
            r7 = r0
            char[] r7 = r7._outputBuffer
            r8 = r0
            int r8 = r8._outputTail
            char r7 = r7[r8]
            r5 = r7
            r7 = r5
            r8 = r4
            if (r7 >= r8) goto L_0x0060
            r7 = r3
            r8 = r5
            r7 = r7[r8]
            if (r7 == 0) goto L_0x0060
            r7 = r0
            int r7 = r7._outputTail
            r8 = r0
            int r8 = r8._outputHead
            int r7 = r7 - r8
            r5 = r7
            r7 = r5
            if (r7 <= 0) goto L_0x0040
            r7 = r0
            java.io.Writer r7 = r7._writer
            r8 = r0
            char[] r8 = r8._outputBuffer
            r9 = r0
            int r9 = r9._outputHead
            r10 = r5
            r7.write(r8, r9, r10)
        L_0x0040:
            r7 = r0
            char[] r7 = r7._outputBuffer
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
            char r7 = r7[r8]
            r6 = r7
            r7 = r0
            r8 = r6
            r9 = r3
            r10 = r6
            r9 = r9[r10]
            r7._prependOrWriteCharacterEscape(r8, r9)
            goto L_0x000f
        L_0x0060:
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            int r8 = r8._outputTail
            r9 = 1
            int r8 = r8 + 1
            r12 = r7
            r13 = r8
            r7 = r13
            r8 = r12
            r9 = r13
            r8._outputTail = r9
            r8 = r2
            if (r7 < r8) goto L_0x0074
        L_0x0073:
            return
        L_0x0074:
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeString2(int):void");
    }

    private void _writeLongString(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        _flushBuffer();
        int length = str2.length();
        int i = 0;
        do {
            int i2 = this._outputEnd;
            int i3 = i + i2 > length ? length - i : i2;
            str2.getChars(i, i + i3, this._outputBuffer, 0);
            if (this._characterEscapes != null) {
                _writeSegmentCustom(i3);
            } else if (this._maximumNonEscapedChar != 0) {
                _writeSegmentASCII(i3, this._maximumNonEscapedChar);
            } else {
                _writeSegment(i3);
            }
            i += i3;
        } while (i < length);
    }

    /* JADX WARNING: type inference failed for: r8v3, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeSegment(int r16) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r8 = r0
            int[] r8 = r8._outputEscapes
            r2 = r8
            r8 = r2
            int r8 = r8.length
            r3 = r8
            r8 = 0
            r4 = r8
            r8 = r4
            r5 = r8
        L_0x000e:
            r8 = r4
            r9 = r1
            if (r8 >= r9) goto L_0x0039
        L_0x0012:
            r8 = r0
            char[] r8 = r8._outputBuffer
            r9 = r4
            char r8 = r8[r9]
            r6 = r8
            r8 = r6
            r9 = r3
            if (r8 >= r9) goto L_0x003a
            r8 = r2
            r9 = r6
            r8 = r8[r9]
            if (r8 == 0) goto L_0x003a
        L_0x0023:
            r8 = r4
            r9 = r5
            int r8 = r8 - r9
            r7 = r8
            r8 = r7
            if (r8 <= 0) goto L_0x0041
            r8 = r0
            java.io.Writer r8 = r8._writer
            r9 = r0
            char[] r9 = r9._outputBuffer
            r10 = r5
            r11 = r7
            r8.write(r9, r10, r11)
            r8 = r4
            r9 = r1
            if (r8 < r9) goto L_0x0041
        L_0x0039:
            return
        L_0x003a:
            int r4 = r4 + 1
            r8 = r4
            r9 = r1
            if (r8 < r9) goto L_0x0012
            goto L_0x0023
        L_0x0041:
            int r4 = r4 + 1
            r8 = r0
            r9 = r0
            char[] r9 = r9._outputBuffer
            r10 = r4
            r11 = r1
            r12 = r6
            r13 = r2
            r14 = r6
            r13 = r13[r14]
            int r8 = r8._prependOrWriteCharacterEscape(r9, r10, r11, r12, r13)
            r5 = r8
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeSegment(int):void");
    }

    /* JADX WARNING: type inference failed for: r9v9, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeString(char[] r16, int r17, int r18) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r9 = r0
            com.shaded.fasterxml.jackson.core.io.CharacterEscapes r9 = r9._characterEscapes
            if (r9 == 0) goto L_0x0014
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r9._writeStringCustom(r10, r11, r12)
        L_0x0013:
            return
        L_0x0014:
            r9 = r0
            int r9 = r9._maximumNonEscapedChar
            if (r9 == 0) goto L_0x0024
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r0
            int r13 = r13._maximumNonEscapedChar
            r9._writeStringASCII(r10, r11, r12, r13)
            goto L_0x0013
        L_0x0024:
            r9 = r3
            r10 = r2
            int r9 = r9 + r10
            r3 = r9
            r9 = r0
            int[] r9 = r9._outputEscapes
            r4 = r9
            r9 = r4
            int r9 = r9.length
            r5 = r9
        L_0x002f:
            r9 = r2
            r10 = r3
            if (r9 >= r10) goto L_0x0078
            r9 = r2
            r6 = r9
        L_0x0035:
            r9 = r1
            r10 = r2
            char r9 = r9[r10]
            r7 = r9
            r9 = r7
            r10 = r5
            if (r9 >= r10) goto L_0x0079
            r9 = r4
            r10 = r7
            r9 = r9[r10]
            if (r9 == 0) goto L_0x0079
        L_0x0044:
            r9 = r2
            r10 = r6
            int r9 = r9 - r10
            r7 = r9
            r9 = r7
            r10 = 32
            if (r9 >= r10) goto L_0x0081
            r9 = r0
            int r9 = r9._outputTail
            r10 = r7
            int r9 = r9 + r10
            r10 = r0
            int r10 = r10._outputEnd
            if (r9 <= r10) goto L_0x005b
            r9 = r0
            r9._flushBuffer()
        L_0x005b:
            r9 = r7
            if (r9 <= 0) goto L_0x0074
            r9 = r1
            r10 = r6
            r11 = r0
            char[] r11 = r11._outputBuffer
            r12 = r0
            int r12 = r12._outputTail
            r13 = r7
            java.lang.System.arraycopy(r9, r10, r11, r12, r13)
            r9 = r0
            r14 = r9
            r9 = r14
            r10 = r14
            int r10 = r10._outputTail
            r11 = r7
            int r10 = r10 + r11
            r9._outputTail = r10
        L_0x0074:
            r9 = r2
            r10 = r3
            if (r9 < r10) goto L_0x008f
        L_0x0078:
            goto L_0x0013
        L_0x0079:
            int r2 = r2 + 1
            r9 = r2
            r10 = r3
            if (r9 < r10) goto L_0x0080
            goto L_0x0044
        L_0x0080:
            goto L_0x0035
        L_0x0081:
            r9 = r0
            r9._flushBuffer()
            r9 = r0
            java.io.Writer r9 = r9._writer
            r10 = r1
            r11 = r6
            r12 = r7
            r9.write(r10, r11, r12)
            goto L_0x0074
        L_0x008f:
            r9 = r1
            r10 = r2
            int r2 = r2 + 1
            char r9 = r9[r10]
            r8 = r9
            r9 = r0
            r10 = r8
            r11 = r4
            r12 = r8
            r11 = r11[r12]
            r9._appendCharacterEscape(r10, r11)
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeString(char[], int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x007b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeStringASCII(int r16, int r17) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r9 = r0
            int r9 = r9._outputTail
            r10 = r1
            int r9 = r9 + r10
            r3 = r9
            r9 = r0
            int[] r9 = r9._outputEscapes
            r4 = r9
            r9 = r4
            int r9 = r9.length
            r10 = r2
            r11 = 1
            int r10 = r10 + 1
            int r9 = java.lang.Math.min(r9, r10)
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x001c:
            r9 = r0
            int r9 = r9._outputTail
            r10 = r3
            if (r9 >= r10) goto L_0x007b
        L_0x0022:
            r9 = r0
            char[] r9 = r9._outputBuffer
            r10 = r0
            int r10 = r10._outputTail
            char r9 = r9[r10]
            r7 = r9
            r9 = r7
            r10 = r5
            if (r9 >= r10) goto L_0x0061
            r9 = r4
            r10 = r7
            r9 = r9[r10]
            r6 = r9
            r9 = r6
            if (r9 == 0) goto L_0x0068
        L_0x0037:
            r9 = r0
            int r9 = r9._outputTail
            r10 = r0
            int r10 = r10._outputHead
            int r9 = r9 - r10
            r8 = r9
            r9 = r8
            if (r9 <= 0) goto L_0x004f
            r9 = r0
            java.io.Writer r9 = r9._writer
            r10 = r0
            char[] r10 = r10._outputBuffer
            r11 = r0
            int r11 = r11._outputHead
            r12 = r8
            r9.write(r10, r11, r12)
        L_0x004f:
            r9 = r0
            r13 = r9
            r9 = r13
            r10 = r13
            int r10 = r10._outputTail
            r11 = 1
            int r10 = r10 + 1
            r9._outputTail = r10
            r9 = r0
            r10 = r7
            r11 = r6
            r9._prependOrWriteCharacterEscape(r10, r11)
            goto L_0x001c
        L_0x0061:
            r9 = r7
            r10 = r2
            if (r9 <= r10) goto L_0x0068
            r9 = -1
            r6 = r9
            goto L_0x0037
        L_0x0068:
            r9 = r0
            r13 = r9
            r9 = r13
            r10 = r13
            int r10 = r10._outputTail
            r11 = 1
            int r10 = r10 + 1
            r13 = r9
            r14 = r10
            r9 = r14
            r10 = r13
            r11 = r14
            r10._outputTail = r11
            r10 = r3
            if (r9 < r10) goto L_0x0022
        L_0x007b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringASCII(int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0032 A[EDGE_INSN: B:22:0x0032->B:7:0x0032 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeSegmentASCII(int r17, int r18) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r10 = r0
            int[] r10 = r10._outputEscapes
            r3 = r10
            r10 = r3
            int r10 = r10.length
            r11 = r2
            r12 = 1
            int r11 = r11 + 1
            int r10 = java.lang.Math.min(r10, r11)
            r4 = r10
            r10 = 0
            r5 = r10
            r10 = 0
            r6 = r10
            r10 = r5
            r7 = r10
        L_0x001b:
            r10 = r5
            r11 = r1
            if (r10 >= r11) goto L_0x0048
        L_0x001f:
            r10 = r0
            char[] r10 = r10._outputBuffer
            r11 = r5
            char r10 = r10[r11]
            r8 = r10
            r10 = r8
            r11 = r4
            if (r10 >= r11) goto L_0x0049
            r10 = r3
            r11 = r8
            r10 = r10[r11]
            r6 = r10
            r10 = r6
            if (r10 == 0) goto L_0x0050
        L_0x0032:
            r10 = r5
            r11 = r7
            int r10 = r10 - r11
            r9 = r10
            r10 = r9
            if (r10 <= 0) goto L_0x0057
            r10 = r0
            java.io.Writer r10 = r10._writer
            r11 = r0
            char[] r11 = r11._outputBuffer
            r12 = r7
            r13 = r9
            r10.write(r11, r12, r13)
            r10 = r5
            r11 = r1
            if (r10 < r11) goto L_0x0057
        L_0x0048:
            return
        L_0x0049:
            r10 = r8
            r11 = r2
            if (r10 <= r11) goto L_0x0050
            r10 = -1
            r6 = r10
            goto L_0x0032
        L_0x0050:
            int r5 = r5 + 1
            r10 = r5
            r11 = r1
            if (r10 < r11) goto L_0x001f
            goto L_0x0032
        L_0x0057:
            int r5 = r5 + 1
            r10 = r0
            r11 = r0
            char[] r11 = r11._outputBuffer
            r12 = r5
            r13 = r1
            r14 = r8
            r15 = r6
            int r10 = r10._prependOrWriteCharacterEscape(r11, r12, r13, r14, r15)
            r7 = r10
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeSegmentASCII(int, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0036 A[EDGE_INSN: B:29:0x0036->B:8:0x0036 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeStringASCII(char[] r18, int r19, int r20, int r21) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r11 = r3
            r12 = r2
            int r11 = r11 + r12
            r3 = r11
            r11 = r0
            int[] r11 = r11._outputEscapes
            r5 = r11
            r11 = r5
            int r11 = r11.length
            r12 = r4
            r13 = 1
            int r12 = r12 + 1
            int r11 = java.lang.Math.min(r11, r12)
            r6 = r11
            r11 = 0
            r7 = r11
        L_0x001f:
            r11 = r2
            r12 = r3
            if (r11 >= r12) goto L_0x006d
            r11 = r2
            r8 = r11
        L_0x0025:
            r11 = r1
            r12 = r2
            char r11 = r11[r12]
            r9 = r11
            r11 = r9
            r12 = r6
            if (r11 >= r12) goto L_0x006e
            r11 = r5
            r12 = r9
            r11 = r11[r12]
            r7 = r11
            r11 = r7
            if (r11 == 0) goto L_0x0075
        L_0x0036:
            r11 = r2
            r12 = r8
            int r11 = r11 - r12
            r10 = r11
            r11 = r10
            r12 = 32
            if (r11 >= r12) goto L_0x007c
            r11 = r0
            int r11 = r11._outputTail
            r12 = r10
            int r11 = r11 + r12
            r12 = r0
            int r12 = r12._outputEnd
            if (r11 <= r12) goto L_0x004d
            r11 = r0
            r11._flushBuffer()
        L_0x004d:
            r11 = r10
            if (r11 <= 0) goto L_0x0069
            r11 = r1
            r12 = r8
            r13 = r0
            char[] r13 = r13._outputBuffer
            r14 = r0
            int r14 = r14._outputTail
            r15 = r10
            java.lang.System.arraycopy(r11, r12, r13, r14, r15)
            r11 = r0
            r16 = r11
            r11 = r16
            r12 = r16
            int r12 = r12._outputTail
            r13 = r10
            int r12 = r12 + r13
            r11._outputTail = r12
        L_0x0069:
            r11 = r2
            r12 = r3
            if (r11 < r12) goto L_0x008a
        L_0x006d:
            return
        L_0x006e:
            r11 = r9
            r12 = r4
            if (r11 <= r12) goto L_0x0075
            r11 = -1
            r7 = r11
            goto L_0x0036
        L_0x0075:
            int r2 = r2 + 1
            r11 = r2
            r12 = r3
            if (r11 < r12) goto L_0x0025
            goto L_0x0036
        L_0x007c:
            r11 = r0
            r11._flushBuffer()
            r11 = r0
            java.io.Writer r11 = r11._writer
            r12 = r1
            r13 = r8
            r14 = r10
            r11.write(r12, r13, r14)
            goto L_0x0069
        L_0x008a:
            int r2 = r2 + 1
            r11 = r0
            r12 = r9
            r13 = r7
            r11._appendCharacterEscape(r12, r13)
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringASCII(char[], int, int, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v42, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v43, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeStringCustom(int r17) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r10 = r0
            int r10 = r10._outputTail
            r11 = r1
            int r10 = r10 + r11
            r2 = r10
            r10 = r0
            int[] r10 = r10._outputEscapes
            r3 = r10
            r10 = r0
            int r10 = r10._maximumNonEscapedChar
            r11 = 1
            if (r10 >= r11) goto L_0x006e
            r10 = 65535(0xffff, float:9.1834E-41)
        L_0x0017:
            r4 = r10
            r10 = r3
            int r10 = r10.length
            r11 = r4
            r12 = 1
            int r11 = r11 + 1
            int r10 = java.lang.Math.min(r10, r11)
            r5 = r10
            r10 = 0
            r6 = r10
            r10 = r0
            com.shaded.fasterxml.jackson.core.io.CharacterEscapes r10 = r10._characterEscapes
            r7 = r10
        L_0x0029:
            r10 = r0
            int r10 = r10._outputTail
            r11 = r2
            if (r10 >= r11) goto L_0x009f
        L_0x002f:
            r10 = r0
            char[] r10 = r10._outputBuffer
            r11 = r0
            int r11 = r11._outputTail
            char r10 = r10[r11]
            r8 = r10
            r10 = r8
            r11 = r5
            if (r10 >= r11) goto L_0x0072
            r10 = r3
            r11 = r8
            r10 = r10[r11]
            r6 = r10
            r10 = r6
            if (r10 == 0) goto L_0x008c
        L_0x0044:
            r10 = r0
            int r10 = r10._outputTail
            r11 = r0
            int r11 = r11._outputHead
            int r10 = r10 - r11
            r9 = r10
            r10 = r9
            if (r10 <= 0) goto L_0x005c
            r10 = r0
            java.io.Writer r10 = r10._writer
            r11 = r0
            char[] r11 = r11._outputBuffer
            r12 = r0
            int r12 = r12._outputHead
            r13 = r9
            r10.write(r11, r12, r13)
        L_0x005c:
            r10 = r0
            r14 = r10
            r10 = r14
            r11 = r14
            int r11 = r11._outputTail
            r12 = 1
            int r11 = r11 + 1
            r10._outputTail = r11
            r10 = r0
            r11 = r8
            r12 = r6
            r10._prependOrWriteCharacterEscape(r11, r12)
            goto L_0x0029
        L_0x006e:
            r10 = r0
            int r10 = r10._maximumNonEscapedChar
            goto L_0x0017
        L_0x0072:
            r10 = r8
            r11 = r4
            if (r10 <= r11) goto L_0x0079
            r10 = -1
            r6 = r10
            goto L_0x0044
        L_0x0079:
            r10 = r0
            r11 = r7
            r12 = r8
            com.shaded.fasterxml.jackson.core.SerializableString r11 = r11.getEscapeSequence(r12)
            r14 = r10
            r15 = r11
            r10 = r15
            r11 = r14
            r12 = r15
            r11._currentEscape = r12
            if (r10 == 0) goto L_0x008c
            r10 = -2
            r6 = r10
            goto L_0x0044
        L_0x008c:
            r10 = r0
            r14 = r10
            r10 = r14
            r11 = r14
            int r11 = r11._outputTail
            r12 = 1
            int r11 = r11 + 1
            r14 = r10
            r15 = r11
            r10 = r15
            r11 = r14
            r12 = r15
            r11._outputTail = r12
            r11 = r2
            if (r10 < r11) goto L_0x002f
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringCustom(int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003e A[EDGE_INSN: B:30:0x003e->B:10:0x003e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeSegmentCustom(int r20) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r11 = r0
            int[] r11 = r11._outputEscapes
            r2 = r11
            r11 = r0
            int r11 = r11._maximumNonEscapedChar
            r12 = 1
            if (r11 >= r12) goto L_0x0055
            r11 = 65535(0xffff, float:9.1834E-41)
        L_0x0011:
            r3 = r11
            r11 = r2
            int r11 = r11.length
            r12 = r3
            r13 = 1
            int r12 = r12 + 1
            int r11 = java.lang.Math.min(r11, r12)
            r4 = r11
            r11 = r0
            com.shaded.fasterxml.jackson.core.io.CharacterEscapes r11 = r11._characterEscapes
            r5 = r11
            r11 = 0
            r6 = r11
            r11 = 0
            r7 = r11
            r11 = r6
            r8 = r11
        L_0x0027:
            r11 = r6
            r12 = r1
            if (r11 >= r12) goto L_0x0054
        L_0x002b:
            r11 = r0
            char[] r11 = r11._outputBuffer
            r12 = r6
            char r11 = r11[r12]
            r9 = r11
            r11 = r9
            r12 = r4
            if (r11 >= r12) goto L_0x0059
            r11 = r2
            r12 = r9
            r11 = r11[r12]
            r7 = r11
            r11 = r7
            if (r11 == 0) goto L_0x0078
        L_0x003e:
            r11 = r6
            r12 = r8
            int r11 = r11 - r12
            r10 = r11
            r11 = r10
            if (r11 <= 0) goto L_0x007f
            r11 = r0
            java.io.Writer r11 = r11._writer
            r12 = r0
            char[] r12 = r12._outputBuffer
            r13 = r8
            r14 = r10
            r11.write(r12, r13, r14)
            r11 = r6
            r12 = r1
            if (r11 < r12) goto L_0x007f
        L_0x0054:
            return
        L_0x0055:
            r11 = r0
            int r11 = r11._maximumNonEscapedChar
            goto L_0x0011
        L_0x0059:
            r11 = r9
            r12 = r3
            if (r11 <= r12) goto L_0x0060
            r11 = -1
            r7 = r11
            goto L_0x003e
        L_0x0060:
            r11 = r0
            r12 = r5
            r13 = r9
            com.shaded.fasterxml.jackson.core.SerializableString r12 = r12.getEscapeSequence(r13)
            r17 = r11
            r18 = r12
            r11 = r18
            r12 = r17
            r13 = r18
            r12._currentEscape = r13
            if (r11 == 0) goto L_0x0078
            r11 = -2
            r7 = r11
            goto L_0x003e
        L_0x0078:
            int r6 = r6 + 1
            r11 = r6
            r12 = r1
            if (r11 < r12) goto L_0x002b
            goto L_0x003e
        L_0x007f:
            int r6 = r6 + 1
            r11 = r0
            r12 = r0
            char[] r12 = r12._outputBuffer
            r13 = r6
            r14 = r1
            r15 = r9
            r16 = r7
            int r11 = r11._prependOrWriteCharacterEscape(r12, r13, r14, r15, r16)
            r8 = r11
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeSegmentCustom(int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v45, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v46, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0042 A[EDGE_INSN: B:36:0x0042->B:11:0x0042 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeStringCustom(char[] r20, int r21, int r22) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonGenerationException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r12 = r3
            r13 = r2
            int r12 = r12 + r13
            r3 = r12
            r12 = r0
            int[] r12 = r12._outputEscapes
            r4 = r12
            r12 = r0
            int r12 = r12._maximumNonEscapedChar
            r13 = 1
            if (r12 >= r13) goto L_0x007b
            r12 = 65535(0xffff, float:9.1834E-41)
        L_0x0019:
            r5 = r12
            r12 = r4
            int r12 = r12.length
            r13 = r5
            r14 = 1
            int r13 = r13 + 1
            int r12 = java.lang.Math.min(r12, r13)
            r6 = r12
            r12 = r0
            com.shaded.fasterxml.jackson.core.io.CharacterEscapes r12 = r12._characterEscapes
            r7 = r12
            r12 = 0
            r8 = r12
        L_0x002b:
            r12 = r2
            r13 = r3
            if (r12 >= r13) goto L_0x007a
            r12 = r2
            r9 = r12
        L_0x0031:
            r12 = r1
            r13 = r2
            char r12 = r12[r13]
            r10 = r12
            r12 = r10
            r13 = r6
            if (r12 >= r13) goto L_0x007f
            r12 = r4
            r13 = r10
            r12 = r12[r13]
            r8 = r12
            r12 = r8
            if (r12 == 0) goto L_0x009e
        L_0x0042:
            r12 = r2
            r13 = r9
            int r12 = r12 - r13
            r11 = r12
            r12 = r11
            r13 = 32
            if (r12 >= r13) goto L_0x00a5
            r12 = r0
            int r12 = r12._outputTail
            r13 = r11
            int r12 = r12 + r13
            r13 = r0
            int r13 = r13._outputEnd
            if (r12 <= r13) goto L_0x0059
            r12 = r0
            r12._flushBuffer()
        L_0x0059:
            r12 = r11
            if (r12 <= 0) goto L_0x0076
            r12 = r1
            r13 = r9
            r14 = r0
            char[] r14 = r14._outputBuffer
            r15 = r0
            int r15 = r15._outputTail
            r16 = r11
            java.lang.System.arraycopy(r12, r13, r14, r15, r16)
            r12 = r0
            r17 = r12
            r12 = r17
            r13 = r17
            int r13 = r13._outputTail
            r14 = r11
            int r13 = r13 + r14
            r12._outputTail = r13
        L_0x0076:
            r12 = r2
            r13 = r3
            if (r12 < r13) goto L_0x00b3
        L_0x007a:
            return
        L_0x007b:
            r12 = r0
            int r12 = r12._maximumNonEscapedChar
            goto L_0x0019
        L_0x007f:
            r12 = r10
            r13 = r5
            if (r12 <= r13) goto L_0x0086
            r12 = -1
            r8 = r12
            goto L_0x0042
        L_0x0086:
            r12 = r0
            r13 = r7
            r14 = r10
            com.shaded.fasterxml.jackson.core.SerializableString r13 = r13.getEscapeSequence(r14)
            r17 = r12
            r18 = r13
            r12 = r18
            r13 = r17
            r14 = r18
            r13._currentEscape = r14
            if (r12 == 0) goto L_0x009e
            r12 = -2
            r8 = r12
            goto L_0x0042
        L_0x009e:
            int r2 = r2 + 1
            r12 = r2
            r13 = r3
            if (r12 < r13) goto L_0x0031
            goto L_0x0042
        L_0x00a5:
            r12 = r0
            r12._flushBuffer()
            r12 = r0
            java.io.Writer r12 = r12._writer
            r13 = r1
            r14 = r9
            r15 = r11
            r12.write(r13, r14, r15)
            goto L_0x0076
        L_0x00b3:
            int r2 = r2 + 1
            r12 = r0
            r13 = r10
            r14 = r8
            r12._appendCharacterEscape(r13, r14)
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringCustom(char[], int, int):void");
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
            char[] r13 = r13._outputBuffer
            r14 = r0
            int r14 = r14._outputTail
            int r11 = r11.encodeBase64Chunk((int) r12, (char[]) r13, (int) r14)
            r10._outputTail = r11
            int r7 = r7 + -1
            r10 = r7
            if (r10 > 0) goto L_0x00b1
            r10 = r0
            char[] r10 = r10._outputBuffer
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
            char[] r10 = r10._outputBuffer
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
            char[] r14 = r14._outputBuffer
            r15 = r0
            int r15 = r15._outputTail
            int r11 = r11.encodeBase64Partial((int) r12, (int) r13, (char[]) r14, (int) r15)
            r10._outputTail = r11
        L_0x00f4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant, byte[], int, int):void");
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
            char[] r0 = r0._outputBuffer
            r17 = r0
            r18 = r1
            r0 = r18
            int r0 = r0._outputTail
            r18 = r0
            int r14 = r14.encodeBase64Partial((int) r15, (int) r16, (char[]) r17, (int) r18)
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
            char[] r0 = r0._outputBuffer
            r16 = r0
            r17 = r1
            r0 = r17
            int r0 = r0._outputTail
            r17 = r0
            int r14 = r14.encodeBase64Chunk((int) r15, (char[]) r16, (int) r17)
            r13._outputTail = r14
            int r10 = r10 + -1
            r13 = r10
            if (r13 > 0) goto L_0x0143
            r13 = r1
            char[] r13 = r13._outputBuffer
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
            char[] r13 = r13._outputBuffer
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
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant, java.io.InputStream, byte[], int):int");
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
            char[] r0 = r0._outputBuffer
            r17 = r0
            r18 = r1
            r0 = r18
            int r0 = r0._outputTail
            r18 = r0
            int r14 = r14.encodeBase64Partial((int) r15, (int) r16, (char[]) r17, (int) r18)
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
            char[] r0 = r0._outputBuffer
            r16 = r0
            r17 = r1
            r0 = r17
            int r0 = r0._outputTail
            r17 = r0
            int r14 = r14.encodeBase64Chunk((int) r15, (char[]) r16, (int) r17)
            r13._outputTail = r14
            int r10 = r10 + -1
            r13 = r10
            if (r13 > 0) goto L_0x0134
            r13 = r1
            char[] r13 = r13._outputBuffer
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
            char[] r13 = r13._outputBuffer
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
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeBinary(com.shaded.fasterxml.jackson.core.Base64Variant, java.io.InputStream, byte[]):int");
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

    private void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        int i = this._outputTail;
        char[] cArr = this._outputBuffer;
        cArr[i] = 'n';
        int i2 = i + 1;
        cArr[i2] = 'u';
        int i3 = i2 + 1;
        cArr[i3] = 'l';
        int i4 = i3 + 1;
        cArr[i4] = 'l';
        this._outputTail = i4 + 1;
    }

    private void _prependOrWriteCharacterEscape(char c, int i) throws IOException, JsonGenerationException {
        String value;
        int i2;
        char c2 = c;
        int i3 = i;
        if (i3 >= 0) {
            if (this._outputTail >= 2) {
                int i4 = this._outputTail - 2;
                this._outputHead = i4;
                this._outputBuffer[i4] = '\\';
                this._outputBuffer[i4 + 1] = (char) i3;
                return;
            }
            char[] cArr = this._entityBuffer;
            if (cArr == null) {
                cArr = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            cArr[1] = (char) i3;
            this._writer.write(cArr, 0, 2);
        } else if (i3 == -2) {
            if (this._currentEscape == null) {
                value = this._characterEscapes.getEscapeSequence(c2).getValue();
            } else {
                value = this._currentEscape.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (this._outputTail >= length) {
                int i5 = this._outputTail - length;
                this._outputHead = i5;
                value.getChars(0, length, this._outputBuffer, i5);
                return;
            }
            this._outputHead = this._outputTail;
            this._writer.write(value);
        } else if (this._outputTail >= 6) {
            char[] cArr2 = this._outputBuffer;
            int i6 = this._outputTail - 6;
            this._outputHead = i6;
            cArr2[i6] = '\\';
            int i7 = i6 + 1;
            cArr2[i7] = 'u';
            if (c2 > 255) {
                int i8 = (c2 >> 8) & 255;
                int i9 = i7 + 1;
                cArr2[i9] = HEX_CHARS[i8 >> 4];
                i2 = i9 + 1;
                cArr2[i2] = HEX_CHARS[i8 & 15];
                c2 = (char) (c2 & 255);
            } else {
                int i10 = i7 + 1;
                cArr2[i10] = '0';
                i2 = i10 + 1;
                cArr2[i2] = '0';
            }
            int i11 = i2 + 1;
            cArr2[i11] = HEX_CHARS[c2 >> 4];
            cArr2[i11 + 1] = HEX_CHARS[c2 & 15];
        } else {
            char[] cArr3 = this._entityBuffer;
            if (cArr3 == null) {
                cArr3 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (c2 > 255) {
                int i12 = (c2 >> 8) & 255;
                char c3 = c2 & 255;
                cArr3[10] = HEX_CHARS[i12 >> 4];
                cArr3[11] = HEX_CHARS[i12 & 15];
                cArr3[12] = HEX_CHARS[c3 >> 4];
                cArr3[13] = HEX_CHARS[c3 & 15];
                this._writer.write(cArr3, 8, 6);
                return;
            }
            cArr3[6] = HEX_CHARS[c2 >> 4];
            cArr3[7] = HEX_CHARS[c2 & 15];
            this._writer.write(cArr3, 2, 6);
        }
    }

    private int _prependOrWriteCharacterEscape(char[] cArr, int i, int i2, char c, int i3) throws IOException, JsonGenerationException {
        String value;
        int i4;
        char[] cArr2 = cArr;
        int i5 = i;
        int i6 = i2;
        char c2 = c;
        int i7 = i3;
        if (i7 >= 0) {
            if (i5 <= 1 || i5 >= i6) {
                char[] cArr3 = this._entityBuffer;
                if (cArr3 == null) {
                    cArr3 = _allocateEntityBuffer();
                }
                cArr3[1] = (char) i7;
                this._writer.write(cArr3, 0, 2);
            } else {
                i5 -= 2;
                cArr2[i5] = '\\';
                cArr2[i5 + 1] = (char) i7;
            }
            return i5;
        } else if (i7 != -2) {
            if (i5 <= 5 || i5 >= i6) {
                char[] cArr4 = this._entityBuffer;
                if (cArr4 == null) {
                    cArr4 = _allocateEntityBuffer();
                }
                this._outputHead = this._outputTail;
                if (c2 > 255) {
                    int i8 = (c2 >> 8) & 255;
                    char c3 = c2 & 255;
                    cArr4[10] = HEX_CHARS[i8 >> 4];
                    cArr4[11] = HEX_CHARS[i8 & 15];
                    cArr4[12] = HEX_CHARS[c3 >> 4];
                    cArr4[13] = HEX_CHARS[c3 & 15];
                    this._writer.write(cArr4, 8, 6);
                } else {
                    cArr4[6] = HEX_CHARS[c2 >> 4];
                    cArr4[7] = HEX_CHARS[c2 & 15];
                    this._writer.write(cArr4, 2, 6);
                }
            } else {
                int i9 = i5 - 6;
                int i10 = i9;
                int i11 = i9 + 1;
                cArr2[i10] = '\\';
                int i12 = i11;
                int i13 = i11 + 1;
                cArr2[i12] = 'u';
                if (c2 > 255) {
                    int i14 = (c2 >> 8) & 255;
                    int i15 = i13;
                    int i16 = i13 + 1;
                    cArr2[i15] = HEX_CHARS[i14 >> 4];
                    int i17 = i16;
                    i4 = i16 + 1;
                    cArr2[i17] = HEX_CHARS[i14 & 15];
                    c2 = (char) (c2 & 255);
                } else {
                    int i18 = i13;
                    int i19 = i13 + 1;
                    cArr2[i18] = '0';
                    int i20 = i19;
                    i4 = i19 + 1;
                    cArr2[i20] = '0';
                }
                int i21 = i4;
                int i22 = i4 + 1;
                cArr2[i21] = HEX_CHARS[c2 >> 4];
                cArr2[i22] = HEX_CHARS[c2 & 15];
                i5 = i22 - 5;
            }
            return i5;
        } else {
            if (this._currentEscape == null) {
                value = this._characterEscapes.getEscapeSequence(c2).getValue();
            } else {
                value = this._currentEscape.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (i5 < length || i5 >= i6) {
                this._writer.write(value);
            } else {
                i5 -= length;
                value.getChars(0, length, cArr2, i5);
            }
            return i5;
        }
    }

    private void _appendCharacterEscape(char c, int i) throws IOException, JsonGenerationException {
        String value;
        int i2;
        char c2 = c;
        int i3 = i;
        if (i3 >= 0) {
            if (this._outputTail + 2 > this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int i4 = this._outputTail;
            int i5 = i4 + 1;
            this._outputTail = i5;
            cArr[i4] = '\\';
            char[] cArr2 = this._outputBuffer;
            int i6 = this._outputTail;
            int i7 = i6 + 1;
            this._outputTail = i7;
            cArr2[i6] = (char) i3;
        } else if (i3 != -2) {
            if (this._outputTail + 2 > this._outputEnd) {
                _flushBuffer();
            }
            int i8 = this._outputTail;
            char[] cArr3 = this._outputBuffer;
            int i9 = i8;
            int i10 = i8 + 1;
            cArr3[i9] = '\\';
            int i11 = i10;
            int i12 = i10 + 1;
            cArr3[i11] = 'u';
            if (c2 > 255) {
                int i13 = (c2 >> 8) & 255;
                int i14 = i12;
                int i15 = i12 + 1;
                cArr3[i14] = HEX_CHARS[i13 >> 4];
                int i16 = i15;
                i2 = i15 + 1;
                cArr3[i16] = HEX_CHARS[i13 & 15];
                c2 = (char) (c2 & 255);
            } else {
                int i17 = i12;
                int i18 = i12 + 1;
                cArr3[i17] = '0';
                int i19 = i18;
                i2 = i18 + 1;
                cArr3[i19] = '0';
            }
            int i20 = i2;
            int i21 = i2 + 1;
            cArr3[i20] = HEX_CHARS[c2 >> 4];
            cArr3[i21] = HEX_CHARS[c2 & 15];
            this._outputTail = i21;
        } else {
            if (this._currentEscape == null) {
                value = this._characterEscapes.getEscapeSequence(c2).getValue();
            } else {
                value = this._currentEscape.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (this._outputTail + length > this._outputEnd) {
                _flushBuffer();
                if (length > this._outputEnd) {
                    this._writer.write(value);
                    return;
                }
            }
            value.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
        }
    }

    private char[] _allocateEntityBuffer() {
        char[] cArr = new char[14];
        cArr[0] = '\\';
        cArr[2] = '\\';
        cArr[3] = 'u';
        cArr[4] = '0';
        cArr[5] = '0';
        cArr[8] = '\\';
        cArr[9] = 'u';
        char[] cArr2 = cArr;
        this._entityBuffer = cArr2;
        return cArr;
    }

    /* access modifiers changed from: protected */
    public void _flushBuffer() throws IOException {
        int i = this._outputTail - this._outputHead;
        if (i > 0) {
            int i2 = this._outputHead;
            this._outputHead = 0;
            this._outputTail = 0;
            this._writer.write(this._outputBuffer, i2, i);
        }
    }
}
