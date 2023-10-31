package gnu.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LineBufferedReader extends Reader {
    public static final int BUFFER_SIZE = 8192;
    private static final int CONVERT_CR = 1;
    private static final int DONT_KEEP_FULL_LINES = 8;
    private static final int PREV_WAS_CR = 4;
    private static final int USER_BUFFER = 2;
    public char[] buffer;
    private int flags;
    int highestPos;

    /* renamed from: in */
    protected Reader f247in;
    public int limit;
    protected int lineNumber;
    private int lineStartPos;
    protected int markPos;
    Path path;
    public int pos;
    protected int readAheadLimit = 0;
    public char readState = 10;

    public void close() throws IOException {
        this.f247in.close();
    }

    public char getReadState() {
        return this.readState;
    }

    public void setKeepFullLines(boolean keep) {
        if (keep) {
            this.flags &= -9;
            return;
        }
        this.flags |= 8;
    }

    public final boolean getConvertCR() {
        return (this.flags & 1) != 0;
    }

    public final void setConvertCR(boolean convertCR) {
        if (convertCR) {
            this.flags |= 1;
            return;
        }
        this.flags &= -2;
    }

    public LineBufferedReader(InputStream in) {
        Reader reader;
        new InputStreamReader(in);
        this.f247in = reader;
    }

    public LineBufferedReader(Reader in) {
        this.f247in = in;
    }

    public void lineStart(boolean revisited) throws IOException {
    }

    public int fill(int len) throws IOException {
        return this.f247in.read(this.buffer, this.pos, len);
    }

    private void clearMark() {
        this.readAheadLimit = 0;
        int i = this.lineStartPos < 0 ? 0 : this.lineStartPos;
        while (true) {
            i++;
            if (i < this.pos) {
                char ch = this.buffer[i - 1];
                if (ch == 10 || (ch == 13 && (!getConvertCR() || this.buffer[i] != 10))) {
                    this.lineNumber++;
                    this.lineStartPos = i;
                }
            } else {
                return;
            }
        }
    }

    public void setBuffer(char[] cArr) throws IOException {
        Throwable th;
        char[] buffer2 = cArr;
        if (buffer2 == null) {
            if (this.buffer != null) {
                char[] buffer3 = new char[this.buffer.length];
                System.arraycopy(this.buffer, 0, buffer3, 0, this.buffer.length);
                this.buffer = buffer3;
            }
            this.flags &= -3;
        } else if (this.limit - this.pos > buffer2.length) {
            Throwable th2 = th;
            new IOException("setBuffer - too short");
            throw th2;
        } else {
            this.flags |= 2;
            reserve(buffer2, 0);
        }
    }

    private void reserve(char[] cArr, int reserve) throws IOException {
        int saveStart;
        char[] buffer2 = cArr;
        int reserve2 = reserve + this.limit;
        if (reserve2 <= buffer2.length) {
            saveStart = 0;
        } else {
            saveStart = this.pos;
            if (this.readAheadLimit > 0 && this.markPos < this.pos) {
                if (this.pos - this.markPos > this.readAheadLimit || ((this.flags & 2) != 0 && reserve2 - this.markPos > buffer2.length)) {
                    clearMark();
                } else {
                    saveStart = this.markPos;
                }
            }
            int reserve3 = reserve2 - buffer2.length;
            if (reserve3 > saveStart || (saveStart > this.lineStartPos && (this.flags & 8) == 0)) {
                if (reserve3 <= this.lineStartPos && saveStart > this.lineStartPos) {
                    saveStart = this.lineStartPos;
                } else if ((this.flags & 2) != 0) {
                    saveStart -= (saveStart - reserve3) >> 2;
                } else {
                    if (this.lineStartPos >= 0) {
                        saveStart = this.lineStartPos;
                    }
                    buffer2 = new char[(2 * buffer2.length)];
                }
            }
            this.lineStartPos -= saveStart;
            this.limit -= saveStart;
            this.markPos -= saveStart;
            this.pos -= saveStart;
            this.highestPos -= saveStart;
        }
        if (this.limit > 0) {
            System.arraycopy(this.buffer, saveStart, buffer2, 0, this.limit);
        }
        this.buffer = buffer2;
    }

    public int read() throws IOException {
        char prev;
        if (this.pos > 0) {
            prev = this.buffer[this.pos - 1];
        } else if ((this.flags & 4) != 0) {
            prev = 13;
        } else if (this.lineStartPos >= 0) {
            prev = 10;
        } else {
            prev = 0;
        }
        if (prev == 13 || prev == 10) {
            if (this.lineStartPos < this.pos && (this.readAheadLimit == 0 || this.pos <= this.markPos)) {
                this.lineStartPos = this.pos;
                this.lineNumber++;
            }
            boolean revisited = this.pos < this.highestPos;
            if (prev != 10 || (this.pos > 1 ? this.buffer[this.pos - 2] != 13 : (this.flags & 4) == 0)) {
                lineStart(revisited);
            }
            if (!revisited) {
                this.highestPos = this.pos + 1;
            }
        }
        if (this.pos >= this.limit) {
            if (this.buffer == null) {
                this.buffer = new char[8192];
            } else if (this.limit == this.buffer.length) {
                reserve(this.buffer, 1);
            }
            if (this.pos == 0) {
                if (prev == 13) {
                    this.flags |= 4;
                } else {
                    this.flags &= -5;
                }
            }
            int readCount = fill(this.buffer.length - this.pos);
            if (readCount <= 0) {
                return -1;
            }
            this.limit += readCount;
        }
        char[] cArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        int ch = cArr[i];
        if (ch == 10) {
            if (prev == 13) {
                if (this.lineStartPos == this.pos - 1) {
                    this.lineNumber--;
                    this.lineStartPos--;
                }
                if (getConvertCR()) {
                    return read();
                }
            }
        } else if (ch == 13 && getConvertCR()) {
            return 10;
        }
        return ch;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(char[] r12, int r13, int r14) throws java.io.IOException {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r8 = r0
            int r8 = r8.pos
            r9 = r0
            int r9 = r9.limit
            if (r8 < r9) goto L_0x0036
            r8 = 0
            r4 = r8
        L_0x000e:
            r8 = r3
            r5 = r8
        L_0x0010:
            r8 = r5
            if (r8 <= 0) goto L_0x00bc
            r8 = r0
            int r8 = r8.pos
            r9 = r0
            int r9 = r9.limit
            if (r8 >= r9) goto L_0x0025
            r8 = r4
            r9 = 10
            if (r8 == r9) goto L_0x0025
            r8 = r4
            r9 = 13
            if (r8 != r9) goto L_0x007c
        L_0x0025:
            r8 = r0
            int r8 = r8.pos
            r9 = r0
            int r9 = r9.limit
            if (r8 < r9) goto L_0x005c
            r8 = r5
            r9 = r3
            if (r8 >= r9) goto L_0x005c
            r8 = r3
            r9 = r5
            int r8 = r8 - r9
            r0 = r8
        L_0x0035:
            return r0
        L_0x0036:
            r8 = r0
            int r8 = r8.pos
            if (r8 <= 0) goto L_0x0048
            r8 = r0
            char[] r8 = r8.buffer
            r9 = r0
            int r9 = r9.pos
            r10 = 1
            int r9 = r9 + -1
            char r8 = r8[r9]
            r4 = r8
            goto L_0x000e
        L_0x0048:
            r8 = r0
            int r8 = r8.flags
            r9 = 4
            r8 = r8 & 4
            if (r8 != 0) goto L_0x0055
            r8 = r0
            int r8 = r8.lineStartPos
            if (r8 < 0) goto L_0x0059
        L_0x0055:
            r8 = 10
            r4 = r8
            goto L_0x000e
        L_0x0059:
            r8 = 0
            r4 = r8
            goto L_0x000e
        L_0x005c:
            r8 = r0
            int r8 = r8.read()
            r4 = r8
            r8 = r4
            if (r8 >= 0) goto L_0x0071
            r8 = r3
            r9 = r5
            int r8 = r8 - r9
            r3 = r8
            r8 = r3
            if (r8 > 0) goto L_0x006f
            r8 = -1
        L_0x006d:
            r0 = r8
            goto L_0x0035
        L_0x006f:
            r8 = r3
            goto L_0x006d
        L_0x0071:
            r8 = r1
            r9 = r2
            int r2 = r2 + 1
            r10 = r4
            char r10 = (char) r10
            r8[r9] = r10
            int r5 = r5 + -1
            goto L_0x0010
        L_0x007c:
            r8 = r0
            int r8 = r8.pos
            r6 = r8
            r8 = r0
            int r8 = r8.limit
            r7 = r8
            r8 = r5
            r9 = r7
            r10 = r6
            int r9 = r9 - r10
            if (r8 >= r9) goto L_0x008e
            r8 = r6
            r9 = r5
            int r8 = r8 + r9
            r7 = r8
        L_0x008e:
            r8 = r6
            r9 = r7
            if (r8 >= r9) goto L_0x00a3
            r8 = r0
            char[] r8 = r8.buffer
            r9 = r6
            char r8 = r8[r9]
            r4 = r8
            r8 = r4
            r9 = 10
            if (r8 == r9) goto L_0x00a3
            r8 = r4
            r9 = 13
            if (r8 != r9) goto L_0x00b1
        L_0x00a3:
            r8 = r5
            r9 = r6
            r10 = r0
            int r10 = r10.pos
            int r9 = r9 - r10
            int r8 = r8 - r9
            r5 = r8
            r8 = r0
            r9 = r6
            r8.pos = r9
            goto L_0x0010
        L_0x00b1:
            r8 = r1
            r9 = r2
            int r2 = r2 + 1
            r10 = r4
            char r10 = (char) r10
            r8[r9] = r10
            int r6 = r6 + 1
            goto L_0x008e
        L_0x00bc:
            r8 = r3
            r0 = r8
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.read(char[], int, int):int");
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path2) {
        Path path3 = path2;
        this.path = path3;
    }

    public String getName() {
        return this.path == null ? null : this.path.toString();
    }

    public void setName(Object name) {
        setPath(Path.valueOf(name));
    }

    public int getLineNumber() {
        char prev;
        int lineno = this.lineNumber;
        if (this.readAheadLimit != 0) {
            lineno += countLines(this.buffer, this.lineStartPos < 0 ? 0 : this.lineStartPos, this.pos);
        } else if (this.pos > 0 && this.pos > this.lineStartPos && ((prev = this.buffer[this.pos - 1]) == 10 || prev == 13)) {
            lineno++;
        }
        return lineno;
    }

    public void setLineNumber(int lineNumber2) {
        this.lineNumber += lineNumber2 - getLineNumber();
    }

    public void incrLineNumber(int lineDelta, int lineStartPos2) {
        this.lineNumber += lineDelta;
        this.lineStartPos = lineStartPos2;
    }

    public int getColumnNumber() {
        char prev;
        if (this.pos > 0 && ((prev = this.buffer[this.pos - 1]) == 10 || prev == 13)) {
            return 0;
        }
        if (this.readAheadLimit <= 0) {
            return this.pos - this.lineStartPos;
        }
        int start = this.lineStartPos < 0 ? 0 : this.lineStartPos;
        int i = start;
        while (i < this.pos) {
            int i2 = i;
            i++;
            char ch = this.buffer[i2];
            if (ch == 10 || ch == 13) {
                start = i;
            }
        }
        int col = this.pos - start;
        if (this.lineStartPos < 0) {
            col -= this.lineStartPos;
        }
        return col;
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized void mark(int i) {
        int readAheadLimit2 = i;
        synchronized (this) {
            if (this.readAheadLimit > 0) {
                clearMark();
            }
            this.readAheadLimit = readAheadLimit2;
            this.markPos = this.pos;
        }
    }

    public void reset() throws IOException {
        Throwable th;
        if (this.readAheadLimit <= 0) {
            Throwable th2 = th;
            new IOException("mark invalid");
            throw th2;
        }
        if (this.pos > this.highestPos) {
            this.highestPos = this.pos;
        }
        this.pos = this.markPos;
        this.readAheadLimit = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readLine(java.lang.StringBuffer r13, char r14) throws java.io.IOException {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
        L_0x0003:
            r5 = r0
            int r5 = r5.read()
            r3 = r5
            r5 = r3
            if (r5 >= 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            r5 = r0
            r10 = r5
            r5 = r10
            r6 = r10
            int r6 = r6.pos
            r7 = 1
            int r6 = r6 + -1
            r10 = r5
            r11 = r6
            r5 = r11
            r6 = r10
            r7 = r11
            r6.pos = r7
            r4 = r5
        L_0x001e:
            r5 = r0
            int r5 = r5.pos
            r6 = r0
            int r6 = r6.limit
            if (r5 >= r6) goto L_0x00ad
            r5 = r0
            char[] r5 = r5.buffer
            r6 = r0
            r10 = r6
            r6 = r10
            r7 = r10
            int r7 = r7.pos
            r10 = r6
            r11 = r7
            r6 = r11
            r7 = r10
            r8 = r11
            r9 = 1
            int r8 = r8 + 1
            r7.pos = r8
            char r5 = r5[r6]
            r3 = r5
            r5 = r3
            r6 = 13
            if (r5 == r6) goto L_0x0046
            r5 = r3
            r6 = 10
            if (r5 != r6) goto L_0x001e
        L_0x0046:
            r5 = r1
            r6 = r0
            char[] r6 = r6.buffer
            r7 = r4
            r8 = r0
            int r8 = r8.pos
            r9 = 1
            int r8 = r8 + -1
            r9 = r4
            int r8 = r8 - r9
            java.lang.StringBuffer r5 = r5.append(r6, r7, r8)
            r5 = r2
            r6 = 80
            if (r5 != r6) goto L_0x0068
            r5 = r0
            r10 = r5
            r5 = r10
            r6 = r10
            int r6 = r6.pos
            r7 = 1
            int r6 = r6 + -1
            r5.pos = r6
            goto L_0x000c
        L_0x0068:
            r5 = r0
            boolean r5 = r5.getConvertCR()
            if (r5 != 0) goto L_0x0074
            r5 = r3
            r6 = 10
            if (r5 != r6) goto L_0x0081
        L_0x0074:
            r5 = r2
            r6 = 73
            if (r5 == r6) goto L_0x0080
            r5 = r1
            r6 = 10
            java.lang.StringBuffer r5 = r5.append(r6)
        L_0x0080:
            goto L_0x000c
        L_0x0081:
            r5 = r2
            r6 = 73
            if (r5 == r6) goto L_0x008d
            r5 = r1
            r6 = 13
            java.lang.StringBuffer r5 = r5.append(r6)
        L_0x008d:
            r5 = r0
            int r5 = r5.read()
            r3 = r5
            r5 = r3
            r6 = 10
            if (r5 != r6) goto L_0x00a5
            r5 = r2
            r6 = 73
            if (r5 == r6) goto L_0x0080
            r5 = r1
            r6 = 10
            java.lang.StringBuffer r5 = r5.append(r6)
            goto L_0x0080
        L_0x00a5:
            r5 = r3
            if (r5 < 0) goto L_0x0080
            r5 = r0
            r5.unread_quick()
            goto L_0x0080
        L_0x00ad:
            r5 = r1
            r6 = r0
            char[] r6 = r6.buffer
            r7 = r4
            r8 = r0
            int r8 = r8.pos
            r9 = r4
            int r8 = r8 - r9
            java.lang.StringBuffer r5 = r5.append(r6, r7, r8)
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.readLine(java.lang.StringBuffer, char):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
        if (r1 == 10) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005c, code lost:
        if (getConvertCR() != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0064, code lost:
        if (r0.pos < r0.limit) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        r4 = r0;
        r10 = r4;
        r10.pos--;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a3, code lost:
        if (r0.buffer[r0.pos] != 10) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a5, code lost:
        r4 = r0;
        r4.pos++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b0, code lost:
        new java.lang.String(r0.buffer, r2, r3 - r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0071 A[EDGE_INSN: B:29:0x0071->B:22:0x0071 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String readLine() throws java.io.IOException {
        /*
            r12 = this;
            r0 = r12
            r4 = r0
            int r4 = r4.read()
            r1 = r4
            r4 = r1
            if (r4 >= 0) goto L_0x000d
            r4 = 0
            r0 = r4
        L_0x000c:
            return r0
        L_0x000d:
            r4 = r1
            r5 = 13
            if (r4 == r5) goto L_0x0017
            r4 = r1
            r5 = 10
            if (r4 != r5) goto L_0x001c
        L_0x0017:
            java.lang.String r4 = ""
            r0 = r4
            goto L_0x000c
        L_0x001c:
            r4 = r0
            int r4 = r4.pos
            r5 = 1
            int r4 = r4 + -1
            r2 = r4
        L_0x0023:
            r4 = r0
            int r4 = r4.pos
            r5 = r0
            int r5 = r5.limit
            if (r4 >= r5) goto L_0x0071
            r4 = r0
            char[] r4 = r4.buffer
            r5 = r0
            r10 = r5
            r5 = r10
            r6 = r10
            int r6 = r6.pos
            r10 = r5
            r11 = r6
            r5 = r11
            r6 = r10
            r7 = r11
            r8 = 1
            int r7 = r7 + 1
            r6.pos = r7
            char r4 = r4[r5]
            r1 = r4
            r4 = r1
            r5 = 13
            if (r4 == r5) goto L_0x004b
            r4 = r1
            r5 = 10
            if (r4 != r5) goto L_0x0023
        L_0x004b:
            r4 = r0
            int r4 = r4.pos
            r5 = 1
            int r4 = r4 + -1
            r3 = r4
            r4 = r1
            r5 = 10
            if (r4 == r5) goto L_0x00b0
            r4 = r0
            boolean r4 = r4.getConvertCR()
            if (r4 != 0) goto L_0x00b0
            r4 = r0
            int r4 = r4.pos
            r5 = r0
            int r5 = r5.limit
            if (r4 < r5) goto L_0x0099
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            int r5 = r5.pos
            r6 = 1
            int r5 = r5 + -1
            r4.pos = r5
        L_0x0071:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r10 = r4
            r4 = r10
            r5 = r10
            r6 = 100
            r5.<init>(r6)
            r3 = r4
            r4 = r3
            r5 = r0
            char[] r5 = r5.buffer
            r6 = r2
            r7 = r0
            int r7 = r7.pos
            r8 = r2
            int r7 = r7 - r8
            java.lang.StringBuffer r4 = r4.append(r5, r6, r7)
            r4 = r0
            r5 = r3
            r6 = 73
            r4.readLine(r5, r6)
            r4 = r3
            java.lang.String r4 = r4.toString()
            r0 = r4
            goto L_0x000c
        L_0x0099:
            r4 = r0
            char[] r4 = r4.buffer
            r5 = r0
            int r5 = r5.pos
            char r4 = r4[r5]
            r5 = 10
            if (r4 != r5) goto L_0x00b0
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            int r5 = r5.pos
            r6 = 1
            int r5 = r5 + 1
            r4.pos = r5
        L_0x00b0:
            java.lang.String r4 = new java.lang.String
            r10 = r4
            r4 = r10
            r5 = r10
            r6 = r0
            char[] r6 = r6.buffer
            r7 = r2
            r8 = r3
            r9 = r2
            int r8 = r8 - r9
            r5.<init>(r6, r7, r8)
            r0 = r4
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.readLine():java.lang.String");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int skip(int r10) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r6 = r1
            if (r6 >= 0) goto L_0x001c
            r6 = r1
            int r6 = -r6
            r2 = r6
        L_0x0008:
            r6 = r2
            if (r6 <= 0) goto L_0x0017
            r6 = r0
            int r6 = r6.pos
            if (r6 <= 0) goto L_0x0017
            r6 = r0
            r6.unread()
            int r2 = r2 + -1
            goto L_0x0008
        L_0x0017:
            r6 = r1
            r7 = r2
            int r6 = r6 + r7
            r0 = r6
        L_0x001b:
            return r0
        L_0x001c:
            r6 = r1
            r2 = r6
            r6 = r0
            int r6 = r6.pos
            r7 = r0
            int r7 = r7.limit
            if (r6 < r7) goto L_0x004b
            r6 = 0
            r3 = r6
        L_0x0028:
            r6 = r2
            if (r6 <= 0) goto L_0x00ab
            r6 = r3
            r7 = 10
            if (r6 == r7) goto L_0x003d
            r6 = r3
            r7 = 13
            if (r6 == r7) goto L_0x003d
            r6 = r0
            int r6 = r6.pos
            r7 = r0
            int r7 = r7.limit
            if (r6 < r7) goto L_0x0074
        L_0x003d:
            r6 = r0
            int r6 = r6.read()
            r3 = r6
            r6 = r3
            if (r6 >= 0) goto L_0x0071
            r6 = r1
            r7 = r2
            int r6 = r6 - r7
            r0 = r6
            goto L_0x001b
        L_0x004b:
            r6 = r0
            int r6 = r6.pos
            if (r6 <= 0) goto L_0x005d
            r6 = r0
            char[] r6 = r6.buffer
            r7 = r0
            int r7 = r7.pos
            r8 = 1
            int r7 = r7 + -1
            char r6 = r6[r7]
            r3 = r6
            goto L_0x0028
        L_0x005d:
            r6 = r0
            int r6 = r6.flags
            r7 = 4
            r6 = r6 & 4
            if (r6 != 0) goto L_0x006a
            r6 = r0
            int r6 = r6.lineStartPos
            if (r6 < 0) goto L_0x006e
        L_0x006a:
            r6 = 10
            r3 = r6
            goto L_0x0028
        L_0x006e:
            r6 = 0
            r3 = r6
            goto L_0x0028
        L_0x0071:
            int r2 = r2 + -1
            goto L_0x0028
        L_0x0074:
            r6 = r0
            int r6 = r6.pos
            r4 = r6
            r6 = r0
            int r6 = r6.limit
            r5 = r6
            r6 = r2
            r7 = r5
            r8 = r4
            int r7 = r7 - r8
            if (r6 >= r7) goto L_0x0086
            r6 = r4
            r7 = r2
            int r6 = r6 + r7
            r5 = r6
        L_0x0086:
            r6 = r4
            r7 = r5
            if (r6 >= r7) goto L_0x009b
            r6 = r0
            char[] r6 = r6.buffer
            r7 = r4
            char r6 = r6[r7]
            r3 = r6
            r6 = r3
            r7 = 10
            if (r6 == r7) goto L_0x009b
            r6 = r3
            r7 = 13
            if (r6 != r7) goto L_0x00a8
        L_0x009b:
            r6 = r2
            r7 = r4
            r8 = r0
            int r8 = r8.pos
            int r7 = r7 - r8
            int r6 = r6 - r7
            r2 = r6
            r6 = r0
            r7 = r4
            r6.pos = r7
            goto L_0x0028
        L_0x00a8:
            int r4 = r4 + 1
            goto L_0x0086
        L_0x00ab:
            r6 = r1
            r0 = r6
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.skip(int):int");
    }

    public boolean ready() throws IOException {
        return this.pos < this.limit || this.f247in.ready();
    }

    public final void skip_quick() throws IOException {
        this.pos++;
    }

    public void skip() throws IOException {
        int read = read();
    }

    static int countLines(char[] cArr, int start, int i) {
        char[] buffer2 = cArr;
        int limit2 = i;
        int count = 0;
        char prev = 0;
        for (int i2 = start; i2 < limit2; i2++) {
            char ch = buffer2[i2];
            if ((ch == 10 && prev != 13) || ch == 13) {
                count++;
            }
            prev = ch;
        }
        return count;
    }

    public void skipRestOfLine() throws IOException {
        int c;
        do {
            c = read();
            if (c >= 0) {
                if (c == 13) {
                    int c2 = read();
                    if (c2 >= 0 && c2 != 10) {
                        unread();
                        return;
                    }
                    return;
                }
            } else {
                return;
            }
        } while (c != 10);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008a A[EDGE_INSN: B:29:0x008a->B:27:0x008a ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unread() throws java.io.IOException {
        /*
            r7 = this;
            r0 = r7
            r3 = r0
            int r3 = r3.pos
            if (r3 != 0) goto L_0x0012
            java.io.IOException r3 = new java.io.IOException
            r6 = r3
            r3 = r6
            r4 = r6
            java.lang.String r5 = "unread too much"
            r4.<init>(r5)
            throw r3
        L_0x0012:
            r3 = r0
            r6 = r3
            r3 = r6
            r4 = r6
            int r4 = r4.pos
            r5 = 1
            int r4 = r4 + -1
            r3.pos = r4
            r3 = r0
            char[] r3 = r3.buffer
            r4 = r0
            int r4 = r4.pos
            char r3 = r3[r4]
            r1 = r3
            r3 = r1
            r4 = 10
            if (r3 == r4) goto L_0x0030
            r3 = r1
            r4 = 13
            if (r3 != r4) goto L_0x008e
        L_0x0030:
            r3 = r0
            int r3 = r3.pos
            if (r3 <= 0) goto L_0x005b
            r3 = r1
            r4 = 10
            if (r3 != r4) goto L_0x005b
            r3 = r0
            boolean r3 = r3.getConvertCR()
            if (r3 == 0) goto L_0x005b
            r3 = r0
            char[] r3 = r3.buffer
            r4 = r0
            int r4 = r4.pos
            r5 = 1
            int r4 = r4 + -1
            char r3 = r3[r4]
            r4 = 13
            if (r3 != r4) goto L_0x005b
            r3 = r0
            r6 = r3
            r3 = r6
            r4 = r6
            int r4 = r4.pos
            r5 = 1
            int r4 = r4 + -1
            r3.pos = r4
        L_0x005b:
            r3 = r0
            int r3 = r3.pos
            r4 = r0
            int r4 = r4.lineStartPos
            if (r3 >= r4) goto L_0x008e
            r3 = r0
            r6 = r3
            r3 = r6
            r4 = r6
            int r4 = r4.lineNumber
            r5 = 1
            int r4 = r4 + -1
            r3.lineNumber = r4
            r3 = r0
            int r3 = r3.pos
            r2 = r3
        L_0x0072:
            r3 = r2
            if (r3 <= 0) goto L_0x008a
            r3 = r0
            char[] r3 = r3.buffer
            int r2 = r2 + -1
            r4 = r2
            char r3 = r3[r4]
            r1 = r3
            r3 = r1
            r4 = 13
            if (r3 == r4) goto L_0x0088
            r3 = r1
            r4 = 10
            if (r3 != r4) goto L_0x0072
        L_0x0088:
            int r2 = r2 + 1
        L_0x008a:
            r3 = r0
            r4 = r2
            r3.lineStartPos = r4
        L_0x008e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.unread():void");
    }

    public void unread_quick() {
        this.pos--;
    }

    public int peek() throws IOException {
        char ch;
        if (this.pos >= this.limit || this.pos <= 0 || (ch = this.buffer[this.pos - 1]) == 10 || ch == 13) {
            int c = read();
            if (c >= 0) {
                unread_quick();
            }
            return c;
        }
        char ch2 = this.buffer[this.pos];
        if (ch2 == 13 && getConvertCR()) {
            ch2 = 10;
        }
        return ch2;
    }
}
