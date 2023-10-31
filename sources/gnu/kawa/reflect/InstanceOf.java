package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class InstanceOf extends Procedure2 implements Inlineable {
    static Method instanceMethod;
    static ClassType typeType;
    protected Language language;

    public InstanceOf(Language language2) {
        this.language = language2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyInstanceOf");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InstanceOf(Language language2, String name) {
        this(language2);
        setName(name);
    }

    public Object apply2(Object obj, Object arg2) {
        Object arg1 = obj;
        Type type = this.language.asType(arg2);
        if (type instanceof PrimType) {
            type = ((PrimType) type).boxedType();
        }
        return this.language.booleanObject(type.isInstance(arg1));
    }

    public void compile(ApplyExp exp, Compilation compilation, Target target) {
        StringBuilder sb;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        CodeAttr code = comp.getCode();
        Type type = null;
        Expression typeArg = args[1];
        if (typeArg instanceof QuoteExp) {
            try {
                type = this.language.asType(((QuoteExp) typeArg).getValue());
            } catch (Exception e) {
                Exception exc = e;
                new StringBuilder();
                comp.error('w', sb.append("unknown type spec: ").append((Object) null).toString());
            }
        } else {
            type = this.language.getTypeFor(typeArg);
        }
        if (type != null) {
            if (type instanceof PrimType) {
                type = ((PrimType) type).boxedType();
            }
            args[0].compile(comp, Target.pushObject);
            if (type instanceof TypeValue) {
                ((TypeValue) type).emitIsInstance((Variable) null, comp, target2);
                return;
            } else {
                type.emitIsInstance(code);
                comp.usedClass(type);
            }
        } else {
            if (typeType == null) {
                typeType = ClassType.make("gnu.bytecode.Type");
                instanceMethod = typeType.addMethod("isInstance", Compilation.apply1args, (Type) Type.boolean_type, 1);
            }
            args[1].compile(comp, (Type) typeType);
            args[0].compile(comp, Target.pushObject);
            code.emitInvokeVirtual(instanceMethod);
        }
        target2.compileFromStack(comp, this.language.getTypeFor(Boolean.TYPE));
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return this.language.getTypeFor(Boolean.TYPE);
    }

    public static void emitIsInstance(TypeValue type, Variable variable, Compilation compilation, Target target) {
        Variable variable2 = variable;
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        type.emitTestIf((Variable) null, (Declaration) null, comp);
        ConditionalTarget cond = null;
        if (target2 instanceof ConditionalTarget) {
            cond = (ConditionalTarget) target2;
            code.emitGoto(cond.ifTrue);
        } else {
            code.emitPushInt(1);
        }
        code.emitElse();
        if (cond != null) {
            code.emitGoto(cond.ifFalse);
        } else {
            code.emitPushInt(0);
        }
        code.emitFi();
        if (cond == null) {
            target2.compileFromStack(comp, comp.getLanguage().getTypeFor(Boolean.TYPE));
        }
    }
}
