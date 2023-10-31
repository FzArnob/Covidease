package gnu.expr;

import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.AbstractFormat;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import gnu.text.SourceLocator;

public class QuoteExp extends Expression {
    public static final int EXPLICITLY_TYPED = 2;
    public static final int SHARED_CONSTANT = 4;
    public static QuoteExp abstractExp = makeShared(Special.abstractSpecial);
    public static final QuoteExp classObjectExp = makeShared(Type.objectType);
    public static QuoteExp falseExp = makeShared(Boolean.FALSE);
    public static QuoteExp nullExp = makeShared((Object) null, Type.nullType);
    public static QuoteExp trueExp = makeShared(Boolean.TRUE);
    public static QuoteExp undefined_exp = makeShared(Special.undefined);
    public static QuoteExp voidExp = makeShared(Values.empty, Type.voidType);
    protected Type type;
    Object value;

    public final Object getValue() {
        return this.value;
    }

    public final Object valueIfConstant() {
        return this.value;
    }

    public final Type getRawType() {
        return this.type;
    }

    public final Type getType() {
        if (this.type != null) {
            return this.type;
        }
        if (this.value == Values.empty) {
            return Type.voidType;
        }
        if (this.value == null) {
            return Type.nullType;
        }
        if (this == undefined_exp) {
            return Type.pointer_type;
        }
        return Type.make(this.value.getClass());
    }

    public void setType(Type type2) {
        this.type = type2;
        setFlag(2);
    }

    public boolean isExplicitlyTyped() {
        return getFlag(2);
    }

    public boolean isSharedConstant() {
        return getFlag(4);
    }

    public static QuoteExp getInstance(Object value2) {
        return getInstance(value2, (SourceLocator) null);
    }

    public static QuoteExp getInstance(Object obj, SourceLocator sourceLocator) {
        QuoteExp quoteExp;
        Object value2 = obj;
        SourceLocator position = sourceLocator;
        if (value2 == null) {
            return nullExp;
        }
        if (value2 == Type.pointer_type) {
            return classObjectExp;
        }
        if (value2 == Special.undefined) {
            return undefined_exp;
        }
        if (value2 == Values.empty) {
            return voidExp;
        }
        if (value2 instanceof Boolean) {
            return ((Boolean) value2).booleanValue() ? trueExp : falseExp;
        }
        new QuoteExp(value2);
        QuoteExp q = quoteExp;
        if (position != null) {
            q.setLocation(position);
        }
        return q;
    }

    static QuoteExp makeShared(Object value2) {
        QuoteExp quoteExp;
        new QuoteExp(value2);
        QuoteExp exp = quoteExp;
        exp.setFlag(4);
        return exp;
    }

    static QuoteExp makeShared(Object value2, Type type2) {
        QuoteExp quoteExp;
        new QuoteExp(value2, type2);
        QuoteExp exp = quoteExp;
        exp.setFlag(4);
        return exp;
    }

    public QuoteExp(Object val) {
        this.value = val;
    }

    public QuoteExp(Object val, Type type2) {
        this.value = val;
        setType(type2);
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void apply(CallContext ctx) {
        ctx.writeValue(this.value);
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        if (this.type == null || this.type == Type.pointer_type || (target2 instanceof IgnoreTarget) || ((this.type instanceof ObjectType) && this.type.isInstance(this.value))) {
            comp.compileConstant(this.value, target2);
            return;
        }
        comp.compileConstant(this.value, StackTarget.getInstance(this.type));
        target2.compileFromStack(comp, this.type);
    }

    public Expression deepCopy(IdentityHashTable identityHashTable) {
        IdentityHashTable identityHashTable2 = identityHashTable;
        return this;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitQuoteExp(this, d);
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type2, Declaration declaration) {
        ApplyExp nexp;
        ApplyExp applyExp2;
        Expression expression;
        ApplyExp applyExp3;
        ApplyExp applyExp4;
        Expression e;
        String str;
        StringBuilder sb;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type2;
        Declaration decl = declaration;
        if (this == undefined_exp) {
            return exp;
        }
        Object fval = getValue();
        if (!(fval instanceof Procedure)) {
            InlineCalls inlineCalls2 = visitor;
            if (decl == null || fval == null) {
                str = "called value is not a procedure";
            } else {
                new StringBuilder();
                str = sb.append("calling ").append(decl.getName()).append(" which is a ").append(fval.getClass().getName()).toString();
            }
            return inlineCalls2.noteError(str);
        }
        Procedure proc = (Procedure) fval;
        int nargs = exp.getArgCount();
        String msg = WrongArguments.checkArgCount(proc, nargs);
        if (msg != null) {
            return visitor.noteError(msg);
        }
        Expression inlined = visitor.maybeInline(exp, required, proc);
        if (inlined != null) {
            return inlined;
        }
        Expression[] args = exp.args;
        MethodProc asMProc = proc instanceof MethodProc ? (MethodProc) proc : null;
        for (int i = 0; i < nargs; i++) {
            Type ptype = asMProc != null ? asMProc.getParameterType(i) : null;
            if (i == nargs - 1 && ptype != null && asMProc.maxArgs() < 0 && i == asMProc.minArgs()) {
                ptype = null;
            }
            args[i] = visitor.visit(args[i], ptype);
        }
        if (exp.getFlag(4) && (e = exp.inlineIfConstant(proc, visitor)) != exp) {
            return visitor.visit(e, required);
        }
        Compilation comp = visitor.getCompilation();
        if (comp.inlineOk(proc)) {
            if (ApplyExp.asInlineable(proc) != null) {
                if (exp.getFunction() == this) {
                    return exp;
                }
                new ApplyExp((Expression) this, exp.getArgs());
                return applyExp4.setLine((Expression) exp);
            }
            PrimProcedure mproc = PrimProcedure.getMethodFor(proc, decl, exp.args, comp.getLanguage());
            if (mproc != null) {
                if (mproc.getStaticFlag() || decl == null) {
                    new ApplyExp((Procedure) mproc, exp.args);
                    nexp = applyExp2;
                } else if (decl.base == null) {
                    return exp;
                } else {
                    Expression[] margs = new Expression[(1 + nargs)];
                    System.arraycopy(exp.getArgs(), 0, margs, 1, nargs);
                    new ReferenceExp(decl.base);
                    margs[0] = expression;
                    new ApplyExp((Procedure) mproc, margs);
                    nexp = applyExp3;
                }
                return nexp.setLine((Expression) exp);
            }
        }
        return exp;
    }

    public boolean side_effects() {
        return false;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("QuoteExp[").append(this.value).append("]").toString();
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.startLogicalBlock("(Quote", ")", 2);
        out.writeSpaceLinear();
        Object val = this.value;
        if (val instanceof Expression) {
            val = val.toString();
        }
        AbstractFormat saveFormat = out.objectFormat;
        try {
            out.objectFormat = Language.getDefaultLanguage().getFormat(true);
            out.print(val);
            if (this.type != null) {
                out.print(" ::");
                out.print(this.type.getName());
            }
            out.objectFormat = saveFormat;
            out.endLogicalBlock(")");
        } catch (Throwable th) {
            Throwable th2 = th;
            out.objectFormat = saveFormat;
            throw th2;
        }
    }
}
