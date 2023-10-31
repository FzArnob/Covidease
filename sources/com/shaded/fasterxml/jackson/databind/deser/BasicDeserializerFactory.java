package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.databind.AbstractTypeResolver;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.shaded.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.JacksonDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.EnumResolver;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
    private static final Class<?> CLASS_CHAR_BUFFER = CharSequence.class;
    private static final Class<?> CLASS_ITERABLE = Iterable.class;
    private static final Class<?> CLASS_OBJECT = Object.class;
    private static final Class<?> CLASS_STRING = String.class;
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    static final HashMap<String, Class<? extends Map>> _mapFallbacks;
    protected final DeserializerFactoryConfig _factoryConfig;

    /* access modifiers changed from: protected */
    public abstract DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig);

    static {
        HashMap<String, Class<? extends Map>> hashMap;
        StringBuilder sb;
        HashMap<String, Class<? extends Collection>> hashMap2;
        new HashMap<>();
        _mapFallbacks = hashMap;
        Class<? extends Map> put = _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        Class<? extends Map> put2 = _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        Class<? extends Map> put3 = _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        Class<? extends Map> put4 = _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            Class<? extends Map> put5 = _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        } catch (Throwable th) {
            Throwable th2 = th;
            PrintStream printStream = System.err;
            new StringBuilder();
            printStream.println(sb.append("Problems with (optional) types: ").append(th2).toString());
        }
        new HashMap<>();
        _collectionFallbacks = hashMap2;
        Class<? extends Collection> put6 = _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        Class<? extends Collection> put7 = _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        Class<? extends Collection> put8 = _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        Class<? extends Collection> put9 = _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        Class<? extends Collection> put10 = _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        Class<? extends Collection> put11 = _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        Class<? extends Collection> put12 = _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
    }

    protected BasicDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        this._factoryConfig = deserializerFactoryConfig;
    }

    public DeserializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers deserializers) {
        return withConfig(this._factoryConfig.withAdditionalDeserializers(deserializers));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(keyDeserializers));
    }

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        return withConfig(this._factoryConfig.withDeserializerModifier(beanDeserializerModifier));
    }

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        return withConfig(this._factoryConfig.withAbstractTypeResolver(abstractTypeResolver));
    }

    public final DeserializerFactory withValueInstantiators(ValueInstantiators valueInstantiators) {
        return withConfig(this._factoryConfig.withValueInstantiators(valueInstantiators));
    }

    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType _mapAbstractType2;
        Throwable th;
        StringBuilder sb;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        while (true) {
            _mapAbstractType2 = _mapAbstractType2(deserializationConfig2, javaType2);
            if (_mapAbstractType2 == null) {
                return javaType2;
            }
            Class<?> rawClass = javaType2.getRawClass();
            Class<?> rawClass2 = _mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Invalid abstract type resolution from ").append(javaType2).append(" to ").append(_mapAbstractType2).append(": latter is not a subtype of former").toString());
            } else {
                javaType2 = _mapAbstractType2;
            }
        }
        Throwable th22 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Invalid abstract type resolution from ").append(javaType2).append(" to ").append(_mapAbstractType2).append(": latter is not a subtype of former").toString());
        throw th22;
    }

    private JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        Class<?> rawClass = javaType2.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            for (AbstractTypeResolver findTypeMapping : this._factoryConfig.abstractTypeResolvers()) {
                JavaType findTypeMapping2 = findTypeMapping.findTypeMapping(deserializationConfig2, javaType2);
                if (findTypeMapping2 != null && findTypeMapping2.getRawClass() != rawClass) {
                    return findTypeMapping2;
                }
            }
        }
        return null;
    }

    public ValueInstantiator findValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        DeserializationConfig config = deserializationContext2.getConfig();
        ValueInstantiator valueInstantiator = null;
        AnnotatedClass classInfo = beanDescription2.getClassInfo();
        Object findValueInstantiator = deserializationContext2.getAnnotationIntrospector().findValueInstantiator(classInfo);
        if (findValueInstantiator != null) {
            valueInstantiator = _valueInstantiatorInstance(config, classInfo, findValueInstantiator);
        }
        if (valueInstantiator == null) {
            valueInstantiator = _findStdValueInstantiator(config, beanDescription2);
            if (valueInstantiator == null) {
                valueInstantiator = _constructDefaultValueInstantiator(deserializationContext2, beanDescription2);
            }
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            for (ValueInstantiators next : this._factoryConfig.valueInstantiators()) {
                valueInstantiator = next.findValueInstantiator(config, beanDescription2, valueInstantiator);
                if (valueInstantiator == null) {
                    Throwable th3 = th2;
                    new StringBuilder();
                    new JsonMappingException(sb2.append("Broken registered ValueInstantiators (of type ").append(next.getClass().getName()).append("): returned null ValueInstantiator").toString());
                    throw th3;
                }
            }
        }
        if (valueInstantiator.getIncompleteParameter() == null) {
            return valueInstantiator;
        }
        AnnotatedParameter incompleteParameter = valueInstantiator.getIncompleteParameter();
        AnnotatedWithParams owner = incompleteParameter.getOwner();
        Throwable th4 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Argument #").append(incompleteParameter.getIndex()).append(" of constructor ").append(owner).append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator").toString());
        throw th4;
    }

    private ValueInstantiator _findStdValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        return JacksonDeserializers.findValueInstantiator(deserializationConfig, beanDescription);
    }

    /* access modifiers changed from: protected */
    public ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        CreatorCollector creatorCollector;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        new CreatorCollector(beanDescription2, deserializationContext2.canOverrideAccessModifiers());
        CreatorCollector creatorCollector2 = creatorCollector;
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        DeserializationConfig config = deserializationContext2.getConfig();
        VisibilityChecker<?> findAutoDetectVisibility = annotationIntrospector.findAutoDetectVisibility(beanDescription2.getClassInfo(), config.getDefaultVisibilityChecker());
        _addDeserializerFactoryMethods(deserializationContext2, beanDescription2, findAutoDetectVisibility, annotationIntrospector, creatorCollector2);
        if (beanDescription2.getType().isConcrete()) {
            _addDeserializerConstructors(deserializationContext2, beanDescription2, findAutoDetectVisibility, annotationIntrospector, creatorCollector2);
        }
        return creatorCollector2.constructValueInstantiator(config);
    }

    public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig deserializationConfig, Annotated annotated, Object obj) throws JsonMappingException {
        ValueInstantiator valueInstantiatorInstance;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        Annotated annotated2 = annotated;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof ValueInstantiator) {
            return (ValueInstantiator) obj2;
        }
        if (!(obj2 instanceof Class)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("AnnotationIntrospector returned key deserializer definition of type ").append(obj2.getClass().getName()).append("; expected type KeyDeserializer or Class<KeyDeserializer> instead").toString());
            throw th3;
        }
        Class<NoClass> cls = (Class) obj2;
        if (cls == NoClass.class) {
            return null;
        }
        if (!ValueInstantiator.class.isAssignableFrom(cls)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("AnnotationIntrospector returned Class ").append(cls.getName()).append("; expected Class<ValueInstantiator>").toString());
            throw th4;
        }
        HandlerInstantiator handlerInstantiator = deserializationConfig2.getHandlerInstantiator();
        if (handlerInstantiator == null || (valueInstantiatorInstance = handlerInstantiator.valueInstantiatorInstance(deserializationConfig2, annotated2, cls)) == null) {
            return (ValueInstantiator) ClassUtil.createInstance(cls, deserializationConfig2.canOverrideAccessModifiers());
        }
        return valueInstantiatorInstance;
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerConstructors(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        AnnotatedConstructor findDefaultConstructor = beanDescription2.findDefaultConstructor();
        if (findDefaultConstructor != null && (!creatorCollector2.hasDefaultCreator() || annotationIntrospector2.hasCreatorAnnotation(findDefaultConstructor))) {
            creatorCollector2.setDefaultCreator(findDefaultConstructor);
        }
        String[] strArr = null;
        AnnotatedConstructor annotatedConstructor = null;
        for (BeanPropertyDefinition next : beanDescription2.findProperties()) {
            if (next.getConstructorParameter() != null) {
                AnnotatedParameter constructorParameter = next.getConstructorParameter();
                AnnotatedWithParams owner = constructorParameter.getOwner();
                if (owner instanceof AnnotatedConstructor) {
                    if (annotatedConstructor == null) {
                        annotatedConstructor = (AnnotatedConstructor) owner;
                        strArr = new String[annotatedConstructor.getParameterCount()];
                    }
                    strArr[constructorParameter.getIndex()] = next.getName();
                }
            }
        }
        Iterator<AnnotatedConstructor> it = beanDescription2.getConstructors().iterator();
        while (it.hasNext()) {
            AnnotatedConstructor next2 = it.next();
            int parameterCount = next2.getParameterCount();
            boolean z = annotationIntrospector2.hasCreatorAnnotation(next2) || next2 == annotatedConstructor;
            boolean isCreatorVisible = visibilityChecker2.isCreatorVisible((AnnotatedMember) next2);
            if (parameterCount == 1) {
                boolean _handleSingleArgumentConstructor = _handleSingleArgumentConstructor(deserializationContext2, beanDescription2, visibilityChecker2, annotationIntrospector2, creatorCollector2, next2, z, isCreatorVisible, next2 == annotatedConstructor ? strArr[0] : null);
            } else if (z || isCreatorVisible) {
                AnnotatedParameter annotatedParameter = null;
                int i = 0;
                int i2 = 0;
                CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                for (int i3 = 0; i3 < parameterCount; i3++) {
                    AnnotatedParameter parameter = next2.getParameter(i3);
                    String str = null;
                    if (next2 == annotatedConstructor) {
                        str = strArr[i3];
                    }
                    if (str == null) {
                        PropertyName findNameForDeserialization = parameter == null ? null : annotationIntrospector2.findNameForDeserialization(parameter);
                        str = findNameForDeserialization == null ? null : findNameForDeserialization.getSimpleName();
                    }
                    Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter);
                    if (str != null && str.length() > 0) {
                        i++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext2, beanDescription2, str, i3, parameter, findInjectableValueId);
                    } else if (findInjectableValueId != null) {
                        i2++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext2, beanDescription2, str, i3, parameter, findInjectableValueId);
                    } else if (annotatedParameter == null) {
                        annotatedParameter = parameter;
                    }
                }
                if (z || i > 0 || i2 > 0) {
                    if (i + i2 == parameterCount) {
                        creatorCollector2.addPropertyCreator(next2, creatorPropertyArr);
                    } else if (i == 0 && i2 + 1 == parameterCount) {
                        creatorCollector2.addDelegatingCreator(next2, creatorPropertyArr);
                    } else {
                        creatorCollector2.addIncompeteParameter(annotatedParameter);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentConstructor(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedConstructor annotatedConstructor, boolean z, boolean z2, String str) throws JsonMappingException {
        String simpleName;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        AnnotatedConstructor annotatedConstructor2 = annotatedConstructor;
        boolean z3 = z;
        boolean z4 = z2;
        String str2 = str;
        AnnotatedParameter parameter = annotatedConstructor2.getParameter(0);
        if (str2 == null) {
            PropertyName findNameForDeserialization = parameter == null ? null : annotationIntrospector2.findNameForDeserialization(parameter);
            if (findNameForDeserialization == null) {
                simpleName = null;
            } else {
                simpleName = findNameForDeserialization.getSimpleName();
            }
            str2 = simpleName;
        }
        Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter);
        if (findInjectableValueId != null || (str2 != null && str2.length() > 0)) {
            creatorCollector2.addPropertyCreator(annotatedConstructor2, new CreatorProperty[]{constructCreatorProperty(deserializationContext2, beanDescription2, str2, 0, parameter, findInjectableValueId)});
            return true;
        }
        Class<?> rawParameterType = annotatedConstructor2.getRawParameterType(0);
        if (rawParameterType == String.class) {
            if (z3 || z4) {
                creatorCollector2.addStringCreator(annotatedConstructor2);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z3 || z4) {
                creatorCollector2.addIntCreator(annotatedConstructor2);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z3 || z4) {
                creatorCollector2.addLongCreator(annotatedConstructor2);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z3 || z4) {
                creatorCollector2.addDoubleCreator(annotatedConstructor2);
            }
            return true;
        } else if (!z3) {
            return false;
        } else {
            creatorCollector2.addDelegatingCreator(annotatedConstructor2, (CreatorProperty[]) null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerFactoryMethods(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        DeserializationConfig config = deserializationContext2.getConfig();
        for (AnnotatedMethod next : beanDescription2.getFactoryMethods()) {
            boolean hasCreatorAnnotation = annotationIntrospector2.hasCreatorAnnotation(next);
            int parameterCount = next.getParameterCount();
            if (parameterCount != 0) {
                if (parameterCount == 1) {
                    AnnotatedParameter parameter = next.getParameter(0);
                    PropertyName findNameForDeserialization = parameter == null ? null : annotationIntrospector2.findNameForDeserialization(parameter);
                    String simpleName = findNameForDeserialization == null ? null : findNameForDeserialization.getSimpleName();
                    if (annotationIntrospector2.findInjectableValueId(parameter) == null && (simpleName == null || simpleName.length() == 0)) {
                        boolean _handleSingleArgumentFactory = _handleSingleArgumentFactory(config, beanDescription2, visibilityChecker2, annotationIntrospector2, creatorCollector2, next, hasCreatorAnnotation);
                    }
                } else if (!annotationIntrospector2.hasCreatorAnnotation(next)) {
                    continue;
                }
                AnnotatedParameter annotatedParameter = null;
                CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                int i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < parameterCount; i3++) {
                    AnnotatedParameter parameter2 = next.getParameter(i3);
                    PropertyName findNameForDeserialization2 = parameter2 == null ? null : annotationIntrospector2.findNameForDeserialization(parameter2);
                    String simpleName2 = findNameForDeserialization2 == null ? null : findNameForDeserialization2.getSimpleName();
                    Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter2);
                    if (simpleName2 != null && simpleName2.length() > 0) {
                        i++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext2, beanDescription2, simpleName2, i3, parameter2, findInjectableValueId);
                    } else if (findInjectableValueId != null) {
                        i2++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext2, beanDescription2, simpleName2, i3, parameter2, findInjectableValueId);
                    } else if (annotatedParameter == null) {
                        annotatedParameter = parameter2;
                    }
                }
                if (hasCreatorAnnotation || i > 0 || i2 > 0) {
                    if (i + i2 == parameterCount) {
                        creatorCollector2.addPropertyCreator(next, creatorPropertyArr);
                    } else if (i == 0 && i2 + 1 == parameterCount) {
                        creatorCollector2.addDelegatingCreator(next, creatorPropertyArr);
                    } else {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("Argument #").append(annotatedParameter.getIndex()).append(" of factory method ").append(next).append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator").toString());
                        throw th2;
                    }
                }
            } else if (hasCreatorAnnotation) {
                creatorCollector2.setDefaultCreator(next);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentFactory(DeserializationConfig deserializationConfig, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedMethod annotatedMethod, boolean z) throws JsonMappingException {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        boolean z2 = z;
        Class<?> rawParameterType = annotatedMethod2.getRawParameterType(0);
        if (rawParameterType == String.class) {
            if (z2 || visibilityChecker2.isCreatorVisible((AnnotatedMember) annotatedMethod2)) {
                creatorCollector2.addStringCreator(annotatedMethod2);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z2 || visibilityChecker2.isCreatorVisible((AnnotatedMember) annotatedMethod2)) {
                creatorCollector2.addIntCreator(annotatedMethod2);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z2 || visibilityChecker2.isCreatorVisible((AnnotatedMember) annotatedMethod2)) {
                creatorCollector2.addLongCreator(annotatedMethod2);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z2 || visibilityChecker2.isCreatorVisible((AnnotatedMember) annotatedMethod2)) {
                creatorCollector2.addDoubleCreator(annotatedMethod2);
            }
            return true;
        } else if (rawParameterType == Boolean.TYPE || rawParameterType == Boolean.class) {
            if (z2 || visibilityChecker2.isCreatorVisible((AnnotatedMember) annotatedMethod2)) {
                creatorCollector2.addBooleanCreator(annotatedMethod2);
            }
            return true;
        } else if (!annotationIntrospector2.hasCreatorAnnotation(annotatedMethod2)) {
            return false;
        } else {
            creatorCollector2.addDelegatingCreator(annotatedMethod2, (CreatorProperty[]) null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public CreatorProperty constructCreatorProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, String str, int i, AnnotatedParameter annotatedParameter, Object obj) throws JsonMappingException {
        BeanProperty.Std std;
        CreatorProperty creatorProperty;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        String str2 = str;
        int i2 = i;
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        Object obj2 = obj;
        DeserializationConfig config = deserializationContext2.getConfig();
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        Boolean hasRequiredMarker = annotationIntrospector == null ? null : annotationIntrospector.hasRequiredMarker(annotatedParameter2);
        boolean booleanValue = hasRequiredMarker == null ? false : hasRequiredMarker.booleanValue();
        JavaType constructType = config.getTypeFactory().constructType(annotatedParameter2.getParameterType(), beanDescription2.bindingsForBeanType());
        new BeanProperty.Std(str2, constructType, annotationIntrospector.findWrapperName(annotatedParameter2), beanDescription2.getClassAnnotations(), annotatedParameter2, booleanValue);
        BeanProperty.Std std2 = std;
        JavaType resolveType = resolveType(deserializationContext2, beanDescription2, constructType, annotatedParameter2);
        if (resolveType != constructType) {
            std2 = std2.withType(resolveType);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, annotatedParameter2);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext2, annotatedParameter2, resolveType);
        TypeDeserializer typeDeserializer = (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, modifyTypeByAnnotation);
        }
        new CreatorProperty(str2, modifyTypeByAnnotation, std2.getWrapperName(), typeDeserializer, beanDescription2.getClassAnnotations(), annotatedParameter2, i2, obj2, std2.isRequired());
        CreatorProperty creatorProperty2 = creatorProperty;
        if (findDeserializerFromAnnotation != null) {
            creatorProperty2 = creatorProperty2.withValueDeserializer((JsonDeserializer<?>) findDeserializerFromAnnotation);
        }
        return creatorProperty2;
    }

    public JsonDeserializer<?> createArrayDeserializer(DeserializationContext deserializationContext, ArrayType arrayType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        ArrayType arrayType2 = arrayType;
        BeanDescription beanDescription2 = beanDescription;
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType contentType = arrayType2.getContentType();
        JsonDeserializer jsonDeserializer2 = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType2, config, beanDescription2, typeDeserializer, jsonDeserializer2);
        if (_findCustomArrayDeserializer == null) {
            if (jsonDeserializer2 == null) {
                Class<?> rawClass = contentType.getRawClass();
                if (contentType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(rawClass);
                }
                if (rawClass == String.class) {
                    return StringArrayDeserializer.instance;
                }
            }
            if (_findCustomArrayDeserializer == null) {
                new ObjectArrayDeserializer(arrayType2, jsonDeserializer2, typeDeserializer);
                _findCustomArrayDeserializer = jsonDeserializer;
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyArrayDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomArrayDeserializer = modifyArrayDeserializer.modifyArrayDeserializer(config, arrayType2, beanDescription2, _findCustomArrayDeserializer);
            }
        }
        return _findCustomArrayDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        ArrayType arrayType2 = arrayType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        for (Deserializers findArrayDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findArrayDeserializer2 = findArrayDeserializer.findArrayDeserializer(arrayType2, deserializationConfig2, beanDescription2, typeDeserializer2, jsonDeserializer2);
            if (findArrayDeserializer2 != null) {
                return findArrayDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext deserializationContext, CollectionType collectionType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        JsonDeserializer<?> jsonDeserializer2;
        JsonDeserializer<?> jsonDeserializer3;
        Throwable th;
        StringBuilder sb;
        JsonDeserializer<?> jsonDeserializer4;
        DeserializationContext deserializationContext2 = deserializationContext;
        CollectionType collectionType2 = collectionType;
        BeanDescription beanDescription2 = beanDescription;
        JavaType contentType = collectionType2.getContentType();
        JsonDeserializer jsonDeserializer5 = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext2.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType2, config, beanDescription2, typeDeserializer, jsonDeserializer5);
        if (_findCustomCollectionDeserializer == null) {
            Class<?> rawClass = collectionType2.getRawClass();
            if (jsonDeserializer5 == null && EnumSet.class.isAssignableFrom(rawClass)) {
                new EnumSetDeserializer(contentType, (JsonDeserializer<?>) null);
                _findCustomCollectionDeserializer = jsonDeserializer4;
            }
        }
        if (_findCustomCollectionDeserializer == null) {
            if (collectionType2.isInterface() || collectionType2.isAbstract()) {
                CollectionType _mapAbstractCollectionType = _mapAbstractCollectionType(collectionType2, config);
                if (_mapAbstractCollectionType == null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Can not find a deserializer for non-concrete Collection type ").append(collectionType2).toString());
                    throw th2;
                }
                collectionType2 = _mapAbstractCollectionType;
                beanDescription2 = config.introspectForCreation(collectionType2);
            }
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext2, beanDescription2);
            if (!findValueInstantiator.canCreateUsingDefault() && collectionType2.getRawClass() == ArrayBlockingQueue.class) {
                new ArrayBlockingQueueDeserializer(collectionType2, jsonDeserializer5, typeDeserializer, findValueInstantiator, (JsonDeserializer<Object>) null);
                return jsonDeserializer3;
            } else if (contentType.getRawClass() == String.class) {
                new StringCollectionDeserializer(collectionType2, jsonDeserializer5, findValueInstantiator);
                _findCustomCollectionDeserializer = jsonDeserializer2;
            } else {
                new CollectionDeserializer(collectionType2, jsonDeserializer5, typeDeserializer, findValueInstantiator);
                _findCustomCollectionDeserializer = jsonDeserializer;
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionDeserializer = modifyCollectionDeserializer.modifyCollectionDeserializer(config, collectionType2, beanDescription2, _findCustomCollectionDeserializer);
            }
        }
        return _findCustomCollectionDeserializer;
    }

    /* access modifiers changed from: protected */
    public CollectionType _mapAbstractCollectionType(JavaType javaType, DeserializationConfig deserializationConfig) {
        JavaType javaType2 = javaType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        Class cls = _collectionFallbacks.get(javaType2.getRawClass().getName());
        if (cls == null) {
            return null;
        }
        return (CollectionType) deserializationConfig2.constructSpecializedType(javaType2, cls);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        CollectionType collectionType2 = collectionType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        for (Deserializers findCollectionDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionDeserializer2 = findCollectionDeserializer.findCollectionDeserializer(collectionType2, deserializationConfig2, beanDescription2, typeDeserializer2, jsonDeserializer2);
            if (findCollectionDeserializer2 != null) {
                return findCollectionDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext deserializationContext, CollectionLikeType collectionLikeType, BeanDescription beanDescription) throws JsonMappingException {
        CollectionLikeType collectionLikeType2 = collectionLikeType;
        BeanDescription beanDescription2 = beanDescription;
        JavaType contentType = collectionLikeType2.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomCollectionLikeDeserializer = _findCustomCollectionLikeDeserializer(collectionLikeType2, config, beanDescription2, typeDeserializer, jsonDeserializer);
        if (_findCustomCollectionLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionLikeDeserializer = modifyCollectionLikeDeserializer.modifyCollectionLikeDeserializer(config, collectionLikeType2, beanDescription2, _findCustomCollectionLikeDeserializer);
            }
        }
        return _findCustomCollectionLikeDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        CollectionLikeType collectionLikeType2 = collectionLikeType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        for (Deserializers findCollectionLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionLikeDeserializer2 = findCollectionLikeDeserializer.findCollectionLikeDeserializer(collectionLikeType2, deserializationConfig2, beanDescription2, typeDeserializer2, jsonDeserializer2);
            if (findCollectionLikeDeserializer2 != null) {
                return findCollectionLikeDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createMapDeserializer(DeserializationContext deserializationContext, MapType mapType, BeanDescription beanDescription) throws JsonMappingException {
        MapDeserializer mapDeserializer;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        JsonDeserializer<?> jsonDeserializer;
        DeserializationContext deserializationContext2 = deserializationContext;
        MapType mapType2 = mapType;
        BeanDescription beanDescription2 = beanDescription;
        DeserializationConfig config = deserializationContext2.getConfig();
        JavaType keyType = mapType2.getKeyType();
        JavaType contentType = mapType2.getContentType();
        JsonDeserializer jsonDeserializer2 = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        MapDeserializer _findCustomMapDeserializer = _findCustomMapDeserializer(mapType2, config, beanDescription2, keyDeserializer, typeDeserializer, jsonDeserializer2);
        if (_findCustomMapDeserializer == null) {
            Class<?> rawClass = mapType2.getRawClass();
            if (EnumMap.class.isAssignableFrom(rawClass)) {
                Class<?> rawClass2 = keyType.getRawClass();
                if (rawClass2 == null || !rawClass2.isEnum()) {
                    Throwable th3 = th2;
                    new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
                    throw th3;
                }
                new EnumMapDeserializer(mapType2, (JsonDeserializer<?>) null, jsonDeserializer2, typeDeserializer);
                _findCustomMapDeserializer = jsonDeserializer;
            }
            if (_findCustomMapDeserializer == null) {
                if (mapType2.isInterface() || mapType2.isAbstract()) {
                    Class cls = _mapFallbacks.get(rawClass.getName());
                    if (cls == null) {
                        Throwable th4 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("Can not find a deserializer for non-concrete Map type ").append(mapType2).toString());
                        throw th4;
                    }
                    mapType2 = (MapType) config.constructSpecializedType(mapType2, cls);
                    beanDescription2 = config.introspectForCreation(mapType2);
                }
                new MapDeserializer((JavaType) mapType2, findValueInstantiator(deserializationContext2, beanDescription2), keyDeserializer, (JsonDeserializer<Object>) jsonDeserializer2, typeDeserializer);
                MapDeserializer mapDeserializer2 = mapDeserializer;
                mapDeserializer2.setIgnorableProperties(config.getAnnotationIntrospector().findPropertiesToIgnore(beanDescription2.getClassInfo()));
                _findCustomMapDeserializer = mapDeserializer2;
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyMapDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomMapDeserializer = modifyMapDeserializer.modifyMapDeserializer(config, mapType2, beanDescription2, _findCustomMapDeserializer);
            }
        }
        return _findCustomMapDeserializer;
    }

    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext deserializationContext, MapLikeType mapLikeType, BeanDescription beanDescription) throws JsonMappingException {
        MapLikeType mapLikeType2 = mapLikeType;
        BeanDescription beanDescription2 = beanDescription;
        JavaType keyType = mapLikeType2.getKeyType();
        JavaType contentType = mapLikeType2.getContentType();
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomMapLikeDeserializer = _findCustomMapLikeDeserializer(mapLikeType2, config, beanDescription2, keyDeserializer, typeDeserializer, jsonDeserializer);
        if (_findCustomMapLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyMapLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomMapLikeDeserializer = modifyMapLikeDeserializer.modifyMapLikeDeserializer(config, mapLikeType2, beanDescription2, _findCustomMapLikeDeserializer);
            }
        }
        return _findCustomMapLikeDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        MapType mapType2 = mapType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        for (Deserializers findMapDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapDeserializer2 = findMapDeserializer.findMapDeserializer(mapType2, deserializationConfig2, beanDescription2, keyDeserializer2, typeDeserializer2, jsonDeserializer2);
            if (findMapDeserializer2 != null) {
                return findMapDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        MapLikeType mapLikeType2 = mapLikeType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        for (Deserializers findMapLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapLikeDeserializer2 = findMapLikeDeserializer.findMapLikeDeserializer(mapLikeType2, deserializationConfig2, beanDescription2, keyDeserializer2, typeDeserializer2, jsonDeserializer2);
            if (findMapLikeDeserializer2 != null) {
                return findMapLikeDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        DeserializationConfig config = deserializationContext2.getConfig();
        Class<?> rawClass = javaType2.getRawClass();
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, beanDescription2);
        if (_findCustomEnumDeserializer == null) {
            Iterator<AnnotatedMethod> it = beanDescription2.getFactoryMethods().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnnotatedMethod next = it.next();
                if (deserializationContext2.getAnnotationIntrospector().hasCreatorAnnotation(next)) {
                    if (next.getParameterCount() != 1 || !next.getRawReturnType().isAssignableFrom(rawClass)) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("Unsuitable method (").append(next).append(") decorated with @JsonCreator (for Enum type ").append(rawClass.getName()).append(")").toString());
                        throw th2;
                    }
                    _findCustomEnumDeserializer = EnumDeserializer.deserializerForCreator(config, rawClass, next);
                }
            }
            if (_findCustomEnumDeserializer == null) {
                new EnumDeserializer(constructEnumResolver(rawClass, config, beanDescription2.findJsonValueMethod()));
                _findCustomEnumDeserializer = jsonDeserializer;
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyEnumDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomEnumDeserializer = modifyEnumDeserializer.modifyEnumDeserializer(config, javaType2, beanDescription2, _findCustomEnumDeserializer);
            }
        }
        return _findCustomEnumDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Class<?> cls2 = cls;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        for (Deserializers findEnumDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findEnumDeserializer2 = findEnumDeserializer.findEnumDeserializer(cls2, deserializationConfig2, beanDescription2);
            if (findEnumDeserializer2 != null) {
                return findEnumDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanDescription);
        if (_findCustomTreeNodeDeserializer != null) {
            return _findCustomTreeNodeDeserializer;
        }
        return JsonNodeDeserializer.getDeserializer(rawClass);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Class<? extends JsonNode> cls2 = cls;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        for (Deserializers findTreeNodeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findTreeNodeDeserializer2 = findTreeNodeDeserializer.findTreeNodeDeserializer(cls2, deserializationConfig2, beanDescription2);
            if (findTreeNodeDeserializer2 != null) {
                return findTreeNodeDeserializer2;
            }
        }
        return null;
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType mapAbstractType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        AnnotatedClass classInfo = deserializationConfig2.introspectClassAnnotations(javaType2.getRawClass()).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig2.getAnnotationIntrospector();
        Object findTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig2, classInfo, javaType2);
        Collection<NamedType> collection = null;
        if (findTypeResolver == null) {
            findTypeResolver = deserializationConfig2.getDefaultTyper(javaType2);
            if (findTypeResolver == null) {
                return null;
            }
        } else {
            collection = deserializationConfig2.getSubtypeResolver().collectAndResolveSubtypes(classInfo, (MapperConfig<?>) deserializationConfig2, annotationIntrospector);
        }
        if (findTypeResolver.getDefaultImpl() == null && javaType2.isAbstract() && (mapAbstractType = mapAbstractType(deserializationConfig2, javaType2)) != null && mapAbstractType.getRawClass() != javaType2.getRawClass()) {
            findTypeResolver = findTypeResolver.defaultImpl(mapAbstractType.getRawClass());
        }
        return findTypeResolver.buildTypeDeserializer(deserializationConfig2, javaType2, collection);
    }

    public KeyDeserializer createKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        DeserializationConfig config = deserializationContext2.getConfig();
        KeyDeserializer keyDeserializer = null;
        if (this._factoryConfig.hasKeyDeserializers()) {
            BeanDescription introspectClassAnnotations = config.introspectClassAnnotations(javaType2.getRawClass());
            for (KeyDeserializers findKeyDeserializer : this._factoryConfig.keyDeserializers()) {
                keyDeserializer = findKeyDeserializer.findKeyDeserializer(javaType2, config, introspectClassAnnotations);
                if (keyDeserializer != null) {
                    break;
                }
            }
        }
        if (keyDeserializer == null) {
            if (javaType2.isEnumType()) {
                return _createEnumKeyDeserializer(deserializationContext2, javaType2);
            }
            keyDeserializer = StdKeyDeserializers.findStringBasedKeyDeserializer(config, javaType2);
        }
        if (keyDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyKeyDeserializer : this._factoryConfig.deserializerModifiers()) {
                keyDeserializer = modifyKeyDeserializer.modifyKeyDeserializer(config, javaType2, keyDeserializer);
            }
        }
        return keyDeserializer;
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        DeserializationConfig config = deserializationContext2.getConfig();
        BeanDescription introspect = config.introspect(javaType2);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType2, findDeserializerFromAnnotation);
        }
        Class<?> rawClass = javaType2.getRawClass();
        if (_findCustomEnumDeserializer(rawClass, config, introspect) != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType2, findDeserializerFromAnnotation);
        }
        EnumResolver<?> constructEnumResolver = constructEnumResolver(rawClass, config, introspect.findJsonValueMethod());
        for (AnnotatedMethod next : introspect.getFactoryMethods()) {
            if (config.getAnnotationIntrospector().hasCreatorAnnotation(next)) {
                if (next.getParameterCount() != 1 || !next.getRawReturnType().isAssignableFrom(rawClass)) {
                    Throwable th3 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Unsuitable method (").append(next).append(") decorated with @JsonCreator (for Enum type ").append(rawClass.getName()).append(")").toString());
                    throw th3;
                } else if (next.getGenericParameterType(0) != String.class) {
                    Throwable th4 = th2;
                    new StringBuilder();
                    new IllegalArgumentException(sb2.append("Parameter #0 type for factory method (").append(next).append(") not suitable, must be java.lang.String").toString());
                    throw th4;
                } else {
                    if (config.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(next.getMember());
                    }
                    return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver, next);
                }
            }
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        AnnotatedMember annotatedMember2 = annotatedMember;
        AnnotationIntrospector annotationIntrospector = deserializationConfig2.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig2, annotatedMember2, javaType2);
        if (findPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig2, javaType2);
        }
        return findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig2, javaType2, deserializationConfig2.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember2, deserializationConfig2, annotationIntrospector, javaType2));
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        AnnotatedMember annotatedMember2 = annotatedMember;
        AnnotationIntrospector annotationIntrospector = deserializationConfig2.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig2, annotatedMember2, javaType2);
        JavaType contentType = javaType2.getContentType();
        if (findPropertyContentTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig2, contentType);
        }
        return findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig2, contentType, deserializationConfig2.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember2, deserializationConfig2, annotationIntrospector, contentType));
    }

    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        Class<?> rawClass = javaType2.getRawClass();
        String name = rawClass.getName();
        if (rawClass.isPrimitive() || name.startsWith("java.")) {
            if (rawClass == CLASS_OBJECT) {
                return UntypedObjectDeserializer.instance;
            }
            if (rawClass == CLASS_STRING || rawClass == CLASS_CHAR_BUFFER) {
                return StringDeserializer.instance;
            }
            if (rawClass == CLASS_ITERABLE) {
                return createCollectionDeserializer(deserializationContext2, deserializationContext2.getTypeFactory().constructCollectionType((Class<? extends Collection>) Collection.class, javaType2.containedTypeCount() > 0 ? javaType2.containedType(0) : TypeFactory.unknownType()), beanDescription2);
            }
            JsonDeserializer<?> find = NumberDeserializers.find(rawClass, name);
            if (find == null) {
                find = DateDeserializers.find(rawClass, name);
                if (find == null) {
                    find = JdkDeserializers.find(rawClass, name);
                }
            }
            return find;
        } else if (name.startsWith("com.fasterxml.")) {
            return JacksonDeserializers.find(rawClass);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        Annotated annotated2 = annotated;
        Object findDeserializer = deserializationContext2.getAnnotationIntrospector().findDeserializer(annotated2);
        if (findDeserializer == null) {
            return null;
        }
        return deserializationContext2.deserializerInstance(annotated2, findDeserializer);
    }

    /* access modifiers changed from: protected */
    public <T extends JavaType> T modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, T t) throws JsonMappingException {
        JsonDeserializer<Object> deserializerInstance;
        Throwable th;
        StringBuilder sb;
        KeyDeserializer keyDeserializerInstance;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        DeserializationContext deserializationContext2 = deserializationContext;
        Annotated annotated2 = annotated;
        T t2 = t;
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        Class<?> findDeserializationType = annotationIntrospector.findDeserializationType(annotated2, t2);
        if (findDeserializationType != null) {
            try {
                t2 = t2.narrowBy(findDeserializationType);
            } catch (IllegalArgumentException e) {
                IllegalArgumentException illegalArgumentException = e;
                Throwable th5 = th4;
                new StringBuilder();
                new JsonMappingException(sb4.append("Failed to narrow type ").append(t2).append(" with concrete-type annotation (value ").append(findDeserializationType.getName()).append("), method '").append(annotated2.getName()).append("': ").append(illegalArgumentException.getMessage()).toString(), (JsonLocation) null, illegalArgumentException);
                throw th5;
            }
        }
        if (t2.isContainerType()) {
            Class<?> findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated2, t2.getKeyType());
            if (findDeserializationKeyType != null) {
                if (!(t2 instanceof MapLikeType)) {
                    Throwable th6 = th3;
                    new StringBuilder();
                    new JsonMappingException(sb3.append("Illegal key-type annotation: type ").append(t2).append(" is not a Map(-like) type").toString());
                    throw th6;
                }
                try {
                    t2 = ((MapLikeType) t2).narrowKey(findDeserializationKeyType);
                } catch (IllegalArgumentException e2) {
                    IllegalArgumentException illegalArgumentException2 = e2;
                    Throwable th7 = th2;
                    new StringBuilder();
                    new JsonMappingException(sb2.append("Failed to narrow key type ").append(t2).append(" with key-type annotation (").append(findDeserializationKeyType.getName()).append("): ").append(illegalArgumentException2.getMessage()).toString(), (JsonLocation) null, illegalArgumentException2);
                    throw th7;
                }
            }
            JavaType keyType = t2.getKeyType();
            if (!(keyType == null || keyType.getValueHandler() != null || (keyDeserializerInstance = deserializationContext2.keyDeserializerInstance(annotated2, annotationIntrospector.findKeyDeserializer(annotated2))) == null)) {
                t2 = ((MapLikeType) t2).withKeyValueHandler(keyDeserializerInstance);
                JavaType keyType2 = t2.getKeyType();
            }
            Class<?> findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated2, t2.getContentType());
            if (findDeserializationContentType != null) {
                try {
                    t2 = t2.narrowContentsBy(findDeserializationContentType);
                } catch (IllegalArgumentException e3) {
                    IllegalArgumentException illegalArgumentException3 = e3;
                    Throwable th8 = th;
                    new StringBuilder();
                    new JsonMappingException(sb.append("Failed to narrow content type ").append(t2).append(" with content-type annotation (").append(findDeserializationContentType.getName()).append("): ").append(illegalArgumentException3.getMessage()).toString(), (JsonLocation) null, illegalArgumentException3);
                    throw th8;
                }
            }
            if (t2.getContentType().getValueHandler() == null && (deserializerInstance = deserializationContext2.deserializerInstance(annotated2, annotationIntrospector.findContentDeserializer(annotated2))) != null) {
                t2 = t2.withContentValueHandler(deserializerInstance);
            }
        }
        return t2;
    }

    /* access modifiers changed from: protected */
    public JavaType resolveType(DeserializationContext deserializationContext, BeanDescription beanDescription, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        TypeDeserializer findTypeDeserializer;
        TypeDeserializer findPropertyContentTypeDeserializer;
        KeyDeserializer keyDeserializerInstance;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        MapLikeType mapLikeType = javaType;
        AnnotatedMember annotatedMember2 = annotatedMember;
        if (mapLikeType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
            if (!(mapLikeType.getKeyType() == null || (keyDeserializerInstance = deserializationContext2.keyDeserializerInstance(annotatedMember2, annotationIntrospector.findKeyDeserializer(annotatedMember2))) == null)) {
                mapLikeType = ((MapLikeType) mapLikeType).withKeyValueHandler(keyDeserializerInstance);
                JavaType keyType = mapLikeType.getKeyType();
            }
            JsonDeserializer<Object> deserializerInstance = deserializationContext2.deserializerInstance(annotatedMember2, annotationIntrospector.findContentDeserializer(annotatedMember2));
            if (deserializerInstance != null) {
                mapLikeType = mapLikeType.withContentValueHandler(deserializerInstance);
            }
            if ((annotatedMember2 instanceof AnnotatedMember) && (findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationContext2.getConfig(), mapLikeType, annotatedMember2)) != null) {
                mapLikeType = mapLikeType.withContentTypeHandler(findPropertyContentTypeDeserializer);
            }
        }
        if (annotatedMember2 instanceof AnnotatedMember) {
            findTypeDeserializer = findPropertyTypeDeserializer(deserializationContext2.getConfig(), mapLikeType, annotatedMember2);
        } else {
            findTypeDeserializer = findTypeDeserializer(deserializationContext2.getConfig(), mapLikeType);
        }
        if (findTypeDeserializer != null) {
            mapLikeType = mapLikeType.withTypeHandler(findTypeDeserializer);
        }
        return mapLikeType;
    }

    /* access modifiers changed from: protected */
    public EnumResolver<?> constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig, AnnotatedMethod annotatedMethod) {
        Class<?> cls2 = cls;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        if (annotatedMethod2 != null) {
            Method annotated = annotatedMethod2.getAnnotated();
            if (deserializationConfig2.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(annotated);
            }
            return EnumResolver.constructUnsafeUsingMethod(cls2, annotated);
        } else if (deserializationConfig2.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {
            return EnumResolver.constructUnsafeUsingToString(cls2);
        } else {
            return EnumResolver.constructUnsafe(cls2, deserializationConfig2.getAnnotationIntrospector());
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _findJsonValueFor(DeserializationConfig deserializationConfig, JavaType javaType) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        if (javaType2 == null) {
            return null;
        }
        return deserializationConfig2.introspect(javaType2).findJsonValueMethod();
    }
}
