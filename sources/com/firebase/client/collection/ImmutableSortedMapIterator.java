package com.firebase.client.collection;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

public class ImmutableSortedMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
    private final boolean isReverse;
    private final Stack<LLRBValueNode<K, V>> nodeStack;

    ImmutableSortedMapIterator(LLRBNode<K, V> root, K k, Comparator<K> comparator, boolean z) {
        Stack<LLRBValueNode<K, V>> stack;
        int cmp;
        K startKey = k;
        Comparator<K> comparator2 = comparator;
        boolean isReverse2 = z;
        new Stack<>();
        this.nodeStack = stack;
        this.isReverse = isReverse2;
        LLRBNode<K, V> lLRBNode = root;
        while (true) {
            LLRBNode<K, V> node = lLRBNode;
            if (!node.isEmpty()) {
                if (startKey != null) {
                    cmp = isReverse2 ? comparator2.compare(startKey, node.getKey()) : comparator2.compare(node.getKey(), startKey);
                } else {
                    cmp = 1;
                }
                if (cmp < 0) {
                    if (isReverse2) {
                        lLRBNode = node.getLeft();
                    } else {
                        lLRBNode = node.getRight();
                    }
                } else if (cmp == 0) {
                    LLRBValueNode<K, V> push = this.nodeStack.push((LLRBValueNode) node);
                    return;
                } else {
                    LLRBValueNode<K, V> push2 = this.nodeStack.push((LLRBValueNode) node);
                    if (isReverse2) {
                        lLRBNode = node.getRight();
                    } else {
                        lLRBNode = node.getLeft();
                    }
                }
            } else {
                return;
            }
        }
    }

    public boolean hasNext() {
        return this.nodeStack.size() > 0;
    }

    public Map.Entry<K, V> next() {
        Throwable th;
        Map.Entry<K, V> entry;
        try {
            LLRBValueNode<K, V> node = this.nodeStack.pop();
            new AbstractMap.SimpleEntry(node.getKey(), node.getValue());
            Map.Entry<K, V> entry2 = entry;
            if (!this.isReverse) {
                LLRBNode<K, V> right = node.getRight();
                while (true) {
                    LLRBNode<K, V> next = right;
                    if (next.isEmpty()) {
                        break;
                    }
                    LLRBValueNode<K, V> push = this.nodeStack.push((LLRBValueNode) next);
                    right = next.getLeft();
                }
            } else {
                for (LLRBNode<K, V> next2 = node.getLeft(); !next2.isEmpty(); next2 = next2.getRight()) {
                    LLRBValueNode<K, V> push2 = this.nodeStack.push((LLRBValueNode) next2);
                }
            }
            return entry2;
        } catch (EmptyStackException e) {
            EmptyStackException emptyStackException = e;
            Throwable th2 = th;
            new NoSuchElementException();
            throw th2;
        }
    }

    public void remove() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("remove called on immutable collection");
        throw th2;
    }
}
