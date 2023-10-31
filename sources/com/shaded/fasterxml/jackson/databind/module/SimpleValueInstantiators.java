package com.shaded.fasterxml.jackson.databind.module;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleValueInstantiators extends ValueInstantiators.Base implements Serializable {
    private static final long serialVersionUID = -8929386427526115130L;
    protected HashMap<ClassKey, ValueInstantiator> _classMappings;

    public SimpleValueInstantiators() {
        HashMap<ClassKey, ValueInstantiator> hashMap;
        new HashMap<>();
        this._classMappings = hashMap;
    }

    public SimpleValueInstantiators addValueInstantiator(Class<?> cls, ValueInstantiator valueInstantiator) {
        Object obj;
        new ClassKey(cls);
        ValueInstantiator put = this._classMappings.put(obj, valueInstantiator);
        return this;
    }

    public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator) {
        Object obj;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        ValueInstantiator valueInstantiator2 = valueInstantiator;
        new ClassKey(beanDescription.getBeanClass());
        ValueInstantiator valueInstantiator3 = this._classMappings.get(obj);
        return valueInstantiator3 == null ? valueInstantiator2 : valueInstantiator3;
    }
}
