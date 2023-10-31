package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SourceError implements SourceLocator {
    public String code;
    public int column;
    public Throwable fakeException;
    public String filename;
    public int line;
    public String message;
    public SourceError next;
    public char severity;

    public SourceError(char severity2, String filename2, int line2, int column2, String message2) {
        this.severity = severity2;
        this.filename = filename2;
        this.line = line2;
        this.column = column2;
        this.message = message2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SourceError(char r11, gnu.text.SourceLocator r12, java.lang.String r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            r5 = r1
            r6 = r2
            java.lang.String r6 = r6.getFileName()
            r7 = r2
            int r7 = r7.getLineNumber()
            r8 = r2
            int r8 = r8.getColumnNumber()
            r9 = r3
            r4.<init>(r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.SourceError.<init>(char, gnu.text.SourceLocator, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SourceError(gnu.text.LineBufferedReader r12, char r13, java.lang.String r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            r5 = r2
            r6 = r1
            java.lang.String r6 = r6.getName()
            r7 = r1
            int r7 = r7.getLineNumber()
            r8 = 1
            int r7 = r7 + 1
            r8 = r1
            int r8 = r8.getColumnNumber()
            r9 = r3
            r4.<init>(r5, r6, r7, r8, r9)
            r4 = r0
            int r4 = r4.column
            if (r4 < 0) goto L_0x002c
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            int r5 = r5.column
            r6 = 1
            int r5 = r5 + 1
            r4.column = r5
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.SourceError.<init>(gnu.text.LineBufferedReader, char, java.lang.String):void");
    }

    public String toString() {
        StringBuffer stringBuffer;
        String str;
        new StringBuffer();
        StringBuffer buffer = stringBuffer;
        StringBuffer stringBuffer2 = buffer;
        if (this.filename == null) {
            str = "<unknown>";
        } else {
            str = this.filename;
        }
        StringBuffer append = stringBuffer2.append(str);
        if (this.line > 0 || this.column > 0) {
            StringBuffer append2 = buffer.append(':');
            StringBuffer append3 = buffer.append(this.line);
            if (this.column > 0) {
                StringBuffer append4 = buffer.append(':');
                StringBuffer append5 = buffer.append(this.column);
            }
        }
        StringBuffer append6 = buffer.append(": ");
        if (this.severity == 'w') {
            StringBuffer append7 = buffer.append("warning - ");
        }
        StringBuffer append8 = buffer.append(this.message);
        if (this.code != null) {
            StringBuffer append9 = buffer.append(" [");
            StringBuffer append10 = buffer.append(this.code);
            StringBuffer append11 = buffer.append("]");
        }
        if (this.fakeException != null) {
            StackTraceElement[] stackTrace = this.fakeException.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                StringBuffer append12 = buffer.append("\n");
                StringBuffer append13 = buffer.append("    ");
                StringBuffer append14 = buffer.append(stackTrace[i].toString());
            }
        }
        return buffer.toString();
    }

    public void print(PrintWriter out) {
        out.print(this);
    }

    public void println(PrintWriter printWriter) {
        PrintWriter out = printWriter;
        String sourceError = toString();
        while (true) {
            String line2 = sourceError;
            int nl = line2.indexOf(10);
            if (nl < 0) {
                out.println(line2);
                return;
            } else {
                out.println(line2.substring(0, nl));
                sourceError = line2.substring(nl + 1);
            }
        }
    }

    public void println(PrintStream printStream) {
        PrintStream out = printStream;
        String sourceError = toString();
        while (true) {
            String line2 = sourceError;
            int nl = line2.indexOf(10);
            if (nl < 0) {
                out.println(line2);
                return;
            } else {
                out.println(line2.substring(0, nl));
                sourceError = line2.substring(nl + 1);
            }
        }
    }

    public int getLineNumber() {
        return this.line == 0 ? -1 : this.line;
    }

    public int getColumnNumber() {
        return this.column == 0 ? -1 : this.column;
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return this.filename;
    }

    public String getFileName() {
        return this.filename;
    }

    public boolean isStableSourceLocation() {
        return true;
    }
}
