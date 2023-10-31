package com.shaded.fasterxml.jackson.core.base;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.json.JsonReadContext;
import com.shaded.fasterxml.jackson.core.json.PackageVersion;
import com.shaded.fasterxml.jackson.core.p005io.IOContext;
import com.shaded.fasterxml.jackson.core.p005io.NumberInput;
import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.shaded.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ParserBase extends ParserMinimalBase {
    static final BigDecimal BD_MAX_INT;
    static final BigDecimal BD_MAX_LONG;
    static final BigDecimal BD_MIN_INT;
    static final BigDecimal BD_MIN_LONG;
    static final BigInteger BI_MAX_INT = BigInteger.valueOf(MAX_INT_L);
    static final BigInteger BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    static final BigInteger BI_MIN_INT = BigInteger.valueOf(MIN_INT_L);
    static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    protected static final char CHAR_NULL = '\u0000';
    protected static final int INT_0 = 48;
    protected static final int INT_1 = 49;
    protected static final int INT_2 = 50;
    protected static final int INT_3 = 51;
    protected static final int INT_4 = 52;
    protected static final int INT_5 = 53;
    protected static final int INT_6 = 54;
    protected static final int INT_7 = 55;
    protected static final int INT_8 = 56;
    protected static final int INT_9 = 57;
    protected static final int INT_DECIMAL_POINT = 46;
    protected static final int INT_E = 69;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PLUS = 43;
    protected static final int INT_e = 101;
    static final double MAX_INT_D = 2.147483647E9d;
    static final long MAX_INT_L = 2147483647L;
    static final double MAX_LONG_D = 9.223372036854776E18d;
    static final double MIN_INT_D = -2.147483648E9d;
    static final long MIN_INT_L = -2147483648L;
    static final double MIN_LONG_D = -9.223372036854776E18d;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_UNKNOWN = 0;
    protected byte[] _binaryValue;
    protected ByteArrayBuilder _byteArrayBuilder = null;
    protected boolean _closed;
    protected long _currInputProcessed = 0;
    protected int _currInputRow = 1;
    protected int _currInputRowStart = 0;
    protected int _expLength;
    protected int _fractLength;
    protected int _inputEnd = 0;
    protected int _inputPtr = 0;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected boolean _nameCopied = false;
    protected char[] _nameCopyBuffer = null;
    protected JsonToken _nextToken;
    protected int _numTypesValid = 0;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    protected int _tokenInputCol = 0;
    protected int _tokenInputRow = 1;
    protected long _tokenInputTotal = 0;

    /* access modifiers changed from: protected */
    public abstract void _closeInput() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void _finishString() throws IOException, JsonParseException;

    /* access modifiers changed from: protected */
    public abstract boolean loadMore() throws IOException;

    static {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        new BigDecimal(BI_MIN_LONG);
        BD_MIN_LONG = bigDecimal;
        new BigDecimal(BI_MAX_LONG);
        BD_MAX_LONG = bigDecimal2;
        new BigDecimal(BI_MIN_INT);
        BD_MIN_INT = bigDecimal3;
        new BigDecimal(BI_MAX_INT);
        BD_MAX_INT = bigDecimal4;
    }

    protected ParserBase(IOContext iOContext, int i) {
        IOContext iOContext2 = iOContext;
        this._features = i;
        this._ioContext = iOContext2;
        this._textBuffer = iOContext2.constructTextBuffer();
        this._parsingContext = JsonReadContext.createRootContext();
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public String getCurrentName() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            return this._parsingContext.getParent().getCurrentName();
        }
        return this._parsingContext.getCurrentName();
    }

    public void overrideCurrentName(String str) {
        String str2 = str;
        JsonReadContext jsonReadContext = this._parsingContext;
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            jsonReadContext = jsonReadContext.getParent();
        }
        jsonReadContext.setCurrentName(str2);
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            try {
                _closeInput();
                _releaseBuffers();
            } catch (Throwable th) {
                Throwable th2 = th;
                _releaseBuffers();
                throw th2;
            }
        }
    }

    public boolean isClosed() {
        return this._closed;
    }

    public JsonReadContext getParsingContext() {
        return this._parsingContext;
    }

    public JsonLocation getTokenLocation() {
        JsonLocation jsonLocation;
        new JsonLocation(this._ioContext.getSourceReference(), getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
        return jsonLocation;
    }

    public JsonLocation getCurrentLocation() {
        JsonLocation jsonLocation;
        new JsonLocation(this._ioContext.getSourceReference(), (this._currInputProcessed + ((long) this._inputPtr)) - 1, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
        return jsonLocation;
    }

    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return null;
    }

    public long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }

    public int getTokenLineNr() {
        return this._tokenInputRow;
    }

    public int getTokenColumnNr() {
        int i = this._tokenInputCol;
        return i < 0 ? i : i + 1;
    }

    /* access modifiers changed from: protected */
    public final void loadMoreGuaranteed() throws IOException {
        if (!loadMore()) {
            _reportInvalidEOF();
        }
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    /* access modifiers changed from: protected */
    public void _handleEOF() throws JsonParseException {
        StringBuilder sb;
        if (!this._parsingContext.inRoot()) {
            new StringBuilder();
            _reportInvalidEOF(sb.append(": expected close marker for ").append(this._parsingContext.getTypeDesc()).append(" (from ").append(this._parsingContext.getStartLocation(this._ioContext.getSourceReference())).append(")").toString());
        }
    }

    /* access modifiers changed from: protected */
    public void _reportMismatchedEndMarker(int i, char c) throws JsonParseException {
        StringBuilder sb;
        StringBuilder sb2;
        new StringBuilder();
        String sb3 = sb.append("").append(this._parsingContext.getStartLocation(this._ioContext.getSourceReference())).toString();
        new StringBuilder();
        _reportError(sb2.append("Unexpected close marker '").append((char) i).append("': expected '").append(c).append("' (for ").append(this._parsingContext.getTypeDesc()).append(" starting at ").append(sb3).append(")").toString());
    }

    public ByteArrayBuilder _getByteArrayBuilder() {
        ByteArrayBuilder byteArrayBuilder;
        if (this._byteArrayBuilder == null) {
            new ByteArrayBuilder();
            this._byteArrayBuilder = byteArrayBuilder;
        } else {
            this._byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    /* access modifiers changed from: protected */
    public final JsonToken reset(boolean z, int i, int i2, int i3) {
        boolean z2 = z;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (i5 >= 1 || i6 >= 1) {
            return resetFloat(z2, i4, i5, i6);
        }
        return resetInt(z2, i4);
    }

    /* access modifiers changed from: protected */
    public final JsonToken resetInt(boolean z, int i) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    /* access modifiers changed from: protected */
    public final JsonToken resetFloat(boolean z, int i, int i2, int i3) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = i2;
        this._expLength = i3;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    /* access modifiers changed from: protected */
    public final JsonToken resetAsNaN(String str, double d) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public Number getNumberValue() throws IOException, JsonParseException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((this._numTypesValid & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((this._numTypesValid & 4) != 0) {
                return this._numberBigInt;
            }
            return this._numberBigDecimal;
        } else if ((this._numTypesValid & 16) != 0) {
            return this._numberBigDecimal;
        } else {
            if ((this._numTypesValid & 8) == 0) {
                _throwInternal();
            }
            return Double.valueOf(this._numberDouble);
        }
    }

    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return JsonParser.NumberType.INT;
            }
            if ((this._numTypesValid & 2) != 0) {
                return JsonParser.NumberType.LONG;
            }
            return JsonParser.NumberType.BIG_INTEGER;
        } else if ((this._numTypesValid & 16) != 0) {
            return JsonParser.NumberType.BIG_DECIMAL;
        } else {
            return JsonParser.NumberType.DOUBLE;
        }
    }

    public int getIntValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 1) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public long getLongValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 2) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 4) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    public float getFloatValue() throws IOException, JsonParseException {
        return (float) getDoubleValue();
    }

    public double getDoubleValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 8) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 16) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    /* access modifiers changed from: protected */
    public void _parseNumericValue(int i) throws IOException, JsonParseException {
        StringBuilder sb;
        int i2 = i;
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            int i3 = this._intLength;
            if (this._numberNegative) {
                textOffset++;
            }
            if (i3 <= 9) {
                int parseInt = NumberInput.parseInt(textBuffer, textOffset, i3);
                this._numberInt = this._numberNegative ? -parseInt : parseInt;
                this._numTypesValid = 1;
            } else if (i3 <= 18) {
                long parseLong = NumberInput.parseLong(textBuffer, textOffset, i3);
                if (this._numberNegative) {
                    parseLong = -parseLong;
                }
                if (i3 == 10) {
                    if (this._numberNegative) {
                        if (parseLong >= MIN_INT_L) {
                            this._numberInt = (int) parseLong;
                            this._numTypesValid = 1;
                            return;
                        }
                    } else if (parseLong <= MAX_INT_L) {
                        this._numberInt = (int) parseLong;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = parseLong;
                this._numTypesValid = 2;
            } else {
                _parseSlowIntValue(i2, textBuffer, textOffset, i3);
            }
        } else if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloatValue(i2);
        } else {
            new StringBuilder();
            _reportError(sb.append("Current token (").append(this._currToken).append(") not numeric, can not use numeric value accessors").toString());
        }
    }

    private void _parseSlowFloatValue(int i) throws IOException, JsonParseException {
        StringBuilder sb;
        if (i == 16) {
            try {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
            } catch (NumberFormatException e) {
                new StringBuilder();
                _wrapError(sb.append("Malformed numeric value '").append(this._textBuffer.contentsAsString()).append("'").toString(), e);
            }
        } else {
            this._numberDouble = this._textBuffer.contentsAsDouble();
            this._numTypesValid = 8;
        }
    }

    private void _parseSlowIntValue(int i, char[] cArr, int i2, int i3) throws IOException, JsonParseException {
        StringBuilder sb;
        BigInteger bigInteger;
        int i4 = i;
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            if (NumberInput.inLongRange(cArr, i2, i3, this._numberNegative)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
                return;
            }
            new BigInteger(contentsAsString);
            this._numberBigInt = bigInteger;
            this._numTypesValid = 4;
        } catch (NumberFormatException e) {
            new StringBuilder();
            _wrapError(sb.append("Malformed numeric value '").append(contentsAsString).append("'").toString(), e);
        }
    }

    /* access modifiers changed from: protected */
    public void convertNumberToInt() throws IOException, JsonParseException {
        StringBuilder sb;
        if ((this._numTypesValid & 2) != 0) {
            int i = (int) this._numberLong;
            if (((long) i) != this._numberLong) {
                new StringBuilder();
                _reportError(sb.append("Numeric value (").append(getText()).append(") out of range of int").toString());
            }
            this._numberInt = i;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_INT.compareTo(this._numberBigInt) > 0 || BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < MIN_INT_D || this._numberDouble > MAX_INT_D) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToLong() throws IOException, JsonParseException {
        if ((this._numTypesValid & 1) != 0) {
            this._numberLong = (long) this._numberInt;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < MIN_LONG_D || this._numberDouble > MAX_LONG_D) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToBigInteger() throws IOException, JsonParseException {
        if ((this._numTypesValid & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf((long) this._numberInt);
        } else if ((this._numTypesValid & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToDouble() throws IOException, JsonParseException {
        if ((this._numTypesValid & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberDouble = (double) this._numberLong;
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberDouble = (double) this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToBigDecimal() throws IOException, JsonParseException {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        if ((this._numTypesValid & 8) != 0) {
            new BigDecimal(getText());
            this._numberBigDecimal = bigDecimal2;
        } else if ((this._numTypesValid & 4) != 0) {
            new BigDecimal(this._numberBigInt);
            this._numberBigDecimal = bigDecimal;
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf((long) this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }

    /* access modifiers changed from: protected */
    public void reportUnexpectedNumberChar(int i, String str) throws JsonParseException {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        new StringBuilder();
        String sb3 = sb.append("Unexpected character (").append(_getCharDesc(i)).append(") in numeric value").toString();
        if (str2 != null) {
            new StringBuilder();
            sb3 = sb2.append(sb3).append(": ").append(str2).toString();
        }
        _reportError(sb3);
    }

    /* access modifiers changed from: protected */
    public void reportInvalidNumber(String str) throws JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportError(sb.append("Invalid numeric value: ").append(str).toString());
    }

    /* access modifiers changed from: protected */
    public void reportOverflowInt() throws IOException, JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportError(sb.append("Numeric value (").append(getText()).append(") out of range of int (").append(Integer.MIN_VALUE).append(" - ").append(Integer.MAX_VALUE).append(")").toString());
    }

    /* access modifiers changed from: protected */
    public void reportOverflowLong() throws IOException, JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportError(sb.append("Numeric value (").append(getText()).append(") out of range of long (").append(Long.MIN_VALUE).append(" - ").append(Long.MAX_VALUE).append(")").toString());
    }

    /* access modifiers changed from: protected */
    public char _decodeEscaped() throws IOException, JsonParseException {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    /* access modifiers changed from: protected */
    public final int _decodeBase64Escape(Base64Variant base64Variant, int i, int i2) throws IOException, JsonParseException {
        Base64Variant base64Variant2 = base64Variant;
        int i3 = i;
        int i4 = i2;
        if (i3 != 92) {
            throw reportInvalidBase64Char(base64Variant2, i3, i4);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= ' ' && i4 == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant2.decodeBase64Char((int) _decodeEscaped);
        if (decodeBase64Char >= 0) {
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant2, _decodeEscaped, i4);
    }

    /* access modifiers changed from: protected */
    public final int _decodeBase64Escape(Base64Variant base64Variant, char c, int i) throws IOException, JsonParseException {
        Base64Variant base64Variant2 = base64Variant;
        char c2 = c;
        int i2 = i;
        if (c2 != '\\') {
            throw reportInvalidBase64Char(base64Variant2, c2, i2);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= ' ' && i2 == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant2.decodeBase64Char(_decodeEscaped);
        if (decodeBase64Char >= 0) {
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant2, _decodeEscaped, i2);
    }

    /* access modifiers changed from: protected */
    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2) throws IllegalArgumentException {
        return reportInvalidBase64Char(base64Variant, i, i2, (String) null);
    }

    /* access modifiers changed from: protected */
    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2, String str) throws IllegalArgumentException {
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        IllegalArgumentException illegalArgumentException;
        StringBuilder sb5;
        StringBuilder sb6;
        Base64Variant base64Variant2 = base64Variant;
        int i3 = i;
        int i4 = i2;
        String str2 = str;
        if (i3 <= 32) {
            new StringBuilder();
            sb2 = sb6.append("Illegal white space character (code 0x").append(Integer.toHexString(i3)).append(") as character #").append(i4 + 1).append(" of 4-char base64 unit: can only used between units").toString();
        } else if (base64Variant2.usesPaddingChar(i3)) {
            new StringBuilder();
            sb2 = sb4.append("Unexpected padding character ('").append(base64Variant2.getPaddingChar()).append("') as character #").append(i4 + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        } else if (!Character.isDefined(i3) || Character.isISOControl(i3)) {
            new StringBuilder();
            sb2 = sb.append("Illegal character (code 0x").append(Integer.toHexString(i3)).append(") in base64 content").toString();
        } else {
            new StringBuilder();
            sb2 = sb3.append("Illegal character '").append((char) i3).append("' (code 0x").append(Integer.toHexString(i3)).append(") in base64 content").toString();
        }
        if (str2 != null) {
            new StringBuilder();
            sb2 = sb5.append(sb2).append(": ").append(str2).toString();
        }
        new IllegalArgumentException(sb2);
        return illegalArgumentException;
    }
}
