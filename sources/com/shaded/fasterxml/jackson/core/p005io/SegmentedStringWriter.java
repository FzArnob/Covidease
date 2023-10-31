package com.shaded.fasterxml.jackson.core.p005io;

import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import com.shaded.fasterxml.jackson.core.util.TextBuffer;
import java.io.Writer;

/* renamed from: com.shaded.fasterxml.jackson.core.io.SegmentedStringWriter */
public final class SegmentedStringWriter extends Writer {
    protected final TextBuffer _buffer;

    public SegmentedStringWriter(BufferRecycler bufferRecycler) {
        TextBuffer textBuffer;
        new TextBuffer(bufferRecycler);
        this._buffer = textBuffer;
    }

    public Writer append(char c) {
        write((int) c);
        return this;
    }

    public Writer append(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        this._buffer.append(charSequence2, 0, charSequence2.length());
        return this;
    }

    public Writer append(CharSequence charSequence, int i, int i2) {
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        this._buffer.append(charSequence2, 0, charSequence2.length());
        return this;
    }

    public void close() {
    }

    public void flush() {
    }

    public void write(char[] cArr) {
        char[] cArr2 = cArr;
        this._buffer.append(cArr2, 0, cArr2.length);
    }

    public void write(char[] cArr, int i, int i2) {
        this._buffer.append(cArr, i, i2);
    }

    public void write(int i) {
        this._buffer.append((char) i);
    }

    public void write(String str) {
        String str2 = str;
        this._buffer.append(str2, 0, str2.length());
    }

    public void write(String str, int i, int i2) {
        this._buffer.append(str, i, i2);
    }

    public String getAndClear() {
        String contentsAsString = this._buffer.contentsAsString();
        this._buffer.releaseBuffers();
        return contentsAsString;
    }
}
