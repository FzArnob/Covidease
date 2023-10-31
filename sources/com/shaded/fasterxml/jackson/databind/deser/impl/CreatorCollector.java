package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.deser.CreatorProperty;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Member;
import java.util.HashMap;

public class CreatorCollector {
    protected final BeanDescription _beanDesc;
    protected AnnotatedWithParams _booleanCreator;
    protected final boolean _canFixAccess;
    protected AnnotatedWithParams _defaultConstructor;
    protected CreatorProperty[] _delegateArgs;
    protected AnnotatedWithParams _delegateCreator;
    protected AnnotatedWithParams _doubleCreator;
    protected AnnotatedParameter _incompleteParameter;
    protected AnnotatedWithParams _intCreator;
    protected AnnotatedWithParams _longCreator;
    protected CreatorProperty[] _propertyBasedArgs = null;
    protected AnnotatedWithParams _propertyBasedCreator;
    protected AnnotatedWithParams _stringCreator;

    public CreatorCollector(BeanDescription beanDescription, boolean z) {
        this._beanDesc = beanDescription;
        this._canFixAccess = z;
    }

    public ValueInstantiator constructValueInstantiator(DeserializationConfig deserializationConfig) {
        StdValueInstantiator stdValueInstantiator;
        JavaType resolveType;
        new StdValueInstantiator(deserializationConfig, this._beanDesc.getType());
        StdValueInstantiator stdValueInstantiator2 = stdValueInstantiator;
        if (this._delegateCreator == null) {
            resolveType = null;
        } else {
            int i = 0;
            if (this._delegateArgs != null) {
                int i2 = 0;
                int length = this._delegateArgs.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (this._delegateArgs[i2] == null) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            resolveType = this._beanDesc.bindingsForBeanType().resolveType(this._delegateCreator.getGenericParameterType(i));
        }
        stdValueInstantiator2.configureFromObjectSettings(this._defaultConstructor, this._delegateCreator, resolveType, this._delegateArgs, this._propertyBasedCreator, this._propertyBasedArgs);
        stdValueInstantiator2.configureFromStringCreator(this._stringCreator);
        stdValueInstantiator2.configureFromIntCreator(this._intCreator);
        stdValueInstantiator2.configureFromLongCreator(this._longCreator);
        stdValueInstantiator2.configureFromDoubleCreator(this._doubleCreator);
        stdValueInstantiator2.configureFromBooleanCreator(this._booleanCreator);
        stdValueInstantiator2.configureIncompleteParameter(this._incompleteParameter);
        return stdValueInstantiator2;
    }

    @Deprecated
    public void setDefaultConstructor(AnnotatedConstructor annotatedConstructor) {
        this._defaultConstructor = (AnnotatedWithParams) _fixAccess(annotatedConstructor);
    }

    public void setDefaultCreator(AnnotatedWithParams annotatedWithParams) {
        AnnotatedWithParams annotatedWithParams2 = annotatedWithParams;
        if (annotatedWithParams2 instanceof AnnotatedConstructor) {
            setDefaultConstructor((AnnotatedConstructor) annotatedWithParams2);
            return;
        }
        this._defaultConstructor = (AnnotatedWithParams) _fixAccess(annotatedWithParams2);
    }

    public void addStringCreator(AnnotatedWithParams annotatedWithParams) {
        this._stringCreator = verifyNonDup(annotatedWithParams, this._stringCreator, "String");
    }

    public void addIntCreator(AnnotatedWithParams annotatedWithParams) {
        this._intCreator = verifyNonDup(annotatedWithParams, this._intCreator, "int");
    }

    public void addLongCreator(AnnotatedWithParams annotatedWithParams) {
        this._longCreator = verifyNonDup(annotatedWithParams, this._longCreator, "long");
    }

    public void addDoubleCreator(AnnotatedWithParams annotatedWithParams) {
        this._doubleCreator = verifyNonDup(annotatedWithParams, this._doubleCreator, "double");
    }

    public void addBooleanCreator(AnnotatedWithParams annotatedWithParams) {
        this._booleanCreator = verifyNonDup(annotatedWithParams, this._booleanCreator, PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN);
    }

    public void addDelegatingCreator(AnnotatedWithParams annotatedWithParams, CreatorProperty[] creatorPropertyArr) {
        this._delegateCreator = verifyNonDup(annotatedWithParams, this._delegateCreator, "delegate");
        this._delegateArgs = creatorPropertyArr;
    }

    public void addPropertyCreator(AnnotatedWithParams annotatedWithParams, CreatorProperty[] creatorPropertyArr) {
        HashMap hashMap;
        Integer num;
        Throwable th;
        StringBuilder sb;
        CreatorProperty[] creatorPropertyArr2 = creatorPropertyArr;
        this._propertyBasedCreator = verifyNonDup(annotatedWithParams, this._propertyBasedCreator, "property-based");
        if (creatorPropertyArr2.length > 1) {
            new HashMap();
            HashMap hashMap2 = hashMap;
            int length = creatorPropertyArr2.length;
            for (int i = 0; i < length; i++) {
                String name = creatorPropertyArr2[i].getName();
                if ((name.length() != 0 || creatorPropertyArr2[i].getInjectableValueId() == null) && (num = (Integer) hashMap2.put(name, Integer.valueOf(i))) != null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Duplicate creator property \"").append(name).append("\" (index ").append(num).append(" vs ").append(i).append(")").toString());
                    throw th2;
                }
            }
        }
        this._propertyBasedArgs = creatorPropertyArr2;
    }

    public void addIncompeteParameter(AnnotatedParameter annotatedParameter) {
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        if (this._incompleteParameter == null) {
            this._incompleteParameter = annotatedParameter2;
        }
    }

    public boolean hasDefaultCreator() {
        return this._defaultConstructor != null;
    }

    private <T extends AnnotatedMember> T _fixAccess(T t) {
        T t2 = t;
        if (t2 != null && this._canFixAccess) {
            ClassUtil.checkAndFixAccess((Member) t2.getAnnotated());
        }
        return t2;
    }

    /* access modifiers changed from: protected */
    public AnnotatedWithParams verifyNonDup(AnnotatedWithParams annotatedWithParams, AnnotatedWithParams annotatedWithParams2, String str) {
        Throwable th;
        StringBuilder sb;
        AnnotatedWithParams annotatedWithParams3 = annotatedWithParams;
        AnnotatedWithParams annotatedWithParams4 = annotatedWithParams2;
        String str2 = str;
        if (annotatedWithParams4 == null || annotatedWithParams4.getClass() != annotatedWithParams3.getClass()) {
            return (AnnotatedWithParams) _fixAccess(annotatedWithParams3);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Conflicting ").append(str2).append(" creators: already had ").append(annotatedWithParams4).append(", encountered ").append(annotatedWithParams3).toString());
        throw th2;
    }
}
