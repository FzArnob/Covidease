package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.io.IOException;
import java.lang.reflect.Method;

public final class SettableAnyProperty {
    protected final BeanProperty _property;
    protected final Method _setter;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMethod annotatedMethod, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this(beanProperty, annotatedMethod.getAnnotated(), javaType, jsonDeserializer);
    }

    public SettableAnyProperty(BeanProperty beanProperty, Method method, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this._property = beanProperty;
        this._type = javaType;
        this._setter = method;
        this._valueDeserializer = jsonDeserializer;
    }

    public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        SettableAnyProperty settableAnyProperty;
        new SettableAnyProperty(this._property, this._setter, this._type, jsonDeserializer);
        return settableAnyProperty;
    }

    public BeanProperty getProperty() {
        return this._property;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public JavaType getType() {
        return this._type;
    }

    public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        set(obj, str, deserialize(jsonParser, deserializationContext));
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParser2.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return this._valueDeserializer.deserialize(jsonParser2, deserializationContext2);
    }

    public final void set(Object obj, String str, Object obj2) throws IOException {
        Object obj3 = obj;
        String str2 = str;
        Object obj4 = obj2;
        try {
            Object[] objArr = new Object[2];
            objArr[0] = str2;
            Object[] objArr2 = objArr;
            objArr2[1] = obj4;
            Object invoke = this._setter.invoke(obj3, objArr2);
        } catch (Exception e) {
            _throwAsIOE(e, str2, obj4);
        }
    }

    /* access modifiers changed from: protected */
    public void _throwAsIOE(Exception exc, String str, Object obj) throws IOException {
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th2;
        Exception exc2 = exc;
        String str2 = str;
        Object obj2 = obj;
        if (exc2 instanceof IllegalArgumentException) {
            String name = obj2 == null ? "[NULL]" : obj2.getClass().getName();
            new StringBuilder("Problem deserializing \"any\" property '");
            StringBuilder append = sb.append(str2);
            new StringBuilder();
            StringBuilder append2 = append.append(sb2.append("' of class ").append(getClassName()).append(" (expected type: ").toString()).append(this._type);
            StringBuilder append3 = append.append("; actual type: ").append(name).append(")");
            String message = exc2.getMessage();
            if (message != null) {
                StringBuilder append4 = append.append(", problem: ").append(message);
            } else {
                StringBuilder append5 = append.append(" (no error message provided)");
            }
            Throwable th3 = th2;
            new JsonMappingException(append.toString(), (JsonLocation) null, exc2);
            throw th3;
        } else if (exc2 instanceof IOException) {
            throw ((IOException) exc2);
        } else if (exc2 instanceof RuntimeException) {
            throw ((RuntimeException) exc2);
        } else {
            Throwable th4 = exc2;
            while (true) {
                Throwable th5 = th4;
                if (th5.getCause() != null) {
                    th4 = th5.getCause();
                } else {
                    Throwable th6 = th;
                    new JsonMappingException(th5.getMessage(), (JsonLocation) null, th5);
                    throw th6;
                }
            }
        }
    }

    private String getClassName() {
        return this._setter.getDeclaringClass().getName();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[any property on class ").append(getClassName()).append("]").toString();
    }
}
