package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.shaded.fasterxml.jackson.databind.deser.DeserializerCache;
import com.shaded.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.shaded.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.shaded.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.shaded.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.shaded.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.LinkedNode;
import com.shaded.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final int MAX_ERROR_STR_LEN = 500;
    private static final long serialVersionUID = -7727373309391091315L;
    protected transient ArrayBuilders _arrayBuilders;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class<?> _view;

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator);

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected DeserializationContext(DeserializerFactory deserializerFactory) {
        this(deserializerFactory, (DeserializerCache) null);
    }

    protected DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        DeserializerCache deserializerCache2;
        DeserializerCache deserializerCache3;
        Throwable th;
        DeserializerFactory deserializerFactory2 = deserializerFactory;
        DeserializerCache deserializerCache4 = deserializerCache;
        if (deserializerFactory2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null DeserializerFactory");
            throw th2;
        }
        this._factory = deserializerFactory2;
        if (deserializerCache4 == null) {
            deserializerCache2 = deserializerCache3;
            new DeserializerCache();
        } else {
            deserializerCache2 = deserializerCache4;
        }
        this._cache = deserializerCache2;
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory) {
        DeserializationContext deserializationContext2 = deserializationContext;
        this._cache = deserializationContext2._cache;
        this._factory = deserializerFactory;
        this._config = deserializationContext2._config;
        this._featureFlags = deserializationContext2._featureFlags;
        this._view = deserializationContext2._view;
        this._parser = deserializationContext2._parser;
        this._injectableValues = deserializationContext2._injectableValues;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        DeserializationContext deserializationContext2 = deserializationContext;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        this._cache = deserializationContext2._cache;
        this._factory = deserializationContext2._factory;
        this._config = deserializationConfig2;
        this._featureFlags = deserializationConfig2.getDeserializationFeatures();
        this._view = deserializationConfig2.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public DeserializerFactory getFactory() {
        return this._factory;
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (this._featureFlags & deserializationFeature.getMask()) != 0;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        Throwable th;
        StringBuilder sb;
        Object obj3 = obj;
        BeanProperty beanProperty2 = beanProperty;
        Object obj4 = obj2;
        if (this._injectableValues != null) {
            return this._injectableValues.findInjectableValue(obj3, this, beanProperty2, obj4);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("No 'injectableValues' configured, can not inject value with id [").append(obj3).append("]").toString());
        throw th2;
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public boolean hasValueDeserializerFor(JavaType javaType) {
        return this._cache.hasValueDeserializerFor(this, this._factory, javaType);
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<?> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (findValueDeserializer != null && (findValueDeserializer instanceof ContextualDeserializer)) {
            findValueDeserializer = ((ContextualDeserializer) findValueDeserializer).createContextual(this, beanProperty2);
        }
        return findValueDeserializer;
    }

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer;
        JavaType javaType2 = javaType;
        JsonDeserializer<?> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType2);
        if (findValueDeserializer == null) {
            return null;
        }
        if (findValueDeserializer instanceof ContextualDeserializer) {
            findValueDeserializer = ((ContextualDeserializer) findValueDeserializer).createContextual(this, (BeanProperty) null);
        }
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType2);
        if (findTypeDeserializer == null) {
            return findValueDeserializer;
        }
        new TypeWrappedDeserializer(findTypeDeserializer.forProperty((BeanProperty) null), findValueDeserializer);
        return jsonDeserializer;
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        BeanProperty beanProperty2 = beanProperty;
        KeyDeserializer findKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        if (findKeyDeserializer instanceof ContextualKeyDeserializer) {
            findKeyDeserializer = ((ContextualKeyDeserializer) findKeyDeserializer).createContextual(this, beanProperty2);
        }
        return findKeyDeserializer;
    }

    public final JavaType constructType(Class<?> cls) {
        return this._config.constructType(cls);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        return ClassUtil.findClass(str);
    }

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer;
        ObjectBuffer objectBuffer2 = this._objectBuffer;
        if (objectBuffer2 == null) {
            new ObjectBuffer();
            objectBuffer2 = objectBuffer;
        } else {
            this._objectBuffer = null;
        }
        return objectBuffer2;
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        ObjectBuffer objectBuffer2 = objectBuffer;
        if (this._objectBuffer == null || objectBuffer2.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer2;
        }
    }

    public final ArrayBuilders getArrayBuilders() {
        ArrayBuilders arrayBuilders;
        if (this._arrayBuilders == null) {
            new ArrayBuilders();
            this._arrayBuilders = arrayBuilders;
        }
        return this._arrayBuilders;
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        try {
            return getDateFormat().parse(str2);
        } catch (ParseException e) {
            ParseException parseException = e;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Failed to parse Date value '").append(str2).append("': ").append(parseException.getMessage()).toString());
            throw th2;
        }
    }

    public Calendar constructCalendar(Date date) {
        Calendar instance = Calendar.getInstance(getTimeZone());
        instance.setTime(date);
        return instance;
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        Object obj2 = obj;
        String str2 = str;
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        if (problemHandlers != null) {
            while (problemHandlers != null) {
                if (problemHandlers.value().handleUnknownProperty(this, jsonParser2, jsonDeserializer2, obj2, str2)) {
                    return true;
                }
                problemHandlers = problemHandlers.next();
            }
        }
        return false;
    }

    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Object obj2 = obj;
        String str2 = str;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(this._parser, obj2, str2, jsonDeserializer2 == null ? null : jsonDeserializer2.getKnownPropertyNames());
        }
    }

    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        StringBuilder sb;
        String _calcName = _calcName(cls);
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return JsonMappingException.from(jsonParser, sb.append("Can not deserialize instance of ").append(_calcName).append(" out of ").append(jsonToken).append(" token").toString());
    }

    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        StringBuilder sb;
        Throwable th2 = th;
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return JsonMappingException.from(jsonParser, sb.append("Can not construct instance of ").append(cls.getName()).append(", problem: ").append(th2.getMessage()).toString(), th2);
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        StringBuilder sb;
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return JsonMappingException.from(jsonParser, sb.append("Can not construct instance of ").append(cls.getName()).append(", problem: ").append(str).toString());
    }

    @Deprecated
    public JsonMappingException weirdStringException(Class<?> cls, String str) {
        return weirdStringException((String) null, cls, str);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        StringBuilder sb;
        Class<?> cls2 = cls;
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return InvalidFormatException.from(jsonParser, sb.append("Can not construct instance of ").append(cls2.getName()).append(" from String value '").append(_valueDesc()).append("': ").append(str2).toString(), str, cls2);
    }

    @Deprecated
    public JsonMappingException weirdNumberException(Class<?> cls, String str) {
        return weirdStringException((String) null, cls, str);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        StringBuilder sb;
        Number number2 = number;
        Class<?> cls2 = cls;
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return InvalidFormatException.from(jsonParser, sb.append("Can not construct instance of ").append(cls2.getName()).append(" from number value (").append(_valueDesc()).append("): ").append(str).toString(), (Object) null, cls2);
    }

    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        StringBuilder sb;
        Class<?> cls2 = cls;
        String str3 = str;
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return InvalidFormatException.from(jsonParser, sb.append("Can not construct Map key of type ").append(cls2.getName()).append(" from String \"").append(_desc(str3)).append("\": ").append(str2).toString(), str3, cls2);
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        new StringBuilder();
        return JsonMappingException.from(jsonParser2, sb.append("Unexpected token (").append(jsonParser2.getCurrentToken()).append("), expected ").append(jsonToken).append(": ").append(str).toString());
    }

    public JsonMappingException unknownTypeException(JavaType javaType, String str) {
        StringBuilder sb;
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return JsonMappingException.from(jsonParser, sb.append("Could not resolve type id '").append(str).append("' into a subtype of ").append(javaType).toString());
    }

    public JsonMappingException endOfInputException(Class<?> cls) {
        StringBuilder sb;
        JsonParser jsonParser = this._parser;
        new StringBuilder();
        return JsonMappingException.from(jsonParser, sb.append("Unexpected end-of-input when trying to deserialize a ").append(cls.getName()).toString());
    }

    /* access modifiers changed from: protected */
    public DateFormat getDateFormat() {
        if (this._dateFormat != null) {
            return this._dateFormat;
        }
        DateFormat dateFormat = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat;
        return dateFormat;
    }

    /* access modifiers changed from: protected */
    public String determineClassName(Object obj) {
        return ClassUtil.getClassDescription(obj);
    }

    /* access modifiers changed from: protected */
    public String _calcName(Class<?> cls) {
        StringBuilder sb;
        Class<?> cls2 = cls;
        if (!cls2.isArray()) {
            return cls2.getName();
        }
        new StringBuilder();
        return sb.append(_calcName(cls2.getComponentType())).append("[]").toString();
    }

    /* access modifiers changed from: protected */
    public String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception e) {
            Exception exc = e;
            return "[N/A]";
        }
    }

    /* access modifiers changed from: protected */
    public String _desc(String str) {
        StringBuilder sb;
        String str2 = str;
        if (str2.length() > 500) {
            new StringBuilder();
            str2 = sb.append(str2.substring(0, 500)).append("]...[").append(str2.substring(str2.length() - 500)).toString();
        }
        return str2;
    }
}
