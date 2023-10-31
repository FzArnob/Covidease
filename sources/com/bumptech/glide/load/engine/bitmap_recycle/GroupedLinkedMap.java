package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupedLinkedMap<K extends Poolable, V> {
    private final LinkedEntry<K, V> head;
    private final Map<K, LinkedEntry<K, V>> keyToEntry;

    GroupedLinkedMap() {
        LinkedEntry<K, V> linkedEntry;
        Map<K, LinkedEntry<K, V>> map;
        new LinkedEntry<>();
        this.head = linkedEntry;
        new HashMap();
        this.keyToEntry = map;
    }

    public void put(K k, V v) {
        LinkedEntry linkedEntry;
        K key = k;
        V value = v;
        LinkedEntry linkedEntry2 = this.keyToEntry.get(key);
        if (linkedEntry2 == null) {
            new LinkedEntry(key);
            linkedEntry2 = linkedEntry;
            makeTail(linkedEntry2);
            LinkedEntry<K, V> put = this.keyToEntry.put(key, linkedEntry2);
        } else {
            key.offer();
        }
        linkedEntry2.add(value);
    }

    public V get(K k) {
        LinkedEntry linkedEntry;
        K key = k;
        LinkedEntry linkedEntry2 = this.keyToEntry.get(key);
        if (linkedEntry2 == null) {
            new LinkedEntry(key);
            linkedEntry2 = linkedEntry;
            LinkedEntry<K, V> put = this.keyToEntry.put(key, linkedEntry2);
        } else {
            key.offer();
        }
        makeHead(linkedEntry2);
        return linkedEntry2.removeLast();
    }

    public V removeLast() {
        LinkedEntry<K, V> linkedEntry = this.head.prev;
        while (true) {
            LinkedEntry<K, V> last = linkedEntry;
            if (last.equals(this.head)) {
                return null;
            }
            GroupedLinkedMap<K, V> removed = last.removeLast();
            if (removed != null) {
                return removed;
            }
            removeEntry(last);
            LinkedEntry<K, V> remove = this.keyToEntry.remove(last.key);
            ((Poolable) last.key).offer();
            linkedEntry = last.prev;
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder("GroupedLinkedMap( ");
        StringBuilder sb2 = sb;
        boolean hadAtLeastOneItem = false;
        for (LinkedEntry<K, V> current = this.head.next; !current.equals(this.head); current = current.next) {
            hadAtLeastOneItem = true;
            StringBuilder append = sb2.append('{').append(current.key).append(':').append(current.size()).append("}, ");
        }
        if (hadAtLeastOneItem) {
            StringBuilder delete = sb2.delete(sb2.length() - 2, sb2.length());
        }
        return sb2.append(" )").toString();
    }

    private void makeHead(LinkedEntry<K, V> linkedEntry) {
        LinkedEntry<K, V> entry = linkedEntry;
        removeEntry(entry);
        entry.prev = this.head;
        entry.next = this.head.next;
        updateEntry(entry);
    }

    private void makeTail(LinkedEntry<K, V> linkedEntry) {
        LinkedEntry<K, V> entry = linkedEntry;
        removeEntry(entry);
        entry.prev = this.head.prev;
        entry.next = this.head;
        updateEntry(entry);
    }

    private static <K, V> void updateEntry(LinkedEntry<K, V> linkedEntry) {
        LinkedEntry<K, V> entry = linkedEntry;
        entry.next.prev = entry;
        entry.prev.next = entry;
    }

    private static <K, V> void removeEntry(LinkedEntry<K, V> linkedEntry) {
        LinkedEntry<K, V> entry = linkedEntry;
        entry.prev.next = entry.next;
        entry.next.prev = entry.prev;
    }

    private static class LinkedEntry<K, V> {
        /* access modifiers changed from: private */
        public final K key;
        LinkedEntry<K, V> next;
        LinkedEntry<K, V> prev;
        private List<V> values;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public LinkedEntry() {
            this((Object) null);
        }

        public LinkedEntry(K key2) {
            this.prev = this;
            this.next = this;
            this.key = key2;
        }

        public V removeLast() {
            int valueSize = size();
            return valueSize > 0 ? this.values.remove(valueSize - 1) : null;
        }

        public int size() {
            return this.values != null ? this.values.size() : 0;
        }

        public void add(V v) {
            List<V> list;
            V value = v;
            if (this.values == null) {
                new ArrayList();
                this.values = list;
            }
            boolean add = this.values.add(value);
        }
    }
}
