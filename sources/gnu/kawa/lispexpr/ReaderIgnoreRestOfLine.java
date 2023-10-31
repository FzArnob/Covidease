package gnu.kawa.lispexpr;

public class ReaderIgnoreRestOfLine extends ReadTableEntry {
    static ReaderIgnoreRestOfLine instance;

    public ReaderIgnoreRestOfLine() {
    }

    static {
        ReaderIgnoreRestOfLine readerIgnoreRestOfLine;
        new ReaderIgnoreRestOfLine();
        instance = readerIgnoreRestOfLine;
    }

    public static ReaderIgnoreRestOfLine getInstance() {
        return instance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x000d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0011  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object read(gnu.text.Lexer r7, int r8, int r9) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
        L_0x0004:
            r4 = r1
            int r4 = r4.read()
            r2 = r4
            r4 = r2
            if (r4 >= 0) goto L_0x0011
            java.lang.Object r4 = gnu.lists.Sequence.eofValue
            r0 = r4
        L_0x0010:
            return r0
        L_0x0011:
            r4 = r2
            r5 = 10
            if (r4 == r5) goto L_0x001b
            r4 = r2
            r5 = 13
            if (r4 != r5) goto L_0x0004
        L_0x001b:
            gnu.mapping.Values r4 = gnu.mapping.Values.empty
            r0 = r4
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderIgnoreRestOfLine.read(gnu.text.Lexer, int, int):java.lang.Object");
    }
}
