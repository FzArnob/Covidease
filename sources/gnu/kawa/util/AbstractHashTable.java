package gnu.kawa.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractHashTable<Entry extends Map.Entry<K, V>, K, V> extends AbstractMap<K, V> {
    public static final int DEFAULT_INITIAL_SIZE = 64;
    protected int mask;
    protected int num_bindings;
    protected Entry[] table;

    /* access modifiers changed from: protected */
    public abstract Entry[] allocEntries(int i);

    /* access modifiers changed from: protected */
    public abstract int getEntryHashCode(Entry entry);

    /* access modifiers changed from: protected */
    public abstract Entry getEntryNext(Entry entry);

    /* access modifiers changed from: protected */
    public abstract Entry makeEntry(K k, int i, V v);

    /* access modifiers changed from: protected */
    public abstract void setEntryNext(Entry entry, Entry entry2);

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AbstractHashTable() {
        this(64);
    }

    public AbstractHashTable(int capacity) {
        int log2Size = 4;
        while (capacity > (1 << log2Size)) {
            log2Size++;
        }
        int capacity2 = 1 << log2Size;
        this.table = allocEntries(capacity2);
        this.mask = capacity2 - 1;
    }

    public int hash(Object obj) {
        Object key = obj;
        return key == null ? 0 : key.hashCode();
    }

    /* access modifiers changed from: protected */
    public int hashToIndex(int i) {
        int hash = i;
        return (hash ^ (hash >>> 15)) & this.mask;
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object key, int hash, Entry entry) {
        Entry node = entry;
        return getEntryHashCode(node) == hash && matches(node.getKey(), key);
    }

    /* access modifiers changed from: protected */
    public boolean matches(K k, Object obj) {
        Object key1 = k;
        Object key2 = obj;
        return key1 == key2 || (key1 != null && key1.equals(key2));
    }

    public V get(Object key) {
        return get(key, (Object) null);
    }

    public Entry getNode(Object obj) {
        Object key = obj;
        int hash = hash(key);
        Entry entry = this.table[hashToIndex(hash)];
        while (true) {
            AbstractHashTable<Entry, K, V> node = entry;
            if (node == null) {
                return null;
            }
            if (matches(key, hash, node)) {
                return node;
            }
            entry = getEntryNext(node);
        }
    }

    public V get(Object key, V v) {
        V defaultValue = v;
        Entry node = getNode(key);
        return node == null ? defaultValue : node.getValue();
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        Entry[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        int newCapacity = 2 * oldCapacity;
        Entry[] newTable = allocEntries(newCapacity);
        this.table = newTable;
        this.mask = newCapacity - 1;
        int i = oldCapacity;
        while (true) {
            i--;
            if (i >= 0) {
                Entry chain = oldTable[i];
                if (!(chain == null || getEntryNext(chain) == null)) {
                    Entry prev = null;
                    do {
                        Entry node = chain;
                        chain = getEntryNext(node);
                        setEntryNext(node, prev);
                        prev = node;
                    } while (chain != null);
                    chain = prev;
                }
                Entry entry = chain;
                while (true) {
                    Entry element = entry;
                    if (element != null) {
                        Entry next = getEntryNext(element);
                        int j = hashToIndex(getEntryHashCode(element));
                        setEntryNext(element, newTable[j]);
                        newTable[j] = element;
                        entry = next;
                    }
                }
            } else {
                return;
            }
        }
    }

    public V put(K k, V value) {
        K key = k;
        return put(key, hash(key), value);
    }

    public V put(K k, int i, V v) {
        K key = k;
        int hash = i;
        V value = v;
        int index = hashToIndex(hash);
        Entry first = this.table[index];
        Entry entry = first;
        while (true) {
            Entry node = entry;
            if (node == null) {
                int i2 = this.num_bindings + 1;
                int i3 = i2;
                this.num_bindings = i2;
                if (i3 >= this.table.length) {
                    rehash();
                    index = hashToIndex(hash);
                    first = this.table[index];
                }
                Entry node2 = makeEntry(key, hash, value);
                setEntryNext(node2, first);
                this.table[index] = node2;
                return null;
            } else if (matches(key, hash, node)) {
                AbstractHashTable<Entry, K, V> oldValue = node.getValue();
                Object value2 = node.setValue(value);
                return oldValue;
            } else {
                entry = getEntryNext(node);
            }
        }
    }

    public V remove(Object obj) {
        Object key = obj;
        int hash = hash(key);
        int index = hashToIndex(hash);
        Entry prev = null;
        Entry entry = this.table[index];
        while (true) {
            Entry node = entry;
            if (node == null) {
                return null;
            }
            Entry next = getEntryNext(node);
            if (matches(key, hash, node)) {
                if (prev == null) {
                    this.table[index] = next;
                } else {
                    setEntryNext(prev, next);
                }
                this.num_bindings--;
                return node.getValue();
            }
            prev = node;
            entry = next;
        }
    }

    public void clear() {
        Entry[] t = this.table;
        int i = t.length;
        while (true) {
            i--;
            if (i >= 0) {
                Entry entry = t[i];
                while (true) {
                    Entry e = entry;
                    if (e == null) {
                        break;
                    }
                    Entry next = getEntryNext(e);
                    setEntryNext(e, (Entry) null);
                    entry = next;
                }
                t[i] = null;
            } else {
                this.num_bindings = 0;
                return;
            }
        }
    }

    public int size() {
        return this.num_bindings;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set;
        new AbstractEntrySet(this);
        return set;
    }

    static class AbstractEntrySet<Entry extends Map.Entry<K, V>, K, V> extends AbstractSet<Entry> {
        AbstractHashTable<Entry, K, V> htable;

        public AbstractEntrySet(AbstractHashTable<Entry, K, V> htable2) {
            this.htable = htable2;
        }

        public int size() {
            return this.htable.size();
        }

        public Iterator<Entry> iterator() {
            Iterator<Entry> it;
            new Iterator<Entry>(this) {
                int curIndex = -1;
                Entry currentEntry;
                Entry nextEntry;
                int nextIndex;
                Entry previousEntry;
                final /* synthetic */ AbstractEntrySet this$0;

                {
                    this.this$0 = r5;
                }

                public boolean hasNext() {
                    if (this.curIndex < 0) {
                        this.nextIndex = this.this$0.htable.table.length;
                        this.curIndex = this.nextIndex;
                        advance();
                    }
                    return this.nextEntry != null;
                }

                private void advance() {
                    while (this.nextEntry == null) {
                        int i = this.nextIndex - 1;
                        int i2 = i;
                        this.nextIndex = i;
                        if (i2 >= 0) {
                            this.nextEntry = this.this$0.htable.table[this.nextIndex];
                        } else {
                            return;
                        }
                    }
                }

                public Entry next() {
                    Throwable th;
                    if (this.nextEntry == null) {
                        Throwable th2 = th;
                        new NoSuchElementException();
                        throw th2;
                    }
                    this.previousEntry = this.currentEntry;
                    this.currentEntry = this.nextEntry;
                    this.curIndex = this.nextIndex;
                    this.nextEntry = this.this$0.htable.getEntryNext(this.currentEntry);
                    advance();
                    return this.currentEntry;
                }

                public void remove() {
                    Throwable th;
                    if (this.previousEntry == this.currentEntry) {
                        Throwable th2 = th;
                        new IllegalStateException();
                        throw th2;
                    }
                    if (this.previousEntry == null) {
                        this.this$0.htable.table[this.curIndex] = this.nextEntry;
                    } else {
                        this.this$0.htable.setEntryNext(this.previousEntry, this.nextEntry);
                    }
                    AbstractHashTable<Entry, K, V> abstractHashTable = this.this$0.htable;
                    abstractHashTable.num_bindings--;
                    this.previousEntry = this.currentEntry;
                }
            };
            return it;
        }
    }
}
