package com.shaded.fasterxml.jackson.databind.util;

public final class LinkedNode<T> {
    final LinkedNode<T> _next;
    final T _value;

    public LinkedNode(T t, LinkedNode<T> linkedNode) {
        this._value = t;
        this._next = linkedNode;
    }

    public LinkedNode<T> next() {
        return this._next;
    }

    public T value() {
        return this._value;
    }

    public static <ST> boolean contains(LinkedNode<ST> linkedNode, ST st) {
        ST st2 = st;
        for (LinkedNode<ST> linkedNode2 = linkedNode; linkedNode2 != null; linkedNode2 = linkedNode2.next()) {
            if (linkedNode2.value() == st2) {
                return true;
            }
        }
        return false;
    }
}
