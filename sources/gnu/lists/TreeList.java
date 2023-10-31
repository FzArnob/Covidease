package gnu.lists;

import gnu.kawa.lispexpr.LispReader;
import gnu.text.Char;
import java.io.PrintWriter;

public class TreeList extends AbstractSequence implements Appendable, XConsumer, PositionConsumer, Consumable {
    protected static final int BEGIN_ATTRIBUTE_LONG = 61705;
    public static final int BEGIN_ATTRIBUTE_LONG_SIZE = 5;
    protected static final int BEGIN_DOCUMENT = 61712;
    protected static final int BEGIN_ELEMENT_LONG = 61704;
    protected static final int BEGIN_ELEMENT_SHORT = 40960;
    protected static final int BEGIN_ELEMENT_SHORT_INDEX_MAX = 4095;
    public static final int BEGIN_ENTITY = 61714;
    public static final int BEGIN_ENTITY_SIZE = 5;
    static final char BOOL_FALSE = '';
    static final char BOOL_TRUE = '';
    static final int BYTE_PREFIX = 61440;
    static final int CDATA_SECTION = 61717;
    static final int CHAR_FOLLOWS = 61702;
    static final int COMMENT = 61719;
    protected static final int DOCUMENT_URI = 61720;
    static final int DOUBLE_FOLLOWS = 61701;
    static final int END_ATTRIBUTE = 61706;
    public static final int END_ATTRIBUTE_SIZE = 1;
    protected static final int END_DOCUMENT = 61713;
    protected static final int END_ELEMENT_LONG = 61708;
    protected static final int END_ELEMENT_SHORT = 61707;
    protected static final int END_ENTITY = 61715;
    static final int FLOAT_FOLLOWS = 61700;
    public static final int INT_FOLLOWS = 61698;
    static final int INT_SHORT_ZERO = 49152;
    static final int JOINER = 61718;
    static final int LONG_FOLLOWS = 61699;
    public static final int MAX_CHAR_SHORT = 40959;
    static final int MAX_INT_SHORT = 8191;
    static final int MIN_INT_SHORT = -4096;
    static final char OBJECT_REF_FOLLOWS = '';
    static final int OBJECT_REF_SHORT = 57344;
    static final int OBJECT_REF_SHORT_INDEX_MAX = 4095;
    protected static final char POSITION_PAIR_FOLLOWS = '';
    static final char POSITION_REF_FOLLOWS = '';
    protected static final int PROCESSING_INSTRUCTION = 61716;
    public int attrStart;
    int currentParent;
    public char[] data;
    public int docStart;
    public int gapEnd;
    public int gapStart;
    public Object[] objects;
    public int oindex;

    public TreeList() {
        this.currentParent = -1;
        resizeObjects();
        this.gapEnd = 200;
        this.data = new char[this.gapEnd];
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TreeList(TreeList list, int startPosition, int endPosition) {
        this();
        int consumeIRange = list.consumeIRange(startPosition, endPosition, this);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TreeList(gnu.lists.TreeList r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = 0
            r5 = r1
            char[] r5 = r5.data
            int r5 = r5.length
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.<init>(gnu.lists.TreeList):void");
    }

    public void clear() {
        this.gapStart = 0;
        this.gapEnd = this.data.length;
        this.attrStart = 0;
        if (this.gapEnd > 1500) {
            this.gapEnd = 200;
            this.data = new char[this.gapEnd];
        }
        this.objects = null;
        this.oindex = 0;
        resizeObjects();
    }

    public void ensureSpace(int i) {
        int needed = i;
        int avail = this.gapEnd - this.gapStart;
        if (needed > avail) {
            int oldSize = this.data.length;
            int neededSize = (oldSize - avail) + needed;
            int newSize = 2 * oldSize;
            if (newSize < neededSize) {
                newSize = neededSize;
            }
            char[] tmp = new char[newSize];
            if (this.gapStart > 0) {
                System.arraycopy(this.data, 0, tmp, 0, this.gapStart);
            }
            int afterGap = oldSize - this.gapEnd;
            if (afterGap > 0) {
                System.arraycopy(this.data, this.gapEnd, tmp, newSize - afterGap, afterGap);
            }
            this.gapEnd = newSize - afterGap;
            this.data = tmp;
        }
    }

    public final void resizeObjects() {
        Object[] tmp;
        if (this.objects == null) {
            tmp = new Object[100];
        } else {
            int oldLength = this.objects.length;
            tmp = new Object[(2 * oldLength)];
            System.arraycopy(this.objects, 0, tmp, 0, oldLength);
        }
        this.objects = tmp;
    }

    public int find(Object obj) {
        Object arg1 = obj;
        if (this.oindex == this.objects.length) {
            resizeObjects();
        }
        this.objects[this.oindex] = arg1;
        int i = this.oindex;
        int i2 = i + 1;
        this.oindex = i2;
        return i;
    }

    /* access modifiers changed from: protected */
    public final int getIntN(int i) {
        int index = i;
        return (this.data[index] << 16) | (this.data[index + 1] & LispReader.TOKEN_ESCAPE_CHAR);
    }

    /* access modifiers changed from: protected */
    public final long getLongN(int i) {
        int index = i;
        char[] data2 = this.data;
        return ((((long) data2[index]) & 65535) << 48) | ((((long) data2[index + 1]) & 65535) << 32) | ((((long) data2[index + 2]) & 65535) << 16) | (((long) data2[index + 3]) & 65535);
    }

    public final void setIntN(int i, int i2) {
        int index = i;
        int i3 = i2;
        this.data[index] = (char) (i3 >> 16);
        this.data[index + 1] = (char) i3;
    }

    public void consume(SeqPosition position) {
        ensureSpace(3);
        int index = find(position.copy());
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = POSITION_REF_FOLLOWS;
        setIntN(this.gapStart, index);
        this.gapStart += 2;
    }

    public void writePosition(AbstractSequence seq, int ipos) {
        ensureSpace(5);
        this.data[this.gapStart] = POSITION_PAIR_FOLLOWS;
        setIntN(this.gapStart + 1, find(seq));
        setIntN(this.gapStart + 3, ipos);
        this.gapStart += 5;
    }

    public void writeObject(Object v) {
        ensureSpace(3);
        int index = find(v);
        if (index < 4096) {
            char[] cArr = this.data;
            int i = this.gapStart;
            int i2 = i + 1;
            this.gapStart = i2;
            cArr[i] = (char) (OBJECT_REF_SHORT | index);
            return;
        }
        char[] cArr2 = this.data;
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.gapStart = i4;
        cArr2[i3] = OBJECT_REF_FOLLOWS;
        setIntN(this.gapStart, index);
        this.gapStart += 2;
    }

    public void writeDocumentUri(Object uri) {
        ensureSpace(3);
        int index = find(uri);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = 61720;
        setIntN(this.gapStart, index);
        this.gapStart += 2;
    }

    public void writeComment(char[] chars, int offset, int i) {
        int length = i;
        ensureSpace(3 + length);
        int i2 = this.gapStart;
        int i3 = i2;
        int i4 = i2 + 1;
        this.data[i3] = 61719;
        setIntN(i4, length);
        int i5 = i4 + 2;
        System.arraycopy(chars, offset, this.data, i5, length);
        this.gapStart = i5 + length;
    }

    public void writeComment(String comment, int i, int i2) {
        int offset = i;
        int length = i2;
        ensureSpace(3 + length);
        int i3 = this.gapStart;
        int i4 = i3;
        int i5 = i3 + 1;
        this.data[i4] = 61719;
        setIntN(i5, length);
        int i6 = i5 + 2;
        comment.getChars(offset, offset + length, this.data, i6);
        this.gapStart = i6 + length;
    }

    public void writeProcessingInstruction(String target, char[] content, int offset, int i) {
        int length = i;
        ensureSpace(5 + length);
        int i2 = this.gapStart;
        int i3 = i2;
        int i4 = i2 + 1;
        this.data[i3] = 61716;
        setIntN(i4, find(target));
        setIntN(i4 + 2, length);
        int i5 = i4 + 4;
        System.arraycopy(content, offset, this.data, i5, length);
        this.gapStart = i5 + length;
    }

    public void writeProcessingInstruction(String target, String content, int i, int i2) {
        int offset = i;
        int length = i2;
        ensureSpace(5 + length);
        int i3 = this.gapStart;
        int i4 = i3;
        int i5 = i3 + 1;
        this.data[i4] = 61716;
        setIntN(i5, find(target));
        setIntN(i5 + 2, length);
        int i6 = i5 + 4;
        content.getChars(offset, offset + length, this.data, i6);
        this.gapStart = i6 + length;
    }

    public void startElement(Object type) {
        startElement(find(type));
    }

    public void startDocument() {
        Throwable th;
        ensureSpace(6);
        this.gapEnd--;
        int p = this.gapStart;
        this.data[p] = 61712;
        if (this.docStart != 0) {
            Throwable th2 = th;
            new Error("nested document");
            throw th2;
        }
        this.docStart = p + 1;
        setIntN(p + 1, this.gapEnd - this.data.length);
        setIntN(p + 3, this.currentParent == -1 ? -1 : this.currentParent - p);
        this.currentParent = p;
        this.gapStart = p + 5;
        this.currentParent = p;
        this.data[this.gapEnd] = 61713;
    }

    public void endDocument() {
        Throwable th;
        if (this.data[this.gapEnd] == END_DOCUMENT && this.docStart > 0 && this.data[this.currentParent] == BEGIN_DOCUMENT) {
            this.gapEnd++;
            setIntN(this.docStart, (this.gapStart - this.docStart) + 1);
            this.docStart = 0;
            char[] cArr = this.data;
            int i = this.gapStart;
            this.gapStart = i + 1;
            cArr[i] = 61713;
            int parent = getIntN(this.currentParent + 3);
            this.currentParent = parent >= -1 ? parent : this.currentParent + parent;
            return;
        }
        Throwable th2 = th;
        new Error("unexpected endDocument");
        throw th2;
    }

    public void beginEntity(Object obj) {
        Object base = obj;
        if (this.gapStart == 0) {
            ensureSpace(6);
            this.gapEnd--;
            int p = this.gapStart;
            this.data[p] = 61714;
            setIntN(p + 1, find(base));
            setIntN(p + 3, this.currentParent == -1 ? -1 : this.currentParent - p);
            this.gapStart = p + 5;
            this.currentParent = p;
            this.data[this.gapEnd] = 61715;
        }
    }

    public void endEntity() {
        Throwable th;
        if (this.gapEnd + 1 != this.data.length || this.data[this.gapEnd] != END_ENTITY) {
            return;
        }
        if (this.data[this.currentParent] != 61714) {
            Throwable th2 = th;
            new Error("unexpected endEntity");
            throw th2;
        }
        this.gapEnd++;
        char[] cArr = this.data;
        int i = this.gapStart;
        this.gapStart = i + 1;
        cArr[i] = 61715;
        int parent = getIntN(this.currentParent + 3);
        this.currentParent = parent >= -1 ? parent : this.currentParent + parent;
    }

    public void startElement(int index) {
        ensureSpace(10);
        this.gapEnd -= 7;
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = 61704;
        setIntN(this.gapStart, this.gapEnd - this.data.length);
        this.gapStart += 2;
        this.data[this.gapEnd] = 61708;
        setIntN(this.gapEnd + 1, index);
        setIntN(this.gapEnd + 3, this.gapStart - 3);
        setIntN(this.gapEnd + 5, this.currentParent);
        this.currentParent = this.gapStart - 3;
    }

    public void setElementName(int i, int i2) {
        Throwable th;
        int elementIndex = i;
        int nameIndex = i2;
        if (this.data[elementIndex] == BEGIN_ELEMENT_LONG) {
            int j = getIntN(elementIndex + 1);
            elementIndex = j + (j < 0 ? this.data.length : elementIndex);
        }
        if (elementIndex < this.gapEnd) {
            Throwable th2 = th;
            new Error("setElementName before gapEnd");
            throw th2;
        }
        setIntN(elementIndex + 1, nameIndex);
    }

    public void endElement() {
        Throwable th;
        if (this.data[this.gapEnd] != END_ELEMENT_LONG) {
            Throwable th2 = th;
            new Error("unexpected endElement");
            throw th2;
        }
        int index = getIntN(this.gapEnd + 1);
        int begin = getIntN(this.gapEnd + 3);
        int parent = getIntN(this.gapEnd + 5);
        this.currentParent = parent;
        this.gapEnd += 7;
        int offset = this.gapStart - begin;
        int parentOffset = begin - parent;
        if (index >= 4095 || offset >= 65536 || parentOffset >= 65536) {
            this.data[begin] = 61704;
            setIntN(begin + 1, offset);
            this.data[this.gapStart] = 61708;
            setIntN(this.gapStart + 1, index);
            setIntN(this.gapStart + 3, -offset);
            if (parent >= this.gapStart || begin <= this.gapStart) {
                parent -= this.gapStart;
            }
            setIntN(this.gapStart + 5, parent);
            this.gapStart += 7;
            return;
        }
        this.data[begin] = (char) (BEGIN_ELEMENT_SHORT | index);
        this.data[begin + 1] = (char) offset;
        this.data[begin + 2] = (char) parentOffset;
        this.data[this.gapStart] = 61707;
        this.data[this.gapStart + 1] = (char) offset;
        this.gapStart += 2;
    }

    public void startAttribute(Object attrType) {
        startAttribute(find(attrType));
    }

    public void startAttribute(int i) {
        Throwable th;
        int index = i;
        ensureSpace(6);
        this.gapEnd--;
        char[] cArr = this.data;
        int i2 = this.gapStart;
        int i3 = i2 + 1;
        this.gapStart = i3;
        cArr[i2] = 61705;
        if (this.attrStart != 0) {
            Throwable th2 = th;
            new Error("nested attribute");
            throw th2;
        }
        this.attrStart = this.gapStart;
        setIntN(this.gapStart, index);
        setIntN(this.gapStart + 2, this.gapEnd - this.data.length);
        this.gapStart += 4;
        this.data[this.gapEnd] = 61706;
    }

    public void setAttributeName(int attrIndex, int nameIndex) {
        setIntN(attrIndex + 1, nameIndex);
    }

    public void endAttribute() {
        Throwable th;
        if (this.attrStart > 0) {
            if (this.data[this.gapEnd] != END_ATTRIBUTE) {
                Throwable th2 = th;
                new Error("unexpected endAttribute");
                throw th2;
            }
            this.gapEnd++;
            setIntN(this.attrStart + 2, (this.gapStart - this.attrStart) + 1);
            this.attrStart = 0;
            char[] cArr = this.data;
            int i = this.gapStart;
            int i2 = i + 1;
            this.gapStart = i2;
            cArr[i] = 61706;
        }
    }

    public Consumer append(char c) {
        write((int) c);
        return this;
    }

    public void write(int i) {
        int c = i;
        ensureSpace(3);
        if (c <= 40959) {
            char[] cArr = this.data;
            int i2 = this.gapStart;
            int i3 = i2 + 1;
            this.gapStart = i3;
            cArr[i2] = (char) c;
        } else if (c < 65536) {
            char[] cArr2 = this.data;
            int i4 = this.gapStart;
            int i5 = i4 + 1;
            this.gapStart = i5;
            cArr2[i4] = 61702;
            char[] cArr3 = this.data;
            int i6 = this.gapStart;
            int i7 = i6 + 1;
            this.gapStart = i7;
            cArr3[i6] = (char) c;
        } else {
            Char.print(c, this);
        }
    }

    public void writeBoolean(boolean v) {
        ensureSpace(1);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i;
        this.gapStart = i + 1;
        cArr[i2] = v ? BOOL_TRUE : BOOL_FALSE;
    }

    public void writeByte(int v) {
        ensureSpace(1);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = (char) (BYTE_PREFIX + (v & 255));
    }

    public void writeInt(int i) {
        int v = i;
        ensureSpace(3);
        if (v < MIN_INT_SHORT || v > MAX_INT_SHORT) {
            char[] cArr = this.data;
            int i2 = this.gapStart;
            int i3 = i2 + 1;
            this.gapStart = i3;
            cArr[i2] = 61698;
            setIntN(this.gapStart, v);
            this.gapStart += 2;
            return;
        }
        char[] cArr2 = this.data;
        int i4 = this.gapStart;
        int i5 = i4 + 1;
        this.gapStart = i5;
        cArr2[i4] = (char) (INT_SHORT_ZERO + v);
    }

    public void writeLong(long j) {
        long v = j;
        ensureSpace(5);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = 61699;
        char[] cArr2 = this.data;
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.gapStart = i4;
        cArr2[i3] = (char) ((int) (v >>> 48));
        char[] cArr3 = this.data;
        int i5 = this.gapStart;
        int i6 = i5 + 1;
        this.gapStart = i6;
        cArr3[i5] = (char) ((int) (v >>> 32));
        char[] cArr4 = this.data;
        int i7 = this.gapStart;
        int i8 = i7 + 1;
        this.gapStart = i8;
        cArr4[i7] = (char) ((int) (v >>> 16));
        char[] cArr5 = this.data;
        int i9 = this.gapStart;
        int i10 = i9 + 1;
        this.gapStart = i10;
        cArr5[i9] = (char) ((int) v);
    }

    public void writeFloat(float v) {
        ensureSpace(3);
        int i = Float.floatToIntBits(v);
        char[] cArr = this.data;
        int i2 = this.gapStart;
        int i3 = i2 + 1;
        this.gapStart = i3;
        cArr[i2] = 61700;
        char[] cArr2 = this.data;
        int i4 = this.gapStart;
        int i5 = i4 + 1;
        this.gapStart = i5;
        cArr2[i4] = (char) (i >>> 16);
        char[] cArr3 = this.data;
        int i6 = this.gapStart;
        int i7 = i6 + 1;
        this.gapStart = i7;
        cArr3[i6] = (char) i;
    }

    public void writeDouble(double v) {
        ensureSpace(5);
        long l = Double.doubleToLongBits(v);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = 61701;
        char[] cArr2 = this.data;
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.gapStart = i4;
        cArr2[i3] = (char) ((int) (l >>> 48));
        char[] cArr3 = this.data;
        int i5 = this.gapStart;
        int i6 = i5 + 1;
        this.gapStart = i6;
        cArr3[i5] = (char) ((int) (l >>> 32));
        char[] cArr4 = this.data;
        int i7 = this.gapStart;
        int i8 = i7 + 1;
        this.gapStart = i8;
        cArr4[i7] = (char) ((int) (l >>> 16));
        char[] cArr5 = this.data;
        int i9 = this.gapStart;
        int i10 = i9 + 1;
        this.gapStart = i10;
        cArr5[i9] = (char) ((int) l);
    }

    public boolean ignoring() {
        return false;
    }

    public void writeJoiner() {
        ensureSpace(1);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = 61718;
    }

    public void write(char[] cArr, int i, int i2) {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (len == 0) {
            writeJoiner();
        }
        ensureSpace(len);
        while (len > 0) {
            int i3 = off;
            off++;
            char ch = buf[i3];
            len--;
            if (ch <= 40959) {
                char[] cArr2 = this.data;
                int i4 = this.gapStart;
                int i5 = i4 + 1;
                this.gapStart = i5;
                cArr2[i4] = ch;
            } else {
                write((int) ch);
                ensureSpace(len);
            }
        }
    }

    public void write(String str) {
        String str2 = str;
        write((CharSequence) str2, 0, str2.length());
    }

    public void write(CharSequence charSequence, int i, int i2) {
        CharSequence str = charSequence;
        int start = i;
        int length = i2;
        if (length == 0) {
            writeJoiner();
        }
        ensureSpace(length);
        while (length > 0) {
            int i3 = start;
            start++;
            char ch = str.charAt(i3);
            length--;
            if (ch <= 40959) {
                char[] cArr = this.data;
                int i4 = this.gapStart;
                int i5 = i4 + 1;
                this.gapStart = i5;
                cArr[i4] = ch;
            } else {
                write((int) ch);
                ensureSpace(length);
            }
        }
    }

    public void writeCDATA(char[] chars, int offset, int i) {
        int length = i;
        ensureSpace(3 + length);
        int i2 = this.gapStart;
        int i3 = i2;
        int i4 = i2 + 1;
        this.data[i3] = 61717;
        setIntN(i4, length);
        int i5 = i4 + 2;
        System.arraycopy(chars, offset, this.data, i5, length);
        this.gapStart = i5 + length;
    }

    public Consumer append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (csq == null) {
            csq = "null";
        }
        return append(csq, 0, csq.length());
    }

    public Consumer append(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        if (csq == null) {
            csq = "null";
        }
        for (int i3 = start; i3 < end; i3++) {
            Consumer append = append(csq.charAt(i3));
        }
        return this;
    }

    public boolean isEmpty() {
        return (this.gapStart == 0 ? this.gapEnd : 0) == this.data.length;
    }

    public int size() {
        int size = 0;
        int i = 0;
        while (true) {
            i = nextPos(i);
            if (i == 0) {
                return size;
            }
            size++;
        }
    }

    public int createPos(int index, boolean isAfter) {
        return createRelativePos(0, index, isAfter);
    }

    public final int posToDataIndex(int i) {
        int ipos = i;
        if (ipos == -1) {
            return this.data.length;
        }
        int index = ipos >>> 1;
        if ((ipos & 1) != 0) {
            index--;
        }
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        if ((ipos & 1) != 0) {
            index = nextDataIndex(index);
            if (index < 0) {
                return this.data.length;
            }
            if (index == this.gapStart) {
                index += this.gapEnd - this.gapStart;
            }
        }
        return index;
    }

    public int firstChildPos(int ipos) {
        int index = gotoChildrenStart(posToDataIndex(ipos));
        if (index < 0) {
            return 0;
        }
        return index << 1;
    }

    public final int gotoChildrenStart(int i) {
        int index;
        int index2;
        int index3 = i;
        if (index3 == this.data.length) {
            return -1;
        }
        char datum = this.data[index3];
        if ((datum >= BEGIN_ELEMENT_SHORT && datum <= 45055) || datum == BEGIN_ELEMENT_LONG) {
            index = index3 + 3;
        } else if (datum != BEGIN_DOCUMENT && datum != 61714) {
            return -1;
        } else {
            index = index3 + 5;
        }
        while (true) {
            if (index >= this.gapStart) {
                index += this.gapEnd - this.gapStart;
            }
            char datum2 = this.data[index];
            if (datum2 == BEGIN_ATTRIBUTE_LONG) {
                int end = getIntN(index + 3);
                index2 = end + (end < 0 ? this.data.length : index);
            } else if (datum2 == END_ATTRIBUTE || datum2 == JOINER) {
                index2 = index + 1;
            } else if (datum2 != DOCUMENT_URI) {
                return index;
            } else {
                index2 = index + 3;
            }
        }
    }

    public int parentPos(int ipos) {
        int index = posToDataIndex(ipos);
        do {
            index = parentOrEntityI(index);
            if (index == -1) {
                return -1;
            }
        } while (this.data[index] == 61714);
        return index << 1;
    }

    public int parentOrEntityPos(int ipos) {
        int index = parentOrEntityI(posToDataIndex(ipos));
        return index < 0 ? -1 : index << 1;
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 158 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parentOrEntityI(int r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r5 = r1
            r6 = r0
            char[] r6 = r6.data
            int r6 = r6.length
            if (r5 != r6) goto L_0x000c
            r5 = -1
            r0 = r5
        L_0x000b:
            return r0
        L_0x000c:
            r5 = r0
            char[] r5 = r5.data
            r6 = r1
            char r5 = r5[r6]
            r2 = r5
            r5 = r2
            r6 = 61712(0xf110, float:8.6477E-41)
            if (r5 == r6) goto L_0x001f
            r5 = r2
            r6 = 61714(0xf112, float:8.648E-41)
            if (r5 != r6) goto L_0x0035
        L_0x001f:
            r5 = r0
            r6 = r1
            r7 = 3
            int r6 = r6 + 3
            int r5 = r5.getIntN(r6)
            r3 = r5
            r5 = r3
            r6 = -1
            if (r5 < r6) goto L_0x0030
            r5 = r3
            r0 = r5
            goto L_0x000b
        L_0x0030:
            r5 = r1
            r6 = r3
            int r5 = r5 + r6
            r0 = r5
            goto L_0x000b
        L_0x0035:
            r5 = r2
            r6 = 40960(0xa000, float:5.7397E-41)
            if (r5 < r6) goto L_0x0055
            r5 = r2
            r6 = 45055(0xafff, float:6.3136E-41)
            if (r5 > r6) goto L_0x0055
            r5 = r0
            char[] r5 = r5.data
            r6 = r1
            r7 = 2
            int r6 = r6 + 2
            char r5 = r5[r6]
            r3 = r5
            r5 = r3
            if (r5 != 0) goto L_0x0051
            r5 = -1
        L_0x004f:
            r0 = r5
            goto L_0x000b
        L_0x0051:
            r5 = r1
            r6 = r3
            int r5 = r5 - r6
            goto L_0x004f
        L_0x0055:
            r5 = r2
            r6 = 61704(0xf108, float:8.6466E-41)
            if (r5 != r6) goto L_0x008d
            r5 = r0
            r6 = r1
            r7 = 1
            int r6 = r6 + 1
            int r5 = r5.getIntN(r6)
            r3 = r5
            r5 = r3
            r6 = r3
            if (r6 >= 0) goto L_0x007f
            r6 = r0
            char[] r6 = r6.data
            int r6 = r6.length
        L_0x006d:
            int r5 = r5 + r6
            r3 = r5
            r5 = r0
            r6 = r3
            r7 = 5
            int r6 = r6 + 5
            int r5 = r5.getIntN(r6)
            r4 = r5
            r5 = r4
            if (r5 != 0) goto L_0x0081
            r5 = -1
            r0 = r5
            goto L_0x000b
        L_0x007f:
            r6 = r1
            goto L_0x006d
        L_0x0081:
            r5 = r4
            if (r5 >= 0) goto L_0x0088
            r5 = r4
            r6 = r3
            int r5 = r5 + r6
            r4 = r5
        L_0x0088:
            r5 = r4
            r0 = r5
            goto L_0x000b
        L_0x008b:
            int r1 = r1 + 1
        L_0x008d:
            r5 = r1
            r6 = r0
            int r6 = r6.gapStart
            if (r5 != r6) goto L_0x0097
            r5 = r0
            int r5 = r5.gapEnd
            r1 = r5
        L_0x0097:
            r5 = r1
            r6 = r0
            char[] r6 = r6.data
            int r6 = r6.length
            if (r5 != r6) goto L_0x00a2
            r5 = -1
            r0 = r5
            goto L_0x000b
        L_0x00a2:
            r5 = r0
            char[] r5 = r5.data
            r6 = r1
            char r5 = r5[r6]
            r2 = r5
            r5 = r2
            switch(r5) {
                case 61706: goto L_0x008b;
                case 61707: goto L_0x00bb;
                case 61708: goto L_0x00c9;
                case 61709: goto L_0x00ad;
                case 61710: goto L_0x00ad;
                case 61711: goto L_0x00ad;
                case 61712: goto L_0x00ad;
                case 61713: goto L_0x00de;
                default: goto L_0x00ad;
            }
        L_0x00ad:
            r5 = r0
            r6 = r1
            int r5 = r5.nextDataIndex(r6)
            r1 = r5
            r5 = r1
            if (r5 >= 0) goto L_0x008d
            r5 = -1
            r0 = r5
            goto L_0x000b
        L_0x00bb:
            r5 = r1
            r6 = r0
            char[] r6 = r6.data
            r7 = r1
            r8 = 1
            int r7 = r7 + 1
            char r6 = r6[r7]
            int r5 = r5 - r6
            r0 = r5
            goto L_0x000b
        L_0x00c9:
            r5 = r0
            r6 = r1
            r7 = 3
            int r6 = r6 + 3
            int r5 = r5.getIntN(r6)
            r3 = r5
            r5 = r3
            if (r5 >= 0) goto L_0x00da
            r5 = r3
            r6 = r1
            int r5 = r5 + r6
            r3 = r5
        L_0x00da:
            r5 = r3
            r0 = r5
            goto L_0x000b
        L_0x00de:
            r5 = -1
            r0 = r5
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.parentOrEntityI(int):int");
    }

    public int getAttributeCount(int parent) {
        int n = 0;
        int firstAttributePos = firstAttributePos(parent);
        while (true) {
            int attr = firstAttributePos;
            if (attr != 0 && getNextKind(attr) == 35) {
                n++;
                firstAttributePos = nextPos(attr);
            }
        }
        return n;
    }

    public boolean gotoAttributesStart(TreePosition treePosition) {
        TreePosition pos = treePosition;
        int index = gotoAttributesStart(pos.ipos >> 1);
        if (index < 0) {
            return false;
        }
        pos.push(this, index << 1);
        return true;
    }

    public int firstAttributePos(int ipos) {
        int index = gotoAttributesStart(posToDataIndex(ipos));
        return index < 0 ? 0 : index << 1;
    }

    public int gotoAttributesStart(int i) {
        int index = i;
        if (index >= this.gapStart) {
            index += this.gapEnd - this.gapStart;
        }
        if (index == this.data.length) {
            return -1;
        }
        char datum = this.data[index];
        if ((datum < BEGIN_ELEMENT_SHORT || datum > 45055) && datum != BEGIN_ELEMENT_LONG) {
            return -1;
        }
        return index + 3;
    }

    public Object get(int i) {
        Throwable th;
        int index = i;
        int i2 = 0;
        do {
            index--;
            if (index < 0) {
                return getPosNext(i2);
            }
            i2 = nextPos(i2);
        } while (i2 != 0);
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public boolean consumeNext(int i, Consumer consumer) {
        int ipos = i;
        Consumer out = consumer;
        if (!hasNext(ipos)) {
            return false;
        }
        int start = posToDataIndex(ipos);
        int end = nextNodeIndex(start, Integer.MAX_VALUE);
        if (end == start) {
            end = nextDataIndex(start);
        }
        if (end >= 0) {
            int consumeIRange = consumeIRange(start, end, out);
        }
        return true;
    }

    public void consumePosRange(int startPos, int endPos, Consumer out) {
        int consumeIRange = consumeIRange(posToDataIndex(startPos), posToDataIndex(endPos), out);
    }

    public int consumeIRange(int i, int i2, Consumer consumer) {
        Throwable th;
        StringBuilder sb;
        int startPosition = i;
        int endPosition = i2;
        Consumer out = consumer;
        int pos = startPosition;
        int limit = (startPosition > this.gapStart || endPosition <= this.gapStart) ? endPosition : this.gapStart;
        while (true) {
            if (pos >= limit) {
                if (pos == this.gapStart && endPosition > this.gapEnd) {
                    pos = this.gapEnd;
                    limit = endPosition;
                }
            }
            int i3 = pos;
            pos++;
            char datum = this.data[i3];
            if (datum <= 40959) {
                int start = pos - 1;
                int lim = limit;
                while (true) {
                    if (pos < lim) {
                        int i4 = pos;
                        pos++;
                        if (this.data[i4] > 40959) {
                            pos--;
                        }
                    }
                }
                out.write(this.data, start, pos - start);
            } else if (datum >= OBJECT_REF_SHORT && datum <= 61439) {
                out.writeObject(this.objects[datum - OBJECT_REF_SHORT]);
            } else if (datum >= BEGIN_ELEMENT_SHORT && datum <= 45055) {
                out.startElement(this.objects[datum - BEGIN_ELEMENT_SHORT]);
                pos += 2;
            } else if (datum < 45056 || datum > 57343) {
                switch (datum) {
                    case 61696:
                    case 61697:
                        out.writeBoolean(datum != 61696);
                        break;
                    case INT_FOLLOWS /*61698*/:
                        out.writeInt(getIntN(pos));
                        pos += 2;
                        break;
                    case LONG_FOLLOWS /*61699*/:
                        out.writeLong(getLongN(pos));
                        pos += 4;
                        break;
                    case FLOAT_FOLLOWS /*61700*/:
                        out.writeFloat(Float.intBitsToFloat(getIntN(pos)));
                        pos += 2;
                        break;
                    case DOUBLE_FOLLOWS /*61701*/:
                        out.writeDouble(Double.longBitsToDouble(getLongN(pos)));
                        pos += 4;
                        break;
                    case CHAR_FOLLOWS /*61702*/:
                        out.write(this.data, pos, (1 + datum) - CHAR_FOLLOWS);
                        pos++;
                        break;
                    case BEGIN_ELEMENT_LONG /*61704*/:
                        int index = getIntN(pos);
                        int i5 = index;
                        int length = index >= 0 ? pos - 1 : this.data.length;
                        pos += 2;
                        out.startElement(this.objects[getIntN(i5 + length + 1)]);
                        break;
                    case BEGIN_ATTRIBUTE_LONG /*61705*/:
                        out.startAttribute(this.objects[getIntN(pos)]);
                        pos += 4;
                        break;
                    case END_ATTRIBUTE /*61706*/:
                        out.endAttribute();
                        break;
                    case END_ELEMENT_SHORT /*61707*/:
                        pos++;
                        out.endElement();
                        break;
                    case END_ELEMENT_LONG /*61708*/:
                        int intN = getIntN(pos);
                        out.endElement();
                        pos += 6;
                        break;
                    case 61710:
                        if (out instanceof PositionConsumer) {
                            ((PositionConsumer) out).consume((SeqPosition) this.objects[getIntN(pos)]);
                            pos += 2;
                            break;
                        }
                    case 61709:
                        out.writeObject(this.objects[getIntN(pos)]);
                        pos += 2;
                        break;
                    case 61711:
                        AbstractSequence seq = (AbstractSequence) this.objects[getIntN(pos)];
                        int ipos = getIntN(pos + 2);
                        if (out instanceof PositionConsumer) {
                            ((PositionConsumer) out).writePosition(seq, ipos);
                        } else {
                            out.writeObject(seq.getIteratorAtPos(ipos));
                        }
                        pos += 4;
                        break;
                    case BEGIN_DOCUMENT /*61712*/:
                        out.startDocument();
                        pos += 4;
                        break;
                    case END_DOCUMENT /*61713*/:
                        out.endDocument();
                        break;
                    case BEGIN_ENTITY /*61714*/:
                        if (out instanceof TreeList) {
                            ((TreeList) out).beginEntity(this.objects[getIntN(pos)]);
                        }
                        pos += 4;
                        break;
                    case END_ENTITY /*61715*/:
                        if (!(out instanceof TreeList)) {
                            break;
                        } else {
                            ((TreeList) out).endEntity();
                            break;
                        }
                    case PROCESSING_INSTRUCTION /*61716*/:
                        String target = (String) this.objects[getIntN(pos)];
                        int length2 = getIntN(pos + 2);
                        int pos2 = pos + 4;
                        if (out instanceof XConsumer) {
                            ((XConsumer) out).writeProcessingInstruction(target, this.data, pos2, length2);
                        }
                        pos = pos2 + length2;
                        break;
                    case CDATA_SECTION /*61717*/:
                        int length3 = getIntN(pos);
                        int pos3 = pos + 2;
                        if (out instanceof XConsumer) {
                            ((XConsumer) out).writeCDATA(this.data, pos3, length3);
                        } else {
                            out.write(this.data, pos3, length3);
                        }
                        pos = pos3 + length3;
                        break;
                    case JOINER /*61718*/:
                        out.write("");
                        break;
                    case COMMENT /*61719*/:
                        int length4 = getIntN(pos);
                        int pos4 = pos + 2;
                        if (out instanceof XConsumer) {
                            ((XConsumer) out).writeComment(this.data, pos4, length4);
                        }
                        pos = pos4 + length4;
                        break;
                    case DOCUMENT_URI /*61720*/:
                        if (out instanceof TreeList) {
                            ((TreeList) out).writeDocumentUri(this.objects[getIntN(pos)]);
                        }
                        pos += 2;
                        break;
                    default:
                        Throwable th2 = th;
                        new StringBuilder();
                        new Error(sb.append("unknown code:").append(datum).toString());
                        throw th2;
                }
            } else {
                out.writeInt(datum - INT_SHORT_ZERO);
            }
        }
        return pos;
    }

    public void toString(String str, StringBuffer stringBuffer) {
        Throwable th;
        StringBuilder sb;
        int index;
        int pos;
        String sep = str;
        StringBuffer sbuf = stringBuffer;
        int pos2 = 0;
        int limit = this.gapStart;
        boolean seen = false;
        boolean inStartTag = false;
        while (true) {
            if (pos2 >= limit) {
                if (pos2 == this.gapStart) {
                    pos2 = this.gapEnd;
                    limit = this.data.length;
                    if (pos2 == limit) {
                        return;
                    }
                } else {
                    return;
                }
            }
            int i = pos2;
            pos2++;
            char datum = this.data[i];
            if (datum <= 40959) {
                int start = pos2 - 1;
                int lim = limit;
                while (true) {
                    if (pos2 < lim) {
                        int i2 = pos2;
                        pos2++;
                        if (this.data[i2] > 40959) {
                            pos2--;
                        }
                    }
                }
                if (inStartTag) {
                    StringBuffer append = sbuf.append('>');
                    inStartTag = false;
                }
                StringBuffer append2 = sbuf.append(this.data, start, pos2 - start);
                seen = false;
            } else if (datum >= OBJECT_REF_SHORT && datum <= 61439) {
                if (inStartTag) {
                    StringBuffer append3 = sbuf.append('>');
                    inStartTag = false;
                }
                if (seen) {
                    StringBuffer append4 = sbuf.append(sep);
                } else {
                    seen = true;
                }
                StringBuffer append5 = sbuf.append(this.objects[datum - OBJECT_REF_SHORT]);
            } else if (datum >= BEGIN_ELEMENT_SHORT && datum <= 45055) {
                if (inStartTag) {
                    StringBuffer append6 = sbuf.append('>');
                }
                int index2 = datum - BEGIN_ELEMENT_SHORT;
                if (seen) {
                    StringBuffer append7 = sbuf.append(sep);
                }
                StringBuffer append8 = sbuf.append('<');
                StringBuffer append9 = sbuf.append(this.objects[index2].toString());
                pos2 += 2;
                seen = false;
                inStartTag = true;
            } else if (datum < 45056 || datum > 57343) {
                switch (datum) {
                    case 61696:
                    case 61697:
                        if (inStartTag) {
                            StringBuffer append10 = sbuf.append('>');
                            inStartTag = false;
                        }
                        if (seen) {
                            StringBuffer append11 = sbuf.append(sep);
                        } else {
                            seen = true;
                        }
                        StringBuffer append12 = sbuf.append(datum != 61696);
                        break;
                    case INT_FOLLOWS /*61698*/:
                        if (inStartTag) {
                            StringBuffer append13 = sbuf.append('>');
                            inStartTag = false;
                        }
                        if (seen) {
                            StringBuffer append14 = sbuf.append(sep);
                        } else {
                            seen = true;
                        }
                        StringBuffer append15 = sbuf.append(getIntN(pos2));
                        pos2 += 2;
                        break;
                    case LONG_FOLLOWS /*61699*/:
                        if (inStartTag) {
                            StringBuffer append16 = sbuf.append('>');
                            inStartTag = false;
                        }
                        if (seen) {
                            StringBuffer append17 = sbuf.append(sep);
                        } else {
                            seen = true;
                        }
                        StringBuffer append18 = sbuf.append(getLongN(pos2));
                        pos2 += 4;
                        break;
                    case FLOAT_FOLLOWS /*61700*/:
                        if (inStartTag) {
                            StringBuffer append19 = sbuf.append('>');
                            inStartTag = false;
                        }
                        if (seen) {
                            StringBuffer append20 = sbuf.append(sep);
                        } else {
                            seen = true;
                        }
                        StringBuffer append21 = sbuf.append(Float.intBitsToFloat(getIntN(pos2)));
                        pos2 += 2;
                        break;
                    case DOUBLE_FOLLOWS /*61701*/:
                        if (inStartTag) {
                            StringBuffer append22 = sbuf.append('>');
                            inStartTag = false;
                        }
                        if (seen) {
                            StringBuffer append23 = sbuf.append(sep);
                        } else {
                            seen = true;
                        }
                        StringBuffer append24 = sbuf.append(Double.longBitsToDouble(getLongN(pos2)));
                        pos2 += 4;
                        break;
                    case CHAR_FOLLOWS /*61702*/:
                        if (inStartTag) {
                            StringBuffer append25 = sbuf.append('>');
                            inStartTag = false;
                        }
                        StringBuffer append26 = sbuf.append(this.data, pos2, (1 + datum) - CHAR_FOLLOWS);
                        seen = false;
                        pos2++;
                        break;
                    case BEGIN_ELEMENT_LONG /*61704*/:
                        int index3 = getIntN(pos2);
                        int i3 = index3;
                        int length = index3 >= 0 ? pos2 - 1 : this.data.length;
                        pos2 += 2;
                        int index4 = getIntN(i3 + length + 1);
                        if (inStartTag) {
                            StringBuffer append27 = sbuf.append('>');
                        } else if (seen) {
                            StringBuffer append28 = sbuf.append(sep);
                        }
                        StringBuffer append29 = sbuf.append('<');
                        StringBuffer append30 = sbuf.append(this.objects[index4]);
                        seen = false;
                        inStartTag = true;
                        break;
                    case BEGIN_ATTRIBUTE_LONG /*61705*/:
                        int index5 = getIntN(pos2);
                        StringBuffer append31 = sbuf.append(' ');
                        StringBuffer append32 = sbuf.append(this.objects[index5]);
                        StringBuffer append33 = sbuf.append("=\"");
                        inStartTag = false;
                        pos2 += 4;
                        break;
                    case END_ATTRIBUTE /*61706*/:
                        StringBuffer append34 = sbuf.append('\"');
                        inStartTag = true;
                        seen = false;
                        break;
                    case END_ELEMENT_SHORT /*61707*/:
                    case END_ELEMENT_LONG /*61708*/:
                        if (datum == END_ELEMENT_SHORT) {
                            int i4 = pos2;
                            pos = pos2 + 1;
                            index = this.data[(pos - 2) - this.data[i4]] - BEGIN_ELEMENT_SHORT;
                        } else {
                            index = getIntN(pos2);
                            pos = pos2 + 6;
                        }
                        if (inStartTag) {
                            StringBuffer append35 = sbuf.append("/>");
                        } else {
                            StringBuffer append36 = sbuf.append("</");
                            StringBuffer append37 = sbuf.append(this.objects[index]);
                            StringBuffer append38 = sbuf.append('>');
                        }
                        inStartTag = false;
                        seen = true;
                        break;
                    case 61709:
                    case 61710:
                        if (inStartTag) {
                            StringBuffer append39 = sbuf.append('>');
                            inStartTag = false;
                        }
                        if (seen) {
                            StringBuffer append40 = sbuf.append(sep);
                        } else {
                            seen = true;
                        }
                        StringBuffer append41 = sbuf.append(this.objects[getIntN(pos2)]);
                        pos2 += 2;
                        break;
                    case 61711:
                        if (inStartTag) {
                            StringBuffer append42 = sbuf.append('>');
                            inStartTag = false;
                        }
                        if (seen) {
                            StringBuffer append43 = sbuf.append(sep);
                        } else {
                            seen = true;
                        }
                        StringBuffer append44 = sbuf.append(((AbstractSequence) this.objects[getIntN(pos2)]).getIteratorAtPos(getIntN(pos2 + 2)));
                        pos2 += 4;
                        break;
                    case BEGIN_DOCUMENT /*61712*/:
                    case BEGIN_ENTITY /*61714*/:
                        pos2 += 4;
                        break;
                    case END_DOCUMENT /*61713*/:
                    case END_ENTITY /*61715*/:
                    case JOINER /*61718*/:
                        break;
                    case PROCESSING_INSTRUCTION /*61716*/:
                        if (inStartTag) {
                            StringBuffer append45 = sbuf.append('>');
                            inStartTag = false;
                        }
                        StringBuffer append46 = sbuf.append("<?");
                        int index6 = getIntN(pos2);
                        int pos3 = pos2 + 2;
                        StringBuffer append47 = sbuf.append(this.objects[index6]);
                        int index7 = getIntN(pos3);
                        pos2 = pos3 + 2;
                        if (index7 > 0) {
                            StringBuffer append48 = sbuf.append(' ');
                            StringBuffer append49 = sbuf.append(this.data, pos2, index7);
                            pos2 += index7;
                        }
                        StringBuffer append50 = sbuf.append("?>");
                        break;
                    case CDATA_SECTION /*61717*/:
                        if (inStartTag) {
                            StringBuffer append51 = sbuf.append('>');
                            inStartTag = false;
                        }
                        int index8 = getIntN(pos2);
                        int pos4 = pos2 + 2;
                        StringBuffer append52 = sbuf.append("<![CDATA[");
                        StringBuffer append53 = sbuf.append(this.data, pos4, index8);
                        StringBuffer append54 = sbuf.append("]]>");
                        pos2 = pos4 + index8;
                        break;
                    case COMMENT /*61719*/:
                        if (inStartTag) {
                            StringBuffer append55 = sbuf.append('>');
                            inStartTag = false;
                        }
                        int index9 = getIntN(pos2);
                        int pos5 = pos2 + 2;
                        StringBuffer append56 = sbuf.append("<!--");
                        StringBuffer append57 = sbuf.append(this.data, pos5, index9);
                        StringBuffer append58 = sbuf.append("-->");
                        pos2 = pos5 + index9;
                        break;
                    case DOCUMENT_URI /*61720*/:
                        pos2 += 2;
                        break;
                    default:
                        Throwable th2 = th;
                        new StringBuilder();
                        new Error(sb.append("unknown code:").append(datum).toString());
                        throw th2;
                }
            } else {
                if (inStartTag) {
                    StringBuffer append59 = sbuf.append('>');
                    inStartTag = false;
                }
                if (seen) {
                    StringBuffer append60 = sbuf.append(sep);
                } else {
                    seen = true;
                }
                StringBuffer append61 = sbuf.append(datum - INT_SHORT_ZERO);
            }
        }
    }

    public boolean hasNext(int ipos) {
        int index = posToDataIndex(ipos);
        if (index == this.data.length) {
            return false;
        }
        char ch = this.data[index];
        return (ch == END_ATTRIBUTE || ch == END_ELEMENT_SHORT || ch == END_ELEMENT_LONG || ch == END_DOCUMENT) ? false : true;
    }

    public int getNextKind(int ipos) {
        return getNextKindI(posToDataIndex(ipos));
    }

    public int getNextKindI(int i) {
        int index = i;
        if (index == this.data.length) {
            return 0;
        }
        char datum = this.data[index];
        if (datum <= 40959) {
            return 29;
        }
        if (datum >= OBJECT_REF_SHORT && datum <= 61439) {
            return 32;
        }
        if (datum >= BEGIN_ELEMENT_SHORT && datum <= 45055) {
            return 33;
        }
        if ((datum & 65280) == BYTE_PREFIX) {
            return 28;
        }
        if (datum >= 45056 && datum <= 57343) {
            return 22;
        }
        switch (datum) {
            case 61696:
            case 61697:
                return 27;
            case INT_FOLLOWS /*61698*/:
                return 22;
            case LONG_FOLLOWS /*61699*/:
                return 24;
            case FLOAT_FOLLOWS /*61700*/:
                return 25;
            case DOUBLE_FOLLOWS /*61701*/:
                return 26;
            case CHAR_FOLLOWS /*61702*/:
            case BEGIN_DOCUMENT /*61712*/:
                return 34;
            case BEGIN_ELEMENT_LONG /*61704*/:
                return 33;
            case BEGIN_ATTRIBUTE_LONG /*61705*/:
                return 35;
            case END_ATTRIBUTE /*61706*/:
            case END_ELEMENT_SHORT /*61707*/:
            case END_ELEMENT_LONG /*61708*/:
            case END_DOCUMENT /*61713*/:
            case END_ENTITY /*61715*/:
                return 0;
            case BEGIN_ENTITY /*61714*/:
                return getNextKind((index + 5) << 1);
            case PROCESSING_INSTRUCTION /*61716*/:
                return 37;
            case CDATA_SECTION /*61717*/:
                return 31;
            case COMMENT /*61719*/:
                return 36;
            default:
                return 32;
        }
    }

    public Object getNextTypeObject(int ipos) {
        int index;
        Object obj;
        for (int index2 = posToDataIndex(ipos); index2 != this.data.length; index2 += 5) {
            char datum = this.data[index2];
            if (datum != 61714) {
                if (datum >= BEGIN_ELEMENT_SHORT && datum <= 45055) {
                    index = datum - BEGIN_ELEMENT_SHORT;
                } else if (datum == BEGIN_ELEMENT_LONG) {
                    int j = getIntN(index2 + 1);
                    index = getIntN(j + (j < 0 ? this.data.length : index2) + 1);
                } else if (datum == BEGIN_ATTRIBUTE_LONG) {
                    index = getIntN(index2 + 1);
                } else if (datum != PROCESSING_INSTRUCTION) {
                    return null;
                } else {
                    index = getIntN(index2 + 1);
                }
                if (index < 0) {
                    obj = null;
                } else {
                    obj = this.objects[index];
                }
                return obj;
            }
        }
        return null;
    }

    public String getNextTypeName(int ipos) {
        Object type = getNextTypeObject(ipos);
        return type == null ? null : type.toString();
    }

    public Object getPosPrevious(int i) {
        int ipos = i;
        if ((ipos & 1) == 0 || ipos == -1) {
            return super.getPosPrevious(ipos);
        }
        return getPosNext(ipos - 3);
    }

    private Object copyToList(int startPosition, int endPosition) {
        TreeList treeList;
        new TreeList(this, startPosition, endPosition);
        return treeList;
    }

    public int getPosNextInt(int i) {
        int ipos = i;
        int index = posToDataIndex(ipos);
        if (index < this.data.length) {
            char datum = this.data[index];
            if (datum >= 45056 && datum <= 57343) {
                return datum - INT_SHORT_ZERO;
            }
            if (datum == 61698) {
                return getIntN(index + 1);
            }
        }
        return ((Number) getPosNext(ipos)).intValue();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v30, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v34, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v38, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v40, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v42, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v45, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v50, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v62, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getPosNext(int r14) {
        /*
            r13 = this;
            r1 = r13
            r2 = r14
            r6 = r1
            r7 = r2
            int r6 = r6.posToDataIndex(r7)
            r3 = r6
            r6 = r3
            r7 = r1
            char[] r7 = r7.data
            int r7 = r7.length
            if (r6 != r7) goto L_0x0014
            java.lang.Object r6 = gnu.lists.Sequence.eofValue
            r1 = r6
        L_0x0013:
            return r1
        L_0x0014:
            r6 = r1
            char[] r6 = r6.data
            r7 = r3
            char r6 = r6[r7]
            r4 = r6
            r6 = r4
            r7 = 40959(0x9fff, float:5.7396E-41)
            if (r6 > r7) goto L_0x0028
            r6 = r4
            java.lang.Object r6 = gnu.lists.Convert.toObject((char) r6)
            r1 = r6
            goto L_0x0013
        L_0x0028:
            r6 = r4
            r7 = 57344(0xe000, float:8.0356E-41)
            if (r6 < r7) goto L_0x0040
            r6 = r4
            r7 = 61439(0xefff, float:8.6094E-41)
            if (r6 > r7) goto L_0x0040
            r6 = r1
            java.lang.Object[] r6 = r6.objects
            r7 = r4
            r8 = 57344(0xe000, float:8.0356E-41)
            int r7 = r7 - r8
            r6 = r6[r7]
            r1 = r6
            goto L_0x0013
        L_0x0040:
            r6 = r4
            r7 = 40960(0xa000, float:5.7397E-41)
            if (r6 < r7) goto L_0x0062
            r6 = r4
            r7 = 45055(0xafff, float:6.3136E-41)
            if (r6 > r7) goto L_0x0062
            r6 = r1
            r7 = r3
            r8 = r3
            r9 = r1
            char[] r9 = r9.data
            r10 = r3
            r11 = 1
            int r10 = r10 + 1
            char r9 = r9[r10]
            int r8 = r8 + r9
            r9 = 2
            int r8 = r8 + 2
            java.lang.Object r6 = r6.copyToList(r7, r8)
            r1 = r6
            goto L_0x0013
        L_0x0062:
            r6 = r4
            r7 = 45056(0xb000, float:6.3137E-41)
            if (r6 < r7) goto L_0x0079
            r6 = r4
            r7 = 57343(0xdfff, float:8.0355E-41)
            if (r6 > r7) goto L_0x0079
            r6 = r4
            r7 = 49152(0xc000, float:6.8877E-41)
            int r6 = r6 - r7
            java.lang.Object r6 = gnu.lists.Convert.toObject((int) r6)
            r1 = r6
            goto L_0x0013
        L_0x0079:
            r6 = r4
            switch(r6) {
                case 61696: goto L_0x00c1;
                case 61697: goto L_0x00c1;
                case 61698: goto L_0x00d1;
                case 61699: goto L_0x00e1;
                case 61700: goto L_0x00f1;
                case 61701: goto L_0x0105;
                case 61702: goto L_0x0119;
                case 61703: goto L_0x007d;
                case 61704: goto L_0x014c;
                case 61705: goto L_0x0129;
                case 61706: goto L_0x016f;
                case 61707: goto L_0x016f;
                case 61708: goto L_0x016f;
                case 61709: goto L_0x0174;
                case 61710: goto L_0x0174;
                case 61711: goto L_0x018b;
                case 61712: goto L_0x009f;
                case 61713: goto L_0x016f;
                case 61714: goto L_0x007d;
                case 61715: goto L_0x007d;
                case 61716: goto L_0x007d;
                case 61717: goto L_0x007d;
                case 61718: goto L_0x0185;
                default: goto L_0x007d;
            }
        L_0x007d:
            r6 = r1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r12 = r7
            r7 = r12
            r8 = r12
            r8.<init>()
            java.lang.String r8 = "getPosNext, code="
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r4
            java.lang.String r8 = java.lang.Integer.toHexString(r8)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.RuntimeException r6 = r6.unsupported(r7)
            throw r6
        L_0x009f:
            r6 = r1
            r7 = r3
            r8 = 1
            int r7 = r7 + 1
            int r6 = r6.getIntN(r7)
            r5 = r6
            r6 = r5
            r7 = r5
            if (r7 >= 0) goto L_0x00bf
            r7 = r1
            char[] r7 = r7.data
            int r7 = r7.length
        L_0x00b1:
            int r6 = r6 + r7
            r5 = r6
            int r5 = r5 + 1
            r6 = r1
            r7 = r3
            r8 = r5
            java.lang.Object r6 = r6.copyToList(r7, r8)
            r1 = r6
            goto L_0x0013
        L_0x00bf:
            r7 = r3
            goto L_0x00b1
        L_0x00c1:
            r6 = r4
            r7 = 61696(0xf100, float:8.6455E-41)
            if (r6 == r7) goto L_0x00cf
            r6 = 1
        L_0x00c8:
            java.lang.Object r6 = gnu.lists.Convert.toObject((boolean) r6)
            r1 = r6
            goto L_0x0013
        L_0x00cf:
            r6 = 0
            goto L_0x00c8
        L_0x00d1:
            r6 = r1
            r7 = r3
            r8 = 1
            int r7 = r7 + 1
            int r6 = r6.getIntN(r7)
            java.lang.Object r6 = gnu.lists.Convert.toObject((int) r6)
            r1 = r6
            goto L_0x0013
        L_0x00e1:
            r6 = r1
            r7 = r3
            r8 = 1
            int r7 = r7 + 1
            long r6 = r6.getLongN(r7)
            java.lang.Object r6 = gnu.lists.Convert.toObject((long) r6)
            r1 = r6
            goto L_0x0013
        L_0x00f1:
            r6 = r1
            r7 = r3
            r8 = 1
            int r7 = r7 + 1
            int r6 = r6.getIntN(r7)
            float r6 = java.lang.Float.intBitsToFloat(r6)
            java.lang.Object r6 = gnu.lists.Convert.toObject((float) r6)
            r1 = r6
            goto L_0x0013
        L_0x0105:
            r6 = r1
            r7 = r3
            r8 = 1
            int r7 = r7 + 1
            long r6 = r6.getLongN(r7)
            double r6 = java.lang.Double.longBitsToDouble(r6)
            java.lang.Object r6 = gnu.lists.Convert.toObject((double) r6)
            r1 = r6
            goto L_0x0013
        L_0x0119:
            r6 = r1
            char[] r6 = r6.data
            r7 = r3
            r8 = 1
            int r7 = r7 + 1
            char r6 = r6[r7]
            java.lang.Object r6 = gnu.lists.Convert.toObject((char) r6)
            r1 = r6
            goto L_0x0013
        L_0x0129:
            r6 = r1
            r7 = r3
            r8 = 3
            int r7 = r7 + 3
            int r6 = r6.getIntN(r7)
            r5 = r6
            r6 = r5
            r7 = r5
            if (r7 >= 0) goto L_0x014a
            r7 = r1
            char[] r7 = r7.data
            int r7 = r7.length
        L_0x013b:
            int r6 = r6 + r7
            r5 = r6
            r6 = r1
            r7 = r3
            r8 = r5
            r9 = 1
            int r8 = r8 + 1
            java.lang.Object r6 = r6.copyToList(r7, r8)
            r1 = r6
            goto L_0x0013
        L_0x014a:
            r7 = r3
            goto L_0x013b
        L_0x014c:
            r6 = r1
            r7 = r3
            r8 = 1
            int r7 = r7 + 1
            int r6 = r6.getIntN(r7)
            r5 = r6
            r6 = r5
            r7 = r5
            if (r7 >= 0) goto L_0x016d
            r7 = r1
            char[] r7 = r7.data
            int r7 = r7.length
        L_0x015e:
            int r6 = r6 + r7
            r5 = r6
            r6 = r1
            r7 = r3
            r8 = r5
            r9 = 7
            int r8 = r8 + 7
            java.lang.Object r6 = r6.copyToList(r7, r8)
            r1 = r6
            goto L_0x0013
        L_0x016d:
            r7 = r3
            goto L_0x015e
        L_0x016f:
            java.lang.Object r6 = gnu.lists.Sequence.eofValue
            r1 = r6
            goto L_0x0013
        L_0x0174:
            r6 = r1
            java.lang.Object[] r6 = r6.objects
            r7 = r1
            r8 = r3
            r9 = 1
            int r8 = r8 + 1
            int r7 = r7.getIntN(r8)
            r6 = r6[r7]
            r1 = r6
            goto L_0x0013
        L_0x0185:
            java.lang.String r6 = ""
            r1 = r6
            goto L_0x0013
        L_0x018b:
            r6 = r1
            java.lang.Object[] r6 = r6.objects
            r7 = r1
            r8 = r3
            r9 = 1
            int r8 = r8 + 1
            int r7 = r7.getIntN(r8)
            r6 = r6[r7]
            gnu.lists.AbstractSequence r6 = (gnu.lists.AbstractSequence) r6
            r5 = r6
            r6 = r1
            r7 = r3
            r8 = 3
            int r7 = r7 + 3
            int r6 = r6.getIntN(r7)
            r2 = r6
            r6 = r5
            r7 = r2
            gnu.lists.SeqPosition r6 = r6.getIteratorAtPos(r7)
            r1 = r6
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.getPosNext(int):java.lang.Object");
    }

    public void stringValue(int startIndex, int i, StringBuffer stringBuffer) {
        int endIndex = i;
        StringBuffer sbuf = stringBuffer;
        int i2 = startIndex;
        while (true) {
            int index = i2;
            if (index < endIndex && index >= 0) {
                i2 = stringValue(false, index, sbuf);
            } else {
                return;
            }
        }
    }

    public int stringValue(int i, StringBuffer stringBuffer) {
        int index = i;
        StringBuffer sbuf = stringBuffer;
        int next = nextNodeIndex(index, Integer.MAX_VALUE);
        if (next <= index) {
            return stringValue(false, index, sbuf);
        }
        stringValue(index, next, sbuf);
        return index;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0115, code lost:
        r10 = getIntN(r3);
        r3 = r3 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x011f, code lost:
        if (r2 == false) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0125, code lost:
        if (r8 != CDATA_SECTION) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0127, code lost:
        r13 = r4.append(r1.data, r3, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        return r3 + r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int stringValue(boolean r19, int r20, java.lang.StringBuffer r21) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r13 = 0
            r5 = r13
            r13 = 0
            r6 = r13
            r13 = r3
            r14 = r1
            int r14 = r14.gapStart
            if (r13 < r14) goto L_0x001c
            r13 = r3
            r14 = r1
            int r14 = r14.gapEnd
            r15 = r1
            int r15 = r15.gapStart
            int r14 = r14 - r15
            int r13 = r13 + r14
            r3 = r13
        L_0x001c:
            r13 = r3
            r14 = r1
            char[] r14 = r14.data
            int r14 = r14.length
            if (r13 != r14) goto L_0x0026
            r13 = -1
            r1 = r13
        L_0x0025:
            return r1
        L_0x0026:
            r13 = r1
            char[] r13 = r13.data
            r14 = r3
            char r13 = r13[r14]
            r8 = r13
            int r3 = r3 + 1
            r13 = 0
            r9 = r13
            r13 = r8
            r14 = 40959(0x9fff, float:5.7396E-41)
            if (r13 > r14) goto L_0x0040
            r13 = r4
            r14 = r8
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = r3
            r1 = r13
            goto L_0x0025
        L_0x0040:
            r13 = r8
            r14 = 57344(0xe000, float:8.0356E-41)
            if (r13 < r14) goto L_0x007f
            r13 = r8
            r14 = 61439(0xefff, float:8.6094E-41)
            if (r13 > r14) goto L_0x007f
            r13 = r9
            if (r13 == 0) goto L_0x0056
            r13 = r4
            r14 = 32
            java.lang.StringBuffer r13 = r13.append(r14)
        L_0x0056:
            r13 = r1
            java.lang.Object[] r13 = r13.objects
            r14 = r8
            r15 = 57344(0xe000, float:8.0356E-41)
            int r14 = r14 - r15
            r13 = r13[r14]
            r5 = r13
            r13 = 0
            r9 = r13
        L_0x0063:
            r13 = r5
            if (r13 == 0) goto L_0x006c
            r13 = r4
            r14 = r5
            java.lang.StringBuffer r13 = r13.append(r14)
        L_0x006c:
            r13 = r6
            if (r13 <= 0) goto L_0x007c
        L_0x006f:
            r13 = r1
            r14 = 1
            r15 = r6
            r16 = r4
            int r13 = r13.stringValue((boolean) r14, (int) r15, (java.lang.StringBuffer) r16)
            r6 = r13
            r13 = r6
            if (r13 >= 0) goto L_0x006f
        L_0x007c:
            r13 = r3
            r1 = r13
            goto L_0x0025
        L_0x007f:
            r13 = r8
            r14 = 40960(0xa000, float:5.7397E-41)
            if (r13 < r14) goto L_0x009d
            r13 = r8
            r14 = 45055(0xafff, float:6.3136E-41)
            if (r13 > r14) goto L_0x009d
            r13 = r3
            r14 = 2
            int r13 = r13 + 2
            r6 = r13
            r13 = r1
            char[] r13 = r13.data
            r14 = r3
            char r13 = r13[r14]
            r14 = r3
            int r13 = r13 + r14
            r14 = 1
            int r13 = r13 + 1
            r3 = r13
            goto L_0x0063
        L_0x009d:
            r13 = r8
            r14 = 65280(0xff00, float:9.1477E-41)
            r13 = r13 & r14
            r14 = 61440(0xf000, float:8.6096E-41)
            if (r13 != r14) goto L_0x00b5
            r13 = r4
            r14 = r8
            r15 = 255(0xff, float:3.57E-43)
            r14 = r14 & 255(0xff, float:3.57E-43)
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = r3
            r1 = r13
            goto L_0x0025
        L_0x00b5:
            r13 = r8
            r14 = 45056(0xb000, float:6.3137E-41)
            if (r13 < r14) goto L_0x00cf
            r13 = r8
            r14 = 57343(0xdfff, float:8.0355E-41)
            if (r13 > r14) goto L_0x00cf
            r13 = r4
            r14 = r8
            r15 = 49152(0xc000, float:6.8877E-41)
            int r14 = r14 - r15
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = r3
            r1 = r13
            goto L_0x0025
        L_0x00cf:
            r13 = r8
            switch(r13) {
                case 61696: goto L_0x0138;
                case 61697: goto L_0x0138;
                case 61698: goto L_0x0156;
                case 61699: goto L_0x0174;
                case 61700: goto L_0x0192;
                case 61701: goto L_0x01b4;
                case 61702: goto L_0x01d6;
                case 61703: goto L_0x00d3;
                case 61704: goto L_0x01fb;
                case 61705: goto L_0x0227;
                case 61706: goto L_0x0223;
                case 61707: goto L_0x0223;
                case 61708: goto L_0x0223;
                case 61709: goto L_0x00d3;
                case 61710: goto L_0x00d3;
                case 61711: goto L_0x024a;
                case 61712: goto L_0x01ea;
                case 61713: goto L_0x0223;
                case 61714: goto L_0x01ea;
                case 61715: goto L_0x0223;
                case 61716: goto L_0x0113;
                case 61717: goto L_0x0115;
                case 61718: goto L_0x021f;
                case 61719: goto L_0x0115;
                case 61720: goto L_0x010c;
                default: goto L_0x00d3;
            }
        L_0x00d3:
            java.lang.Error r13 = new java.lang.Error
            r17 = r13
            r13 = r17
            r14 = r17
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r17 = r15
            r15 = r17
            r16 = r17
            r16.<init>()
            java.lang.String r16 = "unimplemented: "
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r8
            java.lang.String r16 = java.lang.Integer.toHexString(r16)
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = " at:"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r3
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            r14.<init>(r15)
            throw r13
        L_0x010c:
            r13 = r3
            r14 = 2
            int r13 = r13 + 2
            r1 = r13
            goto L_0x0025
        L_0x0113:
            int r3 = r3 + 2
        L_0x0115:
            r13 = r1
            r14 = r3
            int r13 = r13.getIntN(r14)
            r10 = r13
            int r3 = r3 + 2
            r13 = r2
            if (r13 == 0) goto L_0x0127
            r13 = r8
            r14 = 61717(0xf115, float:8.6484E-41)
            if (r13 != r14) goto L_0x0132
        L_0x0127:
            r13 = r4
            r14 = r1
            char[] r14 = r14.data
            r15 = r3
            r16 = r10
            java.lang.StringBuffer r13 = r13.append(r14, r15, r16)
        L_0x0132:
            r13 = r3
            r14 = r10
            int r13 = r13 + r14
            r1 = r13
            goto L_0x0025
        L_0x0138:
            r13 = r9
            if (r13 == 0) goto L_0x0142
            r13 = r4
            r14 = 32
            java.lang.StringBuffer r13 = r13.append(r14)
        L_0x0142:
            r13 = r4
            r14 = r8
            r15 = 61696(0xf100, float:8.6455E-41)
            if (r14 == r15) goto L_0x0154
            r14 = 1
        L_0x014a:
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = 1
            r9 = r13
            r13 = r3
            r1 = r13
            goto L_0x0025
        L_0x0154:
            r14 = 0
            goto L_0x014a
        L_0x0156:
            r13 = r9
            if (r13 == 0) goto L_0x0160
            r13 = r4
            r14 = 32
            java.lang.StringBuffer r13 = r13.append(r14)
        L_0x0160:
            r13 = r4
            r14 = r1
            r15 = r3
            int r14 = r14.getIntN(r15)
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = 1
            r9 = r13
            r13 = r3
            r14 = 2
            int r13 = r13 + 2
            r1 = r13
            goto L_0x0025
        L_0x0174:
            r13 = r9
            if (r13 == 0) goto L_0x017e
            r13 = r4
            r14 = 32
            java.lang.StringBuffer r13 = r13.append(r14)
        L_0x017e:
            r13 = r4
            r14 = r1
            r15 = r3
            long r14 = r14.getLongN(r15)
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = 1
            r9 = r13
            r13 = r3
            r14 = 4
            int r13 = r13 + 4
            r1 = r13
            goto L_0x0025
        L_0x0192:
            r13 = r9
            if (r13 == 0) goto L_0x019c
            r13 = r4
            r14 = 32
            java.lang.StringBuffer r13 = r13.append(r14)
        L_0x019c:
            r13 = r4
            r14 = r1
            r15 = r3
            int r14 = r14.getIntN(r15)
            float r14 = java.lang.Float.intBitsToFloat(r14)
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = 1
            r9 = r13
            r13 = r3
            r14 = 2
            int r13 = r13 + 2
            r1 = r13
            goto L_0x0025
        L_0x01b4:
            r13 = r9
            if (r13 == 0) goto L_0x01be
            r13 = r4
            r14 = 32
            java.lang.StringBuffer r13 = r13.append(r14)
        L_0x01be:
            r13 = r4
            r14 = r1
            r15 = r3
            long r14 = r14.getLongN(r15)
            double r14 = java.lang.Double.longBitsToDouble(r14)
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = 1
            r9 = r13
            r13 = r3
            r14 = 4
            int r13 = r13 + 4
            r1 = r13
            goto L_0x0025
        L_0x01d6:
            r13 = 0
            r9 = r13
            r13 = r4
            r14 = r1
            char[] r14 = r14.data
            r15 = r3
            char r14 = r14[r15]
            java.lang.StringBuffer r13 = r13.append(r14)
            r13 = r3
            r14 = 1
            int r13 = r13 + 1
            r1 = r13
            goto L_0x0025
        L_0x01ea:
            r13 = r3
            r14 = 4
            int r13 = r13 + 4
            r6 = r13
            r13 = r1
            r14 = r3
            r15 = 1
            int r14 = r14 + -1
            int r13 = r13.nextDataIndex(r14)
            r3 = r13
            goto L_0x0063
        L_0x01fb:
            r13 = 0
            r9 = r13
            r13 = r3
            r14 = 2
            int r13 = r13 + 2
            r6 = r13
            r13 = r1
            r14 = r3
            int r13 = r13.getIntN(r14)
            r7 = r13
            r13 = r7
            r14 = r7
            if (r14 >= 0) goto L_0x021a
            r14 = r1
            char[] r14 = r14.data
            int r14 = r14.length
        L_0x0211:
            int r13 = r13 + r14
            r7 = r13
            r13 = r7
            r14 = 7
            int r13 = r13 + 7
            r3 = r13
            goto L_0x0063
        L_0x021a:
            r14 = r3
            r15 = 1
            int r14 = r14 + -1
            goto L_0x0211
        L_0x021f:
            r13 = 0
            r9 = r13
            goto L_0x0063
        L_0x0223:
            r13 = -1
            r1 = r13
            goto L_0x0025
        L_0x0227:
            r13 = r2
            if (r13 != 0) goto L_0x022f
            r13 = r3
            r14 = 4
            int r13 = r13 + 4
            r6 = r13
        L_0x022f:
            r13 = r1
            r14 = r3
            r15 = 2
            int r14 = r14 + 2
            int r13 = r13.getIntN(r14)
            r10 = r13
            r13 = r10
            r14 = r10
            if (r14 >= 0) goto L_0x0248
            r14 = r1
            char[] r14 = r14.data
            int r14 = r14.length
            r15 = 1
            int r14 = r14 + 1
        L_0x0244:
            int r13 = r13 + r14
            r3 = r13
            goto L_0x0063
        L_0x0248:
            r14 = r3
            goto L_0x0244
        L_0x024a:
            r13 = r1
            java.lang.Object[] r13 = r13.objects
            r14 = r1
            r15 = r3
            int r14 = r14.getIntN(r15)
            r13 = r13[r14]
            gnu.lists.AbstractSequence r13 = (gnu.lists.AbstractSequence) r13
            r11 = r13
            r13 = r1
            r14 = r3
            r15 = 2
            int r14 = r14 + 2
            int r13 = r13.getIntN(r14)
            r12 = r13
            r13 = r11
            gnu.lists.TreeList r13 = (gnu.lists.TreeList) r13
            r14 = r2
            r15 = r12
            r16 = 1
            int r15 = r15 >> 1
            r16 = r4
            int r13 = r13.stringValue((boolean) r14, (int) r15, (java.lang.StringBuffer) r16)
            int r3 = r3 + 4
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.stringValue(boolean, int, java.lang.StringBuffer):int");
    }

    public int createRelativePos(int i, int i2, boolean z) {
        Throwable th;
        int istart = i;
        int offset = i2;
        boolean isAfter = z;
        if (isAfter) {
            if (offset == 0) {
                if ((istart & 1) != 0) {
                    return istart;
                }
                if (istart == 0) {
                    return 1;
                }
            }
            offset--;
        }
        if (offset < 0) {
            throw unsupported("backwards createRelativePos");
        }
        int pos = posToDataIndex(istart);
        do {
            offset--;
            if (offset >= 0) {
                pos = nextDataIndex(pos);
            } else {
                if (pos >= this.gapEnd) {
                    pos -= this.gapEnd - this.gapStart;
                }
                return isAfter ? ((pos + 1) << 1) | 1 : pos << 1;
            }
        } while (pos >= 0);
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public final int nextNodeIndex(int i, int i2) {
        int pos = i;
        int limit = i2;
        if ((limit | Integer.MIN_VALUE) == -1) {
            limit = this.data.length;
        }
        while (true) {
            if (pos == this.gapStart) {
                pos = this.gapEnd;
            }
            if (pos >= limit) {
                return pos;
            }
            char datum = this.data[pos];
            if (datum <= 40959 || ((datum >= OBJECT_REF_SHORT && datum <= 61439) || ((datum >= 45056 && datum <= 57343) || (datum & 65280) == BYTE_PREFIX))) {
                pos++;
            } else if (datum < BEGIN_ELEMENT_SHORT || datum > 45055) {
                switch (datum) {
                    case BEGIN_ELEMENT_LONG /*61704*/:
                    case BEGIN_ATTRIBUTE_LONG /*61705*/:
                    case BEGIN_DOCUMENT /*61712*/:
                    case PROCESSING_INSTRUCTION /*61716*/:
                    case COMMENT /*61719*/:
                        return pos;
                    case END_ATTRIBUTE /*61706*/:
                    case END_ELEMENT_SHORT /*61707*/:
                    case END_ELEMENT_LONG /*61708*/:
                    case END_DOCUMENT /*61713*/:
                    case END_ENTITY /*61715*/:
                        return pos;
                    case BEGIN_ENTITY /*61714*/:
                        pos += 5;
                        break;
                    case JOINER /*61718*/:
                        pos++;
                        break;
                    case DOCUMENT_URI /*61720*/:
                        pos += 3;
                        break;
                    default:
                        pos = nextDataIndex(pos);
                        break;
                }
            } else {
                return pos;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v22, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v33, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v37, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v39, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v43, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v47, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v51, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v54, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v56, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v60, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v40, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v44, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v45, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v48, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v68, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v74, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v76, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v81, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v83, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v85, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v88, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v90, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v93, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v22, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v95, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v58, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v98, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v61, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v99, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v101, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v108, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v24, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v112, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v116, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v119, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v77, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: char} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int nextMatching(int r23, gnu.lists.ItemPredicate r24, int r25, boolean r26) {
        /*
            r22 = this;
            r2 = r22
            r3 = r23
            r4 = r24
            r5 = r25
            r6 = r26
            r17 = r2
            r18 = r3
            int r17 = r17.posToDataIndex(r18)
            r7 = r17
            r17 = r2
            r18 = r5
            int r17 = r17.posToDataIndex(r18)
            r8 = r17
            r17 = r7
            r9 = r17
            r17 = r4
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.NodePredicate
            r17 = r0
            if (r17 == 0) goto L_0x0038
            r17 = r2
            r18 = r9
            r19 = r8
            int r17 = r17.nextNodeIndex(r18, r19)
            r9 = r17
        L_0x0038:
            r17 = 0
            r10 = r17
            r17 = r4
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.ElementPredicate
            r17 = r0
            if (r17 == 0) goto L_0x0085
            r17 = 1
            r11 = r17
            r17 = 1
            r13 = r17
            r17 = 0
            r12 = r17
        L_0x0052:
            r17 = r9
            r18 = r2
            r0 = r18
            int r0 = r0.gapStart
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x006c
            r17 = r2
            r0 = r17
            int r0 = r0.gapEnd
            r17 = r0
            r9 = r17
        L_0x006c:
            r17 = r9
            r18 = r8
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x00a9
            r17 = r8
            r18 = -1
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x00a9
            r17 = 0
            r2 = r17
        L_0x0084:
            return r2
        L_0x0085:
            r17 = r4
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.AttributePredicate
            r17 = r0
            if (r17 == 0) goto L_0x009c
            r17 = 1
            r11 = r17
            r17 = 0
            r13 = r17
            r17 = 0
            r12 = r17
            goto L_0x0052
        L_0x009c:
            r17 = 1
            r11 = r17
            r17 = 1
            r13 = r17
            r17 = 1
            r12 = r17
            goto L_0x0052
        L_0x00a9:
            r17 = r2
            r0 = r17
            char[] r0 = r0.data
            r17 = r0
            r18 = r9
            char r17 = r17[r18]
            r16 = r17
            r17 = r16
            r18 = 40959(0x9fff, float:5.7396E-41)
            r0 = r17
            r1 = r18
            if (r0 <= r1) goto L_0x00ee
            r17 = r16
            r18 = 57344(0xe000, float:8.0356E-41)
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x00d8
            r17 = r16
            r18 = 61439(0xefff, float:8.6094E-41)
            r0 = r17
            r1 = r18
            if (r0 <= r1) goto L_0x00ee
        L_0x00d8:
            r17 = r16
            r18 = 45056(0xb000, float:6.3137E-41)
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x0142
            r17 = r16
            r18 = 57343(0xdfff, float:8.0355E-41)
            r0 = r17
            r1 = r18
            if (r0 > r1) goto L_0x0142
        L_0x00ee:
            r17 = r12
            if (r17 == 0) goto L_0x0134
            r17 = r4
            r18 = r2
            r19 = r9
            r20 = 1
            int r19 = r19 << 1
            boolean r17 = r17.isInstancePos(r18, r19)
            if (r17 == 0) goto L_0x0134
            r17 = r9
            r18 = r2
            r0 = r18
            int r0 = r0.gapEnd
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x012a
            r17 = r9
            r18 = r2
            r0 = r18
            int r0 = r0.gapEnd
            r18 = r0
            r19 = r2
            r0 = r19
            int r0 = r0.gapStart
            r19 = r0
            int r18 = r18 - r19
            int r17 = r17 - r18
            r9 = r17
        L_0x012a:
            r17 = r9
            r18 = 1
            int r17 = r17 << 1
            r2 = r17
            goto L_0x0084
        L_0x0134:
            r17 = r9
            r18 = 1
            int r17 = r17 + 1
            r14 = r17
        L_0x013c:
            r17 = r14
            r9 = r17
            goto L_0x0052
        L_0x0142:
            r17 = r16
            switch(r17) {
                case 61696: goto L_0x027a;
                case 61697: goto L_0x027a;
                case 61698: goto L_0x01da;
                case 61699: goto L_0x0292;
                case 61700: goto L_0x01da;
                case 61701: goto L_0x0292;
                case 61702: goto L_0x01e7;
                case 61703: goto L_0x0147;
                case 61704: goto L_0x02f4;
                case 61705: goto L_0x023b;
                case 61706: goto L_0x0227;
                case 61707: goto L_0x01f1;
                case 61708: goto L_0x0213;
                case 61709: goto L_0x01da;
                case 61710: goto L_0x01da;
                case 61711: goto L_0x0205;
                case 61712: goto L_0x01c3;
                case 61713: goto L_0x0227;
                case 61714: goto L_0x01d0;
                case 61715: goto L_0x0231;
                case 61716: goto L_0x02a0;
                case 61717: goto L_0x02d8;
                case 61718: goto L_0x0288;
                case 61719: goto L_0x02bc;
                case 61720: goto L_0x01b9;
                default: goto L_0x0147;
            }
        L_0x0147:
            r17 = r16
            r18 = 40960(0xa000, float:5.7397E-41)
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x034f
            r17 = r16
            r18 = 45055(0xafff, float:6.3136E-41)
            r0 = r17
            r1 = r18
            if (r0 > r1) goto L_0x034f
            r17 = r6
            if (r17 == 0) goto L_0x0333
            r17 = r9
            r18 = 3
            int r17 = r17 + 3
            r14 = r17
        L_0x0169:
            r17 = r13
            if (r17 == 0) goto L_0x013c
        L_0x016d:
            r17 = r9
            r18 = r7
            r0 = r17
            r1 = r18
            if (r0 <= r1) goto L_0x013c
            r17 = r4
            r18 = r2
            r19 = r9
            r20 = 1
            int r19 = r19 << 1
            boolean r17 = r17.isInstancePos(r18, r19)
            if (r17 == 0) goto L_0x013c
            r17 = r9
            r18 = r2
            r0 = r18
            int r0 = r0.gapEnd
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x01af
            r17 = r9
            r18 = r2
            r0 = r18
            int r0 = r0.gapEnd
            r18 = r0
            r19 = r2
            r0 = r19
            int r0 = r0.gapStart
            r19 = r0
            int r18 = r18 - r19
            int r17 = r17 - r18
            r9 = r17
        L_0x01af:
            r17 = r9
            r18 = 1
            int r17 = r17 << 1
            r2 = r17
            goto L_0x0084
        L_0x01b9:
            r17 = r9
            r18 = 3
            int r17 = r17 + 3
            r14 = r17
            goto L_0x013c
        L_0x01c3:
            r17 = r9
            r18 = 5
            int r17 = r17 + 5
            r14 = r17
            r17 = r11
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x01d0:
            r17 = r9
            r18 = 5
            int r17 = r17 + 5
            r14 = r17
            goto L_0x013c
        L_0x01da:
            r17 = r9
            r18 = 3
            int r17 = r17 + 3
            r14 = r17
            r17 = r12
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x01e7:
            r17 = r9
            r18 = 2
            int r17 = r17 + 2
            r14 = r17
            goto L_0x013c
        L_0x01f1:
            r17 = r6
            if (r17 != 0) goto L_0x01fb
            r17 = 0
            r2 = r17
            goto L_0x0084
        L_0x01fb:
            r17 = r9
            r18 = 2
            int r17 = r17 + 2
            r14 = r17
            goto L_0x013c
        L_0x0205:
            r17 = r9
            r18 = 5
            int r17 = r17 + 5
            r14 = r17
            r17 = r12
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x0213:
            r17 = r6
            if (r17 != 0) goto L_0x021d
            r17 = 0
            r2 = r17
            goto L_0x0084
        L_0x021d:
            r17 = r9
            r18 = 7
            int r17 = r17 + 7
            r14 = r17
            goto L_0x013c
        L_0x0227:
            r17 = r6
            if (r17 != 0) goto L_0x0231
            r17 = 0
            r2 = r17
            goto L_0x0084
        L_0x0231:
            r17 = r9
            r18 = 1
            int r17 = r17 + 1
            r14 = r17
            goto L_0x013c
        L_0x023b:
            r17 = r11
            if (r17 == 0) goto L_0x0271
            r17 = r2
            r18 = r9
            r19 = 3
            int r18 = r18 + 3
            int r17 = r17.getIntN(r18)
            r15 = r17
            r17 = r15
            r18 = 1
            int r17 = r17 + 1
            r18 = r15
            if (r18 >= 0) goto L_0x026e
            r18 = r2
            r0 = r18
            char[] r0 = r0.data
            r18 = r0
            r0 = r18
            int r0 = r0.length
            r18 = r0
        L_0x0264:
            int r17 = r17 + r18
            r14 = r17
        L_0x0268:
            r17 = r10
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x026e:
            r18 = r9
            goto L_0x0264
        L_0x0271:
            r17 = r9
            r18 = 5
            int r17 = r17 + 5
            r14 = r17
            goto L_0x0268
        L_0x027a:
            r17 = r9
            r18 = 1
            int r17 = r17 + 1
            r14 = r17
            r17 = r12
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x0288:
            r17 = r9
            r18 = 1
            int r17 = r17 + 1
            r14 = r17
            goto L_0x013c
        L_0x0292:
            r17 = r9
            r18 = 5
            int r17 = r17 + 5
            r14 = r17
            r17 = r12
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x02a0:
            r17 = r9
            r18 = 5
            int r17 = r17 + 5
            r18 = r2
            r19 = r9
            r20 = 3
            int r19 = r19 + 3
            int r18 = r18.getIntN(r19)
            int r17 = r17 + r18
            r14 = r17
            r17 = r11
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x02bc:
            r17 = r9
            r18 = 3
            int r17 = r17 + 3
            r18 = r2
            r19 = r9
            r20 = 1
            int r19 = r19 + 1
            int r18 = r18.getIntN(r19)
            int r17 = r17 + r18
            r14 = r17
            r17 = r11
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x02d8:
            r17 = r9
            r18 = 3
            int r17 = r17 + 3
            r18 = r2
            r19 = r9
            r20 = 1
            int r19 = r19 + 1
            int r18 = r18.getIntN(r19)
            int r17 = r17 + r18
            r14 = r17
            r17 = r12
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x02f4:
            r17 = r6
            if (r17 == 0) goto L_0x0306
            r17 = r9
            r18 = 3
            int r17 = r17 + 3
            r14 = r17
        L_0x0300:
            r17 = r13
            if (r17 == 0) goto L_0x013c
            goto L_0x016d
        L_0x0306:
            r17 = r2
            r18 = r9
            r19 = 1
            int r18 = r18 + 1
            int r17 = r17.getIntN(r18)
            r15 = r17
            r17 = r15
            r18 = r15
            if (r18 >= 0) goto L_0x0330
            r18 = r2
            r0 = r18
            char[] r0 = r0.data
            r18 = r0
            r0 = r18
            int r0 = r0.length
            r18 = r0
        L_0x0327:
            int r17 = r17 + r18
            r18 = 7
            int r17 = r17 + 7
            r14 = r17
            goto L_0x0300
        L_0x0330:
            r18 = r9
            goto L_0x0327
        L_0x0333:
            r17 = r9
            r18 = r2
            r0 = r18
            char[] r0 = r0.data
            r18 = r0
            r19 = r9
            r20 = 1
            int r19 = r19 + 1
            char r18 = r18[r19]
            int r17 = r17 + r18
            r18 = 2
            int r17 = r17 + 2
            r14 = r17
            goto L_0x0169
        L_0x034f:
            java.lang.Error r17 = new java.lang.Error
            r21 = r17
            r17 = r21
            r18 = r21
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r21 = r19
            r19 = r21
            r20 = r21
            r20.<init>()
            java.lang.String r20 = "unknown code:"
            java.lang.StringBuilder r19 = r19.append(r20)
            r20 = r16
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r18.<init>(r19)
            throw r17
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.nextMatching(int, gnu.lists.ItemPredicate, int, boolean):int");
    }

    public int nextPos(int position) {
        int index = posToDataIndex(position);
        if (index == this.data.length) {
            return 0;
        }
        if (index >= this.gapEnd) {
            index -= this.gapEnd - this.gapStart;
        }
        return (index << 1) + 3;
    }

    public final int nextDataIndex(int i) {
        Throwable th;
        StringBuilder sb;
        int pos = i;
        if (pos == this.gapStart) {
            pos = this.gapEnd;
        }
        if (pos == this.data.length) {
            return -1;
        }
        int i2 = pos;
        int pos2 = pos + 1;
        char datum = this.data[i2];
        if (datum <= 40959 || ((datum >= OBJECT_REF_SHORT && datum <= 61439) || (datum >= 45056 && datum <= 57343))) {
            return pos2;
        }
        if (datum >= BEGIN_ELEMENT_SHORT && datum <= 45055) {
            return this.data[pos2] + pos2 + 1;
        }
        switch (datum) {
            case 61696:
            case 61697:
            case JOINER /*61718*/:
                return pos2;
            case INT_FOLLOWS /*61698*/:
            case FLOAT_FOLLOWS /*61700*/:
            case 61709:
            case 61710:
                return pos2 + 2;
            case LONG_FOLLOWS /*61699*/:
            case DOUBLE_FOLLOWS /*61701*/:
                return pos2 + 4;
            case CHAR_FOLLOWS /*61702*/:
                return pos2 + 1;
            case BEGIN_ELEMENT_LONG /*61704*/:
                int j = getIntN(pos2);
                return j + (j < 0 ? this.data.length : pos2 - 1) + 7;
            case BEGIN_ATTRIBUTE_LONG /*61705*/:
                int j2 = getIntN(pos2 + 2);
                return j2 + (j2 < 0 ? this.data.length : pos2 - 1) + 1;
            case END_ATTRIBUTE /*61706*/:
            case END_ELEMENT_SHORT /*61707*/:
            case END_ELEMENT_LONG /*61708*/:
            case END_DOCUMENT /*61713*/:
            case END_ENTITY /*61715*/:
                return -1;
            case 61711:
                return pos2 + 4;
            case BEGIN_DOCUMENT /*61712*/:
                int j3 = getIntN(pos2);
                return j3 + (j3 < 0 ? this.data.length : pos2 - 1) + 1;
            case BEGIN_ENTITY /*61714*/:
                int i3 = pos2 + 4;
                while (true) {
                    int j4 = i3;
                    if (j4 == this.gapStart) {
                        j4 = this.gapEnd;
                    }
                    if (j4 == this.data.length) {
                        return -1;
                    }
                    if (this.data[j4] == END_ENTITY) {
                        return j4 + 1;
                    }
                    i3 = nextDataIndex(j4);
                }
            case PROCESSING_INSTRUCTION /*61716*/:
                pos2 += 2;
                break;
            case CDATA_SECTION /*61717*/:
            case COMMENT /*61719*/:
                break;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new Error(sb.append("unknown code:").append(Integer.toHexString(datum)).toString());
                throw th2;
        }
        return pos2 + 2 + getIntN(pos2);
    }

    public Object documentUriOfPos(int pos) {
        int index = posToDataIndex(pos);
        if (index == this.data.length) {
            return null;
        }
        if (this.data[index] == BEGIN_DOCUMENT) {
            int next = index + 5;
            if (next == this.gapStart) {
                next = this.gapEnd;
            }
            if (next < this.data.length && this.data[next] == DOCUMENT_URI) {
                return this.objects[getIntN(next + 1)];
            }
        }
        return null;
    }

    public int compare(int ipos1, int ipos2) {
        int i1 = posToDataIndex(ipos1);
        int i2 = posToDataIndex(ipos2);
        return i1 < i2 ? -1 : i1 > i2 ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public int getIndexDifference(int ipos1, int ipos0) {
        int i0 = posToDataIndex(ipos0);
        int i1 = posToDataIndex(ipos1);
        boolean negate = false;
        if (i0 > i1) {
            negate = true;
            int i = i1;
            i1 = i0;
            i0 = i;
        }
        int i2 = 0;
        while (i0 < i1) {
            i0 = nextDataIndex(i0);
            i2++;
        }
        return negate ? -i2 : i2;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public void consume(Consumer out) {
        int consumeIRange = consumeIRange(0, this.data.length, out);
    }

    public void statistics() {
        PrintWriter printWriter;
        new PrintWriter(System.out);
        PrintWriter out = printWriter;
        statistics(out);
        out.flush();
    }

    public void statistics(PrintWriter printWriter) {
        PrintWriter out = printWriter;
        out.print("data array length: ");
        out.println(this.data.length);
        out.print("data array gap: ");
        out.println(this.gapEnd - this.gapStart);
        out.print("object array length: ");
        out.println(this.objects.length);
    }

    public void dump() {
        PrintWriter printWriter;
        new PrintWriter(System.out);
        PrintWriter out = printWriter;
        dump(out);
        out.flush();
    }

    public void dump(PrintWriter printWriter) {
        StringBuilder sb;
        PrintWriter out = printWriter;
        new StringBuilder();
        out.println(sb.append(getClass().getName()).append(" @").append(Integer.toHexString(System.identityHashCode(this))).append(" gapStart:").append(this.gapStart).append(" gapEnd:").append(this.gapEnd).append(" length:").append(this.data.length).toString());
        dump(out, 0, this.data.length);
    }

    public void dump(PrintWriter printWriter, int start, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        StringBuilder sb11;
        StringBuilder sb12;
        StringBuilder sb13;
        StringBuilder sb14;
        StringBuilder sb15;
        StringBuilder sb16;
        PrintWriter out = printWriter;
        int limit = i;
        int toskip = 0;
        int i2 = start;
        while (i2 < limit) {
            if (i2 < this.gapStart || i2 >= this.gapEnd) {
                char c = this.data[i2];
                new StringBuilder();
                out.print(sb.append("").append(i2).append(": 0x").append(Integer.toHexString(c)).append('=').append((short) c).toString());
                toskip--;
                if (toskip < 0) {
                    if (c <= 40959) {
                        if (c >= ' ' && c < 127) {
                            new StringBuilder();
                            out.print(sb16.append("='").append((char) c).append("'").toString());
                        } else if (c == 10) {
                            out.print("='\\n'");
                        } else {
                            new StringBuilder();
                            out.print(sb15.append("='\\u").append(Integer.toHexString(c)).append("'").toString());
                        }
                    } else if (c >= OBJECT_REF_SHORT && c <= 61439) {
                        int ch = c - OBJECT_REF_SHORT;
                        Object obj = this.objects[ch];
                        new StringBuilder();
                        out.print(sb14.append("=Object#").append(ch).append('=').append(obj).append(':').append(obj.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(obj))).toString());
                    } else if (c >= BEGIN_ELEMENT_SHORT && c <= 45055) {
                        int ch2 = c - BEGIN_ELEMENT_SHORT;
                        int j = this.data[i2 + 1] + i2;
                        new StringBuilder();
                        out.print(sb13.append("=BEGIN_ELEMENT_SHORT end:").append(j).append(" index#").append(ch2).append("=<").append(this.objects[ch2]).append('>').toString());
                        toskip = 2;
                    } else if (c < 45056 || c > 57343) {
                        switch (c) {
                            case 61696:
                                out.print("= false");
                                break;
                            case 61697:
                                out.print("= true");
                                break;
                            case INT_FOLLOWS /*61698*/:
                                int j2 = getIntN(i2 + 1);
                                new StringBuilder();
                                out.print(sb11.append("=INT_FOLLOWS value:").append(j2).toString());
                                toskip = 2;
                                break;
                            case LONG_FOLLOWS /*61699*/:
                                long l = getLongN(i2 + 1);
                                new StringBuilder();
                                out.print(sb10.append("=LONG_FOLLOWS value:").append(l).toString());
                                toskip = 4;
                                break;
                            case FLOAT_FOLLOWS /*61700*/:
                                int j3 = getIntN(i2 + 1);
                                new StringBuilder();
                                out.write(sb9.append("=FLOAT_FOLLOWS value:").append(Float.intBitsToFloat(j3)).toString());
                                toskip = 2;
                                break;
                            case DOUBLE_FOLLOWS /*61701*/:
                                long l2 = getLongN(i2 + 1);
                                new StringBuilder();
                                out.print(sb8.append("=DOUBLE_FOLLOWS value:").append(Double.longBitsToDouble(l2)).toString());
                                toskip = 4;
                                break;
                            case CHAR_FOLLOWS /*61702*/:
                                out.print("=CHAR_FOLLOWS");
                                toskip = 1;
                                break;
                            case BEGIN_ELEMENT_LONG /*61704*/:
                                int j4 = getIntN(i2 + 1);
                                int j5 = j4 + (j4 < 0 ? this.data.length : i2);
                                out.print("=BEGIN_ELEMENT_LONG end:");
                                out.print(j5);
                                int j6 = getIntN(j5 + 1);
                                out.print(" -> #");
                                out.print(j6);
                                if (j6 < 0 || j6 + 1 >= this.objects.length) {
                                    out.print("=<out-of-bounds>");
                                } else {
                                    new StringBuilder();
                                    out.print(sb7.append("=<").append(this.objects[j6]).append('>').toString());
                                }
                                toskip = 2;
                                break;
                            case BEGIN_ATTRIBUTE_LONG /*61705*/:
                                int j7 = getIntN(i2 + 1);
                                new StringBuilder();
                                out.print(sb2.append("=BEGIN_ATTRIBUTE name:").append(j7).append("=").append(this.objects[j7]).toString());
                                int j8 = getIntN(i2 + 3);
                                int i3 = j8;
                                int length = j8 < 0 ? this.data.length : i2;
                                new StringBuilder();
                                out.print(sb3.append(" end:").append(i3 + length).toString());
                                toskip = 4;
                                break;
                            case END_ATTRIBUTE /*61706*/:
                                out.print("=END_ATTRIBUTE");
                                break;
                            case END_ELEMENT_SHORT /*61707*/:
                                out.print("=END_ELEMENT_SHORT begin:");
                                int j9 = i2 - this.data[i2 + 1];
                                out.print(j9);
                                int j10 = this.data[j9] - BEGIN_ELEMENT_SHORT;
                                out.print(" -> #");
                                out.print(j10);
                                out.print("=<");
                                out.print(this.objects[j10]);
                                out.print('>');
                                toskip = 1;
                                break;
                            case END_ELEMENT_LONG /*61708*/:
                                int j11 = getIntN(i2 + 1);
                                new StringBuilder();
                                out.print(sb4.append("=END_ELEMENT_LONG name:").append(j11).append("=<").append(this.objects[j11]).append('>').toString());
                                int j12 = getIntN(i2 + 3);
                                int j13 = j12 < 0 ? i2 + j12 : j12;
                                new StringBuilder();
                                out.print(sb5.append(" begin:").append(j13).toString());
                                int j14 = getIntN(i2 + 5);
                                int j15 = j14 < 0 ? i2 + j14 : j14;
                                new StringBuilder();
                                out.print(sb6.append(" parent:").append(j15).toString());
                                toskip = 6;
                                break;
                            case 61709:
                            case 61710:
                                toskip = 2;
                                break;
                            case 61711:
                                out.print("=POSITION_PAIR_FOLLOWS seq:");
                                int j16 = getIntN(i2 + 1);
                                out.print(j16);
                                out.print('=');
                                Object seq = this.objects[j16];
                                out.print(seq == null ? null : seq.getClass().getName());
                                out.print('@');
                                if (seq == null) {
                                    out.print("null");
                                } else {
                                    out.print(Integer.toHexString(System.identityHashCode(seq)));
                                }
                                out.print(" ipos:");
                                out.print(getIntN(i2 + 3));
                                toskip = 4;
                                break;
                            case BEGIN_DOCUMENT /*61712*/:
                                int j17 = getIntN(i2 + 1);
                                int i4 = j17;
                                int length2 = j17 < 0 ? this.data.length : i2;
                                out.print("=BEGIN_DOCUMENT end:");
                                out.print(i4 + length2);
                                out.print(" parent:");
                                out.print(getIntN(i2 + 3));
                                toskip = 4;
                                break;
                            case END_DOCUMENT /*61713*/:
                                out.print("=END_DOCUMENT");
                                break;
                            case BEGIN_ENTITY /*61714*/:
                                int j18 = getIntN(i2 + 1);
                                out.print("=BEGIN_ENTITY base:");
                                out.print(j18);
                                out.print(" parent:");
                                out.print(getIntN(i2 + 3));
                                toskip = 4;
                                break;
                            case END_ENTITY /*61715*/:
                                out.print("=END_ENTITY");
                                break;
                            case PROCESSING_INSTRUCTION /*61716*/:
                                out.print("=PROCESSING_INSTRUCTION: ");
                                out.print(this.objects[getIntN(i2 + 1)]);
                                out.print(" '");
                                int j19 = getIntN(i2 + 3);
                                out.write(this.data, i2 + 5, j19);
                                out.print('\'');
                                toskip = 4 + j19;
                                break;
                            case CDATA_SECTION /*61717*/:
                                out.print("=CDATA: '");
                                int j20 = getIntN(i2 + 1);
                                out.write(this.data, i2 + 3, j20);
                                out.print('\'');
                                toskip = 2 + j20;
                                break;
                            case JOINER /*61718*/:
                                out.print("= joiner");
                                break;
                            case COMMENT /*61719*/:
                                out.print("=COMMENT: '");
                                int j21 = getIntN(i2 + 1);
                                out.write(this.data, i2 + 3, j21);
                                out.print('\'');
                                toskip = 2 + j21;
                                break;
                            case DOCUMENT_URI /*61720*/:
                                out.print("=DOCUMENT_URI: ");
                                out.print(this.objects[getIntN(i2 + 1)]);
                                toskip = 2;
                                break;
                        }
                    } else {
                        new StringBuilder();
                        out.print(sb12.append("= INT_SHORT:").append(c - INT_SHORT_ZERO).toString());
                    }
                }
                out.println();
                if (1 != 0 && toskip > 0) {
                    i2 += toskip;
                    toskip = 0;
                }
            }
            i2++;
        }
    }
}
