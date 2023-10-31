package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.ser.SerializerCache;
import java.util.Map;

public class JsonSerializerMap {
    private final Bucket[] _buckets;
    private final int _size;

    public JsonSerializerMap(Map<SerializerCache.TypeKey, JsonSerializer<Object>> map) {
        Bucket bucket;
        Map<SerializerCache.TypeKey, JsonSerializer<Object>> map2 = map;
        int findSize = findSize(map2.size());
        this._size = findSize;
        int i = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (Map.Entry next : map2.entrySet()) {
            SerializerCache.TypeKey typeKey = (SerializerCache.TypeKey) next.getKey();
            int hashCode = typeKey.hashCode() & i;
            new Bucket(bucketArr[hashCode], typeKey, (JsonSerializer) next.getValue());
            bucketArr[hashCode] = bucket;
        }
        this._buckets = bucketArr;
    }

    private static final int findSize(int i) {
        int i2 = i;
        int i3 = 8;
        while (true) {
            int i4 = i3;
            if (i4 >= (i2 <= 64 ? i2 + i2 : i2 + (i2 >> 2))) {
                return i4;
            }
            i3 = i4 + i4;
        }
    }

    public int size() {
        return this._size;
    }

    public JsonSerializer<Object> find(SerializerCache.TypeKey typeKey) {
        SerializerCache.TypeKey typeKey2 = typeKey;
        Bucket bucket = this._buckets[typeKey2.hashCode() & (this._buckets.length - 1)];
        if (bucket == null) {
            return null;
        }
        if (typeKey2.equals(bucket.key)) {
            return bucket.value;
        }
        do {
            Bucket bucket2 = bucket.next;
            bucket = bucket2;
            if (bucket2 == null) {
                return null;
            }
        } while (!typeKey2.equals(bucket.key));
        return bucket.value;
    }

    private static final class Bucket {
        public final SerializerCache.TypeKey key;
        public final Bucket next;
        public final JsonSerializer<Object> value;

        public Bucket(Bucket bucket, SerializerCache.TypeKey typeKey, JsonSerializer<Object> jsonSerializer) {
            this.next = bucket;
            this.key = typeKey;
            this.value = jsonSerializer;
        }
    }
}
