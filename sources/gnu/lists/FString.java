package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Writer;

public class FString extends SimpleVector implements Comparable, Appendable, CharSeq, Externalizable, Consumable {
    protected static char[] empty = new char[0];
    public char[] data;

    public FString() {
        this.data = empty;
    }

    public FString(int i) {
        int num = i;
        this.size = num;
        this.data = new char[num];
    }

    public FString(int i, char c) {
        int num = i;
        char value = c;
        char[] array = new char[num];
        this.data = array;
        this.size = num;
        while (true) {
            num--;
            if (num >= 0) {
                array[num] = value;
            } else {
                return;
            }
        }
    }

    public FString(char[] cArr) {
        char[] values = cArr;
        this.size = values.length;
        this.data = values;
    }

    public FString(String str) {
        this.data = str.toCharArray();
        this.size = this.data.length;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FString(java.lang.StringBuffer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = 0
            r5 = r1
            int r5 = r5.length()
            r2.<init>((java.lang.StringBuffer) r3, (int) r4, (int) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.FString.<init>(java.lang.StringBuffer):void");
    }

    public FString(StringBuffer stringBuffer, int i, int i2) {
        StringBuffer buffer = stringBuffer;
        int offset = i;
        int length = i2;
        this.size = length;
        this.data = new char[length];
        if (length > 0) {
            buffer.getChars(offset, offset + length, this.data, 0);
        }
    }

    public FString(char[] buffer, int offset, int i) {
        int length = i;
        this.size = length;
        this.data = new char[length];
        System.arraycopy(buffer, offset, this.data, 0, length);
    }

    public FString(Sequence sequence) {
        Sequence seq = sequence;
        this.data = new char[seq.size()];
        boolean addAll = addAll(seq);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FString(gnu.lists.CharSeq r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = 0
            r5 = r1
            int r5 = r5.size()
            r2.<init>((gnu.lists.CharSeq) r3, (int) r4, (int) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.FString.<init>(gnu.lists.CharSeq):void");
    }

    public FString(CharSeq seq, int i, int i2) {
        int offset = i;
        int length = i2;
        char[] data2 = new char[length];
        seq.getChars(offset, offset + length, data2, 0);
        this.data = data2;
        this.size = length;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FString(java.lang.CharSequence r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = 0
            r5 = r1
            int r5 = r5.length()
            r2.<init>((java.lang.CharSequence) r3, (int) r4, (int) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.FString.<init>(java.lang.CharSequence):void");
    }

    public FString(CharSequence charSequence, int i, int i2) {
        CharSequence seq = charSequence;
        int offset = i;
        int length = i2;
        char[] data2 = new char[length];
        int i3 = length;
        while (true) {
            i3--;
            if (i3 >= 0) {
                data2[i3] = seq.charAt(offset + i3);
            } else {
                this.data = data2;
                this.size = length;
                return;
            }
        }
    }

    public int length() {
        return this.size;
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int i) {
        int length = i;
        int oldLength = this.data.length;
        if (oldLength != length) {
            char[] tmp = new char[length];
            System.arraycopy(this.data, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    public void ensureBufferLength(int i) {
        int sz = i;
        if (sz > this.data.length) {
            char[] d = new char[(sz < 60 ? 120 : 2 * sz)];
            System.arraycopy(this.data, 0, d, 0, sz);
            this.data = d;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final Object getBuffer(int index) {
        return Convert.toObject(this.data[index]);
    }

    public final Object setBuffer(int i, Object value) {
        int index = i;
        Object old = Convert.toObject(this.data[index]);
        this.data[index] = Convert.toChar(value);
        return old;
    }

    public final Object get(int i) {
        Throwable th;
        int index = i;
        if (index < this.size) {
            return Convert.toObject(this.data[index]);
        }
        Throwable th2 = th;
        new ArrayIndexOutOfBoundsException();
        throw th2;
    }

    public final char charAt(int i) {
        Throwable th;
        int index = i;
        if (index < this.size) {
            return this.data[index];
        }
        Throwable th2 = th;
        new StringIndexOutOfBoundsException(index);
        throw th2;
    }

    public final char charAtBuffer(int index) {
        return this.data[index];
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        int srcBegin = i;
        int srcEnd = i2;
        char[] dst = cArr;
        int dstBegin = i3;
        if (srcBegin < 0 || srcBegin > srcEnd) {
            Throwable th4 = th;
            new StringIndexOutOfBoundsException(srcBegin);
            throw th4;
        } else if (srcEnd > this.size) {
            Throwable th5 = th3;
            new StringIndexOutOfBoundsException(srcEnd);
            throw th5;
        } else if ((dstBegin + srcEnd) - srcBegin > dst.length) {
            Throwable th6 = th2;
            new StringIndexOutOfBoundsException(dstBegin);
            throw th6;
        } else if (srcBegin < srcEnd) {
            System.arraycopy(this.data, srcBegin, dst, dstBegin, srcEnd - srcBegin);
        }
    }

    public void getChars(int i, int i2, StringBuffer stringBuffer) {
        Throwable th;
        Throwable th2;
        int srcBegin = i;
        int srcEnd = i2;
        StringBuffer dst = stringBuffer;
        if (srcBegin < 0 || srcBegin > srcEnd) {
            Throwable th3 = th;
            new StringIndexOutOfBoundsException(srcBegin);
            throw th3;
        } else if (srcEnd > this.size) {
            Throwable th4 = th2;
            new StringIndexOutOfBoundsException(srcEnd);
            throw th4;
        } else if (srcBegin < srcEnd) {
            StringBuffer append = dst.append(this.data, srcBegin, srcEnd - srcBegin);
        }
    }

    public void getChars(StringBuffer dst) {
        StringBuffer append = dst.append(this.data, 0, this.size);
    }

    public char[] toCharArray() {
        int val_length = this.data.length;
        int seq_length = this.size;
        if (seq_length == val_length) {
            return this.data;
        }
        char[] tmp = new char[seq_length];
        System.arraycopy(this.data, 0, tmp, 0, seq_length);
        return tmp;
    }

    public void shift(int srcStart, int dstStart, int count) {
        System.arraycopy(this.data, srcStart, this.data, dstStart, count);
    }

    public FString copy(int i, int i2) {
        FString fString;
        int start = i;
        int end = i2;
        char[] copy = new char[(end - start)];
        char[] src = this.data;
        for (int i3 = start; i3 < end; i3++) {
            copy[i3 - start] = src[i3];
        }
        new FString(copy);
        return fString;
    }

    public boolean addAll(FString fString) {
        FString s = fString;
        int newSize = this.size + s.size;
        if (this.data.length < newSize) {
            setBufferLength(newSize);
        }
        System.arraycopy(s.data, 0, this.data, this.size, s.size);
        this.size = newSize;
        return s.size > 0;
    }

    public boolean addAll(CharSequence charSequence) {
        CharSequence s = charSequence;
        int ssize = s.length();
        int newSize = this.size + ssize;
        if (this.data.length < newSize) {
            setBufferLength(newSize);
        }
        if (!(s instanceof FString)) {
            if (!(s instanceof String)) {
                if (!(s instanceof CharSeq)) {
                    int i = ssize;
                    while (true) {
                        i--;
                        if (i < 0) {
                            break;
                        }
                        this.data[this.size + i] = s.charAt(i);
                    }
                } else {
                    ((CharSeq) s).getChars(0, ssize, this.data, this.size);
                }
            } else {
                ((String) s).getChars(0, ssize, this.data, this.size);
            }
        } else {
            System.arraycopy(((FString) s).data, 0, this.data, this.size, ssize);
        }
        this.size = newSize;
        return ssize > 0;
    }

    public void addAllStrings(Object[] objArr, int i) {
        Object[] args = objArr;
        int startIndex = i;
        int total = this.size;
        for (int i2 = startIndex; i2 < args.length; i2++) {
            total += ((CharSequence) args[i2]).length();
        }
        if (this.data.length < total) {
            setBufferLength(total);
        }
        for (int i3 = startIndex; i3 < args.length; i3++) {
            boolean addAll = addAll((CharSequence) args[i3]);
        }
    }

    public String toString() {
        String str;
        new String(this.data, 0, this.size);
        return str;
    }

    public String substring(int i, int end) {
        String str;
        int start = i;
        new String(this.data, start, end - start);
        return str;
    }

    public CharSequence subSequence(int i, int end) {
        FString fString;
        int start = i;
        new FString(this.data, start, end - start);
        return fString;
    }

    public void setCharAt(int i, char c) {
        Throwable th;
        int index = i;
        char ch = c;
        if (index < 0 || index >= this.size) {
            Throwable th2 = th;
            new StringIndexOutOfBoundsException(index);
            throw th2;
        }
        this.data[index] = ch;
    }

    public void setCharAtBuffer(int index, char ch) {
        this.data[index] = ch;
    }

    public final void fill(char c) {
        char ch = c;
        char[] d = this.data;
        int i = this.size;
        while (true) {
            i--;
            if (i >= 0) {
                d[i] = ch;
            } else {
                return;
            }
        }
    }

    public void fill(int i, int i2, char c) {
        Throwable th;
        int fromIndex = i;
        int toIndex = i2;
        char value = c;
        if (fromIndex < 0 || toIndex > this.size) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        }
        char[] d = this.data;
        for (int i3 = fromIndex; i3 < toIndex; i3++) {
            d[i3] = value;
        }
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int i, int i2) {
        int start = i;
        int count = i2;
        char[] d = this.data;
        while (true) {
            count--;
            if (count >= 0) {
                int i3 = start;
                start++;
                d[i3] = 0;
            } else {
                return;
            }
        }
    }

    public void replace(int where, char[] chars, int start, int count) {
        System.arraycopy(chars, start, this.data, where, count);
    }

    public void replace(int where, String str) {
        String string = str;
        string.getChars(0, string.length(), this.data, where);
    }

    public int hashCode() {
        char[] val = this.data;
        int len = this.size;
        int hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (31 * hash) + val[i];
        }
        return hash;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof FString)) {
            return false;
        }
        char[] str = ((FString) obj2).data;
        int n = this.size;
        if (str == null || str.length != n) {
            return false;
        }
        char[] d = this.data;
        int i = n;
        do {
            i--;
            if (i < 0) {
                return true;
            }
        } while (d[i] == str[i]);
        return false;
    }

    public int compareTo(Object obj) {
        FString str2 = (FString) obj;
        char[] cs1 = this.data;
        char[] cs2 = str2.data;
        int n1 = this.size;
        int n2 = str2.size;
        int n = n1 > n2 ? n2 : n1;
        for (int i = 0; i < n; i++) {
            int d = cs1[i] - cs2[i];
            if (d != 0) {
                return d;
            }
        }
        return n1 - n2;
    }

    public int getElementKind() {
        return 29;
    }

    public void consume(Consumer out) {
        out.write(this.data, 0, this.data.length);
    }

    public boolean consumeNext(int ipos, Consumer consumer) {
        Consumer out = consumer;
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.write((int) this.data[index]);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        int iposStart = i;
        int iposEnd = i2;
        Consumer out = consumer;
        if (!out.ignoring()) {
            int i3 = iposStart >>> 1;
            int end = iposEnd >>> 1;
            if (end > this.size) {
                end = this.size;
            }
            if (end > i3) {
                out.write(this.data, i3, end - i3);
            }
        }
    }

    public FString append(char c) {
        char c2 = c;
        int sz = this.size;
        if (sz >= this.data.length) {
            ensureBufferLength(sz + 1);
        }
        this.data[sz] = c2;
        this.size = sz + 1;
        return this;
    }

    public FString append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (csq == null) {
            csq = "null";
        }
        return append(csq, 0, csq.length());
    }

    public FString append(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        if (csq == null) {
            csq = "null";
        }
        int len = end - start;
        int sz = this.size;
        if (sz + len > this.data.length) {
            ensureBufferLength(sz + len);
        }
        char[] d = this.data;
        if (csq instanceof String) {
            ((String) csq).getChars(start, end, d, sz);
        } else if (csq instanceof CharSeq) {
            ((CharSeq) csq).getChars(start, end, d, sz);
        } else {
            int j = sz;
            for (int i3 = start; i3 < end; i3++) {
                int i4 = j;
                j++;
                d[i4] = csq.charAt(i3);
            }
        }
        this.size = sz;
        return this;
    }

    public void writeTo(int i, int i2, Appendable appendable) throws IOException {
        Throwable th;
        int start = i;
        int count = i2;
        Appendable dest = appendable;
        if (dest instanceof Writer) {
            try {
                ((Writer) dest).write(this.data, start, count);
            } catch (IOException e) {
                IOException ex = e;
                Throwable th2 = th;
                new RuntimeException(ex);
                throw th2;
            }
        } else {
            Appendable append = dest.append(this, start, start + count);
        }
    }

    public void writeTo(Appendable dest) throws IOException {
        writeTo(0, this.size, dest);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        int size = this.size;
        out.writeInt(size);
        char[] d = this.data;
        for (int i = 0; i < size; i++) {
            out.writeChar(d[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int size = in.readInt();
        char[] data2 = new char[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readChar();
        }
        this.data = data2;
        this.size = size;
    }
}
