package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY;
    private final List<DateFormat> dateFormats;

    static {
        TypeAdapterFactory typeAdapterFactory;
        new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                TypeAdapter<T> typeAdapter;
                TypeAdapter<T> typeAdapter2;
                Gson gson2 = gson;
                if (typeToken.getRawType() == Date.class) {
                    typeAdapter = typeAdapter2;
                    new DateTypeAdapter();
                } else {
                    typeAdapter = null;
                }
                return typeAdapter;
            }
        };
        FACTORY = typeAdapterFactory;
    }

    public DateTypeAdapter() {
        List<DateFormat> list;
        new ArrayList();
        this.dateFormats = list;
        boolean add = this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            boolean add2 = this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (JavaVersion.isJava9OrLater()) {
            boolean add3 = this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(2, 2));
        }
    }

    public Date read(JsonReader jsonReader) throws IOException {
        JsonReader in = jsonReader;
        if (in.peek() != JsonToken.NULL) {
            return deserializeToDate(in.nextString());
        }
        in.nextNull();
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        new java.text.ParsePosition(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r0 = com.google.gson.internal.bind.util.ISO8601Utils.parse(r5, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r5 = r10;
        new com.google.gson.JsonSyntaxException(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0043, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.Date deserializeToDate(java.lang.String r12) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r9 = r11
            monitor-enter(r9)
            r5 = r0
            java.util.List<java.text.DateFormat> r5 = r5.dateFormats     // Catch:{ all -> 0x0044 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0044 }
            r2 = r5
        L_0x000c:
            r5 = r2
            boolean r5 = r5.hasNext()     // Catch:{ all -> 0x0044 }
            if (r5 == 0) goto L_0x0027
            r5 = r2
            java.lang.Object r5 = r5.next()     // Catch:{ all -> 0x0044 }
            java.text.DateFormat r5 = (java.text.DateFormat) r5     // Catch:{ all -> 0x0044 }
            r3 = r5
            r5 = r3
            r6 = r1
            java.util.Date r5 = r5.parse(r6)     // Catch:{ ParseException -> 0x0024 }
            r0 = r5
        L_0x0022:
            monitor-exit(r9)
            return r0
        L_0x0024:
            r5 = move-exception
            r4 = r5
            goto L_0x000c
        L_0x0027:
            r5 = r1
            java.text.ParsePosition r6 = new java.text.ParsePosition     // Catch:{ ParseException -> 0x0037 }
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = 0
            r7.<init>(r8)     // Catch:{ ParseException -> 0x0037 }
            java.util.Date r5 = com.google.gson.internal.bind.util.ISO8601Utils.parse(r5, r6)     // Catch:{ ParseException -> 0x0037 }
            r0 = r5
            goto L_0x0022
        L_0x0037:
            r5 = move-exception
            r2 = r5
            com.google.gson.JsonSyntaxException r5 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0044 }
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r1
            r8 = r2
            r6.<init>(r7, r8)     // Catch:{ all -> 0x0044 }
            throw r5     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.DateTypeAdapter.deserializeToDate(java.lang.String):java.util.Date");
    }

    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        JsonWriter out = jsonWriter;
        Date value = date;
        synchronized (this) {
            if (value == null) {
                JsonWriter nullValue = out.nullValue();
            } else {
                JsonWriter value2 = out.value(this.dateFormats.get(0).format(value));
            }
        }
    }
}
