package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.FormatSchema;
import com.shaded.fasterxml.jackson.core.JsonFactory;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.TreeNode;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.Versioned;
import com.shaded.fasterxml.jackson.core.type.ResolvedType;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.cfg.PackageVersion;
import com.shaded.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.shaded.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.node.NullNode;
import com.shaded.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.shaded.fasterxml.jackson.databind.type.SimpleType;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader extends ObjectCodec implements Versioned, Serializable {
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    private static final long serialVersionUID = -4251443320039569153L;
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final DataFormatReaders _dataFormatReaders;
    protected final InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final JsonDeserializer<Object> _rootDeserializer;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final RootNameLookup _rootNames;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig) {
        this(objectMapper, deserializationConfig, (JavaType) null, (Object) null, (FormatSchema) null, (InjectableValues) null);
    }

    protected ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        Throwable th;
        ObjectMapper objectMapper2 = objectMapper;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        Object obj2 = obj;
        FormatSchema formatSchema2 = formatSchema;
        InjectableValues injectableValues2 = injectableValues;
        this._config = deserializationConfig2;
        this._context = objectMapper2._deserializationContext;
        this._rootDeserializers = objectMapper2._rootDeserializers;
        this._jsonFactory = objectMapper2._jsonFactory;
        this._rootNames = objectMapper2._rootNames;
        this._valueType = javaType2;
        this._valueToUpdate = obj2;
        if (obj2 == null || !javaType2.isArrayType()) {
            this._schema = formatSchema2;
            this._injectableValues = injectableValues2;
            this._unwrapRoot = deserializationConfig2.useRootWrapping();
            this._rootDeserializer = _prefetchRootDeserializer(deserializationConfig2, javaType2);
            this._dataFormatReaders = null;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can not update an array value");
        throw th2;
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, Object obj, FormatSchema formatSchema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        Throwable th;
        ObjectReader objectReader2 = objectReader;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        Object obj2 = obj;
        FormatSchema formatSchema2 = formatSchema;
        InjectableValues injectableValues2 = injectableValues;
        DataFormatReaders dataFormatReaders2 = dataFormatReaders;
        this._config = deserializationConfig2;
        this._context = objectReader2._context;
        this._rootDeserializers = objectReader2._rootDeserializers;
        this._jsonFactory = objectReader2._jsonFactory;
        this._rootNames = objectReader2._rootNames;
        this._valueType = javaType2;
        this._rootDeserializer = jsonDeserializer;
        this._valueToUpdate = obj2;
        if (obj2 == null || !javaType2.isArrayType()) {
            this._schema = formatSchema2;
            this._injectableValues = injectableValues2;
            this._unwrapRoot = deserializationConfig2.useRootWrapping();
            this._dataFormatReaders = dataFormatReaders2;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Can not update an array value");
        throw th2;
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig) {
        ObjectReader objectReader2 = objectReader;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        this._config = deserializationConfig2;
        this._context = objectReader2._context;
        this._rootDeserializers = objectReader2._rootDeserializers;
        this._jsonFactory = objectReader2._jsonFactory;
        this._rootNames = objectReader2._rootNames;
        this._valueType = objectReader2._valueType;
        this._rootDeserializer = objectReader2._rootDeserializer;
        this._valueToUpdate = objectReader2._valueToUpdate;
        this._schema = objectReader2._schema;
        this._injectableValues = objectReader2._injectableValues;
        this._unwrapRoot = deserializationConfig2.useRootWrapping();
        this._dataFormatReaders = objectReader2._dataFormatReaders;
    }

    protected ObjectReader(ObjectReader objectReader, JsonFactory jsonFactory) {
        ObjectReader objectReader2 = objectReader;
        this._config = objectReader2._config;
        this._context = objectReader2._context;
        this._rootDeserializers = objectReader2._rootDeserializers;
        this._jsonFactory = jsonFactory;
        this._rootNames = objectReader2._rootNames;
        this._valueType = objectReader2._valueType;
        this._rootDeserializer = objectReader2._rootDeserializer;
        this._valueToUpdate = objectReader2._valueToUpdate;
        this._schema = objectReader2._schema;
        this._injectableValues = objectReader2._injectableValues;
        this._unwrapRoot = objectReader2._unwrapRoot;
        this._dataFormatReaders = objectReader2._dataFormatReaders;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectReader with(DeserializationConfig deserializationConfig) {
        return _with(deserializationConfig);
    }

    public ObjectReader with(DeserializationFeature deserializationFeature) {
        return _with(this._config.with(deserializationFeature));
    }

    public ObjectReader with(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.with(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectReader withFeatures(DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.withFeatures(deserializationFeatureArr));
    }

    public ObjectReader without(DeserializationFeature deserializationFeature) {
        return _with(this._config.without(deserializationFeature));
    }

    public ObjectReader without(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.without(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectReader withoutFeatures(DeserializationFeature... deserializationFeatureArr) {
        return _with(this._config.withoutFeatures(deserializationFeatureArr));
    }

    public ObjectReader with(InjectableValues injectableValues) {
        ObjectReader objectReader;
        InjectableValues injectableValues2 = injectableValues;
        if (this._injectableValues == injectableValues2) {
            return this;
        }
        new ObjectReader(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, injectableValues2, this._dataFormatReaders);
        return objectReader;
    }

    public ObjectReader with(JsonNodeFactory jsonNodeFactory) {
        return _with(this._config.with(jsonNodeFactory));
    }

    public ObjectReader with(JsonFactory jsonFactory) {
        ObjectReader objectReader;
        JsonFactory jsonFactory2 = jsonFactory;
        if (jsonFactory2 == this._jsonFactory) {
            return this;
        }
        new ObjectReader(this, jsonFactory2);
        ObjectReader objectReader2 = objectReader;
        if (jsonFactory2.getCodec() == null) {
            JsonFactory codec = jsonFactory2.setCodec(objectReader2);
        }
        return objectReader2;
    }

    public ObjectReader withRootName(String str) {
        return _with(this._config.withRootName(str));
    }

    public ObjectReader with(FormatSchema formatSchema) {
        ObjectReader objectReader;
        FormatSchema formatSchema2 = formatSchema;
        if (this._schema == formatSchema2) {
            return this;
        }
        _verifySchemaType(formatSchema2);
        new ObjectReader(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, formatSchema2, this._injectableValues, this._dataFormatReaders);
        return objectReader;
    }

    public ObjectReader withType(JavaType javaType) {
        ObjectReader objectReader;
        JavaType javaType2 = javaType;
        if (javaType2 == null || !javaType2.equals(this._valueType)) {
            JsonDeserializer<Object> _prefetchRootDeserializer = _prefetchRootDeserializer(this._config, javaType2);
            DataFormatReaders dataFormatReaders = this._dataFormatReaders;
            if (dataFormatReaders != null) {
                dataFormatReaders = dataFormatReaders.withType(javaType2);
            }
            new ObjectReader(this, this._config, javaType2, _prefetchRootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, dataFormatReaders);
            return objectReader;
        }
        return this;
    }

    public ObjectReader withType(Class<?> cls) {
        return withType(this._config.constructType(cls));
    }

    public ObjectReader withType(Type type) {
        return withType(this._config.getTypeFactory().constructType(type));
    }

    public ObjectReader withType(TypeReference<?> typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public ObjectReader withValueToUpdate(Object obj) {
        JavaType javaType;
        ObjectReader objectReader;
        Throwable th;
        Object obj2 = obj;
        if (obj2 == this._valueToUpdate) {
            return this;
        } else if (obj2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("cat not update null value");
            throw th2;
        } else {
            if (this._valueType == null) {
                javaType = this._config.constructType(obj2.getClass());
            } else {
                javaType = this._valueType;
            }
            new ObjectReader(this, this._config, javaType, this._rootDeserializer, obj2, this._schema, this._injectableValues, this._dataFormatReaders);
            return objectReader;
        }
    }

    public ObjectReader withView(Class<?> cls) {
        return _with(this._config.withView(cls));
    }

    public ObjectReader with(Locale locale) {
        return _with(this._config.with(locale));
    }

    public ObjectReader with(TimeZone timeZone) {
        return _with(this._config.with(timeZone));
    }

    public ObjectReader withHandler(DeserializationProblemHandler deserializationProblemHandler) {
        return _with(this._config.withHandler(deserializationProblemHandler));
    }

    public ObjectReader with(Base64Variant base64Variant) {
        return _with(this._config.with(base64Variant));
    }

    public ObjectReader withFormatDetection(ObjectReader... objectReaderArr) {
        DataFormatReaders dataFormatReaders;
        new DataFormatReaders(objectReaderArr);
        return withFormatDetection(dataFormatReaders);
    }

    public ObjectReader withFormatDetection(DataFormatReaders dataFormatReaders) {
        ObjectReader objectReader;
        new ObjectReader(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, dataFormatReaders);
        return objectReader;
    }

    public boolean isEnabled(DeserializationFeature deserializationFeature) {
        return this._config.isEnabled(deserializationFeature);
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    @Deprecated
    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public <T> T readValue(JsonParser jsonParser) throws IOException, JsonProcessingException {
        return _bind(jsonParser, this._valueToUpdate);
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return withType((Class<?>) cls).readValue(jsonParser);
    }

    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return withType(typeReference).readValue(jsonParser);
    }

    public <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonProcessingException {
        return withType((JavaType) resolvedType).readValue(jsonParser);
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        return withType(javaType).readValue(jsonParser);
    }

    public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException, JsonProcessingException {
        return _bindAsTree(jsonParser);
    }

    public <T> Iterator<T> readValues(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return withType((Class<?>) cls).readValues(jsonParser);
    }

    public <T> Iterator<T> readValues(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return withType(typeReference).readValues(jsonParser);
    }

    public <T> Iterator<T> readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonProcessingException {
        return readValues(jsonParser, (JavaType) resolvedType);
    }

    public <T> Iterator<T> readValues(JsonParser jsonParser, JavaType javaType) throws IOException, JsonProcessingException {
        return withType(javaType).readValues(jsonParser);
    }

    public <T> T readValue(InputStream inputStream) throws IOException, JsonProcessingException {
        InputStream inputStream2 = inputStream;
        if (this._dataFormatReaders != null) {
            return _detectBindAndClose(this._dataFormatReaders.findFormat(inputStream2), false);
        }
        return _bindAndClose(this._jsonFactory.createParser(inputStream2), this._valueToUpdate);
    }

    public <T> T readValue(Reader reader) throws IOException, JsonProcessingException {
        Reader reader2 = reader;
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader2);
        }
        return _bindAndClose(this._jsonFactory.createParser(reader2), this._valueToUpdate);
    }

    public <T> T readValue(String str) throws IOException, JsonProcessingException {
        String str2 = str;
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str2);
        }
        return _bindAndClose(this._jsonFactory.createParser(str2), this._valueToUpdate);
    }

    public <T> T readValue(byte[] bArr) throws IOException, JsonProcessingException {
        byte[] bArr2 = bArr;
        if (this._dataFormatReaders != null) {
            return _detectBindAndClose(bArr2, 0, bArr2.length);
        }
        return _bindAndClose(this._jsonFactory.createParser(bArr2), this._valueToUpdate);
    }

    public <T> T readValue(byte[] bArr, int i, int i2) throws IOException, JsonProcessingException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this._dataFormatReaders != null) {
            return _detectBindAndClose(bArr2, i3, i4);
        }
        return _bindAndClose(this._jsonFactory.createParser(bArr2, i3, i4), this._valueToUpdate);
    }

    public <T> T readValue(File file) throws IOException, JsonProcessingException {
        File file2 = file;
        if (this._dataFormatReaders != null) {
            return _detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(file2)), true);
        }
        return _bindAndClose(this._jsonFactory.createParser(file2), this._valueToUpdate);
    }

    public <T> T readValue(URL url) throws IOException, JsonProcessingException {
        URL url2 = url;
        if (this._dataFormatReaders != null) {
            return _detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(url2)), true);
        }
        return _bindAndClose(this._jsonFactory.createParser(url2), this._valueToUpdate);
    }

    public <T> T readValue(JsonNode jsonNode) throws IOException, JsonProcessingException {
        JsonNode jsonNode2 = jsonNode;
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(jsonNode2);
        }
        return _bindAndClose(treeAsTokens(jsonNode2), this._valueToUpdate);
    }

    public JsonNode readTree(InputStream inputStream) throws IOException, JsonProcessingException {
        InputStream inputStream2 = inputStream;
        if (this._dataFormatReaders != null) {
            return _detectBindAndCloseAsTree(inputStream2);
        }
        return _bindAndCloseAsTree(this._jsonFactory.createParser(inputStream2));
    }

    public JsonNode readTree(Reader reader) throws IOException, JsonProcessingException {
        Reader reader2 = reader;
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(reader2);
        }
        return _bindAndCloseAsTree(this._jsonFactory.createParser(reader2));
    }

    public JsonNode readTree(String str) throws IOException, JsonProcessingException {
        String str2 = str;
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(str2);
        }
        return _bindAndCloseAsTree(this._jsonFactory.createParser(str2));
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.shaded.fasterxml.jackson.databind.MappingIterator<T> readValues(com.shaded.fasterxml.jackson.core.JsonParser r13) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r3 = r0
            r4 = r1
            r5 = r0
            com.shaded.fasterxml.jackson.databind.DeserializationConfig r5 = r5._config
            com.shaded.fasterxml.jackson.databind.deser.DefaultDeserializationContext r3 = r3.createDeserializationContext(r4, r5)
            r2 = r3
            com.shaded.fasterxml.jackson.databind.MappingIterator r3 = new com.shaded.fasterxml.jackson.databind.MappingIterator
            r11 = r3
            r3 = r11
            r4 = r11
            r5 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r5 = r5._valueType
            r6 = r1
            r7 = r2
            r8 = r0
            r9 = r2
            r10 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r10 = r10._valueType
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r8 = r8._findRootDeserializer(r9, r10)
            r9 = 0
            r10 = r0
            java.lang.Object r10 = r10._valueToUpdate
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ObjectReader.readValues(com.shaded.fasterxml.jackson.core.JsonParser):com.shaded.fasterxml.jackson.databind.MappingIterator");
    }

    public <T> MappingIterator<T> readValues(InputStream inputStream) throws IOException, JsonProcessingException {
        InputStream inputStream2 = inputStream;
        if (this._dataFormatReaders != null) {
            return _detectBindAndReadValues(this._dataFormatReaders.findFormat(inputStream2), false);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(inputStream2), this._valueToUpdate);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.shaded.fasterxml.jackson.databind.MappingIterator<T> readValues(java.io.Reader r14) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r4 = r0
            com.shaded.fasterxml.jackson.databind.deser.DataFormatReaders r4 = r4._dataFormatReaders
            if (r4 == 0) goto L_0x000c
            r4 = r0
            r5 = r1
            r4._reportUndetectableSource(r5)
        L_0x000c:
            r4 = r0
            com.shaded.fasterxml.jackson.core.JsonFactory r4 = r4._jsonFactory
            r5 = r1
            com.shaded.fasterxml.jackson.core.JsonParser r4 = r4.createParser((java.io.Reader) r5)
            r2 = r4
            r4 = r0
            com.shaded.fasterxml.jackson.core.FormatSchema r4 = r4._schema
            if (r4 == 0) goto L_0x0021
            r4 = r2
            r5 = r0
            com.shaded.fasterxml.jackson.core.FormatSchema r5 = r5._schema
            r4.setSchema(r5)
        L_0x0021:
            r4 = r2
            com.shaded.fasterxml.jackson.core.JsonToken r4 = r4.nextToken()
            r4 = r0
            r5 = r2
            r6 = r0
            com.shaded.fasterxml.jackson.databind.DeserializationConfig r6 = r6._config
            com.shaded.fasterxml.jackson.databind.deser.DefaultDeserializationContext r4 = r4.createDeserializationContext(r5, r6)
            r3 = r4
            com.shaded.fasterxml.jackson.databind.MappingIterator r4 = new com.shaded.fasterxml.jackson.databind.MappingIterator
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r6 = r6._valueType
            r7 = r2
            r8 = r3
            r9 = r0
            r10 = r3
            r11 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r11 = r11._valueType
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r9 = r9._findRootDeserializer(r10, r11)
            r10 = 1
            r11 = r0
            java.lang.Object r11 = r11._valueToUpdate
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ObjectReader.readValues(java.io.Reader):com.shaded.fasterxml.jackson.databind.MappingIterator");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.shaded.fasterxml.jackson.databind.MappingIterator<T> readValues(java.lang.String r14) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r4 = r0
            com.shaded.fasterxml.jackson.databind.deser.DataFormatReaders r4 = r4._dataFormatReaders
            if (r4 == 0) goto L_0x000c
            r4 = r0
            r5 = r1
            r4._reportUndetectableSource(r5)
        L_0x000c:
            r4 = r0
            com.shaded.fasterxml.jackson.core.JsonFactory r4 = r4._jsonFactory
            r5 = r1
            com.shaded.fasterxml.jackson.core.JsonParser r4 = r4.createParser((java.lang.String) r5)
            r2 = r4
            r4 = r0
            com.shaded.fasterxml.jackson.core.FormatSchema r4 = r4._schema
            if (r4 == 0) goto L_0x0021
            r4 = r2
            r5 = r0
            com.shaded.fasterxml.jackson.core.FormatSchema r5 = r5._schema
            r4.setSchema(r5)
        L_0x0021:
            r4 = r2
            com.shaded.fasterxml.jackson.core.JsonToken r4 = r4.nextToken()
            r4 = r0
            r5 = r2
            r6 = r0
            com.shaded.fasterxml.jackson.databind.DeserializationConfig r6 = r6._config
            com.shaded.fasterxml.jackson.databind.deser.DefaultDeserializationContext r4 = r4.createDeserializationContext(r5, r6)
            r3 = r4
            com.shaded.fasterxml.jackson.databind.MappingIterator r4 = new com.shaded.fasterxml.jackson.databind.MappingIterator
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r6 = r6._valueType
            r7 = r2
            r8 = r3
            r9 = r0
            r10 = r3
            r11 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r11 = r11._valueType
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r9 = r9._findRootDeserializer(r10, r11)
            r10 = 1
            r11 = r0
            java.lang.Object r11 = r11._valueToUpdate
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ObjectReader.readValues(java.lang.String):com.shaded.fasterxml.jackson.databind.MappingIterator");
    }

    public <T> MappingIterator<T> readValues(byte[] bArr, int i, int i2) throws IOException, JsonProcessingException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this._dataFormatReaders != null) {
            return _detectBindAndReadValues(this._dataFormatReaders.findFormat(bArr2, i3, i4), false);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(bArr2), this._valueToUpdate);
    }

    public final <T> MappingIterator<T> readValues(byte[] bArr) throws IOException, JsonProcessingException {
        byte[] bArr2 = bArr;
        return readValues(bArr2, 0, bArr2.length);
    }

    public <T> MappingIterator<T> readValues(File file) throws IOException, JsonProcessingException {
        File file2 = file;
        if (this._dataFormatReaders != null) {
            return _detectBindAndReadValues(this._dataFormatReaders.findFormat(_inputStream(file2)), false);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(file2), this._valueToUpdate);
    }

    public <T> MappingIterator<T> readValues(URL url) throws IOException, JsonProcessingException {
        URL url2 = url;
        if (this._dataFormatReaders != null) {
            return _detectBindAndReadValues(this._dataFormatReaders.findFormat(_inputStream(url2)), true);
        }
        return _bindAndReadValues(this._jsonFactory.createParser(url2), this._valueToUpdate);
    }

    public JsonNode createArrayNode() {
        return this._config.getNodeFactory().arrayNode();
    }

    public JsonNode createObjectNode() {
        return this._config.getNodeFactory().objectNode();
    }

    public JsonParser treeAsTokens(TreeNode treeNode) {
        JsonParser jsonParser;
        new TreeTraversingParser((JsonNode) treeNode, this);
        return jsonParser;
    }

    public <T> T treeToValue(TreeNode treeNode, Class<T> cls) throws JsonProcessingException {
        Throwable th;
        try {
            return readValue(treeAsTokens(treeNode), cls);
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            IOException iOException = e2;
            Throwable th2 = th;
            new IllegalArgumentException(iOException.getMessage(), iOException);
            throw th2;
        }
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonProcessingException {
        Throwable th;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        Throwable th2 = th;
        new UnsupportedOperationException("Not implemented for ObjectReader");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public Object _bind(JsonParser jsonParser, Object obj) throws IOException, JsonParseException, JsonMappingException {
        Object obj2;
        JsonParser jsonParser2 = jsonParser;
        Object obj3 = obj;
        JsonToken _initForReading = _initForReading(jsonParser2);
        if (_initForReading == JsonToken.VALUE_NULL) {
            if (obj3 == null) {
                obj2 = _findRootDeserializer(createDeserializationContext(jsonParser2, this._config), this._valueType).getNullValue();
            } else {
                obj2 = obj3;
            }
        } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            obj2 = obj3;
        } else {
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser2, this._config);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, this._valueType);
            if (this._unwrapRoot) {
                obj2 = _unwrapAndDeserialize(jsonParser2, createDeserializationContext, this._valueType, _findRootDeserializer);
            } else if (obj3 == null) {
                obj2 = _findRootDeserializer.deserialize(jsonParser2, createDeserializationContext);
            } else {
                Object deserialize = _findRootDeserializer.deserialize(jsonParser2, createDeserializationContext, obj3);
                obj2 = obj3;
            }
        }
        jsonParser2.clearCurrentToken();
        return obj2;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public Object _bindAndClose(JsonParser jsonParser, Object obj) throws IOException, JsonParseException, JsonMappingException {
        Object obj2;
        JsonParser jsonParser2 = jsonParser;
        Object obj3 = obj;
        if (this._schema != null) {
            jsonParser2.setSchema(this._schema);
        }
        try {
            JsonToken _initForReading = _initForReading(jsonParser2);
            if (_initForReading == JsonToken.VALUE_NULL) {
                if (obj3 == null) {
                    obj2 = _findRootDeserializer(createDeserializationContext(jsonParser2, this._config), this._valueType).getNullValue();
                } else {
                    obj2 = obj3;
                }
            } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
                obj2 = obj3;
            } else {
                DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser2, this._config);
                JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, this._valueType);
                if (this._unwrapRoot) {
                    obj2 = _unwrapAndDeserialize(jsonParser2, createDeserializationContext, this._valueType, _findRootDeserializer);
                } else if (obj3 == null) {
                    obj2 = _findRootDeserializer.deserialize(jsonParser2, createDeserializationContext);
                } else {
                    Object deserialize = _findRootDeserializer.deserialize(jsonParser2, createDeserializationContext, obj3);
                    obj2 = obj3;
                }
            }
            Object obj4 = obj2;
            try {
                jsonParser2.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
            return obj4;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                jsonParser2.close();
            } catch (IOException e2) {
                IOException iOException2 = e2;
            }
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public JsonNode _bindAsTree(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        NullNode nullNode;
        JsonParser jsonParser2 = jsonParser;
        JsonToken _initForReading = _initForReading(jsonParser2);
        if (_initForReading == JsonToken.VALUE_NULL || _initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            nullNode = NullNode.instance;
        } else {
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser2, this._config);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, JSON_NODE_TYPE);
            if (this._unwrapRoot) {
                nullNode = (JsonNode) _unwrapAndDeserialize(jsonParser2, createDeserializationContext, JSON_NODE_TYPE, _findRootDeserializer);
            } else {
                nullNode = (JsonNode) _findRootDeserializer.deserialize(jsonParser2, createDeserializationContext);
            }
        }
        jsonParser2.clearCurrentToken();
        return nullNode;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public JsonNode _bindAndCloseAsTree(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonParser jsonParser2 = jsonParser;
        if (this._schema != null) {
            jsonParser2.setSchema(this._schema);
        }
        try {
            JsonNode _bindAsTree = _bindAsTree(jsonParser2);
            try {
                jsonParser2.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
            return _bindAsTree;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                jsonParser2.close();
            } catch (IOException e2) {
                IOException iOException2 = e2;
            }
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.shaded.fasterxml.jackson.databind.MappingIterator<T> _bindAndReadValues(com.shaded.fasterxml.jackson.core.JsonParser r14, java.lang.Object r15) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r4 = r0
            com.shaded.fasterxml.jackson.core.FormatSchema r4 = r4._schema
            if (r4 == 0) goto L_0x000f
            r4 = r1
            r5 = r0
            com.shaded.fasterxml.jackson.core.FormatSchema r5 = r5._schema
            r4.setSchema(r5)
        L_0x000f:
            r4 = r1
            com.shaded.fasterxml.jackson.core.JsonToken r4 = r4.nextToken()
            r4 = r0
            r5 = r1
            r6 = r0
            com.shaded.fasterxml.jackson.databind.DeserializationConfig r6 = r6._config
            com.shaded.fasterxml.jackson.databind.deser.DefaultDeserializationContext r4 = r4.createDeserializationContext(r5, r6)
            r3 = r4
            com.shaded.fasterxml.jackson.databind.MappingIterator r4 = new com.shaded.fasterxml.jackson.databind.MappingIterator
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r6 = r6._valueType
            r7 = r1
            r8 = r3
            r9 = r0
            r10 = r3
            r11 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r11 = r11._valueType
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r9 = r9._findRootDeserializer(r10, r11)
            r10 = 1
            r11 = r0
            java.lang.Object r11 = r11._valueToUpdate
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ObjectReader._bindAndReadValues(com.shaded.fasterxml.jackson.core.JsonParser, java.lang.Object):com.shaded.fasterxml.jackson.databind.MappingIterator");
    }

    protected static JsonToken _initForReading(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonParser jsonParser2 = jsonParser;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == null) {
            currentToken = jsonParser2.nextToken();
            if (currentToken == null) {
                throw JsonMappingException.from(jsonParser2, "No content to map due to end-of-input");
            }
        }
        return currentToken;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        if (this._rootDeserializer != null) {
            return this._rootDeserializer;
        }
        if (javaType2 == null) {
            Throwable th3 = th2;
            new JsonMappingException("No value type configured for ObjectReader");
            throw th3;
        }
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType2);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> findRootValueDeserializer = deserializationContext2.findRootValueDeserializer(javaType2);
        if (findRootValueDeserializer == null) {
            Throwable th4 = th;
            new StringBuilder();
            new JsonMappingException(sb.append("Can not find a deserializer for type ").append(javaType2).toString());
            throw th4;
        }
        JsonDeserializer<Object> put = this._rootDeserializers.put(javaType2, findRootValueDeserializer);
        return findRootValueDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _prefetchRootDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        if (javaType2 == null || !this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType2);
        if (jsonDeserializer == null) {
            try {
                JsonDeserializer<Object> findRootValueDeserializer = createDeserializationContext((JsonParser) null, this._config).findRootValueDeserializer(javaType2);
                if (findRootValueDeserializer != null) {
                    JsonDeserializer<Object> put = this._rootDeserializers.put(javaType2, findRootValueDeserializer);
                }
                return findRootValueDeserializer;
            } catch (JsonProcessingException e) {
                JsonProcessingException jsonProcessingException = e;
            }
        }
        return jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public Object _unwrapAndDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        JsonDeserializer<Object> jsonDeserializer2 = jsonDeserializer;
        String rootName = this._config.getRootName();
        if (rootName == null) {
            rootName = this._rootNames.findRootName(javaType2, (MapperConfig<?>) this._config).getValue();
        }
        if (jsonParser2.getCurrentToken() != JsonToken.START_OBJECT) {
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb4.append("Current token not START_OBJECT (needed to unwrap root name '").append(rootName).append("'), but ").append(jsonParser2.getCurrentToken()).toString());
        } else if (jsonParser2.nextToken() != JsonToken.FIELD_NAME) {
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb3.append("Current token not FIELD_NAME (to contain expected root name '").append(rootName).append("'), but ").append(jsonParser2.getCurrentToken()).toString());
        } else {
            String currentName = jsonParser2.getCurrentName();
            if (!rootName.equals(currentName)) {
                new StringBuilder();
                throw JsonMappingException.from(jsonParser2, sb2.append("Root name '").append(currentName).append("' does not match expected ('").append(rootName).append("') for type ").append(javaType2).toString());
            }
            JsonToken nextToken = jsonParser2.nextToken();
            if (this._valueToUpdate == null) {
                obj = jsonDeserializer2.deserialize(jsonParser2, deserializationContext2);
            } else {
                Object deserialize = jsonDeserializer2.deserialize(jsonParser2, deserializationContext2, this._valueToUpdate);
                obj = this._valueToUpdate;
            }
            if (jsonParser2.nextToken() == JsonToken.END_OBJECT) {
                return obj;
            }
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb.append("Current token not END_OBJECT (to match wrapper object with root name '").append(rootName).append("'), but ").append(jsonParser2.getCurrentToken()).toString());
        }
    }

    /* access modifiers changed from: protected */
    public Object _detectBindAndClose(byte[] bArr, int i, int i2) throws IOException {
        DataFormatReaders.Match findFormat = this._dataFormatReaders.findFormat(bArr, i, i2);
        if (!findFormat.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, findFormat);
        }
        return findFormat.getReader()._bindAndClose(findFormat.createParserWithMatch(), this._valueToUpdate);
    }

    /* access modifiers changed from: protected */
    public Object _detectBindAndClose(DataFormatReaders.Match match, boolean z) throws IOException {
        DataFormatReaders.Match match2 = match;
        boolean z2 = z;
        if (!match2.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match2);
        }
        JsonParser createParserWithMatch = match2.createParserWithMatch();
        if (z2) {
            JsonParser enable = createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match2.getReader()._bindAndClose(createParserWithMatch, this._valueToUpdate);
    }

    /* access modifiers changed from: protected */
    public <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match match, boolean z) throws IOException, JsonProcessingException {
        DataFormatReaders.Match match2 = match;
        boolean z2 = z;
        if (!match2.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match2);
        }
        JsonParser createParserWithMatch = match2.createParserWithMatch();
        if (z2) {
            JsonParser enable = createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match2.getReader()._bindAndReadValues(createParserWithMatch, this._valueToUpdate);
    }

    /* access modifiers changed from: protected */
    public JsonNode _detectBindAndCloseAsTree(InputStream inputStream) throws IOException {
        DataFormatReaders.Match findFormat = this._dataFormatReaders.findFormat(inputStream);
        if (!findFormat.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, findFormat);
        }
        JsonParser createParserWithMatch = findFormat.createParserWithMatch();
        JsonParser enable = createParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return findFormat.getReader()._bindAndCloseAsTree(createParserWithMatch);
    }

    /* access modifiers changed from: protected */
    public void _reportUnkownFormat(DataFormatReaders dataFormatReaders, DataFormatReaders.Match match) throws JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DataFormatReaders.Match match2 = match;
        Throwable th2 = th;
        new StringBuilder();
        new JsonParseException(sb.append("Can not detect format from input, does not look like any of detectable formats ").append(dataFormatReaders.toString()).toString(), JsonLocation.f285NA);
        throw th2;
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

    /* access modifiers changed from: protected */
    public DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return this._context.createInstance(deserializationConfig, jsonParser, this._injectableValues);
    }

    /* access modifiers changed from: protected */
    public ObjectReader _with(DeserializationConfig deserializationConfig) {
        ObjectReader objectReader;
        ObjectReader objectReader2;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        if (deserializationConfig2 == this._config) {
            return this;
        } else if (this._dataFormatReaders != null) {
            new ObjectReader(this, deserializationConfig2);
            return objectReader2.withFormatDetection(this._dataFormatReaders.with(deserializationConfig2));
        } else {
            new ObjectReader(this, deserializationConfig2);
            return objectReader;
        }
    }

    /* access modifiers changed from: protected */
    public void _reportUndetectableSource(Object obj) throws JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new JsonParseException(sb.append("Can not use source of type ").append(obj.getClass().getName()).append(" with format auto-detection: must be byte- not char-based").toString(), JsonLocation.f285NA);
        throw th2;
    }

    /* access modifiers changed from: protected */
    public InputStream _inputStream(URL url) throws IOException {
        return url.openStream();
    }

    /* access modifiers changed from: protected */
    public InputStream _inputStream(File file) throws IOException {
        InputStream inputStream;
        new FileInputStream(file);
        return inputStream;
    }
}
