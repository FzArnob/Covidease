package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public abstract class ReadTableEntry {
    public static final ReadTableEntry brace;
    public static final ReadTableEntry constituent;
    public static final ReadTableEntry illegal;
    public static final ReadTableEntry multipleEscape;
    public static final ReadTableEntry singleEscape;
    public static final ReadTableEntry whitespace;

    public ReadTableEntry() {
    }

    static {
        ReadTableEntry readTableEntry;
        ReadTableEntry readTableEntry2;
        ReadTableEntry readTableEntry3;
        ReadTableEntry readTableEntry4;
        ReadTableEntry readTableEntry5;
        ReadTableEntry readTableEntry6;
        new ReaderMisc(0);
        illegal = readTableEntry;
        new ReaderMisc(1);
        whitespace = readTableEntry2;
        new ReaderMisc(3);
        singleEscape = readTableEntry3;
        new ReaderMisc(4);
        multipleEscape = readTableEntry4;
        new ReaderMisc(2);
        constituent = readTableEntry5;
        new ReaderMisc(2);
        brace = readTableEntry6;
    }

    public static ReadTableEntry getIllegalInstance() {
        return illegal;
    }

    public static ReadTableEntry getWhitespaceInstance() {
        return whitespace;
    }

    public static ReadTableEntry getSingleEscapeInstance() {
        return singleEscape;
    }

    public static ReadTableEntry getMultipleEscapeInstance() {
        return multipleEscape;
    }

    public static ReadTableEntry getDigitInstance() {
        return constituent;
    }

    public static ReadTableEntry getConstituentInstance() {
        return constituent;
    }

    public int getKind() {
        return 5;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        Throwable th;
        Lexer lexer2 = lexer;
        int i3 = i;
        int i4 = i2;
        Throwable th2 = th;
        new Error("invalid character");
        throw th2;
    }
}
