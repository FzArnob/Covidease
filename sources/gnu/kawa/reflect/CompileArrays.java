package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.mapping.Values;

public class CompileArrays implements Inlineable {
    public char code;
    Procedure proc;

    public CompileArrays(Procedure proc2, char code2) {
        this.proc = proc2;
        this.code = code2;
    }

    public static CompileArrays getForArrayGet(Object proc2) {
        CompileArrays compileArrays;
        new CompileArrays((Procedure) proc2, 'G');
        return compileArrays;
    }

    public static CompileArrays getForArraySet(Object proc2) {
        CompileArrays compileArrays;
        new CompileArrays((Procedure) proc2, 'S');
        return compileArrays;
    }

    public static CompileArrays getForArrayLength(Object proc2) {
        CompileArrays compileArrays;
        new CompileArrays((Procedure) proc2, 'L');
        return compileArrays;
    }

    public static CompileArrays getForArrayNew(Object proc2) {
        CompileArrays compileArrays;
        new CompileArrays((Procedure) proc2, 'N');
        return compileArrays;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        switch (this.code) {
            case 'G':
                compileArrayGet((ArrayGet) this.proc, exp, comp, target2);
                return;
            case 'N':
                compileArrayNew((ArrayNew) this.proc, exp, comp, target2);
                return;
            case 'S':
                compileArraySet((ArraySet) this.proc, exp, comp, target2);
                return;
            default:
                compileArrayLength((ArrayLength) this.proc, exp, comp, target2);
                return;
        }
    }

    public static void compileArrayGet(ArrayGet proc2, ApplyExp exp, Compilation compilation, Target target) {
        Compilation comp = compilation;
        Type element_type = proc2.element_type;
        Expression[] args = exp.getArgs();
        args[0].compile(comp, (Type) ArrayType.make(element_type));
        args[1].compile(comp, (Type) Type.int_type);
        comp.getCode().emitArrayLoad(element_type);
        target.compileFromStack(comp, element_type);
    }

    public static void compileArraySet(ArraySet proc2, ApplyExp exp, Compilation compilation, Target target) {
        Compilation comp = compilation;
        Type element_type = proc2.element_type;
        Expression[] args = exp.getArgs();
        args[0].compile(comp, (Type) ArrayType.make(element_type));
        args[1].compile(comp, (Type) Type.int_type);
        args[2].compile(comp, element_type);
        comp.getCode().emitArrayStore(element_type);
        comp.compileConstant(Values.empty, target);
    }

    public static void compileArrayNew(ArrayNew proc2, ApplyExp exp, Compilation compilation, Target target) {
        Compilation comp = compilation;
        Type element_type = proc2.element_type;
        exp.getArgs()[0].compile(comp, (Type) Type.intType);
        comp.getCode().emitNewArray(element_type.getImplementationType());
        target.compileFromStack(comp, ArrayType.make(element_type));
    }

    public static void compileArrayLength(ArrayLength proc2, ApplyExp exp, Compilation compilation, Target target) {
        Compilation comp = compilation;
        exp.getArgs()[0].compile(comp, (Type) ArrayType.make(proc2.element_type));
        comp.getCode().emitArrayLength();
        target.compileFromStack(comp, LangPrimType.intType);
    }

    public static Expression validateArrayNew(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure proc2) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        exp.visitArgs(visitor);
        exp.setType(ArrayType.make(((ArrayNew) proc2).element_type));
        return exp;
    }

    public static Expression validateArrayLength(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        exp.setType(LangPrimType.intType);
        return exp;
    }

    public static Expression validateArrayGet(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure proc2) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        exp.visitArgs(visitor);
        exp.setType(((ArrayGet) proc2).element_type);
        return exp;
    }

    public static Expression validateArraySet(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        exp.setType(Type.void_type);
        return exp;
    }
}
