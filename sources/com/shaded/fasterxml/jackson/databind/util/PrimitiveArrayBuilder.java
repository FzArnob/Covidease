package com.shaded.fasterxml.jackson.databind.util;

public abstract class PrimitiveArrayBuilder<T> {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    protected Node<T> _bufferHead;
    protected Node<T> _bufferTail;
    protected int _bufferedEntryCount;
    protected T _freeBuffer;

    /* access modifiers changed from: protected */
    public abstract T _constructArray(int i);

    protected PrimitiveArrayBuilder() {
    }

    public T resetAndStart() {
        _reset();
        return this._freeBuffer == null ? _constructArray(12) : this._freeBuffer;
    }

    public final T appendCompletedChunk(T t, int i) {
        Node node;
        int i2;
        int i3 = i;
        new Node(t, i3);
        Node node2 = node;
        if (this._bufferHead == null) {
            Node node3 = node2;
            this._bufferTail = node3;
            this._bufferHead = node3;
        } else {
            this._bufferTail.linkNext(node2);
            this._bufferTail = node2;
        }
        this._bufferedEntryCount += i3;
        int i4 = i3;
        if (i4 < 16384) {
            i2 = i4 + i4;
        } else {
            i2 = i4 + (i4 >> 2);
        }
        return _constructArray(i2);
    }

    public T completeAndClearBuffer(T t, int i) {
        Throwable th;
        StringBuilder sb;
        T t2 = t;
        int i2 = i;
        int i3 = i2 + this._bufferedEntryCount;
        T _constructArray = _constructArray(i3);
        int i4 = 0;
        Node<T> node = this._bufferHead;
        while (true) {
            Node<T> node2 = node;
            if (node2 == null) {
                break;
            }
            i4 = node2.copyData(_constructArray, i4);
            node = node2.next();
        }
        System.arraycopy(t2, 0, _constructArray, i4, i2);
        int i5 = i4 + i2;
        if (i5 == i3) {
            return _constructArray;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Should have gotten ").append(i3).append(" entries, got ").append(i5).toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void _reset() {
        if (this._bufferTail != null) {
            this._freeBuffer = this._bufferTail.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    static final class Node<T> {
        final T _data;
        final int _dataLength;
        Node<T> _next;

        public Node(T t, int i) {
            this._data = t;
            this._dataLength = i;
        }

        public T getData() {
            return this._data;
        }

        public int copyData(T t, int i) {
            int i2 = i;
            System.arraycopy(this._data, 0, t, i2, this._dataLength);
            return i2 + this._dataLength;
        }

        public Node<T> next() {
            return this._next;
        }

        public void linkNext(Node<T> node) {
            Throwable th;
            Node<T> node2 = node;
            if (this._next != null) {
                Throwable th2 = th;
                new IllegalStateException();
                throw th2;
            }
            this._next = node2;
        }
    }
}
