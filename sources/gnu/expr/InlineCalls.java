package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import java.lang.reflect.InvocationTargetException;

public class InlineCalls extends ExpExpVisitor<Type> {
    private static Class[] inlinerMethodArgTypes;

    public static Expression inlineCalls(Expression exp, Compilation comp) {
        InlineCalls visitor;
        new InlineCalls(comp);
        return visitor.visit(exp, (Type) null);
    }

    public InlineCalls(Compilation comp) {
        setContext(comp);
    }

    public Expression visit(Expression expression, Type type) {
        Expression exp = expression;
        Type required = type;
        if (!exp.getFlag(1)) {
            exp.setFlag(1);
            exp = (Expression) super.visit(exp, required);
            exp.setFlag(1);
        }
        return checkType(exp, required);
    }

    public Expression checkType(Expression expression, Type type) {
        boolean incompatible;
        Expression converted;
        StringBuilder sb;
        Method amethod;
        ObjectExp objectExp;
        Expression expression2;
        Expression exp = expression;
        Type required = type;
        Type expType = exp.getType();
        if (!(required instanceof ClassType) || !((ClassType) required).isInterface() || !expType.isSubtype(Compilation.typeProcedure) || expType.isSubtype(required)) {
            if (expType == Type.toStringType) {
                expType = Type.javalangStringType;
            }
            incompatible = required != null && required.compare(expType) == -3;
            if (incompatible && (required instanceof TypeValue) && (converted = ((TypeValue) required).convertValue(exp)) != null) {
                return converted;
            }
        } else if (!(exp instanceof LambdaExp) || (amethod = ((ClassType) required).checkSingleAbstractMethod()) == null) {
            incompatible = true;
        } else {
            LambdaExp lexp = (LambdaExp) exp;
            new ObjectExp();
            ObjectExp oexp = objectExp;
            oexp.setLocation(exp);
            Expression[] expressionArr = new Expression[1];
            new QuoteExp(required);
            expressionArr[0] = expression2;
            oexp.supers = expressionArr;
            oexp.setTypes(getCompilation());
            String mname = amethod.getName();
            Declaration addMethod = oexp.addMethod(lexp, mname);
            Declaration addDeclaration = oexp.addDeclaration(mname, Compilation.typeProcedure);
            oexp.firstChild = lexp;
            oexp.declareParts(this.comp);
            return visit((Expression) oexp, required);
        }
        if (incompatible) {
            Language language = this.comp.getLanguage();
            Compilation compilation = this.comp;
            new StringBuilder();
            compilation.error('w', sb.append("type ").append(language.formatType(expType)).append(" is incompatible with required type ").append(language.formatType(required)).toString(), exp);
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitApplyExp(ApplyExp applyExp, Type type) {
        ApplyExp exp = applyExp;
        Type required = type;
        Expression func = exp.func;
        if (func instanceof LambdaExp) {
            LambdaExp lambdaExp = (LambdaExp) func;
            Expression inlined = inlineCall((LambdaExp) func, exp.args, false);
            if (inlined != null) {
                return visit(inlined, required);
            }
        }
        Expression func2 = visit(func, (Type) null);
        exp.func = func2;
        return func2.validateApply(exp, this, required, (Declaration) null);
    }

    public final Expression visitApplyOnly(ApplyExp applyExp, Type required) {
        ApplyExp exp = applyExp;
        return exp.func.validateApply(exp, this, required, (Declaration) null);
    }

    public static Integer checkIntValue(Expression expression) {
        Expression exp = expression;
        if (exp instanceof QuoteExp) {
            QuoteExp qarg = (QuoteExp) exp;
            Object value = qarg.getValue();
            if (!qarg.isExplicitlyTyped() && (value instanceof IntNum)) {
                IntNum ivalue = (IntNum) value;
                if (ivalue.inIntRange()) {
                    return Integer.valueOf(ivalue.intValue());
                }
            }
        }
        return null;
    }

    public static Long checkLongValue(Expression expression) {
        Expression exp = expression;
        if (exp instanceof QuoteExp) {
            QuoteExp qarg = (QuoteExp) exp;
            Object value = qarg.getValue();
            if (!qarg.isExplicitlyTyped() && (value instanceof IntNum)) {
                IntNum ivalue = (IntNum) value;
                if (ivalue.inLongRange()) {
                    return Long.valueOf(ivalue.longValue());
                }
            }
        }
        return null;
    }

    public QuoteExp fixIntValue(Expression exp) {
        QuoteExp quoteExp;
        Integer ival = checkIntValue(exp);
        if (ival == null) {
            return null;
        }
        new QuoteExp(ival, this.comp.getLanguage().getTypeFor(Integer.TYPE));
        return quoteExp;
    }

    public QuoteExp fixLongValue(Expression exp) {
        QuoteExp quoteExp;
        Long ival = checkLongValue(exp);
        if (ival == null) {
            return null;
        }
        new QuoteExp(ival, this.comp.getLanguage().getTypeFor(Long.TYPE));
        return quoteExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitQuoteExp(QuoteExp quoteExp, Type type) {
        QuoteExp exp = quoteExp;
        Type required = type;
        if (exp.getRawType() == null && !exp.isSharedConstant()) {
            Object value = exp.getValue();
            Object value2 = value;
            if (value != null) {
                Type vtype = this.comp.getLanguage().getTypeFor((Class) value2.getClass());
                if (vtype == Type.toStringType) {
                    vtype = Type.javalangStringType;
                }
                exp.type = vtype;
                if (required instanceof PrimType) {
                    char sig1 = required.getSignature().charAt(0);
                    QuoteExp ret = sig1 == 'I' ? fixIntValue(exp) : sig1 == 'J' ? fixLongValue(exp) : null;
                    if (ret != null) {
                        exp = ret;
                    }
                }
            }
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Type type) {
        StringBuilder sb;
        ReferenceExp exp = referenceExp;
        Type required = type;
        Declaration decl = exp.getBinding();
        if (decl != null && decl.field == null && !decl.getCanWrite()) {
            Expression dval = decl.getValue();
            if ((dval instanceof QuoteExp) && dval != QuoteExp.undefined_exp) {
                return visitQuoteExp((QuoteExp) dval, required);
            }
            if ((dval instanceof ReferenceExp) && !decl.isAlias()) {
                ReferenceExp rval = (ReferenceExp) dval;
                Declaration rdecl = rval.getBinding();
                Type dtype = decl.getType();
                if (rdecl != null && !rdecl.getCanWrite() && ((dtype == null || dtype == Type.pointer_type || dtype == rdecl.getType()) && !rval.getDontDereference())) {
                    return visitReferenceExp(rval, required);
                }
            }
            if (!exp.isProcedureName() && (decl.flags & 1048704) == 1048704) {
                Compilation compilation = this.comp;
                new StringBuilder();
                compilation.error('e', sb.append("unimplemented: reference to method ").append(decl.getName()).append(" as variable").toString());
                this.comp.error('e', decl, "here is the definition of ", "");
            }
        }
        return (Expression) super.visitReferenceExp(exp, required);
    }

    /* access modifiers changed from: protected */
    public Expression visitIfExp(IfExp ifExp, Type type) {
        StringBuilder sb;
        Expression expression;
        Declaration decl;
        IfExp exp = ifExp;
        Type required = type;
        Expression test = (Expression) exp.test.visit(this, null);
        if ((test instanceof ReferenceExp) && (decl = ((ReferenceExp) test).getBinding()) != null) {
            Expression value = decl.getValue();
            if ((value instanceof QuoteExp) && value != QuoteExp.undefined_exp) {
                test = value;
            }
        }
        exp.test = test;
        if (this.exitValue == null) {
            exp.then_clause = visit(exp.then_clause, required);
        }
        if (this.exitValue == null && exp.else_clause != null) {
            exp.else_clause = visit(exp.else_clause, required);
        }
        if (test instanceof QuoteExp) {
            return exp.select(this.comp.getLanguage().isTrue(((QuoteExp) test).getValue()));
        } else if (!test.getType().isVoid()) {
            return exp;
        } else {
            boolean truth = this.comp.getLanguage().isTrue(Values.empty);
            Compilation compilation = this.comp;
            new StringBuilder();
            compilation.error('w', sb.append("void-valued condition is always ").append(truth).toString());
            new BeginExp(test, exp.select(truth));
            return expression;
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitBeginExp(BeginExp beginExp, Type type) {
        BeginExp exp = beginExp;
        Type required = type;
        int last = exp.length - 1;
        int i = 0;
        while (i <= last) {
            exp.exps[i] = visit(exp.exps[i], i < last ? null : required);
            i++;
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitScopeExp(ScopeExp scopeExp, Type type) {
        Type type2;
        ScopeExp exp = scopeExp;
        Type type3 = type;
        exp.visitChildren(this, null);
        visitDeclarationTypes(exp);
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                return exp;
            }
            if (decl.type == null) {
                Expression val = decl.getValue();
                decl.type = Type.objectType;
                Declaration declaration = decl;
                if (val == null || val == QuoteExp.undefined_exp) {
                    type2 = Type.objectType;
                } else {
                    type2 = val.getType();
                }
                declaration.setType(type2);
            }
            firstDecl = decl.nextDecl();
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Type type) {
        ReferenceExp ref;
        Declaration d;
        LetExp exp = letExp;
        Type required = type;
        Declaration decl = exp.firstDecl();
        int n = exp.inits.length;
        int i = 0;
        while (i < n) {
            Expression init0 = exp.inits[i];
            boolean typeSpecified = decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            Expression init = visit(init0, (!typeSpecified || init0 == QuoteExp.undefined_exp) ? null : decl.getType());
            exp.inits[i] = init;
            if (decl.value == init0) {
                Expression expression = init;
                Expression dvalue = expression;
                decl.value = expression;
                if (!typeSpecified) {
                    decl.setType(dvalue.getType());
                }
            }
            i++;
            decl = decl.nextDecl();
        }
        if (this.exitValue == null) {
            exp.body = visit(exp.body, required);
        }
        if (!(exp.body instanceof ReferenceExp) || (d = ref.getBinding()) == null || d.context != exp || (ref = (ReferenceExp) exp.body).getDontDereference() || n != 1) {
            return exp;
        }
        Expression init2 = exp.inits[0];
        Expression texp = d.getTypeExp();
        if (texp != QuoteExp.classObjectExp) {
            init2 = visitApplyOnly(Compilation.makeCoercion(init2, texp), (Type) null);
        }
        return init2;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, Type type) {
        LambdaExp exp = lambdaExp;
        Type required = type;
        Declaration firstDecl = exp.firstDecl();
        if (firstDecl != null && firstDecl.isThisParameter() && !exp.isClassMethod() && firstDecl.type == null) {
            firstDecl.setType(this.comp.mainClass);
        }
        return visitScopeExp((ScopeExp) exp, required);
    }

    /* access modifiers changed from: protected */
    public Expression visitTryExp(TryExp tryExp, Type type) {
        TryExp exp = tryExp;
        Type required = type;
        if (exp.getCatchClauses() == null && exp.getFinallyClause() == null) {
            return visit(exp.try_clause, required);
        }
        return (Expression) super.visitTryExp(exp, required);
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExpValue(Expression new_value, Type type, Declaration declaration) {
        Type type2 = type;
        Declaration decl = declaration;
        return visit(new_value, (decl == null || decl.isAlias()) ? null : decl.type);
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Type required) {
        StringBuilder sb;
        SetExp exp = setExp;
        Declaration decl = exp.getBinding();
        Object visitSetExp = super.visitSetExp(exp, required);
        if (!exp.isDefining() && decl != null && (decl.flags & 1048704) == 1048704) {
            Compilation compilation = this.comp;
            new StringBuilder();
            compilation.error('e', sb.append("can't assign to method ").append(decl.getName()).toString(), exp);
        }
        if (decl != null && decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI) && CompileReflect.checkKnownClass(decl.getType(), this.comp) < 0) {
            decl.setType(Type.errorType);
        }
        return exp;
    }

    private static synchronized Class[] getInlinerMethodArgTypes() throws Exception {
        Class[] t;
        synchronized (InlineCalls.class) {
            Class[] t2 = inlinerMethodArgTypes;
            if (t2 == null) {
                Class[] clsArr = new Class[4];
                clsArr[0] = Class.forName("gnu.expr.ApplyExp");
                Class[] clsArr2 = clsArr;
                clsArr2[1] = Class.forName("gnu.expr.InlineCalls");
                Class[] clsArr3 = clsArr2;
                clsArr3[2] = Class.forName("gnu.bytecode.Type");
                Class[] clsArr4 = clsArr3;
                clsArr4[3] = Class.forName("gnu.mapping.Procedure");
                t2 = clsArr4;
                inlinerMethodArgTypes = t2;
            }
            t = t2;
        }
        return t;
    }

    /* JADX INFO: finally extract failed */
    public Expression maybeInline(ApplyExp applyExp, Type type, Procedure procedure) {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        ApplyExp exp = applyExp;
        Type required = type;
        Procedure proc = procedure;
        Procedure procedure2 = proc;
        Procedure procedure3 = procedure2;
        try {
            synchronized (procedure2) {
                java.lang.reflect.Method inliner = proc.getProperty(Procedure.validateApplyKey, (Object) null);
                if (inliner instanceof String) {
                    String inliners = (String) inliner;
                    int colon = inliners.indexOf(58);
                    java.lang.reflect.Method method = null;
                    if (colon > 0) {
                        method = Class.forName(inliners.substring(0, colon), true, proc.getClass().getClassLoader()).getDeclaredMethod(inliners.substring(colon + 1), getInlinerMethodArgTypes());
                    }
                    if (method == null) {
                        new StringBuilder();
                        error('e', sb2.append("inliner property string for ").append(proc).append(" is not of the form CLASS:METHOD").toString());
                        return null;
                    }
                    inliner = method;
                }
                if (inliner != null) {
                    Object[] objArr = new Object[4];
                    objArr[0] = exp;
                    Object[] objArr2 = objArr;
                    objArr2[1] = this;
                    Object[] objArr3 = objArr2;
                    objArr3[2] = required;
                    Object[] objArr4 = objArr3;
                    objArr4[3] = proc;
                    Object[] vargs = objArr4;
                    if (inliner instanceof Procedure) {
                        return (Expression) ((Procedure) inliner).applyN(vargs);
                    }
                    if (inliner instanceof java.lang.reflect.Method) {
                        return (Expression) ((java.lang.reflect.Method) inliner).invoke((Object) null, vargs);
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            Throwable ex = th2;
            if (ex instanceof InvocationTargetException) {
                ex = ((InvocationTargetException) ex).getTargetException();
            }
            SourceMessages sourceMessages = this.messages;
            new StringBuilder();
            sourceMessages.error('e', sb.append("caught exception in inliner for ").append(proc).append(" - ").append(ex).toString(), ex);
        }
    }

    public static Expression inlineCall(LambdaExp lambdaExp, Expression[] expressionArr, boolean z) {
        Expression[] cargs;
        IdentityHashTable mapper;
        LetExp letExp;
        Expression expression;
        IdentityHashTable identityHashTable;
        LambdaExp lexp = lambdaExp;
        Expression[] args = expressionArr;
        boolean makeCopy = z;
        if (lexp.keywords != null || (lexp.nameDecl != null && !makeCopy)) {
            return null;
        }
        boolean varArgs = lexp.max_args < 0;
        if ((lexp.min_args != lexp.max_args || lexp.min_args != args.length) && (!varArgs || lexp.min_args != 0)) {
            return null;
        }
        Declaration prev = null;
        int i = 0;
        if (makeCopy) {
            new IdentityHashTable();
            mapper = identityHashTable;
            cargs = Expression.deepCopy(args, mapper);
            if (cargs == null && args != null) {
                return null;
            }
        } else {
            mapper = null;
            cargs = args;
        }
        if (varArgs) {
            Expression[] xargs = new Expression[(args.length + 1)];
            xargs[0] = QuoteExp.getInstance(lexp.firstDecl().type);
            System.arraycopy(args, 0, xargs, 1, args.length);
            new ApplyExp((Procedure) Invoke.make, xargs);
            cargs = new Expression[]{expression};
        }
        new LetExp(cargs);
        LetExp let = letExp;
        Declaration param = lexp.firstDecl();
        while (param != null) {
            Declaration next = param.nextDecl();
            if (makeCopy) {
                Declaration ldecl = let.addDeclaration(param.symbol, param.type);
                if (param.typeExp != null) {
                    ldecl.typeExp = Expression.deepCopy(param.typeExp);
                    if (ldecl.typeExp == null) {
                        return null;
                    }
                }
                Object put = mapper.put(param, ldecl);
            } else {
                lexp.remove(prev, param);
                let.add(prev, param);
            }
            if (!varArgs && !param.getCanWrite()) {
                param.setValue(cargs[i]);
            }
            prev = param;
            param = next;
            i++;
        }
        Expression body = lexp.body;
        if (makeCopy) {
            body = Expression.deepCopy(body, mapper);
            if (body == null && lexp.body != null) {
                return null;
            }
        }
        let.body = body;
        return let;
    }
}
