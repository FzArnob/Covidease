package com.firebase.client.collection;

import com.firebase.client.collection.LLRBNode;
import java.util.Comparator;

public abstract class LLRBValueNode<K, V> implements LLRBNode<K, V> {
    private final K key;
    private LLRBNode<K, V> left;
    private final LLRBNode<K, V> right;
    private final V value;

    /* access modifiers changed from: protected */
    public abstract LLRBValueNode<K, V> copy(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2);

    /* access modifiers changed from: protected */
    public abstract LLRBNode.Color getColor();

    private static LLRBNode.Color oppositeColor(LLRBNode node) {
        return node.isRed() ? LLRBNode.Color.BLACK : LLRBNode.Color.RED;
    }

    LLRBValueNode(K key2, V value2, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        LLRBNode<K, V> left2 = lLRBNode;
        LLRBNode<K, V> right2 = lLRBNode2;
        this.key = key2;
        this.value = value2;
        this.left = left2 == null ? LLRBEmptyNode.getInstance() : left2;
        this.right = right2 == null ? LLRBEmptyNode.getInstance() : right2;
    }

    public LLRBNode<K, V> getLeft() {
        return this.left;
    }

    public LLRBNode<K, V> getRight() {
        return this.right;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public LLRBValueNode<K, V> copy(K k, V v, LLRBNode.Color color, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        LLRBValueNode<K, V> lLRBValueNode;
        LLRBValueNode<K, V> lLRBValueNode2;
        K key2 = k;
        V value2 = v;
        LLRBNode.Color color2 = color;
        LLRBNode<K, V> left2 = lLRBNode;
        LLRBNode<K, V> right2 = lLRBNode2;
        K newKey = key2 == null ? this.key : key2;
        V newValue = value2 == null ? this.value : value2;
        LLRBNode<K, V> newLeft = left2 == null ? this.left : left2;
        LLRBNode<K, V> newRight = right2 == null ? this.right : right2;
        if (color2 == LLRBNode.Color.RED) {
            new LLRBRedValueNode(newKey, newValue, newLeft, newRight);
            return lLRBValueNode2;
        }
        new LLRBBlackValueNode(newKey, newValue, newLeft, newRight);
        return lLRBValueNode;
    }

    public LLRBNode<K, V> insert(K k, V v, Comparator<K> comparator) {
        LLRBValueNode<K, V> n;
        K key2 = k;
        V value2 = v;
        Comparator<K> comparator2 = comparator;
        int cmp = comparator2.compare(key2, this.key);
        if (cmp < 0) {
            n = copy((Object) null, (Object) null, this.left.insert(key2, value2, comparator2), (LLRBNode<K, V>) null);
        } else if (cmp == 0) {
            n = copy(key2, value2, (LLRBNode) null, (LLRBNode) null);
        } else {
            n = copy((Object) null, (Object) null, (LLRBNode) null, this.right.insert(key2, value2, comparator2));
        }
        return n.fixUp();
    }

    public LLRBNode<K, V> remove(K k, Comparator<K> comparator) {
        LLRBValueNode<K, V> n;
        K key2 = k;
        Comparator<K> comparator2 = comparator;
        LLRBValueNode lLRBValueNode = this;
        if (comparator2.compare(key2, lLRBValueNode.key) < 0) {
            if (!lLRBValueNode.left.isEmpty() && !lLRBValueNode.left.isRed() && !((LLRBValueNode) lLRBValueNode.left).left.isRed()) {
                lLRBValueNode = lLRBValueNode.moveRedLeft();
            }
            n = lLRBValueNode.copy((Object) null, (Object) null, lLRBValueNode.left.remove(key2, comparator2), (LLRBNode<K, V>) null);
        } else {
            if (lLRBValueNode.left.isRed()) {
                lLRBValueNode = lLRBValueNode.rotateRight();
            }
            if (!lLRBValueNode.right.isEmpty() && !lLRBValueNode.right.isRed() && !((LLRBValueNode) lLRBValueNode.right).left.isRed()) {
                lLRBValueNode = lLRBValueNode.moveRedRight();
            }
            if (comparator2.compare(key2, lLRBValueNode.key) == 0) {
                if (lLRBValueNode.right.isEmpty()) {
                    return LLRBEmptyNode.getInstance();
                }
                LLRBNode<K, V> smallest = lLRBValueNode.right.getMin();
                lLRBValueNode = lLRBValueNode.copy(smallest.getKey(), smallest.getValue(), (LLRBNode) null, ((LLRBValueNode) lLRBValueNode.right).removeMin());
            }
            n = lLRBValueNode.copy((Object) null, (Object) null, (LLRBNode) null, lLRBValueNode.right.remove(key2, comparator2));
        }
        return n.fixUp();
    }

    public boolean isEmpty() {
        return false;
    }

    public LLRBNode<K, V> getMin() {
        if (!this.left.isEmpty()) {
            return this.left.getMin();
        }
        return this;
    }

    public LLRBNode<K, V> getMax() {
        if (!this.right.isEmpty()) {
            return this.right.getMax();
        }
        return this;
    }

    public int count() {
        return this.left.count() + 1 + this.right.count();
    }

    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor) {
        LLRBNode.NodeVisitor<K, V> visitor = nodeVisitor;
        this.left.inOrderTraversal(visitor);
        visitor.visitEntry(this.key, this.value);
        this.right.inOrderTraversal(visitor);
    }

    public boolean shortCircuitingInOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        LLRBNode.ShortCircuitingNodeVisitor<K, V> visitor = shortCircuitingNodeVisitor;
        if (!this.left.shortCircuitingInOrderTraversal(visitor) || !visitor.shouldContinue(this.key, this.value)) {
            return false;
        }
        return this.right.shortCircuitingInOrderTraversal(visitor);
    }

    public boolean shortCircuitingReverseOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        LLRBNode.ShortCircuitingNodeVisitor<K, V> visitor = shortCircuitingNodeVisitor;
        if (!this.right.shortCircuitingReverseOrderTraversal(visitor) || !visitor.shouldContinue(this.key, this.value)) {
            return false;
        }
        return this.left.shortCircuitingReverseOrderTraversal(visitor);
    }

    /* access modifiers changed from: package-private */
    public void setLeft(LLRBNode<K, V> left2) {
        LLRBNode<K, V> lLRBNode = left2;
        this.left = lLRBNode;
    }

    private LLRBNode<K, V> removeMin() {
        if (this.left.isEmpty()) {
            return LLRBEmptyNode.getInstance();
        }
        LLRBValueNode lLRBValueNode = this;
        if (!lLRBValueNode.getLeft().isRed() && !lLRBValueNode.getLeft().getLeft().isRed()) {
            lLRBValueNode = lLRBValueNode.moveRedLeft();
        }
        return lLRBValueNode.copy((Object) null, (Object) null, ((LLRBValueNode) lLRBValueNode.left).removeMin(), (LLRBNode) null).fixUp();
    }

    private LLRBValueNode<K, V> moveRedLeft() {
        LLRBValueNode<K, V> n = colorFlip();
        if (n.getRight().getLeft().isRed()) {
            n = n.copy((K) null, (V) null, (LLRBNode<K, V>) null, ((LLRBValueNode) n.getRight()).rotateRight()).rotateLeft().colorFlip();
        }
        return n;
    }

    private LLRBValueNode<K, V> moveRedRight() {
        LLRBValueNode<K, V> n = colorFlip();
        if (n.getLeft().getLeft().isRed()) {
            n = n.rotateRight().colorFlip();
        }
        return n;
    }

    private LLRBValueNode<K, V> fixUp() {
        LLRBValueNode lLRBValueNode = this;
        if (lLRBValueNode.right.isRed() && !lLRBValueNode.left.isRed()) {
            lLRBValueNode = lLRBValueNode.rotateLeft();
        }
        if (lLRBValueNode.left.isRed() && ((LLRBValueNode) lLRBValueNode.left).left.isRed()) {
            lLRBValueNode = lLRBValueNode.rotateRight();
        }
        if (lLRBValueNode.left.isRed() && lLRBValueNode.right.isRed()) {
            lLRBValueNode = lLRBValueNode.colorFlip();
        }
        return lLRBValueNode;
    }

    private LLRBValueNode<K, V> rotateLeft() {
        return (LLRBValueNode) this.right.copy(null, null, getColor(), copy((Object) null, (Object) null, LLRBNode.Color.RED, (LLRBNode) null, (LLRBNode) ((LLRBValueNode) this.right).left), (LLRBValueNode) null);
    }

    private LLRBValueNode<K, V> rotateRight() {
        return (LLRBValueNode) this.left.copy(null, null, getColor(), (LLRBNode) null, copy((Object) null, (Object) null, LLRBNode.Color.RED, (LLRBNode) ((LLRBValueNode) this.left).right, (LLRBNode) null));
    }

    private LLRBValueNode<K, V> colorFlip() {
        return copy((Object) null, (Object) null, oppositeColor(this), (LLRBNode) this.left.copy(null, null, oppositeColor(this.left), (LLRBNode) null, (LLRBNode) null), (LLRBNode) this.right.copy(null, null, oppositeColor(this.right), (LLRBNode) null, (LLRBNode) null));
    }
}
