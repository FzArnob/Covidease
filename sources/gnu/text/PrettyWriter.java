package gnu.text;

import gnu.lists.LList;
import gnu.mapping.ThreadLocation;
import java.io.IOException;
import java.io.Writer;

public class PrettyWriter extends Writer {
    private static final int BLOCK_PER_LINE_PREFIX_END = -3;
    private static final int BLOCK_PREFIX_LENGTH = -4;
    private static final int BLOCK_SECTION_COLUMN = -2;
    private static final int BLOCK_SECTION_START_LINE = -6;
    private static final int BLOCK_START_COLUMN = -1;
    private static final int BLOCK_SUFFIX_LENGTH = -5;
    private static final int LOGICAL_BLOCK_LENGTH = 6;
    public static final int NEWLINE_FILL = 70;
    public static final int NEWLINE_LINEAR = 78;
    public static final int NEWLINE_LITERAL = 76;
    public static final int NEWLINE_MANDATORY = 82;
    public static final int NEWLINE_MISER = 77;
    public static final int NEWLINE_SPACE = 83;
    static final int QITEM_BASE_SIZE = 2;
    static final int QITEM_BLOCK_END_SIZE = 2;
    static final int QITEM_BLOCK_END_TYPE = 5;
    static final int QITEM_BLOCK_START_BLOCK_END = 4;
    static final int QITEM_BLOCK_START_PREFIX = 5;
    static final int QITEM_BLOCK_START_SIZE = 7;
    static final int QITEM_BLOCK_START_SUFFIX = 6;
    static final int QITEM_BLOCK_START_TYPE = 4;
    static final int QITEM_INDENTATION_AMOUNT = 3;
    static final char QITEM_INDENTATION_BLOCK = 'B';
    static final char QITEM_INDENTATION_CURRENT = 'C';
    static final int QITEM_INDENTATION_KIND = 2;
    static final int QITEM_INDENTATION_SIZE = 4;
    static final int QITEM_INDENTATION_TYPE = 3;
    static final int QITEM_NEWLINE_KIND = 4;
    static final int QITEM_NEWLINE_SIZE = 5;
    static final int QITEM_NEWLINE_TYPE = 2;
    static final int QITEM_NOP_TYPE = 0;
    static final int QITEM_POSN = 1;
    static final int QITEM_SECTION_START_DEPTH = 2;
    static final int QITEM_SECTION_START_SECTION_END = 3;
    static final int QITEM_SECTION_START_SIZE = 4;
    static final int QITEM_TAB_COLINC = 4;
    static final int QITEM_TAB_COLNUM = 3;
    static final int QITEM_TAB_FLAGS = 2;
    static final int QITEM_TAB_IS_RELATIVE = 2;
    static final int QITEM_TAB_IS_SECTION = 1;
    static final int QITEM_TAB_SIZE = 5;
    static final int QITEM_TAB_TYPE = 6;
    static final int QITEM_TYPE_AND_SIZE = 0;
    static final int QUEUE_INIT_ALLOC_SIZE = 300;
    public static ThreadLocation indentLoc;
    public static int initialBufferSize = 126;
    public static ThreadLocation lineLengthLoc;
    public static ThreadLocation miserWidthLoc;
    int blockDepth = 6;
    int[] blocks = new int[60];
    public char[] buffer = new char[initialBufferSize];
    public int bufferFillPointer;
    int bufferOffset;
    int bufferStartColumn;
    int currentBlock = -1;
    int lineLength = 80;
    int lineNumber;
    int miserWidth = 40;
    protected Writer out;
    public int pendingBlocksCount;
    char[] prefix = new char[initialBufferSize];
    int prettyPrintingMode;
    int[] queueInts = new int[300];
    int queueSize;
    String[] queueStrings = new String[300];
    int queueTail;
    char[] suffix = new char[initialBufferSize];
    boolean wordEndSeen;

    public PrettyWriter(Writer out2) {
        this.out = out2;
        this.prettyPrintingMode = 1;
    }

    public PrettyWriter(Writer out2, int i) {
        int lineLength2 = i;
        this.out = out2;
        this.lineLength = lineLength2;
        this.prettyPrintingMode = lineLength2 > 1 ? 1 : 0;
    }

    public PrettyWriter(Writer out2, boolean prettyPrintingMode2) {
        this.out = out2;
        this.prettyPrintingMode = prettyPrintingMode2 ? 1 : 0;
    }

    static {
        ThreadLocation threadLocation;
        ThreadLocation threadLocation2;
        ThreadLocation threadLocation3;
        new ThreadLocation("line-length");
        lineLengthLoc = threadLocation;
        new ThreadLocation("miser-width");
        miserWidthLoc = threadLocation2;
        new ThreadLocation("indent");
        indentLoc = threadLocation3;
    }

    public void setPrettyPrintingMode(int mode) {
        int i = mode;
        this.prettyPrintingMode = i;
    }

    public int getPrettyPrintingMode() {
        return this.prettyPrintingMode;
    }

    public boolean isPrettyPrinting() {
        return this.prettyPrintingMode > 0;
    }

    public void setPrettyPrinting(boolean mode) {
        this.prettyPrintingMode = mode ? 0 : 1;
    }

    private int indexPosn(int index) {
        return index + this.bufferOffset;
    }

    private int posnIndex(int posn) {
        return posn - this.bufferOffset;
    }

    private int posnColumn(int posn) {
        return indexColumn(posnIndex(posn));
    }

    private int getQueueType(int index) {
        return this.queueInts[index] & 255;
    }

    private int getQueueSize(int index) {
        return this.queueInts[index] >> 16;
    }

    private int getSectionColumn() {
        return this.blocks[this.blockDepth - 2];
    }

    private int getStartColumn() {
        return this.blocks[this.blockDepth - 1];
    }

    private int getPerLinePrefixEnd() {
        return this.blocks[this.blockDepth - 3];
    }

    private int getPrefixLength() {
        return this.blocks[this.blockDepth - 4];
    }

    private int getSuffixLength() {
        return this.blocks[this.blockDepth - 5];
    }

    private int getSectionStartLine() {
        return this.blocks[this.blockDepth - 6];
    }

    public void writeWordEnd() {
        this.wordEndSeen = true;
    }

    public void writeWordStart() {
        if (this.wordEndSeen) {
            write(32);
        }
        this.wordEndSeen = false;
    }

    public void clearWordEnd() {
        this.wordEndSeen = false;
    }

    public void write(int i) {
        int ch = i;
        this.wordEndSeen = false;
        if (ch != 10 || this.prettyPrintingMode <= 0) {
            int ensureSpaceInBuffer = ensureSpaceInBuffer(1);
            int fillPointer = this.bufferFillPointer;
            this.buffer[fillPointer] = (char) ch;
            this.bufferFillPointer = 1 + fillPointer;
            if (ch == 32 && this.prettyPrintingMode > 1 && this.currentBlock < 0) {
                enqueueNewline(83);
                return;
            }
            return;
        }
        enqueueNewline(76);
    }

    public void write(String str) {
        String str2 = str;
        write(str2, 0, str2.length());
    }

    public void write(String str, int i, int i2) {
        String str2 = str;
        int start = i;
        int count = i2;
        this.wordEndSeen = false;
        while (count > 0) {
            int cnt = count;
            int available = ensureSpaceInBuffer(count);
            if (cnt > available) {
                cnt = available;
            }
            int fillPointer = this.bufferFillPointer;
            count -= cnt;
            while (true) {
                cnt--;
                if (cnt < 0) {
                    break;
                }
                int i3 = start;
                start++;
                char ch = str2.charAt(i3);
                if (ch != 10 || this.prettyPrintingMode <= 0) {
                    int i4 = fillPointer;
                    fillPointer++;
                    this.buffer[i4] = ch;
                    if (ch == ' ' && this.prettyPrintingMode > 1 && this.currentBlock < 0) {
                        this.bufferFillPointer = fillPointer;
                        enqueueNewline(83);
                        fillPointer = this.bufferFillPointer;
                    }
                } else {
                    this.bufferFillPointer = fillPointer;
                    enqueueNewline(76);
                    fillPointer = this.bufferFillPointer;
                }
            }
            this.bufferFillPointer = fillPointer;
        }
    }

    public void write(char[] cArr) {
        char[] str = cArr;
        write(str, 0, str.length);
    }

    public void write(char[] cArr, int i, int i2) {
        char[] str = cArr;
        int start = i;
        int count = i2;
        this.wordEndSeen = false;
        int end = start + count;
        while (count > 0) {
            int i3 = start;
            while (true) {
                if (i3 < end) {
                    if (this.prettyPrintingMode > 0) {
                        char c = str[i3];
                        char c2 = c;
                        if (c == 10 || (c2 == ' ' && this.currentBlock < 0)) {
                            write(str, start, i3 - start);
                            write((int) c2);
                            start = i3 + 1;
                            count = end - start;
                        }
                    }
                    i3++;
                } else {
                    do {
                        int available = ensureSpaceInBuffer(count);
                        int cnt = available < count ? available : count;
                        int fillPointer = this.bufferFillPointer;
                        int newFillPtr = fillPointer + cnt;
                        for (int i4 = fillPointer; i4 < newFillPtr; i4++) {
                            int i5 = start;
                            start++;
                            this.buffer[i4] = str[i5];
                        }
                        this.bufferFillPointer = newFillPtr;
                        count -= cnt;
                    } while (count != 0);
                }
            }
        }
    }

    private void pushLogicalBlock(int i, int i2, int i3, int i4, int i5) {
        int column = i;
        int perLineEnd = i2;
        int prefixLength = i3;
        int suffixLength = i4;
        int sectionStartLine = i5;
        int newLength = this.blockDepth + 6;
        if (newLength >= this.blocks.length) {
            int[] newBlocks = new int[(2 * this.blocks.length)];
            System.arraycopy(this.blocks, 0, newBlocks, 0, this.blockDepth);
            this.blocks = newBlocks;
        }
        this.blockDepth = newLength;
        this.blocks[this.blockDepth - 1] = column;
        this.blocks[this.blockDepth - 2] = column;
        this.blocks[this.blockDepth - 3] = perLineEnd;
        this.blocks[this.blockDepth - 4] = prefixLength;
        this.blocks[this.blockDepth - 5] = suffixLength;
        this.blocks[this.blockDepth - 6] = sectionStartLine;
    }

    /* access modifiers changed from: package-private */
    public void reallyStartLogicalBlock(int i, String str, String str2) {
        int column = i;
        String prefix2 = str;
        String suffix2 = str2;
        int perLineEnd = getPerLinePrefixEnd();
        int prefixLength = getPrefixLength();
        int suffixLength = getSuffixLength();
        pushLogicalBlock(column, perLineEnd, prefixLength, suffixLength, this.lineNumber);
        setIndentation(column);
        if (prefix2 != null) {
            this.blocks[this.blockDepth - 3] = column;
            int plen = prefix2.length();
            prefix2.getChars(0, plen, this.suffix, column - plen);
        }
        if (suffix2 != null) {
            char[] totalSuffix = this.suffix;
            int totalSuffixLen = totalSuffix.length;
            int additional = suffix2.length();
            int newSuffixLen = suffixLength + additional;
            if (newSuffixLen > totalSuffixLen) {
                int newTotalSuffixLen = enoughSpace(totalSuffixLen, additional);
                this.suffix = new char[newTotalSuffixLen];
                System.arraycopy(totalSuffix, totalSuffixLen - suffixLength, this.suffix, newTotalSuffixLen - suffixLength, suffixLength);
                totalSuffixLen = newTotalSuffixLen;
            }
            suffix2.getChars(0, additional, totalSuffix, totalSuffixLen - newSuffixLen);
            this.blocks[this.blockDepth - 5] = newSuffixLen;
        }
    }

    /* access modifiers changed from: package-private */
    public int enqueueTab(int flags, int colnum, int colinc) {
        int addr = enqueue(6, 5);
        this.queueInts[addr + 2] = flags;
        this.queueInts[addr + 3] = colnum;
        this.queueInts[addr + 4] = colinc;
        return addr;
    }

    private static int enoughSpace(int i, int want) {
        int current = i;
        int doubled = 2 * current;
        int enough = current + ((5 * want) >> 2);
        return doubled > enough ? doubled : enough;
    }

    public void setIndentation(int i) {
        int column = i;
        char[] prefix2 = this.prefix;
        int prefixLen = prefix2.length;
        int current = getPrefixLength();
        int minimum = getPerLinePrefixEnd();
        if (minimum > column) {
            column = minimum;
        }
        if (column > prefixLen) {
            prefix2 = new char[enoughSpace(prefixLen, column - prefixLen)];
            System.arraycopy(this.prefix, 0, prefix2, 0, current);
            this.prefix = prefix2;
        }
        if (column > current) {
            for (int i2 = current; i2 < column; i2++) {
                prefix2[i2] = ' ';
            }
        }
        this.blocks[this.blockDepth - 4] = column;
    }

    /* access modifiers changed from: package-private */
    public void reallyEndLogicalBlock() {
        int oldIndent = getPrefixLength();
        this.blockDepth -= 6;
        int newIndent = getPrefixLength();
        if (newIndent > oldIndent) {
            for (int i = oldIndent; i < newIndent; i++) {
                this.prefix[i] = ' ';
            }
        }
    }

    public int enqueue(int i, int i2) {
        int kind = i;
        int size = i2;
        int oldLength = this.queueInts.length;
        int endAvail = (oldLength - this.queueTail) - this.queueSize;
        if (endAvail > 0 && size > endAvail) {
            int enqueue = enqueue(0, endAvail);
        }
        if (this.queueSize + size > oldLength) {
            int newLength = enoughSpace(oldLength, size);
            int[] newInts = new int[newLength];
            String[] newStrings = new String[newLength];
            int queueHead = (this.queueTail + this.queueSize) - oldLength;
            if (queueHead > 0) {
                System.arraycopy(this.queueInts, 0, newInts, 0, queueHead);
                System.arraycopy(this.queueStrings, 0, newStrings, 0, queueHead);
            }
            int part1Len = oldLength - this.queueTail;
            int deltaLength = newLength - oldLength;
            System.arraycopy(this.queueInts, this.queueTail, newInts, this.queueTail + deltaLength, part1Len);
            System.arraycopy(this.queueStrings, this.queueTail, newStrings, this.queueTail + deltaLength, part1Len);
            this.queueInts = newInts;
            this.queueStrings = newStrings;
            if (this.currentBlock >= this.queueTail) {
                this.currentBlock += deltaLength;
            }
            this.queueTail += deltaLength;
        }
        int addr = this.queueTail + this.queueSize;
        if (addr >= this.queueInts.length) {
            addr -= this.queueInts.length;
        }
        this.queueInts[addr + 0] = kind | (size << 16);
        if (size > 1) {
            this.queueInts[addr + 1] = indexPosn(this.bufferFillPointer);
        }
        this.queueSize += size;
        return addr;
    }

    public void enqueueNewline(int i) {
        int kind = i;
        this.wordEndSeen = false;
        int depth = this.pendingBlocksCount;
        int newline = enqueue(2, 5);
        this.queueInts[newline + 4] = kind;
        this.queueInts[newline + 2] = this.pendingBlocksCount;
        this.queueInts[newline + 3] = 0;
        int entry = this.queueTail;
        int todo = this.queueSize;
        while (todo > 0) {
            if (entry == this.queueInts.length) {
                entry = 0;
            }
            if (entry == newline) {
                break;
            }
            int type = getQueueType(entry);
            if ((type == 2 || type == 4) && this.queueInts[entry + 3] == 0 && depth <= this.queueInts[entry + 2]) {
                int delta = newline - entry;
                if (delta < 0) {
                    delta += this.queueInts.length;
                }
                this.queueInts[entry + 3] = delta;
            }
            int size = getQueueSize(entry);
            todo -= size;
            entry += size;
        }
        boolean maybeOutput = maybeOutput(kind == 76 || kind == 82, false);
    }

    public final void writeBreak(int i) {
        int kind = i;
        if (this.prettyPrintingMode > 0) {
            enqueueNewline(kind);
        }
    }

    public int enqueueIndent(char kind, int amount) {
        int result = enqueue(3, 4);
        this.queueInts[result + 2] = kind;
        this.queueInts[result + 3] = amount;
        return result;
    }

    public void addIndentation(int i, boolean z) {
        int amount = i;
        boolean current = z;
        if (this.prettyPrintingMode > 0) {
            int enqueueIndent = enqueueIndent(current ? 'C' : QITEM_INDENTATION_BLOCK, amount);
        }
    }

    public void startLogicalBlock(String str, boolean z, String str2) {
        int outerBlock;
        String prefix2 = str;
        boolean perLine = z;
        String suffix2 = str2;
        if (this.queueSize == 0 && this.bufferFillPointer == 0) {
            Object llen = lineLengthLoc.get((Object) null);
            if (llen == null) {
                this.lineLength = 80;
            } else {
                this.lineLength = Integer.parseInt(llen.toString());
            }
            Object mwidth = miserWidthLoc.get((Object) null);
            if (mwidth == null || mwidth == Boolean.FALSE || mwidth == LList.Empty) {
                this.miserWidth = -1;
            } else {
                this.miserWidth = Integer.parseInt(mwidth.toString());
            }
            Object obj = indentLoc.get((Object) null);
        }
        if (prefix2 != null) {
            write(prefix2);
        }
        if (this.prettyPrintingMode != 0) {
            int start = enqueue(4, 7);
            this.queueInts[start + 2] = this.pendingBlocksCount;
            this.queueStrings[start + 5] = perLine ? prefix2 : null;
            this.queueStrings[start + 6] = suffix2;
            this.pendingBlocksCount++;
            int outerBlock2 = this.currentBlock;
            if (outerBlock2 < 0) {
                outerBlock = 0;
            } else {
                outerBlock = outerBlock2 - start;
                if (outerBlock > 0) {
                    outerBlock -= this.queueInts.length;
                }
            }
            this.queueInts[start + 4] = outerBlock;
            this.queueInts[start + 3] = 0;
            this.currentBlock = start;
        }
    }

    public void endLogicalBlock() {
        int end = enqueue(5, 2);
        this.pendingBlocksCount--;
        if (this.currentBlock < 0) {
            int suffixLength = this.blocks[this.blockDepth - 5];
            int suffixPreviousLength = this.blocks[(this.blockDepth - 6) - 5];
            if (suffixLength > suffixPreviousLength) {
                write(this.suffix, this.suffix.length - suffixLength, suffixLength - suffixPreviousLength);
            }
            this.currentBlock = -1;
            return;
        }
        int start = this.currentBlock;
        int outerBlock = this.queueInts[start + 4];
        if (outerBlock == 0) {
            this.currentBlock = -1;
        } else {
            int qtailFromStart = this.queueTail - start;
            if (qtailFromStart > 0) {
                qtailFromStart -= this.queueInts.length;
            }
            if (outerBlock < qtailFromStart) {
                this.currentBlock = -1;
            } else {
                int outerBlock2 = outerBlock + start;
                if (outerBlock2 < 0) {
                    outerBlock2 += this.queueInts.length;
                }
                this.currentBlock = outerBlock2;
            }
        }
        String suffix2 = this.queueStrings[start + 6];
        if (suffix2 != null) {
            write(suffix2);
        }
        int endFromStart = end - start;
        if (endFromStart < 0) {
            endFromStart += this.queueInts.length;
        }
        this.queueInts[start + 4] = endFromStart;
    }

    public void endLogicalBlock(String str) {
        String suffix2 = str;
        if (this.prettyPrintingMode > 0) {
            endLogicalBlock();
        } else if (suffix2 != null) {
            write(suffix2);
        }
    }

    /* access modifiers changed from: package-private */
    public int computeTabSize(int i, int i2, int i3) {
        int rem;
        int tab = i;
        int sectionStart = i2;
        int column = i3;
        int flags = this.queueInts[tab + 2];
        boolean isSection = (flags & 1) != 0;
        boolean isRelative = (flags & 2) != 0;
        int origin = isSection ? sectionStart : 0;
        int colnum = this.queueInts[tab + 3];
        int colinc = this.queueInts[tab + 4];
        if (isRelative) {
            if (colinc > 1 && (rem = (column + colnum) % colinc) != 0) {
                int i4 = rem;
                int colinc2 = i4;
                colnum += i4;
            }
            return colnum;
        } else if (column <= colnum + origin) {
            return (column + origin) - column;
        } else {
            return colinc - ((column - origin) % colinc);
        }
    }

    /* access modifiers changed from: package-private */
    public int indexColumn(int i) {
        int index = i;
        int column = this.bufferStartColumn;
        int sectionStart = getSectionColumn();
        int endPosn = indexPosn(index);
        int op = this.queueTail;
        int todo = this.queueSize;
        while (todo > 0) {
            if (op >= this.queueInts.length) {
                op = 0;
            }
            int type = getQueueType(op);
            if (type != 0) {
                int posn = this.queueInts[op + 1];
                if (posn >= endPosn) {
                    break;
                } else if (type == 6) {
                    column += computeTabSize(op, sectionStart, column + posnIndex(posn));
                } else if (type == 2 || type == 4) {
                    sectionStart = column + posnIndex(this.queueInts[op + 1]);
                }
            }
            int size = getQueueSize(op);
            todo -= size;
            op += size;
        }
        return column + index;
    }

    /* access modifiers changed from: package-private */
    public void expandTabs(int i) {
        int through = i;
        int numInsertions = 0;
        int additional = 0;
        int column = this.bufferStartColumn;
        int sectionStart = getSectionColumn();
        int op = this.queueTail;
        int todo = this.queueSize;
        int blocksUsed = 6 * this.pendingBlocksCount;
        while (todo > 0) {
            if (op == this.queueInts.length) {
                op = 0;
            }
            if (op == through) {
                break;
            }
            if (getQueueType(op) == 6) {
                int index = posnIndex(this.queueInts[op + 1]);
                int tabsize = computeTabSize(op, sectionStart, column + index);
                if (tabsize != 0) {
                    if (blocksUsed + (2 * numInsertions) + 1 >= this.blocks.length) {
                        int[] newBlocks = new int[(2 * this.blocks.length)];
                        System.arraycopy(this.blocks, 0, newBlocks, 0, this.blocks.length);
                        this.blocks = newBlocks;
                    }
                    this.blocks[blocksUsed + (2 * numInsertions)] = index;
                    this.blocks[blocksUsed + (2 * numInsertions) + 1] = tabsize;
                    numInsertions++;
                    additional += tabsize;
                    column += tabsize;
                }
            } else if (op == 2 || op == 4) {
                sectionStart = column + posnIndex(this.queueInts[op + 1]);
            }
            int size = getQueueSize(op);
            todo -= size;
            op += size;
        }
        if (numInsertions > 0) {
            int fillPtr = this.bufferFillPointer;
            int newFillPtr = fillPtr + additional;
            char[] buffer2 = this.buffer;
            char[] newBuffer = buffer2;
            int end = fillPtr;
            if (newFillPtr > buffer2.length) {
                newBuffer = new char[enoughSpace(fillPtr, additional)];
                this.buffer = newBuffer;
            }
            this.bufferFillPointer = newFillPtr;
            this.bufferOffset -= additional;
            int i2 = numInsertions;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                int srcpos = this.blocks[blocksUsed + (2 * i2)];
                int amount = this.blocks[blocksUsed + (2 * i2) + 1];
                int dstpos = srcpos + additional;
                System.arraycopy(buffer2, srcpos, newBuffer, dstpos, end - srcpos);
                for (int j = dstpos - amount; j < dstpos; j++) {
                    newBuffer[j] = ' ';
                }
                additional -= amount;
                end = srcpos;
            }
            if (newBuffer != buffer2) {
                System.arraycopy(buffer2, 0, newBuffer, 0, end);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int ensureSpaceInBuffer(int i) {
        int want = i;
        char[] buffer2 = this.buffer;
        int length = buffer2.length;
        int fillPtr = this.bufferFillPointer;
        int available = length - fillPtr;
        if (available > 0) {
            return available;
        }
        if (this.prettyPrintingMode <= 0 || fillPtr <= this.lineLength) {
            int newLength = enoughSpace(length, want);
            char[] newBuffer = new char[newLength];
            this.buffer = newBuffer;
            int i2 = fillPtr;
            while (true) {
                i2--;
                if (i2 < 0) {
                    return newLength - fillPtr;
                }
                newBuffer[i2] = buffer2[i2];
            }
        } else {
            if (!maybeOutput(false, false)) {
                outputPartialLine();
            }
            return ensureSpaceInBuffer(want);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0057, code lost:
        if (r6 == false) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0059, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        if (r2 == false) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005f, code lost:
        if (r7 != 0) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        outputPartialLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        outputLine(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c0, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c1, code lost:
        r8 = r12;
        r12 = r17;
        new java.lang.RuntimeException(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ce, code lost:
        throw r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0029, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        r6 = getQueueSize(r0.queueTail);
        r12 = r0;
        r12.queueSize -= r6;
        r0.queueTail = r4 + r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean maybeOutput(boolean r19, boolean r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r12 = 0
            r3 = r12
        L_0x0008:
            r12 = r0
            int r12 = r12.queueSize
            if (r12 <= 0) goto L_0x01a8
            r12 = r0
            int r12 = r12.queueTail
            r13 = r0
            int[] r13 = r13.queueInts
            int r13 = r13.length
            if (r12 < r13) goto L_0x001a
            r12 = r0
            r13 = 0
            r12.queueTail = r13
        L_0x001a:
            r12 = r0
            int r12 = r12.queueTail
            r4 = r12
            r12 = r0
            r13 = r4
            int r12 = r12.getQueueType(r13)
            r5 = r12
            r12 = r5
            switch(r12) {
                case 2: goto L_0x0046;
                case 3: goto L_0x00cf;
                case 4: goto L_0x0111;
                case 5: goto L_0x019b;
                case 6: goto L_0x01a1;
                default: goto L_0x0029;
            }
        L_0x0029:
            r12 = r0
            r13 = r0
            int r13 = r13.queueTail
            int r12 = r12.getQueueSize(r13)
            r6 = r12
            r12 = r0
            r17 = r12
            r12 = r17
            r13 = r17
            int r13 = r13.queueSize
            r14 = r6
            int r13 = r13 - r14
            r12.queueSize = r13
            r12 = r0
            r13 = r4
            r14 = r6
            int r13 = r13 + r14
            r12.queueTail = r13
            goto L_0x0008
        L_0x0046:
            r12 = -1
            r7 = r12
            r12 = r0
            int[] r12 = r12.queueInts
            r13 = r4
            r14 = 4
            int r13 = r13 + 4
            r12 = r12[r13]
            switch(r12) {
                case 70: goto L_0x006d;
                case 77: goto L_0x0066;
                case 83: goto L_0x0081;
                default: goto L_0x0054;
            }
        L_0x0054:
            r12 = 1
            r6 = r12
        L_0x0056:
            r12 = r6
            if (r12 == 0) goto L_0x0029
            r12 = 1
            r3 = r12
            r12 = r2
            if (r12 == 0) goto L_0x00ba
            r12 = r7
            if (r12 != 0) goto L_0x00ba
            r12 = r0
            r12.outputPartialLine()     // Catch:{ IOException -> 0x00c0 }
        L_0x0065:
            goto L_0x0029
        L_0x0066:
            r12 = r0
            boolean r12 = r12.isMisering()
            r6 = r12
            goto L_0x0056
        L_0x006d:
            r12 = r0
            boolean r12 = r12.isMisering()
            if (r12 != 0) goto L_0x007e
            r12 = r0
            int r12 = r12.lineNumber
            r13 = r0
            int r13 = r13.getSectionStartLine()
            if (r12 <= r13) goto L_0x0081
        L_0x007e:
            r12 = 1
            r6 = r12
            goto L_0x0056
        L_0x0081:
            r12 = r0
            int[] r12 = r12.queueInts
            r13 = r4
            r14 = 3
            int r13 = r13 + 3
            r12 = r12[r13]
            r8 = r12
            r12 = r8
            if (r12 != 0) goto L_0x009e
            r12 = -1
            r8 = r12
        L_0x0090:
            r12 = r0
            r13 = r8
            r14 = r1
            int r12 = r12.fitsOnLine(r13, r14)
            r7 = r12
            r12 = r7
            if (r12 <= 0) goto L_0x00b1
            r12 = 0
            r6 = r12
            goto L_0x0056
        L_0x009e:
            r12 = r4
            r13 = r8
            int r12 = r12 + r13
            r8 = r12
            r12 = r8
            r13 = r0
            int[] r13 = r13.queueInts
            int r13 = r13.length
            if (r12 < r13) goto L_0x0090
            r12 = r8
            r13 = r0
            int[] r13 = r13.queueInts
            int r13 = r13.length
            int r12 = r12 - r13
            r8 = r12
            goto L_0x0090
        L_0x00b1:
            r12 = r7
            if (r12 < 0) goto L_0x00b7
            r12 = r2
            if (r12 == 0) goto L_0x01a8
        L_0x00b7:
            r12 = 1
            r6 = r12
            goto L_0x0056
        L_0x00ba:
            r12 = r0
            r13 = r4
            r12.outputLine(r13)     // Catch:{ IOException -> 0x00c0 }
            goto L_0x0065
        L_0x00c0:
            r12 = move-exception
            r8 = r12
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            r17 = r12
            r12 = r17
            r13 = r17
            r14 = r8
            r13.<init>(r14)
            throw r12
        L_0x00cf:
            r12 = r0
            boolean r12 = r12.isMisering()
            if (r12 != 0) goto L_0x0029
            r12 = r0
            int[] r12 = r12.queueInts
            r13 = r4
            r14 = 2
            int r13 = r13 + 2
            r12 = r12[r13]
            r8 = r12
            r12 = r0
            int[] r12 = r12.queueInts
            r13 = r4
            r14 = 3
            int r13 = r13 + 3
            r12 = r12[r13]
            r9 = r12
            r12 = r8
            r13 = 66
            if (r12 != r13) goto L_0x00fe
            r12 = r9
            r13 = r0
            int r13 = r13.getStartColumn()
            int r12 = r12 + r13
            r9 = r12
        L_0x00f7:
            r12 = r0
            r13 = r9
            r12.setIndentation(r13)
            goto L_0x0029
        L_0x00fe:
            r12 = r9
            r13 = r0
            r14 = r0
            int[] r14 = r14.queueInts
            r15 = r4
            r16 = 1
            int r15 = r15 + 1
            r14 = r14[r15]
            int r13 = r13.posnColumn(r14)
            int r12 = r12 + r13
            r9 = r12
            goto L_0x00f7
        L_0x0111:
            r12 = r4
            r8 = r12
            r12 = r0
            int[] r12 = r12.queueInts
            r13 = r4
            r14 = 3
            int r13 = r13 + 3
            r12 = r12[r13]
            r9 = r12
            r12 = r9
            if (r12 <= 0) goto L_0x0169
            r12 = r9
            r13 = r4
            int r12 = r12 + r13
            r13 = r0
            int[] r13 = r13.queueInts
            int r13 = r13.length
            int r12 = r12 % r13
        L_0x0128:
            r9 = r12
            r12 = r0
            r13 = r9
            r14 = r1
            int r12 = r12.fitsOnLine(r13, r14)
            r7 = r12
            r12 = r7
            if (r12 <= 0) goto L_0x016b
            r12 = r0
            int[] r12 = r12.queueInts
            r13 = r4
            r14 = 4
            int r13 = r13 + 4
            r12 = r12[r13]
            r10 = r12
            r12 = r10
            r13 = r4
            int r12 = r12 + r13
            r13 = r0
            int[] r13 = r13.queueInts
            int r13 = r13.length
            int r12 = r12 % r13
            r4 = r12
            r12 = r0
            r13 = r4
            r12.expandTabs(r13)
            r12 = r0
            r13 = r4
            r12.queueTail = r13
            r12 = r0
            r17 = r12
            r12 = r17
            r13 = r17
            int r13 = r13.queueSize
            r14 = r10
            int r13 = r13 - r14
            r12.queueSize = r13
        L_0x015d:
            r12 = r0
            int r12 = r12.currentBlock
            r13 = r8
            if (r12 != r13) goto L_0x0029
            r12 = r0
            r13 = -1
            r12.currentBlock = r13
            goto L_0x0029
        L_0x0169:
            r12 = -1
            goto L_0x0128
        L_0x016b:
            r12 = r7
            if (r12 < 0) goto L_0x0171
            r12 = r2
            if (r12 == 0) goto L_0x01a8
        L_0x0171:
            r12 = r0
            java.lang.String[] r12 = r12.queueStrings
            r13 = r4
            r14 = 5
            int r13 = r13 + 5
            r12 = r12[r13]
            r10 = r12
            r12 = r0
            java.lang.String[] r12 = r12.queueStrings
            r13 = r4
            r14 = 6
            int r13 = r13 + 6
            r12 = r12[r13]
            r11 = r12
            r12 = r0
            r13 = r0
            r14 = r0
            int[] r14 = r14.queueInts
            r15 = r4
            r16 = 1
            int r15 = r15 + 1
            r14 = r14[r15]
            int r13 = r13.posnColumn(r14)
            r14 = r10
            r15 = r11
            r12.reallyStartLogicalBlock(r13, r14, r15)
            goto L_0x015d
        L_0x019b:
            r12 = r0
            r12.reallyEndLogicalBlock()
            goto L_0x0029
        L_0x01a1:
            r12 = r0
            r13 = r4
            r12.expandTabs(r13)
            goto L_0x0029
        L_0x01a8:
            r12 = r3
            r0 = r12
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.PrettyWriter.maybeOutput(boolean, boolean):boolean");
    }

    /* access modifiers changed from: protected */
    public int getMiserWidth() {
        return this.miserWidth;
    }

    /* access modifiers changed from: package-private */
    public boolean isMisering() {
        int mwidth = getMiserWidth();
        return mwidth > 0 && this.lineLength - getStartColumn() <= mwidth;
    }

    /* access modifiers changed from: package-private */
    public int getMaxLines() {
        return -1;
    }

    /* access modifiers changed from: package-private */
    public boolean printReadably() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public int fitsOnLine(int i, boolean z) {
        int sectionEnd = i;
        boolean forceNewlines = z;
        int available = this.lineLength;
        if (!printReadably() && getMaxLines() == this.lineNumber) {
            available = (available - 3) - getSuffixLength();
        }
        if (sectionEnd >= 0) {
            return posnColumn(this.queueInts[sectionEnd + 1]) <= available ? 1 : -1;
        } else if (forceNewlines) {
            return -1;
        } else {
            if (indexColumn(this.bufferFillPointer) > available) {
                return -1;
            }
            return 0;
        }
    }

    public void lineAbbreviationHappened() {
    }

    /* access modifiers changed from: package-private */
    public void outputLine(int i) throws IOException {
        int amountToPrint;
        int maxLines;
        int newline = i;
        char[] buffer2 = this.buffer;
        boolean isLiteral = this.queueInts[newline + 4] == 76;
        int amountToConsume = posnIndex(this.queueInts[newline + 1]);
        if (!isLiteral) {
            int i2 = amountToConsume;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    if (buffer2[i2] != ' ') {
                        amountToPrint = i2 + 1;
                        break;
                    }
                } else {
                    amountToPrint = 0;
                    break;
                }
            }
        } else {
            amountToPrint = amountToConsume;
        }
        this.out.write(buffer2, 0, amountToPrint);
        int lineNumber2 = this.lineNumber + 1;
        if (!printReadably() && (maxLines = getMaxLines()) > 0 && lineNumber2 >= maxLines) {
            this.out.write(" ..");
            int suffixLength = getSuffixLength();
            if (suffixLength != 0) {
                char[] suffix2 = this.suffix;
                this.out.write(suffix2, suffix2.length - suffixLength, suffixLength);
            }
            lineAbbreviationHappened();
        }
        this.lineNumber = lineNumber2;
        this.out.write(10);
        this.bufferStartColumn = 0;
        int fillPtr = this.bufferFillPointer;
        int prefixLen = isLiteral ? getPerLinePrefixEnd() : getPrefixLength();
        int shift = amountToConsume - prefixLen;
        int newFillPtr = fillPtr - shift;
        char[] newBuffer = buffer2;
        int bufferLength = buffer2.length;
        if (newFillPtr > bufferLength) {
            newBuffer = new char[enoughSpace(bufferLength, newFillPtr - bufferLength)];
            this.buffer = newBuffer;
        }
        System.arraycopy(buffer2, amountToConsume, newBuffer, prefixLen, fillPtr - amountToConsume);
        System.arraycopy(this.prefix, 0, newBuffer, 0, prefixLen);
        this.bufferFillPointer = newFillPtr;
        this.bufferOffset += shift;
        if (!isLiteral) {
            this.blocks[this.blockDepth - 2] = prefixLen;
            this.blocks[this.blockDepth - 6] = lineNumber2;
        }
    }

    /* access modifiers changed from: package-private */
    public void outputPartialLine() {
        Throwable th;
        Throwable th2;
        int tail = this.queueTail;
        while (this.queueSize > 0 && getQueueType(tail) == 0) {
            int size = getQueueSize(tail);
            this.queueSize -= size;
            tail += size;
            if (tail == this.queueInts.length) {
                tail = 0;
            }
            this.queueTail = tail;
        }
        int fillPtr = this.bufferFillPointer;
        int count = this.queueSize > 0 ? posnIndex(this.queueInts[tail + 1]) : fillPtr;
        int newFillPtr = fillPtr - count;
        if (count <= 0) {
            Throwable th3 = th2;
            new Error("outputPartialLine called when nothing can be output.");
            throw th3;
        }
        try {
            this.out.write(this.buffer, 0, count);
            this.bufferFillPointer = count;
            this.bufferStartColumn = getColumnNumber();
            System.arraycopy(this.buffer, count, this.buffer, 0, newFillPtr);
            this.bufferFillPointer = newFillPtr;
            this.bufferOffset += count;
        } catch (IOException e) {
            IOException ex = e;
            Throwable th4 = th;
            new RuntimeException(ex);
            throw th4;
        }
    }

    public void forcePrettyOutput() throws IOException {
        boolean maybeOutput = maybeOutput(false, true);
        if (this.bufferFillPointer > 0) {
            outputPartialLine();
        }
        expandTabs(-1);
        this.bufferStartColumn = getColumnNumber();
        this.out.write(this.buffer, 0, this.bufferFillPointer);
        this.bufferFillPointer = 0;
        this.queueSize = 0;
    }

    public void flush() {
        Throwable th;
        if (this.out != null) {
            try {
                forcePrettyOutput();
                this.out.flush();
            } catch (IOException e) {
                IOException ex = e;
                Throwable th2 = th;
                new RuntimeException(ex.toString());
                throw th2;
            }
        }
    }

    public void close() throws IOException {
        if (this.out != null) {
            forcePrettyOutput();
            this.out.close();
            this.out = null;
        }
        this.buffer = null;
    }

    public void closeThis() throws IOException {
        if (this.out != null) {
            forcePrettyOutput();
            this.out = null;
        }
        this.buffer = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x000a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getColumnNumber() {
        /*
            r6 = this;
            r0 = r6
            r3 = r0
            int r3 = r3.bufferFillPointer
            r1 = r3
        L_0x0005:
            int r1 = r1 + -1
            r3 = r1
            if (r3 >= 0) goto L_0x0013
            r3 = r0
            int r3 = r3.bufferStartColumn
            r4 = r0
            int r4 = r4.bufferFillPointer
            int r3 = r3 + r4
            r0 = r3
        L_0x0012:
            return r0
        L_0x0013:
            r3 = r0
            char[] r3 = r3.buffer
            r4 = r1
            char r3 = r3[r4]
            r2 = r3
            r3 = r2
            r4 = 10
            if (r3 == r4) goto L_0x0024
            r3 = r2
            r4 = 13
            if (r3 != r4) goto L_0x002e
        L_0x0024:
            r3 = r0
            int r3 = r3.bufferFillPointer
            r4 = r1
            r5 = 1
            int r4 = r4 + 1
            int r3 = r3 - r4
            r0 = r3
            goto L_0x0012
        L_0x002e:
            goto L_0x0005
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.PrettyWriter.getColumnNumber():int");
    }

    public void setColumnNumber(int column) {
        this.bufferStartColumn += column - getColumnNumber();
    }

    public void clearBuffer() {
        this.bufferStartColumn = 0;
        this.bufferFillPointer = 0;
        this.lineNumber = 0;
        this.bufferOffset = 0;
        this.blockDepth = 6;
        this.queueTail = 0;
        this.queueSize = 0;
        this.pendingBlocksCount = 0;
    }
}
