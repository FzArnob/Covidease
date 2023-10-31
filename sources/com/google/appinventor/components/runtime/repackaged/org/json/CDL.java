package com.google.appinventor.components.runtime.repackaged.org.json;

public class CDL {
    public CDL() {
    }

    private static String getValue(JSONTokener jSONTokener) throws JSONException {
        char c;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        JSONTokener x = jSONTokener;
        while (true) {
            c = x.next();
            if (c != ' ' && c != 9) {
                break;
            }
        }
        switch (c) {
            case 0:
                return null;
            case '\"':
            case '\'':
                char q = c;
                new StringBuffer();
                StringBuffer sb = stringBuffer;
                while (true) {
                    char c2 = x.next();
                    if (c2 == q) {
                        return sb.toString();
                    }
                    if (c2 == 0 || c2 == 10 || c2 == 13) {
                        new StringBuffer();
                    } else {
                        StringBuffer append = sb.append(c2);
                    }
                }
                new StringBuffer();
                throw x.syntaxError(stringBuffer2.append("Missing close quote '").append(q).append("'.").toString());
            case ',':
                x.back();
                return "";
            default:
                x.back();
                return x.nextTo(',');
        }
    }

    public static JSONArray rowToJSONArray(JSONTokener jSONTokener) throws JSONException {
        JSONArray jSONArray;
        StringBuffer stringBuffer;
        JSONTokener x = jSONTokener;
        new JSONArray();
        JSONArray ja = jSONArray;
        while (true) {
            String value = getValue(x);
            char c = x.next();
            if (value != null && (ja.length() != 0 || value.length() != 0 || c == ',')) {
                JSONArray put = ja.put((Object) value);
                while (true) {
                    if (c != ',') {
                        if (c == ' ') {
                            c = x.next();
                        } else if (c == 10 || c == 13 || c == 0) {
                            return ja;
                        } else {
                            new StringBuffer();
                            throw x.syntaxError(stringBuffer.append("Bad character '").append(c).append("' (").append(c).append(").").toString());
                        }
                    }
                }
            }
        }
        return null;
    }

    public static JSONObject rowToJSONObject(JSONArray jSONArray, JSONTokener x) throws JSONException {
        JSONArray names = jSONArray;
        JSONArray ja = rowToJSONArray(x);
        return ja != null ? ja.toJSONObject(names) : null;
    }

    public static String rowToString(JSONArray jSONArray) {
        StringBuffer stringBuffer;
        JSONArray ja = jSONArray;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        for (int i = 0; i < ja.length(); i++) {
            if (i > 0) {
                StringBuffer append = sb.append(',');
            }
            Object object = ja.opt(i);
            if (object != null) {
                String string = object.toString();
                if (string.length() <= 0 || (string.indexOf(44) < 0 && string.indexOf(10) < 0 && string.indexOf(13) < 0 && string.indexOf(0) < 0 && string.charAt(0) != '\"')) {
                    StringBuffer append2 = sb.append(string);
                } else {
                    StringBuffer append3 = sb.append('\"');
                    int length = string.length();
                    for (int j = 0; j < length; j++) {
                        char c = string.charAt(j);
                        if (c >= ' ' && c != '\"') {
                            StringBuffer append4 = sb.append(c);
                        }
                    }
                    StringBuffer append5 = sb.append('\"');
                }
            }
        }
        StringBuffer append6 = sb.append(10);
        return sb.toString();
    }

    public static JSONArray toJSONArray(String string) throws JSONException {
        JSONTokener jSONTokener;
        new JSONTokener(string);
        return toJSONArray(jSONTokener);
    }

    public static JSONArray toJSONArray(JSONTokener jSONTokener) throws JSONException {
        JSONTokener x = jSONTokener;
        return toJSONArray(rowToJSONArray(x), x);
    }

    public static JSONArray toJSONArray(JSONArray names, String string) throws JSONException {
        JSONTokener jSONTokener;
        new JSONTokener(string);
        return toJSONArray(names, jSONTokener);
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        JSONArray jSONArray2;
        JSONArray names = jSONArray;
        JSONTokener x = jSONTokener;
        if (names == null || names.length() == 0) {
            return null;
        }
        new JSONArray();
        JSONArray ja = jSONArray2;
        while (true) {
            JSONObject jo = rowToJSONObject(names, x);
            if (jo == null) {
                break;
            }
            JSONArray put = ja.put((Object) jo);
        }
        if (ja.length() == 0) {
            return null;
        }
        return ja;
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        JSONArray names;
        StringBuffer stringBuffer;
        JSONArray ja = jSONArray;
        JSONObject jo = ja.optJSONObject(0);
        if (jo == null || (names = jo.names()) == null) {
            return null;
        }
        new StringBuffer();
        return stringBuffer.append(rowToString(names)).append(toString(names, ja)).toString();
    }

    public static String toString(JSONArray jSONArray, JSONArray jSONArray2) throws JSONException {
        StringBuffer stringBuffer;
        JSONArray names = jSONArray;
        JSONArray ja = jSONArray2;
        if (names == null || names.length() == 0) {
            return null;
        }
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.optJSONObject(i);
            if (jo != null) {
                StringBuffer append = sb.append(rowToString(jo.toJSONArray(names)));
            }
        }
        return sb.toString();
    }
}
