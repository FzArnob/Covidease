package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.type.TypeBindings;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription extends BeanDescription {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected AnnotatedMember _anyGetter;
    protected AnnotatedMethod _anySetterMethod;
    protected TypeBindings _bindings;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig<?> _config;
    protected Set<String> _ignoredPropertyNames;
    protected Map<Object, AnnotatedMember> _injectables;
    protected AnnotatedMethod _jsonValueMethod;
    protected ObjectIdInfo _objectIdInfo;
    protected final List<BeanPropertyDefinition> _properties;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BasicBeanDescription(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass, List<BeanPropertyDefinition> list) {
        super(javaType);
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedClass annotatedClass2 = annotatedClass;
        List<BeanPropertyDefinition> list2 = list;
        this._config = mapperConfig2;
        this._annotationIntrospector = mapperConfig2 == null ? null : mapperConfig2.getAnnotationIntrospector();
        this._classInfo = annotatedClass2;
        this._properties = list2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BasicBeanDescription(com.shaded.fasterxml.jackson.databind.introspect.POJOPropertiesCollector r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.cfg.MapperConfig r3 = r3.getConfig()
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4.getType()
            r5 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass r5 = r5.getClassDef()
            r6 = r1
            java.util.List r6 = r6.getProperties()
            r2.<init>(r3, r4, r5, r6)
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo r3 = r3.getObjectIdInfo()
            r2._objectIdInfo = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.BasicBeanDescription.<init>(com.shaded.fasterxml.jackson.databind.introspect.POJOPropertiesCollector):void");
    }

    public static BasicBeanDescription forDeserialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription;
        POJOPropertiesCollector pOJOPropertiesCollector2 = pOJOPropertiesCollector;
        new BasicBeanDescription(pOJOPropertiesCollector2);
        BasicBeanDescription basicBeanDescription2 = basicBeanDescription;
        basicBeanDescription2._anySetterMethod = pOJOPropertiesCollector2.getAnySetterMethod();
        basicBeanDescription2._ignoredPropertyNames = pOJOPropertiesCollector2.getIgnoredPropertyNames();
        basicBeanDescription2._injectables = pOJOPropertiesCollector2.getInjectables();
        basicBeanDescription2._jsonValueMethod = pOJOPropertiesCollector2.getJsonValueMethod();
        return basicBeanDescription2;
    }

    public static BasicBeanDescription forSerialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription;
        POJOPropertiesCollector pOJOPropertiesCollector2 = pOJOPropertiesCollector;
        new BasicBeanDescription(pOJOPropertiesCollector2);
        BasicBeanDescription basicBeanDescription2 = basicBeanDescription;
        basicBeanDescription2._jsonValueMethod = pOJOPropertiesCollector2.getJsonValueMethod();
        basicBeanDescription2._anyGetter = pOJOPropertiesCollector2.getAnyGetter();
        return basicBeanDescription2;
    }

    public static BasicBeanDescription forOtherUse(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        BasicBeanDescription basicBeanDescription;
        new BasicBeanDescription(mapperConfig, javaType, annotatedClass, Collections.emptyList());
        return basicBeanDescription;
    }

    public boolean removeProperty(String str) {
        String str2 = str;
        Iterator<BeanPropertyDefinition> it = this._properties.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(str2)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public AnnotatedClass getClassInfo() {
        return this._classInfo;
    }

    public ObjectIdInfo getObjectIdInfo() {
        return this._objectIdInfo;
    }

    public List<BeanPropertyDefinition> findProperties() {
        return this._properties;
    }

    public AnnotatedMethod findJsonValueMethod() {
        return this._jsonValueMethod;
    }

    public Set<String> getIgnoredPropertyNames() {
        if (this._ignoredPropertyNames == null) {
            return Collections.emptySet();
        }
        return this._ignoredPropertyNames;
    }

    public boolean hasKnownClassAnnotations() {
        return this._classInfo.hasAnnotations();
    }

    public Annotations getClassAnnotations() {
        return this._classInfo.getAnnotations();
    }

    public TypeBindings bindingsForBeanType() {
        TypeBindings typeBindings;
        if (this._bindings == null) {
            new TypeBindings(this._config.getTypeFactory(), this._type);
            this._bindings = typeBindings;
        }
        return this._bindings;
    }

    public JavaType resolveType(Type type) {
        Type type2 = type;
        if (type2 == null) {
            return null;
        }
        return bindingsForBeanType().resolveType(type2);
    }

    public AnnotatedConstructor findDefaultConstructor() {
        return this._classInfo.getDefaultConstructor();
    }

    public AnnotatedMethod findAnySetter() throws IllegalArgumentException {
        Class<?> rawParameterType;
        Throwable th;
        StringBuilder sb;
        if (this._anySetterMethod == null || (rawParameterType = this._anySetterMethod.getRawParameterType(0)) == String.class || rawParameterType == Object.class) {
            return this._anySetterMethod;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Invalid 'any-setter' annotation on method ").append(this._anySetterMethod.getName()).append("(): first argument not of type String or Object, but ").append(rawParameterType.getName()).toString());
        throw th2;
    }

    public Map<Object, AnnotatedMember> findInjectables() {
        return this._injectables;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._classInfo.getConstructors();
    }

    public Object instantiateBean(boolean z) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        boolean z2 = z;
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        if (defaultConstructor == null) {
            return null;
        }
        if (z2) {
            defaultConstructor.fixAccess();
        }
        try {
            return defaultConstructor.getAnnotated().newInstance(new Object[0]);
        } catch (Exception e) {
            Throwable th3 = e;
            while (true) {
                th = th3;
                if (th.getCause() == null) {
                    break;
                }
                th3 = th.getCause();
            }
            if (th instanceof Error) {
                throw ((Error) th);
            } else if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            } else {
                Throwable th4 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Failed to instantiate bean of type ").append(this._classInfo.getAnnotated().getName()).append(": (").append(th.getClass().getName()).append(") ").append(th.getMessage()).toString(), th);
                throw th4;
            }
        }
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._classInfo.findMethod(str, clsArr);
    }

    public JsonFormat.Value findExpectedFormat(JsonFormat.Value value) {
        JsonFormat.Value findFormat;
        JsonFormat.Value value2 = value;
        if (this._annotationIntrospector == null || (findFormat = this._annotationIntrospector.findFormat((Annotated) this._classInfo)) == null) {
            return value2;
        }
        return findFormat;
    }

    public Converter<Object, Object> findSerializationConverter() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        return _createConverter(this._annotationIntrospector.findSerializationConverter(this._classInfo));
    }

    public JsonInclude.Include findSerializationInclusion(JsonInclude.Include include) {
        JsonInclude.Include include2 = include;
        if (this._annotationIntrospector == null) {
            return include2;
        }
        return this._annotationIntrospector.findSerializationInclusion(this._classInfo, include2);
    }

    public AnnotatedMember findAnyGetter() throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        if (this._anyGetter != null) {
            if (!Map.class.isAssignableFrom(this._anyGetter.getRawType())) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Invalid 'any-getter' annotation on method ").append(this._anyGetter.getName()).append("(): return type is not instance of java.util.Map").toString());
                throw th2;
            }
        }
        return this._anyGetter;
    }

    public Map<String, AnnotatedMember> findBackReferenceProperties() {
        AnnotationIntrospector.ReferenceProperty findReferenceType;
        Throwable th;
        StringBuilder sb;
        HashMap hashMap;
        HashMap hashMap2 = null;
        for (BeanPropertyDefinition mutator : this._properties) {
            AnnotatedMember mutator2 = mutator.getMutator();
            if (!(mutator2 == null || (findReferenceType = this._annotationIntrospector.findReferenceType(mutator2)) == null || !findReferenceType.isBackReference())) {
                if (hashMap2 == null) {
                    new HashMap();
                    hashMap2 = hashMap;
                }
                String name = findReferenceType.getName();
                if (hashMap2.put(name, mutator2) != null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Multiple back-reference properties with name '").append(name).append("'").toString());
                    throw th2;
                }
            }
        }
        return hashMap2;
    }

    public List<AnnotatedMethod> getFactoryMethods() {
        ArrayList arrayList;
        List<AnnotatedMethod> staticMethods = this._classInfo.getStaticMethods();
        if (staticMethods.isEmpty()) {
            return staticMethods;
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        for (AnnotatedMethod next : staticMethods) {
            if (isFactoryMethod(next)) {
                boolean add = arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public Constructor<?> findSingleArgConstructor(Class<?>... clsArr) {
        Class<?>[] clsArr2 = clsArr;
        for (AnnotatedConstructor next : this._classInfo.getConstructors()) {
            if (next.getParameterCount() == 1) {
                Class<?> rawParameterType = next.getRawParameterType(0);
                Class<?>[] clsArr3 = clsArr2;
                int length = clsArr3.length;
                for (int i = 0; i < length; i++) {
                    if (clsArr3[i] == rawParameterType) {
                        return next.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public Method findFactoryMethod(Class<?>... clsArr) {
        Class<?>[] clsArr2 = clsArr;
        for (AnnotatedMethod next : this._classInfo.getStaticMethods()) {
            if (isFactoryMethod(next)) {
                Class<?> rawParameterType = next.getRawParameterType(0);
                Class<?>[] clsArr3 = clsArr2;
                int length = clsArr3.length;
                for (int i = 0; i < length; i++) {
                    if (rawParameterType.isAssignableFrom(clsArr3[i])) {
                        return next.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isFactoryMethod(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        if (!getBeanClass().isAssignableFrom(annotatedMethod2.getRawReturnType())) {
            return false;
        }
        if (this._annotationIntrospector.hasCreatorAnnotation(annotatedMethod2)) {
            return true;
        }
        if ("valueOf".equals(annotatedMethod2.getName())) {
            return true;
        }
        return false;
    }

    public List<String> findCreatorPropertyNames() {
        PropertyName findNameForDeserialization;
        List<String> list;
        List<String> list2 = null;
        int i = 0;
        while (i < 2) {
            for (AnnotatedWithParams annotatedWithParams : i == 0 ? getConstructors() : getFactoryMethods()) {
                int parameterCount = annotatedWithParams.getParameterCount();
                if (parameterCount >= 1 && (findNameForDeserialization = this._annotationIntrospector.findNameForDeserialization(annotatedWithParams.getParameter(0))) != null) {
                    if (list2 == null) {
                        new ArrayList();
                        list2 = list;
                    }
                    boolean add = list2.add(findNameForDeserialization.getSimpleName());
                    for (int i2 = 1; i2 < parameterCount; i2++) {
                        PropertyName findNameForDeserialization2 = this._annotationIntrospector.findNameForDeserialization(annotatedWithParams.getParameter(i2));
                        boolean add2 = list2.add(findNameForDeserialization2 == null ? null : findNameForDeserialization2.getSimpleName());
                    }
                }
            }
            i++;
        }
        if (list2 == null) {
            return Collections.emptyList();
        }
        return list2;
    }

    public Class<?> findPOJOBuilder() {
        return this._annotationIntrospector == null ? null : this._annotationIntrospector.findPOJOBuilder(this._classInfo);
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig() {
        return this._annotationIntrospector == null ? null : this._annotationIntrospector.findPOJOBuilderConfig(this._classInfo);
    }

    public Converter<Object, Object> findDeserializationConverter() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        return _createConverter(this._annotationIntrospector.findDeserializationConverter(this._classInfo));
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.LinkedHashMap<java.lang.String, com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField> _findPropertyFields(java.util.Collection<java.lang.String> r13, boolean r14) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
            r11 = r8
            r8 = r11
            r9 = r11
            r9.<init>()
            r3 = r8
            r8 = r0
            java.util.List<com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition> r8 = r8._properties
            java.util.Iterator r8 = r8.iterator()
            r4 = r8
        L_0x0014:
            r8 = r4
            boolean r8 = r8.hasNext()
            if (r8 == 0) goto L_0x0046
            r8 = r4
            java.lang.Object r8 = r8.next()
            com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r8 = (com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition) r8
            r5 = r8
            r8 = r5
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField r8 = r8.getField()
            r6 = r8
            r8 = r6
            if (r8 == 0) goto L_0x0045
            r8 = r5
            java.lang.String r8 = r8.getName()
            r7 = r8
            r8 = r1
            if (r8 == 0) goto L_0x003e
            r8 = r1
            r9 = r7
            boolean r8 = r8.contains(r9)
            if (r8 == 0) goto L_0x003e
            goto L_0x0014
        L_0x003e:
            r8 = r3
            r9 = r7
            r10 = r6
            java.lang.Object r8 = r8.put(r9, r10)
        L_0x0045:
            goto L_0x0014
        L_0x0046:
            r8 = r3
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.BasicBeanDescription._findPropertyFields(java.util.Collection, boolean):java.util.LinkedHashMap");
    }

    public Converter<Object, Object> _createConverter(Object obj) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Converter) {
            return (Converter) obj2;
        }
        if (!(obj2 instanceof Class)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("AnnotationIntrospector returned Converter definition of type ").append(obj2.getClass().getName()).append("; expected type Converter or Class<Converter> instead").toString());
            throw th3;
        }
        Class<NoClass> cls = (Class) obj2;
        if (cls == Converter.None.class || cls == NoClass.class) {
            return null;
        }
        if (!Converter.class.isAssignableFrom(cls)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("AnnotationIntrospector returned Class ").append(cls.getName()).append("; expected Class<Converter>").toString());
            throw th4;
        }
        HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
        Converter<?, ?> converterInstance = handlerInstantiator == null ? null : handlerInstantiator.converterInstance(this._config, this._classInfo, cls);
        if (converterInstance == null) {
            converterInstance = (Converter) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
        }
        return converterInstance;
    }
}
