package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public CollectionTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        TypeAdapter<T> result;
        Gson gson2 = gson;
        TypeToken<T> typeToken2 = typeToken;
        Type type = typeToken2.getType();
        Class<? super T> rawType = typeToken2.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type elementType = C$Gson$Types.getCollectionElementType(type, rawType);
        new Adapter(gson2, elementType, gson2.getAdapter(TypeToken.get(elementType)), this.constructorConstructor.get(typeToken2));
        return result;
    }

    private static final class Adapter<E> extends TypeAdapter<Collection<E>> {
        private final ObjectConstructor<? extends Collection<E>> constructor;
        private final TypeAdapter<E> elementTypeAdapter;

        public Adapter(Gson context, Type elementType, TypeAdapter<E> elementTypeAdapter2, ObjectConstructor<? extends Collection<E>> constructor2) {
            TypeAdapter<E> typeAdapter;
            new TypeAdapterRuntimeTypeWrapper(context, elementTypeAdapter2, elementType);
            this.elementTypeAdapter = typeAdapter;
            this.constructor = constructor2;
        }

        public Collection<E> read(JsonReader jsonReader) throws IOException {
            JsonReader in = jsonReader;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            Collection<E> collection = (Collection) this.constructor.construct();
            in.beginArray();
            while (in.hasNext()) {
                boolean add = collection.add(this.elementTypeAdapter.read(in));
            }
            in.endArray();
            return collection;
        }

        public void write(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
            JsonWriter out = jsonWriter;
            Collection<E> collection2 = collection;
            if (collection2 == null) {
                JsonWriter nullValue = out.nullValue();
                return;
            }
            JsonWriter beginArray = out.beginArray();
            for (E element : collection2) {
                this.elementTypeAdapter.write(out, element);
            }
            JsonWriter endArray = out.endArray();
        }
    }
}
