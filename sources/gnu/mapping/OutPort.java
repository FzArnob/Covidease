package gnu.mapping;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PrintConsumer;
import gnu.text.Path;
import gnu.text.PrettyWriter;
import gnu.text.Printable;
import gnu.text.WriterManager;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.NumberFormat;

public class OutPort extends PrintConsumer implements Printable {
    private static OutPort errInitial;
    public static final ThreadLocation errLocation;
    static Writer logFile;
    static OutPort outInitial;
    public static final ThreadLocation outLocation;
    private Writer base;
    protected PrettyWriter bout;
    NumberFormat numberFormat;
    public AbstractFormat objectFormat;
    Path path;
    public boolean printReadable;
    protected Object unregisterRef;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected OutPort(java.io.Writer r8, gnu.text.PrettyWriter r9, boolean r10) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r0
            r5 = r2
            r6 = r3
            r4.<init>((java.io.Writer) r5, (boolean) r6)
            r4 = r0
            r5 = r2
            r4.bout = r5
            r4 = r0
            r5 = r1
            r4.base = r5
            r4 = r0
            boolean r4 = r4.closeOnExit()
            if (r4 == 0) goto L_0x0023
            r4 = r0
            gnu.text.WriterManager r5 = gnu.text.WriterManager.instance
            r6 = r2
            gnu.text.WriterRef r5 = r5.register(r6)
            r4.unregisterRef = r5
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.OutPort.<init>(java.io.Writer, gnu.text.PrettyWriter, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected OutPort(gnu.mapping.OutPort r8, boolean r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            r5 = r1
            gnu.text.PrettyWriter r5 = r5.bout
            r6 = r2
            r3.<init>((java.io.Writer) r4, (gnu.text.PrettyWriter) r5, (boolean) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.OutPort.<init>(gnu.mapping.OutPort, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected OutPort(java.io.Writer r11, boolean r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = r1
            r5 = r1
            boolean r5 = r5 instanceof gnu.mapping.OutPort
            if (r5 == 0) goto L_0x0014
            r5 = r1
            gnu.mapping.OutPort r5 = (gnu.mapping.OutPort) r5
            gnu.text.PrettyWriter r5 = r5.bout
        L_0x000f:
            r6 = r2
            r3.<init>((java.io.Writer) r4, (gnu.text.PrettyWriter) r5, (boolean) r6)
            return
        L_0x0014:
            gnu.text.PrettyWriter r5 = new gnu.text.PrettyWriter
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r1
            r8 = 1
            r6.<init>((java.io.Writer) r7, (boolean) r8)
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.OutPort.<init>(java.io.Writer, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OutPort(java.io.Writer r12, boolean r13, boolean r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            r5 = r1
            gnu.text.PrettyWriter r6 = new gnu.text.PrettyWriter
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r1
            r9 = r2
            r7.<init>((java.io.Writer) r8, (boolean) r9)
            r7 = r3
            r4.<init>((java.io.Writer) r5, (gnu.text.PrettyWriter) r6, (boolean) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.OutPort.<init>(java.io.Writer, boolean, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OutPort(java.io.Writer r13, boolean r14, boolean r15, gnu.text.Path r16) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r0
            r6 = r1
            gnu.text.PrettyWriter r7 = new gnu.text.PrettyWriter
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r1
            r10 = r2
            r8.<init>((java.io.Writer) r9, (boolean) r10)
            r8 = r3
            r5.<init>((java.io.Writer) r6, (gnu.text.PrettyWriter) r7, (boolean) r8)
            r5 = r0
            r6 = r4
            r5.path = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.OutPort.<init>(java.io.Writer, boolean, boolean, gnu.text.Path):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OutPort(OutputStream out) {
        this(out, (Path) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OutPort(java.io.OutputStream r9, gnu.text.Path r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>(r6)
            r5 = 1
            r6 = r2
            r3.<init>((java.io.Writer) r4, (boolean) r5, (gnu.text.Path) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.OutPort.<init>(java.io.OutputStream, gnu.text.Path):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OutPort(java.io.Writer r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            r4 = r1
            boolean r4 = r4 instanceof gnu.mapping.OutPort
            if (r4 == 0) goto L_0x0013
            r4 = r1
            gnu.mapping.OutPort r4 = (gnu.mapping.OutPort) r4
            gnu.text.PrettyWriter r4 = r4.bout
        L_0x000e:
            r5 = 0
            r2.<init>((java.io.Writer) r3, (gnu.text.PrettyWriter) r4, (boolean) r5)
            return
        L_0x0013:
            gnu.text.PrettyWriter r4 = new gnu.text.PrettyWriter
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r7 = 0
            r5.<init>((java.io.Writer) r6, (boolean) r7)
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.OutPort.<init>(java.io.Writer):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OutPort(Writer base2, Path path2) {
        this(base2, false, false);
        this.path = path2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OutPort(Writer base2, boolean autoflush, Path path2) {
        this(base2, false, autoflush);
        this.path = path2;
    }

    static {
        OutPort outPort;
        Writer writer;
        Writer writer2;
        Writer writer3;
        OutPort outPort2;
        Writer writer4;
        Writer writer5;
        ThreadLocation threadLocation;
        ThreadLocation threadLocation2;
        new OutputStreamWriter(System.out);
        new BufferedWriter(writer3);
        new LogWriter(writer2);
        new OutPort(writer, true, true, Path.valueOf("/dev/stdout"));
        outInitial = outPort;
        new OutputStreamWriter(System.err);
        new LogWriter(writer5);
        new OutPort(writer4, true, true, Path.valueOf("/dev/stderr"));
        errInitial = outPort2;
        new ThreadLocation("out-default");
        outLocation = threadLocation;
        outLocation.setGlobal(outInitial);
        new ThreadLocation("err-default");
        errLocation = threadLocation2;
        errLocation.setGlobal(errInitial);
    }

    public static OutPort outDefault() {
        return (OutPort) outLocation.get();
    }

    public static void setOutDefault(OutPort o) {
        outLocation.set(o);
    }

    public static OutPort errDefault() {
        return (OutPort) errLocation.get();
    }

    public static void setErrDefault(OutPort e) {
        errLocation.set(e);
    }

    public static OutPort openFile(Object fname) throws IOException {
        OutputStream outputStream;
        Writer wr;
        OutPort outPort;
        Writer writer;
        Writer writer2;
        Object conv = Environment.user().get((Object) "port-char-encoding");
        Path path2 = Path.valueOf(fname);
        new BufferedOutputStream(path2.openOutputStream());
        OutputStream strm = outputStream;
        if (conv == null || conv == Boolean.TRUE) {
            new OutputStreamWriter(strm);
            wr = writer;
        } else {
            if (conv == Boolean.FALSE) {
                conv = "8859_1";
            }
            new OutputStreamWriter(strm, conv.toString());
            wr = writer2;
        }
        new OutPort(wr, path2);
        return outPort;
    }

    public void echo(char[] cArr, int i, int i2) throws IOException {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (this.base instanceof LogWriter) {
            ((LogWriter) this.base).echo(buf, off, len);
        }
    }

    public static void closeLogFile() throws IOException {
        if (logFile != null) {
            logFile.close();
            logFile = null;
        }
        if (outInitial.base instanceof LogWriter) {
            ((LogWriter) outInitial.base).setLogFile((Writer) null);
        }
        if (errInitial.base instanceof LogWriter) {
            ((LogWriter) errInitial.base).setLogFile((Writer) null);
        }
    }

    public static void setLogFile(String str) throws IOException {
        Writer writer;
        Writer writer2;
        Writer writer3;
        String name = str;
        if (logFile != null) {
            closeLogFile();
        }
        new FileWriter(name);
        new BufferedWriter(writer3);
        new PrintWriter(writer2);
        logFile = writer;
        if (outInitial.base instanceof LogWriter) {
            ((LogWriter) outInitial.base).setLogFile(logFile);
        }
        if (errInitial.base instanceof LogWriter) {
            ((LogWriter) errInitial.base).setLogFile(logFile);
        }
    }

    protected static final boolean isWordChar(char c) {
        char ch = c;
        return Character.isJavaIdentifierPart(ch) || ch == '-' || ch == '+';
    }

    public void print(int i) {
        int v = i;
        if (this.numberFormat == null) {
            super.print(v);
        } else {
            print(this.numberFormat.format((long) v));
        }
    }

    public void print(long j) {
        long v = j;
        if (this.numberFormat == null) {
            super.print(v);
        } else {
            print(this.numberFormat.format(v));
        }
    }

    public void print(double d) {
        double v = d;
        if (this.numberFormat == null) {
            super.print(v);
        } else {
            print(this.numberFormat.format(v));
        }
    }

    public void print(float f) {
        float v = f;
        if (this.numberFormat == null) {
            super.print(v);
        } else {
            print(this.numberFormat.format((double) v));
        }
    }

    public void print(boolean z) {
        boolean v = z;
        if (this.objectFormat == null) {
            super.print(v);
        } else {
            this.objectFormat.writeBoolean(v, this);
        }
    }

    public void print(String str) {
        String v = str;
        write(v == null ? "(null)" : v);
    }

    public void print(Object obj) {
        Object v = obj;
        if (this.objectFormat != null) {
            this.objectFormat.writeObject(v, (PrintConsumer) this);
        } else if (v instanceof Consumable) {
            ((Consumable) v).consume(this);
        } else {
            super.print(v == null ? "null" : v);
        }
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<output-port");
        if (this.path != null) {
            out.write(32);
            out.write(this.path.toString());
        }
        out.write(62);
    }

    public void startElement(Object obj) {
        Object type = obj;
        if (this.objectFormat != null) {
            this.objectFormat.startElement(type, this);
            return;
        }
        print('(');
        print(type);
    }

    public void endElement() {
        if (this.objectFormat != null) {
            this.objectFormat.endElement(this);
        } else {
            print(')');
        }
    }

    public void startAttribute(Object obj) {
        Object attrType = obj;
        if (this.objectFormat != null) {
            this.objectFormat.startAttribute(attrType, this);
            return;
        }
        print(' ');
        print(attrType);
        print(": ");
    }

    public void endAttribute() {
        if (this.objectFormat != null) {
            this.objectFormat.endAttribute(this);
        } else {
            print(' ');
        }
    }

    public void writeWordEnd() {
        this.bout.writeWordEnd();
    }

    public void writeWordStart() {
        this.bout.writeWordStart();
    }

    public void freshLine() {
        if (this.bout.getColumnNumber() != 0) {
            println();
        }
    }

    public int getColumnNumber() {
        return this.bout.getColumnNumber();
    }

    public void setColumnNumber(int column) {
        this.bout.setColumnNumber(column);
    }

    public void clearBuffer() {
        this.bout.clearBuffer();
    }

    public void closeThis() {
        try {
            if (!(this.base instanceof OutPort) || ((OutPort) this.base).bout != this.bout) {
                this.bout.closeThis();
            }
        } catch (IOException e) {
            IOException iOException = e;
            setError();
        }
        WriterManager.instance.unregister(this.unregisterRef);
    }

    public void close() {
        try {
            if (!(this.base instanceof OutPort) || ((OutPort) this.base).bout != this.bout) {
                this.out.close();
                WriterManager.instance.unregister(this.unregisterRef);
            }
            this.base.close();
            WriterManager.instance.unregister(this.unregisterRef);
        } catch (IOException e) {
            IOException iOException = e;
            setError();
        }
    }

    /* access modifiers changed from: protected */
    public boolean closeOnExit() {
        return true;
    }

    public static void runCleanups() {
        WriterManager.instance.run();
    }

    public void startLogicalBlock(String prefix, boolean perLine, String suffix) {
        this.bout.startLogicalBlock(prefix, perLine, suffix);
    }

    public void startLogicalBlock(String str, String suffix, int i) {
        String prefix = str;
        int indent = i;
        this.bout.startLogicalBlock(prefix, false, suffix);
        this.bout.addIndentation(prefix == null ? indent : indent - prefix.length(), false);
    }

    public void endLogicalBlock(String suffix) {
        this.bout.endLogicalBlock(suffix);
    }

    public void writeBreak(int kind) {
        this.bout.writeBreak(kind);
    }

    public void writeSpaceLinear() {
        write(32);
        writeBreak(78);
    }

    public void writeBreakLinear() {
        writeBreak(78);
    }

    public void writeSpaceFill() {
        write(32);
        writeBreak(70);
    }

    public void writeBreakFill() {
        writeBreak(70);
    }

    public void setIndentation(int amount, boolean current) {
        this.bout.addIndentation(amount, current);
    }
}
