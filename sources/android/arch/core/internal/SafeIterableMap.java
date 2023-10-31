package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private Entry<K, V> mEnd;
    private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators;
    private int mSize = 0;
    /* access modifiers changed from: private */
    public Entry<K, V> mStart;

    interface SupportRemove<K, V> {
        void supportRemove(@NonNull Entry<K, V> entry);
    }

    public SafeIterableMap() {
        WeakHashMap<SupportRemove<K, V>, Boolean> weakHashMap;
        new WeakHashMap<>();
        this.mIterators = weakHashMap;
    }

    /* access modifiers changed from: protected */
    public Entry<K, V> get(K k) {
        Entry<K, V> currentNode;
        K k2 = k;
        Entry<K, V> entry = this.mStart;
        while (true) {
            currentNode = entry;
            if (currentNode != null && !currentNode.mKey.equals(k2)) {
                entry = currentNode.mNext;
            }
        }
        return currentNode;
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        K key = k;
        V v2 = v;
        Entry<K, V> entry = get(key);
        if (entry != null) {
            return entry.mValue;
        }
        Entry put = put(key, v2);
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.arch.core.internal.SafeIterableMap.Entry<K, V> put(@android.support.annotation.NonNull K r10, @android.support.annotation.NonNull V r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            android.arch.core.internal.SafeIterableMap$Entry r4 = new android.arch.core.internal.SafeIterableMap$Entry
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r3 = r4
            r4 = r0
            r8 = r4
            r4 = r8
            r5 = r8
            int r5 = r5.mSize
            r6 = 1
            int r5 = r5 + 1
            r4.mSize = r5
            r4 = r0
            android.arch.core.internal.SafeIterableMap$Entry<K, V> r4 = r4.mEnd
            if (r4 != 0) goto L_0x002b
            r4 = r0
            r5 = r3
            r4.mStart = r5
            r4 = r0
            r5 = r0
            android.arch.core.internal.SafeIterableMap$Entry<K, V> r5 = r5.mStart
            r4.mEnd = r5
            r4 = r3
            r0 = r4
        L_0x002a:
            return r0
        L_0x002b:
            r4 = r0
            android.arch.core.internal.SafeIterableMap$Entry<K, V> r4 = r4.mEnd
            r5 = r3
            r4.mNext = r5
            r4 = r3
            r5 = r0
            android.arch.core.internal.SafeIterableMap$Entry<K, V> r5 = r5.mEnd
            r4.mPrevious = r5
            r4 = r0
            r5 = r3
            r4.mEnd = r5
            r4 = r3
            r0 = r4
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.core.internal.SafeIterableMap.put(java.lang.Object, java.lang.Object):android.arch.core.internal.SafeIterableMap$Entry");
    }

    public V remove(@NonNull K key) {
        Entry<K, V> toRemove = get(key);
        if (toRemove == null) {
            return null;
        }
        this.mSize--;
        if (!this.mIterators.isEmpty()) {
            for (SupportRemove<K, V> supportRemove : this.mIterators.keySet()) {
                supportRemove.supportRemove(toRemove);
            }
        }
        if (toRemove.mPrevious != null) {
            toRemove.mPrevious.mNext = toRemove.mNext;
        } else {
            this.mStart = toRemove.mNext;
        }
        if (toRemove.mNext != null) {
            toRemove.mNext.mPrevious = toRemove.mPrevious;
        } else {
            this.mEnd = toRemove.mPrevious;
        }
        toRemove.mNext = null;
        toRemove.mPrevious = null;
        return toRemove.mValue;
    }

    public int size() {
        return this.mSize;
    }

    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        ListIterator<K, V> listIterator;
        new AscendingIterator<>(this.mStart, this.mEnd);
        ListIterator<K, V> iterator = listIterator;
        V put = this.mIterators.put(iterator, false);
        return iterator;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        Iterator<Map.Entry<K, V>> it;
        new DescendingIterator(this.mEnd, this.mStart);
        Iterator<Map.Entry<K, V>> it2 = it;
        Boolean put = this.mIterators.put(it2, false);
        return it2;
    }

    public SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions() {
        IteratorWithAdditions iteratorWithAdditions;
        new IteratorWithAdditions(this, (C00011) null);
        IteratorWithAdditions iteratorWithAdditions2 = iteratorWithAdditions;
        Boolean put = this.mIterators.put(iteratorWithAdditions2, false);
        return iteratorWithAdditions2;
    }

    public Map.Entry<K, V> eldest() {
        return this.mStart;
    }

    public Map.Entry<K, V> newest() {
        return this.mEnd;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == this) {
            return true;
        }
        if (!(obj2 instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap map = (SafeIterableMap) obj2;
        if (size() != map.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> iterator1 = iterator();
        Iterator iterator2 = map.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            Map.Entry<K, V> next1 = iterator1.next();
            Object next2 = iterator2.next();
            if ((next1 == null && next2 != null) || (next1 != null && !next1.equals(next2))) {
                return false;
            }
        }
        return !iterator1.hasNext() && !iterator2.hasNext();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder builder = sb;
        StringBuilder append = builder.append("[");
        Iterator<Map.Entry<K, V>> iterator = iterator();
        while (iterator.hasNext()) {
            StringBuilder append2 = builder.append(iterator.next().toString());
            if (iterator.hasNext()) {
                StringBuilder append3 = builder.append(", ");
            }
        }
        StringBuilder append4 = builder.append("]");
        return builder.toString();
    }

    private static abstract class ListIterator<K, V> implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
        Entry<K, V> mExpectedEnd;
        Entry<K, V> mNext;

        /* access modifiers changed from: package-private */
        public abstract Entry<K, V> backward(Entry<K, V> entry);

        /* access modifiers changed from: package-private */
        public abstract Entry<K, V> forward(Entry<K, V> entry);

        ListIterator(Entry<K, V> start, Entry<K, V> expectedEnd) {
            this.mExpectedEnd = expectedEnd;
            this.mNext = start;
        }

        public boolean hasNext() {
            return this.mNext != null;
        }

        public void supportRemove(@NonNull Entry<K, V> entry) {
            Entry<K, V> entry2 = entry;
            if (this.mExpectedEnd == entry2 && entry2 == this.mNext) {
                this.mNext = null;
                this.mExpectedEnd = null;
            }
            if (this.mExpectedEnd == entry2) {
                this.mExpectedEnd = backward(this.mExpectedEnd);
            }
            if (this.mNext == entry2) {
                this.mNext = nextNode();
            }
        }

        private Entry<K, V> nextNode() {
            if (this.mNext == this.mExpectedEnd || this.mExpectedEnd == null) {
                return null;
            }
            return forward(this.mNext);
        }

        public Map.Entry<K, V> next() {
            Map.Entry<K, V> result = this.mNext;
            this.mNext = nextNode();
            return result;
        }
    }

    static class AscendingIterator<K, V> extends ListIterator<K, V> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AscendingIterator(Entry<K, V> start, Entry<K, V> expectedEnd) {
            super(start, expectedEnd);
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> forward(Entry<K, V> entry) {
            return entry.mNext;
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> backward(Entry<K, V> entry) {
            return entry.mPrevious;
        }
    }

    private static class DescendingIterator<K, V> extends ListIterator<K, V> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        DescendingIterator(Entry<K, V> start, Entry<K, V> expectedEnd) {
            super(start, expectedEnd);
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> forward(Entry<K, V> entry) {
            return entry.mPrevious;
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> backward(Entry<K, V> entry) {
            return entry.mNext;
        }
    }

    private class IteratorWithAdditions implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
        private boolean mBeforeStart;
        private Entry<K, V> mCurrent;
        final /* synthetic */ SafeIterableMap this$0;

        private IteratorWithAdditions(SafeIterableMap safeIterableMap) {
            this.this$0 = safeIterableMap;
            this.mBeforeStart = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ IteratorWithAdditions(SafeIterableMap x0, C00011 r7) {
            this(x0);
            C00011 r2 = r7;
        }

        public void supportRemove(@NonNull Entry<K, V> entry) {
            if (entry == this.mCurrent) {
                this.mCurrent = this.mCurrent.mPrevious;
                this.mBeforeStart = this.mCurrent == null;
            }
        }

        public boolean hasNext() {
            if (this.mBeforeStart) {
                return this.this$0.mStart != null;
            }
            return (this.mCurrent == null || this.mCurrent.mNext == null) ? false : true;
        }

        public Map.Entry<K, V> next() {
            if (this.mBeforeStart) {
                this.mBeforeStart = false;
                this.mCurrent = this.this$0.mStart;
            } else {
                this.mCurrent = this.mCurrent != null ? this.mCurrent.mNext : null;
            }
            return this.mCurrent;
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        @NonNull
        final K mKey;
        Entry<K, V> mNext;
        Entry<K, V> mPrevious;
        @NonNull
        final V mValue;

        Entry(@NonNull K key, @NonNull V value) {
            this.mKey = key;
            this.mValue = value;
        }

        @NonNull
        public K getKey() {
            return this.mKey;
        }

        @NonNull
        public V getValue() {
            return this.mValue;
        }

        public V setValue(V v) {
            Throwable th;
            V v2 = v;
            Throwable th2 = th;
            new UnsupportedOperationException("An entry modification is not supported");
            throw th2;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append(this.mKey).append("=").append(this.mValue).toString();
        }

        public boolean equals(Object obj) {
            Object obj2 = obj;
            if (obj2 == this) {
                return true;
            }
            if (!(obj2 instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj2;
            return this.mKey.equals(entry.mKey) && this.mValue.equals(entry.mValue);
        }
    }
}
