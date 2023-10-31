package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class StaticSet extends Procedure1 implements Inlineable {
    ClassType ctype;
    Field field;
    String fname;
    java.lang.reflect.Field reflectField;

    StaticSet(Class clas, String fname2) {
        this.ctype = (ClassType) Type.make(clas);
        this.fname = fname2;
    }

    public StaticSet(ClassType classType, String str, Type type, int i) {
        ClassType ctype2 = classType;
        String name = str;
        Type ftype = type;
        int flags = i;
        this.ctype = ctype2;
        this.fname = name;
        this.field = ctype2.getField(name);
        if (this.field == null) {
            this.field = ctype2.addField(name, ftype, flags);
        }
    }

    public Object apply1(Object obj) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Object arg1 = obj;
        if (this.reflectField == null) {
            Class clas = this.ctype.getReflectClass();
            try {
                this.reflectField = clas.getField(this.fname);
            } catch (NoSuchFieldException e) {
                NoSuchFieldException noSuchFieldException = e;
                Throwable th3 = th2;
                new StringBuilder();
                new RuntimeException(sb2.append("no such field ").append(this.fname).append(" in ").append(clas.getName()).toString());
                throw th3;
            }
        }
        try {
            this.reflectField.set((Object) null, arg1);
            return Values.empty;
        } catch (IllegalAccessException e2) {
            IllegalAccessException illegalAccessException = e2;
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb.append("illegal access for field ").append(this.fname).toString());
            throw th4;
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        if (this.field == null) {
            this.field = this.ctype.getField(this.fname);
            if (this.field == null) {
                this.field = this.ctype.addField(this.fname, Type.make(this.reflectField.getType()), this.reflectField.getModifiers());
            }
        }
        exp.getArgs()[0].compile(comp, this.field.getType());
        comp.getCode().emitPutStatic(this.field);
        comp.compileConstant(Values.empty, target2);
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.voidType;
    }
}
