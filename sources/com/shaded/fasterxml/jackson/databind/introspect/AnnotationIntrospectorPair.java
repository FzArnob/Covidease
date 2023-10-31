package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospectorPair extends AnnotationIntrospector implements Serializable {
    private static final long serialVersionUID = 1;
    protected final AnnotationIntrospector _primary;
    protected final AnnotationIntrospector _secondary;

    public AnnotationIntrospectorPair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        this._primary = annotationIntrospector;
        this._secondary = annotationIntrospector2;
    }

    public Version version() {
        return this._primary.version();
    }

    public static AnnotationIntrospector create(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        AnnotationIntrospector annotationIntrospector3;
        AnnotationIntrospector annotationIntrospector4 = annotationIntrospector;
        AnnotationIntrospector annotationIntrospector5 = annotationIntrospector2;
        if (annotationIntrospector4 == null) {
            return annotationIntrospector5;
        }
        if (annotationIntrospector5 == null) {
            return annotationIntrospector4;
        }
        new AnnotationIntrospectorPair(annotationIntrospector4, annotationIntrospector5);
        return annotationIntrospector3;
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        Collection collection;
        new ArrayList();
        return allIntrospectors(collection);
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
        Collection<AnnotationIntrospector> collection2 = collection;
        Collection<AnnotationIntrospector> allIntrospectors = this._primary.allIntrospectors(collection2);
        Collection<AnnotationIntrospector> allIntrospectors2 = this._secondary.allIntrospectors(collection2);
        return collection2;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        Annotation annotation2 = annotation;
        return this._primary.isAnnotationBundle(annotation2) || this._secondary.isAnnotationBundle(annotation2);
    }

    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        PropertyName findRootName = this._primary.findRootName(annotatedClass2);
        if (findRootName == null) {
            return this._secondary.findRootName(annotatedClass2);
        }
        if (findRootName.hasSimpleName()) {
            return findRootName;
        }
        PropertyName findRootName2 = this._secondary.findRootName(annotatedClass2);
        return findRootName2 == null ? findRootName : findRootName2;
    }

    public String[] findPropertiesToIgnore(Annotated annotated) {
        Annotated annotated2 = annotated;
        String[] findPropertiesToIgnore = this._primary.findPropertiesToIgnore(annotated2);
        if (findPropertiesToIgnore == null) {
            findPropertiesToIgnore = this._secondary.findPropertiesToIgnore(annotated2);
        }
        return findPropertiesToIgnore;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        Boolean findIgnoreUnknownProperties = this._primary.findIgnoreUnknownProperties(annotatedClass2);
        if (findIgnoreUnknownProperties == null) {
            findIgnoreUnknownProperties = this._secondary.findIgnoreUnknownProperties(annotatedClass2);
        }
        return findIgnoreUnknownProperties;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        Boolean isIgnorableType = this._primary.isIgnorableType(annotatedClass2);
        if (isIgnorableType == null) {
            isIgnorableType = this._secondary.isIgnorableType(annotatedClass2);
        }
        return isIgnorableType;
    }

    public Object findFilterId(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        Object findFilterId = this._primary.findFilterId(annotatedClass2);
        if (findFilterId == null) {
            findFilterId = this._secondary.findFilterId(annotatedClass2);
        }
        return findFilterId;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        Object findNamingStrategy = this._primary.findNamingStrategy(annotatedClass2);
        if (findNamingStrategy == null) {
            findNamingStrategy = this._secondary.findNamingStrategy(annotatedClass2);
        }
        return findNamingStrategy;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return this._primary.findAutoDetectVisibility(annotatedClass2, this._secondary.findAutoDetectVisibility(annotatedClass2, visibilityChecker));
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedClass annotatedClass2 = annotatedClass;
        JavaType javaType2 = javaType;
        TypeResolverBuilder<?> findTypeResolver = this._primary.findTypeResolver(mapperConfig2, annotatedClass2, javaType2);
        if (findTypeResolver == null) {
            findTypeResolver = this._secondary.findTypeResolver(mapperConfig2, annotatedClass2, javaType2);
        }
        return findTypeResolver;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType javaType2 = javaType;
        TypeResolverBuilder<?> findPropertyTypeResolver = this._primary.findPropertyTypeResolver(mapperConfig2, annotatedMember2, javaType2);
        if (findPropertyTypeResolver == null) {
            findPropertyTypeResolver = this._secondary.findPropertyTypeResolver(mapperConfig2, annotatedMember2, javaType2);
        }
        return findPropertyTypeResolver;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType javaType2 = javaType;
        TypeResolverBuilder<?> findPropertyContentTypeResolver = this._primary.findPropertyContentTypeResolver(mapperConfig2, annotatedMember2, javaType2);
        if (findPropertyContentTypeResolver == null) {
            findPropertyContentTypeResolver = this._secondary.findPropertyContentTypeResolver(mapperConfig2, annotatedMember2, javaType2);
        }
        return findPropertyContentTypeResolver;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        ArrayList arrayList;
        Annotated annotated2 = annotated;
        List<NamedType> findSubtypes = this._primary.findSubtypes(annotated2);
        List<NamedType> findSubtypes2 = this._secondary.findSubtypes(annotated2);
        if (findSubtypes == null || findSubtypes.isEmpty()) {
            return findSubtypes2;
        }
        if (findSubtypes2 == null || findSubtypes2.isEmpty()) {
            return findSubtypes;
        }
        new ArrayList(findSubtypes.size() + findSubtypes2.size());
        ArrayList arrayList2 = arrayList;
        boolean addAll = arrayList2.addAll(findSubtypes);
        boolean addAll2 = arrayList2.addAll(findSubtypes2);
        return arrayList2;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        String findTypeName = this._primary.findTypeName(annotatedClass2);
        if (findTypeName == null || findTypeName.length() == 0) {
            findTypeName = this._secondary.findTypeName(annotatedClass2);
        }
        return findTypeName;
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        AnnotationIntrospector.ReferenceProperty findReferenceType = this._primary.findReferenceType(annotatedMember2);
        if (findReferenceType == null) {
            findReferenceType = this._secondary.findReferenceType(annotatedMember2);
        }
        return findReferenceType;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        NameTransformer findUnwrappingNameTransformer = this._primary.findUnwrappingNameTransformer(annotatedMember2);
        if (findUnwrappingNameTransformer == null) {
            findUnwrappingNameTransformer = this._secondary.findUnwrappingNameTransformer(annotatedMember2);
        }
        return findUnwrappingNameTransformer;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        Object findInjectableValueId = this._primary.findInjectableValueId(annotatedMember2);
        if (findInjectableValueId == null) {
            findInjectableValueId = this._secondary.findInjectableValueId(annotatedMember2);
        }
        return findInjectableValueId;
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return this._primary.hasIgnoreMarker(annotatedMember2) || this._secondary.hasIgnoreMarker(annotatedMember2);
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        Boolean hasRequiredMarker = this._primary.hasRequiredMarker(annotatedMember2);
        if (hasRequiredMarker == null) {
            hasRequiredMarker = this._secondary.hasRequiredMarker(annotatedMember2);
        }
        return hasRequiredMarker;
    }

    public Object findSerializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findSerializer = this._primary.findSerializer(annotated2);
        if (findSerializer == null) {
            findSerializer = this._secondary.findSerializer(annotated2);
        }
        return findSerializer;
    }

    public Object findKeySerializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findKeySerializer = this._primary.findKeySerializer(annotated2);
        if (findKeySerializer == null || findKeySerializer == JsonSerializer.None.class || findKeySerializer == NoClass.class) {
            findKeySerializer = this._secondary.findKeySerializer(annotated2);
        }
        return findKeySerializer;
    }

    public Object findContentSerializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findContentSerializer = this._primary.findContentSerializer(annotated2);
        if (findContentSerializer == null || findContentSerializer == JsonSerializer.None.class || findContentSerializer == NoClass.class) {
            findContentSerializer = this._secondary.findContentSerializer(annotated2);
        }
        return findContentSerializer;
    }

    public JsonInclude.Include findSerializationInclusion(Annotated annotated, JsonInclude.Include include) {
        Annotated annotated2 = annotated;
        return this._primary.findSerializationInclusion(annotated2, this._secondary.findSerializationInclusion(annotated2, include));
    }

    public Class<?> findSerializationType(Annotated annotated) {
        Annotated annotated2 = annotated;
        Class<?> findSerializationType = this._primary.findSerializationType(annotated2);
        if (findSerializationType == null) {
            findSerializationType = this._secondary.findSerializationType(annotated2);
        }
        return findSerializationType;
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        Class<?> findSerializationKeyType = this._primary.findSerializationKeyType(annotated2, javaType2);
        if (findSerializationKeyType == null) {
            findSerializationKeyType = this._secondary.findSerializationKeyType(annotated2, javaType2);
        }
        return findSerializationKeyType;
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        Class<?> findSerializationContentType = this._primary.findSerializationContentType(annotated2, javaType2);
        if (findSerializationContentType == null) {
            findSerializationContentType = this._secondary.findSerializationContentType(annotated2, javaType2);
        }
        return findSerializationContentType;
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        Annotated annotated2 = annotated;
        JsonSerialize.Typing findSerializationTyping = this._primary.findSerializationTyping(annotated2);
        if (findSerializationTyping == null) {
            findSerializationTyping = this._secondary.findSerializationTyping(annotated2);
        }
        return findSerializationTyping;
    }

    public Object findSerializationConverter(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findSerializationConverter = this._primary.findSerializationConverter(annotated2);
        if (findSerializationConverter == null) {
            findSerializationConverter = this._secondary.findSerializationConverter(annotated2);
        }
        return findSerializationConverter;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        Object findSerializationContentConverter = this._primary.findSerializationContentConverter(annotatedMember2);
        if (findSerializationContentConverter == null) {
            findSerializationContentConverter = this._secondary.findSerializationContentConverter(annotatedMember2);
        }
        return findSerializationContentConverter;
    }

    public Class<?>[] findViews(Annotated annotated) {
        Annotated annotated2 = annotated;
        Class<?>[] findViews = this._primary.findViews(annotated2);
        if (findViews == null) {
            findViews = this._secondary.findViews(annotated2);
        }
        return findViews;
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        Boolean isTypeId = this._primary.isTypeId(annotatedMember2);
        if (isTypeId == null) {
            isTypeId = this._secondary.isTypeId(annotatedMember2);
        }
        return isTypeId;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        Annotated annotated2 = annotated;
        ObjectIdInfo findObjectIdInfo = this._primary.findObjectIdInfo(annotated2);
        if (findObjectIdInfo == null) {
            findObjectIdInfo = this._secondary.findObjectIdInfo(annotated2);
        }
        return findObjectIdInfo;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        Annotated annotated2 = annotated;
        return this._primary.findObjectReferenceInfo(annotated2, this._secondary.findObjectReferenceInfo(annotated2, objectIdInfo));
    }

    public JsonFormat.Value findFormat(Annotated annotated) {
        Annotated annotated2 = annotated;
        JsonFormat.Value findFormat = this._primary.findFormat(annotated2);
        if (findFormat == null) {
            findFormat = this._secondary.findFormat(annotated2);
        }
        return findFormat;
    }

    public PropertyName findWrapperName(Annotated annotated) {
        PropertyName findWrapperName;
        Annotated annotated2 = annotated;
        PropertyName findWrapperName2 = this._primary.findWrapperName(annotated2);
        if (findWrapperName2 == null) {
            findWrapperName2 = this._secondary.findWrapperName(annotated2);
        } else if (findWrapperName2 == PropertyName.USE_DEFAULT && (findWrapperName = this._secondary.findWrapperName(annotated2)) != null) {
            findWrapperName2 = findWrapperName;
        }
        return findWrapperName2;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        String[] findSerializationPropertyOrder = this._primary.findSerializationPropertyOrder(annotatedClass2);
        if (findSerializationPropertyOrder == null) {
            findSerializationPropertyOrder = this._secondary.findSerializationPropertyOrder(annotatedClass2);
        }
        return findSerializationPropertyOrder;
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        Boolean findSerializationSortAlphabetically = this._primary.findSerializationSortAlphabetically(annotatedClass2);
        if (findSerializationSortAlphabetically == null) {
            findSerializationSortAlphabetically = this._secondary.findSerializationSortAlphabetically(annotatedClass2);
        }
        return findSerializationSortAlphabetically;
    }

    public PropertyName findNameForSerialization(Annotated annotated) {
        PropertyName findNameForSerialization;
        Annotated annotated2 = annotated;
        PropertyName findNameForSerialization2 = this._primary.findNameForSerialization(annotated2);
        if (findNameForSerialization2 == null) {
            findNameForSerialization2 = this._secondary.findNameForSerialization(annotated2);
        } else if (findNameForSerialization2 == PropertyName.USE_DEFAULT && (findNameForSerialization = this._secondary.findNameForSerialization(annotated2)) != null) {
            findNameForSerialization2 = findNameForSerialization;
        }
        return findNameForSerialization2;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return this._primary.hasAsValueAnnotation(annotatedMethod2) || this._secondary.hasAsValueAnnotation(annotatedMethod2);
    }

    public String findEnumValue(Enum<?> enumR) {
        Enum<?> enumR2 = enumR;
        String findEnumValue = this._primary.findEnumValue(enumR2);
        if (findEnumValue == null) {
            findEnumValue = this._secondary.findEnumValue(enumR2);
        }
        return findEnumValue;
    }

    public Object findDeserializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findDeserializer = this._primary.findDeserializer(annotated2);
        if (findDeserializer == null) {
            findDeserializer = this._secondary.findDeserializer(annotated2);
        }
        return findDeserializer;
    }

    public Object findKeyDeserializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findKeyDeserializer = this._primary.findKeyDeserializer(annotated2);
        if (findKeyDeserializer == null || findKeyDeserializer == KeyDeserializer.None.class || findKeyDeserializer == NoClass.class) {
            findKeyDeserializer = this._secondary.findKeyDeserializer(annotated2);
        }
        return findKeyDeserializer;
    }

    public Object findContentDeserializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findContentDeserializer = this._primary.findContentDeserializer(annotated2);
        if (findContentDeserializer == null || findContentDeserializer == JsonDeserializer.None.class || findContentDeserializer == NoClass.class) {
            findContentDeserializer = this._secondary.findContentDeserializer(annotated2);
        }
        return findContentDeserializer;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        Class<?> findDeserializationType = this._primary.findDeserializationType(annotated2, javaType2);
        if (findDeserializationType == null) {
            findDeserializationType = this._secondary.findDeserializationType(annotated2, javaType2);
        }
        return findDeserializationType;
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        Class<?> findDeserializationKeyType = this._primary.findDeserializationKeyType(annotated2, javaType2);
        if (findDeserializationKeyType == null) {
            findDeserializationKeyType = this._secondary.findDeserializationKeyType(annotated2, javaType2);
        }
        return findDeserializationKeyType;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        Class<?> findDeserializationContentType = this._primary.findDeserializationContentType(annotated2, javaType2);
        if (findDeserializationContentType == null) {
            findDeserializationContentType = this._secondary.findDeserializationContentType(annotated2, javaType2);
        }
        return findDeserializationContentType;
    }

    public Object findDeserializationConverter(Annotated annotated) {
        Annotated annotated2 = annotated;
        Object findDeserializationConverter = this._primary.findDeserializationConverter(annotated2);
        if (findDeserializationConverter == null) {
            findDeserializationConverter = this._secondary.findDeserializationConverter(annotated2);
        }
        return findDeserializationConverter;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        Object findDeserializationContentConverter = this._primary.findDeserializationContentConverter(annotatedMember2);
        if (findDeserializationContentConverter == null) {
            findDeserializationContentConverter = this._secondary.findDeserializationContentConverter(annotatedMember2);
        }
        return findDeserializationContentConverter;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        Object findValueInstantiator = this._primary.findValueInstantiator(annotatedClass2);
        if (findValueInstantiator == null) {
            findValueInstantiator = this._secondary.findValueInstantiator(annotatedClass2);
        }
        return findValueInstantiator;
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        Class<?> findPOJOBuilder = this._primary.findPOJOBuilder(annotatedClass2);
        if (findPOJOBuilder == null) {
            findPOJOBuilder = this._secondary.findPOJOBuilder(annotatedClass2);
        }
        return findPOJOBuilder;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        JsonPOJOBuilder.Value findPOJOBuilderConfig = this._primary.findPOJOBuilderConfig(annotatedClass2);
        if (findPOJOBuilderConfig == null) {
            findPOJOBuilderConfig = this._secondary.findPOJOBuilderConfig(annotatedClass2);
        }
        return findPOJOBuilderConfig;
    }

    public PropertyName findNameForDeserialization(Annotated annotated) {
        PropertyName findNameForDeserialization;
        Annotated annotated2 = annotated;
        PropertyName findNameForDeserialization2 = this._primary.findNameForDeserialization(annotated2);
        if (findNameForDeserialization2 == null) {
            findNameForDeserialization2 = this._secondary.findNameForDeserialization(annotated2);
        } else if (findNameForDeserialization2 == PropertyName.USE_DEFAULT && (findNameForDeserialization = this._secondary.findNameForDeserialization(annotated2)) != null) {
            findNameForDeserialization2 = findNameForDeserialization;
        }
        return findNameForDeserialization2;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return this._primary.hasAnySetterAnnotation(annotatedMethod2) || this._secondary.hasAnySetterAnnotation(annotatedMethod2);
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return this._primary.hasAnyGetterAnnotation(annotatedMethod2) || this._secondary.hasAnyGetterAnnotation(annotatedMethod2);
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        Annotated annotated2 = annotated;
        return this._primary.hasCreatorAnnotation(annotated2) || this._secondary.hasCreatorAnnotation(annotated2);
    }

    @Deprecated
    public boolean isHandled(Annotation annotation) {
        Annotation annotation2 = annotation;
        return this._primary.isHandled(annotation2) || this._secondary.isHandled(annotation2);
    }

    @Deprecated
    public String findDeserializationName(AnnotatedMethod annotatedMethod) {
        String findDeserializationName;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        String findDeserializationName2 = this._primary.findDeserializationName(annotatedMethod2);
        if (findDeserializationName2 == null) {
            findDeserializationName2 = this._secondary.findDeserializationName(annotatedMethod2);
        } else if (findDeserializationName2.length() == 0 && (findDeserializationName = this._secondary.findDeserializationName(annotatedMethod2)) != null) {
            findDeserializationName2 = findDeserializationName;
        }
        return findDeserializationName2;
    }

    @Deprecated
    public String findDeserializationName(AnnotatedField annotatedField) {
        String findDeserializationName;
        AnnotatedField annotatedField2 = annotatedField;
        String findDeserializationName2 = this._primary.findDeserializationName(annotatedField2);
        if (findDeserializationName2 == null) {
            findDeserializationName2 = this._secondary.findDeserializationName(annotatedField2);
        } else if (findDeserializationName2.length() == 0 && (findDeserializationName = this._secondary.findDeserializationName(annotatedField2)) != null) {
            findDeserializationName2 = findDeserializationName;
        }
        return findDeserializationName2;
    }

    @Deprecated
    public String findDeserializationName(AnnotatedParameter annotatedParameter) {
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        String findDeserializationName = this._primary.findDeserializationName(annotatedParameter2);
        if (findDeserializationName == null) {
            findDeserializationName = this._secondary.findDeserializationName(annotatedParameter2);
        }
        return findDeserializationName;
    }

    @Deprecated
    public String findSerializationName(AnnotatedMethod annotatedMethod) {
        String findSerializationName;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        String findSerializationName2 = this._primary.findSerializationName(annotatedMethod2);
        if (findSerializationName2 == null) {
            findSerializationName2 = this._secondary.findSerializationName(annotatedMethod2);
        } else if (findSerializationName2.length() == 0 && (findSerializationName = this._secondary.findSerializationName(annotatedMethod2)) != null) {
            findSerializationName2 = findSerializationName;
        }
        return findSerializationName2;
    }

    @Deprecated
    public String findSerializationName(AnnotatedField annotatedField) {
        String findSerializationName;
        AnnotatedField annotatedField2 = annotatedField;
        String findSerializationName2 = this._primary.findSerializationName(annotatedField2);
        if (findSerializationName2 == null) {
            findSerializationName2 = this._secondary.findSerializationName(annotatedField2);
        } else if (findSerializationName2.length() == 0 && (findSerializationName = this._secondary.findSerializationName(annotatedField2)) != null) {
            findSerializationName2 = findSerializationName;
        }
        return findSerializationName2;
    }
}
