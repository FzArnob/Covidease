package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonStreamContext;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.TreeNode;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.base.ParserMinimalBase;
import com.shaded.fasterxml.jackson.core.json.JsonReadContext;
import com.shaded.fasterxml.jackson.core.json.JsonWriteContext;
import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.shaded.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TokenBuffer extends JsonGenerator {
    protected static final int DEFAULT_PARSER_FEATURES = JsonParser.Feature.collectDefaults();
    protected int _appendOffset;
    protected boolean _closed;
    protected Segment _first;
    protected int _generatorFeatures = DEFAULT_PARSER_FEATURES;
    protected Segment _last;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext = JsonWriteContext.createRootContext();

    public TokenBuffer(ObjectCodec objectCodec) {
        Segment segment;
        this._objectCodec = objectCodec;
        Segment segment2 = segment;
        new Segment();
        Segment segment3 = segment2;
        this._last = segment3;
        this._first = segment3;
        this._appendOffset = 0;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public JsonParser asParser() {
        return asParser(this._objectCodec);
    }

    public JsonParser asParser(ObjectCodec objectCodec) {
        JsonParser jsonParser;
        new Parser(this._first, objectCodec);
        return jsonParser;
    }

    public JsonParser asParser(JsonParser jsonParser) {
        Parser parser;
        JsonParser jsonParser2 = jsonParser;
        new Parser(this._first, jsonParser2.getCodec());
        Parser parser2 = parser;
        parser2.setLocation(jsonParser2.getTokenLocation());
        return parser2;
    }

    public JsonToken firstToken() {
        if (this._first != null) {
            return this._first.type(0);
        }
        return null;
    }

    public TokenBuffer append(TokenBuffer tokenBuffer) throws IOException, JsonGenerationException {
        JsonParser asParser = tokenBuffer.asParser();
        while (asParser.nextToken() != null) {
            copyCurrentEvent(asParser);
        }
        return this;
    }

    public void serialize(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Segment segment = this._first;
        int i = -1;
        while (true) {
            i++;
            if (i >= 16) {
                i = 0;
                segment = segment.next();
                if (segment == null) {
                    return;
                }
            }
            JsonToken type = segment.type(i);
            if (type != null) {
                switch (type) {
                    case START_OBJECT:
                        jsonGenerator2.writeStartObject();
                        break;
                    case END_OBJECT:
                        jsonGenerator2.writeEndObject();
                        break;
                    case START_ARRAY:
                        jsonGenerator2.writeStartArray();
                        break;
                    case END_ARRAY:
                        jsonGenerator2.writeEndArray();
                        break;
                    case FIELD_NAME:
                        Object obj = segment.get(i);
                        if (!(obj instanceof SerializableString)) {
                            jsonGenerator2.writeFieldName((String) obj);
                            break;
                        } else {
                            jsonGenerator2.writeFieldName((SerializableString) obj);
                            break;
                        }
                    case VALUE_STRING:
                        Object obj2 = segment.get(i);
                        if (!(obj2 instanceof SerializableString)) {
                            jsonGenerator2.writeString((String) obj2);
                            break;
                        } else {
                            jsonGenerator2.writeString((SerializableString) obj2);
                            break;
                        }
                    case VALUE_NUMBER_INT:
                        Object obj3 = segment.get(i);
                        if (!(obj3 instanceof Integer)) {
                            if (!(obj3 instanceof BigInteger)) {
                                if (!(obj3 instanceof Long)) {
                                    if (!(obj3 instanceof Short)) {
                                        jsonGenerator2.writeNumber(((Number) obj3).intValue());
                                        break;
                                    } else {
                                        jsonGenerator2.writeNumber(((Short) obj3).shortValue());
                                        break;
                                    }
                                } else {
                                    jsonGenerator2.writeNumber(((Long) obj3).longValue());
                                    break;
                                }
                            } else {
                                jsonGenerator2.writeNumber((BigInteger) obj3);
                                break;
                            }
                        } else {
                            jsonGenerator2.writeNumber(((Integer) obj3).intValue());
                            break;
                        }
                    case VALUE_NUMBER_FLOAT:
                        Object obj4 = segment.get(i);
                        if (obj4 instanceof Double) {
                            jsonGenerator2.writeNumber(((Double) obj4).doubleValue());
                            break;
                        } else if (obj4 instanceof BigDecimal) {
                            jsonGenerator2.writeNumber((BigDecimal) obj4);
                            break;
                        } else if (obj4 instanceof Float) {
                            jsonGenerator2.writeNumber(((Float) obj4).floatValue());
                            break;
                        } else if (obj4 == null) {
                            jsonGenerator2.writeNull();
                            break;
                        } else if (obj4 instanceof String) {
                            jsonGenerator2.writeNumber((String) obj4);
                            break;
                        } else {
                            Throwable th3 = th2;
                            new StringBuilder();
                            new JsonGenerationException(sb.append("Unrecognized value type for VALUE_NUMBER_FLOAT: ").append(obj4.getClass().getName()).append(", can not serialize").toString());
                            throw th3;
                        }
                    case VALUE_TRUE:
                        jsonGenerator2.writeBoolean(true);
                        break;
                    case VALUE_FALSE:
                        jsonGenerator2.writeBoolean(false);
                        break;
                    case VALUE_NULL:
                        jsonGenerator2.writeNull();
                        break;
                    case VALUE_EMBEDDED_OBJECT:
                        jsonGenerator2.writeObject(segment.get(i));
                        break;
                    default:
                        Throwable th4 = th;
                        new RuntimeException("Internal error: should never end up through this code path");
                        throw th4;
                }
            } else {
                return;
            }
        }
    }

    public String toString() {
        StringBuilder sb;
        Throwable th;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("[TokenBuffer: ");
        JsonParser asParser = asParser();
        int i = 0;
        while (true) {
            try {
                JsonToken nextToken = asParser.nextToken();
                if (nextToken == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        StringBuilder append2 = sb2.append(", ");
                    }
                    StringBuilder append3 = sb2.append(nextToken.toString());
                    if (nextToken == JsonToken.FIELD_NAME) {
                        StringBuilder append4 = sb2.append('(');
                        StringBuilder append5 = sb2.append(asParser.getCurrentName());
                        StringBuilder append6 = sb2.append(')');
                    }
                }
                i++;
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th2 = th;
                new IllegalStateException(iOException);
                throw th2;
            }
        }
        if (i >= 100) {
            StringBuilder append7 = sb2.append(" ... (truncated ").append(i - 100).append(" entries)");
        }
        StringBuilder append8 = sb2.append(']');
        return sb2.toString();
    }

    public JsonGenerator enable(JsonGenerator.Feature feature) {
        this._generatorFeatures |= feature.getMask();
        return this;
    }

    public JsonGenerator disable(JsonGenerator.Feature feature) {
        this._generatorFeatures &= feature.getMask() ^ -1;
        return this;
    }

    public boolean isEnabled(JsonGenerator.Feature feature) {
        return (this._generatorFeatures & feature.getMask()) != 0;
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
        this._closed = true;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public final void writeStartArray() throws IOException, JsonGenerationException {
        _append(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    public final void writeEndArray() throws IOException, JsonGenerationException {
        _append(JsonToken.END_ARRAY);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    public final void writeStartObject() throws IOException, JsonGenerationException {
        _append(JsonToken.START_OBJECT);
        this._writeContext = this._writeContext.createChildObjectContext();
    }

    public final void writeEndObject() throws IOException, JsonGenerationException {
        _append(JsonToken.END_OBJECT);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    public final void writeFieldName(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        _append(JsonToken.FIELD_NAME, str2);
        int writeFieldName = this._writeContext.writeFieldName(str2);
    }

    public void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        _append(JsonToken.FIELD_NAME, serializableString2);
        int writeFieldName = this._writeContext.writeFieldName(serializableString2.getValue());
    }

    public void writeString(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        if (str2 == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, str2);
        }
    }

    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        String str;
        new String(cArr, i, i2);
        writeString(str);
    }

    public void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        if (serializableString2 == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, serializableString2);
        }
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
        _reportUnsupportedOperation();
    }

    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        _reportUnsupportedOperation();
    }

    public void writeRaw(SerializableString serializableString) throws IOException, JsonGenerationException {
        SerializableString serializableString2 = serializableString;
        _reportUnsupportedOperation();
    }

    public void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        _reportUnsupportedOperation();
    }

    public void writeRaw(char c) throws IOException, JsonGenerationException {
        char c2 = c;
        _reportUnsupportedOperation();
    }

    public void writeRawValue(String str) throws IOException, JsonGenerationException {
        String str2 = str;
        _reportUnsupportedOperation();
    }

    public void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException {
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        _reportUnsupportedOperation();
    }

    public void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        _reportUnsupportedOperation();
    }

    public void writeNumber(short s) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_INT, Short.valueOf(s));
    }

    public void writeNumber(int i) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    public void writeNumber(long j) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    public void writeNumber(double d) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    public void writeNumber(float f) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        BigDecimal bigDecimal2 = bigDecimal;
        if (bigDecimal2 == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_FLOAT, bigDecimal2);
        }
    }

    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        BigInteger bigInteger2 = bigInteger;
        if (bigInteger2 == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_INT, bigInteger2);
        }
    }

    public void writeNumber(String str) throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NUMBER_FLOAT, str);
    }

    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        _append(z ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }

    public void writeNull() throws IOException, JsonGenerationException {
        _append(JsonToken.VALUE_NULL);
    }

    public void writeObject(Object obj) throws IOException, JsonProcessingException {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    public void writeTree(TreeNode treeNode) throws IOException, JsonProcessingException {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, treeNode);
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        Base64Variant base64Variant2 = base64Variant;
        int i3 = i2;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        writeObject(bArr2);
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) {
        Throwable th;
        Base64Variant base64Variant2 = base64Variant;
        InputStream inputStream2 = inputStream;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void copyCurrentEvent(JsonParser jsonParser) throws IOException, JsonProcessingException {
        Throwable th;
        JsonParser jsonParser2 = jsonParser;
        switch (jsonParser2.getCurrentToken()) {
            case START_OBJECT:
                writeStartObject();
                return;
            case END_OBJECT:
                writeEndObject();
                return;
            case START_ARRAY:
                writeStartArray();
                return;
            case END_ARRAY:
                writeEndArray();
                return;
            case FIELD_NAME:
                writeFieldName(jsonParser2.getCurrentName());
                return;
            case VALUE_STRING:
                if (jsonParser2.hasTextCharacters()) {
                    writeString(jsonParser2.getTextCharacters(), jsonParser2.getTextOffset(), jsonParser2.getTextLength());
                    return;
                } else {
                    writeString(jsonParser2.getText());
                    return;
                }
            case VALUE_NUMBER_INT:
                switch (jsonParser2.getNumberType()) {
                    case INT:
                        writeNumber(jsonParser2.getIntValue());
                        return;
                    case BIG_INTEGER:
                        writeNumber(jsonParser2.getBigIntegerValue());
                        return;
                    default:
                        writeNumber(jsonParser2.getLongValue());
                        return;
                }
            case VALUE_NUMBER_FLOAT:
                switch (jsonParser2.getNumberType()) {
                    case BIG_DECIMAL:
                        writeNumber(jsonParser2.getDecimalValue());
                        return;
                    case FLOAT:
                        writeNumber(jsonParser2.getFloatValue());
                        return;
                    default:
                        writeNumber(jsonParser2.getDoubleValue());
                        return;
                }
            case VALUE_TRUE:
                writeBoolean(true);
                return;
            case VALUE_FALSE:
                writeBoolean(false);
                return;
            case VALUE_NULL:
                writeNull();
                return;
            case VALUE_EMBEDDED_OBJECT:
                writeObject(jsonParser2.getEmbeddedObject());
                return;
            default:
                Throwable th2 = th;
                new RuntimeException("Internal error: should never end up through this code path");
                throw th2;
        }
    }

    public void copyCurrentStructure(JsonParser jsonParser) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.FIELD_NAME) {
            writeFieldName(jsonParser2.getCurrentName());
            currentToken = jsonParser2.nextToken();
        }
        switch (currentToken) {
            case START_OBJECT:
                writeStartObject();
                while (jsonParser2.nextToken() != JsonToken.END_OBJECT) {
                    copyCurrentStructure(jsonParser2);
                }
                writeEndObject();
                return;
            case START_ARRAY:
                writeStartArray();
                while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                    copyCurrentStructure(jsonParser2);
                }
                writeEndArray();
                return;
            default:
                copyCurrentEvent(jsonParser2);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public final void _append(JsonToken jsonToken) {
        Segment append = this._last.append(this._appendOffset, jsonToken);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    /* access modifiers changed from: protected */
    public final void _append(JsonToken jsonToken, Object obj) {
        Segment append = this._last.append(this._appendOffset, jsonToken, obj);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    /* access modifiers changed from: protected */
    public final void _appendRaw(int i, Object obj) {
        Segment appendRaw = this._last.appendRaw(this._appendOffset, i, obj);
        if (appendRaw == null) {
            this._appendOffset++;
            return;
        }
        this._last = appendRaw;
        this._appendOffset = 1;
    }

    /* access modifiers changed from: protected */
    public void _reportUnsupportedOperation() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Called operation not supported for TokenBuffer");
        throw th2;
    }

    protected static final class Parser extends ParserMinimalBase {
        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected JsonLocation _location = null;
        protected JsonReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Parser(Segment segment, ObjectCodec objectCodec) {
            super(0);
            this._segment = segment;
            this._segmentPtr = -1;
            this._codec = objectCodec;
            this._parsingContext = JsonReadContext.createRootContext(-1, -1);
        }

        public void setLocation(JsonLocation jsonLocation) {
            JsonLocation jsonLocation2 = jsonLocation;
            this._location = jsonLocation2;
        }

        public ObjectCodec getCodec() {
            return this._codec;
        }

        public void setCodec(ObjectCodec objectCodec) {
            ObjectCodec objectCodec2 = objectCodec;
            this._codec = objectCodec2;
        }

        public Version version() {
            return PackageVersion.VERSION;
        }

        public JsonToken peekNextToken() throws IOException, JsonParseException {
            if (this._closed) {
                return null;
            }
            Segment segment = this._segment;
            int i = this._segmentPtr + 1;
            if (i >= 16) {
                i = 0;
                segment = segment == null ? null : segment.next();
            }
            return segment == null ? null : segment.type(i);
        }

        public void close() throws IOException {
            if (!this._closed) {
                this._closed = true;
            }
        }

        public JsonToken nextToken() throws IOException, JsonParseException {
            if (this._closed || this._segment == null) {
                return null;
            }
            int i = this._segmentPtr + 1;
            int i2 = i;
            this._segmentPtr = i;
            if (i2 >= 16) {
                this._segmentPtr = 0;
                this._segment = this._segment.next();
                if (this._segment == null) {
                    return null;
                }
            }
            this._currToken = this._segment.type(this._segmentPtr);
            if (this._currToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                this._parsingContext.setCurrentName(_currentObject instanceof String ? (String) _currentObject : _currentObject.toString());
            } else if (this._currToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            } else if (this._currToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
            } else if (this._currToken == JsonToken.END_OBJECT || this._currToken == JsonToken.END_ARRAY) {
                this._parsingContext = this._parsingContext.getParent();
                if (this._parsingContext == null) {
                    this._parsingContext = JsonReadContext.createRootContext(-1, -1);
                }
            }
            return this._currToken;
        }

        public boolean isClosed() {
            return this._closed;
        }

        public JsonStreamContext getParsingContext() {
            return this._parsingContext;
        }

        public JsonLocation getTokenLocation() {
            return getCurrentLocation();
        }

        public JsonLocation getCurrentLocation() {
            return this._location == null ? JsonLocation.f285NA : this._location;
        }

        public String getCurrentName() {
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

        public String getText() {
            if (this._currToken == JsonToken.VALUE_STRING || this._currToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof String) {
                    return (String) _currentObject;
                }
                return _currentObject == null ? null : _currentObject.toString();
            } else if (this._currToken == null) {
                return null;
            } else {
                switch (this._currToken) {
                    case VALUE_NUMBER_INT:
                    case VALUE_NUMBER_FLOAT:
                        Object _currentObject2 = _currentObject();
                        return _currentObject2 == null ? null : _currentObject2.toString();
                    default:
                        return this._currToken.asString();
                }
            }
        }

        public char[] getTextCharacters() {
            String text = getText();
            return text == null ? null : text.toCharArray();
        }

        public int getTextLength() {
            String text = getText();
            return text == null ? 0 : text.length();
        }

        public int getTextOffset() {
            return 0;
        }

        public boolean hasTextCharacters() {
            return false;
        }

        public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigInteger) {
                return (BigInteger) numberValue;
            }
            if (getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
                return ((BigDecimal) numberValue).toBigInteger();
            }
            return BigInteger.valueOf(numberValue.longValue());
        }

        public BigDecimal getDecimalValue() throws IOException, JsonParseException {
            BigDecimal bigDecimal;
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigDecimal) {
                return (BigDecimal) numberValue;
            }
            switch (getNumberType()) {
                case INT:
                case LONG:
                    return BigDecimal.valueOf(numberValue.longValue());
                case BIG_INTEGER:
                    new BigDecimal((BigInteger) numberValue);
                    return bigDecimal;
                default:
                    return BigDecimal.valueOf(numberValue.doubleValue());
            }
        }

        public double getDoubleValue() throws IOException, JsonParseException {
            return getNumberValue().doubleValue();
        }

        public float getFloatValue() throws IOException, JsonParseException {
            return getNumberValue().floatValue();
        }

        public int getIntValue() throws IOException, JsonParseException {
            if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
                return ((Number) _currentObject()).intValue();
            }
            return getNumberValue().intValue();
        }

        public long getLongValue() throws IOException, JsonParseException {
            return getNumberValue().longValue();
        }

        public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
            Number numberValue = getNumberValue();
            if (numberValue instanceof Integer) {
                return JsonParser.NumberType.INT;
            }
            if (numberValue instanceof Long) {
                return JsonParser.NumberType.LONG;
            }
            if (numberValue instanceof Double) {
                return JsonParser.NumberType.DOUBLE;
            }
            if (numberValue instanceof BigDecimal) {
                return JsonParser.NumberType.BIG_DECIMAL;
            }
            if (numberValue instanceof BigInteger) {
                return JsonParser.NumberType.BIG_INTEGER;
            }
            if (numberValue instanceof Float) {
                return JsonParser.NumberType.FLOAT;
            }
            if (numberValue instanceof Short) {
                return JsonParser.NumberType.INT;
            }
            return null;
        }

        public final Number getNumberValue() throws IOException, JsonParseException {
            Throwable th;
            StringBuilder sb;
            _checkIsNumber();
            Object _currentObject = _currentObject();
            if (_currentObject instanceof Number) {
                return (Number) _currentObject;
            }
            if (_currentObject instanceof String) {
                String str = (String) _currentObject;
                if (str.indexOf(46) >= 0) {
                    return Double.valueOf(Double.parseDouble(str));
                }
                return Long.valueOf(Long.parseLong(str));
            } else if (_currentObject == null) {
                return null;
            } else {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Internal error: entry should be a Number, but is of type ").append(_currentObject.getClass().getName()).toString());
                throw th2;
            }
        }

        public Object getEmbeddedObject() {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return _currentObject();
            }
            return null;
        }

        public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
            ByteArrayBuilder byteArrayBuilder;
            StringBuilder sb;
            Base64Variant base64Variant2 = base64Variant;
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof byte[]) {
                    return (byte[]) _currentObject;
                }
            }
            if (this._currToken != JsonToken.VALUE_STRING) {
                new StringBuilder();
                throw _constructError(sb.append("Current token (").append(this._currToken).append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary").toString());
            }
            String text = getText();
            if (text == null) {
                return null;
            }
            ByteArrayBuilder byteArrayBuilder2 = this._byteBuilder;
            if (byteArrayBuilder2 == null) {
                new ByteArrayBuilder(100);
                ByteArrayBuilder byteArrayBuilder3 = byteArrayBuilder;
                byteArrayBuilder2 = byteArrayBuilder3;
                this._byteBuilder = byteArrayBuilder3;
            } else {
                this._byteBuilder.reset();
            }
            _decodeBase64(text, byteArrayBuilder2, base64Variant2);
            return byteArrayBuilder2.toByteArray();
        }

        public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
            OutputStream outputStream2 = outputStream;
            byte[] binaryValue = getBinaryValue(base64Variant);
            if (binaryValue == null) {
                return 0;
            }
            outputStream2.write(binaryValue, 0, binaryValue.length);
            return binaryValue.length;
        }

        /* access modifiers changed from: protected */
        public final Object _currentObject() {
            return this._segment.get(this._segmentPtr);
        }

        /* access modifiers changed from: protected */
        public final void _checkIsNumber() throws JsonParseException {
            StringBuilder sb;
            if (this._currToken == null || !this._currToken.isNumeric()) {
                new StringBuilder();
                throw _constructError(sb.append("Current token (").append(this._currToken).append(") not numeric, can not use numeric value accessors").toString());
            }
        }

        /* access modifiers changed from: protected */
        public void _handleEOF() throws JsonParseException {
            _throwInternal();
        }
    }

    protected static final class Segment {
        public static final int TOKENS_PER_SEGMENT = 16;
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens = new Object[16];

        static {
            JsonToken[] values = JsonToken.values();
            System.arraycopy(values, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, values.length - 1));
        }

        public Segment() {
        }

        public JsonToken type(int i) {
            int i2 = i;
            long j = this._tokenTypes;
            if (i2 > 0) {
                j >>= i2 << 2;
            }
            return TOKEN_TYPES_BY_INDEX[((int) j) & 15];
        }

        public int rawType(int i) {
            int i2 = i;
            long j = this._tokenTypes;
            if (i2 > 0) {
                j >>= i2 << 2;
            }
            return ((int) j) & 15;
        }

        public Object get(int i) {
            return this._tokens[i];
        }

        public Segment next() {
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken) {
            Segment segment;
            int i2 = i;
            JsonToken jsonToken2 = jsonToken;
            if (i2 < 16) {
                set(i2, jsonToken2);
                return null;
            }
            new Segment();
            this._next = segment;
            this._next.set(0, jsonToken2);
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj) {
            Segment segment;
            int i2 = i;
            JsonToken jsonToken2 = jsonToken;
            Object obj2 = obj;
            if (i2 < 16) {
                set(i2, jsonToken2, obj2);
                return null;
            }
            new Segment();
            this._next = segment;
            this._next.set(0, jsonToken2, obj2);
            return this._next;
        }

        public Segment appendRaw(int i, int i2, Object obj) {
            Segment segment;
            int i3 = i;
            int i4 = i2;
            Object obj2 = obj;
            if (i3 < 16) {
                set(i3, i4, obj2);
                return null;
            }
            new Segment();
            this._next = segment;
            this._next.set(0, i4, obj2);
            return this._next;
        }

        public void set(int i, JsonToken jsonToken) {
            int i2 = i;
            long ordinal = (long) jsonToken.ordinal();
            if (i2 > 0) {
                ordinal <<= i2 << 2;
            }
            this._tokenTypes |= ordinal;
        }

        public void set(int i, JsonToken jsonToken, Object obj) {
            int i2 = i;
            this._tokens[i2] = obj;
            long ordinal = (long) jsonToken.ordinal();
            if (i2 > 0) {
                ordinal <<= i2 << 2;
            }
            this._tokenTypes |= ordinal;
        }

        private void set(int i, int i2, Object obj) {
            int i3 = i;
            this._tokens[i3] = obj;
            long j = (long) i2;
            if (i3 > 0) {
                j <<= i3 << 2;
            }
            this._tokenTypes |= j;
        }
    }
}
