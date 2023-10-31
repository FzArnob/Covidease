package gnu.bytecode;

public class ExitableBlock {
    CodeAttr code;
    TryState currentTryState;
    Label endLabel;
    TryState initialTryState;
    ExitableBlock nextCase;
    ExitableBlock outer;
    Type resultType;
    Variable resultVariable;
    int switchCase;

    ExitableBlock(Type type, CodeAttr codeAttr, boolean runFinallyBlocks) {
        Label label;
        Type resultType2 = type;
        CodeAttr code2 = codeAttr;
        this.code = code2;
        this.resultType = resultType2;
        this.initialTryState = code2.try_stack;
        if (runFinallyBlocks && resultType2 != null) {
            Scope pushScope = code2.pushScope();
            Variable var = code2.addLocal(resultType2);
            this.resultVariable = var;
            code2.emitStoreDefaultValue(var);
            CodeAttr codeAttr2 = code2;
            int i = codeAttr2.exitableBlockLevel + 1;
            int i2 = i;
            codeAttr2.exitableBlockLevel = i2;
            this.switchCase = i;
        }
        new Label(code2);
        this.endLabel = label;
    }

    /* access modifiers changed from: package-private */
    public void finish() {
        if (this.resultVariable != null && this.code.reachableHere()) {
            this.code.emitStore(this.resultVariable);
        }
        this.endLabel.define(this.code);
        if (this.resultVariable != null) {
            this.code.emitLoad(this.resultVariable);
            Scope popScope = this.code.popScope();
            CodeAttr codeAttr = this.code;
            codeAttr.exitableBlockLevel--;
        }
    }

    public void exit() {
        if (this.resultVariable != null) {
            this.code.emitStore(this.resultVariable);
        }
        exit(TryState.outerHandler(this.code.try_stack, this.initialTryState));
    }

    public Label exitIsGoto() {
        if (TryState.outerHandler(this.code.try_stack, this.initialTryState) == this.initialTryState) {
            return this.endLabel;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void exit(TryState tryState) {
        TryState activeTry = tryState;
        if (activeTry == this.initialTryState) {
            this.code.emitGoto(this.endLabel);
        } else if (this.code.useJsr()) {
            TryState tryState2 = this.code.try_stack;
            while (true) {
                TryState stack = tryState2;
                if (stack != this.initialTryState) {
                    if (stack.finally_subr != null && stack.finally_ret_addr == null) {
                        this.code.emitJsr(stack.finally_subr);
                    }
                    tryState2 = stack.previous;
                } else {
                    this.code.emitGoto(this.endLabel);
                    return;
                }
            }
        } else {
            if (this.currentTryState == null) {
                linkCase(activeTry);
            }
            if (activeTry.saved_result != null) {
                this.code.emitStoreDefaultValue(activeTry.saved_result);
            }
            this.code.emitPushInt(this.switchCase);
            this.code.emitPushNull();
            this.code.emitGoto(activeTry.finally_subr);
        }
    }

    /* access modifiers changed from: package-private */
    public void linkCase(TryState tryState) {
        Throwable th;
        TryState tryState2 = tryState;
        if (this.currentTryState == tryState2) {
            return;
        }
        if (this.currentTryState != null) {
            Throwable th2 = th;
            new Error();
            throw th2;
        }
        this.nextCase = tryState2.exitCases;
        tryState2.exitCases = this;
        this.currentTryState = tryState2;
    }
}
