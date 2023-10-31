package com.shaded.fasterxml.jackson.core.json;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.base.ParserBase;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;
import com.shaded.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.shaded.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public final class ReaderBasedJsonParser extends ParserBase {
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReaderBasedJsonParser(com.shaded.fasterxml.jackson.core.p005io.IOContext r10, int r11, java.io.Reader r12, com.shaded.fasterxml.jackson.core.ObjectCodec r13, com.shaded.fasterxml.jackson.core.sym.CharsToNameCanonicalizer r14) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r0
            r7 = r1
            r8 = r2
            r6.<init>(r7, r8)
            r6 = r0
            r7 = 0
            r6._tokenIncomplete = r7
            r6 = r0
            r7 = r3
            r6._reader = r7
            r6 = r0
            r7 = r1
            char[] r7 = r7.allocTokenBuffer()
            r6._inputBuffer = r7
            r6 = r0
            r7 = r4
            r6._objectCodec = r7
            r6 = r0
            r7 = r5
            r6._symbols = r7
            r6 = r0
            r7 = r5
            int r7 = r7.hashSeed()
            r6._hashSeed = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser.<init>(com.shaded.fasterxml.jackson.core.io.IOContext, int, java.io.Reader, com.shaded.fasterxml.jackson.core.ObjectCodec, com.shaded.fasterxml.jackson.core.sym.CharsToNameCanonicalizer):void");
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public void setCodec(ObjectCodec objectCodec) {
        ObjectCodec objectCodec2 = objectCodec;
        this._objectCodec = objectCodec2;
    }

    public int releaseBuffered(Writer writer) throws IOException {
        Writer writer2 = writer;
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        writer2.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    public Object getInputSource() {
        return this._reader;
    }

    /* access modifiers changed from: protected */
    public boolean loadMore() throws IOException {
        Throwable th;
        StringBuilder sb;
        this._currInputProcessed += (long) this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._reader != null) {
            int read = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                Throwable th2 = th;
                new StringBuilder();
                new IOException(sb.append("Reader returned 0 characters when trying to read ").append(this._inputEnd).toString());
                throw th2;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public char getNextChar(String str) throws IOException, JsonParseException {
        String str2 = str;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(str2);
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        return cArr[i];
    }

    /* access modifiers changed from: protected */
    public void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        char[] cArr = this._inputBuffer;
        if (cArr != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(cArr);
        }
    }

    public String getText() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return _getText2(jsonToken);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public String getValueAsString() throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString((String) null);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public String getValueAsString(String str) throws IOException, JsonParseException {
        String str2 = str;
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString(str2);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    /* access modifiers changed from: protected */
    public String _getText2(JsonToken jsonToken) {
        JsonToken jsonToken2 = jsonToken;
        if (jsonToken2 == null) {
            return null;
        }
        switch (jsonToken2) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName();
            case VALUE_STRING:
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken2.asString();
        }
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return null;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                if (!this._nameCopied) {
                    String currentName = this._parsingContext.getCurrentName();
                    int length = currentName.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                    } else if (this._nameCopyBuffer.length < length) {
                        this._nameCopyBuffer = new char[length];
                    }
                    currentName.getChars(0, length, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            case VALUE_STRING:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                break;
            default:
                return this._currToken.asCharArray();
        }
        return this._textBuffer.getTextBuffer();
    }

    public int getTextLength() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName().length();
            case VALUE_STRING:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                break;
            default:
                return this._currToken.asCharArray().length;
        }
        return this._textBuffer.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return r0._textBuffer.getTextOffset();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getTextOffset() throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r3 = this;
            r0 = r3
            r1 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r1 = r1._currToken
            if (r1 == 0) goto L_0x0014
            int[] r1 = com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser.C14391.$SwitchMap$com$fasterxml$jackson$core$JsonToken
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r2 = r2._currToken
            int r2 = r2.ordinal()
            r1 = r1[r2]
            switch(r1) {
                case 1: goto L_0x0017;
                case 2: goto L_0x001a;
                case 3: goto L_0x0027;
                case 4: goto L_0x0027;
                default: goto L_0x0014;
            }
        L_0x0014:
            r1 = 0
            r0 = r1
        L_0x0016:
            return r0
        L_0x0017:
            r1 = 0
            r0 = r1
            goto L_0x0016
        L_0x001a:
            r1 = r0
            boolean r1 = r1._tokenIncomplete
            if (r1 == 0) goto L_0x0027
            r1 = r0
            r2 = 0
            r1._tokenIncomplete = r2
            r1 = r0
            r1._finishString()
        L_0x0027:
            r1 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r1 = r1._textBuffer
            int r1 = r1.getTextOffset()
            r0 = r1
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser.getTextOffset():int");
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        StringBuilder sb;
        StringBuilder sb2;
        Base64Variant base64Variant2 = base64Variant;
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            new StringBuilder();
            _reportError(sb2.append("Current token (").append(this._currToken).append(") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary").toString());
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant2);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                IllegalArgumentException illegalArgumentException = e;
                new StringBuilder();
                throw _constructError(sb.append("Failed to decode VALUE_STRING as base64 (").append(base64Variant2).append("): ").append(illegalArgumentException.getMessage()).toString());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant2);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    /* JADX INFO: finally extract failed */
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
        Base64Variant base64Variant2 = base64Variant;
        OutputStream outputStream2 = outputStream;
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant2);
            outputStream2.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            int _readBinary = _readBinary(base64Variant2, outputStream2, allocBase64Buffer);
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            return _readBinary;
        } catch (Throwable th) {
            Throwable th2 = th;
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public int _readBinary(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) throws IOException, JsonParseException {
        StringBuilder sb;
        Base64Variant base64Variant2 = base64Variant;
        OutputStream outputStream2 = outputStream;
        byte[] bArr2 = bArr;
        int i = 0;
        int length = bArr2.length - 3;
        int i2 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            char c = cArr[i3];
            if (c > ' ') {
                int decodeBase64Char = base64Variant2.decodeBase64Char(c);
                if (decodeBase64Char < 0) {
                    if (c == '\"') {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant2, c, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (i > length) {
                    i2 += i;
                    outputStream2.write(bArr2, 0, i);
                    i = 0;
                }
                int i5 = decodeBase64Char;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i6 = this._inputPtr;
                int i7 = i6 + 1;
                this._inputPtr = i7;
                char c2 = cArr2[i6];
                int decodeBase64Char2 = base64Variant2.decodeBase64Char(c2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant2, c2, 1);
                }
                int i8 = (i5 << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i9 = this._inputPtr;
                int i10 = i9 + 1;
                this._inputPtr = i10;
                char c3 = cArr3[i9];
                int decodeBase64Char3 = base64Variant2.decodeBase64Char(c3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c3 == '\"' && !base64Variant2.usesPadding()) {
                            int i11 = i;
                            i++;
                            bArr2[i11] = (byte) (i8 >> 4);
                            break;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant2, c3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i12 = this._inputPtr;
                        int i13 = i12 + 1;
                        this._inputPtr = i13;
                        char c4 = cArr4[i12];
                        if (!base64Variant2.usesPaddingChar(c4)) {
                            new StringBuilder();
                            throw reportInvalidBase64Char(base64Variant2, c4, 3, sb.append("expected padding character '").append(base64Variant2.getPaddingChar()).append("'").toString());
                        }
                        int i14 = i;
                        i++;
                        bArr2[i14] = (byte) (i8 >> 4);
                    }
                }
                int i15 = (i8 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i16 = this._inputPtr;
                int i17 = i16 + 1;
                this._inputPtr = i17;
                char c5 = cArr5[i16];
                int decodeBase64Char4 = base64Variant2.decodeBase64Char(c5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c5 == '\"' && !base64Variant2.usesPadding()) {
                            int i18 = i15 >> 2;
                            int i19 = i;
                            int i20 = i + 1;
                            bArr2[i19] = (byte) (i18 >> 8);
                            int i21 = i20;
                            i = i20 + 1;
                            bArr2[i21] = (byte) i18;
                            break;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant2, c5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        int i22 = i15 >> 2;
                        int i23 = i;
                        int i24 = i + 1;
                        bArr2[i23] = (byte) (i22 >> 8);
                        int i25 = i24;
                        i = i24 + 1;
                        bArr2[i25] = (byte) i22;
                    }
                }
                int i26 = (i15 << 6) | decodeBase64Char4;
                int i27 = i;
                int i28 = i + 1;
                bArr2[i27] = (byte) (i26 >> 16);
                int i29 = i28;
                int i30 = i28 + 1;
                bArr2[i29] = (byte) (i26 >> 8);
                int i31 = i30;
                i = i30 + 1;
                bArr2[i31] = (byte) i26;
            }
        }
        this._tokenIncomplete = false;
        if (i > 0) {
            i2 += i;
            outputStream2.write(bArr2, 0, i);
        }
        return i2;
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken parseNumberText;
        StringBuilder sb;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + ((long) this._inputPtr)) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            JsonToken jsonToken2 = jsonToken;
            this._currToken = jsonToken2;
            return jsonToken;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken3 = JsonToken.END_OBJECT;
            JsonToken jsonToken4 = jsonToken3;
            this._currToken = jsonToken4;
            return jsonToken3;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    new StringBuilder();
                    _reportUnexpectedChar(_skipWSOrEnd, sb.append("was expecting comma to separate ").append(this._parsingContext.getTypeDesc()).append(" entries").toString());
                }
                _skipWSOrEnd = _skipWS();
            }
            boolean inObject = this._parsingContext.inObject();
            if (inObject) {
                this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd));
                this._currToken = JsonToken.FIELD_NAME;
                int _skipWS = _skipWS();
                if (_skipWS != 58) {
                    _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
                }
                _skipWSOrEnd = _skipWS();
            }
            switch (_skipWSOrEnd) {
                case 34:
                    this._tokenIncomplete = true;
                    parseNumberText = JsonToken.VALUE_STRING;
                    break;
                case 45:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    parseNumberText = parseNumberText(_skipWSOrEnd);
                    break;
                case 91:
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    parseNumberText = JsonToken.START_ARRAY;
                    break;
                case 93:
                case 125:
                    _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
                    break;
                case 102:
                    _matchToken("false", 1);
                    parseNumberText = JsonToken.VALUE_FALSE;
                    break;
                case 110:
                    _matchToken("null", 1);
                    parseNumberText = JsonToken.VALUE_NULL;
                    break;
                case 116:
                    break;
                case 123:
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    parseNumberText = JsonToken.START_OBJECT;
                    break;
                default:
                    parseNumberText = _handleUnexpectedValue(_skipWSOrEnd);
                    break;
            }
            _matchToken("true", 1);
            parseNumberText = JsonToken.VALUE_TRUE;
            if (inObject) {
                this._nextToken = parseNumberText;
                return this._currToken;
            }
            this._currToken = parseNumberText;
            return parseNumberText;
        }
    }

    private JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        JsonToken jsonToken2 = jsonToken;
        JsonToken jsonToken3 = jsonToken2;
        this._currToken = jsonToken3;
        return jsonToken2;
    }

    public String nextTextValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        return nextToken() == JsonToken.VALUE_STRING ? getText() : null;
    }

    public int nextIntValue(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
                return getIntValue();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return i2;
        }
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i2;
    }

    public long nextLongValue(long j) throws IOException, JsonParseException {
        long j2 = j;
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
                return getLongValue();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return j2;
        }
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j2;
    }

    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        switch (nextToken()) {
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0099, code lost:
        if (r7 != 0) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009b, code lost:
        reportUnexpectedNumberChar(r1, "Decimal point not followed by a digit");
     */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.core.JsonToken parseNumberText(int r16) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r10 = r1
            r11 = 45
            if (r10 != r11) goto L_0x0032
            r10 = 1
        L_0x0009:
            r2 = r10
            r10 = r0
            int r10 = r10._inputPtr
            r3 = r10
            r10 = r3
            r11 = 1
            int r10 = r10 + -1
            r4 = r10
            r10 = r0
            int r10 = r10._inputEnd
            r5 = r10
            r10 = r2
            if (r10 == 0) goto L_0x0054
            r10 = r3
            r11 = r0
            int r11 = r11._inputEnd
            if (r10 < r11) goto L_0x0034
        L_0x0020:
            r10 = r0
            r11 = r2
            if (r11 == 0) goto L_0x0122
            r11 = r4
            r12 = 1
            int r11 = r11 + 1
        L_0x0028:
            r10._inputPtr = r11
            r10 = r0
            r11 = r2
            com.shaded.fasterxml.jackson.core.JsonToken r10 = r10.parseNumberText2(r11)
            r0 = r10
        L_0x0031:
            return r0
        L_0x0032:
            r10 = 0
            goto L_0x0009
        L_0x0034:
            r10 = r0
            char[] r10 = r10._inputBuffer
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10[r11]
            r1 = r10
            r10 = r1
            r11 = 57
            if (r10 > r11) goto L_0x0047
            r10 = r1
            r11 = 48
            if (r10 >= r11) goto L_0x0054
        L_0x0047:
            r10 = r0
            r11 = r3
            r10._inputPtr = r11
            r10 = r0
            r11 = r1
            r12 = 1
            com.shaded.fasterxml.jackson.core.JsonToken r10 = r10._handleInvalidNumberStart(r11, r12)
            r0 = r10
            goto L_0x0031
        L_0x0054:
            r10 = r1
            r11 = 48
            if (r10 != r11) goto L_0x005a
            goto L_0x0020
        L_0x005a:
            r10 = 1
            r6 = r10
        L_0x005c:
            r10 = r3
            r11 = r0
            int r11 = r11._inputEnd
            if (r10 < r11) goto L_0x0063
            goto L_0x0020
        L_0x0063:
            r10 = r0
            char[] r10 = r10._inputBuffer
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10[r11]
            r1 = r10
            r10 = r1
            r11 = 48
            if (r10 < r11) goto L_0x0076
            r10 = r1
            r11 = 57
            if (r10 <= r11) goto L_0x0082
        L_0x0076:
            r10 = 0
            r7 = r10
            r10 = r1
            r11 = 46
            if (r10 != r11) goto L_0x00a3
        L_0x007d:
            r10 = r3
            r11 = r5
            if (r10 < r11) goto L_0x0085
            goto L_0x0020
        L_0x0082:
            int r6 = r6 + 1
            goto L_0x005c
        L_0x0085:
            r10 = r0
            char[] r10 = r10._inputBuffer
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10[r11]
            r1 = r10
            r10 = r1
            r11 = 48
            if (r10 < r11) goto L_0x0098
            r10 = r1
            r11 = 57
            if (r10 <= r11) goto L_0x00b5
        L_0x0098:
            r10 = r7
            if (r10 != 0) goto L_0x00a3
            r10 = r0
            r11 = r1
            java.lang.String r12 = "Decimal point not followed by a digit"
            r10.reportUnexpectedNumberChar(r11, r12)
        L_0x00a3:
            r10 = 0
            r8 = r10
            r10 = r1
            r11 = 101(0x65, float:1.42E-43)
            if (r10 == r11) goto L_0x00af
            r10 = r1
            r11 = 69
            if (r10 != r11) goto L_0x0101
        L_0x00af:
            r10 = r3
            r11 = r5
            if (r10 < r11) goto L_0x00b8
            goto L_0x0020
        L_0x00b5:
            int r7 = r7 + 1
            goto L_0x007d
        L_0x00b8:
            r10 = r0
            char[] r10 = r10._inputBuffer
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10[r11]
            r1 = r10
            r10 = r1
            r11 = 45
            if (r10 == r11) goto L_0x00cb
            r10 = r1
            r11 = 43
            if (r10 != r11) goto L_0x00da
        L_0x00cb:
            r10 = r3
            r11 = r5
            if (r10 < r11) goto L_0x00d1
            goto L_0x0020
        L_0x00d1:
            r10 = r0
            char[] r10 = r10._inputBuffer
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10[r11]
            r1 = r10
        L_0x00da:
            r10 = r1
            r11 = 57
            if (r10 > r11) goto L_0x00f6
            r10 = r1
            r11 = 48
            if (r10 < r11) goto L_0x00f6
            int r8 = r8 + 1
            r10 = r3
            r11 = r5
            if (r10 < r11) goto L_0x00ec
            goto L_0x0020
        L_0x00ec:
            r10 = r0
            char[] r10 = r10._inputBuffer
            r11 = r3
            int r3 = r3 + 1
            char r10 = r10[r11]
            r1 = r10
            goto L_0x00da
        L_0x00f6:
            r10 = r8
            if (r10 != 0) goto L_0x0101
            r10 = r0
            r11 = r1
            java.lang.String r12 = "Exponent indicator not followed by a digit"
            r10.reportUnexpectedNumberChar(r11, r12)
        L_0x0101:
            int r3 = r3 + -1
            r10 = r0
            r11 = r3
            r10._inputPtr = r11
            r10 = r3
            r11 = r4
            int r10 = r10 - r11
            r9 = r10
            r10 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r10 = r10._textBuffer
            r11 = r0
            char[] r11 = r11._inputBuffer
            r12 = r4
            r13 = r9
            r10.resetWithShared(r11, r12, r13)
            r10 = r0
            r11 = r2
            r12 = r6
            r13 = r7
            r14 = r8
            com.shaded.fasterxml.jackson.core.JsonToken r10 = r10.reset(r11, r12, r13, r14)
            r0 = r10
            goto L_0x0031
        L_0x0122:
            r11 = r4
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser.parseNumberText(int):com.shaded.fasterxml.jackson.core.JsonToken");
    }

    private JsonToken parseNumberText2(boolean z) throws IOException, JsonParseException {
        char nextChar;
        int i;
        char nextChar2;
        char nextChar3;
        StringBuilder sb;
        boolean z2 = z;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i2 = 0;
        if (z2) {
            i2 = 0 + 1;
            emptyAndGetCurrentSegment[0] = '-';
        }
        int i3 = 0;
        if (this._inputPtr < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            int i4 = this._inputPtr;
            int i5 = i4 + 1;
            this._inputPtr = i5;
            nextChar = cArr[i4];
        } else {
            nextChar = getNextChar("No digit following minus sign");
        }
        char c = nextChar;
        if (c == '0') {
            c = _verifyNoLeadingZeroes();
        }
        boolean z3 = false;
        while (true) {
            if (c < '0' || c > '9') {
                break;
            }
            i3++;
            if (i2 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            int i6 = i2;
            i2++;
            emptyAndGetCurrentSegment[i6] = c;
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                c = 0;
                z3 = true;
                break;
            }
            char[] cArr2 = this._inputBuffer;
            int i7 = this._inputPtr;
            int i8 = i7 + 1;
            this._inputPtr = i8;
            c = cArr2[i7];
        }
        if (i3 == 0) {
            new StringBuilder();
            reportInvalidNumber(sb.append("Missing integer part (next char ").append(_getCharDesc(c)).append(")").toString());
        }
        int i9 = 0;
        if (c == '.') {
            int i10 = i;
            i++;
            emptyAndGetCurrentSegment[i10] = c;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z3 = true;
                    break;
                }
                char[] cArr3 = this._inputBuffer;
                int i11 = this._inputPtr;
                int i12 = i11 + 1;
                this._inputPtr = i12;
                c = cArr3[i11];
                if (c < '0' || c > '9') {
                    break;
                }
                i9++;
                if (i >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i = 0;
                }
                int i13 = i;
                i++;
                emptyAndGetCurrentSegment[i13] = c;
            }
            if (i9 == 0) {
                reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
            }
        }
        int i14 = 0;
        if (c == 'e' || c == 'E') {
            if (i >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i15 = i;
            int i16 = i + 1;
            emptyAndGetCurrentSegment[i15] = c;
            if (this._inputPtr < this._inputEnd) {
                char[] cArr4 = this._inputBuffer;
                int i17 = this._inputPtr;
                int i18 = i17 + 1;
                this._inputPtr = i18;
                nextChar2 = cArr4[i17];
            } else {
                nextChar2 = getNextChar("expected a digit for number exponent");
            }
            char c2 = nextChar2;
            if (c2 == '-' || c2 == '+') {
                if (i16 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i16 = 0;
                }
                int i19 = i16;
                i16++;
                emptyAndGetCurrentSegment[i19] = c2;
                if (this._inputPtr < this._inputEnd) {
                    char[] cArr5 = this._inputBuffer;
                    int i20 = this._inputPtr;
                    int i21 = i20 + 1;
                    this._inputPtr = i21;
                    nextChar3 = cArr5[i20];
                } else {
                    nextChar3 = getNextChar("expected a digit for number exponent");
                }
                c2 = nextChar3;
            }
            while (true) {
                if (c2 > '9' || c2 < '0') {
                    break;
                }
                i14++;
                if (i16 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i16 = 0;
                }
                int i22 = i16;
                i16++;
                emptyAndGetCurrentSegment[i22] = c2;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z3 = true;
                    break;
                }
                char[] cArr6 = this._inputBuffer;
                int i23 = this._inputPtr;
                int i24 = i23 + 1;
                this._inputPtr = i24;
                c2 = cArr6[i23];
            }
            if (i14 == 0) {
                reportUnexpectedNumberChar(c2, "Exponent indicator not followed by a digit");
            }
        }
        if (!z3) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i);
        return reset(z2, i3, i9, i14);
    }

    private char _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return '0';
        }
        char c = this._inputBuffer[this._inputPtr];
        if (c < '0' || c > '9') {
            return '0';
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (c == '0') {
            do {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    break;
                }
                c = this._inputBuffer[this._inputPtr];
                if (c < '0' || c > '9') {
                    return '0';
                }
                this._inputPtr++;
            } while (c == '0');
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleInvalidNumberStart(int i, boolean z) throws IOException, JsonParseException {
        StringBuilder sb;
        StringBuilder sb2;
        double d;
        char c = i;
        boolean z2 = z;
        if (c == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            char[] cArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            c = cArr[i2];
            if (c == 78) {
                String str = z2 ? "-INF" : "+INF";
                _matchToken(str, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    String str2 = str;
                    if (z2) {
                        d = Double.NEGATIVE_INFINITY;
                    } else {
                        d = Double.POSITIVE_INFINITY;
                    }
                    return resetAsNaN(str2, d);
                }
                new StringBuilder();
                _reportError(sb2.append("Non-standard token '").append(str).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
            } else if (c == 110) {
                String str3 = z2 ? "-Infinity" : "+Infinity";
                _matchToken(str3, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN(str3, z2 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                new StringBuilder();
                _reportError(sb.append("Non-standard token '").append(str3).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
            }
        }
        reportUnexpectedNumberChar(c, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: char} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String _parseFieldName(int r15) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r9 = r1
            r10 = 34
            if (r9 == r10) goto L_0x000f
            r9 = r0
            r10 = r1
            java.lang.String r9 = r9._handleUnusualFieldName(r10)
            r0 = r9
        L_0x000e:
            return r0
        L_0x000f:
            r9 = r0
            int r9 = r9._inputPtr
            r2 = r9
            r9 = r0
            int r9 = r9._hashSeed
            r3 = r9
            r9 = r0
            int r9 = r9._inputEnd
            r4 = r9
            r9 = r2
            r10 = r4
            if (r9 >= r10) goto L_0x0067
            int[] r9 = com.shaded.fasterxml.jackson.core.p005io.CharTypes.getInputCodeLatin1()
            r5 = r9
            r9 = r5
            int r9 = r9.length
            r6 = r9
        L_0x0027:
            r9 = r0
            char[] r9 = r9._inputBuffer
            r10 = r2
            char r9 = r9[r10]
            r7 = r9
            r9 = r7
            r10 = r6
            if (r9 >= r10) goto L_0x0059
            r9 = r5
            r10 = r7
            r9 = r9[r10]
            if (r9 == 0) goto L_0x0059
            r9 = r7
            r10 = 34
            if (r9 != r10) goto L_0x0067
            r9 = r0
            int r9 = r9._inputPtr
            r8 = r9
            r9 = r0
            r10 = r2
            r11 = 1
            int r10 = r10 + 1
            r9._inputPtr = r10
            r9 = r0
            com.shaded.fasterxml.jackson.core.sym.CharsToNameCanonicalizer r9 = r9._symbols
            r10 = r0
            char[] r10 = r10._inputBuffer
            r11 = r8
            r12 = r2
            r13 = r8
            int r12 = r12 - r13
            r13 = r3
            java.lang.String r9 = r9.findSymbol(r10, r11, r12, r13)
            r0 = r9
            goto L_0x000e
        L_0x0059:
            r9 = r3
            r10 = 33
            int r9 = r9 * 33
            r10 = r7
            int r9 = r9 + r10
            r3 = r9
            int r2 = r2 + 1
            r9 = r2
            r10 = r4
            if (r9 < r10) goto L_0x0027
        L_0x0067:
            r9 = r0
            int r9 = r9._inputPtr
            r5 = r9
            r9 = r0
            r10 = r2
            r9._inputPtr = r10
            r9 = r0
            r10 = r5
            r11 = r3
            r12 = 34
            java.lang.String r9 = r9._parseFieldName2(r10, r11, r12)
            r0 = r9
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser._parseFieldName(int):java.lang.String");
    }

    private String _parseFieldName2(int i, int i2, int i3) throws IOException, JsonParseException {
        StringBuilder sb;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        this._textBuffer.resetWithShared(this._inputBuffer, i4, this._inputPtr - i4);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                new StringBuilder();
                _reportInvalidEOF(sb.append(": was expecting closing '").append((char) i6).append("' for name").toString());
            }
            char[] cArr = this._inputBuffer;
            int i7 = this._inputPtr;
            int i8 = i7 + 1;
            this._inputPtr = i8;
            char c = cArr[i7];
            char c2 = c;
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    c = _decodeEscaped();
                } else if (c2 <= i6) {
                    if (c2 == i6) {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        TextBuffer textBuffer = this._textBuffer;
                        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i5);
                    } else if (c2 < ' ') {
                        _throwUnquotedSpace(c2, "name");
                    }
                }
            }
            i5 = (i5 * 33) + c2;
            int i9 = currentSegmentSize;
            currentSegmentSize++;
            currentSegment[i9] = c;
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
        }
    }

    /* access modifiers changed from: protected */
    public String _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        boolean isJavaIdentifierPart;
        int i2 = i;
        if (i2 == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i2, "was expecting double-quote to start field name");
        }
        int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        int length = inputCodeLatin1JsNames.length;
        if (i2 < length) {
            isJavaIdentifierPart = inputCodeLatin1JsNames[i2] == 0 && (i2 < 48 || i2 > 57);
        } else {
            isJavaIdentifierPart = Character.isJavaIdentifierPart((char) i2);
        }
        if (!isJavaIdentifierPart) {
            _reportUnexpectedChar(i2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i3 = this._inputPtr;
        int i4 = this._hashSeed;
        int i5 = this._inputEnd;
        if (i3 < i5) {
            do {
                char c = this._inputBuffer[i3];
                if (c < length) {
                    if (inputCodeLatin1JsNames[c] != 0) {
                        int i6 = this._inputPtr - 1;
                        this._inputPtr = i3;
                        return this._symbols.findSymbol(this._inputBuffer, i6, i3 - i6, i4);
                    }
                } else if (!Character.isJavaIdentifierPart((char) c)) {
                    int i7 = this._inputPtr - 1;
                    this._inputPtr = i3;
                    return this._symbols.findSymbol(this._inputBuffer, i7, i3 - i7, i4);
                }
                i4 = (i4 * 33) + c;
                i3++;
            } while (i3 < i5);
        }
        int i8 = this._inputPtr - 1;
        this._inputPtr = i3;
        return _parseUnusualFieldName2(i8, i4, inputCodeLatin1JsNames);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: char} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String _parseApostropheFieldName() throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r13 = this;
            r0 = r13
            r8 = r0
            int r8 = r8._inputPtr
            r1 = r8
            r8 = r0
            int r8 = r8._hashSeed
            r2 = r8
            r8 = r0
            int r8 = r8._inputEnd
            r3 = r8
            r8 = r1
            r9 = r3
            if (r8 >= r9) goto L_0x004b
            int[] r8 = com.shaded.fasterxml.jackson.core.p005io.CharTypes.getInputCodeLatin1()
            r4 = r8
            r8 = r4
            int r8 = r8.length
            r5 = r8
        L_0x0019:
            r8 = r0
            char[] r8 = r8._inputBuffer
            r9 = r1
            char r8 = r8[r9]
            r6 = r8
            r8 = r6
            r9 = 39
            if (r8 != r9) goto L_0x0041
            r8 = r0
            int r8 = r8._inputPtr
            r7 = r8
            r8 = r0
            r9 = r1
            r10 = 1
            int r9 = r9 + 1
            r8._inputPtr = r9
            r8 = r0
            com.shaded.fasterxml.jackson.core.sym.CharsToNameCanonicalizer r8 = r8._symbols
            r9 = r0
            char[] r9 = r9._inputBuffer
            r10 = r7
            r11 = r1
            r12 = r7
            int r11 = r11 - r12
            r12 = r2
            java.lang.String r8 = r8.findSymbol(r9, r10, r11, r12)
            r0 = r8
        L_0x0040:
            return r0
        L_0x0041:
            r8 = r6
            r9 = r5
            if (r8 >= r9) goto L_0x005e
            r8 = r4
            r9 = r6
            r8 = r8[r9]
            if (r8 == 0) goto L_0x005e
        L_0x004b:
            r8 = r0
            int r8 = r8._inputPtr
            r4 = r8
            r8 = r0
            r9 = r1
            r8._inputPtr = r9
            r8 = r0
            r9 = r4
            r10 = r2
            r11 = 39
            java.lang.String r8 = r8._parseFieldName2(r9, r10, r11)
            r0 = r8
            goto L_0x0040
        L_0x005e:
            r8 = r2
            r9 = 33
            int r8 = r8 * 33
            r9 = r6
            int r8 = r8 + r9
            r2 = r8
            int r1 = r1 + 1
            r8 = r1
            r9 = r3
            if (r8 < r9) goto L_0x0019
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser._parseApostropheFieldName():java.lang.String");
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        int i2 = i;
        switch (i2) {
            case 39:
                if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
                    return _handleApostropheValue();
                }
                break;
            case 43:
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                char[] cArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                return _handleInvalidNumberStart(cArr[i3], false);
            case 78:
                _matchToken("NaN", 1);
                if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                    break;
                } else {
                    return resetAsNaN("NaN", Double.NaN);
                }
        }
        _reportUnexpectedChar(i2, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleApostropheValue() throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            int i2 = i + 1;
            this._inputPtr = i2;
            char c = cArr[i];
            char c2 = c;
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    c = _decodeEscaped();
                } else if (c2 <= '\'') {
                    if (c2 == '\'') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    } else if (c2 < ' ') {
                        _throwUnquotedSpace(c2, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int i3 = currentSegmentSize;
            currentSegmentSize++;
            emptyAndGetCurrentSegment[i3] = c;
        }
    }

    private String _parseUnusualFieldName2(int i, int i2, int[] iArr) throws IOException, JsonParseException {
        int i3 = i;
        int i4 = i2;
        int[] iArr2 = iArr;
        this._textBuffer.resetWithShared(this._inputBuffer, i3, this._inputPtr - i3);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int length = iArr2.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            char c2 = c;
            if (c2 > length) {
                if (!Character.isJavaIdentifierPart(c)) {
                    break;
                }
            } else if (iArr2[c2] != 0) {
                break;
            }
            this._inputPtr++;
            i4 = (i4 * 33) + c2;
            int i5 = currentSegmentSize;
            currentSegmentSize++;
            currentSegment[i5] = c;
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
        }
        this._textBuffer.setCurrentLength(currentSegmentSize);
        TextBuffer textBuffer = this._textBuffer;
        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i4);
    }

    /* JADX WARNING: type inference failed for: r6v11, types: [int] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _finishString() throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r11 = this;
            r0 = r11
            r6 = r0
            int r6 = r6._inputPtr
            r1 = r6
            r6 = r0
            int r6 = r6._inputEnd
            r2 = r6
            r6 = r1
            r7 = r2
            if (r6 >= r7) goto L_0x004a
            int[] r6 = com.shaded.fasterxml.jackson.core.p005io.CharTypes.getInputCodeLatin1()
            r3 = r6
            r6 = r3
            int r6 = r6.length
            r4 = r6
        L_0x0015:
            r6 = r0
            char[] r6 = r6._inputBuffer
            r7 = r1
            char r6 = r6[r7]
            r5 = r6
            r6 = r5
            r7 = r4
            if (r6 >= r7) goto L_0x0044
            r6 = r3
            r7 = r5
            r6 = r6[r7]
            if (r6 == 0) goto L_0x0044
            r6 = r5
            r7 = 34
            if (r6 != r7) goto L_0x004a
            r6 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r6 = r6._textBuffer
            r7 = r0
            char[] r7 = r7._inputBuffer
            r8 = r0
            int r8 = r8._inputPtr
            r9 = r1
            r10 = r0
            int r10 = r10._inputPtr
            int r9 = r9 - r10
            r6.resetWithShared(r7, r8, r9)
            r6 = r0
            r7 = r1
            r8 = 1
            int r7 = r7 + 1
            r6._inputPtr = r7
        L_0x0043:
            return
        L_0x0044:
            int r1 = r1 + 1
            r6 = r1
            r7 = r2
            if (r6 < r7) goto L_0x0015
        L_0x004a:
            r6 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r6 = r6._textBuffer
            r7 = r0
            char[] r7 = r7._inputBuffer
            r8 = r0
            int r8 = r8._inputPtr
            r9 = r1
            r10 = r0
            int r10 = r10._inputPtr
            int r9 = r9 - r10
            r6.resetWithCopy(r7, r8, r9)
            r6 = r0
            r7 = r1
            r6._inputPtr = r7
            r6 = r0
            r6._finishString2()
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser._finishString():void");
    }

    /* access modifiers changed from: protected */
    public void _finishString2() throws IOException, JsonParseException {
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            int i2 = i + 1;
            this._inputPtr = i2;
            char c = cArr[i];
            char c2 = c;
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    c = _decodeEscaped();
                } else if (c2 <= '\"') {
                    if (c2 == '\"') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return;
                    } else if (c2 < ' ') {
                        _throwUnquotedSpace(c2, "string value");
                    }
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int i3 = currentSegmentSize;
            currentSegmentSize++;
            currentSegment[i3] = c;
        }
    }

    /* access modifiers changed from: protected */
    public void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        char[] cArr = this._inputBuffer;
        while (true) {
            if (i >= i2) {
                this._inputPtr = i;
                if (!loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            int i3 = i;
            i++;
            char c = cArr[i3];
            if (c <= '\\') {
                if (c == '\\') {
                    this._inputPtr = i;
                    char _decodeEscaped = _decodeEscaped();
                    i = this._inputPtr;
                    i2 = this._inputEnd;
                } else if (c > '\"') {
                    continue;
                } else if (c == '\"') {
                    this._inputPtr = i;
                    return;
                } else if (c < ' ') {
                    this._inputPtr = i;
                    _throwUnquotedSpace(c, "string value");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    /* access modifiers changed from: protected */
    public void _skipLF() throws IOException {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private int _skipWS() throws IOException, JsonParseException {
        StringBuilder sb;
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                int i2 = i + 1;
                this._inputPtr = i2;
                char c = cArr[i];
                if (c > ' ') {
                    if (c != '/') {
                        return c;
                    }
                    _skipComment();
                } else if (c != ' ') {
                    if (c == 10) {
                        _skipLF();
                    } else if (c == 13) {
                        _skipCR();
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                new StringBuilder();
                throw _constructError(sb.append("Unexpected end-of-input within/between ").append(this._parsingContext.getTypeDesc()).append(" entries").toString());
            }
        }
    }

    private int _skipWSOrEnd() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                int i2 = i + 1;
                this._inputPtr = i2;
                char c = cArr[i];
                if (c > ' ') {
                    if (c != '/') {
                        return c;
                    }
                    _skipComment();
                } else if (c != ' ') {
                    if (c == 10) {
                        _skipLF();
                    } else if (c == 13) {
                        _skipCR();
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    private void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        char c = cArr[i];
        if (c == '/') {
            _skipCppComment();
        } else if (c == '*') {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            int i2 = i + 1;
            this._inputPtr = i2;
            char c = cArr[i];
            if (c <= '*') {
                if (c == '*') {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        break;
                    } else if (this._inputBuffer[this._inputPtr] == '/') {
                        this._inputPtr++;
                        return;
                    }
                } else if (c < ' ') {
                    if (c == 10) {
                        _skipLF();
                    } else if (c == 13) {
                        _skipCR();
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            }
        }
        _reportInvalidEOF(" in a comment");
    }

    private void _skipCppComment() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                int i2 = i + 1;
                this._inputPtr = i2;
                char c = cArr[i];
                if (c < ' ') {
                    if (c == 10) {
                        _skipLF();
                        return;
                    } else if (c == 13) {
                        _skipCR();
                        return;
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public char _decodeEscaped() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        char c = cArr[i];
        switch (c) {
            case '\"':
            case '/':
            case '\\':
                return c;
            case 'b':
                return 8;
            case 'f':
                return 12;
            case 'n':
                return 10;
            case 'r':
                return 13;
            case 't':
                return 9;
            case 'u':
                int i3 = 0;
                for (int i4 = 0; i4 < 4; i4++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    char[] cArr2 = this._inputBuffer;
                    int i5 = this._inputPtr;
                    int i6 = i5 + 1;
                    this._inputPtr = i6;
                    char c2 = cArr2[i5];
                    int charToHex = CharTypes.charToHex(c2);
                    if (charToHex < 0) {
                        _reportUnexpectedChar(c2, "expected a hex-digit for character escape sequence");
                    }
                    i3 = (i3 << 4) | charToHex;
                }
                return (char) i3;
            default:
                return _handleUnrecognizedCharacterEscape(c);
        }
    }

    /* access modifiers changed from: protected */
    public void _matchToken(String str, int i) throws IOException, JsonParseException {
        char c;
        String str2 = str;
        int i2 = i;
        int length = str2.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidToken(str2.substring(0, i2));
            }
            if (this._inputBuffer[this._inputPtr] != str2.charAt(i2)) {
                _reportInvalidToken(str2.substring(0, i2));
            }
            this._inputPtr++;
            i2++;
        } while (i2 < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (c = this._inputBuffer[this._inputPtr]) >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
            _reportInvalidToken(str2.substring(0, i2));
        }
    }

    /* access modifiers changed from: protected */
    public byte[] _decodeBase64(Base64Variant base64Variant) throws IOException, JsonParseException {
        StringBuilder sb;
        Base64Variant base64Variant2 = base64Variant;
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            int i2 = i + 1;
            this._inputPtr = i2;
            char c = cArr[i];
            if (c > ' ') {
                int decodeBase64Char = base64Variant2.decodeBase64Char(c);
                if (decodeBase64Char < 0) {
                    if (c == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant2, c, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                int i3 = decodeBase64Char;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                int i5 = i4 + 1;
                this._inputPtr = i5;
                char c2 = cArr2[i4];
                int decodeBase64Char2 = base64Variant2.decodeBase64Char(c2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant2, c2, 1);
                }
                int i6 = (i3 << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i7 = this._inputPtr;
                int i8 = i7 + 1;
                this._inputPtr = i8;
                char c3 = cArr3[i7];
                int decodeBase64Char3 = base64Variant2.decodeBase64Char(c3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c3 != '\"' || base64Variant2.usesPadding()) {
                            decodeBase64Char3 = _decodeBase64Escape(base64Variant2, c3, 2);
                        } else {
                            _getByteArrayBuilder.append(i6 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i9 = this._inputPtr;
                        int i10 = i9 + 1;
                        this._inputPtr = i10;
                        char c4 = cArr4[i9];
                        if (!base64Variant2.usesPaddingChar(c4)) {
                            new StringBuilder();
                            throw reportInvalidBase64Char(base64Variant2, c4, 3, sb.append("expected padding character '").append(base64Variant2.getPaddingChar()).append("'").toString());
                        }
                        _getByteArrayBuilder.append(i6 >> 4);
                    }
                }
                int i11 = (i6 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i12 = this._inputPtr;
                int i13 = i12 + 1;
                this._inputPtr = i13;
                char c5 = cArr5[i12];
                int decodeBase64Char4 = base64Variant2.decodeBase64Char(c5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c5 != '\"' || base64Variant2.usesPadding()) {
                            decodeBase64Char4 = _decodeBase64Escape(base64Variant2, c5, 3);
                        } else {
                            _getByteArrayBuilder.appendTwoBytes(i11 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i11 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i11 << 6) | decodeBase64Char4);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidToken(String str) throws IOException, JsonParseException {
        _reportInvalidToken(str, "'null', 'true', 'false' or NaN");
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidToken(String str, String str2) throws IOException, JsonParseException {
        StringBuilder sb;
        StringBuilder sb2;
        String str3 = str2;
        new StringBuilder(str);
        StringBuilder sb3 = sb;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this._inputPtr++;
            StringBuilder append = sb3.append(c);
        }
        new StringBuilder();
        _reportError(sb2.append("Unrecognized token '").append(sb3.toString()).append("': was expecting ").toString());
    }
}
