package kawa.standard;

import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0or1;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class readchar extends Procedure0or1 {
    public static final readchar peekChar;
    public static final readchar readChar;
    boolean peeking;

    static {
        readchar readchar;
        readchar readchar2;
        new readchar(false);
        readChar = readchar;
        new readchar(true);
        peekChar = readchar2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public readchar(boolean r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            if (r3 == 0) goto L_0x0011
            java.lang.String r3 = "peek-char"
        L_0x0009:
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.peeking = r3
            return
        L_0x0011:
            java.lang.String r3 = "read-char"
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.readchar.<init>(boolean):void");
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(InPort inPort) {
        Throwable th;
        InPort port = inPort;
        try {
            int ch = this.peeking ? port.peek() : port.read();
            if (ch < 0) {
                return Sequence.eofValue;
            }
            return Char.make(ch);
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new RuntimeException("IO Exception caught");
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(Reader reader) {
        Throwable th;
        int ch;
        Reader port = reader;
        try {
            if (this.peeking) {
                port.mark(1);
                ch = port.read();
                port.reset();
            } else {
                ch = port.read();
            }
            if (ch < 0) {
                return Sequence.eofValue;
            }
            return Char.make(ch);
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new RuntimeException("IO Exception caught");
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(InputStream inputStream) {
        Throwable th;
        int ch;
        InputStream port = inputStream;
        try {
            if (this.peeking) {
                port.mark(1);
                ch = port.read();
                port.reset();
            } else {
                ch = port.read();
            }
            if (ch < 0) {
                return Sequence.eofValue;
            }
            return Char.make(ch);
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new RuntimeException("IO Exception caught");
            throw th2;
        }
    }

    public final Object apply0() {
        return readChar(InPort.inDefault());
    }

    public final Object apply1(Object obj) {
        Throwable th;
        Object arg1 = obj;
        if (arg1 instanceof InPort) {
            return readChar((InPort) arg1);
        }
        if (arg1 instanceof Reader) {
            return readChar((Reader) arg1);
        }
        if (arg1 instanceof InputStream) {
            return readChar((InputStream) arg1);
        }
        Throwable th2 = th;
        new WrongType((Procedure) this, 1, arg1, "<input-port>");
        throw th2;
    }
}
