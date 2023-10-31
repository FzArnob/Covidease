package com.google.appinventor.components.runtime.repackaged.org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JSONArray {
    private final ArrayList myArrayList;

    public JSONArray() {
        ArrayList arrayList;
        new ArrayList();
        this.myArrayList = arrayList;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONArray(JSONTokener jSONTokener) throws JSONException {
        this();
        JSONTokener x = jSONTokener;
        if (x.nextClean() != '[') {
            throw x.syntaxError("A JSONArray text must start with '['");
        } else if (x.nextClean() != ']') {
            x.back();
            while (true) {
                if (x.nextClean() == ',') {
                    x.back();
                    boolean add = this.myArrayList.add(JSONObject.NULL);
                } else {
                    x.back();
                    boolean add2 = this.myArrayList.add(x.nextValue());
                }
                switch (x.nextClean()) {
                    case ',':
                        if (x.nextClean() != ']') {
                            x.back();
                        } else {
                            return;
                        }
                    case ']':
                        return;
                    default:
                        throw x.syntaxError("Expected a ',' or ']'");
                }
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONArray(java.lang.String r8) throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            com.google.appinventor.components.runtime.repackaged.org.json.JSONTokener r3 = new com.google.appinventor.components.runtime.repackaged.org.json.JSONTokener
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>((java.lang.String) r5)
            r2.<init>((com.google.appinventor.components.runtime.repackaged.org.json.JSONTokener) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONArray.<init>(java.lang.String):void");
    }

    public JSONArray(Collection collection) {
        ArrayList arrayList;
        Collection<Object> collection2 = collection;
        new ArrayList();
        this.myArrayList = arrayList;
        if (collection2 != null) {
            for (Object wrap : collection2) {
                boolean add = this.myArrayList.add(JSONObject.wrap(wrap));
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONArray(Object obj) throws JSONException {
        this();
        Throwable th;
        Object array = obj;
        if (array.getClass().isArray()) {
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++) {
                JSONArray put = put(JSONObject.wrap(Array.get(array, i)));
            }
            return;
        }
        Throwable th2 = th;
        new JSONException("JSONArray initial value should be a string or collection or array.");
        throw th2;
    }

    public Object get(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = opt(index);
        if (object != null) {
            return object;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONArray[").append(index).append("] not found.").toString());
        throw th2;
    }

    public boolean getBoolean(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = get(index);
        if (object.equals(Boolean.FALSE) || ((object instanceof String) && ((String) object).equalsIgnoreCase("false"))) {
            return false;
        }
        if (object.equals(Boolean.TRUE) || ((object instanceof String) && ((String) object).equalsIgnoreCase("true"))) {
            return true;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONArray[").append(index).append("] is not a boolean.").toString());
        throw th2;
    }

    public double getDouble(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = get(index);
        try {
            return object instanceof Number ? ((Number) object).doubleValue() : Double.parseDouble((String) object);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONArray[").append(index).append("] is not a number.").toString());
            throw th2;
        }
    }

    public int getInt(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = get(index);
        try {
            return object instanceof Number ? ((Number) object).intValue() : Integer.parseInt((String) object);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONArray[").append(index).append("] is not a number.").toString());
            throw th2;
        }
    }

    public JSONArray getJSONArray(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = get(index);
        if (object instanceof JSONArray) {
            return (JSONArray) object;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONArray[").append(index).append("] is not a JSONArray.").toString());
        throw th2;
    }

    public JSONObject getJSONObject(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = get(index);
        if (object instanceof JSONObject) {
            return (JSONObject) object;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONArray[").append(index).append("] is not a JSONObject.").toString());
        throw th2;
    }

    public long getLong(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = get(index);
        try {
            return object instanceof Number ? ((Number) object).longValue() : Long.parseLong((String) object);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONArray[").append(index).append("] is not a number.").toString());
            throw th2;
        }
    }

    public String getString(int i) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object object = get(index);
        if (object instanceof String) {
            return (String) object;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONArray[").append(index).append("] not a string.").toString());
        throw th2;
    }

    public boolean isNull(int index) {
        return JSONObject.NULL.equals(opt(index));
    }

    public String join(String str) throws JSONException {
        StringBuffer stringBuffer;
        String separator = str;
        int len = length();
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                StringBuffer append = sb.append(separator);
            }
            StringBuffer append2 = sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return sb.toString();
    }

    public int length() {
        return this.myArrayList.size();
    }

    public Object opt(int i) {
        int index = i;
        return (index < 0 || index >= length()) ? null : this.myArrayList.get(index);
    }

    public boolean optBoolean(int index) {
        return optBoolean(index, false);
    }

    public boolean optBoolean(int index, boolean z) {
        boolean defaultValue = z;
        try {
            return getBoolean(index);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public double optDouble(int index) {
        return optDouble(index, Double.NaN);
    }

    public double optDouble(int index, double d) {
        double defaultValue = d;
        try {
            return getDouble(index);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public int optInt(int index) {
        return optInt(index, 0);
    }

    public int optInt(int index, int i) {
        int defaultValue = i;
        try {
            return getInt(index);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public JSONArray optJSONArray(int index) {
        Object o = opt(index);
        return o instanceof JSONArray ? (JSONArray) o : null;
    }

    public JSONObject optJSONObject(int index) {
        Object o = opt(index);
        return o instanceof JSONObject ? (JSONObject) o : null;
    }

    public long optLong(int index) {
        return optLong(index, 0);
    }

    public long optLong(int index, long j) {
        long defaultValue = j;
        try {
            return getLong(index);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public String optString(int index) {
        return optString(index, "");
    }

    public String optString(int index, String str) {
        String defaultValue = str;
        Object object = opt(index);
        return JSONObject.NULL.equals(object) ? defaultValue : object.toString();
    }

    public JSONArray put(boolean value) {
        JSONArray put = put((Object) value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONArray put(Collection value) {
        Object obj;
        new JSONArray(value);
        JSONArray put = put(obj);
        return this;
    }

    public JSONArray put(double value) throws JSONException {
        Double d;
        new Double(value);
        Double d2 = d;
        JSONObject.testValidity(d2);
        JSONArray put = put((Object) d2);
        return this;
    }

    public JSONArray put(int value) {
        Object obj;
        new Integer(value);
        JSONArray put = put(obj);
        return this;
    }

    public JSONArray put(long value) {
        Object obj;
        new Long(value);
        JSONArray put = put(obj);
        return this;
    }

    public JSONArray put(Map value) {
        Object obj;
        new JSONObject(value);
        JSONArray put = put(obj);
        return this;
    }

    public JSONArray put(Object value) {
        boolean add = this.myArrayList.add(value);
        return this;
    }

    public JSONArray put(int index, boolean value) throws JSONException {
        JSONArray put = put(index, (Object) value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONArray put(int index, Collection value) throws JSONException {
        Object obj;
        new JSONArray(value);
        JSONArray put = put(index, obj);
        return this;
    }

    public JSONArray put(int index, double value) throws JSONException {
        Object obj;
        new Double(value);
        JSONArray put = put(index, obj);
        return this;
    }

    public JSONArray put(int index, int value) throws JSONException {
        Object obj;
        new Integer(value);
        JSONArray put = put(index, obj);
        return this;
    }

    public JSONArray put(int index, long value) throws JSONException {
        Object obj;
        new Long(value);
        JSONArray put = put(index, obj);
        return this;
    }

    public JSONArray put(int index, Map value) throws JSONException {
        Object obj;
        new JSONObject(value);
        JSONArray put = put(index, obj);
        return this;
    }

    public JSONArray put(int i, Object obj) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        int index = i;
        Object value = obj;
        JSONObject.testValidity(value);
        if (index < 0) {
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONArray[").append(index).append("] not found.").toString());
            throw th2;
        }
        if (index < length()) {
            Object obj2 = this.myArrayList.set(index, value);
        } else {
            while (index != length()) {
                JSONArray put = put(JSONObject.NULL);
            }
            JSONArray put2 = put(value);
        }
        return this;
    }

    public Object remove(int i) {
        int index = i;
        Object o = opt(index);
        Object remove = this.myArrayList.remove(index);
        return o;
    }

    public JSONObject toJSONObject(JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject;
        JSONArray names = jSONArray;
        if (names == null || names.length() == 0 || length() == 0) {
            return null;
        }
        new JSONObject();
        JSONObject jo = jSONObject;
        for (int i = 0; i < names.length(); i++) {
            JSONObject put = jo.put(names.getString(i), opt(i));
        }
        return jo;
    }

    public String toString() {
        try {
            return toString(0);
        } catch (Exception e) {
            Exception exc = e;
            return null;
        }
    }

    public String toString(int i) throws JSONException {
        StringWriter stringWriter;
        int indentFactor = i;
        new StringWriter();
        StringWriter sw = stringWriter;
        StringBuffer buffer = sw.getBuffer();
        StringBuffer stringBuffer = buffer;
        synchronized (buffer) {
            try {
                String obj = write(sw, indentFactor, 0).toString();
                return obj;
            } catch (Throwable th) {
                Throwable th2 = th;
                StringBuffer stringBuffer2 = stringBuffer;
                throw th2;
            }
        }
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    /* access modifiers changed from: package-private */
    public Writer write(Writer writer, int i, int i2) throws JSONException {
        Throwable th;
        Writer writer2 = writer;
        int indentFactor = i;
        int indent = i2;
        boolean commanate = false;
        try {
            int length = length();
            writer2.write(91);
            if (length == 1) {
                Writer writeValue = JSONObject.writeValue(writer2, this.myArrayList.get(0), indentFactor, indent);
            } else if (length != 0) {
                int newindent = indent + indentFactor;
                for (int i3 = 0; i3 < length; i3++) {
                    if (commanate) {
                        writer2.write(44);
                    }
                    if (indentFactor > 0) {
                        writer2.write(10);
                    }
                    JSONObject.indent(writer2, newindent);
                    Writer writeValue2 = JSONObject.writeValue(writer2, this.myArrayList.get(i3), indentFactor, newindent);
                    commanate = true;
                }
                if (indentFactor > 0) {
                    writer2.write(10);
                }
                JSONObject.indent(writer2, indent);
            }
            writer2.write(93);
            return writer2;
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new JSONException((Throwable) e2);
            throw th2;
        }
    }
}
