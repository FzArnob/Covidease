package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;

public class PropertyBasedObjectIdGenerator extends ObjectIdGenerators.PropertyGenerator {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PropertyBasedObjectIdGenerator(Class<?> cls) {
        super(cls);
    }

    public Object generateId(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public ObjectIdGenerator<Object> forScope(Class<?> cls) {
        PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator;
        PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator2;
        Class<?> cls2 = cls;
        if (cls2 == this._scope) {
            propertyBasedObjectIdGenerator2 = this;
        } else {
            propertyBasedObjectIdGenerator2 = propertyBasedObjectIdGenerator;
            new PropertyBasedObjectIdGenerator(cls2);
        }
        return propertyBasedObjectIdGenerator2;
    }

    public ObjectIdGenerator<Object> newForSerialization(Object obj) {
        Object obj2 = obj;
        return this;
    }

    public ObjectIdGenerator.IdKey key(Object obj) {
        ObjectIdGenerator.IdKey idKey;
        new ObjectIdGenerator.IdKey(getClass(), this._scope, obj);
        return idKey;
    }
}
