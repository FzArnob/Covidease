package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class TimeTypeAdapter extends TypeAdapter<Time> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat format;

    public TimeTypeAdapter() {
        DateFormat dateFormat;
        new SimpleDateFormat("hh:mm:ss a");
        this.format = dateFormat;
    }

    static {
        TypeAdapterFactory typeAdapterFactory;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                TypeAdapter<T> typeAdapter;
                TypeAdapter<T> typeAdapter2;
                Gson gson2 = gson;
                if (typeToken.getRawType() == Time.class) {
                    typeAdapter = typeAdapter2;
                    new TimeTypeAdapter();
                } else {
                    typeAdapter = null;
                }
                return typeAdapter;
            }
        };
        FACTORY = typeAdapterFactory;
    }

    public synchronized Time read(JsonReader jsonReader) throws IOException {
        Throwable th;
        Time time;
        Time time2;
        JsonReader in = jsonReader;
        synchronized (this) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                time2 = null;
            } else {
                try {
                    Time time3 = time;
                    new Time(this.format.parse(in.nextString()).getTime());
                    time2 = time3;
                } catch (ParseException e) {
                    ParseException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }
        }
        return time2;
    }

    public synchronized void write(JsonWriter jsonWriter, Time time) throws IOException {
        JsonWriter out = jsonWriter;
        Time value = time;
        synchronized (this) {
            JsonWriter value2 = out.value(value == null ? null : this.format.format(value));
        }
    }
}
