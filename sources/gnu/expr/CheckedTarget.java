package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;

public class CheckedTarget extends StackTarget {
    static Method initWrongTypeProcMethod;
    static Method initWrongTypeStringMethod;
    static ClassType typeClassCastException;
    static ClassType typeWrongType;
    int argno;
    LambdaExp proc;
    String procname;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CheckedTarget(Type type) {
        super(type);
        this.argno = -4;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CheckedTarget(Type type, LambdaExp lambdaExp, int argno2) {
        super(type);
        LambdaExp proc2 = lambdaExp;
        this.proc = proc2;
        this.procname = proc2.getName();
        this.argno = argno2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CheckedTarget(Type type, String procname2, int argno2) {
        super(type);
        this.procname = procname2;
        this.argno = argno2;
    }

    public static Target getInstance(Type type, String str, int i) {
        Target target;
        Target target2;
        Type type2 = type;
        String procname2 = str;
        int argno2 = i;
        if (type2 == Type.objectType) {
            target2 = Target.pushObject;
        } else {
            target2 = target;
            new CheckedTarget(type2, procname2, argno2);
        }
        return target2;
    }

    public static Target getInstance(Type type, LambdaExp lambdaExp, int i) {
        Target target;
        Target target2;
        Type type2 = type;
        LambdaExp proc2 = lambdaExp;
        int argno2 = i;
        if (type2 == Type.objectType) {
            target2 = Target.pushObject;
        } else {
            target2 = target;
            new CheckedTarget(type2, proc2, argno2);
        }
        return target2;
    }

    public static Target getInstance(Type type) {
        Target target;
        Target target2;
        Type type2 = type;
        if (type2 == Type.objectType) {
            target2 = Target.pushObject;
        } else {
            target2 = target;
            new CheckedTarget(type2);
        }
        return target2;
    }

    public static Target getInstance(Declaration declaration) {
        Declaration decl = declaration;
        return getInstance(decl.getType(), decl.getName(), -2);
    }

    private static void initWrongType() {
        if (typeClassCastException == null) {
            typeClassCastException = ClassType.make("java.lang.ClassCastException");
        }
        if (typeWrongType == null) {
            typeWrongType = ClassType.make("gnu.mapping.WrongType");
            initWrongTypeStringMethod = typeWrongType.addMethod("<init>", 1, new Type[]{typeClassCastException, Compilation.javaStringType, Type.intType, Type.objectType}, (Type) Type.voidType);
            initWrongTypeProcMethod = typeWrongType.addMethod("<init>", 1, new Type[]{typeClassCastException, Compilation.typeProcedure, Type.intType, Type.objectType}, (Type) Type.voidType);
        }
    }

    public void compileFromStack(Compilation compilation, Type stackType) {
        Compilation comp = compilation;
        if (!compileFromStack0(comp, stackType)) {
            emitCheckedCoerce(comp, this.proc, this.procname, this.argno, this.type, (Variable) null);
        }
    }

    public static void emitCheckedCoerce(Compilation comp, String procname2, int argno2, Type type) {
        emitCheckedCoerce(comp, (LambdaExp) null, procname2, argno2, type, (Variable) null);
    }

    public static void emitCheckedCoerce(Compilation comp, LambdaExp lambdaExp, int argno2, Type type) {
        LambdaExp proc2 = lambdaExp;
        emitCheckedCoerce(comp, proc2, proc2.getName(), argno2, type, (Variable) null);
    }

    public static void emitCheckedCoerce(Compilation comp, LambdaExp lambdaExp, int argno2, Type type, Variable argValue) {
        LambdaExp proc2 = lambdaExp;
        emitCheckedCoerce(comp, proc2, proc2.getName(), argno2, type, argValue);
    }

    static void emitCheckedCoerce(Compilation compilation, LambdaExp lambdaExp, String str, int i, Type type, Variable variable) {
        Label label;
        Scope tmpScope;
        Label label2;
        Label label3;
        Compilation comp = compilation;
        LambdaExp proc2 = lambdaExp;
        String procname2 = str;
        int argno2 = i;
        Type type2 = type;
        Variable argValue = variable;
        CodeAttr code = comp.getCode();
        boolean isInTry = code.isInTry();
        initWrongType();
        new Label(code);
        Label startTry = label;
        if (argValue != null || type2 == Type.toStringType) {
            tmpScope = null;
        } else {
            tmpScope = code.pushScope();
            argValue = code.addLocal(Type.objectType);
            code.emitDup(1);
            code.emitStore(argValue);
        }
        int startPC = code.getPC();
        startTry.define(code);
        emitCoerceFromObject(type2, comp);
        if (code.getPC() != startPC && type2 != Type.toStringType) {
            new Label(code);
            Label endTry = label2;
            endTry.define(code);
            new Label(code);
            Label endLabel = label3;
            endLabel.setTypes(code);
            if (isInTry) {
                code.emitGoto(endLabel);
            }
            int fragment_cookie = 0;
            code.setUnreachable();
            if (!isInTry) {
                fragment_cookie = code.beginFragment(endLabel);
            }
            code.addHandler(startTry, endTry, typeClassCastException);
            boolean thisIsProc = false;
            if (proc2 != null && proc2.isClassGenerated() && !comp.method.getStaticFlag() && comp.method.getDeclaringClass() == proc2.getCompiledClassType(comp)) {
                thisIsProc = true;
            }
            int line = comp.getLineNumber();
            if (line > 0) {
                code.putLineNumber(line);
            }
            code.emitNew(typeWrongType);
            code.emitDupX();
            code.emitSwap();
            if (thisIsProc) {
                code.emitPushThis();
            } else {
                code.emitPushString((procname2 != null || argno2 == -4) ? procname2 : "lambda");
            }
            code.emitPushInt(argno2);
            code.emitLoad(argValue);
            code.emitInvokeSpecial(thisIsProc ? initWrongTypeProcMethod : initWrongTypeStringMethod);
            if (tmpScope != null) {
                Scope popScope = code.popScope();
            }
            code.emitThrow();
            if (isInTry) {
                endLabel.define(code);
            } else {
                code.endFragment(fragment_cookie);
            }
        } else if (tmpScope != null) {
            Scope popScope2 = code.popScope();
        }
    }
}
