package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Gson gson2 = gson;
        TypeToken<T> targetType = typeToken;
        JsonAdapter annotation = (JsonAdapter) targetType.getRawType().getAnnotation(JsonAdapter.class);
        if (annotation == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson2, targetType, annotation);
    }

    /* access modifiers changed from: package-private */
    public TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor2, Gson gson, TypeToken<?> typeToken, JsonAdapter jsonAdapter) {
        TypeAdapter<?> typeAdapter;
        TypeAdapter<?> typeAdapter2;
        Throwable th;
        StringBuilder sb;
        Gson gson2 = gson;
        TypeToken<?> type = typeToken;
        JsonAdapter annotation = jsonAdapter;
        Object instance = constructorConstructor2.get(TypeToken.get(annotation.value())).construct();
        if (instance instanceof TypeAdapter) {
            typeAdapter2 = (TypeAdapter) instance;
        } else if (instance instanceof TypeAdapterFactory) {
            typeAdapter2 = ((TypeAdapterFactory) instance).create(gson2, type);
        } else if ((instance instanceof JsonSerializer) || (instance instanceof JsonDeserializer)) {
            new TreeTypeAdapter(instance instanceof JsonSerializer ? (JsonSerializer) instance : null, instance instanceof JsonDeserializer ? (JsonDeserializer) instance : null, gson2, type, (TypeAdapterFactory) null);
            typeAdapter2 = typeAdapter;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid attempt to bind an instance of ").append(instance.getClass().getName()).append(" as a @JsonAdapter for ").append(type.toString()).append(". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.").toString());
            throw th2;
        }
        if (typeAdapter2 != null && annotation.nullSafe()) {
            typeAdapter2 = typeAdapter2.nullSafe();
        }
        return typeAdapter2;
    }
}
