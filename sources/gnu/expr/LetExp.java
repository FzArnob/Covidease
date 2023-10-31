package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class LetExp extends ScopeExp {
    public Expression body;
    public Expression[] inits;

    public LetExp(Expression[] i) {
        this.inits = i;
    }

    public Expression getBody() {
        return this.body;
    }

    public void setBody(Expression body2) {
        Expression expression = body2;
        this.body = expression;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Object evalVariable(int i, CallContext ctx) throws Throwable {
        return this.inits[i].eval(ctx);
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        setIndexes();
        int level = ScopeExp.nesting(this);
        Object[] evalFrame = new Object[this.frameSize];
        Object[][] evalFrames = ctx.evalFrames;
        if (evalFrames == null) {
            evalFrames = new Object[(level + 10)][];
            ctx.evalFrames = evalFrames;
        } else if (level >= evalFrames.length) {
            Object[][] newFrames = new Object[(level + 10)][];
            System.arraycopy(evalFrames, 0, newFrames, 0, evalFrames.length);
            Object[][] objArr = newFrames;
            evalFrames = objArr;
            ctx.evalFrames = objArr;
        }
        Object[] oldFrame = evalFrames[level];
        evalFrames[level] = evalFrame;
        int i = 0;
        try {
            Declaration decl = firstDecl();
            while (decl != null) {
                if (this.inits[i] != QuoteExp.undefined_exp) {
                    Object value = evalVariable(i, ctx);
                    Type type = decl.type;
                    if (!(type == null || type == Type.pointer_type)) {
                        value = type.coerceFromObject(value);
                    }
                    if (decl.isIndirectBinding()) {
                        Object loc = decl.makeIndirectLocationFor();
                        loc.set(value);
                        value = loc;
                    }
                    evalFrame[i] = value;
                }
                decl = decl.nextDecl();
                i++;
            }
            this.body.apply(ctx);
            evalFrames[level] = oldFrame;
        } catch (Throwable th) {
            Throwable th2 = th;
            evalFrames[level] = oldFrame;
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void store_rest(Compilation compilation, int i, Declaration declaration) {
        Compilation comp = compilation;
        int i2 = i;
        Declaration decl = declaration;
        if (decl != null) {
            store_rest(comp, i2 + 1, decl.nextDecl());
            if (decl.needsInit()) {
                if (decl.isIndirectBinding()) {
                    CodeAttr code = comp.getCode();
                    if (this.inits[i2] == QuoteExp.undefined_exp) {
                        Object name = decl.getSymbol();
                        comp.compileConstant(name, Target.pushObject);
                        code.emitInvokeStatic(BindingInitializer.makeLocationMethod(name));
                    } else {
                        decl.pushIndirectBinding(comp);
                    }
                }
                decl.compileStore(comp);
            }
        }
    }

    public void compile(Compilation compilation, Target target) {
        Target varTarget;
        Expression expression;
        Object obj;
        Compilation comp = compilation;
        Target target2 = target;
        CodeAttr code = comp.getCode();
        Declaration decl = firstDecl();
        int i = 0;
        while (i < this.inits.length) {
            Expression init = this.inits[i];
            boolean needsInit = decl.needsInit();
            if (needsInit && decl.isSimple()) {
                Variable allocateVariable = decl.allocateVariable(code);
            }
            if (!needsInit || (decl.isIndirectBinding() && init == QuoteExp.undefined_exp)) {
                varTarget = Target.Ignore;
            } else {
                Type varType = decl.getType();
                varTarget = CheckedTarget.getInstance(decl);
                if (init == QuoteExp.undefined_exp) {
                    if (varType instanceof PrimType) {
                        new Byte((byte) 0);
                        new QuoteExp(obj);
                        init = expression;
                    } else if (!(varType == null || varType == Type.pointer_type)) {
                        init = QuoteExp.nullExp;
                    }
                }
            }
            init.compileWithPosition(comp, varTarget);
            i++;
            decl = decl.nextDecl();
        }
        code.enterScope(getVarScope());
        store_rest(comp, 0, firstDecl());
        this.body.compileWithPosition(comp, target2);
        popScope(code);
    }

    public final Type getType() {
        return this.body.getType();
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitLetExp(this, d);
    }

    public <R, D> void visitInitializers(ExpVisitor<R, D> expVisitor, D d) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        Declaration decl = firstDecl();
        int i = 0;
        while (i < this.inits.length) {
            Expression init0 = this.inits[i];
            if (init0 == null) {
                Throwable th3 = th;
                new StringBuilder();
                new Error(sb.append("null1 init for ").append(this).append(" i:").append(i).append(" d:").append(decl).toString());
                throw th3;
            }
            Expression init = visitor.visitAndUpdate(init0, d2);
            if (init == null) {
                Throwable th4 = th2;
                new StringBuilder();
                new Error(sb2.append("null2 init for ").append(this).append(" was:").append(init0).toString());
                throw th4;
            }
            this.inits[i] = init;
            if (decl.value == init0) {
                decl.value = init;
            }
            i++;
            decl = decl.nextDecl();
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        ExpVisitor<R, D> visitor = expVisitor;
        D d2 = d;
        visitInitializers(visitor, d2);
        if (visitor.exitValue == null) {
            this.body = visitor.visitAndUpdate(this.body, d2);
        }
    }

    public void print(OutPort out) {
        print(out, "(Let", ")");
    }

    public void print(OutPort outPort, String startTag, String str) {
        StringBuilder sb;
        OutPort out = outPort;
        String endTag = str;
        new StringBuilder();
        out.startLogicalBlock(sb.append(startTag).append("#").append(this.f60id).toString(), endTag, 2);
        out.writeSpaceFill();
        printLineColumn(out);
        out.startLogicalBlock("(", false, ")");
        int i = 0;
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            if (i > 0) {
                out.writeSpaceFill();
            }
            out.startLogicalBlock("(", false, ")");
            decl.printInfo(out);
            if (this.inits != null) {
                out.writeSpaceFill();
                out.print('=');
                out.writeSpaceFill();
                if (i >= this.inits.length) {
                    out.print("<missing init>");
                } else if (this.inits[i] == null) {
                    out.print("<null>");
                } else {
                    this.inits[i].print(out);
                }
                i++;
            }
            out.endLogicalBlock(")");
        }
        out.endLogicalBlock(")");
        out.writeSpaceLinear();
        if (this.body == null) {
            out.print("<null body>");
        } else {
            this.body.print(out);
        }
        out.endLogicalBlock(endTag);
    }
}
