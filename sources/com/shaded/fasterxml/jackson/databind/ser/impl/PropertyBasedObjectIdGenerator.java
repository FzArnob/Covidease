package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter;

public class PropertyBasedObjectIdGenerator extends ObjectIdGenerators.PropertyGenerator {
    private static final long serialVersionUID = 1;
    protected final BeanPropertyWriter _property;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PropertyBasedObjectIdGenerator(ObjectIdInfo objectIdInfo, BeanPropertyWriter beanPropertyWriter) {
        this(objectIdInfo.getScope(), beanPropertyWriter);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected PropertyBasedObjectIdGenerator(Class<?> cls, BeanPropertyWriter beanPropertyWriter) {
        super(cls);
        this._property = beanPropertyWriter;
    }

    public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
        ObjectIdGenerator<?> objectIdGenerator2 = objectIdGenerator;
        if (objectIdGenerator2.getClass() == getClass()) {
            PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator = (PropertyBasedObjectIdGenerator) objectIdGenerator2;
            if (propertyBasedObjectIdGenerator.getScope() == this._scope) {
                return propertyBasedObjectIdGenerator._property == this._property;
            }
        }
        return false;
    }

    public Object generateId(Object obj) {
        Throwable th;
        StringBuilder sb;
        try {
            return this._property.get(obj);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Problem accessing property '").append(this._property.getName()).append("': ").append(exc.getMessage()).toString(), exc);
            throw th2;
        }
    }

    public ObjectIdGenerator<Object> forScope(Class<?> cls) {
        PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator;
        PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator2;
        Class<?> cls2 = cls;
        if (cls2 == this._scope) {
            propertyBasedObjectIdGenerator2 = this;
        } else {
            propertyBasedObjectIdGenerator2 = propertyBasedObjectIdGenerator;
            new PropertyBasedObjectIdGenerator(cls2, this._property);
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
