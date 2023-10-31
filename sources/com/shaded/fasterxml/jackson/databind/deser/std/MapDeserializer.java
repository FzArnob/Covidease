package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = -3378654289961736240L;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final boolean _hasDefaultCreator;
    protected HashSet<String> _ignorableProperties;
    protected final KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected boolean _standardStringKey;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(Map.class);
        JavaType javaType2 = javaType;
        ValueInstantiator valueInstantiator2 = valueInstantiator;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        this._mapType = javaType2;
        this._keyDeserializer = keyDeserializer2;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator2;
        this._hasDefaultCreator = valueInstantiator2.canCreateUsingDefault();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = _isStdKeyDeser(javaType2, keyDeserializer2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapDeserializer(com.shaded.fasterxml.jackson.databind.deser.std.MapDeserializer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            java.lang.Class r3 = r3._valueClass
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r3 = r3._mapType
            r2._mapType = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.KeyDeserializer r3 = r3._keyDeserializer
            r2._keyDeserializer = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r3 = r3._valueDeserializer
            r2._valueDeserializer = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer r3 = r3._valueTypeDeserializer
            r2._valueTypeDeserializer = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r3 = r3._valueInstantiator
            r2._valueInstantiator = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r3 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r3 = r3._delegateDeserializer
            r2._delegateDeserializer = r3
            r2 = r0
            r3 = r1
            boolean r3 = r3._hasDefaultCreator
            r2._hasDefaultCreator = r3
            r2 = r0
            r3 = r1
            java.util.HashSet<java.lang.String> r3 = r3._ignorableProperties
            r2._ignorableProperties = r3
            r2 = r0
            r3 = r1
            boolean r3 = r3._standardStringKey
            r2._standardStringKey = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.MapDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.std.MapDeserializer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapDeserializer(com.shaded.fasterxml.jackson.databind.deser.std.MapDeserializer r11, com.shaded.fasterxml.jackson.databind.KeyDeserializer r12, com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r13, com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer r14, java.util.HashSet<java.lang.String> r15) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r0
            r7 = r1
            java.lang.Class r7 = r7._valueClass
            r6.<init>(r7)
            r6 = r0
            r7 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r7 = r7._mapType
            r6._mapType = r7
            r6 = r0
            r7 = r2
            r6._keyDeserializer = r7
            r6 = r0
            r7 = r3
            r6._valueDeserializer = r7
            r6 = r0
            r7 = r4
            r6._valueTypeDeserializer = r7
            r6 = r0
            r7 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r7 = r7._valueInstantiator
            r6._valueInstantiator = r7
            r6 = r0
            r7 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r7 = r7._propertyBasedCreator
            r6._propertyBasedCreator = r7
            r6 = r0
            r7 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r7 = r7._delegateDeserializer
            r6._delegateDeserializer = r7
            r6 = r0
            r7 = r1
            boolean r7 = r7._hasDefaultCreator
            r6._hasDefaultCreator = r7
            r6 = r0
            r7 = r5
            r6._ignorableProperties = r7
            r6 = r0
            r7 = r0
            r8 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r8 = r8._mapType
            r9 = r2
            boolean r7 = r7._isStdKeyDeser(r8, r9)
            r6._standardStringKey = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.MapDeserializer.<init>(com.shaded.fasterxml.jackson.databind.deser.std.MapDeserializer, com.shaded.fasterxml.jackson.databind.KeyDeserializer, com.shaded.fasterxml.jackson.databind.JsonDeserializer, com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer, java.util.HashSet):void");
    }

    /* access modifiers changed from: protected */
    public MapDeserializer withResolved(KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, HashSet<String> hashSet) {
        MapDeserializer mapDeserializer;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        HashSet<String> hashSet2 = hashSet;
        if (this._keyDeserializer == keyDeserializer2 && this._valueDeserializer == jsonDeserializer2 && this._valueTypeDeserializer == typeDeserializer2 && this._ignorableProperties == hashSet2) {
            return this;
        }
        new MapDeserializer(this, keyDeserializer2, (JsonDeserializer<Object>) jsonDeserializer2, typeDeserializer2, hashSet2);
        return mapDeserializer;
    }

    /* access modifiers changed from: protected */
    public final boolean _isStdKeyDeser(JavaType javaType, KeyDeserializer keyDeserializer) {
        JavaType javaType2 = javaType;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        if (keyDeserializer2 == null) {
            return true;
        }
        JavaType keyType = javaType2.getKeyType();
        if (keyType == null) {
            return true;
        }
        Class<?> rawClass = keyType.getRawClass();
        return (rawClass == String.class || rawClass == Object.class) && isDefaultKeyDeserializer(keyDeserializer2);
    }

    public void setIgnorableProperties(String[] strArr) {
        String[] strArr2 = strArr;
        this._ignorableProperties = (strArr2 == null || strArr2.length == 0) ? null : ArrayBuilders.arrayToSet(strArr2);
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext2.getConfig());
            if (delegateType == null) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Invalid delegate-creator definition for ").append(this._mapType).append(": value instantiator (").append(this._valueInstantiator.getClass().getName()).append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'").toString());
                throw th2;
            }
            this._delegateDeserializer = findDeserializer(deserializationContext2, delegateType, (BeanProperty) null);
        }
        if (this._valueInstantiator.canCreateFromObjectWith()) {
            this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext2, this._valueInstantiator, this._valueInstantiator.getFromObjectArguments(deserializationContext2.getConfig()));
        }
        this._standardStringKey = _isStdKeyDeser(this._mapType, this._keyDeserializer);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        String[] findPropertiesToIgnore;
        HashSet<String> hashSet;
        HashSet<String> hashSet2;
        HashSet<String> hashSet3;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        if (keyDeserializer == null) {
            keyDeserializer = deserializationContext2.findKeyDeserializer(this._mapType.getKeyType(), beanProperty2);
        } else if (keyDeserializer instanceof ContextualKeyDeserializer) {
            keyDeserializer = ((ContextualKeyDeserializer) keyDeserializer).createContextual(deserializationContext2, beanProperty2);
        }
        JsonDeserializer<Object> findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext2, beanProperty2, this._valueDeserializer);
        if (findConvertingContentDeserializer == null) {
            findConvertingContentDeserializer = deserializationContext2.findContextualValueDeserializer(this._mapType.getContentType(), beanProperty2);
        } else if (findConvertingContentDeserializer instanceof ContextualDeserializer) {
            findConvertingContentDeserializer = ((ContextualDeserializer) findConvertingContentDeserializer).createContextual(deserializationContext2, beanProperty2);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty2);
        }
        HashSet<String> hashSet4 = this._ignorableProperties;
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        if (!(annotationIntrospector == null || beanProperty2 == null || (findPropertiesToIgnore = annotationIntrospector.findPropertiesToIgnore(beanProperty2.getMember())) == null)) {
            if (hashSet4 == null) {
                hashSet2 = hashSet3;
                new HashSet<>();
            } else {
                hashSet2 = hashSet;
                new HashSet<>(hashSet4);
            }
            hashSet4 = hashSet2;
            String[] strArr = findPropertiesToIgnore;
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                boolean add = hashSet4.add(strArr[i]);
            }
        }
        return withResolved(keyDeserializer, typeDeserializer, findConvertingContentDeserializer, hashSet4);
    }

    public JavaType getContentType() {
        return this._mapType.getContentType();
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingCreator(jsonParser2, deserializationContext2);
        }
        if (this._delegateDeserializer != null) {
            return (Map) this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        }
        if (!this._hasDefaultCreator) {
            throw deserializationContext2.instantiationException(getMapClass(), "No default constructor found");
        }
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.FIELD_NAME || currentToken == JsonToken.END_OBJECT) {
            Map<Object, Object> map = (Map) this._valueInstantiator.createUsingDefault(deserializationContext2);
            if (this._standardStringKey) {
                _readAndBindStringMap(jsonParser2, deserializationContext2, map);
                return map;
            }
            _readAndBind(jsonParser2, deserializationContext2, map);
            return map;
        } else if (currentToken == JsonToken.VALUE_STRING) {
            return (Map) this._valueInstantiator.createFromString(deserializationContext2, jsonParser2.getText());
        } else {
            throw deserializationContext2.mappingException(getMapClass());
        }
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Map<Object, Object> map2 = map;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.FIELD_NAME) {
            throw deserializationContext2.mappingException(getMapClass());
        } else if (this._standardStringKey) {
            _readAndBindStringMap(jsonParser2, deserializationContext2, map2);
            return map2;
        } else {
            _readAndBind(jsonParser2, deserializationContext2, map2);
            return map2;
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public final Class<?> getMapClass() {
        return this._mapType.getRawClass();
    }

    public JavaType getValueType() {
        return this._mapType;
    }

    /* access modifiers changed from: protected */
    public final void _readAndBind(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Map<Object, Object> map2 = map;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            Object deserializeKey = keyDeserializer.deserializeKey(currentName, deserializationContext2);
            JsonToken nextToken = jsonParser2.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                if (nextToken == JsonToken.VALUE_NULL) {
                    deserializeWithType = null;
                } else if (typeDeserializer == null) {
                    deserializeWithType = jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
                } else {
                    deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
                }
                Object put = map2.put(deserializeKey, deserializeWithType);
            } else {
                JsonParser skipChildren = jsonParser2.skipChildren();
            }
            currentToken = jsonParser2.nextToken();
        }
    }

    /* access modifiers changed from: protected */
    public final void _readAndBindStringMap(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Map<Object, Object> map2 = map;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                if (nextToken == JsonToken.VALUE_NULL) {
                    deserializeWithType = null;
                } else if (typeDeserializer == null) {
                    deserializeWithType = jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
                } else {
                    deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
                }
                Object put = map2.put(currentName, deserializeWithType);
            } else {
                JsonParser skipChildren = jsonParser2.skipChildren();
            }
            currentToken = jsonParser2.nextToken();
        }
    }

    public Map<Object, Object> _deserializeUsingCreator(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object deserializeWithType;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser2, deserializationContext2, (ObjectIdReader) null);
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser2.nextToken();
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser2.getCurrentName();
            JsonToken nextToken = jsonParser2.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
                if (findCreatorProperty != null) {
                    if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser2, deserializationContext2))) {
                        JsonToken nextToken2 = jsonParser2.nextToken();
                        try {
                            Map<Object, Object> map = (Map) propertyBasedCreator.build(deserializationContext2, startBuilding);
                            _readAndBind(jsonParser2, deserializationContext2, map);
                            return map;
                        } catch (Exception e) {
                            wrapAndThrow(e, this._mapType.getRawClass());
                            return null;
                        }
                    }
                } else {
                    Object deserializeKey = this._keyDeserializer.deserializeKey(jsonParser2.getCurrentName(), deserializationContext2);
                    if (nextToken == JsonToken.VALUE_NULL) {
                        deserializeWithType = null;
                    } else if (typeDeserializer == null) {
                        deserializeWithType = jsonDeserializer.deserialize(jsonParser2, deserializationContext2);
                    } else {
                        deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser2, deserializationContext2, typeDeserializer);
                    }
                    startBuilding.bufferMapProperty(deserializeKey, deserializeWithType);
                }
            } else {
                JsonParser skipChildren = jsonParser2.skipChildren();
            }
            currentToken = jsonParser2.nextToken();
        }
        try {
            return (Map) propertyBasedCreator.build(deserializationContext2, startBuilding);
        } catch (Exception e2) {
            wrapAndThrow(e2, this._mapType.getRawClass());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void wrapAndThrow(Throwable th, Object obj) throws IOException {
        Throwable th2 = th;
        Object obj2 = obj;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        } else if (!(th2 instanceof IOException) || (th2 instanceof JsonMappingException)) {
            throw JsonMappingException.wrapWithPath(th2, obj2, (String) null);
        } else {
            throw ((IOException) th2);
        }
    }
}
