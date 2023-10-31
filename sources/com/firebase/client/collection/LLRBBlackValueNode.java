package com.firebase.client.collection;

import com.firebase.client.collection.LLRBNode;

public class LLRBBlackValueNode<K, V> extends LLRBValueNode<K, V> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LLRBBlackValueNode(K key, V value, LLRBNode<K, V> left, LLRBNode<K, V> right) {
        super(key, value, left, right);
    }

    /* access modifiers changed from: protected */
    public LLRBNode.Color getColor() {
        return LLRBNode.Color.BLACK;
    }

    public boolean isRed() {
        return false;
    }

    /* access modifiers changed from: protected */
    public LLRBValueNode<K, V> copy(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2) {
        LLRBValueNode<K, V> lLRBValueNode;
        K key = k;
        V value = v;
        LLRBNode<K, V> left = lLRBNode;
        LLRBNode<K, V> right = lLRBNode2;
        new LLRBBlackValueNode(key == null ? getKey() : key, value == null ? getValue() : value, left == null ? getLeft() : left, right == null ? getRight() : right);
        return lLRBValueNode;
    }
}
