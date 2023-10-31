package com.google.appinventor.components.runtime.repackaged.org.json;

import org.shaded.apache.http.cookie.ClientCookie;

public class Cookie {
    public Cookie() {
    }

    public static String escape(String string) {
        StringBuffer stringBuffer;
        String s = string.trim();
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c < ' ' || c == '+' || c == '%' || c == '=' || c == ';') {
                StringBuffer append = sb.append('%');
                StringBuffer append2 = sb.append(Character.forDigit((char) ((c >>> 4) & 15), 16));
                StringBuffer append3 = sb.append(Character.forDigit((char) (c & 15), 16));
            } else {
                StringBuffer append4 = sb.append(c);
            }
        }
        return sb.toString();
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jSONObject;
        JSONTokener jSONTokener;
        Object unescape;
        new JSONObject();
        JSONObject jo = jSONObject;
        new JSONTokener(string);
        JSONTokener x = jSONTokener;
        JSONObject put = jo.put("name", (Object) x.nextTo('='));
        char next = x.next('=');
        JSONObject put2 = jo.put("value", (Object) x.nextTo(';'));
        char next2 = x.next();
        while (x.more()) {
            String name = unescape(x.nextTo("=;"));
            if (x.next() == '=') {
                unescape = unescape(x.nextTo(';'));
                char next3 = x.next();
            } else if (name.equals(ClientCookie.SECURE_ATTR)) {
                unescape = Boolean.TRUE;
            } else {
                throw x.syntaxError("Missing '=' in cookie parameter.");
            }
            JSONObject put3 = jo.put(name, unescape);
        }
        return jo;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuffer stringBuffer;
        JSONObject jo = jSONObject;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        StringBuffer append = sb.append(escape(jo.getString("name")));
        StringBuffer append2 = sb.append("=");
        StringBuffer append3 = sb.append(escape(jo.getString("value")));
        if (jo.has(ClientCookie.EXPIRES_ATTR)) {
            StringBuffer append4 = sb.append(";expires=");
            StringBuffer append5 = sb.append(jo.getString(ClientCookie.EXPIRES_ATTR));
        }
        if (jo.has(ClientCookie.DOMAIN_ATTR)) {
            StringBuffer append6 = sb.append(";domain=");
            StringBuffer append7 = sb.append(escape(jo.getString(ClientCookie.DOMAIN_ATTR)));
        }
        if (jo.has(ClientCookie.PATH_ATTR)) {
            StringBuffer append8 = sb.append(";path=");
            StringBuffer append9 = sb.append(escape(jo.getString(ClientCookie.PATH_ATTR)));
        }
        if (jo.optBoolean(ClientCookie.SECURE_ATTR)) {
            StringBuffer append10 = sb.append(";secure");
        }
        return sb.toString();
    }

    public static String unescape(String str) {
        StringBuffer stringBuffer;
        String string = str;
        int length = string.length();
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        int i = 0;
        while (i < length) {
            char c = string.charAt(i);
            if (c == '+') {
                c = ' ';
            } else if (c == '%' && i + 2 < length) {
                int d = JSONTokener.dehexchar(string.charAt(i + 1));
                int e = JSONTokener.dehexchar(string.charAt(i + 2));
                if (d >= 0 && e >= 0) {
                    c = (char) ((d * 16) + e);
                    i += 2;
                }
            }
            StringBuffer append = sb.append(c);
            i++;
        }
        return sb.toString();
    }
}
