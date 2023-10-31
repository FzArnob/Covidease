package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class StdSubtypeResolver extends SubtypeResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected LinkedHashSet<NamedType> _registeredSubtypes;

    public StdSubtypeResolver() {
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        LinkedHashSet<NamedType> linkedHashSet;
        NamedType[] namedTypeArr2 = namedTypeArr;
        if (this._registeredSubtypes == null) {
            new LinkedHashSet<>();
            this._registeredSubtypes = linkedHashSet;
        }
        NamedType[] namedTypeArr3 = namedTypeArr2;
        int length = namedTypeArr3.length;
        for (int i = 0; i < length; i++) {
            boolean add = this._registeredSubtypes.add(namedTypeArr3[i]);
        }
    }

    public void registerSubtypes(Class<?>... clsArr) {
        NamedType namedType;
        Class<?>[] clsArr2 = clsArr;
        NamedType[] namedTypeArr = new NamedType[clsArr2.length];
        int length = clsArr2.length;
        for (int i = 0; i < length; i++) {
            new NamedType(clsArr2[i]);
            namedTypeArr[i] = namedType;
        }
        registerSubtypes(namedTypeArr);
    }

    @Deprecated
    public Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember annotatedMember, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector) {
        return collectAndResolveSubtypes(annotatedMember, mapperConfig, annotationIntrospector, (JavaType) null);
    }

    public Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember annotatedMember, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector, JavaType javaType) {
        Class<?> rawClass;
        HashMap hashMap;
        NamedType namedType;
        Collection<NamedType> collection;
        AnnotatedMember annotatedMember2 = annotatedMember;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        JavaType javaType2 = javaType;
        if (javaType2 == null) {
            rawClass = annotatedMember2.getRawType();
        } else {
            rawClass = javaType2.getRawClass();
        }
        Class<?> cls = rawClass;
        new HashMap();
        HashMap hashMap2 = hashMap;
        if (this._registeredSubtypes != null) {
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                NamedType namedType2 = (NamedType) it.next();
                if (cls.isAssignableFrom(namedType2.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType2.getType(), annotationIntrospector2, mapperConfig2), namedType2, mapperConfig2, annotationIntrospector2, hashMap2);
                }
            }
        }
        List<NamedType> findSubtypes = annotationIntrospector2.findSubtypes(annotatedMember2);
        if (findSubtypes != null) {
            for (NamedType next : findSubtypes) {
                _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(next.getType(), annotationIntrospector2, mapperConfig2), next, mapperConfig2, annotationIntrospector2, hashMap2);
            }
        }
        new NamedType(cls, (String) null);
        AnnotatedClass constructWithoutSuperTypes = AnnotatedClass.constructWithoutSuperTypes(cls, annotationIntrospector2, mapperConfig2);
        _collectAndResolve(constructWithoutSuperTypes, namedType, mapperConfig2, annotationIntrospector2, hashMap2);
        new ArrayList(hashMap2.values());
        return collection;
    }

    public Collection<NamedType> collectAndResolveSubtypes(AnnotatedClass annotatedClass, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector) {
        HashMap hashMap;
        NamedType namedType;
        Collection<NamedType> collection;
        AnnotatedClass annotatedClass2 = annotatedClass;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        new HashMap();
        HashMap hashMap2 = hashMap;
        if (this._registeredSubtypes != null) {
            Class<?> rawType = annotatedClass2.getRawType();
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                NamedType namedType2 = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType2.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType2.getType(), annotationIntrospector2, mapperConfig2), namedType2, mapperConfig2, annotationIntrospector2, hashMap2);
                }
            }
        }
        new NamedType(annotatedClass2.getRawType(), (String) null);
        _collectAndResolve(annotatedClass2, namedType, mapperConfig2, annotationIntrospector2, hashMap2);
        new ArrayList(hashMap2.values());
        return collection;
    }

    /* access modifiers changed from: protected */
    public void _collectAndResolve(AnnotatedClass annotatedClass, NamedType namedType, MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector, HashMap<NamedType, NamedType> hashMap) {
        NamedType namedType2;
        String findTypeName;
        NamedType namedType3;
        AnnotatedClass annotatedClass2 = annotatedClass;
        NamedType namedType4 = namedType;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        HashMap<NamedType, NamedType> hashMap2 = hashMap;
        if (!namedType4.hasName() && (findTypeName = annotationIntrospector2.findTypeName(annotatedClass2)) != null) {
            new NamedType(namedType4.getType(), findTypeName);
            namedType4 = namedType3;
        }
        if (!hashMap2.containsKey(namedType4)) {
            NamedType put = hashMap2.put(namedType4, namedType4);
            List<NamedType> findSubtypes = annotationIntrospector2.findSubtypes(annotatedClass2);
            if (findSubtypes != null && !findSubtypes.isEmpty()) {
                for (NamedType next : findSubtypes) {
                    AnnotatedClass constructWithoutSuperTypes = AnnotatedClass.constructWithoutSuperTypes(next.getType(), annotationIntrospector2, mapperConfig2);
                    if (!next.hasName()) {
                        new NamedType(next.getType(), annotationIntrospector2.findTypeName(constructWithoutSuperTypes));
                        next = namedType2;
                    }
                    _collectAndResolve(constructWithoutSuperTypes, next, mapperConfig2, annotationIntrospector2, hashMap2);
                }
            }
        } else if (namedType4.hasName() && !hashMap2.get(namedType4).hasName()) {
            NamedType put2 = hashMap2.put(namedType4, namedType4);
        }
    }
}
