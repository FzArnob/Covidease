package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public final class PropertyBasedCreator {
    protected final Object[] _defaultValues;
    protected final HashMap<String, SettableBeanProperty> _properties;
    protected final SettableBeanProperty[] _propertiesWithInjectables;
    protected final int _propertyCount;
    protected final ValueInstantiator _valueInstantiator;

    protected PropertyBasedCreator(ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr, Object[] objArr) {
        HashMap<String, SettableBeanProperty> hashMap;
        SettableBeanProperty[] settableBeanPropertyArr2 = settableBeanPropertyArr;
        Object[] objArr2 = objArr;
        this._valueInstantiator = valueInstantiator;
        new HashMap<>();
        this._properties = hashMap;
        SettableBeanProperty[] settableBeanPropertyArr3 = null;
        int length = settableBeanPropertyArr2.length;
        this._propertyCount = length;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr2[i];
            SettableBeanProperty put = this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
            if (settableBeanProperty.getInjectableValueId() != null) {
                settableBeanPropertyArr3 = settableBeanPropertyArr3 == null ? new SettableBeanProperty[length] : settableBeanPropertyArr3;
                settableBeanPropertyArr3[i] = settableBeanProperty;
            }
        }
        this._defaultValues = objArr2;
        this._propertiesWithInjectables = settableBeanPropertyArr3;
    }

    public static PropertyBasedCreator construct(DeserializationContext deserializationContext, ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr) throws JsonMappingException {
        PropertyBasedCreator propertyBasedCreator;
        Object nullValue;
        DeserializationContext deserializationContext2 = deserializationContext;
        ValueInstantiator valueInstantiator2 = valueInstantiator;
        SettableBeanProperty[] settableBeanPropertyArr2 = settableBeanPropertyArr;
        int length = settableBeanPropertyArr2.length;
        SettableBeanProperty[] settableBeanPropertyArr3 = new SettableBeanProperty[length];
        Object[] objArr = null;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr2[i];
            if (!settableBeanProperty.hasValueDeserializer()) {
                settableBeanProperty = settableBeanProperty.withValueDeserializer(deserializationContext2.findContextualValueDeserializer(settableBeanProperty.getType(), settableBeanProperty));
            }
            settableBeanPropertyArr3[i] = settableBeanProperty;
            JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
            if (valueDeserializer == null) {
                nullValue = null;
            } else {
                nullValue = valueDeserializer.getNullValue();
            }
            Object obj = nullValue;
            if (obj == null && settableBeanProperty.getType().isPrimitive()) {
                obj = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
            }
            if (obj != null) {
                if (objArr == null) {
                    objArr = new Object[length];
                }
                objArr[i] = obj;
            }
        }
        new PropertyBasedCreator(valueInstantiator2, settableBeanPropertyArr3, objArr);
        return propertyBasedCreator;
    }

    public void assignDeserializer(SettableBeanProperty settableBeanProperty, JsonDeserializer<Object> jsonDeserializer) {
        SettableBeanProperty withValueDeserializer = settableBeanProperty.withValueDeserializer(jsonDeserializer);
        SettableBeanProperty put = this._properties.put(withValueDeserializer.getName(), withValueDeserializer);
    }

    public Collection<SettableBeanProperty> properties() {
        return this._properties.values();
    }

    public SettableBeanProperty findCreatorProperty(String str) {
        return this._properties.get(str);
    }

    public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectIdReader objectIdReader) {
        PropertyValueBuffer propertyValueBuffer;
        new PropertyValueBuffer(jsonParser, deserializationContext, this._propertyCount, objectIdReader);
        PropertyValueBuffer propertyValueBuffer2 = propertyValueBuffer;
        if (this._propertiesWithInjectables != null) {
            propertyValueBuffer2.inject(this._propertiesWithInjectables);
        }
        return propertyValueBuffer2;
    }

    public Object build(DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer) throws IOException {
        DeserializationContext deserializationContext2 = deserializationContext;
        PropertyValueBuffer propertyValueBuffer2 = propertyValueBuffer;
        Object handleIdValue = propertyValueBuffer2.handleIdValue(deserializationContext2, this._valueInstantiator.createFromObjectWith(deserializationContext2, propertyValueBuffer2.getParameters(this._defaultValues)));
        PropertyValue buffered = propertyValueBuffer2.buffered();
        while (true) {
            PropertyValue propertyValue = buffered;
            if (propertyValue == null) {
                return handleIdValue;
            }
            propertyValue.assign(handleIdValue);
            buffered = propertyValue.next;
        }
    }
}
