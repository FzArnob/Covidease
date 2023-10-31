package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyValue;
import java.io.IOException;

public final class PropertyValueBuffer {
    private PropertyValue _buffered;
    protected final DeserializationContext _context;
    protected final Object[] _creatorParameters;
    private Object _idValue;
    protected final ObjectIdReader _objectIdReader;
    private int _paramsNeeded;
    protected final JsonParser _parser;

    public PropertyValueBuffer(JsonParser jsonParser, DeserializationContext deserializationContext, int i, ObjectIdReader objectIdReader) {
        int i2 = i;
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._paramsNeeded = i2;
        this._objectIdReader = objectIdReader;
        this._creatorParameters = new Object[i2];
    }

    public void inject(SettableBeanProperty[] settableBeanPropertyArr) {
        SettableBeanProperty[] settableBeanPropertyArr2 = settableBeanPropertyArr;
        int length = settableBeanPropertyArr2.length;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr2[i];
            if (settableBeanProperty != null) {
                this._creatorParameters[i] = this._context.findInjectableValue(settableBeanProperty.getInjectableValueId(), settableBeanProperty, (Object) null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object[] getParameters(Object[] objArr) {
        Object obj;
        Object[] objArr2 = objArr;
        if (objArr2 != null) {
            int length = this._creatorParameters.length;
            for (int i = 0; i < length; i++) {
                if (this._creatorParameters[i] == null && (obj = objArr2[i]) != null) {
                    this._creatorParameters[i] = obj;
                }
            }
        }
        return this._creatorParameters;
    }

    public boolean readIdProperty(String str) throws IOException {
        String str2 = str;
        if (this._objectIdReader == null || !str2.equals(this._objectIdReader.propertyName)) {
            return false;
        }
        this._idValue = this._objectIdReader.deserializer.deserialize(this._parser, this._context);
        return true;
    }

    public Object handleIdValue(DeserializationContext deserializationContext, Object obj) throws IOException {
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        if (!(this._objectIdReader == null || this._idValue == null)) {
            deserializationContext2.findObjectId(this._idValue, this._objectIdReader.generator).bindItem(obj2);
            SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
            if (settableBeanProperty != null) {
                return settableBeanProperty.setAndReturn(obj2, this._idValue);
            }
        }
        return obj2;
    }

    /* access modifiers changed from: protected */
    public PropertyValue buffered() {
        return this._buffered;
    }

    public boolean isComplete() {
        return this._paramsNeeded <= 0;
    }

    public boolean assignParameter(int i, Object obj) {
        this._creatorParameters[i] = obj;
        int i2 = this._paramsNeeded - 1;
        int i3 = i2;
        this._paramsNeeded = i2;
        return i3 <= 0;
    }

    public void bufferProperty(SettableBeanProperty settableBeanProperty, Object obj) {
        PropertyValue propertyValue;
        new PropertyValue.Regular(this._buffered, obj, settableBeanProperty);
        this._buffered = propertyValue;
    }

    public void bufferAnyProperty(SettableAnyProperty settableAnyProperty, String str, Object obj) {
        PropertyValue propertyValue;
        new PropertyValue.Any(this._buffered, obj, settableAnyProperty, str);
        this._buffered = propertyValue;
    }

    public void bufferMapProperty(Object obj, Object obj2) {
        PropertyValue propertyValue;
        new PropertyValue.Map(this._buffered, obj2, obj);
        this._buffered = propertyValue;
    }
}
