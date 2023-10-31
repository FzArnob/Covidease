package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderColon extends ReadTableEntry {
    public ReaderColon() {
    }

    public int getKind() {
        return 6;
    }

    public Object read(Lexer in, int i, int i2) throws IOException, SyntaxException {
        int ch = i;
        int i3 = i2;
        LispReader reader = (LispReader) in;
        ReadTable rtable = ReadTable.getCurrent();
        int startPos = reader.tokenBufferLength;
        if (ch == rtable.postfixLookupOperator) {
            int next = reader.read();
            if (next == 58) {
                return rtable.makeSymbol("::");
            }
            reader.tokenBufferAppend(ch);
            ch = next;
        }
        return reader.readAndHandleToken(ch, startPos, rtable);
    }
}
