package gnu.text;

import gnu.lists.CharSeq;
import java.io.Reader;

public class QueueReader extends Reader implements Appendable {
    boolean EOFseen;
    char[] buffer;
    int limit;
    int mark;
    int pos;
    int readAheadLimit;

    public QueueReader() {
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized void mark(int i) {
        int readAheadLimit2 = i;
        synchronized (this) {
            this.readAheadLimit = readAheadLimit2;
            this.mark = this.pos;
        }
    }

    public synchronized void reset() {
        synchronized (this) {
            if (this.readAheadLimit > 0) {
                this.pos = this.mark;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resize(int i) {
        int len = i;
        int cur_size = this.limit - this.pos;
        if (this.readAheadLimit <= 0 || this.pos - this.mark > this.readAheadLimit) {
            this.mark = this.pos;
        } else {
            cur_size = this.limit - this.mark;
        }
        char[] new_buffer = this.buffer.length < cur_size + len ? new char[((2 * cur_size) + len)] : this.buffer;
        System.arraycopy(this.buffer, this.mark, new_buffer, 0, cur_size);
        this.buffer = new_buffer;
        this.pos -= this.mark;
        this.mark = 0;
        this.limit = cur_size;
    }

    public QueueReader append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (csq == null) {
            csq = "null";
        }
        return append(csq, 0, csq.length());
    }

    public synchronized QueueReader append(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        synchronized (this) {
            if (csq == null) {
                csq = "null";
            }
            int len = end - start;
            reserveSpace(len);
            int sz = this.limit;
            char[] d = this.buffer;
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
            this.limit = sz + len;
            notifyAll();
        }
        return this;
    }

    public void append(char[] cArr) {
        char[] chars = cArr;
        append(chars, 0, chars.length);
    }

    public synchronized void append(char[] cArr, int i, int i2) {
        char[] chars = cArr;
        int off = i;
        int len = i2;
        synchronized (this) {
            reserveSpace(len);
            System.arraycopy(chars, off, this.buffer, this.limit, len);
            this.limit += len;
            notifyAll();
        }
    }

    public synchronized QueueReader append(char c) {
        char ch = c;
        synchronized (this) {
            reserveSpace(1);
            char[] cArr = this.buffer;
            int i = this.limit;
            int i2 = i + 1;
            this.limit = i2;
            cArr[i] = ch;
            notifyAll();
        }
        return this;
    }

    public synchronized void appendEOF() {
        synchronized (this) {
            this.EOFseen = true;
        }
    }

    /* access modifiers changed from: protected */
    public void reserveSpace(int i) {
        int len = i;
        if (this.buffer == null) {
            this.buffer = new char[(100 + len)];
        } else if (this.buffer.length < this.limit + len) {
            resize(len);
        }
    }

    public synchronized boolean ready() {
        boolean z;
        synchronized (this) {
            z = this.pos < this.limit || this.EOFseen;
        }
        return z;
    }

    public void checkAvailable() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2 = r0.buffer;
        r3 = r0;
        r8 = r3;
        r9 = r8.pos;
        r5 = r9 + 1;
        r8.pos = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        r0 = r2[r9];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read() {
        /*
            r10 = this;
            r0 = r10
            r7 = r10
            monitor-enter(r7)
        L_0x0003:
            r2 = r0
            int r2 = r2.pos     // Catch:{ all -> 0x0039 }
            r3 = r0
            int r3 = r3.limit     // Catch:{ all -> 0x0039 }
            if (r2 < r3) goto L_0x0020
            r2 = r0
            boolean r2 = r2.EOFseen     // Catch:{ all -> 0x0039 }
            if (r2 == 0) goto L_0x0014
            r2 = -1
            r0 = r2
        L_0x0012:
            monitor-exit(r7)
            return r0
        L_0x0014:
            r2 = r0
            r2.checkAvailable()     // Catch:{ all -> 0x0039 }
            r2 = r0
            r2.wait()     // Catch:{ InterruptedException -> 0x001d }
            goto L_0x0003
        L_0x001d:
            r2 = move-exception
            r1 = r2
            goto L_0x0003
        L_0x0020:
            r2 = r0
            char[] r2 = r2.buffer     // Catch:{ all -> 0x0039 }
            r3 = r0
            r8 = r3
            r3 = r8
            r4 = r8
            int r4 = r4.pos     // Catch:{ all -> 0x0039 }
            r8 = r3
            r9 = r4
            r3 = r9
            r4 = r8
            r5 = r9
            r6 = 1
            int r5 = r5 + 1
            r4.pos = r5     // Catch:{ all -> 0x0039 }
            char r2 = r2[r3]     // Catch:{ all -> 0x0039 }
            r1 = r2
            r2 = r1
            r0 = r2
            goto L_0x0012
        L_0x0039:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.QueueReader.read():int");
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 133 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(char[] r13, int r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r10 = r12
            monitor-enter(r10)
            r5 = r3
            if (r5 != 0) goto L_0x0015
            r5 = 0
            r0 = r5
        L_0x000b:
            monitor-exit(r10)
            return r0
        L_0x000d:
            r5 = r0
            r5.checkAvailable()     // Catch:{ all -> 0x004f }
            r5 = r0
            r5.wait()     // Catch:{ InterruptedException -> 0x0025 }
        L_0x0015:
            r5 = r0
            int r5 = r5.pos     // Catch:{ all -> 0x004f }
            r6 = r0
            int r6 = r6.limit     // Catch:{ all -> 0x004f }
            if (r5 < r6) goto L_0x0028
            r5 = r0
            boolean r5 = r5.EOFseen     // Catch:{ all -> 0x004f }
            if (r5 == 0) goto L_0x000d
            r5 = -1
            r0 = r5
            goto L_0x000b
        L_0x0025:
            r5 = move-exception
            r4 = r5
            goto L_0x0015
        L_0x0028:
            r5 = r0
            int r5 = r5.limit     // Catch:{ all -> 0x004f }
            r6 = r0
            int r6 = r6.pos     // Catch:{ all -> 0x004f }
            int r5 = r5 - r6
            r4 = r5
            r5 = r3
            r6 = r4
            if (r5 <= r6) goto L_0x0036
            r5 = r4
            r3 = r5
        L_0x0036:
            r5 = r0
            char[] r5 = r5.buffer     // Catch:{ all -> 0x004f }
            r6 = r0
            int r6 = r6.pos     // Catch:{ all -> 0x004f }
            r7 = r1
            r8 = r2
            r9 = r3
            java.lang.System.arraycopy(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x004f }
            r5 = r0
            r11 = r5
            r5 = r11
            r6 = r11
            int r6 = r6.pos     // Catch:{ all -> 0x004f }
            r7 = r3
            int r6 = r6 + r7
            r5.pos = r6     // Catch:{ all -> 0x004f }
            r5 = r3
            r0 = r5
            goto L_0x000b
        L_0x004f:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.QueueReader.read(char[], int, int):int");
    }

    public synchronized void close() {
        synchronized (this) {
            this.pos = 0;
            this.limit = 0;
            this.mark = 0;
            this.EOFseen = true;
            this.buffer = null;
        }
    }
}
