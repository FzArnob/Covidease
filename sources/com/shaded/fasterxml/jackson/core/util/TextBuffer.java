package com.shaded.fasterxml.jackson.core.util;

import com.shaded.fasterxml.jackson.core.p005io.NumberInput;
import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import java.math.BigDecimal;
import java.util.ArrayList;

public final class TextBuffer {
    static final int MAX_SEGMENT_LEN = 262144;
    static final int MIN_SEGMENT_LEN = 1000;
    static final char[] NO_CHARS = new char[0];
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments = false;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    public TextBuffer(BufferRecycler bufferRecycler) {
        this._allocator = bufferRecycler;
    }

    public void releaseBuffers() {
        if (this._allocator == null) {
            resetWithEmpty();
        } else if (this._currentSegment != null) {
            resetWithEmpty();
            char[] cArr = this._currentSegment;
            this._currentSegment = null;
            this._allocator.releaseCharBuffer(BufferRecycler.CharBufferType.TEXT_BUFFER, cArr);
        }
    }

    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithShared(char[] cArr, int i, int i2) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = cArr;
        this._inputStart = i;
        this._inputLen = i2;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithCopy(char[] cArr, int i, int i2) {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = findBuffer(i4);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(cArr2, i3, i4);
    }

    public void resetWithString(String str) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = str;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        this._currentSize = 0;
    }

    private char[] findBuffer(int i) {
        int i2 = i;
        if (this._allocator != null) {
            return this._allocator.allocCharBuffer(BufferRecycler.CharBufferType.TEXT_BUFFER, i2);
        }
        return new char[Math.max(i2, 1000)];
    }

    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }

    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        if (this._resultArray != null) {
            return this._resultArray.length;
        }
        if (this._resultString != null) {
            return this._resultString.length();
        }
        return this._segmentSize + this._currentSize;
    }

    public int getTextOffset() {
        return this._inputStart >= 0 ? this._inputStart : 0;
    }

    public boolean hasTextAsCharacters() {
        if (this._inputStart >= 0 || this._resultArray != null) {
            return true;
        }
        if (this._resultString != null) {
            return false;
        }
        return true;
    }

    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        if (this._resultArray != null) {
            return this._resultArray;
        }
        if (this._resultString != null) {
            char[] charArray = this._resultString.toCharArray();
            char[] cArr = charArray;
            this._resultArray = cArr;
            return charArray;
        } else if (!this._hasSegments) {
            return this._currentSegment;
        } else {
            return contentsAsArray();
        }
    }

    public String contentsAsString() {
        StringBuilder sb;
        String str;
        String str2;
        String str3;
        String str4;
        if (this._resultString == null) {
            if (this._resultArray != null) {
                new String(this._resultArray);
                this._resultString = str4;
            } else if (this._inputStart < 0) {
                int i = this._segmentSize;
                int i2 = this._currentSize;
                if (i == 0) {
                    if (i2 == 0) {
                        str2 = "";
                    } else {
                        str2 = str;
                        new String(this._currentSegment, 0, i2);
                    }
                    this._resultString = str2;
                } else {
                    new StringBuilder(i + i2);
                    StringBuilder sb2 = sb;
                    if (this._segments != null) {
                        int size = this._segments.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            char[] cArr = this._segments.get(i3);
                            StringBuilder append = sb2.append(cArr, 0, cArr.length);
                        }
                    }
                    StringBuilder append2 = sb2.append(this._currentSegment, 0, this._currentSize);
                    this._resultString = sb2.toString();
                }
            } else if (this._inputLen < 1) {
                String str5 = "";
                String str6 = str5;
                this._resultString = str6;
                return str5;
            } else {
                new String(this._inputBuffer, this._inputStart, this._inputLen);
                this._resultString = str3;
            }
        }
        return this._resultString;
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr == null) {
            char[] buildResultArray = buildResultArray();
            cArr = buildResultArray;
            this._resultArray = buildResultArray;
        }
        return cArr;
    }

    public BigDecimal contentsAsDecimal() throws NumberFormatException {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        if (this._resultArray != null) {
            new BigDecimal(this._resultArray);
            return bigDecimal4;
        } else if (this._inputStart >= 0) {
            new BigDecimal(this._inputBuffer, this._inputStart, this._inputLen);
            return bigDecimal3;
        } else if (this._segmentSize == 0) {
            new BigDecimal(this._currentSegment, 0, this._currentSize);
            return bigDecimal2;
        } else {
            new BigDecimal(contentsAsArray());
            return bigDecimal;
        }
    }

    public double contentsAsDouble() throws NumberFormatException {
        return NumberInput.parseDouble(contentsAsString());
    }

    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            unshare(16);
        }
    }

    public void append(char c) {
        char c2 = c;
        if (this._inputStart >= 0) {
            unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        if (this._currentSize >= cArr.length) {
            expand(1);
            cArr = this._currentSegment;
        }
        int i = this._currentSize;
        int i2 = i + 1;
        this._currentSize = i2;
        cArr[i] = c2;
    }

    public void append(char[] cArr, int i, int i2) {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        if (this._inputStart >= 0) {
            unshare(i4);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr3 = this._currentSegment;
        int length = cArr3.length - this._currentSize;
        if (length >= i4) {
            System.arraycopy(cArr2, i3, cArr3, this._currentSize, i4);
            this._currentSize += i4;
            return;
        }
        if (length > 0) {
            System.arraycopy(cArr2, i3, cArr3, this._currentSize, length);
            i3 += length;
            i4 -= length;
        }
        do {
            expand(i4);
            int min = Math.min(this._currentSegment.length, i4);
            System.arraycopy(cArr2, i3, this._currentSegment, 0, min);
            this._currentSize += min;
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
    }

    public void append(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        if (this._inputStart >= 0) {
            unshare(i4);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        int length = cArr.length - this._currentSize;
        if (length >= i4) {
            str2.getChars(i3, i3 + i4, cArr, this._currentSize);
            this._currentSize += i4;
            return;
        }
        if (length > 0) {
            str2.getChars(i3, i3 + length, cArr, this._currentSize);
            i4 -= length;
            i3 += length;
        }
        do {
            expand(i4);
            int min = Math.min(this._currentSegment.length, i4);
            str2.getChars(i3, i3 + min, this._currentSegment, 0);
            this._currentSize += min;
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
    }

    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            unshare(1);
        } else {
            char[] cArr = this._currentSegment;
            if (cArr == null) {
                this._currentSegment = findBuffer(0);
            } else if (this._currentSize >= cArr.length) {
                expand(1);
            }
        }
        return this._currentSegment;
    }

    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        char[] cArr = this._currentSegment;
        if (cArr == null) {
            char[] findBuffer = findBuffer(0);
            cArr = findBuffer;
            this._currentSegment = findBuffer;
        }
        return cArr;
    }

    public int getCurrentSegmentSize() {
        return this._currentSize;
    }

    public void setCurrentLength(int i) {
        int i2 = i;
        this._currentSize = i2;
    }

    public char[] finishCurrentSegment() {
        ArrayList<char[]> arrayList;
        if (this._segments == null) {
            new ArrayList<>();
            this._segments = arrayList;
        }
        this._hasSegments = true;
        boolean add = this._segments.add(this._currentSegment);
        int length = this._currentSegment.length;
        this._segmentSize += length;
        char[] _charArray = _charArray(Math.min(length + (length >> 1), 262144));
        this._currentSize = 0;
        this._currentSegment = _charArray;
        return _charArray;
    }

    public char[] expandCurrentSegment() {
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        this._currentSegment = _charArray(length == 262144 ? 262145 : Math.min(262144, length + (length >> 1)));
        System.arraycopy(cArr, 0, this._currentSegment, 0, length);
        return this._currentSegment;
    }

    public String toString() {
        return contentsAsString();
    }

    private void unshare(int i) {
        int i2 = this._inputLen;
        this._inputLen = 0;
        char[] cArr = this._inputBuffer;
        this._inputBuffer = null;
        int i3 = this._inputStart;
        this._inputStart = -1;
        int i4 = i2 + i;
        if (this._currentSegment == null || i4 > this._currentSegment.length) {
            this._currentSegment = findBuffer(i4);
        }
        if (i2 > 0) {
            System.arraycopy(cArr, i3, this._currentSegment, 0, i2);
        }
        this._segmentSize = 0;
        this._currentSize = i2;
    }

    private void expand(int i) {
        ArrayList<char[]> arrayList;
        int i2 = i;
        if (this._segments == null) {
            new ArrayList<>();
            this._segments = arrayList;
        }
        char[] cArr = this._currentSegment;
        this._hasSegments = true;
        boolean add = this._segments.add(cArr);
        this._segmentSize += cArr.length;
        int length = cArr.length;
        int i3 = length >> 1;
        if (i3 < i2) {
            i3 = i2;
        }
        char[] _charArray = _charArray(Math.min(262144, length + i3));
        this._currentSize = 0;
        this._currentSegment = _charArray;
    }

    private char[] buildResultArray() {
        char[] _charArray;
        if (this._resultString != null) {
            return this._resultString.toCharArray();
        }
        if (this._inputStart < 0) {
            int size = size();
            if (size < 1) {
                return NO_CHARS;
            }
            int i = 0;
            _charArray = _charArray(size);
            if (this._segments != null) {
                int size2 = this._segments.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    char[] cArr = this._segments.get(i2);
                    int length = cArr.length;
                    System.arraycopy(cArr, 0, _charArray, i, length);
                    i += length;
                }
            }
            System.arraycopy(this._currentSegment, 0, _charArray, i, this._currentSize);
        } else if (this._inputLen < 1) {
            return NO_CHARS;
        } else {
            _charArray = _charArray(this._inputLen);
            System.arraycopy(this._inputBuffer, this._inputStart, _charArray, 0, this._inputLen);
        }
        return _charArray;
    }

    private char[] _charArray(int i) {
        return new char[i];
    }
}
