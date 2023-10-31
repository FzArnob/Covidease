package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class AbstractWeakHashTable<K, V> extends AbstractHashTable<WEntry<K, V>, K, V> {
    ReferenceQueue<V> rqueue;

    /* access modifiers changed from: protected */
    public abstract K getKeyFromValue(V v);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AbstractWeakHashTable() {
        super(64);
        ReferenceQueue<V> referenceQueue;
        new ReferenceQueue<>();
        this.rqueue = referenceQueue;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AbstractWeakHashTable(int capacity) {
        super(capacity);
        ReferenceQueue<V> referenceQueue;
        new ReferenceQueue<>();
        this.rqueue = referenceQueue;
    }

    /* access modifiers changed from: protected */
    public int getEntryHashCode(WEntry<K, V> entry) {
        return entry.hash;
    }

    /* access modifiers changed from: protected */
    public WEntry<K, V> getEntryNext(WEntry<K, V> entry) {
        return entry.next;
    }

    /* access modifiers changed from: protected */
    public void setEntryNext(WEntry<K, V> entry, WEntry<K, V> next) {
        entry.next = next;
    }

    /* access modifiers changed from: protected */
    public WEntry<K, V>[] allocEntries(int n) {
        return (WEntry[]) new WEntry[n];
    }

    /* access modifiers changed from: protected */
    public V getValueIfMatching(WEntry<K, V> node, Object obj) {
        Object key = obj;
        AbstractWeakHashTable<K, V> val = node.getValue();
        if (val == null || !matches(getKeyFromValue(val), key)) {
            return null;
        }
        return val;
    }

    public V get(Object key, V defaultValue) {
        cleanup();
        return super.get(key, defaultValue);
    }

    public int hash(Object key) {
        return System.identityHashCode(key);
    }

    /* access modifiers changed from: protected */
    public boolean valuesEqual(V oldValue, V newValue) {
        return oldValue == newValue;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.kawa.util.AbstractWeakHashTable.WEntry<K, V> makeEntry(K r11, int r12, V r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            gnu.kawa.util.AbstractWeakHashTable$WEntry r4 = new gnu.kawa.util.AbstractWeakHashTable$WEntry
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r3
            r7 = r0
            r8 = r2
            r5.<init>(r6, r7, r8)
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.AbstractWeakHashTable.makeEntry(java.lang.Object, int, java.lang.Object):gnu.kawa.util.AbstractWeakHashTable$WEntry");
    }

    public V put(K key, V v) {
        AbstractWeakHashTable<K, V> value = v;
        cleanup();
        int hash = hash(key);
        int index = hashToIndex(hash);
        WEntry<K, V> first = ((WEntry[]) this.table)[index];
        WEntry<K, V> node = first;
        WEntry<K, V> prev = null;
        AbstractWeakHashTable<K, V> abstractWeakHashTable = null;
        while (node != null) {
            AbstractWeakHashTable<K, V> curValue = node.getValue();
            if (curValue == value) {
                return curValue;
            }
            WEntry<K, V> next = node.next;
            if (curValue == null || !valuesEqual(curValue, value)) {
                prev = node;
            } else {
                if (prev == null) {
                    ((WEntry[]) this.table)[index] = next;
                } else {
                    prev.next = next;
                }
                abstractWeakHashTable = curValue;
            }
            node = next;
        }
        int i = this.num_bindings + 1;
        int i2 = i;
        this.num_bindings = i;
        if (i2 >= ((WEntry[]) this.table).length) {
            rehash();
            index = hashToIndex(hash);
            first = ((WEntry[]) this.table)[index];
        }
        WEntry<K, V> node2 = makeEntry((Object) null, hash, value);
        node2.next = first;
        ((WEntry[]) this.table)[index] = node2;
        return abstractWeakHashTable;
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
        cleanup(this, this.rqueue);
    }

    static <Entry extends Map.Entry<K, V>, K, V> void cleanup(AbstractHashTable<Entry, ?, ?> abstractHashTable, ReferenceQueue<?> referenceQueue) {
        AbstractHashTable<Entry, ?, ?> map = abstractHashTable;
        ReferenceQueue<?> rqueue2 = referenceQueue;
        while (true) {
            Entry oldref = (Map.Entry) rqueue2.poll();
            if (oldref != null) {
                int index = map.hashToIndex(map.getEntryHashCode(oldref));
                Entry prev = null;
                Entry entry = map.table[index];
                while (true) {
                    Entry node = entry;
                    if (node == null) {
                        break;
                    }
                    Entry next = map.getEntryNext(node);
                    if (node != oldref) {
                        prev = node;
                        entry = next;
                    } else if (prev == null) {
                        map.table[index] = next;
                    } else {
                        map.setEntryNext(prev, next);
                    }
                }
                AbstractHashTable<Entry, ?, ?> abstractHashTable2 = map;
                abstractHashTable2.num_bindings--;
            } else {
                return;
            }
        }
    }

    public static class WEntry<K, V> extends WeakReference<V> implements Map.Entry<K, V> {
        public int hash;
        AbstractWeakHashTable<K, V> htable;
        public WEntry next;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public WEntry(V r8, gnu.kawa.util.AbstractWeakHashTable<K, V> r9, int r10) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r9
                r3 = r10
                r4 = r0
                r5 = r1
                r6 = r2
                java.lang.ref.ReferenceQueue<V> r6 = r6.rqueue
                r4.<init>(r5, r6)
                r4 = r0
                r5 = r2
                r4.htable = r5
                r4 = r0
                r5 = r3
                r4.hash = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.AbstractWeakHashTable.WEntry.<init>(java.lang.Object, gnu.kawa.util.AbstractWeakHashTable, int):void");
        }

        public K getKey() {
            V v = get();
            return v == null ? null : this.htable.getKeyFromValue(v);
        }

        public V getValue() {
            return get();
        }

        public V setValue(V v) {
            Throwable th;
            V v2 = v;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }
    }
}
