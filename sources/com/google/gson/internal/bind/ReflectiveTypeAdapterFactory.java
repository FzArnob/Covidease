package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor2, FieldNamingStrategy fieldNamingPolicy2, Excluder excluder2, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory2) {
        this.constructorConstructor = constructorConstructor2;
        this.fieldNamingPolicy = fieldNamingPolicy2;
        this.excluder = excluder2;
        this.jsonAdapterFactory = jsonAdapterFactory2;
    }

    public boolean excludeField(Field f, boolean serialize) {
        return excludeField(f, serialize, this.excluder);
    }

    static boolean excludeField(Field field, boolean z, Excluder excluder2) {
        Field f = field;
        boolean serialize = z;
        Excluder excluder3 = excluder2;
        return !excluder3.excludeClass(f.getType(), serialize) && !excluder3.excludeField(f, serialize);
    }

    private List<String> getFieldNames(Field field) {
        List<String> list;
        Field f = field;
        SerializedName annotation = (SerializedName) f.getAnnotation(SerializedName.class);
        if (annotation == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(f));
        }
        String serializedName = annotation.value();
        String[] alternates = annotation.alternate();
        if (alternates.length == 0) {
            return Collections.singletonList(serializedName);
        }
        new ArrayList(alternates.length + 1);
        List<String> fieldNames = list;
        boolean add = fieldNames.add(serializedName);
        String[] strArr = alternates;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            boolean add2 = fieldNames.add(strArr[i]);
        }
        return fieldNames;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        TypeAdapter<T> typeAdapter;
        Gson gson2 = gson;
        TypeToken<T> type = typeToken;
        Class<? super T> raw = type.getRawType();
        if (!Object.class.isAssignableFrom(raw)) {
            return null;
        }
        new Adapter(this.constructorConstructor.get(type), getBoundFields(gson2, type, raw));
        return typeAdapter;
    }

    private BoundField createBoundField(Gson gson, Field field, String str, TypeToken<?> typeToken, boolean z, boolean z2) {
        BoundField boundField;
        Gson context = gson;
        Field field2 = field;
        String name = str;
        TypeToken<?> fieldType = typeToken;
        boolean serialize = z;
        boolean deserialize = z2;
        boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        JsonAdapter annotation = (JsonAdapter) field2.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> mapped = null;
        if (annotation != null) {
            mapped = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, context, fieldType, annotation);
        }
        boolean jsonAdapterPresent = mapped != null;
        if (mapped == null) {
            mapped = context.getAdapter(fieldType);
        }
        final Field field3 = field2;
        final boolean z3 = jsonAdapterPresent;
        final TypeAdapter<?> typeAdapter = mapped;
        final Gson gson2 = context;
        final TypeToken<?> typeToken2 = fieldType;
        final boolean z4 = isPrimitive;
        new BoundField(this, name, serialize, deserialize) {
            final /* synthetic */ ReflectiveTypeAdapterFactory this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: package-private */
            public void write(JsonWriter jsonWriter, Object value) throws IOException, IllegalAccessException {
                TypeAdapter typeAdapter;
                TypeAdapter t;
                JsonWriter writer = jsonWriter;
                Object fieldValue = field3.get(value);
                if (z3) {
                    t = typeAdapter;
                } else {
                    t = typeAdapter;
                    new TypeAdapterRuntimeTypeWrapper(gson2, typeAdapter, typeToken2.getType());
                }
                t.write(writer, fieldValue);
            }

            /* access modifiers changed from: package-private */
            public void read(JsonReader reader, Object obj) throws IOException, IllegalAccessException {
                Object value = obj;
                Object fieldValue = typeAdapter.read(reader);
                if (fieldValue != null || !z4) {
                    field3.set(value, fieldValue);
                }
            }

            public boolean writeField(Object obj) throws IOException, IllegalAccessException {
                Object value = obj;
                if (!this.serialized) {
                    return false;
                }
                return field3.get(value) != value;
            }
        };
        return boundField;
    }

    private Map<String, BoundField> getBoundFields(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        Map<String, BoundField> map;
        Throwable th;
        StringBuilder sb;
        Gson context = gson;
        TypeToken<?> type = typeToken;
        Class<?> raw = cls;
        new LinkedHashMap();
        Map<String, BoundField> result = map;
        if (raw.isInterface()) {
            return result;
        }
        Type declaredType = type.getType();
        while (raw != Object.class) {
            Field[] declaredFields = raw.getDeclaredFields();
            int length = declaredFields.length;
            for (int i = 0; i < length; i++) {
                Field field = declaredFields[i];
                boolean serialize = excludeField(field, true);
                boolean deserialize = excludeField(field, false);
                if (serialize || deserialize) {
                    this.accessor.makeAccessible(field);
                    Type fieldType = C$Gson$Types.resolve(type.getType(), raw, field.getGenericType());
                    List<String> fieldNames = getFieldNames(field);
                    BoundField previous = null;
                    int size = fieldNames.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        String name = fieldNames.get(i2);
                        if (i2 != 0) {
                            serialize = false;
                        }
                        BoundField replaced = result.put(name, createBoundField(context, field, name, TypeToken.get(fieldType), serialize, deserialize));
                        if (previous == null) {
                            previous = replaced;
                        }
                    }
                    if (previous != null) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append(declaredType).append(" declares multiple JSON fields named ").append(previous.name).toString());
                        throw th2;
                    }
                }
            }
            type = TypeToken.get(C$Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
            raw = type.getRawType();
        }
        return result;
    }

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        /* access modifiers changed from: package-private */
        public abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: package-private */
        public abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: package-private */
        public abstract boolean writeField(Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String name2, boolean serialized2, boolean deserialized2) {
            this.name = name2;
            this.serialized = serialized2;
            this.deserialized = deserialized2;
        }
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        Adapter(ObjectConstructor<T> constructor2, Map<String, BoundField> boundFields2) {
            this.constructor = constructor2;
            this.boundFields = boundFields2;
        }

        public T read(JsonReader jsonReader) throws IOException {
            Throwable th;
            Throwable th2;
            JsonReader in = jsonReader;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            Adapter<T> instance = this.constructor.construct();
            try {
                in.beginObject();
                while (in.hasNext()) {
                    BoundField field = this.boundFields.get(in.nextName());
                    if (field == null || !field.deserialized) {
                        in.skipValue();
                    } else {
                        field.read(in, instance);
                    }
                }
                in.endObject();
                return instance;
            } catch (IllegalStateException e) {
                IllegalStateException e2 = e;
                Throwable th3 = th2;
                new JsonSyntaxException((Throwable) e2);
                throw th3;
            } catch (IllegalAccessException e3) {
                IllegalAccessException e4 = e3;
                Throwable th4 = th;
                new AssertionError(e4);
                throw th4;
            }
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            Throwable th;
            JsonWriter out = jsonWriter;
            T value = t;
            if (value == null) {
                JsonWriter nullValue = out.nullValue();
                return;
            }
            JsonWriter beginObject = out.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.writeField(value)) {
                        JsonWriter name = out.name(boundField.name);
                        boundField.write(out, value);
                    }
                }
                JsonWriter endObject = out.endObject();
            } catch (IllegalAccessException e) {
                IllegalAccessException e2 = e;
                Throwable th2 = th;
                new AssertionError(e2);
                throw th2;
            }
        }
    }
}
