package gnu.bytecode;

public class SwitchState {
    Label after_label;
    Label cases_label;
    Label defaultLabel;
    Label[] labels;
    int maxValue;
    int minValue;
    int numCases = 0;
    TryState outerTry;
    Label switch_label;
    int[] values;

    public int getMaxValue() {
        return this.maxValue;
    }

    public int getNumCases() {
        return this.numCases;
    }

    public SwitchState(CodeAttr codeAttr) {
        Label label;
        Label label2;
        Label label3;
        CodeAttr code = codeAttr;
        new Label(code);
        this.switch_label = label;
        new Label(code);
        this.cases_label = label2;
        new Label(code);
        this.after_label = label3;
        this.outerTry = code.try_stack;
    }

    public void switchValuePushed(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        Type popType = code.popType();
        this.cases_label.setTypes(code);
        code.fixupChain(this.cases_label, this.switch_label);
    }

    public boolean addCase(int value, CodeAttr codeAttr) {
        Label label;
        CodeAttr code = codeAttr;
        new Label(code);
        Label label2 = label;
        label2.setTypes(this.cases_label);
        label2.define(code);
        return insertCase(value, label2, code);
    }

    public boolean addCaseGoto(int value, CodeAttr codeAttr, Label label) {
        CodeAttr code = codeAttr;
        Label label2 = label;
        boolean ok = insertCase(value, label2, code);
        label2.setTypes(this.cases_label);
        code.setUnreachable();
        return ok;
    }

    public void addDefault(CodeAttr codeAttr) {
        Label label;
        Throwable th;
        CodeAttr code = codeAttr;
        new Label(code);
        Label label2 = label;
        label2.setTypes(this.cases_label);
        label2.define(code);
        if (this.defaultLabel != null) {
            Throwable th2 = th;
            new Error();
            throw th2;
        }
        this.defaultLabel = label2;
    }

    public boolean insertCase(int i, Label label, CodeAttr codeAttr) {
        int copyBefore;
        int value = i;
        Label label2 = label;
        CodeAttr codeAttr2 = codeAttr;
        if (this.values == null) {
            this.values = new int[10];
            this.labels = new Label[10];
            this.numCases = 1;
            int i2 = value;
            this.maxValue = i2;
            this.minValue = i2;
            this.values[0] = value;
            this.labels[0] = label2;
            return true;
        }
        int[] old_values = this.values;
        Label[] old_labels = this.labels;
        if (this.numCases >= this.values.length) {
            this.values = new int[(2 * this.numCases)];
            this.labels = new Label[(2 * this.numCases)];
        }
        if (value < this.minValue) {
            copyBefore = 0;
            this.minValue = value;
        } else if (value > this.maxValue) {
            copyBefore = this.numCases;
            this.maxValue = value;
        } else {
            int low = 0;
            int hi = this.numCases - 1;
            copyBefore = 0;
            while (low <= hi) {
                copyBefore = (low + hi) >>> 1;
                if (old_values[copyBefore] >= value) {
                    hi = copyBefore - 1;
                } else {
                    copyBefore++;
                    low = copyBefore;
                }
            }
            if (value == old_values[copyBefore]) {
                return false;
            }
        }
        int copyAfter = this.numCases - copyBefore;
        System.arraycopy(old_values, copyBefore, this.values, copyBefore + 1, copyAfter);
        System.arraycopy(old_values, 0, this.values, 0, copyBefore);
        this.values[copyBefore] = value;
        System.arraycopy(old_labels, copyBefore, this.labels, copyBefore + 1, copyAfter);
        System.arraycopy(old_labels, 0, this.labels, 0, copyBefore);
        this.labels[copyBefore] = label2;
        this.numCases++;
        return true;
    }

    public void exitSwitch(CodeAttr codeAttr) {
        Throwable th;
        CodeAttr code = codeAttr;
        if (this.outerTry != code.try_stack) {
            Throwable th2 = th;
            new Error("exitSwitch cannot exit through a try");
            throw th2;
        }
        code.emitGoto(this.after_label);
    }

    public void finish(CodeAttr codeAttr) {
        Label lab;
        Label label;
        CodeAttr code = codeAttr;
        if (this.defaultLabel == null) {
            new Label(code);
            this.defaultLabel = label;
            this.defaultLabel.define(code);
            ClassType ex = ClassType.make("java.lang.RuntimeException");
            code.emitNew(ex);
            code.emitDup((Type) ex);
            code.emitPushString("bad case value!");
            code.emitInvokeSpecial(ex.addMethod("<init>", 1, new Type[]{Type.string_type}, (Type) Type.voidType));
            code.emitThrow();
        }
        code.fixupChain(this.switch_label, this.after_label);
        if (this.numCases <= 1) {
            code.pushType(Type.intType);
            if (this.numCases == 1) {
                if (this.minValue == 0) {
                    code.emitIfIntEqZero();
                } else {
                    code.emitPushInt(this.minValue);
                    code.emitIfEq();
                }
                code.emitGoto(this.labels[0]);
                code.emitElse();
                code.emitGoto(this.defaultLabel);
                code.emitFi();
            } else {
                code.emitPop(1);
                code.emitGoto(this.defaultLabel);
            }
        } else if (2 * this.numCases >= this.maxValue - this.minValue) {
            code.reserve(13 + (4 * ((this.maxValue - this.minValue) + 1)));
            code.fixupAdd(2, (Label) null);
            code.put1(170);
            code.fixupAdd(3, this.defaultLabel);
            code.f55PC += 4;
            code.put4(this.minValue);
            code.put4(this.maxValue);
            int index = 0;
            for (int i = this.minValue; i <= this.maxValue; i++) {
                if (this.values[index] == i) {
                    int i2 = index;
                    index++;
                    lab = this.labels[i2];
                } else {
                    lab = this.defaultLabel;
                }
                code.fixupAdd(3, lab);
                code.f55PC += 4;
            }
        } else {
            code.reserve(9 + (8 * this.numCases));
            code.fixupAdd(2, (Label) null);
            code.put1(171);
            code.fixupAdd(3, this.defaultLabel);
            code.f55PC += 4;
            code.put4(this.numCases);
            for (int index2 = 0; index2 < this.numCases; index2++) {
                code.put4(this.values[index2]);
                code.fixupAdd(3, this.labels[index2]);
                code.f55PC += 4;
            }
        }
        code.fixupChain(this.after_label, this.cases_label);
    }
}
