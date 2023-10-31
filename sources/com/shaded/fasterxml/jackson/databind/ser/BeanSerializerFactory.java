package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter;
import com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.shaded.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.shaded.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.shaded.fasterxml.jackson.databind.type.TypeBindings;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BeanSerializerFactory extends BasicSerializerFactory implements Serializable {
    public static final BeanSerializerFactory instance;
    private static final long serialVersionUID = 1;

    static {
        BeanSerializerFactory beanSerializerFactory;
        new BeanSerializerFactory((SerializerFactoryConfig) null);
        instance = beanSerializerFactory;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanSerializerFactory(SerializerFactoryConfig serializerFactoryConfig) {
        super(serializerFactoryConfig);
    }

    public SerializerFactory withConfig(SerializerFactoryConfig serializerFactoryConfig) {
        SerializerFactory serializerFactory;
        Throwable th;
        StringBuilder sb;
        SerializerFactoryConfig serializerFactoryConfig2 = serializerFactoryConfig;
        if (this._factoryConfig == serializerFactoryConfig2) {
            return this;
        } else if (getClass() != BeanSerializerFactory.class) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Subtype of BeanSerializerFactory (").append(getClass().getName()).append(") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with ").append("additional serializer definitions").toString());
            throw th2;
        } else {
            new BeanSerializerFactory(serializerFactoryConfig2);
            return serializerFactory;
        }
    }

    /* access modifiers changed from: protected */
    public Iterable<Serializers> customSerializers() {
        return this._factoryConfig.serializers();
    }

    public JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType) throws JsonMappingException {
        boolean z;
        JsonSerializer<Object> jsonSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        JavaType javaType2 = javaType;
        SerializationConfig config = serializerProvider2.getConfig();
        BeanDescription introspect = config.introspect(javaType2);
        JsonSerializer<Object> findSerializerFromAnnotation = findSerializerFromAnnotation(serializerProvider2, introspect.getClassInfo());
        if (findSerializerFromAnnotation != null) {
            return findSerializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(config, introspect.getClassInfo(), javaType2);
        if (modifyTypeByAnnotation == javaType2) {
            z = false;
        } else {
            z = true;
            if (modifyTypeByAnnotation.getRawClass() != javaType2.getRawClass()) {
                introspect = config.introspect(modifyTypeByAnnotation);
            }
        }
        Converter<Object, Object> findSerializationConverter = introspect.findSerializationConverter();
        if (findSerializationConverter == null) {
            return _createSerializer2(serializerProvider2, modifyTypeByAnnotation, introspect, z);
        }
        JavaType outputType = findSerializationConverter.getOutputType(serializerProvider2.getTypeFactory());
        new StdDelegatingSerializer(findSerializationConverter, outputType, _createSerializer2(serializerProvider2, outputType, introspect, true));
        return jsonSerializer;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> _createSerializer2(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        boolean z2 = z;
        JsonSerializer<Object> findSerializerByAnnotations = findSerializerByAnnotations(serializerProvider2, javaType2, beanDescription2);
        if (findSerializerByAnnotations != null) {
            return findSerializerByAnnotations;
        }
        SerializationConfig config = serializerProvider2.getConfig();
        if (!javaType2.isContainerType()) {
            for (Serializers findSerializer : customSerializers()) {
                findSerializerByAnnotations = findSerializer.findSerializer(config, javaType2, beanDescription2);
                if (findSerializerByAnnotations != null) {
                    break;
                }
            }
        } else {
            if (!z2) {
                z2 = usesStaticTyping(config, beanDescription2, (TypeSerializer) null);
            }
            findSerializerByAnnotations = buildContainerSerializer(serializerProvider2, javaType2, beanDescription2, z2);
            if (findSerializerByAnnotations != null) {
                return findSerializerByAnnotations;
            }
        }
        if (findSerializerByAnnotations == null) {
            findSerializerByAnnotations = findSerializerByLookup(javaType2, config, beanDescription2, z2);
            if (findSerializerByAnnotations == null) {
                findSerializerByAnnotations = findSerializerByPrimaryType(serializerProvider2, javaType2, beanDescription2, z2);
                if (findSerializerByAnnotations == null) {
                    findSerializerByAnnotations = findBeanSerializer(serializerProvider2, javaType2, beanDescription2);
                    if (findSerializerByAnnotations == null) {
                        findSerializerByAnnotations = findSerializerByAddonType(config, javaType2, beanDescription2, z2);
                    }
                }
            }
        }
        if (findSerializerByAnnotations != null && this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifySerializer : this._factoryConfig.serializerModifiers()) {
                findSerializerByAnnotations = modifySerializer.modifySerializer(config, beanDescription2, findSerializerByAnnotations);
            }
        }
        return findSerializerByAnnotations;
    }

    @Deprecated
    public final JsonSerializer<Object> findBeanSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        return findBeanSerializer(serializerProvider, javaType, beanDescription);
    }

    public JsonSerializer<Object> findBeanSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        if (isPotentialBeanType(javaType2.getRawClass()) || javaType2.isEnumType()) {
            return constructBeanSerializer(serializerProvider2, beanDescription2);
        }
        return null;
    }

    @Deprecated
    public final TypeSerializer findPropertyTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        return findPropertyTypeSerializer(javaType, serializationConfig, annotatedMember);
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember) throws JsonMappingException {
        JavaType javaType2 = javaType;
        SerializationConfig serializationConfig2 = serializationConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        AnnotationIntrospector annotationIntrospector = serializationConfig2.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(serializationConfig2, annotatedMember2, javaType2);
        if (findPropertyTypeResolver == null) {
            return createTypeSerializer(serializationConfig2, javaType2);
        }
        return findPropertyTypeResolver.buildTypeSerializer(serializationConfig2, javaType2, serializationConfig2.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember2, serializationConfig2, annotationIntrospector, javaType2));
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember) throws JsonMappingException {
        JavaType javaType2 = javaType;
        SerializationConfig serializationConfig2 = serializationConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType contentType = javaType2.getContentType();
        AnnotationIntrospector annotationIntrospector = serializationConfig2.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(serializationConfig2, annotatedMember2, javaType2);
        if (findPropertyContentTypeResolver == null) {
            return createTypeSerializer(serializationConfig2, contentType);
        }
        return findPropertyContentTypeResolver.buildTypeSerializer(serializationConfig2, contentType, serializationConfig2.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember2, serializationConfig2, annotationIntrospector, contentType));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final JsonSerializer<Object> constructBeanSerializer(SerializerProvider serializerProvider, BeanDescription beanDescription, BeanProperty beanProperty) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        return constructBeanSerializer(serializerProvider, beanDescription);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> constructBeanSerializer(SerializerProvider serializerProvider, BeanDescription beanDescription) throws JsonMappingException {
        BeanProperty beanProperty;
        AnyGetterWriter anyGetterWriter;
        List<BeanPropertyWriter> list;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanDescription beanDescription2 = beanDescription;
        if (beanDescription2.getBeanClass() == Object.class) {
            return serializerProvider2.getUnknownTypeSerializer(Object.class);
        }
        SerializationConfig config = serializerProvider2.getConfig();
        BeanSerializerBuilder constructBeanSerializerBuilder = constructBeanSerializerBuilder(beanDescription2);
        constructBeanSerializerBuilder.setConfig(config);
        List<BeanPropertyWriter> findBeanProperties = findBeanProperties(serializerProvider2, beanDescription2, constructBeanSerializerBuilder);
        if (findBeanProperties == null) {
            new ArrayList();
            findBeanProperties = list;
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier changeProperties : this._factoryConfig.serializerModifiers()) {
                findBeanProperties = changeProperties.changeProperties(config, beanDescription2, findBeanProperties);
            }
        }
        List<BeanPropertyWriter> filterBeanProperties = filterBeanProperties(config, beanDescription2, findBeanProperties);
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier orderProperties : this._factoryConfig.serializerModifiers()) {
                filterBeanProperties = orderProperties.orderProperties(config, beanDescription2, filterBeanProperties);
            }
        }
        constructBeanSerializerBuilder.setObjectIdWriter(constructObjectIdHandler(serializerProvider2, beanDescription2, filterBeanProperties));
        constructBeanSerializerBuilder.setProperties(filterBeanProperties);
        constructBeanSerializerBuilder.setFilterId(findFilterId(config, beanDescription2));
        AnnotatedMember findAnyGetter = beanDescription2.findAnyGetter();
        if (findAnyGetter != null) {
            if (config.canOverrideAccessModifiers()) {
                findAnyGetter.fixAccess();
            }
            JavaType type = findAnyGetter.getType(beanDescription2.bindingsForBeanType());
            boolean isEnabled = config.isEnabled(MapperFeature.USE_STATIC_TYPING);
            JavaType contentType = type.getContentType();
            MapSerializer construct = MapSerializer.construct((String[]) null, type, isEnabled, createTypeSerializer(config, contentType), (JsonSerializer<Object>) null, (JsonSerializer<Object>) null);
            new BeanProperty.Std(findAnyGetter.getName(), contentType, (PropertyName) null, beanDescription2.getClassAnnotations(), findAnyGetter, false);
            new AnyGetterWriter(beanProperty, findAnyGetter, construct);
            constructBeanSerializerBuilder.setAnyGetter(anyGetterWriter);
        }
        processViews(config, constructBeanSerializerBuilder);
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier updateBuilder : this._factoryConfig.serializerModifiers()) {
                constructBeanSerializerBuilder = updateBuilder.updateBuilder(config, beanDescription2, constructBeanSerializerBuilder);
            }
        }
        JsonSerializer<?> build = constructBeanSerializerBuilder.build();
        if (build != null || !beanDescription2.hasKnownClassAnnotations()) {
            return build;
        }
        return constructBeanSerializerBuilder.createDummy();
    }

    /* access modifiers changed from: protected */
    public ObjectIdWriter constructObjectIdHandler(SerializerProvider serializerProvider, BeanDescription beanDescription, List<BeanPropertyWriter> list) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        ObjectIdGenerator objectIdGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanDescription beanDescription2 = beanDescription;
        List<BeanPropertyWriter> list2 = list;
        ObjectIdInfo objectIdInfo = beanDescription2.getObjectIdInfo();
        if (objectIdInfo == null) {
            return null;
        }
        Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
            String propertyName = objectIdInfo.getPropertyName();
            int size = list2.size();
            for (int i = 0; i != size; i++) {
                BeanPropertyWriter beanPropertyWriter = list2.get(i);
                if (propertyName.equals(beanPropertyWriter.getName())) {
                    BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
                    if (i > 0) {
                        BeanPropertyWriter remove = list2.remove(i);
                        list2.add(0, beanPropertyWriter2);
                    }
                    JavaType type = beanPropertyWriter2.getType();
                    new PropertyBasedObjectIdGenerator(objectIdInfo, beanPropertyWriter2);
                    return ObjectIdWriter.construct(type, (String) null, objectIdGenerator, objectIdInfo.getAlwaysAsId());
                }
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid Object Id definition for ").append(beanDescription2.getBeanClass().getName()).append(": can not find property with name '").append(propertyName).append("'").toString());
            throw th2;
        }
        return ObjectIdWriter.construct(serializerProvider2.getTypeFactory().findTypeParameters(serializerProvider2.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0], objectIdInfo.getPropertyName(), serializerProvider2.objectIdGeneratorInstance(beanDescription2.getClassInfo(), objectIdInfo), objectIdInfo.getAlwaysAsId());
    }

    /* access modifiers changed from: protected */
    public BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        return FilteredBeanPropertyWriter.constructViewBased(beanPropertyWriter, clsArr);
    }

    /* access modifiers changed from: protected */
    public PropertyBuilder constructPropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        PropertyBuilder propertyBuilder;
        new PropertyBuilder(serializationConfig, beanDescription);
        return propertyBuilder;
    }

    /* access modifiers changed from: protected */
    public BeanSerializerBuilder constructBeanSerializerBuilder(BeanDescription beanDescription) {
        BeanSerializerBuilder beanSerializerBuilder;
        new BeanSerializerBuilder(beanDescription);
        return beanSerializerBuilder;
    }

    /* access modifiers changed from: protected */
    public Object findFilterId(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        return serializationConfig.getAnnotationIntrospector().findFilterId(beanDescription.getClassInfo());
    }

    /* access modifiers changed from: protected */
    public boolean isPotentialBeanType(Class<?> cls) {
        Class<?> cls2 = cls;
        return ClassUtil.canBeABeanType(cls2) == null && !ClassUtil.isProxyType(cls2);
    }

    /* access modifiers changed from: protected */
    public List<BeanPropertyWriter> findBeanProperties(SerializerProvider serializerProvider, BeanDescription beanDescription, BeanSerializerBuilder beanSerializerBuilder) throws JsonMappingException {
        ArrayList arrayList;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanDescription beanDescription2 = beanDescription;
        BeanSerializerBuilder beanSerializerBuilder2 = beanSerializerBuilder;
        List<BeanPropertyDefinition> findProperties = beanDescription2.findProperties();
        SerializationConfig config = serializerProvider2.getConfig();
        removeIgnorableTypes(config, beanDescription2, findProperties);
        if (config.isEnabled(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS)) {
            removeSetterlessGetters(config, beanDescription2, findProperties);
        }
        if (findProperties.isEmpty()) {
            return null;
        }
        boolean usesStaticTyping = usesStaticTyping(config, beanDescription2, (TypeSerializer) null);
        PropertyBuilder constructPropertyBuilder = constructPropertyBuilder(config, beanDescription2);
        new ArrayList(findProperties.size());
        ArrayList arrayList2 = arrayList;
        TypeBindings bindingsForBeanType = beanDescription2.bindingsForBeanType();
        for (BeanPropertyDefinition next : findProperties) {
            AnnotatedMember accessor = next.getAccessor();
            if (!next.isTypeId()) {
                AnnotationIntrospector.ReferenceProperty findReferenceType = next.findReferenceType();
                if (findReferenceType == null || !findReferenceType.isBackReference()) {
                    if (accessor instanceof AnnotatedMethod) {
                        boolean add = arrayList2.add(_constructWriter(serializerProvider2, next, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, (AnnotatedMethod) accessor));
                    } else {
                        boolean add2 = arrayList2.add(_constructWriter(serializerProvider2, next, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, (AnnotatedField) accessor));
                    }
                }
            } else if (accessor != null) {
                if (config.canOverrideAccessModifiers()) {
                    accessor.fixAccess();
                }
                beanSerializerBuilder2.setTypeId(accessor);
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: protected */
    public List<BeanPropertyWriter> filterBeanProperties(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        List<BeanPropertyWriter> list2 = list;
        String[] findPropertiesToIgnore = serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(beanDescription.getClassInfo());
        if (findPropertiesToIgnore != null && findPropertiesToIgnore.length > 0) {
            HashSet arrayToSet = ArrayBuilders.arrayToSet(findPropertiesToIgnore);
            Iterator<BeanPropertyWriter> it = list2.iterator();
            while (it.hasNext()) {
                if (arrayToSet.contains(it.next().getName())) {
                    it.remove();
                }
            }
        }
        return list2;
    }

    /* access modifiers changed from: protected */
    public void processViews(SerializationConfig serializationConfig, BeanSerializerBuilder beanSerializerBuilder) {
        BeanSerializerBuilder beanSerializerBuilder2 = beanSerializerBuilder;
        List<BeanPropertyWriter> properties = beanSerializerBuilder2.getProperties();
        boolean isEnabled = serializationConfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
        int size = properties.size();
        int i = 0;
        BeanPropertyWriter[] beanPropertyWriterArr = new BeanPropertyWriter[size];
        for (int i2 = 0; i2 < size; i2++) {
            BeanPropertyWriter beanPropertyWriter = properties.get(i2);
            Class[] views = beanPropertyWriter.getViews();
            if (views != null) {
                i++;
                beanPropertyWriterArr[i2] = constructFilteredBeanWriter(beanPropertyWriter, views);
            } else if (isEnabled) {
                beanPropertyWriterArr[i2] = beanPropertyWriter;
            }
        }
        if (!isEnabled || i != 0) {
            beanSerializerBuilder2.setFilteredProperties(beanPropertyWriterArr);
        }
    }

    /* access modifiers changed from: protected */
    public void removeIgnorableTypes(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyDefinition> list) {
        HashMap hashMap;
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        AnnotationIntrospector annotationIntrospector = serializationConfig2.getAnnotationIntrospector();
        new HashMap();
        HashMap hashMap2 = hashMap;
        Iterator<BeanPropertyDefinition> it = list.iterator();
        while (it.hasNext()) {
            AnnotatedMember accessor = it.next().getAccessor();
            if (accessor == null) {
                it.remove();
            } else {
                Class<?> rawType = accessor.getRawType();
                Boolean bool = (Boolean) hashMap2.get(rawType);
                if (bool == null) {
                    bool = annotationIntrospector.isIgnorableType(serializationConfig2.introspectClassAnnotations(rawType).getClassInfo());
                    if (bool == null) {
                        bool = Boolean.FALSE;
                    }
                    Object put = hashMap2.put(rawType, bool);
                }
                if (bool.booleanValue()) {
                    it.remove();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void removeSetterlessGetters(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyDefinition> list) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        Iterator<BeanPropertyDefinition> it = list.iterator();
        while (it.hasNext()) {
            BeanPropertyDefinition next = it.next();
            if (!next.couldDeserialize() && !next.isExplicitlyIncluded()) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: protected */
    public BeanPropertyWriter _constructWriter(SerializerProvider serializerProvider, BeanPropertyDefinition beanPropertyDefinition, TypeBindings typeBindings, PropertyBuilder propertyBuilder, boolean z, AnnotatedMember annotatedMember) throws JsonMappingException {
        BeanProperty beanProperty;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanPropertyDefinition beanPropertyDefinition2 = beanPropertyDefinition;
        TypeBindings typeBindings2 = typeBindings;
        PropertyBuilder propertyBuilder2 = propertyBuilder;
        boolean z2 = z;
        AnnotatedMember annotatedMember2 = annotatedMember;
        String name = beanPropertyDefinition2.getName();
        if (serializerProvider2.canOverrideAccessModifiers()) {
            annotatedMember2.fixAccess();
        }
        JavaType type = annotatedMember2.getType(typeBindings2);
        new BeanProperty.Std(name, type, beanPropertyDefinition2.getWrapperName(), propertyBuilder2.getClassAnnotations(), annotatedMember2, beanPropertyDefinition2.isRequired());
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<?> findSerializerFromAnnotation = findSerializerFromAnnotation(serializerProvider2, annotatedMember2);
        if (findSerializerFromAnnotation instanceof ResolvableSerializer) {
            ((ResolvableSerializer) findSerializerFromAnnotation).resolve(serializerProvider2);
        }
        if (findSerializerFromAnnotation instanceof ContextualSerializer) {
            findSerializerFromAnnotation = ((ContextualSerializer) findSerializerFromAnnotation).createContextual(serializerProvider2, beanProperty2);
        }
        TypeSerializer typeSerializer = null;
        if (ClassUtil.isCollectionMapOrArray(type.getRawClass())) {
            typeSerializer = findPropertyContentTypeSerializer(type, serializerProvider2.getConfig(), annotatedMember2);
        }
        return propertyBuilder2.buildWriter(beanPropertyDefinition2, type, findSerializerFromAnnotation, findPropertyTypeSerializer(type, serializerProvider2.getConfig(), annotatedMember2), typeSerializer, annotatedMember2, z2);
    }
}
