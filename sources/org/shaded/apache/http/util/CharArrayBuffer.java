package org.shaded.apache.http.util;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import org.shaded.apache.http.protocol.HTTP;

public final class CharArrayBuffer {
    private char[] buffer;
    private int len;

    public CharArrayBuffer(int i) {
        Throwable th;
        int capacity = i;
        if (capacity < 0) {
            Throwable th2 = th;
            new IllegalArgumentException("Buffer capacity may not be negative");
            throw th2;
        }
        this.buffer = new char[capacity];
    }

    private void expand(int newlen) {
        char[] newbuffer = new char[Math.max(this.buffer.length << 1, newlen)];
        System.arraycopy(this.buffer, 0, newbuffer, 0, this.len);
        this.buffer = newbuffer;
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
                int newlen = this.len + len2;
                if (newlen > this.buffer.length) {
                    expand(newlen);
                }
                System.arraycopy(b, off, this.buffer, this.len, len2);
                this.len = newlen;
            }
        }
    }

    public void append(String str) {
        String str2 = str;
        if (str2 == null) {
            str2 = "null";
        }
        int strlen = str2.length();
        int newlen = this.len + strlen;
        if (newlen > this.buffer.length) {
            expand(newlen);
        }
        str2.getChars(0, strlen, this.buffer, this.len);
        this.len = newlen;
    }

    public void append(CharArrayBuffer charArrayBuffer, int i, int i2) {
        CharArrayBuffer b = charArrayBuffer;
        int off = i;
        int len2 = i2;
        if (b != null) {
            append(b.buffer, off, len2);
        }
    }

    public void append(CharArrayBuffer charArrayBuffer) {
        CharArrayBuffer b = charArrayBuffer;
        if (b != null) {
            append(b.buffer, 0, b.len);
        }
    }

    public void append(char c) {
        char ch = c;
        int newlen = this.len + 1;
        if (newlen > this.buffer.length) {
            expand(newlen);
        }
        this.buffer[this.len] = ch;
        this.len = newlen;
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
                int oldlen = this.len;
                int newlen = oldlen + len2;
                if (newlen > this.buffer.length) {
                    expand(newlen);
                }
                int i1 = off;
                for (int i22 = oldlen; i22 < newlen; i22++) {
                    this.buffer[i22] = (char) (b[i1] & Ev3Constants.Opcode.TST);
                    i1++;
                }
                this.len = newlen;
            }
        }
    }

    public void append(ByteArrayBuffer byteArrayBuffer, int i, int i2) {
        ByteArrayBuffer b = byteArrayBuffer;
        int off = i;
        int len2 = i2;
        if (b != null) {
            append(b.buffer(), off, len2);
        }
    }

    public void append(Object obj) {
        append(String.valueOf(obj));
    }

    public void clear() {
        this.len = 0;
    }

    public char[] toCharArray() {
        char[] b = new char[this.len];
        if (this.len > 0) {
            System.arraycopy(this.buffer, 0, b, 0, this.len);
        }
        return b;
    }

    public char charAt(int i) {
        return this.buffer[i];
    }

    public char[] buffer() {
        return this.buffer;
    }

    public int capacity() {
        return this.buffer.length;
    }

    public int length() {
        return this.len;
    }

    public void ensureCapacity(int i) {
        int required = i;
        if (required > 0 && required > this.buffer.length - this.len) {
            expand(this.len + required);
        }
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

    public int indexOf(int i, int i2, int i3) {
        int ch = i;
        int beginIndex = i2;
        int endIndex = i3;
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (endIndex > this.len) {
            endIndex = this.len;
        }
        if (beginIndex > endIndex) {
            return -1;
        }
        for (int i4 = beginIndex; i4 < endIndex; i4++) {
            if (this.buffer[i4] == ch) {
                return i4;
            }
        }
        return -1;
    }

    public int indexOf(int ch) {
        return indexOf(ch, 0, this.len);
    }

    public String substring(int i, int i2) {
        String str;
        Throwable th;
        Throwable th2;
        Throwable th3;
        int beginIndex = i;
        int endIndex = i2;
        if (beginIndex < 0) {
            Throwable th4 = th3;
            new IndexOutOfBoundsException();
            throw th4;
        } else if (endIndex > this.len) {
            Throwable th5 = th2;
            new IndexOutOfBoundsException();
            throw th5;
        } else if (beginIndex > endIndex) {
            Throwable th6 = th;
            new IndexOutOfBoundsException();
            throw th6;
        } else {
            new String(this.buffer, beginIndex, endIndex - beginIndex);
            return str;
        }
    }

    public String substringTrimmed(int i, int i2) {
        String str;
        Throwable th;
        Throwable th2;
        Throwable th3;
        int beginIndex = i;
        int endIndex = i2;
        if (beginIndex < 0) {
            Throwable th4 = th3;
            new IndexOutOfBoundsException();
            throw th4;
        } else if (endIndex > this.len) {
            Throwable th5 = th2;
            new IndexOutOfBoundsException();
            throw th5;
        } else if (beginIndex > endIndex) {
            Throwable th6 = th;
            new IndexOutOfBoundsException();
            throw th6;
        } else {
            while (beginIndex < endIndex && HTTP.isWhitespace(this.buffer[beginIndex])) {
                beginIndex++;
            }
            while (endIndex > beginIndex && HTTP.isWhitespace(this.buffer[endIndex - 1])) {
                endIndex--;
            }
            new String(this.buffer, beginIndex, endIndex - beginIndex);
            return str;
        }
    }

    public String toString() {
        String str;
        new String(this.buffer, 0, this.len);
        return str;
    }
}
