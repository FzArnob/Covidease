package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class TypeAdapters {
    public static final TypeAdapter<AtomicBoolean> ATOMIC_BOOLEAN;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY = newFactory(AtomicBoolean.class, ATOMIC_BOOLEAN);
    public static final TypeAdapter<AtomicInteger> ATOMIC_INTEGER;
    public static final TypeAdapter<AtomicIntegerArray> ATOMIC_INTEGER_ARRAY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY = newFactory(AtomicIntegerArray.class, ATOMIC_INTEGER_ARRAY);
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY = newFactory(AtomicInteger.class, ATOMIC_INTEGER);
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL;
    public static final TypeAdapter<BigInteger> BIG_INTEGER;
    public static final TypeAdapter<BitSet> BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY = newFactory(BitSet.class, BIT_SET);
    public static final TypeAdapter<Boolean> BOOLEAN;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, BOOLEAN);
    public static final TypeAdapter<Number> BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, BYTE);
    public static final TypeAdapter<Calendar> CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, CALENDAR);
    public static final TypeAdapter<Character> CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, CHARACTER);
    public static final TypeAdapter<Class> CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY = newFactory(Class.class, CLASS);
    public static final TypeAdapter<Currency> CURRENCY;
    public static final TypeAdapterFactory CURRENCY_FACTORY = newFactory(Currency.class, CURRENCY);
    public static final TypeAdapter<Number> DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter<Number> FLOAT;
    public static final TypeAdapter<InetAddress> INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, INET_ADDRESS);
    public static final TypeAdapter<Number> INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, INTEGER);
    public static final TypeAdapter<JsonElement> JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, JSON_ELEMENT);
    public static final TypeAdapter<Locale> LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY = newFactory(Locale.class, LOCALE);
    public static final TypeAdapter<Number> LONG;
    public static final TypeAdapter<Number> NUMBER;
    public static final TypeAdapterFactory NUMBER_FACTORY = newFactory(Number.class, NUMBER);
    public static final TypeAdapter<Number> SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY = newFactory(Short.TYPE, Short.class, SHORT);
    public static final TypeAdapter<String> STRING;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, STRING_BUFFER);
    public static final TypeAdapter<StringBuilder> STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, STRING_BUILDER);
    public static final TypeAdapterFactory STRING_FACTORY = newFactory(String.class, STRING);
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapter<URI> URI;
    public static final TypeAdapterFactory URI_FACTORY = newFactory(URI.class, URI);
    public static final TypeAdapter<URL> URL;
    public static final TypeAdapterFactory URL_FACTORY = newFactory(URL.class, URL);
    public static final TypeAdapter<UUID> UUID;
    public static final TypeAdapterFactory UUID_FACTORY = newFactory(UUID.class, UUID);

    private TypeAdapters() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    static {
        C16021 r3;
        C16132 r32;
        TypeAdapter<Boolean> typeAdapter;
        TypeAdapter<Boolean> typeAdapter2;
        TypeAdapter<Number> typeAdapter3;
        TypeAdapter<Number> typeAdapter4;
        TypeAdapter<Number> typeAdapter5;
        C16388 r33;
        C16399 r34;
        C160310 r35;
        TypeAdapter<Number> typeAdapter6;
        TypeAdapter<Number> typeAdapter7;
        TypeAdapter<Number> typeAdapter8;
        TypeAdapter<Number> typeAdapter9;
        TypeAdapter<Character> typeAdapter10;
        TypeAdapter<String> typeAdapter11;
        TypeAdapter<BigDecimal> typeAdapter12;
        TypeAdapter<BigInteger> typeAdapter13;
        TypeAdapter<StringBuilder> typeAdapter14;
        TypeAdapter<StringBuffer> typeAdapter15;
        TypeAdapter<URL> typeAdapter16;
        TypeAdapter<URI> typeAdapter17;
        TypeAdapter<InetAddress> typeAdapter18;
        TypeAdapter<UUID> typeAdapter19;
        C161925 r36;
        TypeAdapterFactory typeAdapterFactory;
        TypeAdapter<Calendar> typeAdapter20;
        TypeAdapter<Locale> typeAdapter21;
        TypeAdapter<JsonElement> typeAdapter22;
        TypeAdapterFactory typeAdapterFactory2;
        new TypeAdapter<Class>() {
            public void write(JsonWriter jsonWriter, Class value) throws IOException {
                Throwable th;
                StringBuilder sb;
                JsonWriter jsonWriter2 = jsonWriter;
                Throwable th2 = th;
                new StringBuilder();
                new UnsupportedOperationException(sb.append("Attempted to serialize java.lang.Class: ").append(value.getName()).append(". Forgot to register a type adapter?").toString());
                throw th2;
            }

            public Class read(JsonReader jsonReader) throws IOException {
                Throwable th;
                JsonReader jsonReader2 = jsonReader;
                Throwable th2 = th;
                new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
                throw th2;
            }
        };
        CLASS = r3.nullSafe();
        new TypeAdapter<BitSet>() {
            public BitSet read(JsonReader jsonReader) throws IOException {
                BitSet bitSet;
                Throwable th;
                StringBuilder sb;
                Throwable th2;
                StringBuilder sb2;
                boolean set;
                boolean z;
                JsonReader in = jsonReader;
                new BitSet();
                BitSet bitset = bitSet;
                in.beginArray();
                int i = 0;
                JsonToken peek = in.peek();
                while (true) {
                    JsonToken tokenType = peek;
                    if (tokenType != JsonToken.END_ARRAY) {
                        switch (C163336.$SwitchMap$com$google$gson$stream$JsonToken[tokenType.ordinal()]) {
                            case 1:
                                if (in.nextInt() != 0) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                set = z;
                                break;
                            case 2:
                                set = in.nextBoolean();
                                break;
                            case 3:
                                String stringValue = in.nextString();
                                try {
                                    set = Integer.parseInt(stringValue) != 0;
                                    break;
                                } catch (NumberFormatException e) {
                                    NumberFormatException numberFormatException = e;
                                    Throwable th3 = th2;
                                    new StringBuilder();
                                    new JsonSyntaxException(sb2.append("Error: Expecting: bitset number value (1, 0), Found: ").append(stringValue).toString());
                                    throw th3;
                                }
                            default:
                                Throwable th4 = th;
                                new StringBuilder();
                                new JsonSyntaxException(sb.append("Invalid bitset value type: ").append(tokenType).toString());
                                throw th4;
                        }
                        if (set) {
                            bitset.set(i);
                        }
                        i++;
                        peek = in.peek();
                    } else {
                        in.endArray();
                        return bitset;
                    }
                }
            }

            public void write(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
                JsonWriter out = jsonWriter;
                BitSet src = bitSet;
                JsonWriter beginArray = out.beginArray();
                int length = src.length();
                for (int i = 0; i < length; i++) {
                    JsonWriter value = out.value((long) (src.get(i) ? 1 : 0));
                }
                JsonWriter endArray = out.endArray();
            }
        };
        BIT_SET = r32.nullSafe();
        new TypeAdapter<Boolean>() {
            public Boolean read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                JsonToken peek = in.peek();
                if (peek == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                } else if (peek == JsonToken.STRING) {
                    return Boolean.valueOf(Boolean.parseBoolean(in.nextString()));
                } else {
                    return Boolean.valueOf(in.nextBoolean());
                }
            }

            public void write(JsonWriter out, Boolean value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        BOOLEAN = typeAdapter;
        new TypeAdapter<Boolean>() {
            public Boolean read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return Boolean.valueOf(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Boolean bool) throws IOException {
                Boolean value = bool;
                JsonWriter value2 = out.value(value == null ? "null" : value.toString());
            }
        };
        BOOLEAN_AS_STRING = typeAdapter2;
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                Throwable th;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Byte.valueOf((byte) in.nextInt());
                } catch (NumberFormatException e) {
                    NumberFormatException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        BYTE = typeAdapter3;
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                Throwable th;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Short.valueOf((short) in.nextInt());
                } catch (NumberFormatException e) {
                    NumberFormatException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        SHORT = typeAdapter4;
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                Throwable th;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Integer.valueOf(in.nextInt());
                } catch (NumberFormatException e) {
                    NumberFormatException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        INTEGER = typeAdapter5;
        new TypeAdapter<AtomicInteger>() {
            public AtomicInteger read(JsonReader in) throws IOException {
                Throwable th;
                AtomicInteger atomicInteger;
                try {
                    AtomicInteger atomicInteger2 = atomicInteger;
                    new AtomicInteger(in.nextInt());
                    return atomicInteger2;
                } catch (NumberFormatException e) {
                    NumberFormatException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, AtomicInteger value) throws IOException {
                JsonWriter value2 = out.value((long) value.get());
            }
        };
        ATOMIC_INTEGER = r33.nullSafe();
        new TypeAdapter<AtomicBoolean>() {
            public AtomicBoolean read(JsonReader in) throws IOException {
                AtomicBoolean atomicBoolean;
                new AtomicBoolean(in.nextBoolean());
                return atomicBoolean;
            }

            public void write(JsonWriter out, AtomicBoolean value) throws IOException {
                JsonWriter value2 = out.value(value.get());
            }
        };
        ATOMIC_BOOLEAN = r34.nullSafe();
        new TypeAdapter<AtomicIntegerArray>() {
            public AtomicIntegerArray read(JsonReader jsonReader) throws IOException {
                List<Integer> list;
                AtomicIntegerArray atomicIntegerArray;
                Throwable th;
                JsonReader in = jsonReader;
                new ArrayList<>();
                List<Integer> list2 = list;
                in.beginArray();
                while (in.hasNext()) {
                    try {
                        boolean add = list2.add(Integer.valueOf(in.nextInt()));
                    } catch (NumberFormatException e) {
                        NumberFormatException e2 = e;
                        Throwable th2 = th;
                        new JsonSyntaxException((Throwable) e2);
                        throw th2;
                    }
                }
                in.endArray();
                int length = list2.size();
                new AtomicIntegerArray(length);
                AtomicIntegerArray array = atomicIntegerArray;
                for (int i = 0; i < length; i++) {
                    array.set(i, list2.get(i).intValue());
                }
                return array;
            }

            public void write(JsonWriter jsonWriter, AtomicIntegerArray atomicIntegerArray) throws IOException {
                JsonWriter out = jsonWriter;
                AtomicIntegerArray value = atomicIntegerArray;
                JsonWriter beginArray = out.beginArray();
                int length = value.length();
                for (int i = 0; i < length; i++) {
                    JsonWriter value2 = out.value((long) value.get(i));
                }
                JsonWriter endArray = out.endArray();
            }
        };
        ATOMIC_INTEGER_ARRAY = r35.nullSafe();
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                Throwable th;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Long.valueOf(in.nextLong());
                } catch (NumberFormatException e) {
                    NumberFormatException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        LONG = typeAdapter6;
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        FLOAT = typeAdapter7;
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return Double.valueOf(in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        DOUBLE = typeAdapter8;
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                Number number;
                Throwable th;
                StringBuilder sb;
                JsonReader in = jsonReader;
                JsonToken jsonToken = in.peek();
                switch (C163336.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken.ordinal()]) {
                    case 1:
                    case 3:
                        new LazilyParsedNumber(in.nextString());
                        return number;
                    case 4:
                        in.nextNull();
                        return null;
                    default:
                        Throwable th2 = th;
                        new StringBuilder();
                        new JsonSyntaxException(sb.append("Expecting number, got: ").append(jsonToken).toString());
                        throw th2;
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        NUMBER = typeAdapter9;
        new TypeAdapter<Character>() {
            public Character read(JsonReader jsonReader) throws IOException {
                Throwable th;
                StringBuilder sb;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String str = in.nextString();
                if (str.length() == 1) {
                    return Character.valueOf(str.charAt(0));
                }
                Throwable th2 = th;
                new StringBuilder();
                new JsonSyntaxException(sb.append("Expecting character, got: ").append(str).toString());
                throw th2;
            }

            public void write(JsonWriter out, Character ch) throws IOException {
                Character value = ch;
                JsonWriter value2 = out.value(value == null ? null : String.valueOf(value));
            }
        };
        CHARACTER = typeAdapter10;
        new TypeAdapter<String>() {
            public String read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                JsonToken peek = in.peek();
                if (peek == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                } else if (peek == JsonToken.BOOLEAN) {
                    return Boolean.toString(in.nextBoolean());
                } else {
                    return in.nextString();
                }
            }

            public void write(JsonWriter out, String value) throws IOException {
                JsonWriter value2 = out.value(value);
            }
        };
        STRING = typeAdapter11;
        new TypeAdapter<BigDecimal>() {
            public BigDecimal read(JsonReader jsonReader) throws IOException {
                Throwable th;
                BigDecimal bigDecimal;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    BigDecimal bigDecimal2 = bigDecimal;
                    new BigDecimal(in.nextString());
                    return bigDecimal2;
                } catch (NumberFormatException e) {
                    NumberFormatException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, BigDecimal value) throws IOException {
                JsonWriter value2 = out.value((Number) value);
            }
        };
        BIG_DECIMAL = typeAdapter12;
        new TypeAdapter<BigInteger>() {
            public BigInteger read(JsonReader jsonReader) throws IOException {
                Throwable th;
                BigInteger bigInteger;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    BigInteger bigInteger2 = bigInteger;
                    new BigInteger(in.nextString());
                    return bigInteger2;
                } catch (NumberFormatException e) {
                    NumberFormatException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, BigInteger value) throws IOException {
                JsonWriter value2 = out.value((Number) value);
            }
        };
        BIG_INTEGER = typeAdapter13;
        new TypeAdapter<StringBuilder>() {
            public StringBuilder read(JsonReader jsonReader) throws IOException {
                StringBuilder sb;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                new StringBuilder(in.nextString());
                return sb;
            }

            public void write(JsonWriter out, StringBuilder sb) throws IOException {
                StringBuilder value = sb;
                JsonWriter value2 = out.value(value == null ? null : value.toString());
            }
        };
        STRING_BUILDER = typeAdapter14;
        new TypeAdapter<StringBuffer>() {
            public StringBuffer read(JsonReader jsonReader) throws IOException {
                StringBuffer stringBuffer;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                new StringBuffer(in.nextString());
                return stringBuffer;
            }

            public void write(JsonWriter out, StringBuffer stringBuffer) throws IOException {
                StringBuffer value = stringBuffer;
                JsonWriter value2 = out.value(value == null ? null : value.toString());
            }
        };
        STRING_BUFFER = typeAdapter15;
        new TypeAdapter<URL>() {
            public URL read(JsonReader jsonReader) throws IOException {
                URL url;
                URL url2;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String nextString = in.nextString();
                if ("null".equals(nextString)) {
                    url2 = null;
                } else {
                    url2 = url;
                    new URL(nextString);
                }
                return url2;
            }

            public void write(JsonWriter out, URL url) throws IOException {
                URL value = url;
                JsonWriter value2 = out.value(value == null ? null : value.toExternalForm());
            }
        };
        URL = typeAdapter16;
        new TypeAdapter<URI>() {
            public URI read(JsonReader jsonReader) throws IOException {
                Throwable th;
                URI uri;
                URI uri2;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    String nextString = in.nextString();
                    if ("null".equals(nextString)) {
                        uri2 = null;
                    } else {
                        uri2 = uri;
                        new URI(nextString);
                    }
                    return uri2;
                } catch (URISyntaxException e) {
                    URISyntaxException e2 = e;
                    Throwable th2 = th;
                    new JsonIOException((Throwable) e2);
                    throw th2;
                }
            }

            public void write(JsonWriter out, URI uri) throws IOException {
                URI value = uri;
                JsonWriter value2 = out.value(value == null ? null : value.toASCIIString());
            }
        };
        URI = typeAdapter17;
        new TypeAdapter<InetAddress>() {
            public InetAddress read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return InetAddress.getByName(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, InetAddress inetAddress) throws IOException {
                InetAddress value = inetAddress;
                JsonWriter value2 = out.value(value == null ? null : value.getHostAddress());
            }
        };
        INET_ADDRESS = typeAdapter18;
        new TypeAdapter<UUID>() {
            public UUID read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return UUID.fromString(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, UUID uuid) throws IOException {
                UUID value = uuid;
                JsonWriter value2 = out.value(value == null ? null : value.toString());
            }
        };
        UUID = typeAdapter19;
        new TypeAdapter<Currency>() {
            public Currency read(JsonReader in) throws IOException {
                return Currency.getInstance(in.nextString());
            }

            public void write(JsonWriter out, Currency value) throws IOException {
                JsonWriter value2 = out.value(value.getCurrencyCode());
            }
        };
        CURRENCY = r36.nullSafe();
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                TypeAdapter<T> typeAdapter;
                Gson gson2 = gson;
                if (typeToken.getRawType() != Timestamp.class) {
                    return null;
                }
                final TypeAdapter<Date> adapter = gson2.getAdapter(Date.class);
                new TypeAdapter<Timestamp>(this) {
                    final /* synthetic */ C162026 this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public Timestamp read(JsonReader in) throws IOException {
                        Timestamp timestamp;
                        Timestamp timestamp2;
                        Date date = (Date) adapter.read(in);
                        if (date != null) {
                            timestamp = timestamp2;
                            new Timestamp(date.getTime());
                        } else {
                            timestamp = null;
                        }
                        return timestamp;
                    }

                    public void write(JsonWriter out, Timestamp value) throws IOException {
                        adapter.write(out, value);
                    }
                };
                return typeAdapter;
            }
        };
        TIMESTAMP_FACTORY = typeAdapterFactory;
        new TypeAdapter<Calendar>() {
            private static final String DAY_OF_MONTH = "dayOfMonth";
            private static final String HOUR_OF_DAY = "hourOfDay";
            private static final String MINUTE = "minute";
            private static final String MONTH = "month";
            private static final String SECOND = "second";
            private static final String YEAR = "year";

            public Calendar read(JsonReader jsonReader) throws IOException {
                Calendar calendar;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                in.beginObject();
                int year = 0;
                int month = 0;
                int dayOfMonth = 0;
                int hourOfDay = 0;
                int minute = 0;
                int second = 0;
                while (in.peek() != JsonToken.END_OBJECT) {
                    String name = in.nextName();
                    int value = in.nextInt();
                    if (YEAR.equals(name)) {
                        year = value;
                    } else if (MONTH.equals(name)) {
                        month = value;
                    } else if (DAY_OF_MONTH.equals(name)) {
                        dayOfMonth = value;
                    } else if (HOUR_OF_DAY.equals(name)) {
                        hourOfDay = value;
                    } else if (MINUTE.equals(name)) {
                        minute = value;
                    } else if (SECOND.equals(name)) {
                        second = value;
                    }
                }
                in.endObject();
                new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
                return calendar;
            }

            public void write(JsonWriter jsonWriter, Calendar calendar) throws IOException {
                JsonWriter out = jsonWriter;
                Calendar value = calendar;
                if (value == null) {
                    JsonWriter nullValue = out.nullValue();
                    return;
                }
                JsonWriter beginObject = out.beginObject();
                JsonWriter name = out.name(YEAR);
                JsonWriter value2 = out.value((long) value.get(1));
                JsonWriter name2 = out.name(MONTH);
                JsonWriter value3 = out.value((long) value.get(2));
                JsonWriter name3 = out.name(DAY_OF_MONTH);
                JsonWriter value4 = out.value((long) value.get(5));
                JsonWriter name4 = out.name(HOUR_OF_DAY);
                JsonWriter value5 = out.value((long) value.get(11));
                JsonWriter name5 = out.name(MINUTE);
                JsonWriter value6 = out.value((long) value.get(12));
                JsonWriter name6 = out.name(SECOND);
                JsonWriter value7 = out.value((long) value.get(13));
                JsonWriter endObject = out.endObject();
            }
        };
        CALENDAR = typeAdapter20;
        new TypeAdapter<Locale>() {
            public Locale read(JsonReader jsonReader) throws IOException {
                StringTokenizer stringTokenizer;
                Locale locale;
                Locale locale2;
                Locale locale3;
                JsonReader in = jsonReader;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                new StringTokenizer(in.nextString(), "_");
                StringTokenizer tokenizer = stringTokenizer;
                String language = null;
                String country = null;
                String variant = null;
                if (tokenizer.hasMoreElements()) {
                    language = tokenizer.nextToken();
                }
                if (tokenizer.hasMoreElements()) {
                    country = tokenizer.nextToken();
                }
                if (tokenizer.hasMoreElements()) {
                    variant = tokenizer.nextToken();
                }
                if (country == null && variant == null) {
                    new Locale(language);
                    return locale3;
                } else if (variant == null) {
                    new Locale(language, country);
                    return locale2;
                } else {
                    new Locale(language, country, variant);
                    return locale;
                }
            }

            public void write(JsonWriter out, Locale locale) throws IOException {
                Locale value = locale;
                JsonWriter value2 = out.value(value == null ? null : value.toString());
            }
        };
        LOCALE = typeAdapter21;
        new TypeAdapter<JsonElement>() {
            public JsonElement read(JsonReader jsonReader) throws IOException {
                JsonObject jsonObject;
                JsonArray jsonArray;
                JsonElement jsonElement;
                JsonElement jsonElement2;
                Number number;
                JsonElement jsonElement3;
                Throwable th;
                JsonReader in = jsonReader;
                switch (C163336.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
                    case 1:
                        new LazilyParsedNumber(in.nextString());
                        new JsonPrimitive(number);
                        return jsonElement2;
                    case 2:
                        new JsonPrimitive(Boolean.valueOf(in.nextBoolean()));
                        return jsonElement;
                    case 3:
                        new JsonPrimitive(in.nextString());
                        return jsonElement3;
                    case 4:
                        in.nextNull();
                        return JsonNull.INSTANCE;
                    case 5:
                        new JsonArray();
                        JsonArray array = jsonArray;
                        in.beginArray();
                        while (in.hasNext()) {
                            array.add(read(in));
                        }
                        in.endArray();
                        return array;
                    case 6:
                        new JsonObject();
                        JsonObject object = jsonObject;
                        in.beginObject();
                        while (in.hasNext()) {
                            object.add(in.nextName(), read(in));
                        }
                        in.endObject();
                        return object;
                    default:
                        Throwable th2 = th;
                        new IllegalArgumentException();
                        throw th2;
                }
            }

            public void write(JsonWriter jsonWriter, JsonElement jsonElement) throws IOException {
                Throwable th;
                StringBuilder sb;
                JsonWriter out = jsonWriter;
                JsonElement value = jsonElement;
                if (value == null || value.isJsonNull()) {
                    JsonWriter nullValue = out.nullValue();
                } else if (value.isJsonPrimitive()) {
                    JsonPrimitive primitive = value.getAsJsonPrimitive();
                    if (primitive.isNumber()) {
                        JsonWriter value2 = out.value(primitive.getAsNumber());
                    } else if (primitive.isBoolean()) {
                        JsonWriter value3 = out.value(primitive.getAsBoolean());
                    } else {
                        JsonWriter value4 = out.value(primitive.getAsString());
                    }
                } else if (value.isJsonArray()) {
                    JsonWriter beginArray = out.beginArray();
                    Iterator<JsonElement> it = value.getAsJsonArray().iterator();
                    while (it.hasNext()) {
                        write(out, it.next());
                    }
                    JsonWriter endArray = out.endArray();
                } else if (value.isJsonObject()) {
                    JsonWriter beginObject = out.beginObject();
                    for (Map.Entry<String, JsonElement> e : value.getAsJsonObject().entrySet()) {
                        JsonWriter name = out.name(e.getKey());
                        write(out, e.getValue());
                    }
                    JsonWriter endObject = out.endObject();
                } else {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Couldn't write ").append(value.getClass()).toString());
                    throw th2;
                }
            }
        };
        JSON_ELEMENT = typeAdapter22;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                TypeAdapter<T> typeAdapter;
                Gson gson2 = gson;
                Class<? super Object> rawType = typeToken.getRawType();
                if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                    return null;
                }
                if (!rawType.isEnum()) {
                    rawType = rawType.getSuperclass();
                }
                new EnumTypeAdapter(rawType);
                return typeAdapter;
            }
        };
        ENUM_FACTORY = typeAdapterFactory2;
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$36 */
    static /* synthetic */ class C163336 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
                NoSuchFieldError noSuchFieldError = e;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
                NoSuchFieldError noSuchFieldError2 = e2;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
                NoSuchFieldError noSuchFieldError3 = e3;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
                NoSuchFieldError noSuchFieldError4 = e4;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
                NoSuchFieldError noSuchFieldError5 = e5;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
                NoSuchFieldError noSuchFieldError6 = e6;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
                NoSuchFieldError noSuchFieldError7 = e7;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
                NoSuchFieldError noSuchFieldError8 = e8;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
                NoSuchFieldError noSuchFieldError9 = e9;
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
                NoSuchFieldError noSuchFieldError10 = e10;
            }
        }
    }

    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<T, String> constantToName;
        private final Map<String, T> nameToConstant;

        public EnumTypeAdapter(Class<T> cls) {
            Map<String, T> map;
            Map<T, String> map2;
            Throwable th;
            Class<T> classOfT = cls;
            new HashMap();
            this.nameToConstant = map;
            new HashMap();
            this.constantToName = map2;
            try {
                T[] tArr = (Enum[]) classOfT.getEnumConstants();
                int length = tArr.length;
                for (int i = 0; i < length; i++) {
                    T constant = tArr[i];
                    String name = constant.name();
                    SerializedName annotation = (SerializedName) classOfT.getField(name).getAnnotation(SerializedName.class);
                    if (annotation != null) {
                        name = annotation.value();
                        String[] alternate = annotation.alternate();
                        int length2 = alternate.length;
                        for (int i2 = 0; i2 < length2; i2++) {
                            T put = this.nameToConstant.put(alternate[i2], constant);
                        }
                    }
                    T put2 = this.nameToConstant.put(name, constant);
                    String put3 = this.constantToName.put(constant, name);
                }
            } catch (NoSuchFieldException e) {
                NoSuchFieldException e2 = e;
                Throwable th2 = th;
                new AssertionError(e2);
                throw th2;
            }
        }

        public T read(JsonReader jsonReader) throws IOException {
            JsonReader in = jsonReader;
            if (in.peek() != JsonToken.NULL) {
                return (Enum) this.nameToConstant.get(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, T t) throws IOException {
            T value = t;
            JsonWriter value2 = out.value(value == null ? null : this.constantToName.get(value));
        }
    }

    public static <TT> TypeAdapterFactory newFactory(TypeToken<TT> type, TypeAdapter<TT> typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        final TypeToken<TT> typeToken = type;
        final TypeAdapter<TT> typeAdapter2 = typeAdapter;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Gson gson2 = gson;
                return typeToken.equals(typeToken) ? typeAdapter2 : null;
            }
        };
        return typeAdapterFactory;
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> type, TypeAdapter<TT> typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        final Class<TT> cls = type;
        final TypeAdapter<TT> typeAdapter2 = typeAdapter;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Gson gson2 = gson;
                return typeToken.getRawType() == cls ? typeAdapter2 : null;
            }

            public String toString() {
                StringBuilder sb;
                new StringBuilder();
                return sb.append("Factory[type=").append(cls.getName()).append(",adapter=").append(typeAdapter2).append("]").toString();
            }
        };
        return typeAdapterFactory;
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> unboxed, Class<TT> boxed, TypeAdapter<? super TT> typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        final Class<TT> cls = unboxed;
        final Class<TT> cls2 = boxed;
        final TypeAdapter<? super TT> typeAdapter2 = typeAdapter;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Gson gson2 = gson;
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == cls || rawType == cls2) ? typeAdapter2 : null;
            }

            public String toString() {
                StringBuilder sb;
                new StringBuilder();
                return sb.append("Factory[type=").append(cls2.getName()).append("+").append(cls.getName()).append(",adapter=").append(typeAdapter2).append("]").toString();
            }
        };
        return typeAdapterFactory;
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(Class<TT> base, Class<? extends TT> sub, TypeAdapter<? super TT> typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        final Class<TT> cls = base;
        final Class<? extends TT> cls2 = sub;
        final TypeAdapter<? super TT> typeAdapter2 = typeAdapter;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Gson gson2 = gson;
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == cls || rawType == cls2) ? typeAdapter2 : null;
            }

            public String toString() {
                StringBuilder sb;
                new StringBuilder();
                return sb.append("Factory[type=").append(cls.getName()).append("+").append(cls2.getName()).append(",adapter=").append(typeAdapter2).append("]").toString();
            }
        };
        return typeAdapterFactory;
    }

    public static <T1> TypeAdapterFactory newTypeHierarchyFactory(Class<T1> clazz, TypeAdapter<T1> typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        final Class<T1> cls = clazz;
        final TypeAdapter<T1> typeAdapter2 = typeAdapter;
        new TypeAdapterFactory() {
            public <T2> TypeAdapter<T2> create(Gson gson, TypeToken<T2> typeToken) {
                TypeAdapter<T2> typeAdapter;
                Gson gson2 = gson;
                Class<? super T2> requestedType = typeToken.getRawType();
                if (!cls.isAssignableFrom(requestedType)) {
                    return null;
                }
                final Class<? super T2> cls = requestedType;
                new TypeAdapter<T1>(this) {
                    final /* synthetic */ C163135 this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void write(JsonWriter out, T1 value) throws IOException {
                        typeAdapter2.write(out, value);
                    }

                    public T1 read(JsonReader in) throws IOException {
                        Throwable th;
                        StringBuilder sb;
                        C16321 result = typeAdapter2.read(in);
                        if (result == null || cls.isInstance(result)) {
                            return result;
                        }
                        Throwable th2 = th;
                        new StringBuilder();
                        new JsonSyntaxException(sb.append("Expected a ").append(cls.getName()).append(" but was ").append(result.getClass().getName()).toString());
                        throw th2;
                    }
                };
                return typeAdapter;
            }

            public String toString() {
                StringBuilder sb;
                new StringBuilder();
                return sb.append("Factory[typeHierarchy=").append(cls.getName()).append(",adapter=").append(typeAdapter2).append("]").toString();
            }
        };
        return typeAdapterFactory;
    }
}
