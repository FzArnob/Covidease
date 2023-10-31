package com.shaded.fasterxml.jackson.core.json;

import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.base.ParserBase;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;
import com.shaded.fasterxml.jackson.core.p005io.IOContext;
import com.shaded.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.shaded.fasterxml.jackson.core.sym.Name;
import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class UTF8StreamJsonParser extends ParserBase {
    static final byte BYTE_LF = 10;
    private static final int[] sInputCodesLatin1 = CharTypes.getInputCodeLatin1();
    private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer = new int[16];
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete = false;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UTF8StreamJsonParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public void setCodec(ObjectCodec objectCodec) {
        ObjectCodec objectCodec2 = objectCodec;
        this._objectCodec = objectCodec2;
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        OutputStream outputStream2 = outputStream;
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        outputStream2.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    public Object getInputSource() {
        return this._inputStream;
    }

    /* access modifiers changed from: protected */
    public boolean loadMore() throws IOException {
        Throwable th;
        StringBuilder sb;
        this._currInputProcessed += (long) this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._inputStream != null) {
            int read = this._inputStream.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                Throwable th2 = th;
                new StringBuilder();
                new IOException(sb.append("InputStream.read() returned 0 characters when trying to read ").append(this._inputBuffer.length).append(" bytes").toString());
                throw th2;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean _loadToHaveAtLeast(int i) throws IOException {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        if (this._inputStream == null) {
            return false;
        }
        int i3 = this._inputEnd - this._inputPtr;
        if (i3 <= 0 || this._inputPtr <= 0) {
            this._inputEnd = 0;
        } else {
            this._currInputProcessed += (long) this._inputPtr;
            this._currInputRowStart -= this._inputPtr;
            System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i3);
            this._inputEnd = i3;
        }
        this._inputPtr = 0;
        while (this._inputEnd < i2) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read != 0) {
                    return false;
                }
                Throwable th2 = th;
                new StringBuilder();
                new IOException(sb.append("InputStream.read() returned 0 characters when trying to read ").append(i3).append(" bytes").toString());
                throw th2;
            }
            this._inputEnd += read;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        if (this._bufferRecyclable && (bArr = this._inputBuffer) != null) {
            this._inputBuffer = null;
            this._ioContext.releaseReadIOBuffer(bArr);
        }
    }

    public String getText() throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return _getText2(this._currToken);
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
            int[] r1 = com.shaded.fasterxml.jackson.core.json.UTF8StreamJsonParser.C14401.$SwitchMap$com$fasterxml$jackson$core$JsonToken
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
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8StreamJsonParser.getTextOffset():int");
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
            byte[] bArr3 = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            byte b = bArr3[i3] & Ev3Constants.Opcode.TST;
            if (b > 32) {
                int decodeBase64Char = base64Variant2.decodeBase64Char((int) b);
                if (decodeBase64Char < 0) {
                    if (b == 34) {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant2, (int) b, 0);
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
                byte[] bArr4 = this._inputBuffer;
                int i6 = this._inputPtr;
                int i7 = i6 + 1;
                this._inputPtr = i7;
                byte b2 = bArr4[i6] & Ev3Constants.Opcode.TST;
                int decodeBase64Char2 = base64Variant2.decodeBase64Char((int) b2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant2, (int) b2, 1);
                }
                int i8 = (i5 << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i9 = this._inputPtr;
                int i10 = i9 + 1;
                this._inputPtr = i10;
                byte b3 = bArr5[i9] & Ev3Constants.Opcode.TST;
                int decodeBase64Char3 = base64Variant2.decodeBase64Char((int) b3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (b3 == 34 && !base64Variant2.usesPadding()) {
                            int i11 = i;
                            i++;
                            bArr2[i11] = (byte) (i8 >> 4);
                            break;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant2, (int) b3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr6 = this._inputBuffer;
                        int i12 = this._inputPtr;
                        int i13 = i12 + 1;
                        this._inputPtr = i13;
                        byte b4 = bArr6[i12] & Ev3Constants.Opcode.TST;
                        if (!base64Variant2.usesPaddingChar((int) b4)) {
                            new StringBuilder();
                            throw reportInvalidBase64Char(base64Variant2, b4, 3, sb.append("expected padding character '").append(base64Variant2.getPaddingChar()).append("'").toString());
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
                byte[] bArr7 = this._inputBuffer;
                int i16 = this._inputPtr;
                int i17 = i16 + 1;
                this._inputPtr = i17;
                byte b5 = bArr7[i16] & Ev3Constants.Opcode.TST;
                int decodeBase64Char4 = base64Variant2.decodeBase64Char((int) b5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (b5 == 34 && !base64Variant2.usesPadding()) {
                            int i18 = i15 >> 2;
                            int i19 = i;
                            int i20 = i + 1;
                            bArr2[i19] = (byte) (i18 >> 8);
                            int i21 = i20;
                            i = i20 + 1;
                            bArr2[i21] = (byte) i18;
                            break;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant2, (int) b5, 3);
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
            if (!this._parsingContext.inObject()) {
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd).getName());
            this._currToken = JsonToken.FIELD_NAME;
            int _skipWS = _skipWS();
            if (_skipWS != 58) {
                _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
            }
            int _skipWS2 = _skipWS();
            if (_skipWS2 == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            switch (_skipWS2) {
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
                    parseNumberText = parseNumberText(_skipWS2);
                    break;
                case 91:
                    parseNumberText = JsonToken.START_ARRAY;
                    break;
                case 93:
                case 125:
                    _reportUnexpectedChar(_skipWS2, "expected a value");
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
                    parseNumberText = JsonToken.START_OBJECT;
                    break;
                default:
                    parseNumberText = _handleUnexpectedValue(_skipWS2);
                    break;
            }
            _matchToken("true", 1);
            parseNumberText = JsonToken.VALUE_TRUE;
            this._nextToken = parseNumberText;
            return this._currToken;
        }
    }

    private JsonToken _nextTokenNotInObject(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (i2 == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            JsonToken jsonToken2 = jsonToken;
            this._currToken = jsonToken2;
            return jsonToken;
        }
        switch (i2) {
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
                JsonToken parseNumberText = parseNumberText(i2);
                JsonToken jsonToken3 = parseNumberText;
                this._currToken = jsonToken3;
                return parseNumberText;
            case 91:
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken4 = JsonToken.START_ARRAY;
                JsonToken jsonToken5 = jsonToken4;
                this._currToken = jsonToken5;
                return jsonToken4;
            case 93:
            case 125:
                _reportUnexpectedChar(i2, "expected a value");
                break;
            case 102:
                _matchToken("false", 1);
                JsonToken jsonToken6 = JsonToken.VALUE_FALSE;
                JsonToken jsonToken7 = jsonToken6;
                this._currToken = jsonToken7;
                return jsonToken6;
            case 110:
                _matchToken("null", 1);
                JsonToken jsonToken8 = JsonToken.VALUE_NULL;
                JsonToken jsonToken9 = jsonToken8;
                this._currToken = jsonToken9;
                return jsonToken8;
            case 116:
                break;
            case 123:
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken10 = JsonToken.START_OBJECT;
                JsonToken jsonToken11 = jsonToken10;
                this._currToken = jsonToken11;
                return jsonToken10;
            default:
                JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i2);
                JsonToken jsonToken12 = _handleUnexpectedValue;
                this._currToken = jsonToken12;
                return _handleUnexpectedValue;
        }
        _matchToken("true", 1);
        JsonToken jsonToken13 = JsonToken.VALUE_TRUE;
        JsonToken jsonToken14 = jsonToken13;
        this._currToken = jsonToken14;
        return jsonToken13;
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

    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        StringBuilder sb;
        SerializableString serializableString2 = serializableString;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            JsonToken _nextAfterName = _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
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
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_OBJECT;
            return false;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    new StringBuilder();
                    _reportUnexpectedChar(_skipWSOrEnd, sb.append("was expecting comma to separate ").append(this._parsingContext.getTypeDesc()).append(" entries").toString());
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                JsonToken _nextTokenNotInObject = _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString2.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                if (this._inputPtr + length < this._inputEnd) {
                    int i = this._inputPtr + length;
                    if (this._inputBuffer[i] == 34) {
                        int i2 = 0;
                        int i3 = this._inputPtr;
                        while (i2 != length) {
                            if (asQuotedUTF8[i2] == this._inputBuffer[i3 + i2]) {
                                i2++;
                            }
                        }
                        this._inputPtr = i + 1;
                        this._parsingContext.setCurrentName(serializableString2.getValue());
                        this._currToken = JsonToken.FIELD_NAME;
                        _isNextTokenNameYes();
                        return true;
                    }
                }
            }
            return _isNextTokenNameMaybe(_skipWSOrEnd, serializableString2);
        }
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [int] */
    /* JADX WARNING: type inference failed for: r2v35, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _isNextTokenNameYes() throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r8 = this;
            r0 = r8
            r2 = r0
            int r2 = r2._inputPtr
            r3 = r0
            int r3 = r3._inputEnd
            r4 = 1
            int r3 = r3 + -1
            if (r2 >= r3) goto L_0x008d
            r2 = r0
            byte[] r2 = r2._inputBuffer
            r3 = r0
            int r3 = r3._inputPtr
            byte r2 = r2[r3]
            r3 = 58
            if (r2 != r3) goto L_0x008d
            r2 = r0
            byte[] r2 = r2._inputBuffer
            r3 = r0
            r6 = r3
            r3 = r6
            r4 = r6
            int r4 = r4._inputPtr
            r5 = 1
            int r4 = r4 + 1
            r6 = r3
            r7 = r4
            r3 = r7
            r4 = r6
            r5 = r7
            r4._inputPtr = r5
            byte r2 = r2[r3]
            r1 = r2
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            int r3 = r3._inputPtr
            r4 = 1
            int r3 = r3 + 1
            r2._inputPtr = r3
            r2 = r1
            r3 = 34
            if (r2 != r3) goto L_0x0048
            r2 = r0
            r3 = 1
            r2._tokenIncomplete = r3
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_STRING
            r2._nextToken = r3
        L_0x0047:
            return
        L_0x0048:
            r2 = r1
            r3 = 123(0x7b, float:1.72E-43)
            if (r2 != r3) goto L_0x0053
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.START_OBJECT
            r2._nextToken = r3
            goto L_0x0047
        L_0x0053:
            r2 = r1
            r3 = 91
            if (r2 != r3) goto L_0x005e
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.START_ARRAY
            r2._nextToken = r3
            goto L_0x0047
        L_0x005e:
            r2 = r1
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r2
            r2 = r1
            r3 = 32
            if (r2 <= r3) goto L_0x006e
            r2 = r1
            r3 = 47
            if (r2 != r3) goto L_0x007f
        L_0x006e:
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            int r3 = r3._inputPtr
            r4 = 1
            int r3 = r3 + -1
            r2._inputPtr = r3
            r2 = r0
            int r2 = r2._skipWS()
            r1 = r2
        L_0x007f:
            r2 = r1
            switch(r2) {
                case 34: goto L_0x0094;
                case 45: goto L_0x00de;
                case 48: goto L_0x00de;
                case 49: goto L_0x00de;
                case 50: goto L_0x00de;
                case 51: goto L_0x00de;
                case 52: goto L_0x00de;
                case 53: goto L_0x00de;
                case 54: goto L_0x00de;
                case 55: goto L_0x00de;
                case 56: goto L_0x00de;
                case 57: goto L_0x00de;
                case 91: goto L_0x009e;
                case 93: goto L_0x00aa;
                case 102: goto L_0x00c0;
                case 110: goto L_0x00cf;
                case 116: goto L_0x00b2;
                case 123: goto L_0x00a4;
                case 125: goto L_0x00aa;
                default: goto L_0x0083;
            }
        L_0x0083:
            r2 = r0
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.core.JsonToken r3 = r3._handleUnexpectedValue(r4)
            r2._nextToken = r3
            goto L_0x0047
        L_0x008d:
            r2 = r0
            int r2 = r2._skipColon()
            r1 = r2
            goto L_0x007f
        L_0x0094:
            r2 = r0
            r3 = 1
            r2._tokenIncomplete = r3
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_STRING
            r2._nextToken = r3
            goto L_0x0047
        L_0x009e:
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.START_ARRAY
            r2._nextToken = r3
            goto L_0x0047
        L_0x00a4:
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.START_OBJECT
            r2._nextToken = r3
            goto L_0x0047
        L_0x00aa:
            r2 = r0
            r3 = r1
            java.lang.String r4 = "expected a value"
            r2._reportUnexpectedChar(r3, r4)
        L_0x00b2:
            r2 = r0
            java.lang.String r3 = "true"
            r4 = 1
            r2._matchToken(r3, r4)
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_TRUE
            r2._nextToken = r3
            goto L_0x0047
        L_0x00c0:
            r2 = r0
            java.lang.String r3 = "false"
            r4 = 1
            r2._matchToken(r3, r4)
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_FALSE
            r2._nextToken = r3
            goto L_0x0047
        L_0x00cf:
            r2 = r0
            java.lang.String r3 = "null"
            r4 = 1
            r2._matchToken(r3, r4)
            r2 = r0
            com.shaded.fasterxml.jackson.core.JsonToken r3 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_NULL
            r2._nextToken = r3
            goto L_0x0047
        L_0x00de:
            r2 = r0
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.core.JsonToken r3 = r3.parseNumberText(r4)
            r2._nextToken = r3
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8StreamJsonParser._isNextTokenNameYes():void");
    }

    private boolean _isNextTokenNameMaybe(int i, SerializableString serializableString) throws IOException, JsonParseException {
        JsonToken parseNumberText;
        String name = _parseFieldName(i).getName();
        this._parsingContext.setCurrentName(name);
        boolean equals = name.equals(serializableString.getValue());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipWS = _skipWS();
        if (_skipWS != 58) {
            _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
        }
        int _skipWS2 = _skipWS();
        if (_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return equals;
        }
        switch (_skipWS2) {
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
                parseNumberText = parseNumberText(_skipWS2);
                break;
            case 91:
                parseNumberText = JsonToken.START_ARRAY;
                break;
            case 93:
            case 125:
                _reportUnexpectedChar(_skipWS2, "expected a value");
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
                parseNumberText = JsonToken.START_OBJECT;
                break;
            default:
                parseNumberText = _handleUnexpectedValue(_skipWS2);
                break;
        }
        _matchToken("true", 1);
        parseNumberText = JsonToken.VALUE_TRUE;
        this._nextToken = parseNumberText;
        return equals;
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

    /* access modifiers changed from: protected */
    public JsonToken parseNumberText(int i) throws IOException, JsonParseException {
        byte b = i;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i2 = 0;
        boolean z = b == 45;
        if (z) {
            i2 = 0 + 1;
            emptyAndGetCurrentSegment[0] = '-';
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            b = bArr[i3] & Ev3Constants.Opcode.TST;
            if (b < 48 || b > 57) {
                return _handleInvalidNumberStart(b, true);
            }
        }
        if (b == 48) {
            b = _verifyNoLeadingZeroes();
        }
        int i4 = i2;
        int i5 = i2 + 1;
        emptyAndGetCurrentSegment[i4] = (char) b;
        int i6 = 1;
        int length = this._inputPtr + emptyAndGetCurrentSegment.length;
        if (length > this._inputEnd) {
            length = this._inputEnd;
        }
        while (this._inputPtr < length) {
            byte[] bArr2 = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            byte b2 = bArr2[i7] & Ev3Constants.Opcode.TST;
            if (b2 >= 48 && b2 <= 57) {
                i6++;
                int i8 = i5;
                i5++;
                emptyAndGetCurrentSegment[i8] = (char) b2;
            } else if (b2 == 46 || b2 == 101 || b2 == 69) {
                return _parseFloatText(emptyAndGetCurrentSegment, i5, b2, z, i6);
            } else {
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i5);
                return resetInt(z, i6);
            }
        }
        return _parserNumber2(emptyAndGetCurrentSegment, i5, z, i6);
    }

    private JsonToken _parserNumber2(char[] cArr, int i, boolean z, int i2) throws IOException, JsonParseException {
        byte b;
        char[] cArr2 = cArr;
        int i3 = i;
        boolean z2 = z;
        int i4 = i2;
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i5 = this._inputPtr;
                int i6 = i5 + 1;
                this._inputPtr = i6;
                b = bArr[i5] & Ev3Constants.Opcode.TST;
                if (b <= 57 && b >= 48) {
                    if (i3 >= cArr2.length) {
                        cArr2 = this._textBuffer.finishCurrentSegment();
                        i3 = 0;
                    }
                    int i7 = i3;
                    i3++;
                    cArr2[i7] = (char) b;
                    i4++;
                }
            } else {
                this._textBuffer.setCurrentLength(i3);
                return resetInt(z2, i4);
            }
        }
        if (b == 46 || b == 101 || b == 69) {
            return _parseFloatText(cArr2, i3, b, z2, i4);
        }
        this._inputPtr--;
        this._textBuffer.setCurrentLength(i3);
        return resetInt(z2, i4);
    }

    private int _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return 48;
        }
        byte b = this._inputBuffer[this._inputPtr] & Ev3Constants.Opcode.TST;
        if (b < 48 || b > 57) {
            return 48;
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (b == 48) {
            do {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    break;
                }
                b = this._inputBuffer[this._inputPtr] & Ev3Constants.Opcode.TST;
                if (b < 48 || b > 57) {
                    return 48;
                }
                this._inputPtr++;
            } while (b == 48);
        }
        return b;
    }

    private JsonToken _parseFloatText(char[] cArr, int i, int i2, boolean z, int i3) throws IOException, JsonParseException {
        char[] cArr2 = cArr;
        int i4 = i;
        byte b = i2;
        boolean z2 = z;
        int i5 = i3;
        int i6 = 0;
        boolean z3 = false;
        if (b == 46) {
            int i7 = i4;
            i4++;
            cArr2[i7] = (char) b;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z3 = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i8 = this._inputPtr;
                int i9 = i8 + 1;
                this._inputPtr = i9;
                b = bArr[i8] & Ev3Constants.Opcode.TST;
                if (b < 48 || b > 57) {
                    break;
                }
                i6++;
                if (i4 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i4 = 0;
                }
                int i10 = i4;
                i4++;
                cArr2[i10] = (char) b;
            }
            if (i6 == 0) {
                reportUnexpectedNumberChar(b, "Decimal point not followed by a digit");
            }
        }
        int i11 = 0;
        if (b == 101 || b == 69) {
            if (i4 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i4 = 0;
            }
            int i12 = i4;
            int i13 = i4 + 1;
            cArr2[i12] = (char) b;
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i14 = this._inputPtr;
            int i15 = i14 + 1;
            this._inputPtr = i15;
            byte b2 = bArr2[i14] & Ev3Constants.Opcode.TST;
            if (b2 == 45 || b2 == 43) {
                if (i13 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i13 = 0;
                }
                int i16 = i13;
                i13++;
                cArr2[i16] = (char) b2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i17 = this._inputPtr;
                int i18 = i17 + 1;
                this._inputPtr = i18;
                b2 = bArr3[i17] & Ev3Constants.Opcode.TST;
            }
            while (true) {
                if (b2 > 57 || b2 < 48) {
                    break;
                }
                i11++;
                if (i13 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i13 = 0;
                }
                int i19 = i13;
                i13++;
                cArr2[i19] = (char) b2;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z3 = true;
                    break;
                }
                byte[] bArr4 = this._inputBuffer;
                int i20 = this._inputPtr;
                int i21 = i20 + 1;
                this._inputPtr = i21;
                b2 = bArr4[i20] & Ev3Constants.Opcode.TST;
            }
            if (i11 == 0) {
                reportUnexpectedNumberChar(b2, "Exponent indicator not followed by a digit");
            }
        }
        if (!z3) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i4);
        return resetFloat(z2, i5, i6, i11);
    }

    /* access modifiers changed from: protected */
    public Name _parseFieldName(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (i2 != 34) {
            return _handleUnusualFieldName(i2);
        }
        if (this._inputPtr + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3] & Ev3Constants.Opcode.TST;
        if (iArr[b] == 0) {
            int i5 = this._inputPtr;
            int i6 = i5 + 1;
            this._inputPtr = i6;
            byte b2 = bArr[i5] & Ev3Constants.Opcode.TST;
            if (iArr[b2] == 0) {
                byte b3 = (b << 8) | b2;
                int i7 = this._inputPtr;
                int i8 = i7 + 1;
                this._inputPtr = i8;
                byte b4 = bArr[i7] & Ev3Constants.Opcode.TST;
                if (iArr[b4] == 0) {
                    byte b5 = (b3 << 8) | b4;
                    int i9 = this._inputPtr;
                    int i10 = i9 + 1;
                    this._inputPtr = i10;
                    byte b6 = bArr[i9] & Ev3Constants.Opcode.TST;
                    if (iArr[b6] == 0) {
                        byte b7 = (b5 << 8) | b6;
                        int i11 = this._inputPtr;
                        int i12 = i11 + 1;
                        this._inputPtr = i12;
                        byte b8 = bArr[i11] & Ev3Constants.Opcode.TST;
                        if (iArr[b8] == 0) {
                            this._quad1 = b7;
                            return parseMediumFieldName(b8, iArr);
                        } else if (b8 == 34) {
                            return findName(b7, 4);
                        } else {
                            return parseFieldName(b7, b8, 4);
                        }
                    } else if (b6 == 34) {
                        return findName(b5, 3);
                    } else {
                        return parseFieldName(b5, b6, 3);
                    }
                } else if (b4 == 34) {
                    return findName(b3, 2);
                } else {
                    return parseFieldName(b3, b4, 2);
                }
            } else if (b2 == 34) {
                return findName(b, 1);
            } else {
                return parseFieldName(b, b2, 1);
            }
        } else if (b == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        } else {
            return parseFieldName(0, b, 0);
        }
    }

    /* access modifiers changed from: protected */
    public Name parseMediumFieldName(int i, int[] iArr) throws IOException, JsonParseException {
        int i2 = i;
        int[] iArr2 = iArr;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3] & Ev3Constants.Opcode.TST;
        if (iArr2[b] == 0) {
            byte b2 = (i2 << 8) | b;
            byte[] bArr2 = this._inputBuffer;
            int i5 = this._inputPtr;
            int i6 = i5 + 1;
            this._inputPtr = i6;
            byte b3 = bArr2[i5] & Ev3Constants.Opcode.TST;
            if (iArr2[b3] == 0) {
                byte b4 = (b2 << 8) | b3;
                byte[] bArr3 = this._inputBuffer;
                int i7 = this._inputPtr;
                int i8 = i7 + 1;
                this._inputPtr = i8;
                byte b5 = bArr3[i7] & Ev3Constants.Opcode.TST;
                if (iArr2[b5] == 0) {
                    byte b6 = (b4 << 8) | b5;
                    byte[] bArr4 = this._inputBuffer;
                    int i9 = this._inputPtr;
                    int i10 = i9 + 1;
                    this._inputPtr = i10;
                    byte b7 = bArr4[i9] & Ev3Constants.Opcode.TST;
                    if (iArr2[b7] == 0) {
                        this._quadBuffer[0] = this._quad1;
                        this._quadBuffer[1] = b6;
                        return parseLongFieldName(b7);
                    } else if (b7 == 34) {
                        return findName(this._quad1, b6, 4);
                    } else {
                        return parseFieldName(this._quad1, b6, b7, 4);
                    }
                } else if (b5 == 34) {
                    return findName(this._quad1, b4, 3);
                } else {
                    return parseFieldName(this._quad1, b4, b5, 3);
                }
            } else if (b3 == 34) {
                return findName(this._quad1, b2, 2);
            } else {
                return parseFieldName(this._quad1, b2, b3, 2);
            }
        } else if (b == 34) {
            return findName(this._quad1, i2, 1);
        } else {
            return parseFieldName(this._quad1, i2, b, 1);
        }
    }

    /* access modifiers changed from: protected */
    public Name parseLongFieldName(int i) throws IOException, JsonParseException {
        byte b = i;
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        while (this._inputEnd - this._inputPtr >= 4) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            byte b2 = bArr[i3] & Ev3Constants.Opcode.TST;
            if (iArr[b2] == 0) {
                byte b3 = (b << 8) | b2;
                byte[] bArr2 = this._inputBuffer;
                int i5 = this._inputPtr;
                int i6 = i5 + 1;
                this._inputPtr = i6;
                byte b4 = bArr2[i5] & Ev3Constants.Opcode.TST;
                if (iArr[b4] == 0) {
                    byte b5 = (b3 << 8) | b4;
                    byte[] bArr3 = this._inputBuffer;
                    int i7 = this._inputPtr;
                    int i8 = i7 + 1;
                    this._inputPtr = i8;
                    byte b6 = bArr3[i7] & Ev3Constants.Opcode.TST;
                    if (iArr[b6] == 0) {
                        byte b7 = (b5 << 8) | b6;
                        byte[] bArr4 = this._inputBuffer;
                        int i9 = this._inputPtr;
                        int i10 = i9 + 1;
                        this._inputPtr = i10;
                        byte b8 = bArr4[i9] & Ev3Constants.Opcode.TST;
                        if (iArr[b8] == 0) {
                            if (i2 >= this._quadBuffer.length) {
                                this._quadBuffer = growArrayBy(this._quadBuffer, i2);
                            }
                            int i11 = i2;
                            i2++;
                            this._quadBuffer[i11] = b7;
                            b = b8;
                        } else if (b8 == 34) {
                            return findName(this._quadBuffer, i2, b7, 4);
                        } else {
                            return parseEscapedFieldName(this._quadBuffer, i2, b7, b8, 4);
                        }
                    } else if (b6 == 34) {
                        return findName(this._quadBuffer, i2, b5, 3);
                    } else {
                        return parseEscapedFieldName(this._quadBuffer, i2, b5, b6, 3);
                    }
                } else if (b4 == 34) {
                    return findName(this._quadBuffer, i2, b3, 2);
                } else {
                    return parseEscapedFieldName(this._quadBuffer, i2, b3, b4, 2);
                }
            } else if (b2 == 34) {
                return findName(this._quadBuffer, i2, b, 1);
            } else {
                return parseEscapedFieldName(this._quadBuffer, i2, b, b2, 1);
            }
        }
        return parseEscapedFieldName(this._quadBuffer, i2, 0, b, 0);
    }

    /* access modifiers changed from: protected */
    public Name slowParseFieldName() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        byte b = bArr[i] & Ev3Constants.Opcode.TST;
        if (b == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        return parseEscapedFieldName(this._quadBuffer, 0, 0, b, 0);
    }

    private Name parseFieldName(int i, int i2, int i3) throws IOException, JsonParseException {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    private Name parseFieldName(int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        this._quadBuffer[0] = i;
        return parseEscapedFieldName(this._quadBuffer, 1, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public Name parseEscapedFieldName(int[] iArr, int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int i5;
        int i6;
        int[] iArr2 = iArr;
        int i7 = i;
        int i8 = i2;
        char c = i3;
        int i9 = i4;
        int[] iArr3 = sInputCodesLatin1;
        while (true) {
            if (iArr3[c] != 0) {
                if (c == 34) {
                    break;
                }
                if (c != 92) {
                    _throwUnquotedSpace(c, "name");
                } else {
                    c = _decodeEscaped();
                }
                if (c > 127) {
                    if (i5 >= 4) {
                        if (i7 >= iArr2.length) {
                            int[] growArrayBy = growArrayBy(iArr2, iArr2.length);
                            iArr2 = growArrayBy;
                            this._quadBuffer = growArrayBy;
                        }
                        int i10 = i7;
                        i7++;
                        iArr2[i10] = i6;
                        i6 = 0;
                        i5 = 0;
                    }
                    if (c < 2048) {
                        i6 = (i6 << 8) | 192 | (c >> 6);
                        i5++;
                    } else {
                        int i11 = (i6 << 8) | 224 | (c >> 12);
                        int i12 = i5 + 1;
                        if (i12 >= 4) {
                            if (i7 >= iArr2.length) {
                                int[] growArrayBy2 = growArrayBy(iArr2, iArr2.length);
                                iArr2 = growArrayBy2;
                                this._quadBuffer = growArrayBy2;
                            }
                            int i13 = i7;
                            i7++;
                            iArr2[i13] = i11;
                            i11 = 0;
                            i12 = 0;
                        }
                        i6 = (i11 << 8) | 128 | ((c >> 6) & 63);
                        i5 = i12 + 1;
                    }
                    c = 128 | (c & 63);
                }
            }
            if (i5 < 4) {
                i9 = i5 + 1;
                i8 = (i6 << 8) | c;
            } else {
                if (i7 >= iArr2.length) {
                    int[] growArrayBy3 = growArrayBy(iArr2, iArr2.length);
                    iArr2 = growArrayBy3;
                    this._quadBuffer = growArrayBy3;
                }
                int i14 = i7;
                i7++;
                iArr2[i14] = i6;
                i8 = c;
                i9 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr = this._inputBuffer;
            int i15 = this._inputPtr;
            int i16 = i15 + 1;
            this._inputPtr = i16;
            c = bArr[i15] & Ev3Constants.Opcode.TST;
        }
        if (i5 > 0) {
            if (i7 >= iArr2.length) {
                int[] growArrayBy4 = growArrayBy(iArr2, iArr2.length);
                iArr2 = growArrayBy4;
                this._quadBuffer = growArrayBy4;
            }
            int i17 = i7;
            i7++;
            iArr2[i17] = i6;
        }
        Name findName = this._symbols.findName(iArr2, i7);
        if (findName == null) {
            findName = addName(iArr2, i7, i5);
        }
        return findName;
    }

    /* access modifiers changed from: protected */
    public Name _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        byte b = i;
        if (b == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(b, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[b] != 0) {
            _reportUnexpectedChar(b, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArr = this._quadBuffer;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 < 4) {
                i4++;
                i3 = (i3 << 8) | b;
            } else {
                if (i2 >= iArr.length) {
                    int[] growArrayBy = growArrayBy(iArr, iArr.length);
                    iArr = growArrayBy;
                    this._quadBuffer = growArrayBy;
                }
                int i5 = i2;
                i2++;
                iArr[i5] = i3;
                i3 = b;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            b = this._inputBuffer[this._inputPtr] & Ev3Constants.Opcode.TST;
            if (inputCodeUtf8JsNames[b] != 0) {
                break;
            }
            this._inputPtr++;
        }
        if (i4 > 0) {
            if (i2 >= iArr.length) {
                int[] growArrayBy2 = growArrayBy(iArr, iArr.length);
                iArr = growArrayBy2;
                this._quadBuffer = growArrayBy2;
            }
            int i6 = i2;
            i2++;
            iArr[i6] = i3;
        }
        Name findName = this._symbols.findName(iArr, i2);
        if (findName == null) {
            findName = addName(iArr, i2, i4);
        }
        return findName;
    }

    /* access modifiers changed from: protected */
    public Name _parseApostropheFieldName() throws IOException, JsonParseException {
        int i;
        char c;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        int i3 = i2 + 1;
        this._inputPtr = i3;
        char c2 = bArr[i2] & 255;
        if (c2 == '\'') {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        int[] iArr = this._quadBuffer;
        int i4 = 0;
        char c3 = 0;
        int i5 = 0;
        int[] iArr2 = sInputCodesLatin1;
        while (c2 != '\'') {
            if (!(c2 == '\"' || iArr2[c2] == 0)) {
                if (c2 != '\\') {
                    _throwUnquotedSpace(c2, "name");
                } else {
                    c2 = _decodeEscaped();
                }
                if (c2 > 127) {
                    if (i >= 4) {
                        if (i4 >= iArr.length) {
                            int[] growArrayBy = growArrayBy(iArr, iArr.length);
                            iArr = growArrayBy;
                            this._quadBuffer = growArrayBy;
                        }
                        int i6 = i4;
                        i4++;
                        iArr[i6] = c;
                        c = 0;
                        i = 0;
                    }
                    if (c2 < 2048) {
                        c = (c << 8) | 192 | (c2 >> 6);
                        i++;
                    } else {
                        int i7 = (c << 8) | 224 | (c2 >> 12);
                        int i8 = i + 1;
                        if (i8 >= 4) {
                            if (i4 >= iArr.length) {
                                int[] growArrayBy2 = growArrayBy(iArr, iArr.length);
                                iArr = growArrayBy2;
                                this._quadBuffer = growArrayBy2;
                            }
                            int i9 = i4;
                            i4++;
                            iArr[i9] = i7;
                            i7 = 0;
                            i8 = 0;
                        }
                        c = (i7 << 8) | 128 | ((c2 >> 6) & '?');
                        i = i8 + 1;
                    }
                    c2 = 128 | (c2 & '?');
                }
            }
            if (i < 4) {
                i5 = i + 1;
                c3 = (c << 8) | c2;
            } else {
                if (i4 >= iArr.length) {
                    int[] growArrayBy3 = growArrayBy(iArr, iArr.length);
                    iArr = growArrayBy3;
                    this._quadBuffer = growArrayBy3;
                }
                int i10 = i4;
                i4++;
                iArr[i10] = c;
                c3 = c2;
                i5 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr2 = this._inputBuffer;
            int i11 = this._inputPtr;
            int i12 = i11 + 1;
            this._inputPtr = i12;
            c2 = bArr2[i11] & 255;
        }
        if (i > 0) {
            if (i4 >= iArr.length) {
                int[] growArrayBy4 = growArrayBy(iArr, iArr.length);
                iArr = growArrayBy4;
                this._quadBuffer = growArrayBy4;
            }
            int i13 = i4;
            i4++;
            iArr[i13] = c;
        }
        Name findName = this._symbols.findName(iArr, i4);
        if (findName == null) {
            findName = addName(iArr, i4, i);
        }
        return findName;
    }

    private Name findName(int i, int i2) throws JsonParseException {
        int i3 = i;
        int i4 = i2;
        Name findName = this._symbols.findName(i3);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i3;
        return addName(this._quadBuffer, 1, i4);
    }

    private Name findName(int i, int i2, int i3) throws JsonParseException {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        Name findName = this._symbols.findName(i4, i5);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i4;
        this._quadBuffer[1] = i5;
        return addName(this._quadBuffer, 2, i6);
    }

    private Name findName(int[] iArr, int i, int i2, int i3) throws JsonParseException {
        int[] iArr2 = iArr;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (i4 >= iArr2.length) {
            int[] growArrayBy = growArrayBy(iArr2, iArr2.length);
            iArr2 = growArrayBy;
            this._quadBuffer = growArrayBy;
        }
        int i7 = i4;
        int i8 = i4 + 1;
        iArr2[i7] = i5;
        Name findName = this._symbols.findName(iArr2, i8);
        if (findName == null) {
            return addName(iArr2, i8, i6);
        }
        return findName;
    }

    private Name addName(int[] iArr, int i, int i2) throws JsonParseException {
        int i3;
        String str;
        int i4;
        int i5;
        int[] iArr2 = iArr;
        int i6 = i;
        int i7 = i2;
        int i8 = ((i6 << 2) - 4) + i7;
        if (i7 < 4) {
            i3 = iArr2[i6 - 1];
            iArr2[i6 - 1] = i3 << ((4 - i7) << 3);
        } else {
            i3 = 0;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i9 = 0;
        int i10 = 0;
        while (i10 < i8) {
            int i11 = (iArr2[i10 >> 2] >> ((3 - (i10 & 3)) << 3)) & 255;
            i10++;
            if (i11 > 127) {
                if ((i11 & YaVersion.YOUNG_ANDROID_VERSION) == 192) {
                    i4 = i11 & 31;
                    i5 = 1;
                } else if ((i11 & 240) == 224) {
                    i4 = i11 & 15;
                    i5 = 2;
                } else if ((i11 & ComponentConstants.LISTVIEW_PREFERRED_WIDTH) == 240) {
                    i4 = i11 & 7;
                    i5 = 3;
                } else {
                    _reportInvalidInitial(i11);
                    i4 = 1;
                    i5 = 1;
                }
                if (i10 + i5 > i8) {
                    _reportInvalidEOF(" in field name");
                }
                int i12 = iArr2[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                i10++;
                if ((i12 & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) != 128) {
                    _reportInvalidOther(i12);
                }
                i11 = (i4 << 6) | (i12 & 63);
                if (i5 > 1) {
                    int i13 = iArr2[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                    i10++;
                    if ((i13 & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) != 128) {
                        _reportInvalidOther(i13);
                    }
                    i11 = (i11 << 6) | (i13 & 63);
                    if (i5 > 2) {
                        int i14 = iArr2[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                        i10++;
                        if ((i14 & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) != 128) {
                            _reportInvalidOther(i14 & 255);
                        }
                        i11 = (i11 << 6) | (i14 & 63);
                    }
                }
                if (i5 > 2) {
                    int i15 = i11 - 65536;
                    if (i9 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                    }
                    int i16 = i9;
                    i9++;
                    emptyAndGetCurrentSegment[i16] = (char) (55296 + (i15 >> 10));
                    i11 = 56320 | (i15 & 1023);
                }
            }
            if (i9 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
            }
            int i17 = i9;
            i9++;
            emptyAndGetCurrentSegment[i17] = (char) i11;
        }
        new String(emptyAndGetCurrentSegment, 0, i9);
        String str2 = str;
        if (i7 < 4) {
            iArr2[i6 - 1] = i3;
        }
        return this._symbols.addName(str2, iArr2, i6);
    }

    /* access modifiers changed from: protected */
    public void _finishString() throws IOException, JsonParseException {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int min = Math.min(this._inputEnd, i + emptyAndGetCurrentSegment.length);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (i >= min) {
                break;
            }
            byte b = bArr[i] & Ev3Constants.Opcode.TST;
            if (iArr[b] == 0) {
                i++;
                int i3 = i2;
                i2++;
                emptyAndGetCurrentSegment[i3] = (char) b;
            } else if (b == 34) {
                this._inputPtr = i + 1;
                this._textBuffer.setCurrentLength(i2);
                return;
            }
        }
        this._inputPtr = i;
        _finishString2(emptyAndGetCurrentSegment, i2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v24, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v50, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v31, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v32, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _finishString2(char[] r14, int r15) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            int[] r8 = sInputCodesUtf8
            r3 = r8
            r8 = r0
            byte[] r8 = r8._inputBuffer
            r4 = r8
        L_0x000a:
            r8 = r0
            int r8 = r8._inputPtr
            r5 = r8
            r8 = r5
            r9 = r0
            int r9 = r9._inputEnd
            if (r8 < r9) goto L_0x001c
            r8 = r0
            r8.loadMoreGuaranteed()
            r8 = r0
            int r8 = r8._inputPtr
            r5 = r8
        L_0x001c:
            r8 = r2
            r9 = r1
            int r9 = r9.length
            if (r8 < r9) goto L_0x002b
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            char[] r8 = r8.finishCurrentSegment()
            r1 = r8
            r8 = 0
            r2 = r8
        L_0x002b:
            r8 = r0
            int r8 = r8._inputEnd
            r9 = r5
            r10 = r1
            int r10 = r10.length
            r11 = r2
            int r10 = r10 - r11
            int r9 = r9 + r10
            int r8 = java.lang.Math.min(r8, r9)
            r6 = r8
        L_0x0039:
            r8 = r5
            r9 = r6
            if (r8 >= r9) goto L_0x0068
            r8 = r4
            r9 = r5
            int r5 = r5 + 1
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r7 = r8
            r8 = r3
            r9 = r7
            r8 = r8[r9]
            if (r8 == 0) goto L_0x005f
            r8 = r0
            r9 = r5
            r8._inputPtr = r9
            r8 = r7
            r9 = 34
            if (r8 != r9) goto L_0x006d
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            r9 = r2
            r8.setCurrentLength(r9)
            return
        L_0x005f:
            r8 = r1
            r9 = r2
            int r2 = r2 + 1
            r10 = r7
            char r10 = (char) r10
            r8[r9] = r10
            goto L_0x0039
        L_0x0068:
            r8 = r0
            r9 = r5
            r8._inputPtr = r9
            goto L_0x000a
        L_0x006d:
            r8 = r3
            r9 = r7
            r8 = r8[r9]
            switch(r8) {
                case 1: goto L_0x009a;
                case 2: goto L_0x00a1;
                case 3: goto L_0x00a9;
                case 4: goto L_0x00c3;
                default: goto L_0x0074;
            }
        L_0x0074:
            r8 = r7
            r9 = 32
            if (r8 >= r9) goto L_0x00f4
            r8 = r0
            r9 = r7
            java.lang.String r10 = "string value"
            r8._throwUnquotedSpace(r9, r10)
        L_0x0081:
            r8 = r2
            r9 = r1
            int r9 = r9.length
            if (r8 < r9) goto L_0x0090
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            char[] r8 = r8.finishCurrentSegment()
            r1 = r8
            r8 = 0
            r2 = r8
        L_0x0090:
            r8 = r1
            r9 = r2
            int r2 = r2 + 1
            r10 = r7
            char r10 = (char) r10
            r8[r9] = r10
            goto L_0x000a
        L_0x009a:
            r8 = r0
            char r8 = r8._decodeEscaped()
            r7 = r8
            goto L_0x0081
        L_0x00a1:
            r8 = r0
            r9 = r7
            int r8 = r8._decodeUtf8_2(r9)
            r7 = r8
            goto L_0x0081
        L_0x00a9:
            r8 = r0
            int r8 = r8._inputEnd
            r9 = r0
            int r9 = r9._inputPtr
            int r8 = r8 - r9
            r9 = 2
            if (r8 < r9) goto L_0x00bb
            r8 = r0
            r9 = r7
            int r8 = r8._decodeUtf8_3fast(r9)
            r7 = r8
            goto L_0x0081
        L_0x00bb:
            r8 = r0
            r9 = r7
            int r8 = r8._decodeUtf8_3(r9)
            r7 = r8
            goto L_0x0081
        L_0x00c3:
            r8 = r0
            r9 = r7
            int r8 = r8._decodeUtf8_4(r9)
            r7 = r8
            r8 = r1
            r9 = r2
            int r2 = r2 + 1
            r10 = 55296(0xd800, float:7.7486E-41)
            r11 = r7
            r12 = 10
            int r11 = r11 >> 10
            r10 = r10 | r11
            char r10 = (char) r10
            r8[r9] = r10
            r8 = r2
            r9 = r1
            int r9 = r9.length
            if (r8 < r9) goto L_0x00e9
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            char[] r8 = r8.finishCurrentSegment()
            r1 = r8
            r8 = 0
            r2 = r8
        L_0x00e9:
            r8 = 56320(0xdc00, float:7.8921E-41)
            r9 = r7
            r10 = 1023(0x3ff, float:1.434E-42)
            r9 = r9 & 1023(0x3ff, float:1.434E-42)
            r8 = r8 | r9
            r7 = r8
            goto L_0x0081
        L_0x00f4:
            r8 = r0
            r9 = r7
            r8._reportInvalidChar(r9)
            goto L_0x0081
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8StreamJsonParser._finishString2(char[], int):void");
    }

    /* access modifiers changed from: protected */
    public void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i;
                    i++;
                    byte b = bArr[i3] & Ev3Constants.Opcode.TST;
                    if (iArr[b] != 0) {
                        this._inputPtr = i;
                        if (b != 34) {
                            switch (iArr[b]) {
                                case 1:
                                    char _decodeEscaped = _decodeEscaped();
                                    break;
                                case 2:
                                    _skipUtf8_2(b);
                                    break;
                                case 3:
                                    _skipUtf8_3(b);
                                    break;
                                case 4:
                                    _skipUtf8_4(b);
                                    break;
                                default:
                                    if (b >= 32) {
                                        _reportInvalidChar(b);
                                        break;
                                    } else {
                                        _throwUnquotedSpace(b, "string value");
                                        break;
                                    }
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    this._inputPtr = i;
                }
            }
        }
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
                byte[] bArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                return _handleInvalidNumberStart(bArr[i3] & Ev3Constants.Opcode.TST, false);
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v27, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v28, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v48, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v57, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v34, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: char} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.core.JsonToken _handleApostropheValue() throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r15 = this;
            r0 = r15
            r8 = 0
            r1 = r8
            r8 = 0
            r2 = r8
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            char[] r8 = r8.emptyAndGetCurrentSegment()
            r3 = r8
            int[] r8 = sInputCodesUtf8
            r4 = r8
            r8 = r0
            byte[] r8 = r8._inputBuffer
            r5 = r8
        L_0x0014:
            r8 = r0
            int r8 = r8._inputPtr
            r9 = r0
            int r9 = r9._inputEnd
            if (r8 < r9) goto L_0x0020
            r8 = r0
            r8.loadMoreGuaranteed()
        L_0x0020:
            r8 = r2
            r9 = r3
            int r9 = r9.length
            if (r8 < r9) goto L_0x002f
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            char[] r8 = r8.finishCurrentSegment()
            r3 = r8
            r8 = 0
            r2 = r8
        L_0x002f:
            r8 = r0
            int r8 = r8._inputEnd
            r6 = r8
            r8 = r0
            int r8 = r8._inputPtr
            r9 = r3
            int r9 = r9.length
            r10 = r2
            int r9 = r9 - r10
            int r8 = r8 + r9
            r7 = r8
            r8 = r7
            r9 = r6
            if (r8 >= r9) goto L_0x0042
            r8 = r7
            r6 = r8
        L_0x0042:
            r8 = r0
            int r8 = r8._inputPtr
            r9 = r6
            if (r8 >= r9) goto L_0x0084
            r8 = r5
            r9 = r0
            r13 = r9
            r9 = r13
            r10 = r13
            int r10 = r10._inputPtr
            r13 = r9
            r14 = r10
            r9 = r14
            r10 = r13
            r11 = r14
            r12 = 1
            int r11 = r11 + 1
            r10._inputPtr = r11
            byte r8 = r8[r9]
            r9 = 255(0xff, float:3.57E-43)
            r8 = r8 & 255(0xff, float:3.57E-43)
            r1 = r8
            r8 = r1
            r9 = 39
            if (r8 == r9) goto L_0x006b
            r8 = r4
            r9 = r1
            r8 = r8[r9]
            if (r8 == 0) goto L_0x007b
        L_0x006b:
            r8 = r1
            r9 = 39
            if (r8 != r9) goto L_0x0085
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            r9 = r2
            r8.setCurrentLength(r9)
            com.shaded.fasterxml.jackson.core.JsonToken r8 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_STRING
            r0 = r8
            return r0
        L_0x007b:
            r8 = r3
            r9 = r2
            int r2 = r2 + 1
            r10 = r1
            char r10 = (char) r10
            r8[r9] = r10
            goto L_0x0042
        L_0x0084:
            goto L_0x0014
        L_0x0085:
            r8 = r4
            r9 = r1
            r8 = r8[r9]
            switch(r8) {
                case 1: goto L_0x00b7;
                case 2: goto L_0x00c3;
                case 3: goto L_0x00cb;
                case 4: goto L_0x00e5;
                default: goto L_0x008c;
            }
        L_0x008c:
            r8 = r1
            r9 = 32
            if (r8 >= r9) goto L_0x0099
            r8 = r0
            r9 = r1
            java.lang.String r10 = "string value"
            r8._throwUnquotedSpace(r9, r10)
        L_0x0099:
            r8 = r0
            r9 = r1
            r8._reportInvalidChar(r9)
        L_0x009e:
            r8 = r2
            r9 = r3
            int r9 = r9.length
            if (r8 < r9) goto L_0x00ad
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            char[] r8 = r8.finishCurrentSegment()
            r3 = r8
            r8 = 0
            r2 = r8
        L_0x00ad:
            r8 = r3
            r9 = r2
            int r2 = r2 + 1
            r10 = r1
            char r10 = (char) r10
            r8[r9] = r10
            goto L_0x0014
        L_0x00b7:
            r8 = r1
            r9 = 34
            if (r8 == r9) goto L_0x009e
            r8 = r0
            char r8 = r8._decodeEscaped()
            r1 = r8
            goto L_0x009e
        L_0x00c3:
            r8 = r0
            r9 = r1
            int r8 = r8._decodeUtf8_2(r9)
            r1 = r8
            goto L_0x009e
        L_0x00cb:
            r8 = r0
            int r8 = r8._inputEnd
            r9 = r0
            int r9 = r9._inputPtr
            int r8 = r8 - r9
            r9 = 2
            if (r8 < r9) goto L_0x00dd
            r8 = r0
            r9 = r1
            int r8 = r8._decodeUtf8_3fast(r9)
            r1 = r8
            goto L_0x009e
        L_0x00dd:
            r8 = r0
            r9 = r1
            int r8 = r8._decodeUtf8_3(r9)
            r1 = r8
            goto L_0x009e
        L_0x00e5:
            r8 = r0
            r9 = r1
            int r8 = r8._decodeUtf8_4(r9)
            r1 = r8
            r8 = r3
            r9 = r2
            int r2 = r2 + 1
            r10 = 55296(0xd800, float:7.7486E-41)
            r11 = r1
            r12 = 10
            int r11 = r11 >> 10
            r10 = r10 | r11
            char r10 = (char) r10
            r8[r9] = r10
            r8 = r2
            r9 = r3
            int r9 = r9.length
            if (r8 < r9) goto L_0x010b
            r8 = r0
            com.shaded.fasterxml.jackson.core.util.TextBuffer r8 = r8._textBuffer
            char[] r8 = r8.finishCurrentSegment()
            r3 = r8
            r8 = 0
            r2 = r8
        L_0x010b:
            r8 = 56320(0xdc00, float:7.8921E-41)
            r9 = r1
            r10 = 1023(0x3ff, float:1.434E-42)
            r9 = r9 & 1023(0x3ff, float:1.434E-42)
            r8 = r8 | r9
            r1 = r8
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleApostropheValue():com.shaded.fasterxml.jackson.core.JsonToken");
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleInvalidNumberStart(int i, boolean z) throws IOException, JsonParseException {
        String str;
        StringBuilder sb;
        byte b = i;
        boolean z2 = z;
        while (b == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            b = bArr[i2];
            if (b != 78) {
                if (b != 110) {
                    break;
                }
                str = z2 ? "-Infinity" : "+Infinity";
            } else {
                str = z2 ? "-INF" : "+INF";
            }
            _matchToken(str, 3);
            if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                return resetAsNaN(str, z2 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            new StringBuilder();
            _reportError(sb.append("Non-standard token '").append(str).append("': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow").toString());
        }
        reportUnexpectedNumberChar(b, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    /* access modifiers changed from: protected */
    public void _matchToken(String str, int i) throws IOException, JsonParseException {
        byte b;
        String str2 = str;
        int i2 = i;
        int length = str2.length();
        do {
            if ((this._inputPtr >= this._inputEnd && !loadMore()) || this._inputBuffer[this._inputPtr] != str2.charAt(i2)) {
                _reportInvalidToken(str2.substring(0, i2));
            }
            this._inputPtr++;
            i2++;
        } while (i2 < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (b = this._inputBuffer[this._inputPtr] & Ev3Constants.Opcode.TST) >= 48 && b != 93 && b != 125 && Character.isJavaIdentifierPart((char) _decodeCharForError(b))) {
            _reportInvalidToken(str2.substring(0, i2));
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
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            int i2 = i + 1;
            this._inputPtr = i2;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            StringBuilder append = sb3.append(_decodeCharForError);
        }
        new StringBuilder();
        _reportError(sb2.append("Unrecognized token '").append(sb3.toString()).append("': was expecting ").append(str3).toString());
    }

    private int _skipWS() throws IOException, JsonParseException {
        StringBuilder sb;
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                int i2 = i + 1;
                this._inputPtr = i2;
                byte b = bArr[i] & Ev3Constants.Opcode.TST;
                if (b > 32) {
                    if (b != 47) {
                        return b;
                    }
                    _skipComment();
                } else if (b != 32) {
                    if (b == 10) {
                        _skipLF();
                    } else if (b == 13) {
                        _skipCR();
                    } else if (b != 9) {
                        _throwInvalidSpace(b);
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
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                int i2 = i + 1;
                this._inputPtr = i2;
                byte b = bArr[i] & Ev3Constants.Opcode.TST;
                if (b > 32) {
                    if (b != 47) {
                        return b;
                    }
                    _skipComment();
                } else if (b != 32) {
                    if (b == 10) {
                        _skipLF();
                    } else if (b == 13) {
                        _skipCR();
                    } else if (b != 9) {
                        _throwInvalidSpace(b);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b2, code lost:
        if (r0._inputPtr < r0._inputEnd) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b4, code lost:
        loadMoreGuaranteed();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int _skipColon() throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonParseException {
        /*
            r9 = this;
            r0 = r9
            r2 = r0
            int r2 = r2._inputPtr
            r3 = r0
            int r3 = r3._inputEnd
            if (r2 < r3) goto L_0x000d
            r2 = r0
            r2.loadMoreGuaranteed()
        L_0x000d:
            r2 = r0
            byte[] r2 = r2._inputBuffer
            r3 = r0
            r7 = r3
            r3 = r7
            r4 = r7
            int r4 = r4._inputPtr
            r7 = r3
            r8 = r4
            r3 = r8
            r4 = r7
            r5 = r8
            r6 = 1
            int r5 = r5 + 1
            r4._inputPtr = r5
            byte r2 = r2[r3]
            r1 = r2
            r2 = r1
            r3 = 58
            if (r2 != r3) goto L_0x0055
            r2 = r0
            int r2 = r2._inputPtr
            r3 = r0
            int r3 = r3._inputEnd
            if (r2 >= r3) goto L_0x0076
            r2 = r0
            byte[] r2 = r2._inputBuffer
            r3 = r0
            int r3 = r3._inputPtr
            byte r2 = r2[r3]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r2
            r2 = r1
            r3 = 32
            if (r2 <= r3) goto L_0x0076
            r2 = r1
            r3 = 47
            if (r2 == r3) goto L_0x0076
            r2 = r0
            r7 = r2
            r2 = r7
            r3 = r7
            int r3 = r3._inputPtr
            r4 = 1
            int r3 = r3 + 1
            r2._inputPtr = r3
            r2 = r1
            r0 = r2
        L_0x0054:
            return r0
        L_0x0055:
            r2 = r1
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r2
        L_0x005b:
            r2 = r1
            switch(r2) {
                case 9: goto L_0x00ac;
                case 10: goto L_0x00d8;
                case 13: goto L_0x00d3;
                case 32: goto L_0x00ac;
                case 47: goto L_0x00dd;
                default: goto L_0x005f;
            }
        L_0x005f:
            r2 = r1
            r3 = 32
            if (r2 >= r3) goto L_0x0069
            r2 = r0
            r3 = r1
            r2._throwInvalidSpace(r3)
        L_0x0069:
            r2 = r1
            r3 = 58
            if (r2 == r3) goto L_0x0076
            r2 = r0
            r3 = r1
            java.lang.String r4 = "was expecting a colon to separate field name and value"
            r2._reportUnexpectedChar(r3, r4)
        L_0x0076:
            r2 = r0
            int r2 = r2._inputPtr
            r3 = r0
            int r3 = r3._inputEnd
            if (r2 < r3) goto L_0x0085
            r2 = r0
            boolean r2 = r2.loadMore()
            if (r2 == 0) goto L_0x010d
        L_0x0085:
            r2 = r0
            byte[] r2 = r2._inputBuffer
            r3 = r0
            r7 = r3
            r3 = r7
            r4 = r7
            int r4 = r4._inputPtr
            r7 = r3
            r8 = r4
            r3 = r8
            r4 = r7
            r5 = r8
            r6 = 1
            int r5 = r5 + 1
            r4._inputPtr = r5
            byte r2 = r2[r3]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r2
            r2 = r1
            r3 = 32
            if (r2 <= r3) goto L_0x00e7
            r2 = r1
            r3 = 47
            if (r2 == r3) goto L_0x00e2
            r2 = r1
            r0 = r2
            goto L_0x0054
        L_0x00ac:
            r2 = r0
            int r2 = r2._inputPtr
            r3 = r0
            int r3 = r3._inputEnd
            if (r2 < r3) goto L_0x00b8
            r2 = r0
            r2.loadMoreGuaranteed()
        L_0x00b8:
            r2 = r0
            byte[] r2 = r2._inputBuffer
            r3 = r0
            r7 = r3
            r3 = r7
            r4 = r7
            int r4 = r4._inputPtr
            r7 = r3
            r8 = r4
            r3 = r8
            r4 = r7
            r5 = r8
            r6 = 1
            int r5 = r5 + 1
            r4._inputPtr = r5
            byte r2 = r2[r3]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r2
            goto L_0x005b
        L_0x00d3:
            r2 = r0
            r2._skipCR()
            goto L_0x00ac
        L_0x00d8:
            r2 = r0
            r2._skipLF()
            goto L_0x00ac
        L_0x00dd:
            r2 = r0
            r2._skipComment()
            goto L_0x00ac
        L_0x00e2:
            r2 = r0
            r2._skipComment()
            goto L_0x0076
        L_0x00e7:
            r2 = r1
            r3 = 32
            if (r2 == r3) goto L_0x0076
            r2 = r1
            r3 = 10
            if (r2 != r3) goto L_0x00f6
            r2 = r0
            r2._skipLF()
            goto L_0x0076
        L_0x00f6:
            r2 = r1
            r3 = 13
            if (r2 != r3) goto L_0x0101
            r2 = r0
            r2._skipCR()
            goto L_0x0076
        L_0x0101:
            r2 = r1
            r3 = 9
            if (r2 == r3) goto L_0x0076
            r2 = r0
            r3 = r1
            r2._throwInvalidSpace(r3)
            goto L_0x0076
        L_0x010d:
            r2 = r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r7 = r3
            r3 = r7
            r4 = r7
            r4.<init>()
            java.lang.String r4 = "Unexpected end-of-input within/between "
            java.lang.StringBuilder r3 = r3.append(r4)
            r4 = r0
            com.shaded.fasterxml.jackson.core.json.JsonReadContext r4 = r4._parsingContext
            java.lang.String r4 = r4.getTypeDesc()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = " entries"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.shaded.fasterxml.jackson.core.JsonParseException r2 = r2._constructError(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipColon():int");
    }

    private void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        byte b = bArr[i] & Ev3Constants.Opcode.TST;
        if (b == 47) {
            _skipCppComment();
        } else if (b == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(b, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException, JsonParseException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                int i2 = i + 1;
                this._inputPtr = i2;
                byte b = bArr[i] & Ev3Constants.Opcode.TST;
                int i3 = inputCodeComment[b];
                if (i3 != 0) {
                    switch (i3) {
                        case 2:
                            _skipUtf8_2(b);
                            continue;
                        case 3:
                            _skipUtf8_3(b);
                            continue;
                        case 4:
                            _skipUtf8_4(b);
                            continue;
                        case 10:
                            _skipLF();
                            continue;
                        case 13:
                            _skipCR();
                            continue;
                        case 42:
                            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                                break;
                            } else if (this._inputBuffer[this._inputPtr] == 47) {
                                this._inputPtr++;
                                return;
                            } else {
                                continue;
                            }
                        default:
                            _reportInvalidChar(b);
                            continue;
                    }
                }
            }
        }
        _reportInvalidEOF(" in a comment");
    }

    private void _skipCppComment() throws IOException, JsonParseException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                int i2 = i + 1;
                this._inputPtr = i2;
                byte b = bArr[i] & Ev3Constants.Opcode.TST;
                int i3 = inputCodeComment[b];
                if (i3 != 0) {
                    switch (i3) {
                        case 2:
                            _skipUtf8_2(b);
                            break;
                        case 3:
                            _skipUtf8_3(b);
                            break;
                        case 4:
                            _skipUtf8_4(b);
                            break;
                        case 10:
                            _skipLF();
                            return;
                        case 13:
                            _skipCR();
                            return;
                        case 42:
                            break;
                        default:
                            _reportInvalidChar(b);
                            break;
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
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        byte b = bArr[i];
        switch (b) {
            case 34:
            case 47:
            case 92:
                return (char) b;
            case 98:
                return 8;
            case 102:
                return 12;
            case 110:
                return 10;
            case 114:
                return 13;
            case 116:
                return 9;
            case 117:
                int i3 = 0;
                for (int i4 = 0; i4 < 4; i4++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i5 = this._inputPtr;
                    int i6 = i5 + 1;
                    this._inputPtr = i6;
                    byte b2 = bArr2[i5];
                    int charToHex = CharTypes.charToHex(b2);
                    if (charToHex < 0) {
                        _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
                    }
                    i3 = (i3 << 4) | charToHex;
                }
                return (char) i3;
            default:
                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
        }
    }

    /* access modifiers changed from: protected */
    public int _decodeCharForError(int i) throws IOException, JsonParseException {
        boolean z;
        int i2 = i;
        if (i2 < 0) {
            if ((i2 & YaVersion.YOUNG_ANDROID_VERSION) == 192) {
                i2 &= 31;
                z = true;
            } else if ((i2 & 240) == 224) {
                i2 &= 15;
                z = true;
            } else if ((i2 & ComponentConstants.LISTVIEW_PREFERRED_WIDTH) == 240) {
                i2 &= 7;
                z = true;
            } else {
                _reportInvalidInitial(i2 & 255);
                z = true;
            }
            int nextByte = nextByte();
            if ((nextByte & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) != 128) {
                _reportInvalidOther(nextByte & 255);
            }
            i2 = (i2 << 6) | (nextByte & 63);
            if (z > 1) {
                int nextByte2 = nextByte();
                if ((nextByte2 & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) != 128) {
                    _reportInvalidOther(nextByte2 & 255);
                }
                i2 = (i2 << 6) | (nextByte2 & 63);
                if (z > 2) {
                    int nextByte3 = nextByte();
                    if ((nextByte3 & FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE) != 128) {
                        _reportInvalidOther(nextByte3 & 255);
                    }
                    i2 = (i2 << 6) | (nextByte3 & 63);
                }
            }
        }
        return i2;
    }

    private int _decodeUtf8_2(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        return ((i2 & 31) << 6) | (b & Ev3Constants.Opcode.MOVEF_F);
    }

    private int _decodeUtf8_3(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i3 = i2 & 15;
        byte[] bArr = this._inputBuffer;
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b = bArr[i4];
        if ((b & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        byte b2 = (i3 << 6) | (b & Ev3Constants.Opcode.MOVEF_F);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        this._inputPtr = i7;
        byte b3 = bArr2[i6];
        if ((b3 & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b3 & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        return (b2 << 6) | (b3 & Ev3Constants.Opcode.MOVEF_F);
    }

    private int _decodeUtf8_3fast(int i) throws IOException, JsonParseException {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        byte b2 = (i2 << 6) | (b & Ev3Constants.Opcode.MOVEF_F);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b3 = bArr2[i5];
        if ((b3 & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b3 & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        return (b2 << 6) | (b3 & Ev3Constants.Opcode.MOVEF_F);
    }

    private int _decodeUtf8_4(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        byte b2 = ((i2 & 7) << 6) | (b & Ev3Constants.Opcode.MOVEF_F);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b3 = bArr2[i5];
        if ((b3 & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b3 & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        byte b4 = (b2 << 6) | (b3 & Ev3Constants.Opcode.MOVEF_F);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i7 = this._inputPtr;
        int i8 = i7 + 1;
        this._inputPtr = i8;
        byte b5 = bArr3[i7];
        if ((b5 & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b5 & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        return ((b4 << 6) | (b5 & Ev3Constants.Opcode.MOVEF_F)) - 65536;
    }

    private void _skipUtf8_2(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b & Ev3Constants.Opcode.TST, this._inputPtr);
        }
    }

    private void _skipUtf8_3(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b2 = bArr2[i5];
        if ((b2 & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b2 & Ev3Constants.Opcode.TST, this._inputPtr);
        }
    }

    private void _skipUtf8_4(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b2 = bArr2[i5];
        if ((b2 & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b2 & Ev3Constants.Opcode.TST, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i7 = this._inputPtr;
        int i8 = i7 + 1;
        this._inputPtr = i8;
        byte b3 = bArr3[i7];
        if ((b3 & Ev3Constants.Opcode.FILE) != 128) {
            _reportInvalidOther(b3 & Ev3Constants.Opcode.TST, this._inputPtr);
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

    private int nextByte() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        return bArr[i] & Ev3Constants.Opcode.TST;
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidChar(int i) throws JsonParseException {
        int i2 = i;
        if (i2 < 32) {
            _throwInvalidSpace(i2);
        }
        _reportInvalidInitial(i2);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidInitial(int i) throws JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportError(sb.append("Invalid UTF-8 start byte 0x").append(Integer.toHexString(i)).toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int i) throws JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportError(sb.append("Invalid UTF-8 middle byte 0x").append(Integer.toHexString(i)).toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int i, int i2) throws JsonParseException {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        int[] iArr2 = iArr;
        int i2 = i;
        if (iArr2 == null) {
            return new int[i2];
        }
        int[] iArr3 = iArr2;
        int length = iArr2.length;
        int[] iArr4 = new int[(length + i2)];
        System.arraycopy(iArr3, 0, iArr4, 0, length);
        return iArr4;
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
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            int i2 = i + 1;
            this._inputPtr = i2;
            byte b = bArr[i] & Ev3Constants.Opcode.TST;
            if (b > 32) {
                int decodeBase64Char = base64Variant2.decodeBase64Char((int) b);
                if (decodeBase64Char < 0) {
                    if (b == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant2, (int) b, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                int i3 = decodeBase64Char;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                int i5 = i4 + 1;
                this._inputPtr = i5;
                byte b2 = bArr2[i4] & Ev3Constants.Opcode.TST;
                int decodeBase64Char2 = base64Variant2.decodeBase64Char((int) b2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant2, (int) b2, 1);
                }
                int i6 = (i3 << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i7 = this._inputPtr;
                int i8 = i7 + 1;
                this._inputPtr = i8;
                byte b3 = bArr3[i7] & Ev3Constants.Opcode.TST;
                int decodeBase64Char3 = base64Variant2.decodeBase64Char((int) b3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (b3 != 34 || base64Variant2.usesPadding()) {
                            decodeBase64Char3 = _decodeBase64Escape(base64Variant2, (int) b3, 2);
                        } else {
                            _getByteArrayBuilder.append(i6 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i9 = this._inputPtr;
                        int i10 = i9 + 1;
                        this._inputPtr = i10;
                        byte b4 = bArr4[i9] & Ev3Constants.Opcode.TST;
                        if (!base64Variant2.usesPaddingChar((int) b4)) {
                            new StringBuilder();
                            throw reportInvalidBase64Char(base64Variant2, b4, 3, sb.append("expected padding character '").append(base64Variant2.getPaddingChar()).append("'").toString());
                        }
                        _getByteArrayBuilder.append(i6 >> 4);
                    }
                }
                int i11 = (i6 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i12 = this._inputPtr;
                int i13 = i12 + 1;
                this._inputPtr = i13;
                byte b5 = bArr5[i12] & Ev3Constants.Opcode.TST;
                int decodeBase64Char4 = base64Variant2.decodeBase64Char((int) b5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (b5 != 34 || base64Variant2.usesPadding()) {
                            decodeBase64Char4 = _decodeBase64Escape(base64Variant2, (int) b5, 3);
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
}
