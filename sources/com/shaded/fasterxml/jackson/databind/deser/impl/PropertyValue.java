package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;

public abstract class PropertyValue {
    public final PropertyValue next;
    public final Object value;

    public abstract void assign(Object obj) throws IOException, JsonProcessingException;

    protected PropertyValue(PropertyValue propertyValue, Object obj) {
        this.next = propertyValue;
        this.value = obj;
    }

    static final class Regular extends PropertyValue {
        final SettableBeanProperty _property;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Regular(PropertyValue propertyValue, Object obj, SettableBeanProperty settableBeanProperty) {
            super(propertyValue, obj);
            this._property = settableBeanProperty;
        }

        public void assign(Object obj) throws IOException, JsonProcessingException {
            this._property.set(obj, this.value);
        }
    }

    static final class Any extends PropertyValue {
        final SettableAnyProperty _property;
        final String _propertyName;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Any(PropertyValue propertyValue, Object obj, SettableAnyProperty settableAnyProperty, String str) {
            super(propertyValue, obj);
            this._property = settableAnyProperty;
            this._propertyName = str;
        }

        public void assign(Object obj) throws IOException, JsonProcessingException {
            this._property.set(obj, this._propertyName, this.value);
        }
    }

    static final class Map extends PropertyValue {
        final Object _key;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Map(PropertyValue propertyValue, Object obj, Object obj2) {
            super(propertyValue, obj);
            this._key = obj2;
        }

        public void assign(Object obj) throws IOException, JsonProcessingException {
            Object put = ((java.util.Map) obj).put(this._key, this.value);
        }
    }
}
