package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public final class TreeTypeAdapter<T> extends TypeAdapter<T> {
    private final TreeTypeAdapter<T>.GsonContextImpl context;
    private TypeAdapter<T> delegate;
    private final JsonDeserializer<T> deserializer;
    final Gson gson;
    private final JsonSerializer<T> serializer;
    private final TypeAdapterFactory skipPast;
    private final TypeToken<T> typeToken;

    public TreeTypeAdapter(JsonSerializer<T> serializer2, JsonDeserializer<T> deserializer2, Gson gson2, TypeToken<T> typeToken2, TypeAdapterFactory skipPast2) {
        TreeTypeAdapter<T>.GsonContextImpl gsonContextImpl;
        new GsonContextImpl(this, (C16011) null);
        this.context = gsonContextImpl;
        this.serializer = serializer2;
        this.deserializer = deserializer2;
        this.gson = gson2;
        this.typeToken = typeToken2;
        this.skipPast = skipPast2;
    }

    public T read(JsonReader jsonReader) throws IOException {
        JsonReader in = jsonReader;
        if (this.deserializer == null) {
            return delegate().read(in);
        }
        JsonElement value = Streams.parse(in);
        if (value.isJsonNull()) {
            return null;
        }
        return this.deserializer.deserialize(value, this.typeToken.getType(), this.context);
    }

    public void write(JsonWriter jsonWriter, T t) throws IOException {
        JsonWriter out = jsonWriter;
        T value = t;
        if (this.serializer == null) {
            delegate().write(out, value);
        } else if (value == null) {
            JsonWriter nullValue = out.nullValue();
        } else {
            Streams.write(this.serializer.serialize(value, this.typeToken.getType(), this.context), out);
        }
    }

    private TypeAdapter<T> delegate() {
        TypeAdapter<T> typeAdapter;
        TypeAdapter<T> d = this.delegate;
        if (d != null) {
            typeAdapter = d;
        } else {
            TypeAdapter<T> delegateAdapter = this.gson.getDelegateAdapter(this.skipPast, this.typeToken);
            typeAdapter = delegateAdapter;
            this.delegate = delegateAdapter;
        }
        return typeAdapter;
    }

    public static TypeAdapterFactory newFactory(TypeToken<?> exactType, Object typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        new SingleTypeFactory(typeAdapter, exactType, false, (Class<?>) null);
        return typeAdapterFactory;
    }

    public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken<?> typeToken2, Object typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        TypeToken<?> exactType = typeToken2;
        new SingleTypeFactory(typeAdapter, exactType, exactType.getType() == exactType.getRawType(), (Class<?>) null);
        return typeAdapterFactory;
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class<?> hierarchyType, Object typeAdapter) {
        TypeAdapterFactory typeAdapterFactory;
        new SingleTypeFactory(typeAdapter, (TypeToken<?>) null, false, hierarchyType);
        return typeAdapterFactory;
    }

    private static final class SingleTypeFactory implements TypeAdapterFactory {
        private final JsonDeserializer<?> deserializer;
        private final TypeToken<?> exactType;
        private final Class<?> hierarchyType;
        private final boolean matchRawType;
        private final JsonSerializer<?> serializer;

        SingleTypeFactory(Object obj, TypeToken<?> typeToken, boolean z, Class<?> cls) {
            Object typeAdapter = obj;
            TypeToken<?> exactType2 = typeToken;
            boolean matchRawType2 = z;
            Class<?> hierarchyType2 = cls;
            this.serializer = typeAdapter instanceof JsonSerializer ? (JsonSerializer) typeAdapter : null;
            this.deserializer = typeAdapter instanceof JsonDeserializer ? (JsonDeserializer) typeAdapter : null;
            C$Gson$Preconditions.checkArgument((this.serializer == null && this.deserializer == null) ? false : true);
            this.exactType = exactType2;
            this.matchRawType = matchRawType2;
            this.hierarchyType = hierarchyType2;
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            boolean matches;
            TypeAdapter<T> typeAdapter;
            TypeAdapter<T> typeAdapter2;
            Gson gson2 = gson;
            TypeToken<T> type = typeToken;
            if (this.exactType != null) {
                matches = this.exactType.equals(type) || (this.matchRawType && this.exactType.getType() == type.getRawType());
            } else {
                matches = this.hierarchyType.isAssignableFrom(type.getRawType());
            }
            if (matches) {
                typeAdapter = typeAdapter2;
                new TreeTypeAdapter(this.serializer, this.deserializer, gson2, type, this);
            } else {
                typeAdapter = null;
            }
            return typeAdapter;
        }
    }

    private final class GsonContextImpl implements JsonSerializationContext, JsonDeserializationContext {
        final /* synthetic */ TreeTypeAdapter this$0;

        private GsonContextImpl(TreeTypeAdapter treeTypeAdapter) {
            this.this$0 = treeTypeAdapter;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ GsonContextImpl(TreeTypeAdapter x0, C16011 r7) {
            this(x0);
            C16011 r2 = r7;
        }

        public JsonElement serialize(Object src) {
            return this.this$0.gson.toJsonTree(src);
        }

        public JsonElement serialize(Object src, Type typeOfSrc) {
            return this.this$0.gson.toJsonTree(src, typeOfSrc);
        }

        public <R> R deserialize(JsonElement json, Type typeOfT) throws JsonParseException {
            return this.this$0.gson.fromJson(json, typeOfT);
        }
    }
}
