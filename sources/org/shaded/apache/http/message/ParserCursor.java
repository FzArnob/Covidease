package org.shaded.apache.http.message;

import org.shaded.apache.http.util.CharArrayBuffer;

public class ParserCursor {
    private final int lowerBound;
    private int pos;
    private final int upperBound;

    public ParserCursor(int i, int i2) {
        Throwable th;
        Throwable th2;
        int lowerBound2 = i;
        int upperBound2 = i2;
        if (lowerBound2 < 0) {
            Throwable th3 = th2;
            new IndexOutOfBoundsException("Lower bound cannot be negative");
            throw th3;
        } else if (lowerBound2 > upperBound2) {
            Throwable th4 = th;
            new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
            throw th4;
        } else {
            this.lowerBound = lowerBound2;
            this.upperBound = upperBound2;
            this.pos = lowerBound2;
        }
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public int getPos() {
        return this.pos;
    }

    public void updatePos(int i) {
        Throwable th;
        Throwable th2;
        int pos2 = i;
        if (pos2 < this.lowerBound) {
            Throwable th3 = th2;
            new IndexOutOfBoundsException();
            throw th3;
        } else if (pos2 > this.upperBound) {
            Throwable th4 = th;
            new IndexOutOfBoundsException();
            throw th4;
        } else {
            this.pos = pos2;
        }
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer;
        new CharArrayBuffer(16);
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append('[');
        buffer.append(Integer.toString(this.lowerBound));
        buffer.append('>');
        buffer.append(Integer.toString(this.pos));
        buffer.append('>');
        buffer.append(Integer.toString(this.upperBound));
        buffer.append(']');
        return buffer.toString();
    }
}
