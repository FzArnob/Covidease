package gnu.kawa.lispexpr;

import gnu.kawa.util.RangeTable;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatch extends ReadTableEntry {
    int kind;
    RangeTable table;

    public int getKind() {
        return this.kind;
    }

    public void set(int i, Object value) {
        int key = i;
        this.table.set(key, key, value);
    }

    public ReadTableEntry lookup(int key) {
        return (ReadTableEntry) this.table.lookup(key, (Object) null);
    }

    public ReaderDispatch() {
        RangeTable rangeTable;
        new RangeTable();
        this.table = rangeTable;
        this.kind = 5;
    }

    public ReaderDispatch(boolean nonTerminating) {
        RangeTable rangeTable;
        new RangeTable();
        this.table = rangeTable;
        this.kind = nonTerminating ? 6 : 5;
    }

    public static ReaderDispatch create(ReadTable rtable) {
        ReaderDispatch readerDispatch;
        Object obj;
        Object obj2;
        Object obj3;
        new ReaderDispatch();
        ReaderDispatch tab = readerDispatch;
        ReaderDispatchMisc entry = ReaderDispatchMisc.getInstance();
        tab.set(58, entry);
        tab.set(66, entry);
        tab.set(68, entry);
        tab.set(69, entry);
        tab.set(70, entry);
        tab.set(73, entry);
        tab.set(79, entry);
        tab.set(82, entry);
        tab.set(83, entry);
        tab.set(84, entry);
        tab.set(85, entry);
        tab.set(88, entry);
        tab.set(124, entry);
        tab.set(59, entry);
        tab.set(33, entry);
        tab.set(92, entry);
        tab.set(61, entry);
        tab.set(35, entry);
        tab.set(47, entry);
        new ReaderQuote(rtable.makeSymbol("function"));
        tab.set(39, obj);
        new ReaderVector(')');
        tab.set(40, obj2);
        new ReaderXmlElement();
        tab.set(60, obj3);
        return tab;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        int count;
        int ch;
        StringBuilder sb;
        StringBuilder sb2;
        Lexer in = lexer;
        int i3 = i;
        int i4 = i2;
        int i5 = -1;
        while (true) {
            count = i5;
            ch = in.read();
            if (ch < 0) {
                new StringBuilder();
                in.eofError(sb2.append("unexpected EOF after ").append((char) ch).toString());
            }
            if (ch > 65536) {
                break;
            }
            int digit = Character.digit((char) ch, 10);
            if (digit < 0) {
                ch = Character.toUpperCase((char) ch);
                break;
            } else if (count < 0) {
                i5 = digit;
            } else {
                i5 = (count * 10) + digit;
            }
        }
        ReadTableEntry entry = (ReadTableEntry) this.table.lookup(ch, (Object) null);
        if (entry != null) {
            return entry.read(in, ch, count);
        }
        int columnNumber = in.getColumnNumber();
        new StringBuilder();
        in.error('e', in.getName(), in.getLineNumber() + 1, columnNumber, sb.append("invalid dispatch character '").append((char) ch).append('\'').toString());
        return Values.empty;
    }
}
