package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.PrimProcedure;
import gnu.expr.ReferenceExp;
import gnu.mapping.Procedure;

/* compiled from: CompileNamedPart */
class GetNamedExp extends ApplyExp {
    static final Declaration castDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.Convert", "as");
    static final Declaration fieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "field");
    static final Declaration instanceOfDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "instanceOf");
    static final Declaration invokeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invoke");
    static final Declaration invokeStaticDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invokeStatic");
    static final Declaration makeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "make");
    static final Declaration staticFieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "staticField");
    public String combinedName;
    char kind;
    PrimProcedure[] methods;

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r2 = gnu.mapping.Environment.getCurrent();
        r3 = r2.getSymbol(r0.combinedName);
        r4 = gnu.mapping.Location.UNBOUND;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void apply(gnu.mapping.CallContext r12) throws java.lang.Throwable {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r7 = r0
            java.lang.String r7 = r7.combinedName
            if (r7 == 0) goto L_0x002d
            gnu.mapping.Environment r7 = gnu.mapping.Environment.getCurrent()
            r2 = r7
            r7 = r2
            r8 = r0
            java.lang.String r8 = r8.combinedName
            gnu.mapping.Symbol r7 = r7.getSymbol(r8)
            r3 = r7
            java.lang.String r7 = gnu.mapping.Location.UNBOUND
            r4 = r7
            r7 = 0
            r5 = r7
            r7 = r2
            r8 = r3
            r9 = r5
            r10 = r4
            java.lang.Object r7 = r7.get(r8, r9, r10)
            r6 = r7
            r7 = r6
            r8 = r4
            if (r7 == r8) goto L_0x002d
            r7 = r1
            r8 = r6
            r7.writeValue(r8)
        L_0x002c:
            return
        L_0x002d:
            r7 = r0
            r8 = r1
            super.apply(r8)
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.GetNamedExp.apply(gnu.mapping.CallContext):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetNamedExp(Expression[] args) {
        super((Procedure) GetNamedPart.getNamedPart, args);
    }

    /* access modifiers changed from: protected */
    public GetNamedExp setProcedureKind(char kind2) {
        this.type = Compilation.typeProcedure;
        this.kind = kind2;
        return this;
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
        Declaration decl;
        Expression[] xargs;
        ApplyExp applyExp2;
        Expression expression;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        Declaration declaration2 = declaration;
        Expression[] pargs = getArgs();
        Expression context = pargs[0];
        Expression[] args = exp.getArgs();
        switch (this.kind) {
            case 'C':
                decl = castDecl;
                xargs = new Expression[(args.length + 1)];
                System.arraycopy(args, 1, xargs, 2, args.length - 1);
                xargs[0] = context;
                xargs[1] = args[0];
                break;
            case 'I':
                decl = instanceOfDecl;
                xargs = new Expression[(args.length + 1)];
                System.arraycopy(args, 1, xargs, 2, args.length - 1);
                xargs[0] = args[0];
                xargs[1] = context;
                break;
            case 'M':
                decl = invokeDecl;
                xargs = new Expression[(args.length + 2)];
                xargs[0] = pargs[0];
                xargs[1] = pargs[1];
                System.arraycopy(args, 0, xargs, 2, args.length);
                break;
            case 'N':
                decl = makeDecl;
                xargs = new Expression[(args.length + 1)];
                System.arraycopy(args, 0, xargs, 1, args.length);
                xargs[0] = context;
                break;
            case 'S':
                decl = invokeStaticDecl;
                xargs = new Expression[(args.length + 2)];
                xargs[0] = context;
                xargs[1] = pargs[1];
                System.arraycopy(args, 0, xargs, 2, args.length);
                break;
            default:
                return exp;
        }
        new ReferenceExp(decl);
        new ApplyExp(expression, xargs);
        ApplyExp result = applyExp2;
        Expression line = result.setLine((Expression) exp);
        return visitor.visit((Expression) result, required);
    }

    public boolean side_effects() {
        if (this.kind == 'S' || this.kind == 'N' || this.kind == 'C' || this.kind == 'I') {
            return false;
        }
        if (this.kind == 'M') {
            return getArgs()[0].side_effects();
        }
        return true;
    }
}
