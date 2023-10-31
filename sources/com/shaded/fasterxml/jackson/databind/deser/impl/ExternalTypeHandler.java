package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExternalTypeHandler {
    private final HashMap<String, Integer> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    protected ExternalTypeHandler(ExtTypedProperty[] extTypedPropertyArr, HashMap<String, Integer> hashMap, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = hashMap;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    protected ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        ExternalTypeHandler externalTypeHandler2 = externalTypeHandler;
        this._properties = externalTypeHandler2._properties;
        this._nameToPropertyIndex = externalTypeHandler2._nameToPropertyIndex;
        int length = this._properties.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }

    public ExternalTypeHandler start() {
        ExternalTypeHandler externalTypeHandler;
        new ExternalTypeHandler(this);
        return externalTypeHandler;
    }

    public boolean handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        String str2 = str;
        Object obj2 = obj;
        Integer num = this._nameToPropertyIndex.get(str2);
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (!this._properties[intValue].hasTypePropertyName(str2)) {
            return false;
        }
        String text = jsonParser2.getText();
        if ((obj2 == null || this._tokens[intValue] == null) ? false : true) {
            _deserializeAndSet(jsonParser2, deserializationContext2, obj2, intValue, text);
            this._tokens[intValue] = null;
        } else {
            this._typeIds[intValue] = text;
        }
        return true;
    }

    public boolean handlePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        boolean z;
        boolean z2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        String str2 = str;
        Object obj2 = obj;
        Integer num = this._nameToPropertyIndex.get(str2);
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (this._properties[intValue].hasTypePropertyName(str2)) {
            this._typeIds[intValue] = jsonParser2.getText();
            JsonParser skipChildren = jsonParser2.skipChildren();
            if (obj2 == null || this._tokens[intValue] == null) {
                z2 = false;
            } else {
                z2 = true;
            }
            z = z2;
        } else {
            new TokenBuffer(jsonParser2.getCodec());
            TokenBuffer tokenBuffer2 = tokenBuffer;
            tokenBuffer2.copyCurrentStructure(jsonParser2);
            this._tokens[intValue] = tokenBuffer2;
            z = (obj2 == null || this._typeIds[intValue] == null) ? false : true;
        }
        if (z) {
            String str3 = this._typeIds[intValue];
            this._typeIds[intValue] = null;
            _deserializeAndSet(jsonParser2, deserializationContext2, obj2, intValue, str3);
            this._tokens[intValue] = null;
        }
        return true;
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        StringBuilder sb;
        StringBuilder sb2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        int length = this._properties.length;
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            if (str == null) {
                TokenBuffer tokenBuffer = this._tokens[i];
                if (tokenBuffer != null) {
                    JsonToken firstToken = tokenBuffer.firstToken();
                    if (firstToken != null && firstToken.isScalarValue()) {
                        JsonParser asParser = tokenBuffer.asParser(jsonParser2);
                        JsonToken nextToken = asParser.nextToken();
                        SettableBeanProperty property = this._properties[i].getProperty();
                        Object deserializeIfNatural = TypeDeserializer.deserializeIfNatural(asParser, deserializationContext2, property.getType());
                        if (deserializeIfNatural != null) {
                            property.set(obj2, deserializeIfNatural);
                        } else if (!this._properties[i].hasDefaultType()) {
                            new StringBuilder();
                            throw deserializationContext2.mappingException(sb2.append("Missing external type id property '").append(this._properties[i].getTypePropertyName()).append("'").toString());
                        } else {
                            str = this._properties[i].getDefaultTypeId();
                        }
                    }
                    _deserializeAndSet(jsonParser2, deserializationContext2, obj2, i, str);
                } else {
                    continue;
                }
            } else {
                if (this._tokens[i] == null) {
                    SettableBeanProperty property2 = this._properties[i].getProperty();
                    new StringBuilder();
                    throw deserializationContext2.mappingException(sb.append("Missing property '").append(property2.getName()).append("' for external type id '").append(this._properties[i].getTypePropertyName()).toString());
                }
                _deserializeAndSet(jsonParser2, deserializationContext2, obj2, i, str);
            }
        }
        return obj2;
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer, PropertyBasedCreator propertyBasedCreator) throws IOException, JsonProcessingException {
        StringBuilder sb;
        StringBuilder sb2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        PropertyValueBuffer propertyValueBuffer2 = propertyValueBuffer;
        PropertyBasedCreator propertyBasedCreator2 = propertyBasedCreator;
        int length = this._properties.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            if (str != null) {
                if (this._tokens[i] == null) {
                    SettableBeanProperty property = this._properties[i].getProperty();
                    new StringBuilder();
                    throw deserializationContext2.mappingException(sb.append("Missing property '").append(property.getName()).append("' for external type id '").append(this._properties[i].getTypePropertyName()).toString());
                }
                objArr[i] = _deserialize(jsonParser2, deserializationContext2, i, str);
            } else if (this._tokens[i] == null) {
                continue;
            } else if (!this._properties[i].hasDefaultType()) {
                new StringBuilder();
                throw deserializationContext2.mappingException(sb2.append("Missing external type id property '").append(this._properties[i].getTypePropertyName()).append("'").toString());
            } else {
                str = this._properties[i].getDefaultTypeId();
                objArr[i] = _deserialize(jsonParser2, deserializationContext2, i, str);
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            SettableBeanProperty property2 = this._properties[i2].getProperty();
            if (propertyBasedCreator2.findCreatorProperty(property2.getName()) != null) {
                boolean assignParameter = propertyValueBuffer2.assignParameter(property2.getCreatorIndex(), objArr[i2]);
            }
        }
        Object build = propertyBasedCreator2.build(deserializationContext2, propertyValueBuffer2);
        for (int i3 = 0; i3 < length; i3++) {
            SettableBeanProperty property3 = this._properties[i3].getProperty();
            if (propertyBasedCreator2.findCreatorProperty(property3.getName()) == null) {
                property3.set(build, objArr[i3]);
            }
        }
        return build;
    }

    /* access modifiers changed from: protected */
    public final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, int i, String str) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        JsonParser jsonParser2 = jsonParser;
        int i2 = i;
        new TokenBuffer(jsonParser2.getCodec());
        TokenBuffer tokenBuffer2 = tokenBuffer;
        tokenBuffer2.writeStartArray();
        tokenBuffer2.writeString(str);
        JsonParser asParser = this._tokens[i2].asParser(jsonParser2);
        JsonToken nextToken = asParser.nextToken();
        tokenBuffer2.copyCurrentStructure(asParser);
        tokenBuffer2.writeEndArray();
        JsonParser asParser2 = tokenBuffer2.asParser(jsonParser2);
        JsonToken nextToken2 = asParser2.nextToken();
        return this._properties[i2].getProperty().deserialize(asParser2, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public final void _deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i, String str) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        JsonParser jsonParser2 = jsonParser;
        int i2 = i;
        new TokenBuffer(jsonParser2.getCodec());
        TokenBuffer tokenBuffer2 = tokenBuffer;
        tokenBuffer2.writeStartArray();
        tokenBuffer2.writeString(str);
        JsonParser asParser = this._tokens[i2].asParser(jsonParser2);
        JsonToken nextToken = asParser.nextToken();
        tokenBuffer2.copyCurrentStructure(asParser);
        tokenBuffer2.writeEndArray();
        JsonParser asParser2 = tokenBuffer2.asParser(jsonParser2);
        JsonToken nextToken2 = asParser2.nextToken();
        this._properties[i2].getProperty().deserializeAndSet(asParser2, deserializationContext, obj);
    }

    public static class Builder {
        private final HashMap<String, Integer> _nameToPropertyIndex;
        private final ArrayList<ExtTypedProperty> _properties;

        public Builder() {
            ArrayList<ExtTypedProperty> arrayList;
            HashMap<String, Integer> hashMap;
            new ArrayList<>();
            this._properties = arrayList;
            new HashMap<>();
            this._nameToPropertyIndex = hashMap;
        }

        public void addExternal(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            Object obj;
            SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
            TypeDeserializer typeDeserializer2 = typeDeserializer;
            Integer valueOf = Integer.valueOf(this._properties.size());
            new ExtTypedProperty(settableBeanProperty2, typeDeserializer2);
            boolean add = this._properties.add(obj);
            Integer put = this._nameToPropertyIndex.put(settableBeanProperty2.getName(), valueOf);
            Integer put2 = this._nameToPropertyIndex.put(typeDeserializer2.getPropertyName(), valueOf);
        }

        public ExternalTypeHandler build() {
            ExternalTypeHandler externalTypeHandler;
            new ExternalTypeHandler((ExtTypedProperty[]) this._properties.toArray(new ExtTypedProperty[this._properties.size()]), this._nameToPropertyIndex, (String[]) null, (TokenBuffer[]) null);
            return externalTypeHandler;
        }
    }

    private static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            TypeDeserializer typeDeserializer2 = typeDeserializer;
            this._property = settableBeanProperty;
            this._typeDeserializer = typeDeserializer2;
            this._typePropertyName = typeDeserializer2.getPropertyName();
        }

        public boolean hasTypePropertyName(String str) {
            return str.equals(this._typePropertyName);
        }

        public boolean hasDefaultType() {
            return this._typeDeserializer.getDefaultImpl() != null;
        }

        public String getDefaultTypeId() {
            Class<?> defaultImpl = this._typeDeserializer.getDefaultImpl();
            if (defaultImpl == null) {
                return null;
            }
            return this._typeDeserializer.getTypeIdResolver().idFromValueAndType((Object) null, defaultImpl);
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }
    }
}
