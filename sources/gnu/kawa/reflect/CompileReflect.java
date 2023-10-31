package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class CompileReflect {
    public CompileReflect() {
    }

    public static int checkKnownClass(Type type, Compilation compilation) {
        StringBuilder sb;
        Type type2 = type;
        Compilation comp = compilation;
        if (!(type2 instanceof ClassType) || !type2.isExisting()) {
            return 0;
        }
        try {
            Class reflectClass = type2.getReflectClass();
            return 1;
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            comp.error('e', sb.append("unknown class: ").append(type2.getName()).toString());
            return -1;
        }
    }

    public static ApplyExp inlineClassName(ApplyExp applyExp, int i, InlineCalls walker) {
        Expression expression;
        ApplyExp applyExp2;
        ApplyExp exp = applyExp;
        int carg = i;
        Compilation comp = walker.getCompilation();
        Language language = comp.getLanguage();
        Expression[] args = exp.getArgs();
        if (args.length <= carg) {
            return exp;
        }
        Type type = language.getTypeFor(args[carg]);
        if (!(type instanceof Type)) {
            return exp;
        }
        int checkKnownClass = checkKnownClass(type, comp);
        Expression[] nargs = new Expression[args.length];
        System.arraycopy(args, 0, nargs, 0, args.length);
        new QuoteExp(type);
        nargs[carg] = expression;
        new ApplyExp(exp.getFunction(), nargs);
        ApplyExp nexp = applyExp2;
        Expression line = nexp.setLine((Expression) exp);
        return nexp;
    }

    public static Expression validateApplyInstanceOf(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        QuoteExp quoteExp;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        ApplyExp exp2 = inlineClassName(exp, 1, visitor);
        Expression[] args = exp2.getArgs();
        if (args.length == 2) {
            Expression value = args[0];
            Expression texp = args[1];
            if (texp instanceof QuoteExp) {
                Object t = ((QuoteExp) texp).getValue();
                if (t instanceof Type) {
                    Type type3 = (Type) t;
                    if (type3 instanceof PrimType) {
                        type3 = ((PrimType) type3).boxedType();
                    }
                    if (value instanceof QuoteExp) {
                        if (type3.isInstance(((QuoteExp) value).getValue())) {
                            quoteExp = QuoteExp.trueExp;
                        } else {
                            quoteExp = QuoteExp.falseExp;
                        }
                        return quoteExp;
                    } else if (!value.side_effects()) {
                        int comp = type3.compare(value.getType());
                        if (comp == 1 || comp == 0) {
                            return QuoteExp.trueExp;
                        }
                        if (comp == -3) {
                            return QuoteExp.falseExp;
                        }
                    }
                }
            }
        }
        return exp2;
    }

    public static Expression validateApplySlotGet(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Type type2;
        QuoteExp quoteExp;
        Expression expression;
        StringBuilder sb;
        Expression expression2;
        StringBuilder sb2;
        StringBuilder sb3;
        Expression expression3;
        ApplyExp applyExp2;
        Expression expression4;
        StringBuilder sb4;
        Expression expression5;
        StringBuilder sb5;
        Expression expression6;
        ApplyExp applyExp3;
        ApplyExp applyExp4;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type3 = type;
        Procedure proc = procedure;
        exp.visitArgs(visitor);
        Compilation comp = visitor.getCompilation();
        Language language = comp.getLanguage();
        SlotGet gproc = (SlotGet) proc;
        boolean isStatic = gproc.isStatic;
        Expression[] args = exp.getArgs();
        Expression arg0 = args[0];
        Expression arg1 = args[1];
        Object val1 = arg1.valueIfConstant();
        if (!(val1 instanceof String) && !(val1 instanceof FString) && !(val1 instanceof Symbol)) {
            return exp;
        }
        String name = val1.toString();
        if (isStatic) {
            type2 = language.getTypeFor(arg0);
            int known = checkKnownClass(type2, comp);
            if (known < 0) {
                return exp;
            }
            if ("class".equals(name)) {
                if (known > 0) {
                    return QuoteExp.getInstance(type2.getReflectClass());
                }
                ApplyExp exp2 = applyExp4;
                new ApplyExp(Compilation.typeType.getDeclaredMethod("getReflectClass", 0), arg0);
                return exp2;
            } else if (type2 != null) {
                Expression[] expressionArr = new Expression[2];
                new QuoteExp(type2);
                expressionArr[0] = expression6;
                Expression[] nargs = expressionArr;
                nargs[1] = arg1;
                new ApplyExp(exp.getFunction(), nargs);
                ApplyExp nexp = applyExp3;
                Expression line = nexp.setLine((Expression) exp);
                exp = nexp;
            }
        } else {
            type2 = arg0.getType();
        }
        if (type2 instanceof ArrayType) {
            return exp;
        }
        if (type2 instanceof ObjectType) {
            ObjectType ctype = (ObjectType) type2;
            ClassType caller = comp.curClass != null ? comp.curClass : comp.mainClass;
            SlotGet slotGet = gproc;
            Member part = SlotGet.lookupMember(ctype, name, caller);
            if (part instanceof Field) {
                Field field = (Field) part;
                boolean isStaticField = (field.getModifiers() & 8) != 0;
                if (isStatic && !isStaticField) {
                    new StringBuilder();
                    new ErrorExp(sb5.append("cannot access non-static field `").append(name).append("' using `").append(proc.getName()).append('\'').toString(), comp);
                    return expression5;
                } else if (caller != null && !caller.isAccessible(field, ctype)) {
                    new StringBuilder();
                    new ErrorExp(sb4.append("field ").append(field.getDeclaringClass().getName()).append('.').append(name).append(" is not accessible here").toString(), comp);
                    return expression4;
                }
            } else if (part instanceof Method) {
                Method method = (Method) part;
                ClassType dtype = method.getDeclaringClass();
                int modifiers = method.getModifiers();
                boolean isStaticMethod = method.getStaticFlag();
                if (isStatic && !isStaticMethod) {
                    new StringBuilder();
                    new ErrorExp(sb2.append("cannot call non-static getter method `").append(name).append("' using `").append(proc.getName()).append('\'').toString(), comp);
                    return expression2;
                } else if (caller != null && !caller.isAccessible(dtype, ctype, modifiers)) {
                    new StringBuilder();
                    new ErrorExp(sb.append("method ").append(method).append(" is not accessible here").toString(), comp);
                    return expression;
                }
            }
            if (part != null) {
                Expression[] expressionArr2 = new Expression[2];
                expressionArr2[0] = arg0;
                Expression[] nargs2 = expressionArr2;
                new QuoteExp(part);
                nargs2[1] = expression3;
                new ApplyExp(exp.getFunction(), nargs2);
                ApplyExp nexp2 = applyExp2;
                Expression line2 = nexp2.setLine((Expression) exp);
                return nexp2;
            } else if (type2 != Type.pointer_type && comp.warnUnknownMember()) {
                new StringBuilder();
                comp.error('e', sb3.append("no slot `").append(name).append("' in ").append(ctype.getName()).toString());
            }
        }
        String fname = Compilation.mangleNameIfNeeded(name).intern();
        String getName = ClassExp.slotToMethodName("get", name);
        String isName = ClassExp.slotToMethodName("is", name);
        ApplyExp applyExp5 = r31;
        Invoke invoke = Invoke.invokeStatic;
        Expression[] expressionArr3 = new Expression[9];
        expressionArr3[0] = QuoteExp.getInstance("gnu.kawa.reflect.SlotGet");
        Expression[] expressionArr4 = expressionArr3;
        expressionArr4[1] = QuoteExp.getInstance("getSlotValue");
        Expression[] expressionArr5 = expressionArr4;
        Expression[] expressionArr6 = expressionArr5;
        Expression[] expressionArr7 = expressionArr5;
        if (isStatic) {
            quoteExp = QuoteExp.trueExp;
        } else {
            quoteExp = QuoteExp.falseExp;
        }
        expressionArr7[2] = quoteExp;
        Expression[] expressionArr8 = expressionArr6;
        expressionArr8[3] = args[0];
        Expression[] expressionArr9 = expressionArr8;
        expressionArr9[4] = QuoteExp.getInstance(name);
        Expression[] expressionArr10 = expressionArr9;
        expressionArr10[5] = QuoteExp.getInstance(fname);
        Expression[] expressionArr11 = expressionArr10;
        expressionArr11[6] = QuoteExp.getInstance(getName);
        Expression[] expressionArr12 = expressionArr11;
        expressionArr12[7] = QuoteExp.getInstance(isName);
        Expression[] expressionArr13 = expressionArr12;
        expressionArr13[8] = QuoteExp.getInstance(language);
        ApplyExp applyExp6 = new ApplyExp((Procedure) invoke, expressionArr13);
        ApplyExp nexp3 = applyExp5;
        Expression line3 = nexp3.setLine((Expression) exp);
        return visitor.visitApplyOnly(nexp3, (Type) null);
    }

    public static Expression validateApplySlotSet(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        exp.visitArgs(visitor);
        SlotSet sproc = (SlotSet) proc;
        if (sproc.isStatic && visitor.getCompilation().mustCompile) {
            exp = inlineClassName(exp, 0, visitor);
        }
        exp.setType((!sproc.returnSelf || exp.getArgCount() != 3) ? Type.voidType : exp.getArg(0).getType());
        return exp;
    }

    public static Expression validateApplyTypeSwitch(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        for (int i = 1; i < args.length; i++) {
            if (args[i] instanceof LambdaExp) {
                LambdaExp lexp = (LambdaExp) args[i];
                lexp.setInlineOnly(true);
                lexp.returnContinuation = exp;
                lexp.inlineHome = visitor.getCurrentLambda();
            }
        }
        return exp;
    }
}
