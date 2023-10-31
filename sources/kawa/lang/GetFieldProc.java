package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;

public class GetFieldProc extends Procedure1 implements Inlineable {
    ClassType ctype;
    Field field;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetFieldProc(Class clas, String fname) {
        this((ClassType) Type.make(clas), fname);
    }

    public GetFieldProc(ClassType classType, String fname) {
        ClassType ctype2 = classType;
        this.ctype = ctype2;
        this.field = Field.searchField(ctype2.getFields(), fname);
    }

    public GetFieldProc(ClassType classType, String str, Type type, int i) {
        ClassType ctype2 = classType;
        String name = str;
        Type ftype = type;
        int flags = i;
        this.ctype = ctype2;
        this.field = ctype2.getField(name);
        if (this.field == null) {
            this.field = ctype2.addField(name, ftype, flags);
        }
    }

    public Object apply1(Object arg1) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        try {
            return this.field.getReflectField().get(arg1);
        } catch (NoSuchFieldException e) {
            NoSuchFieldException noSuchFieldException = e;
            Throwable th3 = th2;
            new StringBuilder();
            new RuntimeException(sb2.append("no such field ").append(this.field.getSourceName()).append(" in ").append(this.ctype.getName()).toString());
            throw th3;
        } catch (IllegalAccessException e2) {
            IllegalAccessException illegalAccessException = e2;
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb.append("illegal access for field ").append(this.field.getSourceName()).toString());
            throw th4;
        }
    }

    private Field getField() {
        return this.field;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        if (this.ctype.getReflectClass().getClassLoader() instanceof ArrayClassLoader) {
            ApplyExp.compile(exp, comp, target2);
            return;
        }
        exp.getArgs()[0].compile(comp, (Type) this.ctype);
        comp.getCode().emitGetField(this.field);
        target2.compileFromStack(comp, this.field.getType());
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return getField().getType();
    }
}
