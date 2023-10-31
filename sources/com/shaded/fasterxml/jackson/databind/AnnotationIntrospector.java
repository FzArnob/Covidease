package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.Versioned;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.shaded.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AnnotationIntrospector implements Versioned, Serializable {
    public abstract Version version();

    public AnnotationIntrospector() {
    }

    public static class ReferenceProperty {
        private final String _name;
        private final Type _type;

        public enum Type {
        }

        public ReferenceProperty(Type type, String str) {
            this._type = type;
            this._name = str;
        }

        public static ReferenceProperty managed(String str) {
            ReferenceProperty referenceProperty;
            new ReferenceProperty(Type.MANAGED_REFERENCE, str);
            return referenceProperty;
        }

        public static ReferenceProperty back(String str) {
            ReferenceProperty referenceProperty;
            new ReferenceProperty(Type.BACK_REFERENCE, str);
            return referenceProperty;
        }

        public Type getType() {
            return this._type;
        }

        public String getName() {
            return this._name;
        }

        public boolean isManagedReference() {
            return this._type == Type.MANAGED_REFERENCE;
        }

        public boolean isBackReference() {
            return this._type == Type.BACK_REFERENCE;
        }
    }

    public static AnnotationIntrospector nopInstance() {
        return NopAnnotationIntrospector.instance;
    }

    public static AnnotationIntrospector pair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        AnnotationIntrospector annotationIntrospector3;
        new AnnotationIntrospectorPair(annotationIntrospector, annotationIntrospector2);
        return annotationIntrospector3;
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        return Collections.singletonList(this);
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
        Collection<AnnotationIntrospector> collection2 = collection;
        boolean add = collection2.add(this);
        return collection2;
    }

    @Deprecated
    public boolean isHandled(Annotation annotation) {
        Annotation annotation2 = annotation;
        return false;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        Annotation annotation2 = annotation;
        return false;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        Annotated annotated2 = annotated;
        return objectIdInfo;
    }

    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public String[] findPropertiesToIgnore(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public Object findFilterId(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return visibilityChecker;
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedClass annotatedClass2 = annotatedClass;
        JavaType javaType2 = javaType;
        return null;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType javaType2 = javaType;
        return null;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType javaType2 = javaType;
        return null;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return false;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public Class<?>[] findViews(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    @Deprecated
    public JsonFormat.Value findFormat(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public JsonFormat.Value findFormat(Annotated annotated) {
        Annotated annotated2 = annotated;
        if (annotated2 instanceof AnnotatedMember) {
            return findFormat((AnnotatedMember) annotated2);
        }
        return null;
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public PropertyName findWrapperName(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findSerializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findKeySerializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findContentSerializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public JsonInclude.Include findSerializationInclusion(Annotated annotated, JsonInclude.Include include) {
        Annotated annotated2 = annotated;
        return include;
    }

    public Class<?> findSerializationType(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        return null;
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        return null;
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findSerializationConverter(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public PropertyName findNameForSerialization(Annotated annotated) {
        String str;
        PropertyName propertyName;
        Annotated annotated2 = annotated;
        if (annotated2 instanceof AnnotatedField) {
            str = findSerializationName((AnnotatedField) annotated2);
        } else if (annotated2 instanceof AnnotatedMethod) {
            str = findSerializationName((AnnotatedMethod) annotated2);
        } else {
            str = null;
        }
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return PropertyName.USE_DEFAULT;
        }
        new PropertyName(str);
        return propertyName;
    }

    @Deprecated
    public String findSerializationName(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return null;
    }

    @Deprecated
    public String findSerializationName(AnnotatedField annotatedField) {
        AnnotatedField annotatedField2 = annotatedField;
        return null;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return false;
    }

    public String findEnumValue(Enum<?> enumR) {
        return enumR.name();
    }

    public Object findDeserializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findKeyDeserializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findContentDeserializer(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        return null;
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        return null;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        Annotated annotated2 = annotated;
        JavaType javaType2 = javaType;
        return null;
    }

    public Object findDeserializationConverter(Annotated annotated) {
        Annotated annotated2 = annotated;
        return null;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        return null;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        AnnotatedClass annotatedClass2 = annotatedClass;
        return null;
    }

    public PropertyName findNameForDeserialization(Annotated annotated) {
        String str;
        PropertyName propertyName;
        Annotated annotated2 = annotated;
        if (annotated2 instanceof AnnotatedField) {
            str = findDeserializationName((AnnotatedField) annotated2);
        } else if (annotated2 instanceof AnnotatedMethod) {
            str = findDeserializationName((AnnotatedMethod) annotated2);
        } else if (annotated2 instanceof AnnotatedParameter) {
            str = findDeserializationName((AnnotatedParameter) annotated2);
        } else {
            str = null;
        }
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return PropertyName.USE_DEFAULT;
        }
        new PropertyName(str);
        return propertyName;
    }

    @Deprecated
    public String findDeserializationName(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return null;
    }

    @Deprecated
    public String findDeserializationName(AnnotatedField annotatedField) {
        AnnotatedField annotatedField2 = annotatedField;
        return null;
    }

    @Deprecated
    public String findDeserializationName(AnnotatedParameter annotatedParameter) {
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        return null;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return false;
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return false;
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        Annotated annotated2 = annotated;
        return false;
    }

    @Deprecated
    public static class Pair extends AnnotationIntrospectorPair {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        @Deprecated
        public Pair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
            super(annotationIntrospector, annotationIntrospector2);
        }
    }
}
