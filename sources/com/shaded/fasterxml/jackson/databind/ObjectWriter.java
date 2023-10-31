package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.FormatSchema;
import com.shaded.fasterxml.jackson.core.JsonEncoding;
import com.shaded.fasterxml.jackson.core.JsonFactory;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.PrettyPrinter;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.Versioned;
import com.shaded.fasterxml.jackson.core.p005io.SegmentedStringWriter;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.shaded.fasterxml.jackson.core.util.Instantiatable;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.shaded.fasterxml.jackson.databind.cfg.PackageVersion;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.FilterProvider;
import com.shaded.fasterxml.jackson.databind.ser.SerializerFactory;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class ObjectWriter implements Versioned, Serializable {
    protected static final PrettyPrinter NULL_PRETTY_PRINTER;
    private static final long serialVersionUID = -7024829992408267532L;
    protected final SerializationConfig _config;
    protected final JsonFactory _jsonFactory;
    protected final PrettyPrinter _prettyPrinter;
    protected final JsonSerializer<Object> _rootSerializer;
    protected final JavaType _rootType;
    protected final FormatSchema _schema;
    protected final SerializerFactory _serializerFactory;
    protected final DefaultSerializerProvider _serializerProvider;

    static {
        PrettyPrinter prettyPrinter;
        new MinimalPrettyPrinter();
        NULL_PRETTY_PRINTER = prettyPrinter;
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter) {
        ObjectMapper objectMapper2 = objectMapper;
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        PrettyPrinter prettyPrinter2 = prettyPrinter;
        this._config = serializationConfig2;
        this._serializerProvider = objectMapper2._serializerProvider;
        this._serializerFactory = objectMapper2._serializerFactory;
        this._jsonFactory = objectMapper2._jsonFactory;
        javaType2 = javaType2 != null ? javaType2.withStaticTyping() : javaType2;
        this._rootType = javaType2;
        this._prettyPrinter = prettyPrinter2;
        this._schema = null;
        this._rootSerializer = _prefetchRootSerializer(serializationConfig2, javaType2);
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig) {
        ObjectMapper objectMapper2 = objectMapper;
        this._config = serializationConfig;
        this._serializerProvider = objectMapper2._serializerProvider;
        this._serializerFactory = objectMapper2._serializerFactory;
        this._jsonFactory = objectMapper2._jsonFactory;
        this._rootType = null;
        this._rootSerializer = null;
        this._prettyPrinter = null;
        this._schema = null;
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, FormatSchema formatSchema) {
        ObjectMapper objectMapper2 = objectMapper;
        this._config = serializationConfig;
        this._serializerProvider = objectMapper2._serializerProvider;
        this._serializerFactory = objectMapper2._serializerFactory;
        this._jsonFactory = objectMapper2._jsonFactory;
        this._rootType = null;
        this._rootSerializer = null;
        this._prettyPrinter = null;
        this._schema = formatSchema;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig, JavaType javaType, JsonSerializer<Object> jsonSerializer, PrettyPrinter prettyPrinter, FormatSchema formatSchema) {
        ObjectWriter objectWriter2 = objectWriter;
        this._config = serializationConfig;
        this._serializerProvider = objectWriter2._serializerProvider;
        this._serializerFactory = objectWriter2._serializerFactory;
        this._jsonFactory = objectWriter2._jsonFactory;
        this._rootType = javaType;
        this._rootSerializer = jsonSerializer;
        this._prettyPrinter = prettyPrinter;
        this._schema = formatSchema;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig) {
        ObjectWriter objectWriter2 = objectWriter;
        this._config = serializationConfig;
        this._serializerProvider = objectWriter2._serializerProvider;
        this._serializerFactory = objectWriter2._serializerFactory;
        this._jsonFactory = objectWriter2._jsonFactory;
        this._schema = objectWriter2._schema;
        this._rootType = objectWriter2._rootType;
        this._rootSerializer = objectWriter2._rootSerializer;
        this._prettyPrinter = objectWriter2._prettyPrinter;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectWriter with(SerializationFeature serializationFeature) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig with = this._config.with(serializationFeature);
        if (with == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, with);
        }
        return objectWriter2;
    }

    public ObjectWriter with(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig with = this._config.with(serializationFeature, serializationFeatureArr);
        if (with == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, with);
        }
        return objectWriter2;
    }

    public ObjectWriter withFeatures(SerializationFeature... serializationFeatureArr) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig withFeatures = this._config.withFeatures(serializationFeatureArr);
        if (withFeatures == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, withFeatures);
        }
        return objectWriter2;
    }

    public ObjectWriter without(SerializationFeature serializationFeature) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig without = this._config.without(serializationFeature);
        if (without == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, without);
        }
        return objectWriter2;
    }

    public ObjectWriter without(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig without = this._config.without(serializationFeature, serializationFeatureArr);
        if (without == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, without);
        }
        return objectWriter2;
    }

    public ObjectWriter withoutFeatures(SerializationFeature... serializationFeatureArr) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig withoutFeatures = this._config.withoutFeatures(serializationFeatureArr);
        if (withoutFeatures == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, withoutFeatures);
        }
        return objectWriter2;
    }

    public ObjectWriter with(DateFormat dateFormat) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig with = this._config.with(dateFormat);
        if (with == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, with);
        }
        return objectWriter2;
    }

    public ObjectWriter withDefaultPrettyPrinter() {
        PrettyPrinter prettyPrinter;
        new DefaultPrettyPrinter();
        return with(prettyPrinter);
    }

    public ObjectWriter with(FilterProvider filterProvider) {
        ObjectWriter objectWriter;
        FilterProvider filterProvider2 = filterProvider;
        if (filterProvider2 == this._config.getFilterProvider()) {
            return this;
        }
        new ObjectWriter(this, this._config.withFilters(filterProvider2));
        return objectWriter;
    }

    public ObjectWriter with(PrettyPrinter prettyPrinter) {
        ObjectWriter objectWriter;
        PrettyPrinter prettyPrinter2 = prettyPrinter;
        if (prettyPrinter2 == this._prettyPrinter) {
            return this;
        }
        if (prettyPrinter2 == null) {
            prettyPrinter2 = NULL_PRETTY_PRINTER;
        }
        new ObjectWriter(this, this._config, this._rootType, this._rootSerializer, prettyPrinter2, this._schema);
        return objectWriter;
    }

    public ObjectWriter withRootName(String str) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig withRootName = this._config.withRootName(str);
        if (withRootName == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, withRootName);
        }
        return objectWriter2;
    }

    public ObjectWriter withSchema(FormatSchema formatSchema) {
        ObjectWriter objectWriter;
        FormatSchema formatSchema2 = formatSchema;
        if (this._schema == formatSchema2) {
            return this;
        }
        _verifySchemaType(formatSchema2);
        new ObjectWriter(this, this._config, this._rootType, this._rootSerializer, this._prettyPrinter, formatSchema2);
        return objectWriter;
    }

    public ObjectWriter withType(JavaType javaType) {
        ObjectWriter objectWriter;
        JavaType withStaticTyping = javaType.withStaticTyping();
        new ObjectWriter(this, this._config, withStaticTyping, _prefetchRootSerializer(this._config, withStaticTyping), this._prettyPrinter, this._schema);
        return objectWriter;
    }

    public ObjectWriter withType(Class<?> cls) {
        return withType(this._config.constructType(cls));
    }

    public ObjectWriter withType(TypeReference<?> typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public ObjectWriter withView(Class<?> cls) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig withView = this._config.withView(cls);
        if (withView == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, withView);
        }
        return objectWriter2;
    }

    public ObjectWriter with(Locale locale) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig with = this._config.with(locale);
        if (with == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, with);
        }
        return objectWriter2;
    }

    public ObjectWriter with(TimeZone timeZone) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig with = this._config.with(timeZone);
        if (with == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, with);
        }
        return objectWriter2;
    }

    public ObjectWriter with(Base64Variant base64Variant) {
        ObjectWriter objectWriter;
        ObjectWriter objectWriter2;
        SerializationConfig with = this._config.with(base64Variant);
        if (with == this._config) {
            objectWriter2 = this;
        } else {
            objectWriter2 = objectWriter;
            new ObjectWriter(this, with);
        }
        return objectWriter2;
    }

    public boolean isEnabled(SerializationFeature serializationFeature) {
        return this._config.isEnabled(serializationFeature);
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public SerializationConfig getConfig() {
        return this._config;
    }

    @Deprecated
    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public boolean hasPrefetchedSerializer() {
        return this._rootSerializer != null;
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        _configureJsonGenerator(jsonGenerator2);
        if (!this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj2 instanceof Closeable)) {
            if (this._rootType == null) {
                _serializerProvider(this._config).serializeValue(jsonGenerator2, obj2);
            } else {
                _serializerProvider(this._config).serializeValue(jsonGenerator2, obj2, this._rootType, this._rootSerializer);
            }
            if (this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator2.flush();
                return;
            }
            return;
        }
        _writeCloseableValue(jsonGenerator2, obj2, this._config);
    }

    public void writeValue(File file, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(writer), obj);
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        SegmentedStringWriter segmentedStringWriter;
        Object obj2 = obj;
        new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        SegmentedStringWriter segmentedStringWriter2 = segmentedStringWriter;
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator((Writer) segmentedStringWriter2), obj2);
            return segmentedStringWriter2.getAndClear();
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public byte[] writeValueAsBytes(Object obj) throws JsonProcessingException {
        ByteArrayBuilder byteArrayBuilder;
        Object obj2 = obj;
        new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        ByteArrayBuilder byteArrayBuilder2 = byteArrayBuilder;
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator((OutputStream) byteArrayBuilder2, JsonEncoding.UTF8), obj2);
            byte[] byteArray = byteArrayBuilder2.toByteArray();
            byteArrayBuilder2.release();
            return byteArray;
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        Throwable th;
        JavaType javaType2 = javaType;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        if (javaType2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("type must be provided");
            throw th2;
        }
        _serializerProvider(this._config).acceptJsonFormatVisitor(javaType2, jsonFormatVisitorWrapper2);
    }

    public boolean canSerialize(Class<?> cls) {
        return _serializerProvider(this._config).hasSerializerFor(cls);
    }

    /* access modifiers changed from: protected */
    public DefaultSerializerProvider _serializerProvider(SerializationConfig serializationConfig) {
        return this._serializerProvider.createInstance(serializationConfig, this._serializerFactory);
    }

    /* access modifiers changed from: protected */
    public void _verifySchemaType(FormatSchema formatSchema) {
        Throwable th;
        StringBuilder sb;
        FormatSchema formatSchema2 = formatSchema;
        if (formatSchema2 != null && !this._jsonFactory.canUseSchema(formatSchema2)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can not use FormatSchema of type ").append(formatSchema2.getClass().getName()).append(" for format ").append(this._jsonFactory.getFormatName()).toString());
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        _configureJsonGenerator(jsonGenerator2);
        if (!this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj2 instanceof Closeable)) {
            try {
                if (this._rootType == null) {
                    _serializerProvider(this._config).serializeValue(jsonGenerator2, obj2);
                } else {
                    _serializerProvider(this._config).serializeValue(jsonGenerator2, obj2, this._rootType, this._rootSerializer);
                }
                jsonGenerator2.close();
                if (1 == 0) {
                    try {
                        jsonGenerator2.close();
                    } catch (IOException e) {
                        IOException iOException = e;
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (0 == 0) {
                    try {
                        jsonGenerator2.close();
                    } catch (IOException e2) {
                        IOException iOException2 = e2;
                    }
                }
                throw th2;
            }
        } else {
            _writeCloseable(jsonGenerator2, obj2, this._config);
        }
    }

    /* JADX INFO: finally extract failed */
    private final void _writeCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        SerializationConfig serializationConfig2 = serializationConfig;
        Closeable closeable = (Closeable) obj2;
        try {
            if (this._rootType == null) {
                _serializerProvider(serializationConfig2).serializeValue(jsonGenerator2, obj2);
            } else {
                _serializerProvider(serializationConfig2).serializeValue(jsonGenerator2, obj2, this._rootType, this._rootSerializer);
            }
            jsonGenerator2.close();
            closeable.close();
            if (0 != 0) {
                JsonGenerator jsonGenerator3 = null;
                try {
                    jsonGenerator3.close();
                } catch (IOException e) {
                    IOException iOException = e;
                }
            }
            if (0 != 0) {
                Closeable closeable2 = null;
                try {
                    closeable2.close();
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (jsonGenerator2 != null) {
                try {
                    jsonGenerator2.close();
                } catch (IOException e3) {
                    IOException iOException3 = e3;
                }
            }
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e4) {
                    IOException iOException4 = e4;
                }
            }
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        SerializationConfig serializationConfig2 = serializationConfig;
        Closeable closeable = (Closeable) obj2;
        try {
            if (this._rootType == null) {
                _serializerProvider(serializationConfig2).serializeValue(jsonGenerator2, obj2);
            } else {
                _serializerProvider(serializationConfig2).serializeValue(jsonGenerator2, obj2, this._rootType, this._rootSerializer);
            }
            if (this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator2.flush();
            }
            closeable.close();
            if (0 != 0) {
                Closeable closeable2 = null;
                try {
                    closeable2.close();
                } catch (IOException e) {
                    IOException iOException = e;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                }
            }
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _prefetchRootSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        if (javaType2 == null || !this._config.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH)) {
            return null;
        }
        try {
            return _serializerProvider(serializationConfig2).findTypedValueSerializer(javaType2, true, (BeanProperty) null);
        } catch (JsonProcessingException e) {
            JsonProcessingException jsonProcessingException = e;
            return null;
        }
    }

    private void _configureJsonGenerator(JsonGenerator jsonGenerator) {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (this._prettyPrinter != null) {
            PrettyPrinter prettyPrinter = this._prettyPrinter;
            if (prettyPrinter == NULL_PRETTY_PRINTER) {
                JsonGenerator prettyPrinter2 = jsonGenerator2.setPrettyPrinter((PrettyPrinter) null);
            } else {
                if (prettyPrinter instanceof Instantiatable) {
                    prettyPrinter = (PrettyPrinter) ((Instantiatable) prettyPrinter).createInstance();
                }
                JsonGenerator prettyPrinter3 = jsonGenerator2.setPrettyPrinter(prettyPrinter);
            }
        } else if (this._config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            JsonGenerator useDefaultPrettyPrinter = jsonGenerator2.useDefaultPrettyPrinter();
        }
        if (this._schema != null) {
            jsonGenerator2.setSchema(this._schema);
        }
    }
}
