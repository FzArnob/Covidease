package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.util.BeanUtil;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList<AnnotatedMember> _anyGetters = null;
    protected LinkedList<AnnotatedMethod> _anySetters = null;
    protected final AnnotatedClass _classDef;
    protected final MapperConfig<?> _config;
    protected LinkedList<POJOPropertyBuilder> _creatorProperties = null;
    protected final boolean _forSerialization;
    protected HashSet<String> _ignoredPropertyNames;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected LinkedList<AnnotatedMethod> _jsonValueGetters = null;
    protected final String _mutatorPrefix;
    protected final LinkedHashMap<String, POJOPropertyBuilder> _properties;
    protected final JavaType _type;
    protected final VisibilityChecker<?> _visibilityChecker;

    protected POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass, String str) {
        LinkedHashMap<String, POJOPropertyBuilder> linkedHashMap;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedClass annotatedClass2 = annotatedClass;
        String str2 = str;
        new LinkedHashMap<>();
        this._properties = linkedHashMap;
        this._config = mapperConfig2;
        this._forSerialization = z;
        this._type = javaType;
        this._classDef = annotatedClass2;
        this._mutatorPrefix = str2 == null ? "set" : str2;
        this._annotationIntrospector = mapperConfig2.isAnnotationProcessingEnabled() ? this._config.getAnnotationIntrospector() : null;
        if (this._annotationIntrospector == null) {
            this._visibilityChecker = this._config.getDefaultVisibilityChecker();
        } else {
            this._visibilityChecker = this._annotationIntrospector.findAutoDetectVisibility(annotatedClass2, this._config.getDefaultVisibilityChecker());
        }
    }

    public MapperConfig<?> getConfig() {
        return this._config;
    }

    public JavaType getType() {
        return this._type;
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public List<BeanPropertyDefinition> getProperties() {
        List<BeanPropertyDefinition> list;
        new ArrayList(this._properties.values());
        return list;
    }

    public Map<Object, AnnotatedMember> getInjectables() {
        return this._injectables;
    }

    public AnnotatedMethod getJsonValueMethod() {
        StringBuilder sb;
        if (this._jsonValueGetters == null) {
            return null;
        }
        if (this._jsonValueGetters.size() > 1) {
            new StringBuilder();
            reportProblem(sb.append("Multiple value properties defined (").append(this._jsonValueGetters.get(0)).append(" vs ").append(this._jsonValueGetters.get(1)).append(")").toString());
        }
        return this._jsonValueGetters.get(0);
    }

    public AnnotatedMember getAnyGetter() {
        StringBuilder sb;
        if (this._anyGetters == null) {
            return null;
        }
        if (this._anyGetters.size() > 1) {
            new StringBuilder();
            reportProblem(sb.append("Multiple 'any-getters' defined (").append(this._anyGetters.get(0)).append(" vs ").append(this._anyGetters.get(1)).append(")").toString());
        }
        return this._anyGetters.getFirst();
    }

    public AnnotatedMethod getAnySetterMethod() {
        StringBuilder sb;
        if (this._anySetters == null) {
            return null;
        }
        if (this._anySetters.size() > 1) {
            new StringBuilder();
            reportProblem(sb.append("Multiple 'any-setters' defined (").append(this._anySetters.get(0)).append(" vs ").append(this._anySetters.get(1)).append(")").toString());
        }
        return this._anySetters.getFirst();
    }

    public Set<String> getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public ObjectIdInfo getObjectIdInfo() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        ObjectIdInfo findObjectIdInfo = this._annotationIntrospector.findObjectIdInfo(this._classDef);
        if (findObjectIdInfo != null) {
            findObjectIdInfo = this._annotationIntrospector.findObjectReferenceInfo(this._classDef, findObjectIdInfo);
        }
        return findObjectIdInfo;
    }

    public Class<?> findPOJOBuilderClass() {
        return this._annotationIntrospector.findPOJOBuilder(this._classDef);
    }

    /* access modifiers changed from: protected */
    public Map<String, POJOPropertyBuilder> getPropertyMap() {
        return this._properties;
    }

    public POJOPropertiesCollector collect() {
        this._properties.clear();
        _addFields();
        _addMethods();
        _addCreators();
        _addInjectables();
        _removeUnwantedProperties();
        _renameProperties();
        PropertyNamingStrategy _findNamingStrategy = _findNamingStrategy();
        if (_findNamingStrategy != null) {
            _renameUsing(_findNamingStrategy);
        }
        for (POJOPropertyBuilder trimByVisibility : this._properties.values()) {
            trimByVisibility.trimByVisibility();
        }
        for (POJOPropertyBuilder mergeAnnotations : this._properties.values()) {
            mergeAnnotations.mergeAnnotations(this._forSerialization);
        }
        if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            _renameWithWrappers();
        }
        _sortProperties();
        return this;
    }

    /* access modifiers changed from: protected */
    public void _sortProperties() {
        Boolean findSerializationSortAlphabetically;
        boolean booleanValue;
        String[] findSerializationPropertyOrder;
        Map map;
        Map map2;
        Map map3;
        Map map4;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            findSerializationSortAlphabetically = null;
        } else {
            findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        }
        Boolean bool = findSerializationSortAlphabetically;
        if (bool == null) {
            booleanValue = this._config.shouldSortPropertiesAlphabetically();
        } else {
            booleanValue = bool.booleanValue();
        }
        if (annotationIntrospector == null) {
            findSerializationPropertyOrder = null;
        } else {
            findSerializationPropertyOrder = annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        }
        String[] strArr = findSerializationPropertyOrder;
        if (!booleanValue) {
            if (this._creatorProperties == null && strArr == null) {
                return;
            }
        }
        int size = this._properties.size();
        if (booleanValue) {
            new TreeMap();
            map2 = map4;
        } else {
            new LinkedHashMap(size + size);
            map2 = map;
        }
        for (POJOPropertyBuilder next : this._properties.values()) {
            Object put = map2.put(next.getName(), next);
        }
        new LinkedHashMap(size + size);
        Map map5 = map3;
        if (strArr != null) {
            String[] strArr2 = strArr;
            int length = strArr2.length;
            for (int i = 0; i < length; i++) {
                String str = strArr2[i];
                POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) map2.get(str);
                if (pOJOPropertyBuilder == null) {
                    Iterator<POJOPropertyBuilder> it = this._properties.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        POJOPropertyBuilder next2 = it.next();
                        if (str.equals(next2.getInternalName())) {
                            pOJOPropertyBuilder = next2;
                            str = next2.getName();
                            break;
                        }
                    }
                }
                if (pOJOPropertyBuilder != null) {
                    Object put2 = map5.put(str, pOJOPropertyBuilder);
                }
            }
        }
        if (this._creatorProperties != null) {
            Iterator it2 = this._creatorProperties.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                Object put3 = map5.put(pOJOPropertyBuilder2.getName(), pOJOPropertyBuilder2);
            }
        }
        map5.putAll(map2);
        this._properties.clear();
        this._properties.putAll(map5);
    }

    /* access modifiers changed from: protected */
    public void _addFields() {
        String simpleName;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        boolean z = !this._forSerialization && !this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
        for (AnnotatedField next : this._classDef.fields()) {
            String name = next.getName();
            if (annotationIntrospector == null) {
                simpleName = null;
            } else if (this._forSerialization) {
                PropertyName findNameForSerialization = annotationIntrospector.findNameForSerialization(next);
                simpleName = findNameForSerialization == null ? null : findNameForSerialization.getSimpleName();
            } else {
                PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(next);
                simpleName = findNameForDeserialization == null ? null : findNameForDeserialization.getSimpleName();
            }
            if ("".equals(simpleName)) {
                simpleName = name;
            }
            boolean z2 = simpleName != null;
            if (!z2) {
                z2 = this._visibilityChecker.isFieldVisible(next);
            }
            boolean z3 = annotationIntrospector != null && annotationIntrospector.hasIgnoreMarker(next);
            if (!z || simpleName != null || z3 || !Modifier.isFinal(next.getModifiers())) {
                _property(name).addField(next, simpleName, z2, z3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addCreators() {
        LinkedList<POJOPropertyBuilder> linkedList;
        LinkedList<POJOPropertyBuilder> linkedList2;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            for (AnnotatedConstructor next : this._classDef.getConstructors()) {
                if (this._creatorProperties == null) {
                    new LinkedList<>();
                    this._creatorProperties = linkedList2;
                }
                int parameterCount = next.getParameterCount();
                for (int i = 0; i < parameterCount; i++) {
                    AnnotatedParameter parameter = next.getParameter(i);
                    PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(parameter);
                    String simpleName = findNameForDeserialization == null ? null : findNameForDeserialization.getSimpleName();
                    if (simpleName != null) {
                        POJOPropertyBuilder _property = _property(simpleName);
                        _property.addCtor(parameter, simpleName, true, false);
                        boolean add = this._creatorProperties.add(_property);
                    }
                }
            }
            for (AnnotatedMethod next2 : this._classDef.getStaticMethods()) {
                if (this._creatorProperties == null) {
                    new LinkedList<>();
                    this._creatorProperties = linkedList;
                }
                int parameterCount2 = next2.getParameterCount();
                for (int i2 = 0; i2 < parameterCount2; i2++) {
                    AnnotatedParameter parameter2 = next2.getParameter(i2);
                    PropertyName findNameForDeserialization2 = annotationIntrospector.findNameForDeserialization(parameter2);
                    String simpleName2 = findNameForDeserialization2 == null ? null : findNameForDeserialization2.getSimpleName();
                    if (simpleName2 != null) {
                        POJOPropertyBuilder _property2 = _property(simpleName2);
                        _property2.addCtor(parameter2, simpleName2, true, false);
                        boolean add2 = this._creatorProperties.add(_property2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethods() {
        LinkedList<AnnotatedMethod> linkedList;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedMethod next : this._classDef.memberMethods()) {
            int parameterCount = next.getParameterCount();
            if (parameterCount == 0) {
                _addGetterMethod(next, annotationIntrospector);
            } else if (parameterCount == 1) {
                _addSetterMethod(next, annotationIntrospector);
            } else if (parameterCount == 2 && annotationIntrospector != null && annotationIntrospector.hasAnySetterAnnotation(next)) {
                if (this._anySetters == null) {
                    new LinkedList<>();
                    this._anySetters = linkedList;
                }
                boolean add = this._anySetters.add(next);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addGetterMethod(AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        String okNameForGetter;
        boolean z;
        LinkedList<AnnotatedMethod> linkedList;
        LinkedList<AnnotatedMember> linkedList2;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        if (annotationIntrospector2 != null) {
            if (annotationIntrospector2.hasAnyGetterAnnotation(annotatedMethod2)) {
                if (this._anyGetters == null) {
                    new LinkedList<>();
                    this._anyGetters = linkedList2;
                }
                boolean add = this._anyGetters.add(annotatedMethod2);
                return;
            } else if (annotationIntrospector2.hasAsValueAnnotation(annotatedMethod2)) {
                if (this._jsonValueGetters == null) {
                    new LinkedList<>();
                    this._jsonValueGetters = linkedList;
                }
                boolean add2 = this._jsonValueGetters.add(annotatedMethod2);
                return;
            }
        }
        PropertyName findNameForSerialization = annotationIntrospector2 == null ? null : annotationIntrospector2.findNameForSerialization(annotatedMethod2);
        String simpleName = findNameForSerialization == null ? null : findNameForSerialization.getSimpleName();
        if (simpleName == null) {
            okNameForGetter = BeanUtil.okNameForRegularGetter(annotatedMethod2, annotatedMethod2.getName());
            if (okNameForGetter == null) {
                okNameForGetter = BeanUtil.okNameForIsGetter(annotatedMethod2, annotatedMethod2.getName());
                if (okNameForGetter != null) {
                    z = this._visibilityChecker.isIsGetterVisible(annotatedMethod2);
                } else {
                    return;
                }
            } else {
                z = this._visibilityChecker.isGetterVisible(annotatedMethod2);
            }
        } else {
            okNameForGetter = BeanUtil.okNameForGetter(annotatedMethod2);
            if (okNameForGetter == null) {
                okNameForGetter = annotatedMethod2.getName();
            }
            if (simpleName.length() == 0) {
                simpleName = okNameForGetter;
            }
            z = true;
        }
        _property(okNameForGetter).addGetter(annotatedMethod2, simpleName, z, annotationIntrospector2 == null ? false : annotationIntrospector2.hasIgnoreMarker(annotatedMethod2));
    }

    /* access modifiers changed from: protected */
    public void _addSetterMethod(AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        String okNameForMutator;
        boolean z;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        PropertyName findNameForDeserialization = annotationIntrospector2 == null ? null : annotationIntrospector2.findNameForDeserialization(annotatedMethod2);
        String simpleName = findNameForDeserialization == null ? null : findNameForDeserialization.getSimpleName();
        if (simpleName == null) {
            okNameForMutator = BeanUtil.okNameForMutator(annotatedMethod2, this._mutatorPrefix);
            if (okNameForMutator != null) {
                z = this._visibilityChecker.isSetterVisible(annotatedMethod2);
            } else {
                return;
            }
        } else {
            okNameForMutator = BeanUtil.okNameForMutator(annotatedMethod2, this._mutatorPrefix);
            if (okNameForMutator == null) {
                okNameForMutator = annotatedMethod2.getName();
            }
            if (simpleName.length() == 0) {
                simpleName = okNameForMutator;
            }
            z = true;
        }
        _property(okNameForMutator).addSetter(annotatedMethod2, simpleName, z, annotationIntrospector2 == null ? false : annotationIntrospector2.hasIgnoreMarker(annotatedMethod2));
    }

    /* access modifiers changed from: protected */
    public void _addInjectables() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            for (AnnotatedField next : this._classDef.fields()) {
                _doAddInjectable(annotationIntrospector.findInjectableValueId(next), next);
            }
            for (AnnotatedMethod next2 : this._classDef.memberMethods()) {
                if (next2.getParameterCount() == 1) {
                    _doAddInjectable(annotationIntrospector.findInjectableValueId(next2), next2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _doAddInjectable(Object obj, AnnotatedMember annotatedMember) {
        Throwable th;
        StringBuilder sb;
        LinkedHashMap<Object, AnnotatedMember> linkedHashMap;
        Object obj2 = obj;
        AnnotatedMember annotatedMember2 = annotatedMember;
        if (obj2 != null) {
            if (this._injectables == null) {
                new LinkedHashMap<>();
                this._injectables = linkedHashMap;
            }
            if (((AnnotatedMember) this._injectables.put(obj2, annotatedMember2)) != null) {
                String name = obj2 == null ? "[null]" : obj2.getClass().getName();
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Duplicate injectable value with id '").append(String.valueOf(obj2)).append("' (of type ").append(name).append(")").toString());
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _removeUnwantedProperties() {
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = this._properties.entrySet().iterator();
        boolean z = !this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it.next().getValue();
            if (!pOJOPropertyBuilder.anyVisible()) {
                it.remove();
            } else {
                if (pOJOPropertyBuilder.anyIgnorals()) {
                    if (!pOJOPropertyBuilder.isExplicitlyIncluded()) {
                        it.remove();
                        _addIgnored(pOJOPropertyBuilder.getName());
                    } else {
                        pOJOPropertyBuilder.removeIgnored();
                        if (!this._forSerialization && !pOJOPropertyBuilder.couldDeserialize()) {
                            _addIgnored(pOJOPropertyBuilder.getName());
                        }
                    }
                }
                pOJOPropertyBuilder.removeNonVisible(z);
            }
        }
    }

    private void _addIgnored(String str) {
        HashSet<String> hashSet;
        String str2 = str;
        if (!this._forSerialization) {
            if (this._ignoredPropertyNames == null) {
                new HashSet<>();
                this._ignoredPropertyNames = hashSet;
            }
            boolean add = this._ignoredPropertyNames.add(str2);
        }
    }

    /* access modifiers changed from: protected */
    public void _renameProperties() {
        LinkedList linkedList;
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = this._properties.entrySet().iterator();
        LinkedList linkedList2 = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it.next().getValue();
            String findNewName = pOJOPropertyBuilder.findNewName();
            if (findNewName != null) {
                if (linkedList2 == null) {
                    new LinkedList();
                    linkedList2 = linkedList;
                }
                boolean add = linkedList2.add(pOJOPropertyBuilder.withName(findNewName));
                it.remove();
            }
        }
        if (linkedList2 != null) {
            Iterator it2 = linkedList2.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                POJOPropertyBuilder pOJOPropertyBuilder3 = this._properties.get(name);
                if (pOJOPropertyBuilder3 == null) {
                    Object put = this._properties.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
                }
                if (this._creatorProperties != null) {
                    int i = 0;
                    while (true) {
                        if (i >= this._creatorProperties.size()) {
                            break;
                        } else if (this._creatorProperties.get(i).getInternalName() == pOJOPropertyBuilder2.getInternalName()) {
                            POJOPropertyBuilder pOJOPropertyBuilder4 = this._creatorProperties.set(i, pOJOPropertyBuilder2);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _renameUsing(PropertyNamingStrategy propertyNamingStrategy) {
        PropertyNamingStrategy propertyNamingStrategy2 = propertyNamingStrategy;
        POJOPropertyBuilder[] pOJOPropertyBuilderArr = (POJOPropertyBuilder[]) this._properties.values().toArray(new POJOPropertyBuilder[this._properties.size()]);
        this._properties.clear();
        POJOPropertyBuilder[] pOJOPropertyBuilderArr2 = pOJOPropertyBuilderArr;
        int length = pOJOPropertyBuilderArr2.length;
        for (int i = 0; i < length; i++) {
            POJOPropertyBuilder pOJOPropertyBuilder = pOJOPropertyBuilderArr2[i];
            String name = pOJOPropertyBuilder.getName();
            if (this._forSerialization) {
                if (pOJOPropertyBuilder.hasGetter()) {
                    name = propertyNamingStrategy2.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
                } else if (pOJOPropertyBuilder.hasField()) {
                    name = propertyNamingStrategy2.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
                }
            } else if (pOJOPropertyBuilder.hasSetter()) {
                name = propertyNamingStrategy2.nameForSetterMethod(this._config, pOJOPropertyBuilder.getSetter(), name);
            } else if (pOJOPropertyBuilder.hasConstructorParameter()) {
                name = propertyNamingStrategy2.nameForConstructorParameter(this._config, pOJOPropertyBuilder.getConstructorParameter(), name);
            } else if (pOJOPropertyBuilder.hasField()) {
                name = propertyNamingStrategy2.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
            } else if (pOJOPropertyBuilder.hasGetter()) {
                name = propertyNamingStrategy2.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
            }
            if (!name.equals(pOJOPropertyBuilder.getName())) {
                pOJOPropertyBuilder = pOJOPropertyBuilder.withName(name);
            }
            POJOPropertyBuilder pOJOPropertyBuilder2 = this._properties.get(name);
            if (pOJOPropertyBuilder2 == null) {
                Object put = this._properties.put(name, pOJOPropertyBuilder);
            } else {
                pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _renameWithWrappers() {
        PropertyName findWrapperName;
        LinkedList linkedList;
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = this._properties.entrySet().iterator();
        LinkedList linkedList2 = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it.next().getValue();
            AnnotatedMember primaryMember = pOJOPropertyBuilder.getPrimaryMember();
            if (!(primaryMember == null || (findWrapperName = this._annotationIntrospector.findWrapperName(primaryMember)) == null || !findWrapperName.hasSimpleName())) {
                String simpleName = findWrapperName.getSimpleName();
                if (!simpleName.equals(pOJOPropertyBuilder.getName())) {
                    if (linkedList2 == null) {
                        new LinkedList();
                        linkedList2 = linkedList;
                    }
                    boolean add = linkedList2.add(pOJOPropertyBuilder.withName(simpleName));
                    it.remove();
                }
            }
        }
        if (linkedList2 != null) {
            Iterator it2 = linkedList2.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                POJOPropertyBuilder pOJOPropertyBuilder3 = this._properties.get(name);
                if (pOJOPropertyBuilder3 == null) {
                    Object put = this._properties.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void reportProblem(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Problem with definition of ").append(this._classDef).append(": ").append(str).toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public POJOPropertyBuilder _property(String str) {
        POJOPropertyBuilder pOJOPropertyBuilder;
        String str2 = str;
        POJOPropertyBuilder pOJOPropertyBuilder2 = this._properties.get(str2);
        if (pOJOPropertyBuilder2 == null) {
            new POJOPropertyBuilder(str2, this._annotationIntrospector, this._forSerialization);
            pOJOPropertyBuilder2 = pOJOPropertyBuilder;
            Object put = this._properties.put(str2, pOJOPropertyBuilder2);
        }
        return pOJOPropertyBuilder2;
    }

    private PropertyNamingStrategy _findNamingStrategy() {
        PropertyNamingStrategy namingStrategyInstance;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Object findNamingStrategy = this._annotationIntrospector == null ? null : this._annotationIntrospector.findNamingStrategy(this._classDef);
        if (findNamingStrategy == null) {
            return this._config.getPropertyNamingStrategy();
        }
        if (findNamingStrategy instanceof PropertyNamingStrategy) {
            return (PropertyNamingStrategy) findNamingStrategy;
        }
        if (!(findNamingStrategy instanceof Class)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("AnnotationIntrospector returned PropertyNamingStrategy definition of type ").append(findNamingStrategy.getClass().getName()).append("; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead").toString());
            throw th3;
        }
        Class cls = (Class) findNamingStrategy;
        if (!PropertyNamingStrategy.class.isAssignableFrom(cls)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("AnnotationIntrospector returned Class ").append(cls.getName()).append("; expected Class<PropertyNamingStrategy>").toString());
            throw th4;
        }
        HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
        if (handlerInstantiator == null || (namingStrategyInstance = handlerInstantiator.namingStrategyInstance(this._config, this._classDef, cls)) == null) {
            return (PropertyNamingStrategy) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
        }
        return namingStrategyInstance;
    }
}
