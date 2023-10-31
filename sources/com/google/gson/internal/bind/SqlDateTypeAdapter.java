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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat format;

    public SqlDateTypeAdapter() {
        DateFormat dateFormat;
        new SimpleDateFormat("MMM d, yyyy");
        this.format = dateFormat;
    }

    static {
        TypeAdapterFactory typeAdapterFactory;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                TypeAdapter<T> typeAdapter;
                TypeAdapter<T> typeAdapter2;
                Gson gson2 = gson;
                if (typeToken.getRawType() == Date.class) {
                    typeAdapter = typeAdapter2;
                    new SqlDateTypeAdapter();
                } else {
                    typeAdapter = null;
                }
                return typeAdapter;
            }
        };
        FACTORY = typeAdapterFactory;
    }

    public synchronized Date read(JsonReader jsonReader) throws IOException {
        Throwable th;
        Date date;
        Date date2;
        JsonReader in = jsonReader;
        synchronized (this) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                date2 = null;
            } else {
                try {
                    Date date3 = date;
                    new Date(this.format.parse(in.nextString()).getTime());
                    date2 = date3;
                } catch (ParseException e) {
                    ParseException e2 = e;
                    Throwable th2 = th;
                    new JsonSyntaxException((Throwable) e2);
                    throw th2;
                }
            }
        }
        return date2;
    }

    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        JsonWriter out = jsonWriter;
        Date value = date;
        synchronized (this) {
            JsonWriter value2 = out.value(value == null ? null : this.format.format(value));
        }
    }
}
