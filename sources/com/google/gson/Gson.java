package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class Gson {
    static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    static final boolean DEFAULT_ESCAPE_HTML = true;
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    static final boolean DEFAULT_LENIENT = false;
    static final boolean DEFAULT_PRETTY_PRINT = false;
    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    private static final TypeToken<?> NULL_KEY_SURROGATE = TypeToken.get(Object.class);
    final List<TypeAdapterFactory> builderFactories;
    final List<TypeAdapterFactory> builderHierarchyFactories;
    private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls;
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    final String datePattern;
    final int dateStyle;
    final Excluder excluder;
    final List<TypeAdapterFactory> factories;
    final FieldNamingStrategy fieldNamingStrategy;
    final boolean generateNonExecutableJson;
    final boolean htmlSafe;
    final Map<Type, InstanceCreator<?>> instanceCreators;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    final boolean lenient;
    final LongSerializationPolicy longSerializationPolicy;
    final boolean prettyPrinting;
    final boolean serializeNulls;
    final boolean serializeSpecialFloatingPointValues;
    final int timeStyle;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, (String) null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Gson(com.google.gson.internal.Excluder r31, com.google.gson.FieldNamingStrategy r32, java.util.Map<java.lang.reflect.Type, com.google.gson.InstanceCreator<?>> r33, boolean r34, boolean r35, boolean r36, boolean r37, boolean r38, boolean r39, boolean r40, com.google.gson.LongSerializationPolicy r41, java.lang.String r42, int r43, int r44, java.util.List<com.google.gson.TypeAdapterFactory> r45, java.util.List<com.google.gson.TypeAdapterFactory> r46, java.util.List<com.google.gson.TypeAdapterFactory> r47) {
        /*
            r30 = this;
            r2 = r30
            r3 = r31
            r4 = r32
            r5 = r33
            r6 = r34
            r7 = r35
            r8 = r36
            r9 = r37
            r10 = r38
            r11 = r39
            r12 = r40
            r13 = r41
            r14 = r42
            r15 = r43
            r16 = r44
            r17 = r45
            r18 = r46
            r19 = r47
            r22 = r2
            r22.<init>()
            r22 = r2
            java.lang.ThreadLocal r23 = new java.lang.ThreadLocal
            r29 = r23
            r23 = r29
            r24 = r29
            r24.<init>()
            r0 = r23
            r1 = r22
            r1.calls = r0
            r22 = r2
            java.util.concurrent.ConcurrentHashMap r23 = new java.util.concurrent.ConcurrentHashMap
            r29 = r23
            r23 = r29
            r24 = r29
            r24.<init>()
            r0 = r23
            r1 = r22
            r1.typeTokenCache = r0
            r22 = r2
            r23 = r3
            r0 = r23
            r1 = r22
            r1.excluder = r0
            r22 = r2
            r23 = r4
            r0 = r23
            r1 = r22
            r1.fieldNamingStrategy = r0
            r22 = r2
            r23 = r5
            r0 = r23
            r1 = r22
            r1.instanceCreators = r0
            r22 = r2
            com.google.gson.internal.ConstructorConstructor r23 = new com.google.gson.internal.ConstructorConstructor
            r29 = r23
            r23 = r29
            r24 = r29
            r25 = r5
            r24.<init>(r25)
            r0 = r23
            r1 = r22
            r1.constructorConstructor = r0
            r22 = r2
            r23 = r6
            r0 = r23
            r1 = r22
            r1.serializeNulls = r0
            r22 = r2
            r23 = r7
            r0 = r23
            r1 = r22
            r1.complexMapKeySerialization = r0
            r22 = r2
            r23 = r8
            r0 = r23
            r1 = r22
            r1.generateNonExecutableJson = r0
            r22 = r2
            r23 = r9
            r0 = r23
            r1 = r22
            r1.htmlSafe = r0
            r22 = r2
            r23 = r10
            r0 = r23
            r1 = r22
            r1.prettyPrinting = r0
            r22 = r2
            r23 = r11
            r0 = r23
            r1 = r22
            r1.lenient = r0
            r22 = r2
            r23 = r12
            r0 = r23
            r1 = r22
            r1.serializeSpecialFloatingPointValues = r0
            r22 = r2
            r23 = r13
            r0 = r23
            r1 = r22
            r1.longSerializationPolicy = r0
            r22 = r2
            r23 = r14
            r0 = r23
            r1 = r22
            r1.datePattern = r0
            r22 = r2
            r23 = r15
            r0 = r23
            r1 = r22
            r1.dateStyle = r0
            r22 = r2
            r23 = r16
            r0 = r23
            r1 = r22
            r1.timeStyle = r0
            r22 = r2
            r23 = r17
            r0 = r23
            r1 = r22
            r1.builderFactories = r0
            r22 = r2
            r23 = r18
            r0 = r23
            r1 = r22
            r1.builderHierarchyFactories = r0
            java.util.ArrayList r22 = new java.util.ArrayList
            r29 = r22
            r22 = r29
            r23 = r29
            r23.<init>()
            r20 = r22
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.JSON_ELEMENT_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.ObjectTypeAdapter.FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            r23 = r3
            boolean r22 = r22.add(r23)
            r22 = r20
            r23 = r19
            boolean r22 = r22.addAll(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.STRING_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.INTEGER_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.BOOLEAN_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.BYTE_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.SHORT_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r13
            com.google.gson.TypeAdapter r22 = longAdapter(r22)
            r21 = r22
            r22 = r20
            java.lang.Class r23 = java.lang.Long.TYPE
            java.lang.Class<java.lang.Long> r24 = java.lang.Long.class
            r25 = r21
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.newFactory(r23, r24, r25)
            boolean r22 = r22.add(r23)
            r22 = r20
            java.lang.Class r23 = java.lang.Double.TYPE
            java.lang.Class<java.lang.Double> r24 = java.lang.Double.class
            r25 = r2
            r26 = r12
            com.google.gson.TypeAdapter r25 = r25.doubleAdapter(r26)
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.newFactory(r23, r24, r25)
            boolean r22 = r22.add(r23)
            r22 = r20
            java.lang.Class r23 = java.lang.Float.TYPE
            java.lang.Class<java.lang.Float> r24 = java.lang.Float.class
            r25 = r2
            r26 = r12
            com.google.gson.TypeAdapter r25 = r25.floatAdapter(r26)
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.newFactory(r23, r24, r25)
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.NUMBER_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.ATOMIC_INTEGER_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.ATOMIC_BOOLEAN_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            java.lang.Class<java.util.concurrent.atomic.AtomicLong> r23 = java.util.concurrent.atomic.AtomicLong.class
            r24 = r21
            com.google.gson.TypeAdapter r24 = atomicLongAdapter(r24)
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.newFactory(r23, r24)
            boolean r22 = r22.add(r23)
            r22 = r20
            java.lang.Class<java.util.concurrent.atomic.AtomicLongArray> r23 = java.util.concurrent.atomic.AtomicLongArray.class
            r24 = r21
            com.google.gson.TypeAdapter r24 = atomicLongArrayAdapter(r24)
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.newFactory(r23, r24)
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.CHARACTER_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.STRING_BUILDER_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.STRING_BUFFER_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            java.lang.Class<java.math.BigDecimal> r23 = java.math.BigDecimal.class
            com.google.gson.TypeAdapter<java.math.BigDecimal> r24 = com.google.gson.internal.bind.TypeAdapters.BIG_DECIMAL
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.newFactory(r23, r24)
            boolean r22 = r22.add(r23)
            r22 = r20
            java.lang.Class<java.math.BigInteger> r23 = java.math.BigInteger.class
            com.google.gson.TypeAdapter<java.math.BigInteger> r24 = com.google.gson.internal.bind.TypeAdapters.BIG_INTEGER
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.newFactory(r23, r24)
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.URL_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.URI_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.UUID_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.CURRENCY_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.LOCALE_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.INET_ADDRESS_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.BIT_SET_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.DateTypeAdapter.FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.CALENDAR_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TimeTypeAdapter.FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.SqlDateTypeAdapter.FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.TIMESTAMP_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.ArrayTypeAdapter.FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.CLASS_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.internal.bind.CollectionTypeAdapterFactory r23 = new com.google.gson.internal.bind.CollectionTypeAdapterFactory
            r29 = r23
            r23 = r29
            r24 = r29
            r25 = r2
            r0 = r25
            com.google.gson.internal.ConstructorConstructor r0 = r0.constructorConstructor
            r25 = r0
            r24.<init>(r25)
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.internal.bind.MapTypeAdapterFactory r23 = new com.google.gson.internal.bind.MapTypeAdapterFactory
            r29 = r23
            r23 = r29
            r24 = r29
            r25 = r2
            r0 = r25
            com.google.gson.internal.ConstructorConstructor r0 = r0.constructorConstructor
            r25 = r0
            r26 = r7
            r24.<init>(r25, r26)
            boolean r22 = r22.add(r23)
            r22 = r2
            com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory r23 = new com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory
            r29 = r23
            r23 = r29
            r24 = r29
            r25 = r2
            r0 = r25
            com.google.gson.internal.ConstructorConstructor r0 = r0.constructorConstructor
            r25 = r0
            r24.<init>(r25)
            r0 = r23
            r1 = r22
            r1.jsonAdapterFactory = r0
            r22 = r20
            r23 = r2
            r0 = r23
            com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory r0 = r0.jsonAdapterFactory
            r23 = r0
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.TypeAdapterFactory r23 = com.google.gson.internal.bind.TypeAdapters.ENUM_FACTORY
            boolean r22 = r22.add(r23)
            r22 = r20
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory r23 = new com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
            r29 = r23
            r23 = r29
            r24 = r29
            r25 = r2
            r0 = r25
            com.google.gson.internal.ConstructorConstructor r0 = r0.constructorConstructor
            r25 = r0
            r26 = r4
            r27 = r3
            r28 = r2
            r0 = r28
            com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory r0 = r0.jsonAdapterFactory
            r28 = r0
            r24.<init>(r25, r26, r27, r28)
            boolean r22 = r22.add(r23)
            r22 = r2
            r23 = r20
            java.util.List r23 = java.util.Collections.unmodifiableList(r23)
            r0 = r23
            r1 = r22
            r1.factories = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.<init>(com.google.gson.internal.Excluder, com.google.gson.FieldNamingStrategy, java.util.Map, boolean, boolean, boolean, boolean, boolean, boolean, boolean, com.google.gson.LongSerializationPolicy, java.lang.String, int, int, java.util.List, java.util.List, java.util.List):void");
    }

    public GsonBuilder newBuilder() {
        GsonBuilder gsonBuilder;
        new GsonBuilder(this);
        return gsonBuilder;
    }

    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    private TypeAdapter<Number> doubleAdapter(boolean serializeSpecialFloatingPointValues2) {
        TypeAdapter<Number> typeAdapter;
        if (serializeSpecialFloatingPointValues2) {
            return TypeAdapters.DOUBLE;
        }
        new TypeAdapter<Number>(this) {
            final /* synthetic */ Gson this$0;

            {
                this.this$0 = this$0;
            }

            public Double read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return Double.valueOf(in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                JsonWriter out = jsonWriter;
                Number value = number;
                if (value == null) {
                    JsonWriter nullValue = out.nullValue();
                    return;
                }
                Gson.checkValidFloatingPoint(value.doubleValue());
                JsonWriter value2 = out.value(value);
            }
        };
        return typeAdapter;
    }

    private TypeAdapter<Number> floatAdapter(boolean serializeSpecialFloatingPointValues2) {
        TypeAdapter<Number> typeAdapter;
        if (serializeSpecialFloatingPointValues2) {
            return TypeAdapters.FLOAT;
        }
        new TypeAdapter<Number>(this) {
            final /* synthetic */ Gson this$0;

            {
                this.this$0 = this$0;
            }

            public Float read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                JsonWriter out = jsonWriter;
                Number value = number;
                if (value == null) {
                    JsonWriter nullValue = out.nullValue();
                    return;
                }
                Gson.checkValidFloatingPoint((double) value.floatValue());
                JsonWriter value2 = out.value(value);
            }
        };
        return typeAdapter;
    }

    static void checkValidFloatingPoint(double d) {
        Throwable th;
        StringBuilder sb;
        double value = d;
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append(value).append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.").toString());
            throw th2;
        }
    }

    private static TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy2) {
        TypeAdapter<Number> typeAdapter;
        if (longSerializationPolicy2 == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }
        new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (in.peek() != JsonToken.NULL) {
                    return Long.valueOf(in.nextLong());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                JsonWriter out = jsonWriter;
                Number value = number;
                if (value == null) {
                    JsonWriter nullValue = out.nullValue();
                } else {
                    JsonWriter value2 = out.value(value.toString());
                }
            }
        };
        return typeAdapter;
    }

    private static TypeAdapter<AtomicLong> atomicLongAdapter(TypeAdapter<Number> longAdapter) {
        C15624 r4;
        final TypeAdapter<Number> typeAdapter = longAdapter;
        new TypeAdapter<AtomicLong>() {
            public void write(JsonWriter out, AtomicLong value) throws IOException {
                typeAdapter.write(out, Long.valueOf(value.get()));
            }

            public AtomicLong read(JsonReader in) throws IOException {
                AtomicLong atomicLong;
                new AtomicLong(((Number) typeAdapter.read(in)).longValue());
                return atomicLong;
            }
        };
        return r4.nullSafe();
    }

    private static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(TypeAdapter<Number> longAdapter) {
        C15635 r4;
        final TypeAdapter<Number> typeAdapter = longAdapter;
        new TypeAdapter<AtomicLongArray>() {
            public void write(JsonWriter jsonWriter, AtomicLongArray atomicLongArray) throws IOException {
                JsonWriter out = jsonWriter;
                AtomicLongArray value = atomicLongArray;
                JsonWriter beginArray = out.beginArray();
                int length = value.length();
                for (int i = 0; i < length; i++) {
                    typeAdapter.write(out, Long.valueOf(value.get(i)));
                }
                JsonWriter endArray = out.endArray();
            }

            public AtomicLongArray read(JsonReader jsonReader) throws IOException {
                List<Long> list;
                AtomicLongArray atomicLongArray;
                JsonReader in = jsonReader;
                new ArrayList<>();
                List<Long> list2 = list;
                in.beginArray();
                while (in.hasNext()) {
                    boolean add = list2.add(Long.valueOf(((Number) typeAdapter.read(in)).longValue()));
                }
                in.endArray();
                int length = list2.size();
                new AtomicLongArray(length);
                AtomicLongArray array = atomicLongArray;
                for (int i = 0; i < length; i++) {
                    array.set(i, list2.get(i).longValue());
                }
                return array;
            }
        };
        return r4.nullSafe();
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v39, resolved type: com.google.gson.Gson$FutureTypeAdapter} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.google.gson.TypeAdapter<T> getAdapter(com.google.gson.reflect.TypeToken<T> r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r12 = r0
            java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>> r12 = r12.typeTokenCache
            r13 = r1
            if (r13 != 0) goto L_0x0019
            com.google.gson.reflect.TypeToken<?> r13 = NULL_KEY_SURROGATE
        L_0x000c:
            java.lang.Object r12 = r12.get(r13)
            com.google.gson.TypeAdapter r12 = (com.google.gson.TypeAdapter) r12
            r2 = r12
            r12 = r2
            if (r12 == 0) goto L_0x001b
            r12 = r2
            r0 = r12
        L_0x0018:
            return r0
        L_0x0019:
            r13 = r1
            goto L_0x000c
        L_0x001b:
            r12 = r0
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.Gson$FutureTypeAdapter<?>>> r12 = r12.calls
            java.lang.Object r12 = r12.get()
            java.util.Map r12 = (java.util.Map) r12
            r3 = r12
            r12 = 0
            r4 = r12
            r12 = r3
            if (r12 != 0) goto L_0x003f
            java.util.HashMap r12 = new java.util.HashMap
            r16 = r12
            r12 = r16
            r13 = r16
            r13.<init>()
            r3 = r12
            r12 = r0
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.Gson$FutureTypeAdapter<?>>> r12 = r12.calls
            r13 = r3
            r12.set(r13)
            r12 = 1
            r4 = r12
        L_0x003f:
            r12 = r3
            r13 = r1
            java.lang.Object r12 = r12.get(r13)
            com.google.gson.Gson$FutureTypeAdapter r12 = (com.google.gson.Gson.FutureTypeAdapter) r12
            r5 = r12
            r12 = r5
            if (r12 == 0) goto L_0x004e
            r12 = r5
            r0 = r12
            goto L_0x0018
        L_0x004e:
            com.google.gson.Gson$FutureTypeAdapter r12 = new com.google.gson.Gson$FutureTypeAdapter     // Catch:{ all -> 0x00ce }
            r16 = r12
            r12 = r16
            r13 = r16
            r13.<init>()     // Catch:{ all -> 0x00ce }
            r6 = r12
            r12 = r3
            r13 = r1
            r14 = r6
            java.lang.Object r12 = r12.put(r13, r14)     // Catch:{ all -> 0x00ce }
            r12 = r0
            java.util.List<com.google.gson.TypeAdapterFactory> r12 = r12.factories     // Catch:{ all -> 0x00ce }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ all -> 0x00ce }
            r7 = r12
        L_0x0069:
            r12 = r7
            boolean r12 = r12.hasNext()     // Catch:{ all -> 0x00ce }
            if (r12 == 0) goto L_0x00a7
            r12 = r7
            java.lang.Object r12 = r12.next()     // Catch:{ all -> 0x00ce }
            com.google.gson.TypeAdapterFactory r12 = (com.google.gson.TypeAdapterFactory) r12     // Catch:{ all -> 0x00ce }
            r8 = r12
            r12 = r8
            r13 = r0
            r14 = r1
            com.google.gson.TypeAdapter r12 = r12.create(r13, r14)     // Catch:{ all -> 0x00ce }
            r9 = r12
            r12 = r9
            if (r12 == 0) goto L_0x00a6
            r12 = r6
            r13 = r9
            r12.setDelegate(r13)     // Catch:{ all -> 0x00ce }
            r12 = r0
            java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>> r12 = r12.typeTokenCache     // Catch:{ all -> 0x00ce }
            r13 = r1
            r14 = r9
            java.lang.Object r12 = r12.put(r13, r14)     // Catch:{ all -> 0x00ce }
            r12 = r9
            r10 = r12
            r12 = r3
            r13 = r1
            java.lang.Object r12 = r12.remove(r13)
            r12 = r4
            if (r12 == 0) goto L_0x00a2
            r12 = r0
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.Gson$FutureTypeAdapter<?>>> r12 = r12.calls
            r12.remove()
        L_0x00a2:
            r12 = r10
            r0 = r12
            goto L_0x0018
        L_0x00a6:
            goto L_0x0069
        L_0x00a7:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00ce }
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ce }
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()     // Catch:{ all -> 0x00ce }
            java.lang.String r15 = "GSON (2.8.5) cannot handle "
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x00ce }
            r15 = r1
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x00ce }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x00ce }
            r13.<init>(r14)     // Catch:{ all -> 0x00ce }
            throw r12     // Catch:{ all -> 0x00ce }
        L_0x00ce:
            r12 = move-exception
            r11 = r12
            r12 = r3
            r13 = r1
            java.lang.Object r12 = r12.remove(r13)
            r12 = r4
            if (r12 == 0) goto L_0x00df
            r12 = r0
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.Gson$FutureTypeAdapter<?>>> r12 = r12.calls
            r12.remove()
        L_0x00df:
            r12 = r11
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.getAdapter(com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter");
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken) {
        Throwable th;
        StringBuilder sb;
        TypeAdapterFactory skipPast = typeAdapterFactory;
        TypeToken<T> type = typeToken;
        if (!this.factories.contains(skipPast)) {
            skipPast = this.jsonAdapterFactory;
        }
        boolean skipPastFound = false;
        for (TypeAdapterFactory factory : this.factories) {
            if (skipPastFound) {
                TypeAdapter<T> candidate = factory.create(this, type);
                if (candidate != null) {
                    return candidate;
                }
            } else if (factory == skipPast) {
                skipPastFound = true;
            }
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("GSON cannot serialize ").append(type).toString());
        throw th2;
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> type) {
        return getAdapter(TypeToken.get(type));
    }

    public JsonElement toJsonTree(Object obj) {
        Object src = obj;
        if (src == null) {
            return JsonNull.INSTANCE;
        }
        return toJsonTree(src, src.getClass());
    }

    public JsonElement toJsonTree(Object src, Type typeOfSrc) {
        JsonTreeWriter jsonTreeWriter;
        new JsonTreeWriter();
        JsonTreeWriter writer = jsonTreeWriter;
        toJson(src, typeOfSrc, (JsonWriter) writer);
        return writer.get();
    }

    public String toJson(Object obj) {
        Object src = obj;
        if (src == null) {
            return toJson((JsonElement) JsonNull.INSTANCE);
        }
        return toJson(src, (Type) src.getClass());
    }

    public String toJson(Object src, Type typeOfSrc) {
        StringWriter stringWriter;
        new StringWriter();
        StringWriter writer = stringWriter;
        toJson(src, typeOfSrc, (Appendable) writer);
        return writer.toString();
    }

    public void toJson(Object obj, Appendable appendable) throws JsonIOException {
        Object src = obj;
        Appendable writer = appendable;
        if (src != null) {
            toJson(src, (Type) src.getClass(), writer);
        } else {
            toJson((JsonElement) JsonNull.INSTANCE, writer);
        }
    }

    public void toJson(Object obj, Type type, Appendable writer) throws JsonIOException {
        Throwable th;
        Object src = obj;
        Type typeOfSrc = type;
        try {
            toJson(src, typeOfSrc, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new JsonIOException((Throwable) e2);
            throw th2;
        }
    }

    public void toJson(Object src, Type typeOfSrc, JsonWriter jsonWriter) throws JsonIOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        JsonWriter writer = jsonWriter;
        TypeAdapter<?> adapter = getAdapter(TypeToken.get(typeOfSrc));
        boolean oldLenient = writer.isLenient();
        writer.setLenient(true);
        boolean oldHtmlSafe = writer.isHtmlSafe();
        writer.setHtmlSafe(this.htmlSafe);
        boolean oldSerializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(this.serializeNulls);
        try {
            adapter.write(writer, src);
            writer.setLenient(oldLenient);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th3 = th2;
            new JsonIOException((Throwable) e2);
            throw th3;
        } catch (AssertionError e3) {
            AssertionError e4 = e3;
            Throwable th4 = th;
            new StringBuilder();
            new AssertionError(sb.append("AssertionError (GSON 2.8.5): ").append(e4.getMessage()).toString(), e4);
            throw th4;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            writer.setLenient(oldLenient);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
            throw th6;
        }
    }

    public String toJson(JsonElement jsonElement) {
        StringWriter stringWriter;
        new StringWriter();
        StringWriter writer = stringWriter;
        toJson(jsonElement, (Appendable) writer);
        return writer.toString();
    }

    public void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
        Throwable th;
        JsonElement jsonElement2 = jsonElement;
        try {
            toJson(jsonElement2, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new JsonIOException((Throwable) e2);
            throw th2;
        }
    }

    public JsonWriter newJsonWriter(Writer writer) throws IOException {
        JsonWriter jsonWriter;
        Writer writer2 = writer;
        if (this.generateNonExecutableJson) {
            writer2.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        new JsonWriter(writer2);
        JsonWriter jsonWriter2 = jsonWriter;
        if (this.prettyPrinting) {
            jsonWriter2.setIndent("  ");
        }
        jsonWriter2.setSerializeNulls(this.serializeNulls);
        return jsonWriter2;
    }

    public JsonReader newJsonReader(Reader reader) {
        JsonReader jsonReader;
        new JsonReader(reader);
        JsonReader jsonReader2 = jsonReader;
        jsonReader2.setLenient(this.lenient);
        return jsonReader2;
    }

    public void toJson(JsonElement jsonElement, JsonWriter jsonWriter) throws JsonIOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        JsonWriter writer = jsonWriter;
        boolean oldLenient = writer.isLenient();
        writer.setLenient(true);
        boolean oldHtmlSafe = writer.isHtmlSafe();
        writer.setHtmlSafe(this.htmlSafe);
        boolean oldSerializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(this.serializeNulls);
        try {
            Streams.write(jsonElement, writer);
            writer.setLenient(oldLenient);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th3 = th2;
            new JsonIOException((Throwable) e2);
            throw th3;
        } catch (AssertionError e3) {
            AssertionError e4 = e3;
            Throwable th4 = th;
            new StringBuilder();
            new AssertionError(sb.append("AssertionError (GSON 2.8.5): ").append(e4.getMessage()).toString(), e4);
            throw th4;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            writer.setLenient(oldLenient);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
            throw th6;
        }
    }

    public <T> T fromJson(String json, Class<T> cls) throws JsonSyntaxException {
        Class<T> classOfT = cls;
        return Primitives.wrap(classOfT).cast(fromJson(json, (Type) classOfT));
    }

    public <T> T fromJson(String str, Type type) throws JsonSyntaxException {
        Reader reader;
        String json = str;
        Type typeOfT = type;
        if (json == null) {
            return null;
        }
        new StringReader(json);
        return fromJson(reader, typeOfT);
    }

    public <T> T fromJson(Reader json, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        Class<T> classOfT = cls;
        JsonReader jsonReader = newJsonReader(json);
        Object object = fromJson(jsonReader, (Type) classOfT);
        assertFullConsumption(object, jsonReader);
        return Primitives.wrap(classOfT).cast(object);
    }

    public <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        JsonReader jsonReader = newJsonReader(json);
        Gson object = fromJson(jsonReader, typeOfT);
        assertFullConsumption(object, jsonReader);
        return object;
    }

    private static void assertFullConsumption(Object obj, JsonReader jsonReader) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        JsonReader reader = jsonReader;
        if (obj != null) {
            try {
                if (reader.peek() != JsonToken.END_DOCUMENT) {
                    Throwable th4 = th3;
                    new JsonIOException("JSON document was not fully consumed.");
                    throw th4;
                }
            } catch (MalformedJsonException e) {
                MalformedJsonException e2 = e;
                Throwable th5 = th2;
                new JsonSyntaxException((Throwable) e2);
                throw th5;
            } catch (IOException e3) {
                IOException e4 = e3;
                Throwable th6 = th;
                new JsonIOException((Throwable) e4);
                throw th6;
            }
        }
    }

    public <T> T fromJson(JsonReader jsonReader, Type type) throws JsonIOException, JsonSyntaxException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        JsonReader reader = jsonReader;
        Type typeOfT = type;
        boolean isEmpty = true;
        boolean oldLenient = reader.isLenient();
        reader.setLenient(true);
        try {
            JsonToken peek = reader.peek();
            isEmpty = false;
            Gson read = getAdapter(TypeToken.get(typeOfT)).read(reader);
            reader.setLenient(oldLenient);
            return read;
        } catch (EOFException e) {
            EOFException e2 = e;
            if (isEmpty) {
                reader.setLenient(oldLenient);
                return null;
            }
            Throwable th5 = th4;
            new JsonSyntaxException((Throwable) e2);
            throw th5;
        } catch (IllegalStateException e3) {
            IllegalStateException e4 = e3;
            Throwable th6 = th3;
            new JsonSyntaxException((Throwable) e4);
            throw th6;
        } catch (IOException e5) {
            IOException e6 = e5;
            Throwable th7 = th2;
            new JsonSyntaxException((Throwable) e6);
            throw th7;
        } catch (AssertionError e7) {
            AssertionError e8 = e7;
            Throwable th8 = th;
            new StringBuilder();
            new AssertionError(sb.append("AssertionError (GSON 2.8.5): ").append(e8.getMessage()).toString(), e8);
            throw th8;
        } catch (Throwable th9) {
            Throwable th10 = th9;
            reader.setLenient(oldLenient);
            throw th10;
        }
    }

    public <T> T fromJson(JsonElement json, Class<T> cls) throws JsonSyntaxException {
        Class<T> classOfT = cls;
        return Primitives.wrap(classOfT).cast(fromJson(json, (Type) classOfT));
    }

    public <T> T fromJson(JsonElement jsonElement, Type type) throws JsonSyntaxException {
        JsonReader jsonReader;
        JsonElement json = jsonElement;
        Type typeOfT = type;
        if (json == null) {
            return null;
        }
        new JsonTreeReader(json);
        return fromJson(jsonReader, typeOfT);
    }

    static class FutureTypeAdapter<T> extends TypeAdapter<T> {
        private TypeAdapter<T> delegate;

        FutureTypeAdapter() {
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            Throwable th;
            TypeAdapter<T> typeAdapter2 = typeAdapter;
            if (this.delegate != null) {
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }
            this.delegate = typeAdapter2;
        }

        public T read(JsonReader jsonReader) throws IOException {
            Throwable th;
            JsonReader in = jsonReader;
            if (this.delegate != null) {
                return this.delegate.read(in);
            }
            Throwable th2 = th;
            new IllegalStateException();
            throw th2;
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            Throwable th;
            JsonWriter out = jsonWriter;
            T value = t;
            if (this.delegate == null) {
                Throwable th2 = th;
                new IllegalStateException();
                throw th2;
            }
            this.delegate.write(out, value);
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder("{serializeNulls:");
        return sb.append(this.serializeNulls).append(",factories:").append(this.factories).append(",instanceCreators:").append(this.constructorConstructor).append("}").toString();
    }
}
