package com.google.appinventor.components.runtime.repackaged.org.json;

import java.util.Iterator;

public class CookieList {
    public CookieList() {
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jSONObject;
        JSONTokener jSONTokener;
        new JSONObject();
        JSONObject jo = jSONObject;
        new JSONTokener(string);
        JSONTokener x = jSONTokener;
        while (x.more()) {
            String name = Cookie.unescape(x.nextTo('='));
            char next = x.next('=');
            JSONObject put = jo.put(name, (Object) Cookie.unescape(x.nextTo(';')));
            char next2 = x.next();
        }
        return jo;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuffer stringBuffer;
        JSONObject jo = jSONObject;
        boolean b = false;
        Iterator keys = jo.keys();
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        while (keys.hasNext()) {
            String string = keys.next().toString();
            if (!jo.isNull(string)) {
                if (b) {
                    StringBuffer append = sb.append(';');
                }
                StringBuffer append2 = sb.append(Cookie.escape(string));
                StringBuffer append3 = sb.append("=");
                StringBuffer append4 = sb.append(Cookie.escape(jo.getString(string)));
                b = true;
            }
        }
        return sb.toString();
    }
}
