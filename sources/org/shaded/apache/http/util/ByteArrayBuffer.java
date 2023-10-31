package org.shaded.apache.http.util;

public final class ByteArrayBuffer {
    private byte[] buffer;
    private int len;

    public ByteArrayBuffer(int i) {
        Throwable th;
        int capacity = i;
        if (capacity < 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Buffer capacity may not be negative");
            throw th2;
        }
        this.buffer = new byte[capacity];
    }

    private void expand(int newlen) {
        byte[] newbuffer = new byte[Math.max(this.buffer.length << 1, newlen)];
        System.arraycopy(this.buffer, 0, newbuffer, 0, this.len);
        this.buffer = newbuffer;
    }

    public void append(byte[] bArr, int i, int i2) {
        Throwable th;
        byte[] b = bArr;
        int off = i;
        int len2 = i2;
        if (b != null) {
            if (off < 0 || off > b.length || len2 < 0 || off + len2 < 0 || off + len2 > b.length) {
                Throwable th2 = th;
                new IndexOutOfBoundsException();
                throw th2;
            } else if (len2 != 0) {
                int newlen = this.len + len2;
                if (newlen > this.buffer.length) {
                    expand(newlen);
                }
                System.arraycopy(b, off, this.buffer, this.len, len2);
                this.len = newlen;
            }
        }
    }

    public void append(int i) {
        int b = i;
        int newlen = this.len + 1;
        if (newlen > this.buffer.length) {
            expand(newlen);
        }
        this.buffer[this.len] = (byte) b;
        this.len = newlen;
    }

    public void append(char[] cArr, int i, int i2) {
        Throwable th;
        char[] b = cArr;
        int off = i;
        int len2 = i2;
        if (b != null) {
            if (off < 0 || off > b.length || len2 < 0 || off + len2 < 0 || off + len2 > b.length) {
                Throwable th2 = th;
                new IndexOutOfBoundsException();
                throw th2;
            } else if (len2 != 0) {
                int oldlen = this.len;
                int newlen = oldlen + len2;
                if (newlen > this.buffer.length) {
                    expand(newlen);
                }
                int i1 = off;
                for (int i22 = oldlen; i22 < newlen; i22++) {
                    this.buffer[i22] = (byte) b[i1];
                    i1++;
                }
                this.len = newlen;
            }
        }
    }

    public void append(CharArrayBuffer charArrayBuffer, int i, int i2) {
        CharArrayBuffer b = charArrayBuffer;
        int off = i;
        int len2 = i2;
        if (b != null) {
            append(b.buffer(), off, len2);
        }
    }

    public void clear() {
        this.len = 0;
    }

    public byte[] toByteArray() {
        byte[] b = new byte[this.len];
        if (this.len > 0) {
            System.arraycopy(this.buffer, 0, b, 0, this.len);
        }
        return b;
    }

    public int byteAt(int i) {
        return this.buffer[i];
    }

    public int capacity() {
        return this.buffer.length;
    }

    public int length() {
        return this.len;
    }

    public byte[] buffer() {
        return this.buffer;
    }

    public void setLength(int i) {
        Throwable th;
        int len2 = i;
        if (len2 < 0 || len2 > this.buffer.length) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        this.len = len2;
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public boolean isFull() {
        return this.len == this.buffer.length;
    }
}
