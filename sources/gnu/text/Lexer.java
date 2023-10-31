package gnu.text;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class Lexer extends Reader {
    protected boolean interactive;
    SourceMessages messages = null;
    protected int nesting;
    protected LineBufferedReader port;
    private int saveTokenBufferLength = -1;
    public char[] tokenBuffer = new char[100];
    public int tokenBufferLength = 0;

    public Lexer(LineBufferedReader port2) {
        this.port = port2;
    }

    public Lexer(LineBufferedReader port2, SourceMessages messages2) {
        this.port = port2;
        this.messages = messages2;
    }

    public char pushNesting(char promptChar) {
        this.nesting++;
        LineBufferedReader port2 = getPort();
        char save = port2.readState;
        port2.readState = promptChar;
        return save;
    }

    public void popNesting(char save) {
        getPort().readState = save;
        this.nesting--;
    }

    public final LineBufferedReader getPort() {
        return this.port;
    }

    public void close() throws IOException {
        this.port.close();
    }

    public int read() throws IOException {
        return this.port.read();
    }

    public int readUnicodeChar() throws IOException {
        int next;
        int c = this.port.read();
        if (c >= 55296 && c < 56319 && (next = this.port.read()) >= 56320 && next <= 57343) {
            c = ((c - 55296) << 10) + (c - 56320) + 65536;
        }
        return c;
    }

    public int read(char[] buf, int offset, int length) throws IOException {
        return this.port.read(buf, offset, length);
    }

    public void unread(int ch) throws IOException {
        if (ch >= 0) {
            this.port.unread();
        }
    }

    public int peek() throws IOException {
        return this.port.peek();
    }

    public void skip() throws IOException {
        this.port.skip();
    }

    /* access modifiers changed from: protected */
    public void unread() throws IOException {
        this.port.unread();
    }

    /* access modifiers changed from: protected */
    public void unread_quick() throws IOException {
        this.port.unread_quick();
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean checkNext(char r6) throws java.io.IOException {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            gnu.text.LineBufferedReader r3 = r3.port
            int r3 = r3.read()
            r2 = r3
            r3 = r2
            r4 = r1
            if (r3 != r4) goto L_0x0011
            r3 = 1
            r0 = r3
        L_0x0010:
            return r0
        L_0x0011:
            r3 = r2
            if (r3 < 0) goto L_0x001a
            r3 = r0
            gnu.text.LineBufferedReader r3 = r3.port
            r3.unread_quick()
        L_0x001a:
            r3 = 0
            r0 = r3
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Lexer.checkNext(char):boolean");
    }

    /* access modifiers changed from: protected */
    public void skip_quick() throws IOException {
        this.port.skip_quick();
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void setMessages(SourceMessages messages2) {
        SourceMessages sourceMessages = messages2;
        this.messages = sourceMessages;
    }

    public boolean checkErrors(PrintWriter out, int max) {
        return this.messages != null && this.messages.checkErrors(out, max);
    }

    public SourceError getErrors() {
        return this.messages == null ? null : this.messages.getErrors();
    }

    public boolean seenErrors() {
        return this.messages != null && this.messages.seenErrors();
    }

    public void clearErrors() {
        if (this.messages != null) {
            this.messages.clearErrors();
        }
    }

    public void error(char c, String str, int i, int i2, String str2) {
        SourceMessages sourceMessages;
        char severity = c;
        String filename = str;
        int line = i;
        int column = i2;
        String message = str2;
        if (this.messages == null) {
            new SourceMessages();
            this.messages = sourceMessages;
        }
        this.messages.error(severity, filename, line, column, message);
    }

    public void error(char severity, String str) {
        String message = str;
        int line = this.port.getLineNumber();
        int column = this.port.getColumnNumber();
        error(severity, this.port.getName(), line + 1, column >= 0 ? column + 1 : 0, message);
    }

    public void error(String message) {
        error('e', message);
    }

    public void fatal(String message) throws SyntaxException {
        Throwable th;
        error('f', message);
        Throwable th2 = th;
        new SyntaxException(this.messages);
        throw th2;
    }

    public void eofError(String msg) throws SyntaxException {
        fatal(msg);
    }

    public void eofError(String message, int startLine, int startColumn) throws SyntaxException {
        Throwable th;
        error('f', this.port.getName(), startLine, startColumn, message);
        Throwable th2 = th;
        new SyntaxException(this.messages);
        throw th2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int readOptionalExponent() throws java.io.IOException {
        /*
            r10 = this;
            r0 = r10
            r7 = r0
            int r7 = r7.read()
            r1 = r7
            r7 = 0
            r2 = r7
            r7 = r1
            r8 = 43
            if (r7 == r8) goto L_0x0013
            r7 = r1
            r8 = 45
            if (r7 != r8) goto L_0x0052
        L_0x0013:
            r7 = r0
            int r7 = r7.read()
            r3 = r7
        L_0x0019:
            r7 = r3
            if (r7 < 0) goto L_0x002a
            r7 = r3
            char r7 = (char) r7
            r8 = 10
            int r7 = java.lang.Character.digit(r7, r8)
            r9 = r7
            r7 = r9
            r8 = r9
            r4 = r8
            if (r7 >= 0) goto L_0x0057
        L_0x002a:
            r7 = r1
            if (r7 == 0) goto L_0x0034
            r7 = r0
            java.lang.String r8 = "exponent sign not followed by digit"
            r7.error(r8)
        L_0x0034:
            r7 = 1
            r4 = r7
        L_0x0036:
            r7 = r3
            if (r7 < 0) goto L_0x003e
            r7 = r0
            r8 = r3
            r7.unread(r8)
        L_0x003e:
            r7 = r1
            r8 = 45
            if (r7 != r8) goto L_0x0046
            r7 = r4
            int r7 = -r7
            r4 = r7
        L_0x0046:
            r7 = r2
            if (r7 == 0) goto L_0x0080
            r7 = r1
            r8 = 45
            if (r7 != r8) goto L_0x007c
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x0050:
            r0 = r7
        L_0x0051:
            return r0
        L_0x0052:
            r7 = r1
            r3 = r7
            r7 = 0
            r1 = r7
            goto L_0x0019
        L_0x0057:
            r7 = 214748363(0xccccccb, float:3.1554432E-31)
            r5 = r7
        L_0x005b:
            r7 = r0
            int r7 = r7.read()
            r3 = r7
            r7 = r3
            char r7 = (char) r7
            r8 = 10
            int r7 = java.lang.Character.digit(r7, r8)
            r6 = r7
            r7 = r6
            if (r7 >= 0) goto L_0x006e
            goto L_0x0036
        L_0x006e:
            r7 = r4
            r8 = r5
            if (r7 <= r8) goto L_0x0074
            r7 = 1
            r2 = r7
        L_0x0074:
            r7 = 10
            r8 = r4
            int r7 = r7 * r8
            r8 = r6
            int r7 = r7 + r8
            r4 = r7
            goto L_0x005b
        L_0x007c:
            r7 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0050
        L_0x0080:
            r7 = r4
            r0 = r7
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Lexer.readOptionalExponent():int");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean readDelimited(java.lang.String r12) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r7 = r0
            r8 = 0
            r7.tokenBufferLength = r8
            r7 = r1
            int r7 = r7.length()
            r2 = r7
            r7 = r1
            r8 = r2
            r9 = 1
            int r8 = r8 + -1
            char r7 = r7.charAt(r8)
            r3 = r7
        L_0x0016:
            r7 = r0
            int r7 = r7.read()
            r4 = r7
            r7 = r4
            if (r7 >= 0) goto L_0x0022
            r7 = 0
            r0 = r7
        L_0x0021:
            return r0
        L_0x0022:
            r7 = r4
            r8 = r3
            if (r7 != r8) goto L_0x0054
            r7 = r0
            int r7 = r7.tokenBufferLength
            r8 = r2
            r9 = 1
            int r8 = r8 + -1
            r10 = r8
            r8 = r10
            r9 = r10
            r6 = r9
            int r7 = r7 - r8
            r10 = r7
            r7 = r10
            r8 = r10
            r5 = r8
            if (r7 < 0) goto L_0x0054
        L_0x0038:
            r7 = r6
            if (r7 != 0) goto L_0x0042
            r7 = r0
            r8 = r5
            r7.tokenBufferLength = r8
            r7 = 1
            r0 = r7
            goto L_0x0021
        L_0x0042:
            int r6 = r6 + -1
            r7 = r0
            char[] r7 = r7.tokenBuffer
            r8 = r5
            r9 = r6
            int r8 = r8 + r9
            char r7 = r7[r8]
            r8 = r1
            r9 = r6
            char r8 = r8.charAt(r9)
            if (r7 == r8) goto L_0x0038
        L_0x0054:
            r7 = r0
            r8 = r4
            char r8 = (char) r8
            r7.tokenBufferAppend(r8)
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Lexer.readDelimited(java.lang.String):boolean");
    }

    public static long readDigitsInBuffer(LineBufferedReader lineBufferedReader, int i) {
        long j;
        LineBufferedReader port2 = lineBufferedReader;
        int radix = i;
        long ival = 0;
        boolean overflow = false;
        long max_val = Long.MAX_VALUE / ((long) radix);
        int i2 = port2.pos;
        if (i2 >= port2.limit) {
            return 0;
        }
        do {
            int dval = Character.digit(port2.buffer[i2], radix);
            if (dval < 0) {
                break;
            }
            if (ival > max_val) {
                overflow = true;
            } else {
                ival = (ival * ((long) radix)) + ((long) dval);
            }
            if (ival < 0) {
                overflow = true;
            }
            i2++;
        } while (i2 < port2.limit);
        port2.pos = i2;
        if (overflow) {
            j = -1;
        } else {
            j = ival;
        }
        return j;
    }

    public String getName() {
        return this.port.getName();
    }

    public int getLineNumber() {
        return this.port.getLineNumber();
    }

    public int getColumnNumber() {
        return this.port.getColumnNumber();
    }

    public boolean isInteractive() {
        return this.interactive;
    }

    public void setInteractive(boolean v) {
        boolean z = v;
        this.interactive = z;
    }

    public void tokenBufferAppend(int i) {
        int ch = i;
        if (ch >= 65536) {
            tokenBufferAppend(((ch - 65536) >> 10) + 55296);
            ch = (ch & 1023) + 56320;
        }
        int len = this.tokenBufferLength;
        char[] buffer = this.tokenBuffer;
        if (len == this.tokenBuffer.length) {
            this.tokenBuffer = new char[(2 * len)];
            System.arraycopy(buffer, 0, this.tokenBuffer, 0, len);
            buffer = this.tokenBuffer;
        }
        buffer[len] = (char) ch;
        this.tokenBufferLength = len + 1;
    }

    public String tokenBufferString() {
        String str;
        new String(this.tokenBuffer, 0, this.tokenBufferLength);
        return str;
    }

    public void mark() throws IOException {
        Throwable th;
        if (this.saveTokenBufferLength >= 0) {
            Throwable th2 = th;
            new Error("internal error: recursive call to mark not allowed");
            throw th2;
        }
        this.port.mark(Integer.MAX_VALUE);
        this.saveTokenBufferLength = this.tokenBufferLength;
    }

    public void reset() throws IOException {
        Throwable th;
        if (this.saveTokenBufferLength < 0) {
            Throwable th2 = th;
            new Error("internal error: reset called without prior mark");
            throw th2;
        }
        this.port.reset();
        this.saveTokenBufferLength = -1;
    }
}
