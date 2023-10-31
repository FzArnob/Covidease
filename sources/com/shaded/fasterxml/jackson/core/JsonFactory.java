package com.shaded.fasterxml.jackson.core;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.format.InputAccessor;
import com.shaded.fasterxml.jackson.core.format.MatchStrength;
import com.shaded.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.shaded.fasterxml.jackson.core.json.PackageVersion;
import com.shaded.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.shaded.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.shaded.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.shaded.fasterxml.jackson.core.p005io.CharacterEscapes;
import com.shaded.fasterxml.jackson.core.p005io.IOContext;
import com.shaded.fasterxml.jackson.core.p005io.InputDecorator;
import com.shaded.fasterxml.jackson.core.p005io.OutputDecorator;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.core.p005io.UTF8Writer;
import com.shaded.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.shaded.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.net.URL;

public class JsonFactory implements Versioned, Serializable {
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    public static final String FORMAT_NAME_JSON = "JSON";
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;
    private static final long serialVersionUID = 8726401676402117450L;
    protected CharacterEscapes _characterEscapes;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected final transient BytesToNameCanonicalizer _rootByteSymbols;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;

    public enum Feature {
        ;
        
        private final boolean _defaultState;

        public static int collectDefaults() {
            int i = 0;
            Feature[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 < length; i2++) {
                Feature feature = values[i2];
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        private Feature(boolean z) {
            String str = r8;
            int i = r9;
            this._defaultState = z;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i) {
            return (i & getMask()) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    static {
        ThreadLocal<SoftReference<BufferRecycler>> threadLocal;
        new ThreadLocal<>();
        _recyclerRef = threadLocal;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JsonFactory() {
        this((ObjectCodec) null);
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
    }

    protected JsonFactory(JsonFactory jsonFactory, ObjectCodec objectCodec) {
        JsonFactory jsonFactory2 = jsonFactory;
        ObjectCodec objectCodec2 = objectCodec;
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = null;
        this._factoryFeatures = jsonFactory2._factoryFeatures;
        this._parserFeatures = jsonFactory2._parserFeatures;
        this._generatorFeatures = jsonFactory2._generatorFeatures;
        this._characterEscapes = jsonFactory2._characterEscapes;
        this._inputDecorator = jsonFactory2._inputDecorator;
        this._outputDecorator = jsonFactory2._outputDecorator;
        this._rootValueSeparator = jsonFactory2._rootValueSeparator;
    }

    public JsonFactory copy() {
        JsonFactory jsonFactory;
        _checkInvalidCopy(JsonFactory.class);
        new JsonFactory(this, (ObjectCodec) null);
        return jsonFactory;
    }

    /* access modifiers changed from: protected */
    public void _checkInvalidCopy(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        if (getClass() != cls) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Failed copy(): ").append(getClass().getName()).append(" (version: ").append(version()).append(") does not override copy(); it has to").toString());
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        Object obj;
        new JsonFactory(this, this._objectCodec);
        return obj;
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        FormatSchema formatSchema2 = formatSchema;
        String formatName = getFormatName();
        return formatName != null && formatName.equals(formatSchema2.getSchemaType());
    }

    public String getFormatName() {
        if (getClass() == JsonFactory.class) {
            return FORMAT_NAME_JSON;
        }
        return null;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        InputAccessor inputAccessor2 = inputAccessor;
        if (getClass() == JsonFactory.class) {
            return hasJSONFormat(inputAccessor2);
        }
        return null;
    }

    public boolean requiresCustomCodec() {
        return false;
    }

    /* access modifiers changed from: protected */
    public MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        return ByteSourceJsonBootstrapper.hasJSONFormat(inputAccessor);
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public final JsonFactory configure(Feature feature, boolean z) {
        Feature feature2 = feature;
        return z ? enable(feature2) : disable(feature2);
    }

    public JsonFactory enable(Feature feature) {
        this._factoryFeatures |= feature.getMask();
        return this;
    }

    public JsonFactory disable(Feature feature) {
        this._factoryFeatures &= feature.getMask() ^ -1;
        return this;
    }

    public final boolean isEnabled(Feature feature) {
        return (this._factoryFeatures & feature.getMask()) != 0;
    }

    public final JsonFactory configure(JsonParser.Feature feature, boolean z) {
        JsonParser.Feature feature2 = feature;
        return z ? enable(feature2) : disable(feature2);
    }

    public JsonFactory enable(JsonParser.Feature feature) {
        this._parserFeatures |= feature.getMask();
        return this;
    }

    public JsonFactory disable(JsonParser.Feature feature) {
        this._parserFeatures &= feature.getMask() ^ -1;
        return this;
    }

    public final boolean isEnabled(JsonParser.Feature feature) {
        return (this._parserFeatures & feature.getMask()) != 0;
    }

    public InputDecorator getInputDecorator() {
        return this._inputDecorator;
    }

    public JsonFactory setInputDecorator(InputDecorator inputDecorator) {
        this._inputDecorator = inputDecorator;
        return this;
    }

    public final JsonFactory configure(JsonGenerator.Feature feature, boolean z) {
        JsonGenerator.Feature feature2 = feature;
        return z ? enable(feature2) : disable(feature2);
    }

    public JsonFactory enable(JsonGenerator.Feature feature) {
        this._generatorFeatures |= feature.getMask();
        return this;
    }

    public JsonFactory disable(JsonGenerator.Feature feature) {
        this._generatorFeatures &= feature.getMask() ^ -1;
        return this;
    }

    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (this._generatorFeatures & feature.getMask()) != 0;
    }

    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }

    public JsonFactory setCharacterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }

    public OutputDecorator getOutputDecorator() {
        return this._outputDecorator;
    }

    public JsonFactory setOutputDecorator(OutputDecorator outputDecorator) {
        this._outputDecorator = outputDecorator;
        return this;
    }

    public JsonFactory setRootValueSeparator(String str) {
        SerializableString serializableString;
        SerializableString serializableString2;
        String str2 = str;
        if (str2 == null) {
            serializableString2 = null;
        } else {
            serializableString2 = serializableString;
            new SerializedString(str2);
        }
        this._rootValueSeparator = serializableString2;
        return this;
    }

    public String getRootValueSeparator() {
        return this._rootValueSeparator == null ? null : this._rootValueSeparator.getValue();
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonParser createParser(File file) throws IOException, JsonParseException {
        InputStream inputStream;
        File file2 = file;
        IOContext _createContext = _createContext(file2, true);
        new FileInputStream(file2);
        InputStream inputStream2 = inputStream;
        if (this._inputDecorator != null) {
            inputStream2 = this._inputDecorator.decorate(_createContext, inputStream2);
        }
        return _createParser(inputStream2, _createContext);
    }

    public JsonParser createParser(URL url) throws IOException, JsonParseException {
        URL url2 = url;
        IOContext _createContext = _createContext(url2, true);
        InputStream _optimizedStreamFromURL = _optimizedStreamFromURL(url2);
        if (this._inputDecorator != null) {
            _optimizedStreamFromURL = this._inputDecorator.decorate(_createContext, _optimizedStreamFromURL);
        }
        return _createParser(_optimizedStreamFromURL, _createContext);
    }

    public JsonParser createParser(InputStream inputStream) throws IOException, JsonParseException {
        InputStream inputStream2 = inputStream;
        IOContext _createContext = _createContext(inputStream2, false);
        if (this._inputDecorator != null) {
            inputStream2 = this._inputDecorator.decorate(_createContext, inputStream2);
        }
        return _createParser(inputStream2, _createContext);
    }

    public JsonParser createParser(Reader reader) throws IOException, JsonParseException {
        Reader reader2 = reader;
        IOContext _createContext = _createContext(reader2, false);
        if (this._inputDecorator != null) {
            reader2 = this._inputDecorator.decorate(_createContext, reader2);
        }
        return _createParser(reader2, _createContext);
    }

    public JsonParser createParser(byte[] bArr) throws IOException, JsonParseException {
        InputStream decorate;
        byte[] bArr2 = bArr;
        IOContext _createContext = _createContext(bArr2, true);
        if (this._inputDecorator == null || (decorate = this._inputDecorator.decorate(_createContext, bArr2, 0, bArr2.length)) == null) {
            return _createParser(bArr2, 0, bArr2.length, _createContext);
        }
        return _createParser(decorate, _createContext);
    }

    public JsonParser createParser(byte[] bArr, int i, int i2) throws IOException, JsonParseException {
        InputStream decorate;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        IOContext _createContext = _createContext(bArr2, true);
        if (this._inputDecorator == null || (decorate = this._inputDecorator.decorate(_createContext, bArr2, i3, i4)) == null) {
            return _createParser(bArr2, i3, i4, _createContext);
        }
        return _createParser(decorate, _createContext);
    }

    public JsonParser createParser(String str) throws IOException, JsonParseException {
        Reader reader;
        new StringReader(str);
        Reader reader2 = reader;
        IOContext _createContext = _createContext(reader2, true);
        if (this._inputDecorator != null) {
            reader2 = this._inputDecorator.decorate(_createContext, reader2);
        }
        return _createParser(reader2, _createContext);
    }

    @Deprecated
    public JsonParser createJsonParser(File file) throws IOException, JsonParseException {
        return createParser(file);
    }

    @Deprecated
    public JsonParser createJsonParser(URL url) throws IOException, JsonParseException {
        return createParser(url);
    }

    @Deprecated
    public JsonParser createJsonParser(InputStream inputStream) throws IOException, JsonParseException {
        return createParser(inputStream);
    }

    @Deprecated
    public JsonParser createJsonParser(Reader reader) throws IOException, JsonParseException {
        return createParser(reader);
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr) throws IOException, JsonParseException {
        return createParser(bArr);
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr, int i, int i2) throws IOException, JsonParseException {
        return createParser(bArr, i, i2);
    }

    @Deprecated
    public JsonParser createJsonParser(String str) throws IOException, JsonParseException {
        return createParser(str);
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        OutputStream outputStream2 = outputStream;
        JsonEncoding jsonEncoding2 = jsonEncoding;
        IOContext _createContext = _createContext(outputStream2, false);
        _createContext.setEncoding(jsonEncoding2);
        if (jsonEncoding2 == JsonEncoding.UTF8) {
            if (this._outputDecorator != null) {
                outputStream2 = this._outputDecorator.decorate(_createContext, outputStream2);
            }
            return _createUTF8Generator(outputStream2, _createContext);
        }
        Writer _createWriter = _createWriter(outputStream2, jsonEncoding2, _createContext);
        if (this._outputDecorator != null) {
            _createWriter = this._outputDecorator.decorate(_createContext, _createWriter);
        }
        return _createGenerator(_createWriter, _createContext);
    }

    public JsonGenerator createGenerator(OutputStream outputStream) throws IOException {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        Writer writer2 = writer;
        IOContext _createContext = _createContext(writer2, false);
        if (this._outputDecorator != null) {
            writer2 = this._outputDecorator.decorate(_createContext, writer2);
        }
        return _createGenerator(writer2, _createContext);
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        OutputStream outputStream;
        JsonEncoding jsonEncoding2 = jsonEncoding;
        new FileOutputStream(file);
        OutputStream outputStream2 = outputStream;
        IOContext _createContext = _createContext(outputStream2, true);
        _createContext.setEncoding(jsonEncoding2);
        if (jsonEncoding2 == JsonEncoding.UTF8) {
            if (this._outputDecorator != null) {
                outputStream2 = this._outputDecorator.decorate(_createContext, outputStream2);
            }
            return _createUTF8Generator(outputStream2, _createContext);
        }
        Writer _createWriter = _createWriter(outputStream2, jsonEncoding2, _createContext);
        if (this._outputDecorator != null) {
            _createWriter = this._outputDecorator.decorate(_createContext, _createWriter);
        }
        return _createGenerator(_createWriter, _createContext);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        return createGenerator(outputStream, jsonEncoding);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
        return createGenerator(writer);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream) throws IOException {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        return createGenerator(file, jsonEncoding);
    }

    /* access modifiers changed from: protected */
    public JsonParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException, JsonParseException {
        ByteSourceJsonBootstrapper byteSourceJsonBootstrapper;
        new ByteSourceJsonBootstrapper(iOContext, inputStream);
        return byteSourceJsonBootstrapper.constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols, isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonParser _createJsonParser(InputStream inputStream, IOContext iOContext) throws IOException, JsonParseException {
        return _createParser(inputStream, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonParser _createParser(Reader reader, IOContext iOContext) throws IOException, JsonParseException {
        JsonParser jsonParser;
        new ReaderBasedJsonParser(iOContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES)));
        return jsonParser;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonParser _createJsonParser(Reader reader, IOContext iOContext) throws IOException, JsonParseException {
        return _createParser(reader, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonParser _createParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException, JsonParseException {
        ByteSourceJsonBootstrapper byteSourceJsonBootstrapper;
        new ByteSourceJsonBootstrapper(iOContext, bArr, i, i2);
        return byteSourceJsonBootstrapper.constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols, isEnabled(Feature.CANONICALIZE_FIELD_NAMES), isEnabled(Feature.INTERN_FIELD_NAMES));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonParser _createJsonParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException, JsonParseException {
        return _createParser(bArr, i, i2, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonGenerator _createGenerator(Writer writer, IOContext iOContext) throws IOException {
        WriterBasedJsonGenerator writerBasedJsonGenerator;
        new WriterBasedJsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, writer);
        WriterBasedJsonGenerator writerBasedJsonGenerator2 = writerBasedJsonGenerator;
        if (this._characterEscapes != null) {
            JsonGenerator characterEscapes = writerBasedJsonGenerator2.setCharacterEscapes(this._characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            JsonGenerator rootValueSeparator = writerBasedJsonGenerator2.setRootValueSeparator(serializableString);
        }
        return writerBasedJsonGenerator2;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonGenerator _createJsonGenerator(Writer writer, IOContext iOContext) throws IOException {
        return _createGenerator(writer, iOContext);
    }

    /* access modifiers changed from: protected */
    public JsonGenerator _createUTF8Generator(OutputStream outputStream, IOContext iOContext) throws IOException {
        UTF8JsonGenerator uTF8JsonGenerator;
        new UTF8JsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, outputStream);
        UTF8JsonGenerator uTF8JsonGenerator2 = uTF8JsonGenerator;
        if (this._characterEscapes != null) {
            JsonGenerator characterEscapes = uTF8JsonGenerator2.setCharacterEscapes(this._characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            JsonGenerator rootValueSeparator = uTF8JsonGenerator2.setRootValueSeparator(serializableString);
        }
        return uTF8JsonGenerator2;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonGenerator _createUTF8JsonGenerator(OutputStream outputStream, IOContext iOContext) throws IOException {
        return _createUTF8Generator(outputStream, iOContext);
    }

    /* access modifiers changed from: protected */
    public Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) throws IOException {
        Writer writer;
        Writer writer2;
        OutputStream outputStream2 = outputStream;
        JsonEncoding jsonEncoding2 = jsonEncoding;
        IOContext iOContext2 = iOContext;
        if (jsonEncoding2 == JsonEncoding.UTF8) {
            new UTF8Writer(iOContext2, outputStream2);
            return writer2;
        }
        new OutputStreamWriter(outputStream2, jsonEncoding2.getJavaName());
        return writer;
    }

    /* access modifiers changed from: protected */
    public IOContext _createContext(Object obj, boolean z) {
        IOContext iOContext;
        new IOContext(_getBufferRecycler(), obj, z);
        return iOContext;
    }

    public BufferRecycler _getBufferRecycler() {
        BufferRecycler bufferRecycler;
        Object obj;
        SoftReference softReference = _recyclerRef.get();
        BufferRecycler bufferRecycler2 = softReference == null ? null : (BufferRecycler) softReference.get();
        if (bufferRecycler2 == null) {
            new BufferRecycler();
            bufferRecycler2 = bufferRecycler;
            new SoftReference(bufferRecycler2);
            _recyclerRef.set(obj);
        }
        return bufferRecycler2;
    }

    /* access modifiers changed from: protected */
    public InputStream _optimizedStreamFromURL(URL url) throws IOException {
        String host;
        InputStream inputStream;
        URL url2 = url;
        if (!"file".equals(url2.getProtocol()) || (((host = url2.getHost()) != null && host.length() != 0) || url2.getPath().indexOf(37) >= 0)) {
            return url2.openStream();
        }
        new FileInputStream(url2.getPath());
        return inputStream;
    }
}
