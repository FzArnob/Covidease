package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!LinkedTreeMap.class.desiredAssertionStatus());
    private static final Comparator<Comparable> NATURAL_ORDER;
    Comparator<? super K> comparator;
    private LinkedTreeMap<K, V>.EntrySet entrySet;
    final Node<K, V> header;
    private LinkedTreeMap<K, V>.KeySet keySet;
    int modCount;
    Node<K, V> root;
    int size;

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
    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator2) {
        Node<K, V> node;
        Comparator<? super K> comparator3 = comparator2;
        this.size = 0;
        this.modCount = 0;
        new Node<>();
        this.header = node;
        this.comparator = comparator3 != null ? comparator3 : NATURAL_ORDER;
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
        LinkedTreeMap<K, V> result = created.value;
        created.value = value;
        return result;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        Node<K, V> header2 = this.header;
        Node<K, V> node = header2;
        header2.prev = node;
        header2.next = node;
    }

    public V remove(Object key) {
        Node<K, V> node = removeInternalByKey(key);
        return node != null ? node.value : null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.internal.LinkedTreeMap.Node<K, V> find(K r16, boolean r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r8 = r0
            java.util.Comparator<? super K> r8 = r8.comparator
            r3 = r8
            r8 = r0
            com.google.gson.internal.LinkedTreeMap$Node<K, V> r8 = r8.root
            r4 = r8
            r8 = 0
            r5 = r8
            r8 = r4
            if (r8 == 0) goto L_0x0043
            r8 = r3
            java.util.Comparator<java.lang.Comparable> r9 = NATURAL_ORDER
            if (r8 != r9) goto L_0x002d
            r8 = r1
            java.lang.Comparable r8 = (java.lang.Comparable) r8
        L_0x001a:
            r6 = r8
        L_0x001b:
            r8 = r6
            if (r8 == 0) goto L_0x002f
            r8 = r6
            r9 = r4
            K r9 = r9.key
            int r8 = r8.compareTo(r9)
        L_0x0026:
            r5 = r8
            r8 = r5
            if (r8 != 0) goto L_0x0039
            r8 = r4
            r0 = r8
        L_0x002c:
            return r0
        L_0x002d:
            r8 = 0
            goto L_0x001a
        L_0x002f:
            r8 = r3
            r9 = r1
            r10 = r4
            K r10 = r10.key
            int r8 = r8.compare(r9, r10)
            goto L_0x0026
        L_0x0039:
            r8 = r5
            if (r8 >= 0) goto L_0x0049
            r8 = r4
            com.google.gson.internal.LinkedTreeMap$Node<K, V> r8 = r8.left
        L_0x003f:
            r7 = r8
            r8 = r7
            if (r8 != 0) goto L_0x004d
        L_0x0043:
            r8 = r2
            if (r8 != 0) goto L_0x0050
            r8 = 0
            r0 = r8
            goto L_0x002c
        L_0x0049:
            r8 = r4
            com.google.gson.internal.LinkedTreeMap$Node<K, V> r8 = r8.right
            goto L_0x003f
        L_0x004d:
            r8 = r7
            r4 = r8
            goto L_0x001b
        L_0x0050:
            r8 = r0
            com.google.gson.internal.LinkedTreeMap$Node<K, V> r8 = r8.header
            r6 = r8
            r8 = r4
            if (r8 != 0) goto L_0x00b7
            r8 = r3
            java.util.Comparator<java.lang.Comparable> r9 = NATURAL_ORDER
            if (r8 != r9) goto L_0x008a
            r8 = r1
            boolean r8 = r8 instanceof java.lang.Comparable
            if (r8 != 0) goto L_0x008a
            java.lang.ClassCastException r8 = new java.lang.ClassCastException
            r14 = r8
            r8 = r14
            r9 = r14
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r14 = r10
            r10 = r14
            r11 = r14
            r11.<init>()
            r11 = r1
            java.lang.Class r11 = r11.getClass()
            java.lang.String r11 = r11.getName()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = " is not Comparable"
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r8
        L_0x008a:
            com.google.gson.internal.LinkedTreeMap$Node r8 = new com.google.gson.internal.LinkedTreeMap$Node
            r14 = r8
            r8 = r14
            r9 = r14
            r10 = r4
            r11 = r1
            r12 = r6
            r13 = r6
            com.google.gson.internal.LinkedTreeMap$Node<K, V> r13 = r13.prev
            r9.<init>(r10, r11, r12, r13)
            r7 = r8
            r8 = r0
            r9 = r7
            r8.root = r9
        L_0x009d:
            r8 = r0
            r14 = r8
            r8 = r14
            r9 = r14
            int r9 = r9.size
            r10 = 1
            int r9 = r9 + 1
            r8.size = r9
            r8 = r0
            r14 = r8
            r8 = r14
            r9 = r14
            int r9 = r9.modCount
            r10 = 1
            int r9 = r9 + 1
            r8.modCount = r9
            r8 = r7
            r0 = r8
            goto L_0x002c
        L_0x00b7:
            com.google.gson.internal.LinkedTreeMap$Node r8 = new com.google.gson.internal.LinkedTreeMap$Node
            r14 = r8
            r8 = r14
            r9 = r14
            r10 = r4
            r11 = r1
            r12 = r6
            r13 = r6
            com.google.gson.internal.LinkedTreeMap$Node<K, V> r13 = r13.prev
            r9.<init>(r10, r11, r12, r13)
            r7 = r8
            r8 = r5
            if (r8 >= 0) goto L_0x00d4
            r8 = r4
            r9 = r7
            r8.left = r9
        L_0x00cd:
            r8 = r0
            r9 = r4
            r10 = 1
            r8.rebalance(r9, r10)
            goto L_0x009d
        L_0x00d4:
            r8 = r4
            r9 = r7
            r8.right = r9
            goto L_0x00cd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedTreeMap.find(java.lang.Object, boolean):com.google.gson.internal.LinkedTreeMap$Node");
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

    /* access modifiers changed from: package-private */
    public void removeInternal(Node<K, V> node, boolean unlink) {
        Node<K, V> first;
        Node<K, V> node2 = node;
        if (unlink) {
            node2.prev.next = node2.next;
            node2.next.prev = node2.prev;
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
            this.root = replacement;
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
        Node<K, V> root2 = node;
        Node<K, V> left = root2.left;
        Node<K, V> pivot = root2.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;
        root2.right = pivotLeft;
        if (pivotLeft != null) {
            pivotLeft.parent = root2;
        }
        replaceInParent(root2, pivot);
        pivot.left = root2;
        root2.parent = pivot;
        root2.height = Math.max(left != null ? left.height : 0, pivotLeft != null ? pivotLeft.height : 0) + 1;
        pivot.height = Math.max(root2.height, pivotRight != null ? pivotRight.height : 0) + 1;
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> root2 = node;
        Node<K, V> pivot = root2.left;
        Node<K, V> right = root2.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;
        root2.left = pivotRight;
        if (pivotRight != null) {
            pivotRight.parent = root2;
        }
        replaceInParent(root2, pivot);
        pivot.right = root2;
        root2.parent = pivot;
        root2.height = Math.max(right != null ? right.height : 0, pivotRight != null ? pivotRight.height : 0) + 1;
        pivot.height = Math.max(root2.height, pivotLeft != null ? pivotLeft.height : 0) + 1;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.EntrySet entrySet2;
        LinkedTreeMap<K, V>.EntrySet entrySet3;
        LinkedTreeMap<K, V>.EntrySet result = this.entrySet;
        if (result != null) {
            entrySet3 = result;
        } else {
            LinkedTreeMap<K, V>.EntrySet entrySet4 = entrySet2;
            new EntrySet(this);
            LinkedTreeMap<K, V>.EntrySet entrySet5 = entrySet4;
            entrySet3 = entrySet5;
            this.entrySet = entrySet5;
        }
        return entrySet3;
    }

    public Set<K> keySet() {
        LinkedTreeMap<K, V>.KeySet keySet2;
        LinkedTreeMap<K, V>.KeySet keySet3;
        LinkedTreeMap<K, V>.KeySet result = this.keySet;
        if (result != null) {
            keySet3 = result;
        } else {
            LinkedTreeMap<K, V>.KeySet keySet4 = keySet2;
            new KeySet(this);
            LinkedTreeMap<K, V>.KeySet keySet5 = keySet4;
            keySet3 = keySet5;
            this.keySet = keySet5;
        }
        return keySet3;
    }

    static final class Node<K, V> implements Map.Entry<K, V> {
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
            this.prev = this;
            this.next = this;
        }

        Node(Node<K, V> parent2, K key2, Node<K, V> node, Node<K, V> node2) {
            Node<K, V> next2 = node;
            Node<K, V> prev2 = node2;
            this.parent = parent2;
            this.key = key2;
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

    private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        int expectedModCount = this.this$0.modCount;
        Node<K, V> lastReturned = null;
        Node<K, V> next = this.this$0.header.next;
        final /* synthetic */ LinkedTreeMap this$0;

        LinkedTreeMapIterator(LinkedTreeMap linkedTreeMap) {
            this.this$0 = linkedTreeMap;
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

    class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        final /* synthetic */ LinkedTreeMap this$0;

        EntrySet(LinkedTreeMap this$02) {
            this.this$0 = this$02;
        }

        public int size() {
            return this.this$0.size;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            Iterator<Map.Entry<K, V>> it;
            new LinkedTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>(this) {
                final /* synthetic */ EntrySet this$1;

                {
                    EntrySet this$12 = r5;
                    this.this$1 = this$12;
                    LinkedTreeMap linkedTreeMap = this$12.this$0;
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
        final /* synthetic */ LinkedTreeMap this$0;

        KeySet(LinkedTreeMap this$02) {
            this.this$0 = this$02;
        }

        public int size() {
            return this.this$0.size;
        }

        public Iterator<K> iterator() {
            Iterator<K> it;
            new LinkedTreeMap<K, V>.LinkedTreeMapIterator<K>(this) {
                final /* synthetic */ KeySet this$1;

                {
                    KeySet this$12 = r5;
                    this.this$1 = this$12;
                    LinkedTreeMap linkedTreeMap = this$12.this$0;
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
