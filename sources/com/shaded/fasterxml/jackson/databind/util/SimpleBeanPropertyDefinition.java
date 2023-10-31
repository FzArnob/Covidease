package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class SimpleBeanPropertyDefinition extends BeanPropertyDefinition {
    protected final AnnotationIntrospector _introspector;
    protected final AnnotatedMember _member;
    protected final String _name;

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimpleBeanPropertyDefinition(com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = r1
            java.lang.String r4 = r4.getName()
            r5 = 0
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition.<init>(com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public SimpleBeanPropertyDefinition(AnnotatedMember annotatedMember, String str) {
        this(annotatedMember, str, (AnnotationIntrospector) null);
    }

    private SimpleBeanPropertyDefinition(AnnotatedMember annotatedMember, String str, AnnotationIntrospector annotationIntrospector) {
        this._introspector = annotationIntrospector;
        this._member = annotatedMember;
        this._name = str;
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMember annotatedMember2 = annotatedMember;
        SimpleBeanPropertyDefinition simpleBeanPropertyDefinition = r7;
        SimpleBeanPropertyDefinition simpleBeanPropertyDefinition2 = new SimpleBeanPropertyDefinition(annotatedMember2, annotatedMember2.getName(), mapperConfig2 == null ? null : mapperConfig2.getAnnotationIntrospector());
        return simpleBeanPropertyDefinition;
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, String str) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        SimpleBeanPropertyDefinition simpleBeanPropertyDefinition = r8;
        SimpleBeanPropertyDefinition simpleBeanPropertyDefinition2 = new SimpleBeanPropertyDefinition(annotatedMember, str, mapperConfig2 == null ? null : mapperConfig2.getAnnotationIntrospector());
        return simpleBeanPropertyDefinition;
    }

    public SimpleBeanPropertyDefinition withName(String str) {
        SimpleBeanPropertyDefinition simpleBeanPropertyDefinition;
        String str2 = str;
        if (this._name.equals(str2)) {
            return this;
        }
        new SimpleBeanPropertyDefinition(this._member, str2, this._introspector);
        return simpleBeanPropertyDefinition;
    }

    public String getName() {
        return this._name;
    }

    public String getInternalName() {
        return getName();
    }

    public PropertyName getWrapperName() {
        return this._introspector == null ? null : this._introspector.findWrapperName(this._member);
    }

    public boolean isExplicitlyIncluded() {
        return false;
    }

    public boolean hasGetter() {
        return getGetter() != null;
    }

    public boolean hasSetter() {
        return getSetter() != null;
    }

    public boolean hasField() {
        return this._member instanceof AnnotatedField;
    }

    public boolean hasConstructorParameter() {
        return this._member instanceof AnnotatedParameter;
    }

    public AnnotatedMethod getGetter() {
        if (!(this._member instanceof AnnotatedMethod) || ((AnnotatedMethod) this._member).getParameterCount() != 0) {
            return null;
        }
        return (AnnotatedMethod) this._member;
    }

    public AnnotatedMethod getSetter() {
        if (!(this._member instanceof AnnotatedMethod) || ((AnnotatedMethod) this._member).getParameterCount() != 1) {
            return null;
        }
        return (AnnotatedMethod) this._member;
    }

    public AnnotatedField getField() {
        return this._member instanceof AnnotatedField ? (AnnotatedField) this._member : null;
    }

    public AnnotatedParameter getConstructorParameter() {
        return this._member instanceof AnnotatedParameter ? (AnnotatedParameter) this._member : null;
    }

    public AnnotatedMember getAccessor() {
        AnnotatedMember getter = getGetter();
        if (getter == null) {
            getter = getField();
        }
        return getter;
    }

    public AnnotatedMember getMutator() {
        AnnotatedMember constructorParameter = getConstructorParameter();
        if (constructorParameter == null) {
            constructorParameter = getSetter();
            if (constructorParameter == null) {
                constructorParameter = getField();
            }
        }
        return constructorParameter;
    }

    public AnnotatedMember getPrimaryMember() {
        return this._member;
    }
}
