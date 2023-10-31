package com.shaded.fasterxml.jackson.core;

import com.shaded.fasterxml.jackson.core.type.TypeReference;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

public abstract class JsonParser implements Closeable, Versioned {
    private static final int MAX_BYTE_I = 255;
    private static final int MAX_SHORT_I = 32767;
    private static final int MIN_BYTE_I = -128;
    private static final int MIN_SHORT_I = -32768;
    protected int _features;

    public enum NumberType {
    }

    public abstract void clearCurrentToken();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException, JsonParseException;

    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName() throws IOException, JsonParseException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException, JsonParseException;

    public abstract double getDoubleValue() throws IOException, JsonParseException;

    public abstract Object getEmbeddedObject() throws IOException, JsonParseException;

    public abstract float getFloatValue() throws IOException, JsonParseException;

    public abstract int getIntValue() throws IOException, JsonParseException;

    public abstract JsonToken getLastClearedToken();

    public abstract long getLongValue() throws IOException, JsonParseException;

    public abstract NumberType getNumberType() throws IOException, JsonParseException;

    public abstract Number getNumberValue() throws IOException, JsonParseException;

    public abstract JsonStreamContext getParsingContext();

    public abstract String getText() throws IOException, JsonParseException;

    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    public abstract int getTextLength() throws IOException, JsonParseException;

    public abstract int getTextOffset() throws IOException, JsonParseException;

    public abstract JsonLocation getTokenLocation();

    public abstract String getValueAsString(String str) throws IOException, JsonParseException;

    public abstract boolean hasCurrentToken();

    public abstract boolean hasTextCharacters();

    public abstract boolean isClosed();

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public abstract JsonToken nextValue() throws IOException, JsonParseException;

    public abstract void overrideCurrentName(String str);

    public abstract void setCodec(ObjectCodec objectCodec);

    public abstract JsonParser skipChildren() throws IOException, JsonParseException;

    public abstract Version version();

    public enum Feature {
        ;
        
        private final boolean _defaultState;

        public static int collectDefaults() {
            int i = 0;
            Feature[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 < length; i2++) {
                Feature feature = values[i2];
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        private Feature(boolean z) {
            String str = r8;
            int i = r9;
            this._defaultState = z;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    protected JsonParser() {
    }

    protected JsonParser(int i) {
        this._features = i;
    }

    public Object getInputSource() {
        return null;
    }

    public void setSchema(FormatSchema formatSchema) {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Parser of type ").append(getClass().getName()).append(" does not support schema of type '").append(formatSchema.getSchemaType()).append("'").toString());
        throw th2;
    }

    public FormatSchema getSchema() {
        return null;
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        FormatSchema formatSchema2 = formatSchema;
        return false;
    }

    public boolean requiresCustomCodec() {
        return false;
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        OutputStream outputStream2 = outputStream;
        return -1;
    }

    public int releaseBuffered(Writer writer) throws IOException {
        Writer writer2 = writer;
        return -1;
    }

    public JsonParser enable(Feature feature) {
        this._features |= feature.getMask();
        return this;
    }

    public JsonParser disable(Feature feature) {
        this._features &= feature.getMask() ^ -1;
        return this;
    }

    public JsonParser configure(Feature feature, boolean z) {
        Feature feature2 = feature;
        if (z) {
            JsonParser enable = enable(feature2);
        } else {
            JsonParser disable = disable(feature2);
        }
        return this;
    }

    public boolean isEnabled(Feature feature) {
        return (this._features & feature.getMask()) != 0;
    }

    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        return nextToken() == JsonToken.FIELD_NAME && serializableString.getValue().equals(getCurrentName());
    }

    public String nextTextValue() throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_STRING ? getText() : null;
    }

    public int nextIntValue(int i) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
    }

    public long nextLongValue(long j) throws IOException, JsonParseException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
    }

    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        switch (nextToken()) {
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    public boolean isExpectedStartArrayToken() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public byte getByteValue() throws IOException, JsonParseException {
        StringBuilder sb;
        int intValue = getIntValue();
        if (intValue >= MIN_BYTE_I && intValue <= 255) {
            return (byte) intValue;
        }
        new StringBuilder();
        throw _constructError(sb.append("Numeric value (").append(getText()).append(") out of range of Java byte").toString());
    }

    public short getShortValue() throws IOException, JsonParseException {
        StringBuilder sb;
        int intValue = getIntValue();
        if (intValue >= MIN_SHORT_I && intValue <= MAX_SHORT_I) {
            return (short) intValue;
        }
        new StringBuilder();
        throw _constructError(sb.append("Numeric value (").append(getText()).append(") out of range of Java short").toString());
    }

    public boolean getBooleanValue() throws IOException, JsonParseException {
        Throwable th;
        StringBuilder sb;
        JsonToken currentToken = getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return false;
        }
        Throwable th2 = th;
        new StringBuilder();
        new JsonParseException(sb.append("Current token (").append(currentToken).append(") not of boolean type").toString(), getCurrentLocation());
        throw th2;
    }

    public byte[] getBinaryValue() throws IOException, JsonParseException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public int readBinaryValue(OutputStream outputStream) throws IOException, JsonParseException {
        return readBinaryValue(Base64Variants.getDefaultVariant(), outputStream);
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
        Base64Variant base64Variant2 = base64Variant;
        OutputStream outputStream2 = outputStream;
        _reportUnsupportedOperation();
        return 0;
    }

    public int getValueAsInt() throws IOException, JsonParseException {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int i) throws IOException, JsonParseException {
        return i;
    }

    public long getValueAsLong() throws IOException, JsonParseException {
        return getValueAsLong(0);
    }

    public long getValueAsLong(long j) throws IOException, JsonParseException {
        return j;
    }

    public double getValueAsDouble() throws IOException, JsonParseException {
        return getValueAsDouble(0.0d);
    }

    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        return d;
    }

    public boolean getValueAsBoolean() throws IOException, JsonParseException {
        return getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean z) throws IOException, JsonParseException {
        return z;
    }

    public String getValueAsString() throws IOException, JsonParseException {
        return getValueAsString((String) null);
    }

    public <T> T readValueAs(Class<T> cls) throws IOException, JsonProcessingException {
        Throwable th;
        Class<T> cls2 = cls;
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValue(this, cls2);
        }
        Throwable th2 = th;
        new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        throw th2;
    }

    public <T> T readValueAs(TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        Throwable th;
        TypeReference<?> typeReference2 = typeReference;
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValue(this, typeReference2);
        }
        Throwable th2 = th;
        new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        throw th2;
    }

    public <T> Iterator<T> readValuesAs(Class<T> cls) throws IOException, JsonProcessingException {
        Throwable th;
        Class<T> cls2 = cls;
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValues(this, cls2);
        }
        Throwable th2 = th;
        new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        throw th2;
    }

    public <T> Iterator<T> readValuesAs(TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        Throwable th;
        TypeReference<?> typeReference2 = typeReference;
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readValues(this, typeReference2);
        }
        Throwable th2 = th;
        new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        throw th2;
    }

    public <T extends TreeNode> T readValueAsTree() throws IOException, JsonProcessingException {
        Throwable th;
        ObjectCodec codec = getCodec();
        if (codec != null) {
            return codec.readTree(this);
        }
        Throwable th2 = th;
        new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public JsonParseException _constructError(String str) {
        JsonParseException jsonParseException;
        new JsonParseException(str, getCurrentLocation());
        return jsonParseException;
    }

    /* access modifiers changed from: protected */
    public void _reportUnsupportedOperation() {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Operation not supported by parser of type ").append(getClass().getName()).toString());
        throw th2;
    }
}
