package com.shaded.fasterxml.jackson.core.base;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.PrettyPrinter;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.TreeNode;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.json.JsonWriteContext;
import com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.shaded.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class GeneratorBase extends JsonGenerator {
    protected boolean _cfgNumbersAsStrings;
    protected boolean _closed;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext = JsonWriteContext.createRootContext();

    /* access modifiers changed from: protected */
    public abstract void _releaseBuffers();

    /* access modifiers changed from: protected */
    public abstract void _verifyValueWrite(String str) throws IOException, JsonGenerationException;

    public abstract void flush() throws IOException;

    protected GeneratorBase(int i, ObjectCodec objectCodec) {
        this._features = i;
        this._objectCodec = objectCodec;
        this._cfgNumbersAsStrings = isEnabled(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public JsonGenerator enable(JsonGenerator.Feature feature) {
        JsonGenerator.Feature feature2 = feature;
        this._features |= feature2.getMask();
        if (feature2 == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = true;
        } else if (feature2 == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
            JsonGenerator highestNonEscapedChar = setHighestNonEscapedChar(127);
        }
        return this;
    }

    public JsonGenerator disable(JsonGenerator.Feature feature) {
        JsonGenerator.Feature feature2 = feature;
        this._features &= feature2.getMask() ^ -1;
        if (feature2 == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = false;
        } else if (feature2 == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
            JsonGenerator highestNonEscapedChar = setHighestNonEscapedChar(0);
        }
        return this;
    }

    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (this._features & feature.getMask()) != 0;
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        PrettyPrinter prettyPrinter;
        if (getPrettyPrinter() != null) {
            return this;
        }
        new DefaultPrettyPrinter();
        return setPrettyPrinter(prettyPrinter);
    }

    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public final ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    public void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        writeFieldName(serializableString.getValue());
    }

    public void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        writeString(serializableString.getValue());
    }

    public void writeRawValue(String str) throws IOException, JsonGenerationException {
        _verifyValueWrite("write raw value");
        writeRaw(str);
    }

    public void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write raw value");
        writeRaw(str, i, i2);
    }

    public void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write raw value");
        writeRaw(cArr, i, i2);
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException, JsonGenerationException {
        Base64Variant base64Variant2 = base64Variant;
        InputStream inputStream2 = inputStream;
        int i2 = i;
        _reportUnsupportedOperation();
        return 0;
    }

    public void writeObject(Object obj) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        if (obj2 == null) {
            writeNull();
        } else if (this._objectCodec != null) {
            this._objectCodec.writeValue(this, obj2);
        } else {
            _writeSimpleObject(obj2);
        }
    }

    public void writeTree(TreeNode treeNode) throws IOException, JsonProcessingException {
        Throwable th;
        TreeNode treeNode2 = treeNode;
        if (treeNode2 == null) {
            writeNull();
        } else if (this._objectCodec == null) {
            Throwable th2 = th;
            new IllegalStateException("No ObjectCodec defined");
            throw th2;
        } else {
            this._objectCodec.writeValue(this, treeNode2);
        }
    }

    public void close() throws IOException {
        this._closed = true;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public final void copyCurrentEvent(JsonParser jsonParser) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == null) {
            _reportError("No current event to copy");
        }
        switch (currentToken) {
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
                _throwInternal();
                return;
        }
    }

    public final void copyCurrentStructure(JsonParser jsonParser) throws IOException, JsonProcessingException {
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
    public void _reportError(String str) throws JsonGenerationException {
        Throwable th;
        Throwable th2 = th;
        new JsonGenerationException(str);
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void _writeSimpleObject(Object obj) throws IOException, JsonGenerationException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        if (obj2 == null) {
            writeNull();
        } else if (obj2 instanceof String) {
            writeString((String) obj2);
        } else {
            if (obj2 instanceof Number) {
                Number number = (Number) obj2;
                if (number instanceof Integer) {
                    writeNumber(number.intValue());
                    return;
                } else if (number instanceof Long) {
                    writeNumber(number.longValue());
                    return;
                } else if (number instanceof Double) {
                    writeNumber(number.doubleValue());
                    return;
                } else if (number instanceof Float) {
                    writeNumber(number.floatValue());
                    return;
                } else if (number instanceof Short) {
                    writeNumber(number.shortValue());
                    return;
                } else if (number instanceof Byte) {
                    writeNumber((short) number.byteValue());
                    return;
                } else if (number instanceof BigInteger) {
                    writeNumber((BigInteger) number);
                    return;
                } else if (number instanceof BigDecimal) {
                    writeNumber((BigDecimal) number);
                    return;
                } else if (number instanceof AtomicInteger) {
                    writeNumber(((AtomicInteger) number).get());
                    return;
                } else if (number instanceof AtomicLong) {
                    writeNumber(((AtomicLong) number).get());
                    return;
                }
            } else if (obj2 instanceof byte[]) {
                writeBinary((byte[]) obj2);
                return;
            } else if (obj2 instanceof Boolean) {
                writeBoolean(((Boolean) obj2).booleanValue());
                return;
            } else if (obj2 instanceof AtomicBoolean) {
                writeBoolean(((AtomicBoolean) obj2).get());
                return;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed ").append(obj2.getClass().getName()).append(")").toString());
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    /* access modifiers changed from: protected */
    public void _reportUnsupportedOperation() {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Operation not supported by generator of type ").append(getClass().getName()).toString());
        throw th2;
    }
}
