package com.firebase.client.collection;

import com.firebase.client.collection.ImmutableSortedMap;
import com.firebase.client.collection.LLRBNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RBTreeSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private Comparator<K> comparator;
    private LLRBNode<K, V> root;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ RBTreeSortedMap(LLRBNode x0, Comparator x1, C13161 r10) {
        this(x0, x1);
        C13161 r3 = r10;
    }

    RBTreeSortedMap(Comparator<K> comparator2) {
        this.root = LLRBEmptyNode.getInstance();
        this.comparator = comparator2;
    }

    private RBTreeSortedMap(LLRBNode<K, V> root2, Comparator<K> comparator2) {
        this.root = root2;
        this.comparator = comparator2;
    }

    /* access modifiers changed from: package-private */
    public LLRBNode<K, V> getRoot() {
        return this.root;
    }

    private LLRBNode<K, V> getNode(K k) {
        K key = k;
        LLRBNode<K, V> lLRBNode = this.root;
        while (true) {
            LLRBNode<K, V> node = lLRBNode;
            if (node.isEmpty()) {
                return null;
            }
            int cmp = this.comparator.compare(key, node.getKey());
            if (cmp < 0) {
                lLRBNode = node.getLeft();
            } else if (cmp == 0) {
                return node;
            } else {
                lLRBNode = node.getRight();
            }
        }
    }

    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    public V get(K key) {
        LLRBNode<K, V> node = getNode(key);
        return node != null ? node.getValue() : null;
    }

    public ImmutableSortedMap<K, V> remove(K k) {
        ImmutableSortedMap<K, V> immutableSortedMap;
        K key = k;
        if (!containsKey(key)) {
            return this;
        }
        new RBTreeSortedMap(this.root.remove(key, this.comparator).copy(null, null, LLRBNode.Color.BLACK, (LLRBNode) null, (LLRBNode) null), this.comparator);
        return immutableSortedMap;
    }

    public ImmutableSortedMap<K, V> insert(K key, V value) {
        ImmutableSortedMap<K, V> immutableSortedMap;
        new RBTreeSortedMap(this.root.insert(key, value, this.comparator).copy(null, null, LLRBNode.Color.BLACK, (LLRBNode) null, (LLRBNode) null), this.comparator);
        return immutableSortedMap;
    }

    public K getMinKey() {
        return this.root.getMin().getKey();
    }

    public K getMaxKey() {
        return this.root.getMax().getKey();
    }

    public int size() {
        return this.root.count();
    }

    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> visitor) {
        this.root.inOrderTraversal(visitor);
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        Iterator<Map.Entry<K, V>> it;
        new ImmutableSortedMapIterator(this.root, null, this.comparator, false);
        return it;
    }

    public Iterator<Map.Entry<K, V>> iteratorFrom(K key) {
        Iterator<Map.Entry<K, V>> it;
        new ImmutableSortedMapIterator(this.root, key, this.comparator, false);
        return it;
    }

    public Iterator<Map.Entry<K, V>> reverseIteratorFrom(K key) {
        Iterator<Map.Entry<K, V>> it;
        new ImmutableSortedMapIterator(this.root, key, this.comparator, true);
        return it;
    }

    public Iterator<Map.Entry<K, V>> reverseIterator() {
        Iterator<Map.Entry<K, V>> it;
        new ImmutableSortedMapIterator(this.root, null, this.comparator, true);
        return it;
    }

    public K getPredecessorKey(K k) {
        Throwable th;
        StringBuilder sb;
        K key = k;
        LLRBNode<K, V> node = this.root;
        LLRBNode<K, V> rightParent = null;
        while (!node.isEmpty()) {
            int cmp = this.comparator.compare(key, node.getKey());
            if (cmp == 0) {
                if (!node.getLeft().isEmpty()) {
                    LLRBNode<K, V> left = node.getLeft();
                    while (true) {
                        LLRBNode<K, V> node2 = left;
                        if (node2.getRight().isEmpty()) {
                            return node2.getKey();
                        }
                        left = node2.getRight();
                    }
                } else if (rightParent != null) {
                    return rightParent.getKey();
                } else {
                    return null;
                }
            } else if (cmp < 0) {
                node = node.getLeft();
            } else {
                rightParent = node;
                node = node.getRight();
            }
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Couldn't find predecessor key of non-present key: ").append(key).toString());
        throw th2;
    }

    public K getSuccessorKey(K k) {
        Throwable th;
        StringBuilder sb;
        K key = k;
        LLRBNode<K, V> node = this.root;
        LLRBNode<K, V> leftParent = null;
        while (!node.isEmpty()) {
            int cmp = this.comparator.compare(node.getKey(), key);
            if (cmp == 0) {
                if (!node.getRight().isEmpty()) {
                    LLRBNode<K, V> right = node.getRight();
                    while (true) {
                        LLRBNode<K, V> node2 = right;
                        if (node2.getLeft().isEmpty()) {
                            return node2.getKey();
                        }
                        right = node2.getLeft();
                    }
                } else if (leftParent != null) {
                    return leftParent.getKey();
                } else {
                    return null;
                }
            } else if (cmp < 0) {
                node = node.getRight();
            } else {
                leftParent = node;
                node = node.getLeft();
            }
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Couldn't find successor key of non-present key: ").append(key).toString());
        throw th2;
    }

    public Comparator<K> getComparator() {
        return this.comparator;
    }

    public static <A, B, C> RBTreeSortedMap<A, C> buildFrom(List<A> keys, Map<B, C> values, ImmutableSortedMap.Builder.KeyTranslator<A, B> translator, Comparator<A> comparator2) {
        return Builder.buildFrom(keys, values, translator, comparator2);
    }

    public static <A, B> RBTreeSortedMap<A, B> fromMap(Map<A, B> map, Comparator<A> comparator2) {
        List list;
        Map<A, B> values = map;
        new ArrayList(values.keySet());
        return Builder.buildFrom(list, values, ImmutableSortedMap.Builder.identityTranslator(), comparator2);
    }

    private static class Builder<A, B, C> {
        private final ImmutableSortedMap.Builder.KeyTranslator<A, B> keyTranslator;
        private final List<A> keys;
        private LLRBValueNode<A, C> leaf;
        private LLRBValueNode<A, C> root;
        private final Map<B, C> values;

        static class BooleanChunk {
            public int chunkSize;
            public boolean isOne;

            BooleanChunk() {
            }
        }

        static class Base1_2 implements Iterable<BooleanChunk> {
            /* access modifiers changed from: private */
            public final int length;
            /* access modifiers changed from: private */
            public long value;

            public Base1_2(int size) {
                int toCalc = size + 1;
                this.length = (int) Math.floor(Math.log((double) toCalc) / Math.log(2.0d));
                this.value = ((long) toCalc) & (((long) Math.pow(2.0d, (double) this.length)) - 1);
            }

            public Iterator<BooleanChunk> iterator() {
                Iterator<BooleanChunk> it;
                new Iterator<BooleanChunk>(this) {
                    private int current = (this.this$0.length - 1);
                    final /* synthetic */ Base1_2 this$0;

                    {
                        this.this$0 = r6;
                    }

                    public boolean hasNext() {
                        return this.current >= 0;
                    }

                    public BooleanChunk next() {
                        BooleanChunk booleanChunk;
                        long result = this.this$0.value & ((long) (1 << this.current));
                        new BooleanChunk();
                        BooleanChunk next = booleanChunk;
                        next.isOne = result == 0;
                        next.chunkSize = (int) Math.pow(2.0d, (double) this.current);
                        this.current--;
                        return next;
                    }

                    public void remove() {
                    }
                };
                return it;
            }
        }

        private Builder(List<A> keys2, Map<B, C> values2, ImmutableSortedMap.Builder.KeyTranslator<A, B> translator) {
            this.keys = keys2;
            this.values = values2;
            this.keyTranslator = translator;
        }

        private C getValue(A key) {
            return this.values.get(this.keyTranslator.translate(key));
        }

        private LLRBNode<A, C> buildBalancedTree(int i, int i2) {
            LLRBNode<A, C> lLRBNode;
            LLRBNode<A, C> lLRBNode2;
            int start = i;
            int size = i2;
            if (size == 0) {
                return LLRBEmptyNode.getInstance();
            }
            if (size == 1) {
                A key = this.keys.get(start);
                new LLRBBlackValueNode(key, getValue(key), (LLRBNode) null, (LLRBNode) null);
                return lLRBNode2;
            }
            int half = size / 2;
            int middle = start + half;
            LLRBNode<A, C> left = buildBalancedTree(start, half);
            LLRBNode<A, C> right = buildBalancedTree(middle + 1, half);
            A key2 = this.keys.get(middle);
            new LLRBBlackValueNode(key2, getValue(key2), left, right);
            return lLRBNode;
        }

        private void buildPennant(LLRBNode.Color color, int chunkSize, int i) {
            LLRBValueNode<A, C> lLRBValueNode;
            LLRBValueNode<A, C> node;
            LLRBValueNode<A, C> lLRBValueNode2;
            int start = i;
            LLRBNode<A, C> treeRoot = buildBalancedTree(start + 1, chunkSize - 1);
            A key = this.keys.get(start);
            if (color == LLRBNode.Color.RED) {
                new LLRBRedValueNode(key, getValue(key), (LLRBNode) null, treeRoot);
                node = lLRBValueNode2;
            } else {
                new LLRBBlackValueNode(key, getValue(key), (LLRBNode) null, treeRoot);
                node = lLRBValueNode;
            }
            if (this.root == null) {
                this.root = node;
                this.leaf = node;
                return;
            }
            this.leaf.setLeft(node);
            this.leaf = node;
        }

        public static <A, B, C> RBTreeSortedMap<A, C> buildFrom(List<A> list, Map<B, C> values2, ImmutableSortedMap.Builder.KeyTranslator<A, B> translator, Comparator<A> comparator) {
            Builder builder;
            Base1_2 base1_2;
            List<A> keys2 = list;
            Comparator<A> comparator2 = comparator;
            new Builder(keys2, values2, translator);
            Builder builder2 = builder;
            Collections.sort(keys2, comparator2);
            new Base1_2(keys2.size());
            Iterator<BooleanChunk> iter = base1_2.iterator();
            int index = keys2.size();
            while (iter.hasNext()) {
                BooleanChunk next = iter.next();
                index -= next.chunkSize;
                if (next.isOne) {
                    builder2.buildPennant(LLRBNode.Color.BLACK, next.chunkSize, index);
                } else {
                    builder2.buildPennant(LLRBNode.Color.BLACK, next.chunkSize, index);
                    index -= next.chunkSize;
                    builder2.buildPennant(LLRBNode.Color.RED, next.chunkSize, index);
                }
            }
            RBTreeSortedMap<A, C> rBTreeSortedMap = r13;
            RBTreeSortedMap<A, C> rBTreeSortedMap2 = new RBTreeSortedMap<>(builder2.root == null ? LLRBEmptyNode.getInstance() : builder2.root, comparator2, (C13161) null);
            return rBTreeSortedMap;
        }
    }
}
