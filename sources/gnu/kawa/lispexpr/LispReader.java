package gnu.kawa.lispexpr;

import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;

public class LispReader extends Lexer {
    static final int SCM_COMPLEX = 1;
    public static final int SCM_NUMBERS = 1;
    public static final char TOKEN_ESCAPE_CHAR = '￿';
    protected boolean seenEscapes;
    GeneralHashTable<Integer, Object> sharedStructureTable;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LispReader(LineBufferedReader port) {
        super(port);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LispReader(LineBufferedReader port, SourceMessages messages) {
        super(port, messages);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void readNestedComment(char r14, char r15) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r7 = 1
            r3 = r7
            r7 = r0
            gnu.text.LineBufferedReader r7 = r7.port
            int r7 = r7.getLineNumber()
            r4 = r7
            r7 = r0
            gnu.text.LineBufferedReader r7 = r7.port
            int r7 = r7.getColumnNumber()
            r5 = r7
        L_0x0015:
            r7 = r0
            int r7 = r7.read()
            r6 = r7
            r7 = r6
            r8 = 124(0x7c, float:1.74E-43)
            if (r7 != r8) goto L_0x0060
            r7 = r0
            int r7 = r7.read()
            r6 = r7
            r7 = r6
            r8 = r1
            if (r7 != r8) goto L_0x002c
            int r3 = r3 + -1
        L_0x002c:
            r7 = r6
            if (r7 >= 0) goto L_0x0071
            r7 = r0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r12 = r8
            r8 = r12
            r9 = r12
            r9.<init>()
            java.lang.String r9 = "unexpected end-of-file in "
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r1
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r2
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = " comment starting here"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r9 = r4
            r10 = 1
            int r9 = r9 + 1
            r10 = r5
            r11 = 1
            int r10 = r10 + -1
            r7.eofError(r8, r9, r10)
        L_0x005f:
            return
        L_0x0060:
            r7 = r6
            r8 = r1
            if (r7 != r8) goto L_0x002c
            r7 = r0
            int r7 = r7.read()
            r6 = r7
            r7 = r6
            r8 = r2
            if (r7 != r8) goto L_0x002c
            int r3 = r3 + 1
            goto L_0x002c
        L_0x0071:
            r7 = r3
            if (r7 > 0) goto L_0x0015
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readNestedComment(char, char):void");
    }

    static char getReadCase() {
        char read_case;
        try {
            read_case = Environment.getCurrent().get("symbol-read-case", (Object) "P").toString().charAt(0);
            if (read_case != 'P') {
                if (read_case == 'u') {
                    read_case = 'U';
                } else if (read_case == 'd' || read_case == 'l' || read_case == 'L') {
                    read_case = 'D';
                } else if (read_case == 'i') {
                    read_case = 'I';
                }
            }
        } catch (Exception e) {
            Exception exc = e;
            read_case = 'P';
        }
        return read_case;
    }

    public Object readValues(int i, ReadTable readTable) throws IOException, SyntaxException {
        int ch = i;
        ReadTable rtable = readTable;
        return readValues(ch, rtable.lookup(ch), rtable);
    }

    public Object readValues(int i, ReadTableEntry readTableEntry, ReadTable readTable) throws IOException, SyntaxException {
        StringBuilder sb;
        int ch = i;
        ReadTableEntry entry = readTableEntry;
        ReadTable rtable = readTable;
        int startPos = this.tokenBufferLength;
        this.seenEscapes = false;
        switch (entry.getKind()) {
            case 0:
                new StringBuilder();
                String err = sb.append("invalid character #\\").append((char) ch).toString();
                if (this.interactive) {
                    fatal(err);
                } else {
                    error(err);
                }
                return Values.empty;
            case 1:
                return Values.empty;
            case 5:
            case 6:
                return entry.read(this, ch, -1);
            default:
                return readAndHandleToken(ch, startPos, rtable);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0339, code lost:
        if (r2.seenEscapes != false) goto L_0x033b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02c4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readAndHandleToken(int r27, int r28, gnu.kawa.lispexpr.ReadTable r29) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r26 = this;
            r2 = r26
            r3 = r27
            r4 = r28
            r5 = r29
            r19 = r2
            r20 = r3
            char r21 = getReadCase()
            r22 = r5
            r19.readToken(r20, r21, r22)
            r19 = r2
            r0 = r19
            int r0 = r0.tokenBufferLength
            r19 = r0
            r6 = r19
            r19 = r2
            r0 = r19
            boolean r0 = r0.seenEscapes
            r19 = r0
            if (r19 != 0) goto L_0x0058
            r19 = r2
            r0 = r19
            char[] r0 = r0.tokenBuffer
            r19 = r0
            r20 = r4
            r21 = r6
            r22 = r4
            int r21 = r21 - r22
            r22 = 0
            r23 = 0
            r24 = 1
            java.lang.Object r19 = parseNumber(r19, r20, r21, r22, r23, r24)
            r7 = r19
            r19 = r7
            if (r19 == 0) goto L_0x0058
            r19 = r7
            r0 = r19
            boolean r0 = r0 instanceof java.lang.String
            r19 = r0
            if (r19 != 0) goto L_0x0058
            r19 = r7
            r2 = r19
        L_0x0057:
            return r2
        L_0x0058:
            char r19 = getReadCase()
            r7 = r19
            r19 = r7
            r20 = 73
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x00ba
            r19 = 0
            r8 = r19
            r19 = 0
            r9 = r19
            r19 = r4
            r10 = r19
        L_0x0074:
            r19 = r10
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x00b2
            r19 = r2
            r0 = r19
            char[] r0 = r0.tokenBuffer
            r19 = r0
            r20 = r10
            char r19 = r19[r20]
            r11 = r19
            r19 = r11
            r20 = 65535(0xffff, float:9.1834E-41)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x009c
            int r10 = r10 + 1
        L_0x0099:
            int r10 = r10 + 1
            goto L_0x0074
        L_0x009c:
            r19 = r11
            boolean r19 = java.lang.Character.isLowerCase(r19)
            if (r19 == 0) goto L_0x00a7
            int r9 = r9 + 1
            goto L_0x0099
        L_0x00a7:
            r19 = r11
            boolean r19 = java.lang.Character.isUpperCase(r19)
            if (r19 == 0) goto L_0x0099
            int r8 = r8 + 1
            goto L_0x0099
        L_0x00b2:
            r19 = r9
            if (r19 != 0) goto L_0x0173
            r19 = 68
            r7 = r19
        L_0x00ba:
            r19 = r6
            r20 = r4
            r21 = 2
            int r20 = r20 + 2
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0183
            r19 = r2
            r0 = r19
            char[] r0 = r0.tokenBuffer
            r19 = r0
            r20 = r6
            r21 = 1
            int r20 = r20 + -1
            char r19 = r19[r20]
            r20 = 125(0x7d, float:1.75E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0183
            r19 = r2
            r0 = r19
            char[] r0 = r0.tokenBuffer
            r19 = r0
            r20 = r6
            r21 = 2
            int r20 = r20 + -2
            char r19 = r19[r20]
            r20 = 65535(0xffff, float:9.1834E-41)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0183
            r19 = r2
            int r19 = r19.peek()
            r20 = 58
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0183
            r19 = 1
        L_0x0109:
            r8 = r19
            r19 = -1
            r9 = r19
            r19 = -1
            r10 = r19
            r19 = -1
            r11 = r19
            r19 = 0
            r12 = r19
            r19 = r4
            r13 = r19
            r19 = 0
            r14 = r19
            r19 = r4
            r15 = r19
        L_0x0127:
            r19 = r15
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x021f
            r19 = r2
            r0 = r19
            char[] r0 = r0.tokenBuffer
            r19 = r0
            r20 = r15
            char r19 = r19[r20]
            r16 = r19
            r19 = r16
            r20 = 65535(0xffff, float:9.1834E-41)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0186
            int r15 = r15 + 1
            r19 = r15
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0170
            r19 = r2
            r0 = r19
            char[] r0 = r0.tokenBuffer
            r19 = r0
            r20 = r13
            int r13 = r13 + 1
            r21 = r2
            r0 = r21
            char[] r0 = r0.tokenBuffer
            r21 = r0
            r22 = r15
            char r21 = r21[r22]
            r19[r20] = r21
        L_0x0170:
            int r15 = r15 + 1
            goto L_0x0127
        L_0x0173:
            r19 = r8
            if (r19 != 0) goto L_0x017d
            r19 = 85
            r7 = r19
            goto L_0x00ba
        L_0x017d:
            r19 = 80
            r7 = r19
            goto L_0x00ba
        L_0x0183:
            r19 = 0
            goto L_0x0109
        L_0x0186:
            r19 = r8
            if (r19 == 0) goto L_0x019e
            r19 = r16
            r20 = 123(0x7b, float:1.72E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x01bc
            r19 = r10
            if (r19 >= 0) goto L_0x01b3
            r19 = r13
            r10 = r19
        L_0x019c:
            int r12 = r12 + 1
        L_0x019e:
            r19 = r12
            if (r19 <= 0) goto L_0x01e3
        L_0x01a2:
            r19 = r2
            r0 = r19
            char[] r0 = r0.tokenBuffer
            r19 = r0
            r20 = r13
            int r13 = r13 + 1
            r21 = r16
            r19[r20] = r21
            goto L_0x0170
        L_0x01b3:
            r19 = r12
            if (r19 != 0) goto L_0x019c
            r19 = 1
            r14 = r19
            goto L_0x019c
        L_0x01bc:
            r19 = r16
            r20 = 125(0x7d, float:1.75E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x019e
            int r12 = r12 + -1
            r19 = r12
            if (r19 >= 0) goto L_0x01d1
            r19 = 1
            r14 = r19
            goto L_0x019e
        L_0x01d1:
            r19 = r12
            if (r19 != 0) goto L_0x019e
            r19 = r11
            if (r19 >= 0) goto L_0x01de
            r19 = r13
            r11 = r19
            goto L_0x019e
        L_0x01de:
            r19 = 1
            r14 = r19
            goto L_0x019e
        L_0x01e3:
            r19 = r16
            r20 = 58
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x01f9
            r19 = r9
            if (r19 < 0) goto L_0x01f6
            r19 = -1
        L_0x01f3:
            r9 = r19
            goto L_0x01a2
        L_0x01f6:
            r19 = r13
            goto L_0x01f3
        L_0x01f9:
            r19 = r7
            r20 = 85
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x020c
            r19 = r16
            char r19 = java.lang.Character.toUpperCase(r19)
            r16 = r19
            goto L_0x01a2
        L_0x020c:
            r19 = r7
            r20 = 68
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x01a2
            r19 = r16
            char r19 = java.lang.Character.toLowerCase(r19)
            r16 = r19
            goto L_0x01a2
        L_0x021f:
            r19 = r13
            r6 = r19
            r19 = r6
            r20 = r4
            int r19 = r19 - r20
            r15 = r19
            r19 = r10
            if (r19 < 0) goto L_0x02c4
            r19 = r11
            r20 = r10
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x02c4
            r19 = r10
            if (r19 <= 0) goto L_0x02c1
            java.lang.String r19 = new java.lang.String
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = r2
            r0 = r21
            char[] r0 = r0.tokenBuffer
            r21 = r0
            r22 = r4
            r23 = r10
            r24 = r4
            int r23 = r23 - r24
            r20.<init>(r21, r22, r23)
        L_0x0258:
            r16 = r19
            int r10 = r10 + 1
            java.lang.String r19 = new java.lang.String
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = r2
            r0 = r21
            char[] r0 = r0.tokenBuffer
            r21 = r0
            r22 = r10
            r23 = r11
            r24 = r10
            int r23 = r23 - r24
            r20.<init>(r21, r22, r23)
            r17 = r19
            r19 = r2
            int r19 = r19.read()
            r3 = r19
            r19 = r2
            int r19 = r19.read()
            r3 = r19
            r19 = r2
            r20 = r3
            r21 = r5
            r22 = r3
            gnu.kawa.lispexpr.ReadTableEntry r21 = r21.lookup(r22)
            r22 = r5
            java.lang.Object r19 = r19.readValues(r20, r21, r22)
            r18 = r19
            r19 = r18
            r0 = r19
            boolean r0 = r0 instanceof gnu.mapping.SimpleSymbol
            r19 = r0
            if (r19 != 0) goto L_0x02af
            r19 = r2
            java.lang.String r20 = "expected identifier in symbol after '{URI}:'"
            r19.error(r20)
        L_0x02af:
            r19 = r18
            java.lang.String r19 = r19.toString()
            r20 = r17
            r21 = r16
            gnu.mapping.Symbol r19 = gnu.mapping.Symbol.valueOf(r19, r20, r21)
            r2 = r19
            goto L_0x0057
        L_0x02c1:
            r19 = 0
            goto L_0x0258
        L_0x02c4:
            r19 = r5
            r0 = r19
            boolean r0 = r0.initialColonIsKeyword
            r19 = r0
            if (r19 == 0) goto L_0x030f
            r19 = r9
            r20 = r4
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x030f
            r19 = r15
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x030f
            int r4 = r4 + 1
            java.lang.String r19 = new java.lang.String
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = r2
            r0 = r21
            char[] r0 = r0.tokenBuffer
            r21 = r0
            r22 = r4
            r23 = r6
            r24 = r4
            int r23 = r23 - r24
            r20.<init>(r21, r22, r23)
            r16 = r19
            r19 = r16
            java.lang.String r19 = r19.intern()
            gnu.expr.Keyword r19 = gnu.expr.Keyword.make(r19)
            r2 = r19
            goto L_0x0057
        L_0x030f:
            r19 = r5
            r0 = r19
            boolean r0 = r0.finalColonIsKeyword
            r19 = r0
            if (r19 == 0) goto L_0x0366
            r19 = r9
            r20 = r6
            r21 = 1
            int r20 = r20 + -1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0366
            r19 = r15
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 > r1) goto L_0x033b
            r19 = r2
            r0 = r19
            boolean r0 = r0.seenEscapes
            r19 = r0
            if (r19 == 0) goto L_0x0366
        L_0x033b:
            java.lang.String r19 = new java.lang.String
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = r2
            r0 = r21
            char[] r0 = r0.tokenBuffer
            r21 = r0
            r22 = r4
            r23 = r15
            r24 = 1
            int r23 = r23 + -1
            r20.<init>(r21, r22, r23)
            r16 = r19
            r19 = r16
            java.lang.String r19 = r19.intern()
            gnu.expr.Keyword r19 = gnu.expr.Keyword.make(r19)
            r2 = r19
            goto L_0x0057
        L_0x0366:
            r19 = r5
            java.lang.String r20 = new java.lang.String
            r25 = r20
            r20 = r25
            r21 = r25
            r22 = r2
            r0 = r22
            char[] r0 = r0.tokenBuffer
            r22 = r0
            r23 = r4
            r24 = r15
            r21.<init>(r22, r23, r24)
            java.lang.Object r19 = r19.makeSymbol(r20)
            r2 = r19
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readAndHandleToken(int, int, gnu.kawa.lispexpr.ReadTable):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public void readToken(int i, char c, ReadTable readTable) throws IOException, SyntaxException {
        int ch = i;
        char c2 = c;
        ReadTable rtable = readTable;
        boolean inEscapes = false;
        int braceNesting = 0;
        while (true) {
            if (ch < 0) {
                if (inEscapes) {
                    eofError("unexpected EOF between escapes");
                } else {
                    return;
                }
            }
            ReadTableEntry entry = rtable.lookup(ch);
            int kind = entry.getKind();
            if (kind != 0) {
                if (ch == rtable.postfixLookupOperator && !inEscapes) {
                    int next = this.port.peek();
                    if (next == rtable.postfixLookupOperator) {
                        unread(ch);
                        return;
                    } else if (validPostfixLookupStart(next, rtable)) {
                        kind = 5;
                    }
                }
                if (kind == 3) {
                    int ch2 = read();
                    if (ch2 < 0) {
                        eofError("unexpected EOF after single escape");
                    }
                    if (rtable.hexEscapeAfterBackslash && (ch2 == 120 || ch2 == 88)) {
                        ch2 = readHexEscape();
                    }
                    tokenBufferAppend(65535);
                    tokenBufferAppend(ch2);
                    this.seenEscapes = true;
                } else if (kind == 4) {
                    inEscapes = !inEscapes;
                    this.seenEscapes = true;
                } else if (inEscapes) {
                    tokenBufferAppend(65535);
                    tokenBufferAppend(ch);
                } else {
                    switch (kind) {
                        case 1:
                            unread(ch);
                            return;
                        case 2:
                            if (ch == 123 && entry == ReadTableEntry.brace) {
                                braceNesting++;
                                break;
                            }
                        case 4:
                            inEscapes = true;
                            this.seenEscapes = true;
                            continue;
                        case 5:
                            unread(ch);
                            return;
                        case 6:
                            break;
                    }
                    tokenBufferAppend(ch);
                }
            } else if (inEscapes) {
                tokenBufferAppend(65535);
                tokenBufferAppend(ch);
            } else if (ch == 125) {
                braceNesting--;
                if (braceNesting >= 0) {
                    tokenBufferAppend(ch);
                }
            }
            ch = read();
        }
        unread(ch);
    }

    /* JADX INFO: finally extract failed */
    public Object readObject() throws IOException, SyntaxException {
        char saveReadState = ((InPort) this.port).readState;
        int startPos = this.tokenBufferLength;
        ((InPort) this.port).readState = ' ';
        try {
            ReadTable rtable = ReadTable.getCurrent();
            while (true) {
                int line = this.port.getLineNumber();
                int column = this.port.getColumnNumber();
                int ch = this.port.read();
                if (ch < 0) {
                    Object obj = Sequence.eofValue;
                    this.tokenBufferLength = startPos;
                    ((InPort) this.port).readState = saveReadState;
                    return obj;
                }
                Object value = readValues(ch, rtable);
                if (value != Values.empty) {
                    Object handlePostfix = handlePostfix(value, rtable, line, column);
                    this.tokenBufferLength = startPos;
                    ((InPort) this.port).readState = saveReadState;
                    return handlePostfix;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            this.tokenBufferLength = startPos;
            ((InPort) this.port).readState = saveReadState;
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public boolean validPostfixLookupStart(int i, ReadTable readTable) throws IOException {
        int ch = i;
        ReadTable rtable = readTable;
        if (ch < 0 || ch == 58 || ch == rtable.postfixLookupOperator) {
            return false;
        }
        if (ch == 44) {
            return true;
        }
        int kind = rtable.lookup(ch).getKind();
        return kind == 2 || kind == 6 || kind == 4 || kind == 3;
    }

    /* access modifiers changed from: package-private */
    public Object handlePostfix(Object obj, ReadTable readTable, int i, int i2) throws IOException, SyntaxException {
        Object value = obj;
        ReadTable rtable = readTable;
        int line = i;
        int column = i2;
        if (value == QuoteExp.voidExp) {
            value = Values.empty;
        }
        while (true) {
            int ch = this.port.peek();
            if (ch < 0 || ch != rtable.postfixLookupOperator) {
                break;
            }
            int read = this.port.read();
            if (!validPostfixLookupStart(this.port.peek(), rtable)) {
                unread();
                break;
            }
            int ch2 = this.port.read();
            value = PairWithPosition.make(LispLanguage.lookup_sym, LList.list2(value, LList.list2(rtable.makeSymbol(LispLanguage.quasiquote_sym), readValues(ch2, rtable.lookup(ch2), rtable))), this.port.getName(), line + 1, column + 1);
        }
        return value;
    }

    private boolean isPotentialNumber(char[] cArr, int i, int i2) {
        char[] buffer = cArr;
        int start = i;
        int end = i2;
        int sawDigits = 0;
        for (int i3 = start; i3 < end; i3++) {
            char ch = buffer[i3];
            if (Character.isDigit(ch)) {
                sawDigits++;
            } else if (ch == '-' || ch == '+') {
                if (i3 + 1 == end) {
                    return false;
                }
            } else if (ch == '#') {
                return true;
            } else {
                if (Character.isLetter(ch) || ch == '/' || ch == '_' || ch == '^') {
                    if (i3 == start) {
                        return false;
                    }
                } else if (ch != '.') {
                    return false;
                }
            }
        }
        return sawDigits > 0;
    }

    public static Object parseNumber(CharSequence charSequence, int i) {
        char[] buf;
        CharSequence str = charSequence;
        int radix = i;
        if (str instanceof FString) {
            buf = ((FString) str).data;
        } else {
            buf = str.toString().toCharArray();
        }
        return parseNumber(buf, 0, str.length(), 0, radix, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:412:?, code lost:
        return "fraction symbol '/' following exponent or '.'";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object parseNumber(char[] r40, int r41, int r42, char r43, int r44, int r45) {
        /*
            r3 = r40
            r4 = r41
            r5 = r42
            r6 = r43
            r7 = r44
            r8 = r45
            r32 = r4
            r33 = r5
            int r32 = r32 + r33
            r9 = r32
            r32 = r4
            r10 = r32
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 < r1) goto L_0x0028
            java.lang.String r32 = "no digits"
            r3 = r32
        L_0x0027:
            return r3
        L_0x0028:
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
        L_0x0032:
            r32 = r11
            r33 = 35
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0177
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 < r1) goto L_0x004c
            java.lang.String r32 = "no digits"
            r3 = r32
            goto L_0x0027
        L_0x004c:
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
            r32 = r11
            switch(r32) {
                case 66: goto L_0x008b;
                case 68: goto L_0x00ba;
                case 69: goto L_0x00da;
                case 73: goto L_0x00da;
                case 79: goto L_0x00aa;
                case 88: goto L_0x00ca;
                case 98: goto L_0x008b;
                case 100: goto L_0x00ba;
                case 101: goto L_0x00da;
                case 105: goto L_0x00da;
                case 111: goto L_0x00aa;
                case 120: goto L_0x00ca;
                default: goto L_0x005b;
            }
        L_0x005b:
            r32 = 0
            r12 = r32
        L_0x005f:
            r32 = r11
            r33 = 10
            int r32 = java.lang.Character.digit(r32, r33)
            r13 = r32
            r32 = r13
            if (r32 >= 0) goto L_0x00fb
            r32 = r11
            r33 = 82
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0081
            r32 = r11
            r33 = 114(0x72, float:1.6E-43)
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0145
        L_0x0081:
            r32 = r7
            if (r32 == 0) goto L_0x0124
            java.lang.String r32 = "duplicate radix specifier"
            r3 = r32
            goto L_0x0027
        L_0x008b:
            r32 = r7
            if (r32 == 0) goto L_0x0095
            java.lang.String r32 = "duplicate radix specifier"
            r3 = r32
            goto L_0x0027
        L_0x0095:
            r32 = 2
            r7 = r32
        L_0x0099:
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 < r1) goto L_0x016b
            java.lang.String r32 = "no digits"
            r3 = r32
            goto L_0x0027
        L_0x00aa:
            r32 = r7
            if (r32 == 0) goto L_0x00b5
            java.lang.String r32 = "duplicate radix specifier"
            r3 = r32
            goto L_0x0027
        L_0x00b5:
            r32 = 8
            r7 = r32
            goto L_0x0099
        L_0x00ba:
            r32 = r7
            if (r32 == 0) goto L_0x00c5
            java.lang.String r32 = "duplicate radix specifier"
            r3 = r32
            goto L_0x0027
        L_0x00c5:
            r32 = 10
            r7 = r32
            goto L_0x0099
        L_0x00ca:
            r32 = r7
            if (r32 == 0) goto L_0x00d5
            java.lang.String r32 = "duplicate radix specifier"
            r3 = r32
            goto L_0x0027
        L_0x00d5:
            r32 = 16
            r7 = r32
            goto L_0x0099
        L_0x00da:
            r32 = r6
            if (r32 == 0) goto L_0x00f6
            r32 = r6
            r33 = 32
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x00ef
            java.lang.String r32 = "non-prefix exactness specifier"
            r3 = r32
            goto L_0x0027
        L_0x00ef:
            java.lang.String r32 = "duplicate exactness specifier"
            r3 = r32
            goto L_0x0027
        L_0x00f6:
            r32 = r11
            r6 = r32
            goto L_0x0099
        L_0x00fb:
            r32 = 10
            r33 = r12
            int r32 = r32 * r33
            r33 = r13
            int r32 = r32 + r33
            r12 = r32
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 < r1) goto L_0x0118
            java.lang.String r32 = "missing letter after '#'"
            r3 = r32
            goto L_0x0027
        L_0x0118:
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
            goto L_0x005f
        L_0x0124:
            r32 = r12
            r33 = 2
            r0 = r32
            r1 = r33
            if (r0 < r1) goto L_0x0138
            r32 = r12
            r33 = 35
            r0 = r32
            r1 = r33
            if (r0 <= r1) goto L_0x013f
        L_0x0138:
            java.lang.String r32 = "invalid radix specifier"
            r3 = r32
            goto L_0x0027
        L_0x013f:
            r32 = r12
            r7 = r32
            goto L_0x0099
        L_0x0145:
            java.lang.StringBuilder r32 = new java.lang.StringBuilder
            r39 = r32
            r32 = r39
            r33 = r39
            r33.<init>()
            java.lang.String r33 = "unknown modifier '#"
            java.lang.StringBuilder r32 = r32.append(r33)
            r33 = r11
            java.lang.StringBuilder r32 = r32.append(r33)
            r33 = 39
            java.lang.StringBuilder r32 = r32.append(r33)
            java.lang.String r32 = r32.toString()
            r3 = r32
            goto L_0x0027
        L_0x016b:
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
            goto L_0x0032
        L_0x0177:
            r32 = r6
            if (r32 != 0) goto L_0x017f
            r32 = 32
            r6 = r32
        L_0x017f:
            r32 = r7
            if (r32 != 0) goto L_0x0191
            r32 = r5
            r12 = r32
        L_0x0187:
            int r12 = r12 + -1
            r32 = r12
            if (r32 >= 0) goto L_0x01d0
            r32 = 10
            r7 = r32
        L_0x0191:
            r32 = r11
            r33 = 45
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x01e7
            r32 = 1
        L_0x019d:
            r12 = r32
            r32 = r12
            r13 = r32
            r32 = r11
            r33 = 45
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x01b7
            r32 = r11
            r33 = 43
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x01ea
        L_0x01b7:
            r32 = 1
        L_0x01b9:
            r14 = r32
            r32 = r14
            if (r32 == 0) goto L_0x01f7
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 < r1) goto L_0x01ed
            java.lang.String r32 = "no digits following sign"
            r3 = r32
            goto L_0x0027
        L_0x01d0:
            r32 = r3
            r33 = r4
            r34 = r12
            int r33 = r33 + r34
            char r32 = r32[r33]
            r33 = 46
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0187
            r32 = 10
            r7 = r32
            goto L_0x0191
        L_0x01e7:
            r32 = 0
            goto L_0x019d
        L_0x01ea:
            r32 = 0
            goto L_0x01b9
        L_0x01ed:
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
        L_0x01f7:
            r32 = r11
            r33 = 105(0x69, float:1.47E-43)
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x020b
            r32 = r11
            r33 = 73
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x028d
        L_0x020b:
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x028d
            r32 = r4
            r33 = r10
            r34 = 2
            int r33 = r33 + -2
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x028d
            r32 = r8
            r33 = 1
            r32 = r32 & 1
            if (r32 == 0) goto L_0x028d
            r32 = r3
            r33 = r4
            char r32 = r32[r33]
            r15 = r32
            r32 = r15
            r33 = 43
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x024e
            r32 = r15
            r33 = 45
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x024e
            java.lang.String r32 = "no digits"
            r3 = r32
            goto L_0x0027
        L_0x024e:
            r32 = r6
            r33 = 105(0x69, float:1.47E-43)
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0262
            r32 = r6
            r33 = 73
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x027c
        L_0x0262:
            gnu.math.DComplex r32 = new gnu.math.DComplex
            r39 = r32
            r32 = r39
            r33 = r39
            r34 = 0
            r36 = r12
            if (r36 == 0) goto L_0x0279
            r36 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0272:
            r33.<init>(r34, r36)
            r3 = r32
            goto L_0x0027
        L_0x0279:
            r36 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0272
        L_0x027c:
            r32 = r12
            if (r32 == 0) goto L_0x0288
            gnu.math.CComplex r32 = gnu.math.Complex.imMinusOne()
        L_0x0284:
            r3 = r32
            goto L_0x0027
        L_0x0288:
            gnu.math.CComplex r32 = gnu.math.Complex.imOne()
            goto L_0x0284
        L_0x028d:
            r32 = r10
            r33 = 1
            int r32 = r32 + -1
            r15 = r32
            r32 = 0
            r16 = r32
            r32 = -1
            r17 = r32
            r32 = -1
            r18 = r32
            r32 = -1
            r19 = r32
            r32 = 0
            r20 = r32
            r32 = 0
            r21 = r32
            r32 = 0
            r22 = r32
            r32 = 0
            r23 = r32
        L_0x02b5:
            r32 = r11
            r33 = r7
            int r32 = java.lang.Character.digit(r32, r33)
            r25 = r32
            r32 = r25
            if (r32 < 0) goto L_0x037d
            r32 = r16
            if (r32 == 0) goto L_0x02d2
            r32 = r19
            if (r32 >= 0) goto L_0x02d2
            java.lang.String r32 = "digit after '#' in number"
            r3 = r32
            goto L_0x0027
        L_0x02d2:
            r32 = r18
            if (r32 >= 0) goto L_0x02de
            r32 = r10
            r33 = 1
            int r32 = r32 + -1
            r18 = r32
        L_0x02de:
            r32 = r7
            r0 = r32
            long r0 = (long) r0
            r32 = r0
            r34 = r23
            long r32 = r32 * r34
            r34 = r25
            r0 = r34
            long r0 = (long) r0
            r34 = r0
            long r32 = r32 + r34
            r23 = r32
        L_0x02f4:
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x04af
        L_0x02fe:
            r32 = 0
            r25 = r32
            r32 = r18
            if (r32 >= 0) goto L_0x04f5
            r32 = r14
            if (r32 == 0) goto L_0x0372
            r32 = r10
            r33 = 4
            int r32 = r32 + 4
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 >= r1) goto L_0x0372
            r32 = r3
            r33 = r10
            r34 = 3
            int r33 = r33 + 3
            char r32 = r32[r33]
            r33 = 46
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0372
            r32 = r3
            r33 = r10
            r34 = 4
            int r33 = r33 + 4
            char r32 = r32[r33]
            r33 = 48
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0372
            r32 = r3
            r33 = r10
            char r32 = r32[r33]
            r33 = 105(0x69, float:1.47E-43)
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x04bb
            r32 = r3
            r33 = r10
            r34 = 1
            int r33 = r33 + 1
            char r32 = r32[r33]
            r33 = 110(0x6e, float:1.54E-43)
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x04bb
            r32 = r3
            r33 = r10
            r34 = 2
            int r33 = r33 + 2
            char r32 = r32[r33]
            r33 = 102(0x66, float:1.43E-43)
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x04bb
            r32 = 105(0x69, float:1.47E-43)
            r25 = r32
        L_0x0372:
            r32 = r25
            if (r32 != 0) goto L_0x04f3
            java.lang.String r32 = "no digits"
            r3 = r32
            goto L_0x0027
        L_0x037d:
            r32 = r11
            switch(r32) {
                case 46: goto L_0x0386;
                case 47: goto L_0x045e;
                case 68: goto L_0x03ac;
                case 69: goto L_0x03ac;
                case 70: goto L_0x03ac;
                case 76: goto L_0x03ac;
                case 83: goto L_0x03ac;
                case 100: goto L_0x03ac;
                case 101: goto L_0x03ac;
                case 102: goto L_0x03ac;
                case 108: goto L_0x03ac;
                case 115: goto L_0x03ac;
                default: goto L_0x0382;
            }
        L_0x0382:
            int r10 = r10 + -1
            goto L_0x02fe
        L_0x0386:
            r32 = r19
            if (r32 < 0) goto L_0x0391
            java.lang.String r32 = "duplicate '.' in number"
            r3 = r32
            goto L_0x0027
        L_0x0391:
            r32 = r7
            r33 = 10
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x03a2
            java.lang.String r32 = "'.' in non-decimal number"
            r3 = r32
            goto L_0x0027
        L_0x03a2:
            r32 = r10
            r33 = 1
            int r32 = r32 + -1
            r19 = r32
            goto L_0x02f4
        L_0x03ac:
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x03c0
            r32 = r7
            r33 = 10
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x03c4
        L_0x03c0:
            int r10 = r10 + -1
            goto L_0x02fe
        L_0x03c4:
            r32 = r3
            r33 = r10
            char r32 = r32[r33]
            r26 = r32
            r32 = r10
            r33 = 1
            int r32 = r32 + -1
            r27 = r32
            r32 = r26
            r33 = 43
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x03e8
            r32 = r26
            r33 = 45
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0409
        L_0x03e8:
            int r10 = r10 + 1
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 >= r1) goto L_0x0402
            r32 = r3
            r33 = r10
            char r32 = r32[r33]
            r33 = 10
            int r32 = java.lang.Character.digit(r32, r33)
            if (r32 >= 0) goto L_0x0417
        L_0x0402:
            java.lang.String r32 = "missing exponent digits"
            r3 = r32
            goto L_0x0027
        L_0x0409:
            r32 = r26
            r33 = 10
            int r32 = java.lang.Character.digit(r32, r33)
            if (r32 >= 0) goto L_0x0417
            int r10 = r10 + -1
            goto L_0x02fe
        L_0x0417:
            r32 = r17
            if (r32 < 0) goto L_0x0422
            java.lang.String r32 = "duplicate exponent"
            r3 = r32
            goto L_0x0027
        L_0x0422:
            r32 = r7
            r33 = 10
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0433
            java.lang.String r32 = "exponent in non-decimal number"
            r3 = r32
            goto L_0x0027
        L_0x0433:
            r32 = r18
            if (r32 >= 0) goto L_0x043e
            java.lang.String r32 = "mantissa with no digits"
            r3 = r32
            goto L_0x0027
        L_0x043e:
            r32 = r27
            r17 = r32
        L_0x0442:
            int r10 = r10 + 1
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 >= r1) goto L_0x02fe
            r32 = r3
            r33 = r10
            char r32 = r32[r33]
            r33 = 10
            int r32 = java.lang.Character.digit(r32, r33)
            if (r32 >= 0) goto L_0x0442
            goto L_0x02fe
        L_0x045e:
            r32 = r22
            if (r32 == 0) goto L_0x0469
            java.lang.String r32 = "multiple fraction symbol '/'"
            r3 = r32
            goto L_0x0027
        L_0x0469:
            r32 = r18
            if (r32 >= 0) goto L_0x0474
            java.lang.String r32 = "no digits before fraction symbol '/'"
            r3 = r32
            goto L_0x0027
        L_0x0474:
            r32 = r17
            if (r32 >= 0) goto L_0x047c
            r32 = r19
            if (r32 < 0) goto L_0x0483
        L_0x047c:
            java.lang.String r32 = "fraction symbol '/' following exponent or '.'"
            r3 = r32
            goto L_0x0027
        L_0x0483:
            r32 = r3
            r33 = r18
            r34 = r10
            r35 = r18
            int r34 = r34 - r35
            r35 = r7
            r36 = r12
            r37 = r23
            gnu.math.IntNum r32 = valueOf(r32, r33, r34, r35, r36, r37)
            r22 = r32
            r32 = -1
            r18 = r32
            r32 = 0
            r23 = r32
            r32 = 0
            r12 = r32
            r32 = 0
            r16 = r32
            r32 = 0
            r21 = r32
            goto L_0x02f4
        L_0x04af:
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
            goto L_0x02b5
        L_0x04bb:
            r32 = r3
            r33 = r10
            char r32 = r32[r33]
            r33 = 110(0x6e, float:1.54E-43)
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0372
            r32 = r3
            r33 = r10
            r34 = 1
            int r33 = r33 + 1
            char r32 = r32[r33]
            r33 = 97
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0372
            r32 = r3
            r33 = r10
            r34 = 2
            int r33 = r33 + 2
            char r32 = r32[r33]
            r33 = 110(0x6e, float:1.54E-43)
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0372
            r32 = 110(0x6e, float:1.54E-43)
            r25 = r32
            goto L_0x0372
        L_0x04f3:
            int r10 = r10 + 5
        L_0x04f5:
            r32 = r16
            if (r32 != 0) goto L_0x04fd
            r32 = r21
            if (r32 == 0) goto L_0x04fd
        L_0x04fd:
            r32 = r6
            r33 = 105(0x69, float:1.47E-43)
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x051f
            r32 = r6
            r33 = 73
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x051f
            r32 = r6
            r33 = 32
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x05b9
            r32 = r16
            if (r32 == 0) goto L_0x05b9
        L_0x051f:
            r32 = 1
        L_0x0521:
            r26 = r32
            r32 = 0
            r27 = r32
            r32 = 0
            r28 = r32
            r32 = r25
            if (r32 == 0) goto L_0x05c3
            r32 = 1
            r26 = r32
            r32 = r25
            r33 = 105(0x69, float:1.47E-43)
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x05bd
            r32 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
        L_0x053f:
            r29 = r32
            gnu.math.DFloNum r32 = new gnu.math.DFloNum
            r39 = r32
            r32 = r39
            r33 = r39
            r34 = r12
            if (r34 == 0) goto L_0x05c0
            r34 = r29
            r0 = r34
            double r0 = -r0
            r34 = r0
        L_0x0554:
            r33.<init>((double) r34)
            r27 = r32
        L_0x0559:
            r32 = r6
            r33 = 101(0x65, float:1.42E-43)
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x056d
            r32 = r6
            r33 = 69
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0575
        L_0x056d:
            r32 = r27
            gnu.math.RatNum r32 = r32.toExact()
            r27 = r32
        L_0x0575:
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 >= r1) goto L_0x0870
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
            r32 = r11
            r33 = 64
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0760
            r32 = r3
            r33 = r10
            r34 = r9
            r35 = r10
            int r34 = r34 - r35
            r35 = r6
            r36 = 10
            r37 = r8
            java.lang.Object r32 = parseNumber(r32, r33, r34, r35, r36, r37)
            r29 = r32
            r32 = r29
            r0 = r32
            boolean r0 = r0 instanceof java.lang.String
            r32 = r0
            if (r32 == 0) goto L_0x071c
            r32 = r29
            r3 = r32
            goto L_0x0027
        L_0x05b9:
            r32 = 0
            goto L_0x0521
        L_0x05bd:
            r32 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            goto L_0x053f
        L_0x05c0:
            r34 = r29
            goto L_0x0554
        L_0x05c3:
            r32 = r17
            if (r32 >= 0) goto L_0x05cb
            r32 = r19
            if (r32 < 0) goto L_0x067b
        L_0x05cb:
            r32 = r18
            r33 = r19
            r0 = r32
            r1 = r33
            if (r0 <= r1) goto L_0x05dd
            r32 = r19
            if (r32 < 0) goto L_0x05dd
            r32 = r19
            r18 = r32
        L_0x05dd:
            r32 = r22
            if (r32 == 0) goto L_0x05e8
            java.lang.String r32 = "floating-point number after fraction symbol '/'"
            r3 = r32
            goto L_0x0027
        L_0x05e8:
            java.lang.String r32 = new java.lang.String
            r39 = r32
            r32 = r39
            r33 = r39
            r34 = r3
            r35 = r18
            r36 = r10
            r37 = r18
            int r36 = r36 - r37
            r33.<init>(r34, r35, r36)
            r29 = r32
            r32 = r17
            if (r32 < 0) goto L_0x0656
            r32 = r3
            r33 = r17
            char r32 = r32[r33]
            char r32 = java.lang.Character.toLowerCase(r32)
            r28 = r32
            r32 = r28
            r33 = 101(0x65, float:1.42E-43)
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0656
            r32 = r17
            r33 = r18
            int r32 = r32 - r33
            r30 = r32
            java.lang.StringBuilder r32 = new java.lang.StringBuilder
            r39 = r32
            r32 = r39
            r33 = r39
            r33.<init>()
            r33 = r29
            r34 = 0
            r35 = r30
            java.lang.String r33 = r33.substring(r34, r35)
            java.lang.StringBuilder r32 = r32.append(r33)
            r33 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r32 = r32.append(r33)
            r33 = r29
            r34 = r30
            r35 = 1
            int r34 = r34 + 1
            java.lang.String r33 = r33.substring(r34)
            java.lang.StringBuilder r32 = r32.append(r33)
            java.lang.String r32 = r32.toString()
            r29 = r32
        L_0x0656:
            r32 = r29
            double r32 = gnu.lists.Convert.parseDouble(r32)
            r30 = r32
            gnu.math.DFloNum r32 = new gnu.math.DFloNum
            r39 = r32
            r32 = r39
            r33 = r39
            r34 = r12
            if (r34 == 0) goto L_0x0678
            r34 = r30
            r0 = r34
            double r0 = -r0
            r34 = r0
        L_0x0671:
            r33.<init>((double) r34)
            r27 = r32
            goto L_0x0559
        L_0x0678:
            r34 = r30
            goto L_0x0671
        L_0x067b:
            r32 = r3
            r33 = r18
            r34 = r10
            r35 = r18
            int r34 = r34 - r35
            r35 = r7
            r36 = r12
            r37 = r23
            gnu.math.IntNum r32 = valueOf(r32, r33, r34, r35, r36, r37)
            r29 = r32
            r32 = r22
            if (r32 != 0) goto L_0x06c2
            r32 = r29
            r27 = r32
        L_0x0699:
            r32 = r26
            if (r32 == 0) goto L_0x0559
            r32 = r27
            boolean r32 = r32.isExact()
            if (r32 == 0) goto L_0x0559
            gnu.math.DFloNum r32 = new gnu.math.DFloNum
            r39 = r32
            r32 = r39
            r33 = r39
            r34 = r13
            if (r34 == 0) goto L_0x0715
            r34 = r27
            boolean r34 = r34.isZero()
            if (r34 == 0) goto L_0x0715
            r34 = -9223372036854775808
        L_0x06bb:
            r33.<init>((double) r34)
            r27 = r32
            goto L_0x0559
        L_0x06c2:
            r32 = r29
            boolean r32 = r32.isZero()
            if (r32 == 0) goto L_0x070a
            r32 = r22
            boolean r32 = r32.isZero()
            r30 = r32
            r32 = r26
            if (r32 == 0) goto L_0x06f4
            gnu.math.DFloNum r32 = new gnu.math.DFloNum
            r39 = r32
            r32 = r39
            r33 = r39
            r34 = r30
            if (r34 == 0) goto L_0x06ea
            r34 = 9221120237041090560(0x7ff8000000000000, double:NaN)
        L_0x06e4:
            r33.<init>((double) r34)
            r27 = r32
        L_0x06e9:
            goto L_0x0699
        L_0x06ea:
            r34 = r13
            if (r34 == 0) goto L_0x06f1
            r34 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            goto L_0x06e4
        L_0x06f1:
            r34 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x06e4
        L_0x06f4:
            r32 = r30
            if (r32 == 0) goto L_0x06ff
            java.lang.String r32 = "0/0 is undefined"
            r3 = r32
            goto L_0x0027
        L_0x06ff:
            r32 = r22
            r33 = r29
            gnu.math.RatNum r32 = gnu.math.RatNum.make(r32, r33)
            r27 = r32
            goto L_0x06e9
        L_0x070a:
            r32 = r22
            r33 = r29
            gnu.math.RatNum r32 = gnu.math.RatNum.make(r32, r33)
            r27 = r32
            goto L_0x0699
        L_0x0715:
            r34 = r27
            double r34 = r34.doubleValue()
            goto L_0x06bb
        L_0x071c:
            r32 = r29
            r0 = r32
            boolean r0 = r0 instanceof gnu.math.RealNum
            r32 = r0
            if (r32 != 0) goto L_0x072d
            java.lang.String r32 = "invalid complex polar constant"
            r3 = r32
            goto L_0x0027
        L_0x072d:
            r32 = r29
            gnu.math.RealNum r32 = (gnu.math.RealNum) r32
            r30 = r32
            r32 = r27
            boolean r32 = r32.isZero()
            if (r32 == 0) goto L_0x0754
            r32 = r30
            boolean r32 = r32.isExact()
            if (r32 != 0) goto L_0x0754
            gnu.math.DFloNum r32 = new gnu.math.DFloNum
            r39 = r32
            r32 = r39
            r33 = r39
            r34 = 0
            r33.<init>((double) r34)
            r3 = r32
            goto L_0x0027
        L_0x0754:
            r32 = r27
            r33 = r30
            gnu.math.DComplex r32 = gnu.math.Complex.polar((gnu.math.RealNum) r32, (gnu.math.RealNum) r33)
            r3 = r32
            goto L_0x0027
        L_0x0760:
            r32 = r11
            r33 = 45
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0774
            r32 = r11
            r33 = 43
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x07fa
        L_0x0774:
            int r10 = r10 + -1
            r32 = r3
            r33 = r10
            r34 = r9
            r35 = r10
            int r34 = r34 - r35
            r35 = r6
            r36 = 10
            r37 = r8
            java.lang.Object r32 = parseNumber(r32, r33, r34, r35, r36, r37)
            r29 = r32
            r32 = r29
            r0 = r32
            boolean r0 = r0 instanceof java.lang.String
            r32 = r0
            if (r32 == 0) goto L_0x079c
            r32 = r29
            r3 = r32
            goto L_0x0027
        L_0x079c:
            r32 = r29
            r0 = r32
            boolean r0 = r0 instanceof gnu.math.Complex
            r32 = r0
            if (r32 != 0) goto L_0x07cd
            java.lang.StringBuilder r32 = new java.lang.StringBuilder
            r39 = r32
            r32 = r39
            r33 = r39
            r33.<init>()
            java.lang.String r33 = "invalid numeric constant ("
            java.lang.StringBuilder r32 = r32.append(r33)
            r33 = r29
            java.lang.StringBuilder r32 = r32.append(r33)
            java.lang.String r33 = ")"
            java.lang.StringBuilder r32 = r32.append(r33)
            java.lang.String r32 = r32.toString()
            r3 = r32
            goto L_0x0027
        L_0x07cd:
            r32 = r29
            gnu.math.Complex r32 = (gnu.math.Complex) r32
            r30 = r32
            r32 = r30
            gnu.math.RealNum r32 = r32.mo17635re()
            r31 = r32
            r32 = r31
            boolean r32 = r32.isZero()
            if (r32 != 0) goto L_0x07ea
            java.lang.String r32 = "invalid numeric constant"
            r3 = r32
            goto L_0x0027
        L_0x07ea:
            r32 = r27
            r33 = r30
            gnu.math.RealNum r33 = r33.mo17634im()
            gnu.math.Complex r32 = gnu.math.Complex.make((gnu.math.RealNum) r32, (gnu.math.RealNum) r33)
            r3 = r32
            goto L_0x0027
        L_0x07fa:
            r32 = 0
            r29 = r32
        L_0x07fe:
            r32 = r11
            boolean r32 = java.lang.Character.isLetter(r32)
            if (r32 != 0) goto L_0x0843
            int r10 = r10 + -1
        L_0x0808:
            r32 = r29
            r33 = 1
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0869
            r32 = r3
            r33 = r10
            r34 = 1
            int r33 = r33 + -1
            char r32 = r32[r33]
            r30 = r32
            r32 = r30
            r33 = 105(0x69, float:1.47E-43)
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0832
            r32 = r30
            r33 = 73
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0869
        L_0x0832:
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 >= r1) goto L_0x085b
            java.lang.String r32 = "junk after imaginary suffix 'i'"
            r3 = r32
            goto L_0x0027
        L_0x0843:
            int r29 = r29 + 1
            r32 = r10
            r33 = r9
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x0850
            goto L_0x0808
        L_0x0850:
            r32 = r3
            r33 = r10
            int r10 = r10 + 1
            char r32 = r32[r33]
            r11 = r32
            goto L_0x07fe
        L_0x085b:
            gnu.math.IntNum r32 = gnu.math.IntNum.zero()
            r33 = r27
            gnu.math.Complex r32 = gnu.math.Complex.make((gnu.math.RealNum) r32, (gnu.math.RealNum) r33)
            r3 = r32
            goto L_0x0027
        L_0x0869:
            java.lang.String r32 = "excess junk after number"
            r3 = r32
            goto L_0x0027
        L_0x0870:
            r32 = r27
            r0 = r32
            boolean r0 = r0 instanceof gnu.math.DFloNum
            r32 = r0
            if (r32 == 0) goto L_0x0895
            r32 = r28
            if (r32 <= 0) goto L_0x0895
            r32 = r28
            r33 = 101(0x65, float:1.42E-43)
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0895
            r32 = r27
            double r32 = r32.doubleValue()
            r29 = r32
            r32 = r28
            switch(r32) {
                case 100: goto L_0x08aa;
                case 102: goto L_0x089b;
                case 108: goto L_0x08b4;
                case 115: goto L_0x089b;
                default: goto L_0x0895;
            }
        L_0x0895:
            r32 = r27
            r3 = r32
            goto L_0x0027
        L_0x089b:
            r32 = r29
            r0 = r32
            float r0 = (float) r0
            r32 = r0
            java.lang.Float r32 = java.lang.Float.valueOf(r32)
            r3 = r32
            goto L_0x0027
        L_0x08aa:
            r32 = r29
            java.lang.Double r32 = java.lang.Double.valueOf(r32)
            r3 = r32
            goto L_0x0027
        L_0x08b4:
            r32 = r29
            java.math.BigDecimal r32 = java.math.BigDecimal.valueOf(r32)
            r3 = r32
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.parseNumber(char[], int, int, char, int, int):java.lang.Object");
    }

    private static IntNum valueOf(char[] cArr, int i, int i2, int i3, boolean z, long j) {
        char[] buffer = cArr;
        int digits_start = i;
        int number_of_digits = i2;
        int radix = i3;
        boolean negative = z;
        long lvalue = j;
        if (number_of_digits + radix > 28) {
            return IntNum.valueOf(buffer, digits_start, number_of_digits, radix, negative);
        }
        return IntNum.make(negative ? -lvalue : lvalue);
    }

    public int readEscape() throws IOException, SyntaxException {
        int c = read();
        if (c >= 0) {
            return readEscape(c);
        }
        eofError("unexpected EOF in character literal");
        return -1;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return 127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        return r1 & 159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        eofError("unexpected EOF in literal");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e2, code lost:
        r1 = read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00eb, code lost:
        if (r1 != 92) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ed, code lost:
        r1 = readEscape();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f6, code lost:
        if (r1 != 63) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int readEscape(int r8) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r5 = r1
            char r5 = (char) r5
            switch(r5) {
                case 9: goto L_0x0037;
                case 10: goto L_0x0037;
                case 13: goto L_0x0037;
                case 32: goto L_0x0037;
                case 34: goto L_0x0029;
                case 48: goto L_0x0105;
                case 49: goto L_0x0105;
                case 50: goto L_0x0105;
                case 51: goto L_0x0105;
                case 52: goto L_0x0105;
                case 53: goto L_0x0105;
                case 54: goto L_0x0105;
                case 55: goto L_0x0105;
                case 67: goto L_0x00cb;
                case 77: goto L_0x009b;
                case 88: goto L_0x016d;
                case 92: goto L_0x002d;
                case 94: goto L_0x00e2;
                case 97: goto L_0x000a;
                case 98: goto L_0x000d;
                case 101: goto L_0x0025;
                case 102: goto L_0x001d;
                case 110: goto L_0x0015;
                case 114: goto L_0x0021;
                case 116: goto L_0x0011;
                case 117: goto L_0x0137;
                case 118: goto L_0x0019;
                case 120: goto L_0x016d;
                default: goto L_0x0007;
            }
        L_0x0007:
            r5 = r1
            r0 = r5
        L_0x0009:
            return r0
        L_0x000a:
            r5 = 7
            r1 = r5
            goto L_0x0007
        L_0x000d:
            r5 = 8
            r1 = r5
            goto L_0x0007
        L_0x0011:
            r5 = 9
            r1 = r5
            goto L_0x0007
        L_0x0015:
            r5 = 10
            r1 = r5
            goto L_0x0007
        L_0x0019:
            r5 = 11
            r1 = r5
            goto L_0x0007
        L_0x001d:
            r5 = 12
            r1 = r5
            goto L_0x0007
        L_0x0021:
            r5 = 13
            r1 = r5
            goto L_0x0007
        L_0x0025:
            r5 = 27
            r1 = r5
            goto L_0x0007
        L_0x0029:
            r5 = 34
            r1 = r5
            goto L_0x0007
        L_0x002d:
            r5 = 92
            r1 = r5
            goto L_0x0007
        L_0x0031:
            r5 = r0
            int r5 = r5.read()
            r1 = r5
        L_0x0037:
            r5 = r1
            if (r5 >= 0) goto L_0x0044
            r5 = r0
            java.lang.String r6 = "unexpected EOF in literal"
            r5.eofError(r6)
            r5 = -1
            r0 = r5
            goto L_0x0009
        L_0x0044:
            r5 = r1
            r6 = 10
            if (r5 != r6) goto L_0x004f
        L_0x0049:
            r5 = r1
            r6 = 10
            if (r5 == r6) goto L_0x0075
            goto L_0x0007
        L_0x004f:
            r5 = r1
            r6 = 13
            if (r5 != r6) goto L_0x0065
            r5 = r0
            int r5 = r5.peek()
            r6 = 10
            if (r5 != r6) goto L_0x0061
            r5 = r0
            r5.skip()
        L_0x0061:
            r5 = 10
            r1 = r5
            goto L_0x0049
        L_0x0065:
            r5 = r1
            r6 = 32
            if (r5 == r6) goto L_0x0031
            r5 = r1
            r6 = 9
            if (r5 == r6) goto L_0x0031
            r5 = r0
            r6 = r1
            r5.unread(r6)
            goto L_0x0049
        L_0x0075:
            r5 = r0
            int r5 = r5.read()
            r1 = r5
            r5 = r1
            if (r5 >= 0) goto L_0x0088
            r5 = r0
            java.lang.String r6 = "unexpected EOF in literal"
            r5.eofError(r6)
            r5 = -1
            r0 = r5
            goto L_0x0009
        L_0x0088:
            r5 = r1
            r6 = 32
            if (r5 == r6) goto L_0x0075
            r5 = r1
            r6 = 9
            if (r5 == r6) goto L_0x0075
            r5 = r0
            r6 = r1
            r5.unread(r6)
            r5 = -2
            r0 = r5
            goto L_0x0009
        L_0x009b:
            r5 = r0
            int r5 = r5.read()
            r1 = r5
            r5 = r1
            r6 = 45
            if (r5 == r6) goto L_0x00b2
            r5 = r0
            java.lang.String r6 = "Invalid escape character syntax"
            r5.error(r6)
            r5 = 63
            r0 = r5
            goto L_0x0009
        L_0x00b2:
            r5 = r0
            int r5 = r5.read()
            r1 = r5
            r5 = r1
            r6 = 92
            if (r5 != r6) goto L_0x00c3
            r5 = r0
            int r5 = r5.readEscape()
            r1 = r5
        L_0x00c3:
            r5 = r1
            r6 = 128(0x80, float:1.794E-43)
            r5 = r5 | 128(0x80, float:1.794E-43)
            r0 = r5
            goto L_0x0009
        L_0x00cb:
            r5 = r0
            int r5 = r5.read()
            r1 = r5
            r5 = r1
            r6 = 45
            if (r5 == r6) goto L_0x00e2
            r5 = r0
            java.lang.String r6 = "Invalid escape character syntax"
            r5.error(r6)
            r5 = 63
            r0 = r5
            goto L_0x0009
        L_0x00e2:
            r5 = r0
            int r5 = r5.read()
            r1 = r5
            r5 = r1
            r6 = 92
            if (r5 != r6) goto L_0x00f3
            r5 = r0
            int r5 = r5.readEscape()
            r1 = r5
        L_0x00f3:
            r5 = r1
            r6 = 63
            if (r5 != r6) goto L_0x00fd
            r5 = 127(0x7f, float:1.78E-43)
            r0 = r5
            goto L_0x0009
        L_0x00fd:
            r5 = r1
            r6 = 159(0x9f, float:2.23E-43)
            r5 = r5 & 159(0x9f, float:2.23E-43)
            r0 = r5
            goto L_0x0009
        L_0x0105:
            r5 = r1
            r6 = 48
            int r5 = r5 + -48
            r1 = r5
            r5 = 0
            r2 = r5
        L_0x010d:
            int r2 = r2 + 1
            r5 = r2
            r6 = 3
            if (r5 >= r6) goto L_0x0135
            r5 = r0
            int r5 = r5.read()
            r3 = r5
            r5 = r3
            char r5 = (char) r5
            r6 = 8
            int r5 = java.lang.Character.digit(r5, r6)
            r4 = r5
            r5 = r4
            if (r5 < 0) goto L_0x012d
            r5 = r1
            r6 = 3
            int r5 = r5 << 3
            r6 = r4
            int r5 = r5 + r6
            r1 = r5
            goto L_0x010d
        L_0x012d:
            r5 = r3
            if (r5 < 0) goto L_0x0135
            r5 = r0
            r6 = r3
            r5.unread(r6)
        L_0x0135:
            goto L_0x0007
        L_0x0137:
            r5 = 0
            r1 = r5
            r5 = 4
            r2 = r5
        L_0x013b:
            int r2 = r2 + -1
            r5 = r2
            if (r5 < 0) goto L_0x016b
            r5 = r0
            int r5 = r5.read()
            r3 = r5
            r5 = r3
            if (r5 >= 0) goto L_0x0150
            r5 = r0
            java.lang.String r6 = "premature EOF in \\u escape"
            r5.eofError(r6)
        L_0x0150:
            r5 = r3
            char r5 = (char) r5
            r6 = 16
            int r5 = java.lang.Character.digit(r5, r6)
            r4 = r5
            r5 = r4
            if (r5 >= 0) goto L_0x0163
            r5 = r0
            java.lang.String r6 = "non-hex character following \\u"
            r5.error(r6)
        L_0x0163:
            r5 = 16
            r6 = r1
            int r5 = r5 * r6
            r6 = r4
            int r5 = r5 + r6
            r1 = r5
            goto L_0x013b
        L_0x016b:
            goto L_0x0007
        L_0x016d:
            r5 = r0
            int r5 = r5.readHexEscape()
            r0 = r5
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readEscape(int):int");
    }

    public int readHexEscape() throws IOException, SyntaxException {
        int c;
        int d;
        int i = 0;
        while (true) {
            c = i;
            d = read();
            int v = Character.digit((char) d, 16);
            if (v < 0) {
                break;
            }
            i = (c << 4) + v;
        }
        if (d != 59 && d >= 0) {
            unread(d);
        }
        return c;
    }

    public final Object readObject(int c) throws IOException, SyntaxException {
        unread(c);
        return readObject();
    }

    public Object readCommand() throws IOException, SyntaxException {
        return readObject();
    }

    /* access modifiers changed from: protected */
    public Object makeNil() {
        return LList.Empty;
    }

    /* access modifiers changed from: protected */
    public Pair makePair(Object car, int line, int column) {
        return makePair(car, LList.Empty, line, column);
    }

    /* access modifiers changed from: protected */
    public Pair makePair(Object obj, Object obj2, int i, int i2) {
        Object car = obj;
        Object cdr = obj2;
        int line = i;
        int column = i2;
        String pname = this.port.getName();
        if (pname == null || line < 0) {
            return Pair.make(car, cdr);
        }
        return PairWithPosition.make(car, cdr, pname, line + 1, column + 1);
    }

    /* access modifiers changed from: protected */
    public void setCdr(Object pair, Object cdr) {
        ((Pair) pair).setCdrBackdoor(cdr);
    }

    public static Object readNumberWithRadix(int previous, LispReader lispReader, int i) throws IOException, SyntaxException {
        LispReader reader = lispReader;
        int radix = i;
        int startPos = reader.tokenBufferLength - previous;
        reader.readToken(reader.read(), 'P', ReadTable.getCurrent());
        int endPos = reader.tokenBufferLength;
        if (startPos == endPos) {
            reader.error("missing numeric token");
            return IntNum.zero();
        }
        Object result = parseNumber(reader.tokenBuffer, startPos, endPos - startPos, 0, radix, 0);
        if (result instanceof String) {
            reader.error((String) result);
            return IntNum.zero();
        } else if (result != null) {
            return result;
        } else {
            reader.error("invalid numeric constant");
            return IntNum.zero();
        }
    }

    public static Object readCharacter(LispReader lispReader) throws IOException, SyntaxException {
        String str;
        StringBuilder sb;
        LispReader reader = lispReader;
        int ch = reader.read();
        if (ch < 0) {
            reader.eofError("unexpected EOF in character literal");
        }
        int startPos = reader.tokenBufferLength;
        reader.tokenBufferAppend(ch);
        reader.readToken(reader.read(), 'D', ReadTable.getCurrent());
        char[] tokenBuffer = reader.tokenBuffer;
        int length = reader.tokenBufferLength - startPos;
        if (length == 1) {
            return Char.make(tokenBuffer[startPos]);
        }
        new String(tokenBuffer, startPos, length);
        String name = str;
        int ch2 = Char.nameToChar(name);
        if (ch2 >= 0) {
            return Char.make(ch2);
        }
        int ch3 = tokenBuffer[startPos];
        if (ch3 == 120 || ch3 == 88) {
            int value = 0;
            int i = 1;
            while (i != length) {
                int v = Character.digit(tokenBuffer[startPos + i], 16);
                if (v >= 0) {
                    value = (16 * value) + v;
                    if (value <= 1114111) {
                        i++;
                    }
                }
            }
            return Char.make(value);
        }
        int ch4 = Character.digit(ch3, 8);
        if (ch4 >= 0) {
            int value2 = ch4;
            int i2 = 1;
            while (i2 != length) {
                int ch5 = Character.digit(tokenBuffer[startPos + i2], 8);
                if (ch5 >= 0) {
                    value2 = (8 * value2) + ch5;
                    i2++;
                }
            }
            return Char.make(value2);
        }
        new StringBuilder();
        reader.error(sb.append("unknown character name: ").append(name).toString());
        return Char.make(63);
    }

    public static Object readSpecial(LispReader lispReader) throws IOException, SyntaxException {
        String str;
        StringBuilder sb;
        LispReader reader = lispReader;
        int ch = reader.read();
        if (ch < 0) {
            reader.eofError("unexpected EOF in #! special form");
        }
        if (ch == 47 && reader.getLineNumber() == 0 && reader.getColumnNumber() == 3) {
            Object read = ReaderIgnoreRestOfLine.getInstance().read(reader, 35, 1);
            return Values.empty;
        }
        int startPos = reader.tokenBufferLength;
        reader.tokenBufferAppend(ch);
        reader.readToken(reader.read(), 'D', ReadTable.getCurrent());
        new String(reader.tokenBuffer, startPos, reader.tokenBufferLength - startPos);
        String name = str;
        if (name.equals("optional")) {
            return Special.optional;
        }
        if (name.equals("rest")) {
            return Special.rest;
        }
        if (name.equals("key")) {
            return Special.key;
        }
        if (name.equals("eof")) {
            return Special.eof;
        }
        if (name.equals("void")) {
            return QuoteExp.voidExp;
        }
        if (name.equals("default")) {
            return Special.dfault;
        }
        if (name.equals("undefined")) {
            return Special.undefined;
        }
        if (name.equals("abstract")) {
            return Special.abstractSpecial;
        }
        if (name.equals("null")) {
            return null;
        }
        new StringBuilder();
        reader.error(sb.append("unknown named constant #!").append(name).toString());
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0085, code lost:
        switch(r2) {
            case 8: goto L_0x00ae;
            case 16: goto L_0x00b9;
            case 32: goto L_0x00c4;
            case 64: goto L_0x00d0;
            default: goto L_0x0088;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0089, code lost:
        switch(r2) {
            case 8: goto L_0x008d;
            case 16: goto L_0x00dc;
            case 32: goto L_0x00e8;
            case 64: goto L_0x00f4;
            default: goto L_0x008c;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        new gnu.lists.U8Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ae, code lost:
        new gnu.lists.S8Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b9, code lost:
        new gnu.lists.S16Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c4, code lost:
        new gnu.lists.S32Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d0, code lost:
        new gnu.lists.S64Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00dc, code lost:
        new gnu.lists.U16Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e8, code lost:
        new gnu.lists.U32Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f4, code lost:
        new gnu.lists.U64Vector(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.lists.SimpleVector readSimpleVector(gnu.kawa.lispexpr.LispReader r12, char r13) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = r12
            r1 = r13
            r7 = 0
            r2 = r7
        L_0x0004:
            r7 = r0
            int r7 = r7.read()
            r3 = r7
            r7 = r3
            if (r7 >= 0) goto L_0x0014
            r7 = r0
            java.lang.String r8 = "unexpected EOF reading uniform vector"
            r7.eofError(r8)
        L_0x0014:
            r7 = r3
            char r7 = (char) r7
            r8 = 10
            int r7 = java.lang.Character.digit(r7, r8)
            r4 = r7
            r7 = r4
            if (r7 >= 0) goto L_0x004d
            r7 = r2
            r8 = 8
            if (r7 == r8) goto L_0x0034
            r7 = r2
            r8 = 16
            if (r7 == r8) goto L_0x0034
            r7 = r2
            r8 = 32
            if (r7 == r8) goto L_0x0034
            r7 = r2
            r8 = 64
            if (r7 != r8) goto L_0x0043
        L_0x0034:
            r7 = r1
            r8 = 70
            if (r7 != r8) goto L_0x003e
            r7 = r2
            r8 = 32
            if (r7 < r8) goto L_0x0043
        L_0x003e:
            r7 = r3
            r8 = 40
            if (r7 == r8) goto L_0x0056
        L_0x0043:
            r7 = r0
            java.lang.String r8 = "invalid uniform vector syntax"
            r7.error(r8)
            r7 = 0
            r0 = r7
        L_0x004c:
            return r0
        L_0x004d:
            r7 = r2
            r8 = 10
            int r7 = r7 * 10
            r8 = r4
            int r7 = r7 + r8
            r2 = r7
            goto L_0x0004
        L_0x0056:
            r7 = r0
            r8 = 40
            r9 = -1
            r10 = 41
            java.lang.Object r7 = gnu.kawa.lispexpr.ReaderParens.readList(r7, r8, r9, r10)
            r4 = r7
            r7 = r4
            r8 = 0
            int r7 = gnu.lists.LList.listLength(r7, r8)
            r5 = r7
            r7 = r5
            if (r7 >= 0) goto L_0x0075
            r7 = r0
            java.lang.String r8 = "invalid elements in uniform vector syntax"
            r7.error(r8)
            r7 = 0
            r0 = r7
            goto L_0x004c
        L_0x0075:
            r7 = r4
            gnu.lists.Sequence r7 = (gnu.lists.Sequence) r7
            r6 = r7
            r7 = r1
            switch(r7) {
                case 70: goto L_0x0080;
                case 83: goto L_0x0084;
                case 85: goto L_0x0088;
                default: goto L_0x007d;
            }
        L_0x007d:
            r7 = 0
            r0 = r7
            goto L_0x004c
        L_0x0080:
            r7 = r2
            switch(r7) {
                case 32: goto L_0x0098;
                case 64: goto L_0x00a3;
                default: goto L_0x0084;
            }
        L_0x0084:
            r7 = r2
            switch(r7) {
                case 8: goto L_0x00ae;
                case 16: goto L_0x00b9;
                case 32: goto L_0x00c4;
                case 64: goto L_0x00d0;
                default: goto L_0x0088;
            }
        L_0x0088:
            r7 = r2
            switch(r7) {
                case 8: goto L_0x008d;
                case 16: goto L_0x00dc;
                case 32: goto L_0x00e8;
                case 64: goto L_0x00f4;
                default: goto L_0x008c;
            }
        L_0x008c:
            goto L_0x007d
        L_0x008d:
            gnu.lists.U8Vector r7 = new gnu.lists.U8Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x0098:
            gnu.lists.F32Vector r7 = new gnu.lists.F32Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00a3:
            gnu.lists.F64Vector r7 = new gnu.lists.F64Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00ae:
            gnu.lists.S8Vector r7 = new gnu.lists.S8Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00b9:
            gnu.lists.S16Vector r7 = new gnu.lists.S16Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00c4:
            gnu.lists.S32Vector r7 = new gnu.lists.S32Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00d0:
            gnu.lists.S64Vector r7 = new gnu.lists.S64Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00dc:
            gnu.lists.U16Vector r7 = new gnu.lists.U16Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00e8:
            gnu.lists.U32Vector r7 = new gnu.lists.U32Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        L_0x00f4:
            gnu.lists.U64Vector r7 = new gnu.lists.U64Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r6
            r8.<init>((gnu.lists.Sequence) r9)
            r0 = r7
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readSimpleVector(gnu.kawa.lispexpr.LispReader, char):gnu.lists.SimpleVector");
    }
}
