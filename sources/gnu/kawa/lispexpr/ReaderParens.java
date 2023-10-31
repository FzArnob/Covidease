package gnu.kawa.lispexpr;

import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderParens extends ReadTableEntry {
    private static ReaderParens instance;
    char close;
    Object command;
    int kind;
    char open;

    public int getKind() {
        return this.kind;
    }

    public static ReaderParens getInstance(char open2, char close2) {
        return getInstance(open2, close2, 5);
    }

    public static ReaderParens getInstance(char c, char c2, int i) {
        ReaderParens readerParens;
        ReaderParens readerParens2;
        char open2 = c;
        char close2 = c2;
        int kind2 = i;
        if (open2 == '(' && close2 == ')' && kind2 == 5) {
            if (instance == null) {
                new ReaderParens(open2, close2, kind2, (Object) null);
                instance = readerParens2;
            }
            return instance;
        }
        new ReaderParens(open2, close2, kind2, (Object) null);
        return readerParens;
    }

    public static ReaderParens getInstance(char c, char c2, int i, Object obj) {
        ReaderParens readerParens;
        char open2 = c;
        char close2 = c2;
        int kind2 = i;
        Object command2 = obj;
        if (command2 == null) {
            return getInstance(open2, close2, kind2);
        }
        new ReaderParens(open2, close2, kind2, command2);
        return readerParens;
    }

    public ReaderParens(char open2, char close2, int kind2, Object command2) {
        this.open = open2;
        this.close = close2;
        this.kind = kind2;
        this.command = command2;
    }

    public Object read(Lexer lexer, int ch, int count) throws IOException, SyntaxException {
        Lexer in = lexer;
        Object r = readList((LispReader) in, ch, count, this.close);
        if (this.command != null) {
            LineBufferedReader port = in.getPort();
            Object p = ((LispReader) in).makePair(this.command, port.getLineNumber(), port.getColumnNumber());
            ((LispReader) in).setCdr(p, r);
            r = p;
        }
        return r;
    }

    /* JADX INFO: finally extract failed */
    public static Object readList(LispReader lispReader, int i, int i2, int i3) throws IOException, SyntaxException {
        ReadTableEntry entry;
        StringBuilder sb;
        LispReader lexer = lispReader;
        int i4 = i;
        int i5 = i2;
        int close2 = i3;
        LineBufferedReader port = lexer.getPort();
        char saveReadState = lexer.pushNesting(close2 == 93 ? '[' : '(');
        int startLine = port.getLineNumber();
        int startColumn = port.getColumnNumber();
        Object last = null;
        try {
            Object list = lexer.makeNil();
            boolean sawDot = false;
            boolean sawDotCdr = false;
            ReadTable readTable = ReadTable.getCurrent();
            while (true) {
                int line = port.getLineNumber();
                int column = port.getColumnNumber();
                int ch = port.read();
                if (ch == close2) {
                    break;
                }
                if (ch < 0) {
                    lexer.eofError("unexpected EOF in list starting here", startLine + 1, startColumn);
                }
                if (ch == 46) {
                    ch = port.peek();
                    entry = readTable.lookup(ch);
                    int kind2 = entry.getKind();
                    if (kind2 == 1 || kind2 == 5 || kind2 == 0) {
                        port.skip();
                        column++;
                        if (ch == close2) {
                            new StringBuilder();
                            lexer.error(sb.append("unexpected '").append((char) close2).append("' after '.'").toString());
                            break;
                        }
                        if (ch < 0) {
                            lexer.eofError("unexpected EOF in list starting here", startLine + 1, startColumn);
                        }
                        if (sawDot) {
                            lexer.error("multiple '.' in list");
                            sawDotCdr = false;
                            list = lexer.makeNil();
                            last = null;
                        }
                        sawDot = true;
                    } else {
                        ch = 46;
                        entry = ReadTableEntry.getConstituentInstance();
                    }
                } else {
                    entry = readTable.lookup(ch);
                }
                Object value = lexer.readValues(ch, entry, readTable);
                if (value != Values.empty) {
                    Object value2 = lexer.handlePostfix(value, readTable, line, column);
                    if (sawDotCdr) {
                        lexer.error("multiple values after '.'");
                        last = null;
                        list = lexer.makeNil();
                        sawDotCdr = false;
                    } else {
                        if (sawDot) {
                            sawDotCdr = true;
                        } else {
                            if (last == null) {
                                line = startLine;
                                column = startColumn - 1;
                            }
                            value2 = lexer.makePair(value2, line, column);
                        }
                        if (last == null) {
                            list = value2;
                        } else {
                            lexer.setCdr(last, value2);
                        }
                        last = value2;
                    }
                }
            }
            Object obj = list;
            lexer.popNesting(saveReadState);
            return obj;
        } catch (Throwable th) {
            Throwable th2 = th;
            lexer.popNesting(saveReadState);
            throw th2;
        }
    }
}
