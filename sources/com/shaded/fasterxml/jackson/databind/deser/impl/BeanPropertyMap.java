package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BeanPropertyMap implements Iterable<SettableBeanProperty>, Serializable {
    private static final long serialVersionUID = 1;
    private final Bucket[] _buckets;
    private final int _hashMask;
    private int _nextBucketIndex = 0;
    private final int _size;

    public BeanPropertyMap(Collection<SettableBeanProperty> collection) {
        Bucket bucket;
        Collection<SettableBeanProperty> collection2 = collection;
        this._size = collection2.size();
        int findSize = findSize(this._size);
        this._hashMask = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (SettableBeanProperty next : collection2) {
            String name = next.getName();
            int hashCode = name.hashCode() & this._hashMask;
            Bucket bucket2 = bucket;
            int i = this._nextBucketIndex;
            this._nextBucketIndex = i + 1;
            new Bucket(bucketArr[hashCode], name, next, i);
            bucketArr[hashCode] = bucket2;
        }
        this._buckets = bucketArr;
    }

    private BeanPropertyMap(Bucket[] bucketArr, int i, int i2) {
        Bucket[] bucketArr2 = bucketArr;
        this._buckets = bucketArr2;
        this._size = i;
        this._hashMask = bucketArr2.length - 1;
        this._nextBucketIndex = i2;
    }

    public BeanPropertyMap withProperty(SettableBeanProperty settableBeanProperty) {
        BeanPropertyMap beanPropertyMap;
        Bucket bucket;
        BeanPropertyMap beanPropertyMap2;
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        int length = this._buckets.length;
        Bucket[] bucketArr = new Bucket[length];
        System.arraycopy(this._buckets, 0, bucketArr, 0, length);
        String name = settableBeanProperty2.getName();
        if (find(settableBeanProperty2.getName()) == null) {
            int hashCode = name.hashCode() & this._hashMask;
            Bucket bucket2 = bucket;
            int i = this._nextBucketIndex;
            this._nextBucketIndex = i + 1;
            new Bucket(bucketArr[hashCode], name, settableBeanProperty2, i);
            bucketArr[hashCode] = bucket2;
            new BeanPropertyMap(bucketArr, this._size + 1, this._nextBucketIndex);
            return beanPropertyMap2;
        }
        new BeanPropertyMap(bucketArr, length, this._nextBucketIndex);
        BeanPropertyMap beanPropertyMap3 = beanPropertyMap;
        beanPropertyMap3.replace(settableBeanProperty2);
        return beanPropertyMap3;
    }

    public BeanPropertyMap renameAll(NameTransformer nameTransformer) {
        ArrayList arrayList;
        BeanPropertyMap beanPropertyMap;
        JsonDeserializer<Object> unwrappingDeserializer;
        NameTransformer nameTransformer2 = nameTransformer;
        if (nameTransformer2 == null || nameTransformer2 == NameTransformer.NOP) {
            return this;
        }
        Iterator<SettableBeanProperty> it = iterator();
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        while (it.hasNext()) {
            SettableBeanProperty next = it.next();
            SettableBeanProperty withName = next.withName(nameTransformer2.transform(next.getName()));
            JsonDeserializer<Object> valueDeserializer = withName.getValueDeserializer();
            if (!(valueDeserializer == null || (unwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer2)) == valueDeserializer)) {
                withName = withName.withValueDeserializer(unwrappingDeserializer);
            }
            boolean add = arrayList2.add(withName);
        }
        new BeanPropertyMap(arrayList2);
        return beanPropertyMap;
    }

    public BeanPropertyMap assignIndexes() {
        int i = 0;
        Bucket[] bucketArr = this._buckets;
        int length = bucketArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Bucket bucket = bucketArr[i2];
            while (true) {
                Bucket bucket2 = bucket;
                if (bucket2 == null) {
                    break;
                }
                int i3 = i;
                i++;
                bucket2.value.assignIndex(i3);
                bucket = bucket2.next;
            }
        }
        return this;
    }

    private static final int findSize(int i) {
        int i2 = i;
        int i3 = 2;
        while (true) {
            int i4 = i3;
            if (i4 >= (i2 <= 32 ? i2 + i2 : i2 + (i2 >> 2))) {
                return i4;
            }
            i3 = i4 + i4;
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("Properties=[");
        int i = 0;
        SettableBeanProperty[] propertiesInInsertionOrder = getPropertiesInInsertionOrder();
        int length = propertiesInInsertionOrder.length;
        for (int i2 = 0; i2 < length; i2++) {
            SettableBeanProperty settableBeanProperty = propertiesInInsertionOrder[i2];
            if (settableBeanProperty != null) {
                int i3 = i;
                i++;
                if (i3 > 0) {
                    StringBuilder append2 = sb2.append(", ");
                }
                StringBuilder append3 = sb2.append(settableBeanProperty.getName());
                StringBuilder append4 = sb2.append('(');
                StringBuilder append5 = sb2.append(settableBeanProperty.getType());
                StringBuilder append6 = sb2.append(')');
            }
        }
        StringBuilder append7 = sb2.append(']');
        return sb2.toString();
    }

    public Iterator<SettableBeanProperty> iterator() {
        Iterator<SettableBeanProperty> it;
        new IteratorImpl(this._buckets);
        return it;
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[this._nextBucketIndex];
        Bucket[] bucketArr = this._buckets;
        int length = bucketArr.length;
        for (int i = 0; i < length; i++) {
            Bucket bucket = bucketArr[i];
            while (true) {
                Bucket bucket2 = bucket;
                if (bucket2 == null) {
                    break;
                }
                settableBeanPropertyArr[bucket2.index] = bucket2.value;
                bucket = bucket2.next;
            }
        }
        return settableBeanPropertyArr;
    }

    public int size() {
        return this._size;
    }

    public SettableBeanProperty find(String str) {
        String str2 = str;
        int hashCode = str2.hashCode() & this._hashMask;
        Bucket bucket = this._buckets[hashCode];
        if (bucket == null) {
            return null;
        }
        if (bucket.key == str2) {
            return bucket.value;
        }
        do {
            Bucket bucket2 = bucket.next;
            bucket = bucket2;
            if (bucket2 == null) {
                return _findWithEquals(str2, hashCode);
            }
        } while (bucket.key != str2);
        return bucket.value;
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        Bucket bucket;
        Throwable th;
        StringBuilder sb;
        Bucket bucket2;
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        String name = settableBeanProperty2.getName();
        int hashCode = name.hashCode() & (this._buckets.length - 1);
        Bucket bucket3 = null;
        int i = -1;
        Bucket bucket4 = this._buckets[hashCode];
        while (true) {
            Bucket bucket5 = bucket4;
            if (bucket5 == null) {
                break;
            }
            if (i >= 0 || !bucket5.key.equals(name)) {
                new Bucket(bucket3, bucket5.key, bucket5.value, bucket5.index);
                bucket3 = bucket2;
            } else {
                i = bucket5.index;
            }
            bucket4 = bucket5.next;
        }
        if (i < 0) {
            Throwable th2 = th;
            new StringBuilder();
            new NoSuchElementException(sb.append("No entry '").append(settableBeanProperty2).append("' found, can't replace").toString());
            throw th2;
        }
        new Bucket(bucket3, name, settableBeanProperty2, i);
        this._buckets[hashCode] = bucket;
    }

    public void remove(SettableBeanProperty settableBeanProperty) {
        Throwable th;
        StringBuilder sb;
        Bucket bucket;
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        String name = settableBeanProperty2.getName();
        int hashCode = name.hashCode() & (this._buckets.length - 1);
        Bucket bucket2 = null;
        boolean z = false;
        Bucket bucket3 = this._buckets[hashCode];
        while (true) {
            Bucket bucket4 = bucket3;
            if (bucket4 == null) {
                break;
            }
            if (z || !bucket4.key.equals(name)) {
                new Bucket(bucket2, bucket4.key, bucket4.value, bucket4.index);
                bucket2 = bucket;
            } else {
                z = true;
            }
            bucket3 = bucket4.next;
        }
        if (!z) {
            Throwable th2 = th;
            new StringBuilder();
            new NoSuchElementException(sb.append("No entry '").append(settableBeanProperty2).append("' found, can't remove").toString());
            throw th2;
        }
        this._buckets[hashCode] = bucket2;
    }

    private SettableBeanProperty _findWithEquals(String str, int i) {
        String str2 = str;
        Bucket bucket = this._buckets[i];
        while (true) {
            Bucket bucket2 = bucket;
            if (bucket2 == null) {
                return null;
            }
            if (str2.equals(bucket2.key)) {
                return bucket2.value;
            }
            bucket = bucket2.next;
        }
    }

    private static final class Bucket implements Serializable {
        private static final long serialVersionUID = 1;
        public final int index;
        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String str, SettableBeanProperty settableBeanProperty, int i) {
            this.next = bucket;
            this.key = str;
            this.value = settableBeanProperty;
            this.index = i;
        }
    }

    private static final class IteratorImpl implements Iterator<SettableBeanProperty> {
        private final Bucket[] _buckets;
        private Bucket _currentBucket;
        private int _nextBucketIndex;

        public IteratorImpl(Bucket[] bucketArr) {
            this._buckets = bucketArr;
            int i = 0;
            int length = this._buckets.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                int i2 = i;
                i++;
                Bucket bucket = this._buckets[i2];
                if (bucket != null) {
                    this._currentBucket = bucket;
                    break;
                }
            }
            this._nextBucketIndex = i;
        }

        public boolean hasNext() {
            return this._currentBucket != null;
        }

        public SettableBeanProperty next() {
            Bucket bucket;
            Throwable th;
            Bucket bucket2 = this._currentBucket;
            if (bucket2 == null) {
                Throwable th2 = th;
                new NoSuchElementException();
                throw th2;
            }
            Bucket bucket3 = bucket2.next;
            while (true) {
                bucket = bucket3;
                if (bucket != null || this._nextBucketIndex >= this._buckets.length) {
                    this._currentBucket = bucket;
                } else {
                    Bucket[] bucketArr = this._buckets;
                    int i = this._nextBucketIndex;
                    int i2 = i + 1;
                    this._nextBucketIndex = i2;
                    bucket3 = bucketArr[i];
                }
            }
            this._currentBucket = bucket;
            return bucket2.value;
        }

        public void remove() {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }
    }
}
