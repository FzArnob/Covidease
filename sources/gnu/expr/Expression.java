package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.text.Printable;
import gnu.text.SourceLocator;
import java.io.PrintWriter;
import java.io.Writer;

public abstract class Expression extends Procedure0 implements Printable, SourceLocator {
    protected static final int NEXT_AVAIL_FLAG = 2;
    public static final int VALIDATED = 1;
    public static final Expression[] noExpressions = new Expression[0];
    String filename;
    protected int flags;
    int position;

    public abstract void compile(Compilation compilation, Target target);

    /* access modifiers changed from: protected */
    public abstract boolean mustCompile();

    public abstract void print(OutPort outPort);

    public Expression() {
    }

    public final Object eval(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        int start = ctx.startFromContext();
        try {
            int match0 = match0(ctx);
            return ctx.getFromContext(start);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(start);
            throw ex;
        }
    }

    /* JADX INFO: finally extract failed */
    public final Object eval(Environment env) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        Environment saveEnv = Environment.setSaveCurrent(env);
        try {
            Object eval = eval(ctx);
            Environment.restoreCurrent(saveEnv);
            return eval;
        } catch (Throwable th) {
            Throwable th2 = th;
            Environment.restoreCurrent(saveEnv);
            throw th2;
        }
    }

    public final int match0(CallContext callContext) {
        CallContext ctx = callContext;
        ctx.proc = this;
        ctx.f239pc = 0;
        return 0;
    }

    public final Object apply0() throws Throwable {
        CallContext ctx = CallContext.getInstance();
        check0(ctx);
        return ctx.runUntilValue();
    }

    public void apply(CallContext callContext) throws Throwable {
        Throwable th;
        StringBuilder sb;
        CallContext callContext2 = callContext;
        Throwable th2 = th;
        new StringBuilder();
        new RuntimeException(sb.append("internal error - ").append(getClass()).append(".eval called").toString());
        throw th2;
    }

    public final void print(Consumer consumer) {
        CharArrayOutPort charArrayOutPort;
        OutPort outPort;
        Consumer out = consumer;
        if (out instanceof OutPort) {
            print((OutPort) out);
        } else if (out instanceof PrintWriter) {
            new OutPort((Writer) (PrintWriter) out);
            OutPort port = outPort;
            print(port);
            port.close();
        } else {
            new CharArrayOutPort();
            CharArrayOutPort port2 = charArrayOutPort;
            print((OutPort) port2);
            port2.close();
            port2.writeTo(out);
        }
    }

    public void printLineColumn(OutPort outPort) {
        OutPort out = outPort;
        int line = getLineNumber();
        if (line > 0) {
            out.print("line:");
            out.print(line);
            int column = getColumnNumber();
            if (column > 0) {
                out.print(':');
                out.print(column);
            }
            out.writeSpaceFill();
        }
    }

    public final void compileWithPosition(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        int line = getLineNumber();
        if (line > 0) {
            comp.getCode().putLineNumber(getFileName(), line);
            compileNotePosition(comp, target2, this);
            return;
        }
        compile(comp, target2);
    }

    public final void compileWithPosition(Compilation compilation, Target target, Expression expression) {
        Compilation comp = compilation;
        Target target2 = target;
        Expression position2 = expression;
        int line = position2.getLineNumber();
        if (line > 0) {
            comp.getCode().putLineNumber(position2.getFileName(), line);
            compileNotePosition(comp, target2, position2);
            return;
        }
        compile(comp, target2);
    }

    public final void compileNotePosition(Compilation compilation, Target target, Expression position2) {
        Compilation comp = compilation;
        String saveFilename = comp.getFileName();
        int saveLine = comp.getLineNumber();
        int saveColumn = comp.getColumnNumber();
        comp.setLine(position2);
        compile(comp, target);
        comp.setLine(saveFilename, saveLine, saveColumn);
    }

    public final void compile(Compilation comp, Type type) {
        compile(comp, StackTarget.getInstance(type));
    }

    public final void compile(Compilation comp, Declaration lhs) {
        compile(comp, CheckedTarget.getInstance(lhs));
    }

    public static void compileButFirst(Expression expression, Compilation compilation) {
        BeginExp bexp;
        int n;
        Expression exp = expression;
        Compilation comp = compilation;
        if ((exp instanceof BeginExp) && (n = bexp.length) != 0) {
            Expression[] exps = (bexp = (BeginExp) exp).exps;
            compileButFirst(exps[0], comp);
            for (int i = 1; i < n; i++) {
                exps[i].compileWithPosition(comp, Target.Ignore);
            }
        }
    }

    public static Expression deepCopy(Expression expression, IdentityHashTable identityHashTable) {
        Expression exp = expression;
        IdentityHashTable mapper = identityHashTable;
        if (exp == null) {
            return null;
        }
        Object tr = mapper.get(exp);
        if (tr != null) {
            return (Expression) tr;
        }
        Expression copy = exp.deepCopy(mapper);
        Object put = mapper.put(exp, copy);
        return copy;
    }

    public static Expression[] deepCopy(Expression[] expressionArr, IdentityHashTable identityHashTable) {
        Expression[] exps = expressionArr;
        IdentityHashTable mapper = identityHashTable;
        if (exps == null) {
            return null;
        }
        int nargs = exps.length;
        Expression[] a = new Expression[nargs];
        for (int i = 0; i < nargs; i++) {
            Expression ei = exps[i];
            Expression ai = deepCopy(ei, mapper);
            if (ai == null && ei != null) {
                return null;
            }
            a[i] = ai;
        }
        return a;
    }

    protected static Expression deepCopy(Expression exp) {
        IdentityHashTable identityHashTable;
        new IdentityHashTable();
        return deepCopy(exp, identityHashTable);
    }

    /* access modifiers changed from: protected */
    public Expression deepCopy(IdentityHashTable identityHashTable) {
        IdentityHashTable identityHashTable2 = identityHashTable;
        return null;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitExpression(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls visitor, Type type, Declaration declaration) {
        Expression exp = applyExp;
        Type type2 = type;
        Declaration declaration2 = declaration;
        exp.args = visitor.visitExps(exp.args, null);
        return exp;
    }

    public static Expression makeWhile(Object cond, Object body, Compilation compilation) {
        LetExp letExp;
        Expression recurse;
        Expression expression;
        LambdaExp lambdaExp;
        Expression expression2;
        Expression expression3;
        Expression expression4;
        Expression expression5;
        Compilation parser = compilation;
        Expression[] inits = new Expression[1];
        new LetExp(inits);
        LetExp let = letExp;
        String fname = "%do%loop";
        Declaration fdecl = let.addDeclaration((Object) fname);
        new ReferenceExp(fdecl);
        new ApplyExp(expression, noExpressions);
        new LambdaExp();
        LambdaExp lexp = lambdaExp;
        parser.push((ScopeExp) lexp);
        new BeginExp(parser.parse(body), recurse);
        new IfExp(parser.parse(cond), expression3, QuoteExp.voidExp);
        lexp.body = expression2;
        lexp.setName(fname);
        parser.pop(lexp);
        inits[0] = lexp;
        fdecl.noteValue(lexp);
        new ReferenceExp(fdecl);
        new ApplyExp(expression5, noExpressions);
        let.setBody(expression4);
        return let;
    }

    public final void setLocation(SourceLocator sourceLocator) {
        SourceLocator location = sourceLocator;
        this.filename = location.getFileName();
        setLine(location.getLineNumber(), location.getColumnNumber());
    }

    public final Expression setLine(Expression old) {
        setLocation(old);
        return this;
    }

    public final void setFile(String filename2) {
        String str = filename2;
        this.filename = str;
    }

    public final void setLine(int i, int i2) {
        int lineno = i;
        int colno = i2;
        if (lineno < 0) {
            lineno = 0;
        }
        if (colno < 0) {
            colno = 0;
        }
        this.position = (lineno << 12) + colno;
    }

    public final void setLine(int lineno) {
        setLine(lineno, 0);
    }

    public final String getFileName() {
        return this.filename;
    }

    public void setLine(Compilation compilation) {
        Compilation comp = compilation;
        int line = comp.getLineNumber();
        if (line > 0) {
            setFile(comp.getFileName());
            setLine(line, comp.getColumnNumber());
        }
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return this.filename;
    }

    public final int getLineNumber() {
        int line = this.position >> 12;
        return line == 0 ? -1 : line;
    }

    public final int getColumnNumber() {
        int column = this.position & 4095;
        return column == 0 ? -1 : column;
    }

    public boolean isStableSourceLocation() {
        return true;
    }

    public Type getType() {
        return Type.pointer_type;
    }

    public boolean isSingleValue() {
        return OccurrenceType.itemCountIsOne(getType());
    }

    public Object valueIfConstant() {
        return null;
    }

    public void setFlag(boolean setting, int i) {
        int flag = i;
        if (setting) {
            this.flags |= flag;
            return;
        }
        this.flags &= flag ^ -1;
    }

    public void setFlag(int flag) {
        this.flags |= flag;
    }

    public int getFlags() {
        return this.flags;
    }

    public boolean getFlag(int flag) {
        return (this.flags & flag) != 0;
    }

    public boolean side_effects() {
        return true;
    }

    public String toString() {
        StringBuilder sb;
        String tname = getClass().getName();
        if (tname.startsWith("gnu.expr.")) {
            tname = tname.substring(9);
        }
        new StringBuilder();
        return sb.append(tname).append(GetNamedPart.CAST_METHOD_NAME).append(Integer.toHexString(hashCode())).toString();
    }
}
