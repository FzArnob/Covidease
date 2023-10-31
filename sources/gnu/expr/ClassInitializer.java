package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;

public class ClassInitializer extends Initializer {
    ClassExp cexp;

    public ClassInitializer(ClassExp classExp, Compilation compilation) {
        ClassExp cexp2 = classExp;
        Compilation comp = compilation;
        this.field = cexp2.allocFieldFor(comp);
        ClassType compileMembers = cexp2.compileMembers(comp);
        this.cexp = cexp2;
        if (this.field.getStaticFlag()) {
            this.next = comp.clinitChain;
            comp.clinitChain = this;
            return;
        }
        LambdaExp heapLambda = cexp2.getOwningLambda();
        this.next = heapLambda.initChain;
        heapLambda.initChain = this;
    }

    public void emit(Compilation compilation) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        if (!this.field.getStaticFlag()) {
            code.emitPushThis();
        }
        this.cexp.compilePushClass(comp, Target.pushValue(Compilation.typeClassType));
        if (this.field.getStaticFlag()) {
            code.emitPutStatic(this.field);
        } else {
            code.emitPutField(this.field);
        }
    }
}
