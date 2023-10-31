package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;

public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector = this._config.getAnnotationIntrospector();
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonInclude.Include _outputProps;

    public PropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        this._config = serializationConfig2;
        this._beanDesc = beanDescription2;
        this._outputProps = beanDescription2.findSerializationInclusion(serializationConfig2.getSerializationInclusion());
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    /* access modifiers changed from: protected */
    public BeanPropertyWriter buildWriter(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, TypeSerializer typeSerializer2, AnnotatedMember annotatedMember, boolean z) {
        BeanPropertyWriter beanPropertyWriter;
        Throwable th;
        StringBuilder sb;
        BeanPropertyDefinition beanPropertyDefinition2 = beanPropertyDefinition;
        JavaType javaType2 = javaType;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        TypeSerializer typeSerializer3 = typeSerializer;
        TypeSerializer typeSerializer4 = typeSerializer2;
        AnnotatedMember annotatedMember2 = annotatedMember;
        JavaType findSerializationType = findSerializationType(annotatedMember2, z, javaType2);
        if (typeSerializer4 != null) {
            if (findSerializationType == null) {
                findSerializationType = javaType2;
            }
            if (findSerializationType.getContentType() == null) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Problem trying to create BeanPropertyWriter for property '").append(beanPropertyDefinition2.getName()).append("' (of type ").append(this._beanDesc.getType()).append("); serialization type ").append(findSerializationType).append(" has no content").toString());
                throw th2;
            }
            findSerializationType = findSerializationType.withContentTypeHandler(typeSerializer4);
            JavaType contentType = findSerializationType.getContentType();
        }
        Object obj = null;
        boolean z2 = false;
        JsonInclude.Include findSerializationInclusion = this._annotationIntrospector.findSerializationInclusion(annotatedMember2, this._outputProps);
        if (findSerializationInclusion != null) {
            switch (findSerializationInclusion) {
                case NON_DEFAULT:
                    obj = getDefaultValue(beanPropertyDefinition2.getName(), annotatedMember2);
                    if (obj != null) {
                        if (obj.getClass().isArray()) {
                            obj = ArrayBuilders.getArrayComparator(obj);
                            break;
                        }
                    } else {
                        z2 = true;
                        break;
                    }
                    break;
                case NON_EMPTY:
                    z2 = true;
                    obj = BeanPropertyWriter.MARKER_FOR_EMPTY;
                    break;
                case NON_NULL:
                    z2 = true;
                    break;
                case ALWAYS:
                    break;
            }
            if (javaType2.isContainerType() && !this._config.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)) {
                obj = BeanPropertyWriter.MARKER_FOR_EMPTY;
            }
        }
        new BeanPropertyWriter(beanPropertyDefinition2, annotatedMember2, this._beanDesc.getClassAnnotations(), javaType2, jsonSerializer2, typeSerializer3, findSerializationType, z2, obj);
        BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
        NameTransformer findUnwrappingNameTransformer = this._annotationIntrospector.findUnwrappingNameTransformer(annotatedMember2);
        if (findUnwrappingNameTransformer != null) {
            beanPropertyWriter2 = beanPropertyWriter2.unwrappingWriter(findUnwrappingNameTransformer);
        }
        return beanPropertyWriter2;
    }

    /* access modifiers changed from: protected */
    public JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JavaType javaType2;
        JsonSerialize.Typing findSerializationTyping;
        Throwable th;
        StringBuilder sb;
        Annotated annotated2 = annotated;
        boolean z2 = z;
        JavaType javaType3 = javaType;
        Class<?> findSerializationType = this._annotationIntrospector.findSerializationType(annotated2);
        if (findSerializationType != null) {
            Class<?> rawClass = javaType3.getRawClass();
            if (findSerializationType.isAssignableFrom(rawClass)) {
                javaType3 = javaType3.widenBy(findSerializationType);
            } else if (!rawClass.isAssignableFrom(findSerializationType)) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Illegal concrete-type annotation for method '").append(annotated2.getName()).append("': class ").append(findSerializationType.getName()).append(" not a super-type of (declared) class ").append(rawClass.getName()).toString());
                throw th2;
            } else {
                javaType3 = this._config.constructSpecializedType(javaType3, findSerializationType);
            }
            z2 = true;
        }
        JavaType modifySecondaryTypesByAnnotation = BeanSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated2, javaType3);
        if (modifySecondaryTypesByAnnotation != javaType3) {
            z2 = true;
            javaType3 = modifySecondaryTypesByAnnotation;
        }
        if (!z2 && (findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated2)) != null) {
            z2 = findSerializationTyping == JsonSerialize.Typing.STATIC;
        }
        if (z2) {
            javaType2 = javaType3;
        } else {
            javaType2 = null;
        }
        return javaType2;
    }

    /* access modifiers changed from: protected */
    public Object getDefaultBean() {
        Throwable th;
        StringBuilder sb;
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (this._defaultBean == null) {
                Class<?> annotated = this._beanDesc.getClassInfo().getAnnotated();
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Class ").append(annotated.getName()).append(" has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation").toString());
                throw th2;
            }
        }
        return this._defaultBean;
    }

    /* access modifiers changed from: protected */
    public Object getDefaultValue(String str, AnnotatedMember annotatedMember) {
        String str2 = str;
        Object defaultBean = getDefaultBean();
        try {
            return annotatedMember.getValue(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str2, defaultBean);
        }
    }

    /* access modifiers changed from: protected */
    public Object _throwWrapped(Exception exc, String str, Object obj) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        String str2 = str;
        Object obj2 = obj;
        Throwable th3 = exc;
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
            new IllegalArgumentException(sb.append("Failed to get property '").append(str2).append("' of default ").append(obj2.getClass().getName()).append(" instance").toString());
            throw th4;
        }
    }
}
