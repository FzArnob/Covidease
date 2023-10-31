package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class CatchClause extends LetExp {
    CatchClause next;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CatchClause() {
        super(new Expression[]{QuoteExp.voidExp});
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CatchClause(Object name, ClassType type) {
        this();
        Declaration addDeclaration = addDeclaration(name, type);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CatchClause(LambdaExp lambdaExp) {
        this();
        LambdaExp lexp = lambdaExp;
        Declaration decl = lexp.firstDecl();
        lexp.remove((Declaration) null, decl);
        add(decl);
        this.body = lexp.body;
    }

    public final CatchClause getNext() {
        return this.next;
    }

    public final void setNext(CatchClause next2) {
        CatchClause catchClause = next2;
        this.next = catchClause;
    }

    public final Expression getBody() {
        return this.body;
    }

    public final void setBody(Expression body) {
        Expression expression = body;
        this.body = expression;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Object evalVariable(int i, CallContext ctx) throws Throwable {
        int i2 = i;
        return ctx.value1;
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        CodeAttr code = comp.getCode();
        Variable catchVar = firstDecl().allocateVariable(code);
        code.enterScope(getVarScope());
        code.emitCatchStart(catchVar);
        this.body.compileWithPosition(comp, target);
        code.emitCatchEnd();
        Scope popScope = code.popScope();
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> visitor, D d) {
        this.body = visitor.visitAndUpdate(this.body, d);
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.writeSpaceLinear();
        out.startLogicalBlock("(Catch", ")", 2);
        out.writeSpaceFill();
        this.decls.printInfo(out);
        out.writeSpaceLinear();
        this.body.print(out);
        out.endLogicalBlock(")");
    }
}
