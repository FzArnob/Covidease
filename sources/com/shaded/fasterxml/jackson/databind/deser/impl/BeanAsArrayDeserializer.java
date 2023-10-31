package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.HashSet;

public class BeanAsArrayDeserializer extends BeanDeserializerBase {
    private static final long serialVersionUID = 1;
    protected final BeanDeserializerBase _delegate;
    protected final SettableBeanProperty[] _orderedProperties;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanAsArrayDeserializer(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase r6, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty[] r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            r3._delegate = r4
            r3 = r0
            r4 = r2
            r3._orderedProperties = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty[]):void");
    }

    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        return this._delegate.unwrappingDeserializer(nameTransformer);
    }

    public BeanAsArrayDeserializer withObjectIdReader(ObjectIdReader objectIdReader) {
        BeanAsArrayDeserializer beanAsArrayDeserializer;
        new BeanAsArrayDeserializer(this._delegate.withObjectIdReader(objectIdReader), this._orderedProperties);
        return beanAsArrayDeserializer;
    }

    public BeanAsArrayDeserializer withIgnorableProperties(HashSet<String> hashSet) {
        BeanAsArrayDeserializer beanAsArrayDeserializer;
        new BeanAsArrayDeserializer(this._delegate.withIgnorableProperties(hashSet), this._orderedProperties);
        return beanAsArrayDeserializer;
    }

    /* access modifiers changed from: protected */
    public BeanDeserializerBase asArrayDeserializer() {
        return this;
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParser2.getCurrentToken() != JsonToken.START_ARRAY) {
            return _deserializeFromNonArray(jsonParser2, deserializationContext2);
        }
        if (!this._vanillaProcessing) {
            return _deserializeNonVanilla(jsonParser2, deserializationContext2);
        }
        Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext2);
        SettableBeanProperty[] settableBeanPropertyArr = this._orderedProperties;
        int i = 0;
        int length = settableBeanPropertyArr.length;
        while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
            if (i != length) {
                SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
                if (settableBeanProperty != null) {
                    try {
                        settableBeanProperty.deserializeAndSet(jsonParser2, deserializationContext2, createUsingDefault);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, createUsingDefault, settableBeanProperty.getName(), deserializationContext2);
                    }
                } else {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                }
                i++;
            } else if (!this._ignoreAllUnknown) {
                new StringBuilder();
                throw deserializationContext2.mappingException(sb.append("Unexpected JSON values; expected at most ").append(length).append(" properties (in JSON Array)").toString());
            } else {
                while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                    JsonParser skipChildren2 = jsonParser2.skipChildren();
                }
                return createUsingDefault;
            }
        }
        return createUsingDefault;
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        if (this._injectables != null) {
            injectValues(deserializationContext2, obj2);
        }
        SettableBeanProperty[] settableBeanPropertyArr = this._orderedProperties;
        int i = 0;
        int length = settableBeanPropertyArr.length;
        while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
            if (i != length) {
                SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
                if (settableBeanProperty != null) {
                    try {
                        settableBeanProperty.deserializeAndSet(jsonParser2, deserializationContext2, obj2);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, obj2, settableBeanProperty.getName(), deserializationContext2);
                    }
                } else {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                }
                i++;
            } else if (!this._ignoreAllUnknown) {
                new StringBuilder();
                throw deserializationContext2.mappingException(sb.append("Unexpected JSON values; expected at most ").append(length).append(" properties (in JSON Array)").toString());
            } else {
                while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                    JsonParser skipChildren2 = jsonParser2.skipChildren();
                }
                return obj2;
            }
        }
        return obj2;
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserializeFromNonArray(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public Object _deserializeNonVanilla(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._nonStandardCreation) {
            return _deserializeWithCreator(jsonParser2, deserializationContext2);
        }
        Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext2);
        if (this._injectables != null) {
            injectValues(deserializationContext2, createUsingDefault);
        }
        Class<?> activeView = this._needViewProcesing ? deserializationContext2.getActiveView() : null;
        SettableBeanProperty[] settableBeanPropertyArr = this._orderedProperties;
        int i = 0;
        int length = settableBeanPropertyArr.length;
        while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
            if (i != length) {
                SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
                i++;
                if (settableBeanProperty == null || (activeView != null && !settableBeanProperty.visibleInView(activeView))) {
                    JsonParser skipChildren = jsonParser2.skipChildren();
                } else {
                    try {
                        settableBeanProperty.deserializeAndSet(jsonParser2, deserializationContext2, createUsingDefault);
                    } catch (Exception e) {
                        wrapAndThrow((Throwable) e, createUsingDefault, settableBeanProperty.getName(), deserializationContext2);
                    }
                }
            } else if (!this._ignoreAllUnknown) {
                new StringBuilder();
                throw deserializationContext2.mappingException(sb.append("Unexpected JSON values; expected at most ").append(length).append(" properties (in JSON Array)").toString());
            } else {
                while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                    JsonParser skipChildren2 = jsonParser2.skipChildren();
                }
                return createUsingDefault;
            }
        }
        return createUsingDefault;
    }

    /* access modifiers changed from: protected */
    public Object _deserializeWithCreator(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        StringBuilder sb2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        }
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser2, deserializationContext2);
        }
        if (this._beanType.isAbstract()) {
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb2.append("Can not instantiate abstract type ").append(this._beanType).append(" (need to add/enable type information?)").toString());
        }
        new StringBuilder();
        throw JsonMappingException.from(jsonParser2, sb.append("No suitable constructor found for type ").append(this._beanType).append(": can not instantiate from JSON object (need to add/enable type information?)").toString());
    }

    /* access modifiers changed from: protected */
    public final Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser2, deserializationContext2, this._objectIdReader);
        SettableBeanProperty[] settableBeanPropertyArr = this._orderedProperties;
        int length = settableBeanPropertyArr.length;
        int i = 0;
        Object obj = null;
        while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
            SettableBeanProperty settableBeanProperty = i < length ? settableBeanPropertyArr[i] : null;
            if (settableBeanProperty == null) {
                JsonParser skipChildren = jsonParser2.skipChildren();
            } else if (obj != null) {
                try {
                    settableBeanProperty.deserializeAndSet(jsonParser2, deserializationContext2, obj);
                } catch (Exception e) {
                    wrapAndThrow((Throwable) e, obj, settableBeanProperty.getName(), deserializationContext2);
                }
            } else {
                String name = settableBeanProperty.getName();
                SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(name);
                if (findCreatorProperty != null) {
                    if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser2, deserializationContext2))) {
                        try {
                            obj = propertyBasedCreator.build(deserializationContext2, startBuilding);
                            if (obj.getClass() != this._beanType.getRawClass()) {
                                new StringBuilder();
                                throw deserializationContext2.mappingException(sb.append("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ").append(this._beanType.getRawClass().getName()).append(", actual type ").append(obj.getClass().getName()).toString());
                            }
                        } catch (Exception e2) {
                            wrapAndThrow((Throwable) e2, (Object) this._beanType.getRawClass(), name, deserializationContext2);
                        }
                    } else {
                        continue;
                    }
                } else if (!startBuilding.readIdProperty(name)) {
                    startBuilding.bufferProperty(settableBeanProperty, settableBeanProperty.deserialize(jsonParser2, deserializationContext2));
                }
            }
            i++;
        }
        if (obj == null) {
            try {
                obj = propertyBasedCreator.build(deserializationContext2, startBuilding);
            } catch (Exception e3) {
                wrapInstantiationProblem(e3, deserializationContext2);
                return null;
            }
        }
        return obj;
    }

    /* access modifiers changed from: protected */
    public Object _deserializeFromNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        new StringBuilder();
        throw deserializationContext.mappingException(sb.append("Can not deserialize a POJO (of type ").append(this._beanType.getRawClass().getName()).append(") from non-Array representation (token: ").append(jsonParser.getCurrentToken()).append("): type/property designed to be serialized as JSON Array").toString());
    }
}
