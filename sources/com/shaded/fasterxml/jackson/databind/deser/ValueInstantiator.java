package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;

public abstract class ValueInstantiator {
    public abstract String getValueTypeDesc();

    public ValueInstantiator() {
    }

    public boolean canInstantiate() {
        return canCreateUsingDefault() || canCreateUsingDelegate() || canCreateFromObjectWith() || canCreateFromString() || canCreateFromInt() || canCreateFromLong() || canCreateFromDouble() || canCreateFromBoolean();
    }

    public boolean canCreateFromString() {
        return false;
    }

    public boolean canCreateFromInt() {
        return false;
    }

    public boolean canCreateFromLong() {
        return false;
    }

    public boolean canCreateFromDouble() {
        return false;
    }

    public boolean canCreateFromBoolean() {
        return false;
    }

    public boolean canCreateUsingDefault() {
        return getDefaultCreator() != null;
    }

    public boolean canCreateUsingDelegate() {
        return false;
    }

    public boolean canCreateFromObjectWith() {
        return false;
    }

    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        return null;
    }

    public JavaType getDelegateType(DeserializationConfig deserializationConfig) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        return null;
    }

    public Object createUsingDefault(DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append("; no default creator found").toString());
        throw th2;
    }

    public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object[] objArr2 = objArr;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" with arguments").toString());
        throw th2;
    }

    public Object createUsingDelegate(DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" using delegate").toString());
        throw th2;
    }

    public Object createFromString(DeserializationContext deserializationContext, String str) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        String str2 = str;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from String value").toString());
        throw th2;
    }

    public Object createFromInt(DeserializationContext deserializationContext, int i) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        int i2 = i;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Integer number (int)").toString());
        throw th2;
    }

    public Object createFromLong(DeserializationContext deserializationContext, long j) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        long j2 = j;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Integer number (long)").toString());
        throw th2;
    }

    public Object createFromDouble(DeserializationContext deserializationContext, double d) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        double d2 = d;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Floating-point number (double)").toString());
        throw th2;
    }

    public Object createFromBoolean(DeserializationContext deserializationContext, boolean z) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        boolean z2 = z;
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not instantiate value of type ").append(getValueTypeDesc()).append(" from Boolean value").toString());
        throw th2;
    }

    public AnnotatedWithParams getDefaultCreator() {
        return null;
    }

    public AnnotatedWithParams getDelegateCreator() {
        return null;
    }

    public AnnotatedWithParams getWithArgsCreator() {
        return null;
    }

    public AnnotatedParameter getIncompleteParameter() {
        return null;
    }
}
