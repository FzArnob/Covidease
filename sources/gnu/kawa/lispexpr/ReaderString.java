package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderString extends ReadTableEntry {
    public ReaderString() {
    }

    public Object read(Lexer in, int ch, int count) throws IOException, SyntaxException {
        return readString(in, ch, count);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readString(gnu.text.Lexer r18, int r19, int r20) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            r10 = r0
            int r10 = r10.tokenBufferLength
            r3 = r10
            r10 = r0
            gnu.text.LineBufferedReader r10 = r10.getPort()
            r4 = r10
            r10 = 0
            r5 = r10
            r10 = r1
            r6 = r10
            r10 = r4
            boolean r10 = r10 instanceof gnu.mapping.InPort
            if (r10 == 0) goto L_0x0026
            r10 = r4
            gnu.mapping.InPort r10 = (gnu.mapping.InPort) r10
            char r10 = r10.readState
            r5 = r10
            r10 = r4
            gnu.mapping.InPort r10 = (gnu.mapping.InPort) r10
            r11 = r1
            char r11 = (char) r11
            r10.readState = r11
        L_0x0026:
            r10 = r6
            r7 = r10
            r10 = r7
            r11 = 13
            if (r10 != r11) goto L_0x0039
            r10 = r4
            int r10 = r10.read()     // Catch:{ all -> 0x00e4 }
            r6 = r10
            r10 = r6
            r11 = 10
            if (r10 != r11) goto L_0x0064
            goto L_0x0026
        L_0x0039:
            r10 = r4
            int r10 = r10.pos     // Catch:{ all -> 0x00e4 }
            r11 = r4
            int r11 = r11.limit     // Catch:{ all -> 0x00e4 }
            if (r10 >= r11) goto L_0x0093
            r10 = r7
            r11 = 10
            if (r10 == r11) goto L_0x0093
            r10 = r4
            char[] r10 = r10.buffer     // Catch:{ all -> 0x00e4 }
            r11 = r4
            r16 = r11
            r11 = r16
            r12 = r16
            int r12 = r12.pos     // Catch:{ all -> 0x00e4 }
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            r13 = r17
            r14 = 1
            int r13 = r13 + 1
            r12.pos = r13     // Catch:{ all -> 0x00e4 }
            char r10 = r10[r11]     // Catch:{ all -> 0x00e4 }
            r6 = r10
        L_0x0064:
            r10 = r6
            r11 = r1
            if (r10 != r11) goto L_0x009a
            java.lang.String r10 = new java.lang.String     // Catch:{ all -> 0x00e4 }
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = r0
            char[] r12 = r12.tokenBuffer     // Catch:{ all -> 0x00e4 }
            r13 = r3
            r14 = r0
            int r14 = r14.tokenBufferLength     // Catch:{ all -> 0x00e4 }
            r15 = r3
            int r14 = r14 - r15
            r11.<init>(r12, r13, r14)     // Catch:{ all -> 0x00e4 }
            java.lang.String r10 = r10.intern()     // Catch:{ all -> 0x00e4 }
            r8 = r10
            r10 = r0
            r11 = r3
            r10.tokenBufferLength = r11
            r10 = r4
            boolean r10 = r10 instanceof gnu.mapping.InPort
            if (r10 == 0) goto L_0x0090
            r10 = r4
            gnu.mapping.InPort r10 = (gnu.mapping.InPort) r10
            r11 = r5
            r10.readState = r11
        L_0x0090:
            r10 = r8
            r0 = r10
            return r0
        L_0x0093:
            r10 = r4
            int r10 = r10.read()     // Catch:{ all -> 0x00e4 }
            r6 = r10
            goto L_0x0064
        L_0x009a:
            r10 = r6
            switch(r10) {
                case 13: goto L_0x00af;
                case 92: goto L_0x00c7;
                default: goto L_0x009e;
            }     // Catch:{ all -> 0x00e4 }
        L_0x009e:
            r10 = r6
            if (r10 >= 0) goto L_0x00a8
            r10 = r0
            java.lang.String r11 = "unexpected EOF in string literal"
            r10.eofError(r11)     // Catch:{ all -> 0x00e4 }
        L_0x00a8:
            r10 = r0
            r11 = r6
            r10.tokenBufferAppend(r11)     // Catch:{ all -> 0x00e4 }
            goto L_0x0026
        L_0x00af:
            r10 = r4
            boolean r10 = r10.getConvertCR()     // Catch:{ all -> 0x00e4 }
            if (r10 == 0) goto L_0x00c0
            r10 = 10
            r8 = r10
        L_0x00b9:
            r10 = r0
            r11 = r8
            r10.tokenBufferAppend(r11)     // Catch:{ all -> 0x00e4 }
            goto L_0x0026
        L_0x00c0:
            r10 = 13
            r8 = r10
            r10 = 32
            r6 = r10
            goto L_0x00b9
        L_0x00c7:
            r10 = r0
            boolean r10 = r10 instanceof gnu.kawa.lispexpr.LispReader     // Catch:{ all -> 0x00e4 }
            if (r10 == 0) goto L_0x00dd
            r10 = r0
            gnu.kawa.lispexpr.LispReader r10 = (gnu.kawa.lispexpr.LispReader) r10     // Catch:{ all -> 0x00e4 }
            int r10 = r10.readEscape()     // Catch:{ all -> 0x00e4 }
            r6 = r10
        L_0x00d4:
            r10 = r6
            r11 = -2
            if (r10 != r11) goto L_0x009e
            r10 = 10
            r6 = r10
            goto L_0x0026
        L_0x00dd:
            r10 = r4
            int r10 = r10.read()     // Catch:{ all -> 0x00e4 }
            r6 = r10
            goto L_0x00d4
        L_0x00e4:
            r10 = move-exception
            r9 = r10
            r10 = r0
            r11 = r3
            r10.tokenBufferLength = r11
            r10 = r4
            boolean r10 = r10 instanceof gnu.mapping.InPort
            if (r10 == 0) goto L_0x00f5
            r10 = r4
            gnu.mapping.InPort r10 = (gnu.mapping.InPort) r10
            r11 = r5
            r10.readState = r11
        L_0x00f5:
            r10 = r9
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderString.readString(gnu.text.Lexer, int, int):java.lang.String");
    }
}
