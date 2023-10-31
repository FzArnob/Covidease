package gnu.expr;

import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.math.IntNum;

public class SetExp extends AccessExp {
    public static final int BAD_SHORT = 65536;
    public static final int DEFINING_FLAG = 2;
    public static final int GLOBAL_FLAG = 4;
    public static final int HAS_VALUE = 64;
    public static final int PREFER_BINDING2 = 8;
    public static final int PROCEDURE = 16;
    public static final int SET_IF_UNBOUND = 32;
    Expression new_value;

    public SetExp(Object symbol, Expression val) {
        this.symbol = symbol;
        this.new_value = val;
    }

    public SetExp(Declaration declaration, Expression val) {
        Declaration decl = declaration;
        this.binding = decl;
        this.symbol = decl.getSymbol();
        this.new_value = val;
    }

    public static SetExp makeDefinition(Object symbol, Expression val) {
        SetExp setExp;
        new SetExp(symbol, val);
        SetExp sexp = setExp;
        sexp.setDefining(true);
        return sexp;
    }

    public static SetExp makeDefinition(Declaration decl, Expression val) {
        SetExp setExp;
        new SetExp(decl, val);
        SetExp sexp = setExp;
        sexp.setDefining(true);
        return sexp;
    }

    public final Expression getNewValue() {
        return this.new_value;
    }

    public final boolean isDefining() {
        return (this.flags & 2) != 0;
    }

    public final void setDefining(boolean value) {
        if (value) {
            this.flags |= 2;
            return;
        }
        this.flags &= -3;
    }

    public final boolean getHasValue() {
        return (this.flags & 64) != 0;
    }

    public final void setHasValue(boolean value) {
        if (value) {
            this.flags |= 64;
            return;
        }
        this.flags &= -65;
    }

    public final boolean isFuncDef() {
        return (this.flags & 16) != 0;
    }

    public final void setFuncDef(boolean value) {
        if (value) {
            this.flags |= 16;
            return;
        }
        this.flags &= -17;
    }

    public final boolean isSetIfUnbound() {
        return (this.flags & 32) != 0;
    }

    public final void setSetIfUnbound(boolean value) {
        if (value) {
            this.flags |= 32;
            return;
        }
        this.flags &= -33;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public void apply(CallContext callContext) throws Throwable {
        Symbol symbol;
        CallContext ctx = callContext;
        Environment env = Environment.getCurrent();
        if (this.symbol instanceof Symbol) {
            symbol = (Symbol) this.symbol;
        } else {
            symbol = env.getSymbol(this.symbol.toString());
        }
        Symbol sym = symbol;
        Object property = null;
        Language language = Language.getDefaultLanguage();
        if (isFuncDef() && language.hasSeparateFunctionNamespace()) {
            property = EnvironmentKey.FUNCTION;
        }
        if (isSetIfUnbound()) {
            Location loc = env.getLocation(sym, property);
            if (!loc.isBound()) {
                loc.set(this.new_value.eval(env));
            }
            if (getHasValue()) {
                ctx.writeValue(loc);
                return;
            }
            return;
        }
        Object new_val = this.new_value.eval(env);
        if (this.binding != null && !(this.binding.context instanceof ModuleExp)) {
            Object[] evalFrame = ctx.evalFrames[ScopeExp.nesting(this.binding.context)];
            if (this.binding.isIndirectBinding()) {
                if (isDefining()) {
                    evalFrame[this.binding.evalIndex] = Location.make(sym);
                }
                ((Location) evalFrame[this.binding.evalIndex]).set(this.new_value);
            } else {
                evalFrame[this.binding.evalIndex] = new_val;
            }
        } else if (isDefining()) {
            env.define(sym, property, new_val);
        } else {
            env.put(sym, property, new_val);
        }
        if (getHasValue()) {
            ctx.writeValue(new_val);
        }
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.InsnArg.wrapInstruction(InsnArg.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.inline(CodeShrinkVisitor.java:146)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:71)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    public void compile(gnu.expr.Compilation r22, gnu.expr.Target r23) {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            r3 = r23
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            boolean r15 = r15 instanceof gnu.expr.LambdaExp
            if (r15 == 0) goto L_0x001e
            r15 = r3
            boolean r15 = r15 instanceof gnu.expr.IgnoreTarget
            if (r15 == 0) goto L_0x001e
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            gnu.expr.LambdaExp r15 = (gnu.expr.LambdaExp) r15
            boolean r15 = r15.getInlineOnly()
            if (r15 == 0) goto L_0x001e
        L_0x001d:
            return
        L_0x001e:
            r15 = r2
            gnu.bytecode.CodeAttr r15 = r15.getCode()
            r5 = r15
            r15 = r1
            boolean r15 = r15.getHasValue()
            if (r15 == 0) goto L_0x0087
            r15 = r3
            boolean r15 = r15 instanceof gnu.expr.IgnoreTarget
            if (r15 != 0) goto L_0x0087
            r15 = 1
        L_0x0031:
            r6 = r15
            r15 = 0
            r7 = r15
            r15 = r1
            gnu.expr.Declaration r15 = r15.binding
            r8 = r15
            r15 = r8
            gnu.expr.Expression r15 = r15.getValue()
            r9 = r15
            r15 = r9
            boolean r15 = r15 instanceof gnu.expr.LambdaExp
            if (r15 == 0) goto L_0x0089
            r15 = r8
            gnu.expr.ScopeExp r15 = r15.context
            boolean r15 = r15 instanceof gnu.expr.ModuleExp
            if (r15 == 0) goto L_0x0089
            r15 = r8
            boolean r15 = r15.ignorable()
            if (r15 != 0) goto L_0x0089
            r15 = r9
            gnu.expr.LambdaExp r15 = (gnu.expr.LambdaExp) r15
            java.lang.String r15 = r15.getName()
            if (r15 == 0) goto L_0x0089
            r15 = r9
            r16 = r1
            r0 = r16
            gnu.expr.Expression r0 = r0.new_value
            r16 = r0
            r0 = r16
            if (r15 != r0) goto L_0x0089
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            gnu.expr.LambdaExp r15 = (gnu.expr.LambdaExp) r15
            r16 = r2
            gnu.bytecode.Field r15 = r15.compileSetField(r16)
        L_0x0072:
            r15 = r6
            if (r15 == 0) goto L_0x0324
            r15 = r7
            if (r15 != 0) goto L_0x0324
            java.lang.Error r15 = new java.lang.Error
            r20 = r15
            r15 = r20
            r16 = r20
            java.lang.String r17 = "SetExp.compile: not implemented - return value"
            r16.<init>(r17)
            throw r15
        L_0x0087:
            r15 = 0
            goto L_0x0031
        L_0x0089:
            r15 = r8
            boolean r15 = r15.shouldEarlyInit()
            if (r15 != 0) goto L_0x0097
            r15 = r8
            boolean r15 = r15.isAlias()
            if (r15 == 0) goto L_0x00d3
        L_0x0097:
            r15 = r8
            gnu.expr.ScopeExp r15 = r15.context
            boolean r15 = r15 instanceof gnu.expr.ModuleExp
            if (r15 == 0) goto L_0x00d3
            r15 = r1
            boolean r15 = r15.isDefining()
            if (r15 == 0) goto L_0x00d3
            r15 = r8
            boolean r15 = r15.ignorable()
            if (r15 != 0) goto L_0x00d3
            r15 = r8
            boolean r15 = r15.shouldEarlyInit()
            if (r15 == 0) goto L_0x00c1
            r15 = r8
            r16 = r1
            r0 = r16
            gnu.expr.Expression r0 = r0.new_value
            r16 = r0
            r17 = r2
            gnu.expr.BindingInitializer.create(r15, r16, r17)
        L_0x00c1:
            r15 = r6
            if (r15 == 0) goto L_0x0072
            r15 = r8
            r16 = r1
            r17 = 0
            r18 = r2
            gnu.expr.Target r19 = gnu.expr.Target.pushObject
            r15.load(r16, r17, r18, r19)
            r15 = 1
            r7 = r15
            goto L_0x0072
        L_0x00d3:
            r15 = r1
            r10 = r15
            r15 = r1
            gnu.expr.Declaration r15 = r15.contextDecl()
            r11 = r15
            r15 = r1
            boolean r15 = r15.isDefining()
            if (r15 != 0) goto L_0x00f7
        L_0x00e2:
            r15 = r8
            if (r15 == 0) goto L_0x00f7
            r15 = r8
            boolean r15 = r15.isAlias()
            if (r15 == 0) goto L_0x00f7
            r15 = r8
            gnu.expr.Expression r15 = r15.getValue()
            r9 = r15
            r15 = r9
            boolean r15 = r15 instanceof gnu.expr.ReferenceExp
            if (r15 != 0) goto L_0x010a
        L_0x00f7:
            r15 = r8
            boolean r15 = r15.ignorable()
            if (r15 == 0) goto L_0x012c
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            r16 = r2
            gnu.expr.Target r17 = gnu.expr.Target.Ignore
            r15.compile((gnu.expr.Compilation) r16, (gnu.expr.Target) r17)
            goto L_0x0072
        L_0x010a:
            r15 = r9
            gnu.expr.ReferenceExp r15 = (gnu.expr.ReferenceExp) r15
            r12 = r15
            r15 = r12
            gnu.expr.Declaration r15 = r15.binding
            r13 = r15
            r15 = r13
            if (r15 != 0) goto L_0x0116
            goto L_0x00f7
        L_0x0116:
            r15 = r11
            if (r15 == 0) goto L_0x0121
            r15 = r13
            boolean r15 = r15.needsContext()
            if (r15 == 0) goto L_0x0121
            goto L_0x00f7
        L_0x0121:
            r15 = r12
            gnu.expr.Declaration r15 = r15.contextDecl()
            r11 = r15
            r15 = r12
            r10 = r15
            r15 = r13
            r8 = r15
            goto L_0x00e2
        L_0x012c:
            r15 = r8
            boolean r15 = r15.isAlias()
            if (r15 == 0) goto L_0x0171
            r15 = r1
            boolean r15 = r15.isDefining()
            if (r15 == 0) goto L_0x0171
            r15 = r8
            r16 = r1
            r17 = 2
            r18 = r2
            gnu.expr.Target r19 = gnu.expr.Target.pushObject
            r15.load(r16, r17, r18, r19)
            java.lang.String r15 = "gnu.mapping.IndirectableLocation"
            gnu.bytecode.ClassType r15 = gnu.bytecode.ClassType.make(r15)
            r12 = r15
            r15 = r5
            r16 = r12
            r15.emitCheckcast(r16)
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            r16 = r2
            gnu.expr.Target r17 = gnu.expr.Target.pushObject
            r15.compile((gnu.expr.Compilation) r16, (gnu.expr.Target) r17)
            r15 = r12
            java.lang.String r16 = "setAlias"
            r17 = 1
            gnu.bytecode.Method r15 = r15.getDeclaredMethod((java.lang.String) r16, (int) r17)
            r13 = r15
            r15 = r5
            r16 = r13
            r15.emitInvokeVirtual(r16)
            goto L_0x0072
        L_0x0171:
            r15 = r8
            boolean r15 = r15.isIndirectBinding()
            if (r15 == 0) goto L_0x0202
            r15 = r8
            r16 = r10
            r17 = 2
            r18 = r2
            gnu.expr.Target r19 = gnu.expr.Target.pushObject
            r15.load(r16, r17, r18, r19)
            r15 = r1
            boolean r15 = r15.isSetIfUnbound()
            if (r15 == 0) goto L_0x01c4
            r15 = r6
            if (r15 == 0) goto L_0x0194
            r15 = r5
            r15.emitDup()
            r15 = 1
            r7 = r15
        L_0x0194:
            r15 = r5
            gnu.bytecode.Scope r15 = r15.pushScope()
            r15 = r5
            r15.emitDup()
            r15 = r5
            gnu.bytecode.ClassType r16 = gnu.expr.Compilation.typeLocation
            gnu.bytecode.Variable r15 = r15.addLocal(r16)
            r12 = r15
            r15 = r5
            r16 = r12
            r15.emitStore(r16)
            r15 = r5
            gnu.bytecode.ClassType r16 = gnu.expr.Compilation.typeLocation
            java.lang.String r17 = "isBound"
            r18 = 0
            gnu.bytecode.Method r16 = r16.getDeclaredMethod((java.lang.String) r17, (int) r18)
            r15.emitInvokeVirtual(r16)
            r15 = r5
            r15.emitIfIntEqZero()
            r15 = r5
            r16 = r12
            r15.emitLoad(r16)
        L_0x01c4:
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            r16 = r2
            gnu.expr.Target r17 = gnu.expr.Target.pushObject
            r15.compile((gnu.expr.Compilation) r16, (gnu.expr.Target) r17)
            r15 = r6
            if (r15 == 0) goto L_0x01de
            r15 = r1
            boolean r15 = r15.isSetIfUnbound()
            if (r15 != 0) goto L_0x01de
            r15 = r5
            r15.emitDupX()
            r15 = 1
            r7 = r15
        L_0x01de:
            java.lang.String r15 = "set"
            r12 = r15
            r15 = r5
            gnu.bytecode.ClassType r16 = gnu.expr.Compilation.typeLocation
            r17 = r12
            r18 = 1
            gnu.bytecode.Method r16 = r16.getDeclaredMethod((java.lang.String) r17, (int) r18)
            r15.emitInvokeVirtual(r16)
            r15 = r1
            boolean r15 = r15.isSetIfUnbound()
            if (r15 == 0) goto L_0x0200
            r15 = r5
            r15.emitFi()
            r15 = r5
            gnu.bytecode.Scope r15 = r15.popScope()
        L_0x0200:
            goto L_0x0072
        L_0x0202:
            r15 = r8
            boolean r15 = r15.isSimple()
            if (r15 == 0) goto L_0x026b
            r15 = r8
            gnu.bytecode.Type r15 = r15.getType()
            r4 = r15
            r15 = r8
            gnu.bytecode.Variable r15 = r15.getVariable()
            r12 = r15
            r15 = r12
            if (r15 != 0) goto L_0x0220
            r15 = r8
            r16 = r5
            gnu.bytecode.Variable r15 = r15.allocateVariable(r16)
            r12 = r15
        L_0x0220:
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            r16 = r8
            int r15 = canUseInc(r15, r16)
            r13 = r15
            r15 = r13
            r16 = 65536(0x10000, float:9.18355E-41)
            r0 = r16
            if (r15 == r0) goto L_0x024f
            r15 = r2
            gnu.bytecode.CodeAttr r15 = r15.getCode()
            r16 = r12
            r17 = r13
            r0 = r17
            short r0 = (short) r0
            r17 = r0
            r15.emitInc(r16, r17)
            r15 = r6
            if (r15 == 0) goto L_0x024d
            r15 = r5
            r16 = r12
            r15.emitLoad(r16)
            r15 = 1
            r7 = r15
        L_0x024d:
            goto L_0x0072
        L_0x024f:
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            r16 = r2
            r17 = r8
            r15.compile((gnu.expr.Compilation) r16, (gnu.expr.Declaration) r17)
            r15 = r6
            if (r15 == 0) goto L_0x0264
            r15 = r5
            r16 = r4
            r15.emitDup((gnu.bytecode.Type) r16)
            r15 = 1
            r7 = r15
        L_0x0264:
            r15 = r5
            r16 = r12
            r15.emitStore(r16)
            goto L_0x024d
        L_0x026b:
            r15 = r8
            gnu.expr.ScopeExp r15 = r15.context
            boolean r15 = r15 instanceof gnu.expr.ClassExp
            if (r15 == 0) goto L_0x02cc
            r15 = r8
            gnu.bytecode.Field r15 = r15.field
            if (r15 != 0) goto L_0x02cc
            r15 = r1
            r16 = 16
            boolean r15 = r15.getFlag(r16)
            if (r15 != 0) goto L_0x02cc
            r15 = r8
            gnu.expr.ScopeExp r15 = r15.context
            gnu.expr.ClassExp r15 = (gnu.expr.ClassExp) r15
            boolean r15 = r15.isMakingClassPair()
            if (r15 == 0) goto L_0x02cc
            java.lang.String r15 = "set"
            r16 = r8
            java.lang.String r16 = r16.getName()
            java.lang.String r15 = gnu.expr.ClassExp.slotToMethodName(r15, r16)
            r12 = r15
            r15 = r8
            gnu.expr.ScopeExp r15 = r15.context
            gnu.expr.ClassExp r15 = (gnu.expr.ClassExp) r15
            r13 = r15
            r15 = r13
            gnu.bytecode.ClassType r15 = r15.type
            r16 = r12
            r17 = 1
            gnu.bytecode.Method r15 = r15.getDeclaredMethod((java.lang.String) r16, (int) r17)
            r14 = r15
            r15 = r13
            r16 = r2
            r15.loadHeapFrame(r16)
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            r16 = r2
            r17 = r8
            r15.compile((gnu.expr.Compilation) r16, (gnu.expr.Declaration) r17)
            r15 = r6
            if (r15 == 0) goto L_0x02c4
            r15 = r5
            r15.emitDupX()
            r15 = 1
            r7 = r15
        L_0x02c4:
            r15 = r5
            r16 = r14
            r15.emitInvoke(r16)
            goto L_0x0072
        L_0x02cc:
            r15 = r8
            gnu.bytecode.Field r15 = r15.field
            r12 = r15
            r15 = r12
            boolean r15 = r15.getStaticFlag()
            if (r15 != 0) goto L_0x02df
            r15 = r8
            r16 = r11
            r17 = r2
            r15.loadOwningObject(r16, r17)
        L_0x02df:
            r15 = r12
            gnu.bytecode.Type r15 = r15.getType()
            r4 = r15
            r15 = r1
            gnu.expr.Expression r15 = r15.new_value
            r16 = r2
            r17 = r8
            r15.compile((gnu.expr.Compilation) r16, (gnu.expr.Declaration) r17)
            r15 = r2
            r16 = r12
            gnu.bytecode.ClassType r16 = r16.getDeclaringClass()
            r15.usedClass(r16)
            r15 = r12
            boolean r15 = r15.getStaticFlag()
            if (r15 == 0) goto L_0x0313
            r15 = r6
            if (r15 == 0) goto L_0x030b
            r15 = r5
            r16 = r4
            r15.emitDup((gnu.bytecode.Type) r16)
            r15 = 1
            r7 = r15
        L_0x030b:
            r15 = r5
            r16 = r12
            r15.emitPutStatic(r16)
            goto L_0x0072
        L_0x0313:
            r15 = r6
            if (r15 == 0) goto L_0x031c
            r15 = r5
            r15.emitDupX()
            r15 = 1
            r7 = r15
        L_0x031c:
            r15 = r5
            r16 = r12
            r15.emitPutField(r16)
            goto L_0x0072
        L_0x0324:
            r15 = r6
            if (r15 == 0) goto L_0x0335
            r15 = r3
            r16 = r2
            r17 = r1
            gnu.bytecode.Type r17 = r17.getType()
            r15.compileFromStack(r16, r17)
        L_0x0333:
            goto L_0x001d
        L_0x0335:
            r15 = r2
            gnu.mapping.Values r16 = gnu.mapping.Values.empty
            r17 = r3
            r15.compileConstant(r16, r17)
            goto L_0x0333
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.SetExp.compile(gnu.expr.Compilation, gnu.expr.Target):void");
    }

    public static int canUseInc(Expression expression, Declaration declaration) {
        int sign;
        Expression rhs = expression;
        Declaration target = declaration;
        Variable var = target.getVariable();
        if (target.isSimple() && var.getType().getImplementationType().promote() == Type.intType && (rhs instanceof ApplyExp)) {
            ApplyExp applyExp = (ApplyExp) rhs;
            ApplyExp aexp = applyExp;
            if (applyExp.getArgCount() == 2) {
                Object func = aexp.getFunction().valueIfConstant();
                if (func == AddOp.$Pl) {
                    sign = 1;
                } else if (func == AddOp.$Mn) {
                    sign = -1;
                }
                Expression arg0 = aexp.getArg(0);
                Expression arg1 = aexp.getArg(1);
                if ((arg0 instanceof QuoteExp) && sign > 0) {
                    Expression tmp = arg1;
                    arg1 = arg0;
                    arg0 = tmp;
                }
                if (arg0 instanceof ReferenceExp) {
                    ReferenceExp ref0 = (ReferenceExp) arg0;
                    if (ref0.getBinding() == target && !ref0.getDontDereference()) {
                        Object value1 = arg1.valueIfConstant();
                        if (value1 instanceof Integer) {
                            int val1 = ((Integer) value1).intValue();
                            if (sign < 0) {
                                val1 = -val1;
                            }
                            if (((short) val1) == val1) {
                                return val1;
                            }
                        } else if (value1 instanceof IntNum) {
                            IntNum int1 = (IntNum) value1;
                            int hi = 32767;
                            int lo = -32767;
                            if (sign > 0) {
                                lo--;
                            } else {
                                hi = 32767 + 1;
                            }
                            if (IntNum.compare(int1, (long) lo) >= 0 && IntNum.compare(int1, (long) hi) <= 0) {
                                return sign * int1.intValue();
                            }
                        }
                    }
                }
            }
        }
        return 65536;
    }

    public final Type getType() {
        return !getHasValue() ? Type.voidType : this.binding == null ? Type.pointer_type : this.binding.getType();
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitSetExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> visitor, D d) {
        this.new_value = visitor.visitAndUpdate(this.new_value, d);
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.startLogicalBlock(isDefining() ? "(Define" : "(Set", ")", 2);
        out.writeSpaceFill();
        printLineColumn(out);
        if (this.binding == null || this.symbol.toString() != this.binding.getName()) {
            out.print('/');
            out.print(this.symbol);
        }
        if (this.binding != null) {
            out.print('/');
            out.print((Object) this.binding);
        }
        out.writeSpaceLinear();
        this.new_value.print(out);
        out.endLogicalBlock(")");
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("SetExp[").append(this.symbol).append(":=").append(this.new_value).append(']').toString();
    }
}
