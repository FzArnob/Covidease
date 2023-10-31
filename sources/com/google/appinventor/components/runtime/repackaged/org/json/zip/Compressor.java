package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import com.google.appinventor.components.runtime.repackaged.org.json.JSONArray;
import com.google.appinventor.components.runtime.repackaged.org.json.JSONException;
import com.google.appinventor.components.runtime.repackaged.org.json.JSONObject;
import com.google.appinventor.components.runtime.repackaged.org.json.Kim;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class Compressor extends JSONzip {
    final BitWriter bitwriter;

    public Compressor(BitWriter bitwriter2) {
        this.bitwriter = bitwriter2;
    }

    private static int bcd(char c) {
        char digit = c;
        if (digit >= '0' && digit <= '9') {
            return digit - '0';
        }
        switch (digit) {
            case '+':
                return 12;
            case '-':
                return 11;
            case '.':
                return 10;
            default:
                return 13;
        }
    }

    public void flush() throws JSONException {
        pad(8);
    }

    private void one() throws JSONException {
        write(1, 1);
    }

    public void pad(int factor) throws JSONException {
        Throwable th;
        try {
            this.bitwriter.pad(factor);
        } catch (Throwable th2) {
            Throwable e = th2;
            Throwable th3 = th;
            new JSONException(e);
            throw th3;
        }
    }

    private void write(int integer, int width) throws JSONException {
        Throwable th;
        try {
            this.bitwriter.write(integer, width);
        } catch (Throwable th2) {
            Throwable e = th2;
            Throwable th3 = th;
            new JSONException(e);
            throw th3;
        }
    }

    private void write(int integer, Huff huff) throws JSONException {
        huff.write(integer, this.bitwriter);
    }

    private void write(Kim kim, Huff huff) throws JSONException {
        Kim kim2 = kim;
        write(kim2, 0, kim2.length, huff);
    }

    private void write(Kim kim, int from, int i, Huff huff) throws JSONException {
        Kim kim2 = kim;
        int thru = i;
        Huff huff2 = huff;
        for (int at = from; at < thru; at++) {
            write(kim2.get(at), huff2);
        }
    }

    private void writeAndTick(int i, Keep keep) throws JSONException {
        int integer = i;
        Keep keep2 = keep;
        int width = keep2.bitsize();
        keep2.tick(integer);
        write(integer, width);
    }

    private void writeArray(JSONArray jSONArray) throws JSONException {
        JSONArray jsonarray = jSONArray;
        boolean stringy = false;
        int length = jsonarray.length();
        if (length == 0) {
            write(1, 3);
            return;
        }
        Object value = jsonarray.get(0);
        if (value == null) {
            value = JSONObject.NULL;
        }
        if (value instanceof String) {
            stringy = true;
            write(6, 3);
            writeString((String) value);
        } else {
            write(7, 3);
            writeValue(value);
        }
        for (int i = 1; i < length; i++) {
            Object value2 = jsonarray.get(i);
            if (value2 == null) {
                value2 = JSONObject.NULL;
            }
            if ((value2 instanceof String) != stringy) {
                zero();
            }
            one();
            if (value2 instanceof String) {
                writeString((String) value2);
            } else {
                writeValue(value2);
            }
        }
        zero();
        zero();
    }

    private void writeJSON(Object obj) throws JSONException {
        Object obj2;
        Object obj3;
        Throwable th;
        Object obj4;
        Object value = obj;
        if (JSONObject.NULL.equals(value)) {
            write(4, 3);
        } else if (Boolean.FALSE.equals(value)) {
            write(3, 3);
        } else if (Boolean.TRUE.equals(value)) {
            write(2, 3);
        } else {
            if (value instanceof Map) {
                new JSONObject((Map) value);
                value = obj4;
            } else if (value instanceof Collection) {
                new JSONArray((Collection) value);
                value = obj3;
            } else if (value.getClass().isArray()) {
                new JSONArray(value);
                value = obj2;
            }
            if (value instanceof JSONObject) {
                writeObject((JSONObject) value);
            } else if (value instanceof JSONArray) {
                writeArray((JSONArray) value);
            } else {
                Throwable th2 = th;
                new JSONException("Unrecognized object");
                throw th2;
            }
        }
    }

    private void writeName(String name) throws JSONException {
        Kim kim;
        new Kim(name);
        Kim kim2 = kim;
        int integer = this.namekeep.find(kim2);
        if (integer != -1) {
            one();
            writeAndTick(integer, this.namekeep);
            return;
        }
        zero();
        write(kim2, this.namehuff);
        write(256, this.namehuff);
        this.namekeep.register(kim2);
    }

    private void writeObject(JSONObject jSONObject) throws JSONException {
        JSONObject jsonobject = jSONObject;
        boolean first = true;
        Iterator keys = jsonobject.keys();
        while (keys.hasNext()) {
            Object key = keys.next();
            if (key instanceof String) {
                if (first) {
                    first = false;
                    write(5, 3);
                } else {
                    one();
                }
                writeName((String) key);
                Object value = jsonobject.get((String) key);
                if (value instanceof String) {
                    zero();
                    writeString((String) value);
                } else {
                    one();
                    writeValue(value);
                }
            }
        }
        if (first) {
            write(0, 3);
        } else {
            zero();
        }
    }

    private void writeString(String str) throws JSONException {
        Kim kim;
        String string = str;
        if (string.length() == 0) {
            zero();
            zero();
            write(256, this.substringhuff);
            zero();
            return;
        }
        new Kim(string);
        Kim kim2 = kim;
        int integer = this.stringkeep.find(kim2);
        if (integer != -1) {
            one();
            writeAndTick(integer, this.stringkeep);
            return;
        }
        writeSubstring(kim2);
        this.stringkeep.register(kim2);
    }

    private void writeSubstring(Kim kim) throws JSONException {
        int previousThru;
        Kim kim2 = kim;
        this.substringkeep.reserve();
        zero();
        int from = 0;
        int thru = kim2.length;
        int until = thru - 3;
        int previousFrom = -1;
        int i = 0;
        while (true) {
            previousThru = i;
            int integer = -1;
            int at = from;
            while (at <= until) {
                integer = this.substringkeep.match(kim2, at, thru);
                if (integer != -1) {
                    break;
                }
                at++;
            }
            if (integer == -1) {
                break;
            }
            if (from != at) {
                zero();
                write(kim2, from, at, this.substringhuff);
                write(256, this.substringhuff);
                if (previousFrom != -1) {
                    int registerOne = this.substringkeep.registerOne(kim2, previousFrom, previousThru);
                    previousFrom = -1;
                }
            }
            one();
            writeAndTick(integer, this.substringkeep);
            from = at + this.substringkeep.length(integer);
            if (previousFrom != -1) {
                int registerOne2 = this.substringkeep.registerOne(kim2, previousFrom, previousThru);
            }
            previousFrom = at;
            i = from + 1;
        }
        zero();
        if (from < thru) {
            write(kim2, from, thru, this.substringhuff);
            if (previousFrom != -1) {
                int registerOne3 = this.substringkeep.registerOne(kim2, previousFrom, previousThru);
            }
        }
        write(256, this.substringhuff);
        zero();
        this.substringkeep.registerMany(kim2);
    }

    private void writeValue(Object obj) throws JSONException {
        Object value = obj;
        if (value instanceof Number) {
            String string = JSONObject.numberToString((Number) value);
            int integer = this.values.find(string);
            if (integer != -1) {
                write(2, 2);
                writeAndTick(integer, this.values);
                return;
            }
            if ((value instanceof Integer) || (value instanceof Long)) {
                long longer = ((Number) value).longValue();
                if (longer >= 0 && longer < 16384) {
                    write(0, 2);
                    if (longer < 16) {
                        zero();
                        write((int) longer, 4);
                        return;
                    }
                    one();
                    if (longer < 128) {
                        zero();
                        write((int) longer, 7);
                        return;
                    }
                    one();
                    write((int) longer, 14);
                    return;
                }
            }
            write(1, 2);
            for (int i = 0; i < string.length(); i++) {
                write(bcd(string.charAt(i)), 4);
            }
            write(endOfNumber, 4);
            this.values.register(string);
            return;
        }
        write(3, 2);
        writeJSON(value);
    }

    private void zero() throws JSONException {
        write(0, 1);
    }

    public void zip(JSONObject jsonobject) throws JSONException {
        begin();
        writeJSON(jsonobject);
    }

    public void zip(JSONArray jsonarray) throws JSONException {
        begin();
        writeJSON(jsonarray);
    }
}
