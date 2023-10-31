package gnu.kawa.util;

import java.util.Map;

public class HashNode<K, V> implements Map.Entry<K, V> {
    int hash;
    K key;
    public HashNode<K, V> next;
    V value;

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public V setValue(V value2) {
        HashNode<K, V> old = this.value;
        this.value = value2;
        return old;
    }

    public int hashCode() {
        return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
    }

    public HashNode(K key2, V value2) {
        this.key = key2;
        this.value = value2;
    }

    public V get(V v) {
        V v2 = v;
        return getValue();
    }

    public boolean equals(Object obj) {
        boolean z;
        Object o = obj;
        if (!(o instanceof HashNode)) {
            return false;
        }
        HashNode h2 = (HashNode) o;
        if (this.key != null ? this.key.equals(h2.key) : h2.key == null) {
            if (this.value != null ? this.value.equals(h2.value) : h2.value == null) {
                z = true;
                return z;
            }
        }
        z = false;
        return z;
    }
}
