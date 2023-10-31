package gnu.kawa.util;

public class GeneralHashTable<K, V> extends AbstractHashTable<HashNode<K, V>, K, V> {
    public GeneralHashTable() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GeneralHashTable(int capacity) {
        super(capacity);
    }

    /* access modifiers changed from: protected */
    public int getEntryHashCode(HashNode<K, V> entry) {
        return entry.hash;
    }

    /* access modifiers changed from: protected */
    public HashNode<K, V> getEntryNext(HashNode<K, V> entry) {
        return entry.next;
    }

    /* access modifiers changed from: protected */
    public void setEntryNext(HashNode<K, V> entry, HashNode<K, V> next) {
        entry.next = next;
    }

    /* access modifiers changed from: protected */
    public HashNode<K, V>[] allocEntries(int n) {
        return (HashNode[]) new HashNode[n];
    }

    /* access modifiers changed from: protected */
    public HashNode<K, V> makeEntry(K key, int hash, V value) {
        HashNode hashNode;
        new HashNode(key, value);
        HashNode hashNode2 = hashNode;
        hashNode2.hash = hash;
        return hashNode2;
    }

    public HashNode<K, V> getNode(Object key) {
        return (HashNode) super.getNode(key);
    }
}
