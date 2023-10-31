package com.shaded.fasterxml.jackson.databind.module;

import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.Module;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class SimpleModule extends Module implements Serializable {
    private static final long serialVersionUID = 3132264350026957446L;
    protected SimpleAbstractTypeResolver _abstractTypes;
    protected BeanDeserializerModifier _deserializerModifier;
    protected SimpleDeserializers _deserializers;
    protected SimpleKeyDeserializers _keyDeserializers;
    protected SimpleSerializers _keySerializers;
    protected HashMap<Class<?>, Class<?>> _mixins;
    protected final String _name;
    protected BeanSerializerModifier _serializerModifier;
    protected SimpleSerializers _serializers;
    protected LinkedHashSet<NamedType> _subtypes;
    protected SimpleValueInstantiators _valueInstantiators;
    protected final Version _version;

    public SimpleModule() {
        StringBuilder sb;
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        new StringBuilder();
        this._name = sb.append("SimpleModule-").append(System.identityHashCode(this)).toString();
        this._version = Version.unknownVersion();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleModule(String str) {
        this(str, Version.unknownVersion());
    }

    public SimpleModule(Version version) {
        Version version2 = version;
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._name = version2.getArtifactId();
        this._version = version2;
    }

    public SimpleModule(String str, Version version) {
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._name = str;
        this._version = version;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleModule(String str, Version version, Map<Class<?>, JsonDeserializer<?>> map) {
        this(str, version, map, (List<JsonSerializer<?>>) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleModule(String str, Version version, List<JsonSerializer<?>> list) {
        this(str, version, (Map<Class<?>, JsonDeserializer<?>>) null, list);
    }

    public SimpleModule(String str, Version version, Map<Class<?>, JsonDeserializer<?>> map, List<JsonSerializer<?>> list) {
        SimpleSerializers simpleSerializers;
        SimpleDeserializers simpleDeserializers;
        Map<Class<?>, JsonDeserializer<?>> map2 = map;
        List<JsonSerializer<?>> list2 = list;
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._name = str;
        this._version = version;
        if (map2 != null) {
            new SimpleDeserializers(map2);
            this._deserializers = simpleDeserializers;
        }
        if (list2 != null) {
            new SimpleSerializers(list2);
            this._serializers = simpleSerializers;
        }
    }

    public void setSerializers(SimpleSerializers simpleSerializers) {
        SimpleSerializers simpleSerializers2 = simpleSerializers;
        this._serializers = simpleSerializers2;
    }

    public void setDeserializers(SimpleDeserializers simpleDeserializers) {
        SimpleDeserializers simpleDeserializers2 = simpleDeserializers;
        this._deserializers = simpleDeserializers2;
    }

    public void setKeySerializers(SimpleSerializers simpleSerializers) {
        SimpleSerializers simpleSerializers2 = simpleSerializers;
        this._keySerializers = simpleSerializers2;
    }

    public void setKeyDeserializers(SimpleKeyDeserializers simpleKeyDeserializers) {
        SimpleKeyDeserializers simpleKeyDeserializers2 = simpleKeyDeserializers;
        this._keyDeserializers = simpleKeyDeserializers2;
    }

    public void setAbstractTypes(SimpleAbstractTypeResolver simpleAbstractTypeResolver) {
        SimpleAbstractTypeResolver simpleAbstractTypeResolver2 = simpleAbstractTypeResolver;
        this._abstractTypes = simpleAbstractTypeResolver2;
    }

    public void setValueInstantiators(SimpleValueInstantiators simpleValueInstantiators) {
        SimpleValueInstantiators simpleValueInstantiators2 = simpleValueInstantiators;
        this._valueInstantiators = simpleValueInstantiators2;
    }

    public SimpleModule setDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        this._deserializerModifier = beanDeserializerModifier;
        return this;
    }

    public SimpleModule setSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        this._serializerModifier = beanSerializerModifier;
        return this;
    }

    public SimpleModule addSerializer(JsonSerializer<?> jsonSerializer) {
        SimpleSerializers simpleSerializers;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        if (this._serializers == null) {
            new SimpleSerializers();
            this._serializers = simpleSerializers;
        }
        this._serializers.addSerializer(jsonSerializer2);
        return this;
    }

    public <T> SimpleModule addSerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        SimpleSerializers simpleSerializers;
        Class<? extends T> cls2 = cls;
        JsonSerializer<T> jsonSerializer2 = jsonSerializer;
        if (this._serializers == null) {
            new SimpleSerializers();
            this._serializers = simpleSerializers;
        }
        this._serializers.addSerializer(cls2, jsonSerializer2);
        return this;
    }

    public <T> SimpleModule addKeySerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        SimpleSerializers simpleSerializers;
        Class<? extends T> cls2 = cls;
        JsonSerializer<T> jsonSerializer2 = jsonSerializer;
        if (this._keySerializers == null) {
            new SimpleSerializers();
            this._keySerializers = simpleSerializers;
        }
        this._keySerializers.addSerializer(cls2, jsonSerializer2);
        return this;
    }

    public <T> SimpleModule addDeserializer(Class<T> cls, JsonDeserializer<? extends T> jsonDeserializer) {
        SimpleDeserializers simpleDeserializers;
        Class<T> cls2 = cls;
        JsonDeserializer<? extends T> jsonDeserializer2 = jsonDeserializer;
        if (this._deserializers == null) {
            new SimpleDeserializers();
            this._deserializers = simpleDeserializers;
        }
        this._deserializers.addDeserializer(cls2, jsonDeserializer2);
        return this;
    }

    public SimpleModule addKeyDeserializer(Class<?> cls, KeyDeserializer keyDeserializer) {
        SimpleKeyDeserializers simpleKeyDeserializers;
        Class<?> cls2 = cls;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        if (this._keyDeserializers == null) {
            new SimpleKeyDeserializers();
            this._keyDeserializers = simpleKeyDeserializers;
        }
        SimpleKeyDeserializers addDeserializer = this._keyDeserializers.addDeserializer(cls2, keyDeserializer2);
        return this;
    }

    public <T> SimpleModule addAbstractTypeMapping(Class<T> cls, Class<? extends T> cls2) {
        SimpleAbstractTypeResolver simpleAbstractTypeResolver;
        Class<T> cls3 = cls;
        Class<? extends T> cls4 = cls2;
        if (this._abstractTypes == null) {
            new SimpleAbstractTypeResolver();
            this._abstractTypes = simpleAbstractTypeResolver;
        }
        this._abstractTypes = this._abstractTypes.addMapping(cls3, cls4);
        return this;
    }

    public SimpleModule addValueInstantiator(Class<?> cls, ValueInstantiator valueInstantiator) {
        SimpleValueInstantiators simpleValueInstantiators;
        Class<?> cls2 = cls;
        ValueInstantiator valueInstantiator2 = valueInstantiator;
        if (this._valueInstantiators == null) {
            new SimpleValueInstantiators();
            this._valueInstantiators = simpleValueInstantiators;
        }
        this._valueInstantiators = this._valueInstantiators.addValueInstantiator(cls2, valueInstantiator2);
        return this;
    }

    public SimpleModule registerSubtypes(Class<?>... clsArr) {
        Object obj;
        LinkedHashSet<NamedType> linkedHashSet;
        Class<?>[] clsArr2 = clsArr;
        if (this._subtypes == null) {
            new LinkedHashSet<>(Math.max(16, clsArr2.length));
            this._subtypes = linkedHashSet;
        }
        Class<?>[] clsArr3 = clsArr2;
        int length = clsArr3.length;
        for (int i = 0; i < length; i++) {
            new NamedType(clsArr3[i]);
            boolean add = this._subtypes.add(obj);
        }
        return this;
    }

    public SimpleModule registerSubtypes(NamedType... namedTypeArr) {
        LinkedHashSet<NamedType> linkedHashSet;
        NamedType[] namedTypeArr2 = namedTypeArr;
        if (this._subtypes == null) {
            new LinkedHashSet<>(Math.max(16, namedTypeArr2.length));
            this._subtypes = linkedHashSet;
        }
        NamedType[] namedTypeArr3 = namedTypeArr2;
        int length = namedTypeArr3.length;
        for (int i = 0; i < length; i++) {
            boolean add = this._subtypes.add(namedTypeArr3[i]);
        }
        return this;
    }

    public SimpleModule setMixInAnnotation(Class<?> cls, Class<?> cls2) {
        HashMap<Class<?>, Class<?>> hashMap;
        Class<?> cls3 = cls;
        Class<?> cls4 = cls2;
        if (this._mixins == null) {
            new HashMap<>();
            this._mixins = hashMap;
        }
        Class<?> put = this._mixins.put(cls3, cls4);
        return this;
    }

    public String getModuleName() {
        return this._name;
    }

    public void setupModule(Module.SetupContext setupContext) {
        Module.SetupContext setupContext2 = setupContext;
        if (this._serializers != null) {
            setupContext2.addSerializers(this._serializers);
        }
        if (this._deserializers != null) {
            setupContext2.addDeserializers(this._deserializers);
        }
        if (this._keySerializers != null) {
            setupContext2.addKeySerializers(this._keySerializers);
        }
        if (this._keyDeserializers != null) {
            setupContext2.addKeyDeserializers(this._keyDeserializers);
        }
        if (this._abstractTypes != null) {
            setupContext2.addAbstractTypeResolver(this._abstractTypes);
        }
        if (this._valueInstantiators != null) {
            setupContext2.addValueInstantiators(this._valueInstantiators);
        }
        if (this._deserializerModifier != null) {
            setupContext2.addBeanDeserializerModifier(this._deserializerModifier);
        }
        if (this._serializerModifier != null) {
            setupContext2.addBeanSerializerModifier(this._serializerModifier);
        }
        if (this._subtypes != null && this._subtypes.size() > 0) {
            setupContext2.registerSubtypes((NamedType[]) this._subtypes.toArray(new NamedType[this._subtypes.size()]));
        }
        if (this._mixins != null) {
            for (Map.Entry next : this._mixins.entrySet()) {
                setupContext2.setMixInAnnotations((Class) next.getKey(), (Class) next.getValue());
            }
        }
    }

    public Version version() {
        return this._version;
    }
}
