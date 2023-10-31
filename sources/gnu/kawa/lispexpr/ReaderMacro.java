package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderMacro extends ReaderMisc {
    Procedure procedure;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReaderMacro(Procedure procedure2, boolean nonTerminating) {
        super(nonTerminating ? 6 : 5);
        Procedure procedure3 = procedure2;
        this.procedure = procedure3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReaderMacro(Procedure procedure2) {
        super(5);
        this.procedure = procedure2;
    }

    public boolean isNonTerminating() {
        return this.kind == 6;
    }

    public Procedure getProcedure() {
        return this.procedure;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        StringBuilder sb;
        Lexer in = lexer;
        int ch = i;
        int i3 = i2;
        try {
            return this.procedure.apply2(in.getPort(), Char.make(ch));
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
