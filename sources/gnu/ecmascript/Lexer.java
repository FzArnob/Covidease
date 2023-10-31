package gnu.ecmascript;

import gnu.expr.QuoteExp;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;

public class Lexer extends gnu.text.Lexer {
    public static final Char colonToken = Char.make(58);
    public static final Char commaToken = Char.make(44);
    public static final Char condToken = Char.make(63);
    public static final Char dotToken = Char.make(46);
    public static final Reserved elseToken;
    public static final Object eofToken = Sequence.eofValue;
    public static final Object eolToken = Char.make(10);
    public static final Char equalToken = Char.make(61);
    public static final Char lbraceToken = Char.make(123);
    public static final Char lbracketToken = Char.make(91);
    public static final Char lparenToken = Char.make(40);
    public static final Reserved newToken;
    public static final Char notToken = Char.make(33);
    public static final Char rbraceToken = Char.make(125);
    public static final Char rbracketToken = Char.make(93);
    static Hashtable reserved;
    public static final Char rparenToken = Char.make(41);
    public static final Char semicolonToken = Char.make(59);
    public static final Char tildeToken = Char.make(126);
    private boolean prevWasCR = false;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Lexer(InPort port) {
        super(port);
    }

    static {
        Reserved reserved2;
        Reserved reserved3;
        new Reserved("else", 38);
        elseToken = reserved2;
        new Reserved("new", 39);
        newToken = reserved3;
    }

    static synchronized void initReserved() {
        Hashtable hashtable;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        Object obj12;
        Object obj13;
        synchronized (Lexer.class) {
            if (reserved == null) {
                new Hashtable(20);
                reserved = hashtable;
                new QuoteExp((Object) null);
                Object put = reserved.put("null", obj);
                new QuoteExp(Boolean.TRUE);
                Object put2 = reserved.put("true", obj2);
                new QuoteExp(Boolean.FALSE);
                Object put3 = reserved.put("false", obj3);
                new Reserved("var", 30);
                Object put4 = reserved.put("var", obj4);
                new Reserved("if", 31);
                Object put5 = reserved.put("if", obj5);
                new Reserved("while", 32);
                Object put6 = reserved.put("while", obj6);
                new Reserved("for", 33);
                Object put7 = reserved.put("for", obj7);
                new Reserved("continue", 34);
                Object put8 = reserved.put("continue", obj8);
                new Reserved("break", 35);
                Object put9 = reserved.put("break", obj9);
                new Reserved("return", 36);
                Object put10 = reserved.put("return", obj10);
                new Reserved("with", 37);
                Object put11 = reserved.put("with", obj11);
                new Reserved("function", 41);
                Object put12 = reserved.put("function", obj12);
                new Reserved("this", 40);
                Object put13 = reserved.put("this", obj13);
                Object put14 = reserved.put("else", elseToken);
                Object put15 = reserved.put("new", newToken);
            }
        }
    }

    public static Object checkReserved(String str) {
        String name = str;
        if (reserved == null) {
            initReserved();
        }
        return reserved.get(name);
    }

    public Double getNumericLiteral(int i) throws IOException {
        StringBuffer stringBuffer;
        int c;
        Double d;
        double dval;
        Double d2;
        int c2 = i;
        int radix = 10;
        if (c2 == 48) {
            c2 = read();
            if (c2 == 120 || c2 == 88) {
                radix = 16;
                c2 = read();
            } else if (!(c2 == 46 || c2 == 101 || c2 == 69)) {
                radix = 8;
            }
        }
        int i2 = this.port.pos;
        if (c2 >= 0) {
            i2--;
        }
        this.port.pos = i2;
        long ival = readDigitsInBuffer(this.port, radix);
        boolean digit_seen = this.port.pos > i2;
        if (digit_seen && this.port.pos < this.port.limit) {
            int c3 = this.port.buffer[this.port.pos];
            if (!Character.isLetterOrDigit((char) c3) && c3 != 46) {
                if (ival >= 0) {
                    dval = (double) ival;
                } else {
                    dval = IntNum.valueOf(this.port.buffer, i2, this.port.pos - i2, radix, false).doubleValue();
                }
                new Double(dval);
                return d2;
            }
        }
        if (radix != 10) {
            error("invalid character in non-decimal number");
        }
        new StringBuffer(20);
        StringBuffer str = stringBuffer;
        if (digit_seen) {
            StringBuffer append = str.append(this.port.buffer, i2, this.port.pos - i2);
        }
        int point_loc = -1;
        int exp = 0;
        while (true) {
            c = this.port.read();
            if (Character.digit((char) c, radix) < 0) {
                switch (c) {
                    case 46:
                        if (point_loc < 0) {
                            point_loc = str.length();
                            StringBuffer append2 = str.append('.');
                            break;
                        } else {
                            error("duplicate '.' in number");
                            continue;
                        }
                    case 69:
                    case 101:
                        if (radix == 10) {
                            int peek = this.port.peek();
                            int next = peek;
                            if (peek == 43 || next == 45 || Character.digit((char) next, 10) >= 0) {
                                if (!digit_seen) {
                                    error("mantissa with no digits");
                                }
                                exp = readOptionalExponent();
                                c = read();
                                break;
                            }
                        }
                        break;
                }
            } else {
                digit_seen = true;
                StringBuffer append3 = str.append((char) c);
            }
        }
        if (c >= 0) {
            this.port.unread();
        }
        if (exp != 0) {
            StringBuffer append4 = str.append('e');
            StringBuffer append5 = str.append(exp);
        }
        new Double(str.toString());
        return d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v60, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v61, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v65, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v30, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v81, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getStringLiteral(char r19) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            int r11 = r11.pos
            r2 = r11
            r11 = r2
            r3 = r11
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            int r11 = r11.limit
            r4 = r11
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            char[] r11 = r11.buffer
            r5 = r11
        L_0x0018:
            r11 = r2
            r12 = r4
            if (r11 >= r12) goto L_0x0051
            r11 = r5
            r12 = r2
            char r11 = r11[r12]
            r6 = r11
            r11 = r6
            r12 = r1
            if (r11 != r12) goto L_0x0042
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            r12 = r2
            r13 = 1
            int r12 = r12 + 1
            r11.pos = r12
            java.lang.String r11 = new java.lang.String
            r17 = r11
            r11 = r17
            r12 = r17
            r13 = r5
            r14 = r3
            r15 = r2
            r16 = r3
            int r15 = r15 - r16
            r12.<init>(r13, r14, r15)
            r0 = r11
        L_0x0041:
            return r0
        L_0x0042:
            r11 = r6
            r12 = 92
            if (r11 == r12) goto L_0x0051
            r11 = r6
            r12 = 10
            if (r11 == r12) goto L_0x0051
            r11 = r6
            r12 = 13
            if (r11 != r12) goto L_0x0080
        L_0x0051:
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            r12 = r2
            r11.pos = r12
            java.lang.StringBuffer r11 = new java.lang.StringBuffer
            r17 = r11
            r11 = r17
            r12 = r17
            r12.<init>()
            r7 = r11
            r11 = r7
            r12 = r5
            r13 = r3
            r14 = r2
            r15 = r3
            int r14 = r14 - r15
            java.lang.StringBuffer r11 = r11.append(r12, r13, r14)
        L_0x006d:
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            int r11 = r11.read()
            r8 = r11
            r11 = r8
            r12 = r1
            if (r11 != r12) goto L_0x0083
            r11 = r7
            java.lang.String r11 = r11.toString()
            r0 = r11
            goto L_0x0041
        L_0x0080:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x0083:
            r11 = r8
            if (r11 >= 0) goto L_0x008d
            r11 = r0
            java.lang.String r12 = "unterminated string literal"
            r11.eofError(r12)
        L_0x008d:
            r11 = r8
            r12 = 10
            if (r11 == r12) goto L_0x0097
            r11 = r8
            r12 = 13
            if (r11 != r12) goto L_0x009e
        L_0x0097:
            r11 = r0
            java.lang.String r12 = "string literal not terminated before end of line"
            r11.fatal(r12)
        L_0x009e:
            r11 = r8
            r12 = 92
            if (r11 != r12) goto L_0x00b9
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            int r11 = r11.read()
            r8 = r11
            r11 = r8
            switch(r11) {
                case -1: goto L_0x00c1;
                case 10: goto L_0x00c8;
                case 13: goto L_0x00c8;
                case 34: goto L_0x00cf;
                case 39: goto L_0x00cf;
                case 92: goto L_0x00cf;
                case 98: goto L_0x00d0;
                case 102: goto L_0x00dc;
                case 110: goto L_0x00d8;
                case 114: goto L_0x00e0;
                case 116: goto L_0x00d4;
                case 117: goto L_0x00e4;
                case 120: goto L_0x00e4;
                default: goto L_0x00af;
            }
        L_0x00af:
            r11 = r8
            r12 = 48
            if (r11 < r12) goto L_0x00b9
            r11 = r8
            r12 = 55
            if (r11 <= r12) goto L_0x0168
        L_0x00b9:
            r11 = r7
            r12 = r8
            char r12 = (char) r12
            java.lang.StringBuffer r11 = r11.append(r12)
            goto L_0x006d
        L_0x00c1:
            r11 = r0
            java.lang.String r12 = "eof following '\\' in string"
            r11.eofError(r12)
        L_0x00c8:
            r11 = r0
            java.lang.String r12 = "line terminator following '\\' in string"
            r11.fatal(r12)
        L_0x00cf:
            goto L_0x00b9
        L_0x00d0:
            r11 = 8
            r8 = r11
            goto L_0x00b9
        L_0x00d4:
            r11 = 9
            r8 = r11
            goto L_0x00b9
        L_0x00d8:
            r11 = 10
            r8 = r11
            goto L_0x00b9
        L_0x00dc:
            r11 = 12
            r8 = r11
            goto L_0x00b9
        L_0x00e0:
            r11 = 13
            r8 = r11
            goto L_0x00b9
        L_0x00e4:
            r11 = 0
            r9 = r11
            r11 = r8
            r12 = 120(0x78, float:1.68E-43)
            if (r11 != r12) goto L_0x015e
            r11 = 2
        L_0x00ec:
            r2 = r11
        L_0x00ed:
            int r2 = r2 + -1
            r11 = r2
            if (r11 < 0) goto L_0x015a
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            int r11 = r11.read()
            r10 = r11
            r11 = r10
            if (r11 >= 0) goto L_0x0124
            r11 = r0
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r17 = r12
            r12 = r17
            r13 = r17
            r13.<init>()
            java.lang.String r13 = "eof following '\\"
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r8
            char r13 = (char) r13
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = "' in string"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.eofError(r12)
        L_0x0124:
            r11 = r10
            char r11 = (char) r11
            r12 = 16
            char r11 = java.lang.Character.forDigit(r11, r12)
            r10 = r11
            r11 = r10
            if (r11 >= 0) goto L_0x0160
            r11 = r0
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r17 = r12
            r12 = r17
            r13 = r17
            r13.<init>()
            java.lang.String r13 = "invalid char following '\\"
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r8
            char r13 = (char) r13
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = "' in string"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.error(r12)
            r11 = 63
            r9 = r11
        L_0x015a:
            r11 = r9
            r8 = r11
            goto L_0x00b9
        L_0x015e:
            r11 = 4
            goto L_0x00ec
        L_0x0160:
            r11 = 16
            r12 = r9
            int r11 = r11 * r12
            r12 = r10
            int r11 = r11 + r12
            r9 = r11
            goto L_0x00ed
        L_0x0168:
            r11 = 0
            r9 = r11
            r11 = 3
            r2 = r11
        L_0x016c:
            int r2 = r2 + -1
            r11 = r2
            if (r11 < 0) goto L_0x0195
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            int r11 = r11.read()
            r10 = r11
            r11 = r10
            if (r11 >= 0) goto L_0x0183
            r11 = r0
            java.lang.String r12 = "eof in octal escape in string literal"
            r11.eofError(r12)
        L_0x0183:
            r11 = r10
            char r11 = (char) r11
            r12 = 8
            char r11 = java.lang.Character.forDigit(r11, r12)
            r10 = r11
            r11 = r10
            if (r11 >= 0) goto L_0x0199
            r11 = r0
            gnu.text.LineBufferedReader r11 = r11.port
            r11.unread_quick()
        L_0x0195:
            r11 = r9
            r8 = r11
            goto L_0x00b9
        L_0x0199:
            r11 = 8
            r12 = r9
            int r11 = r11 * r12
            r12 = r10
            int r11 = r11 + r12
            r9 = r11
            goto L_0x016c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.ecmascript.Lexer.getStringLiteral(char):java.lang.String");
    }

    public String getIdentifier(int i) throws IOException {
        StringBuffer stringBuffer;
        String str;
        int i2 = i;
        int i3 = this.port.pos;
        int start = i3 - 1;
        int limit = this.port.limit;
        char[] buffer = this.port.buffer;
        while (i3 < limit && Character.isJavaIdentifierPart(buffer[i3])) {
            i3++;
        }
        this.port.pos = i3;
        if (i3 < limit) {
            new String(buffer, start, i3 - start);
            return str;
        }
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(buffer, start, i3 - start);
        while (true) {
            int ch = this.port.read();
            if (ch >= 0) {
                if (!Character.isJavaIdentifierPart((char) ch)) {
                    this.port.unread_quick();
                    break;
                }
                StringBuffer append2 = sbuf.append((char) ch);
            } else {
                break;
            }
        }
        return sbuf.toString();
    }

    public Object maybeAssignment(Object obj) throws IOException, SyntaxException {
        Object token = obj;
        int ch = read();
        if (ch == 61) {
            error("assignment operation not implemented");
        }
        if (ch >= 0) {
            this.port.unread_quick();
        }
        return token;
    }

    public Object getToken() throws IOException, SyntaxException {
        Object obj;
        Object obj2;
        Object obj3;
        int read = read();
        while (true) {
            int ch = read;
            if (ch < 0) {
                return eofToken;
            }
            if (!Character.isWhitespace((char) ch)) {
                switch (ch) {
                    case 33:
                        if (this.port.peek() != 61) {
                            return notToken;
                        }
                        this.port.skip_quick();
                        return Reserved.opNotEqual;
                    case 34:
                    case 39:
                        new QuoteExp(getStringLiteral((char) ch));
                        return obj;
                    case 37:
                        return maybeAssignment(Reserved.opRemainder);
                    case 38:
                        if (this.port.peek() != 38) {
                            return maybeAssignment(Reserved.opBitAnd);
                        }
                        this.port.skip_quick();
                        return maybeAssignment(Reserved.opBoolAnd);
                    case 40:
                        return lparenToken;
                    case 41:
                        return rparenToken;
                    case 42:
                        return maybeAssignment(Reserved.opTimes);
                    case 43:
                        if (this.port.peek() != 43) {
                            return maybeAssignment(Reserved.opPlus);
                        }
                        this.port.skip_quick();
                        return maybeAssignment(Reserved.opPlusPlus);
                    case 44:
                        return commaToken;
                    case 45:
                        if (this.port.peek() != 45) {
                            return maybeAssignment(Reserved.opMinus);
                        }
                        this.port.skip_quick();
                        return maybeAssignment(Reserved.opMinusMinus);
                    case 46:
                        int ch2 = this.port.peek();
                        if (ch2 < 48 || ch2 > 57) {
                            return dotToken;
                        }
                        new QuoteExp(getNumericLiteral(46));
                        return obj3;
                    case 47:
                        return maybeAssignment(Reserved.opDivide);
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        new QuoteExp(getNumericLiteral(ch));
                        return obj2;
                    case 58:
                        return colonToken;
                    case 59:
                        return semicolonToken;
                    case 60:
                        switch (this.port.peek()) {
                            case 60:
                                this.port.skip_quick();
                                return maybeAssignment(Reserved.opLshift);
                            case 61:
                                this.port.skip_quick();
                                return Reserved.opLessEqual;
                            default:
                                return Reserved.opLess;
                        }
                    case 61:
                        if (this.port.peek() != 61) {
                            return equalToken;
                        }
                        this.port.skip_quick();
                        return Reserved.opEqual;
                    case 62:
                        switch (this.port.peek()) {
                            case 61:
                                this.port.skip_quick();
                                return Reserved.opGreaterEqual;
                            case 62:
                                this.port.skip_quick();
                                if (this.port.peek() != 62) {
                                    return maybeAssignment(Reserved.opRshiftSigned);
                                }
                                this.port.skip_quick();
                                return maybeAssignment(Reserved.opRshiftUnsigned);
                            default:
                                return Reserved.opGreater;
                        }
                    case 63:
                        return condToken;
                    case 91:
                        return lbracketToken;
                    case 93:
                        return rbracketToken;
                    case 94:
                        return maybeAssignment(Reserved.opBitXor);
                    case 123:
                        return lbraceToken;
                    case 124:
                        if (this.port.peek() != 124) {
                            return maybeAssignment(Reserved.opBitOr);
                        }
                        this.port.skip_quick();
                        return maybeAssignment(Reserved.opBoolOr);
                    case 125:
                        return rbraceToken;
                    case 126:
                        return tildeToken;
                    default:
                        if (!Character.isJavaIdentifierStart((char) ch)) {
                            return Char.make((char) ch);
                        }
                        String word = getIdentifier(ch).intern();
                        Object token = checkReserved(word);
                        if (token != null) {
                            return token;
                        }
                        return word;
                }
            } else if (ch == 13) {
                this.prevWasCR = true;
                return eolToken;
            } else if (ch == 10 && !this.prevWasCR) {
                return eolToken;
            } else {
                this.prevWasCR = false;
                read = read();
            }
        }
    }

    public static Object getToken(InPort inp) throws IOException, SyntaxException {
        Lexer lexer;
        new Lexer(inp);
        return lexer.getToken();
    }

    public static void main(String[] strArr) {
        Lexer lexer;
        StringBuilder sb;
        Object token;
        StringBuilder sb2;
        String[] strArr2 = strArr;
        new Lexer(InPort.inDefault());
        Lexer reader = lexer;
        do {
            try {
                token = reader.getToken();
                OutPort out = OutPort.outDefault();
                out.print("token:");
                out.print(token);
                new StringBuilder();
                out.println(sb2.append(" [class:").append(token.getClass()).append("]").toString());
            } catch (Exception e) {
                Exception ex = e;
                PrintStream printStream = System.err;
                new StringBuilder();
                printStream.println(sb.append("caught exception:").append(ex).toString());
                return;
            }
        } while (token != Sequence.eofValue);
    }
}
