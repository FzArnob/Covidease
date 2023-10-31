package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeanDeserializerBuilder {
    protected SettableAnyProperty _anySetter;
    protected HashMap<String, SettableBeanProperty> _backRefProperties;
    protected final BeanDescription _beanDesc;
    protected AnnotatedMethod _buildMethod;
    protected JsonPOJOBuilder.Value _builderConfig;
    protected final boolean _defaultViewInclusion;
    protected HashSet<String> _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected List<ValueInjector> _injectables;
    protected ObjectIdReader _objectIdReader;
    protected final Map<String, SettableBeanProperty> _properties;
    protected ValueInstantiator _valueInstantiator;

    public BeanDeserializerBuilder(BeanDescription beanDescription, DeserializationConfig deserializationConfig) {
        Map<String, SettableBeanProperty> map;
        new LinkedHashMap();
        this._properties = map;
        this._beanDesc = beanDescription;
        this._defaultViewInclusion = deserializationConfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }

    protected BeanDeserializerBuilder(BeanDeserializerBuilder beanDeserializerBuilder) {
        Map<String, SettableBeanProperty> map;
        BeanDeserializerBuilder beanDeserializerBuilder2 = beanDeserializerBuilder;
        new LinkedHashMap();
        this._properties = map;
        this._beanDesc = beanDeserializerBuilder2._beanDesc;
        this._defaultViewInclusion = beanDeserializerBuilder2._defaultViewInclusion;
        this._anySetter = beanDeserializerBuilder2._anySetter;
        this._ignoreAllUnknown = beanDeserializerBuilder2._ignoreAllUnknown;
        this._properties.putAll(beanDeserializerBuilder2._properties);
        this._backRefProperties = _copy(beanDeserializerBuilder2._backRefProperties);
        this._ignorableProps = beanDeserializerBuilder2._ignorableProps;
        this._valueInstantiator = beanDeserializerBuilder2._valueInstantiator;
        this._objectIdReader = beanDeserializerBuilder2._objectIdReader;
        this._buildMethod = beanDeserializerBuilder2._buildMethod;
        this._builderConfig = beanDeserializerBuilder2._builderConfig;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.HashMap<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> _copy(java.util.HashMap<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> r5) {
        /*
            r0 = r5
            r1 = r0
            if (r1 != 0) goto L_0x0007
            r1 = 0
            r0 = r1
        L_0x0006:
            return r0
        L_0x0007:
            java.util.HashMap r1 = new java.util.HashMap
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            r2.<init>(r3)
            r0 = r1
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBuilder._copy(java.util.HashMap):java.util.HashMap");
    }

    public void addOrReplaceProperty(SettableBeanProperty settableBeanProperty, boolean z) {
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        boolean z2 = z;
        SettableBeanProperty put = this._properties.put(settableBeanProperty2.getName(), settableBeanProperty2);
    }

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        Throwable th;
        StringBuilder sb;
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        SettableBeanProperty put = this._properties.put(settableBeanProperty2.getName(), settableBeanProperty2);
        if (put != null && put != settableBeanProperty2) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Duplicate property '").append(settableBeanProperty2.getName()).append("' for ").append(this._beanDesc.getType()).toString());
            throw th2;
        }
    }

    public void addBackReferenceProperty(String str, SettableBeanProperty settableBeanProperty) {
        HashMap<String, SettableBeanProperty> hashMap;
        String str2 = str;
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        if (this._backRefProperties == null) {
            new HashMap<>(4);
            this._backRefProperties = hashMap;
        }
        SettableBeanProperty put = this._backRefProperties.put(str2, settableBeanProperty2);
        if (this._properties != null) {
            SettableBeanProperty remove = this._properties.remove(settableBeanProperty2.getName());
        }
    }

    public void addInjectable(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember, Object obj) {
        Object obj2;
        List<ValueInjector> list;
        String str2 = str;
        JavaType javaType2 = javaType;
        Annotations annotations2 = annotations;
        AnnotatedMember annotatedMember2 = annotatedMember;
        Object obj3 = obj;
        if (this._injectables == null) {
            new ArrayList();
            this._injectables = list;
        }
        new ValueInjector(str2, javaType2, annotations2, annotatedMember2, obj3);
        boolean add = this._injectables.add(obj2);
    }

    public void addIgnorable(String str) {
        HashSet<String> hashSet;
        String str2 = str;
        if (this._ignorableProps == null) {
            new HashSet<>();
            this._ignorableProps = hashSet;
        }
        boolean add = this._ignorableProps.add(str2);
    }

    public void addCreatorProperty(SettableBeanProperty settableBeanProperty) {
        addProperty(settableBeanProperty);
    }

    @Deprecated
    public void addCreatorProperty(BeanPropertyDefinition beanPropertyDefinition) {
    }

    public void setAnySetter(SettableAnyProperty settableAnyProperty) {
        Throwable th;
        SettableAnyProperty settableAnyProperty2 = settableAnyProperty;
        if (this._anySetter == null || settableAnyProperty2 == null) {
            this._anySetter = settableAnyProperty2;
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("_anySetter already set to non-null");
        throw th2;
    }

    public void setIgnoreUnknownProperties(boolean z) {
        boolean z2 = z;
        this._ignoreAllUnknown = z2;
    }

    public void setValueInstantiator(ValueInstantiator valueInstantiator) {
        ValueInstantiator valueInstantiator2 = valueInstantiator;
        this._valueInstantiator = valueInstantiator2;
    }

    public void setObjectIdReader(ObjectIdReader objectIdReader) {
        ObjectIdReader objectIdReader2 = objectIdReader;
        this._objectIdReader = objectIdReader2;
    }

    public void setPOJOBuilder(AnnotatedMethod annotatedMethod, JsonPOJOBuilder.Value value) {
        this._buildMethod = annotatedMethod;
        this._builderConfig = value;
    }

    public Iterator<SettableBeanProperty> getProperties() {
        return this._properties.values().iterator();
    }

    public SettableBeanProperty findProperty(String str) {
        return this._properties.get(str);
    }

    public boolean hasProperty(String str) {
        return findProperty(str) != null;
    }

    public SettableBeanProperty removeProperty(String str) {
        return this._properties.remove(str);
    }

    public SettableAnyProperty getAnySetter() {
        return this._anySetter;
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public List<ValueInjector> getInjectables() {
        return this._injectables;
    }

    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    public AnnotatedMethod getBuildMethod() {
        return this._buildMethod;
    }

    public JsonPOJOBuilder.Value getBuilderConfig() {
        return this._builderConfig;
    }

    public JsonDeserializer<?> build() {
        BeanPropertyMap beanPropertyMap;
        JsonDeserializer<?> jsonDeserializer;
        SettableBeanProperty settableBeanProperty;
        Collection<SettableBeanProperty> values = this._properties.values();
        new BeanPropertyMap(values);
        BeanPropertyMap beanPropertyMap2 = beanPropertyMap;
        BeanPropertyMap assignIndexes = beanPropertyMap2.assignIndexes();
        boolean z = !this._defaultViewInclusion;
        if (!z) {
            Iterator<SettableBeanProperty> it = values.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().hasViews()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (this._objectIdReader != null) {
            new ObjectIdValueProperty(this._objectIdReader, true);
            beanPropertyMap2 = beanPropertyMap2.withProperty(settableBeanProperty);
        }
        new BeanDeserializer(this, this._beanDesc, beanPropertyMap2, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, z);
        return jsonDeserializer;
    }

    public AbstractDeserializer buildAbstract() {
        AbstractDeserializer abstractDeserializer;
        new AbstractDeserializer(this, this._beanDesc, this._backRefProperties);
        return abstractDeserializer;
    }

    public JsonDeserializer<?> buildBuilderBased(JavaType javaType, String str) {
        BeanPropertyMap beanPropertyMap;
        JsonDeserializer<?> jsonDeserializer;
        SettableBeanProperty settableBeanProperty;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        JavaType javaType2 = javaType;
        String str2 = str;
        if (this._buildMethod == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Builder class ").append(this._beanDesc.getBeanClass().getName()).append(" does not have build method '").append(str2).append("()'").toString());
            throw th3;
        }
        Class<?> rawReturnType = this._buildMethod.getRawReturnType();
        if (!javaType2.getRawClass().isAssignableFrom(rawReturnType)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Build method '").append(this._buildMethod.getFullName()).append(" has bad return type (").append(rawReturnType.getName()).append("), not compatible with POJO type (").append(javaType2.getRawClass().getName()).append(")").toString());
            throw th4;
        }
        Collection<SettableBeanProperty> values = this._properties.values();
        new BeanPropertyMap(values);
        BeanPropertyMap beanPropertyMap2 = beanPropertyMap;
        BeanPropertyMap assignIndexes = beanPropertyMap2.assignIndexes();
        boolean z = !this._defaultViewInclusion;
        if (!z) {
            Iterator<SettableBeanProperty> it = values.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().hasViews()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (this._objectIdReader != null) {
            new ObjectIdValueProperty(this._objectIdReader, true);
            beanPropertyMap2 = beanPropertyMap2.withProperty(settableBeanProperty);
        }
        new BuilderBasedDeserializer(this, this._beanDesc, beanPropertyMap2, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, z);
        return jsonDeserializer;
    }
}
