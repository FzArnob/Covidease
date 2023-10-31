package com.google.appinventor.components.runtime.repackaged.org.json;

import java.util.Iterator;

public class XML {
    public static final Character AMP;
    public static final Character APOS;
    public static final Character BANG;

    /* renamed from: EQ */
    public static final Character f320EQ;

    /* renamed from: GT */
    public static final Character f321GT;

    /* renamed from: LT */
    public static final Character f322LT;
    public static final Character QUEST;
    public static final Character QUOT;
    public static final Character SLASH;

    public XML() {
    }

    static {
        Character ch;
        Character ch2;
        Character ch3;
        Character ch4;
        Character ch5;
        Character ch6;
        Character ch7;
        Character ch8;
        Character ch9;
        new Character('&');
        AMP = ch;
        new Character('\'');
        APOS = ch2;
        new Character('!');
        BANG = ch3;
        new Character('=');
        f320EQ = ch4;
        new Character('>');
        f321GT = ch5;
        new Character('<');
        f322LT = ch6;
        new Character('?');
        QUEST = ch7;
        new Character('\"');
        QUOT = ch8;
        new Character('/');
        SLASH = ch9;
    }

    public static String escape(String str) {
        StringBuffer stringBuffer;
        String string = str;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        int length = string.length();
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            switch (c) {
                case '\"':
                    StringBuffer append = sb.append("&quot;");
                    break;
                case '&':
                    StringBuffer append2 = sb.append("&amp;");
                    break;
                case '\'':
                    StringBuffer append3 = sb.append("&apos;");
                    break;
                case '<':
                    StringBuffer append4 = sb.append("&lt;");
                    break;
                case '>':
                    StringBuffer append5 = sb.append("&gt;");
                    break;
                default:
                    StringBuffer append6 = sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static void noSpace(String str) throws JSONException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        String string = str;
        int length = string.length();
        if (length == 0) {
            Throwable th3 = th2;
            new JSONException("Empty string.");
            throw th3;
        }
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(string.charAt(i))) {
                Throwable th4 = th;
                new StringBuffer();
                new JSONException(stringBuffer.append("'").append(string).append("' contains a space character.").toString());
                throw th4;
            }
        }
    }

    private static boolean parse(XMLTokener xMLTokener, JSONObject jSONObject, String str) throws JSONException {
        JSONObject jSONObject2;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        XMLTokener x = xMLTokener;
        JSONObject context = jSONObject;
        String name = str;
        Object token = x.nextToken();
        if (token == BANG) {
            char c = x.next();
            if (c == '-') {
                if (x.next() == '-') {
                    boolean skipPast = x.skipPast("-->");
                    return false;
                }
                x.back();
            } else if (c == '[') {
                if (!"CDATA".equals(x.nextToken()) || x.next() != '[') {
                    throw x.syntaxError("Expected 'CDATA['");
                }
                String string = x.nextCDATA();
                if (string.length() > 0) {
                    JSONObject accumulate = context.accumulate("content", string);
                }
                return false;
            }
            int i = 1;
            do {
                Object token2 = x.nextMeta();
                if (token2 == null) {
                    throw x.syntaxError("Missing '>' after '<!'.");
                } else if (token2 == f322LT) {
                    i++;
                } else if (token2 == f321GT) {
                    i--;
                }
            } while (i > 0);
            return false;
        } else if (token == QUEST) {
            boolean skipPast2 = x.skipPast("?>");
            return false;
        } else if (token == SLASH) {
            Object token3 = x.nextToken();
            if (name == null) {
                new StringBuffer();
                throw x.syntaxError(stringBuffer3.append("Mismatched close tag ").append(token3).toString());
            } else if (!token3.equals(name)) {
                new StringBuffer();
                throw x.syntaxError(stringBuffer2.append("Mismatched ").append(name).append(" and ").append(token3).toString());
            } else if (x.nextToken() == f321GT) {
                return true;
            } else {
                throw x.syntaxError("Misshaped close tag");
            }
        } else if (token instanceof Character) {
            throw x.syntaxError("Misshaped tag");
        } else {
            String tagName = (String) token;
            Object token4 = null;
            new JSONObject();
            JSONObject jsonobject = jSONObject2;
            while (true) {
                if (token4 == null) {
                    token4 = x.nextToken();
                }
                if (token4 instanceof String) {
                    String string2 = (String) token4;
                    token4 = x.nextToken();
                    if (token4 == f320EQ) {
                        Object token5 = x.nextToken();
                        if (!(token5 instanceof String)) {
                            throw x.syntaxError("Missing value");
                        }
                        JSONObject accumulate2 = jsonobject.accumulate(string2, stringToValue((String) token5));
                        token4 = null;
                    } else {
                        JSONObject accumulate3 = jsonobject.accumulate(string2, "");
                    }
                } else if (token4 == SLASH) {
                    if (x.nextToken() != f321GT) {
                        throw x.syntaxError("Misshaped tag");
                    }
                    if (jsonobject.length() > 0) {
                        JSONObject accumulate4 = context.accumulate(tagName, jsonobject);
                    } else {
                        JSONObject accumulate5 = context.accumulate(tagName, "");
                    }
                    return false;
                } else if (token4 == f321GT) {
                    while (true) {
                        Object token6 = x.nextContent();
                        if (token6 == null) {
                            if (tagName == null) {
                                return false;
                            }
                            new StringBuffer();
                            throw x.syntaxError(stringBuffer.append("Unclosed tag ").append(tagName).toString());
                        } else if (token6 instanceof String) {
                            String string3 = (String) token6;
                            if (string3.length() > 0) {
                                JSONObject accumulate6 = jsonobject.accumulate("content", stringToValue(string3));
                            }
                        } else if (token6 == f322LT && parse(x, jsonobject, tagName)) {
                            if (jsonobject.length() == 0) {
                                JSONObject accumulate7 = context.accumulate(tagName, "");
                            } else if (jsonobject.length() != 1 || jsonobject.opt("content") == null) {
                                JSONObject accumulate8 = context.accumulate(tagName, jsonobject);
                            } else {
                                JSONObject accumulate9 = context.accumulate(tagName, jsonobject.opt("content"));
                            }
                            return false;
                        }
                    }
                } else {
                    throw x.syntaxError("Misshaped tag");
                }
            }
        }
    }

    public static Object stringToValue(String str) {
        Double d;
        Long l;
        String string = str;
        if ("true".equalsIgnoreCase(string)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(string)) {
            return Boolean.FALSE;
        }
        if ("null".equalsIgnoreCase(string)) {
            return JSONObject.NULL;
        }
        try {
            char initial = string.charAt(0);
            if (initial == '-' || (initial >= '0' && initial <= '9')) {
                new Long(string);
                Long value = l;
                if (value.toString().equals(string)) {
                    return value;
                }
            }
        } catch (Exception e) {
            Exception exc = e;
            try {
                new Double(string);
                Double value2 = d;
                if (value2.toString().equals(string)) {
                    return value2;
                }
            } catch (Exception e2) {
                Exception exc2 = e2;
            }
        }
        return string;
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jSONObject;
        XMLTokener xMLTokener;
        new JSONObject();
        JSONObject jo = jSONObject;
        new XMLTokener(string);
        XMLTokener x = xMLTokener;
        while (x.more() && x.skipPast("<")) {
            boolean parse = parse(x, jo, (String) null);
        }
        return jo;
    }

    public static String toString(Object object) throws JSONException {
        return toString(object, (String) null);
    }

    public static String toString(Object obj, String str) throws JSONException {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        String stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        Object obj2;
        Object object = obj;
        String tagName = str;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        if (object instanceof JSONObject) {
            if (tagName != null) {
                StringBuffer append = sb.append('<');
                StringBuffer append2 = sb.append(tagName);
                StringBuffer append3 = sb.append('>');
            }
            JSONObject jo = (JSONObject) object;
            Iterator keys = jo.keys();
            while (keys.hasNext()) {
                String key = keys.next().toString();
                Object value = jo.opt(key);
                if (value == null) {
                    value = "";
                }
                if (value instanceof String) {
                    String str2 = (String) value;
                }
                if ("content".equals(key)) {
                    if (value instanceof JSONArray) {
                        JSONArray ja = (JSONArray) value;
                        int length = ja.length();
                        for (int i = 0; i < length; i++) {
                            if (i > 0) {
                                StringBuffer append4 = sb.append(10);
                            }
                            StringBuffer append5 = sb.append(escape(ja.get(i).toString()));
                        }
                    } else {
                        StringBuffer append6 = sb.append(escape(value.toString()));
                    }
                } else if (value instanceof JSONArray) {
                    JSONArray ja2 = (JSONArray) value;
                    int length2 = ja2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        Object value2 = ja2.get(i2);
                        if (value2 instanceof JSONArray) {
                            StringBuffer append7 = sb.append('<');
                            StringBuffer append8 = sb.append(key);
                            StringBuffer append9 = sb.append('>');
                            StringBuffer append10 = sb.append(toString(value2));
                            StringBuffer append11 = sb.append("</");
                            StringBuffer append12 = sb.append(key);
                            StringBuffer append13 = sb.append('>');
                        } else {
                            StringBuffer append14 = sb.append(toString(value2, key));
                        }
                    }
                } else if ("".equals(value)) {
                    StringBuffer append15 = sb.append('<');
                    StringBuffer append16 = sb.append(key);
                    StringBuffer append17 = sb.append("/>");
                } else {
                    StringBuffer append18 = sb.append(toString(value, key));
                }
            }
            if (tagName != null) {
                StringBuffer append19 = sb.append("</");
                StringBuffer append20 = sb.append(tagName);
                StringBuffer append21 = sb.append('>');
            }
            return sb.toString();
        }
        if (object.getClass().isArray()) {
            new JSONArray(object);
            object = obj2;
        }
        if (object instanceof JSONArray) {
            JSONArray ja3 = (JSONArray) object;
            int length3 = ja3.length();
            for (int i3 = 0; i3 < length3; i3++) {
                StringBuffer append22 = sb.append(toString(ja3.opt(i3), tagName == null ? "array" : tagName));
            }
            return sb.toString();
        }
        String string = object == null ? "null" : escape(object.toString());
        if (tagName == null) {
            new StringBuffer();
            stringBuffer3 = stringBuffer5.append("\"").append(string).append("\"").toString();
        } else if (string.length() == 0) {
            new StringBuffer();
            stringBuffer3 = stringBuffer4.append("<").append(tagName).append("/>").toString();
        } else {
            new StringBuffer();
            stringBuffer3 = stringBuffer2.append("<").append(tagName).append(">").append(string).append("</").append(tagName).append(">").toString();
        }
        return stringBuffer3;
    }
}
