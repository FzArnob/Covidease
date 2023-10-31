package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ArrayGet;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.math.Numeric;
import gnu.text.Char;
import java.io.Externalizable;

public class CompilationHelpers {
    public static final Declaration setterDecl;
    static final Field setterField = setterType.getDeclaredField("setter");
    static final ClassType setterType = ClassType.make("gnu.kawa.functions.Setter");
    static final ClassType typeList = ClassType.make("java.util.List");

    public CompilationHelpers() {
    }

    private static boolean nonNumeric(Expression expression) {
        Expression exp = expression;
        if (!(exp instanceof QuoteExp)) {
            return false;
        }
        Object value = ((QuoteExp) exp).getValue();
        return !(value instanceof Numeric) && !(value instanceof Char) && !(value instanceof Symbol);
    }

    static {
        Declaration declaration;
        Expression expression;
        new Declaration((Object) "setter", setterField);
        setterDecl = declaration;
        new QuoteExp(Setter.setter);
        setterDecl.noteValue(expression);
    }

    public static Expression validateApplyToArgs(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp applyExp2;
        ApplyExp applyExp3;
        ApplyExp applyExp4;
        Procedure procedure2;
        ApplyExp applyExp5;
        ApplyExp applyExp6;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        Procedure procedure3 = procedure;
        Expression[] args = exp.getArgs();
        int nargs = args.length - 1;
        if (nargs >= 0) {
            Expression proc = args[0];
            if (!proc.getFlag(1)) {
                if (proc instanceof LambdaExp) {
                    Expression[] rargs = new Expression[nargs];
                    System.arraycopy(args, 1, rargs, 0, nargs);
                    new ApplyExp(proc, rargs);
                    return visitor.visit(applyExp6.setLine((Expression) exp), required);
                }
                proc = visitor.visit(proc, (Type) null);
                args[0] = proc;
            }
            Type ptype = proc.getType().getRealType();
            Compilation comp = visitor.getCompilation();
            Language language = comp.getLanguage();
            if (ptype.isSubtype(Compilation.typeProcedure)) {
                Expression[] rargs2 = new Expression[nargs];
                System.arraycopy(args, 1, rargs2, 0, nargs);
                new ApplyExp(proc, rargs2);
                ApplyExp nexp = applyExp5;
                Expression line = nexp.setLine((Expression) exp);
                return proc.validateApply(nexp, visitor, required, (Declaration) null);
            }
            ApplyExp result = null;
            if (CompileReflect.checkKnownClass(ptype, comp) >= 0) {
                if (ptype.isSubtype(Compilation.typeType) || language.getTypeFor(proc, false) != null) {
                    new ApplyExp((Procedure) Invoke.make, args);
                    result = applyExp2;
                } else if (ptype instanceof ArrayType) {
                    new ArrayGet(((ArrayType) ptype).getComponentType());
                    new ApplyExp(procedure2, args);
                    result = applyExp4;
                } else if (ptype instanceof ClassType) {
                    ClassType classType = (ClassType) ptype;
                    ClassType ctype = classType;
                    if (classType.isSubclass(typeList) && nargs == 1) {
                        new ApplyExp(ctype.getMethod("get", new Type[]{Type.intType}), args);
                        result = applyExp3;
                    }
                }
            }
            if (result != null) {
                Expression line2 = result.setLine((Expression) exp);
                return visitor.visitApplyOnly(result, required);
            }
        }
        exp.visitArgs(visitor);
        return exp;
    }

    public static Expression validateSetter(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        Expression expression;
        Expression expression2;
        Declaration decl;
        ApplyExp exp;
        ApplyExp exp2;
        ApplyExp exp3 = applyExp;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp3.visitArgs(visitor);
        Expression[] args = exp3.getArgs();
        if (args.length == 1) {
            Expression arg = args[0];
            Type argType = arg.getType();
            if (argType instanceof ArrayType) {
                new SetArrayExp(arg, (ArrayType) argType);
                return exp2;
            }
            if (argType instanceof ClassType) {
                ClassType classType = (ClassType) argType;
                ClassType classType2 = classType;
                if (classType.isSubclass(typeList)) {
                    if (exp3 instanceof SetListExp) {
                        return exp3;
                    }
                    new SetListExp(exp3.getFunction(), args);
                    return exp;
                }
            }
            if ((arg instanceof ReferenceExp) && (decl = ((ReferenceExp) arg).getBinding()) != null) {
                arg = decl.getValue();
            }
            if (arg instanceof QuoteExp) {
                Object value = ((QuoteExp) arg).getValue();
                if (value instanceof Procedure) {
                    Procedure setter = ((Procedure) value).getSetter();
                    if (setter instanceof Procedure) {
                        if (setter instanceof Externalizable) {
                            new QuoteExp(setter);
                            return expression2;
                        }
                        Declaration decl2 = Declaration.getDeclaration(setter);
                        if (decl2 != null) {
                            new ReferenceExp(decl2);
                            return expression;
                        }
                    }
                }
            }
        }
        return exp3;
    }

    public static Expression validateIsEqv(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        ApplyExp exp;
        ApplyExp exp2 = applyExp;
        Type type2 = type;
        Procedure proc = procedure;
        exp2.visitArgs(visitor);
        Expression[] args = exp2.getArgs();
        if (!nonNumeric(args[0]) && !nonNumeric(args[1])) {
            return exp2;
        }
        new ApplyExp((Procedure) ((IsEqv) proc).isEq, args);
        return exp;
    }
}
