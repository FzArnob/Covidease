package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import gnu.text.SourceLocator;

public class ReferenceExp extends AccessExp {
    public static final int DONT_DEREFERENCE = 2;
    public static final int PREFER_BINDING2 = 8;
    public static final int PROCEDURE_NAME = 4;
    public static final int TYPE_NAME = 16;
    static int counter;

    /* renamed from: id */
    int f59id;

    public final boolean getDontDereference() {
        return (this.flags & 2) != 0;
    }

    public final void setDontDereference(boolean setting) {
        setFlag(setting, 2);
    }

    public final boolean isUnknown() {
        return Declaration.isUnknown(this.binding);
    }

    public final boolean isProcedureName() {
        return (this.flags & 4) != 0;
    }

    public final void setProcedureName(boolean setting) {
        setFlag(setting, 4);
    }

    public ReferenceExp(Object symbol) {
        int i = counter + 1;
        counter = i;
        this.f59id = i;
        this.symbol = symbol;
    }

    public ReferenceExp(Object symbol, Declaration binding) {
        int i = counter + 1;
        counter = i;
        this.f59id = i;
        this.symbol = symbol;
        this.binding = binding;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReferenceExp(gnu.expr.Declaration r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            java.lang.Object r3 = r3.getSymbol()
            r4 = r1
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ReferenceExp.<init>(gnu.expr.Declaration):void");
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public final Object valueIfConstant() {
        Expression dvalue;
        if (this.binding == null || (dvalue = this.binding.getValue()) == null) {
            return null;
        }
        return dvalue.valueIfConstant();
    }

    public void apply(CallContext callContext) throws Throwable {
        Object value;
        Throwable th;
        Object value2;
        StringBuilder sb;
        Throwable th2;
        CallContext ctx = callContext;
        if (this.binding != null && this.binding.isAlias() && !getDontDereference() && (this.binding.value instanceof ReferenceExp)) {
            ReferenceExp rexp = (ReferenceExp) this.binding.value;
            if (rexp.getDontDereference() && rexp.binding != null) {
                Expression v = rexp.binding.getValue();
                if ((v instanceof QuoteExp) || (v instanceof ReferenceExp) || (v instanceof LambdaExp)) {
                    v.apply(ctx);
                    return;
                }
            }
            value2 = this.binding.value.eval(ctx);
        } else if (this.binding != null && this.binding.field != null && this.binding.field.getDeclaringClass().isExisting() && (!getDontDereference() || this.binding.isIndirectBinding())) {
            try {
                value2 = this.binding.field.getReflectField().get(this.binding.field.getStaticFlag() ? null : contextDecl().getValue().eval(ctx));
            } catch (Exception e) {
                new StringBuilder();
                Throwable th3 = th2;
                new UnboundLocationException((Object) sb.append("exception evaluating ").append(this.symbol).append(" from ").append(this.binding.field).append(" - ").append(e).toString(), (SourceLocator) this);
                throw th3;
            }
        } else if (this.binding != null && (((this.binding.value instanceof QuoteExp) || (this.binding.value instanceof LambdaExp)) && this.binding.value != QuoteExp.undefined_exp && (!getDontDereference() || this.binding.isIndirectBinding()))) {
            value2 = this.binding.value.eval(ctx);
        } else if (this.binding == null || ((this.binding.context instanceof ModuleExp) && !this.binding.isPrivate())) {
            Environment env = Environment.getCurrent();
            Symbol sym = this.symbol instanceof Symbol ? (Symbol) this.symbol : env.getSymbol(this.symbol.toString());
            Object property = (!getFlag(8) || !isProcedureName()) ? null : EnvironmentKey.FUNCTION;
            if (getDontDereference()) {
                value = env.getLocation(sym, property);
            } else {
                String unb = Location.UNBOUND;
                value = env.get(sym, property, unb);
                if (value == unb) {
                    Throwable th4 = th;
                    new UnboundLocationException((Object) sym, (SourceLocator) this);
                    throw th4;
                }
            }
            ctx.writeValue(value);
            return;
        } else {
            value2 = ctx.evalFrames[ScopeExp.nesting(this.binding.context)][this.binding.evalIndex];
        }
        if (!getDontDereference() && this.binding.isIndirectBinding()) {
            value2 = ((Location) value2).get();
        }
        ctx.writeValue(value2);
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        if (!(target2 instanceof ConsumerTarget) || !((ConsumerTarget) target2).compileWrite(this, comp)) {
            this.binding.load(this, this.flags, comp, target2);
        }
    }

    /* access modifiers changed from: protected */
    public Expression deepCopy(IdentityHashTable identityHashTable) {
        ReferenceExp referenceExp;
        IdentityHashTable mapper = identityHashTable;
        Declaration d = (Declaration) mapper.get(this.binding, this.binding);
        new ReferenceExp(mapper.get(this.symbol, this.symbol), d);
        ReferenceExp copy = referenceExp;
        copy.flags = getFlags();
        return copy;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitReferenceExp(this, d);
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
        QuoteExp quoteExp;
        Expression dval;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        Declaration declaration2 = declaration;
        Declaration decl = this.binding;
        if (decl != null && !decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
            Declaration decl2 = Declaration.followAliases(decl);
            if (!decl2.isIndirectBinding() && (dval = decl2.getValue()) != null) {
                return dval.validateApply(exp, visitor, required, decl2);
            }
        } else if (getSymbol() instanceof Symbol) {
            Object fval = Environment.getCurrent().getFunction((Symbol) getSymbol(), (Object) null);
            if (fval instanceof Procedure) {
                new QuoteExp(fval);
                return quoteExp.validateApply(exp, visitor, required, (Declaration) null);
            }
        }
        exp.visitArgs(visitor);
        return exp;
    }

    public void print(OutPort outPort) {
        OutPort ps = outPort;
        ps.print("(Ref/");
        ps.print(this.f59id);
        if (this.symbol != null && (this.binding == null || this.symbol.toString() != this.binding.getName())) {
            ps.print('/');
            ps.print(this.symbol);
        }
        if (this.binding != null) {
            ps.print('/');
            ps.print((Object) this.binding);
        }
        ps.print(")");
    }

    public Type getType() {
        Expression value;
        Declaration decl = this.binding;
        if (decl == null || decl.isFluid()) {
            return Type.pointer_type;
        }
        if (getDontDereference()) {
            return Compilation.typeLocation;
        }
        Declaration decl2 = Declaration.followAliases(decl);
        Type type = decl2.getType();
        if (!((type != null && type != Type.pointer_type) || (value = decl2.getValue()) == null || value == QuoteExp.undefined_exp)) {
            Expression save = decl2.value;
            decl2.value = null;
            type = value.getType();
            decl2.value = save;
        }
        if (type == Type.toStringType) {
            type = Type.javalangStringType;
        }
        return type;
    }

    public boolean isSingleValue() {
        if (this.binding == null || !this.binding.getFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE)) {
            return super.isSingleValue();
        }
        return true;
    }

    public boolean side_effects() {
        return this.binding == null || !this.binding.isLexical();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("RefExp/").append(this.symbol).append('/').append(this.f59id).append('/').toString();
    }
}
