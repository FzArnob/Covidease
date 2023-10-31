package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderVector extends ReadTableEntry {
    char close;

    public ReaderVector(char close2) {
        this.close = close2;
    }

    public Object read(Lexer lexer, int i, int count) throws IOException, SyntaxException {
        Lexer in = lexer;
        int i2 = i;
        return readVector((LispReader) in, in.getPort(), count, this.close);
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: gnu.mapping.Values} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.lists.FVector readVector(gnu.kawa.lispexpr.LispReader r17, gnu.text.LineBufferedReader r18, int r19, char r20) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r13 = 32
            r4 = r13
            r13 = r1
            boolean r13 = r13 instanceof gnu.mapping.InPort
            if (r13 == 0) goto L_0x0022
            r13 = r1
            gnu.mapping.InPort r13 = (gnu.mapping.InPort) r13
            char r13 = r13.readState
            r4 = r13
            r13 = r1
            gnu.mapping.InPort r13 = (gnu.mapping.InPort) r13
            r14 = r3
            r15 = 93
            if (r14 != r15) goto L_0x006f
            r14 = 91
        L_0x0020:
            r13.readState = r14
        L_0x0022:
            java.util.Vector r13 = new java.util.Vector     // Catch:{ all -> 0x00aa }
            r16 = r13
            r13 = r16
            r14 = r16
            r14.<init>()     // Catch:{ all -> 0x00aa }
            r5 = r13
            gnu.kawa.lispexpr.ReadTable r13 = gnu.kawa.lispexpr.ReadTable.getCurrent()     // Catch:{ all -> 0x00aa }
            r6 = r13
        L_0x0033:
            r13 = r0
            int r13 = r13.read()     // Catch:{ all -> 0x00aa }
            r7 = r13
            r13 = r7
            if (r13 >= 0) goto L_0x0043
            r13 = r0
            java.lang.String r14 = "unexpected EOF in vector"
            r13.eofError(r14)     // Catch:{ all -> 0x00aa }
        L_0x0043:
            r13 = r7
            r14 = r3
            if (r13 != r14) goto L_0x0072
            r13 = r5
            int r13 = r13.size()     // Catch:{ all -> 0x00aa }
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ all -> 0x00aa }
            r7 = r13
            r13 = r5
            r14 = r7
            r13.copyInto(r14)     // Catch:{ all -> 0x00aa }
            gnu.lists.FVector r13 = new gnu.lists.FVector     // Catch:{ all -> 0x00aa }
            r16 = r13
            r13 = r16
            r14 = r16
            r15 = r7
            r14.<init>((java.lang.Object[]) r15)     // Catch:{ all -> 0x00aa }
            r8 = r13
            r13 = r1
            boolean r13 = r13 instanceof gnu.mapping.InPort
            if (r13 == 0) goto L_0x006c
            r13 = r1
            gnu.mapping.InPort r13 = (gnu.mapping.InPort) r13
            r14 = r4
            r13.readState = r14
        L_0x006c:
            r13 = r8
            r0 = r13
            return r0
        L_0x006f:
            r14 = 40
            goto L_0x0020
        L_0x0072:
            r13 = r0
            r14 = r7
            r15 = r6
            java.lang.Object r13 = r13.readValues(r14, r15)     // Catch:{ all -> 0x00aa }
            r8 = r13
            r13 = r8
            boolean r13 = r13 instanceof gnu.mapping.Values     // Catch:{ all -> 0x00aa }
            if (r13 == 0) goto L_0x009c
            r13 = r8
            gnu.mapping.Values r13 = (gnu.mapping.Values) r13     // Catch:{ all -> 0x00aa }
            java.lang.Object[] r13 = r13.getValues()     // Catch:{ all -> 0x00aa }
            r9 = r13
            r13 = r9
            int r13 = r13.length     // Catch:{ all -> 0x00aa }
            r10 = r13
            r13 = 0
            r11 = r13
        L_0x008c:
            r13 = r11
            r14 = r10
            if (r13 >= r14) goto L_0x009b
            r13 = r5
            r14 = r9
            r15 = r11
            r14 = r14[r15]     // Catch:{ all -> 0x00aa }
            r13.addElement(r14)     // Catch:{ all -> 0x00aa }
            int r11 = r11 + 1
            goto L_0x008c
        L_0x009b:
            goto L_0x0033
        L_0x009c:
            r13 = r8
            gnu.expr.QuoteExp r14 = gnu.expr.QuoteExp.voidExp     // Catch:{ all -> 0x00aa }
            if (r13 != r14) goto L_0x00a4
            gnu.mapping.Values r13 = gnu.mapping.Values.empty     // Catch:{ all -> 0x00aa }
            r8 = r13
        L_0x00a4:
            r13 = r5
            r14 = r8
            r13.addElement(r14)     // Catch:{ all -> 0x00aa }
            goto L_0x009b
        L_0x00aa:
            r13 = move-exception
            r12 = r13
            r13 = r1
            boolean r13 = r13 instanceof gnu.mapping.InPort
            if (r13 == 0) goto L_0x00b7
            r13 = r1
            gnu.mapping.InPort r13 = (gnu.mapping.InPort) r13
            r14 = r4
            r13.readState = r14
        L_0x00b7:
            r13 = r12
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderVector.readVector(gnu.kawa.lispexpr.LispReader, gnu.text.LineBufferedReader, int, char):gnu.lists.FVector");
    }
}
