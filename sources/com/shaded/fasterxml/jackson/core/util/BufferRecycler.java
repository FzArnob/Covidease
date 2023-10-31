package com.shaded.fasterxml.jackson.core.util;

public class BufferRecycler {
    public static final int DEFAULT_WRITE_CONCAT_BUFFER_LEN = 2000;
    protected final byte[][] _byteBuffers = new byte[ByteBufferType.values().length][];
    protected final char[][] _charBuffers = new char[CharBufferType.values().length][];

    public enum ByteBufferType {
        ;
        
        protected final int size;

        private ByteBufferType(int i) {
            String str = r8;
            int i2 = r9;
            this.size = i;
        }
    }

    public enum CharBufferType {
        ;
        
        protected final int size;

        private CharBufferType(int i) {
            String str = r8;
            int i2 = r9;
            this.size = i;
        }
    }

    public BufferRecycler() {
    }

    public final byte[] allocByteBuffer(ByteBufferType byteBufferType) {
        ByteBufferType byteBufferType2 = byteBufferType;
        int ordinal = byteBufferType2.ordinal();
        byte[] bArr = this._byteBuffers[ordinal];
        if (bArr == null) {
            bArr = balloc(byteBufferType2.size);
        } else {
            this._byteBuffers[ordinal] = null;
        }
        return bArr;
    }

    public final void releaseByteBuffer(ByteBufferType byteBufferType, byte[] bArr) {
        this._byteBuffers[byteBufferType.ordinal()] = bArr;
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType) {
        return allocCharBuffer(charBufferType, 0);
    }

    public final char[] allocCharBuffer(CharBufferType charBufferType, int i) {
        CharBufferType charBufferType2 = charBufferType;
        int i2 = i;
        if (charBufferType2.size > i2) {
            i2 = charBufferType2.size;
        }
        int ordinal = charBufferType2.ordinal();
        char[] cArr = this._charBuffers[ordinal];
        if (cArr == null || cArr.length < i2) {
            cArr = calloc(i2);
        } else {
            this._charBuffers[ordinal] = null;
        }
        return cArr;
    }

    public final void releaseCharBuffer(CharBufferType charBufferType, char[] cArr) {
        this._charBuffers[charBufferType.ordinal()] = cArr;
    }

    private byte[] balloc(int i) {
        return new byte[i];
    }

    private char[] calloc(int i) {
        return new char[i];
    }
}
