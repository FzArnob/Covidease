package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

public final class JsonObject extends JsonElement {
    private final LinkedTreeMap<String, JsonElement> members;

    public JsonObject() {
        LinkedTreeMap<String, JsonElement> linkedTreeMap;
        new LinkedTreeMap<>();
        this.members = linkedTreeMap;
    }

    public JsonObject deepCopy() {
        JsonObject jsonObject;
        new JsonObject();
        JsonObject result = jsonObject;
        for (Map.Entry<String, JsonElement> entry : this.members.entrySet()) {
            result.add(entry.getKey(), entry.getValue().deepCopy());
        }
        return result;
    }

    public void add(String str, JsonElement jsonElement) {
        String property = str;
        JsonElement value = jsonElement;
        if (value == null) {
            value = JsonNull.INSTANCE;
        }
        JsonElement put = this.members.put(property, value);
    }

    public JsonElement remove(String property) {
        return this.members.remove(property);
    }

    public void addProperty(String property, String value) {
        add(property, createJsonElement(value));
    }

    public void addProperty(String property, Number value) {
        add(property, createJsonElement(value));
    }

    public void addProperty(String property, Boolean value) {
        add(property, createJsonElement(value));
    }

    public void addProperty(String property, Character value) {
        add(property, createJsonElement(value));
    }

    private JsonElement createJsonElement(Object obj) {
        JsonNull jsonNull;
        JsonNull jsonNull2;
        Object value = obj;
        if (value == null) {
            jsonNull2 = JsonNull.INSTANCE;
        } else {
            jsonNull2 = jsonNull;
            new JsonPrimitive(value);
        }
        return jsonNull2;
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public Set<String> keySet() {
        return this.members.keySet();
    }

    public int size() {
        return this.members.size();
    }

    public boolean has(String memberName) {
        return this.members.containsKey(memberName);
    }

    public JsonElement get(String memberName) {
        return this.members.get(memberName);
    }

    public JsonPrimitive getAsJsonPrimitive(String memberName) {
        return (JsonPrimitive) this.members.get(memberName);
    }

    public JsonArray getAsJsonArray(String memberName) {
        return (JsonArray) this.members.get(memberName);
    }

    public JsonObject getAsJsonObject(String memberName) {
        return (JsonObject) this.members.get(memberName);
    }

    public boolean equals(Object obj) {
        Object o = obj;
        return o == this || ((o instanceof JsonObject) && ((JsonObject) o).members.equals(this.members));
    }

    public int hashCode() {
        return this.members.hashCode();
    }
}
