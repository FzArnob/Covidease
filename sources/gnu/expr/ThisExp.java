package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;

public class ThisExp extends ReferenceExp {
    static int EVAL_TO_CONTEXT = 2;
    public static final String THIS_NAME;
    ScopeExp context;

    static {
        String str;
        new String("$this$");
        THIS_NAME = str;
    }

    public final boolean isForContext() {
        return (this.flags & EVAL_TO_CONTEXT) != 0;
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        if (isForContext()) {
            ctx.writeValue(this.context);
        } else {
            super.apply(ctx);
        }
    }

    public ScopeExp getContextScope() {
        return this.context;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThisExp() {
        super((Object) THIS_NAME);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThisExp(ScopeExp context2) {
        super((Object) THIS_NAME);
        this.context = context2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThisExp(Declaration binding) {
        super(THIS_NAME, binding);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ThisExp(gnu.bytecode.ClassType r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            gnu.expr.Declaration r3 = new gnu.expr.Declaration
            r7 = r3
            r3 = r7
            r4 = r7
            java.lang.String r5 = THIS_NAME
            r6 = r1
            r4.<init>((java.lang.Object) r5, (gnu.bytecode.Type) r6)
            r2.<init>((gnu.expr.Declaration) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ThisExp.<init>(gnu.bytecode.ClassType):void");
    }

    public static ThisExp makeGivingContext(ScopeExp context2) {
        ThisExp thisExp;
        new ThisExp(context2);
        ThisExp exp = thisExp;
        exp.flags |= EVAL_TO_CONTEXT;
        return exp;
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        if (!(target2 instanceof IgnoreTarget)) {
            if (isForContext()) {
                CodeAttr code = comp.getCode();
                if (comp.method.getStaticFlag()) {
                    code.emitGetStatic(comp.moduleInstanceMainField);
                } else {
                    code.emitPushThis();
                }
                target2.compileFromStack(comp, getType());
                return;
            }
            super.compile(comp, target2);
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitThisExp(this, d);
    }

    public final Type getType() {
        if (this.binding != null) {
            return this.binding.getType();
        }
        if ((this.context instanceof ClassExp) || (this.context instanceof ModuleExp)) {
            return this.context.getType();
        }
        return Type.pointer_type;
    }
}
