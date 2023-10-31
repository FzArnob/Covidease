package gnu.lists;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;

public class CharBuffer extends StableVector implements CharSeq, Serializable {
    private FString string;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CharBuffer(gnu.lists.FString r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.string = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.CharBuffer.<init>(gnu.lists.FString):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CharBuffer(int r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            gnu.lists.FString r3 = new gnu.lists.FString
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>((int) r5)
            r2.<init>((gnu.lists.FString) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.CharBuffer.<init>(int):void");
    }

    protected CharBuffer() {
    }

    public int length() {
        return size();
    }

    public char charAt(int i) {
        int index = i;
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        return this.string.charAt(index);
    }

    public int indexOf(int i, int i2) {
        char c1;
        char c2;
        int ch = i;
        int fromChar = i2;
        if (ch >= 65536) {
            c1 = (char) (((ch - 65536) >> 10) + 55296);
            c2 = (char) ((ch & 1023) + 56320);
        } else {
            c1 = (char) ch;
            c2 = 0;
        }
        char[] arr = getArray();
        int i3 = fromChar;
        int limit = this.gapStart;
        if (i3 >= limit) {
            i3 += this.gapEnd - this.gapStart;
            limit = arr.length;
        }
        while (true) {
            if (i3 == limit) {
                limit = arr.length;
                if (i3 >= limit) {
                    return -1;
                }
                i3 = this.gapEnd;
            }
            if (arr[i3] == c1) {
                if (c2 != 0) {
                    if (i3 + 1 >= limit) {
                        if (this.gapEnd < arr.length && arr[this.gapEnd] == c2) {
                            break;
                        }
                    } else if (arr[i3 + 1] == c2) {
                        break;
                    }
                } else {
                    break;
                }
            }
            i3++;
        }
        return i3 > this.gapStart ? i3 - (this.gapEnd - this.gapStart) : i3;
    }

    public int lastIndexOf(int i, int i2) {
        char c1;
        char c2;
        int ch = i;
        int fromChar = i2;
        if (ch >= 65536) {
            c1 = (char) (((ch - 65536) >> 10) + 55296);
            c2 = (char) ((ch & 1023) + 56320);
        } else {
            c1 = 0;
            c2 = (char) ch;
        }
        int i3 = fromChar;
        while (true) {
            i3--;
            if (i3 < 0) {
                return -1;
            }
            if (charAt(i3) == c2) {
                if (c1 == 0) {
                    return i3;
                }
                if (i3 > 0 && charAt(i3 - 1) == c1) {
                    return i3 - 1;
                }
            }
        }
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        int srcBegin = i;
        int srcEnd = i2;
        char[] dst = cArr;
        int dstBegin = i3;
        char[] array = this.string.data;
        if (srcBegin < this.gapStart) {
            int count = (srcEnd < this.gapStart ? srcEnd : this.gapStart) - srcBegin;
            if (count > 0) {
                System.arraycopy(array, srcBegin, dst, dstBegin, count);
                srcBegin += count;
                dstBegin += count;
            }
        }
        int gapSize = this.gapEnd - this.gapStart;
        int srcBegin2 = srcBegin + gapSize;
        int count2 = (srcEnd + gapSize) - srcBegin2;
        if (count2 > 0) {
            System.arraycopy(array, srcBegin2, dst, dstBegin, count2);
        }
    }

    public void setCharAt(int i, char c) {
        int index = i;
        char value = c;
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        this.string.setCharAt(index, value);
    }

    public String substring(int i, int i2) {
        Throwable th;
        String str;
        int start = i;
        int end = i2;
        int sz = size();
        if (start < 0 || end < start || end > sz) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        int len = end - start;
        new String(getArray(), getSegment(start, len), len);
        return str;
    }

    public CharSequence subSequence(int i, int i2) {
        Throwable th;
        CharSequence charSequence;
        int start = i;
        int end = i2;
        int sz = size();
        if (start < 0 || end < start || end > sz) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        new SubCharSeq(this, this.base.createPos(start, false), this.base.createPos(end, true));
        return charSequence;
    }

    public void fill(int fromIndex, int i, char c) {
        int toIndex = i;
        char value = c;
        char[] array = this.string.data;
        int limit = this.gapStart < toIndex ? this.gapStart : toIndex;
        for (int i2 = fromIndex; i2 < limit; i2++) {
            array[i2] = value;
        }
        int limit2 = limit + toIndex;
        for (int i3 = limit + (this.gapEnd - this.gapStart); i3 < limit2; i3++) {
            array[i3] = value;
        }
    }

    public final void fill(char c) {
        char value = c;
        char[] array = this.string.data;
        int i = array.length;
        while (true) {
            i--;
            if (i < this.gapEnd) {
                break;
            }
            array[i] = value;
        }
        int i2 = this.gapStart;
        while (true) {
            i2--;
            if (i2 >= 0) {
                array[i2] = value;
            } else {
                return;
            }
        }
    }

    public char[] getArray() {
        return (char[]) this.base.getBuffer();
    }

    public void delete(int where, int count) {
        int ipos = createPos(where, false);
        removePos(ipos, count);
        releasePos(ipos);
    }

    public void insert(int i, String str, boolean z) {
        int where = i;
        String str2 = str;
        boolean z2 = z;
        int len = str2.length();
        gapReserve(where, len);
        str2.getChars(0, len, this.string.data, where);
        this.gapStart += len;
    }

    public void consume(int i, int i2, Consumer consumer) {
        int start = i;
        int count = i2;
        Consumer dest = consumer;
        char[] array = this.string.data;
        if (start < this.gapStart) {
            int count0 = this.gapStart - start;
            if (count0 > count) {
                count0 = count;
            }
            dest.write(array, start, count0);
            count -= count0;
            start += count;
        }
        if (count > 0) {
            dest.write(array, start + (this.gapEnd - this.gapStart), count);
        }
    }

    public String toString() {
        String str;
        int len = size();
        new String(getArray(), getSegment(0, len), len);
        return str;
    }

    public void writeTo(int i, int i2, Appendable appendable) throws IOException {
        int start = i;
        int count = i2;
        Appendable dest = appendable;
        if (dest instanceof Writer) {
            writeTo(start, count, (Writer) dest);
        } else {
            Appendable append = dest.append(this, start, start + count);
        }
    }

    public void writeTo(Appendable dest) throws IOException {
        writeTo(0, size(), dest);
    }

    public void writeTo(int i, int i2, Writer writer) throws IOException {
        int start = i;
        int count = i2;
        Writer dest = writer;
        char[] array = this.string.data;
        if (start < this.gapStart) {
            int count0 = this.gapStart - start;
            if (count0 > count) {
                count0 = count;
            }
            dest.write(array, start, count0);
            count -= count0;
            start += count;
        }
        if (count > 0) {
            dest.write(array, start + (this.gapEnd - this.gapStart), count);
        }
    }

    public void writeTo(Writer writer) throws IOException {
        Writer dest = writer;
        char[] array = this.string.data;
        dest.write(array, 0, this.gapStart);
        dest.write(array, this.gapEnd, array.length - this.gapEnd);
    }

    public void dump() {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        StringBuilder sb4;
        PrintStream printStream = System.err;
        new StringBuilder();
        printStream.println(sb.append("Buffer Content dump.  size:").append(size()).append("  buffer:").append(getArray().length).toString());
        System.err.print("before gap: \"");
        new String(getArray(), 0, this.gapStart);
        System.err.print(str);
        PrintStream printStream2 = System.err;
        new StringBuilder();
        printStream2.println(sb2.append("\" (gapStart:").append(this.gapStart).append(" gapEnd:").append(this.gapEnd).append(')').toString());
        System.err.print("after gap: \"");
        new String(getArray(), this.gapEnd, getArray().length - this.gapEnd);
        System.err.print(str2);
        System.err.println("\"");
        int poslen = this.positions == null ? 0 : this.positions.length;
        PrintStream printStream3 = System.err;
        new StringBuilder();
        printStream3.println(sb3.append("Positions (size: ").append(poslen).append(" free:").append(this.free).append("):").toString());
        boolean[] isFree = null;
        if (this.free != -2) {
            isFree = new boolean[this.positions.length];
            int i = this.free;
            while (true) {
                int i2 = i;
                if (i2 < 0) {
                    break;
                }
                isFree[i2] = true;
                i = this.positions[i2];
            }
        }
        for (int i3 = 0; i3 < poslen; i3++) {
            int pos = this.positions[i3];
            if (this.free == -2) {
                if (pos == -2) {
                }
            } else if (isFree[i3]) {
            }
            PrintStream printStream4 = System.err;
            new StringBuilder();
            printStream4.println(sb4.append("position#").append(i3).append(": ").append(pos >> 1).append(" isAfter:").append(pos & 1).toString());
        }
    }
}
