package gnu.mapping;

import java.lang.ref.WeakReference;

public class Table2D {
    private static Table2D instance;
    int log2Size;
    int mask;
    int num_bindings;
    Entry[] table;

    static {
        Table2D table2D;
        new Table2D();
        instance = table2D;
    }

    public static final Table2D getInstance() {
        return instance;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Table2D() {
        this(64);
    }

    public Table2D(int i) {
        int capacity = i;
        this.log2Size = 4;
        while (capacity > (1 << this.log2Size)) {
            this.log2Size++;
        }
        int capacity2 = 1 << this.log2Size;
        this.table = new Entry[capacity2];
        this.mask = capacity2 - 1;
    }

    public Object get(Object obj, Object obj2, Object obj3) {
        Object key1 = obj;
        Object key2 = obj2;
        Object defaultValue = obj3;
        Entry entry = lookup(key1, key2, System.identityHashCode(key1), System.identityHashCode(key2), false);
        return (entry == null || entry.value == entry) ? defaultValue : entry.value;
    }

    public boolean isBound(Object obj, Object obj2) {
        Object key1 = obj;
        Object key2 = obj2;
        Entry entry = lookup(key1, key2, System.identityHashCode(key1), System.identityHashCode(key2), false);
        return (entry == null || entry.value == entry) ? false : true;
    }

    public Object put(Object obj, Object obj2, Object newValue) {
        Object key1 = obj;
        Object key2 = obj2;
        Entry entry = lookup(key1, key2, System.identityHashCode(key1), System.identityHashCode(key2), true);
        Object oldValue = entry.getValue();
        entry.value = newValue;
        return oldValue;
    }

    public Object remove(Object obj, Object obj2) {
        Object key1 = obj;
        Object key2 = obj2;
        return remove(key1, key2, System.identityHashCode(key1) ^ System.identityHashCode(key2));
    }

    public Object remove(Object key1, Object key2, int hash) {
        return remove(key1, key2, hash);
    }

    public Object remove(Object obj, Object obj2, int hash1, int hash2) {
        Object key1 = obj;
        Object key2 = obj2;
        int index = (hash1 ^ hash2) & this.mask;
        Entry prev = null;
        Entry entry = this.table[index];
        while (true) {
            Entry e = entry;
            if (e == null) {
                return null;
            }
            Object k1 = e.key1;
            Object k2 = e.key2;
            boolean dead = false;
            if (k1 instanceof WeakReference) {
                k1 = ((WeakReference) k1).get();
                dead = k1 == null;
            }
            if (k2 instanceof WeakReference) {
                k2 = ((WeakReference) k2).get();
                dead = k2 == null;
            }
            Entry next = e.chain;
            Object oldValue = e.value;
            boolean matches = k1 == key1 && k2 == key2;
            if (dead || matches) {
                if (prev == null) {
                    this.table[index] = next;
                } else {
                    prev.chain = next;
                }
                this.num_bindings--;
                e.value = e;
            } else if (matches) {
                return oldValue;
            } else {
                prev = e;
            }
            entry = next;
        }
    }

    /* access modifiers changed from: package-private */
    public void rehash() {
        Entry[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        int newCapacity = 2 * oldCapacity;
        Entry[] newTable = new Entry[newCapacity];
        int newMask = newCapacity - 1;
        this.num_bindings = 0;
        int i = oldCapacity;
        while (true) {
            i--;
            if (i >= 0) {
                Entry entry = oldTable[i];
                while (true) {
                    Entry e = entry;
                    if (e != null) {
                        Object k1 = e.key1;
                        Object k2 = e.key2;
                        boolean dead = false;
                        if (k1 instanceof WeakReference) {
                            k1 = ((WeakReference) k1).get();
                            dead = k1 == null;
                        }
                        if (k2 instanceof WeakReference) {
                            k2 = ((WeakReference) k2).get();
                            dead = k2 == null;
                        }
                        Entry next = e.chain;
                        if (dead) {
                            e.value = e;
                        } else {
                            int index = (System.identityHashCode(k1) ^ System.identityHashCode(k2)) & newMask;
                            e.chain = newTable[index];
                            newTable[index] = e;
                            this.num_bindings++;
                        }
                        entry = next;
                    }
                }
            } else {
                this.table = newTable;
                this.log2Size++;
                this.mask = newMask;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public Entry lookup(Object obj, Object obj2, int hash1, int hash2, boolean z) {
        Entry entry;
        Object key1 = obj;
        Object key2 = obj2;
        boolean create = z;
        int index = (hash1 ^ hash2) & this.mask;
        Entry prev = null;
        Entry first = this.table[index];
        Entry entry2 = first;
        while (true) {
            Entry e = entry2;
            if (e != null) {
                Object k1 = e.key1;
                Object k2 = e.key2;
                boolean dead = false;
                if (k1 instanceof WeakReference) {
                    k1 = ((WeakReference) k1).get();
                    dead = k1 == null;
                }
                if (k2 instanceof WeakReference) {
                    k2 = ((WeakReference) k2).get();
                    boolean dead2 = k2 == null;
                    dead = true;
                }
                Entry next = e.chain;
                if (dead) {
                    if (prev == null) {
                        this.table[index] = next;
                    } else {
                        prev.chain = next;
                    }
                    this.num_bindings--;
                    e.value = e;
                } else if (k1 == key1 && k2 == key2) {
                    return e;
                } else {
                    prev = e;
                }
                entry2 = next;
            } else if (!create) {
                return null;
            } else {
                new Entry();
                Entry e2 = entry;
                Object key12 = wrapReference(key1);
                Object key22 = wrapReference(key2);
                e2.key1 = key12;
                e2.key2 = key22;
                this.num_bindings++;
                e2.chain = first;
                this.table[index] = e2;
                e2.value = e2;
                return e2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object wrapReference(Object obj) {
        Object obj2;
        Object obj3;
        Object key = obj;
        if (key == null || (key instanceof Symbol)) {
            obj2 = key;
        } else {
            obj2 = obj3;
            new WeakReference(key);
        }
        return obj2;
    }
}
