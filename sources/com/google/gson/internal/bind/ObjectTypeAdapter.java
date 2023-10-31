package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY;
    private final Gson gson;

    static {
        TypeAdapterFactory typeAdapterFactory;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                TypeAdapter<T> typeAdapter;
                Gson gson2 = gson;
                if (type.getRawType() != Object.class) {
                    return null;
                }
                new ObjectTypeAdapter(gson2);
                return typeAdapter;
            }
        };
        FACTORY = typeAdapterFactory;
    }

    ObjectTypeAdapter(Gson gson2) {
        this.gson = gson2;
    }

    public Object read(JsonReader jsonReader) throws IOException {
        Map<String, Object> map;
        List<Object> list;
        Throwable th;
        JsonReader in = jsonReader;
        switch (in.peek()) {
            case BEGIN_ARRAY:
                new ArrayList<>();
                List<Object> list2 = list;
                in.beginArray();
                while (in.hasNext()) {
                    boolean add = list2.add(read(in));
                }
                in.endArray();
                return list2;
            case BEGIN_OBJECT:
                new LinkedTreeMap<>();
                Map<String, Object> map2 = map;
                in.beginObject();
                while (in.hasNext()) {
                    Object put = map2.put(in.nextName(), read(in));
                }
                in.endObject();
                return map2;
            case STRING:
                return in.nextString();
            case NUMBER:
                return Double.valueOf(in.nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(in.nextBoolean());
            case NULL:
                in.nextNull();
                return null;
            default:
                Throwable th2 = th;
                new IllegalStateException();
                throw th2;
        }
    }

    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        JsonWriter out = jsonWriter;
        Object value = obj;
        if (value == null) {
            JsonWriter nullValue = out.nullValue();
            return;
        }
        TypeAdapter<Object> typeAdapter = this.gson.getAdapter(value.getClass());
        if (typeAdapter instanceof ObjectTypeAdapter) {
            JsonWriter beginObject = out.beginObject();
            JsonWriter endObject = out.endObject();
            return;
        }
        typeAdapter.write(out, value);
    }
}
