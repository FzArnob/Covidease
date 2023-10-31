package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.Named;
import java.lang.annotation.Annotation;

public interface BeanProperty extends Named {
    void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor) throws JsonMappingException;

    <A extends Annotation> A getAnnotation(Class<A> cls);

    <A extends Annotation> A getContextAnnotation(Class<A> cls);

    AnnotatedMember getMember();

    String getName();

    JavaType getType();

    PropertyName getWrapperName();

    boolean isRequired();

    public static class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final boolean _isRequired;
        protected final AnnotatedMember _member;
        protected final String _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;

        public Std(String str, JavaType javaType, PropertyName propertyName, Annotations annotations, AnnotatedMember annotatedMember, boolean z) {
            this._name = str;
            this._type = javaType;
            this._wrapperName = propertyName;
            this._isRequired = z;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        public Std withType(JavaType javaType) {
            Std std;
            new Std(this._name, javaType, this._wrapperName, this._contextAnnotations, this._member, this._isRequired);
            return std;
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return this._member == null ? null : this._member.getAnnotation(cls);
        }

        public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
            return this._contextAnnotations == null ? null : this._contextAnnotations.get(cls);
        }

        public String getName() {
            return this._name;
        }

        public JavaType getType() {
            return this._type;
        }

        public PropertyName getWrapperName() {
            return this._wrapperName;
        }

        public boolean isRequired() {
            return this._isRequired;
        }

        public AnnotatedMember getMember() {
            return this._member;
        }

        public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor) {
            Throwable th;
            StringBuilder sb;
            JsonObjectFormatVisitor jsonObjectFormatVisitor2 = jsonObjectFormatVisitor;
            Throwable th2 = th;
            new StringBuilder();
            new UnsupportedOperationException(sb.append("Instances of ").append(getClass().getName()).append(" should not get visited").toString());
            throw th2;
        }
    }
}
