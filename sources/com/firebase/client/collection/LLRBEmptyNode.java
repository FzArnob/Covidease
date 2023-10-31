package com.firebase.client.collection;

import com.firebase.client.collection.LLRBNode;
import java.util.Comparator;

public class LLRBEmptyNode<K, V> implements LLRBNode<K, V> {
    private static final LLRBEmptyNode INSTANCE;

    static {
        LLRBEmptyNode lLRBEmptyNode;
        new LLRBEmptyNode();
        INSTANCE = lLRBEmptyNode;
    }

    public static <K, V> LLRBEmptyNode<K, V> getInstance() {
        return INSTANCE;
    }

    private LLRBEmptyNode() {
    }

    public LLRBNode<K, V> copy(K k, V v, LLRBNode.Color color, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        K k2 = k;
        V v2 = v;
        LLRBNode.Color color2 = color;
        LLRBNode<K, V> lLRBNode3 = lLRBNode;
        LLRBNode<K, V> lLRBNode4 = lLRBNode2;
        return this;
    }

    public LLRBNode<K, V> insert(K key, V value, Comparator<K> comparator) {
        LLRBNode<K, V> lLRBNode;
        Comparator<K> comparator2 = comparator;
        new LLRBRedValueNode(key, value);
        return lLRBNode;
    }

    public LLRBNode<K, V> remove(K k, Comparator<K> comparator) {
        K k2 = k;
        Comparator<K> comparator2 = comparator;
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean isRed() {
        return false;
    }

    public K getKey() {
        return null;
    }

    public V getValue() {
        return null;
    }

    public LLRBNode<K, V> getLeft() {
        return this;
    }

    public LLRBNode<K, V> getRight() {
        return this;
    }

    public LLRBNode<K, V> getMin() {
        return this;
    }

    public LLRBNode<K, V> getMax() {
        return this;
    }

    public int count() {
        return 0;
    }

    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor) {
    }

    public boolean shortCircuitingInOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor2 = shortCircuitingNodeVisitor;
        return true;
    }

    public boolean shortCircuitingReverseOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor) {
        LLRBNode.ShortCircuitingNodeVisitor<K, V> shortCircuitingNodeVisitor2 = shortCircuitingNodeVisitor;
        return true;
    }
}
