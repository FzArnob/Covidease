package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.annotation.JacksonAnnotation;
import com.shaded.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.shaded.fasterxml.jackson.annotation.JacksonInject;
import com.shaded.fasterxml.jackson.annotation.JsonAnyGetter;
import com.shaded.fasterxml.jackson.annotation.JsonAnySetter;
import com.shaded.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shaded.fasterxml.jackson.annotation.JsonBackReference;
import com.shaded.fasterxml.jackson.annotation.JsonCreator;
import com.shaded.fasterxml.jackson.annotation.JsonFilter;
import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.JsonGetter;
import com.shaded.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.shaded.fasterxml.jackson.annotation.JsonIdentityReference;
import com.shaded.fasterxml.jackson.annotation.JsonIgnore;
import com.shaded.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shaded.fasterxml.jackson.annotation.JsonIgnoreType;
import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.annotation.JsonManagedReference;
import com.shaded.fasterxml.jackson.annotation.JsonProperty;
import com.shaded.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shaded.fasterxml.jackson.annotation.JsonRawValue;
import com.shaded.fasterxml.jackson.annotation.JsonRootName;
import com.shaded.fasterxml.jackson.annotation.JsonSetter;
import com.shaded.fasterxml.jackson.annotation.JsonSubTypes;
import com.shaded.fasterxml.jackson.annotation.JsonTypeId;
import com.shaded.fasterxml.jackson.annotation.JsonTypeName;
import com.shaded.fasterxml.jackson.annotation.JsonUnwrapped;
import com.shaded.fasterxml.jackson.annotation.JsonValue;
import com.shaded.fasterxml.jackson.annotation.JsonView;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shaded.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaded.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.cfg.PackageVersion;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class JacksonAnnotationIntrospector extends AnnotationIntrospector implements Serializable {
    private static final long serialVersionUID = 1;

    public JacksonAnnotationIntrospector() {
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    @Deprecated
    public boolean isHandled(Annotation annotation) {
        return annotation.annotationType().getAnnotation(JacksonAnnotation.class) != null;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        return annotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null;
    }

    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        PropertyName propertyName;
        JsonRootName jsonRootName = (JsonRootName) annotatedClass.getAnnotation(JsonRootName.class);
        if (jsonRootName == null) {
            return null;
        }
        new PropertyName(jsonRootName.value());
        return propertyName;
    }

    public String[] findPropertiesToIgnore(Annotated annotated) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotated.getAnnotation(JsonIgnoreProperties.class);
        return jsonIgnoreProperties == null ? null : jsonIgnoreProperties.value();
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        return jsonIgnoreProperties == null ? null : Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) annotatedClass.getAnnotation(JsonIgnoreType.class);
        return jsonIgnoreType == null ? null : Boolean.valueOf(jsonIgnoreType.value());
    }

    public Object findFilterId(AnnotatedClass annotatedClass) {
        JsonFilter jsonFilter = (JsonFilter) annotatedClass.getAnnotation(JsonFilter.class);
        if (jsonFilter != null) {
            String value = jsonFilter.value();
            if (value.length() > 0) {
                return value;
            }
        }
        return null;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        JsonNaming jsonNaming = (JsonNaming) annotatedClass.getAnnotation(JsonNaming.class);
        return jsonNaming == null ? null : jsonNaming.value();
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) annotatedClass.getAnnotation(JsonAutoDetect.class);
        return jsonAutoDetect == null ? visibilityChecker2 : visibilityChecker2.with(jsonAutoDetect);
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        JsonManagedReference jsonManagedReference = (JsonManagedReference) annotatedMember2.getAnnotation(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            return AnnotationIntrospector.ReferenceProperty.managed(jsonManagedReference.value());
        }
        JsonBackReference jsonBackReference = (JsonBackReference) annotatedMember2.getAnnotation(JsonBackReference.class);
        if (jsonBackReference != null) {
            return AnnotationIntrospector.ReferenceProperty.back(jsonBackReference.value());
        }
        return null;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        JsonUnwrapped jsonUnwrapped = (JsonUnwrapped) annotatedMember.getAnnotation(JsonUnwrapped.class);
        if (jsonUnwrapped == null || !jsonUnwrapped.enabled()) {
            return null;
        }
        return NameTransformer.simpleTransformer(jsonUnwrapped.prefix(), jsonUnwrapped.suffix());
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return _isIgnorable(annotatedMember);
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMember.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return Boolean.valueOf(jsonProperty.required());
        }
        return null;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        AnnotatedMember annotatedMember2 = annotatedMember;
        JacksonInject jacksonInject = (JacksonInject) annotatedMember2.getAnnotation(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (!(annotatedMember2 instanceof AnnotatedMethod)) {
            return annotatedMember2.getRawType().getName();
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember2;
        if (annotatedMethod.getParameterCount() == 0) {
            return annotatedMember2.getRawType().getName();
        }
        return annotatedMethod.getRawParameterType(0).getName();
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return _findTypeResolver(mapperConfig, annotatedClass, javaType);
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType javaType2 = javaType;
        if (javaType2.isContainerType()) {
            return null;
        }
        return _findTypeResolver(mapperConfig2, annotatedMember2, javaType2);
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        Throwable th;
        StringBuilder sb;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType javaType2 = javaType;
        if (javaType2.isContainerType()) {
            return _findTypeResolver(mapperConfig2, annotatedMember2, javaType2);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Must call method with a container type (got ").append(javaType2).append(")").toString());
        throw th2;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        ArrayList arrayList;
        Object obj;
        JsonSubTypes jsonSubTypes = (JsonSubTypes) annotated.getAnnotation(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        new ArrayList(value.length);
        ArrayList arrayList2 = arrayList;
        JsonSubTypes.Type[] typeArr = value;
        int length = typeArr.length;
        for (int i = 0; i < length; i++) {
            JsonSubTypes.Type type = typeArr[i];
            new NamedType(type.value(), type.name());
            boolean add = arrayList2.add(obj);
        }
        return arrayList2;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        JsonTypeName jsonTypeName = (JsonTypeName) annotatedClass.getAnnotation(JsonTypeName.class);
        return jsonTypeName == null ? null : jsonTypeName.value();
    }

    public Object findSerializer(Annotated annotated) {
        Object obj;
        Class<? extends JsonSerializer<?>> using;
        Annotated annotated2 = annotated;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated2.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null && (using = jsonSerialize.using()) != JsonSerializer.None.class) {
            return using;
        }
        JsonRawValue jsonRawValue = (JsonRawValue) annotated2.getAnnotation(JsonRawValue.class);
        if (jsonRawValue == null || !jsonRawValue.value()) {
            return null;
        }
        new RawSerializer(annotated2.getRawType());
        return obj;
    }

    public Class<? extends JsonSerializer<?>> findKeySerializer(Annotated annotated) {
        Class<? extends JsonSerializer<?>> keyUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (keyUsing = jsonSerialize.keyUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    public Class<? extends JsonSerializer<?>> findContentSerializer(Annotated annotated) {
        Class<? extends JsonSerializer<?>> contentUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (contentUsing = jsonSerialize.contentUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    public JsonInclude.Include findSerializationInclusion(Annotated annotated, JsonInclude.Include include) {
        Annotated annotated2 = annotated;
        JsonInclude.Include include2 = include;
        JsonInclude jsonInclude = (JsonInclude) annotated2.getAnnotation(JsonInclude.class);
        if (jsonInclude != null) {
            return jsonInclude.value();
        }
        JsonSerialize jsonSerialize = (JsonSerialize) annotated2.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            switch (jsonSerialize.include()) {
                case ALWAYS:
                    return JsonInclude.Include.ALWAYS;
                case NON_NULL:
                    return JsonInclude.Include.NON_NULL;
                case NON_DEFAULT:
                    return JsonInclude.Include.NON_DEFAULT;
                case NON_EMPTY:
                    return JsonInclude.Include.NON_EMPTY;
            }
        }
        return include2;
    }

    public Class<?> findSerializationType(Annotated annotated) {
        Class<?> as;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (as = jsonSerialize.mo21065as()) == NoClass.class) {
            return null;
        }
        return as;
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> keyAs;
        JavaType javaType2 = javaType;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (keyAs = jsonSerialize.keyAs()) == NoClass.class) {
            return null;
        }
        return keyAs;
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> contentAs;
        JavaType javaType2 = javaType;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (contentAs = jsonSerialize.contentAs()) == NoClass.class) {
            return null;
        }
        return contentAs;
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : jsonSerialize.typing();
    }

    public Object findSerializationConverter(Annotated annotated) {
        Class<? extends Converter<?, ?>> converter;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (converter = jsonSerialize.converter()) == Converter.None.class) {
            return null;
        }
        return converter;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        Class<? extends Converter<?, ?>> contentConverter;
        JsonSerialize jsonSerialize = (JsonSerialize) annotatedMember.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (contentConverter = jsonSerialize.contentConverter()) == Converter.None.class) {
            return null;
        }
        return contentConverter;
    }

    public Class<?>[] findViews(Annotated annotated) {
        JsonView jsonView = (JsonView) annotated.getAnnotation(JsonView.class);
        return jsonView == null ? null : jsonView.value();
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        return Boolean.valueOf(annotatedMember.hasAnnotation(JsonTypeId.class));
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        ObjectIdInfo objectIdInfo;
        JsonIdentityInfo jsonIdentityInfo = (JsonIdentityInfo) annotated.getAnnotation(JsonIdentityInfo.class);
        if (jsonIdentityInfo == null || jsonIdentityInfo.generator() == ObjectIdGenerators.None.class) {
            return null;
        }
        new ObjectIdInfo(jsonIdentityInfo.property(), jsonIdentityInfo.scope(), jsonIdentityInfo.generator());
        return objectIdInfo;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        ObjectIdInfo objectIdInfo2 = objectIdInfo;
        JsonIdentityReference jsonIdentityReference = (JsonIdentityReference) annotated.getAnnotation(JsonIdentityReference.class);
        if (jsonIdentityReference != null) {
            objectIdInfo2 = objectIdInfo2.withAlwaysAsId(jsonIdentityReference.alwaysAsId());
        }
        return objectIdInfo2;
    }

    public JsonFormat.Value findFormat(AnnotatedMember annotatedMember) {
        return findFormat(annotatedMember);
    }

    public JsonFormat.Value findFormat(Annotated annotated) {
        JsonFormat.Value value;
        JsonFormat.Value value2;
        JsonFormat jsonFormat = (JsonFormat) annotated.getAnnotation(JsonFormat.class);
        if (jsonFormat == null) {
            value2 = null;
        } else {
            value2 = value;
            new JsonFormat.Value(jsonFormat);
        }
        return value2;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        return jsonPropertyOrder == null ? null : jsonPropertyOrder.value();
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        return jsonPropertyOrder == null ? null : Boolean.valueOf(jsonPropertyOrder.alphabetic());
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

    public String findSerializationName(AnnotatedField annotatedField) {
        AnnotatedField annotatedField2 = annotatedField;
        JsonProperty jsonProperty = (JsonProperty) annotatedField2.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (annotatedField2.hasAnnotation(JsonSerialize.class) || annotatedField2.hasAnnotation(JsonView.class)) {
            return "";
        }
        return null;
    }

    public String findSerializationName(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        JsonGetter jsonGetter = (JsonGetter) annotatedMethod2.getAnnotation(JsonGetter.class);
        if (jsonGetter != null) {
            return jsonGetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod2.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (annotatedMethod2.hasAnnotation(JsonSerialize.class) || annotatedMethod2.hasAnnotation(JsonView.class)) {
            return "";
        }
        return null;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        JsonValue jsonValue = (JsonValue) annotatedMethod.getAnnotation(JsonValue.class);
        return jsonValue != null && jsonValue.value();
    }

    public Class<? extends JsonDeserializer<?>> findDeserializer(Annotated annotated) {
        Class<? extends JsonDeserializer<?>> using;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class) {
            return null;
        }
        return using;
    }

    public Class<? extends KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        Class<? extends KeyDeserializer> keyUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyUsing = jsonDeserialize.keyUsing()) == KeyDeserializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    public Class<? extends JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        Class<? extends JsonDeserializer<?>> contentUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        Class<?> as;
        JavaType javaType2 = javaType;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (as = jsonDeserialize.mo21053as()) == NoClass.class) {
            return null;
        }
        return as;
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> keyAs;
        JavaType javaType2 = javaType;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyAs = jsonDeserialize.keyAs()) == NoClass.class) {
            return null;
        }
        return keyAs;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> contentAs;
        JavaType javaType2 = javaType;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentAs = jsonDeserialize.contentAs()) == NoClass.class) {
            return null;
        }
        return contentAs;
    }

    public Object findDeserializationConverter(Annotated annotated) {
        Class<? extends Converter<?, ?>> converter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (converter = jsonDeserialize.converter()) == Converter.None.class) {
            return null;
        }
        return converter;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        Class<? extends Converter<?, ?>> contentConverter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotatedMember.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentConverter = jsonDeserialize.contentConverter()) == Converter.None.class) {
            return null;
        }
        return contentConverter;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) annotatedClass.getAnnotation(JsonValueInstantiator.class);
        return jsonValueInstantiator == null ? null : jsonValueInstantiator.value();
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotatedClass.getAnnotation(JsonDeserialize.class);
        return (jsonDeserialize == null || jsonDeserialize.builder() == NoClass.class) ? null : jsonDeserialize.builder();
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        JsonPOJOBuilder.Value value;
        JsonPOJOBuilder.Value value2;
        JsonPOJOBuilder jsonPOJOBuilder = (JsonPOJOBuilder) annotatedClass.getAnnotation(JsonPOJOBuilder.class);
        if (jsonPOJOBuilder == null) {
            value2 = null;
        } else {
            value2 = value;
            new JsonPOJOBuilder.Value(jsonPOJOBuilder);
        }
        return value2;
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

    public String findDeserializationName(AnnotatedMethod annotatedMethod) {
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        JsonSetter jsonSetter = (JsonSetter) annotatedMethod2.getAnnotation(JsonSetter.class);
        if (jsonSetter != null) {
            return jsonSetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod2.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (annotatedMethod2.hasAnnotation(JsonDeserialize.class) || annotatedMethod2.hasAnnotation(JsonView.class) || annotatedMethod2.hasAnnotation(JsonBackReference.class) || annotatedMethod2.hasAnnotation(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    public String findDeserializationName(AnnotatedField annotatedField) {
        AnnotatedField annotatedField2 = annotatedField;
        JsonProperty jsonProperty = (JsonProperty) annotatedField2.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (annotatedField2.hasAnnotation(JsonDeserialize.class) || annotatedField2.hasAnnotation(JsonView.class) || annotatedField2.hasAnnotation(JsonBackReference.class) || annotatedField2.hasAnnotation(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    public String findDeserializationName(AnnotatedParameter annotatedParameter) {
        JsonProperty jsonProperty;
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        if (annotatedParameter2 == null || (jsonProperty = (JsonProperty) annotatedParameter2.getAnnotation(JsonProperty.class)) == null) {
            return null;
        }
        return jsonProperty.value();
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnySetter.class);
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnyGetter.class);
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return annotated.hasAnnotation(JsonCreator.class);
    }

    /* access modifiers changed from: protected */
    public boolean _isIgnorable(Annotated annotated) {
        JsonIgnore jsonIgnore = (JsonIgnore) annotated.getAnnotation(JsonIgnore.class);
        return jsonIgnore != null && jsonIgnore.value();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: com.shaded.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.lang.Class<?>} */
    /* JADX WARNING: type inference failed for: r11v28, types: [com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder<?> _findTypeResolver(com.shaded.fasterxml.jackson.databind.cfg.MapperConfig<?> r15, com.shaded.fasterxml.jackson.databind.introspect.Annotated r16, com.shaded.fasterxml.jackson.databind.JavaType r17) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r11 = r2
            java.lang.Class<com.shaded.fasterxml.jackson.annotation.JsonTypeInfo> r12 = com.shaded.fasterxml.jackson.annotation.JsonTypeInfo.class
            java.lang.annotation.Annotation r11 = r11.getAnnotation(r12)
            com.shaded.fasterxml.jackson.annotation.JsonTypeInfo r11 = (com.shaded.fasterxml.jackson.annotation.JsonTypeInfo) r11
            r4 = r11
            r11 = r2
            java.lang.Class<com.shaded.fasterxml.jackson.databind.annotation.JsonTypeResolver> r12 = com.shaded.fasterxml.jackson.databind.annotation.JsonTypeResolver.class
            java.lang.annotation.Annotation r11 = r11.getAnnotation(r12)
            com.shaded.fasterxml.jackson.databind.annotation.JsonTypeResolver r11 = (com.shaded.fasterxml.jackson.databind.annotation.JsonTypeResolver) r11
            r5 = r11
            r11 = r5
            if (r11 == 0) goto L_0x0097
            r11 = r4
            if (r11 != 0) goto L_0x0023
            r11 = 0
            r0 = r11
        L_0x0022:
            return r0
        L_0x0023:
            r11 = r1
            r12 = r2
            r13 = r5
            java.lang.Class r13 = r13.value()
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r11 = r11.typeResolverBuilderInstance(r12, r13)
            r6 = r11
        L_0x002f:
            r11 = r2
            java.lang.Class<com.shaded.fasterxml.jackson.databind.annotation.JsonTypeIdResolver> r12 = com.shaded.fasterxml.jackson.databind.annotation.JsonTypeIdResolver.class
            java.lang.annotation.Annotation r11 = r11.getAnnotation(r12)
            com.shaded.fasterxml.jackson.databind.annotation.JsonTypeIdResolver r11 = (com.shaded.fasterxml.jackson.databind.annotation.JsonTypeIdResolver) r11
            r7 = r11
            r11 = r7
            if (r11 != 0) goto L_0x00b6
            r11 = 0
        L_0x003d:
            r8 = r11
            r11 = r8
            if (r11 == 0) goto L_0x0046
            r11 = r8
            r12 = r3
            r11.init(r12)
        L_0x0046:
            r11 = r6
            r12 = r4
            com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$Id r12 = r12.use()
            r13 = r8
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r11 = r11.init(r12, r13)
            r6 = r11
            r11 = r4
            com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$As r11 = r11.include()
            r9 = r11
            r11 = r9
            com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$As r12 = com.shaded.fasterxml.jackson.annotation.JsonTypeInfo.C1433As.EXTERNAL_PROPERTY
            if (r11 != r12) goto L_0x0065
            r11 = r2
            boolean r11 = r11 instanceof com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass
            if (r11 == 0) goto L_0x0065
            com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$As r11 = com.shaded.fasterxml.jackson.annotation.JsonTypeInfo.C1433As.PROPERTY
            r9 = r11
        L_0x0065:
            r11 = r6
            r12 = r9
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r11 = r11.inclusion(r12)
            r6 = r11
            r11 = r6
            r12 = r4
            java.lang.String r12 = r12.property()
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r11 = r11.typeProperty(r12)
            r6 = r11
            r11 = r4
            java.lang.Class r11 = r11.defaultImpl()
            r10 = r11
            r11 = r10
            java.lang.Class<com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$None> r12 = com.shaded.fasterxml.jackson.annotation.JsonTypeInfo.None.class
            if (r11 == r12) goto L_0x0089
            r11 = r6
            r12 = r10
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r11 = r11.defaultImpl(r12)
            r6 = r11
        L_0x0089:
            r11 = r6
            r12 = r4
            boolean r12 = r12.visible()
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r11 = r11.typeIdVisibility(r12)
            r6 = r11
            r11 = r6
            r0 = r11
            goto L_0x0022
        L_0x0097:
            r11 = r4
            if (r11 != 0) goto L_0x009d
            r11 = 0
            r0 = r11
            goto L_0x0022
        L_0x009d:
            r11 = r4
            com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$Id r11 = r11.use()
            com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$Id r12 = com.shaded.fasterxml.jackson.annotation.JsonTypeInfo.C1434Id.NONE
            if (r11 != r12) goto L_0x00ae
            r11 = r0
            com.shaded.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder r11 = r11._constructNoTypeResolverBuilder()
            r0 = r11
            goto L_0x0022
        L_0x00ae:
            r11 = r0
            com.shaded.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder r11 = r11._constructStdTypeResolverBuilder()
            r6 = r11
            goto L_0x002f
        L_0x00b6:
            r11 = r1
            r12 = r2
            r13 = r7
            java.lang.Class r13 = r13.value()
            com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver r11 = r11.typeIdResolverInstance(r12, r13)
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector._findTypeResolver(com.shaded.fasterxml.jackson.databind.cfg.MapperConfig, com.shaded.fasterxml.jackson.databind.introspect.Annotated, com.shaded.fasterxml.jackson.databind.JavaType):com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder");
    }

    /* access modifiers changed from: protected */
    public StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        StdTypeResolverBuilder stdTypeResolverBuilder;
        new StdTypeResolverBuilder();
        return stdTypeResolverBuilder;
    }

    /* access modifiers changed from: protected */
    public StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
        return StdTypeResolverBuilder.noTypeInfoBuilder();
    }
}
