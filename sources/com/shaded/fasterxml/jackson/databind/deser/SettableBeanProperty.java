package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.util.InternCache;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.deser.impl.NullProvider;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.impl.FailingDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.ViewMatcher;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public abstract class SettableBeanProperty implements BeanProperty, Serializable {
    protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER;
    private static final long serialVersionUID = -1026580169193933453L;
    protected final transient Annotations _contextAnnotations;
    protected final boolean _isRequired;
    protected String _managedReferenceName;
    protected final NullProvider _nullProvider;
    protected final String _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected ViewMatcher _viewMatcher;
    protected final PropertyName _wrapperName;

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException;

    public abstract Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException;

    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    public abstract AnnotatedMember getMember();

    public abstract void set(Object obj, Object obj2) throws IOException;

    public abstract Object setAndReturn(Object obj, Object obj2) throws IOException;

    public abstract SettableBeanProperty withName(String str);

    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer);

    static {
        JsonDeserializer<Object> jsonDeserializer;
        new FailingDeserializer("No _valueDeserializer assigned");
        MISSING_VALUE_DESERIALIZER = jsonDeserializer;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected SettableBeanProperty(com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r13, com.shaded.fasterxml.jackson.databind.JavaType r14, com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer r15, com.shaded.fasterxml.jackson.databind.util.Annotations r16) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r0
            r6 = r1
            java.lang.String r6 = r6.getName()
            r7 = r2
            r8 = r1
            com.shaded.fasterxml.jackson.databind.PropertyName r8 = r8.getWrapperName()
            r9 = r3
            r10 = r4
            r11 = r1
            boolean r11 = r11.isRequired()
            r5.<init>(r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty.<init>(com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer, com.shaded.fasterxml.jackson.databind.util.Annotations):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    protected SettableBeanProperty(String str, JavaType javaType, PropertyName propertyName, TypeDeserializer typeDeserializer, Annotations annotations) {
        this(str, javaType, propertyName, typeDeserializer, annotations, false);
    }

    protected SettableBeanProperty(String str, JavaType javaType, PropertyName propertyName, TypeDeserializer typeDeserializer, Annotations annotations, boolean z) {
        String str2 = str;
        JavaType javaType2 = javaType;
        PropertyName propertyName2 = propertyName;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        Annotations annotations2 = annotations;
        boolean z2 = z;
        this._propertyIndex = -1;
        if (str2 == null || str2.length() == 0) {
            this._propName = "";
        } else {
            this._propName = InternCache.instance.intern(str2);
        }
        this._type = javaType2;
        this._wrapperName = propertyName2;
        this._isRequired = z2;
        this._contextAnnotations = annotations2;
        this._viewMatcher = null;
        this._nullProvider = null;
        this._valueTypeDeserializer = typeDeserializer2 != null ? typeDeserializer2.forProperty(this) : typeDeserializer2;
        this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        this._propertyIndex = -1;
        this._propName = settableBeanProperty2._propName;
        this._type = settableBeanProperty2._type;
        this._wrapperName = settableBeanProperty2._wrapperName;
        this._isRequired = settableBeanProperty2._isRequired;
        this._contextAnnotations = settableBeanProperty2._contextAnnotations;
        this._valueDeserializer = settableBeanProperty2._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty2._valueTypeDeserializer;
        this._nullProvider = settableBeanProperty2._nullProvider;
        this._managedReferenceName = settableBeanProperty2._managedReferenceName;
        this._propertyIndex = settableBeanProperty2._propertyIndex;
        this._viewMatcher = settableBeanProperty2._viewMatcher;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty, JsonDeserializer<?> jsonDeserializer) {
        NullProvider nullProvider;
        NullProvider nullProvider2;
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        this._propertyIndex = -1;
        this._propName = settableBeanProperty2._propName;
        this._type = settableBeanProperty2._type;
        this._wrapperName = settableBeanProperty2._wrapperName;
        this._isRequired = settableBeanProperty2._isRequired;
        this._contextAnnotations = settableBeanProperty2._contextAnnotations;
        this._valueTypeDeserializer = settableBeanProperty2._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty2._managedReferenceName;
        this._propertyIndex = settableBeanProperty2._propertyIndex;
        if (jsonDeserializer2 == null) {
            this._nullProvider = null;
            this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
        } else {
            Object nullValue = jsonDeserializer2.getNullValue();
            if (nullValue == null) {
                nullProvider2 = null;
            } else {
                nullProvider2 = nullProvider;
                new NullProvider(this._type, nullValue);
            }
            this._nullProvider = nullProvider2;
            this._valueDeserializer = jsonDeserializer2;
        }
        this._viewMatcher = settableBeanProperty2._viewMatcher;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty, String str) {
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        this._propertyIndex = -1;
        this._propName = str;
        this._type = settableBeanProperty2._type;
        this._wrapperName = settableBeanProperty2._wrapperName;
        this._isRequired = settableBeanProperty2._isRequired;
        this._contextAnnotations = settableBeanProperty2._contextAnnotations;
        this._valueDeserializer = settableBeanProperty2._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty2._valueTypeDeserializer;
        this._nullProvider = settableBeanProperty2._nullProvider;
        this._managedReferenceName = settableBeanProperty2._managedReferenceName;
        this._propertyIndex = settableBeanProperty2._propertyIndex;
        this._viewMatcher = settableBeanProperty2._viewMatcher;
    }

    public void setManagedReferenceName(String str) {
        String str2 = str;
        this._managedReferenceName = str2;
    }

    public void setViews(Class<?>[] clsArr) {
        Class<?>[] clsArr2 = clsArr;
        if (clsArr2 == null) {
            this._viewMatcher = null;
            return;
        }
        this._viewMatcher = ViewMatcher.construct(clsArr2);
    }

    public void assignIndex(int i) {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        if (this._propertyIndex != -1) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Property '").append(getName()).append("' already had index (").append(this._propertyIndex).append("), trying to assign ").append(i2).toString());
            throw th2;
        }
        this._propertyIndex = i2;
    }

    public final String getName() {
        return this._propName;
    }

    public boolean isRequired() {
        return this._isRequired;
    }

    public JavaType getType() {
        return this._type;
    }

    public PropertyName getWrapperName() {
        return this._wrapperName;
    }

    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return this._contextAnnotations.get(cls);
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor) throws JsonMappingException {
        JsonObjectFormatVisitor jsonObjectFormatVisitor2 = jsonObjectFormatVisitor;
        if (isRequired()) {
            jsonObjectFormatVisitor2.property((BeanProperty) this);
        } else {
            jsonObjectFormatVisitor2.optionalProperty((BeanProperty) this);
        }
    }

    /* access modifiers changed from: protected */
    public final Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    public boolean hasValueDeserializer() {
        return (this._valueDeserializer == null || this._valueDeserializer == MISSING_VALUE_DESERIALIZER) ? false : true;
    }

    public boolean hasValueTypeDeserializer() {
        return this._valueTypeDeserializer != null;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer == MISSING_VALUE_DESERIALIZER) {
            return null;
        }
        return jsonDeserializer;
    }

    public TypeDeserializer getValueTypeDeserializer() {
        return this._valueTypeDeserializer;
    }

    public boolean visibleInView(Class<?> cls) {
        return this._viewMatcher == null || this._viewMatcher.isVisibleForView(cls);
    }

    public boolean hasViews() {
        return this._viewMatcher != null;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    public int getCreatorIndex() {
        return -1;
    }

    public Object getInjectableValueId() {
        return null;
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (jsonParser2.getCurrentToken() == JsonToken.VALUE_NULL) {
            return this._nullProvider == null ? null : this._nullProvider.nullValue(deserializationContext2);
        } else if (this._valueTypeDeserializer != null) {
            return this._valueDeserializer.deserializeWithType(jsonParser2, deserializationContext2, this._valueTypeDeserializer);
        } else {
            return this._valueDeserializer.deserialize(jsonParser2, deserializationContext2);
        }
    }

    /* access modifiers changed from: protected */
    public void _throwAsIOE(Exception exc, Object obj) throws IOException {
        StringBuilder sb;
        Throwable th;
        Exception exc2 = exc;
        Object obj2 = obj;
        if (exc2 instanceof IllegalArgumentException) {
            String name = obj2 == null ? "[NULL]" : obj2.getClass().getName();
            new StringBuilder("Problem deserializing property '");
            StringBuilder append = sb.append(getName());
            StringBuilder append2 = append.append("' (expected type: ").append(getType());
            StringBuilder append3 = append.append("; actual type: ").append(name).append(")");
            String message = exc2.getMessage();
            if (message != null) {
                StringBuilder append4 = append.append(", problem: ").append(message);
            } else {
                StringBuilder append5 = append.append(" (no error message provided)");
            }
            Throwable th2 = th;
            new JsonMappingException(append.toString(), (JsonLocation) null, exc2);
            throw th2;
        }
        IOException _throwAsIOE = _throwAsIOE(exc2);
    }

    /* access modifiers changed from: protected */
    public IOException _throwAsIOE(Exception exc) throws IOException {
        Throwable th;
        Exception exc2 = exc;
        if (exc2 instanceof IOException) {
            throw ((IOException) exc2);
        } else if (exc2 instanceof RuntimeException) {
            throw ((RuntimeException) exc2);
        } else {
            Throwable th2 = exc2;
            while (true) {
                Throwable th3 = th2;
                if (th3.getCause() != null) {
                    th2 = th3.getCause();
                } else {
                    Throwable th4 = th;
                    new JsonMappingException(th3.getMessage(), (JsonLocation) null, th3);
                    throw th4;
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[property '").append(getName()).append("']").toString();
    }
}
