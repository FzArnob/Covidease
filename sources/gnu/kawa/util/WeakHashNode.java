package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

public class WeakHashNode<K, V> extends WeakReference<K> implements Map.Entry<K, V> {
    public int hash;
    public WeakHashNode<K, V> next;
    public V value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WeakHashNode(K key, ReferenceQueue<K> q, int hash2) {
        super(key, q);
        this.hash = hash2;
    }

    public K getKey() {
        return get();
    }

    public V getValue() {
        return this.value;
    }

    public V setValue(V value2) {
        WeakHashNode<K, V> old = this.value;
        this.value = value2;
        return old;
    }
}
