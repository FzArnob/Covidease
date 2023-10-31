package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.shaded.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeanDeserializer(BeanDeserializerBuilder beanDeserializerBuilder, BeanDescription beanDescription, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(beanDeserializerBuilder, beanDescription, beanPropertyMap, map, hashSet, z, z2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanDeserializer(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            boolean r4 = r4._ignoreAllUnknown
            r2.<init>((com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase) r3, (boolean) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanDeserializer(BeanDeserializerBase beanDeserializerBase, boolean z) {
        super(beanDeserializerBase, z);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanDeserializer(BeanDeserializerBase beanDeserializerBase, NameTransformer nameTransformer) {
        super(beanDeserializerBase, nameTransformer);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, ObjectIdReader objectIdReader) {
        super(beanDeserializerBase, objectIdReader);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, HashSet<String> hashSet) {
        super(beanDeserializerBase, hashSet);
    }

    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        JsonDeserializer<Object> jsonDeserializer;
        NameTransformer nameTransformer2 = nameTransformer;
        if (getClass() != BeanDeserializer.class) {
            return this;
        }
        new BeanDeserializer((BeanDeserializerBase) this, nameTransformer2);
        return jsonDeserializer;
    }

    public BeanDeserializer withObjectIdReader(ObjectIdReader objectIdReader) {
        BeanDeserializer beanDeserializer;
        new BeanDeserializer((BeanDeserializerBase) this, objectIdReader);
        return beanDeserializer;
    }

    public BeanDeserializer withIgnorableProperties(HashSet<String> hashSet) {
        BeanDeserializer beanDeserializer;
        new BeanDeserializer((BeanDeserializerBase) this, hashSet);
        return beanDeserializer;
    }

    /* access modifiers changed from: protected */
    public BeanDeserializerBase asArrayDeserializer() {
        BeanDeserializerBase beanDeserializerBase;
        new BeanAsArrayDeserializer(this, this._beanProperties.getPropertiesInInsertionOrder());
        return beanDeserializerBase;
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT) {
            return _deserializeOther(jsonParser2, deserializationContext2, currentToken);
        }
        if (this._vanillaProcessing) {
            return vanillaDeserialize(jsonParser2, deserializationContext2, jsonParser2.nextToken());
        }
        JsonToken nextToken = jsonParser2.nextToken();
        if (this._objectIdReader != null) {
            return deserializeWithObjectId(jsonParser2, deserializationContext2);
        }
        return deserializeFromObject(jsonParser2, deserializationContext2);
    }

    private final Object _deserializeOther(JsonParser jsonParser, DeserializationContext deserializationContext, JsonToken jsonToken) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonToken jsonToken2 = jsonToken;
        if (jsonToken2 == null) {
            return _missingToken(jsonParser2, deserializationContext2);
        }
        switch (jsonToken2) {
            case VALUE_STRING:
                return deserializeFromString(jsonParser2, deserializationContext2);
            case VALUE_NUMBER_INT:
                return deserializeFromNumber(jsonParser2, deserializationContext2);
            case VALUE_NUMBER_FLOAT:
                return deserializeFromDouble(jsonParser2, deserializationContext2);
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser2.getEmbeddedObject();
            case VALUE_TRUE:
            case VALUE_FALSE:
                return deserializeFromBoolean(jsonParser2, deserializationContext2);
            case START_ARRAY:
                return deserializeFromArray(jsonParser2, deserializationContext2);
            case FIELD_NAME:
            case END_OBJECT:
                if (this._vanillaProcessing) {
                    return vanillaDeserialize(jsonParser2, deserializationContext2, jsonToken2);
                }
                if (this._objectIdReader != null) {
                    return deserializeWithObjectId(jsonParser2, deserializationContext2);
                }
                return deserializeFromObject(jsonParser2, deserializationContext2);
            default:
                throw deserializationContext2.mappingException(getBeanClass());
        }
    }

    /* access modifiers changed from: protected */
    public Object _missingToken(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        throw deserializationContext.endOfInputException(getBeanClass());
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        Class<?> activeView;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        if (this._injectables != null) {
            injectValues(deserializationContext2, obj2);
        }
        if (this._unwrappedPropertyHandler != null) {
            return deserializeWithUnwrapped(jsonParser2, deserializationContext2, obj2);
        }
        if (this._externalTypeIdHandler != null) {
            return deserializeWithExternalTypeId(jsonParser2, deserializationContext2, obj2);
        }
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        if (this._needViewProcesing && (activeView = deserializationContext2.getActiveView()) != null) {
            return deserializeWithView(jsonParser2, deserializationContext2, obj2, activeView);
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser2, deserializationContext2, obj2);
                } catch (Exception e) {
                    wrapAndThrow((Throwable) e, obj2, currentName, deserializationContext2);
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                JsonParser skipChildren = jsonParser2.skipChildren();
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(jsonParser2, deserializationContext2, obj2, currentName);
            } else {
                handleUnknownProperty(jsonParser2, deserializationContext2, obj2, currentName);
            }
            currentToken = jsonParser2.nextToken();
        }
        return obj2;
    }

    private final Object vanillaDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, JsonToken jsonToken) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonToken jsonToken2 = jsonToken;
        Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext2);
        while (jsonParser2.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser2, deserializationContext2, createUsingDefault);
                } catch (Exception e) {
                    wrapAndThrow((Throwable) e, createUsingDefault, currentName, deserializationContext2);
                }
            } else {
                handleUnknownVanilla(jsonParser2, deserializationContext2, createUsingDefault, currentName);
            }
            JsonToken nextToken2 = jsonParser2.nextToken();
        }
        return createUsingDefault;
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Class<?> activeView;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (!this._nonStandardCreation) {
            Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext2);
            if (this._injectables != null) {
                injectValues(deserializationContext2, createUsingDefault);
            }
            if (this._needViewProcesing && (activeView = deserializationContext2.getActiveView()) != null) {
                return deserializeWithView(jsonParser2, deserializationContext2, createUsingDefault, activeView);
            }
            while (jsonParser2.getCurrentToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser2.getCurrentName();
                JsonToken nextToken = jsonParser2.nextToken();
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    try {
                        find.deserializeAndSet(jsonParser2, deserializationContext2, createUsingDefault);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, createUsingDefault, currentName, deserializationContext2);
                    }
                } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                } else if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(jsonParser2, deserializationContext2, createUsingDefault, currentName);
                    } catch (Exception e2) {
                        wrapAndThrow((Throwable) e2, createUsingDefault, currentName, deserializationContext2);
                    }
                } else {
                    handleUnknownProperty(jsonParser2, deserializationContext2, createUsingDefault, currentName);
                }
                JsonToken nextToken2 = jsonParser2.nextToken();
            }
            return createUsingDefault;
        } else if (this._unwrappedPropertyHandler != null) {
            return deserializeWithUnwrapped(jsonParser2, deserializationContext2);
        } else {
            if (this._externalTypeIdHandler != null) {
                return deserializeWithExternalTypeId(jsonParser2, deserializationContext2);
            }
            return deserializeFromObjectUsingNonDefault(jsonParser2, deserializationContext2);
        }
    }

    /* access modifiers changed from: protected */
    public Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object obj;
        TokenBuffer tokenBuffer;
        Object obj2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser2, deserializationContext2, this._objectIdReader);
        TokenBuffer tokenBuffer2 = null;
        for (JsonToken currentToken = jsonParser2.getCurrentToken(); currentToken == JsonToken.FIELD_NAME; currentToken = jsonParser2.nextToken()) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser2, deserializationContext2))) {
                    JsonToken nextToken2 = jsonParser2.nextToken();
                    try {
                        obj2 = propertyBasedCreator.build(deserializationContext2, startBuilding);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, (Object) this._beanType.getRawClass(), currentName, deserializationContext2);
                        obj2 = null;
                    }
                    if (obj2.getClass() != this._beanType.getRawClass()) {
                        return handlePolymorphic(jsonParser2, deserializationContext2, obj2, tokenBuffer2);
                    }
                    if (tokenBuffer2 != null) {
                        obj2 = handleUnknownProperties(deserializationContext2, obj2, tokenBuffer2);
                    }
                    return deserialize(jsonParser2, deserializationContext2, obj2);
                }
            } else if (!startBuilding.readIdProperty(currentName)) {
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    startBuilding.bufferProperty(find, find.deserialize(jsonParser2, deserializationContext2));
                } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                } else if (this._anySetter != null) {
                    startBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser2, deserializationContext2));
                } else {
                    if (tokenBuffer2 == null) {
                        new TokenBuffer(jsonParser2.getCodec());
                        tokenBuffer2 = tokenBuffer;
                    }
                    tokenBuffer2.writeFieldName(currentName);
                    tokenBuffer2.copyCurrentStructure(jsonParser2);
                }
            }
        }
        try {
            obj = propertyBasedCreator.build(deserializationContext2, startBuilding);
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext2);
            obj = null;
        }
        if (tokenBuffer2 == null) {
            return obj;
        }
        if (obj.getClass() != this._beanType.getRawClass()) {
            return handlePolymorphic((JsonParser) null, deserializationContext2, obj, tokenBuffer2);
        }
        return handleUnknownProperties(deserializationContext2, obj, tokenBuffer2);
    }

    /* access modifiers changed from: protected */
    public final Object deserializeWithView(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, Class<?> cls) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        Class<?> cls2 = cls;
        for (JsonToken currentToken = jsonParser2.getCurrentToken(); currentToken == JsonToken.FIELD_NAME; currentToken = jsonParser2.nextToken()) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                if (!find.visibleInView(cls2)) {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                } else {
                    try {
                        find.deserializeAndSet(jsonParser2, deserializationContext2, obj2);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, obj2, currentName, deserializationContext2);
                    }
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                JsonParser skipChildren2 = jsonParser2.skipChildren();
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(jsonParser2, deserializationContext2, obj2, currentName);
            } else {
                handleUnknownProperty(jsonParser2, deserializationContext2, obj2, currentName);
            }
        }
        return obj2;
    }

    /* access modifiers changed from: protected */
    public Object deserializeWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        }
        if (this._propertyBasedCreator != null) {
            return deserializeUsingPropertyBasedWithUnwrapped(jsonParser2, deserializationContext2);
        }
        new TokenBuffer(jsonParser2.getCodec());
        TokenBuffer tokenBuffer2 = tokenBuffer;
        tokenBuffer2.writeStartObject();
        Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext2);
        if (this._injectables != null) {
            injectValues(deserializationContext2, createUsingDefault);
        }
        Class<?> activeView = this._needViewProcesing ? deserializationContext2.getActiveView() : null;
        while (jsonParser2.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                if (activeView == null || find.visibleInView(activeView)) {
                    try {
                        find.deserializeAndSet(jsonParser2, deserializationContext2, createUsingDefault);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, createUsingDefault, currentName, deserializationContext2);
                    }
                } else {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                }
            } else if (this._ignorableProps == null || !this._ignorableProps.contains(currentName)) {
                tokenBuffer2.writeFieldName(currentName);
                tokenBuffer2.copyCurrentStructure(jsonParser2);
                if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(jsonParser2, deserializationContext2, createUsingDefault, currentName);
                    } catch (Exception e2) {
                        wrapAndThrow((Throwable) e2, createUsingDefault, currentName, deserializationContext2);
                    }
                }
            } else {
                JsonParser skipChildren2 = jsonParser2.skipChildren();
            }
            JsonToken nextToken2 = jsonParser2.nextToken();
        }
        tokenBuffer2.writeEndObject();
        Object processUnwrapped = this._unwrappedPropertyHandler.processUnwrapped(jsonParser2, deserializationContext2, createUsingDefault, tokenBuffer2);
        return createUsingDefault;
    }

    /* access modifiers changed from: protected */
    public Object deserializeWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        new TokenBuffer(jsonParser2.getCodec());
        TokenBuffer tokenBuffer2 = tokenBuffer;
        tokenBuffer2.writeStartObject();
        Class<?> activeView = this._needViewProcesing ? deserializationContext2.getActiveView() : null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            JsonToken nextToken = jsonParser2.nextToken();
            if (find != null) {
                if (activeView == null || find.visibleInView(activeView)) {
                    try {
                        find.deserializeAndSet(jsonParser2, deserializationContext2, obj2);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, obj2, currentName, deserializationContext2);
                    }
                } else {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                }
            } else if (this._ignorableProps == null || !this._ignorableProps.contains(currentName)) {
                tokenBuffer2.writeFieldName(currentName);
                tokenBuffer2.copyCurrentStructure(jsonParser2);
                if (this._anySetter != null) {
                    this._anySetter.deserializeAndSet(jsonParser2, deserializationContext2, obj2, currentName);
                }
            } else {
                JsonParser skipChildren2 = jsonParser2.skipChildren();
            }
            currentToken = jsonParser2.nextToken();
        }
        tokenBuffer2.writeEndObject();
        Object processUnwrapped = this._unwrappedPropertyHandler.processUnwrapped(jsonParser2, deserializationContext2, obj2, tokenBuffer2);
        return obj2;
    }

    /* access modifiers changed from: protected */
    public Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser2, deserializationContext2, this._objectIdReader);
        new TokenBuffer(jsonParser2.getCodec());
        TokenBuffer tokenBuffer2 = tokenBuffer;
        tokenBuffer2.writeStartObject();
        for (JsonToken currentToken = jsonParser2.getCurrentToken(); currentToken == JsonToken.FIELD_NAME; currentToken = jsonParser2.nextToken()) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser2, deserializationContext2))) {
                    try {
                        Object build = propertyBasedCreator.build(deserializationContext2, startBuilding);
                        for (JsonToken nextToken2 = jsonParser2.nextToken(); nextToken2 == JsonToken.FIELD_NAME; nextToken2 = jsonParser2.nextToken()) {
                            JsonToken nextToken3 = jsonParser2.nextToken();
                            tokenBuffer2.copyCurrentStructure(jsonParser2);
                        }
                        tokenBuffer2.writeEndObject();
                        if (build.getClass() == this._beanType.getRawClass()) {
                            return this._unwrappedPropertyHandler.processUnwrapped(jsonParser2, deserializationContext2, build, tokenBuffer2);
                        }
                        tokenBuffer2.close();
                        throw deserializationContext2.mappingException("Can not create polymorphic instances with unwrapped values");
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, (Object) this._beanType.getRawClass(), currentName, deserializationContext2);
                    }
                } else {
                    continue;
                }
            } else if (!startBuilding.readIdProperty(currentName)) {
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    startBuilding.bufferProperty(find, find.deserialize(jsonParser2, deserializationContext2));
                } else if (this._ignorableProps == null || !this._ignorableProps.contains(currentName)) {
                    tokenBuffer2.writeFieldName(currentName);
                    tokenBuffer2.copyCurrentStructure(jsonParser2);
                    if (this._anySetter != null) {
                        startBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser2, deserializationContext2));
                    }
                } else {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                }
            }
        }
        try {
            return this._unwrappedPropertyHandler.processUnwrapped(jsonParser2, deserializationContext2, propertyBasedCreator.build(deserializationContext2, startBuilding), tokenBuffer2);
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Object deserializeWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._propertyBasedCreator != null) {
            return deserializeUsingPropertyBasedWithExternalTypeId(jsonParser2, deserializationContext2);
        }
        return deserializeWithExternalTypeId(jsonParser2, deserializationContext2, this._valueInstantiator.createUsingDefault(deserializationContext2));
    }

    /* access modifiers changed from: protected */
    public Object deserializeWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        Class<?> activeView = this._needViewProcesing ? deserializationContext2.getActiveView() : null;
        ExternalTypeHandler start = this._externalTypeIdHandler.start();
        while (jsonParser2.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                if (jsonParser2.getCurrentToken().isScalarValue()) {
                    boolean handleTypePropertyValue = start.handleTypePropertyValue(jsonParser2, deserializationContext2, currentName, obj2);
                }
                if (activeView == null || find.visibleInView(activeView)) {
                    try {
                        find.deserializeAndSet(jsonParser2, deserializationContext2, obj2);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, obj2, currentName, deserializationContext2);
                    }
                } else {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                JsonParser skipChildren2 = jsonParser2.skipChildren();
            } else if (!start.handlePropertyValue(jsonParser2, deserializationContext2, currentName, obj2)) {
                if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(jsonParser2, deserializationContext2, obj2, currentName);
                    } catch (Exception e2) {
                        wrapAndThrow((Throwable) e2, obj2, currentName, deserializationContext2);
                    }
                } else {
                    handleUnknownProperty(jsonParser2, deserializationContext2, obj2, currentName);
                }
            }
            JsonToken nextToken2 = jsonParser2.nextToken();
        }
        return start.complete(jsonParser2, deserializationContext2, obj2);
    }

    /* access modifiers changed from: protected */
    public Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        ExternalTypeHandler start = this._externalTypeIdHandler.start();
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser2, deserializationContext2, this._objectIdReader);
        new TokenBuffer(jsonParser2.getCodec());
        TokenBuffer tokenBuffer2 = tokenBuffer;
        tokenBuffer2.writeStartObject();
        for (JsonToken currentToken = jsonParser2.getCurrentToken(); currentToken == JsonToken.FIELD_NAME; currentToken = jsonParser2.nextToken()) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (start.handlePropertyValue(jsonParser2, deserializationContext2, currentName, startBuilding)) {
                    continue;
                } else {
                    if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser2, deserializationContext2))) {
                        try {
                            Object build = propertyBasedCreator.build(deserializationContext2, startBuilding);
                            for (JsonToken nextToken2 = jsonParser2.nextToken(); nextToken2 == JsonToken.FIELD_NAME; nextToken2 = jsonParser2.nextToken()) {
                                JsonToken nextToken3 = jsonParser2.nextToken();
                                tokenBuffer2.copyCurrentStructure(jsonParser2);
                            }
                            if (build.getClass() == this._beanType.getRawClass()) {
                                return start.complete(jsonParser2, deserializationContext2, build);
                            }
                            throw deserializationContext2.mappingException("Can not create polymorphic instances with unwrapped values");
                        } catch (Exception e) {
                            wrapAndThrow((Throwable) e, (Object) this._beanType.getRawClass(), currentName, deserializationContext2);
                        }
                    } else {
                        continue;
                    }
                }
            } else if (!startBuilding.readIdProperty(currentName)) {
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    startBuilding.bufferProperty(find, find.deserialize(jsonParser2, deserializationContext2));
                } else if (!start.handlePropertyValue(jsonParser2, deserializationContext2, currentName, (Object) null)) {
                    if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                        JsonParser skipChildren = jsonParser2.skipChildren();
                    } else if (this._anySetter != null) {
                        startBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser2, deserializationContext2));
                    }
                }
            }
        }
        try {
            return start.complete(jsonParser2, deserializationContext2, startBuilding, propertyBasedCreator);
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext2);
            return null;
        }
    }
}
