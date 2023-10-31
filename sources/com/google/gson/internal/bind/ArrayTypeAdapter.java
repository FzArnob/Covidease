package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY;
    private final Class<E> componentType;
    private final TypeAdapter<E> componentTypeAdapter;

    static {
        TypeAdapterFactory typeAdapterFactory;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                TypeAdapter<T> typeAdapter;
                Gson gson2 = gson;
                Type type = typeToken.getType();
                if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
                    return null;
                }
                Type componentType = C$Gson$Types.getArrayComponentType(type);
                new ArrayTypeAdapter(gson2, gson2.getAdapter(TypeToken.get(componentType)), C$Gson$Types.getRawType(componentType));
                return typeAdapter;
            }
        };
        FACTORY = typeAdapterFactory;
    }

    public ArrayTypeAdapter(Gson context, TypeAdapter<E> componentTypeAdapter2, Class<E> cls) {
        TypeAdapter<E> typeAdapter;
        Class<E> componentType2 = cls;
        new TypeAdapterRuntimeTypeWrapper(context, componentTypeAdapter2, componentType2);
        this.componentTypeAdapter = typeAdapter;
        this.componentType = componentType2;
    }

    public Object read(JsonReader jsonReader) throws IOException {
        List<E> list;
        JsonReader in = jsonReader;
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        new ArrayList<>();
        List<E> list2 = list;
        in.beginArray();
        while (in.hasNext()) {
            boolean add = list2.add(this.componentTypeAdapter.read(in));
        }
        in.endArray();
        int size = list2.size();
        Object array = Array.newInstance(this.componentType, size);
        for (int i = 0; i < size; i++) {
            Array.set(array, i, list2.get(i));
        }
        return array;
    }

    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        JsonWriter out = jsonWriter;
        Object array = obj;
        if (array == null) {
            JsonWriter nullValue = out.nullValue();
            return;
        }
        JsonWriter beginArray = out.beginArray();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            this.componentTypeAdapter.write(out, Array.get(array, i));
        }
        JsonWriter endArray = out.endArray();
    }
}
