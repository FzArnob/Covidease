package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.OutPort;

public class FluidLetExp extends LetExp {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FluidLetExp(Expression[] i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return true;
    }

    public void compile(Compilation compilation, Target target) {
        Target target2;
        Target ttarg;
        Compilation comp = compilation;
        Target target3 = target;
        CodeAttr code = comp.getCode();
        Type result_type = target3 instanceof IgnoreTarget ? null : getType();
        if (result_type == null) {
            ttarg = Target.Ignore;
        } else if (result_type == Type.pointer_type) {
            ttarg = Target.pushObject;
        } else {
            new StackTarget(result_type);
            ttarg = target2;
        }
        Scope scope = getVarScope();
        code.enterScope(scope);
        Variable ctx = scope.addVariable(code, Compilation.typeCallContext, (String) null);
        comp.loadCallContext();
        code.emitStore(ctx);
        Variable[] save = new Variable[this.inits.length];
        Declaration decl = firstDecl();
        doInits(decl, 0, save, comp, ctx);
        code.emitTryStart(true, result_type);
        this.body.compileWithPosition(comp, ttarg);
        code.emitFinallyStart();
        int i = 0;
        while (i < this.inits.length) {
            decl.load((AccessExp) null, 2, comp, Target.pushObject);
            code.emitLoad(save[i]);
            code.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setRestore", 1));
            i++;
            decl = decl.nextDecl();
        }
        code.emitTryCatchEnd();
        popScope(code);
        if (result_type != null) {
            target3.compileFromStack(comp, result_type);
        }
    }

    private void doInits(Declaration declaration, int i, Variable[] variableArr, Compilation compilation, Variable variable) {
        Declaration decl = declaration;
        int i2 = i;
        Variable[] save = variableArr;
        Compilation comp = compilation;
        Variable ctx = variable;
        if (i2 < this.inits.length) {
            CodeAttr code = comp.getCode();
            save[i2] = code.addLocal(Type.pointer_type);
            Variable allocateVariable = decl.allocateVariable(code);
            decl.base.load((AccessExp) null, 2, comp, Target.pushObject);
            code.emitDup();
            code.emitStore(decl.getVariable());
            this.inits[i2].compile(comp, Target.pushObject);
            doInits(decl.nextDecl(), i2 + 1, save, comp, ctx);
            code.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setWithSave", 1));
            code.emitStore(save[i2]);
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitFluidLetExp(this, d);
    }

    public void print(OutPort out) {
        print(out, "(FluidLet", ")");
    }
}
