package com.firebase.client.realtime.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class StringListReader extends Reader {
    private int charPos;
    private boolean closed = false;
    private boolean frozen = false;
    private int markedCharPos = this.charPos;
    private int markedStringListPos = this.stringListPos;
    private int stringListPos;
    private List<String> strings = null;

    public StringListReader() {
        List<String> list;
        new ArrayList();
        this.strings = list;
    }

    public void addString(String str) {
        Throwable th;
        String string = str;
        if (this.frozen) {
            Throwable th2 = th;
            new IllegalStateException("Trying to add string after reading");
            throw th2;
        } else if (string.length() > 0) {
            boolean add = this.strings.add(string);
        }
    }

    public void freeze() {
        Throwable th;
        if (this.frozen) {
            Throwable th2 = th;
            new IllegalStateException("Trying to freeze frozen StringListReader");
            throw th2;
        }
        this.frozen = true;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder builder = sb;
        for (String string : this.strings) {
            StringBuilder append = builder.append(string);
        }
        return builder.toString();
    }

    public void reset() throws IOException {
        this.charPos = this.markedCharPos;
        this.stringListPos = this.markedStringListPos;
    }

    private String currentString() {
        return this.stringListPos < this.strings.size() ? this.strings.get(this.stringListPos) : null;
    }

    private int currentStringRemainingChars() {
        String current = currentString();
        return current == null ? 0 : current.length() - this.charPos;
    }

    private void checkState() throws IOException {
        Throwable th;
        Throwable th2;
        if (this.closed) {
            Throwable th3 = th2;
            new IOException("Stream already closed");
            throw th3;
        } else if (!this.frozen) {
            Throwable th4 = th;
            new IOException("Reader needs to be frozen before read operations can be called");
            throw th4;
        }
    }

    private long advance(long j) {
        long numChars = j;
        long advanced = 0;
        while (this.stringListPos < this.strings.size() && advanced < numChars) {
            int remainingStringChars = currentStringRemainingChars();
            long remainingChars = numChars - advanced;
            if (remainingChars < ((long) remainingStringChars)) {
                this.charPos = (int) (((long) this.charPos) + remainingChars);
                advanced += remainingChars;
            } else {
                advanced += (long) remainingStringChars;
                this.charPos = 0;
                this.stringListPos++;
            }
        }
        return advanced;
    }

    public int read(CharBuffer charBuffer) throws IOException {
        String current;
        CharBuffer target = charBuffer;
        checkState();
        int remaining = target.remaining();
        int total = 0;
        String currentString = currentString();
        while (true) {
            current = currentString;
            if (remaining > 0 && current != null) {
                int strLength = Math.min(current.length() - this.charPos, remaining);
                CharBuffer put = target.put(this.strings.get(this.stringListPos), this.charPos, this.charPos + strLength);
                remaining -= strLength;
                total += strLength;
                long advance = advance((long) strLength);
                currentString = currentString();
            }
        }
        if (total > 0 || current != null) {
            return total;
        }
        return -1;
    }

    public int read() throws IOException {
        checkState();
        String current = currentString();
        if (current == null) {
            return -1;
        }
        char c = current.charAt(this.charPos);
        long advance = advance(1);
        return c;
    }

    public long skip(long n) throws IOException {
        checkState();
        return advance(n);
    }

    public boolean ready() throws IOException {
        checkState();
        return true;
    }

    public boolean markSupported() {
        return true;
    }

    public void mark(int i) throws IOException {
        int i2 = i;
        checkState();
        this.markedCharPos = this.charPos;
        this.markedStringListPos = this.stringListPos;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        String current;
        char[] cbuf = cArr;
        int off = i;
        int len = i2;
        checkState();
        int charsCopied = 0;
        String currentString = currentString();
        while (true) {
            current = currentString;
            if (current != null && charsCopied < len) {
                int copyLength = Math.min(currentStringRemainingChars(), len - charsCopied);
                current.getChars(this.charPos, this.charPos + copyLength, cbuf, off + charsCopied);
                charsCopied += copyLength;
                long advance = advance((long) copyLength);
                currentString = currentString();
            }
        }
        if (charsCopied > 0 || current != null) {
            return charsCopied;
        }
        return -1;
    }

    public void close() throws IOException {
        checkState();
        this.closed = true;
    }
}
