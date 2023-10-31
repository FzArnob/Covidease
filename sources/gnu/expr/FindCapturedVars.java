package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.Method;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.KeyPair;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindCapturedVars extends ExpExpVisitor<Void> {
    int backJumpPossible = 0;
    ModuleExp currentModule = null;
    Hashtable unknownDecls = null;

    public FindCapturedVars() {
    }

    public static void findCapturedVars(Expression exp, Compilation comp) {
        FindCapturedVars findCapturedVars;
        new FindCapturedVars();
        FindCapturedVars visitor = findCapturedVars;
        visitor.setContext(comp);
        Object visit = exp.visit(visitor, null);
    }

    /* access modifiers changed from: protected */
    public Expression visitApplyExp(ApplyExp applyExp, Void voidR) {
        ApplyExp exp = applyExp;
        Void ignored = voidR;
        int oldBackJumpPossible = this.backJumpPossible;
        boolean skipFunc = false;
        boolean skipArgs = false;
        if ((exp.func instanceof ReferenceExp) && Compilation.defaultCallConvention <= 1) {
            Declaration decl = Declaration.followAliases(((ReferenceExp) exp.func).binding);
            if (decl != null && (decl.context instanceof ModuleExp) && !decl.isPublic() && !decl.getFlag(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)) {
                Expression value = decl.getValue();
                if ((value instanceof LambdaExp) && !((LambdaExp) value).getNeedsClosureEnv()) {
                    skipFunc = true;
                }
            }
        } else if ((exp.func instanceof QuoteExp) && exp.getArgCount() > 0) {
            Object val = ((QuoteExp) exp.func).getValue();
            Expression arg0 = exp.getArg(0);
            if ((val instanceof PrimProcedure) && (arg0 instanceof ReferenceExp)) {
                PrimProcedure primProcedure = (PrimProcedure) val;
                Declaration decl2 = Declaration.followAliases(((ReferenceExp) arg0).binding);
                if (decl2 != null && (decl2.context instanceof ModuleExp) && !decl2.getFlag(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)) {
                    Expression value2 = decl2.getValue();
                    if (value2 instanceof ClassExp) {
                        Expression[] args = exp.getArgs();
                        if (!((LambdaExp) value2).getNeedsClosureEnv()) {
                            exp.nextCall = decl2.firstCall;
                            decl2.firstCall = exp;
                            for (int i = 1; i < args.length; i++) {
                                Object visit = args[i].visit(this, ignored);
                            }
                            skipArgs = true;
                            skipFunc = true;
                        }
                    }
                }
            }
        }
        if (!skipFunc) {
            exp.func = (Expression) exp.func.visit(this, ignored);
        }
        if (this.exitValue == null && !skipArgs) {
            exp.args = visitExps(exp.args, ignored);
        }
        if (this.backJumpPossible > oldBackJumpPossible) {
            exp.setFlag(8);
        }
        return exp;
    }

    public void visitDefaultArgs(LambdaExp lambdaExp, Void voidR) {
        LambdaExp exp = lambdaExp;
        Void ignored = voidR;
        if (exp.defaultArgs != null) {
            super.visitDefaultArgs(exp, ignored);
            Declaration firstDecl = exp.firstDecl();
            while (true) {
                Declaration param = firstDecl;
                if (param == null) {
                    return;
                }
                if (!param.isSimple()) {
                    exp.setFlag(true, 512);
                    return;
                }
                firstDecl = param.nextDecl();
            }
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitClassExp(ClassExp classExp, Void ignored) {
        ClassExp exp = classExp;
        Expression ret = (Expression) super.visitClassExp(exp, ignored);
        if (!exp.explicitInit && !exp.instanceType.isInterface()) {
            Method constructor = Compilation.getConstructor(exp.instanceType, exp);
        } else if (exp.getNeedsClosureEnv()) {
            LambdaExp lambdaExp = exp.firstChild;
            while (true) {
                LambdaExp child = lambdaExp;
                if (child == null) {
                    break;
                }
                if ("*init*".equals(child.getName())) {
                    child.setNeedsStaticLink(true);
                }
                lambdaExp = child.nextSibling;
            }
        }
        if (exp.isSimple() && exp.getNeedsClosureEnv() && exp.nameDecl != null && exp.nameDecl.getType() == Compilation.typeClass) {
            exp.nameDecl.setType(Compilation.typeClassType);
        }
        return ret;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public Expression visitModuleExp(ModuleExp moduleExp, Void ignored) {
        ModuleExp exp = moduleExp;
        ModuleExp saveModule = this.currentModule;
        Hashtable saveDecls = this.unknownDecls;
        this.currentModule = exp;
        this.unknownDecls = null;
        try {
            Expression visitLambdaExp = visitLambdaExp((LambdaExp) exp, ignored);
            this.currentModule = saveModule;
            this.unknownDecls = saveDecls;
            return visitLambdaExp;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.currentModule = saveModule;
            this.unknownDecls = saveDecls;
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeWarnNoDeclarationSeen(Object obj, Compilation compilation, SourceLocator sourceLocator) {
        StringBuilder sb;
        Object name = obj;
        Compilation comp = compilation;
        SourceLocator location = sourceLocator;
        if (comp.warnUndefinedVariable()) {
            new StringBuilder();
            comp.error('w', sb.append("no declaration seen for ").append(name).toString(), location);
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitFluidLetExp(FluidLetExp fluidLetExp, Void voidR) {
        FluidLetExp exp = fluidLetExp;
        Void ignored = voidR;
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                return (Expression) super.visitLetExp(exp, ignored);
            }
            if (decl.base == null) {
                Object name = decl.getSymbol();
                Declaration bind = allocUnboundDecl(name, false);
                maybeWarnNoDeclarationSeen(name, this.comp, exp);
                capture(bind);
                decl.base = bind;
            }
            firstDecl = decl.nextDecl();
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Void voidR) {
        LetExp exp = letExp;
        Void ignored = voidR;
        if (exp.body instanceof BeginExp) {
            Expression[] inits = exp.inits;
            int len = inits.length;
            Expression[] exps = ((BeginExp) exp.body).exps;
            int init_index = 0;
            Declaration decl = exp.firstDecl();
            for (int begin_index = 0; begin_index < exps.length && init_index < len; begin_index++) {
                Expression st = exps[begin_index];
                if (st instanceof SetExp) {
                    SetExp set = (SetExp) st;
                    if (set.binding == decl && inits[init_index] == QuoteExp.nullExp && set.isDefining()) {
                        Expression new_value = set.new_value;
                        if (((new_value instanceof QuoteExp) || (new_value instanceof LambdaExp)) && decl.getValue() == new_value) {
                            inits[init_index] = new_value;
                            exps[begin_index] = QuoteExp.voidExp;
                        }
                        init_index++;
                        decl = decl.nextDecl();
                    }
                }
            }
        }
        return (Expression) super.visitLetExp(exp, ignored);
    }

    static Expression checkInlineable(LambdaExp lambdaExp, Set<LambdaExp> set) {
        LambdaExp current = lambdaExp;
        Set<LambdaExp> seen = set;
        if (current.returnContinuation == LambdaExp.unknownContinuation) {
            return current.returnContinuation;
        }
        if (seen.contains(current)) {
            return current.returnContinuation;
        }
        if (current.getCanRead() || current.isClassMethod() || current.min_args != current.max_args) {
            current.returnContinuation = LambdaExp.unknownContinuation;
            return LambdaExp.unknownContinuation;
        }
        boolean add = seen.add(current);
        Expression r = current.returnContinuation;
        if (current.tailCallers != null) {
            for (LambdaExp p : current.tailCallers) {
                Expression t = checkInlineable(p, seen);
                if (t == LambdaExp.unknownContinuation) {
                    if (r == null || r == p.body) {
                        r = p.body;
                        current.inlineHome = p;
                    } else {
                        current.returnContinuation = LambdaExp.unknownContinuation;
                        return t;
                    }
                } else if (r == null) {
                    r = t;
                    if (current.inlineHome == null) {
                        current.inlineHome = current.nestedIn(p) ? p : p.inlineHome;
                    }
                } else if ((t != null && r != t) || current.getFlag(32)) {
                    current.returnContinuation = LambdaExp.unknownContinuation;
                    return LambdaExp.unknownContinuation;
                }
            }
        }
        return r;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, Void voidR) {
        Set set;
        LambdaExp exp = lambdaExp;
        Void ignored = voidR;
        new LinkedHashSet();
        if (checkInlineable(exp, set) != LambdaExp.unknownContinuation && (!(exp.outer instanceof ModuleExp) || exp.nameDecl == null)) {
            exp.setInlineOnly(true);
            this.backJumpPossible++;
        }
        return (Expression) super.visitLambdaExp(exp, ignored);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0239, code lost:
        if (((gnu.expr.ClassExp) r9).isSimple() == false) goto L_0x0270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x023b, code lost:
        r13 = r0.comp;
        r15 = r9.nameDecl;
        new java.lang.StringBuilder();
        r13.error('w', r15, "simple class ", r19.append(" requiring lexical link (because of reference to ").append(r1.getName()).append(") - use define-class instead").toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0270, code lost:
        r9.setNeedsStaticLink();
        r13 = r9.outerLambda();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x027c, code lost:
        r4.capture(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00bb, code lost:
        if (r0.comp.usingCPStyle() == false) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c0, code lost:
        if ((r2 instanceof gnu.expr.ModuleExp) == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c6, code lost:
        if (r2 != r4) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ca, code lost:
        r7 = r1.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d1, code lost:
        if (r7 == null) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d6, code lost:
        if ((r7 instanceof gnu.expr.LambdaExp) != false) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d8, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e2, code lost:
        if (r1.getFlag(android.support.p000v4.media.session.PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) == false) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e4, code lost:
        r13 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e6, code lost:
        r9 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e8, code lost:
        if (r9 != r4) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ed, code lost:
        if (r1.base == null) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ef, code lost:
        r1.base.setCanRead(true);
        capture(r1.base);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ff, code lost:
        r8 = (gnu.expr.LambdaExp) r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0108, code lost:
        if (r8.getInlineOnly() == false) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0111, code lost:
        if (r8.isHandlingTailCalls() == false) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0113, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0118, code lost:
        if (r8 != r2) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011f, code lost:
        if (r1.getCanRead() != false) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0126, code lost:
        if (r9.nameDecl == null) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0131, code lost:
        if (r9.nameDecl.getFlag(android.support.p000v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) == false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0133, code lost:
        r1.setFlag(android.support.p000v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x013a, code lost:
        r13 = r9.outerLambda();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0146, code lost:
        if (r1.getCanRead() != false) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x014d, code lost:
        if (r1.getCanCall() != false) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0150, code lost:
        if (r8 != null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0157, code lost:
        if (r1.isStatic() != false) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0159, code lost:
        r9 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0160, code lost:
        if (r1.isFluid() != false) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0162, code lost:
        r9.setImportsLexVars();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0166, code lost:
        r13 = r9.outerLambda();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x016e, code lost:
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0170, code lost:
        if (r11 == r4) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0173, code lost:
        if (r11 == null) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0175, code lost:
        r9 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x017c, code lost:
        if (r1.getCanRead() != false) goto L_0x01dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0180, code lost:
        if (r8 != r11) goto L_0x01dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0183, code lost:
        if (r4 != null) goto L_0x027c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0185, code lost:
        r13 = java.lang.System.err;
        new java.lang.StringBuilder();
        r13.println(r19.append("null declLambda for ").append(r1).append(" curL:").append(r2).toString());
        r13 = r1.context;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b5, code lost:
        r9 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b6, code lost:
        if (r9 == null) goto L_0x027c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01b8, code lost:
        r13 = java.lang.System.err;
        new java.lang.StringBuilder();
        r13.println(r19.append("- context:").append(r9).toString());
        r13 = r9.outer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01dd, code lost:
        r12 = r9.nameDecl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01e2, code lost:
        if (r12 == null) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01eb, code lost:
        if (r12.getFlag(android.support.p000v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) == false) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01ed, code lost:
        r13 = r0.comp;
        new java.lang.StringBuilder();
        r13.error('e', r19.append("static ").append(r9.getName()).append(" references non-static ").append(r1.getName()).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0229, code lost:
        if ((r9 instanceof gnu.expr.ClassExp) == false) goto L_0x0270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0230, code lost:
        if (r9.getName() == null) goto L_0x0270;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void capture(gnu.expr.Declaration r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r13 = r1
            boolean r13 = r13.getCanRead()
            if (r13 != 0) goto L_0x0013
            r13 = r1
            boolean r13 = r13.getCanCall()
            if (r13 != 0) goto L_0x0013
        L_0x0012:
            return
        L_0x0013:
            r13 = r1
            gnu.bytecode.Field r13 = r13.field
            if (r13 == 0) goto L_0x0022
            r13 = r1
            gnu.bytecode.Field r13 = r13.field
            boolean r13 = r13.getStaticFlag()
            if (r13 == 0) goto L_0x0022
            goto L_0x0012
        L_0x0022:
            r13 = r0
            gnu.expr.Compilation r13 = r13.comp
            boolean r13 = r13.immediate
            if (r13 == 0) goto L_0x0031
            r13 = r1
            boolean r13 = r13.hasConstantValue()
            if (r13 == 0) goto L_0x0031
            goto L_0x0012
        L_0x0031:
            r13 = r0
            gnu.expr.LambdaExp r13 = r13.getCurrentLambda()
            r2 = r13
            r13 = r1
            gnu.expr.ScopeExp r13 = r13.getContext()
            r3 = r13
            r13 = r3
            if (r13 != 0) goto L_0x0075
            java.lang.Error r13 = new java.lang.Error
            r19 = r13
            r13 = r19
            r14 = r19
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r19 = r15
            r15 = r19
            r16 = r19
            r16.<init>()
            java.lang.String r16 = "null context for "
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r1
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = " curL:"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r2
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            r14.<init>(r15)
            throw r13
        L_0x0075:
            r13 = r3
            gnu.expr.LambdaExp r13 = r13.currentLambda()
            r4 = r13
            r13 = 0
            r5 = r13
            r13 = 0
            r6 = r13
        L_0x007f:
            r13 = r2
            r14 = r4
            if (r13 == r14) goto L_0x00b4
            r13 = r2
            boolean r13 = r13.getInlineOnly()
            if (r13 == 0) goto L_0x00b4
            r13 = r2
            gnu.expr.LambdaExp r13 = r13.outerLambda()
            r7 = r13
            r13 = r7
            r14 = r5
            if (r13 == r14) goto L_0x009a
            r13 = r7
            gnu.expr.LambdaExp r13 = r13.firstChild
            r6 = r13
            r13 = r7
            r5 = r13
        L_0x009a:
            r13 = r6
            if (r13 == 0) goto L_0x00a2
            r13 = r2
            gnu.expr.LambdaExp r13 = r13.inlineHome
            if (r13 != 0) goto L_0x00a9
        L_0x00a2:
            r13 = r2
            r14 = 0
            r13.setCanCall(r14)
            goto L_0x0012
        L_0x00a9:
            r13 = r2
            gnu.expr.LambdaExp r13 = r13.getCaller()
            r2 = r13
            r13 = r6
            gnu.expr.LambdaExp r13 = r13.nextSibling
            r6 = r13
            goto L_0x007f
        L_0x00b4:
            r13 = r0
            gnu.expr.Compilation r13 = r13.comp
            boolean r13 = r13.usingCPStyle()
            if (r13 == 0) goto L_0x00c4
            r13 = r2
            boolean r13 = r13 instanceof gnu.expr.ModuleExp
            if (r13 == 0) goto L_0x00ca
            goto L_0x0012
        L_0x00c4:
            r13 = r2
            r14 = r4
            if (r13 != r14) goto L_0x00ca
            goto L_0x0012
        L_0x00ca:
            r13 = r1
            gnu.expr.Expression r13 = r13.getValue()
            r7 = r13
            r13 = r7
            if (r13 == 0) goto L_0x00d8
            r13 = r7
            boolean r13 = r13 instanceof gnu.expr.LambdaExp
            if (r13 != 0) goto L_0x00ff
        L_0x00d8:
            r13 = 0
            r8 = r13
        L_0x00da:
            r13 = r1
            r14 = 65536(0x10000, double:3.2379E-319)
            boolean r13 = r13.getFlag(r14)
            if (r13 == 0) goto L_0x00ea
            r13 = r2
            r9 = r13
        L_0x00e6:
            r13 = r9
            r14 = r4
            if (r13 != r14) goto L_0x0123
        L_0x00ea:
            r13 = r1
            gnu.expr.Declaration r13 = r13.base
            if (r13 == 0) goto L_0x0141
            r13 = r1
            gnu.expr.Declaration r13 = r13.base
            r14 = 1
            r13.setCanRead(r14)
            r13 = r0
            r14 = r1
            gnu.expr.Declaration r14 = r14.base
            r13.capture(r14)
        L_0x00fd:
            goto L_0x0012
        L_0x00ff:
            r13 = r7
            gnu.expr.LambdaExp r13 = (gnu.expr.LambdaExp) r13
            r8 = r13
            r13 = r8
            boolean r13 = r13.getInlineOnly()
            if (r13 == 0) goto L_0x010c
            goto L_0x0012
        L_0x010c:
            r13 = r8
            boolean r13 = r13.isHandlingTailCalls()
            if (r13 == 0) goto L_0x0116
            r13 = 0
            r8 = r13
            goto L_0x00da
        L_0x0116:
            r13 = r8
            r14 = r2
            if (r13 != r14) goto L_0x00da
            r13 = r1
            boolean r13 = r13.getCanRead()
            if (r13 != 0) goto L_0x00da
            goto L_0x0012
        L_0x0123:
            r13 = r9
            gnu.expr.Declaration r13 = r13.nameDecl
            if (r13 == 0) goto L_0x013a
            r13 = r9
            gnu.expr.Declaration r13 = r13.nameDecl
            r14 = 2048(0x800, double:1.0118E-320)
            boolean r13 = r13.getFlag(r14)
            if (r13 == 0) goto L_0x013a
            r13 = r1
            r14 = 2048(0x800, double:1.0118E-320)
            r13.setFlag(r14)
            goto L_0x00ea
        L_0x013a:
            r13 = r9
            gnu.expr.LambdaExp r13 = r13.outerLambda()
            r9 = r13
            goto L_0x00e6
        L_0x0141:
            r13 = r1
            boolean r13 = r13.getCanRead()
            if (r13 != 0) goto L_0x0152
            r13 = r1
            boolean r13 = r13.getCanCall()
            if (r13 != 0) goto L_0x0152
            r13 = r8
            if (r13 != 0) goto L_0x00fd
        L_0x0152:
            r13 = r1
            boolean r13 = r13.isStatic()
            if (r13 != 0) goto L_0x0182
            r13 = r2
            r9 = r13
            r13 = r1
            boolean r13 = r13.isFluid()
            if (r13 != 0) goto L_0x0166
            r13 = r9
            r13.setImportsLexVars()
        L_0x0166:
            r13 = r9
            gnu.expr.LambdaExp r13 = r13.outerLambda()
            r10 = r13
            r13 = r10
            r11 = r13
        L_0x016e:
            r13 = r11
            r14 = r4
            if (r13 == r14) goto L_0x0182
            r13 = r11
            if (r13 == 0) goto L_0x0182
            r13 = r11
            r9 = r13
            r13 = r1
            boolean r13 = r13.getCanRead()
            if (r13 != 0) goto L_0x01dd
            r13 = r8
            r14 = r11
            if (r13 != r14) goto L_0x01dd
        L_0x0182:
            r13 = r4
            if (r13 != 0) goto L_0x027c
            java.io.PrintStream r13 = java.lang.System.err
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r19 = r14
            r14 = r19
            r15 = r19
            r15.<init>()
            java.lang.String r15 = "null declLambda for "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r1
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " curL:"
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.println(r14)
            r13 = r1
            gnu.expr.ScopeExp r13 = r13.context
            r9 = r13
        L_0x01b5:
            r13 = r9
            if (r13 == 0) goto L_0x027c
            java.io.PrintStream r13 = java.lang.System.err
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r19 = r14
            r14 = r19
            r15 = r19
            r15.<init>()
            java.lang.String r15 = "- context:"
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r9
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.println(r14)
            r13 = r9
            gnu.expr.ScopeExp r13 = r13.outer
            r9 = r13
            goto L_0x01b5
        L_0x01dd:
            r13 = r9
            gnu.expr.Declaration r13 = r13.nameDecl
            r12 = r13
            r13 = r12
            if (r13 == 0) goto L_0x0226
            r13 = r12
            r14 = 2048(0x800, double:1.0118E-320)
            boolean r13 = r13.getFlag(r14)
            if (r13 == 0) goto L_0x0226
            r13 = r0
            gnu.expr.Compilation r13 = r13.comp
            r14 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r19 = r15
            r15 = r19
            r16 = r19
            r16.<init>()
            java.lang.String r16 = "static "
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r9
            java.lang.String r16 = r16.getName()
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = " references non-static "
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r1
            java.lang.String r16 = r16.getName()
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            r13.error(r14, r15)
        L_0x0226:
            r13 = r9
            boolean r13 = r13 instanceof gnu.expr.ClassExp
            if (r13 == 0) goto L_0x0270
            r13 = r9
            java.lang.String r13 = r13.getName()
            if (r13 == 0) goto L_0x0270
            r13 = r9
            gnu.expr.ClassExp r13 = (gnu.expr.ClassExp) r13
            boolean r13 = r13.isSimple()
            if (r13 == 0) goto L_0x0270
            r13 = r0
            gnu.expr.Compilation r13 = r13.comp
            r14 = 119(0x77, float:1.67E-43)
            r15 = r9
            gnu.expr.Declaration r15 = r15.nameDecl
            java.lang.String r16 = "simple class "
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r19 = r17
            r17 = r19
            r18 = r19
            r18.<init>()
            java.lang.String r18 = " requiring lexical link (because of reference to "
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r1
            java.lang.String r18 = r18.getName()
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r18 = ") - use define-class instead"
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            r13.error((char) r14, (gnu.expr.Declaration) r15, (java.lang.String) r16, (java.lang.String) r17)
        L_0x0270:
            r13 = r9
            r13.setNeedsStaticLink()
            r13 = r9
            gnu.expr.LambdaExp r13 = r13.outerLambda()
            r11 = r13
            goto L_0x016e
        L_0x027c:
            r13 = r4
            r14 = r1
            r13.capture(r14)
            goto L_0x00fd
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.FindCapturedVars.capture(gnu.expr.Declaration):void");
    }

    /* access modifiers changed from: package-private */
    public Declaration allocUnboundDecl(Object obj, boolean z) {
        Declaration decl;
        Hashtable hashtable;
        Object obj2;
        Object name = obj;
        boolean function = z;
        Object key = name;
        if (function && (name instanceof Symbol)) {
            if (!getCompilation().getLanguage().hasSeparateFunctionNamespace()) {
                function = false;
            } else {
                new KeyPair((Symbol) name, EnvironmentKey.FUNCTION);
                key = obj2;
            }
        }
        if (this.unknownDecls == null) {
            new Hashtable(100);
            this.unknownDecls = hashtable;
            decl = null;
        } else {
            decl = (Declaration) this.unknownDecls.get(key);
        }
        if (decl == null) {
            decl = this.currentModule.addDeclaration(name);
            decl.setSimple(false);
            decl.setPrivate(true);
            if (function) {
                decl.setProcedureDecl(true);
            }
            if (this.currentModule.isStatic()) {
                decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
            }
            decl.setCanRead(true);
            decl.setCanWrite(true);
            decl.setFlag(327680);
            decl.setIndirectBinding(true);
            Object put = this.unknownDecls.put(key, decl);
        }
        return decl;
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Void voidR) {
        ReferenceExp exp = referenceExp;
        Void voidR2 = voidR;
        Declaration decl = exp.getBinding();
        if (decl == null) {
            decl = allocUnboundDecl(exp.getSymbol(), exp.isProcedureName());
            exp.setBinding(decl);
        }
        if (decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) && this.comp.resolve(exp.getSymbol(), exp.isProcedureName()) == null) {
            maybeWarnNoDeclarationSeen(exp.getSymbol(), this.comp, exp);
        }
        capture(exp.contextDecl(), decl);
        return exp;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r3 = (gnu.expr.ReferenceExp) r2.value;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void capture(gnu.expr.Declaration r9, gnu.expr.Declaration r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r5 = r2
            boolean r5 = r5.isAlias()
            if (r5 == 0) goto L_0x0033
            r5 = r2
            gnu.expr.Expression r5 = r5.value
            boolean r5 = r5 instanceof gnu.expr.ReferenceExp
            if (r5 == 0) goto L_0x0033
            r5 = r2
            gnu.expr.Expression r5 = r5.value
            gnu.expr.ReferenceExp r5 = (gnu.expr.ReferenceExp) r5
            r3 = r5
            r5 = r3
            gnu.expr.Declaration r5 = r5.binding
            r4 = r5
            r5 = r4
            if (r5 == 0) goto L_0x0033
            r5 = r1
            if (r5 == 0) goto L_0x0028
            r5 = r4
            boolean r5 = r5.needsContext()
            if (r5 != 0) goto L_0x0033
        L_0x0028:
            r5 = r0
            r6 = r3
            gnu.expr.Declaration r6 = r6.contextDecl()
            r7 = r4
            r5.capture(r6, r7)
        L_0x0032:
            return
        L_0x0033:
            r5 = r2
            boolean r5 = r5.isFluid()
            if (r5 == 0) goto L_0x0046
            r5 = r2
            gnu.expr.ScopeExp r5 = r5.context
            boolean r5 = r5 instanceof gnu.expr.FluidLetExp
            if (r5 == 0) goto L_0x0046
            r5 = r2
            gnu.expr.Declaration r5 = r5.base
            r2 = r5
            goto L_0x0033
        L_0x0046:
            r5 = r1
            if (r5 == 0) goto L_0x0056
            r5 = r2
            boolean r5 = r5.needsContext()
            if (r5 == 0) goto L_0x0056
            r5 = r0
            r6 = r1
            r5.capture(r6)
        L_0x0055:
            goto L_0x0032
        L_0x0056:
            r5 = r0
            r6 = r2
            r5.capture(r6)
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.FindCapturedVars.capture(gnu.expr.Declaration, gnu.expr.Declaration):void");
    }

    /* access modifiers changed from: protected */
    public Expression visitThisExp(ThisExp thisExp, Void voidR) {
        ThisExp exp = thisExp;
        Void ignored = voidR;
        if (!exp.isForContext()) {
            return visitReferenceExp((ReferenceExp) exp, ignored);
        }
        getCurrentLambda().setImportsLexVars();
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Void voidR) {
        SetExp exp = setExp;
        Void ignored = voidR;
        Declaration decl = exp.binding;
        if (decl == null) {
            decl = allocUnboundDecl(exp.getSymbol(), exp.isFuncDef());
            exp.binding = decl;
        }
        if (!decl.ignorable()) {
            if (!exp.isDefining()) {
                decl = Declaration.followAliases(decl);
            }
            capture(exp.contextDecl(), decl);
        }
        return (Expression) super.visitSetExp(exp, ignored);
    }
}
