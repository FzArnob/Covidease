package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!LinkedHashTreeMap.class.desiredAssertionStatus());
    private static final Comparator<Comparable> NATURAL_ORDER;
    Comparator<? super K> comparator;
    private LinkedHashTreeMap<K, V>.EntrySet entrySet;
    final Node<K, V> header;
    private LinkedHashTreeMap<K, V>.KeySet keySet;
    int modCount;
    int size;
    Node<K, V>[] table;
    int threshold;

    static {
        Comparator<Comparable> comparator2;
        new Comparator<Comparable>() {
            public int compare(Comparable a, Comparable b) {
                return a.compareTo(b);
            }
        };
        NATURAL_ORDER = comparator2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LinkedHashTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator2) {
        Node<K, V> node;
        Comparator<? super K> comparator3 = comparator2;
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator3 != null ? comparator3 : NATURAL_ORDER;
        new Node<>();
        this.header = node;
        this.table = new Node[16];
        this.threshold = (this.table.length / 2) + (this.table.length / 4);
    }

    public int size() {
        return this.size;
    }

    public V get(Object key) {
        Node<K, V> node = findByObject(key);
        return node != null ? node.value : null;
    }

    public boolean containsKey(Object key) {
        return findByObject(key) != null;
    }

    public V put(K k, V v) {
        Throwable th;
        K key = k;
        V value = v;
        if (key == null) {
            Throwable th2 = th;
            new NullPointerException("key == null");
            throw th2;
        }
        Node<K, V> created = find(key, true);
        LinkedHashTreeMap<K, V> result = created.value;
        created.value = value;
        return result;
    }

    public void clear() {
        Arrays.fill(this.table, (Object) null);
        this.size = 0;
        this.modCount++;
        Node<K, V> header2 = this.header;
        Node<K, V> node = header2.next;
        while (true) {
            Node<K, V> e = node;
            if (e != header2) {
                Node<K, V> next = e.next;
                e.prev = null;
                e.next = null;
                node = next;
            } else {
                Node<K, V> node2 = header2;
                header2.prev = node2;
                header2.next = node2;
                return;
            }
        }
    }

    public V remove(Object key) {
        Node<K, V> node = removeInternalByKey(key);
        return node != null ? node.value : null;
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> find(K k, boolean z) {
        Node<K, V> node;
        Node<K, V> created;
        Node<K, V> node2;
        Throwable th;
        StringBuilder sb;
        int compare;
        Comparable<Object> key = k;
        boolean create = z;
        Comparator<? super K> comparator2 = this.comparator;
        Node<K, V>[] table2 = this.table;
        int hash = secondaryHash(key.hashCode());
        int index = hash & (table2.length - 1);
        Node<K, V> nearest = table2[index];
        int comparison = 0;
        if (nearest != null) {
            Comparable<Object> comparableKey = comparator2 == NATURAL_ORDER ? key : null;
            while (true) {
                if (comparableKey != null) {
                    compare = comparableKey.compareTo(nearest.key);
                } else {
                    compare = comparator2.compare(key, nearest.key);
                }
                comparison = compare;
                if (comparison == 0) {
                    return nearest;
                }
                Node<K, V> child = comparison < 0 ? nearest.left : nearest.right;
                if (child == null) {
                    break;
                }
                nearest = child;
            }
        }
        if (!create) {
            return null;
        }
        Node<K, V> header2 = this.header;
        if (nearest != null) {
            new Node<>(nearest, key, hash, header2, header2.prev);
            created = node;
            if (comparison < 0) {
                nearest.left = created;
            } else {
                nearest.right = created;
            }
            rebalance(nearest, true);
        } else if (comparator2 != NATURAL_ORDER || (key instanceof Comparable)) {
            new Node<>(nearest, key, hash, header2, header2.prev);
            created = node2;
            table2[index] = created;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new ClassCastException(sb.append(key.getClass().getName()).append(" is not Comparable").toString());
            throw th2;
        }
        int i = this.size;
        int i2 = i;
        this.size = i + 1;
        if (i2 > this.threshold) {
            doubleCapacity();
        }
        this.modCount++;
        return created;
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> findByObject(Object obj) {
        Node<K, V> node;
        Object key = obj;
        if (key != null) {
            try {
                node = find(key, false);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                return null;
            }
        } else {
            node = null;
        }
        return node;
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> findByEntry(Map.Entry<?, ?> entry) {
        Map.Entry<?, ?> entry2 = entry;
        Node<K, V> mine = findByObject(entry2.getKey());
        return mine != null && equal(mine.value, entry2.getValue()) ? mine : null;
    }

    private boolean equal(Object obj, Object obj2) {
        Object a = obj;
        Object b = obj2;
        return a == b || (a != null && a.equals(b));
    }

    private static int secondaryHash(int i) {
        int h = i;
        int h2 = h ^ ((h >>> 20) ^ (h >>> 12));
        return (h2 ^ (h2 >>> 7)) ^ (h2 >>> 4);
    }

    /* access modifiers changed from: package-private */
    public void removeInternal(Node<K, V> node, boolean unlink) {
        Node<K, V> first;
        Node<K, V> node2 = node;
        if (unlink) {
            node2.prev.next = node2.next;
            node2.next.prev = node2.prev;
            node2.prev = null;
            node2.next = null;
        }
        Node<K, V> left = node2.left;
        Node<K, V> right = node2.right;
        Node<K, V> originalParent = node2.parent;
        if (left == null || right == null) {
            if (left != null) {
                replaceInParent(node2, left);
                node2.left = null;
            } else if (right != null) {
                replaceInParent(node2, right);
                node2.right = null;
            } else {
                replaceInParent(node2, (Node<K, V>) null);
            }
            rebalance(originalParent, false);
            this.size--;
            this.modCount++;
            return;
        }
        if (left.height > right.height) {
            first = left.last();
        } else {
            first = right.first();
        }
        Node<K, V> adjacent = first;
        removeInternal(adjacent, false);
        int leftHeight = 0;
        Node<K, V> left2 = node2.left;
        if (left2 != null) {
            leftHeight = left2.height;
            adjacent.left = left2;
            left2.parent = adjacent;
            node2.left = null;
        }
        int rightHeight = 0;
        Node<K, V> right2 = node2.right;
        if (right2 != null) {
            rightHeight = right2.height;
            adjacent.right = right2;
            right2.parent = adjacent;
            node2.right = null;
        }
        adjacent.height = Math.max(leftHeight, rightHeight) + 1;
        replaceInParent(node2, adjacent);
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> removeInternalByKey(Object key) {
        Node<K, V> node = findByObject(key);
        if (node != null) {
            removeInternal(node, true);
        }
        return node;
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> node2) {
        Throwable th;
        Node<K, V> node3 = node;
        Node<K, V> replacement = node2;
        Node<K, V> parent = node3.parent;
        node3.parent = null;
        if (replacement != null) {
            replacement.parent = parent;
        }
        if (parent == null) {
            this.table[node3.hash & (this.table.length - 1)] = replacement;
        } else if (parent.left == node3) {
            parent.left = replacement;
        } else if ($assertionsDisabled || parent.right == node3) {
            parent.right = replacement;
        } else {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
    }

    private void rebalance(Node<K, V> unbalanced, boolean z) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean insert = z;
        Node<K, V> node = unbalanced;
        while (true) {
            Node<K, V> node2 = node;
            if (node2 != null) {
                Node<K, V> left = node2.left;
                Node<K, V> right = node2.right;
                int leftHeight = left != null ? left.height : 0;
                int rightHeight = right != null ? right.height : 0;
                int delta = leftHeight - rightHeight;
                if (delta == -2) {
                    Node<K, V> rightLeft = right.left;
                    Node<K, V> rightRight = right.right;
                    int rightDelta = (rightLeft != null ? rightLeft.height : 0) - (rightRight != null ? rightRight.height : 0);
                    if (rightDelta == -1 || (rightDelta == 0 && !insert)) {
                        rotateLeft(node2);
                    } else if ($assertionsDisabled || rightDelta == 1) {
                        rotateRight(right);
                        rotateLeft(node2);
                    } else {
                        Throwable th4 = th3;
                        new AssertionError();
                        throw th4;
                    }
                    if (insert) {
                        return;
                    }
                } else if (delta == 2) {
                    Node<K, V> leftLeft = left.left;
                    Node<K, V> leftRight = left.right;
                    int leftDelta = (leftLeft != null ? leftLeft.height : 0) - (leftRight != null ? leftRight.height : 0);
                    if (leftDelta == 1 || (leftDelta == 0 && !insert)) {
                        rotateRight(node2);
                    } else if ($assertionsDisabled || leftDelta == -1) {
                        rotateLeft(left);
                        rotateRight(node2);
                    } else {
                        Throwable th5 = th2;
                        new AssertionError();
                        throw th5;
                    }
                    if (insert) {
                        return;
                    }
                } else if (delta == 0) {
                    node2.height = leftHeight + 1;
                    if (insert) {
                        return;
                    }
                } else if ($assertionsDisabled || delta == -1 || delta == 1) {
                    node2.height = Math.max(leftHeight, rightHeight) + 1;
                    if (!insert) {
                        return;
                    }
                } else {
                    Throwable th6 = th;
                    new AssertionError();
                    throw th6;
                }
                node = node2.parent;
            } else {
                return;
            }
        }
    }

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> root = node;
        Node<K, V> left = root.left;
        Node<K, V> pivot = root.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;
        root.right = pivotLeft;
        if (pivotLeft != null) {
            pivotLeft.parent = root;
        }
        replaceInParent(root, pivot);
        pivot.left = root;
        root.parent = pivot;
        root.height = Math.max(left != null ? left.height : 0, pivotLeft != null ? pivotLeft.height : 0) + 1;
        pivot.height = Math.max(root.height, pivotRight != null ? pivotRight.height : 0) + 1;
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> root = node;
        Node<K, V> pivot = root.left;
        Node<K, V> right = root.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;
        root.left = pivotRight;
        if (pivotRight != null) {
            pivotRight.parent = root;
        }
        replaceInParent(root, pivot);
        pivot.right = root;
        root.parent = pivot;
        root.height = Math.max(right != null ? right.height : 0, pivotRight != null ? pivotRight.height : 0) + 1;
        pivot.height = Math.max(root.height, pivotLeft != null ? pivotLeft.height : 0) + 1;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.EntrySet entrySet2;
        LinkedHashTreeMap<K, V>.EntrySet entrySet3;
        LinkedHashTreeMap<K, V>.EntrySet result = this.entrySet;
        if (result != null) {
            entrySet3 = result;
        } else {
            LinkedHashTreeMap<K, V>.EntrySet entrySet4 = entrySet2;
            new EntrySet(this);
            LinkedHashTreeMap<K, V>.EntrySet entrySet5 = entrySet4;
            entrySet3 = entrySet5;
            this.entrySet = entrySet5;
        }
        return entrySet3;
    }

    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.KeySet keySet2;
        LinkedHashTreeMap<K, V>.KeySet keySet3;
        LinkedHashTreeMap<K, V>.KeySet result = this.keySet;
        if (result != null) {
            keySet3 = result;
        } else {
            LinkedHashTreeMap<K, V>.KeySet keySet4 = keySet2;
            new KeySet(this);
            LinkedHashTreeMap<K, V>.KeySet keySet5 = keySet4;
            keySet3 = keySet5;
            this.keySet = keySet5;
        }
        return keySet3;
    }

    static final class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        int height;
        final K key;
        Node<K, V> left;
        Node<K, V> next;
        Node<K, V> parent;
        Node<K, V> prev;
        Node<K, V> right;
        V value;

        Node() {
            this.key = null;
            this.hash = -1;
            this.prev = this;
            this.next = this;
        }

        Node(Node<K, V> parent2, K key2, int hash2, Node<K, V> node, Node<K, V> node2) {
            Node<K, V> next2 = node;
            Node<K, V> prev2 = node2;
            this.parent = parent2;
            this.key = key2;
            this.hash = hash2;
            this.height = 1;
            this.next = next2;
            this.prev = prev2;
            prev2.next = this;
            next2.prev = this;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V value2) {
            Node<K, V> oldValue = this.value;
            this.value = value2;
            return oldValue;
        }

        public boolean equals(Object obj) {
            boolean z;
            Object o = obj;
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry other = (Map.Entry) o;
            if (this.key != null ? this.key.equals(other.getKey()) : other.getKey() == null) {
                if (this.value != null ? this.value.equals(other.getValue()) : other.getValue() == null) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        }

        public int hashCode() {
            int hashCode;
            int hashCode2 = this.key == null ? 0 : this.key.hashCode();
            if (this.value == null) {
                hashCode = 0;
            } else {
                hashCode = this.value.hashCode();
            }
            return hashCode2 ^ hashCode;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append(this.key).append("=").append(this.value).toString();
        }

        public Node<K, V> first() {
            Node<K, V> node = this;
            Node<K, V> node2 = node.left;
            while (true) {
                Node<K, V> child = node2;
                if (child == null) {
                    return node;
                }
                node = child;
                node2 = node.left;
            }
        }

        public Node<K, V> last() {
            Node<K, V> node = this;
            Node<K, V> node2 = node.right;
            while (true) {
                Node<K, V> child = node2;
                if (child == null) {
                    return node;
                }
                node = child;
                node2 = node.right;
            }
        }
    }

    private void doubleCapacity() {
        this.table = doubleCapacity(this.table);
        this.threshold = (this.table.length / 2) + (this.table.length / 4);
    }

    static <K, V> Node<K, V>[] doubleCapacity(Node<K, V>[] nodeArr) {
        AvlIterator avlIterator;
        AvlBuilder avlBuilder;
        AvlBuilder avlBuilder2;
        Node<K, V>[] oldTable = nodeArr;
        int oldCapacity = oldTable.length;
        Node<K, V>[] newTable = new Node[(oldCapacity * 2)];
        new AvlIterator();
        AvlIterator avlIterator2 = avlIterator;
        new AvlBuilder();
        AvlBuilder avlBuilder3 = avlBuilder;
        new AvlBuilder();
        AvlBuilder avlBuilder4 = avlBuilder2;
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> root = oldTable[i];
            if (root != null) {
                avlIterator2.reset(root);
                int leftSize = 0;
                int rightSize = 0;
                while (true) {
                    Node<K, V> next = avlIterator2.next();
                    Node<K, V> node = next;
                    if (next == null) {
                        break;
                    } else if ((node.hash & oldCapacity) == 0) {
                        leftSize++;
                    } else {
                        rightSize++;
                    }
                }
                avlBuilder3.reset(leftSize);
                avlBuilder4.reset(rightSize);
                avlIterator2.reset(root);
                while (true) {
                    Node<K, V> next2 = avlIterator2.next();
                    Node<K, V> node2 = next2;
                    if (next2 == null) {
                        break;
                    } else if ((node2.hash & oldCapacity) == 0) {
                        avlBuilder3.add(node2);
                    } else {
                        avlBuilder4.add(node2);
                    }
                }
                newTable[i] = leftSize > 0 ? avlBuilder3.root() : null;
                newTable[i + oldCapacity] = rightSize > 0 ? avlBuilder4.root() : null;
            }
        }
        return newTable;
    }

    static class AvlIterator<K, V> {
        private Node<K, V> stackTop;

        AvlIterator() {
        }

        /* access modifiers changed from: package-private */
        public void reset(Node<K, V> root) {
            Node<K, V> stackTop2 = null;
            Node<K, V> node = root;
            while (true) {
                Node<K, V> n = node;
                if (n != null) {
                    n.parent = stackTop2;
                    stackTop2 = n;
                    node = n.left;
                } else {
                    this.stackTop = stackTop2;
                    return;
                }
            }
        }

        public Node<K, V> next() {
            Node<K, V> stackTop2 = this.stackTop;
            if (stackTop2 == null) {
                return null;
            }
            Node<K, V> result = stackTop2;
            Node<K, V> stackTop3 = result.parent;
            result.parent = null;
            Node<K, V> node = result.right;
            while (true) {
                Node<K, V> n = node;
                if (n != null) {
                    n.parent = stackTop3;
                    stackTop3 = n;
                    node = n.left;
                } else {
                    this.stackTop = stackTop3;
                    return result;
                }
            }
        }
    }

    static final class AvlBuilder<K, V> {
        private int leavesSkipped;
        private int leavesToSkip;
        private int size;
        private Node<K, V> stack;

        AvlBuilder() {
        }

        /* access modifiers changed from: package-private */
        public void reset(int i) {
            int targetSize = i;
            this.leavesToSkip = ((Integer.highestOneBit(targetSize) * 2) - 1) - targetSize;
            this.size = 0;
            this.leavesSkipped = 0;
            this.stack = null;
        }

        /* access modifiers changed from: package-private */
        public void add(Node<K, V> node) {
            Node<K, V> node2 = node;
            node2.right = null;
            node2.parent = null;
            node2.left = null;
            node2.height = 1;
            if (this.leavesToSkip > 0 && (this.size & 1) == 0) {
                this.size++;
                this.leavesToSkip--;
                this.leavesSkipped++;
            }
            node2.parent = this.stack;
            this.stack = node2;
            this.size++;
            if (this.leavesToSkip > 0 && (this.size & 1) == 0) {
                this.size++;
                this.leavesToSkip--;
                this.leavesSkipped++;
            }
            int i = 4;
            while (true) {
                int scale = i;
                if ((this.size & (scale - 1)) == scale - 1) {
                    if (this.leavesSkipped == 0) {
                        Node<K, V> right = this.stack;
                        Node<K, V> center = right.parent;
                        Node<K, V> left = center.parent;
                        center.parent = left.parent;
                        this.stack = center;
                        center.left = left;
                        center.right = right;
                        center.height = right.height + 1;
                        left.parent = center;
                        right.parent = center;
                    } else if (this.leavesSkipped == 1) {
                        Node<K, V> right2 = this.stack;
                        Node<K, V> center2 = right2.parent;
                        this.stack = center2;
                        center2.right = right2;
                        center2.height = right2.height + 1;
                        right2.parent = center2;
                        this.leavesSkipped = 0;
                    } else if (this.leavesSkipped == 2) {
                        this.leavesSkipped = 0;
                    }
                    i = scale * 2;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Node<K, V> root() {
            Throwable th;
            Node<K, V> stackTop = this.stack;
            if (stackTop.parent == null) {
                return stackTop;
            }
            Throwable th2 = th;
            new IllegalStateException();
            throw th2;
        }
    }

    private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        int expectedModCount = this.this$0.modCount;
        Node<K, V> lastReturned = null;
        Node<K, V> next = this.this$0.header.next;
        final /* synthetic */ LinkedHashTreeMap this$0;

        LinkedTreeMapIterator(LinkedHashTreeMap linkedHashTreeMap) {
            this.this$0 = linkedHashTreeMap;
        }

        public final boolean hasNext() {
            return this.next != this.this$0.header;
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> nextNode() {
            Throwable th;
            Throwable th2;
            Node<K, V> e = this.next;
            if (e == this.this$0.header) {
                Throwable th3 = th2;
                new NoSuchElementException();
                throw th3;
            } else if (this.this$0.modCount != this.expectedModCount) {
                Throwable th4 = th;
                new ConcurrentModificationException();
                throw th4;
            } else {
                this.next = e.next;
                Node<K, V> node = e;
                Node<K, V> node2 = node;
                this.lastReturned = node2;
                return node;
            }
        }

        public final void remove() {
            Throwable th;
            if (this.lastReturned == null) {
                Throwable th2 = th;
                new IllegalStateException();
                throw th2;
            }
            this.this$0.removeInternal(this.lastReturned, true);
            this.lastReturned = null;
            this.expectedModCount = this.this$0.modCount;
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        final /* synthetic */ LinkedHashTreeMap this$0;

        EntrySet(LinkedHashTreeMap this$02) {
            this.this$0 = this$02;
        }

        public int size() {
            return this.this$0.size;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            Iterator<Map.Entry<K, V>> it;
            new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>(this) {
                final /* synthetic */ EntrySet this$1;

                {
                    EntrySet this$12 = r5;
                    this.this$1 = this$12;
                    LinkedHashTreeMap linkedHashTreeMap = this$12.this$0;
                }

                public Map.Entry<K, V> next() {
                    return nextNode();
                }
            };
            return it;
        }

        public boolean contains(Object obj) {
            Object o = obj;
            return (o instanceof Map.Entry) && this.this$0.findByEntry((Map.Entry) o) != null;
        }

        public boolean remove(Object obj) {
            Object o = obj;
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Node<K, V> node = this.this$0.findByEntry((Map.Entry) o);
            if (node == null) {
                return false;
            }
            this.this$0.removeInternal(node, true);
            return true;
        }

        public void clear() {
            this.this$0.clear();
        }
    }

    final class KeySet extends AbstractSet<K> {
        final /* synthetic */ LinkedHashTreeMap this$0;

        KeySet(LinkedHashTreeMap this$02) {
            this.this$0 = this$02;
        }

        public int size() {
            return this.this$0.size;
        }

        public Iterator<K> iterator() {
            Iterator<K> it;
            new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<K>(this) {
                final /* synthetic */ KeySet this$1;

                {
                    KeySet this$12 = r5;
                    this.this$1 = this$12;
                    LinkedHashTreeMap linkedHashTreeMap = this$12.this$0;
                }

                public K next() {
                    return nextNode().key;
                }
            };
            return it;
        }

        public boolean contains(Object o) {
            return this.this$0.containsKey(o);
        }

        public boolean remove(Object key) {
            return this.this$0.removeInternalByKey(key) != null;
        }

        public void clear() {
            this.this$0.clear();
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        Object obj;
        new LinkedHashMap(this);
        return obj;
    }
}
