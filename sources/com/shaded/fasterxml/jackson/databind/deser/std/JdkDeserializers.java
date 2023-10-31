package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class JdkDeserializers {
    private static final HashSet<String> _classNames;

    public JdkDeserializers() {
    }

    static {
        HashSet<String> hashSet;
        new HashSet<>();
        _classNames = hashSet;
        Class[] clsArr = new Class[12];
        clsArr[0] = UUID.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = URL.class;
        Class[] clsArr3 = clsArr2;
        clsArr3[2] = URI.class;
        Class[] clsArr4 = clsArr3;
        clsArr4[3] = File.class;
        Class[] clsArr5 = clsArr4;
        clsArr5[4] = Currency.class;
        Class[] clsArr6 = clsArr5;
        clsArr6[5] = Pattern.class;
        Class[] clsArr7 = clsArr6;
        clsArr7[6] = Locale.class;
        Class[] clsArr8 = clsArr7;
        clsArr8[7] = InetAddress.class;
        Class[] clsArr9 = clsArr8;
        clsArr9[8] = Charset.class;
        Class[] clsArr10 = clsArr9;
        clsArr10[9] = AtomicBoolean.class;
        Class[] clsArr11 = clsArr10;
        clsArr11[10] = Class.class;
        Class[] clsArr12 = clsArr11;
        clsArr12[11] = StackTraceElement.class;
        Class[] clsArr13 = clsArr12;
        int length = clsArr13.length;
        for (int i = 0; i < length; i++) {
            boolean add = _classNames.add(clsArr13[i].getName());
        }
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        StdDeserializer<?>[] stdDeserializerArr = new StdDeserializer[12];
        stdDeserializerArr[0] = UUIDDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr2 = stdDeserializerArr;
        stdDeserializerArr2[1] = URLDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr3 = stdDeserializerArr2;
        stdDeserializerArr3[2] = URIDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr4 = stdDeserializerArr3;
        stdDeserializerArr4[3] = FileDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr5 = stdDeserializerArr4;
        stdDeserializerArr5[4] = CurrencyDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr6 = stdDeserializerArr5;
        stdDeserializerArr6[5] = PatternDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr7 = stdDeserializerArr6;
        stdDeserializerArr7[6] = LocaleDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr8 = stdDeserializerArr7;
        stdDeserializerArr8[7] = InetAddressDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr9 = stdDeserializerArr8;
        stdDeserializerArr9[8] = CharsetDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr10 = stdDeserializerArr9;
        stdDeserializerArr10[9] = AtomicBooleanDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr11 = stdDeserializerArr10;
        stdDeserializerArr11[10] = ClassDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr12 = stdDeserializerArr11;
        StdDeserializer<?>[] stdDeserializerArr13 = stdDeserializerArr12;
        stdDeserializerArr12[11] = StackTraceElementDeserializer.instance;
        return stdDeserializerArr13;
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        String str2 = str;
        if (!_classNames.contains(str2)) {
            return null;
        }
        if (cls2 == URI.class) {
            return URIDeserializer.instance;
        }
        if (cls2 == URL.class) {
            return URLDeserializer.instance;
        }
        if (cls2 == File.class) {
            return FileDeserializer.instance;
        }
        if (cls2 == UUID.class) {
            return UUIDDeserializer.instance;
        }
        if (cls2 == Currency.class) {
            return CurrencyDeserializer.instance;
        }
        if (cls2 == Pattern.class) {
            return PatternDeserializer.instance;
        }
        if (cls2 == Locale.class) {
            return LocaleDeserializer.instance;
        }
        if (cls2 == InetAddress.class) {
            return InetAddressDeserializer.instance;
        }
        if (cls2 == Charset.class) {
            return CharsetDeserializer.instance;
        }
        if (cls2 == Class.class) {
            return ClassDeserializer.instance;
        }
        if (cls2 == StackTraceElement.class) {
            return StackTraceElementDeserializer.instance;
        }
        if (cls2 == AtomicBoolean.class) {
            return AtomicBooleanDeserializer.instance;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Internal error: can't find deserializer for ").append(str2).toString());
        throw th2;
    }

    public static class UUIDDeserializer extends FromStringDeserializer<UUID> {
        public static final UUIDDeserializer instance;

        static {
            UUIDDeserializer uUIDDeserializer;
            new UUIDDeserializer();
            instance = uUIDDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UUIDDeserializer() {
            super(UUID.class);
        }

        /* access modifiers changed from: protected */
        public UUID _deserialize(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return UUID.fromString(str);
        }

        /* access modifiers changed from: protected */
        public UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            DataInputStream dataInputStream;
            InputStream inputStream;
            UUID uuid;
            StringBuilder sb;
            Object obj2 = obj;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (obj2 instanceof byte[]) {
                byte[] bArr = (byte[]) obj2;
                if (bArr.length != 16) {
                    new StringBuilder();
                    JsonMappingException mappingException = deserializationContext2.mappingException(sb.append("Can only construct UUIDs from 16 byte arrays; got ").append(bArr.length).append(" bytes").toString());
                }
                new ByteArrayInputStream(bArr);
                new DataInputStream(inputStream);
                DataInputStream dataInputStream2 = dataInputStream;
                new UUID(dataInputStream2.readLong(), dataInputStream2.readLong());
                return uuid;
            }
            Object _deserializeEmbedded = super._deserializeEmbedded(obj2, deserializationContext2);
            return null;
        }
    }

    public static class URLDeserializer extends FromStringDeserializer<URL> {
        public static final URLDeserializer instance;

        static {
            URLDeserializer uRLDeserializer;
            new URLDeserializer();
            instance = uRLDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public URLDeserializer() {
            super(URL.class);
        }

        /* access modifiers changed from: protected */
        public URL _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            URL url;
            DeserializationContext deserializationContext2 = deserializationContext;
            new URL(str);
            return url;
        }
    }

    public static class URIDeserializer extends FromStringDeserializer<URI> {
        public static final URIDeserializer instance;

        static {
            URIDeserializer uRIDeserializer;
            new URIDeserializer();
            instance = uRIDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public URIDeserializer() {
            super(URI.class);
        }

        /* access modifiers changed from: protected */
        public URI _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return URI.create(str);
        }
    }

    public static class CurrencyDeserializer extends FromStringDeserializer<Currency> {
        public static final CurrencyDeserializer instance;

        static {
            CurrencyDeserializer currencyDeserializer;
            new CurrencyDeserializer();
            instance = currencyDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CurrencyDeserializer() {
            super(Currency.class);
        }

        /* access modifiers changed from: protected */
        public Currency _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return Currency.getInstance(str);
        }
    }

    public static class PatternDeserializer extends FromStringDeserializer<Pattern> {
        public static final PatternDeserializer instance;

        static {
            PatternDeserializer patternDeserializer;
            new PatternDeserializer();
            instance = patternDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PatternDeserializer() {
            super(Pattern.class);
        }

        /* access modifiers changed from: protected */
        public Pattern _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return Pattern.compile(str);
        }
    }

    protected static class LocaleDeserializer extends FromStringDeserializer<Locale> {
        public static final LocaleDeserializer instance;

        static {
            LocaleDeserializer localeDeserializer;
            new LocaleDeserializer();
            instance = localeDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LocaleDeserializer() {
            super(Locale.class);
        }

        /* access modifiers changed from: protected */
        public Locale _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            Locale locale;
            Locale locale2;
            Locale locale3;
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            int indexOf = str2.indexOf(95);
            if (indexOf < 0) {
                new Locale(str2);
                return locale3;
            }
            String substring = str2.substring(0, indexOf);
            String substring2 = str2.substring(indexOf + 1);
            int indexOf2 = substring2.indexOf(95);
            if (indexOf2 < 0) {
                new Locale(substring, substring2);
                return locale2;
            }
            new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
            return locale;
        }
    }

    protected static class InetAddressDeserializer extends FromStringDeserializer<InetAddress> {
        public static final InetAddressDeserializer instance;

        static {
            InetAddressDeserializer inetAddressDeserializer;
            new InetAddressDeserializer();
            instance = inetAddressDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InetAddressDeserializer() {
            super(InetAddress.class);
        }

        /* access modifiers changed from: protected */
        public InetAddress _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return InetAddress.getByName(str);
        }
    }

    protected static class CharsetDeserializer extends FromStringDeserializer<Charset> {
        public static final CharsetDeserializer instance;

        static {
            CharsetDeserializer charsetDeserializer;
            new CharsetDeserializer();
            instance = charsetDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CharsetDeserializer() {
            super(Charset.class);
        }

        /* access modifiers changed from: protected */
        public Charset _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return Charset.forName(str);
        }
    }

    public static class FileDeserializer extends FromStringDeserializer<File> {
        public static final FileDeserializer instance;

        static {
            FileDeserializer fileDeserializer;
            new FileDeserializer();
            instance = fileDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FileDeserializer() {
            super(File.class);
        }

        /* access modifiers changed from: protected */
        public File _deserialize(String str, DeserializationContext deserializationContext) {
            File file;
            DeserializationContext deserializationContext2 = deserializationContext;
            new File(str);
            return file;
        }
    }

    public static class AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements ContextualDeserializer {
        protected final JavaType _referencedType;
        protected final JsonDeserializer<?> _valueDeserializer;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public AtomicReferenceDeserializer(JavaType javaType) {
            this(javaType, (JsonDeserializer<?>) null);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AtomicReferenceDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
            super((Class<?>) AtomicReference.class);
            this._referencedType = javaType;
            this._valueDeserializer = jsonDeserializer;
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.concurrent.atomic.AtomicReference<?> deserialize(com.shaded.fasterxml.jackson.core.JsonParser r10, com.shaded.fasterxml.jackson.databind.DeserializationContext r11) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
            /*
                r9 = this;
                r0 = r9
                r1 = r10
                r2 = r11
                java.util.concurrent.atomic.AtomicReference r3 = new java.util.concurrent.atomic.AtomicReference
                r8 = r3
                r3 = r8
                r4 = r8
                r5 = r0
                com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r5 = r5._valueDeserializer
                r6 = r1
                r7 = r2
                java.lang.Object r5 = r5.deserialize(r6, r7)
                r4.<init>(r5)
                r0 = r3
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.JdkDeserializers.AtomicReferenceDeserializer.deserialize(com.shaded.fasterxml.jackson.core.JsonParser, com.shaded.fasterxml.jackson.databind.DeserializationContext):java.util.concurrent.atomic.AtomicReference");
        }

        public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            JsonDeserializer<?> jsonDeserializer;
            DeserializationContext deserializationContext2 = deserializationContext;
            BeanProperty beanProperty2 = beanProperty;
            if (this._valueDeserializer != null) {
                return this;
            }
            new AtomicReferenceDeserializer(this._referencedType, deserializationContext2.findContextualValueDeserializer(this._referencedType, beanProperty2));
            return jsonDeserializer;
        }
    }

    public static class AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
        public static final AtomicBooleanDeserializer instance;

        static {
            AtomicBooleanDeserializer atomicBooleanDeserializer;
            new AtomicBooleanDeserializer();
            instance = atomicBooleanDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AtomicBooleanDeserializer() {
            super((Class<?>) AtomicBoolean.class);
        }

        public AtomicBoolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            AtomicBoolean atomicBoolean;
            new AtomicBoolean(_parseBooleanPrimitive(jsonParser, deserializationContext));
            return atomicBoolean;
        }
    }

    public static class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
        public static final StackTraceElementDeserializer instance;

        static {
            StackTraceElementDeserializer stackTraceElementDeserializer;
            new StackTraceElementDeserializer();
            instance = stackTraceElementDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StackTraceElementDeserializer() {
            super((Class<?>) StackTraceElement.class);
        }

        public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            StackTraceElement stackTraceElement;
            StringBuilder sb;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.START_OBJECT) {
                String str = "";
                String str2 = "";
                String str3 = "";
                int i = -1;
                while (true) {
                    JsonToken nextValue = jsonParser2.nextValue();
                    JsonToken jsonToken = nextValue;
                    if (nextValue != JsonToken.END_OBJECT) {
                        String currentName = jsonParser2.getCurrentName();
                        if ("className".equals(currentName)) {
                            str = jsonParser2.getText();
                        } else if ("fileName".equals(currentName)) {
                            str3 = jsonParser2.getText();
                        } else if ("lineNumber".equals(currentName)) {
                            if (jsonToken.isNumeric()) {
                                i = jsonParser2.getIntValue();
                            } else {
                                new StringBuilder();
                                throw JsonMappingException.from(jsonParser2, sb.append("Non-numeric token (").append(jsonToken).append(") for property 'lineNumber'").toString());
                            }
                        } else if ("methodName".equals(currentName)) {
                            str2 = jsonParser2.getText();
                        } else if (!"nativeMethod".equals(currentName)) {
                            handleUnknownProperty(jsonParser2, deserializationContext2, this._valueClass, currentName);
                        }
                    } else {
                        new StackTraceElement(str, str2, str3, i);
                        return stackTraceElement;
                    }
                }
            } else {
                throw deserializationContext2.mappingException(this._valueClass, currentToken);
            }
        }
    }
}
