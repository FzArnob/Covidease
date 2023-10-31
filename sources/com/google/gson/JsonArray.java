package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement implements Iterable<JsonElement> {
    private final List<JsonElement> elements;

    public JsonArray() {
        List<JsonElement> list;
        new ArrayList();
        this.elements = list;
    }

    public JsonArray(int capacity) {
        List<JsonElement> list;
        new ArrayList(capacity);
        this.elements = list;
    }

    public JsonArray deepCopy() {
        JsonArray jsonArray;
        JsonArray jsonArray2;
        if (!this.elements.isEmpty()) {
            new JsonArray(this.elements.size());
            JsonArray result = jsonArray2;
            for (JsonElement element : this.elements) {
                result.add(element.deepCopy());
            }
            return result;
        }
        new JsonArray();
        return jsonArray;
    }

    public void add(Boolean bool) {
        JsonNull jsonNull;
        JsonNull jsonNull2;
        Boolean bool2 = bool;
        List<JsonElement> list = this.elements;
        if (bool2 == null) {
            jsonNull2 = JsonNull.INSTANCE;
        } else {
            jsonNull2 = jsonNull;
            new JsonPrimitive(bool2);
        }
        boolean add = list.add(jsonNull2);
    }

    public void add(Character ch) {
        JsonNull jsonNull;
        JsonNull jsonNull2;
        Character character = ch;
        List<JsonElement> list = this.elements;
        if (character == null) {
            jsonNull2 = JsonNull.INSTANCE;
        } else {
            jsonNull2 = jsonNull;
            new JsonPrimitive(character);
        }
        boolean add = list.add(jsonNull2);
    }

    public void add(Number number) {
        JsonNull jsonNull;
        JsonNull jsonNull2;
        Number number2 = number;
        List<JsonElement> list = this.elements;
        if (number2 == null) {
            jsonNull2 = JsonNull.INSTANCE;
        } else {
            jsonNull2 = jsonNull;
            new JsonPrimitive(number2);
        }
        boolean add = list.add(jsonNull2);
    }

    public void add(String str) {
        JsonNull jsonNull;
        JsonNull jsonNull2;
        String string = str;
        List<JsonElement> list = this.elements;
        if (string == null) {
            jsonNull2 = JsonNull.INSTANCE;
        } else {
            jsonNull2 = jsonNull;
            new JsonPrimitive(string);
        }
        boolean add = list.add(jsonNull2);
    }

    public void add(JsonElement jsonElement) {
        JsonElement element = jsonElement;
        if (element == null) {
            element = JsonNull.INSTANCE;
        }
        boolean add = this.elements.add(element);
    }

    public void addAll(JsonArray array) {
        boolean addAll = this.elements.addAll(array.elements);
    }

    public JsonElement set(int index, JsonElement element) {
        return this.elements.set(index, element);
    }

    public boolean remove(JsonElement element) {
        return this.elements.remove(element);
    }

    public JsonElement remove(int index) {
        return this.elements.remove(index);
    }

    public boolean contains(JsonElement element) {
        return this.elements.contains(element);
    }

    public int size() {
        return this.elements.size();
    }

    public Iterator<JsonElement> iterator() {
        return this.elements.iterator();
    }

    public JsonElement get(int i) {
        return this.elements.get(i);
    }

    public Number getAsNumber() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsNumber();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public String getAsString() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsString();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public double getAsDouble() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsDouble();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public BigDecimal getAsBigDecimal() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsBigDecimal();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public BigInteger getAsBigInteger() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsBigInteger();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public float getAsFloat() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsFloat();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public long getAsLong() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsLong();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public int getAsInt() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsInt();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public byte getAsByte() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsByte();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public char getAsCharacter() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsCharacter();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public short getAsShort() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsShort();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public boolean getAsBoolean() {
        Throwable th;
        if (this.elements.size() == 1) {
            return this.elements.get(0).getAsBoolean();
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        return o == this || ((o instanceof JsonArray) && ((JsonArray) o).elements.equals(this.elements));
    }

    public int hashCode() {
        return this.elements.hashCode();
    }
}
