package com.shaded.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    private Node _bufferHead;
    private Node _bufferTail;
    private int _bufferedEntryCount;
    private Object[] _freeBuffer;

    public ObjectBuffer() {
    }

    public Object[] resetAndStart() {
        _reset();
        if (this._freeBuffer == null) {
            return new Object[12];
        }
        return this._freeBuffer;
    }

    public Object[] appendCompletedChunk(Object[] objArr) {
        Node node;
        int i;
        Object[] objArr2 = objArr;
        new Node(objArr2);
        Node node2 = node;
        if (this._bufferHead == null) {
            Node node3 = node2;
            this._bufferTail = node3;
            this._bufferHead = node3;
        } else {
            this._bufferTail.linkNext(node2);
            this._bufferTail = node2;
        }
        int length = objArr2.length;
        this._bufferedEntryCount += length;
        if (length < 16384) {
            i = length + length;
        } else {
            i = length + (length >> 2);
        }
        return new Object[i];
    }

    public Object[] completeAndClearBuffer(Object[] objArr, int i) {
        int i2 = i;
        int i3 = i2 + this._bufferedEntryCount;
        Object[] objArr2 = new Object[i3];
        _copyTo(objArr2, i3, objArr, i2);
        return objArr2;
    }

    public <T> T[] completeAndClearBuffer(Object[] objArr, int i, Class<T> cls) {
        int i2 = i;
        int i3 = i2 + this._bufferedEntryCount;
        T[] tArr = (Object[]) Array.newInstance(cls, i3);
        _copyTo(tArr, i3, objArr, i2);
        _reset();
        return tArr;
    }

    public void completeAndClearBuffer(Object[] objArr, int i, List<Object> list) {
        Object[] objArr2 = objArr;
        int i2 = i;
        List<Object> list2 = list;
        Node node = this._bufferHead;
        while (true) {
            Node node2 = node;
            if (node2 == null) {
                break;
            }
            Object[] data = node2.getData();
            int length = data.length;
            for (int i3 = 0; i3 < length; i3++) {
                boolean add = list2.add(data[i3]);
            }
            node = node2.next();
        }
        for (int i4 = 0; i4 < i2; i4++) {
            boolean add2 = list2.add(objArr2[i4]);
        }
    }

    public int initialCapacity() {
        return this._freeBuffer == null ? 0 : this._freeBuffer.length;
    }

    public int bufferedSize() {
        return this._bufferedEntryCount;
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

    /* access modifiers changed from: protected */
    public final void _copyTo(Object obj, int i, Object[] objArr, int i2) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        int i3 = i;
        Object[] objArr2 = objArr;
        int i4 = i2;
        int i5 = 0;
        Node node = this._bufferHead;
        while (true) {
            Node node2 = node;
            if (node2 == null) {
                break;
            }
            Object[] data = node2.getData();
            int length = data.length;
            System.arraycopy(data, 0, obj2, i5, length);
            i5 += length;
            node = node2.next();
        }
        System.arraycopy(objArr2, 0, obj2, i5, i4);
        int i6 = i5 + i4;
        if (i6 != i3) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Should have gotten ").append(i3).append(" entries, got ").append(i6).toString());
            throw th2;
        }
    }

    static final class Node {
        final Object[] _data;
        Node _next;

        public Node(Object[] objArr) {
            this._data = objArr;
        }

        public Object[] getData() {
            return this._data;
        }

        public Node next() {
            return this._next;
        }

        public void linkNext(Node node) {
            Throwable th;
            Node node2 = node;
            if (this._next != null) {
                Throwable th2 = th;
                new IllegalStateException();
                throw th2;
            }
            this._next = node2;
        }
    }
}
