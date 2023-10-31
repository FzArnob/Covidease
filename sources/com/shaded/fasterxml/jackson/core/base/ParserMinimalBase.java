package com.shaded.fasterxml.jackson.core.base;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonStreamContext;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.p005io.NumberInput;
import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.shaded.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class ParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;

    /* access modifiers changed from: protected */
    public abstract void _handleEOF() throws JsonParseException;

    public abstract void close() throws IOException;

    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    public abstract String getCurrentName() throws IOException, JsonParseException;

    public abstract JsonStreamContext getParsingContext();

    public abstract String getText() throws IOException, JsonParseException;

    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    public abstract int getTextLength() throws IOException, JsonParseException;

    public abstract int getTextOffset() throws IOException, JsonParseException;

    public abstract boolean hasTextCharacters();

    public abstract boolean isClosed();

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public abstract void overrideCurrentName(String str);

    protected ParserMinimalBase() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ParserMinimalBase(int i) {
        super(i);
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public JsonToken nextValue() throws IOException, JsonParseException {
        JsonToken nextToken = nextToken();
        if (nextToken == JsonToken.FIELD_NAME) {
            nextToken = nextToken();
        }
        return nextToken;
    }

    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken != null) {
                    switch (nextToken) {
                        case START_OBJECT:
                        case START_ARRAY:
                            i++;
                            break;
                        case END_OBJECT:
                        case END_ARRAY:
                            i--;
                            if (i != 0) {
                                break;
                            } else {
                                return this;
                            }
                    }
                } else {
                    _handleEOF();
                    return this;
                }
            }
        } else {
            return this;
        }
    }

    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public boolean getValueAsBoolean(boolean z) throws IOException, JsonParseException {
        boolean z2 = z;
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                    return getIntValue() != 0;
                case VALUE_TRUE:
                    return true;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return false;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return ((Boolean) embeddedObject).booleanValue();
                    }
                    break;
                case VALUE_STRING:
                    break;
            }
            if ("true".equals(getText().trim())) {
                return true;
            }
        }
        return z2;
    }

    public int getValueAsInt(int i) throws IOException, JsonParseException {
        int i2 = i;
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getIntValue();
                case VALUE_TRUE:
                    return 1;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).intValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsInt(getText(), i2);
            }
        }
        return i2;
    }

    public long getValueAsLong(long j) throws IOException, JsonParseException {
        long j2 = j;
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getLongValue();
                case VALUE_TRUE:
                    return 1;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).longValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsLong(getText(), j2);
            }
        }
        return j2;
    }

    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        double d2 = d;
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getDoubleValue();
                case VALUE_TRUE:
                    return 1.0d;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0.0d;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).doubleValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsDouble(getText(), d2);
            }
        }
        return d2;
    }

    public String getValueAsString(String str) throws IOException, JsonParseException {
        String str2 = str;
        if (this._currToken == JsonToken.VALUE_STRING || (this._currToken != null && this._currToken != JsonToken.VALUE_NULL && this._currToken.isScalarValue())) {
            return getText();
        }
        return str2;
    }

    /* access modifiers changed from: protected */
    public void _decodeBase64(String str, ByteArrayBuilder byteArrayBuilder, Base64Variant base64Variant) throws IOException, JsonParseException {
        char charAt;
        StringBuilder sb;
        String str2 = str;
        ByteArrayBuilder byteArrayBuilder2 = byteArrayBuilder;
        Base64Variant base64Variant2 = base64Variant;
        int i = 0;
        int length = str2.length();
        while (i < length) {
            do {
                int i2 = i;
                i++;
                charAt = str2.charAt(i2);
                if (i >= length) {
                    return;
                }
            } while (charAt <= ' ');
            int decodeBase64Char = base64Variant2.decodeBase64Char(charAt);
            if (decodeBase64Char < 0) {
                _reportInvalidBase64(base64Variant2, charAt, 0, (String) null);
            }
            int i3 = decodeBase64Char;
            if (i >= length) {
                _reportBase64EOF();
            }
            int i4 = i;
            int i5 = i + 1;
            char charAt2 = str2.charAt(i4);
            int decodeBase64Char2 = base64Variant2.decodeBase64Char(charAt2);
            if (decodeBase64Char2 < 0) {
                _reportInvalidBase64(base64Variant2, charAt2, 1, (String) null);
            }
            int i6 = (i3 << 6) | decodeBase64Char2;
            if (i5 >= length) {
                if (!base64Variant2.usesPadding()) {
                    byteArrayBuilder2.append(i6 >> 4);
                    return;
                }
                _reportBase64EOF();
            }
            int i7 = i5;
            int i8 = i5 + 1;
            char charAt3 = str2.charAt(i7);
            int decodeBase64Char3 = base64Variant2.decodeBase64Char(charAt3);
            if (decodeBase64Char3 < 0) {
                if (decodeBase64Char3 != -2) {
                    _reportInvalidBase64(base64Variant2, charAt3, 2, (String) null);
                }
                if (i8 >= length) {
                    _reportBase64EOF();
                }
                int i9 = i8;
                i = i8 + 1;
                char charAt4 = str2.charAt(i9);
                if (!base64Variant2.usesPaddingChar(charAt4)) {
                    new StringBuilder();
                    _reportInvalidBase64(base64Variant2, charAt4, 3, sb.append("expected padding character '").append(base64Variant2.getPaddingChar()).append("'").toString());
                }
                byteArrayBuilder2.append(i6 >> 4);
            } else {
                int i10 = (i6 << 6) | decodeBase64Char3;
                if (i8 >= length) {
                    if (!base64Variant2.usesPadding()) {
                        byteArrayBuilder2.appendTwoBytes(i10 >> 2);
                        return;
                    }
                    _reportBase64EOF();
                }
                int i11 = i8;
                i = i8 + 1;
                char charAt5 = str2.charAt(i11);
                int decodeBase64Char4 = base64Variant2.decodeBase64Char(charAt5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        _reportInvalidBase64(base64Variant2, charAt5, 3, (String) null);
                    }
                    byteArrayBuilder2.appendTwoBytes(i10 >> 2);
                } else {
                    byteArrayBuilder2.appendThreeBytes((i10 << 6) | decodeBase64Char4);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) throws JsonParseException {
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        Base64Variant base64Variant2 = base64Variant;
        char c2 = c;
        int i2 = i;
        String str2 = str;
        if (c2 <= ' ') {
            new StringBuilder();
            sb2 = sb6.append("Illegal white space character (code 0x").append(Integer.toHexString(c2)).append(") as character #").append(i2 + 1).append(" of 4-char base64 unit: can only used between units").toString();
        } else if (base64Variant2.usesPaddingChar(c2)) {
            new StringBuilder();
            sb2 = sb4.append("Unexpected padding character ('").append(base64Variant2.getPaddingChar()).append("') as character #").append(i2 + 1).append(" of 4-char base64 unit: padding only legal as 3rd or 4th character").toString();
        } else if (!Character.isDefined(c2) || Character.isISOControl(c2)) {
            new StringBuilder();
            sb2 = sb.append("Illegal character (code 0x").append(Integer.toHexString(c2)).append(") in base64 content").toString();
        } else {
            new StringBuilder();
            sb2 = sb3.append("Illegal character '").append(c2).append("' (code 0x").append(Integer.toHexString(c2)).append(") in base64 content").toString();
        }
        if (str2 != null) {
            new StringBuilder();
            sb2 = sb5.append(sb2).append(": ").append(str2).toString();
        }
        throw _constructError(sb2);
    }

    /* access modifiers changed from: protected */
    public void _reportBase64EOF() throws JsonParseException {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    /* access modifiers changed from: protected */
    public void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        new StringBuilder();
        String sb3 = sb.append("Unexpected character (").append(_getCharDesc(i)).append(")").toString();
        if (str2 != null) {
            new StringBuilder();
            sb3 = sb2.append(sb3).append(": ").append(str2).toString();
        }
        _reportError(sb3);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidEOF() throws JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportInvalidEOF(sb.append(" in ").append(this._currToken).toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidEOF(String str) throws JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportError(sb.append("Unexpected end-of-input").append(str).toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    /* access modifiers changed from: protected */
    public void _throwInvalidSpace(int i) throws JsonParseException {
        StringBuilder sb;
        new StringBuilder();
        _reportError(sb.append("Illegal character (").append(_getCharDesc((char) i)).append("): only regular white space (\\r, \\n, \\t) is allowed between tokens").toString());
    }

    /* access modifiers changed from: protected */
    public void _throwUnquotedSpace(int i, String str) throws JsonParseException {
        StringBuilder sb;
        int i2 = i;
        String str2 = str;
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i2 >= 32) {
            new StringBuilder();
            _reportError(sb.append("Illegal unquoted character (").append(_getCharDesc((char) i2)).append("): has to be escaped using backslash to be included in ").append(str2).toString());
        }
    }

    /* access modifiers changed from: protected */
    public char _handleUnrecognizedCharacterEscape(char c) throws JsonProcessingException {
        StringBuilder sb;
        char c2 = c;
        if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c2;
        }
        if (c2 == '\'' && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return c2;
        }
        new StringBuilder();
        _reportError(sb.append("Unrecognized character escape ").append(_getCharDesc(c2)).toString());
        return c2;
    }

    protected static final String _getCharDesc(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        int i2 = i;
        char c = (char) i2;
        if (Character.isISOControl(c)) {
            new StringBuilder();
            return sb3.append("(CTRL-CHAR, code ").append(i2).append(")").toString();
        } else if (i2 > 255) {
            new StringBuilder();
            return sb2.append("'").append(c).append("' (code ").append(i2).append(" / 0x").append(Integer.toHexString(i2)).append(")").toString();
        } else {
            new StringBuilder();
            return sb.append("'").append(c).append("' (code ").append(i2).append(")").toString();
        }
    }

    /* access modifiers changed from: protected */
    public final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    /* access modifiers changed from: protected */
    public final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    /* access modifiers changed from: protected */
    public final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    /* access modifiers changed from: protected */
    public final JsonParseException _constructError(String str, Throwable th) {
        JsonParseException jsonParseException;
        new JsonParseException(str, getCurrentLocation(), th);
        return jsonParseException;
    }
}
