package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonStreamContext;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.base.ParserMinimalBase;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.cfg.PackageVersion;
import com.shaded.fasterxml.jackson.databind.node.NodeCursor;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TreeTraversingParser extends ParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TreeTraversingParser(JsonNode jsonNode) {
        this(jsonNode, (ObjectCodec) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TreeTraversingParser(JsonNode jsonNode, ObjectCodec objectCodec) {
        super(0);
        NodeCursor nodeCursor;
        NodeCursor nodeCursor2;
        NodeCursor nodeCursor3;
        JsonNode jsonNode2 = jsonNode;
        this._objectCodec = objectCodec;
        if (jsonNode2.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            new NodeCursor.Array(jsonNode2, (NodeCursor) null);
            this._nodeCursor = nodeCursor3;
        } else if (jsonNode2.isObject()) {
            this._nextToken = JsonToken.START_OBJECT;
            new NodeCursor.Object(jsonNode2, (NodeCursor) null);
            this._nodeCursor = nodeCursor2;
        } else {
            new NodeCursor.RootValue(jsonNode2, (NodeCursor) null);
            this._nodeCursor = nodeCursor;
        }
    }

    public void setCodec(ObjectCodec objectCodec) {
        ObjectCodec objectCodec2 = objectCodec;
        this._objectCodec = objectCodec2;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            this._nodeCursor = null;
            this._currToken = null;
        }
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        if (this._nextToken != null) {
            this._currToken = this._nextToken;
            this._nextToken = null;
            return this._currToken;
        } else if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            this._nodeCursor = this._nodeCursor.iterateChildren();
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        } else if (this._nodeCursor == null) {
            this._closed = true;
            return null;
        } else {
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken != null) {
                if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                    this._startContainer = true;
                }
                return this._currToken;
            }
            this._currToken = this._nodeCursor.endToken();
            this._nodeCursor = this._nodeCursor.getParent();
            return this._currToken;
        }
    }

    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public String getCurrentName() {
        return this._nodeCursor == null ? null : this._nodeCursor.getCurrentName();
    }

    public void overrideCurrentName(String str) {
        String str2 = str;
        if (this._nodeCursor != null) {
            this._nodeCursor.overrideCurrentName(str2);
        }
    }

    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    public JsonLocation getTokenLocation() {
        return JsonLocation.f285NA;
    }

    public JsonLocation getCurrentLocation() {
        return JsonLocation.f285NA;
    }

    public String getText() {
        String asString;
        if (this._closed) {
            return null;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                return this._nodeCursor.getCurrentName();
            case VALUE_STRING:
                return currentNode().textValue();
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return String.valueOf(currentNode().numberValue());
            case VALUE_EMBEDDED_OBJECT:
                JsonNode currentNode = currentNode();
                if (currentNode != null && currentNode.isBinary()) {
                    return currentNode.asText();
                }
        }
        if (this._currToken == null) {
            asString = null;
        } else {
            asString = this._currToken.asString();
        }
        return asString;
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        return getText().toCharArray();
    }

    public int getTextLength() throws IOException, JsonParseException {
        return getText().length();
    }

    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        JsonNode currentNumericNode = currentNumericNode();
        return currentNumericNode == null ? null : currentNumericNode.numberType();
    }

    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return currentNumericNode().bigIntegerValue();
    }

    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return currentNumericNode().decimalValue();
    }

    public double getDoubleValue() throws IOException, JsonParseException {
        return currentNumericNode().doubleValue();
    }

    public float getFloatValue() throws IOException, JsonParseException {
        return (float) currentNumericNode().doubleValue();
    }

    public long getLongValue() throws IOException, JsonParseException {
        return currentNumericNode().longValue();
    }

    public int getIntValue() throws IOException, JsonParseException {
        return currentNumericNode().intValue();
    }

    public Number getNumberValue() throws IOException, JsonParseException {
        return currentNumericNode().numberValue();
    }

    public Object getEmbeddedObject() {
        JsonNode currentNode;
        if (!this._closed && (currentNode = currentNode()) != null) {
            if (currentNode.isPojo()) {
                return ((POJONode) currentNode).getPojo();
            }
            if (currentNode.isBinary()) {
                return ((BinaryNode) currentNode).binaryValue();
            }
        }
        return null;
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        Base64Variant base64Variant2 = base64Variant;
        JsonNode currentNode = currentNode();
        if (currentNode != null) {
            byte[] binaryValue = currentNode.binaryValue();
            if (binaryValue != null) {
                return binaryValue;
            }
            if (currentNode.isPojo()) {
                Object pojo = ((POJONode) currentNode).getPojo();
                if (pojo instanceof byte[]) {
                    return (byte[]) pojo;
                }
            }
        }
        return null;
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
    public JsonNode currentNode() {
        if (this._closed || this._nodeCursor == null) {
            return null;
        }
        return this._nodeCursor.currentNode();
    }

    /* access modifiers changed from: protected */
    public JsonNode currentNumericNode() throws JsonParseException {
        StringBuilder sb;
        JsonNode currentNode = currentNode();
        if (currentNode != null && currentNode.isNumber()) {
            return currentNode;
        }
        JsonToken asToken = currentNode == null ? null : currentNode.asToken();
        new StringBuilder();
        throw _constructError(sb.append("Current token (").append(asToken).append(") not numeric, can not use numeric value accessors").toString());
    }

    /* access modifiers changed from: protected */
    public void _handleEOF() throws JsonParseException {
        _throwInternal();
    }
}
