package gnu.ecmascript;

import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;
import kawa.standard.Scheme;

public class Parser {
    public static final Expression[] emptyArgs = new Expression[0];
    static Expression emptyStatement;
    public static Expression eofExpr;
    public int errors;
    Lexer lexer;
    InPort port;
    Object previous_token;
    Object token;

    static {
        Expression expression;
        Expression expression2;
        new QuoteExp(Sequence.eofValue);
        eofExpr = expression;
        new QuoteExp(Values.empty);
        emptyStatement = expression2;
    }

    public Parser(InPort inPort) {
        Lexer lexer2;
        InPort port2 = inPort;
        this.port = port2;
        new Lexer(port2);
        this.lexer = lexer2;
    }

    public Expression parseConditionalExpression() throws IOException, SyntaxException {
        Expression expression;
        Expression exp1 = parseBinaryExpression(1);
        if (peekToken() != Lexer.condToken) {
            return exp1;
        }
        skipToken();
        Expression exp2 = parseAssignmentExpression();
        if (getToken() != Lexer.colonToken) {
            return syntaxError("expected ':' in conditional expression");
        }
        new IfExp(exp1, exp2, parseAssignmentExpression());
        return expression;
    }

    public Expression parseAssignmentExpression() throws IOException, SyntaxException {
        Expression expression;
        Expression expression2;
        SetExp setExp;
        Expression exp1 = parseConditionalExpression();
        Object token2 = peekToken();
        if (token2 == Lexer.equalToken) {
            skipToken();
            Expression exp2 = parseAssignmentExpression();
            if (!(exp1 instanceof ReferenceExp)) {
                return syntaxError("unmplemented non-symbol ihs in assignment");
            }
            new SetExp((Object) ((ReferenceExp) exp1).getName(), exp2);
            SetExp sex = setExp;
            sex.setDefining(true);
            return sex;
        } else if (!(token2 instanceof Reserved)) {
            return exp1;
        } else {
            Reserved op = (Reserved) token2;
            if (!op.isAssignmentOp()) {
                return exp1;
            }
            skipToken();
            Expression exp22 = parseAssignmentExpression();
            Expression[] expressionArr = new Expression[2];
            expressionArr[0] = exp1;
            Expression[] args = expressionArr;
            args[1] = exp22;
            new QuoteExp(op.proc);
            new ApplyExp(expression2, args);
            return expression;
        }
    }

    public Expression parseExpression() throws IOException, SyntaxException {
        Expression expression;
        Expression[] exps = null;
        int nExps = 0;
        while (true) {
            Expression exp1 = parseAssignmentExpression();
            boolean last = peekToken() != Lexer.commaToken;
            if (exps == null) {
                if (last) {
                    return exp1;
                }
                exps = new Expression[2];
            } else if (!last ? exps.length <= nExps : exps.length != nExps + 1) {
                Expression[] new_exps = new Expression[(last ? nExps + 1 : 2 * exps.length)];
                System.arraycopy(exps, 0, new_exps, 0, nExps);
                exps = new_exps;
            }
            int i = nExps;
            nExps++;
            exps[i] = exp1;
            if (last) {
                new BeginExp(exps);
                return expression;
            }
            skipToken();
        }
    }

    public Object peekTokenOrLine() throws IOException, SyntaxException {
        if (this.token == null) {
            this.token = this.lexer.getToken();
        }
        return this.token;
    }

    public Object peekToken() throws IOException, SyntaxException {
        if (this.token == null) {
            this.token = this.lexer.getToken();
        }
        while (this.token == Lexer.eolToken) {
            skipToken();
            this.token = this.lexer.getToken();
        }
        return this.token;
    }

    public Object getToken() throws IOException, SyntaxException {
        Object result = peekToken();
        skipToken();
        return result;
    }

    public final void skipToken() {
        if (this.token != Lexer.eofToken) {
            this.previous_token = this.token;
            this.token = null;
        }
    }

    public void getSemicolon() throws IOException, SyntaxException {
        this.token = peekToken();
        if (this.token == Lexer.semicolonToken) {
            skipToken();
        } else if (this.token != Lexer.rbraceToken && this.token != Lexer.eofToken && this.previous_token != Lexer.eolToken) {
            Expression syntaxError = syntaxError("missing ';' after expression");
        }
    }

    public Expression parsePrimaryExpression() throws IOException, SyntaxException {
        StringBuilder sb;
        StringBuilder sb2;
        Expression expression;
        Object result = getToken();
        if (result instanceof QuoteExp) {
            return (QuoteExp) result;
        }
        if (result instanceof String) {
            new ReferenceExp((Object) (String) result);
            return expression;
        } else if (result == Lexer.lparenToken) {
            Expression expr = parseExpression();
            Object token2 = getToken();
            if (token2 == Lexer.rparenToken) {
                return expr;
            }
            new StringBuilder();
            return syntaxError(sb2.append("expected ')' - got:").append(token2).toString());
        } else {
            new StringBuilder();
            return syntaxError(sb.append("unexpected token: ").append(result).toString());
        }
    }

    public Expression makePropertyAccessor(Expression expression, Expression expression2) {
        Expression expression3 = expression;
        Expression expression4 = expression2;
        return null;
    }

    public Expression[] parseArguments() throws IOException, SyntaxException {
        Vector vector;
        StringBuilder sb;
        skipToken();
        if (peekToken() == Lexer.rparenToken) {
            skipToken();
            return emptyArgs;
        }
        new Vector(10);
        Vector args = vector;
        while (true) {
            args.addElement(parseAssignmentExpression());
            Object token2 = getToken();
            if (token2 == Lexer.rparenToken) {
                Expression[] exps = new Expression[args.size()];
                args.copyInto(exps);
                return exps;
            } else if (token2 != Lexer.commaToken) {
                new StringBuilder();
                Expression syntaxError = syntaxError(sb.append("invalid token '").append(token2).append("' in argument list").toString());
            }
        }
    }

    public Expression makeNewExpression(Expression expression, Expression[] expressionArr) {
        Expression expression2;
        Expression expression3 = expression;
        Expression[] args = expressionArr;
        if (args == null) {
            args = emptyArgs;
        }
        new ApplyExp((Expression) null, args);
        return expression2;
    }

    public Expression makeCallExpression(Expression exp, Expression[] args) {
        Expression expression;
        new ApplyExp(exp, args);
        return expression;
    }

    public String getIdentifier() throws IOException, SyntaxException {
        Object token2 = getToken();
        if (token2 instanceof String) {
            return (String) token2;
        }
        Expression syntaxError = syntaxError("missing identifier");
        return "??";
    }

    public Expression parseLeftHandSideExpression() throws IOException, SyntaxException {
        StringBuilder sb;
        StringBuilder sb2;
        Expression expression;
        int newCount = 0;
        while (peekToken() == Lexer.newToken) {
            newCount++;
            skipToken();
        }
        Expression exp = parsePrimaryExpression();
        while (true) {
            Object token2 = peekToken();
            if (token2 == Lexer.dotToken) {
                skipToken();
                new QuoteExp(getIdentifier());
                exp = makePropertyAccessor(exp, expression);
            } else if (token2 == Lexer.lbracketToken) {
                skipToken();
                Expression prop = parseExpression();
                Object token3 = getToken();
                if (token3 != Lexer.rbracketToken) {
                    new StringBuilder();
                    return syntaxError(sb2.append("expected ']' - got:").append(token3).toString());
                }
                exp = makePropertyAccessor(exp, prop);
            } else if (token2 == Lexer.lparenToken) {
                Expression[] args = parseArguments();
                PrintStream printStream = System.err;
                new StringBuilder();
                printStream.println(sb.append("after parseArgs:").append(peekToken()).toString());
                if (newCount > 0) {
                    exp = makeNewExpression(exp, args);
                    newCount--;
                } else {
                    exp = makeCallExpression(exp, args);
                }
            } else {
                while (newCount > 0) {
                    exp = makeNewExpression(exp, (Expression[]) null);
                    newCount--;
                }
                return exp;
            }
        }
    }

    public Expression parsePostfixExpression() throws IOException, SyntaxException {
        Expression expression;
        Expression expression2;
        Expression exp = parseLeftHandSideExpression();
        Object op = peekTokenOrLine();
        if (op != Reserved.opPlusPlus && op != Reserved.opMinusMinus) {
            return exp;
        }
        skipToken();
        new QuoteExp(((Reserved) op).proc);
        new ApplyExp(expression2, exp);
        return expression;
    }

    public Expression parseUnaryExpression() throws IOException, SyntaxException {
        return parsePostfixExpression();
    }

    public Expression syntaxError(String str) {
        Expression expression;
        String message = str;
        this.errors++;
        OutPort err = OutPort.errDefault();
        String current_filename = this.port.getName();
        int current_line = this.port.getLineNumber() + 1;
        int current_column = this.port.getColumnNumber() + 1;
        if (current_line > 0) {
            if (current_filename != null) {
                err.print(current_filename);
            }
            err.print(':');
            err.print(current_line);
            if (current_column > 1) {
                err.print(':');
                err.print(current_column);
            }
            err.print(": ");
        }
        err.println(message);
        new ErrorExp(message);
        return expression;
    }

    public Expression parseBinaryExpression(int i) throws IOException, SyntaxException {
        Expression expression;
        Expression expression2;
        int prio = i;
        Expression parseUnaryExpression = parseUnaryExpression();
        while (true) {
            Expression exp1 = parseUnaryExpression;
            this.token = peekToken();
            if (!(this.token instanceof Reserved)) {
                return exp1;
            }
            Reserved op = (Reserved) this.token;
            if (op.prio < prio) {
                return exp1;
            }
            Object token2 = getToken();
            Expression exp2 = parseBinaryExpression(op.prio + 1);
            Expression[] expressionArr = new Expression[2];
            expressionArr[0] = exp1;
            Expression[] args = expressionArr;
            args[1] = exp2;
            parseUnaryExpression = expression;
            new QuoteExp(op.proc);
            new ApplyExp(expression2, args);
        }
    }

    public Expression parseIfStatement() throws IOException, SyntaxException {
        Expression else_part;
        Expression expression;
        StringBuilder sb;
        StringBuilder sb2;
        skipToken();
        Object token2 = getToken();
        if (token2 != Lexer.lparenToken) {
            new StringBuilder();
            return syntaxError(sb2.append("expected '(' - got:").append(token2).toString());
        }
        Expression test_part = parseExpression();
        Object token3 = getToken();
        if (token3 != Lexer.rparenToken) {
            new StringBuilder();
            return syntaxError(sb.append("expected ')' - got:").append(token3).toString());
        }
        Expression then_part = parseStatement();
        if (peekToken() == Lexer.elseToken) {
            skipToken();
            else_part = parseStatement();
        } else {
            else_part = null;
        }
        new IfExp(test_part, then_part, else_part);
        return expression;
    }

    public Expression buildLoop(Expression expression, Expression expression2, Expression expression3, Expression expression4) {
        Throwable th;
        Expression expression5;
        Expression init = expression;
        Expression test = expression2;
        Expression incr = expression3;
        Expression body = expression4;
        if (init != null) {
            new BeginExp(new Expression[]{init, buildLoop((Expression) null, test, incr, body)});
            return expression5;
        }
        Throwable th2 = th;
        new Error("not implemented - buildLoop");
        throw th2;
    }

    public Expression parseWhileStatement() throws IOException, SyntaxException {
        StringBuilder sb;
        StringBuilder sb2;
        skipToken();
        Object token2 = getToken();
        if (token2 != Lexer.lparenToken) {
            new StringBuilder();
            return syntaxError(sb2.append("expected '(' - got:").append(token2).toString());
        }
        Expression test_part = parseExpression();
        Object token3 = getToken();
        if (token3 != Lexer.rparenToken) {
            new StringBuilder();
            return syntaxError(sb.append("expected ')' - got:").append(token3).toString());
        }
        return buildLoop((Expression) null, test_part, (Expression) null, parseStatement());
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 116 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parseFunctionDefinition() throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r12 = this;
            r0 = r12
            r7 = r0
            r7.skipToken()
            r7 = r0
            java.lang.String r7 = r7.getIdentifier()
            r1 = r7
            r7 = r0
            java.lang.Object r7 = r7.getToken()
            r2 = r7
            r7 = r2
            gnu.text.Char r8 = gnu.ecmascript.Lexer.lparenToken
            if (r7 == r8) goto L_0x0035
            r7 = r0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r11 = r8
            r8 = r11
            r9 = r11
            r9.<init>()
            java.lang.String r9 = "expected '(' - got:"
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r2
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            gnu.expr.Expression r7 = r7.syntaxError(r8)
            r0 = r7
        L_0x0034:
            return r0
        L_0x0035:
            java.util.Vector r7 = new java.util.Vector
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 10
            r8.<init>(r9)
            r3 = r7
            r7 = r0
            java.lang.Object r7 = r7.peekToken()
            gnu.text.Char r8 = gnu.ecmascript.Lexer.rparenToken
            if (r7 != r8) goto L_0x009e
            r7 = r0
            r7.skipToken()
        L_0x004d:
            r7 = r0
            gnu.expr.Expression r7 = r7.parseBlock()
            r4 = r7
            gnu.expr.LambdaExp r7 = new gnu.expr.LambdaExp
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r4
            r8.<init>((gnu.expr.Expression) r9)
            r5 = r7
            r7 = r5
            r8 = r1
            r7.setName(r8)
            gnu.expr.SetExp r7 = new gnu.expr.SetExp
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r1
            r10 = r5
            r8.<init>((java.lang.Object) r9, (gnu.expr.Expression) r10)
            r6 = r7
            r7 = r6
            r8 = 1
            r7.setDefining(r8)
            r7 = r6
            r0 = r7
            goto L_0x0034
        L_0x0075:
            r7 = r2
            gnu.text.Char r8 = gnu.ecmascript.Lexer.commaToken
            if (r7 == r8) goto L_0x009e
            r7 = r0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r11 = r8
            r8 = r11
            r9 = r11
            r9.<init>()
            java.lang.String r9 = "invalid token '"
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r2
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = "' in argument list"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            gnu.expr.Expression r7 = r7.syntaxError(r8)
        L_0x009e:
            r7 = r0
            java.lang.String r7 = r7.getIdentifier()
            r4 = r7
            r7 = r3
            r8 = r4
            r7.addElement(r8)
            r7 = r0
            java.lang.Object r7 = r7.getToken()
            r2 = r7
            r7 = r2
            gnu.text.Char r8 = gnu.ecmascript.Lexer.rparenToken
            if (r7 != r8) goto L_0x0075
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.ecmascript.Parser.parseFunctionDefinition():gnu.expr.Expression");
    }

    public Expression parseBlock() throws IOException, SyntaxException {
        boolean last;
        Expression expression;
        Expression[] exps = null;
        if (getToken() != Lexer.lbraceToken) {
            return syntaxError("extened '{'");
        }
        int nExps = 0;
        while (true) {
            this.token = peekToken();
            if (this.token == Lexer.rbraceToken) {
                skipToken();
                if (exps == null) {
                    return emptyStatement;
                }
                last = true;
            } else {
                last = false;
            }
            if (exps == null) {
                exps = new Expression[2];
            } else if (!last ? exps.length <= nExps : exps.length != nExps) {
                Expression[] new_exps = new Expression[(last ? nExps : 2 * exps.length)];
                System.arraycopy(exps, 0, new_exps, 0, nExps);
                exps = new_exps;
            }
            if (last) {
                new BeginExp(exps);
                return expression;
            }
            int i = nExps;
            nExps++;
            exps[i] = parseStatement();
        }
    }

    public Expression parseStatement() throws IOException, SyntaxException {
        Object token2 = peekToken();
        if (token2 instanceof Reserved) {
            switch (((Reserved) token2).prio) {
                case 31:
                    return parseIfStatement();
                case 32:
                    return parseWhileStatement();
                case 41:
                    return parseFunctionDefinition();
            }
        }
        if (token2 == Lexer.eofToken) {
            return eofExpr;
        }
        if (token2 == Lexer.semicolonToken) {
            skipToken();
            return emptyStatement;
        } else if (token2 == Lexer.lbraceToken) {
            return parseBlock();
        } else {
            Expression exp = parseExpression();
            getSemicolon();
            return exp;
        }
    }

    public static void main(String[] strArr) {
        Object obj;
        Parser parser;
        StringBuilder sb;
        Procedure procedure;
        String[] strArr2 = strArr;
        new Scheme();
        Object obj2 = obj;
        InPort inp = InPort.inDefault();
        if (inp instanceof TtyInPort) {
            new Prompter();
            ((TtyInPort) inp).setPrompter(procedure);
        }
        new Parser(inp);
        Parser parser2 = parser;
        OutPort out = OutPort.outDefault();
        while (true) {
            try {
                Expression expr = parser2.parseStatement();
                if (expr != eofExpr) {
                    out.print("[Expression: ");
                    expr.print(out);
                    out.println("]");
                    Object result = expr.eval(Environment.user());
                    out.print("result: ");
                    out.print(result);
                    out.println();
                } else {
                    return;
                }
            } catch (Throwable th) {
                Throwable ex = th;
                PrintStream printStream = System.err;
                new StringBuilder();
                printStream.println(sb.append("caught exception:").append(ex).toString());
                ex.printStackTrace(System.err);
                return;
            }
        }
    }
}
