package com.shaded.fasterxml.jackson.core.util;

import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public final class ByteArrayBuilder extends OutputStream {
    static final int DEFAULT_BLOCK_ARRAY_SIZE = 40;
    private static final int INITIAL_BLOCK_SIZE = 500;
    private static final int MAX_BLOCK_SIZE = 262144;
    private static final byte[] NO_BYTES = new byte[0];
    private final BufferRecycler _bufferRecycler;
    private byte[] _currBlock;
    private int _currBlockPtr;
    private final LinkedList<byte[]> _pastBlocks;
    private int _pastLen;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ByteArrayBuilder() {
        this((BufferRecycler) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ByteArrayBuilder(BufferRecycler bufferRecycler) {
        this(bufferRecycler, 500);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ByteArrayBuilder(int i) {
        this((BufferRecycler) null, i);
    }

    public ByteArrayBuilder(BufferRecycler bufferRecycler, int i) {
        LinkedList<byte[]> linkedList;
        BufferRecycler bufferRecycler2 = bufferRecycler;
        int i2 = i;
        new LinkedList<>();
        this._pastBlocks = linkedList;
        this._bufferRecycler = bufferRecycler2;
        if (bufferRecycler2 == null) {
            this._currBlock = new byte[i2];
            return;
        }
        this._currBlock = bufferRecycler2.allocByteBuffer(BufferRecycler.ByteBufferType.WRITE_CONCAT_BUFFER);
    }

    public void reset() {
        this._pastLen = 0;
        this._currBlockPtr = 0;
        if (!this._pastBlocks.isEmpty()) {
            this._pastBlocks.clear();
        }
    }

    public void release() {
        reset();
        if (this._bufferRecycler != null && this._currBlock != null) {
            this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.WRITE_CONCAT_BUFFER, this._currBlock);
            this._currBlock = null;
        }
    }

    public void append(int i) {
        int i2 = i;
        if (this._currBlockPtr >= this._currBlock.length) {
            _allocMore();
        }
        byte[] bArr = this._currBlock;
        int i3 = this._currBlockPtr;
        int i4 = i3 + 1;
        this._currBlockPtr = i4;
        bArr[i3] = (byte) i2;
    }

    public void appendTwoBytes(int i) {
        int i2 = i;
        if (this._currBlockPtr + 1 < this._currBlock.length) {
            byte[] bArr = this._currBlock;
            int i3 = this._currBlockPtr;
            int i4 = i3 + 1;
            this._currBlockPtr = i4;
            bArr[i3] = (byte) (i2 >> 8);
            byte[] bArr2 = this._currBlock;
            int i5 = this._currBlockPtr;
            int i6 = i5 + 1;
            this._currBlockPtr = i6;
            bArr2[i5] = (byte) i2;
            return;
        }
        append(i2 >> 8);
        append(i2);
    }

    public void appendThreeBytes(int i) {
        int i2 = i;
        if (this._currBlockPtr + 2 < this._currBlock.length) {
            byte[] bArr = this._currBlock;
            int i3 = this._currBlockPtr;
            int i4 = i3 + 1;
            this._currBlockPtr = i4;
            bArr[i3] = (byte) (i2 >> 16);
            byte[] bArr2 = this._currBlock;
            int i5 = this._currBlockPtr;
            int i6 = i5 + 1;
            this._currBlockPtr = i6;
            bArr2[i5] = (byte) (i2 >> 8);
            byte[] bArr3 = this._currBlock;
            int i7 = this._currBlockPtr;
            int i8 = i7 + 1;
            this._currBlockPtr = i8;
            bArr3[i7] = (byte) i2;
            return;
        }
        append(i2 >> 16);
        append(i2 >> 8);
        append(i2);
    }

    public byte[] toByteArray() {
        Throwable th;
        StringBuilder sb;
        int i = this._pastLen + this._currBlockPtr;
        if (i == 0) {
            return NO_BYTES;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        Iterator it = this._pastBlocks.iterator();
        while (it.hasNext()) {
            byte[] bArr2 = (byte[]) it.next();
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i2, length);
            i2 += length;
        }
        System.arraycopy(this._currBlock, 0, bArr, i2, this._currBlockPtr);
        int i3 = i2 + this._currBlockPtr;
        if (i3 != i) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("Internal error: total len assumed to be ").append(i).append(", copied ").append(i3).append(" bytes").toString());
            throw th2;
        }
        if (!this._pastBlocks.isEmpty()) {
            reset();
        }
        return bArr;
    }

    public byte[] resetAndGetFirstSegment() {
        reset();
        return this._currBlock;
    }

    public byte[] finishCurrentSegment() {
        _allocMore();
        return this._currBlock;
    }

    public byte[] completeAndCoalesce(int i) {
        this._currBlockPtr = i;
        return toByteArray();
    }

    public byte[] getCurrentSegment() {
        return this._currBlock;
    }

    public void setCurrentSegmentLength(int i) {
        int i2 = i;
        this._currBlockPtr = i2;
    }

    public int getCurrentSegmentLength() {
        return this._currBlockPtr;
    }

    public void write(byte[] bArr) {
        byte[] bArr2 = bArr;
        write(bArr2, 0, bArr2.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        while (true) {
            int min = Math.min(this._currBlock.length - this._currBlockPtr, i4);
            if (min > 0) {
                System.arraycopy(bArr2, i3, this._currBlock, this._currBlockPtr, min);
                i3 += min;
                this._currBlockPtr += min;
                i4 -= min;
            }
            if (i4 > 0) {
                _allocMore();
            } else {
                return;
            }
        }
    }

    public void write(int i) {
        append(i);
    }

    public void close() {
    }

    public void flush() {
    }

    private void _allocMore() {
        this._pastLen += this._currBlock.length;
        int max = Math.max(this._pastLen >> 1, 1000);
        if (max > 262144) {
            max = 262144;
        }
        boolean add = this._pastBlocks.add(this._currBlock);
        this._currBlock = new byte[max];
        this._currBlockPtr = 0;
    }
}
