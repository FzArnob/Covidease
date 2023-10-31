package gnu.kawa.lispexpr;

import gnu.lists.PairWithPosition;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderQuote extends ReadTableEntry {
    Object magicSymbol;
    Object magicSymbol2;
    char next;

    public ReaderQuote(Object magicSymbol3) {
        this.magicSymbol = magicSymbol3;
    }

    public ReaderQuote(Object magicSymbol3, char next2, Object magicSymbol22) {
        this.next = next2;
        this.magicSymbol = magicSymbol3;
        this.magicSymbol2 = magicSymbol22;
    }

    public Object read(Lexer in, int i, int i2) throws IOException, SyntaxException {
        int i3 = i;
        int i4 = i2;
        LispReader reader = (LispReader) in;
        String file = reader.getName();
        int line1 = reader.getLineNumber() + 1;
        int column1 = reader.getColumnNumber() + 1;
        Object magic = this.magicSymbol;
        if (this.next != 0) {
            int ch = reader.read();
            if (ch == this.next) {
                magic = this.magicSymbol2;
            } else if (ch >= 0) {
                reader.unread(ch);
            }
        }
        int line2 = reader.getLineNumber() + 1;
        int column2 = reader.getColumnNumber() + 1;
        return PairWithPosition.make(magic, PairWithPosition.make(reader.readObject(), reader.makeNil(), file, line2, column2), file, line1, column1);
    }
}
