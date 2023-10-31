package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shaded.fasterxml.jackson.databind.AbstractTypeResolver;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.shaded.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.shaded.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.shaded.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.shaded.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    private static final Class<?>[] NO_VIEWS = new Class[0];
    public static final BeanDeserializerFactory instance;
    private static final long serialVersionUID = 1;

    static {
        BeanDeserializerFactory beanDeserializerFactory;
        DeserializerFactoryConfig deserializerFactoryConfig;
        new DeserializerFactoryConfig();
        new BeanDeserializerFactory(deserializerFactoryConfig);
        instance = beanDeserializerFactory;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    public DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig) {
        DeserializerFactory deserializerFactory;
        Throwable th;
        StringBuilder sb;
        DeserializerFactoryConfig deserializerFactoryConfig2 = deserializerFactoryConfig;
        if (this._factoryConfig == deserializerFactoryConfig2) {
            return this;
        } else if (getClass() != BeanDeserializerFactory.class) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Subtype of BeanDeserializerFactory (").append(getClass().getName()).append(") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with ").append("additional deserializer definitions").toString());
            throw th2;
        } else {
            new BeanDeserializerFactory(deserializerFactoryConfig2);
            return deserializerFactory;
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        JavaType javaType2 = javaType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        for (Deserializers findBeanDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findBeanDeserializer2 = findBeanDeserializer.findBeanDeserializer(javaType2, deserializationConfig2, beanDescription2);
            if (findBeanDeserializer2 != null) {
                return findBeanDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType materializeAbstractType;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        DeserializationConfig config = deserializationContext2.getConfig();
        JsonDeserializer<Object> _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType2, config, beanDescription2);
        if (_findCustomBeanDeserializer != null) {
            return _findCustomBeanDeserializer;
        }
        if (javaType2.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext2, javaType2, beanDescription2);
        }
        if (!javaType2.isAbstract() || (materializeAbstractType = materializeAbstractType(deserializationContext2, javaType2, beanDescription2)) == null) {
            JsonDeserializer<?> findStdDeserializer = findStdDeserializer(deserializationContext2, javaType2, beanDescription2);
            if (findStdDeserializer != null) {
                return findStdDeserializer;
            }
            if (!isPotentialBeanType(javaType2.getRawClass())) {
                return null;
            }
            return buildBeanDeserializer(deserializationContext2, javaType2, beanDescription2);
        }
        return buildBeanDeserializer(deserializationContext2, materializeAbstractType, config.introspect(materializeAbstractType));
    }

    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        return buildBuilderBasedDeserializer(deserializationContext2, javaType, deserializationContext2.getConfig().introspectForBuilder(deserializationContext2.constructType(cls)));
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType javaType2;
        JsonDeserializer<?> jsonDeserializer;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType3 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        JsonDeserializer<?> findDefaultDeserializer = findDefaultDeserializer(deserializationContext2, javaType3, beanDescription2);
        if (findDefaultDeserializer != null) {
            return findDefaultDeserializer;
        }
        if (!AtomicReference.class.isAssignableFrom(javaType3.getRawClass())) {
            return findOptionalStdDeserializer(deserializationContext2, javaType3, beanDescription2);
        }
        JavaType[] findTypeParameters = deserializationContext2.getTypeFactory().findTypeParameters(javaType3, (Class<?>) AtomicReference.class);
        if (findTypeParameters == null || findTypeParameters.length < 1) {
            javaType2 = TypeFactory.unknownType();
        } else {
            javaType2 = findTypeParameters[0];
        }
        new JdkDeserializers.AtomicReferenceDeserializer(javaType2);
        return jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findDeserializer(javaType, deserializationContext.getConfig(), beanDescription);
    }

    /* access modifiers changed from: protected */
    public JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        JavaType type = beanDescription.getType();
        for (AbstractTypeResolver resolveAbstractType : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType2 = resolveAbstractType.resolveAbstractType(deserializationContext2.getConfig(), type);
            if (resolveAbstractType2 != null) {
                return resolveAbstractType2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        AbstractDeserializer build;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext2, beanDescription2);
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext2, beanDescription2);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        addObjectIdReader(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        addInjectables(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        DeserializationConfig config = deserializationContext2.getConfig();
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription2, constructBeanDeserializerBuilder);
            }
        }
        if (!javaType2.isAbstract() || findValueInstantiator.canInstantiate()) {
            build = constructBeanDeserializerBuilder.build();
        } else {
            build = constructBeanDeserializerBuilder.buildAbstract();
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                build = modifyDeserializer.modifyDeserializer(config, beanDescription2, build);
            }
        }
        return build;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        String str;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext2, beanDescription2);
        DeserializationConfig config = deserializationContext2.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext2, beanDescription2);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        addObjectIdReader(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        addInjectables(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        JsonPOJOBuilder.Value findPOJOBuilderConfig = beanDescription2.findPOJOBuilderConfig();
        if (findPOJOBuilderConfig == null) {
            str = "build";
        } else {
            str = findPOJOBuilderConfig.buildMethodName;
        }
        String str2 = str;
        AnnotatedMethod findMethod = beanDescription2.findMethod(str2, (Class<?>[]) null);
        if (findMethod != null && config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(findMethod.getMember());
        }
        constructBeanDeserializerBuilder.setPOJOBuilder(findMethod, findPOJOBuilderConfig);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription2, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> buildBuilderBased = constructBeanDeserializerBuilder.buildBuilderBased(javaType2, str2);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                buildBuilderBased = modifyDeserializer.modifyDeserializer(config, beanDescription2, buildBuilderBased);
            }
        }
        return buildBuilderBased;
    }

    /* access modifiers changed from: protected */
    public void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        JavaType javaType;
        SettableBeanProperty settableBeanProperty;
        ObjectIdGenerator<?> objectIdGeneratorInstance;
        ObjectIdGenerator<?> objectIdGenerator;
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        BeanDeserializerBuilder beanDeserializerBuilder2 = beanDeserializerBuilder;
        ObjectIdInfo objectIdInfo = beanDescription2.getObjectIdInfo();
        if (objectIdInfo != null) {
            Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
            if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                String propertyName = objectIdInfo.getPropertyName();
                settableBeanProperty = beanDeserializerBuilder2.findProperty(propertyName);
                if (settableBeanProperty == null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Invalid Object Id definition for ").append(beanDescription2.getBeanClass().getName()).append(": can not find property with name '").append(propertyName).append("'").toString());
                    throw th2;
                }
                javaType = settableBeanProperty.getType();
                new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
                objectIdGeneratorInstance = objectIdGenerator;
            } else {
                javaType = deserializationContext2.getTypeFactory().findTypeParameters(deserializationContext2.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                settableBeanProperty = null;
                objectIdGeneratorInstance = deserializationContext2.objectIdGeneratorInstance(beanDescription2.getClassInfo(), objectIdInfo);
            }
            beanDeserializerBuilder2.setObjectIdReader(ObjectIdReader.construct(javaType, objectIdInfo.getPropertyName(), objectIdGeneratorInstance, deserializationContext2.findRootValueDeserializer(javaType), settableBeanProperty));
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        SettableBeanProperty constructSettableProperty;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        DeserializationConfig config = deserializationContext2.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext2, beanDescription2);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext2, beanDescription2));
        addBeanProps(deserializationContext2, beanDescription2, constructBeanDeserializerBuilder);
        AnnotatedMethod findMethod = beanDescription2.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (!(findMethod == null || (constructSettableProperty = constructSettableProperty(deserializationContext2, beanDescription2, SimpleBeanPropertyDefinition.construct(deserializationContext2.getConfig(), findMethod, "cause"), findMethod.getGenericParameterType(0))) == null)) {
            constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("suppressed");
        constructBeanDeserializerBuilder.addIgnorable("message");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription2, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> build = constructBeanDeserializerBuilder.build();
        if (build instanceof BeanDeserializer) {
            new ThrowableDeserializer((BeanDeserializer) build);
            build = jsonDeserializer;
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                build = modifyDeserializer.modifyDeserializer(config, beanDescription2, build);
            }
        }
        return build;
    }

    /* access modifiers changed from: protected */
    public BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        BeanDeserializerBuilder beanDeserializerBuilder;
        new BeanDeserializerBuilder(beanDescription, deserializationContext.getConfig());
        return beanDeserializerBuilder;
    }

    /* access modifiers changed from: protected */
    public void addBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        StringBuilder sb;
        Set<String> ignoredPropertyNames;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        BeanDeserializerBuilder beanDeserializerBuilder2 = beanDeserializerBuilder;
        SettableBeanProperty[] fromObjectArguments = beanDeserializerBuilder2.getValueInstantiator().getFromObjectArguments(deserializationContext2.getConfig());
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        Boolean findIgnoreUnknownProperties = annotationIntrospector.findIgnoreUnknownProperties(beanDescription2.getClassInfo());
        if (findIgnoreUnknownProperties != null) {
            beanDeserializerBuilder2.setIgnoreUnknownProperties(findIgnoreUnknownProperties.booleanValue());
        }
        HashSet<String> arrayToSet = ArrayBuilders.arrayToSet(annotationIntrospector.findPropertiesToIgnore(beanDescription2.getClassInfo()));
        for (String addIgnorable : arrayToSet) {
            beanDeserializerBuilder2.addIgnorable(addIgnorable);
        }
        AnnotatedMethod findAnySetter = beanDescription2.findAnySetter();
        if (findAnySetter != null) {
            beanDeserializerBuilder2.setAnySetter(constructAnySetter(deserializationContext2, beanDescription2, findAnySetter));
        }
        if (findAnySetter == null && (ignoredPropertyNames = beanDescription2.getIgnoredPropertyNames()) != null) {
            for (String addIgnorable2 : ignoredPropertyNames) {
                beanDeserializerBuilder2.addIgnorable(addIgnorable2);
            }
        }
        boolean z = deserializationContext2.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationContext2.isEnabled(MapperFeature.AUTO_DETECT_GETTERS);
        List<BeanPropertyDefinition> filterBeanProps = filterBeanProps(deserializationContext2, beanDescription2, beanDeserializerBuilder2, beanDescription2.findProperties(), arrayToSet);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateProperties : this._factoryConfig.deserializerModifiers()) {
                filterBeanProps = updateProperties.updateProperties(deserializationContext2.getConfig(), beanDescription2, filterBeanProps);
            }
        }
        for (BeanPropertyDefinition next : filterBeanProps) {
            SettableBeanProperty settableBeanProperty = null;
            if (next.hasConstructorParameter()) {
                String name = next.getName();
                if (fromObjectArguments != null) {
                    SettableBeanProperty[] settableBeanPropertyArr = fromObjectArguments;
                    int length = settableBeanPropertyArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        SettableBeanProperty settableBeanProperty2 = settableBeanPropertyArr[i];
                        if (name.equals(settableBeanProperty2.getName())) {
                            settableBeanProperty = settableBeanProperty2;
                            break;
                        }
                        i++;
                    }
                }
                if (settableBeanProperty == null) {
                    new StringBuilder();
                    throw deserializationContext2.mappingException(sb.append("Could not find creator property with name '").append(name).append("' (in class ").append(beanDescription2.getBeanClass().getName()).append(")").toString());
                }
                beanDeserializerBuilder2.addCreatorProperty(settableBeanProperty);
            } else {
                if (next.hasSetter()) {
                    settableBeanProperty = constructSettableProperty(deserializationContext2, beanDescription2, next, next.getSetter().getGenericParameterType(0));
                } else if (next.hasField()) {
                    settableBeanProperty = constructSettableProperty(deserializationContext2, beanDescription2, next, next.getField().getGenericType());
                } else if (z && next.hasGetter()) {
                    Class<?> rawType = next.getGetter().getRawType();
                    if (Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType)) {
                        settableBeanProperty = constructSetterlessProperty(deserializationContext2, beanDescription2, next);
                    }
                }
                if (settableBeanProperty != null) {
                    Class<?>[] findViews = next.findViews();
                    if (findViews == null && !deserializationContext2.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION)) {
                        findViews = NO_VIEWS;
                    }
                    settableBeanProperty.setViews(findViews);
                    beanDeserializerBuilder2.addProperty(settableBeanProperty);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) throws JsonMappingException {
        ArrayList arrayList;
        Map map;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        BeanDeserializerBuilder beanDeserializerBuilder2 = beanDeserializerBuilder;
        List<BeanPropertyDefinition> list2 = list;
        Set<String> set2 = set;
        new ArrayList(Math.max(4, list2.size()));
        ArrayList arrayList2 = arrayList;
        new HashMap();
        Map map2 = map;
        for (BeanPropertyDefinition next : list2) {
            String name = next.getName();
            if (!set2.contains(name)) {
                if (!next.hasConstructorParameter()) {
                    Class<?> cls = null;
                    if (next.hasSetter()) {
                        cls = next.getSetter().getRawParameterType(0);
                    } else if (next.hasField()) {
                        cls = next.getField().getRawType();
                    }
                    if (cls != null && isIgnorableType(deserializationContext2.getConfig(), beanDescription2, cls, map2)) {
                        beanDeserializerBuilder2.addIgnorable(name);
                    }
                }
                boolean add = arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: protected */
    public void addReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Type rawType;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        BeanDeserializerBuilder beanDeserializerBuilder2 = beanDeserializerBuilder;
        Map<String, AnnotatedMember> findBackReferenceProperties = beanDescription2.findBackReferenceProperties();
        if (findBackReferenceProperties != null) {
            for (Map.Entry next : findBackReferenceProperties.entrySet()) {
                String str = (String) next.getKey();
                AnnotatedMember annotatedMember = (AnnotatedMember) next.getValue();
                if (annotatedMember instanceof AnnotatedMethod) {
                    rawType = ((AnnotatedMethod) annotatedMember).getGenericParameterType(0);
                } else {
                    rawType = annotatedMember.getRawType();
                }
                SimpleBeanPropertyDefinition construct = SimpleBeanPropertyDefinition.construct(deserializationContext2.getConfig(), annotatedMember);
                beanDeserializerBuilder2.addBackReferenceProperty(str, constructSettableProperty(deserializationContext2, beanDescription2, construct, rawType));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        BeanDeserializerBuilder beanDeserializerBuilder2 = beanDeserializerBuilder;
        Map<Object, AnnotatedMember> findInjectables = beanDescription2.findInjectables();
        if (findInjectables != null) {
            boolean canOverrideAccessModifiers = deserializationContext2.canOverrideAccessModifiers();
            for (Map.Entry next : findInjectables.entrySet()) {
                AnnotatedMember annotatedMember = (AnnotatedMember) next.getValue();
                if (canOverrideAccessModifiers) {
                    annotatedMember.fixAccess();
                }
                beanDeserializerBuilder2.addInjectable(annotatedMember.getName(), beanDescription2.resolveType(annotatedMember.getGenericType()), beanDescription2.getClassAnnotations(), annotatedMember, next.getKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    public SettableAnyProperty constructAnySetter(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedMethod annotatedMethod) throws JsonMappingException {
        BeanProperty beanProperty;
        SettableAnyProperty settableAnyProperty;
        SettableAnyProperty settableAnyProperty2;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        if (deserializationContext2.canOverrideAccessModifiers()) {
            annotatedMethod2.fixAccess();
        }
        JavaType resolveType = beanDescription2.bindingsForBeanType().resolveType(annotatedMethod2.getGenericParameterType(1));
        new BeanProperty.Std(annotatedMethod2.getName(), resolveType, (PropertyName) null, beanDescription2.getClassAnnotations(), annotatedMethod2, false);
        BeanProperty beanProperty2 = beanProperty;
        JavaType resolveType2 = resolveType(deserializationContext2, beanDescription2, resolveType, annotatedMethod2);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, annotatedMethod2);
        if (findDeserializerFromAnnotation != null) {
            new SettableAnyProperty(beanProperty2, annotatedMethod2, resolveType2, findDeserializerFromAnnotation);
            return settableAnyProperty2;
        }
        new SettableAnyProperty(beanProperty2, annotatedMethod2, modifyTypeByAnnotation(deserializationContext2, annotatedMethod2, resolveType2), (JsonDeserializer<Object>) null);
        return settableAnyProperty;
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructSettableProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, Type type) throws JsonMappingException {
        BeanProperty.Std std;
        SettableBeanProperty settableBeanProperty;
        SettableBeanProperty settableBeanProperty2;
        SettableBeanProperty settableBeanProperty3;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        BeanPropertyDefinition beanPropertyDefinition2 = beanPropertyDefinition;
        Type type2 = type;
        AnnotatedMember mutator = beanPropertyDefinition2.getMutator();
        if (deserializationContext2.canOverrideAccessModifiers()) {
            mutator.fixAccess();
        }
        JavaType resolveType = beanDescription2.resolveType(type2);
        new BeanProperty.Std(beanPropertyDefinition2.getName(), resolveType, beanPropertyDefinition2.getWrapperName(), beanDescription2.getClassAnnotations(), mutator, beanPropertyDefinition2.isRequired());
        BeanProperty.Std std2 = std;
        JavaType resolveType2 = resolveType(deserializationContext2, beanDescription2, resolveType, mutator);
        if (resolveType2 != resolveType) {
            BeanProperty.Std withType = std2.withType(resolveType2);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, mutator);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext2, mutator, resolveType2);
        TypeDeserializer typeDeserializer = (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler();
        if (mutator instanceof AnnotatedMethod) {
            new MethodProperty(beanPropertyDefinition2, modifyTypeByAnnotation, typeDeserializer, beanDescription2.getClassAnnotations(), (AnnotatedMethod) mutator);
            settableBeanProperty2 = settableBeanProperty3;
        } else {
            new FieldProperty(beanPropertyDefinition2, modifyTypeByAnnotation, typeDeserializer, beanDescription2.getClassAnnotations(), (AnnotatedField) mutator);
            settableBeanProperty2 = settableBeanProperty;
        }
        if (findDeserializerFromAnnotation != null) {
            settableBeanProperty2 = settableBeanProperty2.withValueDeserializer(findDeserializerFromAnnotation);
        }
        AnnotationIntrospector.ReferenceProperty findReferenceType = beanPropertyDefinition2.findReferenceType();
        if (findReferenceType != null && findReferenceType.isManagedReference()) {
            settableBeanProperty2.setManagedReferenceName(findReferenceType.getName());
        }
        return settableBeanProperty2;
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) throws JsonMappingException {
        SettableBeanProperty settableBeanProperty;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        BeanPropertyDefinition beanPropertyDefinition2 = beanPropertyDefinition;
        AnnotatedMethod getter = beanPropertyDefinition2.getGetter();
        if (deserializationContext2.canOverrideAccessModifiers()) {
            getter.fixAccess();
        }
        JavaType type = getter.getType(beanDescription2.bindingsForBeanType());
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, getter);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext2, getter, type);
        new SetterlessProperty(beanPropertyDefinition2, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), beanDescription2.getClassAnnotations(), getter);
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        if (findDeserializerFromAnnotation != null) {
            settableBeanProperty2 = settableBeanProperty2.withValueDeserializer(findDeserializerFromAnnotation);
        }
        return settableBeanProperty2;
    }

    /* access modifiers changed from: protected */
    public boolean isPotentialBeanType(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Class<?> cls2 = cls;
        String canBeABeanType = ClassUtil.canBeABeanType(cls2);
        if (canBeABeanType != null) {
            Throwable th4 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb3.append("Can not deserialize Class ").append(cls2.getName()).append(" (of type ").append(canBeABeanType).append(") as a Bean").toString());
            throw th4;
        } else if (ClassUtil.isProxyType(cls2)) {
            Throwable th5 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Can not deserialize Proxy class ").append(cls2.getName()).append(" as a Bean").toString());
            throw th5;
        } else {
            String isLocalType = ClassUtil.isLocalType(cls2, true);
            if (isLocalType == null) {
                return true;
            }
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can not deserialize Class ").append(cls2.getName()).append(" (of type ").append(isLocalType).append(") as a Bean").toString());
            throw th6;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanDescription beanDescription, Class<?> cls, Map<Class<?>, Boolean> map) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        Class<?> cls2 = cls;
        Boolean bool = map.get(cls2);
        if (bool == null) {
            bool = deserializationConfig2.getAnnotationIntrospector().isIgnorableType(deserializationConfig2.introspectClassAnnotations(cls2).getClassInfo());
            if (bool == null) {
                bool = Boolean.FALSE;
            }
        }
        return bool.booleanValue();
    }
}
