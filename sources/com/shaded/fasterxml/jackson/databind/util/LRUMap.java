package com.shaded.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUMap<K, V> extends LinkedHashMap<K, V> implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient int _jdkSerializeMaxEntries;
    protected final int _maxEntries;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LRUMap(int i, int i2) {
        super(i, 0.8f, true);
        this._maxEntries = i2;
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        Map.Entry<K, V> entry2 = entry;
        return size() > this._maxEntries;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        int readInt = objectInputStream.readInt();
        this._jdkSerializeMaxEntries = readInt;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this._jdkSerializeMaxEntries);
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        Object obj;
        new LRUMap(this._jdkSerializeMaxEntries, this._jdkSerializeMaxEntries);
        return obj;
    }
}
