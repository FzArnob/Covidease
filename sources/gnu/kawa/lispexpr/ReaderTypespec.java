package gnu.kawa.lispexpr;

public class ReaderTypespec extends ReadTableEntry {
    public ReaderTypespec() {
    }

    public int getKind() {
        return 6;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b6, code lost:
        if (1 != 1) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        if (0 != 0) goto L_0x00d1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object read(gnu.text.Lexer r24, int r25, int r26) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r15 = r3
            int r15 = r15.tokenBufferLength
            r6 = r15
            r15 = r3
            gnu.text.LineBufferedReader r15 = r15.getPort()
            r7 = r15
            gnu.kawa.lispexpr.ReadTable r15 = gnu.kawa.lispexpr.ReadTable.getCurrent()
            r8 = r15
            r15 = 0
            r9 = r15
            r15 = r3
            r16 = r4
            r15.tokenBufferAppend(r16)
            r15 = r4
            r10 = r15
            r15 = r7
            boolean r15 = r15 instanceof gnu.mapping.InPort
            if (r15 == 0) goto L_0x003a
            r15 = r7
            gnu.mapping.InPort r15 = (gnu.mapping.InPort) r15
            char r15 = r15.readState
            r9 = r15
            r15 = r7
            gnu.mapping.InPort r15 = (gnu.mapping.InPort) r15
            r16 = r4
            r0 = r16
            char r0 = (char) r0
            r16 = r0
            r0 = r16
            r15.readState = r0
        L_0x003a:
            r15 = 0
            r12 = r15
        L_0x003c:
            r15 = r10
            r11 = r15
            r15 = r7
            int r15 = r15.pos     // Catch:{ all -> 0x012e }
            r16 = r7
            r0 = r16
            int r0 = r0.limit     // Catch:{ all -> 0x012e }
            r16 = r0
            r0 = r16
            if (r15 >= r0) goto L_0x0091
            r15 = r11
            r16 = 10
            r0 = r16
            if (r15 == r0) goto L_0x0091
            r15 = r7
            char[] r15 = r15.buffer     // Catch:{ all -> 0x012e }
            r16 = r7
            r21 = r16
            r16 = r21
            r17 = r21
            r0 = r17
            int r0 = r0.pos     // Catch:{ all -> 0x012e }
            r17 = r0
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            r18 = r22
            r19 = 1
            int r18 = r18 + 1
            r0 = r18
            r1 = r17
            r1.pos = r0     // Catch:{ all -> 0x012e }
            char r15 = r15[r16]     // Catch:{ all -> 0x012e }
            r10 = r15
        L_0x007c:
            r15 = r10
            r16 = 92
            r0 = r16
            if (r15 != r0) goto L_0x009f
            r15 = r3
            boolean r15 = r15 instanceof gnu.kawa.lispexpr.LispReader     // Catch:{ all -> 0x012e }
            if (r15 == 0) goto L_0x0098
            r15 = r3
            gnu.kawa.lispexpr.LispReader r15 = (gnu.kawa.lispexpr.LispReader) r15     // Catch:{ all -> 0x012e }
            int r15 = r15.readEscape()     // Catch:{ all -> 0x012e }
            r10 = r15
            goto L_0x003c
        L_0x0091:
            r15 = r7
            int r15 = r15.read()     // Catch:{ all -> 0x012e }
            r10 = r15
            goto L_0x007c
        L_0x0098:
            r15 = r7
            int r15 = r15.read()     // Catch:{ all -> 0x012e }
            r10 = r15
            goto L_0x003c
        L_0x009f:
            r15 = r12
            if (r15 != 0) goto L_0x00b8
            r15 = r10
            r16 = 91
            r0 = r16
            if (r15 != r0) goto L_0x00b8
            r15 = 1
            r16 = 1
            r21 = r16
            r16 = r21
            r17 = r21
            r12 = r17
            r0 = r16
            if (r15 == r0) goto L_0x00e2
        L_0x00b8:
            r15 = r12
            if (r15 == 0) goto L_0x00d1
            r15 = r10
            r16 = 93
            r0 = r16
            if (r15 != r0) goto L_0x00d1
            r15 = 0
            r16 = 0
            r21 = r16
            r16 = r21
            r17 = r21
            r12 = r17
            r0 = r16
            if (r15 == r0) goto L_0x00e2
        L_0x00d1:
            r15 = r8
            r16 = r10
            gnu.kawa.lispexpr.ReadTableEntry r15 = r15.lookup(r16)     // Catch:{ all -> 0x012e }
            int r15 = r15.getKind()     // Catch:{ all -> 0x012e }
            r16 = 2
            r0 = r16
            if (r15 != r0) goto L_0x00ea
        L_0x00e2:
            r15 = r3
            r16 = r10
            r15.tokenBufferAppend(r16)     // Catch:{ all -> 0x012e }
            goto L_0x003c
        L_0x00ea:
            r15 = r3
            r16 = r10
            r15.unread(r16)     // Catch:{ all -> 0x012e }
            java.lang.String r15 = new java.lang.String     // Catch:{ all -> 0x012e }
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = r3
            r0 = r17
            char[] r0 = r0.tokenBuffer     // Catch:{ all -> 0x012e }
            r17 = r0
            r18 = r6
            r19 = r3
            r0 = r19
            int r0 = r0.tokenBufferLength     // Catch:{ all -> 0x012e }
            r19 = r0
            r20 = r6
            int r19 = r19 - r20
            r16.<init>(r17, r18, r19)     // Catch:{ all -> 0x012e }
            java.lang.String r15 = r15.intern()     // Catch:{ all -> 0x012e }
            r13 = r15
            r15 = r3
            r16 = r6
            r0 = r16
            r15.tokenBufferLength = r0
            r15 = r7
            boolean r15 = r15 instanceof gnu.mapping.InPort
            if (r15 == 0) goto L_0x012b
            r15 = r7
            gnu.mapping.InPort r15 = (gnu.mapping.InPort) r15
            r16 = r9
            r0 = r16
            r15.readState = r0
        L_0x012b:
            r15 = r13
            r2 = r15
            return r2
        L_0x012e:
            r15 = move-exception
            r14 = r15
            r15 = r3
            r16 = r6
            r0 = r16
            r15.tokenBufferLength = r0
            r15 = r7
            boolean r15 = r15 instanceof gnu.mapping.InPort
            if (r15 == 0) goto L_0x0145
            r15 = r7
            gnu.mapping.InPort r15 = (gnu.mapping.InPort) r15
            r16 = r9
            r0 = r16
            r15.readState = r0
        L_0x0145:
            r15 = r14
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderTypespec.read(gnu.text.Lexer, int, int):java.lang.Object");
    }
}
