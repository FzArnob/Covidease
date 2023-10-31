package gnu.mapping;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.Printable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class InPort extends LineBufferedReader implements Printable {
    public static final ThreadLocation inLocation;
    private static InPort systemInPort;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InPort(Reader in) {
        super(in);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InPort(Reader in, Path path) {
        this(in);
        setPath(path);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InPort(InputStream in) {
        super(in);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InPort(InputStream in, Path path) {
        this(in);
        setPath(path);
    }

    public static Reader convertToReader(InputStream inputStream, Object obj) {
        Reader reader;
        Throwable th;
        StringBuilder sb;
        Reader reader2;
        InputStream in = inputStream;
        Object conv = obj;
        if (conv == null || conv == Boolean.TRUE) {
            new InputStreamReader(in);
            return reader;
        }
        String enc = conv == Boolean.FALSE ? "8859_1" : conv.toString();
        try {
            Reader reader3 = reader2;
            new InputStreamReader(in, enc);
            return reader3;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("unknown character encoding: ").append(enc).toString());
            throw th2;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InPort(java.io.InputStream r9, gnu.text.Path r10, java.lang.Object r11) throws java.io.UnsupportedEncodingException {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r5 = r0
            r6 = r1
            r7 = r3
            java.io.Reader r6 = convertToReader(r6, r7)
            r7 = r2
            r5.<init>((java.io.Reader) r6, (gnu.text.Path) r7)
            r5 = r3
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 != r6) goto L_0x0020
            r5 = r0
            r6 = 2048(0x800, float:2.87E-42)
            char[] r6 = new char[r6]     // Catch:{ IOException -> 0x001d }
            r5.setBuffer(r6)     // Catch:{ IOException -> 0x001d }
        L_0x001c:
            return
        L_0x001d:
            r5 = move-exception
            r4 = r5
            goto L_0x001c
        L_0x0020:
            r5 = r0
            r6 = 1
            r5.setConvertCR(r6)
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.InPort.<init>(java.io.InputStream, gnu.text.Path, java.lang.Object):void");
    }

    static {
        InPort inPort;
        ThreadLocation threadLocation;
        new TtyInPort(System.in, Path.valueOf("/dev/stdin"), OutPort.outInitial);
        systemInPort = inPort;
        new ThreadLocation("in-default");
        inLocation = threadLocation;
        inLocation.setGlobal(systemInPort);
    }

    public static InPort inDefault() {
        return (InPort) inLocation.get();
    }

    public static void setInDefault(InPort in) {
        inLocation.set(in);
    }

    public static InPort openFile(Object fname) throws IOException {
        InputStream strm;
        Path path = Path.valueOf(fname);
        new BufferedInputStream(path.openInputStream());
        return openFile(strm, path);
    }

    public static InPort openFile(InputStream strm, Object fname) throws UnsupportedEncodingException {
        InPort inPort;
        new InPort(strm, Path.valueOf(fname), Environment.user().get((Object) "port-char-encoding"));
        return inPort;
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<input-port");
        String name = getName();
        if (name != null) {
            out.write(32);
            out.write(name);
        }
        out.write(62);
    }
}
