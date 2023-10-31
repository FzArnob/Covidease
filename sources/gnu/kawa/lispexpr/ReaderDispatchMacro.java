package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatchMacro extends ReaderMisc {
    Procedure procedure;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReaderDispatchMacro(Procedure procedure2) {
        super(5);
        this.procedure = procedure2;
    }

    public Procedure getProcedure() {
        return this.procedure;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        StringBuilder sb;
        Lexer in = lexer;
        int ch = i;
        int count = i2;
        try {
            return this.procedure.apply3(in.getPort(), Char.make(ch), IntNum.make(count));
        } catch (IOException e) {
            throw e;
        } catch (SyntaxException e2) {
            throw e2;
        } catch (Throwable th) {
            new StringBuilder();
            in.fatal(sb.append("reader macro '").append(this.procedure).append("' threw: ").append(th).toString());
            return null;
        }
    }
}
