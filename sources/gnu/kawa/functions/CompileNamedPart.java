package gnu.kawa.functions;

import gnu.bytecode.Access;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.HasNamedParts;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import kawa.lang.Translator;

public class CompileNamedPart {
    static final ClassType typeHasNamedParts = ClassType.make("gnu.mapping.HasNamedParts");

    public CompileNamedPart() {
    }

    public static Expression validateGetNamedPart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        StringBuilder sb;
        ApplyExp applyExp2;
        ApplyExp applyExp3;
        ApplyExp applyExp4;
        Expression expression;
        Object obj;
        Expression expression2;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length != 2 || !(args[1] instanceof QuoteExp) || !(exp instanceof GetNamedExp)) {
            return exp;
        }
        Expression context = args[0];
        Declaration decl = null;
        if (context instanceof ReferenceExp) {
            ReferenceExp rexp = (ReferenceExp) context;
            if ("*".equals(rexp.getName())) {
                return makeGetNamedInstancePartExp(args[1]);
            }
            decl = rexp.getBinding();
        }
        String mname = ((QuoteExp) args[1]).getValue().toString();
        Type type2 = context.getType();
        boolean z = context == QuoteExp.nullExp;
        Compilation comp = visitor.getCompilation();
        Language language = comp.getLanguage();
        Type typeval = language.getTypeFor(context, false);
        ClassType caller = comp == null ? null : comp.curClass != null ? comp.curClass : comp.mainClass;
        GetNamedExp nexp = (GetNamedExp) exp;
        if (typeval != null) {
            if (mname.equals(GetNamedPart.CLASSTYPE_FOR)) {
                new QuoteExp(typeval);
                return expression2;
            } else if (typeval instanceof ObjectType) {
                if (mname.equals("new")) {
                    return nexp.setProcedureKind('N');
                }
                if (mname.equals(GetNamedPart.INSTANCEOF_METHOD_NAME)) {
                    return nexp.setProcedureKind(Access.INNERCLASS_CONTEXT);
                }
                if (mname.equals(GetNamedPart.CAST_METHOD_NAME)) {
                    return nexp.setProcedureKind(Access.CLASS_CONTEXT);
                }
            }
        }
        if (!(typeval instanceof ObjectType)) {
            if (typeval != null) {
            }
            if (type2.isSubtype(Compilation.typeClassType) || type2.isSubtype(Type.javalangClassType)) {
                return exp;
            }
            if (type2 instanceof ObjectType) {
                ObjectType otype = (ObjectType) type2;
                PrimProcedure[] methods = ClassMethods.getMethods(otype, Compilation.mangleName(mname), 'V', caller, language);
                if (methods != null && methods.length > 0) {
                    nexp.methods = methods;
                    return nexp.setProcedureKind(Access.METHOD_CONTEXT);
                } else if (type2.isSubtype(typeHasNamedParts)) {
                    if (decl != null) {
                        Object constantValue = Declaration.followAliases(decl).getConstantValue();
                        Object val = constantValue;
                        if (constantValue != null) {
                            HasNamedParts value = (HasNamedParts) val;
                            if (value.isConstant(mname)) {
                                return QuoteExp.getInstance(value.get(mname));
                            }
                        }
                    }
                    Expression[] expressionArr = new Expression[2];
                    expressionArr[0] = args[0];
                    Expression[] args2 = expressionArr;
                    args2[1] = QuoteExp.getInstance(mname);
                    new ApplyExp(typeHasNamedParts.getDeclaredMethod("get", 1), args2);
                    return applyExp3.setLine((Expression) exp);
                } else if (SlotGet.lookupMember(otype, mname, caller) != null || (mname.equals("length") && (type2 instanceof ArrayType))) {
                    new ApplyExp((Procedure) SlotGet.field, args);
                    ApplyExp aexp = applyExp2;
                    Expression line = aexp.setLine((Expression) exp);
                    return visitor.visitApplyOnly(aexp, required);
                }
            }
            if (comp.warnUnknownMember()) {
                new StringBuilder();
                comp.error('w', sb.append("no known slot '").append(mname).append("' in ").append(type2.getName()).toString());
            }
            return exp;
        } else if (mname.length() > 1 && mname.charAt(0) == '.') {
            new NamedPart(typeval, mname, 'D');
            new QuoteExp(obj);
            return expression;
        } else if (CompileReflect.checkKnownClass(typeval, comp) < 0) {
            return exp;
        } else {
            PrimProcedure[] methods2 = ClassMethods.getMethods((ObjectType) typeval, Compilation.mangleName(mname), 0, caller, language);
            if (methods2 == null || methods2.length <= 0) {
                new ApplyExp((Procedure) SlotGet.staticField, args);
                ApplyExp aexp2 = applyExp4;
                Expression line2 = aexp2.setLine((Expression) exp);
                return visitor.visitApplyOnly(aexp2, required);
            }
            nexp.methods = methods2;
            return nexp.setProcedureKind('S');
        }
    }

    public static Expression validateSetNamedPart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp applyExp2;
        ApplyExp applyExp3;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length != 3 || !(args[1] instanceof QuoteExp)) {
            return exp;
        }
        Expression context = args[0];
        String mname = ((QuoteExp) args[1]).getValue().toString();
        Type type3 = context.getType();
        Compilation comp = visitor.getCompilation();
        Type typeval = comp.getLanguage().getTypeFor(context);
        ClassType caller = comp == null ? null : comp.curClass != null ? comp.curClass : comp.mainClass;
        ApplyExp original = exp;
        if (typeval instanceof ClassType) {
            new ApplyExp((Procedure) SlotSet.set$Mnstatic$Mnfield$Ex, args);
            exp = applyExp3;
        } else if ((type3 instanceof ClassType) && SlotSet.lookupMember((ClassType) type3, mname, caller) != null) {
            new ApplyExp((Procedure) SlotSet.set$Mnfield$Ex, args);
            exp = applyExp2;
        }
        if (exp != original) {
            Expression line = exp.setLine((Expression) original);
        }
        exp.setType(Type.voidType);
        return exp;
    }

    public static Expression makeExp(Expression expression, Expression expression2) {
        Expression expression3;
        Expression clas;
        Expression clas2;
        Expression clas3 = expression;
        Expression member = expression2;
        String combinedName = combineName(clas3, member);
        Environment env = Environment.getCurrent();
        if (combinedName != null) {
            Translator tr = (Translator) Compilation.getCurrent();
            Symbol symbol = Namespace.EmptyNamespace.getSymbol(combinedName);
            Declaration decl = tr.lexical.lookup((Object) symbol, false);
            if (!Declaration.isUnknown(decl)) {
                new ReferenceExp(decl);
                return clas2;
            } else if (symbol != null && env.isBound(symbol, (Object) null)) {
                new ReferenceExp((Object) combinedName);
                return clas;
            }
        }
        if (clas3 instanceof ReferenceExp) {
            ReferenceExp referenceExp = (ReferenceExp) clas3;
            ReferenceExp rexp = referenceExp;
            if (referenceExp.isUnknown()) {
                Object rsym = rexp.getSymbol();
                if (env.get((EnvironmentKey) rsym instanceof Symbol ? (Symbol) rsym : env.getSymbol(rsym.toString()), (Object) null) == null) {
                    try {
                        clas3 = QuoteExp.getInstance(Type.make(ClassType.getContextClass(rexp.getName())));
                    } catch (Throwable th) {
                        Throwable th2 = th;
                    }
                }
            }
        }
        Expression[] expressionArr = new Expression[2];
        expressionArr[0] = clas3;
        Expression[] args = expressionArr;
        args[1] = member;
        new GetNamedExp(args);
        Expression exp = expression3;
        exp.combinedName = combinedName;
        return exp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        new java.lang.StringBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return r6.append(r2).append(':').append(r3).toString().intern();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001f, code lost:
        if (r6 == null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        if (r6 != null) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String combineName(gnu.expr.Expression r7, gnu.expr.Expression r8) {
        /*
            r0 = r7
            r1 = r8
            r4 = r1
            java.lang.Object r4 = r4.valueIfConstant()
            r6 = r4
            r4 = r6
            r5 = r6
            r3 = r5
            boolean r4 = r4 instanceof gnu.mapping.SimpleSymbol
            if (r4 == 0) goto L_0x0053
            r4 = r0
            boolean r4 = r4 instanceof gnu.expr.ReferenceExp
            if (r4 == 0) goto L_0x0021
            r4 = r0
            gnu.expr.ReferenceExp r4 = (gnu.expr.ReferenceExp) r4
            java.lang.String r4 = r4.getSimpleName()
            r6 = r4
            r4 = r6
            r5 = r6
            r2 = r5
            if (r4 != 0) goto L_0x0031
        L_0x0021:
            r4 = r0
            boolean r4 = r4 instanceof gnu.kawa.functions.GetNamedExp
            if (r4 == 0) goto L_0x0053
            r4 = r0
            gnu.kawa.functions.GetNamedExp r4 = (gnu.kawa.functions.GetNamedExp) r4
            java.lang.String r4 = r4.combinedName
            r6 = r4
            r4 = r6
            r5 = r6
            r2 = r5
            if (r4 == 0) goto L_0x0053
        L_0x0031:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r5 = r2
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = 58
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = r3
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r4.intern()
            r0 = r4
        L_0x0052:
            return r0
        L_0x0053:
            r4 = 0
            r0 = r4
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileNamedPart.combineName(gnu.expr.Expression, gnu.expr.Expression):java.lang.String");
    }

    public static Expression makeExp(Expression clas, String member) {
        Expression expression;
        new QuoteExp(member);
        return makeExp(clas, expression);
    }

    public static Expression makeExp(Type type, String member) {
        Expression expression;
        Expression expression2;
        new QuoteExp(type);
        new QuoteExp(member);
        return makeExp(expression, expression2);
    }

    public static Expression validateNamedPart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc) {
        SlotGet slotProc;
        ApplyExp applyExp2;
        Expression expression;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        NamedPart namedPart = (NamedPart) proc;
        switch (namedPart.kind) {
            case 'D':
                Expression[] xargs = new Expression[2];
                xargs[1] = QuoteExp.getInstance(namedPart.member.toString().substring(1));
                if (args.length > 0) {
                    new QuoteExp(namedPart.container);
                    xargs[0] = Compilation.makeCoercion(args[0], expression);
                    slotProc = SlotGet.field;
                } else {
                    xargs[0] = QuoteExp.getInstance(namedPart.container);
                    slotProc = SlotGet.staticField;
                }
                new ApplyExp((Procedure) slotProc, xargs);
                ApplyExp aexp = applyExp2;
                Expression line = aexp.setLine((Expression) exp);
                return visitor.visitApplyOnly(aexp, required);
            default:
                return exp;
        }
    }

    public static Expression validateNamedPartSetter(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc) {
        Expression expression;
        SlotSet slotProc;
        ApplyExp applyExp2;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        exp.visitArgs(visitor);
        NamedPart get = (NamedPart) ((NamedPartSetter) proc).getGetter();
        if (get.kind != 'D') {
            return exp;
        }
        Expression[] xargs = new Expression[3];
        xargs[1] = QuoteExp.getInstance(get.member.toString().substring(1));
        xargs[2] = exp.getArgs()[0];
        if (exp.getArgCount() == 1) {
            xargs[0] = QuoteExp.getInstance(get.container);
            slotProc = SlotSet.set$Mnstatic$Mnfield$Ex;
        } else if (exp.getArgCount() != 2) {
            return exp;
        } else {
            new QuoteExp(get.container);
            xargs[0] = Compilation.makeCoercion(exp.getArgs()[0], expression);
            slotProc = SlotSet.set$Mnfield$Ex;
        }
        new ApplyExp((Procedure) slotProc, xargs);
        ApplyExp aexp = applyExp2;
        Expression line = aexp.setLine((Expression) exp);
        return visitor.visitApplyOnly(aexp, required);
    }

    public static Expression makeGetNamedInstancePartExp(Expression expression) {
        Expression expression2;
        Expression member;
        Object obj;
        Expression member2 = expression;
        if (member2 instanceof QuoteExp) {
            Object val = ((QuoteExp) member2).getValue();
            if (val instanceof SimpleSymbol) {
                new GetNamedInstancePart(val.toString());
                return QuoteExp.getInstance(obj);
            }
        }
        new QuoteExp(ClassType.make("gnu.kawa.functions.GetNamedInstancePart"));
        new ApplyExp((Procedure) Invoke.make, expression2, member2);
        return member;
    }

    public static Expression validateGetNamedInstancePart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc) {
        Expression[] xargs;
        Expression expression;
        Procedure property;
        ApplyExp applyExp2;
        Expression expression2;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        GetNamedInstancePart gproc = (GetNamedInstancePart) proc;
        if (gproc.isField) {
            Expression[] expressionArr = new Expression[2];
            expressionArr[0] = args[0];
            Expression[] expressionArr2 = expressionArr;
            new QuoteExp(gproc.pname);
            expressionArr2[1] = expression2;
            xargs = expressionArr2;
            property = SlotGet.field;
        } else {
            int nargs = args.length;
            xargs = new Expression[(nargs + 1)];
            xargs[0] = args[0];
            new QuoteExp(gproc.pname);
            xargs[1] = expression;
            System.arraycopy(args, 1, xargs, 2, nargs - 1);
            property = Invoke.invoke;
        }
        new ApplyExp(property, xargs);
        return visitor.visitApplyOnly(applyExp2, required);
    }

    public static Expression validateSetNamedInstancePart(ApplyExp applyExp, InlineCalls inlineCalls, Type required, Procedure proc) {
        Expression expression;
        ApplyExp applyExp2;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        String pname = ((SetNamedInstancePart) proc).pname;
        Expression[] expressionArr = new Expression[3];
        expressionArr[0] = args[0];
        Expression[] expressionArr2 = expressionArr;
        new QuoteExp(pname);
        expressionArr2[1] = expression;
        Expression[] xargs = expressionArr2;
        xargs[2] = args[1];
        new ApplyExp((Procedure) SlotSet.set$Mnfield$Ex, xargs);
        return visitor.visitApplyOnly(applyExp2, required);
    }
}
