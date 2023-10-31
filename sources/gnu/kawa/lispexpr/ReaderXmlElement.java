package gnu.kawa.lispexpr;

import gnu.expr.Compilation;
import gnu.expr.PrimProcedure;
import gnu.kawa.xml.CommentConstructor;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeCDATA;
import gnu.kawa.xml.MakeProcInst;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import gnu.xml.XName;
import java.io.IOException;

public class ReaderXmlElement extends ReadTableEntry {
    static final String DEFAULT_ELEMENT_NAMESPACE = "[default-element-namespace]";

    public ReaderXmlElement() {
    }

    public Object read(Lexer in, int i, int i2) throws IOException, SyntaxException {
        int i3 = i;
        int i4 = i2;
        LispReader reader = (LispReader) in;
        return readXMLConstructor(reader, reader.readUnicodeChar(), false);
    }

    public static Pair quote(Object obj) {
        return LList.list2(Namespace.EmptyNamespace.getSymbol(LispLanguage.quote_sym), obj);
    }

    public static Object readQNameExpression(LispReader lispReader, int i, boolean z) throws IOException, SyntaxException {
        String str;
        String str2;
        String prefix;
        Object obj;
        Object obj2;
        LispReader reader = lispReader;
        int ch = i;
        boolean forElement = z;
        String name = reader.getName();
        int line = reader.getLineNumber() + 1;
        int column = reader.getColumnNumber();
        reader.tokenBufferLength = 0;
        if (XName.isNameStart(ch)) {
            int colon = -1;
            while (true) {
                reader.tokenBufferAppend(ch);
                ch = reader.readUnicodeChar();
                if (ch == 58 && colon < 0) {
                    colon = reader.tokenBufferLength;
                } else if (!XName.isNamePart(ch)) {
                    break;
                }
            }
            reader.unread(ch);
            if (colon < 0 && !forElement) {
                return quote(Namespace.getDefaultSymbol(reader.tokenBufferString().intern()));
            }
            new String(reader.tokenBuffer, colon + 1, (reader.tokenBufferLength - colon) - 1);
            String local = str.intern();
            if (colon < 0) {
                prefix = DEFAULT_ELEMENT_NAMESPACE;
            } else {
                new String(reader.tokenBuffer, 0, colon);
                prefix = str2.intern();
            }
            Symbol psym = Namespace.EmptyNamespace.getSymbol(prefix);
            ResolveNamespace resolveNamespace = ResolveNamespace.resolveQName;
            new Pair(local, LList.Empty);
            new Pair(resolveNamespace, PairWithPosition.make(psym, obj2, reader.getName(), line, column));
            return obj;
        } else if (ch == 123 || ch == 40) {
            return readEscapedExpression(reader, ch);
        } else {
            reader.error("missing element name");
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    static Object readEscapedExpression(LispReader lispReader, int i) throws IOException, SyntaxException {
        Object obj;
        LispReader reader = lispReader;
        int ch = i;
        if (ch == 40) {
            reader.unread(ch);
            return reader.readObject();
        }
        LineBufferedReader port = reader.getPort();
        char saveReadState = reader.pushNesting('{');
        int startLine = port.getLineNumber();
        int startColumn = port.getColumnNumber();
        try {
            new PrimProcedure(Compilation.typeValues.getDeclaredMethod("values", 1));
            Pair list = reader.makePair(obj, startLine, startColumn);
            Pair last = list;
            ReadTable readTable = ReadTable.getCurrent();
            while (true) {
                int line = port.getLineNumber();
                int column = port.getColumnNumber();
                int ch2 = port.read();
                if (ch2 == 125) {
                    break;
                }
                if (ch2 < 0) {
                    reader.eofError("unexpected EOF in list starting here", startLine + 1, startColumn);
                }
                Object value = reader.readValues(ch2, readTable.lookup(ch2), readTable);
                if (value != Values.empty) {
                    Pair pair = reader.makePair(reader.handlePostfix(value, readTable, line, column), line, column);
                    reader.setCdr(last, pair);
                    last = pair;
                }
            }
            reader.tokenBufferLength = 0;
            if (last == list.getCdr()) {
                Object car = last.getCar();
                reader.popNesting(saveReadState);
                return car;
            }
            Pair pair2 = list;
            reader.popNesting(saveReadState);
            return pair2;
        } catch (Throwable th) {
            Throwable th2 = th;
            reader.popNesting(saveReadState);
            throw th2;
        }
    }

    static Object readXMLConstructor(LispReader lispReader, int i, boolean z) throws IOException, SyntaxException {
        Pair readElementConstructor;
        LispReader reader = lispReader;
        int next = i;
        boolean z2 = z;
        int startLine = reader.getLineNumber() + 1;
        int startColumn = reader.getColumnNumber() - 2;
        if (next == 33) {
            int next2 = reader.read();
            if (next2 == 45) {
                int peek = reader.peek();
                next2 = peek;
                if (peek == 45) {
                    reader.skip();
                    if (!reader.readDelimited("-->")) {
                        reader.error('f', reader.getName(), startLine, startColumn, "unexpected end-of-file in XML comment starting here - expected \"-->\"");
                    }
                    readElementConstructor = LList.list2(CommentConstructor.commentConstructor, reader.tokenBufferString());
                }
            }
            if (next2 == 91) {
                int read = reader.read();
                next2 = read;
                if (read == 67) {
                    int read2 = reader.read();
                    next2 = read2;
                    if (read2 == 68) {
                        int read3 = reader.read();
                        next2 = read3;
                        if (read3 == 65) {
                            int read4 = reader.read();
                            next2 = read4;
                            if (read4 == 84) {
                                int read5 = reader.read();
                                next2 = read5;
                                if (read5 == 65) {
                                    int read6 = reader.read();
                                    next2 = read6;
                                    if (read6 == 91) {
                                        if (!reader.readDelimited("]]>")) {
                                            reader.error('f', reader.getName(), startLine, startColumn, "unexpected end-of-file in CDATA starting here - expected \"]]>\"");
                                        }
                                        readElementConstructor = LList.list2(MakeCDATA.makeCDATA, reader.tokenBufferString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            reader.error('f', reader.getName(), startLine, startColumn, "'<!' must be followed by '--' or '[CDATA['");
            while (next2 >= 0 && next2 != 62 && next2 != 10 && next2 != 13) {
                next2 = reader.read();
            }
            readElementConstructor = null;
        } else if (next == 63) {
            int next3 = reader.readUnicodeChar();
            if (next3 < 0 || !XName.isNameStart(next3)) {
                reader.error("missing target after '<?'");
            }
            do {
                reader.tokenBufferAppend(next3);
                next3 = reader.readUnicodeChar();
            } while (XName.isNamePart(next3));
            String target = reader.tokenBufferString();
            int nspaces = 0;
            while (next3 >= 0 && Character.isWhitespace(next3)) {
                nspaces++;
                next3 = reader.read();
            }
            reader.unread(next3);
            char saveReadState = reader.pushNesting('?');
            try {
                if (!reader.readDelimited("?>")) {
                    reader.error('f', reader.getName(), startLine, startColumn, "unexpected end-of-file looking for \"?>\"");
                }
                reader.popNesting(saveReadState);
                if (nspaces == 0 && reader.tokenBufferLength > 0) {
                    reader.error("target must be followed by space or '?>'");
                }
                readElementConstructor = LList.list3(MakeProcInst.makeProcInst, target, reader.tokenBufferString());
            } catch (Throwable th) {
                Throwable th2 = th;
                reader.popNesting(saveReadState);
                throw th2;
            }
        } else {
            readElementConstructor = readElementConstructor(reader, next);
        }
        return readElementConstructor;
    }

    public static Object readElementConstructor(LispReader lispReader, int ch) throws IOException, SyntaxException {
        String str;
        int ch2;
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        LList lList;
        String str2;
        LispReader reader = lispReader;
        int startLine = reader.getLineNumber() + 1;
        int startColumn = reader.getColumnNumber() - 2;
        Object tag = readQNameExpression(reader, ch, true);
        if (reader.tokenBufferLength == 0) {
            str = null;
        } else {
            str = reader.tokenBufferString();
        }
        String startTag = str;
        Pair tagPair = PairWithPosition.make(tag, LList.Empty, reader.getName(), startLine, startColumn);
        Pair resultTail = tagPair;
        LList namespaceList = LList.Empty;
        while (true) {
            boolean sawSpace = false;
            ch2 = reader.readUnicodeChar();
            while (ch2 >= 0 && Character.isWhitespace(ch2)) {
                ch2 = reader.read();
                sawSpace = true;
            }
            if (ch2 < 0 || ch2 == 62 || ch2 == 47) {
                boolean empty = false;
            } else {
                if (!sawSpace) {
                    reader.error("missing space before attribute");
                }
                Object attrName = readQNameExpression(reader, ch2, false);
                int lineNumber = reader.getLineNumber() + 1;
                int columnNumber = (reader.getColumnNumber() + 1) - reader.tokenBufferLength;
                String definingNamespace = null;
                if (reader.tokenBufferLength >= 5 && reader.tokenBuffer[0] == 'x' && reader.tokenBuffer[1] == 'm' && reader.tokenBuffer[2] == 'l' && reader.tokenBuffer[3] == 'n' && reader.tokenBuffer[4] == 's') {
                    if (reader.tokenBufferLength == 5) {
                        definingNamespace = "";
                    } else if (reader.tokenBuffer[5] == ':') {
                        new String(reader.tokenBuffer, 6, reader.tokenBufferLength - 6);
                        definingNamespace = str2;
                    }
                }
                if (skipSpace(reader, 32) != 61) {
                    reader.error("missing '=' after attribute");
                }
                int ch3 = skipSpace(reader, 32);
                Pair attrList = PairWithPosition.make(MakeAttribute.makeAttribute, LList.Empty, reader.getName(), startLine, startColumn);
                Pair attrPair = PairWithPosition.make(attrName, LList.Empty, reader.getName(), startLine, startColumn);
                reader.setCdr(attrList, attrPair);
                Pair attrTail = readContent(reader, (char) ch3, attrPair);
                if (definingNamespace != null) {
                    new PairWithPosition(attrPair, Pair.make(definingNamespace, attrPair.getCdr()), namespaceList);
                    namespaceList = lList;
                } else {
                    Pair pair = PairWithPosition.make(attrList, reader.makeNil(), (String) null, -1, -1);
                    resultTail.setCdrBackdoor(pair);
                    resultTail = pair;
                }
            }
        }
        boolean empty2 = false;
        if (ch2 == 47) {
            ch2 = reader.read();
            if (ch2 == 62) {
                empty2 = true;
            } else {
                reader.unread(ch2);
            }
        }
        if (!empty2) {
            if (ch2 != 62) {
                reader.error("missing '>' after start element");
                return Boolean.FALSE;
            }
            Pair resultTail2 = readContent(reader, '<', resultTail);
            int ch4 = reader.readUnicodeChar();
            if (XName.isNameStart(ch4)) {
                reader.tokenBufferLength = 0;
                while (true) {
                    reader.tokenBufferAppend(ch4);
                    ch4 = reader.readUnicodeChar();
                    if (!XName.isNamePart(ch4) && ch4 != 58) {
                        break;
                    }
                }
                String endTag = reader.tokenBufferString();
                if (startTag == null || !endTag.equals(startTag)) {
                    LispReader lispReader2 = reader;
                    String name = reader.getName();
                    int lineNumber2 = reader.getLineNumber() + 1;
                    int columnNumber2 = reader.getColumnNumber() - reader.tokenBufferLength;
                    if (startTag == null) {
                        new StringBuilder();
                        sb2 = sb3.append("computed start tag closed by '</").append(endTag).append(">'").toString();
                    } else {
                        new StringBuilder();
                        sb2 = sb.append("'<").append(startTag).append(">' closed by '</").append(endTag).append(">'").toString();
                    }
                    lispReader2.error('e', name, lineNumber2, columnNumber2, sb2);
                }
                reader.tokenBufferLength = 0;
            }
            if (skipSpace(reader, ch4) != 62) {
                reader.error("missing '>' after end element");
            }
        }
        return PairWithPosition.make(MakeXmlElement.makeXml, Pair.make(LList.reverseInPlace(namespaceList), tagPair), reader.getName(), startLine, startColumn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: gnu.lists.PairWithPosition} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: gnu.lists.PairWithPosition} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.lists.Pair readContent(gnu.kawa.lispexpr.LispReader r16, char r17, gnu.lists.Pair r18) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r11 = r0
            r12 = 0
            r11.tokenBufferLength = r12
            r11 = 0
            r3 = r11
        L_0x000c:
            r11 = 0
            r4 = r11
            r11 = 0
            r5 = r11
            r11 = r0
            int r11 = r11.getLineNumber()
            r12 = 1
            int r11 = r11 + 1
            r6 = r11
            r11 = r0
            int r11 = r11.getColumnNumber()
            r7 = r11
            r11 = r0
            int r11 = r11.read()
            r8 = r11
            r11 = r8
            if (r11 >= 0) goto L_0x006c
            r11 = r0
            java.lang.String r12 = "unexpected end-of-file"
            r11.error(r12)
            java.lang.Object r11 = gnu.expr.Special.eof
            r4 = r11
        L_0x0032:
            r11 = r4
            if (r11 == 0) goto L_0x0044
            r11 = r0
            int r11 = r11.tokenBufferLength
            if (r11 <= 0) goto L_0x0044
            r11 = r0
            java.lang.String r11 = r11.tokenBufferString()
            r5 = r11
            r11 = r0
            r12 = 0
            r11.tokenBufferLength = r12
        L_0x0044:
            r11 = r5
            if (r11 == 0) goto L_0x0064
            gnu.kawa.xml.MakeText r11 = gnu.kawa.xml.MakeText.makeText
            r12 = r5
            gnu.lists.Pair r11 = gnu.lists.Pair.list2(r11, r12)
            r9 = r11
            r11 = r9
            r12 = r0
            java.lang.Object r12 = r12.makeNil()
            r13 = 0
            r14 = -1
            r15 = -1
            gnu.lists.PairWithPosition r11 = gnu.lists.PairWithPosition.make(r11, r12, r13, r14, r15)
            r10 = r11
            r11 = r2
            r12 = r10
            r11.setCdrBackdoor(r12)
            r11 = r10
            r2 = r11
        L_0x0064:
            r11 = r4
            java.lang.Object r12 = gnu.expr.Special.eof
            if (r11 != r12) goto L_0x012d
            r11 = r2
            r0 = r11
            return r0
        L_0x006c:
            r11 = r8
            r12 = r1
            if (r11 != r12) goto L_0x00b0
            r11 = r1
            r12 = 60
            if (r11 != r12) goto L_0x009e
            r11 = r0
            int r11 = r11.tokenBufferLength
            if (r11 <= 0) goto L_0x0084
            r11 = r0
            java.lang.String r11 = r11.tokenBufferString()
            r5 = r11
            r11 = r0
            r12 = 0
            r11.tokenBufferLength = r12
        L_0x0084:
            r11 = r0
            int r11 = r11.read()
            r8 = r11
            r11 = r8
            r12 = 47
            if (r11 != r12) goto L_0x0095
            java.lang.Object r11 = gnu.expr.Special.eof
            r4 = r11
        L_0x0092:
            r11 = 0
            r3 = r11
            goto L_0x0032
        L_0x0095:
            r11 = r0
            r12 = r8
            r13 = 1
            java.lang.Object r11 = readXMLConstructor(r11, r12, r13)
            r4 = r11
            goto L_0x0092
        L_0x009e:
            r11 = r0
            r12 = r1
            boolean r11 = r11.checkNext(r12)
            if (r11 == 0) goto L_0x00ac
            r11 = r0
            r12 = r1
            r11.tokenBufferAppend(r12)
            goto L_0x0092
        L_0x00ac:
            java.lang.Object r11 = gnu.expr.Special.eof
            r4 = r11
            goto L_0x0092
        L_0x00b0:
            r11 = r8
            r12 = 38
            if (r11 != r12) goto L_0x0100
            r11 = r0
            int r11 = r11.read()
            r8 = r11
            r11 = r8
            r12 = 35
            if (r11 != r12) goto L_0x00c8
            r11 = r0
            readCharRef(r11)
        L_0x00c4:
            r11 = 1
            r3 = r11
            goto L_0x0032
        L_0x00c8:
            r11 = r8
            r12 = 40
            if (r11 == r12) goto L_0x00d2
            r11 = r8
            r12 = 123(0x7b, float:1.72E-43)
            if (r11 != r12) goto L_0x00ec
        L_0x00d2:
            r11 = r0
            int r11 = r11.tokenBufferLength
            if (r11 > 0) goto L_0x00da
            r11 = r3
            if (r11 == 0) goto L_0x00e0
        L_0x00da:
            r11 = r0
            java.lang.String r11 = r11.tokenBufferString()
            r5 = r11
        L_0x00e0:
            r11 = r0
            r12 = 0
            r11.tokenBufferLength = r12
            r11 = r0
            r12 = r8
            java.lang.Object r11 = readEscapedExpression(r11, r12)
            r4 = r11
            goto L_0x00c4
        L_0x00ec:
            r11 = r0
            r12 = r8
            java.lang.Object r11 = readEntity(r11, r12)
            r4 = r11
            r11 = r3
            if (r11 == 0) goto L_0x00c4
            r11 = r0
            int r11 = r11.tokenBufferLength
            if (r11 != 0) goto L_0x00c4
            java.lang.String r11 = ""
            r5 = r11
            goto L_0x00c4
        L_0x0100:
            r11 = r1
            r12 = 60
            if (r11 == r12) goto L_0x0117
            r11 = r8
            r12 = 9
            if (r11 == r12) goto L_0x0114
            r11 = r8
            r12 = 10
            if (r11 == r12) goto L_0x0114
            r11 = r8
            r12 = 13
            if (r11 != r12) goto L_0x0117
        L_0x0114:
            r11 = 32
            r8 = r11
        L_0x0117:
            r11 = r8
            r12 = 60
            if (r11 != r12) goto L_0x0125
            r11 = r0
            r12 = 101(0x65, float:1.42E-43)
            java.lang.String r13 = "'<' must be quoted in a direct attribute value"
            r11.error(r12, r13)
        L_0x0125:
            r11 = r0
            r12 = r8
            char r12 = (char) r12
            r11.tokenBufferAppend(r12)
            goto L_0x0032
        L_0x012d:
            r11 = r4
            if (r11 == 0) goto L_0x0145
            r11 = r4
            r12 = r0
            java.lang.Object r12 = r12.makeNil()
            r13 = 0
            r14 = r6
            r15 = r7
            gnu.lists.PairWithPosition r11 = gnu.lists.PairWithPosition.make(r11, r12, r13, r14, r15)
            r9 = r11
            r11 = r2
            r12 = r9
            r11.setCdrBackdoor(r12)
            r11 = r9
            r2 = r11
        L_0x0145:
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderXmlElement.readContent(gnu.kawa.lispexpr.LispReader, char, gnu.lists.Pair):gnu.lists.Pair");
    }

    static void readCharRef(LispReader lispReader) throws IOException, SyntaxException {
        int base;
        StringBuilder sb;
        int digit;
        LispReader reader = lispReader;
        int next = reader.read();
        if (next == 120) {
            base = 16;
            next = reader.read();
        } else {
            base = 10;
        }
        int value = 0;
        while (next >= 0 && (digit = Character.digit((char) next, base)) >= 0 && value < 134217728) {
            value = (value * base) + digit;
            next = reader.read();
        }
        if (next != 59) {
            reader.unread(next);
            reader.error("invalid character reference");
        } else if ((value <= 0 || value > 55295) && ((value < 57344 || value > 65533) && (value < 65536 || value > 1114111))) {
            new StringBuilder();
            reader.error(sb.append("invalid character value ").append(value).toString());
        } else {
            reader.tokenBufferAppend(value);
        }
    }

    static Object readEntity(LispReader lispReader, int i) throws IOException, SyntaxException {
        String ref;
        LispReader reader = lispReader;
        int next = i;
        LispReader result = "?";
        int saveLength = reader.tokenBufferLength;
        while (next >= 0) {
            char ch = (char) next;
            if (!XName.isNamePart(ch)) {
                break;
            }
            reader.tokenBufferAppend(ch);
            next = reader.read();
        }
        if (next != 59) {
            reader.unread(next);
            reader.error("invalid entity reference");
        } else {
            new String(reader.tokenBuffer, saveLength, reader.tokenBufferLength - saveLength);
            reader.tokenBufferLength = saveLength;
            namedEntity(reader, ref);
            result = null;
        }
        return result;
    }

    public static void namedEntity(LispReader lispReader, String name) {
        StringBuilder sb;
        LispReader reader = lispReader;
        String name2 = name.intern();
        char ch = '?';
        if (name2 == "lt") {
            ch = '<';
        } else if (name2 == "gt") {
            ch = '>';
        } else if (name2 == "amp") {
            ch = '&';
        } else if (name2 == "quot") {
            ch = '\"';
        } else if (name2 == "apos") {
            ch = '\'';
        } else {
            new StringBuilder();
            reader.error(sb.append("unknown enity reference: '").append(name2).append("'").toString());
        }
        reader.tokenBufferAppend(ch);
    }

    static int skipSpace(LispReader lispReader, int i) throws IOException, SyntaxException {
        LispReader reader = lispReader;
        int ch = i;
        while (ch >= 0 && Character.isWhitespace(ch)) {
            ch = reader.readUnicodeChar();
        }
        return ch;
    }
}
