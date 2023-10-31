package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class ExitExp extends Expression {
    BlockExp block;
    Expression result;

    public ExitExp(Expression result2, BlockExp block2) {
        this.result = result2;
        this.block = block2;
    }

    public ExitExp(BlockExp block2) {
        this.result = QuoteExp.voidExp;
        this.block = block2;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void apply(CallContext ctx) throws Throwable {
        Throwable th;
        Throwable th2 = th;
        new BlockExitException(this, this.result.eval(ctx));
        throw th2;
    }

    public void compile(Compilation compilation, Target target) {
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        (this.result == null ? QuoteExp.voidExp : this.result).compileWithPosition(comp, this.block.exitTarget);
        this.block.exitableBlock.exit();
    }

    /* access modifiers changed from: protected */
    public Expression deepCopy(IdentityHashTable identityHashTable) {
        IdentityHashTable mapper = identityHashTable;
        Expression res = deepCopy(this.result, mapper);
        if (res == null && this.result != null) {
            return null;
        }
        Object b = mapper.get(this.block);
        ExitExp exitExp = r9;
        ExitExp exitExp2 = new ExitExp(res, b == null ? this.block : (BlockExp) b);
        ExitExp copy = exitExp;
        copy.flags = getFlags();
        return copy;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitExitExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> visitor, D d) {
        this.result = visitor.visitAndUpdate(this.result, d);
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.startLogicalBlock("(Exit", false, ")");
        out.writeSpaceFill();
        if (this.block == null || this.block.label == null) {
            out.print("<unknown>");
        } else {
            out.print(this.block.label.getName());
        }
        if (this.result != null) {
            out.writeSpaceLinear();
            this.result.print(out);
        }
        out.endLogicalBlock(")");
    }

    public Type getType() {
        return Type.neverReturnsType;
    }
}
