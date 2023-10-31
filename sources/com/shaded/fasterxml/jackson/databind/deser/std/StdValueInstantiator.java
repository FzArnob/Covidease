package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.CreatorProperty;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;
import java.io.Serializable;

public class StdValueInstantiator extends ValueInstantiator implements Serializable {
    private static final long serialVersionUID = 1;
    protected final boolean _cfgEmptyStringsAsObjects;
    protected CreatorProperty[] _constructorArguments;
    protected AnnotatedWithParams _defaultCreator;
    protected CreatorProperty[] _delegateArguments;
    protected AnnotatedWithParams _delegateCreator;
    protected JavaType _delegateType;
    protected AnnotatedWithParams _fromBooleanCreator;
    protected AnnotatedWithParams _fromDoubleCreator;
    protected AnnotatedWithParams _fromIntCreator;
    protected AnnotatedWithParams _fromLongCreator;
    protected AnnotatedWithParams _fromStringCreator;
    protected AnnotatedParameter _incompleteParameter;
    protected final String _valueTypeDesc;
    protected AnnotatedWithParams _withArgsCreator;

    public StdValueInstantiator(DeserializationConfig deserializationConfig, Class<?> cls) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        Class<?> cls2 = cls;
        this._cfgEmptyStringsAsObjects = deserializationConfig2 == null ? false : deserializationConfig2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        this._valueTypeDesc = cls2 == null ? "UNKNOWN TYPE" : cls2.getName();
    }

    public StdValueInstantiator(DeserializationConfig deserializationConfig, JavaType javaType) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        this._cfgEmptyStringsAsObjects = deserializationConfig2 == null ? false : deserializationConfig2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        this._valueTypeDesc = javaType2 == null ? "UNKNOWN TYPE" : javaType2.toString();
    }

    protected StdValueInstantiator(StdValueInstantiator stdValueInstantiator) {
        StdValueInstantiator stdValueInstantiator2 = stdValueInstantiator;
        this._cfgEmptyStringsAsObjects = stdValueInstantiator2._cfgEmptyStringsAsObjects;
        this._valueTypeDesc = stdValueInstantiator2._valueTypeDesc;
        this._defaultCreator = stdValueInstantiator2._defaultCreator;
        this._constructorArguments = stdValueInstantiator2._constructorArguments;
        this._withArgsCreator = stdValueInstantiator2._withArgsCreator;
        this._delegateType = stdValueInstantiator2._delegateType;
        this._delegateCreator = stdValueInstantiator2._delegateCreator;
        this._delegateArguments = stdValueInstantiator2._delegateArguments;
        this._fromStringCreator = stdValueInstantiator2._fromStringCreator;
        this._fromIntCreator = stdValueInstantiator2._fromIntCreator;
        this._fromLongCreator = stdValueInstantiator2._fromLongCreator;
        this._fromDoubleCreator = stdValueInstantiator2._fromDoubleCreator;
        this._fromBooleanCreator = stdValueInstantiator2._fromBooleanCreator;
    }

    public void configureFromObjectSettings(AnnotatedWithParams annotatedWithParams, AnnotatedWithParams annotatedWithParams2, JavaType javaType, CreatorProperty[] creatorPropertyArr, AnnotatedWithParams annotatedWithParams3, CreatorProperty[] creatorPropertyArr2) {
        this._defaultCreator = annotatedWithParams;
        this._delegateCreator = annotatedWithParams2;
        this._delegateType = javaType;
        this._delegateArguments = creatorPropertyArr;
        this._withArgsCreator = annotatedWithParams3;
        this._constructorArguments = creatorPropertyArr2;
    }

    public void configureFromStringCreator(AnnotatedWithParams annotatedWithParams) {
        AnnotatedWithParams annotatedWithParams2 = annotatedWithParams;
        this._fromStringCreator = annotatedWithParams2;
    }

    public void configureFromIntCreator(AnnotatedWithParams annotatedWithParams) {
        AnnotatedWithParams annotatedWithParams2 = annotatedWithParams;
        this._fromIntCreator = annotatedWithParams2;
    }

    public void configureFromLongCreator(AnnotatedWithParams annotatedWithParams) {
        AnnotatedWithParams annotatedWithParams2 = annotatedWithParams;
        this._fromLongCreator = annotatedWithParams2;
    }

    public void configureFromDoubleCreator(AnnotatedWithParams annotatedWithParams) {
        AnnotatedWithParams annotatedWithParams2 = annotatedWithParams;
        this._fromDoubleCreator = annotatedWithParams2;
    }

    public void configureFromBooleanCreator(AnnotatedWithParams annotatedWithParams) {
        AnnotatedWithParams annotatedWithParams2 = annotatedWithParams;
        this._fromBooleanCreator = annotatedWithParams2;
    }

    public void configureIncompleteParameter(AnnotatedParameter annotatedParameter) {
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        this._incompleteParameter = annotatedParameter2;
    }

    public String getValueTypeDesc() {
        return this._valueTypeDesc;
    }

    public boolean canCreateFromString() {
        return this._fromStringCreator != null;
    }

    public boolean canCreateFromInt() {
        return this._fromIntCreator != null;
    }

    public boolean canCreateFromLong() {
        return this._fromLongCreator != null;
    }

    public boolean canCreateFromDouble() {
        return this._fromDoubleCreator != null;
    }

    public boolean canCreateFromBoolean() {
        return this._fromBooleanCreator != null;
    }

    public boolean canCreateUsingDefault() {
        return this._defaultCreator != null;
    }

    public boolean canCreateUsingDelegate() {
        return this._delegateType != null;
    }

    public boolean canCreateFromObjectWith() {
        return this._withArgsCreator != null;
    }

    public JavaType getDelegateType(DeserializationConfig deserializationConfig) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        return this._delegateType;
    }

    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        return this._constructorArguments;
    }

    public Object createUsingDefault(DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._defaultCreator == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("No default constructor for ").append(getValueTypeDesc()).toString());
            throw th2;
        }
        try {
            return this._defaultCreator.call();
        } catch (ExceptionInInitializerError e) {
            throw wrapException(e);
        } catch (Exception e2) {
            throw wrapException(e2);
        }
    }

    public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object[] objArr2 = objArr;
        if (this._withArgsCreator == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("No with-args constructor for ").append(getValueTypeDesc()).toString());
            throw th2;
        }
        try {
            return this._withArgsCreator.call(objArr2);
        } catch (ExceptionInInitializerError e) {
            throw wrapException(e);
        } catch (Exception e2) {
            throw wrapException(e2);
        }
    }

    public Object createUsingDelegate(DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        if (this._delegateCreator == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("No delegate constructor for ").append(getValueTypeDesc()).toString());
            throw th2;
        }
        try {
            if (this._delegateArguments == null) {
                return this._delegateCreator.call1(obj2);
            }
            int length = this._delegateArguments.length;
            Object[] objArr = new Object[length];
            for (int i = 0; i < length; i++) {
                CreatorProperty creatorProperty = this._delegateArguments[i];
                if (creatorProperty == null) {
                    objArr[i] = obj2;
                } else {
                    objArr[i] = deserializationContext2.findInjectableValue(creatorProperty.getInjectableValueId(), creatorProperty, (Object) null);
                }
            }
            return this._delegateCreator.call(objArr);
        } catch (ExceptionInInitializerError e) {
            throw wrapException(e);
        } catch (Exception e2) {
            throw wrapException(e2);
        }
    }

    public Object createFromString(DeserializationContext deserializationContext, String str) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        String str2 = str;
        if (this._fromStringCreator == null) {
            return _createFromStringFallbacks(deserializationContext2, str2);
        }
        try {
            return this._fromStringCreator.call1(str2);
        } catch (Exception e) {
            throw wrapException(e);
        } catch (ExceptionInInitializerError e2) {
            throw wrapException(e2);
        }
    }

    public Object createFromInt(DeserializationContext deserializationContext, int i) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        int i2 = i;
        try {
            if (this._fromIntCreator != null) {
                return this._fromIntCreator.call1(Integer.valueOf(i2));
            }
            if (this._fromLongCreator != null) {
                return this._fromLongCreator.call1(Long.valueOf((long) i2));
            }
            Throwable th2 = th;
            new StringBuilder();
            new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Integral number; no single-int-arg constructor/factory method").toString());
            throw th2;
        } catch (Exception e) {
            throw wrapException(e);
        } catch (ExceptionInInitializerError e2) {
            throw wrapException(e2);
        }
    }

    public Object createFromLong(DeserializationContext deserializationContext, long j) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        long j2 = j;
        try {
            if (this._fromLongCreator != null) {
                return this._fromLongCreator.call1(Long.valueOf(j2));
            }
            Throwable th2 = th;
            new StringBuilder();
            new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Long integral number; no single-long-arg constructor/factory method").toString());
            throw th2;
        } catch (Exception e) {
            throw wrapException(e);
        } catch (ExceptionInInitializerError e2) {
            throw wrapException(e2);
        }
    }

    public Object createFromDouble(DeserializationContext deserializationContext, double d) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        double d2 = d;
        try {
            if (this._fromDoubleCreator != null) {
                return this._fromDoubleCreator.call1(Double.valueOf(d2));
            }
            Throwable th2 = th;
            new StringBuilder();
            new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Floating-point number; no one-double/Double-arg constructor/factory method").toString());
            throw th2;
        } catch (Exception e) {
            throw wrapException(e);
        } catch (ExceptionInInitializerError e2) {
            throw wrapException(e2);
        }
    }

    public Object createFromBoolean(DeserializationContext deserializationContext, boolean z) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        boolean z2 = z;
        try {
            if (this._fromBooleanCreator != null) {
                return this._fromBooleanCreator.call1(Boolean.valueOf(z2));
            }
            Throwable th2 = th;
            new StringBuilder();
            new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Boolean value; no single-boolean/Boolean-arg constructor/factory method").toString());
            throw th2;
        } catch (Exception e) {
            throw wrapException(e);
        } catch (ExceptionInInitializerError e2) {
            throw wrapException(e2);
        }
    }

    public AnnotatedWithParams getDelegateCreator() {
        return this._delegateCreator;
    }

    public AnnotatedWithParams getDefaultCreator() {
        return this._defaultCreator;
    }

    public AnnotatedWithParams getWithArgsCreator() {
        return this._withArgsCreator;
    }

    public AnnotatedParameter getIncompleteParameter() {
        return this._incompleteParameter;
    }

    /* access modifiers changed from: protected */
    public Object _createFromStringFallbacks(DeserializationContext deserializationContext, String str) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        String str2 = str;
        if (this._fromBooleanCreator != null) {
            String trim = str2.trim();
            if ("true".equals(trim)) {
                return createFromBoolean(deserializationContext2, true);
            }
            if ("false".equals(trim)) {
                return createFromBoolean(deserializationContext2, false);
            }
        }
        if (this._cfgEmptyStringsAsObjects && str2.length() == 0) {
            return null;
        }
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from String value; no single-String constructor/factory method").toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public JsonMappingException wrapException(Throwable th) {
        JsonMappingException jsonMappingException;
        StringBuilder sb;
        Throwable th2 = th;
        while (th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof JsonMappingException) {
            return (JsonMappingException) th2;
        }
        new StringBuilder();
        new JsonMappingException(sb.append("Instantiation of ").append(getValueTypeDesc()).append(" value failed: ").append(th2.getMessage()).toString(), th2);
        return jsonMappingException;
    }
}
