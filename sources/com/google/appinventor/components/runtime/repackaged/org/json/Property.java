package com.google.appinventor.components.runtime.repackaged.org.json;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

public class Property {
    public Property() {
    }

    public static JSONObject toJSONObject(Properties properties) throws JSONException {
        JSONObject jSONObject;
        Properties properties2 = properties;
        new JSONObject();
        JSONObject jo = jSONObject;
        if (properties2 != null && !properties2.isEmpty()) {
            Enumeration enumProperties = properties2.propertyNames();
            while (enumProperties.hasMoreElements()) {
                String name = (String) enumProperties.nextElement();
                JSONObject put = jo.put(name, (Object) properties2.getProperty(name));
            }
        }
        return jo;
    }

    public static Properties toProperties(JSONObject jSONObject) throws JSONException {
        Properties properties;
        JSONObject jo = jSONObject;
        new Properties();
        Properties properties2 = properties;
        if (jo != null) {
            Iterator keys = jo.keys();
            while (keys.hasNext()) {
                String name = keys.next().toString();
                Object put = properties2.put(name, jo.getString(name));
            }
        }
        return properties2;
    }
}
