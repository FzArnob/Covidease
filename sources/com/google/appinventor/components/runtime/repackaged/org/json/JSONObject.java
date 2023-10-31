package com.google.appinventor.components.runtime.repackaged.org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class JSONObject {
    public static final Object NULL;
    private final Map map;

    /* renamed from: com.google.appinventor.components.runtime.repackaged.org.json.JSONObject$1 */
    static class C15521 {
    }

    private static final class Null {
        private Null() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Null(C15521 r4) {
            this();
            C15521 r1 = r4;
        }

        /* access modifiers changed from: protected */
        public final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            Object object = obj;
            return object == null || object == this;
        }

        public String toString() {
            return "null";
        }
    }

    static {
        Object obj;
        new Null((C15521) null);
        NULL = obj;
    }

    public JSONObject() {
        Map map2;
        new HashMap();
        this.map = map2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONObject(JSONObject jSONObject, String[] strArr) {
        this();
        JSONObject jo = jSONObject;
        String[] names = strArr;
        for (int i = 0; i < names.length; i++) {
            try {
                JSONObject putOnce = putOnce(names[i], jo.opt(names[i]));
            } catch (Exception e) {
                Exception exc = e;
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        JSONTokener x = jSONTokener;
        if (x.nextClean() != '{') {
            throw x.syntaxError("A JSONObject text must begin with '{'");
        }
        while (true) {
            switch (x.nextClean()) {
                case 0:
                    throw x.syntaxError("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    x.back();
                    String key = x.nextValue().toString();
                    if (x.nextClean() != ':') {
                        throw x.syntaxError("Expected a ':' after a key");
                    }
                    JSONObject putOnce = putOnce(key, x.nextValue());
                    switch (x.nextClean()) {
                        case ',':
                        case ';':
                            if (x.nextClean() != '}') {
                                x.back();
                            } else {
                                return;
                            }
                        case '}':
                            return;
                        default:
                            throw x.syntaxError("Expected a ',' or '}'");
                    }
            }
        }
    }

    public JSONObject(Map map2) {
        Map map3;
        Map map4 = map2;
        new HashMap();
        this.map = map3;
        if (map4 != null) {
            for (Map.Entry e : map4.entrySet()) {
                Object value = e.getValue();
                if (value != null) {
                    Object put = this.map.put(e.getKey(), wrap(value));
                }
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONObject(Object bean) {
        this();
        populateMap(bean);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONObject(Object obj, String[] strArr) {
        this();
        Object object = obj;
        String[] names = strArr;
        Class c = object.getClass();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            try {
                JSONObject putOpt = putOpt(name, c.getField(name).get(object));
            } catch (Exception e) {
                Exception exc = e;
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONObject(java.lang.String r8) throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONObject.<init>(java.lang.String):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONObject(String baseName, Locale locale) throws JSONException {
        this();
        JSONObject jSONObject;
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale, Thread.currentThread().getContextClassLoader());
        Enumeration keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (key instanceof String) {
                String[] path = ((String) key).split("\\.");
                int last = path.length - 1;
                JSONObject target = this;
                for (int i = 0; i < last; i++) {
                    String segment = path[i];
                    JSONObject nextTarget = target.optJSONObject(segment);
                    if (nextTarget == null) {
                        new JSONObject();
                        nextTarget = jSONObject;
                        JSONObject put = target.put(segment, (Object) nextTarget);
                    }
                    target = nextTarget;
                }
                JSONObject put2 = target.put(path[last], (Object) bundle.getString((String) key));
            }
        }
    }

    public JSONObject accumulate(String str, Object obj) throws JSONException {
        JSONArray jSONArray;
        Object obj2;
        JSONArray jSONArray2;
        String key = str;
        Object value = obj;
        testValidity(value);
        Object object = opt(key);
        if (object == null) {
            String str2 = key;
            if (value instanceof JSONArray) {
                new JSONArray();
                obj2 = jSONArray2.put(value);
            } else {
                obj2 = value;
            }
            JSONObject put = put(str2, obj2);
        } else if (object instanceof JSONArray) {
            JSONArray put2 = ((JSONArray) object).put(value);
        } else {
            new JSONArray();
            JSONObject put3 = put(key, (Object) jSONArray.put(object).put(value));
        }
        return this;
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        JSONArray jSONArray;
        String key = str;
        Object value = obj;
        testValidity(value);
        Object object = opt(key);
        if (object == null) {
            new JSONArray();
            JSONObject put = put(key, (Object) jSONArray.put(value));
        } else if (object instanceof JSONArray) {
            JSONObject put2 = put(key, (Object) ((JSONArray) object).put(value));
        } else {
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONObject[").append(key).append("] is not a JSONArray.").toString());
            throw th2;
        }
        return this;
    }

    public static String doubleToString(double d) {
        double d2 = d;
        if (Double.isInfinite(d2) || Double.isNaN(d2)) {
            return "null";
        }
        String string = Double.toString(d2);
        if (string.indexOf(46) > 0 && string.indexOf(101) < 0 && string.indexOf(69) < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }

    public Object get(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        String key = str;
        if (key == null) {
            Throwable th3 = th2;
            new JSONException("Null key.");
            throw th3;
        }
        Object object = opt(key);
        if (object != null) {
            return object;
        }
        Throwable th4 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] not found.").toString());
        throw th4;
    }

    public boolean getBoolean(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object object = get(key);
        if (object.equals(Boolean.FALSE) || ((object instanceof String) && ((String) object).equalsIgnoreCase("false"))) {
            return false;
        }
        if (object.equals(Boolean.TRUE) || ((object instanceof String) && ((String) object).equalsIgnoreCase("true"))) {
            return true;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] is not a Boolean.").toString());
        throw th2;
    }

    public double getDouble(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object object = get(key);
        try {
            return object instanceof Number ? ((Number) object).doubleValue() : Double.parseDouble((String) object);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] is not a number.").toString());
            throw th2;
        }
    }

    public int getInt(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object object = get(key);
        try {
            return object instanceof Number ? ((Number) object).intValue() : Integer.parseInt((String) object);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] is not an int.").toString());
            throw th2;
        }
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object object = get(key);
        if (object instanceof JSONArray) {
            return (JSONArray) object;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] is not a JSONArray.").toString());
        throw th2;
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object object = get(key);
        if (object instanceof JSONObject) {
            return (JSONObject) object;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] is not a JSONObject.").toString());
        throw th2;
    }

    public long getLong(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object object = get(key);
        try {
            return object instanceof Number ? ((Number) object).longValue() : Long.parseLong((String) object);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] is not a long.").toString());
            throw th2;
        }
    }

    public static String[] getNames(JSONObject jSONObject) {
        JSONObject jo = jSONObject;
        int length = jo.length();
        if (length == 0) {
            return null;
        }
        Iterator iterator = jo.keys();
        String[] names = new String[length];
        int i = 0;
        while (iterator.hasNext()) {
            names[i] = (String) iterator.next();
            i++;
        }
        return names;
    }

    public static String[] getNames(Object obj) {
        Object object = obj;
        if (object == null) {
            return null;
        }
        Field[] fields = object.getClass().getFields();
        int length = fields.length;
        if (length == 0) {
            return null;
        }
        String[] names = new String[length];
        for (int i = 0; i < length; i++) {
            names[i] = fields[i].getName();
        }
        return names;
    }

    public String getString(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object object = get(key);
        if (object instanceof String) {
            return (String) object;
        }
        Throwable th2 = th;
        new StringBuffer();
        new JSONException(stringBuffer.append("JSONObject[").append(quote(key)).append("] not a string.").toString());
        throw th2;
    }

    public boolean has(String key) {
        return this.map.containsKey(key);
    }

    public JSONObject increment(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object value = opt(key);
        if (value == null) {
            JSONObject put = put(key, 1);
        } else if (value instanceof Integer) {
            JSONObject put2 = put(key, ((Integer) value).intValue() + 1);
        } else if (value instanceof Long) {
            JSONObject put3 = put(key, ((Long) value).longValue() + 1);
        } else if (value instanceof Double) {
            JSONObject put4 = put(key, ((Double) value).doubleValue() + 1.0d);
        } else if (value instanceof Float) {
            JSONObject put5 = put(key, (double) (((Float) value).floatValue() + 1.0f));
        } else {
            Throwable th2 = th;
            new StringBuffer();
            new JSONException(stringBuffer.append("Unable to increment [").append(quote(key)).append("].").toString());
            throw th2;
        }
        return this;
    }

    public boolean isNull(String key) {
        return NULL.equals(opt(key));
    }

    public Iterator keys() {
        return keySet().iterator();
    }

    public Set keySet() {
        return this.map.keySet();
    }

    public int length() {
        return this.map.size();
    }

    public JSONArray names() {
        JSONArray jSONArray;
        new JSONArray();
        JSONArray ja = jSONArray;
        Iterator keys = keys();
        while (keys.hasNext()) {
            JSONArray put = ja.put(keys.next());
        }
        return ja.length() == 0 ? null : ja;
    }

    public static String numberToString(Number number) throws JSONException {
        Throwable th;
        Number number2 = number;
        if (number2 == null) {
            Throwable th2 = th;
            new JSONException("Null pointer");
            throw th2;
        }
        testValidity(number2);
        String string = number2.toString();
        if (string.indexOf(46) > 0 && string.indexOf(101) < 0 && string.indexOf(69) < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }

    public Object opt(String str) {
        String key = str;
        return key == null ? null : this.map.get(key);
    }

    public boolean optBoolean(String key) {
        return optBoolean(key, false);
    }

    public boolean optBoolean(String key, boolean z) {
        boolean defaultValue = z;
        try {
            return getBoolean(key);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public double optDouble(String key) {
        return optDouble(key, Double.NaN);
    }

    public double optDouble(String key, double d) {
        double defaultValue = d;
        try {
            return getDouble(key);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public int optInt(String key) {
        return optInt(key, 0);
    }

    public int optInt(String key, int i) {
        int defaultValue = i;
        try {
            return getInt(key);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public JSONArray optJSONArray(String key) {
        Object o = opt(key);
        return o instanceof JSONArray ? (JSONArray) o : null;
    }

    public JSONObject optJSONObject(String key) {
        Object object = opt(key);
        return object instanceof JSONObject ? (JSONObject) object : null;
    }

    public long optLong(String key) {
        return optLong(key, 0);
    }

    public long optLong(String key, long j) {
        long defaultValue = j;
        try {
            return getLong(key);
        } catch (Exception e) {
            Exception exc = e;
            return defaultValue;
        }
    }

    public String optString(String key) {
        return optString(key, "");
    }

    public String optString(String key, String str) {
        String defaultValue = str;
        Object object = opt(key);
        return NULL.equals(object) ? defaultValue : object.toString();
    }

    private void populateMap(Object obj) {
        Method[] declaredMethods;
        StringBuffer stringBuffer;
        Object bean = obj;
        Class klass = bean.getClass();
        if (klass.getClassLoader() != null) {
            declaredMethods = klass.getMethods();
        } else {
            declaredMethods = klass.getDeclaredMethods();
        }
        Method[] methods = declaredMethods;
        for (int i = 0; i < methods.length; i++) {
            try {
                Method method = methods[i];
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    String key = "";
                    if (name.startsWith("get")) {
                        if ("getClass".equals(name) || "getDeclaringClass".equals(name)) {
                            key = "";
                        } else {
                            key = name.substring(3);
                        }
                    } else if (name.startsWith("is")) {
                        key = name.substring(2);
                    }
                    if (key.length() > 0 && Character.isUpperCase(key.charAt(0)) && method.getParameterTypes().length == 0) {
                        if (key.length() == 1) {
                            key = key.toLowerCase();
                        } else if (!Character.isUpperCase(key.charAt(1))) {
                            new StringBuffer();
                            key = stringBuffer.append(key.substring(0, 1).toLowerCase()).append(key.substring(1)).toString();
                        }
                        Object result = method.invoke(bean, (Object[]) null);
                        if (result != null) {
                            Object put = this.map.put(key, wrap(result));
                        }
                    }
                }
            } catch (Exception e) {
                Exception exc = e;
            }
        }
    }

    public JSONObject put(String key, boolean value) throws JSONException {
        JSONObject put = put(key, (Object) value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONObject put(String key, Collection value) throws JSONException {
        Object obj;
        new JSONArray(value);
        JSONObject put = put(key, obj);
        return this;
    }

    public JSONObject put(String key, double value) throws JSONException {
        Object obj;
        new Double(value);
        JSONObject put = put(key, obj);
        return this;
    }

    public JSONObject put(String key, int value) throws JSONException {
        Object obj;
        new Integer(value);
        JSONObject put = put(key, obj);
        return this;
    }

    public JSONObject put(String key, long value) throws JSONException {
        Object obj;
        new Long(value);
        JSONObject put = put(key, obj);
        return this;
    }

    public JSONObject put(String key, Map value) throws JSONException {
        Object obj;
        new JSONObject(value);
        JSONObject put = put(key, obj);
        return this;
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        Throwable th;
        String key = str;
        Object value = obj;
        if (key == null) {
            Throwable th2 = th;
            new NullPointerException("Null key.");
            throw th2;
        }
        if (value != null) {
            testValidity(value);
            Object put = this.map.put(key, value);
        } else {
            Object remove = remove(key);
        }
        return this;
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        String key = str;
        Object value = obj;
        if (!(key == null || value == null)) {
            if (opt(key) != null) {
                Throwable th2 = th;
                new StringBuffer();
                new JSONException(stringBuffer.append("Duplicate key \"").append(key).append("\"").toString());
                throw th2;
            }
            JSONObject put = put(key, value);
        }
        return this;
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        String key = str;
        Object value = obj;
        if (!(key == null || value == null)) {
            JSONObject put = put(key, value);
        }
        return this;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String quote(java.lang.String r8) {
        /*
            r0 = r8
            java.io.StringWriter r5 = new java.io.StringWriter
            r7 = r5
            r5 = r7
            r6 = r7
            r6.<init>()
            r1 = r5
            r5 = r1
            java.lang.StringBuffer r5 = r5.getBuffer()
            r7 = r5
            r5 = r7
            r6 = r7
            r2 = r6
            monitor-enter(r5)
            r5 = r0
            r6 = r1
            java.io.Writer r5 = quote(r5, r6)     // Catch:{ IOException -> 0x0022 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0022 }
            r6 = r2
            monitor-exit(r6)     // Catch:{ all -> 0x002b }
            r0 = r5
        L_0x0021:
            return r0
        L_0x0022:
            r5 = move-exception
            r3 = r5
            java.lang.String r5 = ""
            r6 = r2
            monitor-exit(r6)     // Catch:{ all -> 0x002b }
            r0 = r5
            goto L_0x0021
        L_0x002b:
            r5 = move-exception
            r4 = r5
            r5 = r2
            monitor-exit(r5)     // Catch:{ all -> 0x002b }
            r5 = r4
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONObject.quote(java.lang.String):java.lang.String");
    }

    public static Writer quote(String str, Writer writer) throws IOException {
        String string = str;
        Writer w = writer;
        if (string == null || string.length() == 0) {
            w.write("\"\"");
            return w;
        }
        char c = 0;
        int len = string.length();
        w.write(34);
        for (int i = 0; i < len; i++) {
            char b = c;
            c = string.charAt(i);
            switch (c) {
                case 8:
                    w.write("\\b");
                    break;
                case 9:
                    w.write("\\t");
                    break;
                case 10:
                    w.write("\\n");
                    break;
                case 12:
                    w.write("\\f");
                    break;
                case 13:
                    w.write("\\r");
                    break;
                case '\"':
                case '\\':
                    w.write(92);
                    w.write(c);
                    break;
                case '/':
                    if (b == '<') {
                        w.write(92);
                    }
                    w.write(c);
                    break;
                default:
                    if (c >= ' ' && ((c < 128 || c >= 160) && (c < 8192 || c >= 8448))) {
                        w.write(c);
                        break;
                    } else {
                        w.write("\\u");
                        String hhhh = Integer.toHexString(c);
                        w.write("0000", 0, 4 - hhhh.length());
                        w.write(hhhh);
                        break;
                    }
            }
        }
        w.write(34);
        return w;
    }

    public Object remove(String key) {
        return this.map.remove(key);
    }

    public static Object stringToValue(String str) {
        Long l;
        Object obj;
        String string = str;
        if (string.equals("")) {
            return string;
        }
        if (string.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (string.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (string.equalsIgnoreCase("null")) {
            return NULL;
        }
        char b = string.charAt(0);
        if ((b >= '0' && b <= '9') || b == '-') {
            try {
                if (string.indexOf(46) > -1 || string.indexOf(101) > -1 || string.indexOf(69) > -1) {
                    Double d = Double.valueOf(string);
                    if (!d.isInfinite() && !d.isNaN()) {
                        return d;
                    }
                } else {
                    new Long(string);
                    Long myLong = l;
                    if (string.equals(myLong.toString())) {
                        if (myLong.longValue() != ((long) myLong.intValue())) {
                            return myLong;
                        }
                        Object obj2 = obj;
                        new Integer(myLong.intValue());
                        return obj2;
                    }
                }
            } catch (Exception e) {
                Exception exc = e;
            }
        }
        return string;
    }

    public static void testValidity(Object obj) throws JSONException {
        Throwable th;
        Throwable th2;
        Object o = obj;
        if (o == null) {
            return;
        }
        if (o instanceof Double) {
            if (((Double) o).isInfinite() || ((Double) o).isNaN()) {
                Throwable th3 = th2;
                new JSONException("JSON does not allow non-finite numbers.");
                throw th3;
            }
        } else if (!(o instanceof Float)) {
        } else {
            if (((Float) o).isInfinite() || ((Float) o).isNaN()) {
                Throwable th4 = th;
                new JSONException("JSON does not allow non-finite numbers.");
                throw th4;
            }
        }
    }

    public JSONArray toJSONArray(JSONArray jSONArray) throws JSONException {
        JSONArray jSONArray2;
        JSONArray names = jSONArray;
        if (names == null || names.length() == 0) {
            return null;
        }
        new JSONArray();
        JSONArray ja = jSONArray2;
        for (int i = 0; i < names.length(); i++) {
            JSONArray put = ja.put(opt(names.getString(i)));
        }
        return ja;
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
        StringWriter w = stringWriter;
        StringBuffer buffer = w.getBuffer();
        StringBuffer stringBuffer = buffer;
        synchronized (buffer) {
            try {
                String obj = write(w, indentFactor, 0).toString();
                return obj;
            } catch (Throwable th) {
                Throwable th2 = th;
                StringBuffer stringBuffer2 = stringBuffer;
                throw th2;
            }
        }
    }

    public static String valueToString(Object obj) throws JSONException {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject;
        Throwable th;
        Throwable th2;
        StringBuffer stringBuffer;
        Object value = obj;
        if (value == null || value.equals((Object) null)) {
            return "null";
        }
        if (value instanceof JSONString) {
            try {
                String object = ((JSONString) value).toJSONString();
                if (object instanceof String) {
                    return object;
                }
                Throwable th3 = th2;
                new StringBuffer();
                new JSONException(stringBuffer.append("Bad value from toJSONString: ").append(object).toString());
                throw th3;
            } catch (Exception e) {
                Exception e2 = e;
                Throwable th4 = th;
                new JSONException((Throwable) e2);
                throw th4;
            }
        } else if (value instanceof Number) {
            return numberToString((Number) value);
        } else {
            if ((value instanceof Boolean) || (value instanceof JSONObject) || (value instanceof JSONArray)) {
                return value.toString();
            }
            if (value instanceof Map) {
                new JSONObject((Map) value);
                return jSONObject.toString();
            } else if (value instanceof Collection) {
                new JSONArray((Collection) value);
                return jSONArray2.toString();
            } else if (!value.getClass().isArray()) {
                return quote(value.toString());
            } else {
                new JSONArray(value);
                return jSONArray.toString();
            }
        }
    }

    public static Object wrap(Object obj) {
        Object obj2;
        Object object;
        Object object2;
        Object object3;
        Object object4 = obj;
        if (object4 == null) {
            try {
                return NULL;
            } catch (Exception e) {
                Exception exc = e;
                return null;
            }
        } else if ((object4 instanceof JSONObject) || (object4 instanceof JSONArray) || NULL.equals(object4) || (object4 instanceof JSONString) || (object4 instanceof Byte) || (object4 instanceof Character) || (object4 instanceof Short) || (object4 instanceof Integer) || (object4 instanceof Long) || (object4 instanceof Boolean) || (object4 instanceof Float) || (object4 instanceof Double) || (object4 instanceof String)) {
            return object4;
        } else {
            if (object4 instanceof Collection) {
                new JSONArray((Collection) object4);
                return object3;
            } else if (object4.getClass().isArray()) {
                new JSONArray(object4);
                return object2;
            } else if (object4 instanceof Map) {
                new JSONObject((Map) object4);
                return object;
            } else {
                Package objectPackage = object4.getClass().getPackage();
                String objectPackageName = objectPackage != null ? objectPackage.getName() : "";
                if (objectPackageName.startsWith("java.") || objectPackageName.startsWith("javax.") || object4.getClass().getClassLoader() == null) {
                    return object4.toString();
                }
                Object object5 = obj2;
                new JSONObject(object4);
                return object5;
            }
        }
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    static final Writer writeValue(Writer writer, Object obj, int i, int i2) throws JSONException, IOException {
        Throwable th;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject;
        Writer writer2 = writer;
        Object value = obj;
        int indentFactor = i;
        int indent = i2;
        if (value == null || value.equals((Object) null)) {
            writer2.write("null");
        } else if (value instanceof JSONObject) {
            Writer write = ((JSONObject) value).write(writer2, indentFactor, indent);
        } else if (value instanceof JSONArray) {
            Writer write2 = ((JSONArray) value).write(writer2, indentFactor, indent);
        } else if (value instanceof Map) {
            new JSONObject((Map) value);
            Writer write3 = jSONObject.write(writer2, indentFactor, indent);
        } else if (value instanceof Collection) {
            new JSONArray((Collection) value);
            Writer write4 = jSONArray2.write(writer2, indentFactor, indent);
        } else if (value.getClass().isArray()) {
            new JSONArray(value);
            Writer write5 = jSONArray.write(writer2, indentFactor, indent);
        } else if (value instanceof Number) {
            writer2.write(numberToString((Number) value));
        } else if (value instanceof Boolean) {
            writer2.write(value.toString());
        } else if (value instanceof JSONString) {
            try {
                String o = ((JSONString) value).toJSONString();
                writer2.write(o != null ? o.toString() : quote(value.toString()));
            } catch (Exception e) {
                Exception e2 = e;
                Throwable th2 = th;
                new JSONException((Throwable) e2);
                throw th2;
            }
        } else {
            Writer quote = quote(value.toString(), writer2);
        }
        return writer2;
    }

    static final void indent(Writer writer, int i) throws IOException {
        Writer writer2 = writer;
        int indent = i;
        for (int i2 = 0; i2 < indent; i2++) {
            writer2.write(32);
        }
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
            Iterator keys = keys();
            writer2.write(123);
            if (length == 1) {
                Object key = keys.next();
                writer2.write(quote(key.toString()));
                writer2.write(58);
                if (indentFactor > 0) {
                    writer2.write(32);
                }
                Writer writeValue = writeValue(writer2, this.map.get(key), indentFactor, indent);
            } else if (length != 0) {
                int newindent = indent + indentFactor;
                while (keys.hasNext()) {
                    Object key2 = keys.next();
                    if (commanate) {
                        writer2.write(44);
                    }
                    if (indentFactor > 0) {
                        writer2.write(10);
                    }
                    indent(writer2, newindent);
                    writer2.write(quote(key2.toString()));
                    writer2.write(58);
                    if (indentFactor > 0) {
                        writer2.write(32);
                    }
                    Writer writeValue2 = writeValue(writer2, this.map.get(key2), indentFactor, newindent);
                    commanate = true;
                }
                if (indentFactor > 0) {
                    writer2.write(10);
                }
                indent(writer2, indent);
            }
            writer2.write(125);
            return writer2;
        } catch (IOException e) {
            IOException exception = e;
            Throwable th2 = th;
            new JSONException((Throwable) exception);
            throw th2;
        }
    }
}
