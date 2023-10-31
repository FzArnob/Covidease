package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.HashSet;

public class BuilderBasedDeserializer extends BeanDeserializerBase {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMethod _buildMethod;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BuilderBasedDeserializer(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBuilder r18, com.shaded.fasterxml.jackson.databind.BeanDescription r19, com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r20, java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> r21, java.util.HashSet<java.lang.String> r22, boolean r23, boolean r24) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r4
            r13 = r5
            r14 = r6
            r15 = r7
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            r8 = r0
            r9 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r9 = r9.getBuildMethod()
            r8._buildMethod = r9
            r8 = r0
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r8 = r8._objectIdReader
            if (r8 == 0) goto L_0x005a
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r16 = r8
            r8 = r16
            r9 = r16
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r16 = r10
            r10 = r16
            r11 = r16
            r11.<init>()
            java.lang.String r11 = "Can not use Object Id with Builder-based deserialization (type "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r2
            com.shaded.fasterxml.jackson.databind.JavaType r11 = r11.getType()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = ")"
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r8
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBuilder, com.shaded.fasterxml.jackson.databind.BeanDescription, com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap, java.util.Map, java.util.HashSet, boolean, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BuilderBasedDeserializer(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            boolean r4 = r4._ignoreAllUnknown
            r2.<init>((com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer) r3, (boolean) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BuilderBasedDeserializer(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer r7, boolean r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase) r4, (boolean) r5)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r4 = r4._buildMethod
            r3._buildMethod = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BuilderBasedDeserializer(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer r7, com.shaded.fasterxml.jackson.databind.util.NameTransformer r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase) r4, (com.shaded.fasterxml.jackson.databind.util.NameTransformer) r5)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r4 = r4._buildMethod
            r3._buildMethod = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer, com.shaded.fasterxml.jackson.databind.util.NameTransformer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BuilderBasedDeserializer(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer r7, com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase) r4, (com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader) r5)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r4 = r4._buildMethod
            r3._buildMethod = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer, com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BuilderBasedDeserializer(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer r7, java.util.HashSet<java.lang.String> r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase) r4, (java.util.HashSet<java.lang.String>) r5)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r4 = r4._buildMethod
            r3._buildMethod = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.BuilderBasedDeserializer, java.util.HashSet):void");
    }

    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        JsonDeserializer<Object> jsonDeserializer;
        new BuilderBasedDeserializer(this, nameTransformer);
        return jsonDeserializer;
    }

    public BuilderBasedDeserializer withObjectIdReader(ObjectIdReader objectIdReader) {
        BuilderBasedDeserializer builderBasedDeserializer;
        new BuilderBasedDeserializer(this, objectIdReader);
        return builderBasedDeserializer;
    }

    public BuilderBasedDeserializer withIgnorableProperties(HashSet<String> hashSet) {
        BuilderBasedDeserializer builderBasedDeserializer;
        new BuilderBasedDeserializer(this, hashSet);
        return builderBasedDeserializer;
    }

    /* access modifiers changed from: protected */
    public BeanAsArrayBuilderDeserializer asArrayDeserializer() {
        BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer;
        new BeanAsArrayBuilderDeserializer(this, this._beanProperties.getPropertiesInInsertionOrder(), this._buildMethod);
        return beanAsArrayBuilderDeserializer;
    }

    /* access modifiers changed from: protected */
    public final Object finishBuild(DeserializationContext deserializationContext, Object obj) throws IOException {
        DeserializationContext deserializationContext2 = deserializationContext;
        try {
            return this._buildMethod.getMember().invoke(obj, new Object[0]);
        } catch (Exception e) {
            wrapInstantiationProblem(e, deserializationContext2);
            return null;
        }
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            JsonToken nextToken = jsonParser2.nextToken();
            if (this._vanillaProcessing) {
                return finishBuild(deserializationContext2, vanillaDeserialize(jsonParser2, deserializationContext2, nextToken));
            }
            return finishBuild(deserializationContext2, deserializeFromObject(jsonParser2, deserializationContext2));
        }
        switch (currentToken) {
            case VALUE_STRING:
                return finishBuild(deserializationContext2, deserializeFromString(jsonParser2, deserializationContext2));
            case VALUE_NUMBER_INT:
                return finishBuild(deserializationContext2, deserializeFromNumber(jsonParser2, deserializationContext2));
            case VALUE_NUMBER_FLOAT:
                return finishBuild(deserializationContext2, deserializeFromDouble(jsonParser2, deserializationContext2));
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser2.getEmbeddedObject();
            case VALUE_TRUE:
            case VALUE_FALSE:
                return finishBuild(deserializationContext2, deserializeFromBoolean(jsonParser2, deserializationContext2));
            case START_ARRAY:
                return finishBuild(deserializationContext2, deserializeFromArray(jsonParser2, deserializationContext2));
            case FIELD_NAME:
            case END_OBJECT:
                return finishBuild(deserializationContext2, deserializeFromObject(jsonParser2, deserializationContext2));
            default:
                throw deserializationContext2.mappingException(getBeanClass());
        }
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        return finishBuild(deserializationContext2, _deserialize(jsonParser, deserializationContext2, obj));
    }

    /* access modifiers changed from: protected */
    public final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
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
        if (this._needViewProcesing && (activeView = deserializationContext2.getActiveView()) != null) {
            return deserializeWithView(jsonParser2, deserializationContext2, obj2, activeView);
        }
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    obj2 = find.deserializeSetAndReturn(jsonParser2, deserializationContext2, obj2);
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
                    createUsingDefault = find.deserializeSetAndReturn(jsonParser2, deserializationContext2, createUsingDefault);
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
                        createUsingDefault = find.deserializeSetAndReturn(jsonParser2, deserializationContext2, createUsingDefault);
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
    public final Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
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
                        Object build = propertyBasedCreator.build(deserializationContext2, startBuilding);
                        if (build.getClass() != this._beanType.getRawClass()) {
                            return handlePolymorphic(jsonParser2, deserializationContext2, build, tokenBuffer2);
                        }
                        if (tokenBuffer2 != null) {
                            build = handleUnknownProperties(deserializationContext2, build, tokenBuffer2);
                        }
                        return _deserialize(jsonParser2, deserializationContext2, build);
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
            Object build2 = propertyBasedCreator.build(deserializationContext2, startBuilding);
            if (tokenBuffer2 == null) {
                return build2;
            }
            if (build2.getClass() != this._beanType.getRawClass()) {
                return handlePolymorphic((JsonParser) null, deserializationContext2, build2, tokenBuffer2);
            }
            return handleUnknownProperties(deserializationContext2, build2, tokenBuffer2);
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext2);
            return null;
        }
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
                        obj2 = find.deserializeSetAndReturn(jsonParser2, deserializationContext2, obj2);
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
                        createUsingDefault = find.deserializeSetAndReturn(jsonParser2, deserializationContext2, createUsingDefault);
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
                        obj2 = find.deserializeSetAndReturn(jsonParser2, deserializationContext2, obj2);
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
                if (activeView == null || find.visibleInView(activeView)) {
                    try {
                        obj2 = find.deserializeSetAndReturn(jsonParser2, deserializationContext2, obj2);
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
        Throwable th;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Throwable th2 = th;
        new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
        throw th2;
    }
}
