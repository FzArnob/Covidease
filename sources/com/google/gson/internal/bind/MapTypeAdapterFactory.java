package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;

    public MapTypeAdapterFactory(ConstructorConstructor constructorConstructor2, boolean complexMapKeySerialization2) {
        this.constructorConstructor = constructorConstructor2;
        this.complexMapKeySerialization = complexMapKeySerialization2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        TypeAdapter<T> result;
        Gson gson2 = gson;
        TypeToken<T> typeToken2 = typeToken;
        Type type = typeToken2.getType();
        if (!Map.class.isAssignableFrom(typeToken2.getRawType())) {
            return null;
        }
        Type[] keyAndValueTypes = C$Gson$Types.getMapKeyAndValueTypes(type, C$Gson$Types.getRawType(type));
        new Adapter(this, gson2, keyAndValueTypes[0], getKeyAdapter(gson2, keyAndValueTypes[0]), keyAndValueTypes[1], gson2.getAdapter(TypeToken.get(keyAndValueTypes[1])), this.constructorConstructor.get(typeToken2));
        return result;
    }

    private TypeAdapter<?> getKeyAdapter(Gson gson, Type type) {
        TypeAdapter<Boolean> typeAdapter;
        Gson context = gson;
        Type keyType = type;
        if (keyType == Boolean.TYPE || keyType == Boolean.class) {
            typeAdapter = TypeAdapters.BOOLEAN_AS_STRING;
        } else {
            typeAdapter = context.getAdapter(TypeToken.get(keyType));
        }
        return typeAdapter;
    }

    private final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {
        private final ObjectConstructor<? extends Map<K, V>> constructor;
        private final TypeAdapter<K> keyTypeAdapter;
        final /* synthetic */ MapTypeAdapterFactory this$0;
        private final TypeAdapter<V> valueTypeAdapter;

        public Adapter(MapTypeAdapterFactory mapTypeAdapterFactory, Gson gson, Type keyType, TypeAdapter<K> keyTypeAdapter2, Type valueType, TypeAdapter<V> valueTypeAdapter2, ObjectConstructor<? extends Map<K, V>> constructor2) {
            TypeAdapter<K> typeAdapter;
            TypeAdapter<V> typeAdapter2;
            Gson context = gson;
            this.this$0 = mapTypeAdapterFactory;
            new TypeAdapterRuntimeTypeWrapper(context, keyTypeAdapter2, keyType);
            this.keyTypeAdapter = typeAdapter;
            new TypeAdapterRuntimeTypeWrapper(context, valueTypeAdapter2, valueType);
            this.valueTypeAdapter = typeAdapter2;
            this.constructor = constructor2;
        }

        public Map<K, V> read(JsonReader jsonReader) throws IOException {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            StringBuilder sb2;
            JsonReader in = jsonReader;
            JsonToken peek = in.peek();
            if (peek == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            Map<K, V> map = (Map) this.constructor.construct();
            if (peek == JsonToken.BEGIN_ARRAY) {
                in.beginArray();
                while (in.hasNext()) {
                    in.beginArray();
                    K key = this.keyTypeAdapter.read(in);
                    if (map.put(key, this.valueTypeAdapter.read(in)) != null) {
                        Throwable th3 = th2;
                        new StringBuilder();
                        new JsonSyntaxException(sb2.append("duplicate key: ").append(key).toString());
                        throw th3;
                    }
                    in.endArray();
                }
                in.endArray();
            } else {
                in.beginObject();
                while (in.hasNext()) {
                    JsonReaderInternalAccess.INSTANCE.promoteNameToValue(in);
                    K key2 = this.keyTypeAdapter.read(in);
                    if (map.put(key2, this.valueTypeAdapter.read(in)) != null) {
                        Throwable th4 = th;
                        new StringBuilder();
                        new JsonSyntaxException(sb.append("duplicate key: ").append(key2).toString());
                        throw th4;
                    }
                }
                in.endObject();
            }
            return map;
        }

        public void write(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
            List<JsonElement> list;
            List<V> list2;
            JsonWriter out = jsonWriter;
            Map<K, V> map2 = map;
            if (map2 == null) {
                JsonWriter nullValue = out.nullValue();
            } else if (!this.this$0.complexMapKeySerialization) {
                JsonWriter beginObject = out.beginObject();
                for (Map.Entry<K, V> entry : map2.entrySet()) {
                    JsonWriter name = out.name(String.valueOf(entry.getKey()));
                    this.valueTypeAdapter.write(out, entry.getValue());
                }
                JsonWriter endObject = out.endObject();
            } else {
                boolean hasComplexKeys = false;
                new ArrayList<>(map2.size());
                List<JsonElement> keys = list;
                new ArrayList<>(map2.size());
                List<V> values = list2;
                for (Map.Entry<K, V> entry2 : map2.entrySet()) {
                    JsonElement keyElement = this.keyTypeAdapter.toJsonTree(entry2.getKey());
                    boolean add = keys.add(keyElement);
                    boolean add2 = values.add(entry2.getValue());
                    hasComplexKeys |= keyElement.isJsonArray() || keyElement.isJsonObject();
                }
                if (hasComplexKeys) {
                    JsonWriter beginArray = out.beginArray();
                    int size = keys.size();
                    for (int i = 0; i < size; i++) {
                        JsonWriter beginArray2 = out.beginArray();
                        Streams.write(keys.get(i), out);
                        this.valueTypeAdapter.write(out, values.get(i));
                        JsonWriter endArray = out.endArray();
                    }
                    JsonWriter endArray2 = out.endArray();
                    return;
                }
                JsonWriter beginObject2 = out.beginObject();
                int size2 = keys.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    JsonWriter name2 = out.name(keyToString(keys.get(i2)));
                    this.valueTypeAdapter.write(out, values.get(i2));
                }
                JsonWriter endObject2 = out.endObject();
            }
        }

        private String keyToString(JsonElement jsonElement) {
            Throwable th;
            Throwable th2;
            JsonElement keyElement = jsonElement;
            if (keyElement.isJsonPrimitive()) {
                JsonPrimitive primitive = keyElement.getAsJsonPrimitive();
                if (primitive.isNumber()) {
                    return String.valueOf(primitive.getAsNumber());
                }
                if (primitive.isBoolean()) {
                    return Boolean.toString(primitive.getAsBoolean());
                }
                if (primitive.isString()) {
                    return primitive.getAsString();
                }
                Throwable th3 = th2;
                new AssertionError();
                throw th3;
            } else if (keyElement.isJsonNull()) {
                return "null";
            } else {
                Throwable th4 = th;
                new AssertionError();
                throw th4;
            }
        }
    }
}
