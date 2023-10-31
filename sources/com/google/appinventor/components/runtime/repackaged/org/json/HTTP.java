package com.google.appinventor.components.runtime.repackaged.org.json;

import java.util.Iterator;
import org.shaded.apache.http.HttpVersion;

public class HTTP {
    public static final String CRLF = "\r\n";

    public HTTP() {
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jSONObject;
        HTTPTokener hTTPTokener;
        new JSONObject();
        JSONObject jo = jSONObject;
        new HTTPTokener(string);
        HTTPTokener x = hTTPTokener;
        String token = x.nextToken();
        if (token.toUpperCase().startsWith(HttpVersion.HTTP)) {
            JSONObject put = jo.put("HTTP-Version", (Object) token);
            JSONObject put2 = jo.put("Status-Code", (Object) x.nextToken());
            JSONObject put3 = jo.put("Reason-Phrase", (Object) x.nextTo(0));
            char next = x.next();
        } else {
            JSONObject put4 = jo.put("Method", (Object) token);
            JSONObject put5 = jo.put("Request-URI", (Object) x.nextToken());
            JSONObject put6 = jo.put("HTTP-Version", (Object) x.nextToken());
        }
        while (x.more()) {
            String name = x.nextTo(':');
            char next2 = x.next(':');
            JSONObject put7 = jo.put(name, (Object) x.nextTo(0));
            char next3 = x.next();
        }
        return jo;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuffer stringBuffer;
        Throwable th;
        JSONObject jo = jSONObject;
        Iterator keys = jo.keys();
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        if (jo.has("Status-Code") && jo.has("Reason-Phrase")) {
            StringBuffer append = sb.append(jo.getString("HTTP-Version"));
            StringBuffer append2 = sb.append(' ');
            StringBuffer append3 = sb.append(jo.getString("Status-Code"));
            StringBuffer append4 = sb.append(' ');
            StringBuffer append5 = sb.append(jo.getString("Reason-Phrase"));
        } else if (!jo.has("Method") || !jo.has("Request-URI")) {
            Throwable th2 = th;
            new JSONException("Not enough material for an HTTP header.");
            throw th2;
        } else {
            StringBuffer append6 = sb.append(jo.getString("Method"));
            StringBuffer append7 = sb.append(' ');
            StringBuffer append8 = sb.append('\"');
            StringBuffer append9 = sb.append(jo.getString("Request-URI"));
            StringBuffer append10 = sb.append('\"');
            StringBuffer append11 = sb.append(' ');
            StringBuffer append12 = sb.append(jo.getString("HTTP-Version"));
        }
        StringBuffer append13 = sb.append(CRLF);
        while (keys.hasNext()) {
            String string = keys.next().toString();
            if (!"HTTP-Version".equals(string) && !"Status-Code".equals(string) && !"Reason-Phrase".equals(string) && !"Method".equals(string) && !"Request-URI".equals(string) && !jo.isNull(string)) {
                StringBuffer append14 = sb.append(string);
                StringBuffer append15 = sb.append(": ");
                StringBuffer append16 = sb.append(jo.getString(string));
                StringBuffer append17 = sb.append(CRLF);
            }
        }
        StringBuffer append18 = sb.append(CRLF);
        return sb.toString();
    }
}
